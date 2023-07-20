package bean;

import com.blueweb.controller.SAccesosJpaController;
import com.blueweb.controller.SPerfilesAccesosJpaController;
import com.blueweb.controller.SPerfilesJpaController;
import com.blueweb.controller.exceptions.NonexistentEntityException;
import com.blueweb.entity.SAccesos;
import com.blueweb.entity.SPerfiles;
import com.blueweb.entity.SPerfilesAccesos;
import com.blueweb.entity.SPerfilesAccesosPK;
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
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class AccesosBean implements Serializable {

    private DualListModel<SAccesos> accesos;
    private DualListModel<SAccesos> accesosNuevos;
    private SAccesos acceso = new SAccesos();
    private SPerfilesAccesosPK perfilesAccesoPK = new SPerfilesAccesosPK();
    private SPerfilesAccesos perfilesAcceso = new SPerfilesAccesos();
    private SAccesosJpaController AController = new SAccesosJpaController();
    private SPerfilesJpaController PController = new SPerfilesJpaController();
    private SPerfilesAccesosJpaController PAController = new SPerfilesAccesosJpaController();
    private LoginBean usu = new LoginBean();

    private SPerfiles perfil;
    private SPerfilesAccesos perfilesAcceos;
    private List<SAccesos> accesosEntitys;
    private List<SAccesos> perfilesAccesosEntitysActuales;
    private List<SAccesos> perfilesAccesosEntitysDisponibles;
    private List<SAccesos> accesosDisponibles;
    private List<SAccesos> accesosActuales;
    private static int idPerfilAcceso;
    private List<SAccesos> listAccesosTarget;
    private List<SAccesos> listAccesosSource;

    @PostConstruct
    public void init() {
        accesos = new DualListModel<SAccesos>();
        accesosNuevos = new DualListModel<SAccesos>();
        accesosActuales = new ArrayList<SAccesos>();
        accesosDisponibles = new ArrayList<SAccesos>();
        listAccesosTarget = new ArrayList<SAccesos>();
        listAccesosSource = new ArrayList<SAccesos>();
    }

    public void mostrarAccesosActualesDisponibles(int idPerfil) {
        idPerfilAcceso = idPerfil;
        perfilesAccesosEntitysActuales = PAController.encontrarPerfilesAccesosActuales(idPerfil);
        perfilesAccesosEntitysDisponibles = PAController.encontrarPerfilesAccesosDisponibles(idPerfil);
        //Llenamos la lista con el nombre 
 
        accesos = new DualListModel<SAccesos>(perfilesAccesosEntitysActuales, perfilesAccesosEntitysDisponibles);

    }

    public DualListModel<SAccesos> capturarNuevosAccesos() {

        //capturar las nuevas listas 
        accesosActuales.addAll(accesos.getSource());
        accesosDisponibles.addAll(accesos.getTarget());

        System.out.println(accesosActuales);
        System.out.println(accesosDisponibles);

        accesosNuevos = new DualListModel<SAccesos>(accesosActuales, accesosDisponibles);
        //Retornamos la nueva lista
        return accesosNuevos;
    }

    public void aplicarCambioAccesos() {

        //Traer nueva lista
        accesosNuevos = capturarNuevosAccesos();
        //Borrar accesos TARGET se necesita el idperfil y id de cada acceso
        if (!accesosDisponibles.isEmpty()) {
            listAccesosTarget = AController.encontrarAcceso(accesosNuevos.getTarget());
            for (SAccesos acces : listAccesosTarget) {
                perfilesAccesoPK.setIdPerfil(idPerfilAcceso);
                perfilesAccesoPK.setIdAcceso(acces.getIdAcceso());
                try {
                    PAController.destroy(perfilesAccesoPK);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
            //Agregar nuevos accesos SOURCE se necesita entidad PerfilAccesos
        if (!accesosActuales.isEmpty()) {
            listAccesosSource = AController.encontrarAcceso(accesosNuevos.getSource());
            perfil = PController.encontrarPerfil(idPerfilAcceso);
            for (SAccesos acces : listAccesosSource) {
                perfilesAcceso.setFechaServidor(new Date());
                perfilesAcceso.setIdUsuarioModifica(usu.retornaUsuarioActual());
                perfilesAcceso.setSAccesos(acces);
                perfilesAcceso.setSPerfiles(perfil);
                perfilesAccesoPK.setIdAcceso(acces.getIdAcceso());
                perfilesAcceso.setSPerfilesAccesosPK(perfilesAccesoPK);

                try {
                    PAController.create(perfilesAcceso);
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Registros encontrados", "Operación exitosa"));
                } catch (Exception ex) {
                    Logger.getLogger(AccesosBean.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            if(perfilesAcceso != null){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Registros encontrados", "Operación exitosa"));
            }
        }
        
        //Cerrar dialog y actualizar
        PrimeFaces current = PrimeFaces.current();
        current.executeScript("PF('pickList').hide();");

    }

    /**
     * @return the accesos
     */
    public DualListModel<SAccesos> getAccesos() {
        return accesos;
    }

    /**
     * @param accesos the accesos to set
     */
    public void setAccesos(DualListModel<SAccesos> accesos) {
        this.accesos = accesos;
    }

    /**
     * @return the acceso
     */
    public SAccesos getAcceso() {
        return acceso;
    }

    /**
     * @param acceso the acceso to set
     */
    public void setAcceso(SAccesos acceso) {
        this.acceso = acceso;
    }

    /**
     * @return the AController
     */
    public SAccesosJpaController getAController() {
        return AController;
    }

    /**
     * @param AController the AController to set
     */
    public void setAController(SAccesosJpaController AController) {
        this.AController = AController;
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
     * @return the accesosEntitys
     */
    public List<SAccesos> getAccesosEntitys() {
        return accesosEntitys;
    }

    /**
     * @param accesosEntitys the accesosEntitys to set
     */
    public void setAccesosEntitys(List<SAccesos> accesosEntitys) {
        this.accesosEntitys = accesosEntitys;
    }

    /**
     * @return the perfilesAccesosEntitys
     */
    public List<SAccesos> getPerfilesAccesosEntitys() {
        return perfilesAccesosEntitysActuales;
    }

    /**
     * @param perfilesAccesosEntitys the perfilesAccesosEntitys to set
     */
    public void setPerfilesAccesosEntitys(List<SAccesos> perfilesAccesosEntitys) {
        this.perfilesAccesosEntitysActuales = perfilesAccesosEntitys;
    }

    /**
     * @return the PAController
     */
    public SPerfilesAccesosJpaController getPAController() {
        return PAController;
    }

    /**
     * @param PAController the PAController to set
     */
    public void setPAController(SPerfilesAccesosJpaController PAController) {
        this.PAController = PAController;
    }

    /**
     * @return the perfilesAccesosEntitysDisponibles
     */
    public List<SAccesos> getPerfilesAccesosEntitysDisponibles() {
        return perfilesAccesosEntitysDisponibles;
    }

    /**
     * @param perfilesAccesosEntitysDisponibles the
     * perfilesAccesosEntitysDisponibles to set
     */
    public void setPerfilesAccesosEntitysDisponibles(List<SAccesos> perfilesAccesosEntitysDisponibles) {
        this.perfilesAccesosEntitysDisponibles = perfilesAccesosEntitysDisponibles;
    }

    /**
     * @return the idPerfilAcceso
     */
    public int getIdPerfilAcceso() {
        return idPerfilAcceso;
    }

    /**
     * @param idPerfilAcceso the idPerfilAcceso to set
     */
    public void setIdPerfilAcceso(int idPerfilAcceso) {
        this.idPerfilAcceso = idPerfilAcceso;
    }

    /**
     * @return the perfilesAccesoPK
     */
    public SPerfilesAccesosPK getPerfilesAccesoPK() {
        return perfilesAccesoPK;
    }

    /**
     * @param perfilesAccesoPK the perfilesAccesoPK to set
     */
    public void setPerfilesAccesoPK(SPerfilesAccesosPK perfilesAccesoPK) {
        this.perfilesAccesoPK = perfilesAccesoPK;
    }

    /**
     * @return the perfilesAcceos
     */
    public SPerfilesAccesos getPerfilesAcceos() {
        return perfilesAcceos;
    }

    /**
     * @param perfilesAcceos the perfilesAcceos to set
     */
    public void setPerfilesAcceos(SPerfilesAccesos perfilesAcceos) {
        this.perfilesAcceos = perfilesAcceos;
    }

    /**
     * @return the listAccesosTarget
     */
    public List<SAccesos> getListAccesosTarget() {
        return listAccesosTarget;
    }

    /**
     * @param listAccesosTarget the listAccesosTarget to set
     */
    public void setListAccesosTarget(List<SAccesos> listAccesosTarget) {
        this.listAccesosTarget = listAccesosTarget;
    }

    /**
     * @return the listAccesosSource
     */
    public List<SAccesos> getListAccesosSource() {
        return listAccesosSource;
    }

    /**
     * @param listAccesosSource the listAccesosSource to set
     */
    public void setListAccesosSource(List<SAccesos> listAccesosSource) {
        this.listAccesosSource = listAccesosSource;
    }

    /**
     * @return the perfilesAcceso
     */
    public SPerfilesAccesos getPerfilesAcceso() {
        return perfilesAcceso;
    }

    /**
     * @param perfilesAcceso the perfilesAcceso to set
     */
    public void setPerfilesAcceso(SPerfilesAccesos perfilesAcceso) {
        this.perfilesAcceso = perfilesAcceso;
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

}
