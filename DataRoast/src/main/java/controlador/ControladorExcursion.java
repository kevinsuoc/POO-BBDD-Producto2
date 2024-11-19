package controlador;

import modelo.Datos;
import modelo.Excursion;

import java.time.LocalDate;
import java.util.List;

public class ControladorExcursion {
    private final Datos datos;

    public ControladorExcursion(Datos datos){
        this.datos = datos;
    }

    public void agregarExcursion(int numDias, Double precioInscripcion, String codigo, String descripcion, LocalDate fecha){
        datos.agregarExcursion(new Excursion(numDias, precioInscripcion, codigo, descripcion, fecha));
    }

    public List<Excursion> obtenerExcursiones(LocalDate fechaInferior, LocalDate fechaSuperior){
        return datos.obtenerExcursiones(fechaInferior, fechaSuperior);
    }
}
