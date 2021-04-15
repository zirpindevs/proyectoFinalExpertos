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


    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    private List<Expert> experts = new ArrayList<>();


    public Tag() {
    }

    public Tag(String name, Instant createdDate) {
        this.name = name;
        this.createdDate = createdDate;
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

    public List<Expert> getExperts() {
        return experts;
    }

    public Tag setExperts(List<Expert> experts) {
        this.experts = experts;
        return this;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdDate=" + createdDate +
                ", experts=" + experts +
                '}';
    }
}
