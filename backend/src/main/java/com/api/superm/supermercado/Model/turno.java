/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

/**
 *
 * @author mariana01colorado
 */

@Entity
@Table(name = "turnos")
public class turno {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer numero;  

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private estadoTurno estado = estadoTurno.ESPERA;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaLlamado;
    private LocalDateTime fechaFinalizado;
    private Long tiempoEspera;
    
    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private categoriaPrioridad categoria;

    
    @ManyToOne
    @JoinColumn(name = "empleado_id", nullable = true)
    private empleado empleado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public estadoTurno getEstado() {
        return estado;
    }

    public void setEstado(estadoTurno estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaLlamado() {
        return fechaLlamado;
    }

    public void setFechaLlamado(LocalDateTime fechaLlamado) {
        this.fechaLlamado = fechaLlamado;
    }

    public LocalDateTime getFechaFinalizado() {
        return fechaFinalizado;
    }

    public void setFechaFinalizado(LocalDateTime fechaFinalizado) {
        this.fechaFinalizado = fechaFinalizado;
    }

    public Long getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(Long tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }


    public categoriaPrioridad getCategoria() {
        return categoria;
    }

    public void setCategoria(categoriaPrioridad categoria) {
        this.categoria = categoria;
    }

    public empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(empleado empleado) {
        this.empleado = empleado;
    }
    
}
