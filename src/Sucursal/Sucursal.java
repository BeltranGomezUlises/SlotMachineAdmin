
package Sucursal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Sucursal {
    
    private String nombre;
    private int turnos, comision;
    
    List<String> nomProductos;
    
    public Sucursal(String nombre, int turnos, int comision, List<String> nomProductos) {
        this.nombre = nombre;
        this.turnos = turnos;
        this.comision = comision;
        this.nomProductos = nomProductos;
    }

    public Sucursal(String nombre, int turnos, int comision) {
        this.nombre = nombre;
        this.turnos = turnos;
        this.comision = comision;
        nomProductos = new ArrayList<>();
    }
    
    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }

    
    public int getTurnos() {
        return turnos;
    }
    public void setTurnos(int turnos) {
        this.turnos = turnos;
    }
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getNomProductos() {
        return nomProductos;
    }

    public void setNomProductos(List<String> nomProductos) {
        this.nomProductos = nomProductos;
    }
    
    public String toString(){
        String cadena = nombre+" "+turnos+" "+comision;
        for (String nomProducto : nomProductos) {
            cadena += " " + nomProducto;
        }
        return cadena;
    }
    
    public static void actualizarBD(Vector<Sucursal> sucursales){
        try{
            FileWriter fw = new FileWriter("Archivos/Sucursales.bin");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < sucursales.size(); i++) {
                //sobreEscribir
                pw.println(sucursales.elementAt(i).toString()); 
            }                                                           
            pw.close(); 
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de escritura en sucursales","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public int compareTo(Sucursal e){                
        return nombre.compareTo( e.getNombre() );
    }
    
    public static Sucursal copy(Sucursal s){
        return new Sucursal (s.getNombre(), s.getTurnos(), s.getComision(), s.getNomProductos());
    }
    
    public static Vector<Sucursal> cargarSucursales(){
        Vector<Sucursal> sucursales = new Vector<Sucursal>();
        try{            
            FileReader fr = new FileReader("Archivos/Sucursales.bin");
            BufferedReader br= new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while((linea=br.readLine())!=null){
                token= new StringTokenizer(linea);
                String suc=token.nextToken();                
                int turnos = Integer.parseInt(token.nextToken());
                int comision = Integer.parseInt(token.nextToken());
                
                sucursales.add(new Sucursal(suc, turnos, comision));
            }
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error de lectura en sucursales","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return sucursales;
    }
  
   public static Vector<Sucursal> cargarSucursalesConProductos(){
       Vector<Sucursal> sucursales = new Vector<Sucursal>();
        try{            
            FileReader fr = new FileReader("Archivos/Sucursales.bin");
            BufferedReader br= new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while((linea=br.readLine())!=null){
                token= new StringTokenizer(linea);
                String suc=token.nextToken();                
                int turnos = Integer.parseInt(token.nextToken());
                int comision = Integer.parseInt(token.nextToken());
                ArrayList<String> nomProductos = new ArrayList<>();
                
                try {
                    String producto = token.nextToken();
                    while (true) {
                        nomProductos.add(producto);
                        producto = token.nextToken();
                    }
                } catch (NoSuchElementException e) {
                    //se terminaron los productos
                }
                sucursales.add(new Sucursal(suc, turnos, comision, nomProductos));
            }
            
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error de lectura en sucursales","Error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return sucursales;
   }
}
