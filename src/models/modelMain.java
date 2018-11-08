/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VICTOR MANUEL ARANDA
 */
public class modelMain {
   private final String base = "sistematienda";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/"+base;
    Connection con = null;
    
    
     public Connection conectarDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            try {
                con =  (Connection) DriverManager.getConnection(url, user, password);
                System.out.println("CONEXION ESTABLECIDA A LA BASE DE DATOS");
            } catch (SQLException ex) {
                Logger.getLogger(modelMain.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("ERROR AL CONECTAR A LA BASE DE DATOS"+ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(modelMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
