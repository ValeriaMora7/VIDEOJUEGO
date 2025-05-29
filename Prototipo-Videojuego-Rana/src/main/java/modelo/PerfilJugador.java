/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author losmo
 */




import java.io.Serializable;

public class PerfilJugador implements Serializable {
    private String nombre;
    private int puntaje;

    public PerfilJugador(String nombre) {
        this.nombre = nombre;
        this.puntaje = 0;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) { 
    this.nombre = nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public String toString() {
        return nombre + " - " + puntaje + " pts";
    }
}

