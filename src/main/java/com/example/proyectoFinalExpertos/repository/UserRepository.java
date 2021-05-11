package com.example.proyectoFinalExpertos.repository;

import com.example.proyectoFinalExpertos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Boolean existsByEmail(String email);

}
