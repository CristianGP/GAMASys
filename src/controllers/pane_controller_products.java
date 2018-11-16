/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import models.pane_model_products;
import views.pane_view_products;

/**
 *
 * @author DELL
 */
public class pane_controller_products implements ActionListener{
   private pane_model_products modelo;
   private pane_view_products vista;

    public pane_controller_products(pane_model_products modelo, pane_view_products vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.jb_add.addActionListener(this);
        this.vista.jb_modify.addActionListener(this);
        this.vista.jb_delete.addActionListener(this);
        this.vista.jb_new.addActionListener(this);
    }
   
       public void initComponents() {
        vista.setLocationRelativeTo(null);
        vista.setTitle("Productos");
        vista.setVisible(true);
        vista.jtf_id.setVisible(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == vista.jb_add){
            modelo.setNombre_producto(vista.jtf_nombre.getText());
            modelo.setTipo_producto(vista.jtf_tipo.getText());
            modelo.setMarca(vista.jtf_marca.getText());
            modelo.setPrecio_venta(Double.parseDouble(vista.jtf_precio.getText()));
            modelo.setSku(Integer.parseInt(vista.jtf_sku.getText()));
            modelo.setLote(Integer.parseInt(vista.jtf_lote.getText()));
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            modelo.setFecha_entrada(vista.jDc_fecha.getDateFormatString());
            if(modelo.registrar()){
                JOptionPane.showMessageDialog(null, "Registro Guardado");
            }else{
                JOptionPane.showMessageDialog(null, "Error al Guardar");
            }
            
            
        }
    }
    public void limpiar(){
        vista.jtf_id.setText(null);
        vista.jtf_nombre.setText(null);
        vista.jtf_tipo.setText(null);
        vista.jtf_marca.setText(null);
        vista.jtf_precio.setText(null);
        vista.jtf_sku.setText(null);
        vista.jtf_lote.setText(null);
        vista.jDc_fecha.setDateFormatString(null);
         }
       
   
}
