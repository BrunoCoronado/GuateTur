package org.coronado.guatetur.bean;

/**
 * Created by TrexT on 02/06/2016.
 */
public class SitioTuristico {
    private int id_sitioTuristico;
    private String _nombre;
    private String _descripcion;

    public void setId_sitioTuristico(int _id){
        this.id_sitioTuristico = _id;
    }

    public int getId_sitioTuristico(){
        return  this.id_sitioTuristico;
    }

    public void setNombre(String nombre){
        this._nombre = nombre;
    }

    public String getNombre(){
        return this._nombre;
    }

    public void setDescripcion(String descripcion){
        this._descripcion = descripcion;
    }

    public String getDescripcion(){
        return  this._descripcion;
    }

    public SitioTuristico(){}

    public SitioTuristico(int id, String nombre, String descripcion){
        this.id_sitioTuristico = id;
        this._nombre = nombre;
        this._descripcion= descripcion;
    }
}
