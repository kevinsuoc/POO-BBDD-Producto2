
import modelo.Datos;
import modelo.Seguro;
import modelo.TipoSeguro;
import org.junit.jupiter.api.*;
import util.HibernateUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ModelTipoSeguroTest {
    Datos datos = new Datos();

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
    void get(){
        Seguro basico = datos.obtenerSeguro(TipoSeguro.BASICO);
        Seguro completo = datos.obtenerSeguro(TipoSeguro.COMPLETO);
        if (basico == null || completo == null)
            throw new AssertionError("No se pudo obtener un seguro");
    }
}
