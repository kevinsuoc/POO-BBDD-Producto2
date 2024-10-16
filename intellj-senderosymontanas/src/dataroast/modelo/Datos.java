package dataroast.modelo;

import java.util.ArrayList;

import static dataroast.modelo.TipoSeguro.*;

public class Datos {
    ArrayList<Federacion> federaciones = new ArrayList<Federacion>();
    ArrayList<Excursion> excursiones = new ArrayList<Excursion>();
    ArrayList<Inscripcion> inscripciones = new ArrayList<Inscripcion>();
    ArrayList<Seguro> seguros = new ArrayList<Seguro>();
    ArrayList<Socio> socios = new ArrayList<Socio>();

    public Datos(){
        seguros.add(new Seguro(10, BASICO));
        seguros.add(new Seguro(15, COMPLETO));
    }

    public ArrayList<Excursion> getExcursiones() {
        return excursiones;
    }
}
