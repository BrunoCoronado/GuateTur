package org.coronado.guatetur.bean;

/**
 * Created by informatica on 03/06/2016.
 */
public class Usuario {
    private int id_Usuario;
    private String nombre;
    private String correo;
    private String nick;
    private String password;

    public int getId_Usuario() {
        return id_Usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public String getNick() {
        return nick;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setId_Usuario(int id_Usuario) {
        this.id_Usuario = id_Usuario;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Usuario(){}

    public Usuario(int id, String nombre, String correo, String nick){
        this.id_Usuario = id;
        this.nombre = nombre;
        this.correo = correo;
        this.nick = nick;
    }
}
