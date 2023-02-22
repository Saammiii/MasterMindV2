package bbdd;

import java.sql.*;
import java.util.ArrayList;

public class BBDDConn {
    private Connection conn;

    public BBDDConn() throws SQLException {
        conn= DriverManager.getConnection("jdbc:mysql://localhost/mastermind_bbdd","root","");
    }

    public void addJugador(String nombre, int intentos) throws SQLException {
        try(PreparedStatement ps = conn.prepareStatement("INSERT INTO puntuaciones VALUES(?,?)")){
            ps.setString(1, nombre);
            ps.setInt(2, intentos);

            ps.executeUpdate();
        }
    }
    public ArrayList<String> ordenarDescendente(){
        ArrayList<String> listaPLayers= new ArrayList<>();
        try(Statement st= conn.createStatement();
            ResultSet rs=st.executeQuery("SELECT * FROM puntuaciones ORDER BY 2 DESC")){
            while (rs.next()){
                listaPLayers.add("Jugador: " + rs.getString(1) + ", Nº Intentos restantes: " + rs.getString(2));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaPLayers;
    }

    public Connection getConn() {
        return conn;
    }
    public void setConn(Connection conn) {
        this.conn = conn;
    }
}
