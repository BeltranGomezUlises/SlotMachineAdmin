package Formato;

import ControlUsuario.Loged;
import static Utilerias.Utileria.*;
import Excel.ExcelManager;
import Inventario.InventarioRutero;
import Inventario.Movimiento;
import Utilerias.Filtro;
import java.awt.Desktop;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConsultaFormato extends javax.swing.JFrame {
        
    private Vector<Formato> Formatos= new Vector<Formato>();
    private Vector fila= new Vector();
    private Vector<Formato> filtrado= new Vector<Formato>();    
    private Vector<String> sucursales= new Vector<String>();
    private Vector<String> encargados= new Vector<String>();
    
    private Vector<Vector<Integer>> posFaltantesAnteriores = new Vector<Vector<Integer>>();
    private Vector<Vector<Integer>> posFaltantesPosteriores = new Vector<Vector<Integer>>();
    private Vector<Vector<Integer>> cantFaltantesAnteriores = new Vector<Vector<Integer>>();
    private Vector<Vector<Integer>> cantFaltantesPosteriores = new Vector<Vector<Integer>>();
    
    private Vector<Float> faltantesFondoInicial = new Vector<Float>();
    private Vector<Float> faltantesFondoFinal = new Vector<Float>();
    private Vector<Float> sobrantesFondo = new Vector<Float>();
    
    private Vector<Float> sobrantesFondoFinal = new Vector<Float>();
        
    private Vector<Vector<Integer>> posSobrantes= new Vector<Vector<Integer>>();
    private Vector<Vector<Integer>> cantSobrantes= new Vector<Vector<Integer>>();
    private Vector<Integer> marcasTotalFinal= new Vector<Integer>();
    private SimpleDateFormat df = new SimpleDateFormat("d/MM/yyyy");
    private Calendar c = Calendar.getInstance();
    private String datos[][]={};
    private String cabecera[]={"Fecha","Turno","Sucursal","Encargado","Venta Total","Retiro","Faltante","Sobrante","Utilidad","Comisión","Firmado","Pagado","Total Final"};
    private int anchos[]={40,6,70,70,50,50,50,50,50,50,10,10,55}; //cargar la tabla
    private DefaultTableModel md = new DefaultTableModel(datos, cabecera);
    private DecimalFormat decimalFormat= new DecimalFormat("0.00");
    
    public ConsultaFormato() {
        initComponents();                
        try{            
            this.permisosUsuario(); //asignar los permisos a los botones            
            tabla.setAutoCreateRowSorter(true); 
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setModel(md);//cargar la tabla            
            ajustarTamañosTabla(tabla,anchos);
            
            Formatos = Formato.cargarFormatos(); //cargar los formatos al vector  
            
            this.llenarComboBox();//llenar los comboBox con los datos
            
            Formato.ordenarFormatosQ(Formatos, 0, Formatos.size()-1);            
            Formatos = Formato.ordenarFormatosSucursal(Formatos, sucursales);            
            Formato.ordenarFormatosTurno(Formatos);
                        
            this.asignarFaltantes();                                           
                                
            //actualizarFormatos(Formatos);              
            this.llenarTabla(Formatos);
            this.agregarFilaTotales(tabla);                       
//            tabla.setDefaultRenderer(Object.class, new TableCellTotalFinalRenderer());//cambiar el defaultTableCellRenderer
            this.MarcarTotalFinal(Formatos);
            filtrado=Formatos;   
            
        }      
        catch(NoSuchElementException e){
            JOptionPane.showMessageDialog(rootPane, "Base de datos no encontrada o inutilizable", "Error", WIDTH);
        }        
    }
    
    public ConsultaFormato(Filtro filtro) {
        initComponents();                
        try{            
            this.permisosUsuario(); //asignar los permisos a los botones            
            tabla.setAutoCreateRowSorter(true); 
            tabla.getTableHeader().setReorderingAllowed(false);
            tabla.setModel(md);//cargar la tabla            
            ajustarTamañosTabla(tabla,anchos);
            
            Formatos = Formato.cargarFormatos(); //cargar los formatos al vector  
            
            this.llenarComboBox();//llenar los comboBox con los datos
            
            Formato.ordenarFormatosQ(Formatos, 0, Formatos.size()-1);            
            Formatos = Formato.ordenarFormatosSucursal(Formatos, sucursales);            
            Formato.ordenarFormatosTurno(Formatos);
                        
            this.asignarFaltantes();                                           
                                
            //actualizarFormatos(Formatos);              
            this.llenarTabla(Formatos);
            this.agregarFilaTotales(tabla);                       
//            tabla.setDefaultRenderer(Object.class, new TableCellTotalFinalRenderer());//cambiar el defaultTableCellRenderer
            this.MarcarTotalFinal(Formatos);
            filtrado=Formatos;   
           
            //colocar los valores del filtro y mandar llamar filtrar
            cmbSucursal.setSelectedItem(filtro.getSucursal());
            cmbEncargado.setSelectedItem(filtro.getEncargado());
            cmbTurno.setSelectedItem(filtro.getTurno());
            cmbPagado.setSelectedItem(filtro.getPagado());
            try {
                dFechaI.setDate(df.parse(filtro.getFechaIni()));
                dFechaF.setDate(df.parse(filtro.getFechaFin()));
            } catch (NullPointerException | ParseException e) {
               //en caso de no existir fechas omitir asignacion 
            }
            
            btnFiltrarActionPerformed(null);
        }      
        catch(NoSuchElementException e){
            JOptionPane.showMessageDialog(rootPane, "Base de datos no encontrada o inutilizable", "Error", WIDTH);
        }
        
        
            
        
    }                
    
    private void permisosUsuario(){
         new Runnable(){
             @Override
             public void run() {
                  //Permisos de usuario
                    btnEliminar.setEnabled(Loged.getLoged().isEliminarFormato());
                    btnPagar.setEnabled(Loged.getLoged().isPagarFormato());
                    btnExportar.setEnabled(Loged.getLoged().isExportarFormatos());
                    btnEditar.setEnabled(Loged.getLoged().isEditarFormato());
             }             
         }.run();
           
    }
            
    private  void llenarComboBox(){
        //llenar los comboBox
            cmbSucursal.addItem("Todos");
            cmbEncargado.addItem("Todos");
            for (int i = 0; i < Formatos.size(); i++) {
                if(!sucursales.contains(Formatos.elementAt(i).getSucursal())){
                    sucursales.add(Formatos.elementAt(i).getSucursal());
                    cmbSucursal.addItem(quitaGuion(sucursales.lastElement()));
                }
                if(!encargados.contains(Formatos.elementAt(i).getEncargado())){
                    encargados.add(Formatos.elementAt(i).getEncargado());
                    cmbEncargado.addItem(quitaGuion(Formatos.elementAt(i).getEncargado()));
                }
            }
            cmbTurno.addItem("Todos");
            cmbTurno.addItem("D");
            cmbTurno.addItem("N");
    }       
    
    static void ajustarTamañosTabla(final JTable table,final int[] anchos){
        new Runnable(){
            @Override
            public void run() {
                for(int k=0; k < anchos.length; k++){//ajustar los tamaños
                    table.getColumnModel().getColumn(k).setPreferredWidth(anchos[k]); 
                }
            }            
        }.run();
        
    }
    
    private void asignarFaltantes(){
        //Revisar los formatos y asignar los faltantes con las 
        //posiciones de los faltantes,sobrante y utilidad
        for(int k=0; k < Formatos.size(); k++){ //INICIALIZAR VECTORES
             posFaltantesAnteriores.add(new Vector<Integer>());
             posFaltantesPosteriores.add(new Vector<Integer>());
             cantFaltantesAnteriores.add(new Vector<Integer>());
             cantFaltantesPosteriores.add(new Vector<Integer>());
             posSobrantes.add(new Vector<Integer>());
             cantSobrantes.add(new Vector<Integer>());
             faltantesFondoInicial.add(0f);
             faltantesFondoFinal.add(0f);
             sobrantesFondo.add(0f);
        }
        for(int k=0; k < Formatos.size() - 1; k++){
            if(Formatos.elementAt(k).getSucursal().equals(Formatos.elementAt(k+1).getSucursal())){
                if (Formatos.elementAt(k).getTurnos() == 2){
                    if (Formatos.elementAt(k).getTurno() == 'D' ) {
                        if (Formatos.elementAt(k).getFecha().equals(Formatos.elementAt(k+1).getFecha())) {
                            //asignar primero los sobrantes
                            posSobrantes.set(k, Formatos.elementAt(k).posSobrantes(Formatos.elementAt(k+1)));
                            cantSobrantes.set(k,Formatos.elementAt(k).cantSobrantes(Formatos.elementAt(k+1)));
                            sobrantesFondo.set(k,Formatos.elementAt(k).sobrante(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k).setSobrante(Formatos.elementAt(k).sobrante(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k).setSobranteUtilidad(Formatos.elementAt(k).sobranteUtilidad(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k).setSobranteComision(Formatos.elementAt(k).sobranteComision(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k).setSobranteFondo(Formatos.elementAt(k).sobranteFondo(Formatos.elementAt(k+1)));                            
                            if (Formatos.elementAt(k).getFirma()) {//firma para saber hacia donde iran los faltantes
                                //firmado los faltantes seran los anteriores de k+1                                
                                posFaltantesAnteriores.set(k+1,Formatos.elementAt(k).posFaltantesAnteriores(Formatos.elementAt(k+1)));                                
                                cantFaltantesAnteriores.set(k+1,Formatos.elementAt(k).cantFaltantes(Formatos.elementAt(k+1)));                                
                                faltantesFondoInicial.set(k+1,Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k+1).setFaltante(Formatos.elementAt(k).faltante(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k+1).setFaltanteUtilidad(Formatos.elementAt(k).faltanteUtilidad(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k+1).setFaltanteComision(Formatos.elementAt(k).faltanteComision(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k+1).setFondoFaltante(Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                                
                            }else{
                                //no firmado sera los posteriores de k                                
                                posFaltantesPosteriores.set(k,Formatos.elementAt(k).posFaltantesPosteriores(Formatos.elementAt(k+1)));                                
                                cantFaltantesPosteriores.set(k, Formatos.elementAt(k).cantFaltantes(Formatos.elementAt(k+1)));                                                                
                                faltantesFondoFinal.set(k,Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k).setFaltante(Formatos.elementAt(k).faltante(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k).setFaltanteUtilidad(Formatos.elementAt(k).faltanteUtilidad(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k).setFaltanteComision(Formatos.elementAt(k).faltanteComision(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k).setFondoFaltante(Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                            }
                        }
                    }else{
                        try {
                            c.setTime(df.parse(Formatos.elementAt(k).getFecha()));
                            c.add(Calendar.DATE, 1); //sumarle un dia
                            if (Formatos.elementAt(k+1).getFecha().equals(df.format(c.getTime())) && Formatos.elementAt(k+1).getTurno()=='D') {
                                //si es el correcto asignar sobrantes
                                //asignar primero los sobrantes
//                                System.out.println(Formatos.elementAt(k));
//                                System.out.println(Formatos.elementAt(k+1));
                                posSobrantes.set(k, Formatos.elementAt(k).posSobrantes(Formatos.elementAt(k+1)));
                                cantSobrantes.set(k,Formatos.elementAt(k).cantSobrantes(Formatos.elementAt(k+1)));
                                sobrantesFondo.set(k,Formatos.elementAt(k).sobrante(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k).setSobranteUtilidad(Formatos.elementAt(k).sobranteUtilidad(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k).setSobranteComision(Formatos.elementAt(k).sobranteComision(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k).setSobrante(Formatos.elementAt(k).sobrante(Formatos.elementAt(k+1)));
                                Formatos.elementAt(k).setSobranteFondo(Formatos.elementAt(k).sobranteFondo(Formatos.elementAt(k+1))); 
                                if (Formatos.elementAt(k).getFirma()) {
                                    //firmado los faltantes seran los anteriores de k+1                                    
                                    posFaltantesAnteriores.set(k+1,Formatos.elementAt(k).posFaltantesAnteriores(Formatos.elementAt(k+1)));                                    
                                    cantFaltantesAnteriores.set(k+1,Formatos.elementAt(k).cantFaltantes(Formatos.elementAt(k+1)));                                    
                                    faltantesFondoInicial.set(k+1,Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                                    Formatos.elementAt(k+1).setFaltante(Formatos.elementAt(k).faltante(Formatos.elementAt(k+1)));
                                    Formatos.elementAt(k+1).setFaltanteUtilidad(Formatos.elementAt(k).faltanteUtilidad(Formatos.elementAt(k+1)));
                                    Formatos.elementAt(k+1).setFaltanteComision(Formatos.elementAt(k).faltanteComision(Formatos.elementAt(k+1)));
                                    Formatos.elementAt(k+1).setFondoFaltante(Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                                }else{
                                   //no firmado sera los posteriores de k                                    
                                    posFaltantesPosteriores.set(k,Formatos.elementAt(k).posFaltantesPosteriores(Formatos.elementAt(k+1)));                                    
                                    cantFaltantesPosteriores.set(k, Formatos.elementAt(k).cantFaltantes(Formatos.elementAt(k+1))); 
                                    faltantesFondoFinal.set(k,Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                                    Formatos.elementAt(k).setFaltante(Formatos.elementAt(k).faltante(Formatos.elementAt(k+1)));                                    
                                    Formatos.elementAt(k).setFaltanteUtilidad(Formatos.elementAt(k).faltanteUtilidad(Formatos.elementAt(k+1)));
                                    Formatos.elementAt(k).setFaltanteComision(Formatos.elementAt(k).faltanteComision(Formatos.elementAt(k+1)));
                                    Formatos.elementAt(k).setFondoFaltante(Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                                }
                            }
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(rootPane,"No se logró marcar los formatos, por error de parseo de fechas","Error",JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }                        
                    }                    
                }else{                                                               
                    try{
                    //tomar la fecha del formato k y preguntar si el siguiente formato es el siguiente dia y turno 'D'                    
                    c.setTime(df.parse(Formatos.elementAt(k).getFecha()));                        
                    c.add(Calendar.DATE, 1); //sumarle un dia a la fecha
                    if(Formatos.elementAt(k+1).getFecha().equals(df.format(c.getTime())) && Formatos.elementAt(k+1).getTurno()=='D'){
                        //asignar primero los sobrantes                                               
                        posSobrantes.set(k, Formatos.elementAt(k).posSobrantes(Formatos.elementAt(k+1)));                        
                        cantSobrantes.set(k,Formatos.elementAt(k).cantSobrantes(Formatos.elementAt(k+1)));
                        sobrantesFondo.set(k,Formatos.elementAt(k).sobrante(Formatos.elementAt(k+1)));
                        Formatos.elementAt(k).setSobrante(Formatos.elementAt(k).sobrante(Formatos.elementAt(k+1)));                        
                        Formatos.elementAt(k).setSobranteUtilidad(Formatos.elementAt(k).sobranteUtilidad(Formatos.elementAt(k+1)));
                        Formatos.elementAt(k).setSobranteComision(Formatos.elementAt(k).sobranteComision(Formatos.elementAt(k+1)));
                        Formatos.elementAt(k).setSobranteFondo(Formatos.elementAt(k).sobranteFondo(Formatos.elementAt(k+1)));                            
                        if (Formatos.elementAt(k).getFirma()) {
                            //firmado los faltantes seran los anteriores de k+1                            
                            posFaltantesAnteriores.set(k+1,Formatos.elementAt(k).posFaltantesAnteriores(Formatos.elementAt(k+1)));                            
                            cantFaltantesAnteriores.set(k+1,Formatos.elementAt(k).cantFaltantes(Formatos.elementAt(k+1)));                            
                            faltantesFondoInicial.set(k+1,Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k+1).setFaltante(Formatos.elementAt(k).faltante(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k+1).setFaltanteUtilidad(Formatos.elementAt(k).faltanteUtilidad(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k+1).setFaltanteComision(Formatos.elementAt(k).faltanteComision(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k+1).setFondoFaltante(Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                        }else{
                           //no firmado sera los posteriores de k                            
                            posFaltantesPosteriores.set(k,Formatos.elementAt(k).posFaltantesPosteriores(Formatos.elementAt(k+1)));                            
                            cantFaltantesPosteriores.set(k, Formatos.elementAt(k).cantFaltantes(Formatos.elementAt(k+1)));                                     
                            faltantesFondoFinal.set(k,Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k).setFaltante(Formatos.elementAt(k).faltante(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k).setFaltanteUtilidad(Formatos.elementAt(k).faltanteUtilidad(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k).setFaltanteComision(Formatos.elementAt(k).faltanteComision(Formatos.elementAt(k+1)));
                            Formatos.elementAt(k).setFondoFaltante(Formatos.elementAt(k).faltanteFondo(Formatos.elementAt(k+1)));
                        }
                    }
                    }catch(ParseException e){                                
                        JOptionPane.showMessageDialog(rootPane,"No se logró marcar los formatos, por error de parseo de fechas","Error",JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }                                           
                }                            
            }
        }
    }    
    
    public static void actualizarFormatos(Vector<Formato> formatos){
        //actualizar los valores de los formatos en el archivo de origen
        try {
               FileWriter out = new FileWriter("Archivos/Formatos.bin");
               PrintWriter pw = new PrintWriter(out);
               for(int k=0;k<formatos.size();k++){
                   pw.println(formatos.elementAt(k).toString());
               }
               pw.close();
           } catch (IOException ex) {
               JOptionPane.showMessageDialog(null, "Ocurrió un error con el archivo", "Error de Archvo",JOptionPane.ERROR_MESSAGE);
           }
    }
    
    public final void llenarTabla(Vector<Formato> filtrado){
        //vaciar tabla
       
        DefaultTableModel md = (DefaultTableModel) tabla.getModel();
        
        md.setRowCount(0);
        //llenar las filas de las tablas con con cada formato
            for(int k=0; k<filtrado.size();k++){
                fila= new Vector();
                fila.add(filtrado.elementAt(k).getFecha());
                fila.add(filtrado.elementAt(k).getTurno());
                fila.add(quitaGuion(filtrado.elementAt(k).getSucursal()));
                fila.add(quitaGuion(filtrado.elementAt(k).getEncargado()));
                fila.add(filtrado.elementAt(k).totalVenta()-filtrado.elementAt(k).getSobrante()+filtrado.elementAt(k).getFaltante());
                fila.add(filtrado.elementAt(k).getRetiro());
                fila.add(filtrado.elementAt(k).getFaltante());
                fila.add(filtrado.elementAt(k).getSobrante());
                fila.add(decimalFormat.format(filtrado.elementAt(k).ganancia()-filtrado.elementAt(k).getSobranteUtilidad()));
                fila.add(decimalFormat.format(filtrado.elementAt(k).getComision()-filtrado.elementAt(k).getSobranteComision()));
                String firmado="";
                if(filtrado.elementAt(k).getFirma()){
                    firmado="Sí";
                }else{
                    firmado="No";
                }
                String pagado="";
                if(filtrado.elementAt(k).getPagado()){
                    pagado="Sí";
                }else{
                    pagado="No";
                }
                fila.add(firmado);
                fila.add(pagado);
                fila.add(filtrado.elementAt(k).totalVenta()-filtrado.elementAt(k).getSobrante()-filtrado.elementAt(k).getFondoFinal()-filtrado.elementAt(k).getFondoSobrante()+filtrado.elementAt(k).getFondoInicial()+filtrado.elementAt(k).getFaltante()+filtrado.elementAt(k).getFondoFaltante()-filtrado.elementAt(k).getRetiro()-200);
                md.addRow(fila);
            }
    }
    
    public final void MarcarTotalFinal(final Vector<Formato> formatos){        
        new Runnable(){

            @Override
            public void run() {
               //copiar el vector de formatos y guardar los indices de los formatos que se deba marcar el total final como afectable        
                marcasTotalFinal=new Vector<Integer>();
                for(int k=0;k<formatos.size();k++){            
                    if (!formatos.elementAt(k).getFirma()){ //si no esta firmado
                        try{
                            if(!formatos.elementAt(k).getSucursal().equals(formatos.elementAt(k+1).getSucursal())){ //si no son de la misma sucursal
                                marcasTotalFinal.add(k);  
                            }else{
                                try{
                                    if (formatos.elementAt(k).getTurnos() == 2){//cuando tiene dos turno D y N
                                        if (formatos.elementAt(k).getTurno() == 'D') {
                                            if (!formatos.elementAt(k).getFecha().equals(formatos.elementAt(k+1).getFecha())) { //si el siguiete formato no es del mismo dia
                                                marcasTotalFinal.add(k);
                                            }
                                        }else{
                                            Calendar c = Calendar.getInstance();
                                            c.setTime(df.parse(formatos.elementAt(k).getFecha()));
                                            //sumarle un dia a la fecha
                                            c.add(Calendar.DATE, 1);
                                            if (!(formatos.elementAt(k+1).getFecha().equals(df.format(c.getTime())) && formatos.elementAt(k+1).getTurno() == 'D')) { //si el siguiente no es turno "D" y del dia siguiente
                                                marcasTotalFinal.add(k);
                                            }
                                        }                                
                                    }else{//cuando tiene un solo turno D
                                       //tomar la fecha del formato k y preguntar si el siguiente formato es el siguiente dia y turno 'D'
                                        Calendar c = Calendar.getInstance();
                                        c.setTime(df.parse(formatos.elementAt(k).getFecha()));
                                        //sumarle un dia a la fecha
                                        c.add(Calendar.DATE, 1);
                                        if(!(formatos.elementAt(k+1).getFecha().equals(df.format(c.getTime()))&& formatos.elementAt(k+1).getTurno()=='D')){
                                          marcasTotalFinal.add(k);  
                                        } 
                                    }
                                }catch(ParseException e){
                                    System.out.println("Error de parse de fecha");
                                }   
                            }                    
                        }catch(ArrayIndexOutOfBoundsException e){
                            marcasTotalFinal.add(k); //ultimo elemento
                        }
                    }
                }
                //marcar los totales Finales cuando pueda cambiar
                for(int k=0; k<marcasTotalFinal.size(); k++){
                     md.setValueAt(md.getValueAt(marcasTotalFinal.elementAt(k), 12) + "*",marcasTotalFinal.elementAt(k), 12);
                } 
            }            
        }.run();        
    }
    
    public final void agregarFilaTotales(JTable tabla){
         //Agregar la ultima fila de totales:
        int filas = tabla.getRowCount();
        float venta=0, retiro=0, faltante=0, sobrante=0, utilidad=0, comision=0, total=0;
        for(int k=0; k < filas; k++){
            venta += Float.parseFloat(String.valueOf(tabla.getValueAt(k, 4)));
            retiro += Float.parseFloat(String.valueOf(tabla.getValueAt(k, 5)));
            faltante += Float.parseFloat(String.valueOf(tabla.getValueAt(k, 6)));
            sobrante += Float.parseFloat(String.valueOf(tabla.getValueAt(k, 7)));
            utilidad += Float.parseFloat(String.valueOf(tabla.getValueAt(k, 8)));
            comision += Float.parseFloat(String.valueOf(tabla.getValueAt(k, 9)));
            total += Float.parseFloat(String.valueOf(tabla.getValueAt(k, 12)));
        }
        Vector fila= new Vector();
        fila.add("");fila.add("");fila.add("");fila.add("TOTALES:");
        fila.add(decimalFormat.format(venta));
        fila.add(decimalFormat.format(retiro)); 
        fila.add(decimalFormat.format(faltante));
        fila.add(decimalFormat.format(sobrante));
        fila.add(decimalFormat.format(utilidad));
        fila.add(comision);
        fila.add("");
        fila.add("");
        fila.add(total);
        
        md.addRow(fila);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        panelFiltros = new javax.swing.JPanel();
        cmbEncargado = new javax.swing.JComboBox();
        cmbSucursal = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        cmbTurno = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        cmbPagado = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        dFechaI = new com.toedter.calendar.JDateChooser();
        dFechaF = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnFiltrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnEntregas = new javax.swing.JButton();
        btnUtilidad = new javax.swing.JButton();
        btnTotal = new javax.swing.JButton();
        btnPagar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultas");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        panelFiltros.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        cmbEncargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEncargadoActionPerformed(evt);
            }
        });

        cmbSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSucursalActionPerformed(evt);
            }
        });

        jLabel2.setText("Sucursal:");
        jLabel2.setToolTipText("");

        jLabel3.setText("Encargado:");
        jLabel3.setToolTipText("");

        jLabel1.setText("Turno:");

        jLabel6.setText("Pagado:");

        cmbPagado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Si", "No" }));

        javax.swing.GroupLayout panelFiltrosLayout = new javax.swing.GroupLayout(panelFiltros);
        panelFiltros.setLayout(panelFiltrosLayout);
        panelFiltrosLayout.setHorizontalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(185, Short.MAX_VALUE))
        );
        panelFiltrosLayout.setVerticalGroup(
            panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltrosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(cmbEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel2)
                .addComponent(jLabel3)
                .addComponent(jLabel1)
                .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jLabel6)
                .addComponent(cmbPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Fechas", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel4.setText("Fecha Inicial:");

        jLabel5.setText("Fecha Final:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dFechaI, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(dFechaF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(dFechaI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dFechaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/filtrarFinal.png"))); // NOI18N
        btnFiltrar.setText("      Filtrar Lista   ");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });
        btnFiltrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnFiltrarKeyTyped(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eliminarFinal.png"))); // NOI18N
        btnEliminar.setText(" Eliminar Formato");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        btnEliminar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnEliminarKeyTyped(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/editar.png"))); // NOI18N
        btnEditar.setText("    Editar Formato");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        btnEditar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnEditarKeyTyped(evt);
            }
        });

        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MostrarFinal.png"))); // NOI18N
        btnMostrar.setText("Mostrar Formato");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });
        btnMostrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnMostrarKeyTyped(evt);
            }
        });

        btnEntregas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/entrega.png"))); // NOI18N
        btnEntregas.setText("      Entregas     ");
        btnEntregas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregasActionPerformed(evt);
            }
        });
        btnEntregas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnEntregasKeyTyped(evt);
            }
        });

        btnUtilidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/utilidadPorProducto.png"))); // NOI18N
        btnUtilidad.setText("      Utilidades      ");
        btnUtilidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUtilidadActionPerformed(evt);
            }
        });
        btnUtilidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnUtilidadKeyTyped(evt);
            }
        });

        btnTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/suma.png"))); // NOI18N
        btnTotal.setText("         Totales        ");
        btnTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalActionPerformed(evt);
            }
        });
        btnTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnTotalKeyTyped(evt);
            }
        });

        btnPagar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/pagar_.png"))); // NOI18N
        btnPagar.setText("            Pagar      ");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });
        btnPagar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnPagarKeyTyped(evt);
            }
        });

        btnExportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/excel_final.png"))); // NOI18N
        btnExportar.setText("Exportar a Excel");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        btnCerrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCerrarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnMostrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnUtilidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPagar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEntregas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnEntregas, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnUtilidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnExportar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(panelFiltros, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelFiltros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cmbSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSucursalActionPerformed
        
    }//GEN-LAST:event_cmbSucursalActionPerformed
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
     this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed
    private void cmbEncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEncargadoActionPerformed
        
    }//GEN-LAST:event_cmbEncargadoActionPerformed
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        String sucursal = quitaEspacios(cmbSucursal.getSelectedItem().toString());
        String encargado = quitaEspacios(cmbEncargado.getSelectedItem().toString());
        char turno = cmbTurno.getSelectedItem().toString().charAt(0);
        String pagado = cmbPagado.getSelectedItem().toString();
        //copiar y filtrar los Todos los formatos
        filtrado= new Vector<Formato>();
        for(int k=0; k < Formatos.size(); k++){
            filtrado.add(Formatos.elementAt(k));
        }
        //una vez lleno eliminar todos aquellos registros a los que no
        //corresponda el filtro seleccionado
        //Para la sucursal
        if(!"Todos".equals(sucursal)){
            int k=0;
            while(k<filtrado.size()){
                if(!filtrado.elementAt(k).getSucursal().equals(sucursal)){
                    filtrado.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        //Para el encargado
        if(!"Todos".equals(encargado)){
            int k=0;
            while(k<filtrado.size()){
                if(!filtrado.elementAt(k).getEncargado().equals(encargado)){
                    filtrado.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        //para el turno
        if(turno=='D' || turno=='N'){
            int k=0;
            while(k<filtrado.size()){
                if(filtrado.elementAt(k).getTurno()!=turno){
                    filtrado.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        //para el pago
            if(pagado.equals("Si")){
                int k=0;
                while(k<filtrado.size()){
                    if(!filtrado.elementAt(k).getPagado()){
                        filtrado.removeElementAt(k);
                    }else{
                        k++;
                    }
                }
            }
            if(pagado.equals("No")){
                int k=0;
                while(k<filtrado.size()){
                    if(filtrado.elementAt(k).getPagado()!=false){
                        filtrado.removeElementAt(k);
                    }else{
                        k++;
                    }
                }
            }
        //Para las fechas
        //tomar la fecha inicial como string y convertirlo a fecha
        //tomar la fecha final y convertirlo a fecha
        //usar los metodos before() y after() para sacar las que se
        //encuentren en medio de el rango
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        try{
            int k=0;
            Date fechaInicial=sdf.parse(sdf.format(dFechaI.getDate()));            
            Date fechaFinal=sdf.parse(sdf.format(dFechaF.getDate()));            
            String fecha;
            while(k<filtrado.size()){
                fecha=filtrado.elementAt(k).getFecha();
                Date fechaFormato = sdf.parse(fecha);
                if(fechaFormato.before(fechaInicial) || fechaFormato.after(fechaFinal)){
                   filtrado.removeElementAt(k); 
                }else{
                    k++;
                }
            }        
        }catch(NullPointerException e){
//            JOptionPane.showMessageDialog(rootPane, "Referencia nula al filtrar por fechas", "ERROR", WIDTH);
        }catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al convertir fechas al filtrar", "ERROR", WIDTH);
            ex.printStackTrace();
        }
        //Cargar la tabla
       // md = new DefaultTableModel(datos, cabecera);
        //tabla.setModel(md);
        //this.ajustarTamañosTabla(tabla,anchos);//ajustar los tamaños       
        //llenar la tabla con el vector filtrado
        this.llenarTabla(filtrado);
        //Agregar la ultima fila de totales:
        this.agregarFilaTotales(tabla);
        //marcar los totales Finales cuando pueda cambiar
        this.MarcarTotalFinal(filtrado);
    }//GEN-LAST:event_btnFiltrarActionPerformed
    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
       int fila= tabla.getSelectedRow();
       if(fila+1==tabla.getRowCount()){
            JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada inválida!", "Atención", JOptionPane.WARNING_MESSAGE );
       }else{
            if(fila!=-1){
                try{
                    int[] filas=tabla.getSelectedRows();
                    for(int k=0; k<filas.length;k++){
                        MostrarFormato mf= new MostrarFormato(filtrado.elementAt(filas[k]));
                        mf.setVisible(true);
                        mf.setLocationRelativeTo(this);         
                             //asignar las posiciones anteriores y posteriores de los faltanes para marcarlos
                             //primero buscar el formato filtrado al que corresponde en el vector Formatos
                             for(int j=0; j<Formatos.size(); j++){
                                 if(filtrado.elementAt(filas[k]).getFecha().equals(Formatos.elementAt(j).getFecha())
                                 && filtrado.elementAt(filas[k]).getTurno()==Formatos.elementAt(j).getTurno()
                                 && filtrado.elementAt(filas[k]).getSucursal().equals(Formatos.elementAt(j).getSucursal())){ 
                                    mf.setPosFaltantesAnteriores(posFaltantesAnteriores.elementAt(j));
                                    mf.setCantFaltantesAnteriores(cantFaltantesAnteriores.elementAt(j));
                                    mf.setPosFaltantesPosteriores(posFaltantesPosteriores.elementAt(j));
                                    mf.setCantFaltantesPosteriores(cantFaltantesPosteriores.elementAt(j));
                                    mf.setPosSobrantes(posSobrantes.elementAt(j));
                                    mf.setCantSobrantes(cantSobrantes.elementAt(j)); 
                                    mf.setMarcas();
                                 }
                             }          
                    }  
                }catch(ArrayIndexOutOfBoundsException e){
                    JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada inválida!", "Atención", JOptionPane.WARNING_MESSAGE );
                    e.printStackTrace();
                }
                
            }
            else{
                 JOptionPane.showMessageDialog(this,"Debe seleccionar una fila","Atención",JOptionPane.ERROR_MESSAGE);
            }
       }
    }//GEN-LAST:event_btnMostrarActionPerformed
    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int indexFila = tabla.getSelectedRow();
        if(indexFila + 1 == tabla.getRowCount()){
            JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada inválida!", "Atención", JOptionPane.WARNING_MESSAGE );
        }else{
            if( indexFila != -1){
                String fecha = String.valueOf(tabla.getValueAt(indexFila, 0));
                char turno = tabla.getValueAt(indexFila, 1).toString().charAt(0);
                String sucursal = quitaEspacios(String.valueOf(tabla.getValueAt(indexFila, 2)));
                System.out.println("fecha: " + fecha);
                System.out.println("turno: " + turno);
                System.out.println("sucursal: " + sucursal);
                if(JOptionPane.showConfirmDialog(rootPane,"¿Seguro que desea eliminar el formato seleccionado?", "Eliminar", YES_NO_OPTION)==JOptionPane.OK_OPTION){
                    //buscar el indice de la fila que seleccionaste en el vector del formatos (el que tiene todos)
                    //tomar la fecha turno y sucursal del seleccionado
                    
                    for(int k = 0; k < Formatos.size(); k++){
                        if(fecha.equals(Formatos.elementAt(k).getFecha()) &&
                           turno == Formatos.elementAt(k).getTurno() &&
                           sucursal.equals(Formatos.elementAt(k).getSucursal())){
                           indexFila = k;
                           break;
                        }
                    }
                    //buscar el movimiento y preguntar si se desea eliminarlo
                    Vector<Movimiento> movs = Movimiento.CargarMovimientos();
                    int indiceMovEliminar = -1;
                    for (int i = 0; i < movs.size(); i++) {
                        if (movs.elementAt(i).getFecha().equals(Formatos.elementAt(indexFila).getFecha())
                                && movs.elementAt(i).getOrigen().equals(Formatos.elementAt(indexFila).getEntregador())
                                && movs.elementAt(i).getTurno() == Formatos.elementAt(indexFila).getTurno() 
                                && movs.elementAt(i).getDestino().equals(Formatos.elementAt(indexFila).getSucursal())) {
                            indiceMovEliminar = i;
                        }
                    }
                    if (indiceMovEliminar != -1) {
                        if(JOptionPane.showConfirmDialog(rootPane,"¿Desea eliminar el movimiento generado por este formato?", "Eliminar", YES_NO_OPTION)==JOptionPane.OK_OPTION){
                            try{
                                
                                //reducir el inventario del rutero
                                Vector<InventarioRutero> ruteros = InventarioRutero.cargarInvRuteros();
                                for (int i = 0; i < ruteros.size(); i++) {
                                    if (ruteros.elementAt(i).getRutero().equals(Formatos.elementAt(indexFila).getEntregador())) {
                                        ruteros.elementAt(i).aumentarExistencia(movs.elementAt( indiceMovEliminar ));
                                    }
                                }
                                InventarioRutero.actualizarBD( ruteros );
                                //eliminar del vector y actualizar archivo
                                movs.removeElementAt( indiceMovEliminar );                                
                                FileWriter out = new FileWriter("Archivos/Movimientos.data");
                                PrintWriter pw = new PrintWriter(out);
                                for(int k=0;k < movs.size();k++){
                                    pw.println(movs.elementAt(k).toString());
                                }
                                pw.close();
                                
                            } catch (IOException e){
                                
                            }
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "No se encontro Movimiento referente a este Formato", "Atención", JOptionPane.INFORMATION_MESSAGE);
                    }
                    try {
                       FileWriter out = new FileWriter("Archivos/Formatos.bin");
                       PrintWriter pw = new PrintWriter(out);
                       Formatos.removeElementAt(indexFila);
                       for(int k=0;k<Formatos.size();k++){
                           pw.println(Formatos.elementAt(k).toString());
                       }
                       pw.close();
                   } catch (IOException ex) {
                       JOptionPane.showMessageDialog(null, "Ocurrió un error con el archivo", "Error de Archvo",JOptionPane.ERROR_MESSAGE);
                   }
                    
                }
                //aplicar el filtro que corresponde
                btnFiltrarActionPerformed(null);
                }
                else{
                    JOptionPane.showMessageDialog(this,"Debe seleccionar una fila","Atencion",JOptionPane.ERROR_MESSAGE);
                }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed
    private void btnTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalActionPerformed
            if(tabla.getSelectedRow()!=-1){
                try{
                    //crear un objeto de tipo Total y mandar los totales de parametro
                    float totalVenta=0,totalFinal=0,retiro=0,faltante=0,ganancia=0,sobrante=0,comision=0;
                    int[] filasSeleccionadas= tabla.getSelectedRows();
                    for(int k=0; k<filasSeleccionadas.length; k++){
                        totalVenta+=(Float)tabla.getValueAt(filasSeleccionadas[k], 4);
                        retiro+=(float)tabla.getValueAt(filasSeleccionadas[k], 5);
                        faltante+=(float)tabla.getValueAt(filasSeleccionadas[k], 6);
                        sobrante+=(float)tabla.getValueAt(filasSeleccionadas[k],7);
                        ganancia+=Float.parseFloat(String.valueOf(tabla.getValueAt(filasSeleccionadas[k],8)));
                        comision+=Float.parseFloat(String.valueOf(tabla.getValueAt(filasSeleccionadas[k],9)));
                        totalFinal+=Float.parseFloat(tabla.getValueAt(filasSeleccionadas[k], 12).toString().substring(0,tabla.getValueAt(filasSeleccionadas[k], 12).toString().length()-1));
                    }
                    Total miTotal = new Total(totalVenta,retiro,faltante,totalFinal,ganancia,comision);
                    miTotal.setVisible(true);
                    miTotal.setLocationRelativeTo(this);
                }catch(NumberFormatException e){
                    JOptionPane.showMessageDialog(this,"Debe de tener en orden las columnas para totalizar, Number Format Exception","Atención",JOptionPane.ERROR_MESSAGE);
                }catch(ClassCastException e){
                    JOptionPane.showMessageDialog(this,"Debe de tener en orden las columnas para totalizar, classCastException","Atención",JOptionPane.ERROR_MESSAGE);                    
                }    
            }else{
                JOptionPane.showMessageDialog(this,"Debe Seleccionar una fila","Atención",JOptionPane.ERROR_MESSAGE);
            }
    }//GEN-LAST:event_btnTotalActionPerformed
    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        int indexFila=tabla.getSelectedRow();
        if(indexFila!=-1){
            if(JOptionPane.showConfirmDialog(rootPane,"¿Seguro de Marcar como pagado los formatos seleccionados?", "Pagar", YES_NO_OPTION)==0){
                int[] filas=tabla.getSelectedRows();
                for(int f=0;f<filas.length; f++){
                    for(int k=0; k<Formatos.size();k++){
                            if(filtrado.elementAt(filas[f]).getFecha().equals(Formatos.elementAt(k).getFecha()) &&
                               filtrado.elementAt(filas[f]).getTurno()==Formatos.elementAt(k).getTurno() &&
                               filtrado.elementAt(filas[f]).getSucursal().equals(Formatos.elementAt(k).getSucursal())){
                               Formatos.elementAt(k).setPagado(true);
                               break;
                            }
                        }
                }
                //actualizar en el archivo
                try {
                   FileWriter out = new FileWriter("Archivos/Formatos.bin");
                   PrintWriter pw = new PrintWriter(out);
                   for(int k=0;k<Formatos.size();k++){
                       pw.println(Formatos.elementAt(k).toString());
                   }
                   pw.close();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error con el archivo", "Error de Archvo",JOptionPane.ERROR_MESSAGE);
                }
                //filtrar
                btnFiltrarActionPerformed(null);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Debe de seleccionar una fila","Atencion",WIDTH);
        }    
    }//GEN-LAST:event_btnPagarActionPerformed
  
    private void btnFiltrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnFiltrarKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnFiltrarActionPerformed(null);
        }
    }//GEN-LAST:event_btnFiltrarKeyTyped
    private void btnEliminarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnEliminarActionPerformed(null);
        }
    }//GEN-LAST:event_btnEliminarKeyTyped
    private void btnMostrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnMostrarKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnMostrarActionPerformed(null);
        }
    }//GEN-LAST:event_btnMostrarKeyTyped
    private void btnTotalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnTotalKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnTotalActionPerformed(null);
        }
    }//GEN-LAST:event_btnTotalKeyTyped
    private void btnPagarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnPagarKeyTyped
       char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnPagarActionPerformed(null);
        }
    }//GEN-LAST:event_btnPagarKeyTyped
    private void btnCerrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCerrarKeyTyped
       char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnCerrarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCerrarKeyTyped
    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        //exportar a excel usar un exportador        
        String nombreArchivo=JOptionPane.showInputDialog(rootPane, "Ingrese el nombre del archivo a crear", "Ingresar Nombre",JOptionPane.INFORMATION_MESSAGE);
        
        if(nombreArchivo!=null){
            nombreArchivo = quitaDiagonal(nombreArchivo);  
            File archivo;
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(JFileChooser.APPROVE_OPTION==fc.showDialog(this,"Exportar")){
                archivo= new File(fc.getSelectedFile().toString().concat("/"+nombreArchivo+".xlsx"));                 
                new ExcelManager(archivo,tabla,"Formatos").exportar();
            try {
                Desktop.getDesktop().open(archivo);
            } catch (IOException ex) {
                Logger.getLogger(ConsultaFormato.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }
    }//GEN-LAST:event_btnExportarActionPerformed
    private void btnUtilidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUtilidadActionPerformed
        int fila = tabla.getSelectedRow(); //que este minimo una seleccinoada
        if (fila!=-1) {                    
            int filas[] = tabla.getSelectedRows();
            boolean valido=true;
            for (int i = 0; i < filas.length; i++) {
                if(filas[i]+1==tabla.getRowCount()){
                    JOptionPane.showMessageDialog(rootPane,"Seleccionó una fila inválida","Atencion",JOptionPane.WARNING_MESSAGE);
                    valido=false;
                }
            }
            if(valido){
                //crear varibales acomuladores de utilidad          
                Vector<Formato> seleccionados= new Vector<Formato>();
                for (int i = 0; i < filas.length; i++) {
                    seleccionados.add(filtrado.elementAt(filas[i]));
                }
                //crear objeto y lanzarlo
                UtilidadPorProducto upp = new  UtilidadPorProducto(seleccionados);
                upp.setVisible(true);
                upp.setLocationRelativeTo(this);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Debe seleccionar por lo menos una fila","Atencion",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnUtilidadActionPerformed
    private void btnUtilidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnUtilidadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btnUtilidadKeyTyped
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int indexFila=tabla.getSelectedRow();
        if(indexFila+1==tabla.getRowCount()){
            JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada inválida!", "Atención", JOptionPane.WARNING_MESSAGE );
        }else{
            
            if(indexFila != -1){
                String fecha = String.valueOf(tabla.getValueAt(indexFila, 0));
                char turno = tabla.getValueAt(indexFila, 1).toString().charAt(0);
                String sucursal = quitaEspacios(String.valueOf(tabla.getValueAt(indexFila, 2)));
                System.out.println("fecha: " + fecha);
                System.out.println("turno: " + turno);
                System.out.println("sucursal: " + sucursal);
                
                for(int k = 0; k < Formatos.size(); k++){
                    if(fecha.equals(Formatos.elementAt(k).getFecha()) &&
                       turno == Formatos.elementAt(k).getTurno() &&
                       sucursal.equals(Formatos.elementAt(k).getSucursal())){
                       indexFila = k;
                       break;
                    }
                }
                Filtro filtro;
                try{
                    String fechaIni = df.format(dFechaI.getDate());
                    String fechaFin = df.format(dFechaF.getDate());
                    filtro = new Filtro(cmbSucursal.getSelectedItem().toString(), cmbEncargado.getSelectedItem().toString(), cmbTurno.getSelectedItem().toString(), cmbPagado.getSelectedItem().toString(), fechaIni, fechaFin);
                }catch(NullPointerException e){ 
                        //en caso de que no existan fechas en los campos
                    filtro = new Filtro(cmbSucursal.getSelectedItem().toString(), cmbEncargado.getSelectedItem().toString(), cmbTurno.getSelectedItem().toString(), cmbPagado.getSelectedItem().toString());
                }
                
                //mostrar el objeto EditarFormato y esperar el retorno
                EditarFormato ef = new EditarFormato(Formatos, Formatos.elementAt(indexFila), filtro);
                ef.setVisible(true);
                ef.setLocationRelativeTo(this);
                this.dispose();
            }else{
                JOptionPane.showMessageDialog(this,"Debe seleccionar una fila","Atencion",JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed
    private void btnEditarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEditarKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarKeyTyped
    private void btnEntregasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEntregasKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEntregasKeyTyped

    private void btnEntregasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregasActionPerformed
        int fila = tabla.getSelectedRow(); //que este minimo una seleccinoada
        if (fila!=-1) {                    
            int filas[] = tabla.getSelectedRows();
            boolean valido=true;
            for (int i = 0; i < filas.length; i++) {
                if(filas[i]+1==tabla.getRowCount()){
                    JOptionPane.showMessageDialog(rootPane,"Seleccionó una fila inválida","Atencion",JOptionPane.WARNING_MESSAGE);
                    valido=false;
                }
            }
            if(valido){
                //crear varibales acomuladores de utilidad          
                Vector<Formato> seleccionados= new Vector<Formato>();
                for (int i = 0; i < filas.length; i++) {
                    seleccionados.add(filtrado.elementAt(filas[i]));
                }
                //crear objeto y lanzarlo
                ComprasPorProducto tpp = new ComprasPorProducto(seleccionados);
                tpp.setLocationRelativeTo(null);
                tpp.setVisible(true);
            }
        }else{
            JOptionPane.showMessageDialog(rootPane,"Debe seleccionar por lo menos una fila","Atencion",JOptionPane.WARNING_MESSAGE);
        }
        
        
    }//GEN-LAST:event_btnEntregasActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnEntregas;
    private javax.swing.JButton btnExportar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JButton btnTotal;
    private javax.swing.JButton btnUtilidad;
    private javax.swing.JComboBox cmbEncargado;
    private javax.swing.JComboBox cmbPagado;
    private javax.swing.JComboBox cmbSucursal;
    private javax.swing.JComboBox cmbTurno;
    private com.toedter.calendar.JDateChooser dFechaF;
    private com.toedter.calendar.JDateChooser dFechaI;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
