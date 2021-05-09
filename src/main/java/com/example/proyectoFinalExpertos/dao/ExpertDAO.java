package com.example.proyectoFinalExpertos.dao;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;

import java.util.List;

public interface ExpertDAO {

    Expert findById(Long id);
    void deleteTagExpert(Tag listTag, Expert expert);
}