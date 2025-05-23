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
    private EstadoJuego estado = EstadoJuego.START;
    private int puntaje;
    private PerfilJugador perfil;
    private boolean mostrarMensaje = false;
    private int velocidadVehiculos = 5;

    public Juego() {
        perfil = new PerfilJugador("Jugador1");
        iniciar(); //al crear un Juego, se asegura que rana y los objetos ya están listos para las pruebas
        
    }

    public void iniciar() {
        estado = EstadoJuego.PLAYING;

        objetos.clear();

        rana = new Rana(380, 540);   // Rana en posición más segura
        rana.setJuego(this);
        rana.reiniciarPosicion();
        objetos.add(rana);

        // Vehículos (en posiciones más alejadas de la rana)
        objetos.add(new Vehiculo(0, 400, velocidadVehiculos, "auto1.png"));
        objetos.add(new Vehiculo(200, 450, velocidadVehiculos + 1, "auto2.png"));
        objetos.add(new Vehiculo(400, 490, velocidadVehiculos + 2, "auto3.png"));
        objetos.add(new Vehiculo(100, 350, velocidadVehiculos + 3, "auto4.png"));
        

        // Troncos

objetos.add(new Tronco(0, 240, 3));    // mucho más arriba, en el río
objetos.add(new Tronco(200, 110, 3));  // también arriba, pegado al río
objetos.add(new Tronco(400, 130, 4));  // velocidad un poco mayor, en río
objetos.add(new Tronco(600, 80, 3));   // más arriba, río
objetos.add(new Tronco(100, 190, 3));  // un poco más abajo pero en río

  


        mostrarMensaje = false;
        puntaje = 0;
    }

    public void actualizar() {
        if (estado != EstadoJuego.PLAYING) return;
        for (GameObject obj : objetos) obj.actualizar();
        verificarColisiones();
    }

    private void verificarColisiones() {
        for (GameObject obj : objetos) {
            if (obj instanceof Vehiculo && obj.colisionaCon(rana)) {
                System.out.println("Colisión detectada al iniciar con vehículo en x=" + obj.getX() + ", y=" + obj.getY());
            estado = EstadoJuego.GAME_OVER;
            }
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



