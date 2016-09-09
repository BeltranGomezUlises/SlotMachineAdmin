package Maquinas;

import ControlUsuario.Usuario;
import static Utilerias.Utileria.*;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JOptionPane;

public class EliminarMaquina extends javax.swing.JFrame {
    
    Vector<Maquina> maquinas;
          
    public EliminarMaquina() {
        initComponents();
        
        maquinas = Maquina.cargarMaquinas();
        for (int i = 0; i < maquinas.size(); i++) {
            cmbMaquinas.addItem(quitaGuion(maquinas.elementAt(i).getNombre()));
        }
        
    }
        
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbMaquinas = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Eliminar");

        cmbMaquinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaquinasActionPerformed(evt);
            }
        });

        jLabel1.setText("Maquina: ");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(btnEliminar)
                        .addGap(56, 56, 56)
                        .addComponent(btnCancelar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbMaquinas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEliminar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMaquinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaquinasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbMaquinasActionPerformed
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
       ListaMaquinas lm= new ListaMaquinas();
       lm.setVisible(true);
       lm.setLocationRelativeTo(this);
       this.setVisible(false);
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try{
            maquinas.removeElementAt(cmbMaquinas.getSelectedIndex());
            //reescribir el archivo de maquinas
            FileWriter fw = new FileWriter("Archivos/Maquinas.bin");
            PrintWriter pw= new PrintWriter(fw);
            for(int k=0; k<maquinas.size(); k++){
                pw.println(maquinas.elementAt(k).toString());
            }
            pw.close();
            btnCancelarActionPerformed(null);
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(rootPane, "Base de datos no se encuentra o inutilizable","Error", WIDTH);
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Base de datos no se encuentra o inutilizable","Error", WIDTH);
        }
        
    }//GEN-LAST:event_btnEliminarActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JComboBox cmbMaquinas;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
