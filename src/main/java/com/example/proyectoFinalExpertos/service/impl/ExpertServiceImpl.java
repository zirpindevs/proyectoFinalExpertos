package com.example.proyectoFinalExpertos.service.impl;

import com.example.proyectoFinalExpertos.dao.ExpertDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.service.ExpertService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {
    private final Logger log = LoggerFactory.getLogger(ExpertServiceImpl.class);

    private final ExpertDAO expertDAO;

    public ExpertServiceImpl(ExpertDAO expertDAO) {
        this.expertDAO = expertDAO;
    }

    @Override
    public Expert createExpert(Expert expert) {
        log.info("REST request to create an expert");
        return this.expertDAO.createExpert(expert);
    }

    @Override
    public Expert updateExpert(Long id, Expert expert) {
        log.info("REST request to update an expert");

        Expert findExpert = this.findOne(id);

        if(findExpert == null) {
            return null;
        }

        return this.expertDAO.modifyExpert(expert, findExpert);
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
    public List<Expert> findAllByName(String name) {
        log.info("REST request to find an expert by name");

        if(name.isEmpty())
            return null;
        return this.expertDAO.findAllByName(name);
    }


}
