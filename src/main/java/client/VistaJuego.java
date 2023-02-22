package client;

import bbdd.BBDDConn;
import entities.Intento;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class VistaJuego extends JFrame {
    private JPanel content;
    private JPanel panelDisponibles;
    private JPanel panelElegidos;
    private JLabel labelIntentos;
    private JButton buttonEstadisticas;
    private ArrayList<String> arrayListOpciones = new ArrayList<>();
    private ArrayList<String> arrayListSolucion = new ArrayList<>();
    private ArrayList<String> arrayListIntento = new ArrayList<>();
    private JSONObject jsonOpciones;
    private JSONObject jsonSolucion;
    private JSONObject jsonIntento = new JSONObject();
    private ArrayList<Button> listaButtons = new ArrayList<>();
    private int intentosRestantes = 6;
    private BBDDConn conn;

    private DataInputStream in;
    private DataOutputStream out;

    private int x = 0;
    private int y = 0;

    public VistaJuego(JSONObject coloresOpciones, JSONObject coloresSolucion) throws SQLException {
        conn = new BBDDConn();
        inicializarVentana();
        inicializarComponentes();
        this.jsonOpciones = coloresOpciones;
        this.jsonSolucion = coloresSolucion;
        jsonsToArrayLists(jsonOpciones, arrayListOpciones);
        crearBotones(arrayListOpciones);
        funcionalidadBotones();

    }

    private void jsonsToArrayLists(JSONObject jsonOpciones, ArrayList<String> opciones) {
        opciones.add(jsonOpciones.getString("opcion0"));
        opciones.add(jsonOpciones.getString("opcion1"));
        opciones.add(jsonOpciones.getString("opcion2"));
        opciones.add(jsonOpciones.getString("opcion3"));
        opciones.add(jsonOpciones.getString("opcion4"));
        opciones.add(jsonOpciones.getString("opcion5"));
    }

    private void inicializarComponentes() {
        panelDisponibles.setLayout(new BoxLayout(panelDisponibles,BoxLayout.X_AXIS));

    }

    private void crearBotones(ArrayList<String> arrayList) {
        for (String color : arrayList) {
            switch (color) {
                case "green" -> addColoresButton(Color.green);
                case "red" -> addColoresButton(Color.red);
                case "black" -> addColoresButton(Color.black);
                case "orange" -> addColoresButton(Color.orange);
                case "blue" -> addColoresButton(Color.blue);
                case "pink" -> addColoresButton(Color.pink);
            }
        }
    }

    private void addColoresButton(Color color) {
        Button button = new Button();
        button.setBackground(color);
        panelDisponibles.add(button);
        panelDisponibles.validate();

        listaButtons.add(button);
    }

    private void funcionalidadBotones(){
        for (int i = 0; i < panelDisponibles.getComponentCount(); i++) {
            Button b = (Button) panelDisponibles.getComponent(i);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        accionesBtn(b);
                    } catch (IOException | SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
        buttonEstadisticas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    VistaIntentoIndividual vistaIntento = new VistaIntentoIndividual();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void accionesBtn(Button b) throws IOException, SQLException {
        GridBagConstraints constraints = new GridBagConstraints();
        Button button = new Button();
        button.setBackground(b.getBackground());

        constraints.gridx = x;
        constraints.gridy = y;

        panelElegidos.add(button, constraints);
        listaButtons.add(button);

        x += 1;

        if (panelElegidos.getComponentCount() % 5 == 0 && panelElegidos.getComponentCount() != 0) {
            for (int i = 0; i < 5; i++) {
                setJSONIntento(i, y, jsonIntento);
            }

            x = 0;
            y += 1;
            checkIsCorrecto();
        }
       // bw.close();
        panelElegidos.repaint();
        panelElegidos.revalidate();

    }

    private void checkIsCorrecto() throws SQLException {
        String messageErrores="";

        this.arrayListSolucion = new ArrayList<>();
        this.arrayListIntento = new ArrayList<>();

        if (intentosRestantes != 0) {

            jsonToArray(jsonSolucion, this.arrayListSolucion, "color");
            jsonToArray(jsonIntento, this.arrayListIntento, "intento");

            if (this.arrayListSolucion.equals(this.arrayListIntento)) {
                int opcion = JOptionPane.showConfirmDialog(this,"¡Enhorabuena monstruo!","Win win 4 you", JOptionPane.OK_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);

                if (opcion == 0) {
                    String userName = JOptionPane.showInputDialog("Introduce tu gamertag: ");
                    userName = userName.substring(0, 3).toUpperCase();
                    conn.addJugador(userName, intentosRestantes);
                }

            } else {

                for (int i = 0; i < this.arrayListIntento.size(); i++) {
                    if (!this.arrayListIntento.get(i).equals(this.arrayListSolucion.get(i))){
                        messageErrores += "Color erróneo en : " + (i + 1) + ", ";
                    }
                }

                intentosRestantes--;
                labelIntentos.setText(intentosRestantes + "");
                JOptionPane.showMessageDialog(this, messageErrores, "Intento erróneo",JOptionPane.WARNING_MESSAGE);
                }
        } else {

            JOptionPane.showMessageDialog(this, "No te quedan intentos restantes", "JAJA",JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }

    }

    private static void jsonToArray(JSONObject json, ArrayList<String> array, String clave) {

        for (int i = 0; i < 5; i++) {
            array.add(json.getString(clave+i));
        }
    }

    private void setJSONIntento(int i, int y, JSONObject jsonIntento) {

        ArrayList<Intento> arrayIntento = new ArrayList<>();

        if (panelElegidos.getComponent(5*y+i).getBackground()==Color.green){
            arrayIntento.add(new Intento("intento"+ i, "green"));
        } else if (panelElegidos.getComponent(5*y+i).getBackground() == Color.black) {
            arrayIntento.add(new Intento("intento"+ i, "black"));
        }else if (panelElegidos.getComponent(5*y+i).getBackground() == Color.red){
            arrayIntento.add(new Intento("intento"+ i, "red"));
        } else if (panelElegidos.getComponent(5*y+i).getBackground() == Color.orange) {
            arrayIntento.add(new Intento("intento"+ i, "orange"));
        } else if (panelElegidos.getComponent(5*y+i).getBackground() == Color.pink) {
            arrayIntento.add(new Intento("intento"+ i, "pink"));
        } else if (panelElegidos.getComponent(5*y+i).getBackground() == Color.blue) {
            arrayIntento.add(new Intento("intento"+ i, "blue"));
        }

        for (Intento in : arrayIntento) {
            jsonIntento.put(in.getKey(), in.getValue());
        }

        System.out.println(jsonIntento.toString());
    }

    private void inicializarVentana() {
        setContentPane(content);
        setTitle("Mastermind");
        setSize(700,500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }
}

