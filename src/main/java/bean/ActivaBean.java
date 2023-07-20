package bean;

import com.blueweb.controller.HActivacionJpaController;
import com.blueweb.entity.CCiudad;
import com.blueweb.entity.HActivacion;
import java.io.Serializable;
import java.time.LocalDate;
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

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class ActivaBean implements Serializable {

    private List<HActivacion> rangoFechasEntitys;

    private HActivacion activacion = new HActivacion();
    private LocalDate fechaInicial;
    private LocalDate fechaFinal;
    private boolean activo = true;    
    private CCiudad ciudad = new CCiudad();
    private LocalDate fechaActual; 
    private LocalDate fechaInicialActivacion;

    private HActivacionJpaController HAController = new HActivacionJpaController();
    
    @PostConstruct
    public void init() {
        fechaActual = LocalDate.now();
       
        System.out.println(fechaActual);
        // Inizalizar objteto
    }
    
    public Boolean activarFechaFinal(){
        return fechaInicial == null;
    }
    
    


    
     public List<HActivacion> consultarTabla() {

        System.out.println(activo);
        System.out.println(fechaInicial);
        System.out.println(fechaFinal);
        
        Date fechaInicialDate = java.sql.Date.valueOf(fechaInicial);
        Date fechaFinalDate = java.sql.Date.valueOf(fechaFinal);

        
        rangoFechasEntitys = new ArrayList<>();
        
        //Obtener el rango fechas 
        
         try {
            rangoFechasEntitys = HAController.encontrarReporteHActivacion(fechaInicialDate, fechaFinalDate, ciudad);
            
            if(!rangoFechasEntitys.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Registros encontrados", "Operación exitosa"));
            }
        } catch (Exception ex) {
            Logger.getLogger(ActivaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Obtener entidades en ese rango de fechas 
        
        //Retornar entidades filtradas

        return rangoFechasEntitys;
        //mostrarTabla();

    }
    
        public List<HActivacion> consultarTablaSTP() {

        System.out.println(activo);
        System.out.println(fechaInicial);
        System.out.println(fechaFinal);

        
        rangoFechasEntitys = new ArrayList<>();
        
        //Obtener el rango fechas 
        
         try {
            rangoFechasEntitys = HAController.encontrarReporteHActivacionSTP(fechaInicial, fechaFinal, ciudad);
            
            if(!rangoFechasEntitys.isEmpty()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Registros encontrados", "Operación exitosa"));
            }
           
        } catch (Exception ex) {
            Logger.getLogger(ActivaBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Obtener entidades en ese rango de fechas 
        
        //Retornar entidades filtradas

        return rangoFechasEntitys;
        //mostrarTabla();

    }

    public boolean mostrarTabla() {
        return fechaInicial != null && fechaFinal != null;
    }

    //<editor-fold defaultstate="collapsed" desc="Gets y Sets">
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
    public LocalDate getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(LocalDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFianl
     */
    public LocalDate getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFianl the fechaFianl to set
     */
    public void setFechaFinal(LocalDate fechaFianl) {
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

    /**
     * @return the fechaActual
     */
    public LocalDate getFechaActual() {
        return fechaActual;
    }

    /**
     * @param fechaActual the fechaActual to set
     */
    public void setFechaActual(LocalDate fechaActual) {
        this.fechaActual = fechaActual;
    }

    /**
     * @return the fechaInicialActivacion
     */
    public LocalDate getFechaInicialActivacion() {
        return fechaInicialActivacion;
    }

    /**
     * @param fechaInicialActivacion the fechaInicialActivacion to set
     */
    public void setFechaInicialActivacion(LocalDate fechaInicialActivacion) {
        this.fechaInicialActivacion = fechaInicialActivacion;
    }

}
