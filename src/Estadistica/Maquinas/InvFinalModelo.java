/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estadistica.Maquinas;

import Productos.Producto;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Ulises Beltrán Gómez --- beltrangomezulises@gmail.com
 */
public class InvFinalModelo {

    private String fecha;
    private String sucursal;
    private String turno;
    private HashMap<String, Integer> cantidadesPorProducto;

    public InvFinalModelo(String fecha, String sucursal, String turno, List<Producto> productos, Vector<Integer> invFinal) {
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.turno = turno;
        
        cantidadesPorProducto = new HashMap<>();        
        for (int i = 0; i < productos.size(); i++) {
            cantidadesPorProducto.put(productos.get(i).getNombre(), invFinal.elementAt(i));            
        }
    }

    public void aumentarProducto(String nombreProducto, int cantidad){
        if (cantidadesPorProducto.containsKey(nombreProducto)) {
            cantidadesPorProducto.put(nombreProducto, cantidad +  cantidadesPorProducto.get(nombreProducto));
        }        
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public HashMap<String, Integer> getCantidadesPorProducto() {
        return cantidadesPorProducto;
    }

    public void setCantidadesPorProducto(HashMap<String, Integer> cantidadesPorProducto) {
        this.cantidadesPorProducto = cantidadesPorProducto;
    }

    @Override
    public String toString() {
        return "InvFinalModelo{" + "fecha=" + fecha + ", sucursal=" + sucursal + ", turno=" + turno + ", cantidadesPorProducto=" + cantidadesPorProducto + '}';
    }

}
