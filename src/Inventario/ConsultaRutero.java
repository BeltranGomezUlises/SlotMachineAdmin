package Inventario;

import static Utilerias.Utileria.*;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ConsultaRutero extends javax.swing.JFrame {
    Vector<InventarioRutero> inventarioRuteros = InventarioRutero.cargarInvRuteros();

    public ConsultaRutero() {
        initComponents();                              
        //con el vector lleno de inventario de ruteros, meter al combo box el nombre de todos los ruteros
        for (int i = 0; i < inventarioRuteros.size(); i++) {
            cmbRutero.addItem(quitaGuion(inventarioRuteros.elementAt(i).getRutero()));
        }         
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        cmbRutero = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta Rutero");
        setResizable(false);

        jLabel1.setText("Rutero: ");

        cmbRutero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbRuteroActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabla.setEnabled(false);
        jScrollPane1.setViewportView(tabla);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbRutero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cmbRutero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(btnCerrar)
                .addContainerGap(59, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void cmbRuteroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbRuteroActionPerformed
        //llenar la tabla con el rutero seleccionado
        String rutero = quitaEspacios(cmbRutero.getSelectedItem().toString());
        String cabecera[]={"Nombre","Cantidad"};
        Object datos[][]={};
        DefaultTableModel md = new DefaultTableModel(datos,cabecera);
        tabla.setModel(md);
        for (int i = 0; i < inventarioRuteros.size(); i++) {
            if (rutero.equals(inventarioRuteros.elementAt(i).getRutero())) {                
                for (int j = 0; j < inventarioRuteros.elementAt(i).productos.size(); j++) {
                    Vector v = new Vector();
                    v.add(quitaGuion(inventarioRuteros.elementAt(i).productos.elementAt(j)));
                    v.add(inventarioRuteros.elementAt(i).cantidades.elementAt(j).toString());
                    md.addRow(v);
                }
            }
        }
    }//GEN-LAST:event_cmbRuteroActionPerformed
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JComboBox cmbRutero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
