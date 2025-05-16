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
        Juego juego = new Juego();
        VistaJuego vista = new VistaJuego(juego);
        new ControladorJuego(juego, vista);

        JFrame ventana = new JFrame("Frogger Prototipo");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.add(vista);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
