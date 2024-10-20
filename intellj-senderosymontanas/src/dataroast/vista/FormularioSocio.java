package dataroast.vista;

import dataroast.controlador.Controlador;
import dataroast.controlador.ControladorSocio;
import dataroast.modelo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class FormularioSocio extends Formulario {
    private final ControladorSocio controlador;

    public FormularioSocio(ControladorSocio controladorSocio, Scanner in) {
        super(in);
        this.controlador = controladorSocio;
    }

    public void agregarSocioEstandar() {
        int numeroSocio = obtenerNumero("Ingresa el numero de socio");
        String nif = obtenerString("Ingresa el NIF del socio");
        String nombre = obtenerString("Ingresa el nombre");
        TipoSeguro tipoSeguro = obtenerTipoSeguro("Tipo de seguro");

        try {
            controlador.agregarSocioEstandar(numeroSocio, nif, nombre, tipoSeguro);
            System.out.println("Socio agregado");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void agregarSocioFederado() {
        int numeroSocio = obtenerNumero("Ingresa el numero de socio");
        String nif = obtenerString("Ingresa el NIF del socio");
        String nombre = obtenerString("Ingresa el nombre");
        String codigoFederacion = obtenerString("Ingresa el codigo de federacion");

        try {
            controlador.agregarSocioFederado(numeroSocio, nif, nombre, codigoFederacion);
            System.out.println("Socio agregado");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void agregarSocioInfantil() {
        int numeroSocio = obtenerNumero("Ingresa el numero de socio");
        String nombre = obtenerString("Ingresa el nombre");
        String nifAdulto = obtenerString("Ingresa el NIF de un socio adulto");
        System.out.print("Introduce el número de socio del Tutor: ");

        try {
            controlador.agregarSocioInfantil(numeroSocio, nombre, nifAdulto);
            System.out.println("Socio agregado");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void modificarSeguroSocioEstandar() {
        int numeroSocio = obtenerNumero("Ingresa el numero de socio");
        TipoSeguro tipoSeguro = obtenerTipoSeguro("Que tipo de seguro desea");
        try {
            controlador.cambiarTipoSeguro(numeroSocio, tipoSeguro);
            System.out.println("El tipo de seguro se ha cambiado");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminarSocio() {
        int numeroSocio = obtenerNumero("Ingresa el numero del socio a eliminar");

        try {
            controlador.eliminarSocio(numeroSocio);
            System.out.println("Socio eliminado");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarSocios() {
        boolean filtrarPorTipo = obtenerBool("Filtrar por tipo de socio");
        ArrayList<Socio> sociosFiltrados;

        try {
            if (!filtrarPorTipo)
                sociosFiltrados = controlador.obtenerSocios();
            else
                sociosFiltrados = controlador.obtenerSociosPorTipo(obtenerTipoSocio("Ingresa el tipo de socio"));
            for (Socio socio: sociosFiltrados) {
                System.out.println("----- Socio -----");
                System.out.println(socio);
                System.out.println("--------------------");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void mostrarFacturasMensuales() {
        int numeroSocio = obtenerNumero("Ingresa el numero de socio");
        try {
            Socio socio = controlador.obtenerSocioPorNumero(numeroSocio);
            ArrayList<Inscripcion> inscripcionesDelMes = controlador.obtenerInscripcionesMesSocio(numeroSocio);
            double baseCuota = Controlador.precioCuotaExcursionista;
            double totalCuota;
            double baseExcursiones = 0;
            double totalExcursiones;
            double total;

            for (Inscripcion inscripcion: inscripcionesDelMes)
                baseExcursiones += inscripcion.getExcursion().getPrecioInscripcion();
            if (socio instanceof SocioFederado)
                totalExcursiones = SocioFederado.obtenerPrecioExcursionConDescuento(baseExcursiones);
            else
                totalExcursiones = baseExcursiones;

            if (socio instanceof SocioFederado)
                totalCuota = SocioFederado.obtenerCuotaConDescuento(baseCuota);
            else if (socio instanceof  SocioInfantil)
                totalCuota = SocioInfantil.obtenerCuotaConDescuento(baseCuota);
            else
                totalCuota = baseCuota;

            total = totalCuota + totalExcursiones;

            System.out.println("----- Cuota mensual de socio -----");
            System.out.println(socio);
            System.out.println("Precio mensual de excursiones base: " + baseExcursiones);
            if (socio instanceof SocioFederado)
                System.out.println("Con descuento de socio federado: " + totalExcursiones);
            System.out.println("Precio de cuota excursionista base: " + baseCuota);
            if (socio instanceof  SocioFederado)
                System.out.println("Con descuento de socio federado: " + totalCuota);
            if (socio instanceof  SocioInfantil)
                System.out.println("Con descuento de socio infantil: " + totalCuota);
            System.out.println("Total: " + total);
            System.out.println("----------------------------------");

        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}