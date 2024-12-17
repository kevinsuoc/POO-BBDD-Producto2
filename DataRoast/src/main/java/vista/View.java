package vista;

import controlador.Controlador;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;
import modelo.Socio;
import modelo.SocioEstandar;
import modelo.SocioFederado;
import modelo.SocioInfantil;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class View {
    public static Stage stage;

    public View(){}

    // Mostrar vista
    private void show(String view) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(View.class.getResource(view));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    // Mostrar Alertas
    public void infoAlert(String info){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setTitle(null);
        DialogPane dialogPane = alerta.getDialogPane();
        dialogPane.setStyle("-fx-background-color: lightblue; -fx-font-size: 16px;");
        alerta.setContentText(info);
        alerta.showAndWait();
    }

    public void infoAlert(String info, String descripcion) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(descripcion);
        alerta.setTitle(null);
        DialogPane dialogPane = alerta.getDialogPane();
        dialogPane.setStyle("-fx-background-color: lightblue; -fx-font-size: 16px;");
        alerta.setContentText(info);
        alerta.showAndWait();
    }

    public void errorAlert(String error){
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setTitle(null);
        DialogPane dialogPane = alerta.getDialogPane();
        dialogPane.setStyle("-fx-background-color: #ffcccb; -fx-font-size: 16px;");
        alerta.setContentText(error);
        alerta.showAndWait();
    }

    // Vistas

    // Menues principales
    public void ejecutarMenuPrincipal() throws IOException {show("menu-principal.fxml");}

    public void ejecutarMenuSocios() throws IOException {show("menu-socio.fxml");}

    public void ejecutarMenuExcursiones() throws IOException {show("menu-excursion.fxml");}

    public void ejecutarMenuInscripciones() throws IOException {show("menu-inscripcion.fxml");}

    // Vistas de menu de socio
    public void mostrarSociosForm() throws IOException {show("mostrar-socios.fxml");}
    public void mostrarFacturas() throws IOException {show("facturas-mensuales.fxml");}
    public void mostrarEliminarSocioForm() throws IOException {show("eliminar-socio.fxml");}
    public void mostrarModificarSeguroForm() throws IOException {show("modificar-seguro.fxml");}
    public void mostrarAgregarSocioEstandarForm() throws IOException {show("agregar-estandar.fxml");}
    public void mostrarAgregarSocioFederadoForm() throws IOException {show("agregar-federado.fxml");}
    public void mostrarAgregarSocioInfantilForm() throws IOException { show("agregar-infantil.fxml");}

    // Vistas excursiones
    public void agregarExcursion() throws IOException {show("agregar-excursion.fxml");}
    public void mostrarExcursiones() throws IOException {show("mostrar-excursiones.fxml");}

    // Vistas inscripciones
    public void mostrarInscripcionesFiltradas() throws IOException {show("mostrar-inscripcion.fxml");}
    public void eliminarInscripcion() throws IOException {show("eliminar-inscripcion.fxml");}
    public void agregarInscripcion() throws IOException {show("agregar-inscripcion.fxml");}
}
