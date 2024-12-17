package controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.*;
import util.DataErrorException;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import vista.MostrarSocioUtil;
import vista.View;

public class ControladorSocio {
    public static Datos datos;
    static Stage stage;
    public static View view;

    // FXML
    @FXML
    public ComboBox<String> tipoSocioABuscar;
    @FXML
    public TableView<MostrarSocioUtil> tablaSocios;
    @FXML
    public TableColumn<MostrarSocioUtil, String> idSocio;
    @FXML
    public TableColumn<MostrarSocioUtil, String> nombreSocio;
    @FXML
    public TableColumn<MostrarSocioUtil, String> tipoSocio;
    @FXML
    public TableColumn<MostrarSocioUtil, String> nifSocio;
    @FXML
    public TableColumn<MostrarSocioUtil, String> nombreSeguro;
    @FXML
    public TableColumn<MostrarSocioUtil, String> federacionCodigo;
    @FXML
    public TableColumn<MostrarSocioUtil, String> idTutor;
    @FXML
    public Spinner<Integer> idSocioSpinner;
    @FXML
    public ChoiceBox<String> tipoSeguro;
    @FXML
    public TextField nombreSocioField;
    @FXML
    public TextField nifSocioField;
    @FXML
    public TextField codigoFederacionField;

    public ControladorSocio() {}

