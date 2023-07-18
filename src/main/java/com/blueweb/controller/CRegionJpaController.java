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
import com.blueweb.entity.SUsuarios;
import com.blueweb.entity.CDistribuidor;
import java.util.ArrayList;
import java.util.Collection;
import com.blueweb.entity.CAutomata;
import com.blueweb.entity.CRegion;
import com.blueweb.entity.CTelefonia;
import com.blueweb.entity.HActivacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class CRegionJpaController implements Serializable {

    public CRegionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CRegion CRegion) throws PreexistingEntityException, Exception {
        if (CRegion.getCDistribuidorCollection() == null) {
            CRegion.setCDistribuidorCollection(new ArrayList<CDistribuidor>());
        }
        if (CRegion.getCAutomataCollection() == null) {
            CRegion.setCAutomataCollection(new ArrayList<CAutomata>());
        }
        if (CRegion.getCTelefoniaCollection() == null) {
            CRegion.setCTelefoniaCollection(new ArrayList<CTelefonia>());
        }
        if (CRegion.getHActivacionCollection() == null) {
            CRegion.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios idUsuarioModifica = CRegion.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica = em.getReference(idUsuarioModifica.getClass(), idUsuarioModifica.getIdUsuario());
                CRegion.setIdUsuarioModifica(idUsuarioModifica);
            }
            Collection<CDistribuidor> attachedCDistribuidorCollection = new ArrayList<CDistribuidor>();
            for (CDistribuidor CDistribuidorCollectionCDistribuidorToAttach : CRegion.getCDistribuidorCollection()) {
                CDistribuidorCollectionCDistribuidorToAttach = em.getReference(CDistribuidorCollectionCDistribuidorToAttach.getClass(), CDistribuidorCollectionCDistribuidorToAttach.getIdDistribuidor());
                attachedCDistribuidorCollection.add(CDistribuidorCollectionCDistribuidorToAttach);
            }
            CRegion.setCDistribuidorCollection(attachedCDistribuidorCollection);
            Collection<CAutomata> attachedCAutomataCollection = new ArrayList<CAutomata>();
            for (CAutomata CAutomataCollectionCAutomataToAttach : CRegion.getCAutomataCollection()) {
                CAutomataCollectionCAutomataToAttach = em.getReference(CAutomataCollectionCAutomataToAttach.getClass(), CAutomataCollectionCAutomataToAttach.getIdAutomata());
                attachedCAutomataCollection.add(CAutomataCollectionCAutomataToAttach);
            }
            CRegion.setCAutomataCollection(attachedCAutomataCollection);
            Collection<CTelefonia> attachedCTelefoniaCollection = new ArrayList<CTelefonia>();
            for (CTelefonia CTelefoniaCollectionCTelefoniaToAttach : CRegion.getCTelefoniaCollection()) {
                CTelefoniaCollectionCTelefoniaToAttach = em.getReference(CTelefoniaCollectionCTelefoniaToAttach.getClass(), CTelefoniaCollectionCTelefoniaToAttach.getIdTelefonia());
                attachedCTelefoniaCollection.add(CTelefoniaCollectionCTelefoniaToAttach);
            }
            CRegion.setCTelefoniaCollection(attachedCTelefoniaCollection);
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : CRegion.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            CRegion.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(CRegion);
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getCRegionCollection().add(CRegion);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            for (CDistribuidor CDistribuidorCollectionCDistribuidor : CRegion.getCDistribuidorCollection()) {
                CRegion oldIdRegionOfCDistribuidorCollectionCDistribuidor = CDistribuidorCollectionCDistribuidor.getIdRegion();
                CDistribuidorCollectionCDistribuidor.setIdRegion(CRegion);
                CDistribuidorCollectionCDistribuidor = em.merge(CDistribuidorCollectionCDistribuidor);
                if (oldIdRegionOfCDistribuidorCollectionCDistribuidor != null) {
                    oldIdRegionOfCDistribuidorCollectionCDistribuidor.getCDistribuidorCollection().remove(CDistribuidorCollectionCDistribuidor);
                    oldIdRegionOfCDistribuidorCollectionCDistribuidor = em.merge(oldIdRegionOfCDistribuidorCollectionCDistribuidor);
                }
            }
            for (CAutomata CAutomataCollectionCAutomata : CRegion.getCAutomataCollection()) {
                CRegion oldIdRegionOfCAutomataCollectionCAutomata = CAutomataCollectionCAutomata.getIdRegion();
                CAutomataCollectionCAutomata.setIdRegion(CRegion);
                CAutomataCollectionCAutomata = em.merge(CAutomataCollectionCAutomata);
                if (oldIdRegionOfCAutomataCollectionCAutomata != null) {
                    oldIdRegionOfCAutomataCollectionCAutomata.getCAutomataCollection().remove(CAutomataCollectionCAutomata);
                    oldIdRegionOfCAutomataCollectionCAutomata = em.merge(oldIdRegionOfCAutomataCollectionCAutomata);
                }
            }
            for (CTelefonia CTelefoniaCollectionCTelefonia : CRegion.getCTelefoniaCollection()) {
                CRegion oldIdRegionOfCTelefoniaCollectionCTelefonia = CTelefoniaCollectionCTelefonia.getIdRegion();
                CTelefoniaCollectionCTelefonia.setIdRegion(CRegion);
                CTelefoniaCollectionCTelefonia = em.merge(CTelefoniaCollectionCTelefonia);
                if (oldIdRegionOfCTelefoniaCollectionCTelefonia != null) {
                    oldIdRegionOfCTelefoniaCollectionCTelefonia.getCTelefoniaCollection().remove(CTelefoniaCollectionCTelefonia);
                    oldIdRegionOfCTelefoniaCollectionCTelefonia = em.merge(oldIdRegionOfCTelefoniaCollectionCTelefonia);
                }
            }
            for (HActivacion HActivacionCollectionHActivacion : CRegion.getHActivacionCollection()) {
                CRegion oldIdRegionOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdRegion();
                HActivacionCollectionHActivacion.setIdRegion(CRegion);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdRegionOfHActivacionCollectionHActivacion != null) {
                    oldIdRegionOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdRegionOfHActivacionCollectionHActivacion = em.merge(oldIdRegionOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCRegion(CRegion.getIdRegion()) != null) {
                throw new PreexistingEntityException("CRegion " + CRegion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CRegion CRegion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CRegion persistentCRegion = em.find(CRegion.class, CRegion.getIdRegion());
            SUsuarios idUsuarioModificaOld = persistentCRegion.getIdUsuarioModifica();
            SUsuarios idUsuarioModificaNew = CRegion.getIdUsuarioModifica();
            Collection<CDistribuidor> CDistribuidorCollectionOld = persistentCRegion.getCDistribuidorCollection();
            Collection<CDistribuidor> CDistribuidorCollectionNew = CRegion.getCDistribuidorCollection();
            Collection<CAutomata> CAutomataCollectionOld = persistentCRegion.getCAutomataCollection();
            Collection<CAutomata> CAutomataCollectionNew = CRegion.getCAutomataCollection();
            Collection<CTelefonia> CTelefoniaCollectionOld = persistentCRegion.getCTelefoniaCollection();
            Collection<CTelefonia> CTelefoniaCollectionNew = CRegion.getCTelefoniaCollection();
            Collection<HActivacion> HActivacionCollectionOld = persistentCRegion.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = CRegion.getHActivacionCollection();
            if (idUsuarioModificaNew != null) {
                idUsuarioModificaNew = em.getReference(idUsuarioModificaNew.getClass(), idUsuarioModificaNew.getIdUsuario());
                CRegion.setIdUsuarioModifica(idUsuarioModificaNew);
            }
            Collection<CDistribuidor> attachedCDistribuidorCollectionNew = new ArrayList<CDistribuidor>();
            for (CDistribuidor CDistribuidorCollectionNewCDistribuidorToAttach : CDistribuidorCollectionNew) {
                CDistribuidorCollectionNewCDistribuidorToAttach = em.getReference(CDistribuidorCollectionNewCDistribuidorToAttach.getClass(), CDistribuidorCollectionNewCDistribuidorToAttach.getIdDistribuidor());
                attachedCDistribuidorCollectionNew.add(CDistribuidorCollectionNewCDistribuidorToAttach);
            }
            CDistribuidorCollectionNew = attachedCDistribuidorCollectionNew;
            CRegion.setCDistribuidorCollection(CDistribuidorCollectionNew);
            Collection<CAutomata> attachedCAutomataCollectionNew = new ArrayList<CAutomata>();
            for (CAutomata CAutomataCollectionNewCAutomataToAttach : CAutomataCollectionNew) {
                CAutomataCollectionNewCAutomataToAttach = em.getReference(CAutomataCollectionNewCAutomataToAttach.getClass(), CAutomataCollectionNewCAutomataToAttach.getIdAutomata());
                attachedCAutomataCollectionNew.add(CAutomataCollectionNewCAutomataToAttach);
            }
            CAutomataCollectionNew = attachedCAutomataCollectionNew;
            CRegion.setCAutomataCollection(CAutomataCollectionNew);
            Collection<CTelefonia> attachedCTelefoniaCollectionNew = new ArrayList<CTelefonia>();
            for (CTelefonia CTelefoniaCollectionNewCTelefoniaToAttach : CTelefoniaCollectionNew) {
                CTelefoniaCollectionNewCTelefoniaToAttach = em.getReference(CTelefoniaCollectionNewCTelefoniaToAttach.getClass(), CTelefoniaCollectionNewCTelefoniaToAttach.getIdTelefonia());
                attachedCTelefoniaCollectionNew.add(CTelefoniaCollectionNewCTelefoniaToAttach);
            }
            CTelefoniaCollectionNew = attachedCTelefoniaCollectionNew;
            CRegion.setCTelefoniaCollection(CTelefoniaCollectionNew);
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            CRegion.setHActivacionCollection(HActivacionCollectionNew);
            CRegion = em.merge(CRegion);
            if (idUsuarioModificaOld != null && !idUsuarioModificaOld.equals(idUsuarioModificaNew)) {
                idUsuarioModificaOld.getCRegionCollection().remove(CRegion);
                idUsuarioModificaOld = em.merge(idUsuarioModificaOld);
            }
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                idUsuarioModificaNew.getCRegionCollection().add(CRegion);
                idUsuarioModificaNew = em.merge(idUsuarioModificaNew);
            }
            for (CDistribuidor CDistribuidorCollectionOldCDistribuidor : CDistribuidorCollectionOld) {
                if (!CDistribuidorCollectionNew.contains(CDistribuidorCollectionOldCDistribuidor)) {
                    CDistribuidorCollectionOldCDistribuidor.setIdRegion(null);
                    CDistribuidorCollectionOldCDistribuidor = em.merge(CDistribuidorCollectionOldCDistribuidor);
                }
            }
            for (CDistribuidor CDistribuidorCollectionNewCDistribuidor : CDistribuidorCollectionNew) {
                if (!CDistribuidorCollectionOld.contains(CDistribuidorCollectionNewCDistribuidor)) {
                    CRegion oldIdRegionOfCDistribuidorCollectionNewCDistribuidor = CDistribuidorCollectionNewCDistribuidor.getIdRegion();
                    CDistribuidorCollectionNewCDistribuidor.setIdRegion(CRegion);
                    CDistribuidorCollectionNewCDistribuidor = em.merge(CDistribuidorCollectionNewCDistribuidor);
                    if (oldIdRegionOfCDistribuidorCollectionNewCDistribuidor != null && !oldIdRegionOfCDistribuidorCollectionNewCDistribuidor.equals(CRegion)) {
                        oldIdRegionOfCDistribuidorCollectionNewCDistribuidor.getCDistribuidorCollection().remove(CDistribuidorCollectionNewCDistribuidor);
                        oldIdRegionOfCDistribuidorCollectionNewCDistribuidor = em.merge(oldIdRegionOfCDistribuidorCollectionNewCDistribuidor);
                    }
                }
            }
            for (CAutomata CAutomataCollectionOldCAutomata : CAutomataCollectionOld) {
                if (!CAutomataCollectionNew.contains(CAutomataCollectionOldCAutomata)) {
                    CAutomataCollectionOldCAutomata.setIdRegion(null);
                    CAutomataCollectionOldCAutomata = em.merge(CAutomataCollectionOldCAutomata);
                }
            }
            for (CAutomata CAutomataCollectionNewCAutomata : CAutomataCollectionNew) {
                if (!CAutomataCollectionOld.contains(CAutomataCollectionNewCAutomata)) {
                    CRegion oldIdRegionOfCAutomataCollectionNewCAutomata = CAutomataCollectionNewCAutomata.getIdRegion();
                    CAutomataCollectionNewCAutomata.setIdRegion(CRegion);
                    CAutomataCollectionNewCAutomata = em.merge(CAutomataCollectionNewCAutomata);
                    if (oldIdRegionOfCAutomataCollectionNewCAutomata != null && !oldIdRegionOfCAutomataCollectionNewCAutomata.equals(CRegion)) {
                        oldIdRegionOfCAutomataCollectionNewCAutomata.getCAutomataCollection().remove(CAutomataCollectionNewCAutomata);
                        oldIdRegionOfCAutomataCollectionNewCAutomata = em.merge(oldIdRegionOfCAutomataCollectionNewCAutomata);
                    }
                }
            }
            for (CTelefonia CTelefoniaCollectionOldCTelefonia : CTelefoniaCollectionOld) {
                if (!CTelefoniaCollectionNew.contains(CTelefoniaCollectionOldCTelefonia)) {
                    CTelefoniaCollectionOldCTelefonia.setIdRegion(null);
                    CTelefoniaCollectionOldCTelefonia = em.merge(CTelefoniaCollectionOldCTelefonia);
                }
            }
            for (CTelefonia CTelefoniaCollectionNewCTelefonia : CTelefoniaCollectionNew) {
                if (!CTelefoniaCollectionOld.contains(CTelefoniaCollectionNewCTelefonia)) {
                    CRegion oldIdRegionOfCTelefoniaCollectionNewCTelefonia = CTelefoniaCollectionNewCTelefonia.getIdRegion();
                    CTelefoniaCollectionNewCTelefonia.setIdRegion(CRegion);
                    CTelefoniaCollectionNewCTelefonia = em.merge(CTelefoniaCollectionNewCTelefonia);
                    if (oldIdRegionOfCTelefoniaCollectionNewCTelefonia != null && !oldIdRegionOfCTelefoniaCollectionNewCTelefonia.equals(CRegion)) {
                        oldIdRegionOfCTelefoniaCollectionNewCTelefonia.getCTelefoniaCollection().remove(CTelefoniaCollectionNewCTelefonia);
                        oldIdRegionOfCTelefoniaCollectionNewCTelefonia = em.merge(oldIdRegionOfCTelefoniaCollectionNewCTelefonia);
                    }
                }
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdRegion(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    CRegion oldIdRegionOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdRegion();
                    HActivacionCollectionNewHActivacion.setIdRegion(CRegion);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdRegionOfHActivacionCollectionNewHActivacion != null && !oldIdRegionOfHActivacionCollectionNewHActivacion.equals(CRegion)) {
                        oldIdRegionOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdRegionOfHActivacionCollectionNewHActivacion = em.merge(oldIdRegionOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CRegion.getIdRegion();
                if (findCRegion(id) == null) {
                    throw new NonexistentEntityException("The cRegion with id " + id + " no longer exists.");
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
            CRegion CRegion;
            try {
                CRegion = em.getReference(CRegion.class, id);
                CRegion.getIdRegion();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CRegion with id " + id + " no longer exists.", enfe);
            }
            SUsuarios idUsuarioModifica = CRegion.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getCRegionCollection().remove(CRegion);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            Collection<CDistribuidor> CDistribuidorCollection = CRegion.getCDistribuidorCollection();
            for (CDistribuidor CDistribuidorCollectionCDistribuidor : CDistribuidorCollection) {
                CDistribuidorCollectionCDistribuidor.setIdRegion(null);
                CDistribuidorCollectionCDistribuidor = em.merge(CDistribuidorCollectionCDistribuidor);
            }
            Collection<CAutomata> CAutomataCollection = CRegion.getCAutomataCollection();
            for (CAutomata CAutomataCollectionCAutomata : CAutomataCollection) {
                CAutomataCollectionCAutomata.setIdRegion(null);
                CAutomataCollectionCAutomata = em.merge(CAutomataCollectionCAutomata);
            }
            Collection<CTelefonia> CTelefoniaCollection = CRegion.getCTelefoniaCollection();
            for (CTelefonia CTelefoniaCollectionCTelefonia : CTelefoniaCollection) {
                CTelefoniaCollectionCTelefonia.setIdRegion(null);
                CTelefoniaCollectionCTelefonia = em.merge(CTelefoniaCollectionCTelefonia);
            }
            Collection<HActivacion> HActivacionCollection = CRegion.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdRegion(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(CRegion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CRegion> findCRegionEntities() {
        return findCRegionEntities(true, -1, -1);
    }

    public List<CRegion> findCRegionEntities(int maxResults, int firstResult) {
        return findCRegionEntities(false, maxResults, firstResult);
    }

    private List<CRegion> findCRegionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CRegion.class));
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

    public CRegion findCRegion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CRegion.class, id);
        } finally {
            em.close();
        }
    }

    public int getCRegionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CRegion> rt = cq.from(CRegion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
