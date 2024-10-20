package dataroast.modelo;

public class Seguro {
    private TipoSeguro tipoSeguro;
    private double precio;

    public Seguro(double precio, TipoSeguro tipoSeguro){
        this.tipoSeguro = tipoSeguro;
        this.precio = precio;
    }

    public TipoSeguro getTipoSeguro() {
        return tipoSeguro;
    }

    public double getPrecio() {
        return precio;
    }

    public void setTipoSeguro(TipoSeguro tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String toString() {
        return switch (tipoSeguro) {
            case BASICO -> "Precio: " + precio + "\n" +
                    "Tipo seguro: BASICO";
            case COMPLETO -> "Precio: " + precio + "\n" +
                    "Tipo seguro: COMPLETO";
        };
    }

}
