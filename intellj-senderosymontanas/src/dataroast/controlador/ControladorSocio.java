package dataroast.controlador;

import dataroast.modelo.Socio;
import java.util.ArrayList;
import java.util.List;

public class ControladorSocio {
    private List<Socio> listaSocios;

    public ControladorSocio() {
        listaSocios = new ArrayList<>();
    }

    public void agregarSocio(Socio nuevoSocio) {
        if (existeSocioPorNumero(nuevoSocio.getNumeroSocio())) {
            System.out.println("Error: El socio con número " + nuevoSocio.getNumeroSocio() + " ya existe. No se puede agregar.");
            return;
        }

        // Agregar socio si no existe
        listaSocios.add(nuevoSocio);
        System.out.println("Socio agregado correctamente: " + nuevoSocio);
    }

    public boolean existeSocioPorNumero(int numeroSocio) {
        for (Socio socio : listaSocios) {
            if (socio.getNumeroSocio() == numeroSocio) {
                return true; // El socio ya existe
            }
        }
        return false; // El socio no existe
    }

    public void mostrarSocios() {
        System.out.println("Lista de socios:");
        if (listaSocios.isEmpty()) {
            System.out.println("No hay socios registrados.");
            return;
        }
        for (Socio socio : listaSocios) {
            System.out.println(socio);
        }
    }

    // Método para obtener el número de socios
    public int getNumeroSocios() {
        return listaSocios.size(); // Devuelve el número total de socios
    }
}