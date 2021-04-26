package com.example.proyectoFinalExpertos.dao.impl;

import com.example.proyectoFinalExpertos.dao.UserDAO;
import com.example.proyectoFinalExpertos.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    @Override
    public List<User> findAllFromSession() {
        return session.createQuery("from User e").list();
    }


    @Override
    public User findById(Long id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        criteria.where(builder.equal(root.get("id"), id));

        User user = session.createQuery(criteria).uniqueResult();

        session.close();

        return user;
    }

    @Override
    public List<User> findAllByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        criteria.select(root);
        criteria.where(builder.like(root.get("name"), "%"+name+"%"));

        List<User> user = session.createQuery(criteria).list();

        session.close();

        return user;
    }

}
