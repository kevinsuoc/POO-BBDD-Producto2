package modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import util.DataErrorException;

import java.math.BigDecimal;

@Entity
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "numero_inscripcion")
    private int numeroInscripcion;

    @ManyToOne
    @JoinColumn(name = "id_socio")
    @NotNull(message = "El socio no puede ser nulo")
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "codigo_excursion")
    @NotNull(message = "La excursion no puede ser nula")
    private Excursion excursion;

    public Inscripcion() {
    }

    public Inscripcion(int numeroInscripcion, Socio socio, Excursion excursion) {
        setNumeroInscripcion(numeroInscripcion);
        setSocio(socio);
        setExcursion(excursion);
    }

    public Inscripcion(Socio socio, Excursion excursion) {
        setSocio(socio);
        setExcursion(excursion);
    }

    public int getNumeroInscripcion() {
        return numeroInscripcion;
    }

    public Socio getSocio() {
        return socio;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setNumeroInscripcion(int numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    private BigDecimal calcularPrecioExcursion(){
        BigDecimal precio = excursion.getPrecioInscripcion();

        if (socio instanceof SocioFederado)
            precio = SocioFederado.obtenerPrecioExcursionConDescuento(precio);
        return precio;
    }

    public String toString() {
        return
                "Numero de socio: " + socio.getNumeroSocio() + "\n" +
                "Nombre de socio: " + socio.getNombre() + "\n" +
                "Fecha de excursion: " + excursion.getFecha() + "\n" +
                "Descripcion: " + excursion.getDescripcion() + "\n" +
                "Importe total: " + calcularPrecioExcursion();
    }
}
