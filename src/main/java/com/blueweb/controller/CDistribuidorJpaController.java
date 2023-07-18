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
import com.blueweb.entity.CAutomata;
import com.blueweb.entity.CDistribuidor;
import com.blueweb.entity.CRegion;
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
public class CDistribuidorJpaController implements Serializable {

    public CDistribuidorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CDistribuidor CDistribuidor) throws PreexistingEntityException, Exception {
        if (CDistribuidor.getHActivacionCollection() == null) {
            CDistribuidor.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CAutomata idAutomata = CDistribuidor.getIdAutomata();
            if (idAutomata != null) {
                idAutomata = em.getReference(idAutomata.getClass(), idAutomata.getIdAutomata());
                CDistribuidor.setIdAutomata(idAutomata);
            }
            CRegion idRegion = CDistribuidor.getIdRegion();
            if (idRegion != null) {
                idRegion = em.getReference(idRegion.getClass(), idRegion.getIdRegion());
                CDistribuidor.setIdRegion(idRegion);
            }
            SUsuarios idUsuarioModifica = CDistribuidor.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica = em.getReference(idUsuarioModifica.getClass(), idUsuarioModifica.getIdUsuario());
                CDistribuidor.setIdUsuarioModifica(idUsuarioModifica);
            }
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : CDistribuidor.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            CDistribuidor.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(CDistribuidor);
            if (idAutomata != null) {
                idAutomata.getCDistribuidorCollection().add(CDistribuidor);
                idAutomata = em.merge(idAutomata);
            }
            if (idRegion != null) {
                idRegion.getCDistribuidorCollection().add(CDistribuidor);
                idRegion = em.merge(idRegion);
            }
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getCDistribuidorCollection().add(CDistribuidor);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            for (HActivacion HActivacionCollectionHActivacion : CDistribuidor.getHActivacionCollection()) {
                CDistribuidor oldIdDistribuidorOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdDistribuidor();
                HActivacionCollectionHActivacion.setIdDistribuidor(CDistribuidor);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdDistribuidorOfHActivacionCollectionHActivacion != null) {
                    oldIdDistribuidorOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdDistribuidorOfHActivacionCollectionHActivacion = em.merge(oldIdDistribuidorOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCDistribuidor(CDistribuidor.getIdDistribuidor()) != null) {
                throw new PreexistingEntityException("CDistribuidor " + CDistribuidor + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CDistribuidor CDistribuidor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CDistribuidor persistentCDistribuidor = em.find(CDistribuidor.class, CDistribuidor.getIdDistribuidor());
            CAutomata idAutomataOld = persistentCDistribuidor.getIdAutomata();
            CAutomata idAutomataNew = CDistribuidor.getIdAutomata();
            CRegion idRegionOld = persistentCDistribuidor.getIdRegion();
            CRegion idRegionNew = CDistribuidor.getIdRegion();
            SUsuarios idUsuarioModificaOld = persistentCDistribuidor.getIdUsuarioModifica();
            SUsuarios idUsuarioModificaNew = CDistribuidor.getIdUsuarioModifica();
            Collection<HActivacion> HActivacionCollectionOld = persistentCDistribuidor.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = CDistribuidor.getHActivacionCollection();
            if (idAutomataNew != null) {
                idAutomataNew = em.getReference(idAutomataNew.getClass(), idAutomataNew.getIdAutomata());
                CDistribuidor.setIdAutomata(idAutomataNew);
            }
            if (idRegionNew != null) {
                idRegionNew = em.getReference(idRegionNew.getClass(), idRegionNew.getIdRegion());
                CDistribuidor.setIdRegion(idRegionNew);
            }
            if (idUsuarioModificaNew != null) {
                idUsuarioModificaNew = em.getReference(idUsuarioModificaNew.getClass(), idUsuarioModificaNew.getIdUsuario());
                CDistribuidor.setIdUsuarioModifica(idUsuarioModificaNew);
            }
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            CDistribuidor.setHActivacionCollection(HActivacionCollectionNew);
            CDistribuidor = em.merge(CDistribuidor);
            if (idAutomataOld != null && !idAutomataOld.equals(idAutomataNew)) {
                idAutomataOld.getCDistribuidorCollection().remove(CDistribuidor);
                idAutomataOld = em.merge(idAutomataOld);
            }
            if (idAutomataNew != null && !idAutomataNew.equals(idAutomataOld)) {
                idAutomataNew.getCDistribuidorCollection().add(CDistribuidor);
                idAutomataNew = em.merge(idAutomataNew);
            }
            if (idRegionOld != null && !idRegionOld.equals(idRegionNew)) {
                idRegionOld.getCDistribuidorCollection().remove(CDistribuidor);
                idRegionOld = em.merge(idRegionOld);
            }
            if (idRegionNew != null && !idRegionNew.equals(idRegionOld)) {
                idRegionNew.getCDistribuidorCollection().add(CDistribuidor);
                idRegionNew = em.merge(idRegionNew);
            }
            if (idUsuarioModificaOld != null && !idUsuarioModificaOld.equals(idUsuarioModificaNew)) {
                idUsuarioModificaOld.getCDistribuidorCollection().remove(CDistribuidor);
                idUsuarioModificaOld = em.merge(idUsuarioModificaOld);
            }
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                idUsuarioModificaNew.getCDistribuidorCollection().add(CDistribuidor);
                idUsuarioModificaNew = em.merge(idUsuarioModificaNew);
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdDistribuidor(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    CDistribuidor oldIdDistribuidorOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdDistribuidor();
                    HActivacionCollectionNewHActivacion.setIdDistribuidor(CDistribuidor);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdDistribuidorOfHActivacionCollectionNewHActivacion != null && !oldIdDistribuidorOfHActivacionCollectionNewHActivacion.equals(CDistribuidor)) {
                        oldIdDistribuidorOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdDistribuidorOfHActivacionCollectionNewHActivacion = em.merge(oldIdDistribuidorOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CDistribuidor.getIdDistribuidor();
                if (findCDistribuidor(id) == null) {
                    throw new NonexistentEntityException("The cDistribuidor with id " + id + " no longer exists.");
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
            CDistribuidor CDistribuidor;
            try {
                CDistribuidor = em.getReference(CDistribuidor.class, id);
                CDistribuidor.getIdDistribuidor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CDistribuidor with id " + id + " no longer exists.", enfe);
            }
            CAutomata idAutomata = CDistribuidor.getIdAutomata();
            if (idAutomata != null) {
                idAutomata.getCDistribuidorCollection().remove(CDistribuidor);
                idAutomata = em.merge(idAutomata);
            }
            CRegion idRegion = CDistribuidor.getIdRegion();
            if (idRegion != null) {
                idRegion.getCDistribuidorCollection().remove(CDistribuidor);
                idRegion = em.merge(idRegion);
            }
            SUsuarios idUsuarioModifica = CDistribuidor.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getCDistribuidorCollection().remove(CDistribuidor);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            Collection<HActivacion> HActivacionCollection = CDistribuidor.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdDistribuidor(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(CDistribuidor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CDistribuidor> findCDistribuidorEntities() {
        return findCDistribuidorEntities(true, -1, -1);
    }

    public List<CDistribuidor> findCDistribuidorEntities(int maxResults, int firstResult) {
        return findCDistribuidorEntities(false, maxResults, firstResult);
    }

    private List<CDistribuidor> findCDistribuidorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CDistribuidor.class));
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

    public CDistribuidor findCDistribuidor(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CDistribuidor.class, id);
        } finally {
            em.close();
        }
    }

    public int getCDistribuidorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CDistribuidor> rt = cq.from(CDistribuidor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
