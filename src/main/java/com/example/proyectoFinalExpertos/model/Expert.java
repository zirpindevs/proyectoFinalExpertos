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

    @Column(name="telefono")
    private Long telefono;

    @Column(name="correo")
    private String correo;

    @Column(name="direccion")
    private String direccion;

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

    @Column(name="puntuacion")
    private String puntuacion;

    @Column(name="estado_motivo")
    private String estadoMotivo;

    @Column(name="created_date")
    private Instant createdDate;

    @Column(name="last_updated")
    private Instant last_updated;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "pivot",
            joinColumns = {@JoinColumn(name="expert_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="tag_id", referencedColumnName = "id")}
    )
    private List<Tag> tags = new ArrayList<>();


    public Expert() {
    }

    public Expert(String name, String surname, String nif, Long telefono, String correo, String direccion, String cursos, String modalidad,
                  Long condiciones, String estado, String disponibilidad, String puntuacion, String estadoMotivo, Instant createdDate, Instant last_updated) {
        this.name = name;
        this.surname = surname;
        this.nif = nif;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.cursos = cursos;
        this.modalidad = modalidad;
        this.condiciones = condiciones;
        this.estado = estado;
        this.disponibilidad = disponibilidad;
        this.puntuacion = puntuacion;
        this.estadoMotivo = estadoMotivo;
        this.createdDate = createdDate;
        this.last_updated = last_updated;
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

    public Long getTelefono() {
        return telefono;
    }

    public Expert setTelefono(Long telefono) {
        this.telefono = telefono;
        return this;
    }

    public String getCorreo() {
        return correo;
    }

    public Expert setCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    public String getDireccion() {
        return direccion;
    }

    public Expert setDireccion(String direccion) {
        this.direccion = direccion;
        return this;
    }

    public String getPuntuacion() {
        return puntuacion;
    }

    public Expert setPuntuacion(String puntuacion) {
        this.puntuacion = puntuacion;
        return this;
    }

    public Instant getLast_updated() {
        return last_updated;
    }

    public Expert setLast_updated(Instant last_updated) {
        this.last_updated = last_updated;
        return this;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Expert setTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public String getEstadoMotivo() {
        return estadoMotivo;
    }

    public Expert setEstadoMotivo(String estadoMotivo) {
        this.estadoMotivo = estadoMotivo;
        return this;
    }

    @Override
    public String toString() {
        return "Expert{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", nif='" + nif + '\'' +
                ", telefono=" + telefono +
                ", correo='" + correo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", cursos='" + cursos + '\'' +
                ", modalidad='" + modalidad + '\'' +
                ", condiciones=" + condiciones +
                ", estado='" + estado + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", puntuacion='" + puntuacion + '\'' +
                ", estadoMotivo='" + estadoMotivo + '\'' +
                ", createdDate=" + createdDate +
                ", last_updated=" + last_updated +
                '}';
    }
}