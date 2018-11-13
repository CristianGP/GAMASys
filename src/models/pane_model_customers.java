
package models;

/**
 *
 * @author ManuelAlonsoMH
 */
public class pane_model_customers {
    String id_cliente;
    String nombre_cliente;
    String apellido_paterno;
    String telefono;
    String rfc;
    String calle;
    String colonia;
    String no_interrior;
    String no_exterrior;
    String cp;
    String email;
    String ciudad;
    String pais; 
    
    public pane_model_customers(){
        id_cliente="";
        nombre_cliente="";
        apellido_paterno="";
        telefono="";
        rfc="";
        calle="";
        colonia="";
        no_interrior="";
        no_exterrior="";
        cp="";
        email="";
        ciudad="";
        pais=""; 
    }

    public String getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(String id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNo_interrior() {
        return no_interrior;
    }

    public void setNo_interrior(String no_interrior) {
        this.no_interrior = no_interrior;
    }

    public String getNo_exterrior() {
        return no_exterrior;
    }

    public void setNo_exterrior(String no_exterrior) {
        this.no_exterrior = no_exterrior;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    void setDni(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
