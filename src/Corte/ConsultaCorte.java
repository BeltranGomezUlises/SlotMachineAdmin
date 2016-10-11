package Corte;

import ControlUsuario.Loged;
import ControlUsuario.Usuario;
import static Corte.ConsultaCorte.ordenarCortesPorFecha;
import Excel.ImportarCortes;
import Maquinas.Maquina;
import Utilerias.Filtro;
import static Utilerias.Utileria.quitaEspacios;
import static Utilerias.Utileria.quitaGuion;
import java.io.File;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author user
 */
public class ConsultaCorte extends javax.swing.JFrame {
    
    String linea;/*Variable para la lectura de lineas de los archivos*/
    Vector<Corte> Cortes = new Vector<Corte>();
    Vector<String> sucursales= new Vector<String>();
    Vector<Corte> CortesFiltrados=new Vector<Corte>();
    Vector<String> nomMaquinas= new Vector<String>(); //vector que contiene los numero de maquinas de los cortes existentes
    Vector<float[]> historicos = new Vector<float[]>();    
    Vector<Integer> corte= new Vector<Integer>();
    String datos[][]={};
    String cabecera[]={"Fecha","Sucursal","#. Maquina","Disc.  Cont/Pant","%   Cont/Pant","Robo","Estatus","Retiro"};
    int anchos[]={33,50,30,85,85,20,10,33}; //cargar la tabla
    DefaultTableModel md;
    StringTokenizer token;
    DateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
    DecimalFormat decimalFormat = new DecimalFormat("0.0");
    
    Vector<Maquina> maquinas = Maquina.cargarMaquinas();    
    Vector<Maquina> maquinasFiltradas;
    
    public ConsultaCorte() {
        initComponents();
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoCreateRowSorter(true);        
        this.permisosUsiario();
        this.ajustarTabla();
        Cortes = Corte.cargarCortes();//para llenar el vector de cortes        
        this.llenarVectorNumMaquinas();
        this.llenarVectorSucursales();        
        this.llenarComboBoxSucursales(); 
        try {
            ordenarCortesPorFecha(Cortes,0,Cortes.size()-1);        
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No hay cortes para mostrar","Atención",JOptionPane.WARNING_MESSAGE);
        }   
        //hasta aqui todo bien 
        Cortes = this.ordenarCortesSucursalYMaquina(Cortes);                          
        this.asignarContadores(Cortes);
        //this.ordenarCortesMaquina();
        this.llenarHistoricos(Cortes);
        this.llenarTabla(Cortes);
        this.filaTotales(Cortes);
        
        CortesFiltrados=Cortes;
        
        
    }
      
    public ConsultaCorte(Filtro filtro){
        initComponents();        
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.setAutoCreateRowSorter(true);        
        this.permisosUsiario();
        this.ajustarTabla();
        Cortes = Corte.cargarCortes();//para llenar el vector de cortes
        this.llenarVectorNumMaquinas();
        this.llenarVectorSucursales();        
        this.llenarComboBoxSucursales(); 
        try {
            ordenarCortesPorFecha(Cortes,0,Cortes.size()-1);        
        } catch (ArrayIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No hay cortes para mostrar","Atención",JOptionPane.WARNING_MESSAGE);
        }                                         
        Cortes = this.ordenarCortesSucursalYMaquina(Cortes);
       
        this.asignarContadores(Cortes);
        this.llenarHistoricos(Cortes);
        this.llenarTabla(Cortes);
        this.filaTotales(Cortes);
        
        CortesFiltrados=Cortes; 
        
        //colorar los valores de filtro en los combos y mandar llamar filtrar
        cmbSucursal.setSelectedItem(filtro.getSucursal());
        cmbNumMaquina.setSelectedItem(filtro.getNumMaquina());
        cmbMes.setSelectedItem(filtro.getMes());
        cmbAño.setYear(filtro.getAño());
        
        btnFiltrarActionPerformed(null);
    }
    
     private void permisosUsiario(){
        btnEliminar.setEnabled(Loged.getLoged().isEliminarCorte());
        this.btnAsignarFallo.setEnabled(Loged.getLoged().isAsignarFalloCortes());
        this.btnImportar.setEnabled(Loged.getLoged().isImportarCortes());
        this.btnEditar.setEnabled(Loged.getLoged().isEditarCorte());
    }
            
