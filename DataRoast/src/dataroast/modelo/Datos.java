package dataroast.modelo;

import dataroast.DAO.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Datos {
    MysqlDAOFactory factoryDAO = (MysqlDAOFactory) DAOFactory.getDAOFactory();
    FederacionDAO federacionDAO =  factoryDAO.getFederacionDAO();
    SeguroDAO seguroDAO = factoryDAO.getSeguroDAO();
    InscripcionDAO inscripcionDAO = factoryDAO.getInscripcionDAO();
    ExcursionDAO excursionDAO = factoryDAO.getExcursionDAO();

    ArrayList<Inscripcion> inscripciones = new ArrayList<>();
    ArrayList<Socio> socios = new ArrayList<>();

    public Datos(){
    }

    // Excursiones
    public Excursion agregarExcursion(Excursion excursion) {
        return excursionDAO.insert(excursion);
    }

    public List<Excursion> obtenerExcursiones() {
        return excursionDAO.findAll();
    }

    public Excursion obtenerExcursion(String codigo){
        return excursionDAO.find(codigo);
    }

    public List<Excursion> obtenerExcursiones(LocalDate fechaInferior, LocalDate fechaSuperior){
        return excursionDAO.findByDateRange(fechaInferior, fechaSuperior);
    }

    public boolean eliminarExcursion(String codigo) {
        return excursionDAO.delete(codigo);
    }

    public void actualizarExcursion(Excursion excursion) {
        excursionDAO.update(excursion);
    }

    // Socios
    public ArrayList<Socio> getSocios() {
        return socios;
    }

    public Socio buscarSocio(int numeroSocio){
        for (Socio socio: socios){
            if (socio.getNumeroSocio() == numeroSocio){
                return socio;
            }
        }
        return null;
    }

    public Socio buscarSocioNIF(String nif){
        for (Socio socio: socios){
            if (socio instanceof SocioAdulto && Objects.equals(((SocioAdulto) socio).getNif(), nif)){
                return socio;
            }
        }
        return null;
    }

    // Seguros
    public Seguro obtenerSeguro(TipoSeguro tipoSeguro){
        return seguroDAO.find(tipoSeguro);
    }

    public List<Seguro> obtenerSeguros() {
        return seguroDAO.findAll();
    }

    // Federaciones
    public Federacion obtenerFederacion(String codigoFederacion) {
        return federacionDAO.find(codigoFederacion);
    }

    public List<Federacion> buscarFederaciones(){
            return federacionDAO.findAll();
    }

    public Federacion agregarFederacion(Federacion federacion){
        return federacionDAO.insert(federacion);
    }

    public boolean eliminarFederacion(String codigo){
        return federacionDAO.delete(codigo);
    }

    public Federacion actualizarFederacion(Federacion federacion){
        return federacionDAO.update(federacion);
    }

    // Inscripciones
    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio){
        if (numeroSocio < 0)
            throw new ModelException("Numero de inscripcion invalido");
        if (fechaInferior.isAfter(fechaSuperior)){
            throw new ModelException("La fecha inferior está luego de la fecha superior");
        }
        ArrayList<Inscripcion> inscripcionesFiltradas = new ArrayList<>();

        for (Inscripcion inscripcion: inscripciones){
            LocalDate fecha = inscripcion.getExcursion().getFecha();
            if (fecha.isAfter(fechaInferior) && fecha.isBefore(fechaSuperior) && inscripcion.getSocio().getNumeroSocio() == numeroSocio)
                inscripcionesFiltradas.add(inscripcion);
        }
        return inscripcionesFiltradas;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior){
        if (fechaInferior.isAfter(fechaSuperior)){
            throw new ModelException("La fecha inferior está luego de la fecha superior");
        }
        ArrayList<Inscripcion> inscripcionesFiltradas = new ArrayList<>();

        for (Inscripcion inscripcion: inscripciones){
            LocalDate fecha = inscripcion.getExcursion().getFecha();
            if (fecha.isAfter(fechaInferior) && fecha.isBefore(fechaSuperior))
                inscripcionesFiltradas.add(inscripcion);
        }
        return inscripcionesFiltradas;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(int numeroSocio){
        if (numeroSocio <= 0)
            throw new ModelException("Numero de socio invalido");
        ArrayList<Inscripcion> inscripcionesFiltradas = new ArrayList<>();

        for (Inscripcion inscripcion: inscripciones){
            if (inscripcion.getSocio().getNumeroSocio() == numeroSocio)
                inscripcionesFiltradas.add(inscripcion);
        }
        return inscripcionesFiltradas;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(){
        return inscripciones;
    }

    public Inscripcion obtenerInscripcion(int numeroInscripcion) {
        if (numeroInscripcion <= 0)
            throw new ModelException("Numero de inscripcion invalido");
        for (Inscripcion inscripcion: inscripciones)
            if (inscripcion.getNumeroInscripcion() == numeroInscripcion)
                return inscripcion;
        return null;
    }

    public boolean eliminarInscripcion(int numeroInscripcion) {
        if (numeroInscripcion <= 0)
            throw new ModelException("Numero de inscripcion invalido");
        for (Inscripcion inscripcion: inscripciones){
            if (inscripcion.getNumeroInscripcion() == numeroInscripcion) {
                if (inscripcion.getExcursion().getFecha().isBefore(LocalDate.now()))
                    throw new ModelException("Solo se pueden eliminar inscripciones de excursiones futuras");
                inscripciones.remove(inscripcion);
                return false;
            }
        }
        throw new ModelException("La inscripcion no existe");
    }
}
