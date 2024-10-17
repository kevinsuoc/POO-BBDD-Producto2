package dataroast.vista;

import dataroast.controlador.Controlador;
import dataroast.modelo.Excursion;
import dataroast.modelo.Inscripcion;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Formulario {
    Scanner in;
    Controlador controlador;

    public Formulario(Controlador controlador, Scanner in){
        this.in = in;
        this.controlador = controlador;
    }

    public void nuevaInscripcion(){
        int numeroInscripcion = obtenerNumero("Ingresa el numero de inscripcion");
        int numeroSocio = obtenerNumero("Ingresa el numero de socio");
        String codigoExcursion = obtenerString("Ingresa el codigo de excursion");

        try {
            controlador.agregarInscripcion(numeroInscripcion, numeroSocio, codigoExcursion);
            System.out.println("Inscripcion agregada correctamente");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void eliminarInscripcion(){
        int numeroInscripcion = obtenerNumero("Ingresa el numero de inscripcion");

        try {
            controlador.eliminarInscripcion(numeroInscripcion);
            System.out.println("Inscripcion eliminada");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarInscripciones(){
        LocalDate fechaInferior = null;
        LocalDate fechaSuperior = null;
        Boolean filtrarPorSocio = obtenerBool("¿Desea filtrar por socio?");
        Boolean filtrarPorFecha = obtenerBool("¿Desea filtrar por fecha?");
        int numeroSocio = 0;

        if (filtrarPorSocio){
            numeroSocio = obtenerNumero("Ingrese el numero de socio");
        }
        if (filtrarPorFecha){
            fechaInferior = obtenerFecha("Fecha inferior");
            fechaSuperior = obtenerFecha("Fecha superior");
        }
        ArrayList<Inscripcion> inscripciones;
        if (filtrarPorFecha && filtrarPorSocio)
            inscripciones = controlador.obtenerInscripciones(fechaInferior, fechaSuperior, numeroSocio);
        else if (filtrarPorSocio)
            inscripciones = controlador.obtenerInscripciones(numeroSocio);
        else if (filtrarPorFecha)
            inscripciones = controlador.obtenerInscripciones(fechaInferior, fechaSuperior);
        else
            inscripciones = controlador.obtenerInscripciones();

        for (Inscripcion inscripcion: inscripciones){
            System.out.println("----- Inscripcion -----");
            System.out.println(inscripcion);
            System.out.println("--------------------");
        }
    }

    public void nuevaExcursion(){
        int numDias = obtenerNumero("Ingresa el numero de dias");
        double precioInscripcion = obtenerDouble("Ingresa el precio de la inscripcion");
        String codigo = obtenerString("Ingresa el codigo de excursion");
        String descripcion = obtenerString("Ingresa una descripcion");
        LocalDate fecha = obtenerFecha("Fecha de excursion...");

        try {
            controlador.agregarExcursion(numDias, precioInscripcion, codigo, descripcion, fecha);
            System.out.println("Excursion agregada correctamente");
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public void mostrarExcursiones(){
        LocalDate fechaMenor;
        LocalDate fechaMayor;
        ArrayList<Excursion> excursiones;

        fechaMenor = obtenerFecha("Fecha limite inferior...");
        fechaMayor = obtenerFecha("Fecha limite superior...");
        try {
            excursiones = controlador.obtenerExcursiones(fechaMenor, fechaMayor);
            for (Excursion excursion : excursiones) {
                System.out.println("----- Excursion -----");
                System.out.println(excursion);
                System.out.println("--------------------");
            }
        } catch (IllegalArgumentException e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    private int obtenerNumero(String msg){
        int numeroIngresado;

        while (true) {
            try {
                System.out.print(msg + ": ");
                numeroIngresado = in.nextInt();
                in.nextLine();
                return numeroIngresado;
            } catch (InputMismatchException ignored) {
                System.out.println("Debe ingresar un numero valido");
                in.nextLine();
            }
        }
    }

    private double obtenerDouble(String msg){
        double numeroIngresado;

        while (true) {
            try {
                System.out.print(msg + ": ");
                numeroIngresado = in.nextDouble();
                in.nextLine();
                return numeroIngresado;
            } catch (InputMismatchException ignored) {
                System.out.println("Debe ingresar un numero valido");
                in.nextLine();
            }
        }
    }

    private String obtenerString(String msg){
        String stringIngresado;

        System.out.print(msg + ": ");
        stringIngresado = in.nextLine();
        return stringIngresado;
    }

    private LocalDate obtenerFecha(String msg){
        LocalDate fecha;

        while (true)
        {
            try {
                System.out.println(msg);
                int dia = obtenerNumero("Ingresa el dia");
                int mes = obtenerNumero("Ingresa el mes");
                int año = obtenerNumero("Ingresa el año");

                fecha = LocalDate.of(año, mes, dia);
                return fecha;
            } catch (DateTimeException ignored){
                System.out.println("Error con la fecha ingresada");
            }
        }
    }

    private boolean obtenerBool(String msg){
        String respuesta;

        while (true){
            System.out.print(msg + " (y/n): ");
            respuesta = in.nextLine();
            if (respuesta.equals("y") || respuesta.equals("yes") || respuesta.equals("si") || respuesta.equals("s"))
                return true;
            if (respuesta.equals("n") || respuesta.equals("no"))
                return false;
        }
    }
}
