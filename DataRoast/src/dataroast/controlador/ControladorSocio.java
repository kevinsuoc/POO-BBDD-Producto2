package dataroast.controlador;

import dataroast.modelo.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class ControladorSocio {
    private final Datos datos;

    public ControladorSocio(Datos datos) {
        this.datos = datos;
    }

    public void agregarSocioEstandar(int numeroSocio, String nif, String nombre, TipoSeguro tipoSeguro) {
        if (datos.buscarSocio(numeroSocio) != null) {
            throw new UsedIdentifierException("El socio con numero " + numeroSocio + " ya existe. No se puede agregar.");
        }
        datos.getSocios().add(new SocioEstandar(numeroSocio, nif, nombre, datos.buscarSeguroPorTipo(tipoSeguro)));
    }

    public void agregarSocioFederado(int numeroSocio, String nif, String nombre, String codigoFederacion) {
        Federacion federacion;

        if (datos.buscarSocio(numeroSocio) != null) {
            throw new UsedIdentifierException("El socio con numero " + numeroSocio + " ya existe. No se puede agregar.");
        }
        federacion = datos.buscarFederacion(codigoFederacion);
        if (federacion == null){
            throw new InstanceNotFoundException("El codigo no corresponde a ninguna federacion");
        }
        datos.getSocios().add(new SocioFederado(numeroSocio, nif, nombre, federacion));
    }

    public void agregarSocioInfantil(int numeroSocio, String nombre, String nifAdulto){
        Socio socioAdulto;

        if (datos.buscarSocio(numeroSocio) != null) {
            throw new UsedIdentifierException("El socio con numero " + numeroSocio + " ya existe. No se puede agregar.");
        }
        socioAdulto = datos.buscarSocioNIF(nifAdulto);
        if (!(socioAdulto instanceof SocioAdulto))
            throw new IllegalArgumentException("El socio referido no es adulto");
        datos.getSocios().add(new SocioInfantil(numeroSocio, nombre, (SocioAdulto) socioAdulto));
    }

    public void eliminarSocio(int numeroSocio){
        Socio socio = datos.buscarSocio(numeroSocio);

        if (socio == null){
            throw new InstanceNotFoundException("Socio no encontrado");
        }
        for (Inscripcion inscripcion: datos.obtenerInscripciones()){
            if (inscripcion.getSocio().getNumeroSocio() == numeroSocio){
                throw new IllegalArgumentException("El socio no se puede eliminar, está inscrito a una excursión");
            }
        }
        datos.getSocios().remove(socio);
    }


    public ArrayList<Socio> obtenerSocios() {
        return datos.getSocios();
    }

    public ArrayList<Socio> obtenerSociosPorTipo(TipoSocio tipoSocio) {
        ArrayList<Socio> sociosFiltrados = new ArrayList<>();
        ArrayList<Socio> socios = datos.getSocios();

        for (Socio socio: socios){
            if (tipoSocio == TipoSocio.ESTANDAR && socio instanceof SocioEstandar)
                sociosFiltrados.add(socio);
            else if (tipoSocio == TipoSocio.INFANTIL && socio instanceof SocioInfantil)
                sociosFiltrados.add(socio);
            else if (tipoSocio == TipoSocio.FEDERADO && socio instanceof  SocioFederado)
                sociosFiltrados.add(socio);
        }
        return sociosFiltrados;
    }

    public Socio obtenerSocioPorNumero(int numeroSocio) {
        if (numeroSocio <= 0)
            throw new IllegalArgumentException("Numero de socio invalido");
        Socio socio = datos.buscarSocio(numeroSocio);
        if (socio == null)
            throw new InstanceNotFoundException("El socio no existe");
        return socio;
    }

    public void cambiarTipoSeguro(int numeroSocio, TipoSeguro tipoSeguro) {
        Socio socio = datos.buscarSocio(numeroSocio);
        if (socio == null)
            throw new InstanceNotFoundException("El socio no existe");
        if (!(socio instanceof SocioEstandar))
            throw new IllegalArgumentException("Solo se puede cambiar el seguro de un socio estandar");
        if (((SocioEstandar) socio).getSeguro().getTipoSeguro() == tipoSeguro)
            throw new IllegalArgumentException("El socio ya tiene ese seguro");
        Seguro seguro = datos.buscarSeguroPorTipo(tipoSeguro);
        ((SocioEstandar) socio).setSeguro(seguro);
    }

    public ArrayList<Inscripcion> obtenerInscripcionesMesSocio(int numeroSocio){
        YearMonth thisMonth = YearMonth.now();
        LocalDate firstDayOfMonth = thisMonth.atDay(1);
        LocalDate lastDayOfMonth = thisMonth.atEndOfMonth();

        return datos.obtenerInscripciones(firstDayOfMonth, lastDayOfMonth, numeroSocio);
    }
}