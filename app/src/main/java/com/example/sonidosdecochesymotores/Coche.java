package com.example.sonidosdecochesymotores;

import java.io.Serializable;

public class Coche implements Serializable {

    String nombre, nombreEnOtroIdioma;
    int imagen;
    public Coche(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }
    public void setImagen(int i){
        imagen = i;
    }
    public int getImagen(){
        return imagen;
    }
    public void setNombreEnOtroIdioma(String n){
        nombreEnOtroIdioma = n;
    }
    public String getNombreEnOtroIdioma(){
        return nombreEnOtroIdioma;
    }
}
