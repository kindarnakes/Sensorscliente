package Project.GUI;


import Project.Model.Chamber;
import Project.Model.ClientType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
            System.out.println("2. : Sensor de puerta abierta");
            System.out.println("3. : Terminal de administración");
            System.out.println("4. : Salir");

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
                        opt3();
                        break;
                    case 4:
                        salir = true;
                        break;
                    default:
                        System.out.println("Indique un dígito válido entre 1-4");
                }

            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("¡Cuidado! Solo puedes insertar números.");
                sc.next();
            }
        }

    }


    private static void opt1() {
        Scanner sc = new Scanner(System.in);
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        String ipServidor = "localhost";
        boolean valid = false;
        boolean updated = false;
        String yesorno = "";
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
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                e.printStackTrace();
                System.out.println("¡Cuidado! Solo puedes insertar números.");
            }
        } while (n_sensor < 0 && n_sensor < 2);

        do {
            try {
                System.out.println("Introduce el valor de Sensor");
                v_temperatura = sc.nextInt();
                valid = true;


                do {
                    System.out.println("¿Quieres cambiar otra vez el valor del sensor?");
                    System.out.println("----------------------------------------");
                    System.out.println("Y para cambiar el valor del sensor");
                    System.out.println("Cualquier otra letra/numero para no cambiar el valor del sensor");
                    yesorno = sc.next();


                } while (yesorno == "y");


            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                e.printStackTrace();
                System.out.println("¡Cuidado! Solo puedes insertar números.");
            }
        } while (valid == false);


        try {

            cliente = new Socket(ipServidor, 55000);
            //asignamos este numero de puerto
            //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            // será lo que enviaremos al servidor
            //salida = new DataOutputStream(cliente.getOutputStream());
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
            // será lo que nos devuelva el servidor

        } catch (UnknownHostException excepcion) {
            excepcion.printStackTrace();
            System.err.println("El servidor no está levantado");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: " + e);

        }

        try {
            salida.writeObject(ClientType.Temperatura);
            salida.flush();
            salida.writeInt(id_chamber);
            salida.flush();
            salida.writeInt(n_sensor);
            salida.flush();
            salida.writeInt(v_temperatura);
            salida.flush();
            updated = entrada.readBoolean();



            salida.close();
            entrada.close();
            cliente.close();

            if (updated) {
                System.out.println("Se ha actualizado correctamente");

            } else {
                System.out.println("No se ha podido actualizar correctamente");
            }
        } catch (UnknownHostException excepcion) {
            System.err.println("No encuentro el servidor en la dirección" + ipServidor);
        } catch (IOException excepcion) {
            System.err.println("Error de entrada/salida");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }


    private static void opt2() {
        Scanner sc = new Scanner(System.in);
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        String ipServidor = "localhost";
        boolean valid = false;
        boolean updated = false;
        String yesorno = "";
        int id_chamber = -1;
        boolean v_puerta = false;

        while (id_chamber <= 0) {
            System.out.println("Introduce la Id del Chamber");
            id_chamber = sc.nextInt();
        }

        do {
            try {
                System.out.println("Introduce el valor de Sensor 'True' o 'False': ");
                v_puerta= sc.nextBoolean();
                valid = true;


                do {
                    System.out.println("¿Quieres cambiar otra vez el valor del sensor de la puerta?");
                    System.out.println("----------------------------------------");
                    System.out.println("Pulse y para cambiar el valor del sensor");
                    System.out.println("Cualquier otra letra/numero para no cambiar el valor del sensor");
                    yesorno = sc.next();


                } while (yesorno == "y");


            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                e.printStackTrace();
                System.out.println("¡Cuidado! Solo puedes insertar números.");
            }
        } while (valid == false);

        try {

            cliente = new Socket(ipServidor, 55000);
            //asignamos este numero de puerto
            //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            // será lo que enviaremos al servidor
            //salida = new DataOutputStream(cliente.getOutputStream());
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
            // será lo que nos devuelva el servidor

        } catch (UnknownHostException excepcion) {
            excepcion.printStackTrace();
            System.err.println("El servidor no está levantado");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: " + e);

        }

        try {
            salida.writeObject(ClientType.Puerta);
            salida.flush();
            salida.writeInt(id_chamber);
            salida.flush();
            salida.writeBoolean(v_puerta);
            salida.flush();
            updated = entrada.readBoolean();



            salida.close();
            entrada.close();
            cliente.close();

            if (updated) {
                System.out.println("Se ha actualizado correctamente");

            } else {
                System.out.println("No se ha podido actualizar correctamente");
            }
        } catch (UnknownHostException excepcion) {
            System.err.println("No encuentro el servidor en la dirección" + ipServidor);
        } catch (IOException excepcion) {
            System.err.println("Error de entrada/salida");
            excepcion.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
    }

    private static void opt3() {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        int opcion;


        while (!salir) {

            System.out.println();
            System.out.println("----------------------MENU ADMINISTRACIÓN--------------------------");
            System.out.println("1.Ver el estado de los sensores, de la puerta y del motor de una cámara frigorífica.");
            System.out.println("2.Ingresar una nueva cámara frigorífica. ");
            System.out.println("3.Salir");

            try {
                System.out.print("Indica una opción a través del dígito que se muestra:");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        opta1();
                        break;
                    case 2:
                        //opta2();
                        break;
                    case 3:
                        salir = true;
                        break;

                    default:
                        System.out.println("Indique un dígito válido entre 1-5");
                }

            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("¡Cuidado! Solo puedes insertar números.");
                sc.next();
            }

        }

    }

    private static void opta1(){
        Scanner sc = new Scanner(System.in);
        Socket cliente = null;
        ObjectInputStream entrada = null;
        ObjectOutputStream salida = null;
        String ipServidor = "localhost";
        int id_chamber = -1;
        int option = 1;
        boolean respuesta;
        Chamber c;

        while (id_chamber <= 0) {
            System.out.println("Introduce la Id del Chamber");
            id_chamber = sc.nextInt();
        }

        try {

            cliente = new Socket(ipServidor, 55000);
            //asignamos este numero de puerto
            //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            // será lo que enviaremos al servidor
            //salida = new DataOutputStream(cliente.getOutputStream());
            salida = new ObjectOutputStream(cliente.getOutputStream());
            entrada = new ObjectInputStream(cliente.getInputStream());
            // será lo que nos devuelva el servidor

        } catch (UnknownHostException excepcion) {
            excepcion.printStackTrace();
            System.err.println("El servidor no está levantado");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error: " + e);

        }

        try {
            salida.writeObject(ClientType.Administracion);
            salida.flush();
            salida.writeInt(option);
            salida.flush();
            respuesta = entrada.readBoolean();
            if (respuesta==true) {
                salida.writeInt(id_chamber);
                salida.flush();

                Object o = entrada.readObject();
                if (o instanceof Chamber) {
                    System.out.println(o.toString());
                }
            } else {
                System.out.println("Error, no se pudo acceder correctamente");
            }
            salida.close();
            entrada.close();
            cliente.close();


        } catch (UnknownHostException excepcion) {
            System.err.println("No encuentro el servidor en la dirección" + ipServidor);
        } catch (IOException excepcion) {
            System.err.println("Error de entrada/salida");
            excepcion.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }


    }
}
