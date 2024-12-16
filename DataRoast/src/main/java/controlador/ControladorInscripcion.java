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
import vista.MostrarInscripcionUtil;
import vista.MostrarSocioUtil;
import vista.View;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ControladorInscripcion {
    public static Datos datos;
    static Stage stage;
    public static View view;

    // FXML
    @FXML
    public Spinner<Integer> idInscripcionSpinner;
    @FXML
    public Spinner<Integer> idSocioSpinner;
    @FXML
    public TextField codigoExcursionField;
    @FXML
    public DatePicker fechaField;
    @FXML
    public DatePicker fechaField2;
    @FXML
    public TableView<MostrarInscripcionUtil> tablaExcursiones;
    @FXML
    public TableColumn<MostrarInscripcionUtil, Integer> idInscripcionTableField;
    @FXML
    public TableColumn<MostrarInscripcionUtil, Integer> numeroSocioTableField;
    @FXML
    public TableColumn<MostrarInscripcionUtil, String> nombreSocioTableField;
    @FXML
    public TableColumn<MostrarInscripcionUtil, LocalDate> fechaTableField;
    @FXML
    public TableColumn<MostrarInscripcionUtil, String> descripcionTableField;
    @FXML
    public TableColumn<MostrarInscripcionUtil, BigDecimal> precioTotalTableField;
    @FXML
    public ToggleButton toggleFiltroFecha;
    @FXML
    public ToggleButton toggleFiltroSocio;

    public ControladorInscripcion(){}

    @FXML
    public void initialize() {
        // Obtener el valor del "spinner"
        if  (idSocioSpinner != null){
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24000000, 1);
            idSocioSpinner.setValueFactory(valueFactory);
        }

        if  (idInscripcionSpinner != null){
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24000000, 1);
            idInscripcionSpinner.setValueFactory(valueFactory);
        }
    }

    // Metodos enlace vista FXML-Controlador
    @FXML
    protected void onMostrarInscripcionesFiltradas() throws IOException {view.mostrarInscripcionesFiltradas();}
    @FXML
    protected void onEliminarInscripcion() throws IOException {view.eliminarInscripcion();}
    @FXML
    protected void onAgregarInscripcion() throws IOException {view.agregarInscripcion();}
    @FXML
    protected void salir() throws IOException {view.ejecutarMenuPrincipal();}
    @FXML
    public void volver() throws IOException {view.ejecutarMenuInscripciones();}

    // Metodos que manipulan datos
    public Inscripcion agregarInscripcion(int numeroSocio, String codigoExcursion){
        Socio socio = datos.obtenerSocio(numeroSocio);
        if (socio == null){
            throw new DataErrorException("Socio no encontrado");
        }
        Excursion excursion = datos.obtenerExcursion(codigoExcursion);
        if (excursion == null) {
            throw new DataErrorException("Excursion no encontrada");
        }
        return datos.agregarInscripcion(new Inscripcion(socio, excursion));
    }

    public void eliminarInscripcion(int numeroInscripcion) {
        datos.eliminarInscripcion(numeroInscripcion);
    }

    public List<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior, int numeroSocio){
        return datos.obtenerInscripciones(fechaInferior, fechaSuperior, numeroSocio);
    }

    public List<Inscripcion> obtenerInscripciones(LocalDate fechaInferior, LocalDate fechaSuperior){
        return datos.obtenerInscripciones(fechaInferior, fechaSuperior);
    }

    public List<Inscripcion> obtenerInscripciones(int numeroSocio){
        return datos.obtenerInscripciones(numeroSocio);
    }

    public List<Inscripcion> obtenerInscripciones(){
        return datos.obtenerInscripciones();
    }

    // Metodos que manipulan vistas
    public void eliminarInscripcion() throws IOException {
        Integer numeroInscripcion = idInscripcionSpinner.getValue();

        if (numeroInscripcion == null){
            view.errorAlert("Ingresa el numero de inscripcion");
            return ;
        }

        try {
            eliminarInscripcion(numeroInscripcion);
            view.infoAlert("Inscripcion eliminada");
        } catch (DataErrorException e){
            view.errorAlert(e.getMessage());
        }
    }

    public void agregarInscripcion() throws IOException {
        String codigoExcursion = codigoExcursionField.getText();
        Integer idSocio = idSocioSpinner.getValue();

        if (idSocio == null || codigoExcursion == null){
            view.errorAlert("Ingresa todos los datos");
            return ;
        }

        try {
            agregarInscripcion(idSocio, codigoExcursion);
            view.infoAlert("Inscripcion agregada");
        } catch (DataErrorException e){
            view.errorAlert(e.getMessage());
        }
    }

    public void mostrarInscripciones() throws IOException {
        LocalDate fecha1 = fechaField.getValue();
        LocalDate fecha2 = fechaField2.getValue();
        Integer idSocio = idSocioSpinner.getValue();

        List<Inscripcion> inscripciones;
        if (toggleFiltroFecha.isSelected() && (fecha1 == null || fecha2 == null)){
            view.errorAlert("Ingresa la fecha");
            return ;
        }

        if (toggleFiltroSocio.isSelected() && idSocio == null){
            view.errorAlert("Ingresa el id de socio");
            return ;
        }

        if (toggleFiltroFecha.isSelected() && toggleFiltroSocio.isSelected()) {
            inscripciones = obtenerInscripciones(fecha1, fecha2, idSocio);
        } else if (toggleFiltroSocio.isSelected()){
            inscripciones = obtenerInscripciones(idSocio);
        } else if (toggleFiltroFecha.isSelected()){
            inscripciones = obtenerInscripciones(fecha1, fecha2);
        } else {
            inscripciones = obtenerInscripciones();
        }

        idInscripcionTableField.setCellValueFactory(new PropertyValueFactory<>("idInscripcion"));
        numeroSocioTableField.setCellValueFactory(new PropertyValueFactory<>("numeroSocio"));
        nombreSocioTableField.setCellValueFactory(new PropertyValueFactory<>("nombreSocio"));
        fechaTableField.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        descripcionTableField.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioTotalTableField.setCellValueFactory(new PropertyValueFactory<>("precio"));

        ObservableList<MostrarInscripcionUtil> inscripcionesAMostrar = FXCollections.observableArrayList();

        for(Inscripcion inscripcion: inscripciones){
            inscripcionesAMostrar.add(new MostrarInscripcionUtil(inscripcion));
        }

        tablaExcursiones.setItems(FXCollections.observableArrayList(inscripcionesAMostrar));
    }
}