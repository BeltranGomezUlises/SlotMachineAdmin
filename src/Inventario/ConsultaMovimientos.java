
package Inventario;

import ControlUsuario.Usuario;
import Productos.Producto;
import static Utilerias.Utileria.*;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class ConsultaMovimientos extends javax.swing.JFrame {
    
    Vector<Movimiento> movimientos= new Vector<Movimiento>();
    Vector<Movimiento> filtrado;
    Vector<String> ruteros, sucursales;
    int[] anchosTabla={30,30,30,30};
    
    public ConsultaMovimientos() {
        initComponents();                
        movimientos = Movimiento.CargarMovimientos();
        filtrado = movimientos;
        //generar un thread para paralelizar el ordenamiento con el llenado de los cmbBox
        LlenarComboBox c1 = new LlenarComboBox("proceso 1");    c1.start();   
        
        try {
            quickSort(movimientos,0,movimientos.size()-1);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Sin Movimientos para Mostrar ó Error de lectura de Movimientos","Atención",JOptionPane.WARNING_MESSAGE);
        }
        
        
        
        ajustarTamañosTabla(tabla,anchosTabla);
        this.llenarTabla(movimientos);
        /*
        try {
           FileWriter out = new FileWriter("Archivos/movimientos.data");
           PrintWriter pw = new PrintWriter(out);           
           for(int k=0;k<movimientos.size();k++){
               pw.println(movimientos.elementAt(k).toString());
           }
           pw.close();
       } catch (IOException ex) {
           JOptionPane.showMessageDialog(null, "Ocurrió un error con el archivo", "Error de Archvo",JOptionPane.ERROR_MESSAGE);
       }*/
        
    }

    private void filtrar() {
        filtrado = new Vector<Movimiento>();
        
        for (int i = 0; i < movimientos.size(); i++) {//copiar el vector de movimientos a nuevo para filtrar            
            filtrado.add(movimientos.elementAt(i));            
        }
        
        String rutero = quitaEspacios((String)cmbRutero.getSelectedItem());
        String sucursal = quitaEspacios((String)cmbSucursal.getSelectedItem());
        String tipo = quitaEspacios((String)cmbTipo.getSelectedItem());        
        //Filtrar por fecha   
        //DateFormat sdf = DateFormat.getDateInstance();
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try{
            int k=0;
            Date fechaI=sdf.parse(sdf.format(dateChooserI.getDate()));            
            Date fechaF=sdf.parse(sdf.format(dateChooserF.getDate()));            
            while(k<filtrado.size()){
                String fecha=filtrado.elementAt(k).getFecha();
                Date fechaFormato = sdf.parse(fecha);                                  
                if(fechaFormato.compareTo(fechaI)<0 || fechaFormato.compareTo(fechaF)>0){
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
        
        //filtrado por rutero
        if(!"Todos".equals(rutero)){
            int k=0;
            while(k<filtrado.size()){
                if(!filtrado.elementAt(k).getOrigen().equals(rutero) && !filtrado.elementAt(k).getDestino().equals(rutero)){
                    filtrado.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        
        //filtrado por Sucursal
        if(!"Todos".equals(sucursal)){
            int k=0;
            while(k<filtrado.size()){
                if(!filtrado.elementAt(k).getOrigen().equals(sucursal) && !filtrado.elementAt(k).getDestino().equals(sucursal)){
                    filtrado.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        //filtrado por tipo        
        if(!tipo.equals("Todos")){
            int k=0;
            while(k<filtrado.size()){
                if(!filtrado.elementAt(k).getTipo().equals(tipo)){
                    filtrado.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        
        //llenar tabla
        this.llenarTabla(filtrado);                
    }
    
    private class LlenarComboBox extends Thread{
        LlenarComboBox(String nombre){
            super(nombre);
        }
        @Override
        public void run(){
            try {
                //Para cmbRutero
                ruteros = new Vector<String>();
                FileReader fr = new FileReader("Archivos/Ruteros.data");
                BufferedReader br = new BufferedReader(fr);
                String linea;
                StringTokenizer token;
                while((linea=br.readLine())!=null){
                    token= new StringTokenizer(linea);
                    ruteros.add(quitaGuion(token.nextToken()));
                }
                cmbRutero.addItem("Todos");
                for (int i = 0; i < ruteros.size(); i++) {
                    cmbRutero.addItem(ruteros.elementAt(i));
                }
                //para cmbSucursales
                sucursales = new Vector<String>();
                FileReader fr1 = new FileReader("Archivos/Sucursales.bin");
                BufferedReader br1 = new BufferedReader(fr1);                
                while((linea=br1.readLine())!=null){
                    token= new StringTokenizer(linea);
                    sucursales.add(quitaGuion(token.nextToken()));
                }
                cmbSucursal.addItem("Todos");
                for (int i = 0; i < sucursales.size(); i++) {
                    cmbSucursal.addItem(sucursales.elementAt(i));
                }                          
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error de llenado de comboBox", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            
        }
    } 
    
    public static void quickSort(Vector<Movimiento> movs,int izq, int der) {                                                              
            int i = izq; 
            int j = der; 
            Movimiento x = movs.elementAt((izq + der) /2 ); 
            do{ 
                while( (movs.elementAt(i).compareTo(x) < 0) && (j <= der) ){ 
                       i++;} 
                while( (movs.elementAt(j).compareTo(x) > 0) && (j > izq) ){ 
                       j--;} 
                if( i <= j ){ 
                    Movimiento aux = Movimiento.copy(movs.elementAt(i));
                    movs.setElementAt(movs.elementAt(j), i);
                    movs.setElementAt(aux, j); 
                    i++;  j--; 
                } 
            }while( i <= j ); 
            if( izq < j ) 
                quickSort( movs, izq, j ); 
            if( i < der ) 
                quickSort( movs, i, der ); 
                                   
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
        jPanel1 = new javax.swing.JPanel();
        btnFiltrar = new javax.swing.JButton();
        btnBorrar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnTotales = new javax.swing.JButton();
        btnImportarMovimientos = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbRutero = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cmbSucursal = new javax.swing.JComboBox();
        jPanel3 = new javax.swing.JPanel();
        dateChooserI = new com.toedter.calendar.JDateChooser();
        dateChooserF = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Movimientos");
        setResizable(false);

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

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/filtrarFinal.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnBorrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eliminarFinal.png"))); // NOI18N
        btnBorrar.setText("Borrar");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/MostrarFinal.png"))); // NOI18N
        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnTotales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/suma.png"))); // NOI18N
        btnTotales.setText("Totales");
        btnTotales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalesActionPerformed(evt);
            }
        });

        btnImportarMovimientos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/excel_final.png"))); // NOI18N
        btnImportarMovimientos.setText("Importar");
        btnImportarMovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarMovimientosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnImportarMovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(btnTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImportarMovimientos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Filtros"));

        jLabel2.setText("Rutero:");

        cmbRutero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuteroActionPerformed(evt);
            }
        });

        jLabel3.setText("Tipo:");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Entrega Inventario", "Entrega Rutero", "Entrega Sucursal", "Merma", "Perdida", "Ajuste" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        jLabel4.setText("Sucursal:");

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("Fecha Inicial:");

        jLabel6.setText("Fecha Final:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserF, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateChooserI, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(dateChooserI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooserF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(2, 2, 2))))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cmbRutero, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbSucursal, 0, 106, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cmbRutero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        this.filtrar();
    }//GEN-LAST:event_btnFiltrarActionPerformed
    private void cmbRuteroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuteroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuteroActionPerformed
    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
        int indexFila = tabla.getSelectedRow();
        Movimiento movEliminar;
        
        if(indexFila!=-1){
            if(JOptionPane.showConfirmDialog(rootPane,"¿Seguro que desea eliminar el Movimiento seleccionado?", "Eliminar", YES_NO_OPTION)==JOptionPane.OK_OPTION){
                //buscar el indice de la fila que seleccionaste en el vector del formatos (el que tiene todos)
                for(int k=0; k<movimientos.size();k++){
                    if(filtrado.elementAt(indexFila).getFecha().equals(movimientos.elementAt(k).getFecha()) &&
                       filtrado.elementAt(indexFila).getTipo().equals(movimientos.elementAt(k).getTipo()) &&
                       filtrado.elementAt(indexFila).getOrigen().equals(movimientos.elementAt(k).getOrigen()) &&
                       filtrado.elementAt(indexFila).getDestino().equals(movimientos.elementAt(k).getDestino())){
                      indexFila=k;
                      break;
                    }
                }
                try {
                   movEliminar = movimientos.elementAt(indexFila);
                   FileWriter out = new FileWriter("Archivos/Movimientos.data");
                   PrintWriter pw = new PrintWriter(out);
                   //before removing the movement of the vector, we should return the effect that it has in the inventary
                   Vector<InventarioRutero> inventarioRuteros = InventarioRutero.cargarInvRuteros();                   
                   Inventario inventarioGeneral = new Inventario(); // if is the case to use it
                   Vector<Producto> productosArchivo = Producto.cargarProductos();
                   switch(movEliminar.getTipo()){
                       case "Entrega_Rutero":
                           //look up the rutero and less it the products
                           for (int i = 0; i < inventarioRuteros.size(); i++) {
                               if (inventarioRuteros.elementAt(i).getRutero().equals(movEliminar.getDestino())) {                                   
                                   inventarioRuteros.elementAt(i).reducirExistencia( movEliminar );
                               }
                           }
                           inventarioGeneral.aumentarExistencia( movEliminar );                                   
                                                      
                           InventarioRutero.actualizarBD(inventarioRuteros);                           
                           inventarioGeneral.actualizarBD(); 
                           break;
                       case "Entrega_Inventario":                                                      
                            inventarioGeneral.reducirExistencia( movEliminar );
                            inventarioGeneral.actualizarBD();
                            break;
                       case "Merma": 
                           if (movEliminar.getDestino().equals("Inventario")) {                               
                                inventarioGeneral.aumentarExistencia( movEliminar );                               
                                inventarioGeneral.actualizarBD();
                           }else{
                                //look up the rutero and less it the products
                                for (int i = 0; i < inventarioRuteros.size(); i++) {
                                    if (inventarioRuteros.elementAt(i).getRutero().equals(movEliminar.getDestino())) {                                        
                                        inventarioRuteros.elementAt(i).aumentarExistencia( movEliminar );
                                    }
                                }
                                //update rutero file
                                InventarioRutero.actualizarBD(inventarioRuteros); 
                           }                           
                           break;
                       case "Perdida":
                           if (movEliminar.getDestino().equals("Inventario")) {                               
                               inventarioGeneral.aumentarExistencia(movEliminar);                               
                               inventarioGeneral.actualizarBD();
                           }else{
                                //look up the rutero and less it the products
                                for (int i = 0; i < inventarioRuteros.size(); i++) {
                                    if (inventarioRuteros.elementAt(i).getRutero().equals(movEliminar.getDestino())) {                                        
                                        inventarioRuteros.elementAt(i).aumentarExistencia(movEliminar);                                        
                                    }
                                }
                                //update rutero file
                                InventarioRutero.actualizarBD(inventarioRuteros); 
                           }     
                           break;
                       case "Entrega_Sucursal":
                           //look up the rutero and less it the products                           
                            for (int i = 0; i < inventarioRuteros.size(); i++) {
                                if (inventarioRuteros.elementAt(i).getRutero().equals( movEliminar.getOrigen() ) ) {                                        
                                    inventarioRuteros.elementAt(i).aumentarExistencia( movEliminar );                                       
                                }
                            }
                            //update rutero file
                            InventarioRutero.actualizarBD(inventarioRuteros); 
                           break;
                       case "Ajuste":
                           if (movEliminar.getDestino().equals("Inventario")) {                              
                                inventarioGeneral.reducirExistencia(movEliminar);                               
                                inventarioGeneral.actualizarBD();
                           }else{
                                //look up the rutero and less it the products
                                for (int i = 0; i < inventarioRuteros.size(); i++) {
                                    if (inventarioRuteros.elementAt(i).getRutero().equals(movEliminar.getDestino())) {
                                        inventarioRuteros.elementAt(i).reducirExistencia(movEliminar);
                                    }
                                }
                                //update rutero file
                                InventarioRutero.actualizarBD(inventarioRuteros); 
                           }   
                           break;
                   }
                   movimientos.removeElementAt(indexFila);
                   for(int k=0;k<movimientos.size();k++){
                       pw.println(movimientos.elementAt(k).toString());
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
    }//GEN-LAST:event_btnBorrarActionPerformed
    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        //sacar el indice y mostrar
        int fila= tabla.getSelectedRow();
        if(fila!=-1){
            try{
                int[] filas=tabla.getSelectedRows();
                for(int k=0; k<filas.length;k++){
                    MostrarMovimiento mf= new MostrarMovimiento(filtrado.elementAt(filas[k]));
                    mf.setVisible(true);
                    mf.setLocationRelativeTo(this);                                          
                }  
            }catch(ArrayIndexOutOfBoundsException e){
                JOptionPane.showMessageDialog(rootPane, "   ¡Fila Seleccionada inválida!", "Atención", JOptionPane.WARNING_MESSAGE );
                e.printStackTrace();
            }
        }
        else{
             JOptionPane.showMessageDialog(this,"Debe seleccionar una fila","Atención",JOptionPane.ERROR_MESSAGE);
        }       
    }//GEN-LAST:event_btnMostrarActionPerformed
    private void btnTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalesActionPerformed
        Vector<Movimiento> movs = new Vector<Movimiento>();
        for (int i= 0; i < movimientos.size(); i++) {
            movs.add(movimientos.elementAt(i));
        }
        TotalesMovimientos te = new TotalesMovimientos (movs,ruteros,sucursales);
        te.setVisible(true);
        te.setLocationRelativeTo(this);
        
    }//GEN-LAST:event_btnTotalesActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnImportarMovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarMovimientosActionPerformed
        File archivo=null;
        JFileChooser fc= new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setFileFilter(new FileNameExtensionFilter("Archivo Excel","xlsx","xls"));        
        if(JFileChooser.APPROVE_OPTION==fc.showDialog(this, "seleccionar")){
            archivo=fc.getSelectedFile();            
            ImportarMovimientos im = new ImportarMovimientos(archivo, movimientos);
            im.setLocationRelativeTo(null);
            im.setVisible(true);
            this.dispose();
        }                 
    }//GEN-LAST:event_btnImportarMovimientosActionPerformed
   
    private void llenarTabla(Vector<Movimiento> VMov){
        String cabecera[]={"Fecha","Tipo","Origen","Destino"};
        String datos[][]={};
        DefaultTableModel md = new DefaultTableModel(datos,cabecera);
        Vector fila;
        for (int i = 0; i < VMov.size(); i++) {
            fila= new Vector();
            fila.add(VMov.elementAt(i).getFecha());            
            fila.add(quitaGuion(VMov.elementAt(i).getTipo()));
            if (VMov.elementAt(i).getOrigen().equals("null")) {
                fila.add("Sin Origen");
            }else{
                fila.add(quitaGuion(VMov.elementAt(i).getOrigen()));
            }
            fila.add(quitaGuion(VMov.elementAt(i).getDestino()));            
            md.addRow(fila);
        }
        tabla.setModel(md);
    }    
    private static void ajustarTamañosTabla(JTable table,int[] anchos){
        for(int k=0; k<anchos.length; k++){//ajustar los tamaños
            table.getColumnModel().getColumn(k).setPreferredWidth(anchos[k]); 
        }
    }    
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBorrar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnImportarMovimientos;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnTotales;
    private javax.swing.JComboBox cmbRutero;
    private javax.swing.JComboBox cmbSucursal;
    private javax.swing.JComboBox cmbTipo;
    private com.toedter.calendar.JDateChooser dateChooserF;
    private com.toedter.calendar.JDateChooser dateChooserI;
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
