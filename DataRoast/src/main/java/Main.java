import controlador.Controlador;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.HibernateUtil;
import java.util.Scanner;
import java.util.logging.Level;

public class Main extends Application{
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage){
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Scanner in = new Scanner(System.in);

        try {
            HibernateUtil.initializeUtils();
            Controlador controlador = new Controlador(in);
            // controlador.ejecutarPrograma();

            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 400, 400);
            stage.setTitle("Senderos y Montañas");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
            System.out.println("Error ejecutando la aplicación");
        } finally {
            HibernateUtil.closeUtils();
            in.close();
        }
    }
}