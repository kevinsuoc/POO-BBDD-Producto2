package vista;

import controlador.ControladorInscripcion;
import modelo.Inscripcion;
import util.DataErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FormularioInscripcion extends Formulario{
    Scanner in;
    ControladorInscripcion controlador;

    public FormularioInscripcion(ControladorInscripcion controlador, Scanner in) {
        super(in);
        this.controlador = controlador;
    }

    public void nuevaInscripcion() {
        int numeroSocio = obtenerNumero("Ingresa el numero de socio");
        String codigoExcursion = obtenerString("Ingresa el codigo de excursion");

        try {
            Inscripcion inscripcion = controlador.agregarInscripcion(numeroSocio, codigoExcursion);
            System.out.println("Inscripcion agregada correctamente con id " + inscripcion.getNumeroInscripcion());
        } catch (DataErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminarInscripcion() {
        int numeroInscripcion = obtenerNumero("Ingresa el numero de inscripcion");

        try {
            controlador.eliminarInscripcion(numeroInscripcion);
            System.out.println("Inscripcion eliminada");
        } catch (DataErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarInscripciones() {
        LocalDate fechaInferior = null;
        LocalDate fechaSuperior = null;
        Boolean filtrarPorSocio = obtenerBool("¿Desea filtrar por socio?");
        Boolean filtrarPorFecha = obtenerBool("¿Desea filtrar por fecha?");
        int numeroSocio = 0;

        if (filtrarPorSocio) {
            numeroSocio = obtenerNumero("Ingrese el numero de socio");
        }
        if (filtrarPorFecha) {
            fechaInferior = obtenerFecha("Primera fecha limite");
            fechaSuperior = obtenerFecha("Segunda fecha limite");
        }
        List<Inscripcion> inscripciones;
        try {
            if (filtrarPorFecha && filtrarPorSocio)
                inscripciones = controlador.obtenerInscripciones(fechaInferior, fechaSuperior, numeroSocio);
            else if (filtrarPorSocio)
                inscripciones = controlador.obtenerInscripciones(numeroSocio);
            else if (filtrarPorFecha)
                inscripciones = controlador.obtenerInscripciones(fechaInferior, fechaSuperior);
            else
                inscripciones = controlador.obtenerInscripciones();

            for (Inscripcion inscripcion : inscripciones) {
                System.out.println("----- Inscripcion -----");
                System.out.println(inscripcion);
                System.out.println("--------------------");
            }
        } catch (DataErrorException e){
            System.out.println(e.getMessage());
        }
    }
}
