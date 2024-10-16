package dataroast.controlador;

import dataroast.modelo.Datos;
import dataroast.modelo.Excursion;
import dataroast.vista.View;

import java.time.LocalDate;
import java.util.Scanner;

public class Controlador {
    private Datos datos = new Datos();
    private Scanner in = new Scanner(System.in);
    private View view = new View(this, in);

    public void ejecutarPrograma(){
        view.ejecutarMenuPrincipal();
    }

    public void agregarExcursion(int numDias, Double precioInscripcion, String codigo, String descripcion, LocalDate fecha){
        datos.getExcursiones().add(new Excursion(numDias, precioInscripcion, codigo, descripcion, fecha));
        System.out.println("[DEBUG]: Added: ");
        System.out.println(datos.getExcursiones().getLast());
    }
}
