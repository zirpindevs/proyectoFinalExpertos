package com.example.proyectoFinalExpertos.service;

import com.example.proyectoFinalExpertos.model.Expert;

import java.util.List;

public interface ExpertService {

    Expert createExpert(Expert expert);
    Expert updateExpert(Expert modifiedExpert);
    void deleteExpert(Expert expertToDelete);

    Expert findOne(Long id);
    List<Expert> findAll();

    }
