package bean;

import com.blueweb.controller.HActivacionJpaController;
import com.blueweb.entity.CCiudad;
import com.blueweb.entity.HActivacion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class ActivaBean implements Serializable {

    private List<HActivacion> activaciones;
    private List<HActivacion> activacionesFiltrados;

    private List<HActivacion> rangoFechasEntitys;

    private HActivacion activacion = new HActivacion();
    private Date fechaInicial;
    private Date fechaFinal;
    private boolean activo = true;    
    private CCiudad ciudad = new CCiudad();

    private HActivacionJpaController HAController = new HActivacionJpaController();

    @PostConstruct
    public void init() {
        activaciones = new ArrayList<HActivacion>();
        

        // Inizalizar objteto
    }

    public List<HActivacion> consultarTabla() {

        System.out.println(activo);
        System.out.println(fechaInicial);
        System.out.println(fechaFinal);

        ciudad.setActivo(activo);
        rangoFechasEntitys = new ArrayList<>();
        //Obtener el rango fechas 

        //Obtener entidades en ese rango de fechas 
        rangoFechasEntitys = HAController.encontrarReporteHActivacion(fechaInicial, fechaFinal, ciudad);
        //Retornar entidades filtradas

        return rangoFechasEntitys;
        //mostrarTabla();

    }

    public boolean mostrarTabla() {
        return fechaInicial != null && fechaFinal != null;
    }

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
    /**
     * @return the activaciones
     */
    public List<HActivacion> getActivaciones() {
        return activaciones;
    }

    /**
     * @param activaciones the activaciones to set
     */
    public void setActivaciones(List<HActivacion> activaciones) {
        this.activaciones = activaciones;
    }

    /**
     * @return the activacionesFiltrados
     */
    public List<HActivacion> getActivacionesFiltrados() {
        return activacionesFiltrados;
    }

    /**
     * @param activacionesFiltrados the activacionesFiltrados to set
     */
    public void setActivacionesFiltrados(List<HActivacion> activacionesFiltrados) {
        this.activacionesFiltrados = activacionesFiltrados;
    }

    /**
     * @return the activacion
     */
    public HActivacion getActivacion() {
        return activacion;
    }

    /**
     * @param activacion the activacion to set
     */
    public void setActivacion(HActivacion activacion) {
        this.activacion = activacion;
    }

    /**
     * @return the HAController
     */
    public HActivacionJpaController getHAController() {
        return HAController;
    }

    /**
     * @param HAController the HAController to set
     */
    public void setHAController(HActivacionJpaController HAController) {
        this.HAController = HAController;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFianl
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFianl the fechaFianl to set
     */
    public void setFechaFinal(Date fechaFianl) {
        this.fechaFinal = fechaFianl;
    }

    /**
     * @return the rangoFechasEntitys
     */
    public List<HActivacion> getRangoFechasEntitys() {
        return rangoFechasEntitys;
    }

    /**
     * @param rangoFechasEntitys the rangoFechasEntitys to set
     */
    public void setRangoFechasEntitys(List<HActivacion> rangoFechasEntitys) {
        this.rangoFechasEntitys = rangoFechasEntitys;
    }

    /**
     * @return the activo
     */
    public boolean isActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
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
//</editor-fold>

}
