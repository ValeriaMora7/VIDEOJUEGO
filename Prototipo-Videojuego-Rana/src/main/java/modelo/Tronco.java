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

public class Tronco extends GameObject {
private int velocidad;
private Image imagen;

public Tronco(int x, int y, int velocidad) {
    super(x, y, 170, 100); // más largo que un carro
    this.velocidad = velocidad;
    this.imagen = new ImageIcon(getClass().getClassLoader().getResource("tronco.png")).getImage();
}

@Override
public void actualizar() {
    x += velocidad;
    if (x > 800) x = -ancho;
}

public int getVelocidad() {
    return velocidad;
}

@Override
public void dibujar(Graphics g) {
    g.drawImage(imagen, x, y, ancho, alto, null);
}
}

