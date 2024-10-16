package dataroast.controlador;

import dataroast.vista.View;

import java.util.Scanner;

public class Controlador {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        View view = new View(in);

        view.ejecutarMenuPrincipal();
        in.close();
    }
}
