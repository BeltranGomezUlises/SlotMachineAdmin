
package Ruta;

import ControlUsuario.Loged;
import ControlUsuario.Usuario;
import static Utilerias.Utileria.*;
import Inventario.InventarioRutero;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Ruteros extends javax.swing.JFrame {
    
    Vector<InventarioRutero> inventarioRuteros = new Vector<InventarioRutero>();
       
    public Ruteros( ) {
        initComponents();
        
        this.btnAgregar.setEnabled(Loged.getLoged().isEditarRutero());
        this.btnEliminar.setEnabled(Loged.getLoged().isEditarRutero());        
        
        try {            
            FileReader fr = new FileReader("Archivos/Ruteros.data");
            BufferedReader br = new BufferedReader(fr);
            String linea;
            StringTokenizer token;
            while((linea=br.readLine())!=null){
                token=new StringTokenizer(linea);
                Vector<String> productos= new Vector<String>();
                Vector<Integer> cantidades = new Vector<Integer>();
                String rutero=token.nextToken();
                try {
                    String seg=token.nextToken();
                    while(true){                        
                        productos.add(seg);
                        cantidades.add(Integer.parseInt(token.nextToken()));                        
                        seg=token.nextToken();
                        if (seg==null) {
                            break;
                        }
                    }                     
                } catch (java.util.NoSuchElementException e) {
                    //cuando cae la exepcion aqui es por que ya no hay mas productos
                    InventarioRutero inv= new InventarioRutero(rutero,productos,cantidades);
                    inventarioRuteros.add(inv); 
                }                
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(rootPane, "Error: Archivo de ruteros no encontrado","Error",JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Error: Archivo de ruteros no encontrado","Error",JOptionPane.ERROR_MESSAGE);
        }
        //lleno el vector llenar el areaText
        for (int i = 0; i <inventarioRuteros.size(); i++) {
            txtRuteros.setText(txtRuteros.getText()+quitaGuion(inventarioRuteros.elementAt(i).getRutero())+"\n");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtRuteros = new javax.swing.JTextArea();
        btnCancelar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel1.setText("Lista de Ruteros");

        txtRuteros.setEditable(false);
        txtRuteros.setColumns(20);
        txtRuteros.setRows(5);
        jScrollPane1.setViewportView(txtRuteros);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE))))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar)
                        .addGap(84, 84, 84)
                        .addComponent(btnCancelar)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        AgregarRutero ar= new AgregarRutero(inventarioRuteros);
        ar.setLocationRelativeTo(this);
        ar.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarRutero r= new EliminarRutero(inventarioRuteros);
        r.setLocationRelativeTo(this);
        r.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_btnEliminarActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtRuteros;
    // End of variables declaration//GEN-END:variables
}
