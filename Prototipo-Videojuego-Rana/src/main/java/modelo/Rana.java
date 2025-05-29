/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author losmo
 */








import java.awt.*;
import javax.swing.ImageIcon;

public class Rana extends GameObject {
private int velocidad = 30;
private Juego juego;
private Image imagen;
private boolean cayendo = false;
private int tiempoCaida = 0;



public void iniciarCaida() {
    cayendo = true;
    tiempoCaida = 0;
}
public boolean estaCayendo() {
    return cayendo;
}


public void setJuego(Juego juego) {
    this.juego = juego;
}

public Rana(int x, int y) {
    super(x, y, 30, 30); // más grande
    imagen = new ImageIcon(getClass().getClassLoader().getResource("rana.png")).getImage();
}

int salto = (juego != null && juego.estaEnZonaTroncos(y)) ? 80 : velocidad;
public void mover(String direccion) {
    int salto = velocidad;

    // Verifica hacia dónde saltará
    int siguienteY = switch (direccion) {
        case "arriba" -> y - velocidad;
        case "abajo" -> y + velocidad;
        default -> y;
    };

    // Cambiar a salto de 80 si el siguiente paso está en la zona del río
    if (juego != null && juego.estaEnZonaTroncos(siguienteY)) {
        salto = 80;
    }

    switch (direccion) {
        case "arriba" -> {
            y -= salto;
            if (juego != null) {
                juego.aumentarPuntaje();
            }
        }
        case "abajo" -> y += salto;
        case "izquierda" -> x -= velocidad;
        case "derecha" -> x += velocidad;
    }
}


@Override
public void actualizar() {
    if (cayendo) {
        tiempoCaida++;
        if (tiempoCaida > 20) { // medio segundo aprox a 40FPS
            juego.setEstado(EstadoJuego.GAME_OVER);
            juego.guardarPuntajePerfil();
        }
        return;
    }

    // Movimiento con tronco
    for (GameObject obj : juego.getObjetos()) {
        if (obj instanceof Tronco tronco && this.colisionaCon(tronco)) {
            x += tronco.getVelocidad();
        }
    }

    // Meta alcanzada
    if (y < 100) {
        juego.aumentarPuntaje();
        juego.mostrarMensajeNivel();
        reiniciarPosicion();
        juego.incrementarVelocidad();
    }
}




public void reiniciarPosicion() {
    this.x = 380;
    this.y = 540;
}

@Override
public void dibujar(Graphics g) {
    g.drawImage(imagen, x, y, ancho, alto, null);
}
}