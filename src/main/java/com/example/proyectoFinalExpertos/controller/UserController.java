package com.example.proyectoFinalExpertos.controller;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.User;
import com.example.proyectoFinalExpertos.repository.UserRepository;
import com.example.proyectoFinalExpertos.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserServiceImpl userService;
    private final UserRepository userRepository;


    public UserController(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    /**
         * FIND USER BY MAIL
         * @return String
         */
    @PostMapping("/users")
    public ResponseEntity<User>  findUserName(@RequestBody User user) throws URISyntaxException {
        log.debug("REST request to login an user: {} ", user);

        System.out.println(user);
        User checkUser = userRepository.findByEmail((user.getEmail()));
        System.out.println(checkUser);

        if(checkUser.getPassword().equals("1234"))
            return ResponseEntity.ok().body(checkUser);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * CREATE USER
     *
     * @param userToCreate
     * @return ResponseEntity<User>
     * @throws URISyntaxException
     */
    @PostMapping("/users/create")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<User> createUser(@RequestBody User userToCreate) throws URISyntaxException {
        log.debug("REST request to create an user: {} ", userToCreate);

        if (userToCreate.getEmail() != null || userToCreate.getPassword() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        User createdUser = this.userService.createUser(userToCreate);

        return ResponseEntity
                .created(new URI("/api/expertos/" + createdUser.getEmail()))
                .body(createdUser);
    }
}
