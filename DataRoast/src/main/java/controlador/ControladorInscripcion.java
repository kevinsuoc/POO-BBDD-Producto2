package controlador;

import modelo.Datos;
import modelo.Excursion;
import modelo.Inscripcion;
import modelo.Socio;
import util.DataErrorException;

import java.time.LocalDate;
import java.util.List;

public class ControladorInscripcion {
    private final Datos datos;

    public ControladorInscripcion(Datos datos){
        this.datos = datos;
    }

    public Inscripcion agregarInscripcion(int numeroSocio, String codigoExcursion){
        Socio socio = datos.obtenerSocio(numeroSocio);
        if (socio == null){
            throw new DataErrorException("Socio no encontrado");
        }
        Excursion excursion = datos.obtenerExcursion(codigoExcursion);
        if (excursion == null) {
            throw new DataErrorException("Excursion no encontrada");
        }
        return datos.agregarInscripcion(new Inscripcion(socio, excursion));
    }

    public void eliminarInscripcion(int numeroInscripcion) {
        datos.eliminarInscripcion(numeroInscripcion);
    }

    public List<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio){
        return datos.obtenerInscripciones(fechaInferior, fechaSuperior, numeroSocio);
    }

    public List<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior){
        return datos.obtenerInscripciones(fechaInferior, fechaSuperior);
    }

    public List<Inscripcion> obtenerInscripciones(int numeroSocio){
        return datos.obtenerInscripciones(numeroSocio);
    }

    public List<Inscripcion> obtenerInscripciones(){
        return datos.obtenerInscripciones();
    }


}