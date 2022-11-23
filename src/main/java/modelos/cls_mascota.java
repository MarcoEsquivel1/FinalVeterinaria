/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Mery Acevedo
 */
public class cls_mascota {
    private int id,idusuario;
    private String nombre, descripcion, nombreOwner,telOwner;

    public cls_mascota() {
    }

    public cls_mascota(int id, String nombre, String descripcion, String nombreOwner, String telOwner,int idusuario) {
        this.id = id;
        this.idusuario= idusuario;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nombreOwner = nombreOwner;
        this.telOwner = telOwner;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombreOwner() {
        return nombreOwner;
    }

    public void setNombreOwner(String nombreOwner) {
        this.nombreOwner = nombreOwner;
    }

    public String getTelOwner() {
        return telOwner;
    }

    public void setTelOwner(String telOwner) {
        this.telOwner = telOwner;
    }

    public int getId() {
        return id;
    }
    
    
    
}
