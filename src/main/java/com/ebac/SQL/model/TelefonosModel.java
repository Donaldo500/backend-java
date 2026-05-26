package com.ebac.SQL.model;
import jakarta.persistence.EntityManager;
import com.ebac.SQL.dto.telefonos;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.TypedQuery;

public class TelefonosModel {
    private final EntityManager entityManager;

    public TelefonosModel(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void guardar(telefonos telefono) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(telefono);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void actualizar (telefonos telefono){
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(telefono);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public telefonos obtenerPorId(int id) {
        return entityManager.find(telefonos.class, id);
    }

    public void eliminar(telefonos telefono) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.remove(telefono);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<telefonos> obtenerTelefonos() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<telefonos> cq = cb.createQuery(telefonos.class);

        Root<telefonos> rootEntry = cq.from(telefonos.class);
        CriteriaQuery<telefonos> select = cq.select(rootEntry);
        
        TypedQuery<telefonos> query = entityManager.createQuery(select);
        return query.getResultList();
    }
}