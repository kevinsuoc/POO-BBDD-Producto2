package dataroast.modelo;

import dataroast.DAO.*;
import dataroast.util.DataErrorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Datos {
    MysqlDAOFactory factoryDAO = (MysqlDAOFactory) DAOFactory.getDAOFactory();
    FederacionDAO federacionDAO =  factoryDAO.getFederacionDAO();
    SeguroDAO seguroDAO = factoryDAO.getSeguroDAO();
    InscripcionDAO inscripcionDAO = factoryDAO.getInscripcionDAO();
    ExcursionDAO excursionDAO = factoryDAO.getExcursionDAO();
    SocioEstandarDAO socioEstandarDAO = factoryDAO.getSocioEstandarDAO();
    SocioFederadoDAO socioFederadoDAO = factoryDAO.getSocioFederadoDAO();
    SocioInfantilDAO socioInfantilDAO = factoryDAO.getSocioInfantilDAO();
    SocioDAO socioDAO = factoryDAO.getSocioDAO();

    public Datos(){
    }

    // Socios
    public SocioEstandar agregarSocioEstandar(SocioEstandar socio) {
        return socioEstandarDAO.insert(socio);
    }
    public SocioFederado agregarSocioFederado(SocioFederado socio) {
        return socioFederadoDAO.insert(socio);
    }
    public SocioInfantil agregarSocioInfantil(SocioInfantil socio) {
        return socioInfantilDAO.insert(socio);
    }
    public List<Socio> obtenerSocios() {
        List<SocioEstandar> sociosEstandar = socioEstandarDAO.findAll();
        List<SocioFederado> sociosFederados = socioFederadoDAO.findAll();
        List<SocioInfantil> sociosInfantiles = socioInfantilDAO.findAll();

        List<Socio> socios = new ArrayList<Socio>();
        socios.addAll(sociosEstandar);
        socios.addAll(sociosFederados);
        socios.addAll(sociosInfantiles);
        return socios;
    }
    public Socio obtenerSocio(int numeroSocio) {
        Socio socio = socioEstandarDAO.find(numeroSocio);
        if (socio != null)
            return socio;
        socio = socioInfantilDAO.find(numeroSocio);
        if (socio != null)
            return socio;
        socio = socioFederadoDAO.find(numeroSocio);
        return socio;
    }
    public List<SocioEstandar> obtenerSociosEstandar() {
        return socioEstandarDAO.findAll();
    }
    public List<SocioFederado> obtenerSociosFederados() {
        return socioFederadoDAO.findAll();
    }
    public List<SocioInfantil> obtenerSociosInfantiles() {
        return socioInfantilDAO.findAll();
    }
    public SocioEstandar actualizarSocioEstandar(SocioEstandar socioEstandar) {
        Socio socio = obtenerSocio(socioEstandar.getNumeroSocio());
        if (socio == null)
            throw new DataErrorException("El socio no existe");
        if (!(socio instanceof SocioEstandar))
            throw new DataErrorException("El socio no es un socio estandar");
        return socioEstandarDAO.update(socioEstandar);
    }
    public boolean eliminarSocio(int numeroSocio) {
        Socio socio = obtenerSocio(numeroSocio);
        if (socio == null)
            throw new DataErrorException("El socio no existe");
        List<Inscripcion> inscripciones = obtenerInscripciones(numeroSocio);
        if (!inscripciones.isEmpty())
            throw new DataErrorException("El socio no se puede eliminar por tener inscripciones");
        return socioDAO.delete(numeroSocio);
    }

    // Seguros
    public Seguro obtenerSeguro(TipoSeguro tipoSeguro){
        return seguroDAO.find(tipoSeguro);
    }

    // Federaciones
    public Federacion obtenerFederacion(String codigoFederacion) {
        return federacionDAO.find(codigoFederacion);
    }

    // Excursiones
    public Excursion agregarExcursion(Excursion excursion) {
        if (!excursion.getFecha().isAfter(LocalDate.now()))
            throw new DataErrorException("La fecha de excursion debe ser posterior al dia actual");
        Excursion exc = obtenerExcursion(excursion.getCodigo());
        if (exc != null)
            throw new DataErrorException("El codigo de excursion ya esta siendo utilizado");
        return excursionDAO.insert(excursion);
    }
    public Excursion obtenerExcursion(String codigo){
        return excursionDAO.find(codigo);
    }
    public List<Excursion> obtenerExcursiones(LocalDate fechaInferior, LocalDate fechaSuperior){
        return excursionDAO.findByDateRange(fechaInferior, fechaSuperior);
    }

    // Inscripciones
    public Inscripcion agregarInscripcion(Inscripcion inscripcion) {
        Socio socio = obtenerSocio(inscripcion.getSocio().getNumeroSocio());
        if (socio == null)
            throw new DataErrorException("El socio no existe");
        Excursion excursion = obtenerExcursion(inscripcion.getExcursion().getCodigo());
        if (excursion == null)
            throw  new DataErrorException("La excursion no existe");
        return inscripcionDAO.insert(inscripcion);
    }
    public List<Inscripcion> obtenerInscripciones(){
        return inscripcionDAO.findAll();
    }
    public List<Inscripcion> obtenerInscripciones(int numeroSocio){
        return inscripcionDAO.findBySocio(numeroSocio);
    }
    public List<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio){
        return inscripcionDAO.findByDateAndSocio(fechaInferior, fechaSuperior, numeroSocio);
    }
    public List<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior){
        return inscripcionDAO.findByDate(fechaInferior, fechaSuperior);
    }
    public boolean eliminarInscripcion(int numeroInscripcion) {
        Inscripcion inscripcion = inscripcionDAO.find(numeroInscripcion);
        if (inscripcion == null)
            throw new DataErrorException("La inscripcion no existe");
        if (!LocalDate.now().isBefore(inscripcion.getExcursion().getFecha()))
            throw new DataErrorException("La inscripcion no se puede eliminar la fecha actual es posterior a la excursion");
        return inscripcionDAO.delete(numeroInscripcion);
    }

    // ------------------------ Utiles para tests -----------------------

    // Seguros
    public List<Seguro> obtenerSeguros() {
        return seguroDAO.findAll();
    }

    // Excursiones
    public boolean eliminarExcursion(String codigo) {
        return excursionDAO.delete(codigo);
    }
    public void actualizarExcursion(Excursion excursion) {
        excursionDAO.update(excursion);
    }

    // Federaciones
    public Federacion agregarFederacion(Federacion federacion){
        return federacionDAO.insert(federacion);
    }
    public boolean eliminarFederacion(String codigo){
        return federacionDAO.delete(codigo);
    }
    public Federacion actualizarFederacion(Federacion federacion){
        return federacionDAO.update(federacion);
    }

    // Socios
    public SocioInfantil actualizarSocioInfantil(SocioInfantil socio) {
        return socioInfantilDAO.update(socio);
    }
    public SocioFederado actualizarSocioFederado(SocioFederado socioFederado) {
        return socioFederadoDAO.update(socioFederado);
    }
    public SocioFederado obtenerSocioFederado(int id) {return socioFederadoDAO.find(id);}
    public SocioEstandar obtenerSocioEstandar(int id) {return socioEstandarDAO.find(id);}
    public SocioInfantil obtenerSocioInfantil(int id) {return socioInfantilDAO.find(id);}
}
