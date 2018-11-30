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

public class pane_model_supplier {
    
    private Statement st;
    private PreparedStatement pst;
    private ResultSet rs;
    pane_view_supplier view_supplier = new pane_view_supplier();
    
    
    private String ID;

    private String nombre;
    private String calle;
    private String telefono;
    private String colonia;
    private String ciudad;
    private String estado;
    modelMain modelmain;
    /*
        Getter y Setter de cada variable
    */
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

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

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
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
            pst.setString(1, this.getNombre());
            pst.setString(2, this.getTelefono());
            pst.setString(3, this.getCalle());
            pst.setString(4, this.getColonia());
            pst.setString(5, this.getCiudad());
            pst.setString(6, this.getEstado());
            if (this.getNombre().isEmpty() || 
                this.getTelefono().isEmpty() || 
                this.getCalle().isEmpty() || 
                this.getColonia().isEmpty() || 
                this.getCiudad().isEmpty() || 
                this.getEstado().isEmpty()){
            JOptionPane.showMessageDialog(null, "Los campos no deben quedar vacíos");
            }else{
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se insertó el registro");
            }
            }catch (SQLException ex){
                JOptionPane.showMessageDialog(null, "No se pudo insertar el registro" +ex);
            }catch (NullPointerException err) {
                System.err.println("NullPointer:  " + err.getMessage());
            }
        }
    
    /*
        Método modificar un proveedor, donde se hace la sentencia de sql para modificar registros
        dentro de la tabla proveedores de la base de datos, validando si están vacíos que le 
        muestre un mensaje diciendo que los campos no pueden quedar vacíos. 
        Si los capos no están vacíos entonces mostrará un mensaje que el registro ha sido insertado.
    */
    public void modifySupplier(){
        String update = ("UPDATE proveedores SET nombre_prov = ?, telefono_prov = ?, calle_prov = ?, colonia_prov = ?, ciudad_prov = ?, estado_prov = ? WHERE id_prov = ? ;");
        BD DataBase = new BD();
        Connection con = DataBase.getConnection();
        try {
            pst = (PreparedStatement) con.prepareStatement(update);
            pst.setString(1, this.getNombre());
            pst.setString(2, this.getTelefono());
            pst.setString(3, this.getCalle());
            pst.setString(4, this.getColonia());
            pst.setString(5, this.getCiudad());
            pst.setString(6, this.getEstado());
            pst.setString(7, this.getID());
            if (this.getNombre().isEmpty() || 
                this.getTelefono().isEmpty() || 
                this.getCalle().isEmpty() || 
                this.getColonia().isEmpty() || 
                this.getCiudad().isEmpty() || 
                this.getEstado().isEmpty()){
            JOptionPane.showMessageDialog(null, "Los campos no deben quedar vacíos");    
            }else{
                pst.executeUpdate();
                JOptionPane.showMessageDialog(null, "Se actualizó el registro");
            }
        }catch(SQLException ex){ 
            JOptionPane.showMessageDialog(null, "No se pudo actualizar");
        }catch (NullPointerException err) {
            JOptionPane.showMessageDialog(null, "NullPointer:  " + err.getMessage());
        }
    }
    
    /*
        Método para eliminar al proveedor selccionado en la tabla.
    */
    public void deleteSupplier(){          
        int eliminar = JOptionPane.showConfirmDialog(null, "Quieres eliminar este registro?", "Eliminar Registro", JOptionPane.YES_NO_OPTION);
        if(eliminar == 0){  
            String delete = ("DELETE FROM proveedores WHERE id_prov = ? ;");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            try{
                pst = (PreparedStatement) con.prepareStatement(delete);
                pst.setString(1, this.getID());
                System.out.println("Eliminando a: " + this.getID());
                if (this.getID() == "0"){
                    JOptionPane.showMessageDialog(null, "No se puede eliminar este registro");
                }else{
                    pst.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Se eliminó el registro");
                }
            }catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "No se pudo actualizar" + ex.getMessage());
            }
        }                         
    }
}