    private void ajustarTabla(){
        md= new DefaultTableModel(datos,cabecera);
        tabla.setModel(md);
        for(int k=0; k<cabecera.length; k++){
              tabla.getColumnModel().getColumn(k).setPreferredWidth(anchos[k]); 
        }
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        DefaultTableCellRenderer tcr2 = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        tcr2.setHorizontalAlignment(SwingConstants.RIGHT);
        tabla.getColumnModel().getColumn(3).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(4).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(5).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(6).setCellRenderer(tcr);
        tabla.getColumnModel().getColumn(7).setCellRenderer(tcr2);
     } 
        
    public static void ordenarCortesPorFecha(Vector<Corte> cortes, int izq, int der){
            //este metodo ordena los cortes por fecha
            int i = izq; 
            int j = der; 
            Corte x = cortes.elementAt((izq + der) /2 ); 
            Corte aux;
            do{ 
                while( (cortes.elementAt(i).compareTo(x) < 0) && (j <= der) ){ 
                       i++;} 
                while( (cortes.elementAt(j).compareTo(x)>0) && (j > izq) ){ 
                       j--;} 
                if( i <= j ){                                        
                    aux = Corte.copy(cortes.elementAt(i));                                        
                    cortes.setElementAt(cortes.elementAt(j), i);
                    cortes.setElementAt(aux,j);
                            
                    i++;  j--; 
                } 
            }while( i <= j ); 
            if( izq < j ) 
                ordenarCortesPorFecha( cortes, izq, j ); 
            if( i < der ) 
                ordenarCortesPorFecha( cortes, i, der );                                         
    }
    
    public static void ordenarCortesPorMaquina(Vector<Corte> cortes, int izq, int der){
            //este metodo ordena los cortes por fecha
            int i = izq; 
            int j = der; 
            Corte x = cortes.elementAt((izq + der) /2 ); 
            Corte aux;
            do{ 
                while( (cortes.elementAt(i).getMaquina().getNombre().compareTo(x.getMaquina().getNombre()) < 0) && (j <= der) ){ 
                       i++;} 
                while( (cortes.elementAt(j).getMaquina().getNombre().compareTo(x.getMaquina().getNombre())>0) && (j > izq) ){ 
                       j--;} 
                if( i <= j ){                                        
                    aux = Corte.copy(cortes.elementAt(i));                                        
                    cortes.setElementAt(cortes.elementAt(j), i);
                    cortes.setElementAt(aux,j);
                            
                    i++;  j--; 
                } 
            }while( i <= j ); 
            if( izq < j ) 
                ordenarCortesPorMaquina( cortes, izq, j ); 
            if( i < der ) 
                ordenarCortesPorMaquina( cortes, i, der );                                         
     }
    
    private void ordenarCortesSucursal(){
        Vector<Corte> nuevoCortes = new Vector<Corte>();                
        for (int i = 0; i < sucursales.size(); i++) {
            for (int j = 0; j < Cortes.size(); j++) {
                if (Cortes.elementAt(j).getSucursal().equals(sucursales.elementAt(i))) {
                    nuevoCortes.add(Cortes.elementAt(j));
                }
            }
        }
        //terminado de llenar el vector nuevoCortes, referenciar el vector c a nuevoCortes
        Cortes = nuevoCortes;        
    }
    
    private Vector<Corte> ordenarCortesSucursalYMaquina(Vector<Corte> cortes){
        Vector<Corte> nuevoCortes = new Vector<Corte>();                
        Vector<Corte> cortesSucursal;
        Vector<String> fechasSucursal;
        for (int i = 0; i < sucursales.size(); i++){
            cortesSucursal = new Vector<Corte>();
            for (int j = 0; j < cortes.size(); j++){
                if (cortes.elementAt(j).getSucursal().equals(sucursales.elementAt(i))) {
                    cortesSucursal.add(cortes.elementAt(j));                    
                }
            }            
            //ya tengo lo cortes de la sucursal, previamente ordenados por fecha
            //ahora voy a sacar las fechas de esa sucursal                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
            fechasSucursal = new Vector<String>();            
            for (Corte corteSucursal : cortesSucursal) {
                if (!fechasSucursal.contains(corteSucursal.getFecha())) {
                    fechasSucursal.add(corteSucursal.getFecha());                    
                }
            }//con el vector de fechas, obtener un vector con los cortes de esa fecha y ordenarlo por numero de maquina
            // y simplemente agregar al vector final de cortes            
            for (String fecha : fechasSucursal) {                
                //System.out.println(fecha);
                Vector<Corte> cortesPorMaquina = new Vector<Corte>();               
                for (Corte c : cortesSucursal) {                    
                    if (c.getFecha().equals(fecha)) {
                        //System.out.println("        ->"+c.toString());
                        cortesPorMaquina.add(c);                
                    }
                }
                //con el vector de cortePorMaquina lleno, ordenar
                ConsultaCorte.ordenarCortesPorMaquina(cortesPorMaquina, 0, cortesPorMaquina.size() - 1);
                //ya ordenado agregar a nuevo corte
                for (Corte c: cortesPorMaquina){                    
                    nuevoCortes.add(c);
                }
            }            
        }
        //terminado de llenar el vector nuevoCortes, referenciar el vector c a nuevoCortes
        return nuevoCortes;        
    }
    
