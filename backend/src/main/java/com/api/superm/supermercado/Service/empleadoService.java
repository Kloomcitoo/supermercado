/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Service;

import com.api.superm.supermercado.Model.empleado;
import com.api.superm.supermercado.Repository.empleadoRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author mariana01colorado
 */


@Service
@RequiredArgsConstructor
public class empleadoService {
    
    private final empleadoRepository empleadoRepository;
    
    public Optional<empleado> login(String email, String cedula) {
        return empleadoRepository.findByEmailAndCedula(email, cedula);
    }
    
}
