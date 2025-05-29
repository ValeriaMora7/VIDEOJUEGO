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
import java.awt.event.*;
import java.util.List;
import modelo.*;

public class VistaSeleccionPerfil extends JPanel {
    public interface Listener {
        void perfilSeleccionado(PerfilJugador perfil);
        void mostrarRanking();
    }

    private Listener listener;
    private JComboBox<String> comboPerfiles;
    private JButton btnSeleccionar, btnNuevo, btnRanking;
    private JLabel fondoLabel;

    public VistaSeleccionPerfil(Listener listener) {
        this.listener = listener;
        setLayout(null);
        setPreferredSize(new Dimension(800, 600));

        // Fondo estilo imagen con JLabel
        fondoLabel = new JLabel();
        ImageIcon fondo = new ImageIcon(getClass().getClassLoader().getResource("fondo_perfil.jpeg"));
        Image imagenEscalada = fondo.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        fondoLabel.setIcon(new ImageIcon(imagenEscalada));
        fondoLabel.setBounds(0, 0, 800, 600);
        setLayout(null);

        JLabel titulo = new JLabel("Seleccionar Perfil");
        titulo.setFont(new Font("Roboto", Font.BOLD, 34));
        titulo.setForeground(Color.BLACK);
        titulo.setBackground(Color.WHITE);  // Fondo blanco
        titulo.setOpaque(true);             // Hace visible el fondo
        titulo.setBounds(260, 40, 300, 40); 
        fondoLabel.add(titulo);


        comboPerfiles = new JComboBox<>();
        comboPerfiles.setBounds(260, 100, 280, 40);
        cargarPerfiles();
        fondoLabel.add(comboPerfiles);

        btnSeleccionar = crearBotonVerde("Jugar con ese Perfil", 260, 150);
        btnNuevo = crearBotonVerde("Nuevo Perfil", 260, 200);
        btnRanking = crearBotonVerde("Ver Ranking", 260, 250);

        fondoLabel.add(btnSeleccionar);
        fondoLabel.add(btnNuevo);
        fondoLabel.add(btnRanking);

        add(fondoLabel); // Agregar el fondo como último (en el fondo del panel)

        // Acciones
        btnSeleccionar.addActionListener(e -> {
            String nombre = (String) comboPerfiles.getSelectedItem();
            if (nombre != null) {
                PerfilJugador perfil = PerfilManager.buscarPerfil(nombre);
                if (perfil != null) listener.perfilSeleccionado(perfil);
            }
        });

        btnNuevo.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Ingresa tu nombre:");
            if (nombre != null && !nombre.trim().isEmpty()) {
                PerfilJugador nuevo = new PerfilJugador(nombre.trim());
                PerfilManager.actualizarPerfil(nuevo);
                comboPerfiles.addItem(nuevo.getNombre());
            }
        });

        btnRanking.addActionListener(e -> listener.mostrarRanking());
    }

    private JButton crearBotonVerde(String texto, int x, int y) {
        JButton boton = new JButton(texto);
        boton.setBounds(x, y, 280, 40);
        boton.setBackground(new Color(0, 153, 0));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);

         // Fuente más grande
    boton.setFont(new Font("Arial Black", Font.PLAIN, 16));

    return boton;
    }

    private void cargarPerfiles() {
        List<PerfilJugador> perfiles = PerfilManager.cargarPerfiles();
        for (PerfilJugador p : perfiles) {
            comboPerfiles.addItem(p.getNombre());
        }
    }
}
