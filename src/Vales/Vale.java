
package Vales;

import Vales.Chequeras.Chequera;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class Vale {
    
    Chequera chequera;
    String folio, fecha, sucursal, concepto, nota, etiqueta, maquina;
    float cantidad;
    boolean checked, vigente;
    
    public Vale(Chequera chequera, String folio, String fecha, String sucursal, String concepto, String nota, String etiqueta, String maquina, float cantidad, boolean checked, boolean vigente) {
        this.chequera = chequera;
        this.folio = folio;
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.concepto = concepto;
        this.nota = nota;
        this.etiqueta = etiqueta;
        this.maquina = maquina;
        this.cantidad = cantidad;
        this.checked = checked;
        this.vigente = vigente;
    }

    public Chequera getChequera() {
    return chequera;        
    }

    public void setChequera(Chequera chequera) {
        this.chequera = chequera;
    }
    
    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
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

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public String getMaquina() {
        return maquina;
    }

    public void setMaquina(String maquina) {
        this.maquina = maquina;
    }        

    public float getCantidad() {
        return cantidad;
    }

    public void setCantidad(float cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }        

    public boolean isVigente() {
        return vigente;
    }

    public void setVigente(boolean vigente) {
        this.vigente = vigente;
    }
        
    public String getFolioCompleto(){
        return this.chequera.getId()+"-"+this.getFolio();
    }
    @Override
    public String toString(){
        return chequera.getId() + " " + chequera.getRutero() + " " + folio + " " + fecha + " " + sucursal + " " + concepto + " " + nota + " " + etiqueta + " " + maquina + " " + cantidad + " " + checked + " " + vigente;
    }
    
    public boolean equals(Vale v){
        return this.getFolio().equals(v.getFolio()) && this.getChequera().getId().equals(v.getChequera().getId());
    }   
    
    public static ArrayList<Vale> cargarVales(){
        ArrayList<Vale> vales = new ArrayList<>();
        try {
            FileReader fr = new FileReader("Archivos/Vales.bin");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while ((linea=br.readLine()) != null) {
                token = new StringTokenizer(linea);
                //chequera:
                String id = token.nextToken();
                String rutero = token.nextToken();
                String folio = token.nextToken();
                String fecha = token.nextToken();
                String sucursal = token.nextToken();
                String concepto = token.nextToken();
                String nota = token.nextToken();
                String etiqueta = token.nextToken();
                String maquina = token.nextToken();
                float cantidad = Float.parseFloat(token.nextToken());
                boolean checked = Boolean.parseBoolean(token.nextToken());
                boolean vigente = Boolean.parseBoolean(token.nextToken());
                
                Vale v = new Vale(new Chequera(id, rutero),folio, fecha, sucursal, concepto, nota, etiqueta, maquina, cantidad, checked, vigente);
                vales.add(v);
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de lectura de archivo vales", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return vales;
    }
    
    public static void actualizarVD(ArrayList<Vale>  vales){        
        try {
            FileWriter fw = new FileWriter("Archivos/Vales.bin");
            PrintWriter pw = new PrintWriter(fw);
            for (Vale vale : vales) {
                pw.println(vale);
            }
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al intentar guardar el vale al archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }           
    }

    
    
}
