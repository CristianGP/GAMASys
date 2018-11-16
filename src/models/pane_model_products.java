/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;

public class pane_model_products extends Conexion {

    private int id_producto;
    private String nombre_producto;
    private String tipo_producto;
    private String marca;
    private Double precio_venta;
    private int sku;
    private int lote;
    private String fecha_entrada;

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

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public boolean registrar() {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "INSERT INTO productos (nombre_producto,tipo_producto,marca,precio_venta,sku,lote,fecha_entrada) VALUES (?,?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, getNombre_producto());
            ps.setString(2, getTipo_producto());
            ps.setString(3, getMarca());
            ps.setDouble(4, getPrecio_venta());
            ps.setInt(5, getSku());
            ps.setInt(6, getLote());
            ps.setString(7, getFecha_entrada());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

    public boolean modificar() {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "UPDATE productos SET nombre_producto=?,tipo_producto=?,marca=?,precio_venta=?,sku=?,lote=?,fecha_entrada=? WHERE id_producto=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, getNombre_producto());
            ps.setString(2, getTipo_producto());
            ps.setString(3, getMarca());
            ps.setDouble(4, getPrecio_venta());
            ps.setInt(5, getSku());
            ps.setInt(6, getLote());
            ps.setString(7, getFecha_entrada());
            ps.setInt(8, getId_producto());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }

     public boolean eleiminar() {
        PreparedStatement ps = null;
        Connection con = getConexion();

        String sql = "DELATE FROM productos  WHERE id_producto=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, getNombre_producto());
            ps.setString(2, getTipo_producto());
            ps.setString(3, getMarca());
            ps.setDouble(4, getPrecio_venta());
            ps.setInt(5, getSku());
            ps.setInt(6, getLote());
            ps.setString(7, getFecha_entrada());
            ps.setInt(8, getId_producto());
            ps.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }

    }
     
}
