package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(4567);
            Socket sc;

            while(true){

                sc = server.accept();
                System.out.println("*Nuevo cliente aceptado*");

                System.out.println("\t-Número de hilos ACTIVOS: " + Thread.activeCount());

                ObjectInputStream in = new ObjectInputStream(sc.getInputStream());
                PrintWriter out = new PrintWriter(sc.getOutputStream());


                ServidorHilo hilo = new ServidorHilo(out);
                hilo.start();

                out.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
