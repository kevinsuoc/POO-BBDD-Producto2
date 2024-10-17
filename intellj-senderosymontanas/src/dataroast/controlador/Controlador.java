package dataroast.controlador;

import dataroast.modelo.Datos;
import dataroast.modelo.Excursion;
import dataroast.modelo.Inscripcion;
import dataroast.modelo.Socio;
import dataroast.vista.View;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Controlador {
    private Datos datos = new Datos();
    private Scanner in = new Scanner(System.in);
    private View view = new View(this, in);

    public void ejecutarPrograma(){
        view.ejecutarMenuPrincipal();
    }




    // Inscripciones
    public void agregarInscripcion(int numeroInscripcion, int numeroSocio, String codigoExcursion){
        Excursion excursion = encontrarExcursionPorCodigo(codigoExcursion);
        if (excursion == null){
            throw new IllegalArgumentException("La excursion no existe");
        }
        Socio socio = encontrarSocioPorNumeroSocio(numeroSocio);
        if (socio == null){
            throw new IllegalArgumentException("El socio no existe");
        }
        datos.getInscripciones().add(new Inscripcion(numeroInscripcion, socio, excursion));
        //
        // Que pasa si el socio ya esta agregado...
    }
    public void eliminarInscripcion(int numeroInscripcion) {
        ArrayList<Inscripcion> inscripciones = datos.getInscripciones();

        for (Inscripcion inscripcion: inscripciones){
            if (inscripcion.getNumeroInscripcion() == numeroInscripcion) {
                if (inscripcion.getExcursion().getFecha().isBefore(LocalDate.now()))
                    throw new IllegalArgumentException("Solo se pueden eliminar inscripciones de excursiones futuras");
                inscripciones.remove(inscripcion);
                return ;
            }
        }
        //Una inscripción solo se puede cancelar si se realiza en una fecha estrictamente anterior
        //de la que se realiza la excursión.
        throw new IllegalArgumentException("La inscripcion no existe");
    }

    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio){
        ArrayList<Inscripcion> inscripcionesFiltradas = new ArrayList<Inscripcion>();
        ArrayList<Inscripcion> inscripcionesSinfiltrar = datos.getInscripciones();

        if (fechaInferior.isAfter(fechaSuperior))
            throw new IllegalArgumentException("La fecha inferior es mayor que la superior");
        if (numeroSocio <= 0)
            throw new IllegalArgumentException("Numero de socio invalido");
        for (Inscripcion inscripcion: inscripcionesSinfiltrar){
            LocalDate fecha = inscripcion.getExcursion().getFecha();
            if (fecha.isAfter(fechaInferior) && fecha.isBefore(fechaSuperior) && inscripcion.getSocio().getNumeroSocio() == numeroSocio)
                inscripcionesFiltradas.add(inscripcion);
        }
        return inscripcionesFiltradas;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior){
        ArrayList<Inscripcion> inscripcionesFiltradas = new ArrayList<Inscripcion>();
        ArrayList<Inscripcion> inscripcionesSinfiltrar = datos.getInscripciones();

        if (fechaInferior.isAfter(fechaSuperior))
            throw new IllegalArgumentException("La fecha inferior es mayor que la superior");
        for (Inscripcion inscripcion: inscripcionesSinfiltrar){
            LocalDate fecha = inscripcion.getExcursion().getFecha();
            if (fecha.isAfter(fechaInferior) && fecha.isBefore(fechaSuperior))
                inscripcionesFiltradas.add(inscripcion);
        }
        return inscripcionesFiltradas;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(int numeroSocio){
        ArrayList<Inscripcion> inscripcionesFiltradas = new ArrayList<Inscripcion>();
        ArrayList<Inscripcion> inscripcionesSinfiltrar = datos.getInscripciones();

        if (numeroSocio <= 0)
            throw new IllegalArgumentException("Numero de socio invalido");
        for (Inscripcion inscripcion: inscripcionesSinfiltrar){
            if (inscripcion.getSocio().getNumeroSocio() == numeroSocio)
                inscripcionesFiltradas.add(inscripcion);
        }
        return inscripcionesFiltradas;
    }

    public ArrayList<Inscripcion> obtenerInscripciones(){
        return datos.getInscripciones();
    }



    // Excursiones
    public void agregarExcursion(int numDias, Double precioInscripcion, String codigo, String descripcion, LocalDate fecha){
        if (encontrarExcursionPorCodigo(codigo) != null){
            throw new IllegalArgumentException("El codigo de excursion ya esta siendo utilizado");
        }
        datos.getExcursiones().add(new Excursion(numDias, precioInscripcion, codigo, descripcion, fecha));
        System.out.println("[DEBUG]: Added: ");
        System.out.println(datos.getExcursiones().getLast());
    }

    public Excursion encontrarExcursionPorCodigo(String codigo){
        for (Excursion excursion: datos.getExcursiones()){
            if (Objects.equals(excursion.getCodigo(), codigo))
                return excursion;
        }
        return null;
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

    // Socios
    private Socio encontrarSocioPorNumeroSocio(int numeroSocio){
        for (Socio socio: datos.getSocios()){
            if (socio.getNumeroSocio() == numeroSocio){
                return socio;
            }
        }
        return null;
    }
}
