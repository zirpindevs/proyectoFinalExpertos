package com.example.proyectoFinalExpertos.dao.impl;

import com.example.proyectoFinalExpertos.dao.ExpertDAO;
import com.example.proyectoFinalExpertos.model.Expert;
import com.example.proyectoFinalExpertos.model.Tag;
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
import java.util.Collection;
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
    public Expert modifyExpert(Expert modifiedExpert, Expert findedExpert, Tag getNewTag, List existingTags) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@   modifyExpert DAO       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(modifiedExpert);
        System.out.println(modifiedExpert.getTags().size());
        System.out.println(findedExpert.getTags());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");



        findedExpert.setName(modifiedExpert.getName());
        findedExpert.setSurname(modifiedExpert.getSurname());
        findedExpert.setNif(modifiedExpert.getNif());
        findedExpert.setDisponibilidad(modifiedExpert.getDisponibilidad());

        //añadimos etiqueta nueva a la lista de etiquetas
        if(getNewTag != null)
            existingTags.add(getNewTag);
            findedExpert.setTags(existingTags);

        findedExpert.setDisponibilidad(modifiedExpert.getDisponibilidad());
        findedExpert.setLast_updated(Instant.now());

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
    public  Expert updateExpert(Expert modifiedExpert, Expert findedExpert){
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@   modifyExpert DAO       @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println(findedExpert);
       System.out.println(modifiedExpert);


        if(!modifiedExpert.getName().isEmpty())
            findedExpert.setName(modifiedExpert.getName());

        if(!modifiedExpert.getSurname().isEmpty())
            findedExpert.setSurname(modifiedExpert.getSurname());

        if(!modifiedExpert.getNif().isEmpty())
            findedExpert.setNif(modifiedExpert.getNif());

        if(!modifiedExpert.getDisponibilidad().isEmpty())
            findedExpert.setDisponibilidad(modifiedExpert.getDisponibilidad());

        if(!modifiedExpert.getEstado().isEmpty())
        findedExpert.setEstado(modifiedExpert.getEstado());

        if(!modifiedExpert.getPuntuacion().isEmpty())
            findedExpert.setPuntuacion(modifiedExpert.getPuntuacion());

        if(modifiedExpert.getTelefono() != null )
            findedExpert.setTelefono(modifiedExpert.getTelefono());

        if(!modifiedExpert.getCorreo().isEmpty())
            findedExpert.setCorreo(modifiedExpert.getCorreo());

        if(!modifiedExpert.getDireccion().isEmpty())
            findedExpert.setDireccion(modifiedExpert.getDireccion());

        if(!modifiedExpert.getObservaciones().isEmpty())
            findedExpert.setObservaciones(modifiedExpert.getObservaciones());

        if(!modifiedExpert.getEstadoMotivo().isEmpty())
            findedExpert.setEstadoMotivo(modifiedExpert.getEstadoMotivo());

        //añadimos etiqueta nueva a la lista de etiquetas
   /*     if(getNewTag != null)
            existingTags.add(getNewTag);
        findedExpert.setTags(existingTags);
*/
        findedExpert.setLast_updated(Instant.now());

        session.update(findedExpert);
        session.getTransaction().commit();
        session.close();


        // System.out.println(modifiedExpert.getTags());
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

        return modifiedExpert;
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
        System.out.println(hql);
        Query query = session.createQuery(hql);

        query.setParameter("nombre", "%"+nombre+"%");
        query.setParameter("estado", "%"+estado+"%");


        query.setParameter("modalidad", "%"+modalidad+"%");
        /* query.setParameter("etiqueta", "%"+etiqueta+"%"); */


        //si quitas este devuelve all y permitiria paginar, sino se limita a enseñar lo que tiene
       query.setMaxResults(Integer.parseInt(limite));
       /* query.setFirstResult(Integer.parseInt(pagina));*/


        List experts = query.getResultList();

/*        //formulas de paginacion
        Integer countResults = query.getResultList().size();
        int pageSize = Integer.parseInt(limite);

        int lastPageNumber = (int) (Math.ceil(countResults / pageSize));
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println(countResults);
        System.out.println(lastPageNumber);

        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////");


        session.close();*/

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
