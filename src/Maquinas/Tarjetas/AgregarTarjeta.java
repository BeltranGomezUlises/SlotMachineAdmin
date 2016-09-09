package Maquinas.Tarjetas;

import static Utilerias.Utileria.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;

public class AgregarTarjeta extends javax.swing.JFrame {
                
    public AgregarTarjeta() {
        initComponents();        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        spnDenominacion = new javax.swing.JSpinner();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Tarjetas");
        setResizable(false);

        jLabel1.setText("Nombre Tarjeta:");

        jLabel2.setText("Denominación:");

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

        spnDenominacion.setModel(new javax.swing.SpinnerListModel(new String[] {"$", "¢"}));
        spnDenominacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                spnDenominacionKeyTyped(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(1, 1, 1)))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addGap(5, 5, 5)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnDenominacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnCerrar)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(spnDenominacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            String nomTarjeta = quitaEspacios(txtTarjeta.getText()); 
            
            boolean existe=false;
            Vector<Tarjeta> tarjetas = Tarjeta.cargarTarjetas();
            for (int i = 0; i < tarjetas.size(); i++) {
                if (tarjetas.elementAt(i).getNombre().equals(nomTarjeta)) {
                    existe=true;
                    break;
                }
            }
            if(!existe){
                int denominacion=1; //asi validamos si esta seleccinado el signo de peso o de centavo
                if (!spnDenominacion.getValue().toString().equals("$")) {
                    denominacion=10;
                }                
                Tarjeta t = new Tarjeta(nomTarjeta, denominacion);
                
                FileWriter fr = new FileWriter("Archivos/Tarjetas.bin",true);
                PrintWriter pw= new PrintWriter(fr);
                pw.println(t.toString());
                pw.close();

                JOptionPane.showMessageDialog(rootPane,"Tarjetas agregada con Éxito", "Agregar", WIDTH);
                btnCerrarActionPerformed(null);
            }else{
                JOptionPane.showMessageDialog(rootPane, "El número de tarjeta que ingresó ya existe", "Atención", JOptionPane.WARNING_MESSAGE);
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(rootPane, "Error, no se encontro el archivo Tarjetas.bin", "Error", ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Error de escritura de Tarjetas", "Error", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        Tarjetas lm= new Tarjetas();
        lm.setVisible(true);
        lm.setLocationRelativeTo(this);
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void spnDenominacionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_spnDenominacionKeyTyped
        evt.consume();
    }//GEN-LAST:event_spnDenominacionKeyTyped

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSpinner spnDenominacion;
    private javax.swing.JTextField txtTarjeta;
    // End of variables declaration//GEN-END:variables
}
