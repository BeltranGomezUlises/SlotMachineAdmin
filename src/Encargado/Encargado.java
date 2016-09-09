
package Encargado;

import static Utilerias.Utileria.quitaGuion;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;


public class Encargado {
        
    String nombre;

    public Encargado(String nombre) {
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
    
    public int compareTo(Encargado e){
        int res = 0;
        if (nombre.compareTo( e.getNombre() ) > 0) {
            res = 1;
        }else{
            if (nombre.compareTo( e.getNombre() ) < 0) {
                res = -1;
            }
        }
        return res;
    }
    
    public static Encargado copy(Encargado e){
        return new Encargado( e.getNombre() );
    }
    
    public static Vector<Encargado> cargarEncargados(){
        Vector<Encargado> encargados = new Vector<>();
        try{
            FileReader fr = new FileReader( "Archivos/Encargados.bin" );
            BufferedReader br = new BufferedReader( fr );
            String linea;
            StringTokenizer token;
            while( (linea = br.readLine() )!=null){
                token = new StringTokenizer(linea);
                encargados.add(new Encargado(  linea  ) );               
            }                   
            br.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Base de datos de empleados no existe o es inutilizable", "Error", JOptionPane.ERROR_MESSAGE );
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error de I/O en archivo de encargados", "Error", JOptionPane.ERROR_MESSAGE );
        }
        return encargados;
    }
    
    
    public static void actualizarBD(Vector<Encargado> encargados){
        try {                                                                                         
            FileWriter out = new FileWriter("Archivos/Encargados.bin");
            PrintWriter pw = new PrintWriter(out);                        
            for(int k=0;k<encargados.size();k++){
                pw.println(encargados.elementAt(k).toString());
            }
            pw.close(); 
        } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Ocurrió un error con el archivo", "Error de Archvo",JOptionPane.ERROR_MESSAGE);
        }catch(NullPointerException e){
           e.printStackTrace();
        }        
    }
}
