package dataroast.modelo;


public class SocioEstandar extends SocioAdulto{
    private Seguro seguro;

    public SocioEstandar(int numeroSocio, String nif, String nombre, Seguro seguro) {
        super(nombre, numeroSocio, nif);
        this.seguro = seguro;
    }

    public Seguro getSeguro() {
        return seguro;
    }

    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    public String toString() {
        return switch (seguro.getTipoSeguro()) {
            case BASICO -> super.toString() + "\n" +
                    "Tipo seguro: BASICO";
            case COMPLETO -> super.toString() + "\n" +
                    "Tipo seguro: COMPLETO";
        };
    }
}
