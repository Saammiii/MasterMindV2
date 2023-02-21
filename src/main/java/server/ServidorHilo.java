package server;


import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ServidorHilo extends Thread{

    private DataOutputStream out;
    private final ArrayList<String> colores = new ArrayList<>();


    public ServidorHilo(DataOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {

        JSONObject coloresOpciones;
        JSONObject coloresSolucion;
        try {
            rellenarArrayColores();
            coloresOpciones = crearJSONObject(colores);
            out.writeUTF(coloresOpciones.toString());
            System.out.println(coloresOpciones);

            coloresSolucion = setSolucion();
            out.writeUTF(coloresSolucion.toString());
            System.out.println(coloresSolucion);



        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private JSONObject setSolucion() {
        int random;
        JSONObject s = new JSONObject();
        for (int i = 0; i < 5; i++) {
            random= (int) (Math.random()*6);
            s.put("color"+i, colores.get(random));
        }
        return s;
    }

    private JSONObject crearJSONObject(ArrayList<String> colores) {
        JSONObject jsonObject= new JSONObject();
        for (int i = 0; i < colores.size(); i++) {
            jsonObject.put("opcion"+i, colores.get(i));
        }
        return jsonObject;
    }

    private void rellenarArrayColores() {
        this.colores.add("green");
        this.colores.add("red");
        this.colores.add("black");
        this.colores.add("orange");
        this.colores.add("blue");
        this.colores.add("pink");
    }
}
