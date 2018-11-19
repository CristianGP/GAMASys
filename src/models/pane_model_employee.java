package models;

import bd.BD;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import models.modelMain;
/**
 *
 * @author Sebastián
 */
public class pane_model_employee extends Conexion {
  
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;
    private String nombre;
    private String ap_paterno;
    private String ap_materno;
    private String calle;
    private String ciudad;
    private String edo;
    private String cp;
    private String colonia;
    private String banco;
    private Integer no_cuenta;
    private String no_seguro;
    private String curp;
    private Integer telefono;
    private String tipo_empleado;
    
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
    
    /**
     * @param no_cuenta the no_cuenta to set
     */
    public void setNo_cuenta(Integer no_cuenta) {
        this.no_cuenta = no_cuenta;
    }

    /**
     * @return the no_seguro
     */
    public String getNo_seguro() {
        return no_seguro;
    }

    /**
     * @param no_seguro the no_seguro to set
     */
    public void setNo_seguro(String no_seguro) {
        this.no_seguro = no_seguro;
    }
   
    /**
     * @return the no_cuenta
     */
    public Integer getNo_cuenta() {
        return no_cuenta;
    }

    /**
     * @return the colonia
     */
    public String getColonia() {
        return colonia;
    }

    /**
     * @param colonia the colonia to set
     */
    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    /**
     * @return the curp
     */
    public String getCurp() {
        return curp;
    }

    /**
     * @param curp the curp to set
     */
    public void setCurp(String curp) {
        this.curp = curp;
    }

    /**
     * @return the telefono
     */
    public Integer getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the tipo_empleado
     */
    public String getTipo_empleado() {
        return tipo_empleado;
    }

    /**
     * @param tipo_empleado the tipo_empleado to set
     */
    public void setTipo_empleado(String tipo_empleado) {
        this.tipo_empleado = tipo_empleado;
    }

    private void setValues() {
      try {
            nombre = rs.getString("nombre_empleado");
            ap_paterno = rs.getString("ap_paterno");
            ap_materno = rs.getString("ap_materno");
            no_cuenta=rs.getInt("numero_cuenta");
            no_seguro=rs.getString("numero_seguro");
            banco = rs.getString("banco");
            curp =rs.getString("curp");
            telefono = rs.getInt("telefono");
            calle = rs.getString("calle");
            colonia=rs.getString("colonia");
            edo = rs.getString("estado");
            ciudad = rs.getString("ciudad");
            cp = rs.getString("cp");
            tipo_empleado=rs.getString("tipo_empleado");
            
           
            
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
         Connection con =  getConexion();
        try{
            
          
           
            ps.executeQuery("INSERT INTO empleados(nombre_empleado,ap_paterno,ap_materno,numero_cuenta,numero_seguro,banco,curp,telefono,calle,colonia,estado,ciudad,cp,tipo_empleado) "
                    + "VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error model:"+ex.getMessage());
        }
    }
    
    public void insertRegister(){
          Connection con= getConexion();
             try{
                 
          
            ps.executeQuery("INSERT INTO empleados(nombre_empleado,ap_paterno,ap_materno,numero_cuenta,numero_seguro,banco,curp,telefono,calle,colonia,estado,ciudad,cp,tipo_empleado)"
                    + "VALUES ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?");
            
        } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error model:"+ex.getMessage());
        }
    }
    
    public void editRegister(){
        Connection con =  getConexion();
            try{
            
            
            String actualName= this.getNombre();
            String actualFirstName=this.getAp_paterno();
            String actualLastName=this.getAp_materno();
            Integer actualCountNumber=this.getNo_cuenta();
            String actualSecureNumber=this.getNo_seguro();
            String actualBank=this.getBanco();
            String actualCurp=this.getCurp();
            Integer actualPhone=this.getTelefono();
            String actualStreet=this.getCalle();
            String actualColony=this.getColonia();
            String actualState=this.getEdo();
            String actualCity=this.getCiudad();           
            String actualPC=this.getCp();
            String actualType=this.getTipo_empleado();
            
            
            ps.executeQuery("UPDATE empleados SET nombre_empleado=?,ap_paterno=?,ap_materno=?,numero_cuenta=?,numero_seguro=?,banco=?,curp=?,telefono=?,calle=?,colonia=?,estado=?,ciudad=?,cp=?,tipo_empleado=?,"
                    + "WHERE nombre_empleado=?,ap_paterno=?,ap_materno=?,numero_cuenta=?,numero_seguro=?,banco=?,curp=?,telefono=?,calle=?,colonia=?,estado=?,ciudad=?,cp=?,tipo_empleado=?;");
            
            } catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Error model:"+ex.getMessage());
        }
    }
    
    public void deleteRegister(){
        Connection con =  getConexion();
           try{
            ps.executeUpdate("DELETE FROM empleados WHERE nombre_empleado=?,ap_paterno=?,ap_materno=?,numero_cuenta=?,numero_seguro=?,banco=?,curp=?,telefono=?,calle=?,colonia=?,estado=?,ciudad=?,cp=?,tipo_empleado=?;");
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error model:"+ex.getMessage());
        }
    }

        private void searchByName() {
        try{
            String search_by_name = ("SELECT * FROM empleados WHERE nombre_emp LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            ps = (PreparedStatement) con.prepareStatement(search_by_name);
            ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    
    private void searchByStreet() {
        try{
            String search_by_street = ("SELECT * FROM empleados WHERE calle_emp LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            ps = (PreparedStatement) con.prepareStatement(search_by_street);
            ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    private void searchByColony() {
        try{
            String search_by_colony = ("SELECT * FROM empleados WHERE colonia_emp LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            ps = (PreparedStatement) con.prepareStatement(search_by_colony);      
            ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    private void searchByPhone() {
        try{
            String search_by_phone = ("SELECT * FROM empleados WHERE telefono_emp LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            ps = (PreparedStatement) con.prepareStatement(search_by_phone);
            ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    
    private void searchByState() {
        try{
            String search_by_state = ("SELECT * FROM empleados WHERE estado_emp LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            ps = (PreparedStatement) con.prepareStatement(search_by_state);
            ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
    
    private void searchByCity() {
        try{
            String search_by_city = ("SELECT * FROM empleados WHERE ciudad_emp LIKE %?%");
            BD DataBase = new BD();
            Connection con = DataBase.getConnection();
            ps= (PreparedStatement) con.prepareStatement(search_by_city);
            ps.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "No se encontró proveedor");
        }
    }
}
