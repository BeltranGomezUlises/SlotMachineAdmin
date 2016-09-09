
package Inventario;

import Productos.Producto;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;


public class Inventario {
    Vector<String> productos= new Vector<String>();
    Vector<Integer> cantidades= new Vector<Integer>();
    
    Inventario(){
     //llenar mi inventario con los productos que no existan en el archivo inventario
        //cargar los productos y sus cantidades del archivo inventario
        try {
            FileReader fr = new FileReader("Archivos/Inventario.data");
            BufferedReader br = new BufferedReader(fr);
            String linea;            
            StringTokenizer token;            
            while((linea=br.readLine())!=null){
                token=new StringTokenizer(linea);
                productos.add(token.nextToken());
                cantidades.add(Integer.parseInt(token.nextToken()));
            }
            //ya lleno con los valores del archivo inventario añadir los productos nuevos en caso de existir
            FileReader frp = new FileReader("Archivos/Productos.data");
            BufferedReader brp = new BufferedReader(frp);
            String prod;
            while((linea=brp.readLine())!=null){
                token= new StringTokenizer(linea);
                prod= token.nextToken();
                if (!productos.contains(prod)) {
                    productos.add(prod);
                    cantidades.add(0);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error de lectura en archivo de Inventarios", "Error", JOptionPane.ERROR_MESSAGE);
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error de lectura en archivo de Inventarios", "Error", JOptionPane.ERROR_MESSAGE);
        }      
        
    }    

    public Vector<String> getProductos() {
        return productos;
    }

    public void setProductos(Vector<String> productos) {
        this.productos = productos;
    }

    public Vector<Integer> getCantidades() {
        return cantidades;
    }

    public void setCantidades(Vector<Integer> cantidades) {
        this.cantidades = cantidades;
    }
        
    public void reducirExistencia(Movimiento m, Vector<Producto> productosArchivo){                                               
        for (int n = 0; n < m.getCantidades().size() ; n++) {
            int presentacion = 1;
            for (int k = 0; k < productosArchivo.size(); k++) {
                if (productosArchivo.elementAt(k).getNombre().equals(m.getProductos().elementAt(n))) {
                    presentacion = productosArchivo.elementAt(k).getPresentacion();
                }
            }            
            for (int i = 0; i < productos.size(); i++) {
                if (m.getProductos().elementAt(n).equals(productos.elementAt(i))) {                
                    int nuevaCant=cantidades.elementAt(i) - m.getCantidades().elementAt(n)/presentacion;                
                    cantidades.setElementAt(nuevaCant, i);                
                    break;
                }
            }
            //System.out.println("redujo a: "+invRuteros.elementAt(j).getRutero() +" el producto: "+movsImportacion.elementAt(i).getProductos().elementAt(n)+" cantidad de: "+movsImportacion.elementAt(i).getCantidades().elementAt(n)/presentacion);
        }              
    }
   
    public void reducirExistencia(Movimiento m){                                               
        for (int n = 0; n < m.getCantidades().size() ; n++) {                      
            for (int i = 0; i < productos.size(); i++) {
                if (m.getProductos().elementAt(n).equals(productos.elementAt(i))) {                
                    int nuevaCant=cantidades.elementAt(i) - m.getCantidades().elementAt(n);                
                    cantidades.setElementAt(nuevaCant, i);                
                    break;
                }
            }
            //System.out.println("redujo a: "+invRuteros.elementAt(j).getRutero() +" el producto: "+movsImportacion.elementAt(i).getProductos().elementAt(n)+" cantidad de: "+movsImportacion.elementAt(i).getCantidades().elementAt(n)/presentacion);
        }              
    }
    
    public void aumentarExistencia(Movimiento m , Vector<Producto> productosArchivo){
        for (int n = 0; n < m.getCantidades().size() ; n++) {
            int presentacion = 1;
            for (int k = 0; k < productosArchivo.size(); k++) {
                if (productosArchivo.elementAt(k).getNombre().equals(m.getProductos().elementAt(n))) {
                    presentacion = productosArchivo.elementAt(k).getPresentacion();
                }
            }            
            for (int i = 0; i < productos.size(); i++) {
                if (m.getProductos().elementAt(n).equals(productos.elementAt(i))) {                
                    int nuevaCant=cantidades.elementAt(i) + m.getCantidades().elementAt(n)/presentacion;                
                    cantidades.setElementAt(nuevaCant, i);                
                    break;
                }
            }
            //System.out.println("redujo a: "+invRuteros.elementAt(j).getRutero() +" el producto: "+movsImportacion.elementAt(i).getProductos().elementAt(n)+" cantidad de: "+movsImportacion.elementAt(i).getCantidades().elementAt(n)/presentacion);
        }    
    }
    
    public void aumentarExistencia(Movimiento m){
        for (int n = 0; n < m.getCantidades().size() ; n++) {                       
            for (int i = 0; i < productos.size(); i++) {
                if (m.getProductos().elementAt(n).equals(productos.elementAt(i))) {                
                    int nuevaCant=cantidades.elementAt(i) + m.getCantidades().elementAt(n);                
                    cantidades.setElementAt(nuevaCant, i);                
                    break;
                }
            }
            //System.out.println("redujo a: "+invRuteros.elementAt(j).getRutero() +" el producto: "+movsImportacion.elementAt(i).getProductos().elementAt(n)+" cantidad de: "+movsImportacion.elementAt(i).getCantidades().elementAt(n)/presentacion);
        }    
    }
    
    @Override
    public String toString(){
        String cadena="";
        for (int i = 0; i <productos.size(); i++) {
            cadena+=productos.elementAt(i)+" "+cantidades.elementAt(i)+" ";
        }
        return cadena;
    }
    
    
    public final void actualizarBD(){
        // ya sumados actualizar en el archivo
        try{
            FileWriter fw = new FileWriter("Archivos/Inventario.data");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < productos.size(); i++) {
                pw.println(productos.elementAt(i)+" "+cantidades.elementAt(i));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error de escritura en archivo de Inventarios", "Error", JOptionPane.ERROR_MESSAGE);
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error de escritura en archivo de Inventario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
