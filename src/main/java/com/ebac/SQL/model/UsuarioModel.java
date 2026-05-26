package com.ebac.SQL.model;
import jakarta.persistence.EntityManager;
import com.ebac.SQL.dto.Usuario;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.TypedQuery;

public class UsuarioModel {
    private final EntityManager entityManager;

    public UsuarioModel(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void guardar(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.persist(usuario);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public void actualizar (Usuario usuario){
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.merge(usuario);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public Usuario obtenerPorId(int id) {
        return entityManager.find(Usuario.class, id);
    }

    public void eliminar(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            entityManager.remove(usuario);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<Usuario> obtenerUsuarios() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);

        Root<Usuario> rootEntry = cq.from(Usuario.class);
        CriteriaQuery<Usuario> select = cq.select(rootEntry);
        
        TypedQuery<Usuario> query = entityManager.createQuery(select);
        return query.getResultList();
    }

  
}
