/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import entities.Shelves;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Juan
 */
public class ShelvesJpaController implements Serializable {

    public ShelvesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public ShelvesJpaController() {
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myLibraryPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Shelves shelves) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(shelves);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Shelves shelves) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            shelves = em.merge(shelves);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = shelves.getId();
                if (findShelves(id) == null) {
                    throw new NonexistentEntityException("The shelves with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Shelves shelves;
            try {
                shelves = em.getReference(Shelves.class, id);
                shelves.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The shelves with id " + id + " no longer exists.", enfe);
            }
            em.remove(shelves);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Shelves> findShelvesEntities() {
        return findShelvesEntities(true, -1, -1);
    }

    public List<Shelves> findShelvesEntities(int maxResults, int firstResult) {
        return findShelvesEntities(false, maxResults, firstResult);
    }

    private List<Shelves> findShelvesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Shelves.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Shelves findShelves(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Shelves.class, id);
        } finally {
            em.close();
        }
    }

    public int getShelvesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Shelves> rt = cq.from(Shelves.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
