package bean;

import com.blueweb.controller.SPerfilesJpaController;
import com.blueweb.controller.SUsuariosJpaController;
import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.entity.SPerfiles;
import com.blueweb.entity.SUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import objetos.Respuesta;

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class PerfilesBean implements Serializable {

    private List<SPerfiles> perfiles;
    private List<SPerfiles> perfilesFiltrados;
    
    private SPerfilesJpaController PController = new SPerfilesJpaController();
    private SUsuariosJpaController UController = new SUsuariosJpaController();
    private SPerfiles perfil = new SPerfiles();
    private SUsuarios usuario = new SUsuarios();
    
    private final Respuesta res = new Respuesta();
    private LoginBean usu = new LoginBean();
    

    @PostConstruct
    public void init() {
        setPerfiles(new ArrayList<SPerfiles>());
        setPerfiles(getPController().findSPerfilesEntities());
        

        // Inizalizar objteto
    }

    public void agregarPerfil() {

        perfil.setFechaAlta(new Date());
        perfil.setFechaServidor(new Date());
        perfil.setActivo(true);
        perfil.setIdUsuarioModifica(usu.retornaUsuarioActual());

        try {
            PController.create(perfil);
            int r = 0;
            res.evaluarRespuesta(r);
        } catch (Exception ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Se AGREGO con exito");

        perfiles = PController.findSPerfilesEntities();
        limpiarPerfil();
    }

    public void editarPerfil(SPerfiles perfil) {
        
        
        perfil.setIdUsuarioModifica(usu.retornaUsuarioActualEditar());

        try {
            PController.edit(perfil);
            int r = 0;
            res.evaluarRespuesta(r);
        } catch (Exception ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        limpiarPerfil();
    }

    public void borrarPerfil(SPerfiles perfil) {

        try {
            PController.destroy(perfil.getIdPerfil());
            int r = 0;
            res.evaluarRespuesta(r);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        perfiles = PController.findSPerfilesEntities();
    }

    public void limpiarPerfil() {
        this.perfil.setActivo(false);
        this.perfil.setDescripcion("");
        this.perfil.setFechaAlta(null);
        this.perfil.setFechaServidor(null);
        this.perfil.setIdPerfil(0);
        this.perfil.setIdUsuarioModifica(null);
        this.perfil.setNombrePerfil("");
    }

    /**
     * @return the perfiles
     */
    public List<SPerfiles> getPerfiles() {
        return perfiles;
    }

    /**
     * @param perfiles the perfiles to set
     */
    public void setPerfiles(List<SPerfiles> perfiles) {
        this.perfiles = perfiles;
    }

    /**
     * @return the perfil
     */
    public SPerfiles getPerfil() {
        return perfil;
    }

    /**
     * @param perfil the perfil to set
     */
    public void setPerfil(SPerfiles perfil) {
        this.perfil = perfil;
    }

    /**
     * @return the PController
     */
    public SPerfilesJpaController getPController() {
        return PController;
    }

    /**
     * @param PController the PController to set
     */
    public void setPController(SPerfilesJpaController PController) {
        this.PController = PController;
    }

    /**
     * @return the perfilesFiltrados
     */
    public List<SPerfiles> getPerfilesFiltrados() {
        return perfilesFiltrados;
    }

    /**
     * @param perfilesFiltrados the perfilesFiltrados to set
     */
    public void setPerfilesFiltrados(List<SPerfiles> perfilesFiltrados) {
        this.perfilesFiltrados = perfilesFiltrados;
    }

    /**
     * @return the UController
     */
    public SUsuariosJpaController getUController() {
        return UController;
    }

    /**
     * @param UController the UController to set
     */
    public void setUController(SUsuariosJpaController UController) {
        this.UController = UController;
    }

    /**
     * @return the usuario
     */
    public SUsuarios getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(SUsuarios usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the usu
     */
    public LoginBean getUsu() {
        return usu;
    }

    /**
     * @param usu the usu to set
     */
    public void setUsu(LoginBean usu) {
        this.usu = usu;
    }

}
