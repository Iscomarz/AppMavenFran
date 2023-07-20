package bean;

import com.blueweb.controller.CCiudadJpaController;
import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.entity.CCiudad;
import com.blueweb.entity.SUsuarios;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import objetos.Respuesta;
import sesiones.Sesion;


/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class CiudadBean implements Serializable {

    private List<CCiudad> ciudades;
    private CCiudad ciudad = new CCiudad();
    private List<CCiudad> ciudadesFiltradas;
    private Sesion sesionUsu = new Sesion(); 
    private CCiudadJpaController CController = new CCiudadJpaController();
    private long id;
    private final Respuesta res = new Respuesta();
    private SUsuarios usuario = new SUsuarios();
    private LoginBean login = new LoginBean();


    @PostConstruct
    public void init() {
        ciudades = new ArrayList<CCiudad>();
        ciudades = CController.findCCiudadEntities();
        
        // Inizalizar objteto
    }

    public void agregarCiudad() {
        ciudad.setFechaAlta(new Date());
        ciudad.setFechaServidor(new Date());
        ciudad.setActivo(true); 
        ciudad.setIdUsuario(login.retornaUsuarioActual());

        try {
            CController.create(ciudad);
            int r = 0;
            res.evaluarRespuesta(r);
        } catch (Exception ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Se AGREGO con exito");
           
        limpiarCiudad();
        ciudades = CController.findCCiudadEntities();
    }

    public void editarCiudad(CCiudad ciudad) {
        
        try {
            CController.edit(ciudad);
            int r = 0;
            res.evaluarRespuesta(r);
        } catch (Exception ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void borrarCiudad(CCiudad ciudad) {

        try {
            CController.destroy(ciudad.getIdCiudad());
            int r = 0;
            res.evaluarRespuesta(r);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ciudades = CController.findCCiudadEntities();
    }
    
    public void limpiarCiudad(){
        ciudad.setDescripcion("");
        ciudad.setCodigo("");
        ciudad.setLada(0);
    }

  
//</editor-fold>

    /**
     * @return the CController
     */
    public CCiudadJpaController getCController() {
        return CController;
    }

    /**
     * @param CController the CController to set
     */
    public void setCController(CCiudadJpaController CController) {
        this.CController = CController;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the ciudades
     */
    public List<CCiudad> getCiudades() {
        return ciudades;
    }

    /**
     * @param ciudades the ciudades to set
     */
    public void setCiudades(List<CCiudad> ciudades) {
        this.ciudades = ciudades;
    }

    /**
     * @return the ciudadesFiltradas
     */
    public List<CCiudad> getCiudadesFiltradas() {
        return ciudadesFiltradas;
    }

    /**
     * @param ciudadesFiltradas the ciudadesFiltradas to set
     */
    public void setCiudadesFiltradas(List<CCiudad> ciudadesFiltradas) {
        this.ciudadesFiltradas = ciudadesFiltradas;
    }

    /**
     * @return the ciudad
     */
    public CCiudad getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(CCiudad ciudad) {
        this.ciudad = ciudad;
    }

}