    @FXML
    public void initialize() {
        // Obtener el valor del "spinner"
        if  (idSocioSpinner != null){
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24000000, 1);
            idSocioSpinner.setValueFactory(valueFactory);
        }
    }

    // Metodos de enlace vista FXML - controlador
    @FXML
    protected void onAgregarSocioEstandar() throws IOException{view.mostrarAgregarSocioEstandarForm();}
    @FXML
    protected void onModificarSeguroSocioEstandar() throws IOException {view.mostrarModificarSeguroForm();}
    @FXML
    protected void onAgregarSocioFederado() throws IOException{view.mostrarAgregarSocioFederadoForm();}
    @FXML
    protected void onAgregarSocioInfantil() throws IOException{view.mostrarAgregarSocioInfantilForm();}
    @FXML
    protected void onEliminarSocio() throws IOException {view.mostrarEliminarSocioForm();}
    @FXML
    protected void onMostrarSocios() throws IOException {view.mostrarSociosForm();}
    @FXML
    protected void onMostrarFacturasMensuales() throws IOException {view.mostrarFacturas();}
    @FXML
    protected void salir() throws IOException {view.ejecutarMenuPrincipal();}
    @FXML
    protected void volver() throws IOException {view.ejecutarMenuSocios();}

    // Metodos que manipulan datos
    public SocioEstandar agregarSocioEstandar(String nif, String nombre, TipoSeguro tipoSeguro) {
        Seguro seguro = datos.obtenerSeguro(tipoSeguro);
        if (seguro == null) {
            throw new DataErrorException("Seguro no encontrado");
        }
        return datos.agregarSocioEstandar(new SocioEstandar(nif, nombre, seguro));
    }

    public SocioFederado agregarSocioFederado(String nif, String nombre, String codigoFederacion) {
        Federacion federacion = datos.obtenerFederacion(codigoFederacion);
        if (federacion == null) {
            throw new DataErrorException("Federacion no encontrada");
        }
        return datos.agregarSocioFederado(new SocioFederado(nif, nombre, federacion));
    }

    public SocioInfantil agregarSocioInfantil(String nombre, int numeroTutor){
        return datos.agregarSocioInfantil(new SocioInfantil(nombre, numeroTutor));
    }

    public void eliminarSocio(int numeroSocio){
        datos.eliminarSocio(numeroSocio);
    }

    public List<Socio> obtenerSocios() {
        return datos.obtenerSocios();
    }

    public List<Socio> obtenerSociosPorTipo(TipoSocio tipoSocio) {
        List<Socio> socios = new ArrayList<>();
        switch (tipoSocio){
            case TipoSocio.ESTANDAR ->  socios.addAll(datos.obtenerSociosEstandar());
            case TipoSocio.INFANTIL ->  socios.addAll(datos.obtenerSociosInfantiles());
            case TipoSocio.FEDERADO -> socios.addAll(datos.obtenerSociosFederados());
        }
        return socios;
    }

    public Socio obtenerSocioPorNumero(int numeroSocio) {
        return datos.obtenerSocio(numeroSocio);
    }

    public void cambiarTipoSeguro(int numeroSocio, TipoSeguro tipoSeguro) {
        SocioEstandar socioEstandar = datos.obtenerSocioEstandar(numeroSocio);
        if (tipoSeguro == null)
            throw new DataErrorException("Error con el seguro");
        if (socioEstandar == null)
            throw new DataErrorException("El socio estandar no existe");
        if (socioEstandar.getSeguro().getTipoSeguro() == tipoSeguro)
            throw new DataErrorException("El socio ya tiene ese seguro");
        socioEstandar.getSeguro().setTipoSeguro(tipoSeguro);
        datos.actualizarSocioEstandar(socioEstandar);
    }

    public List<Inscripcion> obtenerInscripcionesMesSocio(int numeroSocio){
        YearMonth thisMonth = YearMonth.now();
        LocalDate firstDayOfMonth = thisMonth.atDay(1);
        LocalDate lastDayOfMonth = thisMonth.atEndOfMonth();
        return datos.obtenerInscripciones(firstDayOfMonth, lastDayOfMonth, numeroSocio);
    }

    // Metodos que manipulan vistas
    public void mostrarSocios(ActionEvent actionEvent) throws IOException {
        idSocio.setCellValueFactory(new PropertyValueFactory<>("idSocio"));
        nombreSocio.setCellValueFactory(new PropertyValueFactory<>("nombreSocio"));
        tipoSocio.setCellValueFactory(new PropertyValueFactory<>("tipoSocio"));
        nifSocio.setCellValueFactory(new PropertyValueFactory<>("nifSocio"));
        nombreSeguro.setCellValueFactory(new PropertyValueFactory<>("nombreSeguro"));
        federacionCodigo.setCellValueFactory(new PropertyValueFactory<>("federacionCodigo"));
        idTutor.setCellValueFactory(new PropertyValueFactory<>("idTutor"));

        String opcion = (String) tipoSocioABuscar.getValue();
        if (opcion == null || opcion.isEmpty()) {
            view.errorAlert("Selecciona un tipo de socio");
            return;
        }

        ObservableList<MostrarSocioUtil> sociosAMostrar = FXCollections.observableArrayList();

        switch (opcion){
            case "Todos los socios":
                List<Socio> socios = datos.obtenerSocios();
                for(Socio socio: socios){
                    sociosAMostrar.add(new MostrarSocioUtil(socio));
                }
                break;
            case "Socios estandar":
                List<SocioEstandar> sociosEstandar = datos.obtenerSociosEstandar();
                for(Socio socio: sociosEstandar){
                    sociosAMostrar.add(new MostrarSocioUtil(socio));
                }
                break;
            case "Socios federados":
                List<SocioFederado> sociosFederados = datos.obtenerSociosFederados();
                for(Socio socio: sociosFederados){
                    sociosAMostrar.add(new MostrarSocioUtil(socio));
                }
                break;
            case "Socios infantiles":
                List<SocioInfantil> sociosInfantiles = datos.obtenerSociosInfantiles();
                for(Socio socio: sociosInfantiles){
                    sociosAMostrar.add(new MostrarSocioUtil(socio));
                }
                break;
            default: return;
        }

        tablaSocios.setItems(FXCollections.observableArrayList(sociosAMostrar));
    }

    public void mostrarFacturas(){
        int numeroSocio = idSocioSpinner.getValue();
        BigDecimal baseCuota = Controlador.precioCuotaExcursionista;
        BigDecimal totalCuota;
        BigDecimal baseExcursiones = new BigDecimal(0);
        BigDecimal totalExcursiones;
        BigDecimal total;
        StringBuilder textoAlerta = new StringBuilder();

        Socio socio = datos.obtenerSocio(numeroSocio);

        if (socio != null){
            List<Inscripcion> inscripcionesDelMes = obtenerInscripcionesMesSocio(numeroSocio);

            for (Inscripcion inscripcion: inscripcionesDelMes) {
                baseExcursiones = baseExcursiones.add(inscripcion.getExcursion().getPrecioInscripcion());
            }
            if (socio instanceof SocioFederado)
                totalExcursiones = SocioFederado.obtenerPrecioExcursionConDescuento(baseExcursiones);
            else
                totalExcursiones = baseExcursiones;

            if (socio instanceof SocioFederado)
                totalCuota = SocioFederado.obtenerCuotaConDescuento(baseCuota);
            else if (socio instanceof SocioInfantil)
                totalCuota = SocioInfantil.obtenerCuotaConDescuento(baseCuota);
            else
                totalCuota = baseCuota;

            total = totalCuota.add(totalExcursiones);

            textoAlerta.append(socio.toString() + "\n");
            textoAlerta.append("Precio mensual de excursiones base: " + baseExcursiones + "\n");

            if (socio instanceof SocioFederado)
                textoAlerta.append("Con descuento de socio federado: " + totalExcursiones + "\n");

            textoAlerta.append("Precio de cuota excursionista base: " + baseCuota + "\n");

            if (socio instanceof SocioFederado)
                textoAlerta.append("Con descuento de socio federado: " + totalCuota + "\n");

            if (socio instanceof SocioInfantil)
                textoAlerta.append("Con descuento de socio infantil: " + totalCuota + "\n");

            textoAlerta.append("Total: " + total + "\n");
        } else {
            view.errorAlert("Socio no encontrado");
            return ;
        }

        view.infoAlert(textoAlerta.toString(), "Cuota mensual de socio");
    }

    public void eliminarSocio() throws IOException {
        try {
            eliminarSocio(idSocioSpinner.getValue());
            view.infoAlert("Socio eliminado");
        } catch (DataErrorException e){
           view.errorAlert(e.getMessage());
        }
    }

    public void cambiarSeguro() {
        int numeroSocio = idSocioSpinner.getValue();
        String seguro = tipoSeguro.getValue();
        TipoSeguro seguroNuevo = null;

        if (seguro == null || seguro.isEmpty())
            return ;
        switch (seguro){
            case "Seguro basico": seguroNuevo = TipoSeguro.BASICO; break;
            case "Seguro completo": seguroNuevo = TipoSeguro.COMPLETO; break;
        }

        try {
            cambiarTipoSeguro(numeroSocio, seguroNuevo);
            view.infoAlert("Seguro modificado");
        } catch (DataErrorException e){
            view.errorAlert(e.getMessage());
        }
    }

    public void agregarSocioEstandar() throws IOException {
        String seguro = tipoSeguro.getValue();
        TipoSeguro tipoSeguro = null;

        if (seguro == null || seguro.isEmpty())
            return ;

        switch (seguro){
            case "Seguro basico": tipoSeguro = TipoSeguro.BASICO; break;
            case "Seguro completo": tipoSeguro = TipoSeguro.COMPLETO; break;
        }

        try {
            agregarSocioEstandar(nifSocioField.getText(), nombreSocioField.getText(), tipoSeguro);
            view.infoAlert("Socio agregado");
        } catch (DataErrorException e){
            view.errorAlert(e.getMessage());
        }
    }

    public void agregarSocioFederado() throws IOException {
        try {
            agregarSocioFederado(nifSocioField.getText(), nombreSocioField.getText(), codigoFederacionField.getText());
            view.infoAlert("Socio agregado");
        } catch (DataErrorException e){
            view.errorAlert(e.getMessage());
        }
    }

    public void agregarSocioInfantil() throws IOException {
        try {
            agregarSocioInfantil(nombreSocioField.getText(), idSocioSpinner.getValue());
            view.infoAlert("Socio agregado");
        } catch (DataErrorException e){
            view.errorAlert(e.getMessage());
        }
    }
}