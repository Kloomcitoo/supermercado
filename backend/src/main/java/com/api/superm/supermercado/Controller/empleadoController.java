/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Controller;

import com.api.superm.supermercado.Model.empleado;
import com.api.superm.supermercado.Repository.empleadoRepository;
import com.api.superm.supermercado.Service.empleadoService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mariana01colorado
 */

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
public class empleadoController {
    
    private final empleadoService empleadoService;
    private final empleadoRepository empleadoRepository;
    
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String cedula) {
        return empleadoService.login(email, cedula)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).body("Credenciales inválidas"));
    }

    @GetMapping
    public List<empleado> listar() {
        return empleadoRepository.findAll();
    }

    @PostMapping
    public empleado crear(@RequestBody empleado empleado) {
        return empleadoRepository.save(empleado);
    }
    
}
