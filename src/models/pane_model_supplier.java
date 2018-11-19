/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author VICTOR MANUEL ARANDA
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import models.modelMain;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.modelMain;

public class pane_model_supplier extends Conexion {
    
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    
    private String nombre;
    private String calle;
    private String telefono;
    private String cp;
    private String ciudad;
    private String estado;
    modelMain modelmain;
    
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    private void setValues(){
        try {
            nombre = rs.getString("nombre");
            calle = rs.getString("calle");
            cp = rs.getString("cp");
            telefono = rs.getString("telefono");
            ciudad = rs.getString("ciudad");
            estado = rs.getString("estado");
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error model supp");
        }
        
    }
    
    public void registerSupplier(){
        Connection con =  getConexion();
        try {
           
            pst.executeQuery("INSERT INTO proveedores(nombre, calle, cp, telefono, ciudad, estado) VALUES (?,?,?,?,?,?))");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error registrar" + ex.getMessage());
        }
    }
    
    public void modifySupplier(){
        Connection con =  getConexion();
        try{
           
            pst.executeQuery("UPDATE proveedores SET nombre=? , calle =?, cp =?, telefono=?, ciudad=?, estado=?");
        } catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error modificar" + ex.getMessage());
        }
    }
    
    public void deleteSupplier(){
        Connection con =  getConexion();
        try{
            
            pst.executeQuery("DELETE * FROM proveedores WHERE id_prov=?");
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "Error borrar" + ex.getMessage());
        }
    }
}
