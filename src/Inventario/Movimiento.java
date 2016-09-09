package Inventario;

import static Utilerias.Utileria.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Movimiento {
    String fecha,tipo;
    String origen,destino,nota,encargado="null",usuario;
    String linkHoja="null",linkFirma="null";
    char turno;
    Vector<String> productos;
    Vector<Integer> cantidades;

    public Movimiento(String fecha,char turno, String tipo, String origen, String destino, String nota, Vector<String> productos, Vector<Integer> cantidades) {
        this.fecha = fecha;
        this.turno = turno;
        this.tipo=tipo;
        this.origen = origen;
        this.destino = destino;
        this.nota = nota;
        if (nota==null) {
            nota="null";
        }
        this.productos = productos;
        this.cantidades = cantidades;
    }
    
    public Movimiento(String fecha, char turno, String tipo, String origen, String destino, String nota, Vector<String> productos, Vector<Integer> cantidades,String encargado, String usuario) {
        this.fecha = fecha;
        this.turno = turno;
        this.tipo=tipo;
        this.origen = origen;
        this.destino = destino;
        this.nota = nota;
        if (nota==null) {
            nota="null";
        }
        this.productos = productos;
        this.cantidades = cantidades;
        this.encargado = encargado;
        this.usuario = usuario;
    }
    
    public Movimiento(String fecha,char turno, String tipo, String origen, String destino, String nota, Vector<String> productos, Vector<Integer> cantidades,String encargado, String usuario, String linkHoja,String linkFirma) {
        this.fecha = fecha;
        this.turno = turno;
        this.tipo=tipo;
        this.origen = origen;
        this.destino = destino;
        this.nota = nota;
        if (nota==null) {
            nota="null";
        }
        this.productos = productos;
        this.cantidades = cantidades;
        this.encargado = encargado;
        this.usuario = usuario;
        this.linkHoja= linkHoja; 
        this.linkFirma= linkFirma; 
               
    }

    
    
    public String getEncargado() {
        return encargado;
    }
    public String getUsuario() {
        return usuario;
    }    
    public String getLinkHoja() {
        return linkHoja;
    }    
    public String getLinkFirma() {
        return linkFirma;
    }                  
    public String getFecha() {
        return fecha;
    }
    public String getOrigen() {
        return origen;
    }
    public String getDestino() {
        return destino;
    }
    public String getNota() {
        return nota;
    }
    public Vector<String> getProductos() {
        return productos;
    }
    public Vector<Integer> getCantidades() {
        return cantidades;
    }
    public String getTipo() {
        return tipo;
    }
    public char getTurno() {
        return turno;
    }

    public void setTurno(char turno) {
        this.turno = turno;
    }            
    public void setUsuario(String us) {
        this.usuario = us;
    }
    public void setLinkHoja(String linkHoja) {
        this.linkHoja = linkHoja;
    }
    public void setLinkFirma(String linkFirma) {
        this.linkFirma = linkFirma;
    }  
    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }        
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public void setOrigen(String origen) {
        this.origen = origen;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public void setNota(String nota) {
        this.nota = nota;
    }
    public void setProductos(Vector<String> productos) {
        this.productos = productos;
    }
    public void setCantidades(Vector<Integer> cantidades) {
        this.cantidades = cantidades;
    }
    
    
    public static Vector<Movimiento> CargarMovimientos(){
        Vector<Movimiento> mov= new Vector<Movimiento>();
        //cargar los movimientos en un vector             
        try {
            FileReader fr = new FileReader("Archivos/Movimientos.data");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while ((linea=br.readLine())!=null) {
                token= new StringTokenizer(linea);
                String fecha=token.nextToken();
                char turno = token.nextToken().charAt(0);
                String tipo = token.nextToken();
                String origen = token.nextToken();
                String destino = token.nextToken();                
                String nota = token.nextToken();
                String encargado = token.nextToken();
                String usuario = token.nextToken();
                String linkHoja = token.nextToken();
                String linkFirma = token.nextToken();
                Vector<String> productos = new Vector<String>();
                Vector<Integer> cantidades = new Vector<Integer>();
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
                   Movimiento m = new Movimiento(fecha,turno,tipo,origen,destino,nota,productos,cantidades,encargado,usuario,linkHoja,linkFirma);                  
                    //System.out.println(m.toString());
                   mov.add(m);
                   //System.out.println(m.toString());
                }catch (NumberFormatException e)    {
                    JOptionPane.showMessageDialog(null, "Existen un registro con valores no validos\nse omitirá en la lista","Registro invalido", JOptionPane.WARNING_MESSAGE);
                }
            }           
            fr.close();           
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error de lectura en archivo de Movimientos", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error de lectura en archivo de Movimientos", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return mov;
    }
     
    public static Movimiento copy(Movimiento mov){
        Movimiento newMov = new Movimiento(mov.getFecha(),mov.getTurno(),
                            mov.getTipo(),mov.getOrigen(),
                            mov.getDestino(),mov.getNota(),
                            mov.getProductos(),mov.getCantidades(),
                            mov.getEncargado(),
                            mov.getUsuario(),                                                            
                            mov.getLinkHoja(),
                            mov.getLinkFirma());         
        return newMov;
    }
    
    public String toString(){
        String cadena =fecha+" "+turno+" "+tipo+" "+origen+" "+destino+" "+nota+" "+encargado+" "+usuario+" ";               
        cadena+=linkHoja+" "+linkFirma+" ";
        for (int i = 0; i < productos.size(); i++) {
            cadena+=quitaEspacios(productos.elementAt(i))+" "+cantidades.elementAt(i)+" ";
        }        
        return cadena;
     }    
     
    public int compareTo(Movimiento m){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        int result=0;
        try {
             Date fechaThis = sdf.parse(this.getFecha());
             Date fechaM = sdf.parse(m.getFecha());
             if (fechaThis.after(fechaM)) {
                 result = 1;
             }else{
                 if (fechaThis.before(fechaM)) {
                     result = -1;
                 }else{
                     if (this.turno=='D' && m.getTurno()=='N') { //preguntamos los turnos para diferenciarlos
                         result = -1;
                     }else{
                         if (this.turno=='N' && m.getTurno()=='D') {
                            result = 1;
                         }                         
                     }
                 }
             }
        } catch (ParseException e) {
             JOptionPane.showMessageDialog(null, "Error de parseo en comprar Movimientos","Error",JOptionPane.ERROR_MESSAGE);
        }
        return result;             
     }
     
    public boolean equals(Movimiento m){
        boolean res=false;
        if (fecha.equals(m.getFecha()) && turno == m.getTurno() && tipo.equals(m.getTipo()) && origen.equals(m.getOrigen()) && destino.equals(m.getDestino())) {
            res=true;
        }
        return res;
    }
}

