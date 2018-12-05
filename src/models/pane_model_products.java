/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class pane_model_products {

    private int id_producto;
    private String nombre_producto;
    private String tipo_producto;
    private String marca;
    private Double precio_venta;
    private int sku;
    private int lote;
   

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public String getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(String tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getSku() {
        return sku;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }



    public boolean registrar() {
        PreparedStatement ps = null;
       

        String sql = "INSERT INTO productos (nombre_producto,tipo_producto,marca,precio_venta,sku,lote) VALUES (?,?,?,?,?,?)";

        try {
            
            ps.setString(1, getNombre_producto());
            ps.setString(2, getTipo_producto());
            ps.setString(3, getMarca());
            ps.setDouble(4, getPrecio_venta());
            ps.setInt(5, getSku());
            ps.setInt(6, getLote());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

    public boolean modificar() {
        PreparedStatement ps = null;
        

        String sql = "UPDATE productos SET nombre_producto=?,tipo_producto=?,marca=?,precio_venta=?,sku=?,lote=?,fecha_entrada=? WHERE id_producto=?";

        try {
            
            ps.setString(1, getNombre_producto());
            ps.setString(2, getTipo_producto());
            ps.setString(3, getMarca());
            ps.setDouble(4, getPrecio_venta());
            ps.setInt(5, getSku());
            ps.setInt(6, getLote());
            ps.setInt(8, getId_producto());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

     public boolean eleiminar() {
        PreparedStatement ps = null;
        

        String sql = "DELATE FROM productos  WHERE id_producto=?";

        try {
            ps.setString(1, getNombre_producto());
            ps.setString(2, getTipo_producto());
            ps.setString(3, getMarca());
            ps.setDouble(4, getPrecio_venta());
            ps.setInt(5, getSku());
            ps.setInt(6, getLote());
            ps.setInt(8, getId_producto());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }
     public boolean buscar() {
        PreparedStatement ps = null;
         ResultSet rs = null;

        String sql = "DELATE FROM productos  WHERE =nombre_producto";

        try {
            ps.setString(1, getNombre_producto());
            rs = ps.executeQuery();
            
            if(rs.next()){
                setId_producto(Integer.parseInt(rs.getString("id_producto")));
                setNombre_producto(rs.getString("nombre_producto"));
                setTipo_producto(rs.getString("tipo_producto"));
                setMarca((rs.getString("marca")));
                setPrecio_venta(Double.parseDouble(rs.getString("precio_venta")));
                setSku(Integer.parseInt(rs.getString("sku")));
                setLote(Integer.parseInt(rs.getString("lote")));
               return true;
            }
            return false;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }
     
}
