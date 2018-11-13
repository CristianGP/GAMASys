
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
    
    public String insertpane_model_customers(String id_cliente,String nombre_cliente,String apellido_paterno,String telefono,String rfc,String calle,String colonia,String no_interrior,String no_exterrior,String cp,String email,String ciudad,String pais){
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
    
    public ArrayList<pane_model_customers> listapane_model_customers(){
        ArrayList listapane_model_customers = new ArrayList();
        pane_model_customers Pane_model_customers ;
        try {
            Connection accesoBD = conexion.getConexion();
            PreparedStatement ps = accesoBD.prepareStatement("select * from clientes");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Pane_model_customers = new pane_model_customers();
                Pane_model_customers.setDid_cliente(rs.getString(1));
                Pane_model_customers.setDnombre_cliente(rs.getString(2));
                Pane_model_customers.setDapellido_paterno(rs.getString(3));
                Pane_model_customers.setDapellido_materno(rs.getString(4));
                Pane_model_customers.setDtelefono(rs.getString(5));
                Pane_model_customers.setDrfc(rs.getString(6));
                Pane_model_customers.setDcalle(rs.getString(7));
                Pane_model_customers.setDcolonia(rs.getString(8));
                Pane_model_customers.setDno_interrior(rs.getString(9));
                Pane_model_customers.setDno_exterrior(rs.getString(10));
                Pane_model_customers.setDcp(rs.getString(11));
                Pane_model_customers.setDemail(rs.getString(12));
                Pane_model_customers.setDciudad(rs.getString(13));
                Pane_model_customers.setDpais(rs.getString(13));
                listapane_model_customers.add(Pane_model_customers);
                
            }
        } catch (Exception e) {
        }
        return listapane_model_customers;
    }
}
