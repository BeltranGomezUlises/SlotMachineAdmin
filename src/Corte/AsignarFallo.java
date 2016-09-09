package Corte;

import ControlUsuario.Usuario;
import Utilerias.Filtro;
import static Utilerias.Utileria.*;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.util.Vector;


public class AsignarFallo extends javax.swing.JFrame {
    
    Vector<Corte> cortes;
    int indice;    
    Filtro filtro;
       
    public AsignarFallo(Vector<Corte> cortes, int index, Filtro filtro) {
        initComponents();
        this.filtro = filtro;
        this.cortes=cortes;
        this.indice=index;        
        //cargar los fallos y las notas
        chkContador.setSelected(this.cortes.elementAt(indice).isFalloContador());
        chkPantalla.setSelected(this.cortes.elementAt(indice).isFalloPantalla());
        chkRobo.setSelected(this.cortes.elementAt(indice).isFalloRobo());
        txtRobo.setEnabled(this.cortes.elementAt(indice).isFalloRobo());
        txtRobo.setText(String.valueOf(this.cortes.elementAt(indice).getRobo()));
        chkPendiente.setSelected(this.cortes.elementAt(indice).isFalloPendiente());
        chkVueltaPos.setSelected(this.cortes.elementAt(indice).isVueltaContadorPos());
        chkVueltaNeg.setSelected(this.cortes.elementAt(indice).isVueltaContadorNeg());
        chkDescompostura.setSelected(this.cortes.elementAt(indice).isFalloDescompuesto());
        chkSolucion.setSelected(this.cortes.elementAt(indice).isSolucionado());
        if (!cortes.elementAt(indice).getNota().equals("null")) {
            txtNota.setText(quitaGuion(this.cortes.elementAt(indice).getNota()));
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        chkContador = new javax.swing.JCheckBox();
        chkPantalla = new javax.swing.JCheckBox();
        chkRobo = new javax.swing.JCheckBox();
        txtNota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        chkPendiente = new javax.swing.JCheckBox();
        chkVueltaPos = new javax.swing.JCheckBox();
        chkVueltaNeg = new javax.swing.JCheckBox();
        txtRobo = new javax.swing.JTextField();
        chkSolucion = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        chkDescompostura = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Seleccionar Fallo"));

        chkContador.setText("Contador");

        chkPantalla.setText("Pantalla");

        chkRobo.setText("Robo:");
        chkRobo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRoboActionPerformed(evt);
            }
        });

        jLabel1.setText("Nota:");

        chkPendiente.setText("Pendiente");

        chkVueltaPos.setText("Vuelta Contador(+)");

        chkVueltaNeg.setText("Vuelta Contador(-)");

        txtRobo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRoboKeyTyped(evt);
            }
        });

        chkSolucion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkSolucion.setText("Marca de solución de fallo");

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        chkDescompostura.setText("Descompostura");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chkDescompostura)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkContador)
                                        .addComponent(chkVueltaPos)
                                        .addComponent(chkVueltaNeg))
                                    .addGap(56, 56, 56)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(chkPendiente)
                                        .addComponent(chkPantalla)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(chkRobo)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(txtRobo, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkSolucion)
                                    .addComponent(txtNota)
                                    .addComponent(jLabel1))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkContador)
                    .addComponent(chkPantalla))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkVueltaPos, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(chkPendiente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkVueltaNeg)
                    .addComponent(chkRobo)
                    .addComponent(txtRobo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkDescompostura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkSolucion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

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

        btnAceptar.setText("Aceptar");
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkRoboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRoboActionPerformed
        txtRobo.setEnabled(chkRobo.isSelected());
    }//GEN-LAST:event_chkRoboActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ConsultaCorte cc= new ConsultaCorte( filtro );
        cc.setVisible(true);
        cc.setLocationRelativeTo(this);
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        //llenar las variables de los campos
        boolean contador = chkContador.isSelected();
        boolean pantalla = chkPantalla.isSelected();
        boolean falloRobo = chkRobo.isSelected();
        boolean pendiente = chkPendiente.isSelected();
        boolean vueltaPos = chkVueltaPos.isSelected();
        boolean vueltaNeg = chkVueltaNeg.isSelected();
        boolean falloDescomp = chkDescompostura.isSelected();
        
        boolean solucion = chkSolucion.isSelected();
        
        String nota = txtNota.getText();
        if(nota.equals("")){
            nota="null";
        }
        int robo = 0;
        if (!txtRobo.getText().equals("")) {
            robo = Integer.parseInt(txtRobo.getText());
        }        
        //asignar las variables al corte
        cortes.elementAt(indice).setFalloContador(contador);
        cortes.elementAt(indice).setFalloPantalla(pantalla);
        cortes.elementAt(indice).setFalloRobo(falloRobo);
        cortes.elementAt(indice).setRobo(robo);
        cortes.elementAt(indice).setFalloPendiente(pendiente);
        cortes.elementAt(indice).setVueltaContadorPos(vueltaPos);
        cortes.elementAt(indice).setVueltaContadorNeg(vueltaNeg);
        cortes.elementAt(indice).setNota(quitaEspacios(nota));
        cortes.elementAt(indice).setSolucionado(solucion);
        cortes.elementAt(indice).setFalloDescompuesto(falloDescomp);
        //actualizar el archivo de cortes
        Corte.ActualizarBD(cortes);       
        //cerrar la ventana
        btnCancelarActionPerformed(null);
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAceptarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyTyped
        char key= evt.getKeyChar();
        if (key==VK_ENTER){
            btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAceptarKeyTyped

    private void btnCancelarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyTyped
        char key=evt.getKeyChar();
        if(key==VK_ENTER){
            btnCancelarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCancelarKeyTyped

    private void txtRoboKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoboKeyTyped
        char key = evt.getKeyChar();
        if (!Character.isDigit(key)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRoboKeyTyped

       
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chkContador;
    private javax.swing.JCheckBox chkDescompostura;
    private javax.swing.JCheckBox chkPantalla;
    private javax.swing.JCheckBox chkPendiente;
    private javax.swing.JCheckBox chkRobo;
    private javax.swing.JCheckBox chkSolucion;
    private javax.swing.JCheckBox chkVueltaNeg;
    private javax.swing.JCheckBox chkVueltaPos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtRobo;
    // End of variables declaration//GEN-END:variables
}
