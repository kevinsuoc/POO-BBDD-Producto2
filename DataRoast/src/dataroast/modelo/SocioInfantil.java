package dataroast.modelo;

public class SocioInfantil extends Socio {
    private static final double descuentoCuotaMensual = 0.5;
    private SocioAdulto socioAdulto;

    public SocioInfantil(int numeroSocio, String nombre, SocioAdulto socioAdulto) {
        super(nombre, numeroSocio);
        if (socioAdulto == null){
            throw new ModelException("Socio adulto nulo");
        }
        this.socioAdulto = socioAdulto;
    }

    public SocioAdulto getSocioAdulto() {
        return socioAdulto;
    }

    public void setSocioAdulto(SocioAdulto socioAdulto) {
        this.socioAdulto = socioAdulto;
    }

    public static double obtenerCuotaConDescuento(double cuotaBase){
        return cuotaBase - cuotaBase * descuentoCuotaMensual;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Numero de socio tutor: " + socioAdulto.getNumeroSocio();
    }
}
