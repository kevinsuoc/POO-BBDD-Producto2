package modelo;

import jakarta.persistence.*;
import util.DataErrorException;

import static jakarta.persistence.InheritanceType.JOINED;

@Entity
@DiscriminatorColumn(name="tiposocio")
@Inheritance(strategy=JOINED)
public class Socio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_socio")
    private int numeroSocio;
    private String nombre;
//    @Column(name = "tiposocio")
//    @Enumerated(STRING)
//    private TipoSocio tipoSocio;

    public Socio(String nombre, int numeroSocio) {
        setNumeroSocio(numeroSocio);
        setNombre(nombre);
    }

    public Socio(){}

    public Socio(String nombre) {
        setNombre(nombre);
    }

    public String getNombre() {
        return nombre;
    }

    public int getNumeroSocio() {
        return numeroSocio;
    }

//    public TipoSocio getTipoSocio() {return tipoSocio;}

    public void setNombre(String nombre) {
        if (nombre.length() < 2)
            throw new DataErrorException("Nombre muy corto");
        this.nombre = nombre;
    }

//    public void setTipoSocio(TipoSocio tipoSocio){
//        this.tipoSocio = tipoSocio;
//    }

    public void setNumeroSocio(int numeroSocio) {
        if (numeroSocio <= 0)
            throw new DataErrorException("Numero de socio debe ser mayor a 0");
        this.numeroSocio = numeroSocio;
    }

    public String toString() {
        return "Nombre de socio: " + nombre + "\n" +
                "Numero de socio: " + numeroSocio;
    }
}
