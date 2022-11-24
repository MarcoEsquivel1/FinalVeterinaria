/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;

/**
 *
 * @author Mery Acevedo
 */
public class cls_registro {
    int id, idmascota;
    Date fecha;
    String Sintomas,duennio,mascota;

    public cls_registro() {
    }

    public cls_registro(int id, int idmascota, Date fecha, String Sintomas) {
        this.id = id;
        this.idmascota = idmascota;
        this.fecha = fecha;
        this.Sintomas = Sintomas;
    }

    public void setDuennio(String duennio) {
        this.duennio = duennio;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public int getIdmascota() {
        return idmascota;
    }

    public String getDuennio() {
        return duennio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdmascota(int idmascota) {
        this.idmascota = idmascota;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getSintomas() {
        return Sintomas;
    }

    public void setSintomas(String Sintomas) {
        this.Sintomas = Sintomas;
    }

    public int getId() {
        return id;
    }
    
          
}
