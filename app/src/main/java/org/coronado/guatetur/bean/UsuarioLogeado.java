package org.coronado.guatetur.bean;

/**
 * Created by informatica on 03/06/2016.
 */
public class UsuarioLogeado {
    public static Usuario usuario = new Usuario();
    public static boolean sesionIniciada = false;
    public static void setUsuario(Usuario usr){
        usuario = usr;
        sesionIniciada = true;
    }
    public static void setSesionIniciada(boolean s){sesionIniciada = s;}
    public static Usuario getUsuario(){
        return usuario;
    }
    public static boolean getSesionIniciada(){return sesionIniciada;}
}
