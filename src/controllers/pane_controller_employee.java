package controllers;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.pane_model_employee;
import views.pane_view_employee;
/**
 *
 * @author Sebastián
 */
    public class pane_controller_employee {
    
    public pane_model_employee m_employee;
    public pane_view_employee v_employee;
    
    ActionListener actionListener =  new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        if(e.getSource()== v_employee.jb_añadir){
            jb_add_actionPerformed();
        }
        else if (e.getSource()==v_employee.jb_eliminar){
            jb_delete_actionPerformed();
        }
        else if (e.getSource()==v_employee.jb_modificar){
            jb_edit_actionPerformed();
        }
        else if (e.getSource()==v_employee.jb_next){
            jb_next_actionPerformed();
        }
        else if (e.getSource()==v_employee.jb_prev){
            jb_prev_actionPerformed();
        }
        }
    };
    
    public pane_controller_employee(){
        this.m_employee= m_employee;
        this.v_employee= v_employee;
        setActionListener();
        initDB();
    }   

    private void initDB() {
        m_employee.conectarDB();
        v_employee.jtf_nombre.setText(m_employee.getNombre());
        v_employee.jtf_ap_paterno.setText(m_employee.getAp_paterno());
        v_employee.jtf_ap_materno.setText(m_employee.getAp_materno());
        v_employee.jtf_calle.setText(m_employee.getCalle());
        v_employee.jtf_ciudad.setText(m_employee.getCiudad());
        v_employee.jtf_edo.setText(m_employee.getEdo());
        v_employee.jtf_cp.setText(m_employee.getCp());
        v_employee.jtf_banco.setText(m_employee.getBanco());
        
    }
    private void setActionListener() {
        v_employee.jb_añadir.addActionListener(actionListener);
        v_employee.jb_buscar.addActionListener(actionListener);
        v_employee.jb_eliminar.addActionListener(actionListener);
        v_employee.jb_modificar.addActionListener(actionListener);
        v_employee.jb_next.addActionListener(actionListener);
        v_employee.jb_prev.addActionListener(actionListener);
    }

    private void jb_add_actionPerformed(){
        m_employee.newRegister();
        setValues();
    }
    
    private void jb_delete_actionPerformed(){
        m_employee.deleteRegister();
        setValues();
    }
    
    private void jb_edit_actionPerformed(){
        m_employee.editRegister();
        setValues();
    }
    
    private void jb_next_actionPerformed(){
        m_employee.moveLastRegister();
        setValues();
    }
     
    private void jb_prev_actionPerformed(){
        m_employee.moveFirstRegister();
        setValues();
    }
    private void setValues() {
        
    }
     
}
