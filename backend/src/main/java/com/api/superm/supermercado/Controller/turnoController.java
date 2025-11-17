/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Controller;

import com.api.superm.supermercado.Model.empleado;
import com.api.superm.supermercado.Model.turno;
import com.api.superm.supermercado.Repository.empleadoRepository;
import com.api.superm.supermercado.Service.turnoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mariana01colorado
 */

@RestController
@RequestMapping("/api/turnos")
@RequiredArgsConstructor
public class turnoController {
    
    private final turnoService turnoService;
    private final empleadoRepository empleadoRepository;
    
    @PostMapping("/crear/{categoriaId}")
    public ResponseEntity<turno> crear(@PathVariable Long categoriaId){
        return ResponseEntity.ok(turnoService.crearTurno(categoriaId));
    }
    
    @GetMapping
    public ResponseEntity<List<turno>> listar() {
        return ResponseEntity.ok(turnoService.listar());
    }
    
    @PutMapping("/llamar")
    public ResponseEntity<?> llamar() {
        return turnoService.llamarSiguiente()
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }
    
    @PutMapping("/finalizar/{id}/{empleadoId}")
    public ResponseEntity<turno> finalizar(@PathVariable Long id, @PathVariable Long empleadoId) {
        empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        return ResponseEntity.ok(turnoService.finalizar(id, empleado));
    }
    
    @DeleteMapping("/eliminar/{turnoId}/{empleadoId}")
    public ResponseEntity<?> eliminar(
            @PathVariable Long turnoId,
            @PathVariable Long empleadoId
    ) {
        empleado emp = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        turnoService.eliminar(turnoId, emp);

        return ResponseEntity.ok("Turno eliminado correctamente");
    }
    
}
