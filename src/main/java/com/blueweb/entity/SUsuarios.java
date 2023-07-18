

package com.blueweb.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Blueweb
 */
@Entity
@Table(name = "S_USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SUsuarios.findAll", query = "SELECT s FROM SUsuarios s"),
    @NamedQuery(name = "SUsuarios.findByIdUsuario", query = "SELECT s FROM SUsuarios s WHERE s.idUsuario = :idUsuario"),
    @NamedQuery(name = "SUsuarios.findByUsuario", query = "SELECT s idUsuario FROM SUsuarios s WHERE s.usuario = :usuario"),
    @NamedQuery(name = "SUsuarios.findByNombreUsuario", query = "SELECT s FROM SUsuarios s WHERE s.nombreUsuario = :nombreUsuario"),
    @NamedQuery(name = "SUsuarios.findByPassword", query = "SELECT s FROM SUsuarios s WHERE s.password = :password"),
    @NamedQuery(name = "SUsuarios.findByCorreo", query = "SELECT s FROM SUsuarios s WHERE s.correo = :correo"),
    @NamedQuery(name = "SUsuarios.findByActivo", query = "SELECT s FROM SUsuarios s WHERE s.activo = :activo"),
    @NamedQuery(name = "SUsuarios.findByUltimaSesion", query = "SELECT s FROM SUsuarios s WHERE s.ultimaSesion = :ultimaSesion"),
    @NamedQuery(name = "SUsuarios.findByFechaAlta", query = "SELECT s FROM SUsuarios s WHERE s.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "SUsuarios.findByFechaBaja", query = "SELECT s FROM SUsuarios s WHERE s.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "SUsuarios.findByFechaServidor", query = "SELECT s FROM SUsuarios s WHERE s.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "SUsuarios.findByUsuarioAndPassword", query = "SELECT s FROM SUsuarios s WHERE s.usuario = :usuario AND s.password = :password")
})
public class SUsuarios implements Serializable {

    @OneToMany(mappedBy = "idUsuarioModifica")
    private Collection<CDistribuidor> cDistribuidorCollection;
    @OneToMany(mappedBy = "idUsuarioModifica")
    private Collection<CRegion> cRegionCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<MArchivoLote> mArchivoLoteCollection;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<HActivacion> hActivacionCollection;

    @OneToMany(mappedBy = "idUsuarioModifica")
    private Collection<SPerfilesAccesos> sPerfilesAccesosCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "USUARIO")
    private String usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "PASSWORD")
    private String password;
    @Size(max = 50)
    @Column(name = "CORREO")
    private String correo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ACTIVO")
    private boolean activo;
    @Column(name = "ULTIMA_SESION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimaSesion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    @ManyToOne
    private CClientes idCliente;
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL")
    @ManyToOne
    private SPerfiles idPerfil;
    @OneToMany(mappedBy = "idUsuarioModifica")
    private Collection<SUsuarios> sUsuariosCollection;
    @JoinColumn(name = "ID_USUARIO_MODIFICA", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private SUsuarios idUsuarioModifica;
    @OneToMany(mappedBy = "idUsuario")
    private Collection<CCiudad> cCiudadCollection;
    @OneToMany(mappedBy = "idUsuarioModifica")
    private Collection<CClientes> cClientesCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUsuarioModifica")
    private Collection<SPerfiles> sPerfilesCollection;

    public SUsuarios() {
    }

    public SUsuarios(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public SUsuarios(Integer idUsuario, String usuario, String nombreUsuario, String password, boolean activo, Date fechaAlta, Date fechaServidor) {
        this.idUsuario = idUsuario;
        this.usuario = usuario;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.activo = activo;
        this.fechaAlta = fechaAlta;
        this.fechaServidor = fechaServidor;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Date getUltimaSesion() {
        return ultimaSesion;
    }

    public void setUltimaSesion(Date ultimaSesion) {
        this.ultimaSesion = ultimaSesion;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public CClientes getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(CClientes idCliente) {
        this.idCliente = idCliente;
    }

    public SPerfiles getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(SPerfiles idPerfil) {
        this.idPerfil = idPerfil;
    }

    @XmlTransient
    public Collection<SUsuarios> getSUsuariosCollection() {
        return sUsuariosCollection;
    }

    public void setSUsuariosCollection(Collection<SUsuarios> sUsuariosCollection) {
        this.sUsuariosCollection = sUsuariosCollection;
    }

    public SUsuarios getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(SUsuarios idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }

    @XmlTransient
    public Collection<CCiudad> getCCiudadCollection() {
        return cCiudadCollection;
    }

    public void setCCiudadCollection(Collection<CCiudad> cCiudadCollection) {
        this.cCiudadCollection = cCiudadCollection;
    }

    @XmlTransient
    public Collection<CClientes> getCClientesCollection() {
        return cClientesCollection;
    }

    public void setCClientesCollection(Collection<CClientes> cClientesCollection) {
        this.cClientesCollection = cClientesCollection;
    }

    @XmlTransient
    public Collection<SPerfiles> getSPerfilesCollection() {
        return sPerfilesCollection;
    }

    public void setSPerfilesCollection(Collection<SPerfiles> sPerfilesCollection) {
        this.sPerfilesCollection = sPerfilesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUsuario != null ? idUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SUsuarios)) {
            return false;
        }
        SUsuarios other = (SUsuarios) object;
        if ((this.idUsuario == null && other.idUsuario != null) || (this.idUsuario != null && !this.idUsuario.equals(other.idUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blueweb.entity.SUsuarios[ idUsuario=" + idUsuario + " ]";
    }

    @XmlTransient
    public Collection<SPerfilesAccesos> getSPerfilesAccesosCollection() {
        return sPerfilesAccesosCollection;
    }

    public void setSPerfilesAccesosCollection(Collection<SPerfilesAccesos> sPerfilesAccesosCollection) {
        this.sPerfilesAccesosCollection = sPerfilesAccesosCollection;
    }

    @XmlTransient
    public Collection<CDistribuidor> getCDistribuidorCollection() {
        return cDistribuidorCollection;
    }

    public void setCDistribuidorCollection(Collection<CDistribuidor> cDistribuidorCollection) {
        this.cDistribuidorCollection = cDistribuidorCollection;
    }

    @XmlTransient
    public Collection<CRegion> getCRegionCollection() {
        return cRegionCollection;
    }

    public void setCRegionCollection(Collection<CRegion> cRegionCollection) {
        this.cRegionCollection = cRegionCollection;
    }

    @XmlTransient
    public Collection<MArchivoLote> getMArchivoLoteCollection() {
        return mArchivoLoteCollection;
    }

    public void setMArchivoLoteCollection(Collection<MArchivoLote> mArchivoLoteCollection) {
        this.mArchivoLoteCollection = mArchivoLoteCollection;
    }

    @XmlTransient
    public Collection<HActivacion> getHActivacionCollection() {
        return hActivacionCollection;
    }

    public void setHActivacionCollection(Collection<HActivacion> hActivacionCollection) {
        this.hActivacionCollection = hActivacionCollection;
    }
    
}
