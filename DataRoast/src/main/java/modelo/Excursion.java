package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import util.DataErrorException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Excursion {
    @Id
    @NotNull(message = "El codigo no puede ser nulo")
    @Size(min = 3, max = 7, message = "El codigo debe tener de {min} a {max} caracteres")
    private String codigo;

    @NotNull(message = "La descripcion no puede ser nula")
    @Size(min = 5, max = 100, message = "La descripcion debe tener de {min} a {max} caracteres")
    private String descripcion;

    @Column(name = "num_dias")
    @NotNull(message = "La cantidad de dias no puede ser nula")
    @DecimalMin(value = "1", message = "La excursión debe durar al menos {value} días")
    @DecimalMax(value = "900", message = "La excursión no puede durar más que {value} días")
    private int numDias;

    @Column(name = "precio_inscripcion")
    @NotNull(message = "El precio de la inscripcion no puede ser nulo")
    @DecimalMin(value = "0.0", message = "La excursión debe valer al menos {value}")
    @DecimalMax(value = "999.0", message = "La excursión no puede valer más que {value}")
    private BigDecimal precioInscripcion;

    @Future(message = "La fecha debe estar en el futuro")
    @NotNull(message = "La fecha de excursion no puede ser nula")
    private LocalDate fecha;

    public Excursion() {
    }

    public Excursion(int numDias, BigDecimal precioInscripcion, String codigo, String descripcion, LocalDate fecha) {
        setNumDias(numDias);
        setPrecioInscripcion(precioInscripcion);
        setCodigo(codigo);
        setDescripcion(descripcion);
        setFecha(fecha);
    }

    public int getNumDias() {
        return numDias;
    }

    public BigDecimal getPrecioInscripcion() {
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

    public void setPrecioInscripcion(BigDecimal precioInscripcion) {
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
