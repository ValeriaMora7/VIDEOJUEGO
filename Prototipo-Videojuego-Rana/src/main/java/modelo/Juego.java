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
import modelo.*;

public class Juego {
private List<GameObject> objetos = new ArrayList<>();
private Rana rana;
private EstadoJuego estado = EstadoJuego.START;
private int puntaje;
private PerfilJugador perfil;
private boolean mostrarMensaje = false;
private int velocidadVehiculos = 5;
public Juego() {
    this(new PerfilJugador("Jugador1"));
}

public Juego(PerfilJugador perfil) {
    this.perfil = perfil;
    iniciar();
}

public void iniciar() {
    estado = EstadoJuego.PLAYING;

    objetos.clear();

    rana = new Rana(380, 540);
    rana.setJuego(this);
    rana.reiniciarPosicion();
    objetos.add(rana);

    // Vehículos
    objetos.add(new Vehiculo(0, 400, velocidadVehiculos, "auto1.png"));
    objetos.add(new Vehiculo(200, 450, velocidadVehiculos + 1, "auto2.png"));
    objetos.add(new Vehiculo(400, 490, velocidadVehiculos + 2, "auto3.png"));
    objetos.add(new Vehiculo(100, 350, velocidadVehiculos + 3, "auto4.png"));

    // Troncos
    objetos.add(new Tronco(0, 240, 1)); //primer tronco
objetos.add(new Tronco(100, 160, 1));
objetos.add(new Tronco(200, 80, 1));


    mostrarMensaje = false;
    puntaje = 0;
}

public void actualizar() {
    if (estado != EstadoJuego.PLAYING) return;
    for (GameObject obj : objetos) obj.actualizar();
    verificarColisiones();
}

private void verificarColisiones() {
    boolean sobreTronco = false;

    for (GameObject obj : objetos) {
        if (obj instanceof Vehiculo && obj.colisionaCon(rana)) {
            estado = EstadoJuego.GAME_OVER;
            guardarPuntajePerfil();
            return;
        }

        if (obj instanceof Tronco && obj.colisionaCon(rana)) {
            sobreTronco = true;
        }
    }

    // En lugar de terminar el juego aquí, le decimos a la rana que caiga
    if (estaEnElAgua(rana) && !sobreTronco && !rana.estaCayendo()) {
        rana.iniciarCaida(); // Esta lógica se moverá a la rana
    }
}


private boolean estaEnElAgua(Rana rana) {
    int y = rana.getY();
    // el rango vertical del río .
    return y >= 70 && y <= 260;
}

public boolean estaEnZonaTroncos(int y) {
    // Se Define aquí la zona donde hay troncos 
    return y >= 70 && y <= 260;
}

public void guardarPuntajePerfil() {
    if (perfil != null) {
        perfil.setPuntaje(puntaje);
        PerfilManager.actualizarPerfil(perfil);
    }
}

public void aumentarPuntaje() {
    puntaje += 10;
    perfil.setPuntaje(puntaje);
}

public void mostrarMensajeNivel() {
    mostrarMensaje = true;
}

public boolean isMostrarMensaje() {
    return mostrarMensaje;
}

public void ocultarMensajeNivel() {
    mostrarMensaje = false;
}

public void incrementarVelocidad() {
    velocidadVehiculos += 1;
}

public List<GameObject> getObjetos() { return objetos; }
public Rana getRana() { return rana; }
public EstadoJuego getEstado() { return estado; }
public int getPuntaje() { return puntaje; }
public PerfilJugador getPerfil() { return perfil; }
public void setEstado(EstadoJuego estado) { this.estado = estado; }
}



