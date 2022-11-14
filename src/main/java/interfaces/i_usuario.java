package interfaces;

import modelos.cls_usuario;

import java.util.List;

public interface i_usuario {
    public boolean agregar(cls_usuario usuario);
    public boolean actualizar(cls_usuario usuario);
    public List login(cls_usuario usuario);
}
