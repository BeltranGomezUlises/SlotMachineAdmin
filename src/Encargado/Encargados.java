package Encargado;

import ControlUsuario.Loged;
import ControlUsuario.Usuario;
import Excel.ExcelManager;
import Formato.ConsultaFormato;
import static Utilerias.Utileria.quitaDiagonal;
import static Utilerias.Utileria.quitaGuion;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Encargados extends javax.swing.JFrame {
        
    Vector<Encargado> encargados;
        
    String datos[][]={};
    String cabecera[]={"Nombre"};
    
    
    public Encargados() {
        initComponents();                
        
        btnAgregar.setEnabled( Loged.getLoged().isEditarEmpleados() );
        btnEliminar.setEnabled( Loged.getLoged().isEditarEmpleados() );        
        
        encargados = Encargado.cargarEncargados();
        
        
        ordenarEncargados(encargados, 0, encargados.size()-1); //ordena nombres alfabeticamente
        this.ajustarTabla(); //llena la tabla con los valores y da formato
        Encargado.actualizarBD(encargados);
        
        
    }
    
    public static void ordenarEncargados(Vector<Encargado> encargados, int izq, int der){
            //este metodo ordena los cortes por fecha
            int i = izq; 
            int j = der; 
            Encargado x = encargados.elementAt((izq + der) /2 ); 
            Encargado aux;
            do{ 
                while( (encargados.elementAt(i).compareTo(x) < 0) && (j <= der) ){ 
                       i++;} 
                while( (encargados.elementAt(j).compareTo(x)>0) && (j > izq) ){ 
                       j--;} 
                if( i <= j ){                                        
                    aux = Encargado.copy(encargados.elementAt(i));                                        
                    encargados.setElementAt(encargados.elementAt(j), i);
                    encargados.setElementAt(aux,j);
                            
                    i++;  j--; 
                } 
            }while( i <= j ); 
            if( izq < j ) 
                ordenarEncargados( encargados, izq, j ); 
            if( i < der ) 
                ordenarEncargados( encargados, i, der );                                         
     }
    
    private void ajustarTabla(){
        DefaultTableModel md= new DefaultTableModel(datos,cabecera);       
        for (int i = 0; i < encargados.size(); i++) {
            Vector fila = new Vector();
            fila.add(quitaGuion(encargados.elementAt(i).getNombre()));
            md.addRow(fila);
        }
         tablaEncargados.setModel(md);
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEncargados = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnExportar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Encargados Actuales");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Lista de encargados");

        tablaEncargados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaEncargados);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCancelar.setText("Regresar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnExportar.setText("Exportar");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btnExportar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnExportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int indexFila=tablaEncargados.getSelectedRow();       
            if(indexFila!=-1){
                if(JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el encargado seleccionado?", "Eliminar", YES_NO_OPTION)==0){                    
                   encargados.removeElementAt(indexFila);                   
                   Encargado.actualizarBD(encargados);
                   this.ajustarTabla();
                }
            }else{
                JOptionPane.showMessageDialog(this,"Debe seleccionar una fila","Atencion",JOptionPane.WARNING_MESSAGE);
            }        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        AgregarEncargado ae = new AgregarEncargado( );
        ae.setVisible(true);
        ae.setLocationRelativeTo(this);
        this.dispose();
            
        
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportarActionPerformed
        //exportar a excel usar un exportador        
        String nombreArchivo=JOptionPane.showInputDialog(rootPane, "Ingrese el nombre del archivo a crear", "Ingresar Nombre",JOptionPane.INFORMATION_MESSAGE);
        
        if(nombreArchivo!=null){
            nombreArchivo = quitaDiagonal(nombreArchivo);  
            File archivo;
            JFileChooser fc = new JFileChooser();
            fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if(JFileChooser.APPROVE_OPTION==fc.showDialog(this,"Encargados")){
                archivo= new File(fc.getSelectedFile().toString().concat("/"+nombreArchivo+".xlsx"));                 
                new ExcelManager(archivo,tablaEncargados,"Formatos").exportar();
            try {
                Desktop.getDesktop().open(archivo);
            } catch (IOException ex) {
                System.out.println("Imposible abrir el archivo creado");
            }
            }
        }
    }//GEN-LAST:event_btnExportarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEncargados;
    // End of variables declaration//GEN-END:variables
}
