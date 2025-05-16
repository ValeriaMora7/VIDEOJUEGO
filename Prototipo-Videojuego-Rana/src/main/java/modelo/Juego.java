/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author losmo
 */

import java.util.*;

public class Juego {
    private List<GameObject> objetos = new ArrayList<>();
    private Rana rana;
    private Vehiculo vehiculo;
    private EstadoJuego estado = EstadoJuego.START;

    public Juego() {
        iniciar();
    }

    public void iniciar() {
        rana = new Rana(400, 550);
        vehiculo = new Vehiculo(0, 300, 5);
        objetos.clear();
        objetos.add(vehiculo);
        objetos.add(rana);
        estado = EstadoJuego.PLAYING;
    }

    public void actualizar() {
        if (estado != EstadoJuego.PLAYING) return;
        for (GameObject obj : objetos) obj.actualizar();
        verificarColisiones();
    }

    private void verificarColisiones() {
        if (vehiculo.colisionaCon(rana)) {
            estado = EstadoJuego.GAME_OVER;
        }
    }

    public List<GameObject> getObjetos() { return objetos; }
    public Rana getRana() { return rana; }
    public EstadoJuego getEstado() { return estado; }
}

