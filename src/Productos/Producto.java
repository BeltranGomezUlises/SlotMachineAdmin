package Productos;

import static Utilerias.Utileria.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Producto {
    float precio, costo,comision;
    String nombre;
    int presentacion;

    public Producto( String nombre,float precio, float costo, float comision,int presentacion) {
        this.precio = precio;
        this.costo = costo;
        this.comision = comision;
        this.nombre = nombre;
        this.presentacion = presentacion;
    }

    public int getPresentacion() {
        return presentacion;
    }
    public void setPresentacion(int presentacion) {
        this.presentacion = presentacion;
    }        
    public float getPrecio() {
        return precio;
    }
    public float getCosto() {
        return costo;
    }
    public float getComision() {
        return comision;
    }
    public String getNombre() {
        return nombre;
    }
    public float utilidad(){
        return precio-costo-comision();
    }
    public float comision(){
        return precio*(comision/100);
    }
    public void setPrecio(float precio) {
        this.precio = precio;
    }
    public void setCosto(float costo) {
        this.costo = costo;
    }
    public void setComision(float comision) {
        this.comision = comision;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int compareTo(Producto p){
        if (nombre.equals(p.getNombre())) {
           return 0;
        }else{
            return -1;
        }
    }
    
    public static Producto copy(Producto p){
        Producto aux;
        aux= new Producto(p.getNombre(),p.getPrecio(),p.getCosto(),p.getComision(),p.getPresentacion());
        return aux;
    }
    public static Vector<Producto> cargarProductos(){
        Vector<Producto> productos = new Vector<Producto>();
        try {
            FileReader fr = new FileReader("Archivos/Productos.data");
            BufferedReader br= new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while((linea=br.readLine())!=null){
                token= new StringTokenizer(linea);
                String nombre = token.nextToken();
                float precio = Float.parseFloat(token.nextToken());
                float costo =Float.parseFloat(token.nextToken());
                float comision = Float.parseFloat(token.nextToken());
                int pres = Integer.parseInt(token.nextToken());
                Producto p = new Producto(nombre,precio,costo,comision,pres);
                productos.add(p);
            }            
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontro Archivo Productos","Error", JOptionPane.ERROR_MESSAGE);
        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error de lectura en Archivo Productos","Error", JOptionPane.ERROR_MESSAGE);
                        
        }        
        return productos;
    }
    @Override
    public String toString(){
        return quitaEspacios(nombre)+" "+precio+" "+costo+" "+comision+" "+presentacion;
    }
        
}
