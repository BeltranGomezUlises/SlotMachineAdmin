
package Sucursal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Sucursal {
    private String nombre;
    private int turnos, comision;
    
    public Sucursal(String nombre, int turnos, int comision) {
        this.nombre = nombre;
        this.turnos = turnos;
        this.comision = comision;
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
    
    public String toString(){
        return nombre+" "+turnos+" "+comision;
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
    
    public static Sucursal copy(Sucursal s){
        return new Sucursal (s.getNombre(), s.getTurnos(), s.getComision());
    }
    
    public static Vector<Sucursal> cargarSucursales(){
        Vector<Sucursal> sucursales= new Vector<Sucursal>();
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
}
