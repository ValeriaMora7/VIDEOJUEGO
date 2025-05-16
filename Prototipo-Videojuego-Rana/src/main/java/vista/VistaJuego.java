/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author losmo
 */

import javax.swing.*;
import java.awt.*;
import modelo.*;

public class VistaJuego extends JPanel {
    private Juego juego;

    public VistaJuego(Juego juego) {
        this.juego = juego;
        setPreferredSize(new Dimension(800, 600));
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (juego.getEstado() == EstadoJuego.START) {
            g.drawString("Presiona una tecla para iniciar", 300, 300);
        } else if (juego.getEstado() == EstadoJuego.GAME_OVER) {
            g.drawString("¡Game Over! Presiona R para reiniciar", 300, 300);
        } else {
            for (GameObject obj : juego.getObjetos()) {
                obj.dibujar(g);
            }
        }
    }
}

