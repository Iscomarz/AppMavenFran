
package models;

import data.PoolDB;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import objetos.Usuario;
import sesiones.Sesion;
//import sesiones.Sesion;

/**
 *
 * @author Blueweb
 */
public class UsuarioModel implements Serializable {

    public int validaUsuario(Usuario usu)  {
        int resp = 0;

        try {

            Connection conexion = PoolDB.getConnection("Activa");

            PreparedStatement stmt = conexion.prepareStatement("Select ID_USUARIO, "
                    + "NOMBRE_USUARIO from S_USUARIOS where USUARIO = ? and "
                    + "PASSWORD = ? ");
            //Pasarle los parametros
            stmt.setString(1, usu.getUsuario());
            stmt.setString(2, usu.getPassword());

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                resp = resp + 1;
                //usu.setUsuario(res.getString("USUARIO"));
                //usu.setPassword(res.getString("PASSWORD"));
                
                HttpSession session = Sesion.getSession();
                session.setAttribute("username", res.getString("NOMBRE_USUARIO")); 
                session.setAttribute("userid", res.getString("ID_USUARIO"));
                
                
                session.setMaxInactiveInterval(30*60);
                
                
                System.out.println(Sesion.getUserName());
                System.out.println(Sesion.getUserId());
                System.out.println(session.getCreationTime());
            }
            

        }catch (SQLException ex) {
            return 0;

        } catch (NamingException ex) {
            Logger.getLogger(UsuarioModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (resp == 1) {

                return 1;

            } else {
                return 0;
            }
  }
}
    
