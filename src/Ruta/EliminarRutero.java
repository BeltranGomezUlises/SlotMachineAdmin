
package Ruta;

import ControlUsuario.Usuario;
import static Utilerias.Utileria.*;
import Inventario.InventarioRutero;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JOptionPane;

public class EliminarRutero extends javax.swing.JFrame {

    Vector<InventarioRutero> ruteros;
    
    public EliminarRutero(Vector<InventarioRutero> ruteros) {
        initComponents();        
        this.ruteros=ruteros;
        //llenar el combo box con los nombres de los ruteros
        for (int i = 0; i < ruteros.size(); i++) {
            cmbRutero.addItem(quitaGuion(ruteros.elementAt(i).getRutero()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbRutero = new javax.swing.JComboBox();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar un rutero");
        setResizable(false);

        jLabel1.setText("Rutero a eliminar:");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbRutero, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbRutero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String nuevoRutero = quitaEspacios(cmbRutero.getSelectedItem().toString());        
        try {
            //eliminar del vector de ruteros el rutero seleccionado            
            for (int i = 0; i <ruteros.size(); i++) {
                if (ruteros.elementAt(i).getRutero().equals(nuevoRutero)) {
                    ruteros.removeElementAt(i);
                }
            }            
//            actualizar el archivo de ruteros
            FileWriter fw = new FileWriter("Archivos/Ruteros.data");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < ruteros.size(); i++) {
                pw.println(ruteros.elementAt(i));
            }
            pw.close();
            JOptionPane.showMessageDialog(null, "Rutero Eliminado de la lista con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            btnCancelarActionPerformed(null);
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "Error de escritura en archivo de Productos", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error de escritura en archivo de Productos", "Error", JOptionPane.ERROR_MESSAGE);
        }        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Ruteros r = new Ruteros();
        r.setLocationRelativeTo(this);
        r.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JComboBox cmbRutero;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
