/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import bean.ParametroBean;
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
import objetos.Parametro;

/**
 *
 * @author Blueweb
 */
public class ParametroModel implements Serializable{
    
    public static List<Parametro> traerParametros(){
       List<Parametro> lista = new ArrayList<>();
       Parametro obj = null;
       
       try {
            //PoolDB pool = new PoolDB();
            Connection conexion = PoolDB.getConnection("Activa");
            PreparedStatement stmt = conexion.prepareStatement("Select ID, "
                    + "NOMBRE, VALOR, DESCRIPCION from C_PARAMETRO");

            ResultSet res = stmt.executeQuery();

            while(res.next()){
                obj = new Parametro();

                obj.setId(res.getInt("ID"));
                obj.setNombre(res.getString("NOMBRE"));
                obj.setValor(res.getString("VALOR"));
                obj.setDescripcion(res.getString("DESCRIPCION"));

                lista.add(obj);

            }

            res.close();
            stmt.close();
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ParametroBean.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ParametroBean.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       return lista;
    
}
}
