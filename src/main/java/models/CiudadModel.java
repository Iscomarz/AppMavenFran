package models;

import bean.CiudadBean;
import data.PoolDB;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import objetos.Ciudad;

/**
 *
 * @author Blueweb
 */
public class CiudadModel implements Serializable {

    public static List<Ciudad> traerCiudades() {
        List<Ciudad> lista = new ArrayList<>();
        Ciudad obj = null;

        try {
            //PoolDB pool = new PoolDB();
            Connection conexion = PoolDB.getConnection("Activa");
            PreparedStatement stmt = conexion.prepareStatement("Select ID_CIUDAD, "
                    + "DESCRIPCION, CODIGO, LADA from c_ciudad");

            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                obj = new Ciudad();

                obj.setId(res.getInt("ID_CIUDAD"));
                obj.setDescripcion(res.getString("DESCRIPCION"));
                obj.setCodigo(res.getString("CODIGO"));
                obj.setLada(res.getInt("LADA"));

                lista.add(obj);
            }
            res.close();
            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(CiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }
   

}


   
   

