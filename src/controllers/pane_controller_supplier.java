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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.pane_view_supplier;
import models.pane_model_supplier;
import bd.BD;
import java.sql.PreparedStatement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.*;

public class pane_controller_supplier implements ActionListener {

    pane_view_supplier view_supplier = new pane_view_supplier();
    pane_model_supplier model_supplier = new pane_model_supplier();

    public pane_controller_supplier(pane_view_supplier view_supplier, pane_model_supplier model_supplier) {
        this.view_supplier = view_supplier;
        this.model_supplier = model_supplier;
        
        initComponents();
        view_supplier.jb_add.addActionListener(this);
        view_supplier.jb_delete.addActionListener(this);
        view_supplier.jb_modify.addActionListener(this);
        view_supplier.jb_new.addActionListener(this);
        view_supplier.jb_search.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view_supplier.jb_add) {
            /*
                Editando las variabes de getter y setter para el modelo y su método 
                para registrar al proveedor
             */
            model_supplier.setNombre(view_supplier.jtf_name.getText());
            model_supplier.setTelefono(view_supplier.jtf_phone.getText());
            model_supplier.setCalle(view_supplier.jtf_street.getText());
            model_supplier.setColonia(view_supplier.jtf_colony.getText());
            model_supplier.setCiudad(view_supplier.jtf_city.getText());
            model_supplier.setEstado(view_supplier.jtf_state.getText());
            model_supplier.registerSupplier();

            /*
                Modificando los elementos del view para habilitar los botones por si están deshabilitados
                y las cajas de texto por si se requiere agregar otro.
             */
            view_supplier.jtf_name.setText("");
            view_supplier.jtf_phone.setText("");
            view_supplier.jtf_street.setText("");
            view_supplier.jtf_colony.setText("");
            view_supplier.jtf_city.setText("");
            view_supplier.jtf_state.setText("");
            view_supplier.jtf_search.setText("");
            view_supplier.jtf_id_supplier.setText("0");
            view_supplier.jb_delete.setEnabled(true);
            view_supplier.jb_modify.setEnabled(true);
            view_supplier.jb_new.setEnabled(true);
            view_supplier.jb_search.setEnabled(true);
            cargar();

        } else if (e.getSource() == view_supplier.jb_delete) {
            /*
                Editando las variabes de getter y setter para el modelo y su método 
                para registrar al proveedor
             */
            model_supplier.setID(view_supplier.jtf_id_supplier.getText());
            model_supplier.deleteSupplier();
            /*
                Modificando los elementos del view para habilitar los botones por si están deshabilitados
                y las cajas de texto por si se requiere agregar otro.
             */
            view_supplier.jtf_name.setText("");
            view_supplier.jtf_phone.setText("");
            view_supplier.jtf_street.setText("");
            view_supplier.jtf_colony.setText("");
            view_supplier.jtf_city.setText("");
            view_supplier.jtf_state.setText("");
            view_supplier.jtf_search.setText("");
            view_supplier.jtf_id_supplier.setText("0");
            view_supplier.jb_delete.setEnabled(true);
            view_supplier.jb_modify.setEnabled(true);
            view_supplier.jb_new.setEnabled(true);
            view_supplier.jb_search.setEnabled(true);
            cargar();
        } else if (e.getSource() == view_supplier.jb_modify) {
            /*
                Editando las variabes de getter y setter para el modelo y su método 
                para modificar al proveedor
             */
            model_supplier.setNombre(view_supplier.jtf_name.getText());
            model_supplier.setTelefono(view_supplier.jtf_phone.getText());
            model_supplier.setCalle(view_supplier.jtf_street.getText());
            model_supplier.setColonia(view_supplier.jtf_colony.getText());
            model_supplier.setCiudad(view_supplier.jtf_city.getText());
            model_supplier.setEstado(view_supplier.jtf_state.getText());
            model_supplier.setID(view_supplier.jtf_id_supplier.getText());
            model_supplier.modifySupplier();

            /*
                Modificando los elementos del view para habilitar los botones por si están deshabilitados.
             */
            view_supplier.jb_delete.setEnabled(true);
            view_supplier.jb_modify.setEnabled(true);
            view_supplier.jb_new.setEnabled(true);
            view_supplier.jb_search.setEnabled(true);
            cargar();
        } else if (e.getSource() == view_supplier.jb_new) {
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
        } else if (e.getSource() == view_supplier.jb_search) {
            if (view_supplier.jcb_search.getSelectedItem() == "Nombre") {
                searchByName();
            } else if (view_supplier.jcb_search.getSelectedItem() == "Calle") {
                searchByStreet();
            } else if (view_supplier.jcb_search.getSelectedItem() == "Código Postal") {
                searchByColony();
            } else if (view_supplier.jcb_search.getSelectedItem() == "Teléfono") {
                searchByPhone();
            } else if (view_supplier.jcb_search.getSelectedItem() == "Estado") {
                searchByState();
            } else if (view_supplier.jcb_search.getSelectedItem() == "Ciudad") {
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

    /*
        Método para inicializar los componentes.
     */
    private void initComponents() {
        view_supplier.setVisible(true);
        cargar();
        setAction();
    }

    public void cargar() {
        DefaultTableModel model;
        String[] titulos = {"ID", "Nombre", "Teléfono", "Calle", "Colonia", "Ciudad", "Estado"};
        String[] registros = new String[7];
        String sql = "SELECT * FROM proveedores;";

        model = new DefaultTableModel(null, titulos);
        BD DataBase = new BD();
        Connection con = DataBase.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("id_prov");
                registros[1] = rs.getString("nombre_prov");
                registros[2] = rs.getString("telefono_prov");
                registros[3] = rs.getString("calle_prov");
                registros[4] = rs.getString("colonia_prov");
                registros[5] = rs.getString("ciudad_prov");
                registros[6] = rs.getString("estado_prov");
                model.addRow(registros);

            }
            view_supplier.jt_supplier.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo importar la tabla");
        }
    }

