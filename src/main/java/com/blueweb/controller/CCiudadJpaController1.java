/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blueweb.controller;

import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.entity.CCiudad;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.blueweb.entity.SUsuarios;
import com.blueweb.entity.HActivacion;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class CCiudadJpaController1 implements Serializable {

    public CCiudadJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CCiudad CCiudad) {
        if (CCiudad.getHActivacionCollection() == null) {
            CCiudad.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios idUsuario = CCiudad.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                CCiudad.setIdUsuario(idUsuario);
            }
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : CCiudad.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            CCiudad.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(CCiudad);
            if (idUsuario != null) {
                idUsuario.getCCiudadCollection().add(CCiudad);
                idUsuario = em.merge(idUsuario);
            }
            for (HActivacion HActivacionCollectionHActivacion : CCiudad.getHActivacionCollection()) {
                CCiudad oldIdCiudadOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdCiudad();
                HActivacionCollectionHActivacion.setIdCiudad(CCiudad);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdCiudadOfHActivacionCollectionHActivacion != null) {
                    oldIdCiudadOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdCiudadOfHActivacionCollectionHActivacion = em.merge(oldIdCiudadOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CCiudad CCiudad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CCiudad persistentCCiudad = em.find(CCiudad.class, CCiudad.getIdCiudad());
            SUsuarios idUsuarioOld = persistentCCiudad.getIdUsuario();
            SUsuarios idUsuarioNew = CCiudad.getIdUsuario();
            Collection<HActivacion> HActivacionCollectionOld = persistentCCiudad.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = CCiudad.getHActivacionCollection();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                CCiudad.setIdUsuario(idUsuarioNew);
            }
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            CCiudad.setHActivacionCollection(HActivacionCollectionNew);
            CCiudad = em.merge(CCiudad);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getCCiudadCollection().remove(CCiudad);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getCCiudadCollection().add(CCiudad);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdCiudad(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    CCiudad oldIdCiudadOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdCiudad();
                    HActivacionCollectionNewHActivacion.setIdCiudad(CCiudad);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdCiudadOfHActivacionCollectionNewHActivacion != null && !oldIdCiudadOfHActivacionCollectionNewHActivacion.equals(CCiudad)) {
                        oldIdCiudadOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdCiudadOfHActivacionCollectionNewHActivacion = em.merge(oldIdCiudadOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CCiudad.getIdCiudad();
                if (findCCiudad(id) == null) {
                    throw new NonexistentEntityException("The cCiudad with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CCiudad CCiudad;
            try {
                CCiudad = em.getReference(CCiudad.class, id);
                CCiudad.getIdCiudad();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CCiudad with id " + id + " no longer exists.", enfe);
            }
            SUsuarios idUsuario = CCiudad.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getCCiudadCollection().remove(CCiudad);
                idUsuario = em.merge(idUsuario);
            }
            Collection<HActivacion> HActivacionCollection = CCiudad.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdCiudad(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(CCiudad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CCiudad> findCCiudadEntities() {
        return findCCiudadEntities(true, -1, -1);
    }

    public List<CCiudad> findCCiudadEntities(int maxResults, int firstResult) {
        return findCCiudadEntities(false, maxResults, firstResult);
    }

    private List<CCiudad> findCCiudadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CCiudad.class));
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

    public CCiudad findCCiudad(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CCiudad.class, id);
        } finally {
            em.close();
        }
    }

    public int getCCiudadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CCiudad> rt = cq.from(CCiudad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
