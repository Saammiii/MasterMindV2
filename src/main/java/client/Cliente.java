package client;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {
    public static void main(String[] args) {

        try {

            Socket sc = new Socket("127.0.0.1", 4567);

            DataInputStream in = new DataInputStream(sc.getInputStream());

            ClienteHilo hilo = new ClienteHilo(in);
            hilo.start();
            hilo.join();

        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
