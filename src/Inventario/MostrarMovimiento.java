package Inventario;

import static Utilerias.Utileria.*;
import java.awt.Cursor;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class MostrarMovimiento extends javax.swing.JFrame {
   
    public MostrarMovimiento(Movimiento mov) {
        initComponents();
        //mostrar inv. actualizado
        txtFecha.setText(mov.getFecha());
        txtTipo.setText(quitaGuion(mov.getTipo()));
        
        if (mov.getOrigen().equals("null")) { txtOrigen.setText("Sin Origen");
        }else{ txtOrigen.setText(quitaGuion(mov.getOrigen())); }
        
        txtDestino.setText(quitaGuion(mov.getDestino()));        
        
        if (mov.getNota().equals("null"))    txtNota.setText("Sin Novedad");
        else   txtNota.setText(quitaGuion(mov.getNota())); 
        
        if (mov.getEncargado().equals("null"))  txtEncargado.setText("No Registrado");
        else txtEncargado.setText(mov.getEncargado());
        
        if (mov.getEncargado().equals("null")) txtEncargado.setText("No Encargado");            
        else txtEncargado.setText(quitaGuion(mov.getEncargado())); 
        
        if (mov.getUsuario().equals("null"))  txtEncargado.setText("No Registrado");            
        else txtUsuario.setText(quitaGuion(mov.getUsuario())); 
        
        if (mov.getLinkHoja().equals("null")) txtLinkHoja.setText("Sin Foto");
        else  txtLinkHoja.setText(mov.getLinkHoja());

        if (mov.getLinkFirma().equals("null")) txtLinkFirma.setText("Sin Foto");
        else  txtLinkFirma.setText(mov.getLinkFirma());
        
        txtTurno.setText(String.valueOf(mov.getTurno()));
        //llenar tabla        
        DefaultTableModel dtm= new DefaultTableModel ( new Object [][] {}, new String []{"Productos", "Cantidades"});        
        for (int i = 0; i < mov.getProductos().size(); i++) {
            Vector fila = new Vector();
            fila.add(quitaGuion(mov.getProductos().elementAt(i)));
            fila.add(mov.getCantidades().elementAt(i));
            dtm.addRow(fila);
        }        
        tabla.setModel(dtm);        
    }
    
    
    public void enlace (String enlaceAAceder){
        Desktop enlace=Desktop.getDesktop();
        try {
                enlace.browse(new URI(enlaceAAceder));
        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        txtOrigen = new javax.swing.JTextField();
        txtDestino = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtEncargado = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtTurno = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtLinkHoja = new javax.swing.JTextField();
        txtLinkFirma = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Fecha: ");

        jLabel2.setText("Tipo:");

        jLabel3.setText("Origen:");

        jLabel4.setText("Destino:");

        txtFecha.setEditable(false);

        txtTipo.setEditable(false);

        txtOrigen.setEditable(false);

        txtDestino.setEditable(false);

        jLabel6.setText("Encargado:");

        txtEncargado.setEditable(false);

        jLabel7.setText("Usuario:");

        txtUsuario.setEditable(false);

        jLabel10.setText("T:");

        txtTurno.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel7)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUsuario)
                    .addComponent(txtTipo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12)
                        .addComponent(txtTurno, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)))
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtOrigen, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE)
                    .addComponent(txtDestino)
                    .addComponent(txtEncargado))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtOrigen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel5.setText("Nota:");

        txtNota.setEditable(false);
        txtNota.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel8.setText("Link Hoja:");

        jLabel9.setText("Link Firma:");

        txtLinkHoja.setEditable(false);
        txtLinkHoja.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtLinkHoja.setForeground(new java.awt.Color(0, 51, 204));
        txtLinkHoja.setMaximumSize(new java.awt.Dimension(400, 400));
        txtLinkHoja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLinkHojaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtLinkHojaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtLinkHojaMouseExited(evt);
            }
        });

        txtLinkFirma.setEditable(false);
        txtLinkFirma.setFont(new java.awt.Font("Arial", 0, 11)); // NOI18N
        txtLinkFirma.setForeground(new java.awt.Color(0, 51, 204));
        txtLinkFirma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtLinkFirmaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtLinkFirmaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtLinkFirmaMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLinkFirma, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLinkHoja, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLinkHoja, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLinkFirma)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtLinkHojaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLinkHojaMouseClicked
        this.enlace(txtLinkHoja.getText());        
    }//GEN-LAST:event_txtLinkHojaMouseClicked

    private void txtLinkFirmaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLinkFirmaMouseClicked
        this.enlace(txtLinkFirma.getText());
    }//GEN-LAST:event_txtLinkFirmaMouseClicked

    private void txtLinkHojaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLinkHojaMouseEntered
        this.setCursor( Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_txtLinkHojaMouseEntered

    private void txtLinkHojaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLinkHojaMouseExited
        this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR) );
    }//GEN-LAST:event_txtLinkHojaMouseExited

    private void txtLinkFirmaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLinkFirmaMouseEntered
        this.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR) );
    }//GEN-LAST:event_txtLinkFirmaMouseEntered

    private void txtLinkFirmaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtLinkFirmaMouseExited
        this.setCursor( Cursor.getPredefinedCursor( Cursor.DEFAULT_CURSOR) );
    }//GEN-LAST:event_txtLinkFirmaMouseExited
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtDestino;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtLinkFirma;
    private javax.swing.JTextField txtLinkHoja;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtOrigen;
    private javax.swing.JTextField txtTipo;
    private javax.swing.JTextField txtTurno;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
