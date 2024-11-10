package dataroast.Test;

import dataroast.modelo.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelSocioEstandarTest {
    Datos datos = new Datos();

    @Test
    @Order(1)
    void insert(){
        assertNotNull(datos.agregarSocioEstandar(new SocioEstandar(100100, "QQQQQQQQQ", "Margarita", new Seguro(20, TipoSeguro.BASICO))));
        assertNotNull(datos.agregarSocioEstandar(new SocioEstandar(100101, "FFFFFFFF", "Margarita", new Seguro(10, TipoSeguro.COMPLETO))));
    }

    @Test
    @Order(2)
    void update(){
        SocioEstandar socio = datos.actualizarSocioEstandar(new SocioEstandar(100100, "QQQQQQQQQ", "Amanda", new Seguro(30, TipoSeguro.COMPLETO)));
        SocioEstandar socioReal = datos.obtenerSocioEstandar(100100);
        assertEquals(socioReal.getNombre(), "Amanda");
    }

    @Test
    @Order(3)
    void get(){
        SocioEstandar socio = datos.obtenerSocioEstandar(100100);
        System.out.println(socio);
        assertNotNull(socio);
    }

    @Test
    @Order(4)
    void getAll(){
        List<SocioEstandar> socios = datos.obtenerSociosEstandar();
        assertTrue(socios.size() > 1);
        boolean socioFound = false;
        for (SocioEstandar socio: socios){
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
        assertTrue(datos.eliminarSocio(100101));
    }
}
