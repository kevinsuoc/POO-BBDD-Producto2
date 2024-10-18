package dataroast.controlador;

import dataroast.modelo.Datos;
import dataroast.modelo.Excursion;
import dataroast.modelo.Inscripcion;
import dataroast.modelo.Socio;
import dataroast.vista.View;

import javax.naming.ldap.Control;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Controlador {
    public final static double precioCuotaExcursionista = 10.5;
    private final View view;
    private final ControladorExcursion controladorExcursion;
    private final ControladorInscripcion controladorInscripcion;
    private final ControladorSocio controladorSocio;

    public Controlador(Scanner in){
        Datos datos = new Datos();
        this.view = new View(this, in);
        this.controladorExcursion = new ControladorExcursion(datos);
        this.controladorInscripcion = new ControladorInscripcion(datos);
        this.controladorSocio = new ControladorSocio(datos);
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
