package com.example.proyectoFinalExpertos.service;

import com.example.proyectoFinalExpertos.model.Tag;

import java.util.List;

public interface TagService {

    Tag createTag(Tag tagToCreate);
    Tag updateTag(Tag updatetag);
    void deleteTag(Tag tagToDelete);
    List<Tag> findAllByFilter(String nombre, String limite, String pagina);
    List<Tag> findAll();
    Tag findByName(String name);

    Tag findOne(Long id);
    List<Tag> findByAllByName(String name);

    }
