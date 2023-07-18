/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blueweb.controller;

import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.controller.exceptions.PreexistingEntityException;
import com.blueweb.entity.CAutomata;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.blueweb.entity.CRegion;
import com.blueweb.entity.CDistribuidor;
import java.util.ArrayList;
import java.util.Collection;
import com.blueweb.entity.HActivacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class CAutomataJpaController implements Serializable {

    public CAutomataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CAutomata CAutomata) throws PreexistingEntityException, Exception {
        if (CAutomata.getCDistribuidorCollection() == null) {
            CAutomata.setCDistribuidorCollection(new ArrayList<CDistribuidor>());
        }
        if (CAutomata.getHActivacionCollection() == null) {
            CAutomata.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CRegion idRegion = CAutomata.getIdRegion();
            if (idRegion != null) {
                idRegion = em.getReference(idRegion.getClass(), idRegion.getIdRegion());
                CAutomata.setIdRegion(idRegion);
            }
            Collection<CDistribuidor> attachedCDistribuidorCollection = new ArrayList<CDistribuidor>();
            for (CDistribuidor CDistribuidorCollectionCDistribuidorToAttach : CAutomata.getCDistribuidorCollection()) {
                CDistribuidorCollectionCDistribuidorToAttach = em.getReference(CDistribuidorCollectionCDistribuidorToAttach.getClass(), CDistribuidorCollectionCDistribuidorToAttach.getIdDistribuidor());
                attachedCDistribuidorCollection.add(CDistribuidorCollectionCDistribuidorToAttach);
            }
            CAutomata.setCDistribuidorCollection(attachedCDistribuidorCollection);
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : CAutomata.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            CAutomata.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(CAutomata);
            if (idRegion != null) {
                idRegion.getCAutomataCollection().add(CAutomata);
                idRegion = em.merge(idRegion);
            }
            for (CDistribuidor CDistribuidorCollectionCDistribuidor : CAutomata.getCDistribuidorCollection()) {
                CAutomata oldIdAutomataOfCDistribuidorCollectionCDistribuidor = CDistribuidorCollectionCDistribuidor.getIdAutomata();
                CDistribuidorCollectionCDistribuidor.setIdAutomata(CAutomata);
                CDistribuidorCollectionCDistribuidor = em.merge(CDistribuidorCollectionCDistribuidor);
                if (oldIdAutomataOfCDistribuidorCollectionCDistribuidor != null) {
                    oldIdAutomataOfCDistribuidorCollectionCDistribuidor.getCDistribuidorCollection().remove(CDistribuidorCollectionCDistribuidor);
                    oldIdAutomataOfCDistribuidorCollectionCDistribuidor = em.merge(oldIdAutomataOfCDistribuidorCollectionCDistribuidor);
                }
            }
            for (HActivacion HActivacionCollectionHActivacion : CAutomata.getHActivacionCollection()) {
                CAutomata oldIdAutomataOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdAutomata();
                HActivacionCollectionHActivacion.setIdAutomata(CAutomata);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdAutomataOfHActivacionCollectionHActivacion != null) {
                    oldIdAutomataOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdAutomataOfHActivacionCollectionHActivacion = em.merge(oldIdAutomataOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCAutomata(CAutomata.getIdAutomata()) != null) {
                throw new PreexistingEntityException("CAutomata " + CAutomata + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CAutomata CAutomata) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CAutomata persistentCAutomata = em.find(CAutomata.class, CAutomata.getIdAutomata());
            CRegion idRegionOld = persistentCAutomata.getIdRegion();
            CRegion idRegionNew = CAutomata.getIdRegion();
            Collection<CDistribuidor> CDistribuidorCollectionOld = persistentCAutomata.getCDistribuidorCollection();
            Collection<CDistribuidor> CDistribuidorCollectionNew = CAutomata.getCDistribuidorCollection();
            Collection<HActivacion> HActivacionCollectionOld = persistentCAutomata.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = CAutomata.getHActivacionCollection();
            if (idRegionNew != null) {
                idRegionNew = em.getReference(idRegionNew.getClass(), idRegionNew.getIdRegion());
                CAutomata.setIdRegion(idRegionNew);
            }
            Collection<CDistribuidor> attachedCDistribuidorCollectionNew = new ArrayList<CDistribuidor>();
            for (CDistribuidor CDistribuidorCollectionNewCDistribuidorToAttach : CDistribuidorCollectionNew) {
                CDistribuidorCollectionNewCDistribuidorToAttach = em.getReference(CDistribuidorCollectionNewCDistribuidorToAttach.getClass(), CDistribuidorCollectionNewCDistribuidorToAttach.getIdDistribuidor());
                attachedCDistribuidorCollectionNew.add(CDistribuidorCollectionNewCDistribuidorToAttach);
            }
            CDistribuidorCollectionNew = attachedCDistribuidorCollectionNew;
            CAutomata.setCDistribuidorCollection(CDistribuidorCollectionNew);
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            CAutomata.setHActivacionCollection(HActivacionCollectionNew);
            CAutomata = em.merge(CAutomata);
            if (idRegionOld != null && !idRegionOld.equals(idRegionNew)) {
                idRegionOld.getCAutomataCollection().remove(CAutomata);
                idRegionOld = em.merge(idRegionOld);
            }
            if (idRegionNew != null && !idRegionNew.equals(idRegionOld)) {
                idRegionNew.getCAutomataCollection().add(CAutomata);
                idRegionNew = em.merge(idRegionNew);
            }
            for (CDistribuidor CDistribuidorCollectionOldCDistribuidor : CDistribuidorCollectionOld) {
                if (!CDistribuidorCollectionNew.contains(CDistribuidorCollectionOldCDistribuidor)) {
                    CDistribuidorCollectionOldCDistribuidor.setIdAutomata(null);
                    CDistribuidorCollectionOldCDistribuidor = em.merge(CDistribuidorCollectionOldCDistribuidor);
                }
            }
            for (CDistribuidor CDistribuidorCollectionNewCDistribuidor : CDistribuidorCollectionNew) {
                if (!CDistribuidorCollectionOld.contains(CDistribuidorCollectionNewCDistribuidor)) {
                    CAutomata oldIdAutomataOfCDistribuidorCollectionNewCDistribuidor = CDistribuidorCollectionNewCDistribuidor.getIdAutomata();
                    CDistribuidorCollectionNewCDistribuidor.setIdAutomata(CAutomata);
                    CDistribuidorCollectionNewCDistribuidor = em.merge(CDistribuidorCollectionNewCDistribuidor);
                    if (oldIdAutomataOfCDistribuidorCollectionNewCDistribuidor != null && !oldIdAutomataOfCDistribuidorCollectionNewCDistribuidor.equals(CAutomata)) {
                        oldIdAutomataOfCDistribuidorCollectionNewCDistribuidor.getCDistribuidorCollection().remove(CDistribuidorCollectionNewCDistribuidor);
                        oldIdAutomataOfCDistribuidorCollectionNewCDistribuidor = em.merge(oldIdAutomataOfCDistribuidorCollectionNewCDistribuidor);
                    }
                }
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdAutomata(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    CAutomata oldIdAutomataOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdAutomata();
                    HActivacionCollectionNewHActivacion.setIdAutomata(CAutomata);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdAutomataOfHActivacionCollectionNewHActivacion != null && !oldIdAutomataOfHActivacionCollectionNewHActivacion.equals(CAutomata)) {
                        oldIdAutomataOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdAutomataOfHActivacionCollectionNewHActivacion = em.merge(oldIdAutomataOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CAutomata.getIdAutomata();
                if (findCAutomata(id) == null) {
                    throw new NonexistentEntityException("The cAutomata with id " + id + " no longer exists.");
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
            CAutomata CAutomata;
            try {
                CAutomata = em.getReference(CAutomata.class, id);
                CAutomata.getIdAutomata();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CAutomata with id " + id + " no longer exists.", enfe);
            }
            CRegion idRegion = CAutomata.getIdRegion();
            if (idRegion != null) {
                idRegion.getCAutomataCollection().remove(CAutomata);
                idRegion = em.merge(idRegion);
            }
            Collection<CDistribuidor> CDistribuidorCollection = CAutomata.getCDistribuidorCollection();
            for (CDistribuidor CDistribuidorCollectionCDistribuidor : CDistribuidorCollection) {
                CDistribuidorCollectionCDistribuidor.setIdAutomata(null);
                CDistribuidorCollectionCDistribuidor = em.merge(CDistribuidorCollectionCDistribuidor);
            }
            Collection<HActivacion> HActivacionCollection = CAutomata.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdAutomata(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(CAutomata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CAutomata> findCAutomataEntities() {
        return findCAutomataEntities(true, -1, -1);
    }

    public List<CAutomata> findCAutomataEntities(int maxResults, int firstResult) {
        return findCAutomataEntities(false, maxResults, firstResult);
    }

    private List<CAutomata> findCAutomataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CAutomata.class));
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

    public CAutomata findCAutomata(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CAutomata.class, id);
        } finally {
            em.close();
        }
    }

    public int getCAutomataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CAutomata> rt = cq.from(CAutomata.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
