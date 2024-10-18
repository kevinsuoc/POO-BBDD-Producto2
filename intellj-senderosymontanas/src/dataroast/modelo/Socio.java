package dataroast.modelo;

abstract public class Socio {
    private String nombre;
    private int numeroSocio;

    private String nif;

    public Socio(String nombre, int numeroSocio) {
        if (numeroSocio <= 0)
            throw new IllegalArgumentException("Numero de socio debe ser mayor a 0");
        if (nombre.length() < 2)
            throw new IllegalArgumentException("Nombre invalido");

        this.nombre = nombre;
        this.numeroSocio = numeroSocio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public String getNif() { return nif ; }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String toString() {
        return "Nombre de socio: " + nombre + "\n" +
                "Numero de socio: " + numeroSocio + "\n" +
                   "NIF de socio: " + nif;
    }
}
