package com.example.proyectoFinalExpertos.dao;

import com.example.proyectoFinalExpertos.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAllFromSession();

    User findById(Long id);
    User findByUserName(String username);
}
