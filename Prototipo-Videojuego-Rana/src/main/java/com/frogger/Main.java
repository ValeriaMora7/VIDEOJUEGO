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
                VistaSeleccionPerfil seleccionPerfil = new VistaSeleccionPerfil(new VistaSeleccionPerfil.Listener() {
                    @Override
                    public void perfilSeleccionado(PerfilJugador perfil) {
                        Juego juego = new Juego(perfil);
                        VistaJuego vista = new VistaJuego(juego);
                        new ControladorJuego(juego, vista);
                        ventana.setContentPane(vista);
                        ventana.revalidate();
                        vista.requestFocusInWindow();
                    }

                    @Override
                    public void mostrarRanking() {
                        java.util.List<PerfilJugador> ranking = PerfilManager.obtenerRanking();
                        StringBuilder texto = new StringBuilder("Ranking de jugadores:\n");
                        int pos = 1;
                        for (PerfilJugador p : ranking) {
                            texto.append(pos++).append(". ").append(p.getNombre())
                                  .append(" - ").append(p.getPuntaje()).append(" pts\n");
                        }
                        JOptionPane.showMessageDialog(ventana, texto.toString(), "Ranking", JOptionPane.INFORMATION_MESSAGE);
                    }
                });

                ventana.setContentPane(seleccionPerfil);
                ventana.revalidate();
                seleccionPerfil.requestFocusInWindow();
            });

            ventana.setContentPane(bienvenida);
            ventana.setVisible(true);
        });
    }
}

