package com.example.proyectoFinalExpertos.service;

import com.example.proyectoFinalExpertos.model.Expert;

import java.util.List;

public interface ExpertService {

    Expert createExpert(Expert expert);
    Expert updateExpert(Long id, Expert modifiedExpert);
    void deleteExpert(Expert expertToDelete);

    List<Expert> findAll();
    Expert findOne(Long id);
    List<Expert> findAllByName(String name);
    List<Expert> findAllByFilter(String nombre, String estado, String tamano, String pagina);

    }
