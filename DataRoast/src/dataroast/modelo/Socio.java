package dataroast.modelo;

import dataroast.util.DataErrorException;

public class Socio {
    private String nombre;
    private int numeroSocio;

    public Socio(String nombre, int numeroSocio) {
        setNumeroSocio(numeroSocio);
        setNombre(nombre);
    }

    public Socio(String nombre) {
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNombre(String nombre) {
        if (nombre.length() < 2)
            throw new DataErrorException("Nombre muy corto");
        this.nombre = nombre;
    }

    public void setNumeroSocio(int numeroSocio) {
        if (numeroSocio <= 0)
            throw new DataErrorException("Numero de socio debe ser mayor a 0");
        this.numeroSocio = numeroSocio;
    }

    public String toString() {
        return "Nombre de socio: " + nombre + "\n" +
                "Numero de socio: " + numeroSocio;
    }
}
