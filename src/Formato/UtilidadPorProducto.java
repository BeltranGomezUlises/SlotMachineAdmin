package Formato;

import Productos.Producto;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class UtilidadPorProducto extends javax.swing.JFrame {


    public UtilidadPorProducto() {
        initComponents();
    }
    public UtilidadPorProducto(Vector<Formato> formatos) {
        initComponents();
        DecimalFormat df = new DecimalFormat("0.00");
            
       //poner formato a la tabla
        String datos[][]={};
        String encabezado[]={"Nombre","Utilidad"};
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
        Vector<Float> totales = new Vector<Float>();
        Vector<Integer> cantVentas = new Vector<Integer>();
        for (int i = 0; i < nombresProductos.size(); i++) {//inicializar totales
            totales.add(Float.parseFloat("0"));
        }        
        for (int i = 0; i < nombresProductos.size(); i++) {
            for (int j = 0; j < formatos.size(); j++) {
                productos=formatos.elementAt(j).getProductos();
                cantVentas=formatos.elementAt(j).cantVentas();
                for (int k = 0; k < productos.size(); k++) {                    
                    if (nombresProductos.elementAt(i).equals(productos.elementAt(k).getNombre())) {
                        totales.setElementAt((productos.elementAt(k).utilidad()*cantVentas.elementAt(k))+totales.elementAt(i), i);                        
                    }
                }
            }
        }
        //llenar la table con nombres y totales
        Vector fila;
        for (int i = 0; i < nombresProductos.size(); i++) {
            fila= new Vector();
            fila.add(nombresProductos.elementAt(i));
            fila.add(df.format(totales.elementAt(i)));
            md.addRow(fila);
        } 
            
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Utilidades");
        setResizable(false);

        btnCerrar.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnCerrar.setText("Regresar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 325, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar)
                .addGap(18, 18, 18))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
