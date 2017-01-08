package Encargado;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.codehaus.jackson.map.ObjectMapper;

public class Encargado {

    String nombre;
    float pendiente;

    public Encargado() {

    }

    public Encargado(String nombre) {
        this.nombre = nombre;
    }

    public float getPendiente() {
        return pendiente;
    }

    public void setPendiente(float pendiente) {
        this.pendiente = pendiente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int compareTo(Encargado e) {
        return nombre.compareTo(e.getNombre());
    }

    public static List<Encargado> cargarEncargados() {
        List<Encargado> encargados = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Archivos/Encargados.bin");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            ObjectMapper mapper = new ObjectMapper();
            while ((linea = br.readLine()) != null) {
                Encargado a = mapper.readValue(linea, Encargado.class);
                encargados.add(a);
            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Base de datos de empleados no existe o es inutilizable", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error de I/O en archivo de encargados", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return encargados;
    }

    public static void actualizarBD(List<Encargado> encargados) {
        try {
            FileWriter out = new FileWriter("Archivos/Encargados.bin");
            PrintWriter pw = new PrintWriter(out);
            ObjectMapper mapper = new ObjectMapper();
            for (int k = 0; k < encargados.size(); k++) {
                pw.println(mapper.writeValueAsString(encargados.get(k)));
            }
            pw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error con el archivo", "Error de Archvo", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
