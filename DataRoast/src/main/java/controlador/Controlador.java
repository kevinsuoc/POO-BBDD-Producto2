package controlador;

import modelo.Datos;
import vista.View;

import java.math.BigDecimal;
import java.util.Scanner;

public class Controlador {
    public final static BigDecimal precioCuotaExcursionista = new BigDecimal(10);
    private final View view;
    private final ControladorExcursion controladorExcursion;
    private final ControladorInscripcion controladorInscripcion;
    private final ControladorSocio controladorSocio;

    public Controlador(Scanner in){
        Datos datos = new Datos();
        this.controladorExcursion = new ControladorExcursion(datos);
        this.controladorInscripcion = new ControladorInscripcion(datos);
        this.controladorSocio = new ControladorSocio(datos);
        this.view = new View(this, in);
    }

    public void ejecutarPrograma(){
        view.ejecutarMenuPrincipal();
    }

    public ControladorExcursion getControladorExcursion(){
        return this.controladorExcursion;
    }

    public ControladorInscripcion getControladorInscripcion(){
        return this.controladorInscripcion;
    }

    public ControladorSocio getControladorSocio(){
        return this.controladorSocio;
    }
}
