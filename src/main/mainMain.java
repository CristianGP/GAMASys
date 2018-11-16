/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controllers.pane_controller_products;
import models.pane_model_products;
import views.pane_view_products;

/**
 *
 * @author VICTOR MANUEL ARANDA
 */
public class mainMain {
    public static void main(String[] args) {
    pane_model_products mod = new pane_model_products();
    pane_view_products pro = new pane_view_products();
    
    pane_controller_products ctrl = new pane_controller_products(mod, pro);
    ctrl.initComponents();
    }
}
