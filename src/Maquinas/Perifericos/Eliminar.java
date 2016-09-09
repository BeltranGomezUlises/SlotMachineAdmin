
package Maquinas.Perifericos;

import ControlUsuario.Usuario;
import static Utilerias.Utileria.quitaGuion;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Eliminar extends javax.swing.JFrame {
    
    ArrayList<Periferico> perifericos;
      
    public Eliminar(ArrayList<Periferico> perifericos) {
        initComponents();        
        this.perifericos = perifericos;
        
        for (Periferico periferico : perifericos) {
            cmbPerifericos.addItem(quitaGuion(periferico.getNombre()));            
        }
        
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbPerifericos = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Periférico");

        jLabel1.setText("Periferico:");

        btnEliminar.setText("Eliminar");
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

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCancelarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(btnCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbPerifericos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbPerifericos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int index = cmbPerifericos.getSelectedIndex();
        perifericos.remove(index);
        Periferico.actualizarDB(perifericos);
        btnCancelarActionPerformed(null);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEliminarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnEliminarActionPerformed(null);
        }
    }//GEN-LAST:event_btnEliminarKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Perifericos t = new Perifericos();
        t.setLocationRelativeTo(null);
        t.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnCancelarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnCancelarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCancelarKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cmbPerifericos;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
