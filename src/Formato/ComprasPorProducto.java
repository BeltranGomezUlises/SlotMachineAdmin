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

        btnregresar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnregresar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnregresar.setText("Regresar");
        btnregresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregresarActionPerformed(evt);
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnregresar, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 339, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnregresar)
                .addGap(23, 23, 23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnregresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregresarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnregresarActionPerformed

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnregresar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
