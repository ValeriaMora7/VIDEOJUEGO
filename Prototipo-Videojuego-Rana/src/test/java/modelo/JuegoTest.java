/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author losmo
 */


import modelo.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

public class JuegoTest {

    Juego juego;

    @BeforeEach
    void setup() {
        juego = new Juego();
        juego.iniciar();  // Inicializa objetos y rana
    }

    @Test
    void testColisionCambiaEstadoGameOver() {
        Rana rana = juego.getRana();
        assertNotNull(rana);

        rana.x = 100;
        rana.y = 100;

        Vehiculo vehiculo = (Vehiculo) juego.getObjetos().stream()
                .filter(o -> o instanceof Vehiculo)
                .findFirst()
                .orElse(null);
        assertNotNull(vehiculo);

        vehiculo.x = 100;
        vehiculo.y = 100;

        juego.actualizar();

        assertEquals(EstadoJuego.GAME_OVER, juego.getEstado());
    }

    @Test
void testMoverRanaArribaAumentaPuntaje() {
    int puntajeInicial = juego.getPuntaje();
    juego.getRana().setJuego(juego);  //asigna el juego a la rana
    juego.getRana().mover("arriba");
    assertEquals(puntajeInicial + 10, juego.getPuntaje()); // aumenta 10 puntos según el método aumentarPuntaje()
}


    @Test
    void testIniciarReseteaEstadoYPuntaje() {
        juego.aumentarPuntaje();
        juego.iniciar();

        assertEquals(EstadoJuego.PLAYING, juego.getEstado());
        assertEquals(0, juego.getPuntaje());
    }

    @Test
    void testSerializacionPerfilJugador() throws IOException, ClassNotFoundException {
        PerfilJugador perfil = juego.getPerfil();
        perfil.setNombre("TestPlayer");
        perfil.setPuntaje(42);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(perfil);
        oos.close();

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        PerfilJugador perfilDeserializado = (PerfilJugador) ois.readObject();
        ois.close();

        assertEquals("TestPlayer", perfilDeserializado.getNombre());
        assertEquals(42, perfilDeserializado.getPuntaje());
    }

    @Test
    void testVehiculoActualizaPosicionYResetea() {
        Vehiculo vehiculo = (Vehiculo) juego.getObjetos().stream()
                .filter(o -> o instanceof Vehiculo)
                .findFirst()
                .orElse(null);
        assertNotNull(vehiculo);

        vehiculo.x = 800;
        vehiculo.actualizar();

        assertEquals(-60, vehiculo.getX());
    }
}
