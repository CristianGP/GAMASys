/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Isaías
 */
public class BD {
    
    private String db = "ferreteria";
    private String url = "jdbc:mysql://pi1509.ddns.net:3306/" + db;
    private String user = "Gamasys";
    private String pass = "gamasys";
    Connection con = null;
    
    public BD(){
        try{
            con = DriverManager.getConnection(url, user, pass);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Mensaje 001 " +e);         
        }
    }
    public Connection getConnection(){
        return con;
    }
    
    public void desconectar(){
        con = null;
    }
}
