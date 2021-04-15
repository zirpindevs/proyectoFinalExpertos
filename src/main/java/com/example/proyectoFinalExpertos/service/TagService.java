package com.example.proyectoFinalExpertos.service;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;

import java.util.List;

public interface TagService {

    Tag createTag(Tag tag);
    Tag updateTag(Long id, Tag tag);
    void deleteTag(Tag tagToDelete);

    List<Tag> findAll();
    Tag findOne(Long id);
    List<Tag> findByAllByName(String name);

    }
