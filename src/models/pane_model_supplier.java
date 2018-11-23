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
    
    /*
    Método agregar un proveedor, donde se hace la sentencia de sql para insertar registros
    dentro de la tabla proveedores de la base de datos, validando si están vacíos que le 
    muestre un mensaje diciendo que los campos no pueden quedar vacíos. 
    Si los capos no están vacíos entonces mostrará un mensaje que el registro ha sido insertado.
    */
    public void registerSupplier(){
        String insert = ("INSERT INTO proveedores (nombre_prov, telefono_prov, calle_prov, colonia_prov, ciudad_prov, estado_prov) VALUES (?,?,?,?,?,?);");
        BD DataBase = new BD();
        Connection con = DataBase.getConnection();
        
        try {
            pst = (PreparedStatement) con.prepareStatement(insert); 
            pst.setString(1, view_supplier.jtf_name.getText());
            pst.setString(2, view_supplier.jtf_phone.getText());
            pst.setString(3, view_supplier.jtf_street.getText());
            pst.setString(4, view_supplier.jtf_colony.getText());
            pst.setString(5, view_supplier.jtf_city.getText());
            pst.setString(6, view_supplier.jtf_state.getText());
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
    
    /*
        Método modificar un proveedor, donde se hace la sentencia de sql para modificar registros
        dentro de la tabla proveedores de la base de datos, validando si están vacíos que le 
        muestre un mensaje diciendo que los campos no pueden quedar vacíos. 
        Si los capos no están vacíos entonces mostrará un mensaje que el registro ha sido insertado.
    */
    public void modifySupplier(){
        String update = ("UPDATE proveedores SET nombre_prov=?, telefono=?, calle=?, colonia=?, ciudad=?, estado=? WHERE id_prov=?");
        BD DataBase = new BD();
        Connection con = DataBase.getConnection();
        try {
            pst = (PreparedStatement) con.prepareStatement(update); 
            pst.setString(1, view_supplier.jtf_name.getText());
            pst.setString(2, view_supplier.jtf_phone.getText());
            pst.setString(3, view_supplier.jtf_street.getText());
            pst.setString(4, view_supplier.jtf_colony.getText());
            pst.setString(5, view_supplier.jtf_city.getText());
            pst.setString(6, view_supplier.jtf_state.getText());
            pst.setString(6, rs.getString("id_prov"));
            if (view_supplier.jtf_name.getText().isEmpty() || 
                view_supplier.jtf_phone.getText().isEmpty() || 
                view_supplier.jtf_street.getText().isEmpty() || 
                view_supplier.jtf_colony.getText().isEmpty() || 
                view_supplier.jtf_city.getText().isEmpty() || 
                view_supplier.jtf_state.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Los campos no deben quedar vacíos");    
            }else{
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se actualizó el registro");
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
        }catch(SQLException ex){ 
            JOptionPane.showMessageDialog(null, "No se pudo actualizar");
        }
    }
    
    /*
        Método para eliminar al proveedor selccionado en la tabla.
    */
    public void deleteSupplier(){
        try {            
            int eliminar = JOptionPane.showConfirmDialog(null, "Quieres eliminar este registro?", "Eliminar Registro", JOptionPane.YES_NO_OPTION);
            if(eliminar == 0){  
                String delete = ("DELETE FROM proveedores WHERE id_prov=?");
                BD DataBase = new BD();
                Connection con = DataBase.getConnection();
                pst = (PreparedStatement) con.prepareStatement(delete);
                pst.setString(1, rs.getString("id_prov"));
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se eliminó el registro");
            } else {
                
            }           
        } catch(SQLException ex){ 
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
        }
    }
    
    /*
        Método para limpiar las cajas de texto, así como deshabilitar los botones innecesarios para la acción.
    */
    public void newSupplier() {
        view_supplier.jb_delete.setEnabled(false);
        view_supplier.jb_modify.setEnabled(false);
        view_supplier.jb_search.setEnabled(false);
        view_supplier.jb_new.setEnabled(false);
        view_supplier.jtf_name.setText("");
        view_supplier.jtf_city.setText("");
        view_supplier.jtf_colony.setText("");
        view_supplier.jtf_phone.setText("");
        view_supplier.jtf_state.setText("");
        view_supplier.jtf_street.setText("");
        view_supplier.jtf_search.setText("");
    }
}
