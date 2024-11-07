package dataroast.Test;

import dataroast.controlador.*;
import dataroast.modelo.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelInscripcionTest {
    Datos datos = new Datos();

    @BeforeAll
    void setupData(){
        clearData();
        datos.agregarFederacion(new Federacion("FederacionPrueba", "Prueba"));

        // Agregar algunas excursiones
//        datos.agregarExcursion(10, 15., "testExcursion1", "Una excursion", LocalDate.now().plusDays(3));
//        datos.agregarExcursion(1, 20., "textExcursion2", "Otra excursion", LocalDate.now().plusDays(5));

        // Agregar algunos socios
//        datos.agregarSocioEstandar(new SocioEstandar(123, "11223366H", "Juan", datos.obtenerSeguro(TipoSeguro.BASICO)));
//        datos.agregarSocioEstandar(new SocioEstandar(125, "aaabbbccc", "Maria", datos.obtenerSeguro(TipoSeguro.COMPLETO)));
//        datos.agregarSocioInfantil(new SocioInfantil(126, "Jose", datos.obtenerSocio("11223366H")));
//        datos.agregarSocioFederado(new SocioFederado(127, "11223377H", "Lucia", datos.obtenerFederacion("FederacionPrueba")));
    }

    @AfterAll
    void clearData(){
        datos.eliminarFederacion("FederacionPrueba");

//        datos.eliminarExcursion("testExcursion1");
//        datos.eliminarExcursion("testExcursion2");

//        datos.eliminarSocioEstandar(100100);
//        datos.eliminarSocioEstandar(100101);
//        datos.eliminarSocioInfantil(100102);
//        datos.eliminarSocioFederado(100103);
    }

    @Test
    @Order(1)
    void insert(){
//        if (datos.agregarInscripcion(1, datos.obtenerSocio(100100), datos.obtenerExcursion("testExcursion1"))){
//            throw new AssertionError("No se pudo agregar una inscripcion");
//        }
//        if (datos.agregarInscripcion(2, datos.obtenerSocio(100101), datos.obtenerExcursion("testExcursion2"))) {
//            throw new AssertionError("No se pudo agregar una inscripcion");
//        }
//        if (datos.agregarInscripcion(3, datos.obtenerSocio(100102), datos.obtenerExcursion("testExcursion1"))){
//            throw new AssertionError("No se pudo agregar una inscripcion");
//        }
//        if (datos.agregarInscripcion(4, datos.obtenerSocio(100103), datos.obtenerExcursion("testExcursion2"))) {
//            throw new AssertionError("No se pudo agregar una inscripcion");
//        }
    }

    @Test
    @Order(2)
    void obtenerInscripciones(){
        ArrayList<Inscripcion> inscripciones = datos.obtenerInscripciones(100100);
        if (inscripciones.size() != 1)
            throw new AssertionError("Debe devolver una inscripcion, devuelve " + inscripciones.size());

        inscripciones = datos.obtenerInscripciones(LocalDate.now(), LocalDate.now().plusDays(30));
        if (inscripciones.size() != 4)
            throw new AssertionError("Deberia devolver 4 inscripciones, devuelve " + inscripciones.size());

        inscripciones = datos.obtenerInscripciones(LocalDate.now(), LocalDate.now().plusDays(4));
        if (inscripciones.size() != 2)
            throw new AssertionError("Deberia devolver 2 inscripcion, devuelve " + inscripciones.size());
    }

    @Test
    @Order(3)
    void update(){
        Inscripcion inscripcion = datos.obtenerInscripcion(1);
//        Socio socio = datos.obtenerSocio(100101);
//        Excursion excursion = datos.obtenerExcursion("TestExcursion2");
//        inscripcion.setSocio(socio);
//        inscripcion.setExcursion(excursion);
//        datos.actualizarInscripcion(inscripcion);
        inscripcion = datos.obtenerInscripcion(1);
//        assertEquals(inscripcion.getSocio().getNombre(), socio.getNombre());
//        assertEquals(inscripcion.getExcursion().getCodigo(), excursion.getCodigo());
    }

    @Test
    @Order(4)
    void delete(){
//        if (!datos.eliminarInscripcion(1)){
//            throw new AssertionError("No se pudo eliminar una inscripcion");
//        }
//        if (!datos.eliminarInscripcion(2)){
//            throw new AssertionError("No se pudo eliminar una inscripcion");
//        }
        if (!datos.eliminarInscripcion(3)){
            throw new AssertionError("No se pudo eliminar una inscripcion");
        }
        if (!datos.eliminarInscripcion(4)){
            throw new AssertionError("No se pudo eliminar una inscripcion");
        }
    }
}
