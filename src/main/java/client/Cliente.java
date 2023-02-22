package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    public static void main(String[] args) {

        try {

            Socket sc = new Socket("127.0.0.1", 4567);

            DataInputStream in = new DataInputStream(sc.getInputStream());
            DataOutputStream out = new DataOutputStream(sc.getOutputStream());

            ClienteHilo hilo = new ClienteHilo(in, out);
            hilo.start();
            hilo.join();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
