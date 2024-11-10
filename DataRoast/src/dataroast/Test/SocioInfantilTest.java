package dataroast.Test;

import dataroast.modelo.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SocioInfantilTest {
    private List<SocioInfantil> listaSocioInfantil = new ArrayList<>();

    @Test
    void agregarSocio() {
        // Given: Un socio infantil válido con su tutor
        SocioEstandar tutor1 = new SocioEstandar(1, "11447788A", "Juan", new Seguro(10, TipoSeguro.BASICO));
        SocioInfantil socioInfantil1 = new SocioInfantil(888, "Alex", 1);

        // When: Añadir el socio a la lista
        listaSocioInfantil.add(socioInfantil1);

        // Then: Verificar que el socio fue añadido correctamente
        Assertions.assertFalse(listaSocioInfantil.isEmpty());
        Assertions.assertEquals(1, listaSocioInfantil.size());
        Assertions.assertEquals(socioInfantil1, listaSocioInfantil.get(0));
    }

    @Test
    void mostrarSocioInfantil() {
        // Given: Dos socios infantiles válidos con sus tutores
        SocioEstandar tutor1 = new SocioEstandar(1, "11447788A", "Juan", new Seguro(10, TipoSeguro.BASICO));
        SocioEstandar tutor2 = new SocioEstandar(2, "55889644D", "Marc", new Seguro(10, TipoSeguro.BASICO));
        SocioInfantil socioInfantil1 = new SocioInfantil(888, "Alex", 1);
        SocioInfantil socioInfantil2 = new SocioInfantil(999, "Ana", 2);

        // When: Añadir los socios a la lista
        listaSocioInfantil.add(socioInfantil1);
        listaSocioInfantil.add(socioInfantil2);

        // Then: Verificar que los socios fueron añadidos correctamente
        Assertions.assertFalse(listaSocioInfantil.isEmpty());
        Assertions.assertEquals(2, listaSocioInfantil.size());
        Assertions.assertEquals(socioInfantil1, listaSocioInfantil.get(0));
        Assertions.assertEquals(socioInfantil2, listaSocioInfantil.get(1));

        // Print the list of Socios Infantiles to the console after the test completes
        System.out.println("\n-- Result: Lista de Socios Infantiles --\n");
        listaSocioInfantil.forEach(socio -> System.out.println(socio));
        System.out.println("\n-- Fin de la lista --\n");
    }
    } 
