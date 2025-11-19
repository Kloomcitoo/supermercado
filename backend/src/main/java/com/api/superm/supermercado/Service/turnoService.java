/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Service;

import com.api.superm.supermercado.Model.atencion;
import com.api.superm.supermercado.Model.categoriaPrioridad;
import com.api.superm.supermercado.Model.empleado;
import com.api.superm.supermercado.Model.estadoTurno;
import com.api.superm.supermercado.Model.rolEmpleado;
import com.api.superm.supermercado.Model.turno;
import com.api.superm.supermercado.Repository.atencionRepository;
import com.api.superm.supermercado.Repository.categoriaPrioridadRepository;
import com.api.superm.supermercado.Repository.empleadoRepository;
import com.api.superm.supermercado.Repository.turnoRepository;
import jakarta.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

/**
 *
 * @author mariana01colorado
 */


@Service
@RequiredArgsConstructor
public class turnoService {
    
    private final turnoRepository turnoRepository;
    private final categoriaPrioridadRepository categoriaRepository;
    private final atencionRepository atencionRepository;
    
    
    private int numeroCorrelativo = 1;  // contador de personas regulares
    
    public turno crearTurno(Long categoriaId){
        categoriaPrioridad categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoría no encontrada"));
        
        turno turno = new turno();
        turno.setNumero(numeroCorrelativo++);
        turno.setCategoria(categoria);
        turno.setEstado(estadoTurno.ESPERA);
        turno.setFechaCreacion(LocalDateTime.now());

        return turnoRepository.save(turno);
    }
    
    public List<turno> listar() {
        return turnoRepository.findAll();
    }
    
    
    @Transactional
    public Optional<turno> llamarSiguiente(){
        List<turno> enEspera = turnoRepository.findByEstado(estadoTurno.ESPERA);
        
        if(enEspera.isEmpty()) return Optional.empty();
        
        LocalDateTime ahora = LocalDateTime.now();
        
        // Primero: clientes normales con 3+ saltos (prioridad absoluta)
        List<turno> clientesConPrioridadTemporal = enEspera.stream()
                .filter(this::esClienteNormal)
                .filter(t -> t.getVecesSaltado() >= 3)
                .sorted(Comparator.comparing(turno::getFechaCreacion))
                .toList();
        
        if (!clientesConPrioridadTemporal.isEmpty()) {
            turno siguiente = clientesConPrioridadTemporal.get(0);
            siguiente.setEstado(estadoTurno.ATENDIDO);
            siguiente.setFechaLlamado(ahora);
            turnoRepository.save(siguiente);
            return Optional.of(siguiente);
        }
        
        // Segundo: turnos prioritarios normales
        List<turno> prioritarios = enEspera.stream()
                .filter(t -> !esClienteNormal(t))
                .sorted(Comparator.comparing(turno::getFechaCreacion))
                .toList();
        
        // Tercero: clientes normales (incrementar contador de saltos si hay prioritarios)
        List<turno> clientesNormales = enEspera.stream()
                .filter(this::esClienteNormal)
                .sorted(Comparator.comparing(turno::getFechaCreacion))
                .toList();
        
        Optional<turno> siguiente;
        
        // Si hay prioritarios disponibles, llamar al más antiguo y contar saltos de clientes normales
        if (!prioritarios.isEmpty()) {
            siguiente = Optional.of(prioritarios.get(0));
            
            // Incrementar contador de saltos para todos los clientes normales en espera
            clientesNormales.forEach(cliente -> {
                cliente.setVecesSaltado(cliente.getVecesSaltado() + 1);
                turnoRepository.save(cliente);
            });
        } 
        // Si no hay prioritarios, llamar al cliente normal más antiguo
        else if (!clientesNormales.isEmpty()) {
            siguiente = Optional.of(clientesNormales.get(0));
        } 
        else {
            siguiente = Optional.empty();
        }
        
        siguiente.ifPresent(t -> {
            t.setEstado(estadoTurno.ATENDIDO);
            t.setFechaLlamado(ahora);
            turnoRepository.save(t);
        });
        
        return siguiente;
    }
    
    private boolean esClienteNormal(turno t) {
        String nombreCategoria = t.getCategoria().getNombre();
        // Un turno es cliente normal SOLO si su categoría es exactamente "Cliente normal"
        // Cualquier otra categoría (Adulto Mayor, Embarazada, etc.) es prioritaria
        return nombreCategoria != null && nombreCategoria.trim().equalsIgnoreCase("Cliente normal");
    }
    
    @Transactional
    public turno finalizar(Long id, empleado empleado){
        turno t = turnoRepository.findById(id)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turno no encontrado"));
        
        // Validar que el turno esté en estado ATENDIDO
        if (t.getEstado() != estadoTurno.ATENDIDO) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "Solo se pueden finalizar turnos que estén en estado ATENDIDO");
        }
        
        // Validar que el turno tenga fecha de llamado
        if (t.getFechaLlamado() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "El turno no tiene fecha de llamado. Debe ser llamado primero.");
        }
        
        LocalDateTime ahora = LocalDateTime.now();
        t.setEstado(estadoTurno.FINALIZADO);
        t.setFechaFinalizado(ahora);
        
        // Calcular tiempo de espera (desde creación hasta llamado)
        t.setTiempoEspera(Duration.between(t.getFechaCreacion(), t.getFechaLlamado()).toSeconds());
        t.setEmpleado(empleado);
        turnoRepository.save(t);
        
        // Crear registro de atención
        atencion ate = new atencion();
        ate.setTurno(t);
        ate.setEmpleado(empleado);
        ate.setFechaInicio(t.getFechaLlamado());
        ate.setFechaFin(ahora);
        ate.setDuracionSegundos(
                Duration.between(t.getFechaLlamado(), ahora).toSeconds()
        );

        atencionRepository.save(ate);

        return t;
    }
   
    public void eliminar(Long idTurno, empleado empleadoAutenticado) {

        turno t = turnoRepository.findById(idTurno)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Turno no encontrado"));

        if (empleadoAutenticado.getRol() != rolEmpleado.SUPERVISOR) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Solo un supervisor puede eliminar turnos");
        }

        turnoRepository.delete(t);
    }
}
