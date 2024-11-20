package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import util.DataErrorException;

import java.time.LocalDate;

@Entity
public class Excursion {
    @Id
    private String codigo;
    private String descripcion;
    @Column(name = "num_dias")
    private int numDias;
    @Column(name = "precio_inscripcion")
    private double precioInscripcion;
    private LocalDate fecha;

    public Excursion() {
    }

    public Excursion(int numDias, Double precioInscripcion, String codigo, String descripcion, LocalDate fecha) {
        setNumDias(numDias);
        setPrecioInscripcion(precioInscripcion);
        setCodigo(codigo);
        setDescripcion(descripcion);
        setFecha(fecha);
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
        if (numDias <= 0){
            throw new DataErrorException("La excursion debe durar un dia o mas");
        }
        this.numDias = numDias;
    }

    public void setPrecioInscripcion(double precioInscripcion) {
        if (precioInscripcion < 0){
            throw new DataErrorException("La excursion no puede tener un precio negativo");
        }
        this.precioInscripcion = precioInscripcion;
    }

    public void setCodigo(String codigo) {
        if (codigo.length() < 3){
            throw new DataErrorException("El codigo debe tener minimo tres letras");
        }
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        if (descripcion.length() < 10){
            throw new DataErrorException("La descricion debe tener al menos 10 caracteres");
        }
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
