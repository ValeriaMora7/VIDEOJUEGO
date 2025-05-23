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

public void setJuego(Juego juego) {
    this.juego = juego;
}

public Rana(int x, int y) {
    super(x, y, 30, 30); // más grande
    imagen = new ImageIcon(getClass().getClassLoader().getResource("rana.png")).getImage();
}

public void mover(String direccion) {
    switch (direccion) {
        case "arriba" -> {
            y -= velocidad;
            if (juego != null) {
                juego.aumentarPuntaje();
            }
        }
        case "abajo" -> y += velocidad;
        case "izquierda" -> x -= velocidad;
        case "derecha" -> x += velocidad;
    }
}


@Override
public void actualizar() {
    boolean sobreTronco = false;

    for (GameObject obj : juego.getObjetos()) {
        if (obj instanceof Tronco tronco && this.colisionaCon(tronco)) {
            sobreTronco = true;
            x += tronco.getVelocidad();
        }
    }

    if (y < 300 && y > 150 && !sobreTronco) {
        juego.setEstado(EstadoJuego.GAME_OVER);
    }

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