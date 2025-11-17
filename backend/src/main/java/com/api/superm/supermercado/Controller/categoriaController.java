/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Controller;

import com.api.superm.supermercado.Model.categoriaPrioridad;
import com.api.superm.supermercado.Repository.categoriaPrioridadRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mariana01colorado
 */
@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class categoriaController {
    
    private final categoriaPrioridadRepository categoriaRepository;

    @GetMapping
    public List<categoriaPrioridad> listar() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public categoriaPrioridad crear(@RequestBody categoriaPrioridad categoria) {
        return categoriaRepository.save(categoria);
    }
    
}
