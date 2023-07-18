/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blueweb.controller;

import com.blueweb.controller.exceptions.IllegalOrphanException;
import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.entity.SPerfiles;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.blueweb.entity.SUsuarios;
import java.util.ArrayList;
import java.util.Collection;
import com.blueweb.entity.SPerfilesAccesos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class SPerfilesJpaController1 implements Serializable {

    public SPerfilesJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SPerfiles SPerfiles) throws IllegalOrphanException {
        if (SPerfiles.getSUsuariosCollection() == null) {
            SPerfiles.setSUsuariosCollection(new ArrayList<SUsuarios>());
        }
        if (SPerfiles.getSPerfilesAccesosCollection() == null) {
            SPerfiles.setSPerfilesAccesosCollection(new ArrayList<SPerfilesAccesos>());
        }
        List<String> illegalOrphanMessages = null;
        SUsuarios idUsuarioModificaOrphanCheck = SPerfiles.getIdUsuarioModifica();
        if (idUsuarioModificaOrphanCheck != null) {
            SPerfiles oldIdPerfilOfIdUsuarioModifica = idUsuarioModificaOrphanCheck.getIdPerfil();
            if (oldIdPerfilOfIdUsuarioModifica != null) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("The SUsuarios " + idUsuarioModificaOrphanCheck + " already has an item of type SPerfiles whose idUsuarioModifica column cannot be null. Please make another selection for the idUsuarioModifica field.");
            }
        }
        if (illegalOrphanMessages != null) {
            throw new IllegalOrphanException(illegalOrphanMessages);
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios idUsuarioModifica = SPerfiles.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica = em.getReference(idUsuarioModifica.getClass(), idUsuarioModifica.getIdUsuario());
                SPerfiles.setIdUsuarioModifica(idUsuarioModifica);
            }
            Collection<SUsuarios> attachedSUsuariosCollection = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionSUsuariosToAttach : SPerfiles.getSUsuariosCollection()) {
                SUsuariosCollectionSUsuariosToAttach = em.getReference(SUsuariosCollectionSUsuariosToAttach.getClass(), SUsuariosCollectionSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollection.add(SUsuariosCollectionSUsuariosToAttach);
            }
            SPerfiles.setSUsuariosCollection(attachedSUsuariosCollection);
            Collection<SPerfilesAccesos> attachedSPerfilesAccesosCollection = new ArrayList<SPerfilesAccesos>();
            for (SPerfilesAccesos SPerfilesAccesosCollectionSPerfilesAccesosToAttach : SPerfiles.getSPerfilesAccesosCollection()) {
                SPerfilesAccesosCollectionSPerfilesAccesosToAttach = em.getReference(SPerfilesAccesosCollectionSPerfilesAccesosToAttach.getClass(), SPerfilesAccesosCollectionSPerfilesAccesosToAttach.getSPerfilesAccesosPK());
                attachedSPerfilesAccesosCollection.add(SPerfilesAccesosCollectionSPerfilesAccesosToAttach);
            }
            SPerfiles.setSPerfilesAccesosCollection(attachedSPerfilesAccesosCollection);
            em.persist(SPerfiles);
            if (idUsuarioModifica != null) {
                idUsuarioModifica.setIdPerfil(SPerfiles);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            for (SUsuarios SUsuariosCollectionSUsuarios : SPerfiles.getSUsuariosCollection()) {
                SPerfiles oldIdPerfilOfSUsuariosCollectionSUsuarios = SUsuariosCollectionSUsuarios.getIdPerfil();
                SUsuariosCollectionSUsuarios.setIdPerfil(SPerfiles);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
                if (oldIdPerfilOfSUsuariosCollectionSUsuarios != null) {
                    oldIdPerfilOfSUsuariosCollectionSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionSUsuarios);
                    oldIdPerfilOfSUsuariosCollectionSUsuarios = em.merge(oldIdPerfilOfSUsuariosCollectionSUsuarios);
                }
            }
            for (SPerfilesAccesos SPerfilesAccesosCollectionSPerfilesAccesos : SPerfiles.getSPerfilesAccesosCollection()) {
                SPerfiles oldSPerfilesOfSPerfilesAccesosCollectionSPerfilesAccesos = SPerfilesAccesosCollectionSPerfilesAccesos.getSPerfiles();
                SPerfilesAccesosCollectionSPerfilesAccesos.setSPerfiles(SPerfiles);
                SPerfilesAccesosCollectionSPerfilesAccesos = em.merge(SPerfilesAccesosCollectionSPerfilesAccesos);
                if (oldSPerfilesOfSPerfilesAccesosCollectionSPerfilesAccesos != null) {
                    oldSPerfilesOfSPerfilesAccesosCollectionSPerfilesAccesos.getSPerfilesAccesosCollection().remove(SPerfilesAccesosCollectionSPerfilesAccesos);
                    oldSPerfilesOfSPerfilesAccesosCollectionSPerfilesAccesos = em.merge(oldSPerfilesOfSPerfilesAccesosCollectionSPerfilesAccesos);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SPerfiles SPerfiles) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfiles persistentSPerfiles = em.find(SPerfiles.class, SPerfiles.getIdPerfil());
            SUsuarios idUsuarioModificaOld = persistentSPerfiles.getIdUsuarioModifica();
            SUsuarios idUsuarioModificaNew = SPerfiles.getIdUsuarioModifica();
            Collection<SUsuarios> SUsuariosCollectionOld = persistentSPerfiles.getSUsuariosCollection();
            Collection<SUsuarios> SUsuariosCollectionNew = SPerfiles.getSUsuariosCollection();
            Collection<SPerfilesAccesos> SPerfilesAccesosCollectionOld = persistentSPerfiles.getSPerfilesAccesosCollection();
            Collection<SPerfilesAccesos> SPerfilesAccesosCollectionNew = SPerfiles.getSPerfilesAccesosCollection();
            List<String> illegalOrphanMessages = null;
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                SPerfiles oldIdPerfilOfIdUsuarioModifica = idUsuarioModificaNew.getIdPerfil();
                if (oldIdPerfilOfIdUsuarioModifica != null) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("The SUsuarios " + idUsuarioModificaNew + " already has an item of type SPerfiles whose idUsuarioModifica column cannot be null. Please make another selection for the idUsuarioModifica field.");
                }
            }
            for (SPerfilesAccesos SPerfilesAccesosCollectionOldSPerfilesAccesos : SPerfilesAccesosCollectionOld) {
                if (!SPerfilesAccesosCollectionNew.contains(SPerfilesAccesosCollectionOldSPerfilesAccesos)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SPerfilesAccesos " + SPerfilesAccesosCollectionOldSPerfilesAccesos + " since its SPerfiles field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idUsuarioModificaNew != null) {
                idUsuarioModificaNew = em.getReference(idUsuarioModificaNew.getClass(), idUsuarioModificaNew.getIdUsuario());
                SPerfiles.setIdUsuarioModifica(idUsuarioModificaNew);
            }
            Collection<SUsuarios> attachedSUsuariosCollectionNew = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionNewSUsuariosToAttach : SUsuariosCollectionNew) {
                SUsuariosCollectionNewSUsuariosToAttach = em.getReference(SUsuariosCollectionNewSUsuariosToAttach.getClass(), SUsuariosCollectionNewSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollectionNew.add(SUsuariosCollectionNewSUsuariosToAttach);
            }
            SUsuariosCollectionNew = attachedSUsuariosCollectionNew;
            SPerfiles.setSUsuariosCollection(SUsuariosCollectionNew);
            Collection<SPerfilesAccesos> attachedSPerfilesAccesosCollectionNew = new ArrayList<SPerfilesAccesos>();
            for (SPerfilesAccesos SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach : SPerfilesAccesosCollectionNew) {
                SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach = em.getReference(SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach.getClass(), SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach.getSPerfilesAccesosPK());
                attachedSPerfilesAccesosCollectionNew.add(SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach);
            }
            SPerfilesAccesosCollectionNew = attachedSPerfilesAccesosCollectionNew;
            SPerfiles.setSPerfilesAccesosCollection(SPerfilesAccesosCollectionNew);
            SPerfiles = em.merge(SPerfiles);
            if (idUsuarioModificaOld != null && !idUsuarioModificaOld.equals(idUsuarioModificaNew)) {
                idUsuarioModificaOld.setIdPerfil(null);
                idUsuarioModificaOld = em.merge(idUsuarioModificaOld);
            }
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                idUsuarioModificaNew.setIdPerfil(SPerfiles);
                idUsuarioModificaNew = em.merge(idUsuarioModificaNew);
            }
            for (SUsuarios SUsuariosCollectionOldSUsuarios : SUsuariosCollectionOld) {
                if (!SUsuariosCollectionNew.contains(SUsuariosCollectionOldSUsuarios)) {
                    SUsuariosCollectionOldSUsuarios.setIdPerfil(null);
                    SUsuariosCollectionOldSUsuarios = em.merge(SUsuariosCollectionOldSUsuarios);
                }
            }
            for (SUsuarios SUsuariosCollectionNewSUsuarios : SUsuariosCollectionNew) {
                if (!SUsuariosCollectionOld.contains(SUsuariosCollectionNewSUsuarios)) {
                    SPerfiles oldIdPerfilOfSUsuariosCollectionNewSUsuarios = SUsuariosCollectionNewSUsuarios.getIdPerfil();
                    SUsuariosCollectionNewSUsuarios.setIdPerfil(SPerfiles);
                    SUsuariosCollectionNewSUsuarios = em.merge(SUsuariosCollectionNewSUsuarios);
                    if (oldIdPerfilOfSUsuariosCollectionNewSUsuarios != null && !oldIdPerfilOfSUsuariosCollectionNewSUsuarios.equals(SPerfiles)) {
                        oldIdPerfilOfSUsuariosCollectionNewSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionNewSUsuarios);
                        oldIdPerfilOfSUsuariosCollectionNewSUsuarios = em.merge(oldIdPerfilOfSUsuariosCollectionNewSUsuarios);
                    }
                }
            }
            for (SPerfilesAccesos SPerfilesAccesosCollectionNewSPerfilesAccesos : SPerfilesAccesosCollectionNew) {
                if (!SPerfilesAccesosCollectionOld.contains(SPerfilesAccesosCollectionNewSPerfilesAccesos)) {
                    SPerfiles oldSPerfilesOfSPerfilesAccesosCollectionNewSPerfilesAccesos = SPerfilesAccesosCollectionNewSPerfilesAccesos.getSPerfiles();
                    SPerfilesAccesosCollectionNewSPerfilesAccesos.setSPerfiles(SPerfiles);
                    SPerfilesAccesosCollectionNewSPerfilesAccesos = em.merge(SPerfilesAccesosCollectionNewSPerfilesAccesos);
                    if (oldSPerfilesOfSPerfilesAccesosCollectionNewSPerfilesAccesos != null && !oldSPerfilesOfSPerfilesAccesosCollectionNewSPerfilesAccesos.equals(SPerfiles)) {
                        oldSPerfilesOfSPerfilesAccesosCollectionNewSPerfilesAccesos.getSPerfilesAccesosCollection().remove(SPerfilesAccesosCollectionNewSPerfilesAccesos);
                        oldSPerfilesOfSPerfilesAccesosCollectionNewSPerfilesAccesos = em.merge(oldSPerfilesOfSPerfilesAccesosCollectionNewSPerfilesAccesos);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SPerfiles.getIdPerfil();
                if (findSPerfiles(id) == null) {
                    throw new NonexistentEntityException("The sPerfiles with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfiles SPerfiles;
            try {
                SPerfiles = em.getReference(SPerfiles.class, id);
                SPerfiles.getIdPerfil();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SPerfiles with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<SPerfilesAccesos> SPerfilesAccesosCollectionOrphanCheck = SPerfiles.getSPerfilesAccesosCollection();
            for (SPerfilesAccesos SPerfilesAccesosCollectionOrphanCheckSPerfilesAccesos : SPerfilesAccesosCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SPerfiles (" + SPerfiles + ") cannot be destroyed since the SPerfilesAccesos " + SPerfilesAccesosCollectionOrphanCheckSPerfilesAccesos + " in its SPerfilesAccesosCollection field has a non-nullable SPerfiles field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            SUsuarios idUsuarioModifica = SPerfiles.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica.setIdPerfil(null);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            Collection<SUsuarios> SUsuariosCollection = SPerfiles.getSUsuariosCollection();
            for (SUsuarios SUsuariosCollectionSUsuarios : SUsuariosCollection) {
                SUsuariosCollectionSUsuarios.setIdPerfil(null);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
            }
            em.remove(SPerfiles);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SPerfiles> findSPerfilesEntities() {
        return findSPerfilesEntities(true, -1, -1);
    }

    public List<SPerfiles> findSPerfilesEntities(int maxResults, int firstResult) {
        return findSPerfilesEntities(false, maxResults, firstResult);
    }

    private List<SPerfiles> findSPerfilesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SPerfiles.class));
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

    public SPerfiles findSPerfiles(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SPerfiles.class, id);
        } finally {
            em.close();
        }
    }

    public int getSPerfilesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SPerfiles> rt = cq.from(SPerfiles.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
