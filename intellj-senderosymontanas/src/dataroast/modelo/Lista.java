package dataroast.modelo;

import java.util.ArrayList;
import java.util.List;

// clase generica
public class Lista<T> {

    // metodo generico para llenar una lista con objetos
    protected static <T> ArrayList<T>  retrieveObjetosClase (ArrayList<T> arrayOrigen) {
        // declarar array nueva
        ArrayList<T> lista = new ArrayList<T>();
        // iniciar iterador
        for (int i = 0; i < lista.size(); i++) {
            lista.add(lista.get(i));
        }
        // Devolver array
        return lista;
    }

}
