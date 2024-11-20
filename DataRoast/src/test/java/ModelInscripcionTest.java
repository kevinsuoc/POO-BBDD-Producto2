
import modelo.*;
import org.junit.jupiter.api.*;
import util.HibernateUtil;

import java.time.LocalDate;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModelInscripcionTest {
    Datos datos = new Datos();
    int id1;
    int id2;
    int id3;
    int id4;
    int insid1;
    int insid2;
    int insid3;
    int insid4;

    @BeforeAll
    void setupData(){
        HibernateUtil.startSessionFactory();

        clearData();

        HibernateUtil.startSessionFactory();

        datos.agregarFederacion(new Federacion("TST", "Prueba"));

        // Agregar algunas excursiones
        datos.agregarExcursion(new Excursion(10, 15., "TEST1", "Una excursion", LocalDate.now().plusDays(3)));
        datos.agregarExcursion(new Excursion(1, 20., "TEST2", "Otra excursion", LocalDate.now().plusDays(5)));

        // Agregar algunos socios
        id1 = datos.agregarSocioEstandar(new SocioEstandar("11223366H", "Juan", datos.obtenerSeguro(TipoSeguro.BASICO))).getNumeroSocio();
        id2 = datos.agregarSocioEstandar(new SocioEstandar("aaabbbccc", "Maria", datos.obtenerSeguro(TipoSeguro.COMPLETO))).getNumeroSocio();
        id3 = datos.agregarSocioInfantil(new SocioInfantil("Jose", id1)).getNumeroSocio();
        id4 = datos.agregarSocioFederado(new SocioFederado("11223377H", "Lucia", datos.obtenerFederacion("TST"))).getNumeroSocio();
    }

    @AfterAll
    void clearData() {
        try {datos.eliminarExcursion("TEST1"); } catch (Exception e) {System.out.println(e.getMessage());}
        try {datos.eliminarExcursion("TEST2");} catch (Exception e) {System.out.println(e.getMessage());}
        try { datos.eliminarSocio(id3);} catch (Exception e) {System.out.println(e.getMessage());}
        try { datos.eliminarSocio(id1);} catch (Exception e) {System.out.println(e.getMessage());}
        try { datos.eliminarSocio(id2);} catch (Exception e) {System.out.println(e.getMessage());}
        try {datos.eliminarSocio(id4);} catch (Exception e) {System.out.println(e.getMessage());}
        try {datos.eliminarFederacion("TST");} catch (Exception e) {System.out.println(e.getMessage());}

        HibernateUtil.endSessionFactory();
    }

    @Test
    @Order(1)
    void insert(){
        Inscripcion ins1 = datos.agregarInscripcion(new Inscripcion(datos.obtenerSocio(id1), datos.obtenerExcursion("TEST1")));
        Inscripcion ins2 = datos.agregarInscripcion(new Inscripcion(datos.obtenerSocio(id2), datos.obtenerExcursion("TEST2")));
        Inscripcion ins3 = datos.agregarInscripcion(new Inscripcion(datos.obtenerSocio(id3), datos.obtenerExcursion("TEST1")));
        Inscripcion ins4 = datos.agregarInscripcion(new Inscripcion(datos.obtenerSocio(id4), datos.obtenerExcursion("TEST2")));
        if (ins1 == null){
            throw new AssertionError("No se pudo agregar una inscripcion");
        }
        insid1 = ins1.getNumeroInscripcion();
        if (ins2 == null) {
            throw new AssertionError("No se pudo agregar una inscripcion");
        }
        insid2 = ins2.getNumeroInscripcion();
        if (ins3 == null){
           throw new AssertionError("No se pudo agregar una inscripcion");
        }
        insid3 = ins3.getNumeroInscripcion();
        if (ins4 == null) {
            throw new AssertionError("No se pudo agregar una inscripcion");
        }
        insid4 = ins4.getNumeroInscripcion();
    }

    @Test
    @Order(2)
    void obtenerInscripciones(){
        List<Inscripcion> inscripciones = datos.obtenerInscripciones(id1);
        if (inscripciones.size() != 1)
            throw new AssertionError("Debe devolver una inscripcion, devuelve " + inscripciones.size());

        inscripciones = datos.obtenerInscripciones(LocalDate.now(), LocalDate.now().plusDays(30));
        if (inscripciones.size() != 4)
            throw new AssertionError("Deberia devolver 4 inscripciones, devuelve " + inscripciones.size());

        inscripciones = datos.obtenerInscripciones(LocalDate.now(), LocalDate.now().plusDays(4));
        if (inscripciones.size() != 2)
            throw new AssertionError("Deberia devolver 2 inscripcion, devuelve " + inscripciones.size());
    }

    @Test
    @Order(3)
    void delete(){
        if (!datos.eliminarInscripcion(insid1)){
            throw new AssertionError("No se pudo eliminar una inscripcion");
        }
        if (!datos.eliminarInscripcion(insid2)){
            throw new AssertionError("No se pudo eliminar una inscripcion");
        }
        if (!datos.eliminarInscripcion(insid3)){
            throw new AssertionError("No se pudo eliminar una inscripcion");
        }
        if (!datos.eliminarInscripcion(insid4)){
            throw new AssertionError("No se pudo eliminar una inscripcion");
        }
    }
}
