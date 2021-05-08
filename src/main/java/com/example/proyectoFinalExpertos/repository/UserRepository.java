package com.example.proyectoFinalExpertos.repository;

import com.example.proyectoFinalExpertos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
