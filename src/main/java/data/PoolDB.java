/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Blueweb
 */
public class PoolDB {
     public PoolDB() {
    }

    public static Connection getConnection(String nName)
	            throws SQLException, NamingException {
	        InitialContext cxt = new InitialContext();
	        DataSource     ds  = null;

	        if (cxt == null) {
	            throw new SQLException("No existe el contexto");
	        } else {

	            try{
	                ds = (DataSource) cxt.lookup("java:comp/env/jdbc/Pool" +nName);
	            } catch (Exception ex){
	                ds = (DataSource) cxt.lookup("jdbc/Pool" +nName);
	            }

	        }

	        if (ds == null) {
	            throw new SQLException("Origen de Datos " + nName + " no Encontrado!");
	        } else {
	            Connection conn = ds.getConnection();
	            conn.setAutoCommit(true);
	            return conn;
	        }
    }
}
