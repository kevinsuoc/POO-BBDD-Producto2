package vista;

import modelo.Socio;
import modelo.SocioEstandar;
import modelo.SocioFederado;
import modelo.SocioInfantil;

public class MostrarSocioUtil {
    private int idSocio;
    private String nombreSocio;
    private String tipoSocio;
    private String nifSocio;
    private String nombreSeguro;
    private String federacionCodigo;
    private int idTutor;

    public MostrarSocioUtil(Socio socio){
        if (socio instanceof SocioEstandar){
            setSocioEstData((SocioEstandar) socio);
        }
        if (socio instanceof SocioFederado){
            setSocioFedData((SocioFederado) socio);
        }
        if (socio instanceof SocioInfantil){
            setSocioInfData((SocioInfantil) socio);
        }
    }

    public MostrarSocioUtil(SocioEstandar socio){
        setSocioEstData(socio);
    }

    public MostrarSocioUtil(SocioFederado socio){
        setSocioFedData(socio);
    }

    public MostrarSocioUtil(SocioInfantil socio){
        setSocioInfData(socio);
    }

    private void setSocioData(Socio socio){
        this.idSocio = socio.getNumeroSocio();
        this.nombreSocio = socio.getNombre();
    }

    private void setSocioEstData(SocioEstandar socio){
        setSocioData(socio);
        this.nifSocio = socio.getNif();
        this.nombreSeguro = socio.getSeguro().getTipoSeguro().toString();
        this.tipoSocio = "Socio estandar";
    }

    private void setSocioFedData(SocioFederado socio){
        setSocioData(socio);
        this.federacionCodigo = socio.getFederacion().getCodigo();
        this.nifSocio = socio.getNif();
        this.tipoSocio = "Socio federado";
    }

    private void setSocioInfData(SocioInfantil socio){
        setSocioData(socio);
        this.idTutor = socio.getNumeroSocioTutor();
        this.tipoSocio = "Socio intanfil";
    }

    public int getIdSocio() {
        return idSocio;
    }

    public void setIdSocio(int idSocio) {
        this.idSocio = idSocio;
    }

    public String getNombreSocio() {
        return nombreSocio;
    }

    public void setNombreSocio(String nombreSocio) {
        this.nombreSocio = nombreSocio;
    }

    public String getTipoSocio() {
        return tipoSocio;
    }

    public void setTipoSocio(String tipoSocio) {
        this.tipoSocio = tipoSocio;
    }

    public String getNifSocio() {
        return nifSocio;
    }

    public void setNifSocio(String nifSocio) {
        this.nifSocio = nifSocio;
    }

    public String getNombreSeguro() {
        return nombreSeguro;
    }

    public void setNombreSeguro(String nombreSeguro) {
        this.nombreSeguro = nombreSeguro;
    }

    public String getFederacionCodigo() {
        return federacionCodigo;
    }

    public void setFederacionCodigo(String federacionCodigo) {
        this.federacionCodigo = federacionCodigo;
    }

    public int getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(int idTutor) {
        this.idTutor = idTutor;
    }


}
