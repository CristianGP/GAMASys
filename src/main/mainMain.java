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
        
        /*pane_model_employee model_employee = new pane_model_employee();
        pane_view_employee view_employee = new pane_view_employee();
        pane_controller_employee controller_employee = new pane_controller_employee();*/
        
        pane_model_supplier model_supplier = new pane_model_supplier();
        pane_view_supplier view_supplier = new pane_view_supplier();
        pane_controller_supplier controller_supplier = new pane_controller_supplier(view_supplier, model_supplier);
        
        Object[] paneles = new Object[3];
        paneles[0] = controller_products;
        paneles[1] = controller_supplier;
        
        viewMain view_main = new viewMain();
        pane_controller_main controller_main = new pane_controller_main(view_main, paneles);
    }
}
