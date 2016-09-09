
package Inventario;

public class CapturaMovimientos extends javax.swing.JFrame {
    
    public CapturaMovimientos() {
        initComponents();        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntrada = new javax.swing.JButton();
        btnEntregaRutero = new javax.swing.JButton();

        setTitle("Seleccion de Movimiento");
        setResizable(false);

        btnEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/movInventario.png"))); // NOI18N
        btnEntrada.setText("Movimiento a inventario");
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });

        btnEntregaRutero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/diablito.png"))); // NOI18N
        btnEntregaRutero.setText("Movimiento a rutero      ");
        btnEntregaRutero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregaRuteroActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(btnEntregaRutero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEntrada)
                .addGap(18, 18, 18)
                .addComponent(btnEntregaRutero)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaActionPerformed
        CapturaEntradaInventario cei= new CapturaEntradaInventario();
        cei.setLocationRelativeTo(this);
        cei.setVisible(true);
    }//GEN-LAST:event_btnEntradaActionPerformed

    private void btnEntregaRuteroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregaRuteroActionPerformed
        CapturaMovRuta cmr = new CapturaMovRuta();
         cmr.setLocationRelativeTo(this);
        cmr.setVisible(true);
    }//GEN-LAST:event_btnEntregaRuteroActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnEntregaRutero;
    // End of variables declaration//GEN-END:variables
}
