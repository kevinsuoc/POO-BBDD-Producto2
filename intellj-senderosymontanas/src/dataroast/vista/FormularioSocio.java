package dataroast.vista;

import dataroast.controlador.ControladorSocio;
import dataroast.modelo.Socio;
import dataroast.modelo.SocioEstandar;
import dataroast.modelo.SocioFederado;
import dataroast.modelo.SocioInfantil;

import java.util.Scanner;

public class FormularioSocio {
    private ControladorSocio socioController;

    public FormularioSocio(ControladorSocio socioController) {
        this.socioController = socioController;
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Opción 1: Agregar Socio");
            System.out.println("Opción 0: Volver al menu principal");
            System.out.print("Selecciona una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            if (opcion == 1) {
                agregarSocio();
            } else if (opcion == 0) {
                System.out.println("Volviendo al menu ...");
                break;
            } else {
                System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private void agregarSocio() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elige el tipo de socio:");
        System.out.println("1: Adulto");
        System.out.println("2: Infantil");
        int tipoSocio = Integer.parseInt(scanner.nextLine());

        if (tipoSocio == 1) {
            agregarSocioAdulto();
        } else if (tipoSocio == 2) {
            agregarSocioInfantil();
        } else {
            System.out.println("Opción no válida. Inténtalo de nuevo.");
        }
    }

    private void agregarSocioAdulto() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Elige el tipo de socio adulto:");
        System.out.println("1: Socio Estándar");
        System.out.println("2: Socio Federado");
        int tipoAdulto = Integer.parseInt(scanner.nextLine());

        System.out.print("Introduce el nombre del socio: ");
        String nombre = scanner.nextLine().trim();
        Socio nuevoSocio;

        if (tipoAdulto == 1) {
            nuevoSocio = new SocioEstandar(nombre, socioController.getNumeroSocios() + 1); // Usa el siguiente número
        } else if (tipoAdulto == 2) {
            nuevoSocio = new SocioFederado(nombre, socioController.getNumeroSocios() + 1); // Usa el siguiente número
        } else {
            System.out.println("Opción no válida. No se agrega el socio.");
            return;
        }

        socioController.agregarSocio(nuevoSocio);
    }

    private void agregarSocioInfantil() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el número de socio del Tutor: ");
        int numeroSocioAdulto = Integer.parseInt(scanner.nextLine());

        // Verifica si el número de socio del adulto es válido
        if (!socioController.existeSocioPorNumero(numeroSocioAdulto)) {
            System.out.println("No existe un socio adulto con ese número.");
            return;
        }

        System.out.print("Introduce el nombre del socio infantil: ");
        String nombreInfantil = scanner.nextLine().trim();

        // Aquí asumo que el número del socio infantil debe ser diferente
        Socio nuevoSocioInfantil = new SocioInfantil(nombreInfantil, socioController.getNumeroSocios() + 1);
        socioController.agregarSocio(nuevoSocioInfantil);
    }
}