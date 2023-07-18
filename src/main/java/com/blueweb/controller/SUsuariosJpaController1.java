/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blueweb.controller;

import com.blueweb.controller.exceptions.IllegalOrphanException;
import com.blueweb.controller.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.blueweb.entity.CClientes;
import com.blueweb.entity.SPerfiles;
import com.blueweb.entity.SUsuarios;
import java.util.ArrayList;
import java.util.Collection;
import com.blueweb.entity.CCiudad;
import com.blueweb.entity.SPerfilesAccesos;
import com.blueweb.entity.CDistribuidor;
import com.blueweb.entity.CRegion;
import com.blueweb.entity.MArchivoLote;
import com.blueweb.entity.HActivacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Blueweb
 */
public class SUsuariosJpaController1 implements Serializable {

    public SUsuariosJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SUsuarios SUsuarios) {
        if (SUsuarios.getSUsuariosCollection() == null) {
            SUsuarios.setSUsuariosCollection(new ArrayList<SUsuarios>());
        }
        if (SUsuarios.getCCiudadCollection() == null) {
            SUsuarios.setCCiudadCollection(new ArrayList<CCiudad>());
        }
        if (SUsuarios.getCClientesCollection() == null) {
            SUsuarios.setCClientesCollection(new ArrayList<CClientes>());
        }
        if (SUsuarios.getSPerfilesCollection() == null) {
            SUsuarios.setSPerfilesCollection(new ArrayList<SPerfiles>());
        }
        if (SUsuarios.getSPerfilesAccesosCollection() == null) {
            SUsuarios.setSPerfilesAccesosCollection(new ArrayList<SPerfilesAccesos>());
        }
        if (SUsuarios.getCDistribuidorCollection() == null) {
            SUsuarios.setCDistribuidorCollection(new ArrayList<CDistribuidor>());
        }
        if (SUsuarios.getCRegionCollection() == null) {
            SUsuarios.setCRegionCollection(new ArrayList<CRegion>());
        }
        if (SUsuarios.getMArchivoLoteCollection() == null) {
            SUsuarios.setMArchivoLoteCollection(new ArrayList<MArchivoLote>());
        }
        if (SUsuarios.getHActivacionCollection() == null) {
            SUsuarios.setHActivacionCollection(new ArrayList<HActivacion>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CClientes idCliente = SUsuarios.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                SUsuarios.setIdCliente(idCliente);
            }
            SPerfiles idPerfil = SUsuarios.getIdPerfil();
            if (idPerfil != null) {
                idPerfil = em.getReference(idPerfil.getClass(), idPerfil.getIdPerfil());
                SUsuarios.setIdPerfil(idPerfil);
            }
            SUsuarios idUsuarioModifica = SUsuarios.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica = em.getReference(idUsuarioModifica.getClass(), idUsuarioModifica.getIdUsuario());
                SUsuarios.setIdUsuarioModifica(idUsuarioModifica);
            }
            Collection<SUsuarios> attachedSUsuariosCollection = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionSUsuariosToAttach : SUsuarios.getSUsuariosCollection()) {
                SUsuariosCollectionSUsuariosToAttach = em.getReference(SUsuariosCollectionSUsuariosToAttach.getClass(), SUsuariosCollectionSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollection.add(SUsuariosCollectionSUsuariosToAttach);
            }
            SUsuarios.setSUsuariosCollection(attachedSUsuariosCollection);
            Collection<CCiudad> attachedCCiudadCollection = new ArrayList<CCiudad>();
            for (CCiudad CCiudadCollectionCCiudadToAttach : SUsuarios.getCCiudadCollection()) {
                CCiudadCollectionCCiudadToAttach = em.getReference(CCiudadCollectionCCiudadToAttach.getClass(), CCiudadCollectionCCiudadToAttach.getIdCiudad());
                attachedCCiudadCollection.add(CCiudadCollectionCCiudadToAttach);
            }
            SUsuarios.setCCiudadCollection(attachedCCiudadCollection);
            Collection<CClientes> attachedCClientesCollection = new ArrayList<CClientes>();
            for (CClientes CClientesCollectionCClientesToAttach : SUsuarios.getCClientesCollection()) {
                CClientesCollectionCClientesToAttach = em.getReference(CClientesCollectionCClientesToAttach.getClass(), CClientesCollectionCClientesToAttach.getIdCliente());
                attachedCClientesCollection.add(CClientesCollectionCClientesToAttach);
            }
            SUsuarios.setCClientesCollection(attachedCClientesCollection);
            Collection<SPerfiles> attachedSPerfilesCollection = new ArrayList<SPerfiles>();
            for (SPerfiles SPerfilesCollectionSPerfilesToAttach : SUsuarios.getSPerfilesCollection()) {
                SPerfilesCollectionSPerfilesToAttach = em.getReference(SPerfilesCollectionSPerfilesToAttach.getClass(), SPerfilesCollectionSPerfilesToAttach.getIdPerfil());
                attachedSPerfilesCollection.add(SPerfilesCollectionSPerfilesToAttach);
            }
            SUsuarios.setSPerfilesCollection(attachedSPerfilesCollection);
            Collection<SPerfilesAccesos> attachedSPerfilesAccesosCollection = new ArrayList<SPerfilesAccesos>();
            for (SPerfilesAccesos SPerfilesAccesosCollectionSPerfilesAccesosToAttach : SUsuarios.getSPerfilesAccesosCollection()) {
                SPerfilesAccesosCollectionSPerfilesAccesosToAttach = em.getReference(SPerfilesAccesosCollectionSPerfilesAccesosToAttach.getClass(), SPerfilesAccesosCollectionSPerfilesAccesosToAttach.getSPerfilesAccesosPK());
                attachedSPerfilesAccesosCollection.add(SPerfilesAccesosCollectionSPerfilesAccesosToAttach);
            }
            SUsuarios.setSPerfilesAccesosCollection(attachedSPerfilesAccesosCollection);
            Collection<CDistribuidor> attachedCDistribuidorCollection = new ArrayList<CDistribuidor>();
            for (CDistribuidor CDistribuidorCollectionCDistribuidorToAttach : SUsuarios.getCDistribuidorCollection()) {
                CDistribuidorCollectionCDistribuidorToAttach = em.getReference(CDistribuidorCollectionCDistribuidorToAttach.getClass(), CDistribuidorCollectionCDistribuidorToAttach.getIdDistribuidor());
                attachedCDistribuidorCollection.add(CDistribuidorCollectionCDistribuidorToAttach);
            }
            SUsuarios.setCDistribuidorCollection(attachedCDistribuidorCollection);
            Collection<CRegion> attachedCRegionCollection = new ArrayList<CRegion>();
            for (CRegion CRegionCollectionCRegionToAttach : SUsuarios.getCRegionCollection()) {
                CRegionCollectionCRegionToAttach = em.getReference(CRegionCollectionCRegionToAttach.getClass(), CRegionCollectionCRegionToAttach.getIdRegion());
                attachedCRegionCollection.add(CRegionCollectionCRegionToAttach);
            }
            SUsuarios.setCRegionCollection(attachedCRegionCollection);
            Collection<MArchivoLote> attachedMArchivoLoteCollection = new ArrayList<MArchivoLote>();
            for (MArchivoLote MArchivoLoteCollectionMArchivoLoteToAttach : SUsuarios.getMArchivoLoteCollection()) {
                MArchivoLoteCollectionMArchivoLoteToAttach = em.getReference(MArchivoLoteCollectionMArchivoLoteToAttach.getClass(), MArchivoLoteCollectionMArchivoLoteToAttach.getIdArchivo());
                attachedMArchivoLoteCollection.add(MArchivoLoteCollectionMArchivoLoteToAttach);
            }
            SUsuarios.setMArchivoLoteCollection(attachedMArchivoLoteCollection);
            Collection<HActivacion> attachedHActivacionCollection = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionHActivacionToAttach : SUsuarios.getHActivacionCollection()) {
                HActivacionCollectionHActivacionToAttach = em.getReference(HActivacionCollectionHActivacionToAttach.getClass(), HActivacionCollectionHActivacionToAttach.getId());
                attachedHActivacionCollection.add(HActivacionCollectionHActivacionToAttach);
            }
            SUsuarios.setHActivacionCollection(attachedHActivacionCollection);
            em.persist(SUsuarios);
            if (idCliente != null) {
                idCliente.getSUsuariosCollection().add(SUsuarios);
                idCliente = em.merge(idCliente);
            }
            if (idPerfil != null) {
                idPerfil.getSUsuariosCollection().add(SUsuarios);
                idPerfil = em.merge(idPerfil);
            }
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getSUsuariosCollection().add(SUsuarios);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            for (SUsuarios SUsuariosCollectionSUsuarios : SUsuarios.getSUsuariosCollection()) {
                SUsuarios oldIdUsuarioModificaOfSUsuariosCollectionSUsuarios = SUsuariosCollectionSUsuarios.getIdUsuarioModifica();
                SUsuariosCollectionSUsuarios.setIdUsuarioModifica(SUsuarios);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
                if (oldIdUsuarioModificaOfSUsuariosCollectionSUsuarios != null) {
                    oldIdUsuarioModificaOfSUsuariosCollectionSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionSUsuarios);
                    oldIdUsuarioModificaOfSUsuariosCollectionSUsuarios = em.merge(oldIdUsuarioModificaOfSUsuariosCollectionSUsuarios);
                }
            }
            for (CCiudad CCiudadCollectionCCiudad : SUsuarios.getCCiudadCollection()) {
                SUsuarios oldIdUsuarioOfCCiudadCollectionCCiudad = CCiudadCollectionCCiudad.getIdUsuario();
                CCiudadCollectionCCiudad.setIdUsuario(SUsuarios);
                CCiudadCollectionCCiudad = em.merge(CCiudadCollectionCCiudad);
                if (oldIdUsuarioOfCCiudadCollectionCCiudad != null) {
                    oldIdUsuarioOfCCiudadCollectionCCiudad.getCCiudadCollection().remove(CCiudadCollectionCCiudad);
                    oldIdUsuarioOfCCiudadCollectionCCiudad = em.merge(oldIdUsuarioOfCCiudadCollectionCCiudad);
                }
            }
            for (CClientes CClientesCollectionCClientes : SUsuarios.getCClientesCollection()) {
                SUsuarios oldIdUsuarioModificaOfCClientesCollectionCClientes = CClientesCollectionCClientes.getIdUsuarioModifica();
                CClientesCollectionCClientes.setIdUsuarioModifica(SUsuarios);
                CClientesCollectionCClientes = em.merge(CClientesCollectionCClientes);
                if (oldIdUsuarioModificaOfCClientesCollectionCClientes != null) {
                    oldIdUsuarioModificaOfCClientesCollectionCClientes.getCClientesCollection().remove(CClientesCollectionCClientes);
                    oldIdUsuarioModificaOfCClientesCollectionCClientes = em.merge(oldIdUsuarioModificaOfCClientesCollectionCClientes);
                }
            }
            for (SPerfiles SPerfilesCollectionSPerfiles : SUsuarios.getSPerfilesCollection()) {
                SUsuarios oldIdUsuarioModificaOfSPerfilesCollectionSPerfiles = SPerfilesCollectionSPerfiles.getIdUsuarioModifica();
                SPerfilesCollectionSPerfiles.setIdUsuarioModifica(SUsuarios);
                SPerfilesCollectionSPerfiles = em.merge(SPerfilesCollectionSPerfiles);
                if (oldIdUsuarioModificaOfSPerfilesCollectionSPerfiles != null) {
                    oldIdUsuarioModificaOfSPerfilesCollectionSPerfiles.getSPerfilesCollection().remove(SPerfilesCollectionSPerfiles);
                    oldIdUsuarioModificaOfSPerfilesCollectionSPerfiles = em.merge(oldIdUsuarioModificaOfSPerfilesCollectionSPerfiles);
                }
            }
            for (SPerfilesAccesos SPerfilesAccesosCollectionSPerfilesAccesos : SUsuarios.getSPerfilesAccesosCollection()) {
                SUsuarios oldIdUsuarioModificaOfSPerfilesAccesosCollectionSPerfilesAccesos = SPerfilesAccesosCollectionSPerfilesAccesos.getIdUsuarioModifica();
                SPerfilesAccesosCollectionSPerfilesAccesos.setIdUsuarioModifica(SUsuarios);
                SPerfilesAccesosCollectionSPerfilesAccesos = em.merge(SPerfilesAccesosCollectionSPerfilesAccesos);
                if (oldIdUsuarioModificaOfSPerfilesAccesosCollectionSPerfilesAccesos != null) {
                    oldIdUsuarioModificaOfSPerfilesAccesosCollectionSPerfilesAccesos.getSPerfilesAccesosCollection().remove(SPerfilesAccesosCollectionSPerfilesAccesos);
                    oldIdUsuarioModificaOfSPerfilesAccesosCollectionSPerfilesAccesos = em.merge(oldIdUsuarioModificaOfSPerfilesAccesosCollectionSPerfilesAccesos);
                }
            }
            for (CDistribuidor CDistribuidorCollectionCDistribuidor : SUsuarios.getCDistribuidorCollection()) {
                SUsuarios oldIdUsuarioModificaOfCDistribuidorCollectionCDistribuidor = CDistribuidorCollectionCDistribuidor.getIdUsuarioModifica();
                CDistribuidorCollectionCDistribuidor.setIdUsuarioModifica(SUsuarios);
                CDistribuidorCollectionCDistribuidor = em.merge(CDistribuidorCollectionCDistribuidor);
                if (oldIdUsuarioModificaOfCDistribuidorCollectionCDistribuidor != null) {
                    oldIdUsuarioModificaOfCDistribuidorCollectionCDistribuidor.getCDistribuidorCollection().remove(CDistribuidorCollectionCDistribuidor);
                    oldIdUsuarioModificaOfCDistribuidorCollectionCDistribuidor = em.merge(oldIdUsuarioModificaOfCDistribuidorCollectionCDistribuidor);
                }
            }
            for (CRegion CRegionCollectionCRegion : SUsuarios.getCRegionCollection()) {
                SUsuarios oldIdUsuarioModificaOfCRegionCollectionCRegion = CRegionCollectionCRegion.getIdUsuarioModifica();
                CRegionCollectionCRegion.setIdUsuarioModifica(SUsuarios);
                CRegionCollectionCRegion = em.merge(CRegionCollectionCRegion);
                if (oldIdUsuarioModificaOfCRegionCollectionCRegion != null) {
                    oldIdUsuarioModificaOfCRegionCollectionCRegion.getCRegionCollection().remove(CRegionCollectionCRegion);
                    oldIdUsuarioModificaOfCRegionCollectionCRegion = em.merge(oldIdUsuarioModificaOfCRegionCollectionCRegion);
                }
            }
            for (MArchivoLote MArchivoLoteCollectionMArchivoLote : SUsuarios.getMArchivoLoteCollection()) {
                SUsuarios oldIdUsuarioOfMArchivoLoteCollectionMArchivoLote = MArchivoLoteCollectionMArchivoLote.getIdUsuario();
                MArchivoLoteCollectionMArchivoLote.setIdUsuario(SUsuarios);
                MArchivoLoteCollectionMArchivoLote = em.merge(MArchivoLoteCollectionMArchivoLote);
                if (oldIdUsuarioOfMArchivoLoteCollectionMArchivoLote != null) {
                    oldIdUsuarioOfMArchivoLoteCollectionMArchivoLote.getMArchivoLoteCollection().remove(MArchivoLoteCollectionMArchivoLote);
                    oldIdUsuarioOfMArchivoLoteCollectionMArchivoLote = em.merge(oldIdUsuarioOfMArchivoLoteCollectionMArchivoLote);
                }
            }
            for (HActivacion HActivacionCollectionHActivacion : SUsuarios.getHActivacionCollection()) {
                SUsuarios oldIdUsuarioOfHActivacionCollectionHActivacion = HActivacionCollectionHActivacion.getIdUsuario();
                HActivacionCollectionHActivacion.setIdUsuario(SUsuarios);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
                if (oldIdUsuarioOfHActivacionCollectionHActivacion != null) {
                    oldIdUsuarioOfHActivacionCollectionHActivacion.getHActivacionCollection().remove(HActivacionCollectionHActivacion);
                    oldIdUsuarioOfHActivacionCollectionHActivacion = em.merge(oldIdUsuarioOfHActivacionCollectionHActivacion);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SUsuarios SUsuarios) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SUsuarios persistentSUsuarios = em.find(SUsuarios.class, SUsuarios.getIdUsuario());
            CClientes idClienteOld = persistentSUsuarios.getIdCliente();
            CClientes idClienteNew = SUsuarios.getIdCliente();
            SPerfiles idPerfilOld = persistentSUsuarios.getIdPerfil();
            SPerfiles idPerfilNew = SUsuarios.getIdPerfil();
            SUsuarios idUsuarioModificaOld = persistentSUsuarios.getIdUsuarioModifica();
            SUsuarios idUsuarioModificaNew = SUsuarios.getIdUsuarioModifica();
            Collection<SUsuarios> SUsuariosCollectionOld = persistentSUsuarios.getSUsuariosCollection();
            Collection<SUsuarios> SUsuariosCollectionNew = SUsuarios.getSUsuariosCollection();
            Collection<CCiudad> CCiudadCollectionOld = persistentSUsuarios.getCCiudadCollection();
            Collection<CCiudad> CCiudadCollectionNew = SUsuarios.getCCiudadCollection();
            Collection<CClientes> CClientesCollectionOld = persistentSUsuarios.getCClientesCollection();
            Collection<CClientes> CClientesCollectionNew = SUsuarios.getCClientesCollection();
            Collection<SPerfiles> SPerfilesCollectionOld = persistentSUsuarios.getSPerfilesCollection();
            Collection<SPerfiles> SPerfilesCollectionNew = SUsuarios.getSPerfilesCollection();
            Collection<SPerfilesAccesos> SPerfilesAccesosCollectionOld = persistentSUsuarios.getSPerfilesAccesosCollection();
            Collection<SPerfilesAccesos> SPerfilesAccesosCollectionNew = SUsuarios.getSPerfilesAccesosCollection();
            Collection<CDistribuidor> CDistribuidorCollectionOld = persistentSUsuarios.getCDistribuidorCollection();
            Collection<CDistribuidor> CDistribuidorCollectionNew = SUsuarios.getCDistribuidorCollection();
            Collection<CRegion> CRegionCollectionOld = persistentSUsuarios.getCRegionCollection();
            Collection<CRegion> CRegionCollectionNew = SUsuarios.getCRegionCollection();
            Collection<MArchivoLote> MArchivoLoteCollectionOld = persistentSUsuarios.getMArchivoLoteCollection();
            Collection<MArchivoLote> MArchivoLoteCollectionNew = SUsuarios.getMArchivoLoteCollection();
            Collection<HActivacion> HActivacionCollectionOld = persistentSUsuarios.getHActivacionCollection();
            Collection<HActivacion> HActivacionCollectionNew = SUsuarios.getHActivacionCollection();
            List<String> illegalOrphanMessages = null;
            for (SPerfiles SPerfilesCollectionOldSPerfiles : SPerfilesCollectionOld) {
                if (!SPerfilesCollectionNew.contains(SPerfilesCollectionOldSPerfiles)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SPerfiles " + SPerfilesCollectionOldSPerfiles + " since its idUsuarioModifica field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                SUsuarios.setIdCliente(idClienteNew);
            }
            if (idPerfilNew != null) {
                idPerfilNew = em.getReference(idPerfilNew.getClass(), idPerfilNew.getIdPerfil());
                SUsuarios.setIdPerfil(idPerfilNew);
            }
            if (idUsuarioModificaNew != null) {
                idUsuarioModificaNew = em.getReference(idUsuarioModificaNew.getClass(), idUsuarioModificaNew.getIdUsuario());
                SUsuarios.setIdUsuarioModifica(idUsuarioModificaNew);
            }
            Collection<SUsuarios> attachedSUsuariosCollectionNew = new ArrayList<SUsuarios>();
            for (SUsuarios SUsuariosCollectionNewSUsuariosToAttach : SUsuariosCollectionNew) {
                SUsuariosCollectionNewSUsuariosToAttach = em.getReference(SUsuariosCollectionNewSUsuariosToAttach.getClass(), SUsuariosCollectionNewSUsuariosToAttach.getIdUsuario());
                attachedSUsuariosCollectionNew.add(SUsuariosCollectionNewSUsuariosToAttach);
            }
            SUsuariosCollectionNew = attachedSUsuariosCollectionNew;
            SUsuarios.setSUsuariosCollection(SUsuariosCollectionNew);
            Collection<CCiudad> attachedCCiudadCollectionNew = new ArrayList<CCiudad>();
            for (CCiudad CCiudadCollectionNewCCiudadToAttach : CCiudadCollectionNew) {
                CCiudadCollectionNewCCiudadToAttach = em.getReference(CCiudadCollectionNewCCiudadToAttach.getClass(), CCiudadCollectionNewCCiudadToAttach.getIdCiudad());
                attachedCCiudadCollectionNew.add(CCiudadCollectionNewCCiudadToAttach);
            }
            CCiudadCollectionNew = attachedCCiudadCollectionNew;
            SUsuarios.setCCiudadCollection(CCiudadCollectionNew);
            Collection<CClientes> attachedCClientesCollectionNew = new ArrayList<CClientes>();
            for (CClientes CClientesCollectionNewCClientesToAttach : CClientesCollectionNew) {
                CClientesCollectionNewCClientesToAttach = em.getReference(CClientesCollectionNewCClientesToAttach.getClass(), CClientesCollectionNewCClientesToAttach.getIdCliente());
                attachedCClientesCollectionNew.add(CClientesCollectionNewCClientesToAttach);
            }
            CClientesCollectionNew = attachedCClientesCollectionNew;
            SUsuarios.setCClientesCollection(CClientesCollectionNew);
            Collection<SPerfiles> attachedSPerfilesCollectionNew = new ArrayList<SPerfiles>();
            for (SPerfiles SPerfilesCollectionNewSPerfilesToAttach : SPerfilesCollectionNew) {
                SPerfilesCollectionNewSPerfilesToAttach = em.getReference(SPerfilesCollectionNewSPerfilesToAttach.getClass(), SPerfilesCollectionNewSPerfilesToAttach.getIdPerfil());
                attachedSPerfilesCollectionNew.add(SPerfilesCollectionNewSPerfilesToAttach);
            }
            SPerfilesCollectionNew = attachedSPerfilesCollectionNew;
            SUsuarios.setSPerfilesCollection(SPerfilesCollectionNew);
            Collection<SPerfilesAccesos> attachedSPerfilesAccesosCollectionNew = new ArrayList<SPerfilesAccesos>();
            for (SPerfilesAccesos SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach : SPerfilesAccesosCollectionNew) {
                SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach = em.getReference(SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach.getClass(), SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach.getSPerfilesAccesosPK());
                attachedSPerfilesAccesosCollectionNew.add(SPerfilesAccesosCollectionNewSPerfilesAccesosToAttach);
            }
            SPerfilesAccesosCollectionNew = attachedSPerfilesAccesosCollectionNew;
            SUsuarios.setSPerfilesAccesosCollection(SPerfilesAccesosCollectionNew);
            Collection<CDistribuidor> attachedCDistribuidorCollectionNew = new ArrayList<CDistribuidor>();
            for (CDistribuidor CDistribuidorCollectionNewCDistribuidorToAttach : CDistribuidorCollectionNew) {
                CDistribuidorCollectionNewCDistribuidorToAttach = em.getReference(CDistribuidorCollectionNewCDistribuidorToAttach.getClass(), CDistribuidorCollectionNewCDistribuidorToAttach.getIdDistribuidor());
                attachedCDistribuidorCollectionNew.add(CDistribuidorCollectionNewCDistribuidorToAttach);
            }
            CDistribuidorCollectionNew = attachedCDistribuidorCollectionNew;
            SUsuarios.setCDistribuidorCollection(CDistribuidorCollectionNew);
            Collection<CRegion> attachedCRegionCollectionNew = new ArrayList<CRegion>();
            for (CRegion CRegionCollectionNewCRegionToAttach : CRegionCollectionNew) {
                CRegionCollectionNewCRegionToAttach = em.getReference(CRegionCollectionNewCRegionToAttach.getClass(), CRegionCollectionNewCRegionToAttach.getIdRegion());
                attachedCRegionCollectionNew.add(CRegionCollectionNewCRegionToAttach);
            }
            CRegionCollectionNew = attachedCRegionCollectionNew;
            SUsuarios.setCRegionCollection(CRegionCollectionNew);
            Collection<MArchivoLote> attachedMArchivoLoteCollectionNew = new ArrayList<MArchivoLote>();
            for (MArchivoLote MArchivoLoteCollectionNewMArchivoLoteToAttach : MArchivoLoteCollectionNew) {
                MArchivoLoteCollectionNewMArchivoLoteToAttach = em.getReference(MArchivoLoteCollectionNewMArchivoLoteToAttach.getClass(), MArchivoLoteCollectionNewMArchivoLoteToAttach.getIdArchivo());
                attachedMArchivoLoteCollectionNew.add(MArchivoLoteCollectionNewMArchivoLoteToAttach);
            }
            MArchivoLoteCollectionNew = attachedMArchivoLoteCollectionNew;
            SUsuarios.setMArchivoLoteCollection(MArchivoLoteCollectionNew);
            Collection<HActivacion> attachedHActivacionCollectionNew = new ArrayList<HActivacion>();
            for (HActivacion HActivacionCollectionNewHActivacionToAttach : HActivacionCollectionNew) {
                HActivacionCollectionNewHActivacionToAttach = em.getReference(HActivacionCollectionNewHActivacionToAttach.getClass(), HActivacionCollectionNewHActivacionToAttach.getId());
                attachedHActivacionCollectionNew.add(HActivacionCollectionNewHActivacionToAttach);
            }
            HActivacionCollectionNew = attachedHActivacionCollectionNew;
            SUsuarios.setHActivacionCollection(HActivacionCollectionNew);
            SUsuarios = em.merge(SUsuarios);
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getSUsuariosCollection().remove(SUsuarios);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getSUsuariosCollection().add(SUsuarios);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idPerfilOld != null && !idPerfilOld.equals(idPerfilNew)) {
                idPerfilOld.getSUsuariosCollection().remove(SUsuarios);
                idPerfilOld = em.merge(idPerfilOld);
            }
            if (idPerfilNew != null && !idPerfilNew.equals(idPerfilOld)) {
                idPerfilNew.getSUsuariosCollection().add(SUsuarios);
                idPerfilNew = em.merge(idPerfilNew);
            }
            if (idUsuarioModificaOld != null && !idUsuarioModificaOld.equals(idUsuarioModificaNew)) {
                idUsuarioModificaOld.getSUsuariosCollection().remove(SUsuarios);
                idUsuarioModificaOld = em.merge(idUsuarioModificaOld);
            }
            if (idUsuarioModificaNew != null && !idUsuarioModificaNew.equals(idUsuarioModificaOld)) {
                idUsuarioModificaNew.getSUsuariosCollection().add(SUsuarios);
                idUsuarioModificaNew = em.merge(idUsuarioModificaNew);
            }
            for (SUsuarios SUsuariosCollectionOldSUsuarios : SUsuariosCollectionOld) {
                if (!SUsuariosCollectionNew.contains(SUsuariosCollectionOldSUsuarios)) {
                    SUsuariosCollectionOldSUsuarios.setIdUsuarioModifica(null);
                    SUsuariosCollectionOldSUsuarios = em.merge(SUsuariosCollectionOldSUsuarios);
                }
            }
            for (SUsuarios SUsuariosCollectionNewSUsuarios : SUsuariosCollectionNew) {
                if (!SUsuariosCollectionOld.contains(SUsuariosCollectionNewSUsuarios)) {
                    SUsuarios oldIdUsuarioModificaOfSUsuariosCollectionNewSUsuarios = SUsuariosCollectionNewSUsuarios.getIdUsuarioModifica();
                    SUsuariosCollectionNewSUsuarios.setIdUsuarioModifica(SUsuarios);
                    SUsuariosCollectionNewSUsuarios = em.merge(SUsuariosCollectionNewSUsuarios);
                    if (oldIdUsuarioModificaOfSUsuariosCollectionNewSUsuarios != null && !oldIdUsuarioModificaOfSUsuariosCollectionNewSUsuarios.equals(SUsuarios)) {
                        oldIdUsuarioModificaOfSUsuariosCollectionNewSUsuarios.getSUsuariosCollection().remove(SUsuariosCollectionNewSUsuarios);
                        oldIdUsuarioModificaOfSUsuariosCollectionNewSUsuarios = em.merge(oldIdUsuarioModificaOfSUsuariosCollectionNewSUsuarios);
                    }
                }
            }
            for (CCiudad CCiudadCollectionOldCCiudad : CCiudadCollectionOld) {
                if (!CCiudadCollectionNew.contains(CCiudadCollectionOldCCiudad)) {
                    CCiudadCollectionOldCCiudad.setIdUsuario(null);
                    CCiudadCollectionOldCCiudad = em.merge(CCiudadCollectionOldCCiudad);
                }
            }
            for (CCiudad CCiudadCollectionNewCCiudad : CCiudadCollectionNew) {
                if (!CCiudadCollectionOld.contains(CCiudadCollectionNewCCiudad)) {
                    SUsuarios oldIdUsuarioOfCCiudadCollectionNewCCiudad = CCiudadCollectionNewCCiudad.getIdUsuario();
                    CCiudadCollectionNewCCiudad.setIdUsuario(SUsuarios);
                    CCiudadCollectionNewCCiudad = em.merge(CCiudadCollectionNewCCiudad);
                    if (oldIdUsuarioOfCCiudadCollectionNewCCiudad != null && !oldIdUsuarioOfCCiudadCollectionNewCCiudad.equals(SUsuarios)) {
                        oldIdUsuarioOfCCiudadCollectionNewCCiudad.getCCiudadCollection().remove(CCiudadCollectionNewCCiudad);
                        oldIdUsuarioOfCCiudadCollectionNewCCiudad = em.merge(oldIdUsuarioOfCCiudadCollectionNewCCiudad);
                    }
                }
            }
            for (CClientes CClientesCollectionOldCClientes : CClientesCollectionOld) {
                if (!CClientesCollectionNew.contains(CClientesCollectionOldCClientes)) {
                    CClientesCollectionOldCClientes.setIdUsuarioModifica(null);
                    CClientesCollectionOldCClientes = em.merge(CClientesCollectionOldCClientes);
                }
            }
            for (CClientes CClientesCollectionNewCClientes : CClientesCollectionNew) {
                if (!CClientesCollectionOld.contains(CClientesCollectionNewCClientes)) {
                    SUsuarios oldIdUsuarioModificaOfCClientesCollectionNewCClientes = CClientesCollectionNewCClientes.getIdUsuarioModifica();
                    CClientesCollectionNewCClientes.setIdUsuarioModifica(SUsuarios);
                    CClientesCollectionNewCClientes = em.merge(CClientesCollectionNewCClientes);
                    if (oldIdUsuarioModificaOfCClientesCollectionNewCClientes != null && !oldIdUsuarioModificaOfCClientesCollectionNewCClientes.equals(SUsuarios)) {
                        oldIdUsuarioModificaOfCClientesCollectionNewCClientes.getCClientesCollection().remove(CClientesCollectionNewCClientes);
                        oldIdUsuarioModificaOfCClientesCollectionNewCClientes = em.merge(oldIdUsuarioModificaOfCClientesCollectionNewCClientes);
                    }
                }
            }
            for (SPerfiles SPerfilesCollectionNewSPerfiles : SPerfilesCollectionNew) {
                if (!SPerfilesCollectionOld.contains(SPerfilesCollectionNewSPerfiles)) {
                    SUsuarios oldIdUsuarioModificaOfSPerfilesCollectionNewSPerfiles = SPerfilesCollectionNewSPerfiles.getIdUsuarioModifica();
                    SPerfilesCollectionNewSPerfiles.setIdUsuarioModifica(SUsuarios);
                    SPerfilesCollectionNewSPerfiles = em.merge(SPerfilesCollectionNewSPerfiles);
                    if (oldIdUsuarioModificaOfSPerfilesCollectionNewSPerfiles != null && !oldIdUsuarioModificaOfSPerfilesCollectionNewSPerfiles.equals(SUsuarios)) {
                        oldIdUsuarioModificaOfSPerfilesCollectionNewSPerfiles.getSPerfilesCollection().remove(SPerfilesCollectionNewSPerfiles);
                        oldIdUsuarioModificaOfSPerfilesCollectionNewSPerfiles = em.merge(oldIdUsuarioModificaOfSPerfilesCollectionNewSPerfiles);
                    }
                }
            }
            for (SPerfilesAccesos SPerfilesAccesosCollectionOldSPerfilesAccesos : SPerfilesAccesosCollectionOld) {
                if (!SPerfilesAccesosCollectionNew.contains(SPerfilesAccesosCollectionOldSPerfilesAccesos)) {
                    SPerfilesAccesosCollectionOldSPerfilesAccesos.setIdUsuarioModifica(null);
                    SPerfilesAccesosCollectionOldSPerfilesAccesos = em.merge(SPerfilesAccesosCollectionOldSPerfilesAccesos);
                }
            }
            for (SPerfilesAccesos SPerfilesAccesosCollectionNewSPerfilesAccesos : SPerfilesAccesosCollectionNew) {
                if (!SPerfilesAccesosCollectionOld.contains(SPerfilesAccesosCollectionNewSPerfilesAccesos)) {
                    SUsuarios oldIdUsuarioModificaOfSPerfilesAccesosCollectionNewSPerfilesAccesos = SPerfilesAccesosCollectionNewSPerfilesAccesos.getIdUsuarioModifica();
                    SPerfilesAccesosCollectionNewSPerfilesAccesos.setIdUsuarioModifica(SUsuarios);
                    SPerfilesAccesosCollectionNewSPerfilesAccesos = em.merge(SPerfilesAccesosCollectionNewSPerfilesAccesos);
                    if (oldIdUsuarioModificaOfSPerfilesAccesosCollectionNewSPerfilesAccesos != null && !oldIdUsuarioModificaOfSPerfilesAccesosCollectionNewSPerfilesAccesos.equals(SUsuarios)) {
                        oldIdUsuarioModificaOfSPerfilesAccesosCollectionNewSPerfilesAccesos.getSPerfilesAccesosCollection().remove(SPerfilesAccesosCollectionNewSPerfilesAccesos);
                        oldIdUsuarioModificaOfSPerfilesAccesosCollectionNewSPerfilesAccesos = em.merge(oldIdUsuarioModificaOfSPerfilesAccesosCollectionNewSPerfilesAccesos);
                    }
                }
            }
            for (CDistribuidor CDistribuidorCollectionOldCDistribuidor : CDistribuidorCollectionOld) {
                if (!CDistribuidorCollectionNew.contains(CDistribuidorCollectionOldCDistribuidor)) {
                    CDistribuidorCollectionOldCDistribuidor.setIdUsuarioModifica(null);
                    CDistribuidorCollectionOldCDistribuidor = em.merge(CDistribuidorCollectionOldCDistribuidor);
                }
            }
            for (CDistribuidor CDistribuidorCollectionNewCDistribuidor : CDistribuidorCollectionNew) {
                if (!CDistribuidorCollectionOld.contains(CDistribuidorCollectionNewCDistribuidor)) {
                    SUsuarios oldIdUsuarioModificaOfCDistribuidorCollectionNewCDistribuidor = CDistribuidorCollectionNewCDistribuidor.getIdUsuarioModifica();
                    CDistribuidorCollectionNewCDistribuidor.setIdUsuarioModifica(SUsuarios);
                    CDistribuidorCollectionNewCDistribuidor = em.merge(CDistribuidorCollectionNewCDistribuidor);
                    if (oldIdUsuarioModificaOfCDistribuidorCollectionNewCDistribuidor != null && !oldIdUsuarioModificaOfCDistribuidorCollectionNewCDistribuidor.equals(SUsuarios)) {
                        oldIdUsuarioModificaOfCDistribuidorCollectionNewCDistribuidor.getCDistribuidorCollection().remove(CDistribuidorCollectionNewCDistribuidor);
                        oldIdUsuarioModificaOfCDistribuidorCollectionNewCDistribuidor = em.merge(oldIdUsuarioModificaOfCDistribuidorCollectionNewCDistribuidor);
                    }
                }
            }
            for (CRegion CRegionCollectionOldCRegion : CRegionCollectionOld) {
                if (!CRegionCollectionNew.contains(CRegionCollectionOldCRegion)) {
                    CRegionCollectionOldCRegion.setIdUsuarioModifica(null);
                    CRegionCollectionOldCRegion = em.merge(CRegionCollectionOldCRegion);
                }
            }
            for (CRegion CRegionCollectionNewCRegion : CRegionCollectionNew) {
                if (!CRegionCollectionOld.contains(CRegionCollectionNewCRegion)) {
                    SUsuarios oldIdUsuarioModificaOfCRegionCollectionNewCRegion = CRegionCollectionNewCRegion.getIdUsuarioModifica();
                    CRegionCollectionNewCRegion.setIdUsuarioModifica(SUsuarios);
                    CRegionCollectionNewCRegion = em.merge(CRegionCollectionNewCRegion);
                    if (oldIdUsuarioModificaOfCRegionCollectionNewCRegion != null && !oldIdUsuarioModificaOfCRegionCollectionNewCRegion.equals(SUsuarios)) {
                        oldIdUsuarioModificaOfCRegionCollectionNewCRegion.getCRegionCollection().remove(CRegionCollectionNewCRegion);
                        oldIdUsuarioModificaOfCRegionCollectionNewCRegion = em.merge(oldIdUsuarioModificaOfCRegionCollectionNewCRegion);
                    }
                }
            }
            for (MArchivoLote MArchivoLoteCollectionOldMArchivoLote : MArchivoLoteCollectionOld) {
                if (!MArchivoLoteCollectionNew.contains(MArchivoLoteCollectionOldMArchivoLote)) {
                    MArchivoLoteCollectionOldMArchivoLote.setIdUsuario(null);
                    MArchivoLoteCollectionOldMArchivoLote = em.merge(MArchivoLoteCollectionOldMArchivoLote);
                }
            }
            for (MArchivoLote MArchivoLoteCollectionNewMArchivoLote : MArchivoLoteCollectionNew) {
                if (!MArchivoLoteCollectionOld.contains(MArchivoLoteCollectionNewMArchivoLote)) {
                    SUsuarios oldIdUsuarioOfMArchivoLoteCollectionNewMArchivoLote = MArchivoLoteCollectionNewMArchivoLote.getIdUsuario();
                    MArchivoLoteCollectionNewMArchivoLote.setIdUsuario(SUsuarios);
                    MArchivoLoteCollectionNewMArchivoLote = em.merge(MArchivoLoteCollectionNewMArchivoLote);
                    if (oldIdUsuarioOfMArchivoLoteCollectionNewMArchivoLote != null && !oldIdUsuarioOfMArchivoLoteCollectionNewMArchivoLote.equals(SUsuarios)) {
                        oldIdUsuarioOfMArchivoLoteCollectionNewMArchivoLote.getMArchivoLoteCollection().remove(MArchivoLoteCollectionNewMArchivoLote);
                        oldIdUsuarioOfMArchivoLoteCollectionNewMArchivoLote = em.merge(oldIdUsuarioOfMArchivoLoteCollectionNewMArchivoLote);
                    }
                }
            }
            for (HActivacion HActivacionCollectionOldHActivacion : HActivacionCollectionOld) {
                if (!HActivacionCollectionNew.contains(HActivacionCollectionOldHActivacion)) {
                    HActivacionCollectionOldHActivacion.setIdUsuario(null);
                    HActivacionCollectionOldHActivacion = em.merge(HActivacionCollectionOldHActivacion);
                }
            }
            for (HActivacion HActivacionCollectionNewHActivacion : HActivacionCollectionNew) {
                if (!HActivacionCollectionOld.contains(HActivacionCollectionNewHActivacion)) {
                    SUsuarios oldIdUsuarioOfHActivacionCollectionNewHActivacion = HActivacionCollectionNewHActivacion.getIdUsuario();
                    HActivacionCollectionNewHActivacion.setIdUsuario(SUsuarios);
                    HActivacionCollectionNewHActivacion = em.merge(HActivacionCollectionNewHActivacion);
                    if (oldIdUsuarioOfHActivacionCollectionNewHActivacion != null && !oldIdUsuarioOfHActivacionCollectionNewHActivacion.equals(SUsuarios)) {
                        oldIdUsuarioOfHActivacionCollectionNewHActivacion.getHActivacionCollection().remove(HActivacionCollectionNewHActivacion);
                        oldIdUsuarioOfHActivacionCollectionNewHActivacion = em.merge(oldIdUsuarioOfHActivacionCollectionNewHActivacion);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = SUsuarios.getIdUsuario();
                if (findSUsuarios(id) == null) {
                    throw new NonexistentEntityException("The sUsuarios with id " + id + " no longer exists.");
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
            SUsuarios SUsuarios;
            try {
                SUsuarios = em.getReference(SUsuarios.class, id);
                SUsuarios.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The SUsuarios with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<SPerfiles> SPerfilesCollectionOrphanCheck = SUsuarios.getSPerfilesCollection();
            for (SPerfiles SPerfilesCollectionOrphanCheckSPerfiles : SPerfilesCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This SUsuarios (" + SUsuarios + ") cannot be destroyed since the SPerfiles " + SPerfilesCollectionOrphanCheckSPerfiles + " in its SPerfilesCollection field has a non-nullable idUsuarioModifica field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            CClientes idCliente = SUsuarios.getIdCliente();
            if (idCliente != null) {
                idCliente.getSUsuariosCollection().remove(SUsuarios);
                idCliente = em.merge(idCliente);
            }
            SPerfiles idPerfil = SUsuarios.getIdPerfil();
            if (idPerfil != null) {
                idPerfil.getSUsuariosCollection().remove(SUsuarios);
                idPerfil = em.merge(idPerfil);
            }
            SUsuarios idUsuarioModifica = SUsuarios.getIdUsuarioModifica();
            if (idUsuarioModifica != null) {
                idUsuarioModifica.getSUsuariosCollection().remove(SUsuarios);
                idUsuarioModifica = em.merge(idUsuarioModifica);
            }
            Collection<SUsuarios> SUsuariosCollection = SUsuarios.getSUsuariosCollection();
            for (SUsuarios SUsuariosCollectionSUsuarios : SUsuariosCollection) {
                SUsuariosCollectionSUsuarios.setIdUsuarioModifica(null);
                SUsuariosCollectionSUsuarios = em.merge(SUsuariosCollectionSUsuarios);
            }
            Collection<CCiudad> CCiudadCollection = SUsuarios.getCCiudadCollection();
            for (CCiudad CCiudadCollectionCCiudad : CCiudadCollection) {
                CCiudadCollectionCCiudad.setIdUsuario(null);
                CCiudadCollectionCCiudad = em.merge(CCiudadCollectionCCiudad);
            }
            Collection<CClientes> CClientesCollection = SUsuarios.getCClientesCollection();
            for (CClientes CClientesCollectionCClientes : CClientesCollection) {
                CClientesCollectionCClientes.setIdUsuarioModifica(null);
                CClientesCollectionCClientes = em.merge(CClientesCollectionCClientes);
            }
            Collection<SPerfilesAccesos> SPerfilesAccesosCollection = SUsuarios.getSPerfilesAccesosCollection();
            for (SPerfilesAccesos SPerfilesAccesosCollectionSPerfilesAccesos : SPerfilesAccesosCollection) {
                SPerfilesAccesosCollectionSPerfilesAccesos.setIdUsuarioModifica(null);
                SPerfilesAccesosCollectionSPerfilesAccesos = em.merge(SPerfilesAccesosCollectionSPerfilesAccesos);
            }
            Collection<CDistribuidor> CDistribuidorCollection = SUsuarios.getCDistribuidorCollection();
            for (CDistribuidor CDistribuidorCollectionCDistribuidor : CDistribuidorCollection) {
                CDistribuidorCollectionCDistribuidor.setIdUsuarioModifica(null);
                CDistribuidorCollectionCDistribuidor = em.merge(CDistribuidorCollectionCDistribuidor);
            }
            Collection<CRegion> CRegionCollection = SUsuarios.getCRegionCollection();
            for (CRegion CRegionCollectionCRegion : CRegionCollection) {
                CRegionCollectionCRegion.setIdUsuarioModifica(null);
                CRegionCollectionCRegion = em.merge(CRegionCollectionCRegion);
            }
            Collection<MArchivoLote> MArchivoLoteCollection = SUsuarios.getMArchivoLoteCollection();
            for (MArchivoLote MArchivoLoteCollectionMArchivoLote : MArchivoLoteCollection) {
                MArchivoLoteCollectionMArchivoLote.setIdUsuario(null);
                MArchivoLoteCollectionMArchivoLote = em.merge(MArchivoLoteCollectionMArchivoLote);
            }
            Collection<HActivacion> HActivacionCollection = SUsuarios.getHActivacionCollection();
            for (HActivacion HActivacionCollectionHActivacion : HActivacionCollection) {
                HActivacionCollectionHActivacion.setIdUsuario(null);
                HActivacionCollectionHActivacion = em.merge(HActivacionCollectionHActivacion);
            }
            em.remove(SUsuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SUsuarios> findSUsuariosEntities() {
        return findSUsuariosEntities(true, -1, -1);
    }

    public List<SUsuarios> findSUsuariosEntities(int maxResults, int firstResult) {
        return findSUsuariosEntities(false, maxResults, firstResult);
    }

    private List<SUsuarios> findSUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SUsuarios.class));
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

    public SUsuarios findSUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SUsuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getSUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SUsuarios> rt = cq.from(SUsuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
