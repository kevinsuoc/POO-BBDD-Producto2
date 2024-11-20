package modelo;

import jakarta.persistence.*;
import util.DataErrorException;

import static jakarta.persistence.EnumType.STRING;

@Entity
public class Seguro {
    @Id
    @Column(name="nombre")
    @Enumerated(STRING)
    private TipoSeguro tipoSeguro;
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
        if (tipoSeguro == null)
            throw new DataErrorException("tipoSeguro null");
        this.tipoSeguro = tipoSeguro;
    }

    public void setPrecio(double precio) {
        if (precio < 0)
            throw new DataErrorException("El precio del seguro no puede ser negativo");
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
