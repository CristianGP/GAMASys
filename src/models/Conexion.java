<<<<<<< HEAD

package models;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexion {
    private final String base = "Ferreteria";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/"+base;
    Connection con = null;
    
    public Connection getConexion(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con =  (Connection) DriverManager.getConnection(url, user, password);
                System.out.println("CONEXION ESTABLECIDA A LA BASE DE DATOS");
            } catch (SQLException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR AL CONECTAR A LA BASE DE DATOS"+ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
=======
package models;
import java.sql.*;
/**
 *
 * @author ManuelAlonsoMH
 */
public class Conexion {
    
    public Conexion(){
    
    }
    
    public Connection getConexion(){
        Connection con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_Ferreteria","root","");
        } catch (Exception e) {
        }
        
        return con;
    }
>>>>>>> 99522d00be31e4b89a59980649de2d7e3153c02d
}
