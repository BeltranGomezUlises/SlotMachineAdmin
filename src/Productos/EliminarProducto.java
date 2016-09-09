
package Productos;

import ControlUsuario.Usuario;
import static Utilerias.Utileria.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JOptionPane;

public class EliminarProducto extends javax.swing.JFrame {
    
    Vector<Producto> productos;
        
    public EliminarProducto(Vector<Producto> productos) {
        initComponents();
        this.productos=productos;        
        //llenar el comboBox con los nombres de los productos
        for (int i = 0; i <productos.size(); i++) {
            cmbProductos.addItem(quitaGuion(productos.elementAt(i).getNombre()));
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbProductos = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Producto");
        setResizable(false);

        jLabel1.setText("Producto:");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(btnAceptar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)))
                .addContainerGap(23, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbProductos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String nombre= quitaEspacios(cmbProductos.getSelectedItem().toString());                        
        //Eliminar el producto con ese nombre del vector
        for (int i = 0; i < productos.size(); i++) {
            if (productos.elementAt(i).getNombre().equals(nombre)) {
                productos.removeElementAt(i);
                productos.trimToSize();
            }
        }        
        try {
            FileWriter fw = new FileWriter("Archivos/Productos.data");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < productos.size(); i++) {
                pw.println(productos.elementAt(i).toString());
            }
            pw.close();
            JOptionPane.showMessageDialog(rootPane, "Producto Eliminado con éxito");
            btnCancelarActionPerformed(null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane,"Error de IO del archivo "+"//Productos.data","Atención",JOptionPane.ERROR_MESSAGE);
        }                   
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Productos p = new Productos();
        p.setVisible(true);
        p.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbProductos;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
