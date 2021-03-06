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
import entities.Books;
import entities.Borrowed;
import entities.Users;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Juan
 */
public class BorrowedJpaController implements Serializable {

    public BorrowedJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("myLibraryPU");

    public BorrowedJpaController() {
    }
    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Borrowed borrowed) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Books idBooks = borrowed.getIdBooks();
            if (idBooks != null) {
                idBooks = em.getReference(idBooks.getClass(), idBooks.getId());
                borrowed.setIdBooks(idBooks);
            }
            Users idUsers = borrowed.getIdUsers();
            if (idUsers != null) {
                idUsers = em.getReference(idUsers.getClass(), idUsers.getId());
                borrowed.setIdUsers(idUsers);
            }
            em.persist(borrowed);
            if (idBooks != null) {
                idBooks.getBorrowedCollection().add(borrowed);
                idBooks = em.merge(idBooks);
            }
            if (idUsers != null) {
                idUsers.getBorrowedCollection().add(borrowed);
                idUsers = em.merge(idUsers);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Borrowed borrowed) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Borrowed persistentBorrowed = em.find(Borrowed.class, borrowed.getId());
            Books idBooksOld = persistentBorrowed.getIdBooks();
            Books idBooksNew = borrowed.getIdBooks();
            Users idUsersOld = persistentBorrowed.getIdUsers();
            Users idUsersNew = borrowed.getIdUsers();
            if (idBooksNew != null) {
                idBooksNew = em.getReference(idBooksNew.getClass(), idBooksNew.getId());
                borrowed.setIdBooks(idBooksNew);
            }
            if (idUsersNew != null) {
                idUsersNew = em.getReference(idUsersNew.getClass(), idUsersNew.getId());
                borrowed.setIdUsers(idUsersNew);
            }
            borrowed = em.merge(borrowed);
            if (idBooksOld != null && !idBooksOld.equals(idBooksNew)) {
                idBooksOld.getBorrowedCollection().remove(borrowed);
                idBooksOld = em.merge(idBooksOld);
            }
            if (idBooksNew != null && !idBooksNew.equals(idBooksOld)) {
                idBooksNew.getBorrowedCollection().add(borrowed);
                idBooksNew = em.merge(idBooksNew);
            }
            if (idUsersOld != null && !idUsersOld.equals(idUsersNew)) {
                idUsersOld.getBorrowedCollection().remove(borrowed);
                idUsersOld = em.merge(idUsersOld);
            }
            if (idUsersNew != null && !idUsersNew.equals(idUsersOld)) {
                idUsersNew.getBorrowedCollection().add(borrowed);
                idUsersNew = em.merge(idUsersNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = borrowed.getId();
                if (findBorrowed(id) == null) {
                    throw new NonexistentEntityException("The borrowed with id " + id + " no longer exists.");
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
            Borrowed borrowed;
            try {
                borrowed = em.getReference(Borrowed.class, id);
                borrowed.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The borrowed with id " + id + " no longer exists.", enfe);
            }
            Books idBooks = borrowed.getIdBooks();
            if (idBooks != null) {
                idBooks.getBorrowedCollection().remove(borrowed);
                idBooks = em.merge(idBooks);
            }
            Users idUsers = borrowed.getIdUsers();
            if (idUsers != null) {
                idUsers.getBorrowedCollection().remove(borrowed);
                idUsers = em.merge(idUsers);
            }
            em.remove(borrowed);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Borrowed> findBorrowedEntities() {
        return findBorrowedEntities(true, -1, -1);
    }

    public List<Borrowed> findBorrowedEntities(int maxResults, int firstResult) {
        return findBorrowedEntities(false, maxResults, firstResult);
    }

    private List<Borrowed> findBorrowedEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Borrowed.class));
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

    public Borrowed findBorrowed(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Borrowed.class, id);
        } finally {
            em.close();
        }
    }

    public int getBorrowedCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Borrowed> rt = cq.from(Borrowed.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
