package server;


import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;

public class ServidorHilo extends Thread{

    private PrintWriter out;
    private final ArrayList<String> colores = new ArrayList<>();


    public ServidorHilo(PrintWriter out) {
        this.out = out;
    }

    @Override
    public void run() {

        String s2 = "buenas tardes";
        System.out.println(s2);
        out.write(s2);
        /*System.out.println(colores);
        JSONObject coloresOpciones = crearJSONObject(this.colores);
        out.write(coloresOpciones.toString());*/

    }

    private ArrayList<String> setSolucion() {
        int random;
        ArrayList<String> solucionColores = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            random= (int) (Math.random()*6);
            solucionColores.add(colores.get(random));
        }
        return solucionColores;
    }

    private JSONObject crearJSONObject(ArrayList<String> colores) {
        JSONObject jsonObject= new JSONObject();
        for (int i = 0; i < colores.size(); i++) {
            jsonObject.put("opcion"+i, colores.get(i));
        }
        return jsonObject;
    }
}
