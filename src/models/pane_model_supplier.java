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
import bd.BD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import views.pane_view_supplier;

public class pane_model_supplier extends Conexion {
    
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    pane_view_supplier view_supplier = new pane_view_supplier();
    
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
        String insert = ("INSERT INTO proveedores (nombre_prov, telefono_prov, calle_prov, colonia_prov, ciudad_prov, estado_prov) VALUES (?,?,?,?,?,?) WHERE id_prov =?");
        BD DataBase = new BD();
        Connection con = DataBase.getConnection();
        
        try {
            pst = (PreparedStatement) con.prepareStatement(insert); 
            pst.setString(1, view_supplier.jtf_name.getText());
            pst.setString(2, view_supplier.jtf_phone.getText());
            pst.setString(3, view_supplier.jtf_street.getText());
            pst.setString(3, view_supplier.jtf_colony.getText());
            pst.setString(3, view_supplier.jtf_city.getText());
            pst.setString(3, view_supplier.jtf_state.getText());
            if (view_supplier.jtf_name.getText().isEmpty() || 
                    view_supplier.jtf_phone.getText().isEmpty() || 
                    view_supplier.jtf_street.getText().isEmpty() || 
                    view_supplier.jtf_colony.getText().isEmpty() || 
                    view_supplier.jtf_city.getText().isEmpty() || 
                    view_supplier.jtf_state.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Los campos no deben quedar vacíos");
            }else {
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se insertó el registro");
                view_supplier.jtf_name.setText("");
                view_supplier.jtf_phone.setText("");
                view_supplier.jtf_street.setText("");
                view_supplier.jtf_colony.setText("");
                view_supplier.jtf_city.setText("");
                view_supplier.jtf_state.setText("");
                view_supplier.jtf_search.setText("");
                view_supplier.jb_delete.setEnabled(true);
                view_supplier.jb_modify.setEnabled(true);
                view_supplier.jb_new.setEnabled(true);
                view_supplier.jb_search.setEnabled(true);
            }
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudo insertar el registro");
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
