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
import java.awt.event.ActionListener;

public class PantallaBienvenida extends JPanel {

    public PantallaBienvenida(Runnable alIniciarJuego) {
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        // Fondo
        JLabel fondoLabel = new JLabel();
        ImageIcon fondo = new ImageIcon(getClass().getClassLoader().getResource("fondo_inicio.jpeg"));
        Image imagenEscalada = fondo.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        fondoLabel.setIcon(new ImageIcon(imagenEscalada));
        fondoLabel.setBounds(0, 0, 800, 600);

        // Botón Start
        JButton btnStart = new JButton("Start");
        btnStart.setFont(new Font("Arial", Font.BOLD, 20));
        btnStart.setBackground(new Color(0x4CAF50));
        btnStart.setForeground(Color.WHITE);
        btnStart.setBounds(325, 260, 150, 50);
        btnStart.addActionListener(e -> alIniciarJuego.run());

        // Añadir elementos
        add(btnStart);
        add(fondoLabel);
    }
}








