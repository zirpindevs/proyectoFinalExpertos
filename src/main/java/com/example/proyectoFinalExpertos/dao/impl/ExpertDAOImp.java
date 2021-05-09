package com.example.proyectoFinalExpertos.dao.impl;

import com.example.proyectoFinalExpertos.dao.ExpertDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.List;

@Repository
public class ExpertDAOImp implements ExpertDAO {

    @PersistenceContext
    private EntityManager manager;


    @Override
    public Expert findById(Long id){

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Expert> criteria =  builder.createQuery(Expert.class);
        Root<Expert> root = criteria.from(Expert.class);
        Expert expert;

        criteria.select(root).where(builder.equal(root.get("id"), id));
        try{
            expert = manager.createQuery(criteria).getSingleResult();
        }catch(Exception e){
            expert = null;
        }
        return expert;

    }

    @Override
    public void deleteTagExpert(Tag listTag, Expert expert){

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        System.out.println(expert);
        expert = session.find(Expert.class, expert.getId());
        System.out.println(expert);

        for (int x = 0; x < expert.getTags().size(); x++) {
            Tag t = expert.getTags().get(x);
            if (t.getName().equals(listTag.getName())) {
                expert.getTags().remove(t);
                break;
            }
        }

        session.save(expert);
        session.getTransaction().commit();
        session.close();
        System.out.println(expert.getTags());


    }

}
