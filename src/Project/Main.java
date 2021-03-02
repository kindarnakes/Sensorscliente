package Project;

import Project.Model.Chamber;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;


public class Main {

    public static void main(String[] args) throws IOException {

     Socket cliente = null;
     BufferedReader entrada = null;
     ObjectOutputStream salida = null;
     String ipServidor = "localhost";

      try {
       cliente = new Socket(ipServidor, 55000);
       //entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
       salida = new ObjectOutputStream(cliente.getOutputStream());
      } catch (
              UnknownHostException var9) {
       System.err.println("El servidor no está levantado");
      } catch (Exception var10) {
       System.err.println("Error: " + var10);
      }
      Chamber c=new Chamber(1,2,1,2);
      salida.writeObject(c);
      salida.close();



     }




}
