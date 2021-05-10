package com.example.proyectoFinalExpertos.dao.impl;

import com.example.proyectoFinalExpertos.dao.UserDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImp implements UserDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public User findUser(User user){

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<User> criteria =  builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);

        List<Predicate> predicates = new ArrayList<>();
        User findUser;

        if(user.getEmail()!=null)
            predicates.add(builder.equal(root.get("email"), user.getEmail()));

        if(user.getPassword()!=null)
            predicates.add(builder.equal(root.get("password"), user.getPassword()));

        criteria.distinct(true).select(root).where(builder.and(predicates.toArray(new Predicate[0])));
        try {
            findUser = manager.createQuery(criteria).getSingleResult();

        }catch (NoResultException nre) {
            findUser = null;
        }

         return findUser;
    }

}
