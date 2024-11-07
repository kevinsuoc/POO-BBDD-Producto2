package dataroast.modelo;

abstract public class SocioAdulto extends Socio {
    private String nif;

    public SocioAdulto(String nombre, int numeroSocio, String nif) {
        super(nombre, numeroSocio);
        setNif(nif);
    }

    public String getNif(){
        return this.nif;
    }

    public void setNif(String nif) {
        if (nif.length() < 7 || nif.length() > 10)
            throw new ModelException("Formato de NIF erroneo");
    }

    public String toString() {
        return  super.toString() + "\n" +
                "NIF: " + nif;
    }
}
