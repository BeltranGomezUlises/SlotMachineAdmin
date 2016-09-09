package Ruta;

import ControlUsuario.Usuario;
import static Utilerias.Utileria.*;
import Inventario.InventarioRutero;
import static java.awt.event.KeyEvent.VK_ENTER;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class AgregarRutero extends javax.swing.JFrame {
    
    Vector<InventarioRutero> ruteros;
       
    public AgregarRutero(Vector<InventarioRutero> ruteros) {
        initComponents();
        this.ruteros=ruteros;        
    }
  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtRutero = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Agregar Rutero");
        setResizable(false);

        txtRutero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRuteroKeyTyped(evt);
            }
        });

        jLabel1.setText("Nombre:");

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtRutero, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(txtRutero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        String nuevoRutero = quitaEspacios(txtRutero.getText());
        boolean existe=false;
        for (int i = 0; i < ruteros.size(); i++) {
            if (ruteros.elementAt(i).getRutero().equals(nuevoRutero)) {
                existe=true;
            }
        }
        if (existe) {
            JOptionPane.showMessageDialog(rootPane, "El nombre que ingresó ya existe como rutero", "Atención", JOptionPane.WARNING_MESSAGE);
        }else{
            try {                        
            FileReader fr = new FileReader("Archivos/Productos.data");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            Vector<String> prod = new Vector<String>();
            Vector<Integer> cant = new Vector<Integer>();
            while ((linea=br.readLine())!=null) {
                token= new StringTokenizer(linea);
                prod.add(token.nextToken());
                cant.add(0);
            }                        
            InventarioRutero r = new InventarioRutero(nuevoRutero,prod,cant);
            ruteros.add(r);
            //actualizar el archivo de ruteros
            FileWriter fw = new FileWriter("Archivos/Ruteros.data");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < ruteros.size(); i++) {
                pw.println(ruteros.elementAt(i));
            }
            pw.close();
            JOptionPane.showMessageDialog(null, "Rutero agregado a la lista con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            btnCancelarActionPerformed(null);
        }catch(FileNotFoundException e){   
            JOptionPane.showMessageDialog(null, "Error de escritura en archivo de Productos", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error de escritura en archivo de Productos", "Error", JOptionPane.ERROR_MESSAGE);
        }
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        Ruteros r = new Ruteros();
        r.setLocationRelativeTo(this);
        r.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtRuteroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRuteroKeyTyped
        char key=evt.getKeyChar();
        if (key==VK_ENTER) {
            btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_txtRuteroKeyTyped
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField txtRutero;
    // End of variables declaration//GEN-END:variables
}
