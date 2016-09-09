
package Maquinas;

import Maquinas.Tarjetas.Tarjeta;
import ControlUsuario.Usuario;
import Maquinas.Perifericos.Periferico;
import static Utilerias.Utileria.*;
import Sucursal.Sucursal;
import Utilerias.Validacion;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class EditarMaquina extends javax.swing.JFrame {

    Maquina maquinaEditar;
    Vector<Maquina> maquinas;    
    Vector<Sucursal> sucursales ;
    Vector<Tarjeta> tarjetas ;
    int indiceEditar;
    ArrayList<Periferico> perifericos;
    
    public EditarMaquina() {
        initComponents();
    }
    
    public EditarMaquina(Maquina m, Vector<Maquina> maquinas,int index) {
        initComponents();        
        this.maquinaEditar=m;
        this.maquinas=maquinas;        
        this.indiceEditar=index;        
        lbDatos.setText("Maquina: "+quitaGuion(maquinaEditar.getNombre())+" en: "+quitaGuion(maquinaEditar.getSucursal().getNombre() + " " +"Tarjeta: "+quitaGuion(maquinaEditar.getTarjeta().getNombre())));
       
        txtNumMaquina.setText(quitaGuion(maquinaEditar.getNombre()));
        
        sucursales = Sucursal.cargarSucursales();        
        for (int i = 0; i < sucursales.size(); i++) {
            cmbSucursal.addItem(quitaGuion(sucursales.elementAt(i).getNombre()));            
        }
        perifericos = Periferico.cargarPerifericos();
        for (Periferico periferico : perifericos) {
            cmbPerifericos.addItem(quitaGuion(periferico.getNombre()));
        }
        tarjetas = Tarjeta.cargarTarjetas();
        for (int i = 0; i < tarjetas.size(); i++) {
            cmbTarjeta.addItem(quitaGuion(tarjetas.elementAt(i).getNombre()));
        }
        
        //dejar seleccionado los elementos de la maquina a editar
        cmbSucursal.setSelectedItem(quitaGuion(maquinaEditar.getSucursal().getNombre()));
        cmbTarjeta.setSelectedItem(quitaGuion(maquinaEditar.getTarjeta().getNombre()));
        cmbPerifericos.setSelectedItem(quitaGuion(maquinaEditar.getPeriferico().getNombre()));
        cmbMoneda.setSelectedItem(String.valueOf(maquinaEditar.getMoneda()));
        txtFondo.setText(String.valueOf(maquinaEditar.getFondo()));
        txtPrestamo.setText(String.valueOf(maquinaEditar.getPrestRecu()));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbDatos = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        cmbSucursal = new javax.swing.JComboBox<String>();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtNumMaquina = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        cmbTarjeta = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        txtFondo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtPrestamo = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cmbPerifericos = new javax.swing.JComboBox<String>();
        jLabel7 = new javax.swing.JLabel();
        cmbMoneda = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setTitle("Editar Máquina");
        setResizable(false);

        lbDatos.setText("jLabel1");

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel3.setText("Sucursal:");

        jLabel1.setText("Numero de Máquina:");

        txtNumMaquina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumMaquinaKeyTyped(evt);
            }
        });

        jLabel2.setText("Tipo de Tarjeta:");

        jLabel4.setText("Fondo:");

        txtFondo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFondoKeyTyped(evt);
            }
        });

        jLabel5.setText("Prestamo:");

        txtPrestamo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrestamoKeyTyped(evt);
            }
        });

        jLabel6.setText("Periferico:");

        jLabel7.setText("Moneda:");

        cmbMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "5", "10" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbPerifericos, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNumMaquina)
                    .addComponent(cmbTarjeta, 0, 166, Short.MAX_VALUE)
                    .addComponent(cmbSucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtPrestamo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFondo, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbMoneda, javax.swing.GroupLayout.Alignment.LEADING, 0, 60, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNumMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cmbTarjeta)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmbSucursal)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPerifericos)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addGap(51, 51, 51))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbDatos)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbDatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            String numMaquina= quitaEspacios(txtNumMaquina.getText());
            Tarjeta tarjeta = tarjetas.elementAt(cmbTarjeta.getSelectedIndex());
            Sucursal suc = sucursales.elementAt(cmbSucursal.getSelectedIndex());
            Periferico per = perifericos.get(cmbPerifericos.getSelectedIndex());
            int moneda = Integer.parseInt(cmbMoneda.getSelectedItem().toString());
            int fondo = Integer.parseInt(txtFondo.getText());
            int prestamo = Integer.parseInt(txtPrestamo.getText());
            //verificar que el numero de maquina no exista ya
            boolean existe=false;
            
            Vector<Maquina> maquinasArchivo= Maquina.cargarMaquinas();
            for (int i = 0; i < maquinasArchivo.size(); i++) {
                if (!maquinasArchivo.elementAt(i).getNombre().equals(maquinaEditar.getNombre())) { //omitir la maquina a editar
                    if (maquinasArchivo.elementAt(i).getNombre().equals(numMaquina)) {
                    existe=true;
                    break;
                    }
                }
            }
            
            if(!existe){                               
                Maquina m = new Maquina(numMaquina, tarjeta, per, suc, moneda, fondo, prestamo);
                maquinas.setElementAt(m, indiceEditar);
                Maquina.ActualizarDB(maquinas);
                JOptionPane.showMessageDialog(rootPane,"Máquina Modificada con Éxito", "Agregar", WIDTH);
                btnCerrarActionPerformed(null);
            }else{
                JOptionPane.showMessageDialog(rootPane, "El número de tarjeta que ingresó ya existe", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Error de conversion de datos, Revise los campos", "Error", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAceptarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyTyped
        if (evt.getKeyChar() == evt.VK_ENTER) {
            btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAceptarKeyTyped

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        ListaMaquinas lm= new ListaMaquinas();
        lm.setVisible(true);
        lm.setLocationRelativeTo(this);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCerrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCerrarKeyTyped
        if (evt.getKeyChar() == evt.VK_ENTER) {
            btnCerrarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCerrarKeyTyped

    private void txtNumMaquinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumMaquinaKeyTyped

    }//GEN-LAST:event_txtNumMaquinaKeyTyped

    private void txtFondoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFondoKeyTyped
        if (!Validacion.isDigit(evt)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtFondoKeyTyped

    private void txtPrestamoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrestamoKeyTyped
        if (!Validacion.isDigit(evt)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtPrestamoKeyTyped
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JComboBox cmbMoneda;
    private javax.swing.JComboBox<String> cmbPerifericos;
    private javax.swing.JComboBox<String> cmbSucursal;
    private javax.swing.JComboBox cmbTarjeta;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbDatos;
    private javax.swing.JTextField txtFondo;
    private javax.swing.JTextField txtNumMaquina;
    private javax.swing.JTextField txtPrestamo;
    // End of variables declaration//GEN-END:variables
}
