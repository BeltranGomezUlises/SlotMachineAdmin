
package Etiquetas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Etiqueta {
    String nombre;

    public Etiqueta(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
    public static ArrayList<Etiqueta> cargarEtiquetas(){
        ArrayList<Etiqueta> etiquetas = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Archivos/Etiquetas.bin");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while ((linea=br.readLine()) != null) {
                token = new StringTokenizer(linea);
                //etiqueta:
                String nombre = token.nextToken();
                                
                Etiqueta e = new Etiqueta( nombre ) ;
                etiquetas.add(e);
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de lectura de archivo Etiqueta", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return etiquetas;
    }
    
    public static void actualizarVD(ArrayList<Etiqueta>  etiquetas){        
        try {
            FileWriter fw = new FileWriter("Archivos/Etiquetas.bin");
            PrintWriter pw = new PrintWriter(fw);
            for (Etiqueta e : etiquetas) {
                pw.println(e);
            }
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar guardar el vale al archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }           
    }
}
