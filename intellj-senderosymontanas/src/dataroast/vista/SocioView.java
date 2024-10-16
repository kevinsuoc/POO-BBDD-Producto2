package dataroast.vista;

import dataroast.modelo.*;

public class SocioView {
    private int  numeroSocio = 0;
    private SocioController socioController;

    public SocioView (SocioController socioController){
        this.socioController = socioController;
    }

    public SocioView (){

    }

    public void socioMenu() throws Exception {
        int userInput;

        do{
            System.out.println("Bienvenidos a Senderos y Montañeros");
            System.out.println(" # Gestion de socios # ");
            System.out.println("1- Nuevo socio.");
            System.out.println("2- Mostrar todos los socios.");
            System.out.println("3- Mostrar socio Estandar.");
            System.out.println("4- Mostrar socio Federado.");
            System.out.println("5- Mostrar socio Infantil.");
            System.out.println("6- Volver al menú anterior.");

            userInput = showIntRangeInput(1,6);
        }while (userInput < 1 || userInput >6 );
        printSelectedCase (userInput);
    }

    private void printSelectedCase (int userInput) throws Exception {
        switch (userInput){
            case 1:
                try {
                    printNewSocio();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                this.socioMenu();
                break;
            case 2:
                printAllSocio();
                break;
            case 3:
                printSocioEstandar();
                break;
            case 4:
                printSocioFederado();
                break;
            case 5:
                printSocioInfantil();
                break;
            case 6:
                MainView.mainView();
                break;
        }
    }

    protected Socio printNewSocio() throws Exception {
        String nombre = showStringInput("Introducir nombre");
        String socioType = showStringInput(" Tipo de socio: (Estandar/Federado/Insfantil");

        if (socioType.equalsIgnoreCase("Estandar")){
            SocioEstandar socio = new SocioEstandar(numeroSocio++, nombre, nif, seguro );
            socioController.save(socio);
            return socio;
        }
        if (socioType.equalsIgnoreCase("Federado")){
            SocioFederado socio = new SocioFederado(numeroSocio++, nombre, nif, Federacion );
            socioController.save(socio);
            return socio;
        }
        if (socioType.equalsIgnoreCase("Infantil")){
            SocioInfantil = new SocioInfantil(numeroSocio++, nombre, nif, SocioAdulto );
            socioController.save(socio);
            return socio;
        }

        this.socioMenu();
        return null;

    }


}
