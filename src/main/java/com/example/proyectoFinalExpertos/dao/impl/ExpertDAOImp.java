package com.example.proyectoFinalExpertos.dao.impl;

import com.example.proyectoFinalExpertos.dao.ExpertDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class ExpertDAOImp implements ExpertDAO {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private Session session;

    @Override
    public List<Expert> findAllFromSession() {
        return session.createQuery("from Expert e").list();
    }

    @Override
    public Expert createExpert(Expert expert) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        expert.setCreatedDate(Instant.now());
        session.save(expert);

        session.getTransaction().commit();

        session.close();

        return expert;
    }

    @Override
    public Expert modifyExpert(Expert modifiedExpert, Expert findedExpert) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        findedExpert.setName(modifiedExpert.getName());
        findedExpert.setSurname(modifiedExpert.getSurname());
        findedExpert.setNif(modifiedExpert.getNif());
        findedExpert.setDisponibilidad(modifiedExpert.getDisponibilidad());

        findedExpert.setDisponibilidad(modifiedExpert.getDisponibilidad());

//        switch (findedExpert.getEstado()) {
//            case DESCARTADO -> modifiedExpert.setEstado(ExpertConditions.DESCARTADO);
//            case VALIDADO -> modifiedExpert.setEstado(ExpertConditions.VALIDADO);
//            case PENDIENTE -> modifiedExpert.setEstado(ExpertConditions.PENDIENTE);
//            default -> modifiedExpert.setEstado(ExpertConditions.PENDIENTE);
//        }

        session.update(findedExpert);
        session.getTransaction().commit();
        session.close();

        return findedExpert;
    }

    @Override
    public List<Expert> findAll(){
        Session session = HibernateUtil.getSessionFactory().openSession();

        return session.createQuery("from Expert", Expert.class ).list();

    }

//    @Override
//    public Expert findById(Long id){
//
//        Session session = HibernateUtil.getSessionFactory().openSession();
//
//        CriteriaBuilder builder = session.getCriteriaBuilder();
//        CriteriaQuery<Expert> criteria = builder.createQuery(Expert.class);
//        Root<Expert> root = criteria.from(Expert.class);
//
//        criteria.where(builder.equal(root.get("id"), id));
//
//        Expert expert = session.createQuery(criteria).uniqueResult();
//
//        session.close();
//
//        return expert;
//    }

    @Override
    public Expert findById(Long id){

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql ="SELECT e.name e.surname t.name FROM Expert e INNER JOIN Tag t WHERE e.id = t.id AND e.id = 5";

        Query<Expert> query = session.createQuery(hql);






//        String hql = "select a.firstName, a.lastName from Authors a, Books b, AuthorBook ab where ab.authorId=a.authorId and ab.bookId=b.bookId and ab.bookId=:id";


//        select e.name, e.surname , e.nif, t.name FROM  experts e
//        inner join
//        pivot pvt on e.id = pvt.expert_id
//        inner join
//        tags t on pvt.tag_id = t.id
//        WHERE  e.id = 4

        Expert expert = query.getSingleResult();

        System.out.println("************************************************");
        System.out.println(expert);
        System.out.println(expert.getTags());

        session.close();

        return expert;
    }

    @Override
    public List<Expert> findAllByName(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Expert> criteria = builder.createQuery(Expert.class);
        Root<Expert> root = criteria.from(Expert.class);

        criteria.select(root);
        criteria.where(builder.like(root.get("name"), "%"+name+"%"));

        List<Expert> experts = session.createQuery(criteria).list();

        session.close();

        return experts;
    }

    @Override
    public void deleteExpert(Expert expertToDelete){
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();
        session.delete(expertToDelete);

        session.getTransaction().commit();
        session.close();
    }

}
