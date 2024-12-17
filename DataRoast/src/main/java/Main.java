import controlador.Controlador;
import controlador.ControladorExcursion;
import controlador.ControladorInscripcion;
import controlador.ControladorSocio;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Datos;
import util.HibernateUtil;
import vista.View;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;

public class Main extends Application {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        try {
            HibernateUtil.initializeUtils();
            launch();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            System.out.println("Error ejecutando la aplicación");
        } finally {
            HibernateUtil.closeUtils();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {
        Datos datos = new Datos();
        View view = new View();
        View.stage = stage;

        Controlador controlador = new Controlador();

        Controlador.view = view;
        ControladorInscripcion.view = view;
        ControladorSocio.view = view;
        ControladorExcursion.view = view;

        Controlador.datos =  datos;
        ControladorInscripcion.datos = datos;
        ControladorSocio.datos = datos;
        ControladorExcursion.datos = datos;

        stage.setTitle("Senderos y Montañas");

        controlador.ejecutarPrograma();
    }
}