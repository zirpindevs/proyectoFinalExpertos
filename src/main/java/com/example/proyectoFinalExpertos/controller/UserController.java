package com.example.proyectoFinalExpertos.controller;

import com.example.proyectoFinalExpertos.payload.request.LoginRequest;

import com.example.proyectoFinalExpertos.model.response.Response;
import com.example.proyectoFinalExpertos.model.response.ResponseLoggin;
import com.example.proyectoFinalExpertos.model.User;
import com.example.proyectoFinalExpertos.payload.request.SignupRequest;
import com.example.proyectoFinalExpertos.payload.response.JwtResponse;
import com.example.proyectoFinalExpertos.payload.response.MessageResponse;
import com.example.proyectoFinalExpertos.repository.UserRepository;
import com.example.proyectoFinalExpertos.security.jwt.JwtTokenUtil;
import com.example.proyectoFinalExpertos.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")

@RequestMapping("/api")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserServiceImpl userService;
    private final UserRepository userRepository;


    public UserController(UserServiceImpl userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenUtil jwtTokenUtil;


    /**
     * FIND CHECK USER
     * @return ResponseEntity<User>
     */
    @PostMapping("/users")
//    public ResponseLoggin checkUserName(@RequestBody User user) throws URISyntaxException {
    public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest){

            log.info("REST loginRequest to check: {} ", loginRequest);
//
//        User checkUser = userService.findOne(user);
//        ResponseLoggin response = new ResponseLoggin();
//
//
//        if(user.getEmail() == null || user.getPassword() == null) {
//            response.setResponse(new Response("Error con el usuario",
//                    new ResponseEntity(HttpStatus.BAD_REQUEST).getStatusCode()));
//        }
//        if (userService.findOne(user) != null) {
//            response.setEmailUser(checkUser.getEmail());
//            response.setResponse(new Response("usuario encontrado",
//                    new ResponseEntity(HttpStatus.OK).getStatusCode()));
//        }
//
//        return response;

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenUtil.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt));
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
