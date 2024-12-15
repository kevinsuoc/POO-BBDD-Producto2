package controlador;

import modelo.*;
import util.DataErrorException;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

public class ControladorSocio {
    private final Datos datos;

    public ControladorSocio(Datos datos) {
        this.datos = datos;
    }

    public SocioEstandar agregarSocioEstandar(String nif, String nombre, TipoSeguro tipoSeguro) {
        Seguro seguro = datos.obtenerSeguro(tipoSeguro);
        if (seguro == null) {
            throw new DataErrorException("Seguro no encontrado");
        }
        return datos.agregarSocioEstandar(new SocioEstandar(nif, nombre, seguro));
    }

    public SocioFederado agregarSocioFederado(String nif, String nombre, String codigoFederacion) {
        Federacion federacion = datos.obtenerFederacion(codigoFederacion);
        if (federacion == null) {
            throw new DataErrorException("Federacion no encontrada");
        }
        return datos.agregarSocioFederado(new SocioFederado(nif, nombre, federacion));
    }

    public SocioInfantil agregarSocioInfantil(String nombre, int numeroTutor){
        return datos.agregarSocioInfantil(new SocioInfantil(nombre, numeroTutor));
    }

    public void eliminarSocio(int numeroSocio){
        datos.eliminarSocio(numeroSocio);
    }

    public List<Socio> obtenerSocios() {
        return datos.obtenerSocios();
    }

    public List<Socio> obtenerSociosPorTipo(TipoSocio tipoSocio) {
        List<Socio> socios = new ArrayList<>();
        switch (tipoSocio){
            case TipoSocio.ESTANDAR ->  socios.addAll(datos.obtenerSociosEstandar());
            case TipoSocio.INFANTIL ->  socios.addAll(datos.obtenerSociosInfantiles());
            case TipoSocio.FEDERADO -> socios.addAll(datos.obtenerSociosFederados());
        }
        return socios;
    }

    public Socio obtenerSocioPorNumero(int numeroSocio) {
        return datos.obtenerSocio(numeroSocio);
    }

    public void cambiarTipoSeguro(int numeroSocio, TipoSeguro tipoSeguro) {
        SocioEstandar socioEstandar = datos.obtenerSocioEstandar(numeroSocio);
        if (socioEstandar == null)
            throw new DataErrorException("El socio no existe");
        if (socioEstandar.getSeguro().getTipoSeguro() == tipoSeguro)
            throw new DataErrorException("El socio ya tiene ese seguro");
        socioEstandar.getSeguro().setTipoSeguro(tipoSeguro);
        datos.actualizarSocioEstandar(socioEstandar);
    }

    public List<Inscripcion> obtenerInscripcionesMesSocio(int numeroSocio){
        YearMonth thisMonth = YearMonth.now();
        LocalDate firstDayOfMonth = thisMonth.atDay(1);
        LocalDate lastDayOfMonth = thisMonth.atEndOfMonth();
        return datos.obtenerInscripciones(firstDayOfMonth, lastDayOfMonth, numeroSocio);
    }
}