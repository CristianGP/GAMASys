/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author ManuelAlonsoMH
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.pane_view_customers;
import models.pane_model_customers;
import bd.BD;
import java.sql.PreparedStatement;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import models.*;

public class pane_controller_customers implements ActionListener {

    pane_view_customers view_customers = new pane_view_customers();
    pane_model_customers model_customers = new pane_model_customers();

    public pane_controller_customers(pane_view_customers view_customers, pane_model_customers model_customers) {
        this.view_customers = view_customers;
        this.model_customers = model_customers;
        
        initComponents();
        view_customers.jb_add.addActionListener(this);
        view_customers.jb_delete.addActionListener(this);
        view_customers.jb_modify.addActionListener(this);
        view_customers.jb_new.addActionListener(this);
        view_customers.jb_search.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view_customers.jb_add) {
            /*
                Editando las variabes de getter y setter para el modelo y su método 
                para registrar al proveedor
             */
            model_customers.setNombre(view_customers.jtf_name.getText());
            model_customers.setTelefono(view_customers.jtf_phone.getText());
            model_customers.setCalle(view_customers.jtf_street.getText());
            model_customers.setColonia(view_customers.jtf_colony.getText());
            model_customers.setCiudad(view_customers.jtf_city.getText());
            model_customers.setEstado(view_customers.jtf_state.getText());
            model_customers.registerCustomers();

            /*
                Modificando los elementos del view para habilitar los botones por si están deshabilitados
                y las cajas de texto por si se requiere agregar otro.
             */
            view_customers.jtf_name.setText("");
            view_customers.jtf_phone.setText("");
            view_customers.jtf_street.setText("");
            view_customers.jtf_colony.setText("");
            view_customers.jtf_city.setText("");
            view_customers.jtf_state.setText("");
            view_customers.jtf_search.setText("");
            view_customers.jtf_id_customers.setText("0");
            view_customers.jb_delete.setEnabled(true);
            view_customers.jb_modify.setEnabled(true);
            view_customers.jb_new.setEnabled(true);
            view_customers.jb_search.setEnabled(true);
            cargar();

        } else if (e.getSource() == view_customers.jb_delete) {
            /*
                Editando las variabes de getter y setter para el modelo y su método 
                para registrar al proveedor
             */
            model_customers.setID(view_customers.jtf_id_customers.getText());
            model_customers.deleteCustomers();
            /*
                Modificando los elementos del view para habilitar los botones por si están deshabilitados
                y las cajas de texto por si se requiere agregar otro.
             */
            view_customers.jtf_name.setText("");
            view_customers.jtf_phone.setText("");
            view_customers.jtf_street.setText("");
            view_customers.jtf_colony.setText("");
            view_customers.jtf_city.setText("");
            view_customers.jtf_state.setText("");
            view_customers.jtf_search.setText("");
            view_customers.jtf_id_customers.setText("0");
            view_customers.jb_delete.setEnabled(true);
            view_customers.jb_modify.setEnabled(true);
            view_customers.jb_new.setEnabled(true);
            view_customers.jb_search.setEnabled(true);
            cargar();
        } else if (e.getSource() == view_customers.jb_modify) {
            /*
                Editando las variabes de getter y setter para el modelo y su método 
                para modificar al proveedor
             */
            model_customers.setNombre(view_customers.jtf_name.getText());
            model_customers.setTelefono(view_customers.jtf_phone.getText());
            model_customers.setCalle(view_customers.jtf_street.getText());
            model_customers.setColonia(view_customers.jtf_colony.getText());
            model_customers.setCiudad(view_customers.jtf_city.getText());
            model_customers.setEstado(view_customers.jtf_state.getText());
            model_customers.setID(view_customers.jtf_id_customers.getText());
            model_customers.modifyCustomers();

            /*
                Modificando los elementos del view para habilitar los botones por si están deshabilitados.
             */
            view_customers.jb_delete.setEnabled(true);
            view_customers.jb_modify.setEnabled(true);
            view_customers.jb_new.setEnabled(true);
            view_customers.jb_search.setEnabled(true);
            cargar();
        } else if (e.getSource() == view_customers.jb_new) {
            view_customers.jb_delete.setEnabled(false);
            view_customers.jb_modify.setEnabled(false);
            view_customers.jb_search.setEnabled(false);
            view_customers.jb_new.setEnabled(false);
            view_customers.jtf_name.setText("");
            view_customers.jtf_city.setText("");
            view_customers.jtf_colony.setText("");
            view_customers.jtf_phone.setText("");
            view_customers.jtf_state.setText("");
            view_customers.jtf_street.setText("");
            view_customers.jtf_search.setText("");
        } else if (e.getSource() == view_customers.jb_search) {
            if (view_customers.jcb_search.getSelectedItem() == "Nombre") {
                searchByName();
            } else if (view_customers.jcb_search.getSelectedItem() == "Calle") {
                searchByStreet();
            } else if (view_customers.jcb_search.getSelectedItem() == "Código Postal") {
                searchByColony();
            } else if (view_customers.jcb_search.getSelectedItem() == "Teléfono") {
                searchByPhone();
            } else if (view_customers.jcb_search.getSelectedItem() == "Estado") {
                searchByState();
            } else if (view_customers.jcb_search.getSelectedItem() == "Ciudad") {
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
        view_customers.setTitle("Clientes");
        view_customers.setLocationRelativeTo(null);
        view_customers.setVisible(true);
        cargar();
        setAction();
    }

    public void cargar() {
        DefaultTableModel model;
        String[] titulos = {"ID", "Nombre", "Teléfono", "Calle", "Colonia", "Ciudad", "Estado"};
        String[] registros = new String[7];
        String sql = "SELECT * FROM clientes;";

        model = new DefaultTableModel(null, titulos);
        BD DataBase = new BD();
        Connection con = DataBase.getConnection();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("id_clie");
                registros[1] = rs.getString("nombre_clie");
                registros[2] = rs.getString("telefono_clie");
                registros[3] = rs.getString("calle_clie");
                registros[4] = rs.getString("colonia_clie");
                registros[5] = rs.getString("ciudad_clie");
                registros[6] = rs.getString("estado_clie");
                model.addRow(registros);

            }
            view_customers.jt_customers.setModel(model);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se pudo importar la tabla");
        }
    }

    public void jt_customers_MouseClicked() {
        try {
            if (view_customers.jt_customers.getSelectedRow() != -1) {
                int i = view_customers.jt_customers.getSelectedRow();
                view_customers.jtf_id_customers.setText(view_customers.jt_customers.getValueAt(i, 0).toString());
                view_customers.jtf_name.setText(view_customers.jt_customers.getValueAt(i, 1).toString());
                view_customers.jtf_phone.setText(view_customers.jt_customers.getValueAt(i, 2).toString());
                view_customers.jtf_street.setText(view_customers.jt_customers.getValueAt(i, 3).toString());
                view_customers.jtf_colony.setText(view_customers.jt_customers.getValueAt(i, 4).toString());
                view_customers.jtf_city.setText(view_customers.jt_customers.getValueAt(i, 5).toString());
                view_customers.jtf_state.setText(view_customers.jt_customers.getValueAt(i, 6).toString());
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
            String search_by_name = ("SELECT * FROM clientes WHERE nombre_clie LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_name);
            pst.setString(1, view_customers.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró cliente");
        }
    }

    /*
        Método para buscar un proveedor por calle, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByStreet() {
        try {
            String search_by_street = ("SELECT * FROM clientes WHERE calle_clie LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_street);
            pst.setString(1, view_customers.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró cliente");
        }
    }

    /*
        Método para buscar un proveedor por código postal, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByColony() {
        try {
            String search_by_colony = ("SELECT * FROM clientes WHERE colonia_clie LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_colony);
            pst.setString(1, view_customers.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró cliente");
        }
    }

    /*
        Método para buscar un proveedor por teléfono, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByPhone() {
        try {
            String search_by_phone = ("SELECT * FROM clientes WHERE telefono_clie LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_phone);
            pst.setString(1, view_customers.jtf_search.getText());
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
            String search_by_state = ("SELECT * FROM clientes WHERE estado_clie LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_state);
            pst.setString(1, view_customers.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró cliente");
        }
    }

    /*
        Método para buscar un proveedor por ciudad, hace una sentencia de sql y muestra los datos en
        la tabla
     */
    private void searchByCity() {
        try {
            String search_by_city = ("SELECT * FROM clientes WHERE ciudad_clie LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            pst = (PreparedStatement) con.prepareStatement(search_by_city);
            pst.setString(1, view_customers.jtf_search.getText());
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "No se encontró cliente");
        }
    }

    public void setAction() {
        view_customers.jt_customers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                jt_customers_MouseClicked();
            }
        });
    }
}
