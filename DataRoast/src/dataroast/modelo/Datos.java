package dataroast.modelo;

import dataroast.DAO.DAOFactory;
import dataroast.DAO.FederacionDAO;
import dataroast.DAO.MysqlDAOFactory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static dataroast.modelo.TipoSeguro.*;

public class Datos {
    MysqlDAOFactory factoryDAO = (MysqlDAOFactory) DAOFactory.getDAOFactory();
    FederacionDAO federacionDAO =  factoryDAO.getFederacionDAO();

    ArrayList<Excursion> excursiones = new ArrayList<>();
    ArrayList<Inscripcion> inscripciones = new ArrayList<>();
    ArrayList<Seguro> seguros = new ArrayList<>();
    ArrayList<Socio> socios = new ArrayList<>();

    public Datos(){
        seguros.add(new Seguro(10, BASICO));
        seguros.add(new Seguro(15, COMPLETO));
    }

    // Excursiones
    public ArrayList<Excursion> obtenerExcursiones() {
        return excursiones;
    }

    public Excursion buscarExcursion(String codigo){
        for (Excursion excursion: excursiones){
            if (Objects.equals(excursion.getCodigo(), codigo))
                return excursion;
        }
        return null;
    }

    public ArrayList<Excursion> obtenerExcursiones(LocalDate fechaInferior, LocalDate fechaSuperior){
        ArrayList<Excursion> excursionesValidas = new ArrayList<>();
        if (fechaInferior.isAfter(fechaSuperior)) {
            throw new IllegalArgumentException("La fecha inferior es mayor que la superior");
        }
        for (Excursion excursion: excursiones){
            LocalDate fecha = excursion.getFecha();
            if (fecha.isAfter(fechaInferior) && fecha.isBefore(fechaSuperior))
                excursionesValidas.add(excursion);
        }
        return excursionesValidas;
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
    public Seguro buscarSeguroPorTipo(TipoSeguro tipoSeguro){
        if (tipoSeguro == BASICO)
            return seguros.get(0);
        if (tipoSeguro == COMPLETO)
            return seguros.get(1);
        return null;
    }

    // Federaciones
    public Federacion buscarFederacion(String codigoFederacion) {
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

    public void eliminarInscripcion(int numeroInscripcion) {
        if (numeroInscripcion <= 0)
            throw new ModelException("Numero de inscripcion invalido");
        for (Inscripcion inscripcion: inscripciones){
            if (inscripcion.getNumeroInscripcion() == numeroInscripcion) {
                if (inscripcion.getExcursion().getFecha().isBefore(LocalDate.now()))
                    throw new ModelException("Solo se pueden eliminar inscripciones de excursiones futuras");
                inscripciones.remove(inscripcion);
                return ;
            }
        }
        throw new ModelException("La inscripcion no existe");
    }
}
