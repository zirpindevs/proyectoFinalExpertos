package com.example.proyectoFinalExpertos.service.impl;

import com.example.proyectoFinalExpertos.dao.ExpertDAO;
import com.example.proyectoFinalExpertos.dao.TagDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import com.example.proyectoFinalExpertos.repository.TagRepository;
import com.example.proyectoFinalExpertos.service.TagService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    private final Logger log = LoggerFactory.getLogger(TagServiceImpl.class);

    private final ExpertDAO expertDAO;
    private final TagDAO tagDAO;
    private final TagRepository tagRepository;

    public TagServiceImpl(ExpertDAO expertDAO, TagDAO tagDAO, TagRepository tagRepository) {
        this.expertDAO = expertDAO;
        this.tagDAO = tagDAO;
        this.tagRepository = tagRepository;
    }


    @Override
    public Tag createTag(String tagName) {
        log.info("REST request to create a tag");

        Tag tagCreate = null;

        if (tagName == null) {
            try {
                tagCreate.setCreatedDate(Instant.now());
                tagCreate.setLast_updated(Instant.now());
                tagCreate = tagRepository.save(tagCreate);
            } catch (Exception e) {
                log.error("Cannot save the expert: {} , error : {}", tagCreate, e);
            }
        } else {
            log.warn("Creating expert with id");
        }
    return tagCreate;
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
    public Tag findByName(String name) {
        log.info("REST request to find an tag by title");

        if(name == "")
            return null;
        return this.tagRepository.findByName(name);
    }


    @Override
    public List<Tag> findAllByFilter(String nombre, String limite, String pagina) {
        log.info("REST request to find an expert by filter");

        return this.tagDAO.findAllByFilter(nombre, limite, pagina);
    }

    @Override
    public void deleteTag(Tag tagToDelete){
        log.info("REST request to delete a tag by id");

    //buscamos los expertos que tienen esa tag y se la desasociamos si es que hay
    List<Expert> experts = expertDAO.findAll();
    if (!experts.isEmpty()){
        for (Expert expert : experts) {
            List<Tag> expertTags = expert.getTags();
            for (Tag listTag : expertTags) {
                if (listTag.getName().equals(tagToDelete.getName())) {
                    System.out.println("el experto " + expert.getNombre() + " tiene la etiqueta " + tagToDelete.getName());
                    expertDAO.deleteTagExpert(tagToDelete, expert);
                }
            }
        }
    }
       this.tagDAO.deleteTag(tagToDelete);

    }

}