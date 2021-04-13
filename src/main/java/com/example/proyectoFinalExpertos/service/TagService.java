package com.example.proyectoFinalExpertos.service;

import com.example.proyectoFinalExpertos.model.Tag;

import java.util.List;

public interface TagService {

    Tag createTag(Tag tag);
    Tag updateTag(Long id, Tag tag);

    List<Tag> findAll();
    Tag findOne(Long id);
    List<Tag> findByAllByName(String name);

    }
