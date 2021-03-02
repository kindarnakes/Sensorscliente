package Project.GUI;


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
            System.out.println("1. Simular la lectura de dos sensores de la misma cámara de"
                    + "manera simultánea.");
            System.out.println("2. Simular la lectura de un sensor con valor superior a la temperatura "
                    + "máxima y una apertura de puerta simultánea.");

            System.out.println("3. Ingreso de una nueva cámara frigorífica y lectura de los"
                    + "sensores de temperatura de las cámaras restantes. ");
            System.out.println("4. Resumen del estado de las cámaras.");
            System.out.println("5. Salir");

            try {
                System.out.print("Indica una opción a través del dígito que se muestra:");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        salir = true;
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
    

}





