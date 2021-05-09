package com.example.proyectoFinalExpertos.dao;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;

import java.util.List;

public interface ExpertDAO {

    List<Expert> findAll();
    Expert findById(Long id);
    void deleteExpert(Expert expertToDelete);
    void deleteTagExpert(Tag listTag, Expert expert);
    List<Expert> findAllByName(String name);
    Expert createExpert(Expert expert);
    Expert modifyExpert(Expert expert, Expert findedExpert, Tag getNewTag, List existingTags);
    Expert updateExpert(Expert expert, Expert findedExpert, Tag findedTag, List<Tag> existingTags);
    List<Expert> findAllByFilter(String nombre, String estado, String tamano, String pagina);

    }
