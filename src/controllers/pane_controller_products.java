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
   public pane_model_products modelo;
   public pane_view_products vista;

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
            if(modelo.registrar()){
                JOptionPane.showMessageDialog(null, "Registro Guardado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Guardar");
                limpiar();
            }
            
            
        }
         if(e.getSource() == vista.jb_modify){
           modelo.setId_producto(Integer.parseInt(vista.jtf_id.getText()));
            modelo.setNombre_producto(vista.jtf_nombre.getText());
            modelo.setTipo_producto(vista.jtf_tipo.getText());
            modelo.setMarca(vista.jtf_marca.getText());
            modelo.setPrecio_venta(Double.parseDouble(vista.jtf_precio.getText()));
            modelo.setSku(Integer.parseInt(vista.jtf_sku.getText()));
            modelo.setLote(Integer.parseInt(vista.jtf_lote.getText()));
            if(modelo.modificar()){
                JOptionPane.showMessageDialog(null, "Registro Modificado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Modificar");
                limpiar();
            }
            
            
        }
         if(e.getSource() == vista.jtf_buscar){
           modelo.setNombre_producto(vista.jtf_nombre.getText());
            if(modelo.buscar()){
                
                vista.jtf_id.setText(String.valueOf(modelo.getId_producto()));
                vista.jtf_nombre.setText(modelo.getNombre_producto());
                vista.jtf_tipo.setText(modelo.getTipo_producto());
                vista.jtf_marca.setText(modelo.getMarca());
                vista.jtf_precio.setText(String.valueOf(modelo.getPrecio_venta()));
                vista.jtf_sku.setText(String.valueOf(modelo.getSku()));
                vista.jtf_lote.setText(String.valueOf(modelo.getLote()));
               
            }else{
                JOptionPane.showMessageDialog(null, "No encontro resultado");
                limpiar();
            }
            
            
        }
          if(e.getSource() == vista.jb_new){
           
                limpiar();
            }
            
          if(e.getSource() == vista.jtf_buscar){
           modelo.setId_producto(Integer.parseInt(vista.jtf_id.getText()));
            if(modelo.eleiminar()){
                JOptionPane.showMessageDialog(null, "Registro Eliminado");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "Error al Eliminar");
                limpiar();
            }
            
            
        }
          if(e.getSource() == vista.jb_new){
           
                limpiar();
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
         }
       
   
}
