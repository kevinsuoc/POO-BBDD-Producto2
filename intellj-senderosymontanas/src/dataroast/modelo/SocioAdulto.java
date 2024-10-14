package dataroast.modelo;

abstract public class SocioAdulto extends Socio {
    private String nif;

    public SocioAdulto(String nombre, int numeroSocio, String nif) {
        super(nombre, numeroSocio);
        this.nif = nif;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String toString() {
        return  super.toString() + "\n" +
                "NIF: " + nif;
    }
}
