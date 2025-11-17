/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author mariana01colorado
 */

@Entity
@Table( name="atencion")
public class atencion {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    @ManyToOne
    @JoinColumn(name = "turno_id", nullable = false)
    private turno turno;
    
    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private empleado empleado;
    
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    
    private Long duracionSegundos;

    public empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(empleado empleado) {
        this.empleado = empleado;
    }
    
    
    

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public turno getTurno() {
        return turno;
    }

    public void setTurno(turno turno) {
        this.turno = turno;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getDuracionSegundos() {
        return duracionSegundos;
    }

    public void setDuracionSegundos(Long duracionSegundos) {
        this.duracionSegundos = duracionSegundos;
    }
    
}
