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

public class Rana extends GameObject {
    private int velocidad = 30;

    public Rana(int x, int y) {
        super(x, y, 30, 30);
    }

    public void mover(String direccion) {
        switch (direccion) {
            case "arriba": y -= velocidad; break;
            case "abajo": y += velocidad; break;
            case "izquierda": x -= velocidad; break;
            case "derecha": x += velocidad; break;
        }
    }

    @Override
    public void actualizar() {}

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, ancho, alto);
    }
}

