
package bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import models.CrudModel;
import models.ParametroModel;
import objetos.Parametro;
import objetos.Respuesta;
import sesiones.Sesion;

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class ParametroBean implements Serializable {

    private List<Parametro> parametro;
    private List<Parametro> parametroFiltrado;
    CrudModel crud = new CrudModel();
    Parametro param = new Parametro();
    private Respuesta resError = new Respuesta();
    private String nombre;
    private String valor;
    private String descripcion;
    private int id;
    private int respuesta;

    @PostConstruct
    public void init() {
        parametro = new ArrayList<Parametro>();
        parametro = ParametroModel.traerParametros();
    }

    public void agregarParametro() {
        param.setNombre(nombre);
        param.setValor(valor);
        param.setDescripcion(descripcion);
        param.setId_usuario(Integer.parseInt(Sesion.getUserId()));
        respuesta = crud.agregarRegistroParam(param);
        if (respuesta == 0) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Se AGREGO con exito");
            nombre = "";
            valor = "";
            descripcion = "";

        } else if (respuesta == 1) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Hubo un error");
        } else {
            resError.evaluarRespuesta(respuesta);
            System.out.println("La operacion fallo");
        }
        
        parametro = ParametroModel.traerParametros();

    }

    public void editarParametro(int id, String nombre, String valor, String descripcion) {
        param.setId(id);
        param.setNombre(nombre);
        param.setValor(valor);
        param.setDescripcion(descripcion);
        respuesta = crud.editarRegistroParam(param);
        if (respuesta == 0) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Se edito con exito");

        } else if (respuesta == 1) {
            resError.evaluarRespuesta(respuesta);
        } else {
            resError.evaluarRespuesta(respuesta);
        }
    }

    public void borrarParametro(int id) {

        respuesta = crud.borrarRegistroParam(id);
        if (respuesta == 0) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Se borro con exito");

        } else if (respuesta == 1) {
            resError.evaluarRespuesta(respuesta);
            System.out.println("Hubo un error");
        } else {
            resError.evaluarRespuesta(respuesta);
            System.out.println("La operacion fallo");
        }
        parametro = ParametroModel.traerParametros();
    }

    //<editor-fold defaultstate="collapsed" desc="g">
    public List<Parametro> getParametros() {
        return parametro;
    }
    
    public void setParametros(List<Parametro> parametro) {
        this.parametro = parametro;
    }
    
    public List<Parametro> getParametrosFiltrados() {
        return parametroFiltrado;
    }
    
    public void setParametrosFiltrados(List<Parametro> parametroFiltrado) {
        this.parametroFiltrado = parametroFiltrado;
    }
    
    /**
     * @return the crud
     */
    public CrudModel getCrud() {
        return crud;
    }
    
    /**
     * @param crud the crud to set
     */
    public void setCrud(CrudModel crud) {
        this.crud = crud;
    }
    
    /**
     * @return the resError
     */
    public Respuesta getResError() {
        return resError;
    }
    
    /**
     * @param resError the resError to set
     */
    public void setResError(Respuesta resError) {
        this.resError = resError;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the respuesta
     */
    public int getRespuesta() {
        return respuesta;
    }
    
    /**
     * @param respuesta the respuesta to set
     */
    public void setRespuesta(int respuesta) {
        this.respuesta = respuesta;
    }
    
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }
    
    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }
    
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
//</editor-fold>
}
