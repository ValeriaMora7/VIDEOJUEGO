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

public class Vehiculo extends GameObject {
    private int velocidad;

    public Vehiculo(int x, int y, int velocidad) {
        super(x, y, 60, 30);
        this.velocidad = velocidad;
    }

    @Override
    public void actualizar() {
        x += velocidad;
        if (x > 800) x = -60;
    }

    @Override
    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, ancho, alto);
    }
}
