/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blueweb.controller;

import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.blueweb.entity.CTelefonia;
import com.blueweb.entity.CTipoTelefono;
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
public class CTipoTelefonoJpaController implements Serializable {

    public CTipoTelefonoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CTipoTelefono CTipoTelefono) throws PreexistingEntityException, Exception {
        if (CTipoTelefono.getHActivacionCollection() == null) {
            CTipoTelefono.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CTelefonia idTelefonia = CTipoTelefono.getIdTelefonia();
            if (idTelefonia != null) {
                idTelefonia = em.getReference(idTelefonia.getClass(), idTelefonia.getIdTelefonia());
                CTipoTelefono.setIdTelefonia(idTelefonia);
            }
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : CTipoTelefono.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            CTipoTelefono.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(CTipoTelefono);
            if (idTelefonia != null) {
                idTelefonia.getCTipoTelefonoCollection().add(CTipoTelefono);
                idTelefonia = em.merge(idTelefonia);
            }
            for (HActivacion HActivacionCollectionHActivacion : CTipoTelefono.getHActivacionCollection()) {
                CTipoTelefono oldIdTipoTelefoniaOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdTipoTelefonia();
                HActivacionCollectionHActivacion.setIdTipoTelefonia(CTipoTelefono);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdTipoTelefoniaOfHActivacionCollectionHActivacion != null) {
                    oldIdTipoTelefoniaOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdTipoTelefoniaOfHActivacionCollectionHActivacion = em.merge(oldIdTipoTelefoniaOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCTipoTelefono(CTipoTelefono.getId()) != null) {
                throw new PreexistingEntityException("CTipoTelefono " + CTipoTelefono + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CTipoTelefono CTipoTelefono) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CTipoTelefono persistentCTipoTelefono = em.find(CTipoTelefono.class, CTipoTelefono.getId());
            CTelefonia idTelefoniaOld = persistentCTipoTelefono.getIdTelefonia();
            CTelefonia idTelefoniaNew = CTipoTelefono.getIdTelefonia();
            Collection<HActivacion> HActivacionCollectionOld = persistentCTipoTelefono.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = CTipoTelefono.getHActivacionCollection();
            if (idTelefoniaNew != null) {
                idTelefoniaNew = em.getReference(idTelefoniaNew.getClass(), idTelefoniaNew.getIdTelefonia());
                CTipoTelefono.setIdTelefonia(idTelefoniaNew);
            }
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            CTipoTelefono.setHActivacionCollection(HActivacionCollectionNew);
            CTipoTelefono = em.merge(CTipoTelefono);
            if (idTelefoniaOld != null && !idTelefoniaOld.equals(idTelefoniaNew)) {
                idTelefoniaOld.getCTipoTelefonoCollection().remove(CTipoTelefono);
                idTelefoniaOld = em.merge(idTelefoniaOld);
            }
            if (idTelefoniaNew != null && !idTelefoniaNew.equals(idTelefoniaOld)) {
                idTelefoniaNew.getCTipoTelefonoCollection().add(CTipoTelefono);
                idTelefoniaNew = em.merge(idTelefoniaNew);
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdTipoTelefonia(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    CTipoTelefono oldIdTipoTelefoniaOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdTipoTelefonia();
                    HActivacionCollectionNewHActivacion.setIdTipoTelefonia(CTipoTelefono);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdTipoTelefoniaOfHActivacionCollectionNewHActivacion != null && !oldIdTipoTelefoniaOfHActivacionCollectionNewHActivacion.equals(CTipoTelefono)) {
                        oldIdTipoTelefoniaOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdTipoTelefoniaOfHActivacionCollectionNewHActivacion = em.merge(oldIdTipoTelefoniaOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CTipoTelefono.getId();
                if (findCTipoTelefono(id) == null) {
                    throw new NonexistentEntityException("The cTipoTelefono with id " + id + " no longer exists.");
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
            CTipoTelefono CTipoTelefono;
            try {
                CTipoTelefono = em.getReference(CTipoTelefono.class, id);
                CTipoTelefono.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CTipoTelefono with id " + id + " no longer exists.", enfe);
            }
            CTelefonia idTelefonia = CTipoTelefono.getIdTelefonia();
            if (idTelefonia != null) {
                idTelefonia.getCTipoTelefonoCollection().remove(CTipoTelefono);
                idTelefonia = em.merge(idTelefonia);
            }
            Collection<HActivacion> HActivacionCollection = CTipoTelefono.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdTipoTelefonia(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(CTipoTelefono);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CTipoTelefono> findCTipoTelefonoEntities() {
        return findCTipoTelefonoEntities(true, -1, -1);
    }

    public List<CTipoTelefono> findCTipoTelefonoEntities(int maxResults, int firstResult) {
        return findCTipoTelefonoEntities(false, maxResults, firstResult);
    }

    private List<CTipoTelefono> findCTipoTelefonoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CTipoTelefono.class));
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

    public CTipoTelefono findCTipoTelefono(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CTipoTelefono.class, id);
        } finally {
            em.close();
        }
    }

    public int getCTipoTelefonoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CTipoTelefono> rt = cq.from(CTipoTelefono.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
