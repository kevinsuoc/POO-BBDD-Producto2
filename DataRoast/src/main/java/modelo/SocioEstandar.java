package modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import util.DataErrorException;

@Entity
public class SocioEstandar extends SocioAdulto{
    @ManyToOne
    @NotNull(message = "El seguro no puede ser nulo")
    private Seguro seguro;

    public SocioEstandar(int numeroSocio, String nif, String nombre, Seguro seguro) {
        super(nombre, numeroSocio, nif);
        setSeguro(seguro);
    }

    public SocioEstandar(String nif, String nombre, Seguro seguro) {
        super(nombre, nif);
        setSeguro(seguro);
    }

    public SocioEstandar() {
        super();
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Tipo seguro: " + seguro.getTipoSeguro().toString();
    }
}
