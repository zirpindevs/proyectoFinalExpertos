package com.example.proyectoFinalExpertos.dao;

import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;

import java.util.List;

public interface TagDAO {

    List<Tag> findAll();
    Tag findById(Long id);
    Tag findByName(String name);
    void deleteTag(Tag tagToDelete);
    List<Tag> findAllByFilter(String nombre, String limite, String pagina);
    List<Tag> findByAllByName(String name);
    Tag createTag(String tagName);
    Tag modifyTag(Tag tag, Tag findedTag);



}
