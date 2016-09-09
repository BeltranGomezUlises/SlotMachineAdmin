package Maquinas;

import Maquinas.Tarjetas.Tarjeta;
import ControlUsuario.Usuario;
import Maquinas.Perifericos.Periferico;
import Sucursal.Sucursal;
import static Utilerias.Utileria.quitaEspacios;
import static Utilerias.Utileria.quitaGuion;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class AgregarMaquina extends javax.swing.JFrame {
    
    Vector<Tarjeta> tarjetas;
    Vector<Sucursal> sucursales;
    ArrayList<Periferico> perifericos;
        
    public AgregarMaquina() {
        initComponents();                 
        perifericos = Periferico.cargarPerifericos();
        tarjetas = Tarjeta.cargarTarjetas();
        sucursales = Sucursal.cargarSucursales();
        for (int i = 0; i < tarjetas.size(); i++) {
             cmbTarjeta.addItem(quitaGuion(tarjetas.elementAt(i).getNombre()));
        }
        for (int i = 0; i < sucursales.size(); i++) {
            cmbSucursal.addItem(quitaGuion(sucursales.elementAt(i).getNombre()));
        }
        for (Periferico periferico : perifericos) {
            cmbPerifericos.addItem(quitaGuion(periferico.getNombre()));
        }        
    }
    
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNumMaquina = new javax.swing.JTextField();
        cmbTarjeta = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbSucursal = new javax.swing.JComboBox<String>();
        txtFondo = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cmbPerifericos = new javax.swing.JComboBox<String>();
        jLabel5 = new javax.swing.JLabel();
        cmbMoneda = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Máquina");
        setResizable(false);

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Numero de Máquina:");

        txtNumMaquina.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumMaquinaKeyTyped(evt);
            }
        });

        jLabel2.setText("Tipo de Tarjeta:");

        jLabel3.setText("Sucursal:");

        txtFondo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFondoKeyPressed(evt);
            }
        });

        jLabel4.setText("Fondo:");

        jLabel6.setText("Periferico:");

        jLabel5.setText("Moneda:");

        cmbMoneda.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "5", "10" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNumMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbTarjeta, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(56, 56, 56)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jLabel4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbPerifericos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtFondo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cmbMoneda, javax.swing.GroupLayout.Alignment.LEADING, 0, 60, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNumMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbPerifericos)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addGap(35, 35, 35))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrar))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumMaquinaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumMaquinaKeyTyped

    }//GEN-LAST:event_txtNumMaquinaKeyTyped
    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        ListaMaquinas lm= new ListaMaquinas();
        lm.setVisible(true);
        lm.setLocationRelativeTo(this);
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            String numMaquina= quitaEspacios(txtNumMaquina.getText());
            Tarjeta tarjeta = tarjetas.elementAt(cmbTarjeta.getSelectedIndex());
            Sucursal suc = sucursales.elementAt(cmbSucursal.getSelectedIndex());
            Periferico per = perifericos.get(cmbPerifericos.getSelectedIndex());
            int moneda = Integer.parseInt(cmbMoneda.getSelectedItem().toString());
            int fondo = Integer.parseInt(txtFondo.getText());
            //verificar que el numero de maquina no exista ya
            boolean existe=false;
            Vector<Maquina> maquinas= Maquina.cargarMaquinas();
            for (int i = 0; i < maquinas.size(); i++) {
                if (maquinas.elementAt(i).getNombre().equals(numMaquina)) {
                    existe=true;
                    break;
                }
            }
            
            if(!existe){
               int prestRecu = 0;               
               Maquina m = new Maquina(numMaquina, tarjeta, per, suc, moneda, fondo, prestRecu);
               FileWriter fr = new FileWriter("Archivos/Maquinas.bin",true);
               PrintWriter pw= new PrintWriter(fr);
               pw.println(m.toString());
               pw.close();

               JOptionPane.showMessageDialog(rootPane,"Máquina agregada con Éxito", "Agregar", WIDTH);
               btnCerrarActionPerformed(null);
            }else{
                JOptionPane.showMessageDialog(rootPane, "El número de tarjeta que ingresó ya existe", "Atención", JOptionPane.WARNING_MESSAGE);
            }                                    
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Error de conversion de datos, Revise los campos", "Error", ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Error de escritura de Máquina", "Error", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAceptarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAceptarKeyTyped

    private void btnCerrarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCerrarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnCerrarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCerrarKeyTyped

    private void txtFondoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFondoKeyPressed
        char key = evt.getKeyChar();
        if (!Character.isDigit(key)) {
            evt.consume();
            return;
        }
        if (txtFondo.getText().length() > 5) {
            evt.consume();           
        }
        
    }//GEN-LAST:event_txtFondoKeyPressed
   
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtFondo;
    private javax.swing.JTextField txtNumMaquina;
    // End of variables declaration//GEN-END:variables
}
