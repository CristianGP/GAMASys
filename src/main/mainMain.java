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
        pane_model_supplier model_supplier = new pane_model_supplier();
        pane_view_supplier view_supplier = new pane_view_supplier();
        pane_controller_supplier controller_supplier = new pane_controller_supplier(view_supplier, model_supplier);
    }
}
