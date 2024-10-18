package dataroast.modelo;

import java.util.Objects;

public class Inscripcion {
    private int numeroInscripcion;
    private Socio socio;
    private Excursion excursion;

    public Inscripcion(int numeroInscripcion, Socio socio, Excursion excursion) {
        if (numeroInscripcion <= 0){
            throw new IllegalArgumentException("El numero de inscripcion debe ser positivo");
        }
        this.numeroInscripcion = numeroInscripcion;
        this.socio = socio;
        this.excursion = excursion;
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
        this.numeroInscripcion = numeroInscripcion;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void setExcursion(Excursion excursion) {
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
