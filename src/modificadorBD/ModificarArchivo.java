package modificadorBD;

import ControlUsuario.Usuario;
import Encargado.Encargado;
import Formato.ConsultaFormato;
import Formato.Formato;
import Utilerias.Utileria;
import static Utilerias.Utileria.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

public class ModificarArchivo {

    /*
    public static void main(String[] args) {  
        //Cambio de modelo de encargado a modelo nuevo con json 
        List<Encargado> encargados = new ArrayList<>();
        try{
            FileReader fr = new FileReader( "Archivos/Encargados.bin" );
            BufferedReader br = new BufferedReader( fr );
            String linea;
            StringTokenizer token;            
            while( (linea = br.readLine() )!=null){       
                Encargado a = new Encargado();
                a.setNombre(quitaGuion(linea));
                encargados.add(a);               
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
     */
 /*
   Cambiar los formatos a pagado de una fecha para atras
     */
//    public static void main(String[] args) throws ParseException {
//        Vector<Formato> formatos = Formato.cargarFormatos();
//        Date date = Utileria.SDF.parse("16/01/2017");
//        for (Formato formato : formatos) {
//            if (Utileria.SDF.parse(formato.getFecha()).before(date)) {
//                formato.setPagado(true);
//            }
//        }        
//        Formato.actualizarFormatos(formatos);
//    }
//    public static void main(String[] args) throws IOException {
//        
//        ArrayList<Usuario> usuarios = Usuario.cargarUsuarios();                                               
//        for (Usuario usuario : usuarios) {
//            System.out.println(Utileria.binaryToString(usuario.getContraseña()));
//        }
//        
//        ObjectMapper mapper = new ObjectMapper();                
//        for (Usuario u : usuarios) {
//            System.out.println(mapper.writeValueAsString(u));
//        }
//        //Usuario.actualizarDB(usuarios);
//        
//    }

}
