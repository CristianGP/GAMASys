
package models;
import java.sql.*;
import java.util.ArrayList;
/**
 *
 * @author ManuelAlonsoMH
 */
public class Customers {
    Conexion conexion;
    
    public Customers(){
        conexion = new Conexion();
    }
    
    public String insertCustomers(String id_cliente,String nombre_cliente,String apellido_paterno,String telefono,String rfc,String calle,String colonia,String no_interrior,String no_exterrior,String cp,String email,String ciudad,String pais){
        String rptaRegistro=null;
        try {
            Connection accesoBD = conexion.getConexion();
            CallableStatement cs = accesoBD.prepareCall("{call sp_inserCustomers(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, id_cliente);
            cs.setString(2, nombre_cliente);
            cs.setString(3, apellido_paterno);
            cs.setString(4, telefono);
            cs.setString(5, rfc);
            cs.setString(6, calle);
            cs.setString(7, colonia);
            cs.setString(8, no_interrior);
            cs.setString(9, no_exterrior);
            cs.setString(10, cp);
            cs.setString(11, email);
            cs.setString(12, ciudad);
            cs.setString(13, pais);
            
            int numFAfectas = cs.executeUpdate();
            
            if(numFAfectas>0){
                rptaRegistro="Registro Exitoso"; 
            } 
        } catch (Exception e) {
        }
        return rptaRegistro;
    }
    
    public ArrayList<Customers> listpane_model_customers(){
        ArrayList listCustomer = new ArrayList();
        pane_model_customers pane_model_customers ;
        try {
            Connection accesoBD = conexion.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("select * from clientes");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                pane_model_customers = new pane_model_customers();
                pane_model_customers.setDni(rs.getString(1));
            }
        } catch (Exception e) {
        }
   
