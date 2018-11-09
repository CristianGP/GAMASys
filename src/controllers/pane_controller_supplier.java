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
import views.pane_view_supplier;
import models.pane_model_supplier;

public class pane_controller_supplier implements ActionListener{

    private final pane_view_supplier view_supplier;
    private final pane_model_supplier model_supplier;

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
            addSupplier();
        }
        else if (e.getSource() == view_supplier.jb_delete){
            deleteSupplier();
        }
        else if (e.getSource() == view_supplier.jb_modify){
            modifySupplier();
        }
        else if (e.getSource() == view_supplier.jb_new){
            newSupplier();
        }
        else if (e.getSource() == view_supplier.jb_search){
            searchSupplier();
        }
    }

    private void initComponents() {
        
    }

    private void addSupplier() {
        
    }

    private void modifySupplier() {
        
    }

    private void newSupplier() {
        
    }

    private void searchSupplier() {
        
    }

    private void deleteSupplier() {
        
    }
    
}
