package Formato;

import Productos.Producto;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class ComprasPorProducto extends javax.swing.JFrame {

    public ComprasPorProducto() {
        initComponents();
    }
    public ComprasPorProducto(Vector<Formato> formatos) {
        initComponents();
        //poner formato a la tabla
        String datos[][]={};
        String encabezado[]={"Nombre","Cantidad"};
        DefaultTableModel md = new DefaultTableModel(datos,encabezado);
        tabla.setModel(md);
        //sacar los productos de los formatos y crear un vector que tenga todos los producto
        Vector<String> nombresProductos = new Vector<String>();
        Vector<Producto> productos;
        for (int i = 0; i < formatos.size(); i++) {
            productos=formatos.elementAt(i).getProductos();
            for (int j = 0; j < productos.size(); j++) {
                if (!nombresProductos.contains(productos.elementAt(j).getNombre())) {
                    nombresProductos.add(productos.elementAt(j).getNombre());
                }
            }
        }
//        System.out.println(nombresProductos.toString());
        //con el vector de los nombres de todos los productos sacar los totales
        Vector<Integer> totales = new Vector<Integer>();
        for (int i = 0; i < nombresProductos.size(); i++) {
            totales.add(0);
        }
        Vector<Integer> compras = new Vector<Integer>();
        for (int i = 0; i < nombresProductos.size(); i++) {
            for (int j = 0; j < formatos.size(); j++) {
                productos=formatos.elementAt(j).getProductos();
                compras=formatos.elementAt(j).getCompras(); 
                for (int k = 0; k < productos.size(); k++) {
                    if (nombresProductos.elementAt(i).equals(productos.elementAt(k).getNombre())) {
                        totales.setElementAt(compras.elementAt(k)+totales.elementAt(i), i);                        
                    }
                }
            }
        }
        //llenar la table con nombres y totales
        Vector fila;
        for (int i = 0; i < nombresProductos.size(); i++) {
            fila= new Vector();
            fila.add(nombresProductos.elementAt(i));
            fila.add(totales.elementAt(i));
            md.addRow(fila);
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jButton1.setText("Regresar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
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
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
