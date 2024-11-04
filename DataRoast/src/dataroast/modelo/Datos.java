package dataroast.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import static dataroast.modelo.TipoSeguro.*;

/*
* El criterio para separar metodos en esta clase y una clase controlador es
* si un metodo puede ser pensado como una query a una base de datos y utilizado
* por tdo tipo de controlador. En ese caso, debe encontrarse en esta clase.
* */
public class Datos {
    ArrayList<Federacion> federaciones = new ArrayList<>();
    ArrayList<Excursion> excursiones = new ArrayList<>();
    ArrayList<Inscripcion> inscripciones = new ArrayList<>();
    ArrayList<Seguro> seguros = new ArrayList<>();
    ArrayList<Socio> socios = new ArrayList<>();

    public Datos(){
        seguros.add(new Seguro(10, BASICO));
        seguros.add(new Seguro(15, COMPLETO));
        federaciones.add(new Federacion("abc", "Ejemplo 1"));
        federaciones.add(new Federacion("dce", "Ejemplo 2"));
    }

    // Obtener excursiones
    public ArrayList<Excursion> getExcursiones() {

        return excursiones;
    }

    public Excursion encontrarExcursionPorCodigo(String codigo){
        for (Excursion excursion: excursiones){
            if (Objects.equals(excursion.getCodigo(), codigo))
                return excursion;
        }
        return null;
    }

    // Obtener socios
    public ArrayList<Socio> getSocios() {
        return socios;
    }

    public Socio encontrarSocioPorNumeroSocio(int numeroSocio){
        for (Socio socio: socios){
            if (socio.getNumeroSocio() == numeroSocio){
                return socio;
            }
        }
        return null;
    }

    public Socio encontrarSocioPorNif(String nif){
        for (Socio socio: socios){
            if (socio instanceof SocioAdulto && Objects.equals(((SocioAdulto) socio).getNif(), nif)){
                return socio;
            }
        }
        return null;
    }

    // Obtener seguros
    public Seguro getSeguroPorTipo(TipoSeguro tipoSeguro){
        if (tipoSeguro == BASICO)
            return seguros.get(0);
        if (tipoSeguro == COMPLETO)
            return seguros.get(1);
        return null;
    }

    // Obtener federaciones
    public ArrayList<Federacion> getFederaciones(){
        return federaciones;
    }

    public Federacion encontrarFederacionPorCodigo(String codigoFederacion) {
        for (Federacion federacion: federaciones){
            if (Objects.equals(federacion.getCodigo(), codigoFederacion))
                return federacion;
        }
        return null;
    }

    // Obtener inscripciones
    public ArrayList<Inscripcion> getInscripciones(){
        return inscripciones;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio){
        ArrayList<Inscripcion> inscripcionesFiltradas = new ArrayList<>();

        for (Inscripcion inscripcion: inscripciones){
            LocalDate fecha = inscripcion.getExcursion().getFecha();
            if (fecha.isAfter(fechaInferior) && fecha.isBefore(fechaSuperior) && inscripcion.getSocio().getNumeroSocio() == numeroSocio)
                inscripcionesFiltradas.add(inscripcion);
        }
        return inscripcionesFiltradas;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior){
        ArrayList<Inscripcion> inscripcionesFiltradas = new ArrayList<>();

        for (Inscripcion inscripcion: inscripciones){
            LocalDate fecha = inscripcion.getExcursion().getFecha();
            if (fecha.isAfter(fechaInferior) && fecha.isBefore(fechaSuperior))
                inscripcionesFiltradas.add(inscripcion);
        }
        return inscripcionesFiltradas;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(int numeroSocio){
        ArrayList<Inscripcion> inscripcionesFiltradas = new ArrayList<>();

        for (Inscripcion inscripcion: inscripciones){
            if (inscripcion.getSocio().getNumeroSocio() == numeroSocio)
                inscripcionesFiltradas.add(inscripcion);
        }
        return inscripcionesFiltradas;
    }

    public Inscripcion obtenerInscripcionPorNumero(int numeroInscripcion) {
        for (Inscripcion inscripcion: inscripciones)
            if (inscripcion.getNumeroInscripcion() == numeroInscripcion)
                return inscripcion;
        return null;
    }
}
