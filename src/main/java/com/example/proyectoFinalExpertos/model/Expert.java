package com.example.proyectoFinalExpertos.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "experts")
public class Expert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="nif")
    private String nif;

    @Column(name="cursos")
    private String cursos;

    @Column(name="condiciones")
    private Long condiciones;

    @Column(name="estado")
    @Enumerated(EnumType.STRING)
    private ExpertConditions estado;

    @Column(name="disponibilidad")
    private String disponibilidad;

    @Column(name="created_date")
    private Instant createdDate;


    @ManyToMany
    @JoinTable(
            // nombre de la nueva tabla auxiliar
            name = "expert_tag",
            // nombre de la primera nueva columna de la tabla de la entidad actual (Employee)
            joinColumns = {@JoinColumn(name="expert_id", referencedColumnName = "id")},
            // nombre de la segunda nueva columna de la tabla de la entidad relacionada (Project)
            inverseJoinColumns = {@JoinColumn(name="tag_id", referencedColumnName = "id")}
    )
    private List<Tag> tags = new ArrayList<>();


    public Expert() {
    }

    public String getName() {
        return name;
    }

    public Expert setName(String name) {
        this.name = name;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public Expert setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getNif() {
        return nif;
    }

    public Expert setNif(String nif) {
        this.nif = nif;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Expert setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public String getCursos() {
        return cursos;
    }

    public Expert setCursos(String cursos) {
        this.cursos = cursos;
        return this;
    }

    public Long getCondiciones() {
        return condiciones;
    }

    public Expert setCondiciones(Long condiciones) {
        this.condiciones = condiciones;
        return this;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public Expert setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
        return this;
    }

    public ExpertConditions getEstado() {
        return estado;
    }

    public Expert setEstado(ExpertConditions estado) {
        this.estado = estado;
        return this;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Expert setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    @Override
    public String toString() {
        return "Expert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nif='" + nif + '\'' +
                ", cursos='" + cursos + '\'' +
                ", condiciones='" + condiciones + '\'' +
                ", estado=" + estado +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}