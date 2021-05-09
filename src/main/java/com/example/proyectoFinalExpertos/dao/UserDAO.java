package com.example.proyectoFinalExpertos.dao;

import com.example.proyectoFinalExpertos.model.User;

import java.util.List;

public interface UserDAO {

    User createUser(User userToCreate);
    User findById(Long id);
    User findByEmail(String email);
}
