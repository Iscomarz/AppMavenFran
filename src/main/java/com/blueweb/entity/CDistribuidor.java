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
@Table(name = "C_DISTRIBUIDOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CDistribuidor.findAll", query = "SELECT c FROM CDistribuidor c"),
    @NamedQuery(name = "CDistribuidor.findByIdDistribuidor", query = "SELECT c FROM CDistribuidor c WHERE c.idDistribuidor = :idDistribuidor"),
    @NamedQuery(name = "CDistribuidor.findByClaveDistribuidor", query = "SELECT c FROM CDistribuidor c WHERE c.claveDistribuidor = :claveDistribuidor"),
    @NamedQuery(name = "CDistribuidor.findByNombre", query = "SELECT c FROM CDistribuidor c WHERE c.nombre = :nombre"),
    @NamedQuery(name = "CDistribuidor.findByActivo", query = "SELECT c FROM CDistribuidor c WHERE c.activo = :activo"),
    @NamedQuery(name = "CDistribuidor.findByFechaAlta", query = "SELECT c FROM CDistribuidor c WHERE c.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "CDistribuidor.findByFechaBaja", query = "SELECT c FROM CDistribuidor c WHERE c.fechaBaja = :fechaBaja"),
    @NamedQuery(name = "CDistribuidor.findByFechaServidor", query = "SELECT c FROM CDistribuidor c WHERE c.fechaServidor = :fechaServidor")})
public class CDistribuidor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_DISTRIBUIDOR")
    private Long idDistribuidor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "CLAVE_DISTRIBUIDOR")
    private String claveDistribuidor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "NOMBRE")
    private String nombre;
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
    @JoinColumn(name = "ID_AUTOMATA", referencedColumnName = "ID_AUTOMATA")
    @ManyToOne
    private CAutomata idAutomata;
    @JoinColumn(name = "ID_REGION", referencedColumnName = "ID_REGION")
    @ManyToOne
    private CRegion idRegion;
    @JoinColumn(name = "ID_USUARIO_MODIFICA", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private SUsuarios idUsuarioModifica;
    @OneToMany(mappedBy = "idDistribuidor")
    private Collection<HActivacion> hActivacionCollection;

    public CDistribuidor() {
    }

    public CDistribuidor(Long idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public CDistribuidor(Long idDistribuidor, String claveDistribuidor, String nombre) {
        this.idDistribuidor = idDistribuidor;
        this.claveDistribuidor = claveDistribuidor;
        this.nombre = nombre;
    }

    public Long getIdDistribuidor() {
        return idDistribuidor;
    }

    public void setIdDistribuidor(Long idDistribuidor) {
        this.idDistribuidor = idDistribuidor;
    }

    public String getClaveDistribuidor() {
        return claveDistribuidor;
    }

    public void setClaveDistribuidor(String claveDistribuidor) {
        this.claveDistribuidor = claveDistribuidor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public CAutomata getIdAutomata() {
        return idAutomata;
    }

    public void setIdAutomata(CAutomata idAutomata) {
        this.idAutomata = idAutomata;
    }

    public CRegion getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(CRegion idRegion) {
        this.idRegion = idRegion;
    }

    public SUsuarios getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(SUsuarios idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
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
        hash += (idDistribuidor != null ? idDistribuidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CDistribuidor)) {
            return false;
        }
        CDistribuidor other = (CDistribuidor) object;
        if ((this.idDistribuidor == null && other.idDistribuidor != null) || (this.idDistribuidor != null && !this.idDistribuidor.equals(other.idDistribuidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blueweb.entity.CDistribuidor[ idDistribuidor=" + idDistribuidor + " ]";
    }
    
}
