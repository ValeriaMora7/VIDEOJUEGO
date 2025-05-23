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
import java.awt. *;

public class VistaJuego extends JPanel {
private Juego juego;
private JButton btnPause;
private JLabel lblEstado, lblPuntaje;
private Image fondo;

public VistaJuego(Juego juego) {
    this.juego = juego;
    setPreferredSize(new Dimension(800, 600));
    setLayout(null);
    setBackground(Color.WHITE);

    
    fondo = new ImageIcon(getClass().getClassLoader().getResource("fondo_juego.png")).getImage();

    btnPause = new JButton("Pause");
    btnPause.setBounds(650, 60, 100, 30);
    add(btnPause);

    lblEstado = new JLabel("Estado: START");
    lblEstado.setBounds(20, 20, 200, 20);
    add(lblEstado);

   

    setFocusable(true);
    requestFocusInWindow();
}

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // Fondo del juego
    g.drawImage(fondo, 0, 0, getWidth(), getHeight(), null);

    if (juego.getEstado() == EstadoJuego.PLAYING) {
        for (GameObject obj : juego.getObjetos()) {
            obj.dibujar(g);
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 18));
        g.drawString("Puntaje: " + juego.getPuntaje(), 10, 20);

        if (juego.isMostrarMensaje()) {
            g.setColor(new Color(0, 0, 0, 180));
            g.fillRect(250, 250, 300, 60);
            g.setColor(Color.YELLOW);
            g.setFont(new Font("Arial", Font.BOLD, 22));
            g.drawString("¡Nivel Avanzado!", 310, 290);

            // Ocultar el mensaje después de 2 segundos
            Timer t = new Timer(2000, e -> {
                juego.ocultarMensajeNivel();
                repaint();
            });
            t.setRepeats(false);
            t.start();
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
    lblPuntaje.setText("Puntaje: " + juego.getPuntaje());
}

public void setPauseListener(java.awt.event.ActionListener handler) {
    btnPause.addActionListener(handler);
}
}






