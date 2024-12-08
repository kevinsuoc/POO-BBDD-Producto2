package vista;

import controlador.ControladorExcursion;
import modelo.Excursion;
import util.DataErrorException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class FormularioExcursion extends Formulario {
    ControladorExcursion controlador;

    public FormularioExcursion(ControladorExcursion controlador, Scanner in) {
        super(in);
        this.controlador = controlador;
    }

    public void nuevaExcursion() {
        int numDias = obtenerNumero("Ingresa el numero de dias");
        BigDecimal precioInscripcion = obtenerBigDecimal("Ingresa el precio de la inscripcion");
        String codigo = obtenerString("Ingresa el codigo de excursion");
        String descripcion = obtenerString("Ingresa una descripcion");
        LocalDate fecha = obtenerFecha("Fecha de excursion...");

        try {
            controlador.agregarExcursion(numDias, precioInscripcion, codigo, descripcion, fecha);
            System.out.println("Excursion agregada correctamente");
        } catch (DataErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    public void mostrarExcursiones() {
        LocalDate fechaMenor;
        LocalDate fechaMayor;
        List<Excursion> excursiones;

        fechaMenor = obtenerFecha("Fecha limite inferior...");
        fechaMayor = obtenerFecha("Fecha limite superior...");
        try {
            excursiones = controlador.obtenerExcursiones(fechaMenor, fechaMayor);
            for (Excursion excursion : excursiones) {
                System.out.println("----- Excursion -----");
                System.out.println(excursion);
                System.out.println("--------------------");
            }
        } catch (DataErrorException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
