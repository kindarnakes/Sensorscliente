package Project.GUI;


import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GUI {


    static public void startGUI() {


        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;


        while (!salir) {

            System.out.println();
            System.out.println("---------------------------MENU-----------------------------");
            System.out.println("1. : Sensor de temperatura");
            System.out.println("2. : : Sensor de puerta abierta");
            System.out.println("3. : Sensor de temperatura");

            try {
                System.out.print("Indica una opción a través del dígito que se muestra:");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        opt1();
                        break;
                    case 2:
                        opt2();
                        break;
                    case 3:
                        //opt3();
                        break;

                    default:
                        System.out.println("Indique un dígito válido entre 1-5");
                }

            } catch (InputMismatchException e) {
                System.out.println("¡Cuidado! Solo puedes insertar números.");
                sc.next();
            }
        }

    }


    private static void opt1() {
        Scanner sc = new Scanner(System.in);
        Socket cliente = null;
        BufferedReader entrada = null;
        DataOutputStream salida = null;
        String ipServidor = "localhost";
        boolean valid = false;

        int id_chamber = -1;
        int n_sensor = -1;
        int v_temperatura = 0;
        while (id_chamber <= 0) {
            System.out.println("Introduce la Id del Chamber");
            id_chamber = sc.nextInt();
        }
        do {
            try {
                System.out.println("¿Qué sensor es?");
                System.out.println("------------------");
                System.out.println("1.Sensor 1");
                System.out.println("2.Sensor 2");
                n_sensor = sc.nextInt();
            } catch (Exception e) {
                System.out.println("¡Cuidado! Solo puedes insertar números.");
            }
        } while (n_sensor < 0 && n_sensor < 2);

        do {
            try {
                v_temperatura = sc.nextInt();
                valid = true;
            } catch (Exception e) {
                System.out.println("¡Cuidado! Solo puedes insertar números.");
            }
        } while (valid == false);


        try {

            cliente = new Socket(ipServidor, 2019);
            //asignamos este numero de puerto
            entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            // será lo que enviaremos al servidor
            salida = new DataOutputStream(cliente.getOutputStream());
            // será lo que nos devuelva el servidor

        } catch (UnknownHostException excepcion) {
            System.err.println("El servidor no está levantado");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }

        try {
            salida.writeInt(id_chamber);
            salida.writeInt(n_sensor);
            salida.writeInt(v_temperatura);

            salida.close();
            entrada.close();
            cliente.close();
        } catch (UnknownHostException excepcion) {
            System.err.println("No encuentro el servidor en la dirección" + ipServidor);
        } catch (IOException excepcion) {
            System.err.println("Error de entrada/salida");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }


    private static void opt2() {
    }


}
