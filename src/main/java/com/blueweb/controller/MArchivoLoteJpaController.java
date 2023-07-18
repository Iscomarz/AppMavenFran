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
import com.blueweb.entity.HActivacion;
import com.blueweb.entity.MArchivoLote;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class MArchivoLoteJpaController implements Serializable {

    public MArchivoLoteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(MArchivoLote MArchivoLote) throws PreexistingEntityException, Exception {
        if (MArchivoLote.getHActivacionCollection() == null) {
            MArchivoLote.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios idUsuario = MArchivoLote.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                MArchivoLote.setIdUsuario(idUsuario);
            }
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : MArchivoLote.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            MArchivoLote.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(MArchivoLote);
            if (idUsuario != null) {
                idUsuario.getMArchivoLoteCollection().add(MArchivoLote);
                idUsuario = em.merge(idUsuario);
            }
            for (HActivacion HActivacionCollectionHActivacion : MArchivoLote.getHActivacionCollection()) {
                MArchivoLote oldIdArchivoOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdArchivo();
                HActivacionCollectionHActivacion.setIdArchivo(MArchivoLote);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdArchivoOfHActivacionCollectionHActivacion != null) {
                    oldIdArchivoOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdArchivoOfHActivacionCollectionHActivacion = em.merge(oldIdArchivoOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMArchivoLote(MArchivoLote.getIdArchivo()) != null) {
                throw new PreexistingEntityException("MArchivoLote " + MArchivoLote + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(MArchivoLote MArchivoLote) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            MArchivoLote persistentMArchivoLote = em.find(MArchivoLote.class, MArchivoLote.getIdArchivo());
            SUsuarios idUsuarioOld = persistentMArchivoLote.getIdUsuario();
            SUsuarios idUsuarioNew = MArchivoLote.getIdUsuario();
            Collection<HActivacion> HActivacionCollectionOld = persistentMArchivoLote.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = MArchivoLote.getHActivacionCollection();
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                MArchivoLote.setIdUsuario(idUsuarioNew);
            }
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            MArchivoLote.setHActivacionCollection(HActivacionCollectionNew);
            MArchivoLote = em.merge(MArchivoLote);
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getMArchivoLoteCollection().remove(MArchivoLote);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getMArchivoLoteCollection().add(MArchivoLote);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdArchivo(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    MArchivoLote oldIdArchivoOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdArchivo();
                    HActivacionCollectionNewHActivacion.setIdArchivo(MArchivoLote);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdArchivoOfHActivacionCollectionNewHActivacion != null && !oldIdArchivoOfHActivacionCollectionNewHActivacion.equals(MArchivoLote)) {
                        oldIdArchivoOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdArchivoOfHActivacionCollectionNewHActivacion = em.merge(oldIdArchivoOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = MArchivoLote.getIdArchivo();
                if (findMArchivoLote(id) == null) {
                    throw new NonexistentEntityException("The mArchivoLote with id " + id + " no longer exists.");
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
            MArchivoLote MArchivoLote;
            try {
                MArchivoLote = em.getReference(MArchivoLote.class, id);
                MArchivoLote.getIdArchivo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The MArchivoLote with id " + id + " no longer exists.", enfe);
            }
            SUsuarios idUsuario = MArchivoLote.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getMArchivoLoteCollection().remove(MArchivoLote);
                idUsuario = em.merge(idUsuario);
            }
            Collection<HActivacion> HActivacionCollection = MArchivoLote.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdArchivo(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(MArchivoLote);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<MArchivoLote> findMArchivoLoteEntities() {
        return findMArchivoLoteEntities(true, -1, -1);
    }

    public List<MArchivoLote> findMArchivoLoteEntities(int maxResults, int firstResult) {
        return findMArchivoLoteEntities(false, maxResults, firstResult);
    }

    private List<MArchivoLote> findMArchivoLoteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MArchivoLote.class));
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

    public MArchivoLote findMArchivoLote(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(MArchivoLote.class, id);
        } finally {
            em.close();
        }
    }

    public int getMArchivoLoteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<MArchivoLote> rt = cq.from(MArchivoLote.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
