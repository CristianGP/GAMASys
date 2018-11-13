/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author Isaías
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.pane_view_supplier;
import models.pane_model_supplier;
import bd.BD;
import java.sql.PreparedStatement;

public class pane_controller_supplier implements ActionListener{

    private final pane_view_supplier view_supplier;
    private final pane_model_supplier model_supplier;

    public pane_controller_supplier(pane_view_supplier view_supplier, pane_model_supplier model_supplier) {
        this.view_supplier = view_supplier;
        this.model_supplier = model_supplier;
        
        view_supplier.jb_add.addActionListener(this);
        view_supplier.jb_delete.addActionListener(this);
        view_supplier.jb_modify.addActionListener(this);
        view_supplier.jb_new.addActionListener(this);
        view_supplier.jb_search.addActionListener(this);
        initComponents();
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view_supplier.jb_add){
            addSupplier();
        }
        else if (e.getSource() == view_supplier.jb_delete){
            deleteSupplier();
        }
        else if (e.getSource() == view_supplier.jb_modify){
            modifySupplier();
        }
        else if (e.getSource() == view_supplier.jb_new){
            newSupplier();
        }
        else if (e.getSource() == view_supplier.jb_search){
            searchSupplier();
        }
    }
    /*
        variables para la conexion y las herramientas necesarias para sql.
    */
    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement pst;
    
    public void conectarDB() {
        try {
            conexion = DriverManager.getConnection("");
            st = conexion.createStatement();
            String sql = "SELECT * FROM proveedores";
            System.out.println(sql);
            rs = st.executeQuery(sql);
            rs.next();
           
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error ModelSupplier 001: " + err.getMessage());
        }
    }
    
    /*
        Método para inicializar los componentes.
    */
    private void initComponents() {
        view_supplier.setTitle("Proveedores");
        view_supplier.setLocationRelativeTo(null);
        view_supplier.setVisible(true);
    }
    
    /*
    Método agregar un proveedor, donde se hace la sentencia de sql para insertar registros
    dentro de la tabla proveedores de la base de datos, validando si están vacíos que le 
    muestre un mensaje diciendo que los campos no pueden quedar vacíos. 
    Si los capos no están vacíos entonces mostrará un mensaje que el registro ha sido insertado.
    */
    private void addSupplier() {
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
    /*
        Método modificar un proveedor, donde se hace la sentencia de sql para modificar registros
        dentro de la tabla proveedores de la base de datos, validando si están vacíos que le 
        muestre un mensaje diciendo que los campos no pueden quedar vacíos. 
        Si los capos no están vacíos entonces mostrará un mensaje que el registro ha sido insertado.
    */
    private void modifySupplier() {
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
                cargar();
            }
        }catch(SQLException ex){ 
            JOptionPane.showMessageDialog(null, "No se pudo actualizar");
        }
    }
    /*
        Método para limpiar las cajas de texto, así como deshabilitar los botones innecesarios para la acción.
    */
    private void newSupplier() {
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
    /*
        Método para buscar un proveedor, toma lo que esté seleccionado en el ComboBox,
        después lee lo que está en la caja de texto y lo muestra en la tabla
    */
    private void searchSupplier() {
        
    }
    /*
        Método para eliminar al proveedor selccionado en la tabla.
    */
    private void deleteSupplier() {
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
                cargar();
            } else {
                
            }           
        } catch(SQLException ex){ 
            JOptionPane.showMessageDialog(null, "No se pudo eliminar el registro");
        }
    }
    
    DefaultTableModel model;
    public void cargar(){
        String [] titulos={"Nombre","Teléfono","Calle","Colonia","Ciudad","Estado"};
        String [] registros = new String[7];
        String sql ="SELECT * FROM proveedores";
        
        model = new DefaultTableModel(null, titulos);
        BD DataBase = new BD();
        Connection con = DataBase.getConnection();                
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0]=rs.getString("nombre_proveedor");
                registros[1]=rs.getString("telefono");
                registros[2]=rs.getString("calle");
                registros[3]=rs.getString("colonia");
                registros[4]=rs.getString("ciudad");
                registros[5]=rs.getString("estado");
                model.addRow(registros);
                
            }
            view_supplier.jt_supplier.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo importar la tabla");
        }
    }
}
