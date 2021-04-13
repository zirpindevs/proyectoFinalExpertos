package com.example.proyectoFinalExpertos.controller;

import com.example.proyecto5hibernate.model.User;
import com.example.proyecto5hibernate.service.impl.UserServiceImpl;
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
     * CREATE USER
     *
     * @param user
     * @return ResponseEntity<User>
     * @throws URISyntaxException
     */
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) throws URISyntaxException {
        log.debug("REST request to create an user: {} ", user);

        if (user.getId() != null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        User createUser = this.userService.createUser(user);

        return ResponseEntity
                .created(new URI("/api/users/" + createUser.getName()))
                .body(createUser);
    }

    /**
     * UPDATE USER
     *
     * @param id
     * @param modifiedUser
     * @return ResponseEntity<User>
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User modifiedUser) {
        log.debug("REST request to update one user: {} ", modifiedUser);

        User updateUser = this.userService.updateUser(id, modifiedUser);

        if (updateUser.getId() == null) {
            log.warn("update user without id");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok().body(updateUser);
    }



    /**
     * FIND ALL USERS
     *
     * @return List<User>
     */
    @GetMapping("/users")
    public List<User> findAllUsers() {
        log.debug("REST request to find all users");
        return this.userService.findAllFromRepository();
    }

    /**
     * Find USER BY ID
     *
     * @param id
     * @return ResponseEntity<User>
     * @throws URISyntaxException
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserId(@PathVariable Long id) throws URISyntaxException {

        User findUser = this.userService.findOne(id);
        if (findUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return ResponseEntity.ok().body(findUser);

    }

    /**
     * FIND USER BY NAME
     * @param name
     * @return List<User>
     * @throws URISyntaxException
     */
    @GetMapping("/users/name/{name}")
    public List<User> findUserName(@PathVariable String name) throws URISyntaxException {

        return this.userService.findAllByName(name);

    }

}