    private void ordenarCortesMaquina(){
        
    }
    
    private void llenarVectorNumMaquinas(){
        //llenar el vector de numeros de maquinas con las maquinas que existan en los formatos
        for(int k=0; k<Cortes.size(); k++){
            if(!nomMaquinas.contains(Cortes.elementAt(k).getMaquina().getNombre())){
               nomMaquinas.add(Cortes.elementAt(k).getMaquina().getNombre()); 
            }
        }
     }
    
    private void llenarVectorSucursales(){
        //llenar el vector de sucursales con las maquinas que existan en los formatos
        for(int k=0; k<Cortes.size(); k++){
            if(!sucursales.contains(Cortes.elementAt(k).getSucursal())){
               sucursales.add(Cortes.elementAt(k).getSucursal()); 
            }
        }
    }    
    
    private void llenarComboBoxSucursales(){
       cmbSucursal.addItem("Todos");
        for (int i = 0; i < sucursales.size(); i++) {
            cmbSucursal.addItem(quitaGuion(sucursales.elementAt(i)));
        } 
    }
    
    public static void asignarContadores(Vector<Corte> vCortes){
        Vector<String> vNomMaquinas= new Vector<String>();
        //llenar el vector de numeros de maquinas con las maquinas que existan en los formatos
        for(int k=0; k<vCortes.size(); k++){
            if(!vNomMaquinas.contains(vCortes.elementAt(k).getMaquina().getNombre())){
               vNomMaquinas.add(vCortes.elementAt(k).getMaquina().getNombre()); 
            }
        }
     //asignar los contadores anteriores a cada formato
        for(int k=0; k<vNomMaquinas.size();k++){
            Vector<Corte> maquinas= new Vector<Corte>();
            Vector<Integer> posiciones= new Vector<Integer>();
            for(int j=0; j < vCortes.size(); j++){
                if(vNomMaquinas.elementAt(k).equals(vCortes.elementAt(j).getMaquina().getNombre())){
                    maquinas.add(vCortes.elementAt(j));
                    posiciones.add(j);
                }
            }
            //asignar el contador de el corte anterior existente de esa misma maquina
            for(int j=0; j < maquinas.size()-1; j++){
//                if(vCortes.elementAt(posiciones.elementAt(j)).isFalloContador()){
//                    vCortes.elementAt(posiciones.elementAt(j+1)).setContAntPos(0);
//                    vCortes.elementAt(posiciones.elementAt(j+1)).setContAntNeg(0);
//                }else{                    
                    vCortes.elementAt(posiciones.elementAt(j+1)).setContAntPos(maquinas.elementAt(j).getContPos());
                    vCortes.elementAt(posiciones.elementAt(j+1)).setContAntNeg(maquinas.elementAt(j).getContNeg());    
//                }                
            }
            //asignar al primer corte de una maquina que no tiene corte anterior
            vCortes.elementAt(posiciones.elementAt(0)).setTieneCorteAnterior(false);
        }
     }
    
