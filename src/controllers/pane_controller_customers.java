
package controllers;
import models.*;
import views.*;
/**
 *
 * @author ManuelAlonsoMH
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class pane_controller_customers implements ActionListener{

    JFrame vistaCrud = new JFrame();
    Customers modeloCrud = new Customers();
    
    public pane_controller_customers(JFrame vistaCrud, Customers modeloCrud){
        this.modeloCrud = modeloCrud;
    }
    
    public void actionPerformed(ActionEvent e) {
        
    }
    
}
