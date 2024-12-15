package modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@DiscriminatorColumn(name="tiposocio")
@Inheritance(strategy=JOINED)
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @DecimalMin(value = "0", message = "El numero de socio debe ser mayor a {value}")
    @Column(name = "id_socio")
    private int numeroSocio;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 35, message = "El nombre debe tener entre {min} y {max} caracteres")
    private String nombre;

    public Socio(String nombre, int numeroSocio) {
        setNumeroSocio(numeroSocio);
        setNombre(nombre);
    }

    public Socio(){}

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
        this.nombre = nombre;
    }

    public void setNumeroSocio(int numeroSocio) {
        this.numeroSocio = numeroSocio;
    }

    public String toString() {
        return "Nombre de socio: " + nombre + "\n" +
                "Numero de socio: " + numeroSocio;
    }
}
