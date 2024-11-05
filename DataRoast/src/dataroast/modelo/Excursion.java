package dataroast.modelo;

import java.time.LocalDate;

public class Excursion {
    private int numDias;
    private double precioInscripcion;
    private String codigo;
    private String descripcion;
    private LocalDate fecha;

    public Excursion(int numDias, Double precioInscripcion, String codigo, String descripcion, LocalDate fecha)
    {
        if (numDias <= 0){
            throw new ModelException("La excursion debe durar un dia o mas");
        }
        if (precioInscripcion < 0){
            throw new ModelException("La excursion no puede tener un precio negativo");
        }
        if (codigo.length() < 3){
            throw new ModelException("El codigo debe tener minimo tres letras");
        }
        if (descripcion.length() < 10){
            throw new ModelException("La descricion debe tener al menos 10 caracteres");
        }
        if (fecha.isBefore(LocalDate.now().plusDays(1))){
            throw new ModelException("La fecha debe ser en el futuro");
        }
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
