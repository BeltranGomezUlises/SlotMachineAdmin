package Inventario;

import Productos.Producto;
import java.util.Vector;

public class InventarioSucursal extends Inventario{
    String sucursal;
    InventarioSucursal(String suc,Vector<String> prod, Vector<Integer> cant){
        this.sucursal =suc;
        this.productos=prod;
        this.cantidades=cant;        
    }
    
    @Override
    public String toString(){
        String cadena=sucursal+" ";
        for (int i = 0; i < productos.size(); i++) {
            cadena+=productos.elementAt(i)+" "+cantidades.elementAt(i)+" ";
        }
        return cadena;
    }
}
