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

public abstract class GameObject {
    protected int x, y, ancho, alto;

    public GameObject(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public abstract void actualizar();
    public abstract void dibujar(Graphics g);

    public boolean colisionaCon(GameObject otro) {
        Rectangle r1 = new Rectangle(x, y, ancho, alto);
        Rectangle r2 = new Rectangle(otro.x, otro.y, otro.ancho, otro.alto);
        return r1.intersects(r2);
    }

    public int getX() { return x; }
    public int getY() { return y; }
}

