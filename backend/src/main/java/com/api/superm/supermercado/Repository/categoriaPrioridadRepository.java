/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Repository;

import com.api.superm.supermercado.Model.categoriaPrioridad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mariana01colorado
 */
public interface categoriaPrioridadRepository extends JpaRepository<categoriaPrioridad, Long>{
    
    Optional<categoriaPrioridad> findByNombre(String nombre);
    
}
