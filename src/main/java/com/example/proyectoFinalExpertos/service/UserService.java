package com.example.proyectoFinalExpertos.service;

import com.example.proyectoFinalExpertos.model.Tag;
import com.example.proyectoFinalExpertos.model.User;

import java.util.List;

public interface UserService {
    User findOne(User user);
    User createUser(User user);

}
