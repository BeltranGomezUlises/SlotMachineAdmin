
package Vales.Chequeras;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Chequera {
    
    private String id;
    private String rutero;
    //Datos para obtener el ultimo cambio que tubo de ingreso la chequera
    private String ultimoFolio;
    private String ultimaFecha;
    private String ultimoUsuario;
    
    public Chequera(String id, String rutero) {
        this.id = id;
        this.rutero = rutero;
        ultimoFolio = "Ningúno";
        ultimaFecha = "Ningúno";
        ultimoUsuario = "Ningúno";
    }

    public Chequera(String id, String rutero, String ultimoFolio, String ultimaFecha, String ultimoUsuario) {
        this.id = id;
        this.rutero = rutero;
        this.ultimoFolio = ultimoFolio;
        this.ultimaFecha = ultimaFecha;
        this.ultimoUsuario = ultimoUsuario;
    }        

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRutero() {
        return rutero;
    }

    public void setRutero(String rutero) {
        this.rutero = rutero;
    }

    public String getUltimoFolio() {
        return ultimoFolio;
    }

    public void setUltimoFolio(String ultimoFolio) {
        this.ultimoFolio = ultimoFolio;
    }

    public String getUltimaFecha() {
        return ultimaFecha;
    }

    public void setUltimaFecha(String ultimaFecha) {
        this.ultimaFecha = ultimaFecha;
    }

    public String getUltimoUsuario() {
        return ultimoUsuario;
    }

    public void setUltimoUsuario(String ultimoUsuario) {
        this.ultimoUsuario = ultimoUsuario;
    }
    
    @Override
    public String toString(){
        return id + " " + rutero + " " + ultimoFolio + " " + ultimaFecha + " " + ultimoUsuario;
    }
    
    public boolean equals(Chequera c){
        return this.id.equals(c.getId());
    }
    
    public static ArrayList<Chequera> cargarChequeras(){
        ArrayList<Chequera> chequeras = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Archivos/Chequeras.bin");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while ((linea=br.readLine()) != null) {
                token = new StringTokenizer(linea);
                String id = token.nextToken();
                String rutero = token.nextToken();
                
                String ultimoFolio = token.nextToken();
                String ultimaFecha = token.nextToken();
                String ultimoUsuario = token.nextToken();
                     
                Chequera c = new Chequera(id, rutero, ultimoFolio, ultimaFecha, ultimoUsuario);
                chequeras.add(c);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de lectura de archivo chequeras", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return chequeras;
    }
    
    public static void actualizarDB(ArrayList<Chequera> chequeras){
         try {
            FileWriter fw = new FileWriter("Archivos/Chequeras.bin");
            PrintWriter pw = new PrintWriter(fw);
            for (Chequera c : chequeras) {
                pw.println(c);
            }
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar guardar Chequeras al archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }      
    }
}
