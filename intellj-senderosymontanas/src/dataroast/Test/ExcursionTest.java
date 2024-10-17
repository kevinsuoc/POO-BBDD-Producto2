package dataroast.Test;
import dataroast.modelo.Excursion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class ExcursionTest {
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

        // Then: Verify that the list of excursions is shown correctly
        List<Excursion> excursiones = new ArrayList<>(listaExcursiones);  // Simulate mostrarExcursiones behavior
        Assertions.assertFalse(excursiones.isEmpty());
        Assertions.assertEquals(2, excursiones.size());
    }
}