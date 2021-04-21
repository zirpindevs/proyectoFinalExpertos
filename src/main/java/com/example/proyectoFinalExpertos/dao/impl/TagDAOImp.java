package com.example.proyectoFinalExpertos.dao.impl;

import com.example.proyectoFinalExpertos.dao.TagDAO;
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
public class TagDAOImp implements TagDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    @Override
    public Tag createTag(Tag tag) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        tag.setCreatedDate(Instant.now());
        tag.setLast_updated(Instant.now());

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
        findedTag.setLast_updated(Instant.now());


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
        return session.createQuery("from Tag", Tag.class ).list();
    }

    @Override
    public Tag findById(Long id){

        System.out.println("*****************tag find by id***************************");
        System.out.println(id);
        System.out.println("*********************************************");
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


    @Override
    public List<Tag> findAllByFilter(String nombre, String limite, String pagina) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "FROM Tag WHERE name LIKE :nombre";
        Query query = session.createQuery(hql);

        query.setParameter("nombre", "%"+nombre+"%");

        //si quitas este devuelve all y permitiria paginar, sino se limita a enseñar lo que tiene
        query.setMaxResults(Integer.parseInt(limite));
        /* query.setFirstResult(Integer.parseInt(pagina));*/

        List tags = query.getResultList();

        //formulas de paginacion
        Integer countResults = query.getResultList().size();
        int pageSize = Integer.parseInt(limite);

        int lastPageNumber = (int) (Math.ceil(countResults / pageSize));
        System.out.println("/////////////////////////////findAllByFilter dao///////////////////////////////////////////////////////");
        System.out.println(countResults);
        System.out.println(lastPageNumber);

        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");


        session.close();

        return tags;
    }

    @Override
    public void deleteTag(Tag tagToDelete){
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.delete(tagToDelete);

        session.getTransaction().commit();
        session.close();
    }
}
