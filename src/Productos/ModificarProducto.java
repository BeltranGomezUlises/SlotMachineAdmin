package Productos;

import static Utilerias.Utileria.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModificarProducto extends javax.swing.JFrame {
    
    private Vector<Producto> productos;
        
    public ModificarProducto(Vector<Producto> productos) {
        initComponents();
        this.productos=productos;    
        //llenar la tabla con los productos        
        this.tabla.getTableHeader().setReorderingAllowed(false);
        this.llenarTabla(productos);
    }
    
    private void llenarTabla(Vector<Producto> productos){
        //llenar las filas con el vector de productos
        String datos[][]={};
        String cabecera[]={"Nombre","Precio","Costo","Comision","Presentacion"};        
        DefaultTableModel md = new DefaultTableModel(datos, cabecera);
        for (int i = 0; i < productos.size(); i++) {
            Vector fila = new Vector();
            fila.add(quitaGuion(productos.elementAt(i).getNombre()));
            fila.add(productos.elementAt(i).getPrecio());
            fila.add(productos.elementAt(i).getCosto());
            fila.add(productos.elementAt(i).getComision());
            fila.add(productos.elementAt(i).getPresentacion());
            md.addRow(fila);
        }
        tabla.setModel(md);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnBajar = new javax.swing.JButton();
        btnSubir = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar Productos");

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
        jScrollPane1.setViewportView(tabla);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Orden"));

        btnBajar.setText("↓");
        btnBajar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBajarActionPerformed(evt);
            }
        });

        btnSubir.setText("↑");
        btnSubir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBajar, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubir, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnSubir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBajar)
                .addContainerGap())
        );

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar y Guardar ");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancelar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //cerrar ventana y abrir productos
        Productos p = new Productos();
        p.setVisible(true);
        p.setLocationRelativeTo(null);
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        //modificar el vector de productos con los datos de la tabla       
        try {
            
            for (int i = 0; i < productos.size(); i++) {                                           
                    //asignar los valores
                productos.elementAt(i).setNombre(quitaEspacios(tabla.getValueAt(i, 0).toString()));                            
                productos.elementAt(i).setPrecio(Float.parseFloat(tabla.getValueAt(i, 1).toString()));                            
                productos.elementAt(i).setCosto(Float.parseFloat(tabla.getValueAt(i, 2).toString()));                            
                productos.elementAt(i).setComision(Float.parseFloat(tabla.getValueAt(i, 3).toString()));                                     
                productos.elementAt(i).setPresentacion(Integer.parseInt(tabla.getValueAt(i, 4).toString()));                                     
            }
            
            FileWriter fw = new FileWriter("Archivos/Productos.data");
            PrintWriter pw = new PrintWriter(fw);
            for (int i = 0; i < productos.size(); i++) {
                //sobreEscribir
                pw.println(productos.elementAt(i).toString()); 
            }                                                           
            pw.close();            
            btnCancelarActionPerformed(null);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane,"Error","Atención",JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(rootPane, "Un valor de la tabla no es valido", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnSubirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirActionPerformed
        //mover fila seleccionada hacia arriba        
        int index = tabla.getSelectedRow();
        if (index!=-1) {
            if (index!=0) {
                Producto aux = Producto.copy(productos.elementAt(index-1));
                productos.setElementAt(productos.elementAt(index), index-1);
                productos.setElementAt(aux, index);
                this.llenarTabla(productos);
                tabla.setRowSelectionInterval(index-1, index-1);
            }
        }  
    }//GEN-LAST:event_btnSubirActionPerformed

    private void btnBajarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBajarActionPerformed
        //mover fila seleccionada hacia abajo
        int index = tabla.getSelectedRow();
        if (index!=-1) {
            if (index!=productos.size()-1) {
                Producto aux = Producto.copy(productos.elementAt(index+1));
                productos.setElementAt(productos.elementAt(index), index+1);
                productos.setElementAt(aux, index);
                //te amo:3                
                this.llenarTabla(productos);
                tabla.setRowSelectionInterval(index+1, index+1);
            }
        }        
        
    }//GEN-LAST:event_btnBajarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBajar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSubir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
