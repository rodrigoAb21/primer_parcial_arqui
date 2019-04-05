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
public class M_Cliente {
    private int id;
    private String nit;
    private String nombre;
    private String direccion;
    private String telefono;
    private ConexionBD db;

    public M_Cliente(int id, String nit, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        db = new ConexionBD();
    }

    public M_Cliente() {
        this.id = 0;
        this.nit = "";
        this.nombre = "";
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
            String sql = "INSERT INTO cliente (id, nit, nombre, direccion, " + 
                    "telefono) VALUES (?, ?, ?, ?, ?)" ;
            PreparedStatement ps = db.getConexion().prepareStatement(sql);
            ps.setInt(1, this.id);
            ps.setString(2, this.nit);
            ps.setString(3, this.nombre);
            ps.setString(4, this.direccion);
            ps.setString(5, this.telefono);
            
            int i = ps.executeUpdate();
            db.desconectar();
            return i > 0;
            
        } catch (SQLException e) {
            System.out.println("No se pudo registrar el cliente");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean editar(){
         try {
            db.conectar();
            String sql = "UPDATE cliente SET " + 
                    "nit = ?, " +
                    "nombre = ?, " +
                    "direccion = ?, " +
                    "telefono = ? " + "WHERE id = ?";
            PreparedStatement ps = db.getConexion().prepareStatement(sql);
            ps.setString(1, this.nit);
            ps.setString(2, this.nombre);
            ps.setString(3, this.direccion);
            ps.setString(4, this.telefono);
            ps.setInt(5, this.id);
            
            int i = ps.executeUpdate();
            db.desconectar();
            return i > 0;
            
        } catch (SQLException e) {
            System.out.println("No se pudo editar el cliente");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public boolean eliminar(){
           try {
            db.conectar();
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement ps = db.getConexion().prepareStatement(sql);
            ps.setInt(1, this.id);
            
            int i = ps.executeUpdate();
            db.desconectar();
            return i > 0;
            
        } catch (SQLException e) {
            System.out.println("No se pudo eliminar el cliente");
            System.out.println(e.getMessage());
            return false;
        }
    }
    
    public ArrayList<M_Cliente> getClientes(){
        ArrayList<M_Cliente> clientes = new ArrayList<>();
        try {
            db.conectar();
            String query = "SELECT * FROM cliente ORDER BY(id) DESC";
            PreparedStatement ps = db.getConexion().prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                M_Cliente cliente = new M_Cliente();
                cliente.setId(rs.getInt("id"));
                cliente.setNit(rs.getString("nit"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setTelefono(rs.getString("telefono"));
                
                clientes.add(cliente);
            }
            
            db.desconectar();
            
        } catch (SQLException e) {
            System.out.println("No se pudo obtener los clientes.");
            System.out.println(e.getMessage());
        }
        return clientes;
    }
    
}
