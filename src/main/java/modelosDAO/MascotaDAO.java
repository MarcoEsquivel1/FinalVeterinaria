/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelosDAO;

import db.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelos.cls_mascota;


/**
 *
 * @author Mery Acevedo
 */
public class MascotaDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    
    public boolean agregar(cls_mascota mascota) {
        String sql = "insert into mascotas(nombre, descripcion, nombreowner, telowner,idusuario) values(?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            //format uft-8
            ps.setString(1, mascota.getNombre());
            ps.setString(2, mascota.getDescripcion());
            ps.setString(3, mascota.getNombreOwner());
            ps.setString(4, mascota.getTelOwner());
            ps.setInt(5, mascota.getIdusuario());
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public boolean actualizar(cls_mascota usuario) {
        String sql = "update mascotas set nombre=?, descripcion=?, nombreowner=?, telowner=? where mascotas.id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getDescripcion());
            ps.setString(3, usuario.getNombreOwner());
            ps.setString(4, usuario.getTelOwner());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar mascota: " + e);
            return false;
        }
    }

    
  

  
   
    
   
    public cls_mascota findMascota(int id) {
      cls_mascota us = new cls_mascota();
        String sql = "select * from mascotas where id=? limit 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            us.setId(rs.getInt("id"));
            us.setNombre(rs.getString("nombre"));
            us.setDescripcion(rs.getString("descripcion"));
            us.setNombreOwner(rs.getString("nombreowner"));
            us.setTelOwner(rs.getString("telowner"));
           
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return us;
    }
    
    public List getMascotas(int idusuario) {
        List<cls_mascota> list = new ArrayList<>();
        String sql = "select * from mascotas where idusuario="+String.valueOf(idusuario);
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cls_mascota us = new cls_mascota();
                us.setId(rs.getInt("id"));
                us.setNombre(rs.getString("nombre"));
                us.setDescripcion(rs.getString("descripcion"));
                us.setNombreOwner(rs.getString("nombreowner"));
                us.setTelOwner(rs.getString("telowner"));
                us.setIdusuario(rs.getInt("idusuario"));
                list.add(us);
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return list;
    }
    
    public boolean delete(int id) throws ClassNotFoundException, SQLException{
        try {
             String SQL="delete from mascotas  where id='"+id+"'";
            con = cn.getConnection();
            ps = con.prepareStatement(SQL);
            
       
       
        
        ps.executeUpdate();
        
        return true;
         } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
    
    
}
