/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Borrowed;
import entities.Fines;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Ricardo
 */
public class FinesJpaController implements Serializable {

    public FinesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myLibraryPU");

    public FinesJpaController() {
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fines fines) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Borrowed idBorrowed = fines.getIdBorrowed();
            if (idBorrowed != null) {
                idBorrowed = em.getReference(idBorrowed.getClass(), idBorrowed.getId());
                fines.setIdBorrowed(idBorrowed);
            }
            em.persist(fines);
            if (idBorrowed != null) {
                idBorrowed.getFinesCollection().add(fines);
                idBorrowed = em.merge(idBorrowed);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fines fines) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fines persistentFines = em.find(Fines.class, fines.getId());
            Borrowed idBorrowedOld = persistentFines.getIdBorrowed();
            Borrowed idBorrowedNew = fines.getIdBorrowed();
            if (idBorrowedNew != null) {
                idBorrowedNew = em.getReference(idBorrowedNew.getClass(), idBorrowedNew.getId());
                fines.setIdBorrowed(idBorrowedNew);
            }
            fines = em.merge(fines);
            if (idBorrowedOld != null && !idBorrowedOld.equals(idBorrowedNew)) {
                idBorrowedOld.getFinesCollection().remove(fines);
                idBorrowedOld = em.merge(idBorrowedOld);
            }
            if (idBorrowedNew != null && !idBorrowedNew.equals(idBorrowedOld)) {
                idBorrowedNew.getFinesCollection().add(fines);
                idBorrowedNew = em.merge(idBorrowedNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fines.getId();
                if (findFines(id) == null) {
                    throw new NonexistentEntityException("The fines with id " + id + " no longer exists.");
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
            Fines fines;
            try {
                fines = em.getReference(Fines.class, id);
                fines.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fines with id " + id + " no longer exists.", enfe);
            }
            Borrowed idBorrowed = fines.getIdBorrowed();
            if (idBorrowed != null) {
                idBorrowed.getFinesCollection().remove(fines);
                idBorrowed = em.merge(idBorrowed);
            }
            em.remove(fines);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fines> findFinesEntities() {
        return findFinesEntities(true, -1, -1);
    }

    public List<Fines> findFinesEntities(int maxResults, int firstResult) {
        return findFinesEntities(false, maxResults, firstResult);
    }

    private List<Fines> findFinesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fines.class));
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

    public Fines findFines(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fines.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fines> rt = cq.from(Fines.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
