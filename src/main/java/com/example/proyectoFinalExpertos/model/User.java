package com.example.proyectoFinalExpertos.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="dni")
    private String dni;

    @Column(name="is_active")
    private Boolean isActive;

    @Column(name="created_date")
    private Instant createdDate;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getDni() {
        return dni;
    }

    public User setDni(String dni) {
        this.dni = dni;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public User setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public User setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", dni='" + dni + '\'' +
                ", isActive=" + isActive +
                ", createdDate=" + createdDate +
                '}';
    }
}
