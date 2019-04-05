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
public class M_Tecnico {
    private int id;
    private String marca;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;
    private ConexionBD db;

    public M_Tecnico(int id, String ci, String nombre, String apellido, String direccion, String telefono) {
        this.id = id;
        this.marca = ci;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        db = new ConexionBD();
    }

    public M_Tecnico() {
        this.id = 0;
        this.marca = "";
        this.nombre = "";
        this.apellido = "";
        this.direccion = "";
        this.telefono = "";
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

    public String getCi() {
        return marca;
    }

    public void setCi(String ci) {
        this.marca = ci;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
    
    // METODOS
    public boolean registrar(){
        try {
            db.conectar();
            String sql = "INSERT INTO tecnico (id, ci, nombre, apellido, " + 
                    "direccion, telefono) VALUES (?, ?, ?, ?, ?, ?)" ;
            PreparedStatement ps = db.getConexion().prepareStatement(sql);
            ps.setInt(1, this.id);
            ps.setString(2, this.marca);
            ps.setString(3, this.nombre);
            ps.setString(4, this.apellido);
            ps.setString(5, this.direccion);
            ps.setString(6, this.telefono);
            
            int i = ps.executeUpdate();
            db.desconectar();
            return i > 0;
            
        } catch (SQLException e) {
            System.out.println("No se pudo registrar el tecnico");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean editar(){
         try {
            db.conectar();
            String sql = "UPDATE tecnico SET " + 
                    "ci = ?, " +
                    "nombre = ?, " +
                    "apellido = ?, " +
                    "direccion = ?, " +
                    "telefono = ? " + "WHERE id = ?";
            PreparedStatement ps = db.getConexion().prepareStatement(sql);
            ps.setString(1, this.marca);
            ps.setString(2, this.nombre);
            ps.setString(3, this.apellido);
            ps.setString(4, this.direccion);
            ps.setString(5, this.telefono);
            ps.setInt(6, this.id);
            
            int i = ps.executeUpdate();
            db.desconectar();
            return i > 0;
            
        } catch (SQLException e) {
            System.out.println("No se pudo editar el tecnico");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(){
           try {
            db.conectar();
            String sql = "DELETE FROM tecnico WHERE id = ?";
            PreparedStatement ps = db.getConexion().prepareStatement(sql);
            ps.setInt(1, this.id);
            
            int i = ps.executeUpdate();
            db.desconectar();
            return i > 0;
            
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el tecnico");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<M_Tecnico> getTecnicos(){
        ArrayList<M_Tecnico> tecnicos = new ArrayList<>();
        try {
            db.conectar();
            String query = "SELECT * FROM tecnico ORDER BY(id) DESC";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                M_Tecnico tecnico = new M_Tecnico();
                tecnico.setId(rs.getInt("id"));
                tecnico.setCi(rs.getString("ci"));
                tecnico.setNombre(rs.getString("nombre"));
                tecnico.setApellido(rs.getString("apellido"));
                tecnico.setDireccion(rs.getString("direccion"));
                tecnico.setTelefono(rs.getString("telefono"));
                
                tecnicos.add(tecnico);
            }
            
            db.desconectar();
            
        } catch (SQLException e) {
            System.out.println("No se pudo obtener a los tecnicos.");
            System.out.println(e.getMessage());
        }
        return tecnicos;
    }
}
