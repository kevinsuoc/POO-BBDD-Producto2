package dataroast.modelo;

public class SocioFederado extends SocioAdulto {
    private static final double descuentoCuotaMensual = 0.05;
    private static final double descuentoExcursion = 0.1;
    private Federacion federacion;


    public SocioFederado (int numeroSocio, String nombre, String nif, Federacion federacion) {
        super(nombre, numeroSocio, nif);
        this.federacion = federacion;
    }

    public Federacion getFederacion() {
        return federacion;
    }

    public void setFederacion(Federacion federacion) {
        this.federacion = federacion;
    }

    public static double obtenerCuotaConDescuento(double cuotaBase){
        return cuotaBase - cuotaBase * descuentoCuotaMensual;
    }

    public static double obtenerPrecioExcursionConDescuento(double precioBase){
        return precioBase - precioBase * descuentoExcursion;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Federacion: " + federacion.getNombre();
    }
}
