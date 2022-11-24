package interfaces;

import modelos.cls_usuario;

import java.util.List;

public interface i_usuario {
    public boolean agregar(cls_usuario usuario);
    public boolean actualizar(cls_usuario usuario);
    public boolean actualizarUsuario(cls_usuario usuario);
    public boolean eliminar(int id);
    public List login(cls_usuario usuario);
    public List getUsuarios();
    public cls_usuario findUsuario(int id);

    public boolean existAdmin();
}
