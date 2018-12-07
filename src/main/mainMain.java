/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import models.*;
import views.*;
import controllers.*;

/**
 *
 * @author Isaías
 */


public class mainMain {
    public static void main(String[] args) {
        pane_model_products model_products = new pane_model_products();
        pane_view_products view_products = new pane_view_products();
        pane_controller_products controller_products = new pane_controller_products(model_products, view_products);
        
        pane_model_employee model_employee = new pane_model_employee();
        pane_view_employee view_employee = new pane_view_employee();
        pane_controller_employee controller_employee = new pane_controller_employee(view_employee, model_employee);
        
        pane_model_supplier model_supplier = new pane_model_supplier();
        pane_view_supplier view_supplier = new pane_view_supplier();
        pane_controller_supplier controller_supplier = new pane_controller_supplier(view_supplier, model_supplier);
        
        pane_model_customers model_customers = new pane_model_customers();
        pane_view_customers view_customers = new pane_view_customers();
        pane_controller_customers controller_customers = new pane_controller_customers(view_customers, model_customers);
        
        Object[] paneles = new Object[4];
        paneles[0] = controller_products;
        paneles[1] = controller_employee;
        paneles[2] = controller_supplier;
        paneles[3] = controller_customers;
        
         
        viewMain view_main = new viewMain();
        pane_controller_main controller_main = new pane_controller_main(view_main, paneles);
    }
}
