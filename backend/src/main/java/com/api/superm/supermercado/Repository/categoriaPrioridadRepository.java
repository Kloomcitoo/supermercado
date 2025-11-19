/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.superm.supermercado.Repository;

import com.api.superm.supermercado.Model.categoriaPrioridad;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;

/**
 *
 * @author mariana01colorado
 */
public interface categoriaPrioridadRepository extends JpaRepository<categoriaPrioridad, Long>{
    
    Optional<categoriaPrioridad> findByNombre(String nombre);
    
}

// Componente para inicializar categorías automáticamente
@Component
class CategoriaInitializer implements ApplicationRunner {
    
    @Autowired
    private categoriaPrioridadRepository categoriaRepository;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Verificar si ya existen categorías
        if (categoriaRepository.count() == 0) {
            // Crear categorías por defecto
            categoriaPrioridad adultoMayor = new categoriaPrioridad();
            adultoMayor.setNombre("Adulto mayor");
            adultoMayor.setNivel(1);
            adultoMayor.setRatio(3);
            adultoMayor.setDescripcion("Personas de la tercera edad con prioridad absoluta");
            categoriaRepository.save(adultoMayor);
            
            categoriaPrioridad embarazada = new categoriaPrioridad();
            embarazada.setNombre("Embarazada");
            embarazada.setNivel(1);
            embarazada.setRatio(3);
            embarazada.setDescripcion("Mujeres embarazadas con prioridad absoluta");
            categoriaRepository.save(embarazada);
            
            categoriaPrioridad clienteNormal = new categoriaPrioridad();
            clienteNormal.setNombre("Cliente normal");
            clienteNormal.setNivel(2);
            clienteNormal.setRatio(1);
            clienteNormal.setDescripcion("Clientes sin prioridad especial");
            categoriaRepository.save(clienteNormal);
        }
    }
}
