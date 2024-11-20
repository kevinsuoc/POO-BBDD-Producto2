package modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import util.DataErrorException;

@Entity
public class Federacion {
    @Id
    @NotNull(message = "El codigo no puede ser nulo")
    @Size(min = 3, max = 7, message = "El codigo debe tener de {min} a {max} caracteres")
    private String codigo;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min=2, max=35, message = "El nombre debe tener de {min} a {max} caracteres")
    private String nombre;

    public Federacion(){
    }

    public Federacion(String codigo, String nombre){
        setNombre(nombre);
        setCodigo(codigo);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return "Codigo de federacion: " + codigo + "\n" +
                "Nombre: " + nombre;
    }
}
