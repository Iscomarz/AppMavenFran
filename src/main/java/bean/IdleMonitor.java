
package bean;

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
public class IdleMonitor implements Serializable {
     public void onIdle() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Inactividad.", "El usuario esta inactivo"));
    }

    public void onActive() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Actividad detectada", "El usuario esta activo"));
    }
    
    public void welcomeListener() {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Welcome Back",
			"Continua tu trabajo."));

	}

	public void logoutListener() {
	    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
			"Saliste de la sesion!",
			"Gracias por usar nuestra aplicacion"));
		
	}
}
