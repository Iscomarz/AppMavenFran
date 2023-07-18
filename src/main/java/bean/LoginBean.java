
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;
import sesiones.Sesion;
import bean.IdleMonitor;
import com.blueweb.controller.SUsuariosJpaController;
import com.blueweb.entity.SUsuarios;
import java.io.Serializable;
import java.util.Date;
//import sesiones.Sesion;

/**
 *
 * @author Blueweb
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    
    private IdleMonitor idlMon = new IdleMonitor();
    
    private SUsuarios usuarios = new SUsuarios();
    private SUsuariosJpaController SUController = new SUsuariosJpaController();
    private SUsuarios  usuarioActual = new SUsuarios();

    public String validarLogin()  {
        
        usuarios.setActivo(true);
        usuarios.setFechaAlta(new Date());
       
        usuarioActual =(SUController.findSUsuariosByUsuarioAndPassword(usuarios));

        return "index";
    }
    
    public SUsuarios retornaUsuarioActual(){
        usuarios.setUsuario(Sesion.getUserName());
        usuarioActual =(SUController.findSUsuariosByUsuario(usuarios));
        usuarioActual.setIdPerfil(null);
        return usuarioActual;
    }
    
        public SUsuarios retornaUsuarioActualEditar(){
        usuarios.setUsuario(Sesion.getUserName());
        usuarioActual =(SUController.findSUsuariosByUsuario(usuarios));

        return usuarioActual;
    }

    public void logOut()  {

        HttpSession session = Sesion.getSession();
        if (session != null) {
            session.invalidate();  
            System.out.println("Cerro sesion");
            
            idlMon.logoutListener();
        }   
    }


  
    
    public SUsuarios getUsuarios(){
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(SUsuarios usuarios) {
        this.usuarios = usuarios;
    }

    /**
     * @return the SUController
     */
    public SUsuariosJpaController getSUController() {
        return SUController;
    }

    /**
     * @param SUController the SUController to set
     */
    public void setSUController(SUsuariosJpaController SUController) {
        this.SUController = SUController;
    }

    /**
     * @return the usuarioActual
     */
    public Object getUsuarioActual() {
        return usuarioActual;
    }

    /**
     * @param usuarioActual the usuarioActual to set
     */
    public void setUsuarioActual(SUsuarios usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    /**
     * @return the usuarioActual
     */


}
