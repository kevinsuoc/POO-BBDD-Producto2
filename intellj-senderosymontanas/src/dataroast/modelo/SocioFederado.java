package dataroast.modelo;

public class SocioFederado extends SocioAdulto {
    static final double descuentoCuotaMensual = 0.05;
    static final double descuentoExcursion = 0.1;
    private Federacion federacion;
    private String nif;


    public SocioFederado (String nombre, int numeroSocio, String nif) {
        super(nombre, numeroSocio);
        this.federacion = federacion;
        this.nif = nif;
    }

    public Federacion getFederacion() {
        return federacion;
    }

    public void setFederacion(Federacion federacion) {
        this.federacion = federacion;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Federacion: " + federacion.getNombre();
    }
}
