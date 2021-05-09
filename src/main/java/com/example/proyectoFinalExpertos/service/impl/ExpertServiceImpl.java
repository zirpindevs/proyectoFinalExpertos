package com.example.proyectoFinalExpertos.service.impl;

import com.example.proyectoFinalExpertos.dao.ExpertDAO;
import com.example.proyectoFinalExpertos.dao.TagDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import com.example.proyectoFinalExpertos.repository.ExpertRepository;
import com.example.proyectoFinalExpertos.repository.TagRepository;
import com.example.proyectoFinalExpertos.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {
    private final Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);


    private final ExpertDAO expertDAO;
    private final TagRepository tagRepository;
    private final ExpertRepository expertRepository;


    public ExpertServiceImpl(ExpertDAO expertDAO, TagRepository tagRepository, ExpertRepository expertRepository) {
        this.expertDAO = expertDAO;
        this.tagRepository = tagRepository;
        this.expertRepository = expertRepository;
    }

    @Override
    public Expert createExpert(Expert expert) {
        log.debug("Create expert: {}", expert);

        Expert expertCreated = null;

        if (expert.getId() == null) {
            try{
                expert.setCreatedDate(Instant.now());
                expert.setLast_updated(Instant.now());
                expertCreated = expertRepository.save(expert);
            }catch(Exception e) {
                log.error("Cannot save the expert: {} , error : {}", expert, e);
            }
        }else{
            log.warn("Creating expert with id");
        }
        return expertCreated;
    }



    @Override
    public Expert updateExpert(Expert modifiedExpert) {

        log.debug("Update a expert: {}", modifiedExpert);

        Expert updatedExpert = null;
        Expert findedExpert = expertDAO.findById(modifiedExpert.getId());
        List<Tag> existingTags = null;

        if (findedExpert != null) {
            if(findedExpert.getTags() != null)
              existingTags = findedExpert.getTags();

                try{
                    modifiedExpert.setLast_updated(Instant.now());
                    modifiedExpert.setTags(existingTags);
                    updatedExpert = expertRepository.save(modifiedExpert);
                }catch(Exception e){
                    log.error("Cannot save expert: {} , error : {}", modifiedExpert, e);
                }
        }else{
            log.warn("Cannot save expert: {}, because it doesn´t exist", updatedExpert);
            }
            return updatedExpert;
        }


    @Override
    public List<Expert> findAll() {
        log.info("REST request to find all expert");

        return this.expertDAO.findAll();
    }

    @Override
    public Expert findOne(Long id) {
        log.info("REST request to find one expert by id");

        if(id == null)
            return null;
        return this.expertDAO.findById(id);
    }


    @Override
    public void deleteExpert(Expert expertToDelete){
        log.info("REST request to delete an expert by id");
        this.expertRepository.deleteById(expertToDelete.getId());

    }
}
