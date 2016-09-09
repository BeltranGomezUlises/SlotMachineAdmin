package ControlUsuario;

import static Utilerias.Utileria.*;
import java.util.ArrayList;


public class Usuarios extends javax.swing.JFrame {
    
    ArrayList<Usuario> usuarios= new ArrayList<>();    
    
    public Usuarios(){
        initComponents();
        this.usuarios = Usuario.cargarUsuarios();
//        this.loged=new Usuario(loged.getUsuario(),loged.getContraseña(),loged.getPermiso());        
        for (int i = 0; i < usuarios.size(); i++) {
            txtUsuarios.setText(txtUsuarios.getText()+quitaGuion(usuarios.get(i).getUsuario())+"\n");
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtUsuarios = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarUsuario = new javax.swing.JButton();
        btnEliminarUsuario = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnCambiarContraseña = new javax.swing.JButton();
        btnPermisos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Usuarios");
        setResizable(false);

        txtUsuarios.setEditable(false);
        txtUsuarios.setColumns(20);
        txtUsuarios.setRows(5);
        jScrollPane1.setViewportView(txtUsuarios);

        jLabel1.setText("Usuarios:");

        btnAgregarUsuario.setText("Agregar Usuario");
        btnAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarUsuarioActionPerformed(evt);
            }
        });
        btnAgregarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnAgregarUsuarioKeyTyped(evt);
            }
        });

        btnEliminarUsuario.setText("Eliminar Usuario");
        btnEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarUsuarioActionPerformed(evt);
            }
        });
        btnEliminarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnEliminarUsuarioKeyTyped(evt);
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

        btnCambiarContraseña.setText("Cambiar Contraseña");
        btnCambiarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCambiarContraseñaActionPerformed(evt);
            }
        });
        btnCambiarContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCambiarContraseñaKeyTyped(evt);
            }
        });

        btnPermisos.setText("Modificar Permisos");
        btnPermisos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPermisosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCambiarContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnPermisos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCambiarContraseña)
                        .addGap(11, 11, 11)
                        .addComponent(btnPermisos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed
    private void btnAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioActionPerformed
        AgregarUsuario ag = new AgregarUsuario(usuarios);
        ag.setVisible(true);
        ag.setLocationRelativeTo(this);
        this.setVisible(false);
    }//GEN-LAST:event_btnAgregarUsuarioActionPerformed
    private void btnEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioActionPerformed
       EliminarUsuario e= new EliminarUsuario(usuarios);
       e.setVisible(true);
       e.setLocationRelativeTo(this);
       this.setVisible(false); 
    }//GEN-LAST:event_btnEliminarUsuarioActionPerformed
    private void btnAgregarUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAgregarUsuarioKeyTyped
        this.btnAgregarUsuarioActionPerformed(null);
    }//GEN-LAST:event_btnAgregarUsuarioKeyTyped
    private void btnEliminarUsuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEliminarUsuarioKeyTyped
        this.btnEliminarUsuarioActionPerformed(null);
    }//GEN-LAST:event_btnEliminarUsuarioKeyTyped
    private void btnCancelarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyTyped
        this.btnCancelarActionPerformed(null);
    }//GEN-LAST:event_btnCancelarKeyTyped

    private void btnCambiarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCambiarContraseñaActionPerformed
        CambiarContraseña cc = new CambiarContraseña(usuarios);
        cc.setVisible(true);
        cc.setLocationRelativeTo(this);
        this.setVisible(false);
    }//GEN-LAST:event_btnCambiarContraseñaActionPerformed

    private void btnCambiarContraseñaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCambiarContraseñaKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCambiarContraseñaKeyTyped

    private void btnPermisosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPermisosActionPerformed
            SelectUsuario su= new SelectUsuario(usuarios);
            su.setVisible(true);
            su.setLocationRelativeTo(this);
    }//GEN-LAST:event_btnPermisosActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JButton btnCambiarContraseña;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnPermisos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtUsuarios;
    // End of variables declaration//GEN-END:variables
}
