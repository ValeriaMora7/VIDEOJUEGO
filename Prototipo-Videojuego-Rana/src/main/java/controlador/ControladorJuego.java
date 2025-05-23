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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Timer;

public class ControladorJuego {

    private Juego juego;
    private VistaJuego vista;
    private Timer timer;

   public ControladorJuego(Juego juego, VistaJuego vista) {
    this.juego = juego;
    this.vista = vista;

   

    vista.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            manejarTecla(e);
        }
    });

    vista.setFocusable(true);
    vista.requestFocusInWindow(); // asegura que las teclas funcionen

    timer = new Timer(16, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            juego.actualizar();
            vista.repaint();
        }
    });
    timer.start();
}


    private void manejarTecla(KeyEvent e) {
        switch (juego.getEstado()) {
            case START -> juego.iniciar();
            case GAME_OVER -> {
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    juego.iniciar();
                }
            }
            case PLAYING -> {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> juego.getRana().mover("arriba");
                    case KeyEvent.VK_DOWN -> juego.getRana().mover("abajo");
                    case KeyEvent.VK_LEFT -> juego.getRana().mover("izquierda");
                    case KeyEvent.VK_RIGHT -> juego.getRana().mover("derecha");
                }
            }
        }
        vista.repaint();
    }
}


