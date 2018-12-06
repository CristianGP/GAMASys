/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;


import views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 *
 * @author Isaías
 */
public class pane_controller_main implements ActionListener{
    viewMain view_main = new viewMain();
    
    public Object[] paneles;
    
    private pane_controller_employee controller_employee;
    private pane_controller_products controller_products;
    private pane_controller_supplier controller_supplier;
    
    public pane_controller_main (viewMain view_main, Object[] paneles){
        this.view_main = view_main;
        this.paneles = paneles;
        setControllers();
        view_main.jmi_employee.addActionListener(this);
        view_main.jmi_product.addActionListener(this);
        view_main.jmi_supplier.addActionListener(this);
        initComponents();
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view_main.jmi_employee){
            view_main.setContentPane(controller_employee.v_employee);
            view_main.revalidate();
            view_main.repaint();
        }
        else if (e.getSource() == view_main.jmi_product){
            view_main.setContentPane(controller_products.vista);
            view_main.revalidate();
            view_main.repaint();
        }
        else if (e.getSource() == view_main.jmi_supplier){
            view_main.setContentPane(controller_supplier.view_supplier);
            view_main.revalidate();
            view_main.repaint();
        }
    }
    private void initComponents() {
        view_main.setTitle("Ferretería");
        view_main.setLocationRelativeTo(null);
        view_main.setVisible(true);
    }

    private void setControllers() {
        controller_products = (pane_controller_products) paneles[0];
        /*controller_employee = (pane_controller_employee) paneles[1];*/
        controller_supplier = (pane_controller_supplier) paneles[1];
    }
}
