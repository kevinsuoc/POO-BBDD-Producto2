package dataroast.controlador;

import dataroast.modelo.Datos;
import dataroast.modelo.Excursion;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorExcursion {
    private final Datos datos;

    public ControladorExcursion(Datos datos){
        this.datos = datos;
    }

    public void agregarExcursion(int numDias, Double precioInscripcion, String codigo, String descripcion, LocalDate fecha){
        if (datos.buscarExcursion(codigo) != null){
            throw new UsedIdentifierException("El codigo de excursion ya esta siendo utilizado");
        }
        datos.obtenerExcursiones().add(new Excursion(numDias, precioInscripcion, codigo, descripcion, fecha));
    }

    public ArrayList<Excursion> obtenerExcursiones(LocalDate fechaInferior, LocalDate fechaSuperior){
        return datos.obtenerExcursiones(fechaInferior, fechaSuperior);
    }
}
