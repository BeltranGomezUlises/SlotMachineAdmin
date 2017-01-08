package modificadorBD;

import Encargado.Encargado;
import static Utilerias.Utileria.*;
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


public class ModificarArchivo {
    
    public static void main(String[] args) {  
        //Cambio de modelo de encargado a modelo nuevo con json 
        List<Encargado> encargados = new ArrayList<>();
        try{
            FileReader fr = new FileReader( "Archivos/Encargados.bin" );
            BufferedReader br = new BufferedReader( fr );
            String linea;
            StringTokenizer token;            
            while( (linea = br.readLine() )!=null){
                
                encargados.add(new Encargado (quitaGuion(linea)) );               
            }                   
            br.close();
            
            
            
            
            FileWriter out = new FileWriter("Archivos/Encargados.bin");
            PrintWriter pw = new PrintWriter(out);                        
            ObjectMapper mapper = new ObjectMapper();
            for(int k=0;k<encargados.size();k++){
                pw.println(mapper.writeValueAsString(encargados.get(k)));
            }
            pw.close(); 
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Base de datos de empleados no existe o es inutilizable", "Error", JOptionPane.ERROR_MESSAGE );
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error de I/O en archivo de encargados", "Error", JOptionPane.ERROR_MESSAGE );
        }
    
    
    }       
      
    
}
