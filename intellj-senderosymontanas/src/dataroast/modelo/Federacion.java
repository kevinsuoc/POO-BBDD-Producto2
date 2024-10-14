package dataroast.modelo;

public class Federacion {
    private String codigo;
    private String nombre;

    public Federacion(String codigo, String nombre){
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return "Codigo de federacion: " + codigo + "\n" +
                "Nombre: " + nombre;
    }
}
