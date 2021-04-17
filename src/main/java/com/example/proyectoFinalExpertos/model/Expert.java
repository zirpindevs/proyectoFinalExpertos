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

    @Column(name="modalidad")
    private String modalidad;

    @Column(name="condiciones")
    private Long condiciones;

    @Column(name="estado")
    private String estado;

    @Column(name="disponibilidad")
    private String disponibilidad;

    @Column(name="created_date")
    private Instant createdDate;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pivot",
            joinColumns = {@JoinColumn(name="expert_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="tag_id", referencedColumnName = "id")}
    )
    private List<Tag> tags = new ArrayList<>();


    public Expert() {
    }


    public Expert(String name, String surname, String nif, String cursos, String modalidad, Long condiciones, String estado, String disponibilidad, Instant createdDate) {
        this.name = name;
        this.surname = surname;
        this.nif = nif;
        this.cursos = cursos;
        this.modalidad = modalidad;
        this.condiciones = condiciones;
        this.estado = estado;
        this.disponibilidad = disponibilidad;
        this.createdDate = createdDate;
    }

    public Long getId() {
        return id;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public Expert setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getModalidad() {
        return modalidad;
    }

    public Expert setModalidad(String modalidad) {
        this.modalidad = modalidad;
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
                ", modalidad='" + modalidad + '\'' +
                ", condiciones=" + condiciones +
                ", estado='" + estado + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", createdDate=" + createdDate +
                ", tags=" + tags +
                '}';
    }
}