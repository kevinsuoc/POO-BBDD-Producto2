package controlador;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import modelo.Datos;
import vista.View;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class Controlador {
    public static View view;
    public static Datos datos;
    public final static BigDecimal precioCuotaExcursionista = new BigDecimal(10);

    public Controlador(){}

    public void ejecutarPrograma() throws IOException {
        view.ejecutarMenuPrincipal();
    }

    @FXML
    protected void onMenuExcursion() throws IOException {
        view.ejecutarMenuExcursiones();
    }

    @FXML
    protected void onMenuSocio() throws IOException {
        view.ejecutarMenuSocios();
    }

    @FXML
    protected void onMenuInscripcion() throws IOException {
        view.ejecutarMenuInscripciones();
    }

    @FXML
    protected void salir(){
        Platform.exit();
    }
}
