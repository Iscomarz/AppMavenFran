/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.blueweb.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "C_REGION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CRegion.findAll", query = "SELECT c FROM CRegion c"),
    @NamedQuery(name = "CRegion.findByIdRegion", query = "SELECT c FROM CRegion c WHERE c.idRegion = :idRegion"),
    @NamedQuery(name = "CRegion.findByNombreRegion", query = "SELECT c FROM CRegion c WHERE c.nombreRegion = :nombreRegion"),
    @NamedQuery(name = "CRegion.findByActivo", query = "SELECT c FROM CRegion c WHERE c.activo = :activo"),
    @NamedQuery(name = "CRegion.findByFechaAlta", query = "SELECT c FROM CRegion c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CRegion.findByFechaBaja", query = "SELECT c FROM CRegion c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CRegion.findByFechaServidor", query = "SELECT c FROM CRegion c WHERE c.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "CRegion.findByPlaza", query = "SELECT c FROM CRegion c WHERE c.plaza = :plaza")})
public class CRegion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_REGION")
    private Long idRegion;
    @Size(max = 50)
    @Column(name = "NOMBRE_REGION")
    private String nombreRegion;
    @Column(name = "ACTIVO")
    private Boolean activo;
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "FECHA_BAJA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaBaja;
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @Size(max = 50)
    @Column(name = "PLAZA")
    private String plaza;
    @OneToMany(mappedBy = "idRegion")
    private Collection<CDistribuidor> cDistribuidorCollection;
    @JoinColumn(name = "ID_USUARIO_MODIFICA", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private SUsuarios idUsuarioModifica;
    @OneToMany(mappedBy = "idRegion")
    private Collection<CAutomata> cAutomataCollection;
    @OneToMany(mappedBy = "idRegion")
    private Collection<CTelefonia> cTelefoniaCollection;
    @OneToMany(mappedBy = "idRegion")
    private Collection<HActivacion> hActivacionCollection;

    public CRegion() {
    }

    public CRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public Long getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(Long idRegion) {
        this.idRegion = idRegion;
    }

    public String getNombreRegion() {
        return nombreRegion;
    }

    public void setNombreRegion(String nombreRegion) {
        this.nombreRegion = nombreRegion;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
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

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    @XmlTransient
    public Collection<CDistribuidor> getCDistribuidorCollection() {
        return cDistribuidorCollection;
    }

    public void setCDistribuidorCollection(Collection<CDistribuidor> cDistribuidorCollection) {
        this.cDistribuidorCollection = cDistribuidorCollection;
    }

    public SUsuarios getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(SUsuarios idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }

    @XmlTransient
    public Collection<CAutomata> getCAutomataCollection() {
        return cAutomataCollection;
    }

    public void setCAutomataCollection(Collection<CAutomata> cAutomataCollection) {
        this.cAutomataCollection = cAutomataCollection;
    }

    @XmlTransient
    public Collection<CTelefonia> getCTelefoniaCollection() {
        return cTelefoniaCollection;
    }

    public void setCTelefoniaCollection(Collection<CTelefonia> cTelefoniaCollection) {
        this.cTelefoniaCollection = cTelefoniaCollection;
    }

    @XmlTransient
    public Collection<HActivacion> getHActivacionCollection() {
        return hActivacionCollection;
    }

    public void setHActivacionCollection(Collection<HActivacion> hActivacionCollection) {
        this.hActivacionCollection = hActivacionCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegion != null ? idRegion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CRegion)) {
            return false;
        }
        CRegion other = (CRegion) object;
        if ((this.idRegion == null && other.idRegion != null) || (this.idRegion != null && !this.idRegion.equals(other.idRegion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blueweb.entity.CRegion[ idRegion=" + idRegion + " ]";
    }
    
}
