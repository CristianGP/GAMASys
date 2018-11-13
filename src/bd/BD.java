/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;
import java.sql.*;
/**
 *
 * @author Isaías
 */
public class BD {

    private String db = "ferreteria";
    private String url = "130.100.24.160" + db;
    private String user = "root";
    private String pass = "";
    Connection con = null;
    
    public BD(){
        try{
            con = DriverManager.getConnection(url, user, pass);
        }catch(SQLException e){
            System.out.println(e);         
        }
    }
    public Connection getConnection(){
        return con;
    }
    public void desconectar(){
        con = null;
    }
}
