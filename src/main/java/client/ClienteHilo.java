package client;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.DataInputStream;
import java.io.IOException;


public class ClienteHilo extends Thread{

    private DataInputStream in;

    public ClienteHilo(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {

        JSONParser parser = new JSONParser();
        JSONObject jsonOpciones;
        JSONObject jsonSolucion;
        try {
            jsonOpciones = (JSONObject) parser.parse(in.readUTF());
            System.out.println(jsonOpciones);

            jsonSolucion = (JSONObject) parser.parse(in.readUTF());
            System.out.println(jsonSolucion);
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

    }

    private JSONObject parsearStringToJSON() throws ParseException, InterruptedException, IOException {
        String jsonString = in.readUTF();
        System.out.println(jsonString);
        JSONParser parser = new JSONParser();
        return (JSONObject) parser.parse(jsonString);
    }
}