    public void jt_supplier_MouseClicked() {
        try {
            if (view_supplier.jt_supplier.getSelectedRow() != -1) {
                int i = view_supplier.jt_supplier.getSelectedRow();
                view_supplier.jtf_id_supplier.setText(view_supplier.jt_supplier.getValueAt(i, 0).toString());
                view_supplier.jtf_name.setText(view_supplier.jt_supplier.getValueAt(i, 1).toString());
                view_supplier.jtf_phone.setText(view_supplier.jt_supplier.getValueAt(i, 2).toString());
                view_supplier.jtf_street.setText(view_supplier.jt_supplier.getValueAt(i, 3).toString());
                view_supplier.jtf_colony.setText(view_supplier.jt_supplier.getValueAt(i, 4).toString());
                view_supplier.jtf_city.setText(view_supplier.jt_supplier.getValueAt(i, 5).toString());
                view_supplier.jtf_state.setText(view_supplier.jt_supplier.getValueAt(i, 6).toString());
            } else {
                JOptionPane.showMessageDialog(null, "No hay registros.");
            }

        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo acceder a los registros de la tabla" );
        }

    }

    /*
        Método para buscar un proveedor por nombre, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByName() {
        try {
            String search_by_name = ("SELECT * FROM proveedores WHERE nombre_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_name);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }

    /*
        Método para buscar un proveedor por calle, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByStreet() {
        try {
            String search_by_street = ("SELECT * FROM proveedores WHERE calle_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_street);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }

    /*
        Método para buscar un proveedor por código postal, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByColony() {
        try {
            String search_by_colony = ("SELECT * FROM proveedores WHERE colonia_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_colony);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }

    /*
        Método para buscar un proveedor por teléfono, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByPhone() {
        try {
            String search_by_phone = ("SELECT * FROM proveedores WHERE telefono_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_phone);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }

    /*
        Método para buscar un proveedor por estado, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByState() {
        try {
            String search_by_state = ("SELECT * FROM proveedores WHERE estado_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_state);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }

    /*
        Método para buscar un proveedor por ciudad, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByCity() {
        try {
            String search_by_city = ("SELECT * FROM proveedores WHERE ciudad_prov LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_city);
            pst.setString(1, view_supplier.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }

    public void setAction() {
        view_supplier.jt_supplier.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                jt_supplier_MouseClicked();
            }
        });
    }
}
