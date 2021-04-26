package com.example.proyectoFinalExpertos.service;

import com.example.proyectoFinalExpertos.model.User;

import java.util.List;

public interface UserService {
    User findOne(Long id);
    User findByUserName(String username);
    }
