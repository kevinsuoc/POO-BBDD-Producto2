package modelo;

import util.DataErrorException;

public class SocioInfantil extends Socio {
    private static final double descuentoCuotaMensual = 0.5;
    private int numeroSocioTutor;

    public SocioInfantil(int numeroSocio, String nombre, int numeroSocioTutor) {
        super(nombre, numeroSocio);
        setNumeroSocioTutor(numeroSocioTutor);
    }

    public SocioInfantil(String nombre, int numeroSocioTutor) {
        super(nombre);
        setNumeroSocioTutor(numeroSocioTutor);
    }

    public int getNumeroSocioTutor(){ return this.numeroSocioTutor; }

    public void setNumeroSocioTutor(int numeroSocioTutor){
        if (numeroSocioTutor <= 0)
            throw new DataErrorException("Numero de socio tutor no puede ser menor que 0");
        this.numeroSocioTutor = numeroSocioTutor;
    }

    public static double obtenerCuotaConDescuento(double cuotaBase){
        return cuotaBase - cuotaBase * descuentoCuotaMensual;
    }

    public String toString() {
        return super.toString() + "\n" +
                "Numero de socio tutor: " + numeroSocioTutor;
    }
}
