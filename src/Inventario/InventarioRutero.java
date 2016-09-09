package Inventario;
 
import Productos.Producto;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class InventarioRutero{
    String rutero;
    Vector<String> productos= new Vector<String>();
    Vector<Integer> cantidades= new Vector<Integer>();
    
    public InventarioRutero(String rutero,Vector<String> productos,Vector<Integer> cantidades){
        this.rutero=rutero;
        this.productos=productos;
        this.cantidades=cantidades;
    }                   
            
    public void setCantidades(Vector<Integer> cantidades) {
        this.cantidades = cantidades;
    }  
    public void setProductos(Vector<String> productos) {
        this.productos = productos;
    }

    public String getRutero() {
        return rutero;
    }        
    public Vector<String> getProductos() {
        return productos;
    }
    public Vector<Integer> getCantidades() {
        return cantidades;
    }
                      
//    public void reducirExistencia(Movimiento m, Vector<Producto> productosArchivo){                                               
//        for (int n = 0; n < m.getCantidades().size() ; n++) {
//            int presentacion = 1;
//            for (int k = 0; k < productosArchivo.size(); k++) {
//                if (productosArchivo.elementAt(k).getNombre().equals(m.getProductos().elementAt(n))) {
//                    presentacion = productosArchivo.elementAt(k).getPresentacion();
//                }
//            }            
//            for (int i = 0; i < productos.size(); i++) {
//                if (m.getProductos().elementAt(n).equals(productos.elementAt(i))) {                
//                    int nuevaCant=cantidades.elementAt(i) - m.getCantidades().elementAt(n)/presentacion;                
//                    cantidades.setElementAt(nuevaCant, i);                
//                    break;
//                }
//            }
//            //System.out.println("redujo a: "+invRuteros.elementAt(j).getRutero() +" el producto: "+movsImportacion.elementAt(i).getProductos().elementAt(n)+" cantidad de: "+movsImportacion.elementAt(i).getCantidades().elementAt(n)/presentacion);
//        }              
//    }
    
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
    
//    public void aumentarExistencia(Movimiento m , Vector<Producto> productosArchivo){
//        for (int n = 0; n < m.getCantidades().size() ; n++) {
//            int presentacion = 1;
//            for (int k = 0; k < productosArchivo.size(); k++) {
//                if (productosArchivo.elementAt(k).getNombre().equals(m.getProductos().elementAt(n))) {
//                    presentacion = productosArchivo.elementAt(k).getPresentacion();
//                }
//            }            
//            for (int i = 0; i < productos.size(); i++) {
//                if (m.getProductos().elementAt(n).equals(productos.elementAt(i))) {                
//                    int nuevaCant=cantidades.elementAt(i) + m.getCantidades().elementAt(n)/presentacion;                
//                    cantidades.setElementAt(nuevaCant, i);                
//                    break;
//                }
//            }
//            //System.out.println("redujo a: "+invRuteros.elementAt(j).getRutero() +" el producto: "+movsImportacion.elementAt(i).getProductos().elementAt(n)+" cantidad de: "+movsImportacion.elementAt(i).getCantidades().elementAt(n)/presentacion);
//        }    
//    }
//    
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
   
    public void addProducto(String prod){
        productos.add(prod);
        cantidades.add(0);
    }
    public void addProducto(String prod, int cant){
        productos.add(prod);
        cantidades.add(cant);
    }
    
    public static Vector<InventarioRutero> cargarInvRuteros(){
        Vector<InventarioRutero> invRuteros= new Vector<InventarioRutero>();
        try {
            FileReader fRead= new FileReader("Archivos/Ruteros.data");
            BufferedReader bRead= new BufferedReader(fRead);            
            String linea;
            StringTokenizer token;
            while((linea=bRead.readLine())!=null){
                token=new StringTokenizer(linea);
                Vector<String> productos= new Vector<String>();
                Vector<Integer> cantidades = new Vector<Integer>();
                String rutero=token.nextToken();
                try {
                    String seg=token.nextToken();
                    while(true){                        
                        productos.add(seg);
                        cantidades.add(Integer.parseInt(token.nextToken()));                        
                        seg=token.nextToken();
                        if (seg==null) {
                            break;
                        }
                    }                     
                } catch (java.util.NoSuchElementException e) {
                    //cuando cae la exepcion aqui es por que ya no hay mas productos
                    InventarioRutero inv= new InventarioRutero(rutero,productos,cantidades);                                
                    invRuteros.add(inv);
                }                
            }
            //una vez lleno el vector agregarle los productos que no contenga aun
            Vector<Producto> productos = Producto.cargarProductos();
            for (int i = 0; i < productos.size(); i++) {
                for (int j = 0; j < invRuteros.size(); j++) {
                    if (!invRuteros.elementAt(j).getProductos().contains(productos.elementAt(i).getNombre())) {
                        invRuteros.elementAt(j).addProducto(productos.elementAt(i).getNombre());
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error de IO en ruteros.data","Error",JOptionPane.ERROR_MESSAGE);
        }
        return invRuteros;
    }
    @Override
    public String toString(){
        String cadena=rutero+" ";
        for (int i = 0; i < productos.size(); i++) {
            cadena+=productos.elementAt(i)+" "+cantidades.elementAt(i)+" ";
        }
        return cadena;
    }
    public static void actualizarBD(Vector<InventarioRutero> inventarios){        
        FileWriter fwRuteros = null;
        try {
            fwRuteros = new FileWriter("Archivos/Ruteros.data");
            PrintWriter pwRuteros = new PrintWriter(fwRuteros);
            for (int i = 0; i < inventarios.size(); i++) {
                pwRuteros.println(inventarios.elementAt(i).toString());
            }   
            pwRuteros.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error de escritura en el archivo de ruteros","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
