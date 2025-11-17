/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Repository;

import com.api.superm.supermercado.Model.estadoTurno;
import com.api.superm.supermercado.Model.turno;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mariana01colorado
 */
public interface turnoRepository extends JpaRepository<turno, Long> {
    List<turno> findByEstado(estadoTurno estado);
}
