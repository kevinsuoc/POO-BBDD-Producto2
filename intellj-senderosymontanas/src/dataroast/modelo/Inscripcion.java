package dataroast.modelo;

public class Inscripcion {
    private int numeroInscripcion;
    private Socio socio;
    private Excursion excursion;

    public Inscripcion(int numeroInscripcion, Socio socio, Excursion excursion) {
        this.numeroInscripcion = numeroInscripcion;
        this.socio = socio;
        this.excursion = excursion;
    }

    public int getNumeroInscripcion() {
        return numeroInscripcion;
    }

    public Socio getSocio() {
        return socio;
    }

    public Excursion getExcursion() {
        return excursion;
    }

    public void setNumeroInscripcion(int numeroInscripcion) {
        this.numeroInscripcion = numeroInscripcion;
    }

    public void setSocio(Socio socio) {
        this.socio = socio;
    }

    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }

    public String toString() {
        return "Numero de inscripcion: " + numeroInscripcion + "\n" +
                "Codigo de excursion: " + excursion.getCodigo() + "\n" +
                "Numero de socio: " + socio.getNumeroSocio();
    }
}
