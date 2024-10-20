package dataroast.Test;
import dataroast.modelo.Excursion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ExcursionTest {
    private List<Excursion> listaExcursiones = new ArrayList<>();

    @Test
    void agregarExcursion() {
        // Given: A valid excursion
        Excursion excursion1 = new Excursion(3, 150.0, "ABC", "Excursion a la montaña.", LocalDate.now().plusDays(1));

        // When: Adding the excursion to the list
        listaExcursiones.add(excursion1);

        // Then: Verify that the excursion was added correctly
        Assertions.assertFalse(listaExcursiones.isEmpty());
        Assertions.assertEquals(1, listaExcursiones.size());
        Assertions.assertEquals(excursion1, listaExcursiones.get(0));
    }

    @Test
    void mostrarExcursiones() {
        // Given: Two valid excursions
        Excursion excursion1 = new Excursion(3, 150.0, "ABC", "Excursion a la montaña.", LocalDate.now().plusDays(1));
        Excursion excursion2 = new Excursion(2, 100.0, "DEF", "Excursion a la playa.", LocalDate.now().plusDays(2));

        // When: Adding excursions to the list
        listaExcursiones.add(excursion1);
        listaExcursiones.add(excursion2);

        // Verify that the list of excursions is correct
        Assertions.assertFalse(listaExcursiones.isEmpty());
        Assertions.assertEquals(2, listaExcursiones.size());

        // Print the list of excursions to the console after the test completes
        System.out.println("\n-- Result: Lista de Excursiones --\n");
        listaExcursiones.forEach(excursion -> System.out.println(excursion));
        System.out.println("\n-- Fin de la lista --\n");
    }
}
