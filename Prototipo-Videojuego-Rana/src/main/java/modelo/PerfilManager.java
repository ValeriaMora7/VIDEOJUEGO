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
import java.util.*;

public class PerfilManager {
private static final String ARCHIVO = "perfiles.dat";
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
public static PerfilJugador buscarPerfil(String nombre) {
    List<PerfilJugador> perfiles = cargarPerfiles();
    for (PerfilJugador p : perfiles) {
        if (p.getNombre().equalsIgnoreCase(nombre)) {
            return p;
        }
    }
    return null;
}


public static void guardarPerfiles(List<PerfilJugador> perfiles) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
        oos.writeObject(perfiles);
    } catch (IOException e) {
        e.printStackTrace();
    }
}

public static void actualizarPerfil(PerfilJugador perfilActualizado) {
    List<PerfilJugador> perfiles = cargarPerfiles();
    boolean actualizado = false;
    for (int i = 0; i < perfiles.size(); i++) {
        if (perfiles.get(i).getNombre().equals(perfilActualizado.getNombre())) {
            perfiles.set(i, perfilActualizado);
            actualizado = true;
            break;
        }
    }
    if (!actualizado) {
        perfiles.add(perfilActualizado);
    }
    guardarPerfiles(perfiles);
}

public static List<PerfilJugador> obtenerRanking() {
    List<PerfilJugador> perfiles = cargarPerfiles();
    perfiles.sort((a, b) -> Integer.compare(b.getPuntaje(), a.getPuntaje()));
    return perfiles;
}

}

