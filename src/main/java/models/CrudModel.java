
package models;

import data.PoolDB;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import objetos.Ciudad;
import objetos.Parametro;
import sesiones.Sesion;

/**
 *
 * @author Blueweb
 */
public class CrudModel implements Serializable {

    public int agregarRegistro(Ciudad city) {
        int rs = 1;
        try {
            Connection con = PoolDB.getConnection("Activa");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO C_CIUDAD"
                    + " (DESCRIPCION, CODIGO, LADA, ID_USUARIO) VALUES (?, ?, ?, ?)");

            stmt.setString(1, city.getDescripcion());
            stmt.setString(2, city.getCodigo());
            stmt.setInt(3, city.getLada());
            stmt.setInt(4, Integer.parseInt(Sesion.getUserId()));

            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Se AGREGO con exito");
                rs = 0;
            } else {
                System.out.println("NO se AGREGO con exito");
                rs = 1;
            }
            //stmtE.close();
            //con.close();
        } catch (SQLException ex) {
            rs =-1;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            rs = -2;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
            rs = -3;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs < 0) {
            return -1;
        } else if (rs > 0) {
            return 1;
        } else {
            return 0;
        }

    }

    public int editarRegistro(Ciudad city)  {
        int rs = 1;
        try {
            Connection con = PoolDB.getConnection("Activa");

            PreparedStatement stmtE = con.prepareStatement("UPDATE C_CIUDAD SET"
                    + " DESCRIPCION = ?, CODIGO = ?, LADA = ? "
                    + "WHERE ID_CIUDAD = ?; ");

            stmtE.setString(1, city.getDescripcion());
            stmtE.setString(2, city.getCodigo());
            stmtE.setInt(3, city.getLada());
            stmtE.setInt(4, city.getId());
            int rowsInserted = stmtE.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Se EDITO con exito");
                rs = 0;
            } else {
                System.out.println("NO se EDITO con exito");
                rs = 1;
            }
            //stmtE.close();
            //con.close();
        } catch (SQLException ex) {
            rs =-1;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            rs = -2;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
            rs = -3;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs < 0) {
            return -1;
        } else if (rs > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int borrarRegistro(int id) {
        int rs = 1;
        try {
            Connection con = PoolDB.getConnection("Activa");

            PreparedStatement stmtB = con.prepareStatement("DELETE FROM C_CIUDAD"
                    + " WHERE ID_CIUDAD = ?");

            stmtB.setInt(1, id);
            int rowsInserted = stmtB.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Se BORRO con exito");
                rs = 0;
            } else {
                System.out.println("NO se BORRO con exito");
                rs = 1;
            }
            //stmtE.close();
            //con.close();
        } catch (SQLException ex) {
            rs =-1;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            rs = -2;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
            rs = -3;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs < 0) {
            return -1;
        } else if (rs > 0) {
            return 1;
        } else {
            return 0;
        }

    }
    
    public int agregarRegistroParam(Parametro param) {
        int rs = 1;
        try {
            Connection con = PoolDB.getConnection("Activa");
            PreparedStatement stmt = con.prepareStatement("INSERT INTO C_PARAMETRO"
                    + " (NOMBRE, VALOR, DESCRIPCION, ID_USUARIO) VALUES (?, ?, ?, ?)");

            stmt.setString(1, param.getNombre());
            stmt.setString(2, param.getValor());
            stmt.setString(3, param.getDescripcion());
            stmt.setInt(4, param.getId_usuario());
            
            int rowsInserted = stmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Se AGREGO con exito");
                rs = 0;
            } else {
                System.out.println("NO se AGREGO con exito");
                rs = 1;
            }
            //stmtE.close();
            //con.close();
        } catch (SQLException ex) {
            rs =-1;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            rs = -2;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
            rs = -3;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs < 0) {
            return -1;
        } else if (rs > 0) {
            return 1;
        } else {
            return 0;
        }

    }

    public int editarRegistroParam(Parametro param)  {
        int rs = 1;
        try {
            Connection con = PoolDB.getConnection("Activa");

            PreparedStatement stmtE = con.prepareStatement("UPDATE C_PARAMETRO SET"
                    + " NOMBRE = ?, VALOR = ?, DESCRIPCION = ? "
                    + "WHERE ID = ?; ");

            stmtE.setString(1, param.getNombre());
            stmtE.setString(2, param.getValor());
            stmtE.setString(3, param.getDescripcion());
            stmtE.setInt(4, param.getId());
            int rowsInserted = stmtE.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Se EDITO con exito");
                rs = 0;
            } else {
                System.out.println("NO se EDITO con exito");
                rs = 1;
            }
            //stmtE.close();
            //con.close();
        } catch (SQLException ex) {
            rs =-1;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            rs = -2;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
            rs = -3;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs < 0) {
            return -1;
        } else if (rs > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public int borrarRegistroParam(int id) {
        int rs = 1;
        try {
            Connection con = PoolDB.getConnection("Activa");

            PreparedStatement stmtB = con.prepareStatement("DELETE FROM C_PARAMETRO"
                    + " WHERE ID = ?");

            stmtB.setInt(1, id);
            int rowsInserted = stmtB.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Se BORRO con exito");
                rs = 0;
            } else {
                System.out.println("NO se BORRO con exito");
                rs = 1;
            }
            //stmtE.close();
            //con.close();
        } catch (SQLException ex) {
            rs =-1;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);

        } catch (NamingException ex) {
            rs = -2;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex){
            rs = -3;
            Logger.getLogger(CrudModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (rs < 0) {
            return -1;
        } else if (rs > 0) {
            return 1;
        } else {
            return 0;
        }

    }
}
