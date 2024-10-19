package dataroast.controlador;

import dataroast.modelo.Datos;
import dataroast.vista.View;
import java.util.Scanner;

// todo: tests de al menos dos metodos
// todo: video con ejecucion del programa en todas sus opciones
// todo: informe: enlace a video, enlace a github, autoevaluacion 0/20
public class Controlador {
    public final static double precioCuotaExcursionista = 10;
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
