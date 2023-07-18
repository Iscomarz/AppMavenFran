
package com.blueweb.controller;

import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.controller.exceptions.PreexistingEntityException;
import com.blueweb.data.utils.LocalEntityManagerFactory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.blueweb.entity.SAccesos;
import com.blueweb.entity.SPerfiles;
import com.blueweb.entity.SPerfilesAccesos;
import com.blueweb.entity.SPerfilesAccesosPK;
import com.blueweb.entity.SUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Blueweb
 */
public class SPerfilesAccesosJpaController implements Serializable {

    private List<SPerfilesAccesos> accesosPerfil;

    public SPerfilesAccesosJpaController(){
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SPerfilesAccesos SPerfilesAccesos) throws PreexistingEntityException, Exception {
        if (SPerfilesAccesos.getSPerfilesAccesosPK() == null) {
            SPerfilesAccesos.setSPerfilesAccesosPK(new SPerfilesAccesosPK());
        }
        SPerfilesAccesos.getSPerfilesAccesosPK().setIdAcceso(SPerfilesAccesos.getSAccesos().getIdAcceso());
        SPerfilesAccesos.getSPerfilesAccesosPK().setIdPerfil(SPerfilesAccesos.getSPerfiles().getIdPerfil());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SAccesos SAccesos = SPerfilesAccesos.getSAccesos();
            if (SAccesos != null) {
                SAccesos = em.getReference(SAccesos.getClass(), SAccesos.getIdAcceso());
                SPerfilesAccesos.setSAccesos(SAccesos);
            }
            SPerfiles SPerfiles = SPerfilesAccesos.getSPerfiles();
            if (SPerfiles != null) {
                SPerfiles = em.getReference(SPerfiles.getClass(), SPerfiles.getIdPerfil());
                SPerfilesAccesos.setSPerfiles(SPerfiles);
            }
            SUsuarios idUsuarioModifica = SPerfilesAccesos.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica = em.getReference(idUsuarioModifica.getClass(), idUsuarioModifica.getIdUsuario());
                SPerfilesAccesos.setIdUsuarioModifica(idUsuarioModifica);
            }
            em.persist(SPerfilesAccesos);
            if (SAccesos != null) {
                SAccesos.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                SAccesos = em.merge(SAccesos);
            }
            if (SPerfiles != null) {
                SPerfiles.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                SPerfiles = em.merge(SPerfiles);
            }
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSPerfilesAccesos(SPerfilesAccesos.getSPerfilesAccesosPK()) != null) {
                throw new PreexistingEntityException("SPerfilesAccesos " + SPerfilesAccesos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SPerfilesAccesos SPerfilesAccesos) throws NonexistentEntityException, Exception {
        SPerfilesAccesos.getSPerfilesAccesosPK().setIdAcceso(SPerfilesAccesos.getSAccesos().getIdAcceso());
        SPerfilesAccesos.getSPerfilesAccesosPK().setIdPerfil(SPerfilesAccesos.getSPerfiles().getIdPerfil());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfilesAccesos persistentSPerfilesAccesos = em.find(SPerfilesAccesos.class, SPerfilesAccesos.getSPerfilesAccesosPK());
            SAccesos SAccesosOld = persistentSPerfilesAccesos.getSAccesos();
            SAccesos SAccesosNew = SPerfilesAccesos.getSAccesos();
            SPerfiles SPerfilesOld = persistentSPerfilesAccesos.getSPerfiles();
            SPerfiles SPerfilesNew = SPerfilesAccesos.getSPerfiles();
            SUsuarios idUsuarioModificaOld = persistentSPerfilesAccesos.getIdUsuarioModifica();
            SUsuarios idUsuarioModificaNew = SPerfilesAccesos.getIdUsuarioModifica();
            if (SAccesosNew != null) {
                SAccesosNew = em.getReference(SAccesosNew.getClass(), SAccesosNew.getIdAcceso());
                SPerfilesAccesos.setSAccesos(SAccesosNew);
            }
            if (SPerfilesNew != null) {
                SPerfilesNew = em.getReference(SPerfilesNew.getClass(), SPerfilesNew.getIdPerfil());
                SPerfilesAccesos.setSPerfiles(SPerfilesNew);
            }
            if (idUsuarioModificaNew != null) {
                idUsuarioModificaNew = em.getReference(idUsuarioModificaNew.getClass(), idUsuarioModificaNew.getIdUsuario());
                SPerfilesAccesos.setIdUsuarioModifica(idUsuarioModificaNew);
            }
            SPerfilesAccesos = em.merge(SPerfilesAccesos);
            if (SAccesosOld != null && !SAccesosOld.equals(SAccesosNew)) {
                SAccesosOld.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                SAccesosOld = em.merge(SAccesosOld);
            }
            if (SAccesosNew != null && !SAccesosNew.equals(SAccesosOld)) {
                SAccesosNew.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                SAccesosNew = em.merge(SAccesosNew);
            }
            if (SPerfilesOld != null && !SPerfilesOld.equals(SPerfilesNew)) {
                SPerfilesOld.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                SPerfilesOld = em.merge(SPerfilesOld);
            }
            if (SPerfilesNew != null && !SPerfilesNew.equals(SPerfilesOld)) {
                SPerfilesNew.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                SPerfilesNew = em.merge(SPerfilesNew);
            }
            if (idUsuarioModificaOld != null && !idUsuarioModificaOld.equals(idUsuarioModificaNew)) {
                idUsuarioModificaOld.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                idUsuarioModificaOld = em.merge(idUsuarioModificaOld);
            }
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                idUsuarioModificaNew.getSPerfilesAccesosCollection().add(SPerfilesAccesos);
                idUsuarioModificaNew = em.merge(idUsuarioModificaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                SPerfilesAccesosPK id = SPerfilesAccesos.getSPerfilesAccesosPK();
                if (findSPerfilesAccesos(id) == null) {
                    throw new NonexistentEntityException("The sPerfilesAccesos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(SPerfilesAccesosPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SPerfilesAccesos SPerfilesAccesos;
            try {
                SPerfilesAccesos = em.getReference(SPerfilesAccesos.class, id);
                SPerfilesAccesos.getSPerfilesAccesosPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SPerfilesAccesos with id " + id + " no longer exists.", enfe);
            }
            SAccesos SAccesos = SPerfilesAccesos.getSAccesos();
            if (SAccesos != null) {
                SAccesos.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                SAccesos = em.merge(SAccesos);
            }
            SPerfiles SPerfiles = SPerfilesAccesos.getSPerfiles();
            if (SPerfiles != null) {
                SPerfiles.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                SPerfiles = em.merge(SPerfiles);
            }
            SUsuarios idUsuarioModifica = SPerfilesAccesos.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getSPerfilesAccesosCollection().remove(SPerfilesAccesos);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            em.remove(SPerfilesAccesos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SPerfilesAccesos> findSPerfilesAccesosEntities() {
        return findSPerfilesAccesosEntities(true, -1, -1);
    }

    public List<SPerfilesAccesos> findSPerfilesAccesosEntities(int maxResults, int firstResult) {
        return findSPerfilesAccesosEntities(false, maxResults, firstResult);
    }

    private List<SPerfilesAccesos> findSPerfilesAccesosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SPerfilesAccesos.class));
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

    public SPerfilesAccesos findSPerfilesAccesos(SPerfilesAccesosPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SPerfilesAccesos.class, id);
        } finally {
            em.close();
        }
    }

    public int getSPerfilesAccesosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SPerfilesAccesos> rt = cq.from(SPerfilesAccesos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<SAccesos> encontrarPerfilesAccesosActuales(int idPerfil) {
        EntityManager em = getEntityManager();
        List<SAccesos> listAccesos = new ArrayList<>();
        try {
           
        TypedQuery<SAccesos> query;
        query = em.createQuery("SELECT a FROM SAccesos a JOIN SPerfilesAccesos spa ON a.idAcceso = spa.sPerfilesAccesosPK.idAcceso "
                + "WHERE spa.sPerfilesAccesosPK.idPerfil = :idPerfil", SAccesos.class);
                query.setParameter("idPerfil", idPerfil);

        listAccesos = query.getResultList();
        

        } finally {
            em.close();
        }
        return listAccesos;
    }
    
        public List<SAccesos> encontrarPerfilesAccesosDisponibles(int idPerfil) {
        EntityManager em = getEntityManager();
        List<SAccesos> listAccesos = new ArrayList<>();
        try {
           
        TypedQuery<SAccesos> query;
        query = em.createQuery("SELECT a FROM SAccesos a WHERE a.idAcceso not in "
                + "(SELECT r.sPerfilesAccesosPK.idAcceso FROM SPerfilesAccesos "
                + "r WHERE r.sPerfilesAccesosPK.idPerfil = :idPerfil)", SAccesos.class);
                query.setParameter("idPerfil", idPerfil);

        listAccesos = query.getResultList();
        

        } finally {
            em.close();
        }
        return listAccesos;
    }

    /**
     * @return the accesosPerfil
     */
    public List<SPerfilesAccesos> getAccesosPerfil() {
        return accesosPerfil;
    }

    /**
     * @param accesosPerfil the accesosPerfil to set
     */
    public void setAccesosPerfil(List<SPerfilesAccesos> accesosPerfil) {
        this.accesosPerfil = accesosPerfil;
    }

    /**
     * @return the accesosPerfil
     */


}
