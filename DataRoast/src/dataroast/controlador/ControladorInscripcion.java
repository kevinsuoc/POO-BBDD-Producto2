package dataroast.controlador;

import dataroast.modelo.Datos;
import dataroast.modelo.Excursion;
import dataroast.modelo.Inscripcion;
import dataroast.modelo.Socio;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorInscripcion {
    private final Datos datos;

    public ControladorInscripcion(Datos datos){
        this.datos = datos;
    }

    public void agregarInscripcion(int numeroInscripcion, int numeroSocio, String codigoExcursion){
        Excursion excursion = datos.buscarExcursion(codigoExcursion);
        if (excursion == null){
            throw new InstanceNotFoundException("La excursion no existe");
        }
        Socio socio = datos.buscarSocio(numeroSocio);
        if (socio == null){
            throw new InstanceNotFoundException("El socio no existe");
        }
        if (datos.obtenerInscripcion(numeroInscripcion) != null)
            throw new UsedIdentifierException("Numero de inscripcion ya utilizado");
        datos.obtenerInscripciones().add(new Inscripcion(numeroInscripcion, socio, excursion));
    }

    public void eliminarInscripcion(int numeroInscripcion) {
        datos.eliminarInscripcion(numeroInscripcion);
    }

    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio){
        return datos.obtenerInscripciones(fechaInferior, fechaSuperior, numeroSocio);
    }

    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior){
        return datos.obtenerInscripciones(fechaInferior, fechaSuperior);
    }

    public ArrayList<Inscripcion> obtenerInscripciones(int numeroSocio){
        return datos.obtenerInscripciones(numeroSocio);
    }

    public ArrayList<Inscripcion> obtenerInscripciones(){
        return datos.obtenerInscripciones();
    }


}