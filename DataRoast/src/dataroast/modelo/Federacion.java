package dataroast.modelo;

public class Federacion {
    private String codigo;
    private String nombre;

    public Federacion(String codigo, String nombre){
        setNombre(nombre);
        setCodigo(codigo);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setCodigo(String codigo) {
        if (codigo.length() < 3)
            throw new ModelException("Codigo de federacion muy corto");
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        if (nombre.length() < 3)
            throw new ModelException("Nombre de la federacion muy corto");
        this.nombre = nombre;
    }

    public String toString() {
        return "Codigo de federacion: " + codigo + "\n" +
                "Nombre: " + nombre;
    }
}
