package modificadorBD;

import ControlUsuario.Usuario;
import Corte.Corte;
import static Utilerias.Utileria.*;
import Formato.Formato;
import Inventario.Movimiento;
import Maquinas.Maquina;
import Maquinas.Perifericos.Periferico;
import Maquinas.Tarjetas.Tarjeta;
import Productos.Producto;
import Sucursal.Sucursal;
import Vales.Chequeras.Chequera;
import Vales.Vale;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;


public class ModificarArchivo {
    
    public static void main(String[] args) {  
        /*
        GregorianCalendar cal = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy HH:mm:ss");
        
        System.out.println(cal.getTime().toString());
        System.out.println(sdf.format(cal.getTime()));
        */
	//System.out.println("prueba"); 
       //------------>ARCHIVO CORTES<---------------//
       /*
        Vector<Corte> cortes = Corte.cargarCortes();
        for (Corte corte : cortes) {
            //corte.getMaquina().setPeriferico(new Periferico("Normal"));
           // System.out.println(corte.toString());
        }        
        Corte.ActualizarBD(cortes);                
        */
                        
        
        //------------->ARCHIVO MAQUINAS<-------------------//
         /*
        
        */
        /*
        Vector<Maquina> maquinas = Maquina.cargarMaquinas();
        Maquina.ActualizarDB(maquinas);
         */

        //***ENCRIPTACION DE USUARIOS***
        
        //encriptacion de archivo de control de usuarios
        //ArrayList<Usuario> usuarios = Usuario.cargarUsuarios();
        /*
        for (Usuario u : usuarios) {
            String contra = u.getContraseña();
            //u.setContraseña(stringToBinary(contra));
        }
        for (Usuario usuario1 : usuarios) {
            System.out.println(usuario1);
        }*/
        
        //Usuario.actualizarDB(usuarios);
        
        
        
        
        //SUCURSALES
        
        //Vector<Sucursal> sucursales = Sucursal.cargarSucursales();
        
        //Sucursal.actualizarBD(sucursales);
        
        //CHEQUERAS
       /* ArrayList<Chequera> chequeras = Chequera.cargarChequeras();
        
        Chequera.actualizarDB(chequeras);
        */
        
        //VALES
        /*
        ArrayList<Vale> vales = Vale.cargarVales();
        Vale.actualizarVD(vales);
        */
        
        
        Vector<Producto> productos = Producto.cargarProductos();
        List<String> nomProductos = new ArrayList<>();
        Vector<Sucursal> sucursales = Sucursal.cargarSucursalesConProductos();
        for (Producto producto : productos) {
            nomProductos.add(producto.getNombre());
        }
        for (Sucursal sucursal : sucursales) {
            sucursal.setNomProductos(nomProductos);
        }
        Sucursal.actualizarBD(sucursales);
    }        
}
