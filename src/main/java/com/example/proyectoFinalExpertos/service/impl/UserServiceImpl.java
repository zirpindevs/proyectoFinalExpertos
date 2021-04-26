package com.example.proyectoFinalExpertos.service.impl;

import com.example.proyectoFinalExpertos.dao.UserDAO;
import com.example.proyectoFinalExpertos.model.User;
import com.example.proyectoFinalExpertos.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User findOne(Long id) {
        log.info("REST request to find one user by id");

        if(id == null)
            return null;
        return this.userDAO.findById(id);
    }

    @Override
    public List<User> findAllByName(String name) {
        log.info("REST request to find an user by name");

        if(name.isEmpty())
            return null;
        return this.userDAO.findAllByName(name);
    }


}
