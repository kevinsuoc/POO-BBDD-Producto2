package modelo;

import jakarta.persistence.Entity;
import util.DataErrorException;

@Entity
abstract public class SocioAdulto extends Socio {
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
        if (nif.length() < 7 || nif.length() > 10)
            throw new DataErrorException("Formato de NIF erroneo");
        this.nif = nif;
    }

    public String toString() {
        return  super.toString() + "\n" +
                "NIF: " + nif;
    }
}
