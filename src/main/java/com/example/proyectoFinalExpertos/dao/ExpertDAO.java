package com.example.proyectoFinalExpertos.dao;

import com.example.proyectoFinalExpertos.model.Expert;

import java.util.List;

public interface ExpertDAO {

    List<Expert> findAllFromSession();

    List<Expert> findAll();
    Expert findById(Long id);
    void deleteExpert(Expert expertToDelete);
    List<Expert> findAllByName(String name);
    Expert createExpert(Expert expert);
    Expert modifyExpert(Expert expert, Expert findedExpert);
    List<Expert> findAllByFilter(String name, String estado);

    }
