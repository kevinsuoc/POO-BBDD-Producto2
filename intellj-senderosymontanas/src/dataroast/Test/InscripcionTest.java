package dataroast.Test;

import dataroast.controlador.*;
import dataroast.modelo.Inscripcion;
import dataroast.modelo.TipoSeguro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

// To test: Nuevas inscripciones, remove them, show them
public class InscripcionTest {
    Controlador controlador = new Controlador(new Scanner(System.in));
    ControladorExcursion controladorExcursiones = controlador.getControladorExcursion();
    ControladorSocio controladorSocio = controlador.getControladorSocio();
    ControladorInscripcion controladorInscripcion = controlador.getControladorInscripcion();

    @BeforeEach
    void beforeTest(){
        // Agregar algunas excursiones
        controladorExcursiones.agregarExcursion(10, 15., "abc", "Una excursion", LocalDate.now().plusDays(3));
        controladorExcursiones.agregarExcursion(1, 20., "def", "Otra excursion", LocalDate.now().plusDays(5));

        // Agregar algunos socios
        controladorSocio.agregarSocioEstandar(123, "11223366H", "Juan", TipoSeguro.BASICO);
        controladorSocio.agregarSocioEstandar(125, "aaabbbccc", "Maria", TipoSeguro.COMPLETO);
        controladorSocio.agregarSocioInfantil(126, "Jose", "11223366H");
        controladorSocio.agregarSocioFederado(127, "11223377H", "Lucia", "abc");
    }

    @Test
    void crearInscripcionesValidas(){
        try {
            controladorInscripcion.agregarInscripcion(1, 123, "abc");
            controladorInscripcion.agregarInscripcion(2, 125, "def");
            controladorInscripcion.agregarInscripcion(3, 126, "abc");
            controladorInscripcion.agregarInscripcion(4, 127, "def");

        } catch (Exception e){
            throw new AssertionError("Excepcion agregando inscripcion valida");
        }
    }

    @Test
    void crearInscripcionesInvalidas(){
        // Socio doesn't exist
        try {
            controladorInscripcion.agregarInscripcion(1, 130, "abc");
            throw new AssertionError("Should've thrown exception");
        } catch (IllegalArgumentException e) {}

        // Used identifier
        try {
            controladorInscripcion.agregarInscripcion(1, 127, "abc");
            controladorInscripcion.agregarInscripcion(1, 127, "abc");
            throw new AssertionError("Should've thrown exception");
        } catch (IllegalArgumentException e) {}

        // Invalid excursion
        try {
            controladorInscripcion.agregarInscripcion(1, 127, "qwrtyInvalid");
            throw new AssertionError("Should've thrown exception");
        } catch (IllegalArgumentException e) {}
    }

    @Test
    void eliminarInscripcion(){
        crearInscripcionesInvalidas();

        controladorInscripcion.eliminarInscripcion(1);
        ArrayList<Inscripcion> inscripciones = controladorInscripcion.obtenerInscripciones(123);
        if (!inscripciones.isEmpty())
            throw new AssertionError("Debe eliminar la inscripcion");
    }

    @Test
    void obtenerInscripciones(){
        crearInscripcionesValidas();

        ArrayList<Inscripcion> inscripciones = controladorInscripcion.obtenerInscripciones(123);
        if (inscripciones.size() != 1)
            throw new AssertionError("Debe devolver una inscripcion, devuelve " + inscripciones.size());

        inscripciones = controladorInscripcion.obtenerInscripciones(LocalDate.now(), LocalDate.now().plusDays(30));
        if (inscripciones.size() != 4)
            throw new AssertionError("Deberia devolver 4 inscripciones, devuelve " + inscripciones.size());

       inscripciones = controladorInscripcion.obtenerInscripciones(LocalDate.now(), LocalDate.now().plusDays(6));
        if (inscripciones.size() != 2)
            throw new AssertionError("Deberia devolver 2 inscripcion, devuelve " + inscripciones.size());
    }
}
