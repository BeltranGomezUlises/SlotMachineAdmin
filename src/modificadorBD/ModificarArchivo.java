package modificadorBD;

import ControlUsuario.Usuario;
import Encargado.Encargado;
import Formato.ConsultaFormato;
import Formato.Formato;
import Productos.Producto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.xml.soap.SOAPElement;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;

public class ModificarArchivo {

    public static void main(String[] args) throws ParseException {
        //obtener el total de venta de cada producto desde el 3 al 9 de abril
        List<Formato> formatos = new ArrayList<>(Formato.cargarFormatos());
        Date di = Utileria.SDF.parse("10/04/2017");
        Date df = Utileria.SDF.parse("10/04/2017");
        
        List<Formato> formatosFiltrados = formatos.stream().filter((Formato f) -> {
            try {
                return !Utileria.SDF.parse(f.getFecha()).after(df) && !Utileria.SDF.parse(f.getFecha()).before(di);
            } catch (ParseException ex) {return false;}
        }).collect(toList());
        
        List<String> productos = new ArrayList<>();
        for (Formato f : formatosFiltrados) {
            List<Producto> fproductos = new ArrayList<>(f.getProductos());            
            for (Producto fproducto : fproductos) {
                if (!productos.contains(fproducto.getNombre())) {
                    productos.add(fproducto.getNombre());
                }
            }                            
        }      
        
        HashMap<String, Integer> relacion = new HashMap<>();
        for (String producto : productos) {
            relacion.put(producto, 0);
        }        
        
        for (Formato f : formatosFiltrados) {
            for (int i = 0; i < f.getProductos().size(); i++) {                
                relacion.put(f.getProductos().get(i).getNombre(), relacion.get(f.getProductos().get(i).getNombre()) + f.getInvI().get(i) + f.getCompras().get(i) - f.getInvF().get(i));
            }
        }
                
        for (Map.Entry<String, Integer> entry : relacion.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        
        
        
       
        
    }

}
