
import modelo.Datos;
import modelo.Federacion;
import modelo.SocioFederado;
import org.junit.jupiter.api.*;
import util.HibernateUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelSocioFederadoTest {
    Datos datos = new Datos();
    Federacion fed = new Federacion("FederacionPrueba", "Federacion");
    int id1;

    @BeforeAll
    public static void init (){
        HibernateUtil.startSessionFactory();
    }

    @AfterAll
    public static void end (){
        HibernateUtil.endSessionFactory();
    }

    @Test
    @Order(1)
    void insert(){
        SocioFederado socio = datos.agregarSocioFederado(new SocioFederado("QQQQQQQQQ", "Clara", fed));
        assertNotNull(socio);
        id1 = socio.getNumeroSocio();
    }

    @Test
    @Order(2)
    void update(){
        SocioFederado socio = datos.actualizarSocioFederado(new SocioFederado(id1, "QQQQQQQQQ", "Margarita", fed));
        assertEquals(socio.getNombre(), "Margarita");
    }

    @Test
    @Order(3)
    void get(){
        SocioFederado socio = datos.obtenerSocioFederado(id1);
        assertEquals("Margarita", socio.getNombre());
    }

    @Test
    @Order(4)
    void getAll(){
        List<SocioFederado> socios = datos.obtenerSociosFederados();
        boolean socioFound = false;
        for (SocioFederado socio: socios){
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
    }
}
