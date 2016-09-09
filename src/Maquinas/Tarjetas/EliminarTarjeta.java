package Maquinas.Tarjetas;

import static Utilerias.Utileria.*;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JOptionPane;

public class EliminarTarjeta extends javax.swing.JFrame {

    Vector<Tarjeta> tarjetas;
            
    public EliminarTarjeta() {
        initComponents();        
        tarjetas=Tarjeta.cargarTarjetas();
        
        for (int i = 0; i < tarjetas.size(); i++) {
            cmbTarjeta.addItem(quitaGuion(tarjetas.elementAt(i).getNombre()));
        }
        
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbTarjeta = new javax.swing.JComboBox<String>();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar Tarjeta");
        setResizable(false);

        jLabel1.setText("Tarjeta:");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(0, 19, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbTarjeta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Tarjetas t = new Tarjetas();
        t.setLocationRelativeTo(null);
        t.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try {
            tarjetas.removeElementAt(cmbTarjeta.getSelectedIndex());
            System.out.println(cmbTarjeta.getSelectedIndex());
            FileWriter fr = new FileWriter("Archivos/Tarjetas.bin");
            PrintWriter pw = new PrintWriter(fr);
            for (int i = 0; i < tarjetas.size(); i++) {
                pw.println(tarjetas.elementAt(i).toString());
            }
            pw.close();
            JOptionPane.showMessageDialog(null,"Tarjeta eliminada con éxito","Exito",JOptionPane.INFORMATION_MESSAGE);
            btnCancelarActionPerformed(null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al tratar de eliminar del archivo tarjetas","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEliminarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnEliminarActionPerformed(null);
        }
    }//GEN-LAST:event_btnEliminarKeyTyped

    private void btnCancelarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnCancelarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCancelarKeyTyped
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox<String> cmbTarjeta;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
