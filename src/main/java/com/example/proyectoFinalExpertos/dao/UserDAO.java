package com.example.proyectoFinalExpertos.dao;

import com.example.proyectoFinalExpertos.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAllFromSession();

    List<User> findAll();
    User findById(Long id);
    List<User> findAllByName(String name);
    User createUser(User user);
    User modifyUser(User user, User findedUser);



}
