package com.example.proyectoFinalExpertos.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // Opción 1: Permitir GET (POST, PUT y DELETE bloqueados por CSRF activado por defecto)
//                http
//            .authorizeRequests()
//            .anyRequest().permitAll()
//            .and()
//            .httpBasic();

        // Opcion 2: restringir GET (POST, PUT y DELETE siguen bloqueados por CSRF activado por defecto)
//        http
//            .authorizeRequests()
//            .anyRequest().authenticated()
//            .and()
//            .httpBasic();


        // Opcion 3: Dejar todos publicos menos DELETE

        http
                .authorizeRequests()
                .mvcMatchers( HttpMethod.GET,"/api/experts").permitAll()
                .mvcMatchers( HttpMethod.GET,"/api/tags/**").permitAll()
                .mvcMatchers("/api/**")
                .authenticated()
                .and()
                .csrf().disable() // Deshabilita CSRF (habilitado por defecto) para que funcione permitAll sobre endpoints POST, PUT y DELETE
                .httpBasic();

    }
}
