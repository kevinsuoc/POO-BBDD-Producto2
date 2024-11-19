import modelo.Datos;
import modelo.Federacion;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ModelFederacionTest {
    Datos datos = new Datos();
    String codigoFed1 = "1x2c3ba";
    String codigoFed2 = "awqqe";
    String nombreFed1 = "Federacion 1";
    String nombreFed2 = "Federacion 2";
    String nombreFed2Upd = "Federacion 2 - actualiazdo";

    @Test
    @Order(1)
    public void insert(){
        if (datos.agregarFederacion(new Federacion(codigoFed1, nombreFed1)) == null){
            throw new AssertionError("No se pudo agregar una federacion");
        }
        if (datos.agregarFederacion(new Federacion(codigoFed2, nombreFed2)) == null){
            throw new AssertionError("No se pudo agregar una federacion");
        }
    }

    @Test
    @Order(2)
    public void find(){
        Federacion federacion = datos.obtenerFederacion(codigoFed1);
        if (federacion == null){
            throw new AssertionError("No se pudo buscar una federacion");
        }
        assertEquals(federacion.getCodigo(), codigoFed1);
        assertEquals(federacion.getNombre(), nombreFed1);
    }

    @Test
    @Order(3)
    public void update(){
        Federacion federacion = datos.obtenerFederacion(codigoFed2);
        federacion.setNombre(nombreFed2Upd);
        datos.actualizarFederacion(federacion);
        federacion = datos.obtenerFederacion(codigoFed2);
        assertEquals(nombreFed2Upd, federacion.getNombre());
    }

    @Test
    @Order(4)
    public void delete(){
        if (!datos.eliminarFederacion(codigoFed1) || (!datos.eliminarFederacion(codigoFed2))){
            throw new AssertionError("No se pudo eliminar una federacion");
        }
    }
}
