package client;

import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.Scanner;

public class ClienteHilo extends Thread{

    private Scanner in;

    public ClienteHilo(Scanner in) {
        this.in = in;
    }

    @Override
    public void run() {

        String s = in.next();
        System.out.println(s);
            /*JSONObject jsonOpciones = parsearStringToJSON();
            System.out.println(jsonOpciones.toString());*/


    }

    private JSONObject parsearStringToJSON() throws ParseException, InterruptedException {
        String jsonString = in.next();
        System.out.println(jsonString);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(jsonString);
    }
}
