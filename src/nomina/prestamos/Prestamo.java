/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.prestamos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;
import javax.swing.JOptionPane;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Ulises Beltrán Gómez --- beltrangomezulises@gmail.com
 */
public class Prestamo {

    private String fecha;
    private String encargado;
    private String autorizador;
    private int cantidad;

    private boolean pagado;

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "fecha=" + fecha + ", encargado=" + encargado + ", autorizador=" + autorizador + ", cantidad=" + cantidad + '}';
    }

    public static ArrayList<Prestamo> cargarPrestamos() {
        ArrayList<Prestamo> prestamos = new ArrayList<>();
        try {

            FileReader fr = new FileReader("Archivos/prestamos.json");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            ObjectMapper mapper = new ObjectMapper();
            while ((linea = br.readLine()) != null) {
                prestamos.add(mapper.readValue(linea, Prestamo.class));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de lectura de prestamos", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            return prestamos;
        }
    }

    public static void actualizarDB(ArrayList<Prestamo> prestamos) {
        ObjectMapper mapper = new ObjectMapper();
        String prestamoJSON;
        try {
            FileWriter fw = new FileWriter("Archivos/prestamos.json");
            PrintWriter pw = new PrintWriter(fw);

            for (Prestamo prestamo : prestamos) {
                prestamoJSON = mapper.writeValueAsString(prestamo);
                pw.println(prestamoJSON);
            }
            pw.close();
            fw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de escritura en el archivo de prestamos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Prestamo other = (Prestamo) obj;
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.encargado, other.encargado)) {
            return false;
        }
        if (!Objects.equals(this.autorizador, other.autorizador)) {
            return false;
        }
        return true;
    }

}
