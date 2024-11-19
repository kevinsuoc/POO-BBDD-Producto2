
import modelo.Federacion;
import modelo.SocioFederado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SocioFederadoTest {
    private List<SocioFederado> listaSocioFederado = new ArrayList<>();

    @Test
    void agregarSocio() {
        // Given: Un socio estándar válido
        SocioFederado socio1 = new SocioFederado(147,"88996655A", "Miguel", new Federacion("12M", "Malaga"));

        // When: Añadir el socio a la lista
        listaSocioFederado.add(socio1);

        // Then: Verificar que el socio fue añadido correctamente
        Assertions.assertFalse(listaSocioFederado.isEmpty());
        Assertions.assertEquals(1, listaSocioFederado.size());
        Assertions.assertEquals(socio1, listaSocioFederado.get(0));
    }

    @Test
    void mostrarSocioFederado() {
        // Given: Dos socios federados válidos
        SocioFederado socio1 = new SocioFederado(147, "88996655A", "Miguel", new Federacion("12M","Malaga"));
        SocioFederado socio2 = new SocioFederado(124, "77556688B", "Lucia", new Federacion("13X","Madrid"));

        // When: Añadir los socios a la lista
        listaSocioFederado.add(socio1);
        listaSocioFederado.add(socio2);

        // Then: Verificar que la lista de socios se muestra correctamente
        Assertions.assertFalse(listaSocioFederado.isEmpty());
        Assertions.assertEquals(2, listaSocioFederado.size());
        Assertions.assertEquals(socio1, listaSocioFederado.get(0));
        Assertions.assertEquals(socio2, listaSocioFederado.get(1));

        // Print the list of Socios Federados to the console after the test completes
        System.out.println("\n-- Result: Lista de Socios Federados --\n");
        listaSocioFederado.forEach(socio -> System.out.println(socio));
        System.out.println("\n-- Fin de la lista --\n");
    }
}
