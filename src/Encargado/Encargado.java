package Encargado;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import org.codehaus.jackson.map.ObjectMapper;

public class Encargado {

    private String nombre;
    private String nombreCompleto;
    private String telefono;
    private String familiar;
    private String telFamiliar;
    private String foto;
    private String notaPendiente;
    private Date fechaNac;
    private float pendiente;
    private Date fechaPendiente;

    public Encargado() {        
    }

    public Encargado(String nombre, String nombreCompleto, String telefono, String familiar, String telFamiliar, Date fechaNac) {
        this.nombre = nombre;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.familiar = familiar;
        this.telFamiliar = telFamiliar;
        this.fechaNac = fechaNac;
        this.pendiente = 0f;
        this.fechaPendiente = new Date();
    }

    public String getNotaPendiente() {
        return notaPendiente;
    }

    public void setNotaPendiente(String notaPendiente) {
        this.notaPendiente = notaPendiente;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFamiliar() {
        return familiar;
    }

    public void setFamiliar(String familiar) {
        this.familiar = familiar;
    }

    public String getTelFamiliar() {
        return telFamiliar;
    }

    public void setTelFamiliar(String telFamiliar) {
        this.telFamiliar = telFamiliar;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getFechaPendiente() {
        return fechaPendiente;
    }

    public void setFechaPendiente(Date fechaPendiente) {
        this.fechaPendiente = fechaPendiente;
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

    @Override
    public String toString() {
        return "Encargado{" + "nombre=" + nombre + ", nombreCompleto=" + nombreCompleto + ", notaPendiente=" + notaPendiente + ", pendiente=" + pendiente + ", fechaPendiente=" + fechaPendiente + '}';
    }
    
    

}
