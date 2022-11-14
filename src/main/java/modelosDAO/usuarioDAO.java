package modelosDAO;

import db.Conexion;
import interfaces.i_usuario;
import modelos.cls_usuario;

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
        return false;
    }

    @Override
    public boolean actualizar(cls_usuario usuario) {
        return false;
    }

    @Override
    public List login(cls_usuario usuario) {
        ArrayList<cls_usuario> list = new ArrayList<>();
        String sql = "select * from usuarios where username=? and password=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getUsername());
            ps.setString(2, usuario.getPassword());
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
}
