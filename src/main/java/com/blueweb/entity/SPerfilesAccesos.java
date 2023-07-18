
package com.blueweb.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Blueweb
 */
@Entity
@Table(name = "S_PERFILES_ACCESOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SPerfilesAccesos.findAll", query = "SELECT s FROM SPerfilesAccesos s"),
    @NamedQuery(name = "SPerfilesAccesos.findByIdPerfil", query = "SELECT s FROM SPerfilesAccesos s WHERE s.sPerfilesAccesosPK.idPerfil = :idPerfil"),
    @NamedQuery(name = "SPerfilesAccesos.findByIdAcceso", query = "SELECT s FROM SPerfilesAccesos s WHERE s.sPerfilesAccesosPK.idAcceso = :idAcceso"),
    @NamedQuery(name = "SPerfilesAccesos.findByFechaServidor", query = "SELECT s FROM SPerfilesAccesos s WHERE s.fechaServidor = :fechaServidor")
    })
public class SPerfilesAccesos implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected SPerfilesAccesosPK sPerfilesAccesosPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SERVIDOR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaServidor;
    @JoinColumn(name = "ID_ACCESO", referencedColumnName = "ID_ACCESO", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SAccesos sAccesos;
    @JoinColumn(name = "ID_PERFIL", referencedColumnName = "ID_PERFIL", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SPerfiles sPerfiles;
    @JoinColumn(name = "ID_USUARIO_MODIFICA", referencedColumnName = "ID_USUARIO")
    @ManyToOne
    private SUsuarios idUsuarioModifica;

    public SPerfilesAccesos() {
    }

    public SPerfilesAccesos(SPerfilesAccesosPK sPerfilesAccesosPK) {
        this.sPerfilesAccesosPK = sPerfilesAccesosPK;
    }

    public SPerfilesAccesos(SPerfilesAccesosPK sPerfilesAccesosPK, Date fechaServidor) {
        this.sPerfilesAccesosPK = sPerfilesAccesosPK;
        this.fechaServidor = fechaServidor;
    }

    public SPerfilesAccesos(int idPerfil, int idAcceso) {
        this.sPerfilesAccesosPK = new SPerfilesAccesosPK(idPerfil, idAcceso);
    }

    public SPerfilesAccesosPK getSPerfilesAccesosPK() {
        return sPerfilesAccesosPK;
    }

    public void setSPerfilesAccesosPK(SPerfilesAccesosPK sPerfilesAccesosPK) {
        this.sPerfilesAccesosPK = sPerfilesAccesosPK;
    }

    public Date getFechaServidor() {
        return fechaServidor;
    }

    public void setFechaServidor(Date fechaServidor) {
        this.fechaServidor = fechaServidor;
    }

    public SAccesos getSAccesos() {
        return sAccesos;
    }

    public void setSAccesos(SAccesos sAccesos) {
        this.sAccesos = sAccesos;
    }

    public SPerfiles getSPerfiles() {
        return sPerfiles;
    }

    public void setSPerfiles(SPerfiles sPerfiles) {
        this.sPerfiles = sPerfiles;
    }

    public SUsuarios getIdUsuarioModifica() {
        return idUsuarioModifica;
    }

    public void setIdUsuarioModifica(SUsuarios idUsuarioModifica) {
        this.idUsuarioModifica = idUsuarioModifica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sPerfilesAccesosPK != null ? sPerfilesAccesosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SPerfilesAccesos)) {
            return false;
        }
        SPerfilesAccesos other = (SPerfilesAccesos) object;
        if ((this.sPerfilesAccesosPK == null && other.sPerfilesAccesosPK != null) || (this.sPerfilesAccesosPK != null && !this.sPerfilesAccesosPK.equals(other.sPerfilesAccesosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.blueweb.entity.SPerfilesAccesos[ sPerfilesAccesosPK=" + sPerfilesAccesosPK + " ]";
    }
    
}
