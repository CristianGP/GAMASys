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
}
