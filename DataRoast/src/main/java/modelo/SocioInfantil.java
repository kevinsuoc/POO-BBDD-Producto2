package modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import util.DataErrorException;

import java.math.BigDecimal;

@Entity
public class SocioInfantil extends Socio {
    private static final BigDecimal descuentoCuotaMensual = new BigDecimal("0.5");

    @Column(name="tutor")
    @NotNull(message = "El numero del socio tutor no puede ser nulo")
    @DecimalMin(value = "0", message = "El numero del socio tutor debe ser mayor a 0")
    private int numeroSocioTutor;

    public SocioInfantil() {}

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

    public static BigDecimal obtenerCuotaConDescuento(BigDecimal cuotaBase){
        return cuotaBase.subtract(cuotaBase.multiply(descuentoCuotaMensual));
    }

    public String toString() {
        return super.toString() + "\n" +
                "Numero de socio tutor: " + numeroSocioTutor;
    }
}
