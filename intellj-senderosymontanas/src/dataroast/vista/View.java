package dataroast.vista;

import dataroast.controlador.Controlador;
import dataroast.controlador.ControladorSocio;

import java.util.Scanner;

public class View {
    private final Menu menuPrincipal;
    private final Menu menuSocios;
    private final Menu menuExcursiones;
    private final Menu menuInscripciones;
    private final FormularioSocio formularioSocio;
    private final FormularioInscripcion formularioInscripcion;
    private final FormularioExcursion formularioExcursion;

    public View(Controlador controlador, Scanner in){
        this.formularioSocio = new FormularioSocio(controlador.getControladorSocio(), in);
        this.formularioExcursion = new FormularioExcursion(controlador.getControladorExcursion(), in);
        this.formularioInscripcion = new FormularioInscripcion(controlador.getControladorInscripcion(), in);


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
        menuInscripciones.agregarOpcion("Mostrar inscripciones con las opciones  de filtrar por socio y/o fechas");
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
                case 1: formularioSocio.agregarSocioEstandar(); break;
                case 2: formularioSocio.modificarSeguroSocioEstandar(); break;
                case 3: formularioSocio.agregarSocioFederado(); break;
                case 4: formularioSocio.agregarSocioInfantil(); break;
                case 5: formularioSocio.eliminarSocio(); break;
                case 6: formularioSocio.mostrarSocios(); break;
//                case 7: formularioSocio.mostrarFacturasMensuales(); break;
            }
        }
    }

    public void ejecutarMenuExcursiones(){
        int opcionIngresada;

        while (true) {
            opcionIngresada = menuExcursiones.obtenerOpcionDeMenu();
            switch (opcionIngresada) {
                case 0: return;
                case 1: formularioExcursion.nuevaExcursion(); break;
                case 2: formularioExcursion.mostrarExcursiones(); break;
            }
        }
    }

    public void ejecutarMenuInscripciones(){
        int opcionIngresada;

        while (true) {
            opcionIngresada = menuInscripciones.obtenerOpcionDeMenu();
            switch (opcionIngresada) {
                case 0: return;
                case 1: formularioInscripcion.nuevaInscripcion(); break;
                case 2: formularioInscripcion.eliminarInscripcion(); break;
                case 3: formularioInscripcion.mostrarInscripciones(); break;
            }
        }
    }
}
