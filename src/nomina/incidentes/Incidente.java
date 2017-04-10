/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina.incidentes;

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
public class Incidente {

    private String fecha;
    private String responsable;
    private String nota;
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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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
        final Incidente other = (Incidente) obj;
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.responsable, other.responsable)) {
            return false;
        }
        return true;
    }

    public static ArrayList<Incidente> cargarIncidentes() {
        ArrayList<Incidente> incidentes = new ArrayList<>();
        try {

            FileReader fr = new FileReader("Archivos/incidentes.json");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            ObjectMapper mapper = new ObjectMapper();
            while ((linea = br.readLine()) != null) {
                incidentes.add(mapper.readValue(linea, Incidente.class));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de lectura de incidentes", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            return incidentes;
        }
    }

    public static void actualizarDB(ArrayList<Incidente> incidentes) {
        ObjectMapper mapper = new ObjectMapper();
        String prestamoJSON;
        try {
            FileWriter fw = new FileWriter("Archivos/incidentes.json");
            PrintWriter pw = new PrintWriter(fw);

            for (Incidente incidente : incidentes) {
                prestamoJSON = mapper.writeValueAsString(incidente);
                pw.println(prestamoJSON);
            }
            pw.close();
            fw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de escritura en el archivo de incidentes", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
