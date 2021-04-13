package com.example.proyectoFinalExpertos.dao;

import com.example.proyectoFinalExpertos.model.Tag;

import java.util.List;

public interface TagDAO {

    List<Tag> findAllFromSession();

    List<Tag> findAll();
    Tag findById(Long id);
    List<Tag> findByAllByName(String name);
    Tag createTag(Tag tag);
    Tag modifyTag(Tag tag, Tag findedTag);



}
