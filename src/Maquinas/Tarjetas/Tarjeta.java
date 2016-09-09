package Maquinas.Tarjetas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Tarjeta {
    String nombre;
    int denominacion;

    public Tarjeta(String nombre, int denominacion) {
        this.nombre = nombre;
        this.denominacion = denominacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(int denominacion) {
        this.denominacion = denominacion;
    }
    
    @Override
   public String toString(){
       return nombre+" "+denominacion;
   }
   
   public static Vector<Tarjeta> cargarTarjetas(){
       Vector<Tarjeta> tarjetas = new Vector<Tarjeta>();
       try {
            FileReader fr = new FileReader("Archivos/Tarjetas.bin");
            BufferedReader br= new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            //lectura y llenado del vector de maquinas con su respectiva tarjeta (tarjeta un objeto encapsulado con su denominacion)                        
            while((linea=br.readLine())!=null){
                token=new StringTokenizer(linea);                
                String nomTarjeta = token.nextToken();
                int denomTarjeta= Integer.parseInt(token.nextToken());
                Tarjeta t = new Tarjeta(nomTarjeta, denomTarjeta);                
                tarjetas.add(t);
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las tarjetas del archivo","Error",JOptionPane.ERROR_MESSAGE);
        }
        return tarjetas;
   }
}
