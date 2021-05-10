package com.example.proyectoFinalExpertos.security.service;

import com.example.proyectoFinalExpertos.model.User;
import com.example.proyectoFinalExpertos.repository.UserRepository;
import com.example.proyectoFinalExpertos.model.User;
import com.example.proyectoFinalExpertos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Autentica un usuario de la base de datos
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}
