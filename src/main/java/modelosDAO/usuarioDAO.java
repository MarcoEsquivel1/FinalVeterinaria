package modelosDAO;

import db.Conexion;
import interfaces.i_usuario;
import modelos.cls_usuario;
import utils.encript;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class usuarioDAO implements i_usuario {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public boolean agregar(cls_usuario usuario) {
        String sql = "insert into usuarios(fullname, username, tel, password, idpermiso) values(?,?,?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            //format uft-8
            ps.setString(1, usuario.getFullname());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getTel());
            ps.setString(4, usuario.getPassword());
            ps.setInt(5, usuario.getIdpermiso());
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizar(cls_usuario usuario) {
        String sql = "update usuarios set fullname=?, username=?, password=?, tel=? where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getFullname());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getTel());
            ps.setInt(5, usuario.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e);
            return false;
        }
    }

    @Override
    public boolean actualizarUsuario(cls_usuario usuario) {
        String sql = "update usuarios set fullname=?, username=?, password=?, tel=?, idpermiso=? where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getFullname());
            ps.setString(2, usuario.getUsername());
            ps.setString(3, usuario.getPassword());
            ps.setString(4, usuario.getTel());
            ps.setInt(5, usuario.getIdpermiso());
            ps.setInt(6, usuario.getId());
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error al actualizar usuario: " + e);
            return false;
        }

    }

    @Override
    public boolean eliminar(int id) {
        String sql = "delete from usuarios where id=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List login(cls_usuario usuario) {
        ArrayList<cls_usuario> list = new ArrayList<>();
        String sql = "select * from usuarios where username=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getUsername());
            rs = ps.executeQuery();
            while (rs.next()) {
                if (encript.checkPassword(usuario.getPassword(), rs.getString("password"))) {
                    cls_usuario us = new cls_usuario();
                    us.setId(rs.getInt("id"));
                    us.setFullname(rs.getString("fullname"));
                    us.setUsername(rs.getString("username"));
                    us.setTel(rs.getString("tel"));
                    us.setPassword(rs.getString("password"));
                    us.setIdpermiso(rs.getInt("idpermiso"));
                    list.add(us);
                } else {
                    System.out.println("Contraseña incorrecta");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }

    @Override
    public List getUsuarios() {
        List<cls_usuario> list = new ArrayList<>();
        String sql = "select * from usuarios where idpermiso!=1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                cls_usuario us = new cls_usuario();
                us.setId(rs.getInt("id"));
                us.setFullname(rs.getString("fullname"));
                us.setUsername(rs.getString("username"));
                us.setPassword(rs.getString("password"));
                us.setTel(rs.getString("tel"));
                us.setIdpermiso(rs.getInt("idpermiso"));
                list.add(us);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return list;
    }

    @Override
    public cls_usuario findUsuario(int id) {
        cls_usuario us = new cls_usuario();
        String sql = "select * from usuarios where id=? limit 1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            us.setId(rs.getInt("id"));
            us.setFullname(rs.getString("fullname"));
            us.setUsername(rs.getString("username"));
            us.setPassword(rs.getString("password"));
            us.setTel(rs.getString("tel"));
            us.setIdpermiso(rs.getInt("idpermiso"));
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return us;
    }

    @Override
    public boolean existAdmin() {
        String sql = "select * from usuarios where idpermiso=1";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }
}
