
import modelo.Datos;
import modelo.Seguro;
import modelo.TipoSeguro;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelSeguroTest {
    Datos datos = new Datos();

    @Test
    @Order(1)
    void get(){
        Seguro seguroBasico = datos.obtenerSeguro(TipoSeguro.BASICO);
        Seguro seguroCompleto = datos.obtenerSeguro(TipoSeguro.COMPLETO);
        assertTrue(seguroBasico.getPrecio() > 0 && seguroCompleto.getPrecio() > 0);
        assertSame(seguroBasico.getTipoSeguro(), TipoSeguro.BASICO);
        assertSame(seguroCompleto.getTipoSeguro(), TipoSeguro.COMPLETO);
    }

    @Test
    @Order(2)
    void getAll(){
        List<Seguro> seguros = datos.obtenerSeguros();
        for (Seguro seguro: seguros){
            assertTrue(seguro.getTipoSeguro() == TipoSeguro.BASICO || seguro.getTipoSeguro() == TipoSeguro.COMPLETO);
        }
        assertEquals(2, seguros.size());
    }
}
