package client;


import com.google.gson.Gson;
import com.mysql.cj.xdevapi.JsonParser;
import org.json.JSONObject;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.sql.SQLException;


public class ClienteHilo extends Thread{

    private DataInputStream in;
    private DataOutputStream out;

    public ClienteHilo(DataInputStream in, DataOutputStream out) {
        this.in = in;
        this.out = out;
    }

    @Override
    public void run() {

        String s1, s2;

        JSONObject jsonOpciones;
        JSONObject jsonSolucion;
        try {
            s1 =  in.readUTF();
            jsonOpciones = new JSONObject(s1);

            s2 =  in.readUTF();
            jsonSolucion = new JSONObject(s2);

            VistaJuego mastermind = new VistaJuego(jsonOpciones, jsonSolucion);

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
