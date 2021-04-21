package com.example.proyectoFinalExpertos.service;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;

import java.util.List;

public interface TagService {

    Tag createTag(String tagName);
    Tag updateTag(Long id, Tag tag);
    void deleteTag(Tag tagToDelete);
    List<Tag> findAllByFilter(String nombre, String limite, String pagina);
    List<Tag> findAll();
    Tag findOne(Long id);
    List<Tag> findByAllByName(String name);

    }
