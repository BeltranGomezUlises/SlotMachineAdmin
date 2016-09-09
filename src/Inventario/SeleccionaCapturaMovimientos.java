package Inventario;

import ControlUsuario.Usuario;

public class SeleccionaCapturaMovimientos extends javax.swing.JFrame {
    Usuario loged;   
    
    public SeleccionaCapturaMovimientos(Usuario loged) {
        initComponents();
        this.loged=loged;
    }
    public SeleccionaCapturaMovimientos(){
        initComponents();
    }        
            
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEntrada = new javax.swing.JButton();
        btnEntregaRutero = new javax.swing.JButton();
        btnEntregaRutero1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
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

        btnEntregaRutero1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/examinarInventario.png"))); // NOI18N
        btnEntregaRutero1.setText("  Revision de inventario");
        btnEntregaRutero1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntregaRutero1ActionPerformed(evt);
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
                    .addComponent(btnEntregaRutero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEntregaRutero1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnEntrada)
                .addGap(18, 18, 18)
                .addComponent(btnEntregaRutero)
                .addGap(18, 18, 18)
                .addComponent(btnEntregaRutero1)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaActionPerformed
        CapturaEntradaInventario cei= new CapturaEntradaInventario();
        cei.setLocationRelativeTo(this);
        cei.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEntradaActionPerformed

    private void btnEntregaRuteroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregaRuteroActionPerformed
        CapturaMovRuta cmr = new CapturaMovRuta();
        cmr.setLocationRelativeTo(this);
        cmr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnEntregaRuteroActionPerformed

    private void btnEntregaRutero1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntregaRutero1ActionPerformed
        SeleccionInventario selectInv = new SeleccionInventario();
        selectInv.setLocationRelativeTo(null);
        selectInv.setVisible(true);
    }//GEN-LAST:event_btnEntregaRutero1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnEntregaRutero;
    private javax.swing.JButton btnEntregaRutero1;
    // End of variables declaration//GEN-END:variables
}
