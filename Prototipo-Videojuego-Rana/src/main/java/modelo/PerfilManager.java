/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author losmo
 */


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PerfilManager {

private static final String ARCHIVO = "perfiles.dat";

public static void guardarPerfil(PerfilJugador perfil) {
    List<PerfilJugador> perfiles = cargarPerfiles();
    perfiles.add(perfil);
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
        oos.writeObject(perfiles);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static List<PerfilJugador> cargarPerfiles() {
    File archivo = new File(ARCHIVO);
    if (!archivo.exists()) return new ArrayList<>();
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
        return (List<PerfilJugador>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
        return new ArrayList<>();
    }
}
}