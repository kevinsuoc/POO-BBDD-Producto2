package vista;

import modelo.Inscripcion;

import java.math.BigDecimal;
import java.time.LocalDate;

public class MostrarInscripcionUtil {


    private int idInscripcion;
    private int numeroSocio;
    private String nombreSocio;
    private LocalDate fecha;
    private String descripcion;
    private BigDecimal precio;

    public MostrarInscripcionUtil(Inscripcion inscripcion){
        this.idInscripcion = inscripcion.getNumeroInscripcion();
        this.numeroSocio = inscripcion.getSocio().getNumeroSocio();
        this.nombreSocio = inscripcion.getSocio().getNombre();
        this.fecha = inscripcion.getExcursion().getFecha();
        this.descripcion = inscripcion.getExcursion().getDescripcion();
        this.precio = inscripcion.calcularPrecioExcursion();
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

}
