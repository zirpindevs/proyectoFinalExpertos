package com.example.proyectoFinalExpertos.service.impl;

import com.example.proyectoFinalExpertos.dao.ExpertDAO;
import com.example.proyectoFinalExpertos.dao.TagDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import com.example.proyectoFinalExpertos.repository.ExpertRepository;
import com.example.proyectoFinalExpertos.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpertServiceImpl implements ExpertService {
    private final Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);


    private final ExpertDAO expertDAO;
    private final TagDAO tagDAO;
    private ExpertRepository expertRepository;


    public ExpertServiceImpl(ExpertDAO expertDAO, TagDAO tagDAO, ExpertRepository expertRepository) {
        this.expertDAO = expertDAO;
        this.tagDAO = tagDAO;
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
    public Expert updateExpert(Long id, Expert modifiedExpert) {
        log.info("REST request to update an expert");

        Optional<Expert> recoverExpert = expertRepository.findById(id);

        Expert updatedExpert = null;


        if (expertRepository != null) {
            modifiedExpert.setLast_updated(Instant.now());
            updatedExpert = expertRepository.save(modifiedExpert);
        }

        return updatedExpert;
    }

    @Override
    public List<Expert> findAll() {
        log.info("REST request to find all expert");

        return this.expertDAO.findAll();
    }

    @Override
    public List<Expert> findAllByName(String name) {
        log.info("REST request to find an expert by name");

        if(name.isEmpty())
            return null;
        return this.expertDAO.findAllByName(name);
    }

    @Override
    public List<Expert> findAllByFilter(String nombre, String estado, String tamano, String pagina) {
        log.info("REST request to find an expert by filter");

    /*    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(params.size());
        params.forEach(param -> System.out.println(param.toString()));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
*/
        return this.expertDAO.findAllByFilter(nombre, estado, tamano, pagina);
    }

    @Override
    public void deleteExpert(Expert expertToDelete){
        log.info("REST request to delete an expert by id");
        this.expertDAO.deleteExpert(expertToDelete);

    }
}
