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
        SocioAdulto tutor1 = new SocioAdulto("Juan", 1235,"11447788A");
        SocioInfantil socioInfantil1 = new SocioInfantil(888, "Alex", tutor1);

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
        SocioAdulto tutor1 = new SocioAdulto("Juan", 1235,"11447788A");
        SocioAdulto tutor2 = new SocioAdulto("Marc", 5568,"55889644D");
        SocioInfantil socioInfantil1 = new SocioInfantil(888, "Alex", tutor1);
        SocioInfantil socioInfantil2 = new SocioInfantil(999, "Ana", tutor2);

        // When: Añadir los socios a la lista
        listaSocioInfantil.add(socioInfantil1);
        listaSocioInfantil.add(socioInfantil2);

        // Then: Verificar que los socios fueron añadidos correctamente
        Assertions.assertFalse(listaSocioInfantil.isEmpty());
        Assertions.assertEquals(2, listaSocioInfantil.size());
        Assertions.assertEquals(socioInfantil1, listaSocioInfantil.get(0));
        Assertions.assertEquals(socioInfantil2, listaSocioInfantil.get(1));
    }
}
