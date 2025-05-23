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
import javax.swing.*;

public class Vehiculo extends GameObject {
    private int velocidad;
    private Image imagenVehiculo;

    public Vehiculo(int x, int y, int velocidad, String tipoImagen) {
        super(x, y, 80, 40);
        this.velocidad = velocidad;

        try {
            imagenVehiculo = new ImageIcon(getClass().getClassLoader().getResource(tipoImagen)).getImage();
        } catch (Exception e) {
            System.out.println("No se encontró la imagen del vehículo: " + tipoImagen);
        }
    }

    @Override
    public void actualizar() {
        x += velocidad;
        if (x > 800) x = -60;
    }

    @Override
    public void dibujar(Graphics g) {
        if (imagenVehiculo != null) {
            g.drawImage(imagenVehiculo, x, y, ancho, alto, null);
        } else {
            g.setColor(Color.RED);
            g.fillRect(x, y, ancho, alto);
        }
    }
}

