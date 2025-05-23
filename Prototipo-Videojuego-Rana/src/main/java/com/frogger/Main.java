package com.frogger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author losmo
 */
import javax.swing.*;
import modelo.*;
import vista.*;
import controlador.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Frogger Prototipo");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(800, 600);
            ventana.setLocationRelativeTo(null);
            ventana.setResizable(false);

            PantallaBienvenida bienvenida = new PantallaBienvenida(() -> {
                Juego juego = new Juego();
                juego.iniciar();  // Solo se llama aquí

                VistaJuego vista = new VistaJuego(juego);
                new ControladorJuego(juego, vista);

                ventana.setContentPane(vista);
                ventana.revalidate();
                vista.requestFocusInWindow();
            });

            ventana.setContentPane(bienvenida);
            ventana.setVisible(true);
        });
    }
}
