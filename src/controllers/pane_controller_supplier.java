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
import models.*;

public class pane_controller_supplier implements ActionListener{

    pane_view_supplier view_supplier = new pane_view_supplier();
    pane_model_supplier model_supplier = new pane_model_supplier();

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
            model_supplier.registerSupplier();
            cargar();
        }
        else if (e.getSource() == view_supplier.jb_delete){
            model_supplier.deleteSupplier();
            cargar();
        }
        else if (e.getSource() == view_supplier.jb_modify){
            model_supplier.modifySupplier();
            cargar();
        }
        else if (e.getSource() == view_supplier.jb_new){
            model_supplier.newSupplier();
        }
        else if (e.getSource() == view_supplier.jb_search){
           if (view_supplier.jcb_search.getSelectedItem() == "Nombre"){
               searchByName();
           }            
           else if (view_supplier.jcb_search.getSelectedItem() == "Calle"){
               searchByStreet();
           }
           else if (view_supplier.jcb_search.getSelectedItem() == "Código Postal"){
               searchByColony();
           }
           else if (view_supplier.jcb_search.getSelectedItem() == "Teléfono"){
               searchByPhone();
           }
           else if (view_supplier.jcb_search.getSelectedItem() == "Estado"){
               searchByState();
           }
           else if (view_supplier.jcb_search.getSelectedItem() == "Ciudad"){
               searchByCity();
           }
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
   
    public void cargar(){
        DefaultTableModel model;
        String [] titulos={"Nombre","Teléfono","Calle","Colonia","Ciudad","Estado"};
        String [] registros = new String[6];
        String sql ="SELECT * FROM proveedores";
        
        model = new DefaultTableModel(null, titulos);
        BD DataBase = new BD();
        Connection con = DataBase.getConnection();                
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0]=rs.getString("nombre_prov");
                registros[1]=rs.getString("telefono_prov");
                registros[2]=rs.getString("calle_prov");
                registros[3]=rs.getString("colonia_prov");
                registros[4]=rs.getString("ciudad_prov");
                registros[5]=rs.getString("estado_prov");
                model.addRow(registros);
                
            }
            view_supplier.jt_supplier.setModel(model);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo importar la tabla");
        }
    }
    /*
        Método para buscar un proveedor por nombre, hace una sentencia de sql y muestra los datos en
        la tabla
    */
    private void searchByName() {
        try{
            String search_by_name = ("SELECT * FROM proveedores WHERE nombre_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_name);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    /*
        Método para buscar un proveedor por calle, hace una sentencia de sql y muestra los datos en
        la tabla
    */
    private void searchByStreet() {
        try{
            String search_by_street = ("SELECT * FROM proveedores WHERE calle_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_street);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    /*
        Método para buscar un proveedor por código postal, hace una sentencia de sql y muestra los datos en
        la tabla
    */
    private void searchByColony() {
        try{
            String search_by_colony = ("SELECT * FROM proveedores WHERE colonia_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_colony);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    /*
        Método para buscar un proveedor por teléfono, hace una sentencia de sql y muestra los datos en
        la tabla
    */
    private void searchByPhone() {
        try{
            String search_by_phone = ("SELECT * FROM proveedores WHERE telefono_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_phone);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    /*
        Método para buscar un proveedor por estado, hace una sentencia de sql y muestra los datos en
        la tabla
    */
    private void searchByState() {
        try{
            String search_by_state = ("SELECT * FROM proveedores WHERE estado_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_state);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    /*
        Método para buscar un proveedor por ciudad, hace una sentencia de sql y muestra los datos en
        la tabla
    */
    private void searchByCity() {
        try{
            String search_by_city = ("SELECT * FROM proveedores WHERE ciudad_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_city);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
}
