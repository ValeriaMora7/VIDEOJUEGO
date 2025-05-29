/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author losmo
 */
import modelo.*;
import javax.swing.*;
import java.awt.*;

public class VistaJuego extends JPanel {
    private Juego juego;
    private JButton btnPause, btnResume;
    private JLabel lblEstado, lblPuntaje;
    private Image fondo;

    public VistaJuego(Juego juego) {
        this.juego = juego;
        setPreferredSize(new Dimension(800, 600));
        setLayout(null);
        setBackground(Color.WHITE);

        fondo = new ImageIcon(getClass().getClassLoader().getResource("fondo_juego.png")).getImage();

        btnPause = new JButton("Pausar");
        btnPause.setBounds(650, 60, 100, 30);
        add(btnPause);

        btnResume = new JButton("Reanudar");
        btnResume.setBounds(650, 100, 100, 30);
        add(btnResume);
        btnResume.setVisible(false); // solo visible en pausa

        lblEstado = new JLabel("Estado: START");
        lblEstado.setBounds(20, 20, 200, 20);
        add(lblEstado);

        setFocusable(true);
        requestFocusInWindow();

        // Acción de pausa
        btnPause.addActionListener(e -> {
            juego.setEstado(EstadoJuego.PAUSED);
            actualizarEstado();
            btnPause.setVisible(false);
            btnResume.setVisible(true);
            repaint();
        });

        // Acción de reanudar
        btnResume.addActionListener(e -> {
            juego.setEstado(EstadoJuego.PLAYING);
            actualizarEstado();
            btnPause.setVisible(true);
            btnResume.setVisible(false);
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);

        if (juego.getEstado() == EstadoJuego.PLAYING) {
            // Se Dibuja primero todos los objetos que no son la rana
            for (GameObject obj : juego.getObjetos()) {
                if (!(obj instanceof Rana)) {
                    obj.dibujar(g);
                }
            }
           // Se Dibuja la rana al final para que quede por encima
            for (GameObject obj : juego.getObjetos()) {
                if (obj instanceof Rana) {
                    obj.dibujar(g);
                }
            }
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.drawString("Puntaje: " + juego.getPuntaje(), 10, 20);

            if (juego.isMostrarMensaje()) {
                g.setColor(new Color(0, 0, 0, 180));
                g.fillRect(250, 250, 300, 60);
                g.setColor(Color.YELLOW);
                g.setFont(new Font("Arial", Font.BOLD, 22));
                g.drawString("¡Haz Ganado!", 310, 290);
            }

            if (juego.getEstado() == EstadoJuego.PAUSED) {
                g.setColor(new Color(0, 0, 0, 180));
                g.fillRect(200, 200, 400, 100);
                g.setColor(Color.WHITE);
                g.setFont(new Font("Arial", Font.BOLD, 24));
                g.drawString("Juego en pausa", 310, 260);
            }

        } else if (juego.getEstado() == EstadoJuego.GAME_OVER) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("¡Game Over! Presiona R para reiniciar", 230, 300);
            g.drawString("Puntaje final: " + juego.getPuntaje(), 320, 340);
        }
    }

    public void actualizarEstado() {
        lblEstado.setText("Estado: " + juego.getEstado().name());
    }
}







