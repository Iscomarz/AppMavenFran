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
import javax.persistence.Lob;
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
@Table(name = "M_ARCHIVO_LOTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MArchivoLote.findAll", query = "SELECT m FROM MArchivoLote m"),
    @NamedQuery(name = "MArchivoLote.findByIdArchivo", query = "SELECT m FROM MArchivoLote m WHERE m.idArchivo = :idArchivo"),
    @NamedQuery(name = "MArchivoLote.findByNombreArchivo", query = "SELECT m FROM MArchivoLote m WHERE m.nombreArchivo = :nombreArchivo"),
    @NamedQuery(name = "MArchivoLote.findByTipo", query = "SELECT m FROM MArchivoLote m WHERE m.tipo = :tipo"),
    @NamedQuery(name = "MArchivoLote.findByFechaAlta", query = "SELECT m FROM MArchivoLote m WHERE m.fechaAlta = :fechaAlta"),
    @NamedQuery(name = "MArchivoLote.findByFechaServidor", query = "SELECT m FROM MArchivoLote m WHERE m.fechaServidor = :fechaServidor"),
    @NamedQuery(name = "MArchivoLote.findByArchivoGenerado", query = "SELECT m FROM MArchivoLote m WHERE m.archivoGenerado = :archivoGenerado")})
public class MArchivoLote implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ARCHIVO")
    private Long idArchivo;
    @Size(max = 100)
    @Column(name = "NOMBRE_ARCHIVO")
    private String nombreArchivo;
    @Size(max = 20)
    @Column(name = "TIPO")
    private String tipo;
    @Lob
    @Column(name = "ARCHIVO")
    private byte[] archivo;
    @Column(name = "FECHA_ALTA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAlta;
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @Size(max = 100)
    @Column(name = "ARCHIVO_GENERADO")
    private String archivoGenerado;
    @JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private SUsuarios idUsuario;
    @OneToMany(mappedBy = "idArchivo")
    private Collection<HActivacion> hActivacionCollection;

    public MArchivoLote() {
    }

    public MArchivoLote(Long idArchivo) {
        this.idArchivo = idArchivo;
    }

    public Long getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Long idArchivo) {
        this.idArchivo = idArchivo;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public Date getFechaAlta() {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta) {
        this.fechaAlta = fechaAlta;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public String getArchivoGenerado() {
        return archivoGenerado;
    }

    public void setArchivoGenerado(String archivoGenerado) {
        this.archivoGenerado = archivoGenerado;
    }

    public SUsuarios getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(SUsuarios idUsuario) {
        this.idUsuario = idUsuario;
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
        hash += (idArchivo != null ? idArchivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MArchivoLote)) {
            return false;
        }
        MArchivoLote other = (MArchivoLote) object;
        if ((this.idArchivo == null && other.idArchivo != null) || (this.idArchivo != null && !this.idArchivo.equals(other.idArchivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blueweb.entity.MArchivoLote[ idArchivo=" + idArchivo + " ]";
    }
    
}
