package dataroast.modelo;

import java.time.LocalDate;

public class Excursion {
    private int numDias;
    private double precioInscripcion;
    private String codigo;
    private String descripcion;
    private LocalDate fecha;

    public Excursion(int numDias, float precioInscripcion, String codigo, String descripcion, LocalDate fecha)
    {
        this.numDias = numDias;
        this.precioInscripcion = precioInscripcion;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public int getNumDias() {
        return numDias;
    }

    public double getPrecioInscripcion() {
        return precioInscripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setNumDias(int numDias) {
        this.numDias = numDias;
    }

    public void setPrecioInscripcion(double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String toString() {
        return "Descripcion: " + descripcion + "\n" +
                "Precio: " + precioInscripcion + "\n" +
                "Duracion en dias: " + numDias + "\n" +
                "Codigo: " + codigo + "\n" +
                "Fecha: " + fecha.toString();
    }
}
