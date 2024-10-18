package dataroast.controlador;

import dataroast.modelo.Datos;
import dataroast.modelo.Excursion;

import java.time.LocalDate;
import java.util.ArrayList;

public class ControladorExcursion {
    private Datos datos;

    public ControladorExcursion(Datos datos){
        this.datos = datos;
    }

    public void agregarExcursion(int numDias, Double precioInscripcion, String codigo, String descripcion, LocalDate fecha){
        if (datos.encontrarExcursionPorCodigo(codigo) != null){
            throw new IllegalArgumentException("El codigo de excursion ya esta siendo utilizado");
        }
        datos.getExcursiones().add(new Excursion(numDias, precioInscripcion, codigo, descripcion, fecha));
    }

    public ArrayList<Excursion> obtenerExcursiones(LocalDate fechaInferior, LocalDate fechaSuperior){
        ArrayList<Excursion> excursionesValidas = new ArrayList<Excursion>();
        ArrayList<Excursion> todasLasExcursiones;
        LocalDate fecha;

        if (fechaInferior.isAfter(fechaSuperior))
            throw new IllegalArgumentException("La fecha inferior es mayor que la superior");
        todasLasExcursiones = datos.getExcursiones();
        for (Excursion excursion: todasLasExcursiones){
            fecha = excursion.getFecha();
            if (fecha.isAfter(fechaInferior) && fecha.isBefore(fechaSuperior))
                excursionesValidas.add(excursion);
        }

        return excursionesValidas;
    }
}
