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
import models.Conexion;
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
            System.out.println(e);         
        }
    }
    public Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con =  (com.mysql.jdbc.Connection) DriverManager.getConnection(url, user, pass);
                JOptionPane.showMessageDialog(null, "CONEXION ESTABLECIDA A LA BASE DE DATOS");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "ERROR AL CONECTAR A LA BASE DE DATOS" +ex);
            }
        
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }return con;
        
    }
    
    public void desconectar(){
        con = null;
    }
}
