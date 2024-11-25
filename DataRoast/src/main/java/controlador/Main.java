package controlador;

import util.HibernateUtil;
import java.util.Scanner;
import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        java.util.logging.Logger.getLogger("org.hibernate").setLevel(Level.OFF);

        Scanner in = new Scanner(System.in);

        try {
            HibernateUtil.initializeUtils();
            Controlador controlador = new Controlador(in);
            controlador.ejecutarPrograma();
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
