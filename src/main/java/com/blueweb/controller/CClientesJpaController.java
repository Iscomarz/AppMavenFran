/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blueweb.controller;

import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.controller.exceptions.PreexistingEntityException;
import com.blueweb.data.utils.LocalEntityManagerFactory;
import com.blueweb.entity.CClientes;
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
public class CClientesJpaController implements Serializable {

     public CClientesJpaController(){
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CClientes CClientes) throws PreexistingEntityException, Exception {
        if (CClientes.getSUsuariosCollection() == null) {
            CClientes.setSUsuariosCollection(new ArrayList<SUsuarios>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios idUsuarioModifica = CClientes.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica = em.getReference(idUsuarioModifica.getClass(), idUsuarioModifica.getIdUsuario());
                CClientes.setIdUsuarioModifica(idUsuarioModifica);
            }
            Collection<SUsuarios> attachedSUsuariosCollection = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionSUsuariosToAttach : CClientes.getSUsuariosCollection()) {
                SUsuariosCollectionSUsuariosToAttach = em.getReference(SUsuariosCollectionSUsuariosToAttach.getClass(), SUsuariosCollectionSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollection.add(SUsuariosCollectionSUsuariosToAttach);
            }
            CClientes.setSUsuariosCollection(attachedSUsuariosCollection);
            em.persist(CClientes);
            if (idUsuarioModifica != null) {
                CClientes oldIdClienteOfIdUsuarioModifica = idUsuarioModifica.getIdCliente();
                if (oldIdClienteOfIdUsuarioModifica != null) {
                    oldIdClienteOfIdUsuarioModifica.setIdUsuarioModifica(null);
                    oldIdClienteOfIdUsuarioModifica = em.merge(oldIdClienteOfIdUsuarioModifica);
                }
                idUsuarioModifica.setIdCliente(CClientes);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            for (SUsuarios SUsuariosCollectionSUsuarios : CClientes.getSUsuariosCollection()) {
                CClientes oldIdClienteOfSUsuariosCollectionSUsuarios = SUsuariosCollectionSUsuarios.getIdCliente();
                SUsuariosCollectionSUsuarios.setIdCliente(CClientes);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
                if (oldIdClienteOfSUsuariosCollectionSUsuarios != null) {
                    oldIdClienteOfSUsuariosCollectionSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionSUsuarios);
                    oldIdClienteOfSUsuariosCollectionSUsuarios = em.merge(oldIdClienteOfSUsuariosCollectionSUsuarios);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCClientes(CClientes.getIdCliente()) != null) {
                throw new PreexistingEntityException("CClientes " + CClientes + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CClientes CClientes) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CClientes persistentCClientes = em.find(CClientes.class, CClientes.getIdCliente());
            SUsuarios idUsuarioModificaOld = persistentCClientes.getIdUsuarioModifica();
            SUsuarios idUsuarioModificaNew = CClientes.getIdUsuarioModifica();
            Collection<SUsuarios> SUsuariosCollectionOld = persistentCClientes.getSUsuariosCollection();
            Collection<SUsuarios> SUsuariosCollectionNew = CClientes.getSUsuariosCollection();
            if (idUsuarioModificaNew != null) {
                idUsuarioModificaNew = em.getReference(idUsuarioModificaNew.getClass(), idUsuarioModificaNew.getIdUsuario());
                CClientes.setIdUsuarioModifica(idUsuarioModificaNew);
            }
            Collection<SUsuarios> attachedSUsuariosCollectionNew = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionNewSUsuariosToAttach : SUsuariosCollectionNew) {
                SUsuariosCollectionNewSUsuariosToAttach = em.getReference(SUsuariosCollectionNewSUsuariosToAttach.getClass(), SUsuariosCollectionNewSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollectionNew.add(SUsuariosCollectionNewSUsuariosToAttach);
            }
            SUsuariosCollectionNew = attachedSUsuariosCollectionNew;
            CClientes.setSUsuariosCollection(SUsuariosCollectionNew);
            CClientes = em.merge(CClientes);
            if (idUsuarioModificaOld != null && !idUsuarioModificaOld.equals(idUsuarioModificaNew)) {
                idUsuarioModificaOld.setIdCliente(null);
                idUsuarioModificaOld = em.merge(idUsuarioModificaOld);
            }
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                CClientes oldIdClienteOfIdUsuarioModifica = idUsuarioModificaNew.getIdCliente();
                if (oldIdClienteOfIdUsuarioModifica != null) {
                    oldIdClienteOfIdUsuarioModifica.setIdUsuarioModifica(null);
                    oldIdClienteOfIdUsuarioModifica = em.merge(oldIdClienteOfIdUsuarioModifica);
                }
                idUsuarioModificaNew.setIdCliente(CClientes);
                idUsuarioModificaNew = em.merge(idUsuarioModificaNew);
            }
            for (SUsuarios SUsuariosCollectionOldSUsuarios : SUsuariosCollectionOld) {
                if (!SUsuariosCollectionNew.contains(SUsuariosCollectionOldSUsuarios)) {
                    SUsuariosCollectionOldSUsuarios.setIdCliente(null);
                    SUsuariosCollectionOldSUsuarios = em.merge(SUsuariosCollectionOldSUsuarios);
                }
            }
            for (SUsuarios SUsuariosCollectionNewSUsuarios : SUsuariosCollectionNew) {
                if (!SUsuariosCollectionOld.contains(SUsuariosCollectionNewSUsuarios)) {
                    CClientes oldIdClienteOfSUsuariosCollectionNewSUsuarios = SUsuariosCollectionNewSUsuarios.getIdCliente();
                    SUsuariosCollectionNewSUsuarios.setIdCliente(CClientes);
                    SUsuariosCollectionNewSUsuarios = em.merge(SUsuariosCollectionNewSUsuarios);
                    if (oldIdClienteOfSUsuariosCollectionNewSUsuarios != null && !oldIdClienteOfSUsuariosCollectionNewSUsuarios.equals(CClientes)) {
                        oldIdClienteOfSUsuariosCollectionNewSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionNewSUsuarios);
                        oldIdClienteOfSUsuariosCollectionNewSUsuarios = em.merge(oldIdClienteOfSUsuariosCollectionNewSUsuarios);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = CClientes.getIdCliente();
                if (findCClientes(id) == null) {
                    throw new NonexistentEntityException("The cClientes with id " + id + " no longer exists.");
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
            CClientes CClientes;
            try {
                CClientes = em.getReference(CClientes.class, id);
                CClientes.getIdCliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The CClientes with id " + id + " no longer exists.", enfe);
            }
            SUsuarios idUsuarioModifica = CClientes.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica.setIdCliente(null);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            Collection<SUsuarios> SUsuariosCollection = CClientes.getSUsuariosCollection();
            for (SUsuarios SUsuariosCollectionSUsuarios : SUsuariosCollection) {
                SUsuariosCollectionSUsuarios.setIdCliente(null);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
            }
            em.remove(CClientes);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CClientes> findCClientesEntities() {
        return findCClientesEntities(true, -1, -1);
    }

    public List<CClientes> findCClientesEntities(int maxResults, int firstResult) {
        return findCClientesEntities(false, maxResults, firstResult);
    }

    private List<CClientes> findCClientesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CClientes.class));
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

    public CClientes findCClientes(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CClientes.class, id);
        } finally {
            em.close();
        }
    }

    public int getCClientesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CClientes> rt = cq.from(CClientes.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
