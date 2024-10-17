package dataroast.modelo;


public class SocioEstandar extends SocioAdulto{
    private Seguro seguro;
    private final String nif;

    public SocioEstandar(String nombre, int numeroSocio, String nif) {
        super(nombre, numeroSocio);
        this.seguro = seguro;
        this.nif = nif;
    }

    public Seguro getSeguro() {
        return seguro;
    }

  public String getNif() { return nif ;}

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
