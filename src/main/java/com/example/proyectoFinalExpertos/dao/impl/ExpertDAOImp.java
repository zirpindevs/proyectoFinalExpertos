package com.example.proyectoFinalExpertos.dao.impl;

import com.example.proyectoFinalExpertos.dao.ExpertDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import util.HibernateUtil;
import util.JpaUtil;

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


       String hql = "FROM Expert e WHERE e.id = :id"; // named parameters
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        Expert expert = (Expert) query.getSingleResult();

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
    public List<Expert> findAllByFilter(String nombre, String etiqueta, String modalidad, String estado, String limite, String pagina) {
        Session session = HibernateUtil.getSessionFactory().openSession();


        String hql = "FROM Expert WHERE name LIKE :nombre AND estado LIKE :estado AND modalidad LIKE :modalidad";
        Query query = session.createQuery(hql);

        query.setParameter("nombre", "%"+nombre+"%");
        query.setParameter("estado", "%"+estado+"%");


        query.setParameter("modalidad", "%"+modalidad+"%");
        /* query.setParameter("etiqueta", "%"+etiqueta+"%"); */


        List experts = query.getResultList();

        //formulas de paginacion
        Integer countResults = query.getResultList().size();
        int pageSize = Integer.parseInt(pagina);
        int lastPageNumber = (int) (Math.ceil(countResults / pageSize));

        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println(countResults);
        System.out.println(lastPageNumber);

        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");


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
