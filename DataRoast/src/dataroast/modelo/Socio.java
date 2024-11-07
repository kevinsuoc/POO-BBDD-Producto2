package dataroast.modelo;

abstract public class Socio {
    private String nombre;
    private int numeroSocio;

    public Socio(String nombre, int numeroSocio) {
        setNumeroSocio(numeroSocio);
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
            throw new ModelException("Nombre muy corto");
    }

    public void setNumeroSocio(int numeroSocio) {
        if (numeroSocio <= 0)
            throw new ModelException("Numero de socio debe ser mayor a 0");
    }

    public String toString() {
        return "Nombre de socio: " + nombre + "\n" +
                "Numero de socio: " + numeroSocio;
    }
}
