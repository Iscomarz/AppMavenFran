
package com.blueweb.controller;

import com.blueweb.controller.exceptions.IllegalOrphanException;
import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.controller.exceptions.PreexistingEntityException;
import com.blueweb.data.utils.LocalEntityManagerFactory;
import com.blueweb.entity.SPerfiles;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.blueweb.entity.SUsuarios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class SPerfilesJpaController implements Serializable {

     public SPerfilesJpaController(){
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SPerfiles SPerfiles) throws IllegalOrphanException, PreexistingEntityException, Exception {
        if (SPerfiles.getSUsuariosCollection() == null) {
            SPerfiles.setSUsuariosCollection(new ArrayList<SUsuarios>());
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
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSPerfiles(SPerfiles.getIdPerfil()) != null) {
                throw new PreexistingEntityException("SPerfiles " + SPerfiles + " already exists.", ex);
            }
            throw ex;
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

    public void destroy(Integer id) throws NonexistentEntityException {
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
    
    public SPerfiles encontrarPerfil(int idPerfil){
        
        SPerfiles perfil = new SPerfiles();
        EntityManager em = getEntityManager();
                try {
            Query query = em.createNamedQuery("SPerfiles.findByIdPerfil");
            query.setParameter("idPerfil", idPerfil);
            
           perfil = (SPerfiles) query.getSingleResult();
        } finally {
            em.close();
        }
                
        return perfil;     
    }
    
    
    
}