    private void llenarHistoricos(Vector<Corte> vCortes){
         //llenar los historicos, los promedios  de los valores del origen de los tiempos
         //historicos[0] -> InOut
         //historicos[1] -> discrepanciaCont
         //historicos[2] -> discrepanciaPant
         //historicos[3] -> porcentajeContador
         //historicos[4] -> porcentajePantalla        
        for(int j=0; j<nomMaquinas.size(); j++){
            float[] historico= new float[5];
            Arrays.fill(historico, 0);
            int[] cortes=new int[5];
            Arrays.fill(cortes, 0);
            for(int k=0; k<vCortes.size(); k++){
                if(vCortes.elementAt(k).getMaquina().getNombre().equals(nomMaquinas.elementAt(j))){
                    historico[0]+=vCortes.elementAt(k).getInOut();
                    cortes[0]++;
                    if(vCortes.elementAt(k).getContAntNeg() != 0 && vCortes.elementAt(k).getContAntPos() != 0
                            && !vCortes.elementAt(k).isFalloContador()){
                        historico[1]+=vCortes.elementAt(k).discrepanciaCont();
                        cortes[1]++;
                        historico[3]+=vCortes.elementAt(k).getPorcentajeContador();
                        cortes[3]++; 
                    }
                    if(!vCortes.elementAt(k).isFalloPantalla()){
                       historico[2]+=vCortes.elementAt(k).discrepanciaPant();
                        cortes[2]++;
                        historico[4]+=vCortes.elementAt(k).getPorcentajePantalla();
                        cortes[4]++; 
                    }
                }
            }
            if(cortes[0]>0){
                historico[0]=historico[0]/cortes[0];
            }
            if(cortes[1]>0){
                 historico[1]=historico[1]/cortes[1];
            }
            if(cortes[2]>0){
                historico[2]=historico[2]/cortes[2];
            }
            if(cortes[3]>0){
                 historico[3]=historico[3]/cortes[3];
            }
            if(cortes[4]>0){
                historico[4]=historico[4]/cortes[4];
            }
            historicos.add(historico);
         }   
     }
    
    private void llenarTabla(Vector<Corte> vCortes){
        DefaultTableModel myMd = (DefaultTableModel) tabla.getModel();
        myMd.setRowCount(0);
        for(int k=0; k<vCortes.size(); k++){
            Vector fila = new Vector();
            fila.add(vCortes.elementAt(k).getFecha());
            fila.add(quitaGuion(vCortes.elementAt(k).getSucursal()));
            fila.add(quitaGuion(vCortes.elementAt(k).getMaquina().getNombre()));
            if (vCortes.elementAt(k).isFalloPendiente()) { //si esta el fallo pendiente marcar asi
                fila.add("Pendiente");
                fila.add("Pendiente");
            }else{                            
                if(vCortes.elementAt(k).isFalloContador() && vCortes.elementAt(k).isFalloPantalla()){ //si contador y pantalla fallaron
                    fila.add("Falló    ||    Falló");
                    fila.add("Falló    ||    Falló");
                }else{
                    if(vCortes.elementAt(k).isFalloContador() && !vCortes.elementAt(k).isFalloPantalla()){ //si solo fallo contador
                        fila.add("Falló    ||    "+vCortes.elementAt(k).discrepanciaPant());
                        fila.add("Falló    ||    "+decimalFormat.format(vCortes.elementAt(k).getPorcentajePantalla())+"%");
                    }else{
                        if(!vCortes.elementAt(k).isFalloContador() && vCortes.elementAt(k).isFalloPantalla()){ //si solo fallo la pantalla
                            fila.add(vCortes.elementAt(k).discrepanciaCont()+"    ||    Falló");
                            fila.add(decimalFormat.format(vCortes.elementAt(k).getPorcentajeContador())+"%    ||    Falló");
                        }else{ //si no fallo ninguno de los 2
                            if(vCortes.elementAt(k).discrepanciaCont()==vCortes.elementAt(k).discrepanciaPant()){ //si son iguales mostrar una vez
                                fila.add(vCortes.elementAt(k).discrepanciaCont());
                            }else{
                                fila.add(vCortes.elementAt(k).discrepanciaCont()+"    ||    "+vCortes.elementAt(k).discrepanciaPant());
                            }
                            if(vCortes.elementAt(k).getPorcentajeContador()==vCortes.elementAt(k).getPorcentajePantalla()){
                                fila.add(decimalFormat.format(vCortes.elementAt(k).getPorcentajeContador())+"%");
                            }else{
                                fila.add(decimalFormat.format(vCortes.elementAt(k).getPorcentajeContador())+"%    ||    "+decimalFormat.format(vCortes.elementAt(k).getPorcentajePantalla())+"%");
                            }
                        }
                    }
                }
            }
            fila.add(String.valueOf(vCortes.elementAt(k).getRobo()));
            String solucionado="";
            if (vCortes.elementAt(k).isSolucionado()) {
                solucionado = "OK";
            }else{
                solucionado = "PTE";
            }
            fila.add(solucionado);
            fila.add("$"+vCortes.elementAt(k).getInOut());
            myMd.addRow(fila);
        }
     }
    
