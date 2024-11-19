
import modelo.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelSocioInfantilTest {
    Datos datos = new Datos();
    SocioEstandar socio1 = new SocioEstandar(1000, "AABBCCDDE", "Carlos", new Seguro(100, TipoSeguro.BASICO));
    SocioFederado socio2 = new SocioFederado(2000, "FFGGHHIIJ", "Rosa", new Federacion("FederacionPrueba", "que"));
    int id1;
    int id2;

    @BeforeAll
    void init(){
        datos.agregarSocioEstandar(socio1);
        datos.agregarSocioFederado(socio2);
    }

    @AfterAll
    void afterAll(){
        datos.eliminarSocio(socio1.getNumeroSocio());
        datos.eliminarSocio(socio2.getNumeroSocio());
    }

    @Test
    @Order(1)
    void insert(){
        SocioInfantil socioInf1 = datos.agregarSocioInfantil(new SocioInfantil("John", socio1.getNumeroSocio()));
        SocioInfantil socioInf2 = datos.agregarSocioInfantil(new SocioInfantil("Elena", socio1.getNumeroSocio()));
        assertNotNull(socioInf1);
        assertNotNull(socioInf2);
        id1 = socioInf1.getNumeroSocio();
        id2 = socioInf2.getNumeroSocio();
    }

    @Test
    @Order(2)
    void update(){
        SocioInfantil socio = datos.actualizarSocioInfantil((new SocioInfantil(id1, "Damian", socio1.getNumeroSocio())));
        assertEquals(socio.getNombre(), "Damian");
    }

    @Test
    @Order(3)
    void get(){
        SocioInfantil socioInf1 = datos.obtenerSocioInfantil(id1);
        SocioInfantil socioInf2 = datos.obtenerSocioInfantil(id2);

        assertTrue(socioInf1 != null && socioInf2 != null);
        assertTrue(socioInf1.getNumeroSocio() == id1 && socioInf2.getNumeroSocio() == id2);
    }

    @Test
    @Order(4)
    void getAll(){
        List<SocioInfantil> socios = datos.obtenerSociosInfantiles();
        assertTrue(socios.size() > 2);
        boolean socioFound = false;
        for (SocioInfantil socio: socios){
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
