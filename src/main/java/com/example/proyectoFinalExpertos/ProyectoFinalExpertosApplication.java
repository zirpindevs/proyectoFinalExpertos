package com.example.proyectoFinalExpertos;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import util.HibernateUtil;

import java.time.Instant;

@SpringBootApplication
@ComponentScan({"com.example.proyectoFinalExpertos"})
public class ProyectoFinalExpertosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalExpertosApplication.class, args); }
}
