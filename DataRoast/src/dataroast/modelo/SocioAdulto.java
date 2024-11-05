package dataroast.modelo;

abstract public class SocioAdulto extends Socio {
    private String nif;

    public SocioAdulto(String nombre, int numeroSocio, String nif) {
        super(nombre, numeroSocio);
        if (nif.length() < 7 || nif.length() > 10)
            throw new ModelException("Formato de NIF erroneo");
        this.nif = nif;
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
