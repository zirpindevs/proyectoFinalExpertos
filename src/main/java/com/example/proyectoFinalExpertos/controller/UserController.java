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
     * FIND CHECK USER
     * @param user
     * @return ResponseEntity<User>
     */
    @PostMapping("/users")
    public ResponseEntity<User> checkUserName(@RequestBody User user) throws URISyntaxException {
        log.debug("REST request to check an user: {} ", user);

        System.out.println(user);
        User checkUser = userService.findOne(user);

        if(user.getEmail() == null || user.getPassword() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (userService.findOne(user) != null) {
                return ResponseEntity.ok().body(checkUser);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

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

        if (userRepository.findByEmail(userToCreate.getEmail()) != null)
            return new ResponseEntity<>(HttpStatus.IM_USED);


        User createdUser = this.userService.createUser(userToCreate);

        return ResponseEntity
                .created(new URI("/api/expertos/" + createdUser.getEmail()))
                .body(createdUser);
    }
}
