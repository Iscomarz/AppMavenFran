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
@Table(name = "C_AUTOMATA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CAutomata.findAll", query = "SELECT c FROM CAutomata c"),
    @NamedQuery(name = "CAutomata.findByIdAutomata", query = "SELECT c FROM CAutomata c WHERE c.idAutomata = :idAutomata"),
    @NamedQuery(name = "CAutomata.findByClave", query = "SELECT c FROM CAutomata c WHERE c.clave = :clave"),
    @NamedQuery(name = "CAutomata.findByActivo", query = "SELECT c FROM CAutomata c WHERE c.activo = :activo"),
    @NamedQuery(name = "CAutomata.findByFechaAlta", query = "SELECT c FROM CAutomata c WHERE c.fechaAlta = :fechaAlta")})
public class CAutomata implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_AUTOMATA")
    private Long idAutomata;
    @Size(max = 10)
    @Column(name = "CLAVE")
    private String clave;
    @Column(name = "ACTIVO")
    private Boolean activo;
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @OneToMany(mappedBy = "idAutomata")
    private Collection<CDistribuidor> cDistribuidorCollection;
    @JoinColumn(name = "ID_REGION", referencedColumnName = "ID_REGION")
    @ManyToOne
    private CRegion idRegion;
    @OneToMany(mappedBy = "idAutomata")
    private Collection<HActivacion> hActivacionCollection;

    public CAutomata() {
    }

    public CAutomata(Long idAutomata) {
        this.idAutomata = idAutomata;
    }

    public Long getIdAutomata() {
        return idAutomata;
    }

    public void setIdAutomata(Long idAutomata) {
        this.idAutomata = idAutomata;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
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

    @XmlTransient
    public Collection<CDistribuidor> getCDistribuidorCollection() {
        return cDistribuidorCollection;
    }

    public void setCDistribuidorCollection(Collection<CDistribuidor> cDistribuidorCollection) {
        this.cDistribuidorCollection = cDistribuidorCollection;
    }

    public CRegion getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(CRegion idRegion) {
        this.idRegion = idRegion;
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
        hash += (idAutomata != null ? idAutomata.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CAutomata)) {
            return false;
        }
        CAutomata other = (CAutomata) object;
        if ((this.idAutomata == null && other.idAutomata != null) || (this.idAutomata != null && !this.idAutomata.equals(other.idAutomata))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blueweb.entity.CAutomata[ idAutomata=" + idAutomata + " ]";
    }
    
}
