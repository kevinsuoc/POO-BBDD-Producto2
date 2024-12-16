package controlador;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Datos;
import modelo.Excursion;
import util.DataErrorException;
import vista.View;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ControladorExcursion {
    public static Datos datos;
    static Stage stage;
    public static View view;

    // FXML
    @FXML
    public Spinner<Integer> numeroDiasSpinner;
    @FXML
    public TextField precioField;
    @FXML
    public TextField codigoExcursionField;
    @FXML
    public TextField descripcionField;
    @FXML
    private DatePicker fechaField;
    @FXML
    private DatePicker fechaField2;
    @FXML
    public TableView<Excursion> tablaExcursiones;
    @FXML
    public TableColumn<Excursion, String> descripcionTableField;
    @FXML
    public TableColumn<Excursion, BigDecimal> precioTableField;
    @FXML
    public TableColumn<Excursion, Integer> numDiasTableField;
    @FXML
    public TableColumn<Excursion, String > codigoTableField;
    @FXML
    public TableColumn<Excursion, LocalDate> fechaTableField;

    public ControladorExcursion(){}

    @FXML
    public void initialize() {
        // Obtener el valor del "spinner"
        if  (numeroDiasSpinner != null){
            SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24000000, 1);
            numeroDiasSpinner.setValueFactory(valueFactory);
        }
    }

    // Enlaces vista FXML-controlador
    @FXML
    protected void onAgregarExcursion() throws IOException {view.agregarExcursion();}
    @FXML
    protected void onMostrarExcursionesFiltradas() throws IOException {view.mostrarExcursiones();}
    @FXML
    public void salir() throws IOException {view.ejecutarMenuPrincipal();}
    @FXML
    public void volver() throws IOException {view.ejecutarMenuExcursiones();}

    // Métodos que manipulan datos
    public void agregarExcursion(int numDias, BigDecimal precioInscripcion, String codigo, String descripcion, LocalDate fecha){
        datos.agregarExcursion(new Excursion(numDias, precioInscripcion, codigo, descripcion, fecha));
    }

    public List<Excursion> obtenerExcursiones(LocalDate fechaInferior, LocalDate fechaSuperior){
        return datos.obtenerExcursiones(fechaInferior, fechaSuperior);
    }

    // Metodos que manipulan vistas
    public void agregarExcursion() throws IOException {
        String precioStr = precioField.getText();
        BigDecimal precio;
        try {
            precio = new BigDecimal(precioStr);
        } catch (NumberFormatException e) {
            view.errorAlert("Formato de precio incorrecto");
            return ;
        }
        LocalDate fecha = fechaField.getValue();
        if (fecha == null){
            view.errorAlert("Ingresa una fecha");
            return ;
        }
        String descripcion = descripcionField.getText();
        String codigoExcursion = codigoExcursionField.getText();
        int numeroDias = numeroDiasSpinner.getValue();

        try {
            agregarExcursion(numeroDias, precio, codigoExcursion, descripcion, fecha);
            view.infoAlert("Excursion agregada");
        } catch (DataErrorException e){
            view.errorAlert(e.getMessage());
        }
    }

    public void mostrarExcursiones() throws IOException {
        LocalDate fecha1 = fechaField.getValue();
        LocalDate fecha2 = fechaField2.getValue();

        if (fecha1 == null || fecha2 == null) {
            view.errorAlert("Ingresa una fecha");
            return ;
        }

        descripcionTableField.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        precioTableField.setCellValueFactory(new PropertyValueFactory<>("precioInscripcion"));
        numDiasTableField.setCellValueFactory(new PropertyValueFactory<>("numDias"));
        codigoTableField.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        fechaTableField.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        tablaExcursiones.setItems(FXCollections.observableArrayList(obtenerExcursiones(fecha1, fecha2)));
    }

}
