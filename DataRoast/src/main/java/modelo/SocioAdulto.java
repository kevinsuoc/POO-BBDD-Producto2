package modelo;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import util.DataErrorException;

@Entity
abstract public class SocioAdulto extends Socio {
    @Size(min = 7, max = 13, message = "El NIF debe tener de {min} a {max} caracteres")
    @NotNull(message = "El NIF no puede ser nulo")
    private String nif;

    public SocioAdulto(String nombre, int numeroSocio, String nif) {
        super(nombre, numeroSocio);
        setNif(nif);
    }

    public SocioAdulto(){
    }

    public SocioAdulto(String nombre, String nif) {
        super(nombre);
        setNif(nif);
    }

    public String getNif(){
        return this.nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String toString() {
        return  super.toString() + "\n" +
                "NIF: " + nif;
    }
}
