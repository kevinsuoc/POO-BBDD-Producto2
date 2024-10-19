package dataroast.Test;

import dataroast.modelo.Seguro;
import dataroast.modelo.SocioEstandar;
import dataroast.modelo.TipoSeguro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SocioEstandarTest {
    private List<SocioEstandar> listaSocioEstandar = new ArrayList<>();

    @Test
    void agregarSocio() {
        // Given: Un socio estándar válido
        SocioEstandar socio1 = new SocioEstandar(123, "11223366H", "Juan", new Seguro(10, TipoSeguro.BASICO));

        // When: Añadir el socio a la lista
        listaSocioEstandar.add(socio1);

        // Then: Verificar que el socio fue añadido correctamente
        Assertions.assertFalse(listaSocioEstandar.isEmpty());
        Assertions.assertEquals(1, listaSocioEstandar.size());
        Assertions.assertEquals(socio1, listaSocioEstandar.get(0));
    }

    @Test
    void mostrarSocioEstandar() {
        // Given: Dos socios estándar válidos
        SocioEstandar socio1 = new SocioEstandar(123, "11223366H", "Juan", new Seguro(10, TipoSeguro.BASICO));
        SocioEstandar socio2 = new SocioEstandar(124, "11558877J", "Maria", new Seguro(20, TipoSeguro.COMPLETO));

        // When: Añadir los socios a la lista
        listaSocioEstandar.add(socio1);
        listaSocioEstandar.add(socio2);

        // Then: Verificar que la lista de socios se muestra correctamente
        Assertions.assertFalse(listaSocioEstandar.isEmpty());
        Assertions.assertEquals(2, listaSocioEstandar.size());
        Assertions.assertEquals(socio1, listaSocioEstandar.get(0));
        Assertions.assertEquals(socio2, listaSocioEstandar.get(1));
    }
}