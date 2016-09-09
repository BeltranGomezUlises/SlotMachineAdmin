package Corte;

import static Utilerias.Utileria.*;
import Maquinas.Maquina;
import Maquinas.Perifericos.Periferico;
import Maquinas.Tarjetas.Tarjeta;
import Sucursal.Sucursal;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class Corte {
    
    String fecha,sucursal,encargado,nota;
    Maquina maquina;
    boolean solucionado;
    int contPos,contNeg,contAntNeg=0,contAntPos=0,pantIn,pantOut,pantInOut, inOut,faltantePantalla=0,faltanteContador=0;
    boolean falloContador=false, falloPantalla=false, falloRobo=false, falloPendiente = false, vueltaContadorPos = false, vueltaContadorNeg = false, falloDescompuesto = false;
    int robo;
    
    boolean tieneCorteAnterior = true;   
    
    public Corte(String fecha,String sucursal, String encargado,Maquina maquina, int contPos, int contNeg, int pantIn,
                int pantOut, int pantInOut, int inOut, String nota,int robo, boolean falloCont, boolean falloPant,
                boolean falloRobo, boolean falloPendiente, boolean vueltaContadorPos, boolean vueltaContadorNeg,boolean falloDescompuesto,
                boolean solucionado){            
        this.fecha=fecha;
        this.sucursal=sucursal;
        this.encargado=encargado;
        this.maquina=maquina;
        this.contPos=contPos;
        this.contNeg=contNeg;
        this.pantIn=pantIn;
        this.pantOut=pantOut;
        this.pantInOut=pantInOut;
        this.inOut=inOut;
        faltantePantalla=this.inOut-this.pantInOut;
        this.robo = robo;
        this.falloContador=falloCont;
        this.falloPantalla=falloPant;
        this.falloRobo=falloRobo;
        this.falloPendiente=falloPendiente;
        this.vueltaContadorPos=vueltaContadorPos;
        this.vueltaContadorNeg=vueltaContadorNeg;
        this.falloDescompuesto = falloDescompuesto;
        this.nota=nota;
        this.solucionado = solucionado;
        
    }
    //metodos gets
    public String getFecha(){
        return fecha;
    }
    public String getSucursal(){
        return sucursal;
    }    
    public String getEncargado(){
        return encargado;
    }
    public Maquina getMaquina() {
        return maquina;
    }    
    public int getContPos(){
        return contPos;
    }
    public int getContNeg(){
        return contNeg;

    }
    public int getPantIn(){
        return pantIn;
    }
    public int getPantOut(){
        return pantOut;
    }
    public int getPantInOut(){
        return pantInOut;
    }
    public int getInOut(){
        return inOut;
    }
    public int getContAntPos(){
        return contAntPos;
    }
    public int getContAntNeg(){
        return contAntNeg;
    }
    
    public int getDifContPos(){
        int difContPos = 0;        
        if (this.isVueltaContadorPos()) {
            difContPos = (1000000 - contAntPos) + contPos;
        }else{            
            difContPos = contPos-contAntPos;
        }
            return difContPos / this.maquina.getTarjeta().getDenominacion();         
    }
    public int getDifContNeg(){
        int difContNeg = 0;        
        if (this.isVueltaContadorNeg()) {
            difContNeg = (1000000 - contAntNeg) + contNeg;
        }else{            
            difContNeg = contNeg-contAntNeg;
        }
            return difContNeg / this.maquina.getTarjeta().getDenominacion();     
    }
    
    public int getContInOut(){
        return this.getDifContPos()-this.getDifContNeg();
    }
    public int getFaltantePantalla(){
        if(faltantePantalla<0){
          return faltantePantalla;  
        }else{
            return 0;
        }
    }
    public int getSobrantePantalla(){
        if(faltantePantalla>0 && faltantePantalla<inOut){
            return faltantePantalla;
        }else{
            return 0;
        }
    }
    
    public int FaltanteContador(){
        if(inOut-(this.getDifContPos()-this.getDifContNeg()) < 0){
            return inOut-(this.getDifContPos() - this.getDifContNeg());
        }else{
            return 0;
        }
    }
    public int sobranteContador(){
        if(inOut-((contPos-contAntPos)-(contNeg-contAntNeg)) > 0 && inOut-((contPos-contAntPos)-(contNeg-contAntNeg)) < inOut){
            return inOut-((contPos-contAntPos)-(contNeg-contAntNeg));
        }else{
            return 0;
        }
    }
    
    public int discrepanciaCont(){
        if(inOut == 0 && ((this.getDifContPos())-(this.getDifContNeg()) < 0)){//elimina el sobrante cuando va a menos que 0
            return 0;
        }else{          
            if (!this.isTieneCorteAnterior()) { //no tiene corte anterior 
                return 0;
            }
            // el inOut (dinero que salio del corte)  menos el inOut que nos generan los contadores
            return inOut - (this.getContInOut());                        
        }
    }    
    public int discrepanciaPant(){
        if(inOut==0 && pantInOut<0){
            return 0;
        }else{
            return faltantePantalla;
        }
    }
    
    public String getNota(){
        return nota;
    }
    
    public float getPorcentajePantalla(){
        float porcentaje=0;
        if(pantIn!=0){
            porcentaje=(float)pantOut/(float)pantIn*100;
        }
        return porcentaje;
    }
    public float getPorcentajeContador(){
        float porcentaje=0;
        int difContAntPos = this.getDifContPos();
        if(contAntPos !=0 && contAntNeg!=0){
            if(this.getDifContPos() == 0){
                difContAntPos = 1;
                porcentaje=(float) this.getDifContNeg() / (float) difContAntPos;
            }else{
                porcentaje=(float) this.getDifContNeg() / (float) difContAntPos * 100;
            }
        }
        return porcentaje;
    }
  
    public boolean isFalloContador() {
        return falloContador;
    }
    public boolean isFalloPantalla() {
        return falloPantalla;
    }
    public boolean isFalloRobo() {
        return falloRobo;
    }
    public boolean isFalloPendiente() {
        return falloPendiente;
    }
    public boolean isVueltaContadorPos() {
        return vueltaContadorPos;
    }
    public boolean isVueltaContadorNeg() {
        return vueltaContadorNeg;
    }
    public boolean isFalloDescompuesto() {
        return falloDescompuesto;
    }
    
    public boolean isTieneCorteAnterior() {
        return tieneCorteAnterior;
    }

    public int getRobo() {
        return robo;
    }

    public boolean isSolucionado() {
        return solucionado;
    }
    
    //metodos sets
     
    public void setRobo(int robo) {
        this.robo = robo;
    }

    public void setTieneCorteAnterior(boolean tieneCorteAnterior) {
        this.tieneCorteAnterior = tieneCorteAnterior;
    }
    public void setVueltaContadorPos(boolean vueltaContadorPos) {
        this.vueltaContadorPos = vueltaContadorPos;
    }
    public void setVueltaContadorNeg(boolean vueltaContadorNeg) {
        this.vueltaContadorNeg = vueltaContadorNeg;
    }    
    public void setFalloContador(boolean f) {
        falloContador =f;
    }
    public void setFalloPantalla(boolean f){
        falloPantalla =f;
    }
    public void setFalloRobo(boolean f){
        falloRobo=f;
    }
    public void setFalloPendiente(boolean falloPendiente){
        this.falloPendiente =falloPendiente;
    }
    public void setFalloDescompuesto(boolean falloDescompuesto) {
        this.falloDescompuesto = falloDescompuesto;
    }
    
    public void setNota(String n){
        nota=n;
    }
    
    public int difContPos(Corte c){
        int dif=this.contPos-c.contPos;
        return dif;
    }
    public int difContNeg(Corte c){
        int dif=this.contNeg-c.contNeg;
        return dif;
    }
    
    public void setContAntPos(int cont){
        contAntPos=cont;
    }
    public void setContAntNeg(int cont){
        contAntNeg=cont;
    }

    public void setSolucionado(boolean solucionado) {
        this.solucionado = solucionado;
    }
   
    
    public static void ActualizarBD(Vector<Corte> cortes){
        try {
            FileWriter fr = new FileWriter("Archivos/Cortes.bin");           
            PrintWriter pw = new PrintWriter(fr);
            for (int i = 0; i < cortes.size(); i++) {
                pw.println(cortes.elementAt(i).toString());                
            }
            pw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el archivo de cortes","Error", JOptionPane.ERROR_MESSAGE);
        }                       
    }
    
    public static Vector<Corte> cargarCortes(){
        Vector<Corte> cortes= new Vector<Corte>();
        try{
            String fecha, sucursal, encargado;            
            int contPos, contNeg, pantNeg, pantIn, pantInOut, pantOut, inOut;                       
            FileReader fr = new FileReader("Archivos/Cortes.bin");
            BufferedReader br= new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while((linea=br.readLine()) != null){
                token=new StringTokenizer(linea);
                fecha=token.nextToken();
                sucursal= token.nextToken();
                encargado=token.nextToken();
                String numMaquina=quitaGuion(token.nextToken());
                //tarjeta
                String tarjeta=token.nextToken();
                int denominacion = Integer.parseInt(token.nextToken());   
                //perifericos
                String nomPeriferico = token.nextToken();                
                Periferico p = new Periferico(nomPeriferico);
                //sucursal
                String nomSucMaquina = token.nextToken();
                int turnos = Integer.parseInt(token.nextToken());
                int comision = Integer.parseInt(token.nextToken());                
                
                int moneda = Integer.parseInt(token.nextToken());
                int fondo = Integer.parseInt(token.nextToken());                
                int pRecu = Integer.parseInt(token.nextToken());  
                int pNoRecu = Integer.parseInt(token.nextToken());                
                Maquina maq = new Maquina(numMaquina, new Tarjeta(tarjeta,denominacion), p, new Sucursal(nomSucMaquina,turnos, comision), moneda, fondo, pRecu);
                maq.setPrestNoRecu(pNoRecu);
                contPos=Integer.parseInt(token.nextToken());
                contNeg=Integer.parseInt(token.nextToken());
                pantIn=Integer.parseInt(token.nextToken());
                pantOut=Integer.parseInt(token.nextToken());
                pantInOut=Integer.parseInt(token.nextToken());
                inOut=Integer.parseInt(token.nextToken());
                String nota = token.nextToken();
                int robo = Integer.parseInt(token.nextToken());
                
                boolean falloCont = Boolean.parseBoolean(token.nextToken());
                boolean falloPant = Boolean.parseBoolean(token.nextToken());
                boolean falloRobo = Boolean.parseBoolean(token.nextToken());
                boolean falloPendiente = Boolean.parseBoolean(token.nextToken());
                boolean vueltaContadorPos = Boolean.parseBoolean(token.nextToken());
                boolean vueltaContadorNeg = Boolean.parseBoolean(token.nextToken());
                boolean falloDescompuesto = Boolean.parseBoolean(token.nextToken());
                boolean solucionado = Boolean.parseBoolean(token.nextToken());
                Corte miCorte= new Corte(fecha,sucursal,encargado,maq,contPos,contNeg,pantIn,pantOut,pantInOut,inOut, nota, robo, falloCont, falloPant, falloRobo, falloPendiente, vueltaContadorPos, vueltaContadorNeg, falloDescompuesto, solucionado);
                cortes.add(miCorte);                    
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "La base de Datos lanzó un error de conversion", "Error", ERROR_MESSAGE);
            e.printStackTrace();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Archivo de Cortes no encontrado", "Error", ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error de escritura en archivo de Cortes", "Error", ERROR_MESSAGE);
        }
        return cortes;
    }
    
    public static Corte copy(Corte c){
        Corte corte = new Corte(c.getFecha(),
                               c.getSucursal(),
                               c.getEncargado(),
                               c.getMaquina(),
                               c.getContPos(),
                               c.getContNeg(),
                               c.getPantIn(),
                               c.getPantOut(),
                               c.getPantInOut(),
                               c.getInOut(),
                               c.getNota(),
                               c.getRobo(),
                               c.isFalloContador(),
                               c.isFalloPantalla(),
                               c.isFalloPantalla(),
                               c.isFalloPendiente(),
                               c.isVueltaContadorPos(),
                               c.isVueltaContadorNeg(),
                               c.isFalloDescompuesto(),
                               c.isSolucionado());                
        return corte;
    }
    
    public int compareTo(Corte c){
        int res=0;
        SimpleDateFormat sdf =new SimpleDateFormat("d/MM/yyyy");
        try {                    
            if (sdf.parse(this.getFecha()).before(sdf.parse(c.getFecha()))) {
                res=-1;
            }else{
                if (sdf.parse(this.getFecha()).after(sdf.parse(c.getFecha()))) {
                    res=1;
                }
            }
        } catch (Exception e) {
        }
        return res;
    }
    
    public boolean equals(Corte c){
        boolean res=false;
        if(fecha.equals(c.getFecha()) && sucursal.equals(c.getSucursal()) && maquina.equals(c.getMaquina())){
            res=true;
        }
        return res;
    }
    
    @Override
    public String toString(){
        String cadena;
        cadena=fecha+" "+sucursal+" "+encargado+" "+maquina.toString()+" "+maquina.getPrestNoRecu()+" "+contPos+" "+contNeg+" "+pantIn+" "+pantOut+" "+pantInOut+" "+inOut+" "+nota+" "+robo+" "+falloContador+" "+falloPantalla+" "+falloRobo+" "+falloPendiente+" "+vueltaContadorPos+" "+vueltaContadorNeg+" "+falloDescompuesto+" "+solucionado;
        return cadena;
    }
}
