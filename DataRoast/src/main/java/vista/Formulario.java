package vista;

import modelo.TipoSeguro;
import modelo.TipoSocio;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

abstract public class  Formulario {
        Scanner in;

        public Formulario(Scanner in) {
            this.in = in;
        }

        protected int obtenerNumero(String msg) {
            int numeroIngresado;

            while (true) {
                try {
                    System.out.print(msg + ": ");
                    numeroIngresado = in.nextInt();
                    in.nextLine();
                    return numeroIngresado;
                } catch (InputMismatchException ignored) {
                    System.out.println("Debe ingresar un numero valido");
                    in.nextLine();
                }
            }
        }

        protected BigDecimal obtenerBigDecimal(String msg) {
            BigDecimal numeroIngresado;

            while (true) {
                try {
                    System.out.print(msg + ": ");
                    numeroIngresado = in.nextBigDecimal();
                    in.nextLine();
                    return numeroIngresado;
                } catch (InputMismatchException ignored) {
                    System.out.println("Debe ingresar un numero valido");
                    in.nextLine();
                }
            }
        }

        protected String obtenerString(String msg) {
            String stringIngresado;

            System.out.print(msg + ": ");
            stringIngresado = in.nextLine();
            return stringIngresado;
        }

        protected LocalDate obtenerFecha(String msg) {
            LocalDate fecha;

            while (true) {
                try {
                    System.out.println(msg);
                    int dia = obtenerNumero("Ingresa el dia");
                    int mes = obtenerNumero("Ingresa el mes");
                    int año = obtenerNumero("Ingresa el año");

                    fecha = LocalDate.of(año, mes, dia);
                    return fecha;
                } catch (DateTimeException ignored) {
                    System.out.println("Error con la fecha ingresada");
                }
            }
        }

        protected boolean obtenerBool(String msg) {
            String respuesta;

            while (true) {
                System.out.print(msg + " (y/n): ");
                respuesta = in.nextLine();
                if (respuesta.equals("y") || respuesta.equals("yes") || respuesta.equals("si") || respuesta.equals("s"))
                    return true;
                if (respuesta.equals("n") || respuesta.equals("no"))
                    return false;
            }
        }

        protected TipoSeguro obtenerTipoSeguro(String msg){
            int respuesta;

            System.out.println(msg);
            while (true){
                respuesta = obtenerNumero("ingresa tipo de seguro (1. BASICO, 2. COMPLETO)");
                if (respuesta == 1)
                    return TipoSeguro.BASICO;
                if (respuesta == 2)
                    return TipoSeguro.COMPLETO;
            }
        }

        protected TipoSocio obtenerTipoSocio(String msg){
            int respuesta;

            System.out.println(msg);
            while (true){
                respuesta = obtenerNumero("ingresa tipo de socio (1. ESTANDAR, 2. FEDERADO, 3. INFANTIL)");
                if (respuesta == 1)
                    return TipoSocio.ESTANDAR;
                if (respuesta == 2)
                    return TipoSocio.FEDERADO;
                if (respuesta == 3)
                    return TipoSocio.INFANTIL;
            }
        }
    }