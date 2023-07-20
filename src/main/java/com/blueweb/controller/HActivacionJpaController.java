package com.blueweb.controller;

import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.controller.exceptions.PreexistingEntityException;
import com.blueweb.data.utils.LocalEntityManagerFactory;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.blueweb.entity.CAutomata;
import com.blueweb.entity.CCiudad;
import com.blueweb.entity.CClientes;
import com.blueweb.entity.CDistribuidor;
import com.blueweb.entity.CRegion;
import com.blueweb.entity.CTipoTelefono;
import com.blueweb.entity.HActivacion;
import com.blueweb.entity.MArchivoLote;
import com.blueweb.entity.SUsuarios;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;

/**
 *
 * @author Blueweb
 */
public class HActivacionJpaController implements Serializable {

    public HActivacionJpaController() {
        this.emf = LocalEntityManagerFactory.getEntityManagerFactory();
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HActivacion HActivacion) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CAutomata idAutomata = HActivacion.getIdAutomata();
            if (idAutomata != null) {
                idAutomata = em.getReference(idAutomata.getClass(), idAutomata.getIdAutomata());
                HActivacion.setIdAutomata(idAutomata);
            }
            CCiudad idCiudad = HActivacion.getIdCiudad();
            if (idCiudad != null) {
                idCiudad = em.getReference(idCiudad.getClass(), idCiudad.getIdCiudad());
                HActivacion.setIdCiudad(idCiudad);
            }
            CClientes idCliente = HActivacion.getIdCliente();
            if (idCliente != null) {
                idCliente = em.getReference(idCliente.getClass(), idCliente.getIdCliente());
                HActivacion.setIdCliente(idCliente);
            }
            CDistribuidor idDistribuidor = HActivacion.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor = em.getReference(idDistribuidor.getClass(), idDistribuidor.getIdDistribuidor());
                HActivacion.setIdDistribuidor(idDistribuidor);
            }
            CRegion idRegion = HActivacion.getIdRegion();
            if (idRegion != null) {
                idRegion = em.getReference(idRegion.getClass(), idRegion.getIdRegion());
                HActivacion.setIdRegion(idRegion);
            }
            CTipoTelefono idTipoTelefonia = HActivacion.getIdTipoTelefonia();
            if (idTipoTelefonia != null) {
                idTipoTelefonia = em.getReference(idTipoTelefonia.getClass(), idTipoTelefonia.getId());
                HActivacion.setIdTipoTelefonia(idTipoTelefonia);
            }
            MArchivoLote idArchivo = HActivacion.getIdArchivo();
            if (idArchivo != null) {
                idArchivo = em.getReference(idArchivo.getClass(), idArchivo.getIdArchivo());
                HActivacion.setIdArchivo(idArchivo);
            }
            SUsuarios idUsuario = HActivacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario = em.getReference(idUsuario.getClass(), idUsuario.getIdUsuario());
                HActivacion.setIdUsuario(idUsuario);
            }
            em.persist(HActivacion);
            if (idAutomata != null) {
                idAutomata.getHActivacionCollection().add(HActivacion);
                idAutomata = em.merge(idAutomata);
            }
            if (idCiudad != null) {
                idCiudad.getHActivacionCollection().add(HActivacion);
                idCiudad = em.merge(idCiudad);
            }
            if (idCliente != null) {
                idCliente.getHActivacionCollection().add(HActivacion);
                idCliente = em.merge(idCliente);
            }
            if (idDistribuidor != null) {
                idDistribuidor.getHActivacionCollection().add(HActivacion);
                idDistribuidor = em.merge(idDistribuidor);
            }
            if (idRegion != null) {
                idRegion.getHActivacionCollection().add(HActivacion);
                idRegion = em.merge(idRegion);
            }
            if (idTipoTelefonia != null) {
                idTipoTelefonia.getHActivacionCollection().add(HActivacion);
                idTipoTelefonia = em.merge(idTipoTelefonia);
            }
            if (idArchivo != null) {
                idArchivo.getHActivacionCollection().add(HActivacion);
                idArchivo = em.merge(idArchivo);
            }
            if (idUsuario != null) {
                idUsuario.getHActivacionCollection().add(HActivacion);
                idUsuario = em.merge(idUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHActivacion(HActivacion.getId()) != null) {
                throw new PreexistingEntityException("HActivacion " + HActivacion + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HActivacion HActivacion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HActivacion persistentHActivacion = em.find(HActivacion.class, HActivacion.getId());
            CAutomata idAutomataOld = persistentHActivacion.getIdAutomata();
            CAutomata idAutomataNew = HActivacion.getIdAutomata();
            CCiudad idCiudadOld = persistentHActivacion.getIdCiudad();
            CCiudad idCiudadNew = HActivacion.getIdCiudad();
            CClientes idClienteOld = persistentHActivacion.getIdCliente();
            CClientes idClienteNew = HActivacion.getIdCliente();
            CDistribuidor idDistribuidorOld = persistentHActivacion.getIdDistribuidor();
            CDistribuidor idDistribuidorNew = HActivacion.getIdDistribuidor();
            CRegion idRegionOld = persistentHActivacion.getIdRegion();
            CRegion idRegionNew = HActivacion.getIdRegion();
            CTipoTelefono idTipoTelefoniaOld = persistentHActivacion.getIdTipoTelefonia();
            CTipoTelefono idTipoTelefoniaNew = HActivacion.getIdTipoTelefonia();
            MArchivoLote idArchivoOld = persistentHActivacion.getIdArchivo();
            MArchivoLote idArchivoNew = HActivacion.getIdArchivo();
            SUsuarios idUsuarioOld = persistentHActivacion.getIdUsuario();
            SUsuarios idUsuarioNew = HActivacion.getIdUsuario();
            if (idAutomataNew != null) {
                idAutomataNew = em.getReference(idAutomataNew.getClass(), idAutomataNew.getIdAutomata());
                HActivacion.setIdAutomata(idAutomataNew);
            }
            if (idCiudadNew != null) {
                idCiudadNew = em.getReference(idCiudadNew.getClass(), idCiudadNew.getIdCiudad());
                HActivacion.setIdCiudad(idCiudadNew);
            }
            if (idClienteNew != null) {
                idClienteNew = em.getReference(idClienteNew.getClass(), idClienteNew.getIdCliente());
                HActivacion.setIdCliente(idClienteNew);
            }
            if (idDistribuidorNew != null) {
                idDistribuidorNew = em.getReference(idDistribuidorNew.getClass(), idDistribuidorNew.getIdDistribuidor());
                HActivacion.setIdDistribuidor(idDistribuidorNew);
            }
            if (idRegionNew != null) {
                idRegionNew = em.getReference(idRegionNew.getClass(), idRegionNew.getIdRegion());
                HActivacion.setIdRegion(idRegionNew);
            }
            if (idTipoTelefoniaNew != null) {
                idTipoTelefoniaNew = em.getReference(idTipoTelefoniaNew.getClass(), idTipoTelefoniaNew.getId());
                HActivacion.setIdTipoTelefonia(idTipoTelefoniaNew);
            }
            if (idArchivoNew != null) {
                idArchivoNew = em.getReference(idArchivoNew.getClass(), idArchivoNew.getIdArchivo());
                HActivacion.setIdArchivo(idArchivoNew);
            }
            if (idUsuarioNew != null) {
                idUsuarioNew = em.getReference(idUsuarioNew.getClass(), idUsuarioNew.getIdUsuario());
                HActivacion.setIdUsuario(idUsuarioNew);
            }
            HActivacion = em.merge(HActivacion);
            if (idAutomataOld != null && !idAutomataOld.equals(idAutomataNew)) {
                idAutomataOld.getHActivacionCollection().remove(HActivacion);
                idAutomataOld = em.merge(idAutomataOld);
            }
            if (idAutomataNew != null && !idAutomataNew.equals(idAutomataOld)) {
                idAutomataNew.getHActivacionCollection().add(HActivacion);
                idAutomataNew = em.merge(idAutomataNew);
            }
            if (idCiudadOld != null && !idCiudadOld.equals(idCiudadNew)) {
                idCiudadOld.getHActivacionCollection().remove(HActivacion);
                idCiudadOld = em.merge(idCiudadOld);
            }
            if (idCiudadNew != null && !idCiudadNew.equals(idCiudadOld)) {
                idCiudadNew.getHActivacionCollection().add(HActivacion);
                idCiudadNew = em.merge(idCiudadNew);
            }
            if (idClienteOld != null && !idClienteOld.equals(idClienteNew)) {
                idClienteOld.getHActivacionCollection().remove(HActivacion);
                idClienteOld = em.merge(idClienteOld);
            }
            if (idClienteNew != null && !idClienteNew.equals(idClienteOld)) {
                idClienteNew.getHActivacionCollection().add(HActivacion);
                idClienteNew = em.merge(idClienteNew);
            }
            if (idDistribuidorOld != null && !idDistribuidorOld.equals(idDistribuidorNew)) {
                idDistribuidorOld.getHActivacionCollection().remove(HActivacion);
                idDistribuidorOld = em.merge(idDistribuidorOld);
            }
            if (idDistribuidorNew != null && !idDistribuidorNew.equals(idDistribuidorOld)) {
                idDistribuidorNew.getHActivacionCollection().add(HActivacion);
                idDistribuidorNew = em.merge(idDistribuidorNew);
            }
            if (idRegionOld != null && !idRegionOld.equals(idRegionNew)) {
                idRegionOld.getHActivacionCollection().remove(HActivacion);
                idRegionOld = em.merge(idRegionOld);
            }
            if (idRegionNew != null && !idRegionNew.equals(idRegionOld)) {
                idRegionNew.getHActivacionCollection().add(HActivacion);
                idRegionNew = em.merge(idRegionNew);
            }
            if (idTipoTelefoniaOld != null && !idTipoTelefoniaOld.equals(idTipoTelefoniaNew)) {
                idTipoTelefoniaOld.getHActivacionCollection().remove(HActivacion);
                idTipoTelefoniaOld = em.merge(idTipoTelefoniaOld);
            }
            if (idTipoTelefoniaNew != null && !idTipoTelefoniaNew.equals(idTipoTelefoniaOld)) {
                idTipoTelefoniaNew.getHActivacionCollection().add(HActivacion);
                idTipoTelefoniaNew = em.merge(idTipoTelefoniaNew);
            }
            if (idArchivoOld != null && !idArchivoOld.equals(idArchivoNew)) {
                idArchivoOld.getHActivacionCollection().remove(HActivacion);
                idArchivoOld = em.merge(idArchivoOld);
            }
            if (idArchivoNew != null && !idArchivoNew.equals(idArchivoOld)) {
                idArchivoNew.getHActivacionCollection().add(HActivacion);
                idArchivoNew = em.merge(idArchivoNew);
            }
            if (idUsuarioOld != null && !idUsuarioOld.equals(idUsuarioNew)) {
                idUsuarioOld.getHActivacionCollection().remove(HActivacion);
                idUsuarioOld = em.merge(idUsuarioOld);
            }
            if (idUsuarioNew != null && !idUsuarioNew.equals(idUsuarioOld)) {
                idUsuarioNew.getHActivacionCollection().add(HActivacion);
                idUsuarioNew = em.merge(idUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = HActivacion.getId();
                if (findHActivacion(id) == null) {
                    throw new NonexistentEntityException("The hActivacion with id " + id + " no longer exists.");
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
            HActivacion HActivacion;
            try {
                HActivacion = em.getReference(HActivacion.class, id);
                HActivacion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The HActivacion with id " + id + " no longer exists.", enfe);
            }
            CAutomata idAutomata = HActivacion.getIdAutomata();
            if (idAutomata != null) {
                idAutomata.getHActivacionCollection().remove(HActivacion);
                idAutomata = em.merge(idAutomata);
            }
            CCiudad idCiudad = HActivacion.getIdCiudad();
            if (idCiudad != null) {
                idCiudad.getHActivacionCollection().remove(HActivacion);
                idCiudad = em.merge(idCiudad);
            }
            CClientes idCliente = HActivacion.getIdCliente();
            if (idCliente != null) {
                idCliente.getHActivacionCollection().remove(HActivacion);
                idCliente = em.merge(idCliente);
            }
            CDistribuidor idDistribuidor = HActivacion.getIdDistribuidor();
            if (idDistribuidor != null) {
                idDistribuidor.getHActivacionCollection().remove(HActivacion);
                idDistribuidor = em.merge(idDistribuidor);
            }
            CRegion idRegion = HActivacion.getIdRegion();
            if (idRegion != null) {
                idRegion.getHActivacionCollection().remove(HActivacion);
                idRegion = em.merge(idRegion);
            }
            CTipoTelefono idTipoTelefonia = HActivacion.getIdTipoTelefonia();
            if (idTipoTelefonia != null) {
                idTipoTelefonia.getHActivacionCollection().remove(HActivacion);
                idTipoTelefonia = em.merge(idTipoTelefonia);
            }
            MArchivoLote idArchivo = HActivacion.getIdArchivo();
            if (idArchivo != null) {
                idArchivo.getHActivacionCollection().remove(HActivacion);
                idArchivo = em.merge(idArchivo);
            }
            SUsuarios idUsuario = HActivacion.getIdUsuario();
            if (idUsuario != null) {
                idUsuario.getHActivacionCollection().remove(HActivacion);
                idUsuario = em.merge(idUsuario);
            }
            em.remove(HActivacion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HActivacion> findHActivacionEntities() {
        return findHActivacionEntities(true, -1, -1);
    }

    public List<HActivacion> findHActivacionEntities(int maxResults, int firstResult) {
        return findHActivacionEntities(false, maxResults, firstResult);
    }

    private List<HActivacion> findHActivacionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HActivacion.class));
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

    public HActivacion findHActivacion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HActivacion.class, id);
        } finally {
            em.close();
        }
    }

    public int getHActivacionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HActivacion> rt = cq.from(HActivacion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<HActivacion> findHActivacionEntitiesRango(List fechas) {
        EntityManager em = getEntityManager();
        List<HActivacion> listaFiltrada = new ArrayList<>();
        try {
            Query q = em.createNamedQuery("HActivacion.findByRangoFecha")
                    .setParameter("fechaInicial", fechas.get(0))
                    .setParameter("fechaFinal", fechas.get(1));

            listaFiltrada = q.getResultList();

            return listaFiltrada;
        } finally {
            em.close();
        }
    }

    public List<HActivacion> encontrarReporteHActivacion(Date fechaInicio, Date fechaFinal, CCiudad activoStatus) {
        EntityManager em = getEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<HActivacion> query = cb.createQuery(HActivacion.class);
            Root<HActivacion> hActivacion = query.from(HActivacion.class);

            Join<HActivacion, CCiudad> idCiudad = hActivacion.join("idCiudad");

            List<Predicate> predicado = new ArrayList<>();

            if (activoStatus.getActivo() != null) {
                predicado.add(cb.equal(idCiudad.get("activo"), activoStatus.getActivo()));
            }

            if (fechaInicio != null && fechaFinal != null) {

                Path<Date> fechaPeticionPath = hActivacion.get("fechaPeticion");
                Predicate fecha = cb.between(fechaPeticionPath, fechaInicio, fechaFinal);
                predicado.add(fecha);

            }

            query.select(hActivacion).where(predicado.toArray(new Predicate[]{}));

            TypedQuery<HActivacion> typedQuery = em.createQuery(query);

            List<HActivacion> resultado = typedQuery.getResultList();

            if (resultado.isEmpty()) {
                //Mandar mensaje No existen registros
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "No hay registros", "No se encontraron registros"));
            }

            return resultado;
        } finally {
            em.close();
        }

    }

    public List<HActivacion> encontrarReporteHActivacionSTP(LocalDate fechaInicio, LocalDate fechaFinal, CCiudad activoStatus) {

        EntityManager em = getEntityManager();
        List<HActivacion> resultado = new ArrayList<>();

        try {
            StoredProcedureQuery storedProcedure = em.createStoredProcedureQuery("reporte_STP_francisco", HActivacion.class);
            storedProcedure.registerStoredProcedureParameter("fechaInicial", LocalDate.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("fechaFinal", LocalDate.class, ParameterMode.IN);
            storedProcedure.registerStoredProcedureParameter("estatus", Boolean.class, ParameterMode.IN);

            storedProcedure.setParameter("fechaInicial", fechaInicio);
            storedProcedure.setParameter("fechaFinal", fechaFinal);
            storedProcedure.setParameter("estatus", activoStatus.getActivo());

            resultado = storedProcedure.getResultList();

            if (resultado.isEmpty()) {
                //Mandar mensaje No existen registros
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "No hay registros", "No se encontraron registros"));
            }

            return resultado;
        } finally {
            em.close();
        }

    }

}
