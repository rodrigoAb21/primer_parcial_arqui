/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author KAKU
 */
public class M_Herramienta {
    private int id;
    private String nombre;
    private String marca;
    private ConexionBD db;

    public M_Herramienta(int id, String nombre, String marca) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        db = new ConexionBD();
    }

    public M_Herramienta() {
        this.id = 0;
        this.nombre = "";
        this.marca = "";
        db = new ConexionBD();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

   
    
    
    // METODOS
    public boolean registrar(){
        try {
            db.conectar();
            String sql = "INSERT INTO herramienta (id, nombre, marca) " + 
                    " VALUES (?, ?, ?)" ;
            PreparedStatement ps = db.getConexion().prepareStatement(sql);
            ps.setInt(1, this.id);
            ps.setString(2, this.nombre);
            ps.setString(3, this.marca);
            
            int i = ps.executeUpdate();
            db.desconectar();
            return i > 0;
            
        } catch (SQLException e) {
            System.out.println("No se pudo registrar la herramienta");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean editar(){
         try {
            db.conectar();
            String sql = "UPDATE herramienta SET " + 
                    "nombre = ?, " +
                    "marca = ? " + "WHERE id = ?";
            PreparedStatement ps = db.getConexion().prepareStatement(sql);
            ps.setString(1, this.nombre);
            ps.setString(2, this.marca);
            ps.setInt(3, this.id);
            
            int i = ps.executeUpdate();
            db.desconectar();
            return i > 0;
            
        } catch (SQLException e) {
            System.out.println("No se pudo editar la herramienta");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(){
           try {
            db.conectar();
            String sql = "DELETE FROM herramienta WHERE id = ?";
            PreparedStatement ps = db.getConexion().prepareStatement(sql);
            ps.setInt(1, this.id);
            
            int i = ps.executeUpdate();
            db.desconectar();
            return i > 0;
            
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar la herramienta");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<M_Herramienta> getHerramientas(){
        ArrayList<M_Herramienta> herramientas = new ArrayList<>();
        try {
            db.conectar();
            String query = "SELECT * FROM herramienta ORDER BY(id) DESC";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                M_Herramienta herramienta = new M_Herramienta();
                herramienta.setId(rs.getInt("id"));
                herramienta.setNombre(rs.getString("nombre"));
                herramienta.setMarca(rs.getString("marca"));
                
                herramientas.add(herramienta);
            }
            
            db.desconectar();
            
        } catch (SQLException e) {
            System.out.println("No se pudo obtener las herramientas.");
            System.out.println(e.getMessage());
        }
        return herramientas;
    }
}

