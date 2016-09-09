
package Inventario;

import ControlUsuario.Loged;
import ControlUsuario.Usuario;
import Productos.Producto;
import static Utilerias.Utileria.*;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CapturaEntradaInventario extends javax.swing.JFrame {
    Vector<String> nomProductos = new Vector<String>();  
    Vector<Integer> cantProductos = new Vector<Integer>();     
            
    public CapturaEntradaInventario() {
        initComponents();        
        tabla.getTableHeader().setReorderingAllowed(false);
        chkEntrada.setSelected(true);
        chkSalida.setSelected(false);
        
        String cabecera[]={"Nombre","Cantidad"};
        Object datos[][]={};
        DefaultTableModel md = new DefaultTableModel(datos,cabecera);
        tabla.setModel(md);
        //llenar la tabla con los productos que existan en el archivo de productos        
        Vector<Producto> productos = Producto.cargarProductos();
        //llenar vectores y llenar md
        for (int i = 0; i < productos.size(); i++) {
            nomProductos.add(productos.elementAt(i).getNombre());
            cantProductos.add(0);
            Vector fila = new Vector();
            fila.add(quitaGuion(productos.elementAt(i).getNombre()));
            fila.add(0);
            md.addRow(fila);
        }
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGroup = new javax.swing.ButtonGroup();
        l1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        chkEntrada = new javax.swing.JRadioButton();
        chkSalida = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        dateChooser = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        txtNota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Captura de Entradas");
        setResizable(false);

        l1.setText("Fecha:");

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

        chkEntrada.setText("Entrada");
        chkEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkEntradaActionPerformed(evt);
            }
        });

        chkSalida.setText("Mermas");
        chkSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSalidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(chkSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                    .addComponent(chkEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chkEntrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSalida)
                .addGap(6, 6, 6))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        btnAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnAceptarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Nota:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(l1)
                        .addGap(4, 4, 4)
                        .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(l1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try {                                                    
            //crear un movimiento y registrarlo        
            SimpleDateFormat df =new SimpleDateFormat("d/MM/yyyy");
            String fecha = df.format(dateChooser.getDate());
            String tipo;                       
            if (chkEntrada.isSelected()) {
                tipo="Entrega_Inventario";                
            }else{
                tipo="Merma";                
            }            
            String origen = "null";
            String destino = "Inventario";
            String encargado = "null";
            String nota;
            if (txtNota.getText().equals("")) {
                nota="null";
            }else{
                nota=quitaEspacios(txtNota.getText());   
            }
            //llenar un vector con los nombres y la cantidades de la entrada en un arreglo            
            for (int i = 0; i <tabla.getRowCount(); i++) {            
                 cantProductos.setElementAt(Integer.parseInt(String.valueOf(tabla.getModel().getValueAt(i, 1))), i);
            }
            
            Movimiento m = new Movimiento(fecha,'X',tipo,origen,destino,nota,nomProductos,cantProductos,encargado,Loged.getLoged().getUsuario(),"null","null");         
            try {//agregar el movimiento al archivo
                FileWriter fr = new FileWriter("Archivos/Movimientos.data",true);
                PrintWriter pw = new PrintWriter(fr);                
                pw.println(m.toString());
                pw.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar registro de movimientos","Error",JOptionPane.ERROR_MESSAGE);
            }
            
            Inventario inventario = new Inventario();//crear el objeto inventario: 
            //segun entrada o merma mandar diferente accion
            if (chkEntrada.isSelected()) {                                
                    inventario.aumentarExistencia( m );                
            }else{
                    inventario.reducirExistencia( m );                
            }            
            inventario.actualizarBD();    
            //limpiar tablas
            for (int i = 0; i < tabla.getModel().getRowCount(); i++) {
                    tabla.getModel().setValueAt(0, i, 1);
            }
            txtNota.setText("");
            JOptionPane.showMessageDialog(rootPane, "Inventario Actualizado con Éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Debe llenar todos lo campos", "Error", JOptionPane.WARNING_MESSAGE);
        } catch(NullPointerException e){
            JOptionPane.showMessageDialog(null, "Debe llenar todos lo campos", "Error", JOptionPane.WARNING_MESSAGE);
        }
        
       
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void chkEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkEntradaActionPerformed
        chkSalida.setSelected(false);
    }//GEN-LAST:event_chkEntradaActionPerformed

    private void chkSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSalidaActionPerformed
        chkEntrada.setSelected(false);
    }//GEN-LAST:event_chkSalidaActionPerformed

    private void btnAceptarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyPressed
        if (evt.getKeyChar()==KeyEvent.VK_ENTER) {
            btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAceptarKeyPressed

    private void tablaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_tablaKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.ButtonGroup btnGroup;
    private javax.swing.JRadioButton chkEntrada;
    private javax.swing.JRadioButton chkSalida;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel l1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtNota;
    // End of variables declaration//GEN-END:variables
}
