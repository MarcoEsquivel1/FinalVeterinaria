/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelosDAO;

import db.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelos.cls_registro;

/**
 *
 * @author Mery Acevedo
 */
public class RegistrosDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    
    public boolean agregar(cls_registro mascota) {
        String sql = "insert into registros(idmascota, sintomas, fecha) values(?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            //format uft-8
            ps.setInt(1, mascota.getIdmascota());
            ps.setString(2, mascota.getSintomas());
            ps.setDate(3, mascota.getFecha());
         
            
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    public boolean actualizar(cls_registro usuario) {
        String sql = "update registros set idmascota=?, sintomas=?, fecha=? where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, usuario.getIdmascota());
            ps.setString(2, usuario.getSintomas());
            ps.setDate(3, usuario.getFecha());
          
            ps.setInt(4, usuario.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e);
            return false;
        }
    }

    
  

  
   
    
   
    public cls_registro findRegistros(int id) {
      cls_registro us = new cls_registro();
        String sql = "SELECT *  from registros r JOIN mascotas m on m.id = r.idmascota and r.id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            us.setId(rs.getInt("id"));
            us.setIdmascota(Integer.parseInt(rs.getString("idmascota")));
            us.setMascota(rs.getString("nombre"));
            us.setDuennio(rs.getString("nombreowner"));
            us.setSintomas(rs.getString("sintomas"));
            us.setFecha(Date.valueOf(rs.getString("fecha")));
            
           
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return us;
    }
    
    public List getRegistros(int idusuario) {
        List<cls_registro> list = new ArrayList<>();
        String sql = "SELECT *  from registros r JOIN mascotas m on m.id = r.idmascota and m.idusuario = "+String.valueOf(idusuario);
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cls_registro us = new cls_registro();
                us.setId(rs.getInt("id"));
                us.setIdmascota(Integer.parseInt(rs.getString("idmascota")));
                us.setSintomas(rs.getString("sintomas"));
                us.setFecha(Date.valueOf(rs.getString("fecha")));
                us.setMascota(rs.getString("nombre"));
                us.setDuennio(rs.getString("nombreowner"));
                list.add(us);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }
    
     public boolean delete(int id) throws ClassNotFoundException, SQLException{
        try {
             String SQL="delete from registros  where id='"+id+"'";
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
