package com.example.proyectoFinalExpertos;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import util.HibernateUtil;

import java.time.Instant;

@SpringBootApplication
public class ProyectoFinalExpertosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoFinalExpertosApplication.class, args);

		addDataExample();
	}

	static void addDataExample(){
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		Expert expert1 = new Expert("Juan_Experto", "apellido1", "10000A", "master1", 30L, "pendiente", "inmediata", Instant.now());
		Expert expert2 = new Expert("Jose_Experto", "apellido2", "20000A", "master2", 50L, "pendiente", "inmediata", Instant.now());


		Tag tag1 = new Tag("tarea1", Instant.now());
		Tag tag2 = new Tag("tarea2", Instant.now());
		Tag tag3 = new Tag("tarea1", Instant.now());

		tag1.getExperts().add(expert1);
		tag2.getExperts().add(expert2);
		tag3.getExperts().add(expert1);

		session.save(expert1);
		session.save(expert2);

		session.save(tag1);
		session.save(tag2);
		session.save(tag3);

		session.getTransaction().commit();

		session.close();
		HibernateUtil.shutdown();
	}
}
