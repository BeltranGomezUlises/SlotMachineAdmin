
package Maquinas.Perifericos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Periferico {
    
    private String nombre;

    public Periferico(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
       
    public static void actualizarDB(ArrayList<Periferico> perifericos){
        try {
            FileWriter fr = new FileWriter("Archivos/Perifericos.bin");           
            PrintWriter pw = new PrintWriter(fr);
            for (Periferico periferico : perifericos) {
                pw.println(periferico.toString());                
            }                       
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de cortes","Error", JOptionPane.ERROR_MESSAGE);        
        }
    }
    
    public static ArrayList<Periferico> cargarPerifericos(){
        ArrayList<Periferico> perifericos = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Archivos/Perifericos.bin");
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer token;
            String line;
            while((line = br.readLine()) != null){
                token = new StringTokenizer(line);
                String nombre = token.nextToken();
                Periferico p = new Periferico(nombre);
                perifericos.add(p);
            }
            br.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Archivo de periféricos no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error de lectura en el archivo de periféricos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
            
        return perifericos;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
    
}
