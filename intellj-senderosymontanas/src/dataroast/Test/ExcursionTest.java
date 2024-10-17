package dataroast.Test;

import dataroast.modelo.Excursion;
import dataroast.controlador.Controlador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.time.LocalDate;

class ExcursionTest {
    private Controlador controlador;

    @BeforeEach
    void setUp() {
        controlador = new Controlador(); // Initialize your controlador instance
    }



    @Test
    void agregarExcursion() {
        // Given: A valid excursion
        int numDias = 3;
        double precioInscripcion = 150.0;
        String codigo = "ABC";
        String descripcion = "Excursion a la montaña.";
        LocalDate fecha = LocalDate.now().plusDays(1); // Future date

        // When: The excursion is added
        controlador.agregarExcursion(numDias, precioInscripcion, codigo, descripcion, fecha);

        // Then: Verify that the excursion was added correctly
        Excursion addedExcursion = controlador.obtenerExcursiones(LocalDate.now(), LocalDate.now().plusDays(5)).get(0);
        Assertions.assertEquals(numDias, addedExcursion.getNumDias());
        Assertions.assertEquals(precioInscripcion, addedExcursion.getPrecioInscripcion());
        Assertions.assertEquals(codigo, addedExcursion.getCodigo());
        Assertions.assertEquals(descripcion, addedExcursion.getDescripcion());
        Assertions.assertEquals(fecha, addedExcursion.getFecha());
    }
}



