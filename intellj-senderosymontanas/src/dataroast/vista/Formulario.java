package dataroast.vista;

import dataroast.controlador.Controlador;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Formulario {
    Scanner in;
    Controlador controlador;

    public Formulario(Controlador controlador, Scanner in){
        this.in = in;
        this.controlador = controlador;
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
}
