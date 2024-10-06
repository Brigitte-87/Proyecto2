/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Jever_Gómez
 */
public class Producto {
    private int id_producto;
    private String producto;
    private int id_marca;
    private String descripcion;
    private String imagen;
    private double precio_costo;
    private double precio_venta;
    private int existencia;
    private Date fecha_ingreso;
    conexion cn;

    public Producto(){}
    public Producto(int id_producto, String producto, int id_marca, String descripcion, 
                    String imagen, double precio_costo, double precio_venta, int existencia, 
                    Date fecha_ingreso) {
        this.id_producto = id_producto;
        this.producto = producto;
        this.id_marca = id_marca;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
        this.existencia = existencia;
        this.fecha_ingreso = fecha_ingreso;
    }
    // Getters y setters

    public int getid_producto() {
        return id_producto;
    }

    public void setid_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getproducto() {
        return producto;
    }

    public void setproducto(String producto) {
        this.producto = producto;
    }

    public int getid_marca() {
        return id_marca;
    }

    public void setid_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getdescripcion() {
        return descripcion;
    }

    public void setdescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getimagen() {
        return imagen;
    }

    public void setimagen(String imagen) {
        this.imagen = imagen;
    }

    public double getprecio_costo() {
        return precio_costo;
    }

    public void setprecio_costo(double precio_costo) {
        this.precio_costo = precio_costo;
    }

    public double getprecio_venta() {
        return precio_venta;
    }

    public void setprecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public int getexistencia() {
        return existencia;
    }

    public void setexistencia(int existencia) {
        this.existencia = existencia;
    }
    
    public String getfecha_ingreso() {
        if (fecha_ingreso == null) {
            return "0"; // Devuelve una fecha falsa, o cualquier otra fecha que desees
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(fecha_ingreso); // Convierte Date a String    
    }


    // Método setter que recibe un String y lo convierte a Date
// Método setter que recibe un Date directamente
public void setfecha_ingreso(Date fechaIngreso) {
    this.fecha_ingreso = fechaIngreso; // Asigna directamente el objeto Date
}


    /*public java.sql.Timestamp getFechaIngreso2() {
        return fechaIngreso2;
    }

    public void setFechaIngreso2(java.sql.Timestamp fechaIngreso2) {
        this.fechaIngreso = fechaIngreso;
    }*/
    
    
    public int crear(){
        int retorno = 0;
        
        try{
            PreparedStatement parametro;
            cn = new conexion();
            cn.abrir_conexion();
            String query = "INSERT INTO productos (id_producto, producto, id_marca, descripcion, imagen, precio_costo, precio_venta, existencia, fecha_ingreso) VALUES(?,?,?,?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, this.getid_producto());
            parametro.setString(2, getproducto());
            parametro.setInt(3, this.getid_marca());
            parametro.setString(4, getdescripcion());
            parametro.setString(5, getimagen());
            parametro.setDouble(6, getprecio_costo());
            parametro.setDouble(7, getprecio_venta());
            parametro.setInt(8, this.getexistencia());
            parametro.setString(9, getfecha_ingreso());
            int executar = parametro.executeUpdate();
            retorno  = executar;
            System.out.println("Ingreso Exitoso.." + Integer.toString(executar));
            cn.cerrar_conexion();
            
    }catch(SQLException ex){
        retorno = 0;
        System.out.println("Error en crear:" + ex.getMessage());
        }
      return retorno;
    }
    
    public List<Producto> leerProductos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos";
        try {
            cn = new conexion();
            cn.abrir_conexion();
            PreparedStatement ps = cn.conexionBD.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Producto p = new Producto();
                p.setid_producto(rs.getInt("id_producto"));
                p.setproducto(rs.getString("producto"));
                p.setid_marca(rs.getInt("id_marca"));
                p.setdescripcion(rs.getString("descripcion"));
                p.setimagen(rs.getString("imagen"));
                p.setprecio_costo(rs.getDouble("precio_costo"));
                p.setprecio_venta(rs.getDouble("precio_venta"));
                p.setexistencia(rs.getInt("existencia"));
                p.setfecha_ingreso(rs.getDate("fecha_ingreso")); // Esta línea sigue siendo válida

                productos.add(p);
            }
        } catch (SQLException ex) {
            System.out.println("Error al leer productos: " + ex.getMessage());
        } finally {
            cn.cerrar_conexion();
        }

        return productos;
    }
    

/*public void eliminar(int id_producto) {
    PreparedStatement parametro = null;
    try {
        cn = new conexion();
        cn.abrir_conexion();
    
        String query = "DELETE FROM productos WHERE id_producto = ?;";
        parametro = cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, id_producto);
    
        int executar = parametro.executeUpdate();
        System.out.println("Eliminación exitosa: " + executar);
    } catch (SQLException ex) {
        System.out.println("Error al eliminar: " + ex.getMessage());
    } finally {
        if (parametro != null) {
            try {
                parametro.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar PreparedStatement: " + e.getMessage());
            }
        }
    }
}*/
}