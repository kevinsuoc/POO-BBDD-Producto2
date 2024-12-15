package modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class Seguro {
    @Id
    @Column(name="nombre")
    @NotNull(message = "El tipo de seguro no puede ser nulo")
    @Enumerated(STRING)
    private TipoSeguro tipoSeguro;

    @NotNull(message = "El precio del seguro no puede ser nulo")
    private double precio;

    public Seguro(){
    }

    public Seguro(double precio, TipoSeguro tipoSeguro){
        setPrecio(precio);
        setTipoSeguro(tipoSeguro);
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public double getPrecio() {
        return precio;
    }

    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String toString() {
        return switch (tipoSeguro) {
            case BASICO -> "Precio: " + precio + "\n" +
                    "Tipo seguro: BASICO";
            case COMPLETO -> "Precio: " + precio + "\n" +
                    "Tipo seguro: COMPLETO";
        };
    }

}
