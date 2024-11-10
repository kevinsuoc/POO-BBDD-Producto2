package dataroast.Test;

import com.sun.source.tree.AssertTree;
import dataroast.modelo.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelSocioInfantilTest {
    Datos datos = new Datos();
    SocioEstandar socio1 = new SocioEstandar(1000, "AABBCCDDE", "Carlos", new Seguro(100, TipoSeguro.BASICO));
    SocioFederado socio2 = new SocioFederado(2000, "FFGGHHIIJ", "Rosa", new Federacion("FederacionPrueba", "que"));

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
        assertNotNull(datos.agregarSocioInfantil(new SocioInfantil(100100, "John", socio1.getNumeroSocio())));
        assertNotNull(datos.agregarSocioInfantil(new SocioInfantil(100101, "Elena", socio1.getNumeroSocio())));
    }

    @Test
    @Order(2)
    void update(){
        SocioInfantil socio = datos.actualizarSocioInfantil((new SocioInfantil(100100, "Damian", socio1.getNumeroSocio())));
        assertEquals(socio.getNombre(), "Damian");
    }

    @Test
    @Order(3)
    void get(){
        SocioInfantil socioInf1 = datos.obtenerSocioInfantil(100100);
        SocioInfantil socioInf2 = datos.obtenerSocioInfantil(100101);

        assertTrue(socioInf1 != null && socioInf2 != null);
        assertTrue(socioInf1.getNumeroSocio() == 100100 && socioInf2.getNumeroSocio() == 100101);
    }

    @Test
    @Order(4)
    void getAll(){
        List<SocioInfantil> socios = datos.obtenerSociosInfantiles();
        assertTrue(socios.size() > 2);
        boolean socioFound = false;
        for (SocioInfantil socio: socios){
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
