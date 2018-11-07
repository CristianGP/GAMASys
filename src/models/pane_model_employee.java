package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import models.modelMain;
/**
 *
 * @author Sebastián
 */
public class pane_model_employee {
  
    private Statement st;
    private ResultSet rs;
    
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String calle;
    private String ciudad;
    private String edo;
    private String cp;
    private String banco;

    modelMain modelmain;
    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ap_paterno
     */
    public String getAp_paterno() {
        return ap_paterno;
    }

    /**
     * @param ap_paterno the ap_paterno to set
     */
    public void setAp_paterno(String ap_paterno) {
        this.ap_paterno = ap_paterno;
    }

    /**
     * @return the ap_materno
     */
    public String getAp_materno() {
        return ap_materno;
    }

    /**
     * @param ap_materno the ap_materno to set
     */
    public void setAp_materno(String ap_materno) {
        this.ap_materno = ap_materno;
    }

    /**
     * @return the calle
     */
    public String getCalle() {
        return calle;
    }

    /**
     * @param calle the calle to set
     */
    public void setCalle(String calle) {
        this.calle = calle;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    /**
     * @return the edo
     */
    public String getEdo() {
        return edo;
    }

    /**
     * @param edo the edo to set
     */
    public void setEdo(String edo) {
        this.edo = edo;
    }

    /**
     * @return the cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     * @return the banco
     */
    public String getBanco() {
        return banco;
    }

    /**
     * @param banco the banco to set
     */
    public void setBanco(String banco) {
        this.banco = banco;
    }
    
   

    private void setValues() {
      try {
            nombre = rs.getString("nombre");
            ap_paterno = rs.getString("Apellido paterno");
            ap_materno = rs.getString("Apellido materno");
            calle = rs.getString("calle");
            ciudad = rs.getString("ciudad");
            edo = rs.getString("estado");
            cp = rs.getString("cp");
            banco = rs.getString("banco");
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, "Error model 102: " + err.getMessage());

        }
}
    public void moveFirstRegister(){
        System.out.println("moverAnteriorRegistro pane_model_employee");
        try {
            if (!rs.isFirst()) {
                rs.previous();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error model: " + ex.getMessage());
        }
    }
    
    public void moveLastRegister(){
         System.out.println("moverUltimoRegistro pane_model_employee");
        try {
            rs.last();
            setValues();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error model:" + ex.getMessage());
        }   
    }
    
    public void newRegister(){
        try{
            st.executeQuery("INSERT INTO empleados(nombre,apellido_paterno,apellido_materno,calle,ciudad,edo,cp,banco)"
                    + "VALUES"+"('"+nombre+"','"
                    +ap_paterno+"','"+ap_materno+"','"+calle+"','"+ciudad+"','"+edo+"','"+cp+"','"+banco+"');");
            modelmain.conectarDB();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error model:"+ex.getMessage());
        }
    }
    
    public void insertRegister(){
             try{
            st.executeQuery("INSERT INTO empleados(nombre,apellido_paterno,apellido_materno,calle,ciudad,edo,cp,banco)VALUES"+"('"+nombre+"','"
                    +ap_paterno+"','"+ap_materno+"','"+calle+"','"+ciudad+"','"+edo+"','"+cp+"','"+banco+"');");
            modelmain.conectarDB();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error model:"+ex.getMessage());
        }
    }
    
    public void editRegister(){
            try{
            String actualName= this.getNombre();
            String actualFirstName=this.getAp_paterno();
            String actualLastName=this.getAp_materno();
            String actualStreet=this.getCalle();
            String actualCity=this.getCiudad();
            String actualState=this.getEdo();
            String actualPC=this.getCp();
            String actualBank=this.getBanco();
            
            st.executeQuery("UPDATE empleados SET nombre='"+nombre+"',apellido_paterno='"+ap_paterno+"',apellido_materno='"+ap_materno+"',calle='"+calle+"',ciudad='"+ciudad+"',edo='"+edo+"',cp='"+cp+"',banco='"+banco+"'WHERE name='"+actualName+"',ap_paterno='"+actualFirstName+"',ap_materno='"+actualLastName+"',calle='"+actualStreet+"',city='"+actualCity+"',edo='"+actualState+"',cp='"+actualPC+"',banco='"+actualBank+"';");
            modelmain.conectarDB();
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error model:"+ex.getMessage());
        }
    }
    
    public void deleteRegister(){
           try{
            st.executeUpdate("DELETE FROM empleados WHERE name='"+nombre+"',ap_paterno='"+ap_paterno+"',ap_materno='"+ap_materno+"',calle='"+calle+"',city='"+ciudad+"',edo='"+edo+"',cp='"+cp+"',banco='"+banco+"';");
            modelmain.conectarDB();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error model:"+ex.getMessage());
        }
    }
}
