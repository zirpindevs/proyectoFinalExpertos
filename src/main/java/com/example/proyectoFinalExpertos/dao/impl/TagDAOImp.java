package com.example.proyectoFinalExpertos.dao.impl;

import com.example.proyectoFinalExpertos.dao.TagDAO;
import com.example.proyectoFinalExpertos.model.Tag;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class TagDAOImp implements TagDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    @Override
    public Tag createTag(Tag tag) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

/*        switch (tag.getColor()){
            case RED -> tag.setColor(TagColor.RED);
            case BLUE -> tag.setColor(TagColor.BLUE);
            case GREEN -> tag.setColor(TagColor.GREEN);
            case YELLOW -> tag.setColor(TagColor.YELLOW);
            default -> tag.setColor(TagColor.RED);
        }*/

        session.save(tag);

        session.getTransaction().commit();

        session.close();

        return tag;
    }

    @Override
    public Tag modifyTag(Tag modifiedTag, Tag findedTag) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        findedTag.setName(modifiedTag.getName());
/*
        findedTag.setColor(modifiedTag.getColor());
*/

        session.update(findedTag);
        session.getTransaction().commit();
        session.close();

        return findedTag;
    }

    @Override
    public List<Tag> findAll(){

        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("*************************************************************");
        System.out.println(session);
        System.out.println("*************************************************************");

        return session.createQuery("from Tag", Tag.class ).list();
    }

    @Override
    public Tag findById(Long id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);

        criteria.where(builder.equal(root.get("id"), id));

        Tag tag = session.createQuery(criteria).uniqueResult();

        session.close();

        return tag;
    }

    @Override
    public List<Tag> findByAllByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tag> criteria = builder.createQuery(Tag.class);
        Root<Tag> root = criteria.from(Tag.class);
        criteria.select(root);
        criteria.where(builder.like(root.get("name"), "%"+name+"%"));

        List<Tag> tag = session.createQuery(criteria).list();

        session.close();

        return tag;
    }

}
