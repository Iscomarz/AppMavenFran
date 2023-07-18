
package objetos;

import java.io.Serializable;
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
public class Respuesta implements Serializable {

    private int id;
    private String message;

    public void evaluarRespuesta(int id) {
        if (id == 0) {
            message = "La operacion fue exitosa";
            showInfo();
        } else if (id > 1) {
            message = "Advertencia la operacion talvez fallo";
            showWarn();
        } else {
            message = "Error la operacion fallo";
            showError();
        }
        //return message;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }
    
    public void cancel() {
        addMessage(FacesMessage.SEVERITY_WARN, "Warn Message", "Cancelado");
    }

    public void showInfo() {
        addMessage(FacesMessage.SEVERITY_INFO, "Info Message", message);
    }

    public void showWarn() {
        addMessage(FacesMessage.SEVERITY_WARN, "Warn Message", message);
    }

    public void showError() {
        addMessage(FacesMessage.SEVERITY_ERROR, "Error Message", message);
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
