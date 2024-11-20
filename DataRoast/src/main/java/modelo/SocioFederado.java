package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import util.DataErrorException;

import java.math.BigDecimal;

@Entity
public class SocioFederado extends SocioAdulto {
    private static final BigDecimal descuentoCuotaMensual = new BigDecimal("0.5");
    private static final BigDecimal descuentoExcursion = new BigDecimal("0.1");

    @ManyToOne
    @NotNull(message = "La federacion no puede ser nula")
    private Federacion federacion;


    public SocioFederado (int numeroSocio, String nif, String nombre, Federacion federacion) {
        super(nombre, numeroSocio, nif);
        this.setFederacion(federacion);
    }

    public SocioFederado () {
    }

    public SocioFederado (String nif, String nombre, Federacion federacion) {
        super(nombre, nif);
        this.setFederacion(federacion);
    }

    public Federacion getFederacion() {
        return federacion;
    }

    public void setFederacion(Federacion federacion) {
        if (federacion == null){
            throw new DataErrorException("Federacion nula");
        }
        this.federacion = federacion;
    }

    public static BigDecimal obtenerCuotaConDescuento(BigDecimal cuotaBase){
        return cuotaBase.subtract(cuotaBase.multiply(descuentoCuotaMensual));
    }

    public static BigDecimal obtenerPrecioExcursionConDescuento(BigDecimal precioBase){
        return precioBase.subtract(precioBase.multiply(descuentoExcursion));
    }

    public String toString() {
        return super.toString() + "\n" +
                "Federacion: " + federacion.getNombre();
    }
}