    private void filaTotales(Vector<Corte> vCortes){
        //sacar los totales de retiros, faltante y porcentajes para ponerlos al final en una fila nueva           
        int corte=0,discrepanciaCont=0,discrepanciaPant=0;
        float porcentajeCont=0, porcentajePant=0;
        int contPorCont=0, contPorPant=0, robo = 0;
        for( int k=0; k<vCortes.size(); k++){
            corte+=vCortes.elementAt(k).getInOut();
            robo += vCortes.elementAt(k).getRobo();
            if(!vCortes.elementAt(k).isFalloContador() && vCortes.elementAt(k).getContAntPos()!=0
                    && vCortes.elementAt(k).getContAntNeg()!=0){
                porcentajeCont+=vCortes.elementAt(k).getPorcentajeContador();
                contPorCont++;
                discrepanciaCont+=vCortes.elementAt(k).discrepanciaCont();
            }
            if(!vCortes.elementAt(k).isFalloPantalla()){
                porcentajePant+=vCortes.elementAt(k).getPorcentajePantalla();
                contPorPant++;
                discrepanciaPant+=vCortes.elementAt(k).discrepanciaPant();
            }
        }
            md.addRow(new Vector());
            int filaFinal = md.getRowCount()-1;
            md.setValueAt("TOTALES:", filaFinal, 2);            
            md.setValueAt(discrepanciaCont+"    ||    "+discrepanciaPant, filaFinal, 3);
            md.setValueAt(decimalFormat.format(porcentajeCont/contPorCont)+"%    ||    "+decimalFormat.format(porcentajePant/contPorPant)+"%", filaFinal, 4);
            md.setValueAt("$" + robo, filaFinal, 5);
            md.setValueAt("$"+corte, filaFinal, 7);
     } 
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbAño = new com.toedter.calendar.JYearChooser();
        cmbSucursal = new javax.swing.JComboBox();
        cmbNumMaquina = new javax.swing.JComboBox();
        cmbMes = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmbFallo = new javax.swing.JComboBox<String>();
        jLabel6 = new javax.swing.JLabel();
        cmbEstatus = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jPanel2 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        btnFiltrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnAsignarFallo = new javax.swing.JButton();
        btnConcentrar = new javax.swing.JButton();
        btnImportar = new javax.swing.JButton();

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Filtros"));

        jLabel1.setText("Sucursal:");

        jLabel2.setText("Numero Maquina:");

        jLabel3.setText("Fecha:");

        cmbSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSucursalActionPerformed(evt);
            }
        });

        cmbMes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));

        jLabel4.setText("Fallo:");

        cmbFallo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ningúno", "Contador", "Descompuesto", "Pantalla", "Pendiente", "Robo", "VueltaContPos", "VueltaContNeg" }));

        jLabel6.setText("Estatus:");

        cmbEstatus.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ningúno", "OK", "PTE" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbNumMaquina, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(3, 3, 3)
                        .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbFallo, 0, 113, Short.MAX_VALUE)
                    .addComponent(cmbEstatus, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbAño, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(cmbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cmbFallo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cmbNumMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(cmbEstatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

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

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/filtrarFinal.png"))); // NOI18N
        btnFiltrar.setText("    Filtrar      ");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eliminarFinal.png"))); // NOI18N
        btnEliminar.setText("  Eliminar     ");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MostrarFinal.png"))); // NOI18N
        btnMostrar.setText(" Mostrar      ");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/editar.png"))); // NOI18N
        btnEditar.setText("  Editar         ");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnAsignarFallo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/fallo.png"))); // NOI18N
        btnAsignarFallo.setText("Asginar Fallo");
        btnAsignarFallo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsignarFalloActionPerformed(evt);
            }
        });

        btnConcentrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/concentrado.png"))); // NOI18N
        btnConcentrar.setText(" Concentrar  ");
        btnConcentrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConcentrarActionPerformed(evt);
            }
        });

        btnImportar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/excel_final.png"))); // NOI18N
        btnImportar.setText("  Importar     ");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnImportar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnConcentrar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                            .addComponent(btnEditar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAsignarFallo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAsignarFallo, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConcentrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImportar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSucursalActionPerformed
        String suc = quitaEspacios(cmbSucursal.getSelectedItem().toString());        
        maquinasFiltradas= new Vector<Maquina>();
        if (suc.equals("Todos")) {
            cmbNumMaquina.removeAllItems();
            cmbNumMaquina.addItem("Todos");
            for (int i = 0; i < maquinas.size(); i++) {
                maquinasFiltradas.add(maquinas.elementAt(i));//llenar el vector de maquinas filtradas
                cmbNumMaquina.addItem(maquinasFiltradas.elementAt(i).getNombre());//llenar el cmbBox de maquinas
            }
        }else{
            for (int i = 0; i < maquinas.size(); i++) { //llenar masquinasFiltradas de maquinas con la sucursal actual
                if (maquinas.elementAt(i).getSucursal().getNombre().equals(suc)) {
                    maquinasFiltradas.add(maquinas.elementAt(i));
                }
            }
            cmbNumMaquina.removeAllItems();
            cmbNumMaquina.addItem("Todos");
            for (int i = 0; i < maquinasFiltradas.size(); i++) {
                cmbNumMaquina.addItem(maquinasFiltradas.elementAt(i).getNombre());
            }
        }
    }//GEN-LAST:event_cmbSucursalActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        CortesFiltrados= new Vector<Corte>(Cortes);
        
        String sucursal = quitaEspacios(cmbSucursal.getSelectedItem().toString());
        //remover los que no correspondan a la sucursal
        if(!sucursal.equals("Todos")){
            int k=0;
            while(k<CortesFiltrados.size()){
                if(!CortesFiltrados.elementAt(k).getSucursal().equals(sucursal)){
                    CortesFiltrados.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
//        remover los que no correpondan al numero de maquina
        if(!cmbNumMaquina.getSelectedItem().toString().equals("Todos")){
            String nomMaquina=quitaEspacios(cmbNumMaquina.getSelectedItem().toString());
            int k=0;
            while(k<CortesFiltrados.size()){
                if(!CortesFiltrados.elementAt(k).getMaquina().getNombre().equals(nomMaquina)){
                    CortesFiltrados.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        //remover los que no correspondan al fallo seleccionado
        if (!cmbFallo.getSelectedItem().toString().equals("Ningúno")) {
            String fallo = quitaEspacios(cmbFallo.getSelectedItem().toString());
            int k=0;
            while(k<CortesFiltrados.size()){
                switch (fallo) {
                    case "Contador":
                         if(!CortesFiltrados.elementAt(k).isFalloContador())
                                CortesFiltrados.removeElementAt(k);
                         else k++;                            
                        break;
                    case "Pantalla":
                        if(!CortesFiltrados.elementAt(k).isFalloPantalla())
                                CortesFiltrados.removeElementAt(k);
                         else k++;  
                        break;
                    case "Robo":
                        if(!CortesFiltrados.elementAt(k).isFalloRobo())
                                CortesFiltrados.removeElementAt(k);
                         else k++; 
                        break;
                    case "Pendiente":
                        if(!CortesFiltrados.elementAt(k).isFalloPendiente())
                                CortesFiltrados.removeElementAt(k);
                         else k++; 
                        break;
                    case "VueltaContPos":
                        if(!CortesFiltrados.elementAt(k).isVueltaContadorPos())
                                CortesFiltrados.removeElementAt(k);
                         else k++; 
                        break;
                    case "VueltaContNeg":
                        if(!CortesFiltrados.elementAt(k).isVueltaContadorNeg())
                                CortesFiltrados.removeElementAt(k);
                         else k++; 
                        break;                    
                    case "Descompuesto":
                        if(!CortesFiltrados.elementAt(k).isFalloDescompuesto())
                                CortesFiltrados.removeElementAt(k);
                         else k++; 
                        break;                    
                }               
            }
        }
        //filtrar por estatos
        if(!cmbEstatus.getSelectedItem().toString().equals("Ningúno")){
            String estatus = quitaEspacios(cmbEstatus.getSelectedItem().toString());
            int k=0;
            while(k<CortesFiltrados.size()){
                switch (estatus){
                    case "OK":
                        if (!CortesFiltrados.elementAt(k).isSolucionado())
                            CortesFiltrados.removeElementAt(k);
                        else k++;                        
                        break;
                    case "PTE":
                         if (CortesFiltrados.elementAt(k).isSolucionado())
                            CortesFiltrados.removeElementAt(k);
                        else k++; 
                        break;
                }
            }
        }
        //remover los que no correspondan al mes y al año
        if(!cmbMes.getSelectedItem().toString().equals("Todos")){
            //sacar el el numero segun el mes
            int mes=0;
            switch (cmbMes.getSelectedItem().toString()){
                case "Enero":mes=0; break;
                case "Febrero": mes=1;break;
                case "Marzo": mes=2;break;
                case "Abril": mes=3;break;
                case "Mayo": mes=4;break;
                case "Junio": mes=5;break;
                case "Julio": mes=6;break;
                case "Agosto": mes=7;break;
                case "Septiembre": mes=8;break;
                case "Octubre": mes=9;break;
                case "Noviembre": mes=10;break;
                case "Diciembre": mes=11;break;
                    
            }
            int c=0;
            while(c<CortesFiltrados.size()){
                try {
                    Date date= dateFormat.parse(CortesFiltrados.elementAt(c).getFecha());                
                    if(date.getMonth()!=mes){
                        CortesFiltrados.removeElementAt(c);
                    }else{
                        c++;
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(rootPane,"Error de parse de mes","Error",JOptionPane.ERROR_MESSAGE);
                }
            } 
        }
        
        int a=0;
        while(a<CortesFiltrados.size()){
            try {
                Date date= dateFormat.parse(CortesFiltrados.elementAt(a).getFecha());
                if(date.getYear()+1900!=cmbAño.getYear()){
                    CortesFiltrados.removeElementAt(a);
                }else{
                    a++;
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(rootPane,"Error de parse de año","Error",JOptionPane.ERROR_MESSAGE);
            }
            
        }
        //this.ajustarTabla();
        this.llenarTabla(CortesFiltrados);
        //sacar los totales de retiros, faltante y porcentajes paraa ponerlos al final en una fila nueva
        this.filaTotales(CortesFiltrados);
        
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int indexFila=tabla.getSelectedRow();
        if(indexFila+1==tabla.getRowCount()){
            JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada invalida!", "Atención", JOptionPane.WARNING_MESSAGE );
        }else{
            if(indexFila!=-1){
                //tomar los valores del corte de la tabla
                String fecha = tabla.getValueAt(indexFila, 0).toString();
                String sucursal = quitaEspacios(tabla.getValueAt(indexFila, 1).toString());
                String maquina = tabla.getValueAt(indexFila, 2).toString();
                System.out.println("fecha: " + fecha);
                System.out.println("sucursal: " + sucursal);
                System.out.println("maquina: " + maquina);
                if(JOptionPane.showConfirmDialog(rootPane,"¿Seguro que desea eliminar el corte seleccionado?", "Eliminar", YES_NO_OPTION) == JOptionPane.YES_OPTION){
                    //tomar los valores del corte de la tabla                    
                    //buscar el indice de la fila que seleccionaste en el vector de Cortes (el que tiene todos)                     
                    for(int k=0; k<Cortes.size();k++){
                        if(fecha.equals(Cortes.elementAt(k).getFecha()) 
                                && sucursal.equals(Cortes.elementAt(k).getSucursal())
                                && maquina.equals(Cortes.elementAt(k).getMaquina().getNombre())){
                         indexFila = k;
                         break;
                        }
                    }                    
                    Cortes.removeElementAt(indexFila);
                    Corte.ActualizarBD(Cortes);
                    //Corte.ActualizarBD(Cortes);                   
                    btnFiltrarActionPerformed(null);
                }
            }else{
                JOptionPane.showMessageDialog(this,"Debe seleccionar una fila","Atencion",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        int index=tabla.getSelectedRow();
        if(index+1==tabla.getRowCount()){
            JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada invalida!", "Atención", JOptionPane.WARNING_MESSAGE );
        }else{
            if(index!=-1){
                try{
                    int[] filas=tabla.getSelectedRows();
                    for(int k=0; k<filas.length; k++){
                          CCorte miCCorte= new CCorte(CortesFiltrados.elementAt(filas[k]));
                          miCCorte.setVisible(true);
                          miCCorte.setLocationRelativeTo(this);
                          //buscar la posicion de la maquina en el vector y asignar el historico de esa posicion
                          int pos=0;
                          for(int j=0; j<nomMaquinas.size(); j++){
                              if(CortesFiltrados.elementAt(filas[k]).getMaquina().getNombre().equals(nomMaquinas.elementAt(j))){
                                  pos=j;break;
                              }
                          }
                          miCCorte.setHistoricos(historicos.elementAt(pos));
                    }   
                }catch(ArrayIndexOutOfBoundsException e){
                    JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada invalida!", "Atención", JOptionPane.WARNING_MESSAGE );
                }
            }else{
                JOptionPane.showMessageDialog(rootPane,"Debe de tener una fila Seleccionada", "Atecion", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnAsignarFalloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsignarFalloActionPerformed
         int indexFila=tabla.getSelectedRow();
        if(indexFila+1==tabla.getRowCount()){
            JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada invalida!", "Atención", JOptionPane.WARNING_MESSAGE );
        }else{
            if(indexFila!=-1){
                    for(int k=0; k<Cortes.size();k++){
                        if(CortesFiltrados.elementAt(indexFila).getFecha().equals(Cortes.elementAt(k).getFecha()) &&
                            CortesFiltrados.elementAt(indexFila).getMaquina().getNombre().equals(Cortes.elementAt(k).getMaquina().getNombre()) &&
                            CortesFiltrados.elementAt(indexFila).getSucursal().equals(Cortes.elementAt(k).getSucursal())){
                            
                            Filtro filtro = new Filtro(cmbSucursal.getSelectedItem().toString(), cmbNumMaquina.getSelectedItem().toString(), cmbMes.getSelectedItem().toString(), cmbAño.getYear());
                            
                            AsignarFallo af = new AsignarFallo(Cortes, k, filtro);
                            af.setVisible(true);
                            af.setLocationRelativeTo(this);
                            this.dispose();
                            break;
                        }
                    } 
            }else{
                JOptionPane.showMessageDialog(this,"Debe seleccionar una fila","Atencion",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAsignarFalloActionPerformed

    private void btnConcentrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConcentrarActionPerformed
        SeleccionarMes sm = new SeleccionarMes(Cortes);
        sm.setVisible(true);
        sm.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnConcentrarActionPerformed

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        File archivo=null;
        JFileChooser fc= new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new FileNameExtensionFilter("Archivo Excel","xlsx","xls"));
        fc.setCurrentDirectory(new File("C:/"));
        if(JFileChooser.APPROVE_OPTION==fc.showDialog(this, "seleccionar")){
            archivo=fc.getSelectedFile();
            ImportarCortes imp= new ImportarCortes(archivo, Cortes);
            imp.setVisible(true);
            imp.setLocationRelativeTo(this);
            this.dispose();
        }  
    }//GEN-LAST:event_btnImportarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int indexFila=tabla.getSelectedRow();
        if(indexFila+1==tabla.getRowCount()){
            JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada invalida!", "Atención", JOptionPane.WARNING_MESSAGE );
        }else{
             if(indexFila!=-1){           
                    //tomar los valores del corte de la tabla
                    String fecha = tabla.getValueAt(indexFila, 0).toString();
                    String sucursal = quitaEspacios(tabla.getValueAt(indexFila, 1).toString());
                    String maquina = tabla.getValueAt(indexFila, 2).toString();
                    //buscar el indice de la fila que seleccionaste en el vector de Cortes (el que tiene todos)                     
                    for(int k=0; k<Cortes.size();k++){
                        if(fecha.equals(Cortes.elementAt(k).getFecha()) 
                                && sucursal.equals(Cortes.elementAt(k).getSucursal())
                                && maquina.equals(Cortes.elementAt(k).getMaquina().getNombre())){
                         indexFila = k;
                         break;
                        }
                    }           
                    //crear el filtro que corresponde y mandarlo a editarCorte
                    Filtro filtro = new Filtro(cmbSucursal.getSelectedItem().toString(), cmbNumMaquina.getSelectedItem().toString(), cmbMes.getSelectedItem().toString(), cmbAño.getYear());
                    //ya con el indice que le corresponde mandar a editar
                    EditarCorte ec = new EditarCorte(Cortes.elementAt(indexFila), Cortes, filtro);
                    ec.setLocationRelativeTo(null);
                    ec.setVisible(true);
                    btnCerrarActionPerformed(null);
            }else{
                JOptionPane.showMessageDialog(this,"Debe seleccionar una fila","Atencion",JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAsignarFallo;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnConcentrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnImportar;
    private javax.swing.JButton btnMostrar;
    private com.toedter.calendar.JYearChooser cmbAño;
    private javax.swing.JComboBox cmbEstatus;
    private javax.swing.JComboBox<String> cmbFallo;
    private javax.swing.JComboBox cmbMes;
    private javax.swing.JComboBox cmbNumMaquina;
    private javax.swing.JComboBox cmbSucursal;
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
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
