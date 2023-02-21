package client;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Scanner;

public class ClienteHilo extends Thread{

    private DataInputStream in;

    public ClienteHilo(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {

        String s = null;
        try {
            s = in.readUTF();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(s);
            /*JSONObject jsonOpciones = parsearStringToJSON();
            System.out.println(jsonOpciones.toString());*/


    }

    private JSONObject parsearStringToJSON() throws ParseException, InterruptedException, IOException {
        String jsonString = in.readUTF();
        System.out.println(jsonString);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(jsonString);
    }
}
