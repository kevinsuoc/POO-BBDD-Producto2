package modelo;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import util.DataErrorException;

@Entity
public class SocioEstandar extends SocioAdulto{
    @ManyToOne
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
        if (seguro == null){
            throw new DataErrorException("Seguro nulo");
        }
        this.seguro = seguro;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Tipo seguro: " + seguro.getTipoSeguro().toString();
    }
}
