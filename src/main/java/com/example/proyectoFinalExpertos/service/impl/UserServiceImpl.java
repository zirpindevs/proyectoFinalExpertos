package com.example.proyectoFinalExpertos.service.impl;

import com.example.proyectoFinalExpertos.dao.UserDAO;
import com.example.proyectoFinalExpertos.model.User;
import com.example.proyectoFinalExpertos.repository.UserRepository;
import com.example.proyectoFinalExpertos.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserDAO userDAO;
    private final UserRepository userRepository;

    public UserServiceImpl(UserDAO userDAO, UserRepository userRepository) {
        this.userDAO = userDAO;
        this.userRepository = userRepository;
    }

    @Override
    public User findOne(User user) {
        log.info("REST request to find one user by id");
        return this.userDAO.findUser(user);
    }

    @Override
    public User createUser(User user) {
        log.info("REST request to create an User");

        User userCreated = null;

        try{
                userCreated = userRepository.save(user);
            }catch(Exception e) {
                log.error("Cannot create the user: {} , error : {}", user, e);
            }

        return userCreated;
    }

}
