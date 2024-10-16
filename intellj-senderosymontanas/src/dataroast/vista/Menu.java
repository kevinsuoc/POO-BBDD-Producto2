package dataroast.vista;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private Scanner in;
    private String titulo;
    private ArrayList<String> opciones = new ArrayList<String>();

    public Menu(String titulo, Scanner in){
        this.titulo = titulo;
        this.in = in;
    }

    public void agregarOpcion(String opcion){
        opciones.add(opcion);
    }

    public void mostrar(){
        System.out.println("----- " + this.titulo + " -----");
        for (int i = 0; i < opciones.size(); i ++){
            System.out.println((i + 1) + ". " + opciones.get(i) + ".");
        }
        System.out.println("0. Volver/salir.");
    }

    public int obtenerOpcionDeMenu(){
        int opcionIngresada;

        while (true){
            mostrar();
            try {
                System.out.println("Ingresa una opcion");
                opcionIngresada = in.nextInt();
                if (opcionIngresada < 0 || opcionIngresada > opciones.size()) {
                    opcionIngresada = -1;
                    throw new InputMismatchException();
                }
                break ;
            } catch (InputMismatchException ignored) {
                System.out.println("La opcion ingresada es invalida");
                in.nextLine();
            }
        }
        return (opcionIngresada);
    }
}
