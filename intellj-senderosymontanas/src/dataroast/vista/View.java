package dataroast.vista;

import dataroast.controlador.Controlador;

import java.util.Scanner;

public class View {
    private Scanner in;
    private Controlador controlador;
    private Menu menuPrincipal;
    private Menu menuSocios;
    private Menu menuExcursiones;
    private Menu menuInscripciones;
    private Formulario formularios;

    public View(Controlador controlador, Scanner in){
        this.in = in;
        this.controlador = controlador;
        this.formularios = new Formulario(controlador, in);

        menuPrincipal = new Menu("Menu Principal", in);
        menuPrincipal.agregarOpcion("Gestion de socios");
        menuPrincipal.agregarOpcion("Gestion de excursiones");
        menuPrincipal.agregarOpcion("Gestion de inscripciones");

        menuSocios = new Menu("Gestion de Socios", in);
        menuSocios.agregarOpcion("Añadir socio estandar");
        menuSocios.agregarOpcion("Modificar tipo de seguro de un socio estandar");
        menuSocios.agregarOpcion("Añadir socio federado");
        menuSocios.agregarOpcion("Añadir socio infantil");
        menuSocios.agregarOpcion("Eliminar socio");
        menuSocios.agregarOpcion("Mostrar socios");
        menuSocios.agregarOpcion("Mostrar factura mensual por socios");

        menuExcursiones = new Menu("Gestion de Excursiones", in);
        menuExcursiones.agregarOpcion("Añadir excursion");
        menuExcursiones.agregarOpcion("Mostrar excursiones con filtro entre fechas");

        menuInscripciones = new Menu("Gestion de Inscripciones", in);
        menuInscripciones.agregarOpcion("Añadir inscripcion");
        menuInscripciones.agregarOpcion("Eliminar inscripcion");
        menuInscripciones.agregarOpcion("Mostrar inscripciones con las ocpiones de filtrar por socio y/o fechas");
    }

    public void ejecutarMenuPrincipal(){
        int opcionIngresada;

        while (true) {
            opcionIngresada = menuPrincipal.obtenerOpcionDeMenu();
            switch (opcionIngresada){
                case 0: System.out.println("Adios"); return;
                case 1: ejecutarMenuSocios(); break;
                case 2: ejecutarMenuExcursiones(); break;
                case 3: ejecutarMenuInscripciones(); break;
            }
        }
    }

    public void ejecutarMenuSocios(){
        int opcionIngresada;

        while (true) {
            opcionIngresada = menuSocios.obtenerOpcionDeMenu();
            switch (opcionIngresada) {
                case 0: return;
            }
        }
    }

    public void ejecutarMenuExcursiones(){
        int opcionIngresada;

        while (true) {
            opcionIngresada = menuExcursiones.obtenerOpcionDeMenu();
            switch (opcionIngresada) {
                case 0: return;
                case 1: formularios.nuevaExcursion(); break;
                case 2: break;
            }
        }
    }

    public void ejecutarMenuInscripciones(){
        int opcionIngresada;

        while (true) {
            opcionIngresada = menuInscripciones.obtenerOpcionDeMenu();
            switch (opcionIngresada) {
                case 0: return;
            }
        }
    }
}
