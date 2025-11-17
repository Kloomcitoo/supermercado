/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Model;

import jakarta.persistence.*;

/**
 *
 * @author mariana01colorado
 */

@Entity
@Table(name="empleados")

public class empleado {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long Id;
    
    private String nombre;
   
    @Column(name="email_address", unique = true,nullable=false)
    private String email;
    
    @Column(name="cedula", nullable=false)
    private String cedula;
    
    
    @Enumerated(EnumType.STRING)
    @Column(name = "rol", nullable = true)
    private rolEmpleado rol;
    
    
    private Boolean activo = true;

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public rolEmpleado getRol() {
        return rol;
    }

    public void setRol(rolEmpleado rol) {
        this.rol = rol;
    }
    
    
    
    
    
    
}
