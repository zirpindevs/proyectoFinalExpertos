package com.example.proyectoFinalExpertos.service.impl;

import com.example.proyectoFinalExpertos.dao.TagDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import com.example.proyectoFinalExpertos.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);

    private final TagDAO tagDAO;

    public TagServiceImpl(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @Override
    public Tag createTag(Tag tag) {
        log.info("REST request to create a tag");
        return this.tagDAO.createTag(tag);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        log.info("REST request to update an tag");

        Tag findTag = this.findOne(id);

        if(findTag == null) {
            return null;
        }

        return this.tagDAO.modifyTag(tag, findTag);
    }

    @Override
    public List<Tag> findAll() {
        log.info("REST request to find all tags");

        return this.tagDAO.findAll();
    }

    @Override
    public Tag findOne(Long id) {
        log.info("REST request to find one tag by id");

        if(id == null)
            return null;
        return this.tagDAO.findById(id);
    }

    @Override
    public List<Tag> findByAllByName(String name) {
        log.info("REST request to find an tag by title");

        if(name.isEmpty())
            return null;
        return this.tagDAO.findByAllByName(name);
    }

    @Override
    public List<Tag> findAllByFilter(String nombre, String limite, String pagina) {
        log.info("REST request to find an expert by filter");

    /*    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(params.size());
        params.forEach(param -> System.out.println(param.toString()));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
*/
        return this.tagDAO.findAllByFilter(nombre, limite, pagina);
    }

    @Override
    public void deleteTag(Tag tagToDelete){
        log.info("REST request to delete a tag by id");
        this.tagDAO.deleteTag(tagToDelete);

    }
}
