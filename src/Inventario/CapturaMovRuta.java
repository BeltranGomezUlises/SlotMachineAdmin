
package Inventario;

import ControlUsuario.Loged;
import ControlUsuario.Usuario;
import Productos.Producto;
import static Utilerias.Utileria.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class CapturaMovRuta extends javax.swing.JFrame {
    Vector<String> nomProductos = new Vector<String>();  
    Vector<Integer> cantProductos = new Vector<Integer>();  
    Vector<InventarioRutero> ruteros;
        
    public CapturaMovRuta() {
        initComponents();
    
        tabla.getTableHeader().setReorderingAllowed(false);
        chkEntrega.setSelected(true);
        chkPerdida.setSelected(false);
        //Llenar el combo box con los ruteros del archivo
        //llenar tabla
        String cabecera[]={"Nombre","Cantidad"};
        Object datos[][]={};
        DefaultTableModel md = new DefaultTableModel(datos,cabecera);
        tabla.setModel(md);
        
        Vector<Producto> productos = Producto.cargarProductos();
        ruteros = InventarioRutero.cargarInvRuteros();
        //llenar la tabla con los productos que existan en el archivo de productos
        for (int i = 0; i < productos.size(); i++) {
            nomProductos.add(productos.elementAt(i).getNombre());
            cantProductos.add(0);
            md.addRow(new Object[]{quitaGuion(productos.elementAt(i).getNombre()),0});
        }
        for (int i = 0; i < ruteros.size(); i++) {
            cmbRutero.addItem(quitaGuion(ruteros.elementAt(i).getRutero()));
        }                                     
    }    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        chkEntrega = new javax.swing.JRadioButton();
        chkPerdida = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        txtNota = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        dateChooser = new com.toedter.calendar.JDateChooser();
        cmbRutero = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnCancelarKeyPressed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        btnAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnAceptarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        chkEntrega.setText("Entrega");
        chkEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEntregaActionPerformed(evt);
            }
        });

        chkPerdida.setText("Perdida");
        chkPerdida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPerdidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkEntrega, javax.swing.GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addComponent(chkPerdida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(210, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkEntrega)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkPerdida)
                .addGap(7, 7, 7))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Nota:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNota)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbRutero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuteroActionPerformed(evt);
            }
        });

        jLabel1.setText("Fecha:");

        jLabel2.setText("Rutero:");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbRutero, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRutero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        //llenar vector de la tabla    
        try{
            for (int i = 0; i <tabla.getRowCount(); i++) {                            
                cantProductos.setElementAt(Integer.parseInt((tabla.getModel().getValueAt(i, 1)).toString()), i);
            }            
            //generar movimiento y agregar al archivo
            SimpleDateFormat df =new SimpleDateFormat("d/MM/yyyy");
            String fecha = df.format(dateChooser.getDate());
            String tipo, origen;     
            String rutero = quitaEspacios(cmbRutero.getSelectedItem().toString());

            if (chkEntrega.isSelected()) {
                tipo="Entrega_Rutero";
                origen ="Inventario";            
            }else{
                tipo="Perdida";
                origen="null";            
            }           

            String destino = rutero;
            String nota;

            if (txtNota.getText().equals("")) nota="null"; else nota=quitaEspacios(txtNota.getText());

            Movimiento m = new Movimiento(fecha,'X',tipo,origen,destino,nota,nomProductos,cantProductos,"null",Loged.getLoged().getUsuario());                
            try {//agregar el movimiento al archivo
                FileWriter fr1 = new FileWriter("Archivos/Movimientos.data",true);
                PrintWriter pw = new PrintWriter(fr1);                                            
                pw.close();
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar registro de movimientos","Error",JOptionPane.ERROR_MESSAGE);                        
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar registro de movimientos","Error",JOptionPane.ERROR_MESSAGE);                
            }
            //Archivo InventarioRutas cargarlo en el vector de vectores para hacer la suma al rutero adecuado                                      
            //buscar el rutero y actualizar sus valores
            Inventario inv = new Inventario();            
            if (chkEntrega.isSelected()) {                
                for (int i = 0; i < ruteros.size(); i++) {                       
                    if (ruteros.elementAt(i).getRutero().equals(rutero)) {                                            
                        ruteros.elementAt(i).aumentarExistencia( m );                                                       
                        inv.reducirExistencia( m );                        
                    }
                }                
            }else{                
                 for (int i = 0; i < ruteros.size(); i++) {
                    if (ruteros.elementAt(i).getRutero().equals(rutero)) {                        
                        ruteros.elementAt(i).reducirExistencia( m );
                    }
                }
            }            
           //actualizar el archivo                       
           inv.actualizarBD();
           InventarioRutero.actualizarBD(ruteros);                       
            //Limpiar tabla
            for (int i = 0; i < tabla.getModel().getRowCount(); i++) {
                tabla.getModel().setValueAt(0, i, 1);
            }
            txtNota.setText("");
            JOptionPane.showMessageDialog(null, quitaGuion(tipo)+" a "+quitaGuion(rutero)+" realizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);                                                                       
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null, "Error de lectura de datos de la tabla, verifiquelo","Error", JOptionPane.ERROR_MESSAGE);                                                                       
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Debe llenar todos lo campos", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed
    private void cmbRuteroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuteroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbRuteroActionPerformed
    private void chkEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEntregaActionPerformed
        chkPerdida.setSelected(false);
    }//GEN-LAST:event_chkEntregaActionPerformed
    private void chkPerdidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPerdidaActionPerformed
        chkEntrega.setSelected(false);
    }//GEN-LAST:event_chkPerdidaActionPerformed
    private void btnAceptarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAceptarKeyTyped
    private void btnCancelarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyPressed
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            btnCancelarActionPerformed(null);
        }
        
    }//GEN-LAST:event_btnCancelarKeyPressed
    private void tablaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_tablaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JRadioButton chkEntrega;
    private javax.swing.JRadioButton chkPerdida;
    private javax.swing.JComboBox cmbRutero;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables
}
