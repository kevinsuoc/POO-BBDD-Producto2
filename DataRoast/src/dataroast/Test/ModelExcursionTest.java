package dataroast.Test;

import dataroast.modelo.Datos;
import dataroast.modelo.Excursion;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ModelExcursionTest {
    Datos datos = new Datos();

    @Test
    @Order(1)
    void insert(){
        if (datos.agregarExcursion(new Excursion(10, 20., "test123", "Una excursion de prueba", LocalDate.now().plusDays(1))) == null)
            throw new AssertionError("No se pudo agregar una excursion");
        if (datos.agregarExcursion(new Excursion(10, 20., "test456", "Otra excursion de prueba", LocalDate.now().plusDays(50))) == null)
            throw new AssertionError("No se pudo agregar una excursion");
        if (datos.agregarExcursion(new Excursion(10, 20., "test789", "Otra excursion de prueba", LocalDate.now().plusDays(100))) == null)
            throw new AssertionError("No se pudo agregar una excursion");
        if (datos.agregarExcursion(new Excursion(10, 20., "test101112", "Otra excursion de prueba", LocalDate.now().plusDays(200))) == null)
            throw new AssertionError("No se pudo agregar una excursion");
    }

    @Test
    @Order(2)
    void get(){
        Excursion excursion = datos.obtenerExcursion("test123");
        assertEquals(excursion.getDescripcion(), "Una excursion de prueba");

        List<Excursion> excursiones = datos.obtenerExcursiones(LocalDate.now().plusDays(2), LocalDate.now().plusDays(101));
        if (excursiones.size() < 2){
            throw new AssertionError("Excursiones no econtradas");
        }
        boolean found1 = false;
        boolean found2 = false;
        for (Excursion exc: excursiones){
            if (Objects.equals(exc.getCodigo(), "test456"))
                found1 = true;
            else if (Objects.equals(exc.getCodigo(), "test789"))
                found2 = true;
            else if (Objects.equals(exc.getCodigo(), "test123") || Objects.equals(exc.getCodigo(), "test101112"))
                throw new AssertionError("Excursion no debida encontrada");
        }
        assertTrue(found1 && found2);
    }

    @Test
    @Order(3)
    void update(){
        datos.actualizarExcursion(new Excursion(10, 30., "test123", "Una excursion de prueba", LocalDate.now().plusDays(1)));
        Excursion excursion = datos.obtenerExcursion("test123");
        assertEquals(excursion.getPrecioInscripcion(), 30.);
    }

    @Test
    @Order(4)
    void delete(){
        if (!datos.eliminarExcursion("test123")){
            throw new AssertionError("No se pudo eliminar una excursion");
        }
        if (!datos.eliminarExcursion("test456")){
            throw new AssertionError("No se pudo eliminar una excursion");
        }
        if (!datos.eliminarExcursion("test789")){
            throw new AssertionError("No se pudo eliminar una excursion");
        }
        if (!datos.eliminarExcursion("test101112")){
            throw new AssertionError("No se pudo eliminar una excursion");
        }
    }
}
