/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author VICTOR MANUEL ARANDA
 */
public class modelMain {


    private final String base = "Ferreteria";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    Connection con = null;

    public Connection conectarDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con = (Connection) DriverManager.getConnection(url, user, password);
                System.out.println("CONEXION ESTABLECIDA A LA BASE DE DATOS");
            } catch (SQLException ex) {
                Logger.getLogger(modelMain.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR AL CONECTAR A LA BASE DE DATOS" + ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(modelMain.class.getName()).log(Level.SEVERE, null, ex);
=======
    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    
    
     public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("");
            st = conexion.createStatement();
            String sql = "SELECT * FROM empleados;";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            rs.next();
           
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelAgenda 001: " + err.getMessage());
>>>>>>> 99522d00be31e4b89a59980649de2d7e3153c02d
        }
    }

<<<<<<< HEAD
=======
   
   
>>>>>>> 99522d00be31e4b89a59980649de2d7e3153c02d
}
