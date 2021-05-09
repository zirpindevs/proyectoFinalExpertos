package com.example.proyectoFinalExpertos.repository;

import com.example.proyectoFinalExpertos.model.Tag;
import com.example.proyectoFinalExpertos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
