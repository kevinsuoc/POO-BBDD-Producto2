package dataroast.Test;

import dataroast.modelo.Datos;
import dataroast.modelo.Federacion;
import dataroast.modelo.SocioFederado;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelSocioFederadoTest {
    Datos datos = new Datos();
    Federacion fed = new Federacion("FederacionPrueba", "Federacion");

    @Test
    @Order(1)
    void insert(){
        assertNotNull(datos.agregarSocioFederado(new SocioFederado(100100, "QQQQQQQQQ", "Clara", fed)));
    }

    @Test
    @Order(2)
    void update(){
        SocioFederado socio = datos.actualizarSocioFederado(new SocioFederado(100100, "QQQQQQQQQ", "Margarita", fed));
        assertEquals(socio.getNombre(), "Margarita");
    }

    @Test
    @Order(3)
    void get(){
        SocioFederado socio = datos.obtenerSocioFederado(100100);
        assertEquals("Margarita", socio.getNombre());
    }

    @Test
    @Order(4)
    void getAll(){
        List<SocioFederado> socios = datos.obtenerSociosFederados();
        boolean socioFound = false;
        for (SocioFederado socio: socios){
            if (socio.getNumeroSocio() == 100100) {
                socioFound = true;
                break;
            }
        }
        assertTrue(socioFound);
    }

    @Test
    @Order(5)
    void delete(){
        assertTrue(datos.eliminarSocio(100100));
    }
}
