package com.example.proyectoFinalExpertos.service;

import com.example.proyectoFinalExpertos.model.User;

import java.util.List;

public interface UserService {

    List<User> findAllFromRepository();

    User createUser(User user);
    User updateUser(Long id, User user);

    List<User> findAll();
    User findOne(Long id);
    List<User> findAllByName(String name);

    }
