package com.example.proyectoFinalExpertos.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="created_date")
    private Instant createdDate;

    @Column(name="last_updated")
    private Instant last_updated;

    public Tag() {
    }

    public Tag(String name) {
        this.name = name;
    }

    public Tag(String name, Instant createdDate, Instant last_updated) {
        this.name = name;
        this.createdDate = createdDate;
        this.last_updated = last_updated;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Tag setName(String name) {
        this.name = name;
        return this;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Tag setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public Instant getLast_updated() {
        return last_updated;
    }

    public Tag setLast_updated(Instant last_updated) {
        this.last_updated = last_updated;
        return this;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", last_updated=" + last_updated +
                '}';
    }
}
