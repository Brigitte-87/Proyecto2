/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author Jever_Gómez
 */
public class Marcas {
    private int id_marca;
    private String marca;
    conexion cn;
    
    public Marcas(){}
    public Marcas(int id_marcas, String marcas) {
        this.id_marca = id_marca;
        this.marca = marca;
    }

    public int getid_marca() {
        return id_marca;
    }

    public void setid_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getmarca() {
        return marca;
    }

    public void setmarca(String marca) {
        this.marca = marca;
    }
    
    public HashMap drop_marca(){
        HashMap<String,String> drop = new HashMap();
        try{
            String query = "select id_marca,marca from marcas;";
            cn = new conexion();
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            
            while(consulta.next()){
                drop.put(consulta.getString("id_marca"), consulta.getString("marca"));
            }
        }catch(SQLException ex){
            System.out.println("error drop_marca: " + ex.getMessage());
        }
        return drop;
        
    }
}
