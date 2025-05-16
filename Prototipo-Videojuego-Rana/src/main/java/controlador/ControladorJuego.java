/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

/**
 *
 * @author losmo
 */

import modelo.*;
import vista.*;
import javax.swing.*;
import java.awt.event.*;

public class ControladorJuego {
    private Juego juego;
    private VistaJuego vista;

    public ControladorJuego(Juego juego, VistaJuego vista) {
        this.juego = juego;
        this.vista = vista;

        vista.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (juego.getEstado() == EstadoJuego.START) {
                    juego.iniciar();
                } else if (juego.getEstado() == EstadoJuego.GAME_OVER && e.getKeyCode() == KeyEvent.VK_R) {
                    juego.iniciar();
                }

                if (juego.getEstado() == EstadoJuego.PLAYING) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> juego.getRana().mover("arriba");
                        case KeyEvent.VK_DOWN -> juego.getRana().mover("abajo");
                        case KeyEvent.VK_LEFT -> juego.getRana().mover("izquierda");
                        case KeyEvent.VK_RIGHT -> juego.getRana().mover("derecha");
                    }
                }
            }
        });

        Timer timer = new Timer(30, e -> {
            juego.actualizar();
            vista.repaint();
        });
        timer.start();
    }
}

