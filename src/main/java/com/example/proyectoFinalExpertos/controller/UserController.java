package com.example.proyectoFinalExpertos.controller;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.User;
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

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * FIND USER BY USERNAME
     * @return String
     */
    @PostMapping("/users")
    public ResponseEntity<User>  findUserName(@RequestBody User user) throws URISyntaxException {
        log.debug("REST request to login an user: {} ", user);

        System.out.println(user);
        User checkUser = this.userService.findByUserName(user.getUsername());
        System.out.println(checkUser);

        if(checkUser.getPassword() == "1234")
            return ResponseEntity.ok().body(checkUser);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
