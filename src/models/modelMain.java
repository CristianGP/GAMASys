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
        }
    }

   
   
}
