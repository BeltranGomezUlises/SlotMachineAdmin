package Maquinas;

import Maquinas.Perifericos.Periferico;
import Maquinas.Tarjetas.Tarjeta;
import Sucursal.Sucursal;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Maquina {
    
    String nombre;
    Tarjeta tarjeta;
    Sucursal sucursal;
    int fondo, prestRecu, prestNoRecu, moneda;
    Periferico periferico;
    
    public Maquina(String nombre, Tarjeta tarjeta,Periferico periferico, Sucursal suc,int moneda, int fondo, int prestRecu) {
        this.nombre = nombre;
        this.tarjeta = tarjeta;
        this.sucursal=suc;
        this.fondo = fondo;
        this.prestRecu = prestRecu; 
        this.periferico = periferico;
        this.moneda = moneda;
    }

    public int getMoneda() {
        return moneda;
    }

    public void setMoneda(int moneda) {
        this.moneda = moneda;
    }
    
    public int getPrestRecu() {
        return prestRecu;
    }

    public void setPrestRecu(int prestRecu) {
        this.prestRecu = prestRecu;
    }

    public int getPrestNoRecu() {
        return prestNoRecu;
    }

    public void setPrestNoRecu(int prestNoRecu) {
        this.prestNoRecu = prestNoRecu;
    }
    
    public int getFondo() {
        return fondo;
    }

    public void setFondo(int fondo) {
        this.fondo = fondo;
    }
    
    public Sucursal getSucursal() {
        return sucursal;
    }
    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }        
    
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tarjeta getTarjeta() {
        return tarjeta;
    }
    public void setTarjeta(Tarjeta tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Periferico getPeriferico() {
        return periferico;
    }

    public void setPeriferico(Periferico periferico) {
        this.periferico = periferico;
    }
    
    public String toString(){
        return nombre+" "+tarjeta.toString()+" "+periferico.toString()+" "+sucursal.toString()+" "+moneda+" "+fondo+" "+prestRecu;
    }
    
    public static Maquina copy(Maquina m){
        Maquina nueva = new Maquina (m.getNombre(), m.getTarjeta(), m.getPeriferico(), m.getSucursal(), m.getMoneda(), m.getFondo(), m.getPrestRecu());
        return nueva;
    }
    
    public int compareTo(Maquina m){
        int res = this.getNombre().compareTo(m.getNombre());                 
        return res;
    }
    public boolean equals(Maquina m){
        boolean bandera=false;
        if (this.getNombre().equals(m.getNombre())) {
            bandera=true;
        }
        return bandera;
    }
    
    public static Vector<Maquina> cargarMaquinas(){
        Vector<Maquina> maquinas = new Vector<Maquina>();
        try {
            FileReader fr = new FileReader("Archivos/Maquinas.bin");
            BufferedReader br= new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            //lectura y llenado del vector de maquinas con su respectiva tarjeta (tarjeta un objeto encapsulado con su denominacion)                        
            while((linea=br.readLine())!=null){
                token=new StringTokenizer(linea);
                String nombre = token.nextToken();
                String nomTarjeta = token.nextToken();
                //TARJETA
                int denomTarjeta= Integer.parseInt(token.nextToken());
                Tarjeta t = new Tarjeta(nomTarjeta, denomTarjeta);
                //PERIFERICO
                String nomPeriferico = token.nextToken();
                Periferico p = new Periferico(nomPeriferico);
                //SUCURSAL
                String nombreSucursal= token.nextToken();
                int turnos = Integer.parseInt(token.nextToken());
                int comision = Integer.parseInt(token.nextToken());                
                Sucursal suc = new Sucursal (nombreSucursal, turnos, comision);
                int moneda = Integer.parseInt(token.nextToken());
                int fondo = Integer.parseInt(token.nextToken());
                int pRecu = Integer.parseInt(token.nextToken());                
                Maquina m = new Maquina(nombre, t, p, suc, moneda, fondo, pRecu);
                maquinas.add(m);
            }
            br.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las maquinas del archivo","Error",JOptionPane.ERROR_MESSAGE);
        }
        return maquinas;
    }
    
    public static void ActualizarDB(Vector<Maquina> maquinas){
        try{  
            FileWriter fr = new FileWriter("Archivos/Maquinas.bin");
            PrintWriter pw= new PrintWriter(fr);
            for (int i = 0; i < maquinas.size(); i++) {
                pw.println(maquinas.elementAt(i).toString());
            }                
            pw.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de Maquinas");
        }
    }
}
