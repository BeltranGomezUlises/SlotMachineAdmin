
package Inventario;

import ControlUsuario.Usuario;

public class SeleccionaConsultaInventarios extends javax.swing.JFrame {

    Usuario loged;
    public SeleccionaConsultaInventarios() {
        initComponents();
    }
    public SeleccionaConsultaInventarios(Usuario loged) {
        initComponents();
        this.loged=loged;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnConsGrl = new javax.swing.JButton();
        btnConsRutero = new javax.swing.JButton();
        btnConsSucursal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de Inventarios");
        setResizable(false);

        btnConsGrl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/inventario.png"))); // NOI18N
        btnConsGrl.setText("        Inventario General");
        btnConsGrl.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsGrlActionPerformed(evt);
            }
        });

        btnConsRutero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/cajas.png"))); // NOI18N
        btnConsRutero.setText("   Inventario de Rutero");
        btnConsRutero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsRuteroActionPerformed(evt);
            }
        });

        btnConsSucursal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/movimientoInventario.png"))); // NOI18N
        btnConsSucursal.setText("Consulta Movimientos   ");
        btnConsSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsSucursalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnConsRutero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsGrl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsSucursal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(btnConsGrl)
                .addGap(18, 18, 18)
                .addComponent(btnConsRutero)
                .addGap(22, 22, 22)
                .addComponent(btnConsSucursal)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConsGrlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsGrlActionPerformed
        //abrir una consulta del inventario General
        Inventario inv = new Inventario();
        ConsultaInventario ci= new ConsultaInventario();
        ci.setInv(inv);
        ci.setLocationRelativeTo(this);
        ci.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConsGrlActionPerformed

    private void btnConsRuteroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsRuteroActionPerformed
        ConsultaRutero cr = new ConsultaRutero();
        cr.setLocationRelativeTo(this);
        cr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnConsRuteroActionPerformed

    private void btnConsSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsSucursalActionPerformed
     ConsultaMovimientos cm = new ConsultaMovimientos();
     cm.setLocationRelativeTo(this);
     cm.setVisible(true);
     this.dispose();
    }//GEN-LAST:event_btnConsSucursalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConsGrl;
    private javax.swing.JButton btnConsRutero;
    private javax.swing.JButton btnConsSucursal;
    // End of variables declaration//GEN-END:variables
}
