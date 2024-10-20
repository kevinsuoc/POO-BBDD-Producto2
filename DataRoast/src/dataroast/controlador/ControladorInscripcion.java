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
        Excursion excursion = datos.encontrarExcursionPorCodigo(codigoExcursion);
        if (excursion == null){
            throw new InstanceNotFoundException("La excursion no existe");
        }
        Socio socio = datos.encontrarSocioPorNumeroSocio(numeroSocio);
        if (socio == null){
            throw new InstanceNotFoundException("El socio no existe");
        }
        if (datos.obtenerInscripcionPorNumero(numeroInscripcion) != null)
            throw new UsedIdentifierException("Numero de inscripcion ya utilizado");
        datos.getInscripciones().add(new Inscripcion(numeroInscripcion, socio, excursion));
    }

    public void eliminarInscripcion(int numeroInscripcion) {
        ArrayList<Inscripcion> inscripciones = datos.getInscripciones();

        for (Inscripcion inscripcion: inscripciones){
            if (inscripcion.getNumeroInscripcion() == numeroInscripcion) {
                if (inscripcion.getExcursion().getFecha().isBefore(LocalDate.now()))
                    throw new IllegalArgumentException("Solo se pueden eliminar inscripciones de excursiones futuras");
                inscripciones.remove(inscripcion);
                return ;
            }
        }
        throw new InstanceNotFoundException("La inscripcion no existe");
    }

    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio){
        if (fechaInferior.isAfter(fechaSuperior))
            throw new IllegalArgumentException("La fecha inferior es mayor que la superior");
        if (numeroSocio <= 0)
            throw new IllegalArgumentException("Numero de socio invalido");
        return datos.obtenerInscripciones(fechaInferior, fechaSuperior, numeroSocio);
    }

    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior){
        if (fechaInferior.isAfter(fechaSuperior))
            throw new IllegalArgumentException("La fecha inferior es mayor que la superior");
        return datos.obtenerInscripciones(fechaInferior, fechaSuperior);
    }

    public ArrayList<Inscripcion> obtenerInscripciones(int numeroSocio){
        if (numeroSocio <= 0)
            throw new InstanceNotFoundException("Numero de socio invalido");
        return datos.obtenerInscripciones(numeroSocio);
    }

    public ArrayList<Inscripcion> obtenerInscripciones(){
        return datos.getInscripciones();
    }


}
