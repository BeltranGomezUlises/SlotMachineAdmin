
package Vales;

import static Utilerias.Utileria.quitaGuion;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class TotalesVales extends javax.swing.JFrame {
   
    public TotalesVales(ArrayList<Vale> vales) {
        initComponents();
        ArrayList<Vale> misVales = new ArrayList<>();
        for (Vale vale : vales) {
            misVales.add(vale);
        }
        
        ArrayList<String> sucursales = new ArrayList<>();
        for (Vale vale : misVales) {
            if (!sucursales.contains(vale.getSucursal())) {
                sucursales.add(vale.getSucursal());
            }
        }
        //sacar total por sucursal
        ArrayList<Float> totales = new ArrayList<>();
        for (String sucursal : sucursales) {            
            float total = 0;
            for (int i = 0; i < misVales.size(); i++) {
                if (misVales.get(i).getSucursal().equals(sucursal) && misVales.get(i).isVigente()) {
                    total += misVales.get(i).getCantidad();                    
                    misVales.remove(i);
                    i--;
                } 
            }            
            totales.add(total);
        }
        //sacar el total general
        float totalGeneral = 0;
        for (int i = 0; i < totales.size(); i++) {
            totalGeneral += totales.get(i);
        }
        //llenar tabla
        DefaultTableModel md = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < totales.size(); i++) {
            Vector fila = new Vector();
            fila.add(quitaGuion(sucursales.get(i)));
            fila.add(totales.get(i));
            md.addRow(fila);
        }
        md.addRow(new Object[]{"General", totalGeneral});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Totales");
        setResizable(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sucursal", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.setToolTipText("");
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
