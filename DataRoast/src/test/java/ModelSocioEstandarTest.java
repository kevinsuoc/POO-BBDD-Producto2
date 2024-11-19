
import modelo.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelSocioEstandarTest {
    Datos datos = new Datos();
    int id1;
    int id2;

    @Test
    @Order(1)
    void insert(){
        Socio socio1 = datos.agregarSocioEstandar(new SocioEstandar("QQQQQQQQQ", "Margarita", new Seguro(20, TipoSeguro.BASICO)));
        assertNotNull(socio1);
        id1 = socio1.getNumeroSocio();
        Socio socio2 = datos.agregarSocioEstandar(new SocioEstandar("FFFFFFFF", "Margarita", new Seguro(10, TipoSeguro.COMPLETO)));
        assertNotNull(socio2);
        id2 = socio2.getNumeroSocio();
    }

    @Test
    @Order(2)
    void update(){
        datos.actualizarSocioEstandar(new SocioEstandar(id1, "QQQQQQQQQ", "Amanda", new Seguro(30, TipoSeguro.COMPLETO)));
        SocioEstandar socioReal = datos.obtenerSocioEstandar(id1);
        assertEquals(socioReal.getNombre(), "Amanda");
    }

    @Test
    @Order(3)
    void get(){
        SocioEstandar socio = datos.obtenerSocioEstandar(id1);
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
            if (socio.getNumeroSocio() == id1) {
                socioFound = true;
                break;
            }
        }
        assertTrue(socioFound);
    }

    @Test
    @Order(5)
    void delete(){
        assertTrue(datos.eliminarSocio(id1));
        assertTrue(datos.eliminarSocio(id2));
    }
}
