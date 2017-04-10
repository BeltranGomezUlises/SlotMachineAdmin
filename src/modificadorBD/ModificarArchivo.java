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

    public static void main(String[] args) throws ParseException {
        
        Vector<Formato> formatos = Formato.cargarFormatos();
        
        Date fecha = Utileria.SDF.parse("27/03/2017");
        
        for (Formato formato : formatos) {
            if (Utileria.SDF.parse(formato.getFecha()).compareTo(fecha) >= 0) {
                System.out.println(formato);
                formato.setPagado(false);
            }
        }
        
        Formato.actualizarFormatos(formatos);
        
    }

}
