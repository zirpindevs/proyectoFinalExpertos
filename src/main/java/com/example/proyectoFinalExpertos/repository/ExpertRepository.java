package com.example.proyectoFinalExpertos.repository;

import com.example.proyectoFinalExpertos.model.Expert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertRepository extends JpaRepository<Expert, Long> {
    Page<Expert> findByNombre(String nombre, Pageable pageable);
    Page<Expert> findByEstado(String estado, Pageable pageable);
}
