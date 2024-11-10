package dataroast.modelo;

import dataroast.util.DataErrorException;

public class Inscripcion {
    private int numeroInscripcion;
    private Socio socio;
    private Excursion excursion;

    public Inscripcion(int numeroInscripcion, Socio socio, Excursion excursion) {
        setNumeroInscripcion(numeroInscripcion);
        setSocio(socio);
        setExcursion(excursion);
    }

    public Inscripcion(Socio socio, Excursion excursion) {
        setSocio(socio);
        setExcursion(excursion);
    }

    public int getNumeroInscripcion() {
        return numeroInscripcion;
    }

    public Socio getSocio() {
        return socio;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setNumeroInscripcion(int numeroInscripcion) {
        if (numeroInscripcion <= 0){
            throw new DataErrorException("El numero de inscripcion debe ser positivo");
        }
        this.numeroInscripcion = numeroInscripcion;
    }

    public void setSocio(Socio socio) {
        if (socio == null) {
            throw new DataErrorException("Socio nulo");
        }
        this.socio = socio;
    }

    public void setExcursion(Excursion excursion) {
        if (excursion == null) {
            throw new DataErrorException("Excursion nula");
        }
        this.excursion = excursion;
    }

    private double calcularPrecioExcursion(){
        double precio = excursion.getPrecioInscripcion();

        if (socio instanceof SocioFederado)
            precio = SocioFederado.obtenerPrecioExcursionConDescuento(precio);
        return precio;
    }

    public String toString() {
        return
                "Numero de socio: " + socio.getNumeroSocio() + "\n" +
                "Nombre de socio: " + socio.getNombre() + "\n" +
                "Fecha de excursion: " + excursion.getFecha() + "\n" +
                "Descripcion: " + excursion.getDescripcion() + "\n" +
                "Importe total: " + calcularPrecioExcursion();
    }
}
