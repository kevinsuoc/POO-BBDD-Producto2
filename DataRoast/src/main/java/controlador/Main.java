package controlador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        Controlador controlador = new Controlador(in);
        controlador.ejecutarPrograma();

        in.close();
    }
}
