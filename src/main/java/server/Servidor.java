package server;

import java.io.*;
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

                DataOutputStream out = new DataOutputStream(sc.getOutputStream());


                ServidorHilo hilo = new ServidorHilo(out);
                hilo.start();
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
