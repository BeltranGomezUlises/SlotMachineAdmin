
package Maquinas.Perifericos;

import static Utilerias.Utileria.quitaEspacios;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AgregarPeriferico extends javax.swing.JFrame {
    
    ArrayList<Periferico> perifericos;
            
    public AgregarPeriferico(ArrayList<Periferico> perifericos) {
        initComponents();        
        this.perifericos = perifericos;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCerrar = new javax.swing.JButton();
        txtTarjeta = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Periférico");

        btnCerrar.setText("Cancelar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        btnCerrar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCerrarKeyTyped(evt);
            }
        });

        btnAceptar.setText("Agregar");
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

        jLabel1.setText("Periferico:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(btnCerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        Perifericos lm= new Perifericos();
        lm.setVisible(true);
        lm.setLocationRelativeTo(this);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCerrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCerrarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnCerrarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCerrarKeyTyped

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        
            String nomPeriferico = quitaEspacios(txtTarjeta.getText());
            Periferico p = new Periferico(nomPeriferico);
            boolean existe=false;            
            for (int i = 0; i < perifericos.size(); i++) {
                if (perifericos.get(i).getNombre().equals(p.getNombre())) {
                    existe=true;
                    break;
                }
            }
            if(!existe){                                                
                perifericos.add(p);
                Periferico.actualizarDB(perifericos);
                JOptionPane.showMessageDialog(null, "Periférico agregado con éxito", "éxito", JOptionPane.INFORMATION_MESSAGE);
                btnCerrarActionPerformed(null);
            }else{
                JOptionPane.showMessageDialog(rootPane, "El número de periférico que ingresó ya existe", "Atención", JOptionPane.WARNING_MESSAGE);
            }
       
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAceptarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAceptarKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtTarjeta;
    // End of variables declaration//GEN-END:variables
}
