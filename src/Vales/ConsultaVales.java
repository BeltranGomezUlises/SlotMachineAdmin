
package Vales;

import ControlUsuario.Loged;
import static Utilerias.Utileria.quitaEspacios;
import static Utilerias.Utileria.quitaGuion;
import static java.awt.image.ImageObserver.WIDTH;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConsultaVales extends javax.swing.JFrame {

    ArrayList<Vale> vales;
    ArrayList<Vale> valesFiltrados;
    
    public ConsultaVales() {
        initComponents();
        btnEliminar.setEnabled(Loged.getLoged().isEditarVales());
        vales = Vale.cargarVales();
        valesFiltrados = new ArrayList<>();
        for (Vale vale : vales) {
            valesFiltrados.add(vale);
        }
         //llenar el cmbSucursal con las sucursales de los vales
        new Runnable(){
            @Override
            public void run() {
                ArrayList<String> nomSucursales = new ArrayList<>();
                for (int i = 0; i < vales.size(); i++) {
                    if (!nomSucursales.contains(vales.get(i).getSucursal())) {
                        nomSucursales.add(vales.get(i).getSucursal());
                    }
                }
                for (String nomSucursal : nomSucursales) {
                    cmbSucursal.addItem(quitaGuion(nomSucursal));
                }
            }
            
        }.run();
        
        this.llenarTablaVales(vales);
        tabla.setAutoCreateRowSorter(true);        
    }

    private void llenarTablaVales(ArrayList<Vale> vales){
        DefaultTableModel md = (DefaultTableModel) tabla.getModel();
        md.setRowCount(0);
        for (Vale vale : vales) {
            Vector fila = new Vector();
            fila.add(quitaGuion(vale.getChequera().getId() + "-" + vale.getFolio()));
            fila.add(vale.getFecha());
            fila.add(quitaGuion(vale.getSucursal()));
            fila.add(quitaGuion(vale.getMaquina()));
            fila.add(quitaGuion(vale.getConcepto()));
            fila.add(quitaGuion(vale.getNota()));
            fila.add(quitaGuion(vale.getEtiqueta()));            
            fila.add(vale.getCantidad());            
            fila.add(vale.isChecked());                       
            fila.add(vale.isVigente()); 
            md.addRow(fila);
        }
        tabla.setModel(md);
    }
    
    private void filtrar(){
        //llenar valeFiltrados con la lista de vales general
        valesFiltrados = new ArrayList<>();
        for (Vale vale : vales) {
            valesFiltrados.add(vale);
        }
        if(!cmbVigente.getSelectedItem().toString().equals("Todos")){
            if (cmbVigente.getSelectedItem().toString().equals("Vigente")) {
                int k = 0;
                while(k < valesFiltrados.size()){
                    if(valesFiltrados.get(k).isVigente() == false){
                        valesFiltrados.remove(k);
                    }else{
                        k++;
                    }
                }
            }else{//equivale  a NO
                int k = 0;
                while(k < valesFiltrados.size()){
                    if(valesFiltrados.get(k).isVigente() != false){
                        valesFiltrados.remove(k);
                    }else{
                        k++;
                    }
                }
            }                
        }
        
        if (!cmbSucursal.getSelectedItem().toString().equals("Todos")) {
            String suc = quitaEspacios(cmbSucursal.getSelectedItem().toString());
            int k = 0;
            while(k < valesFiltrados.size()){
                if(!valesFiltrados.get(k).getSucursal().equals(suc)){
                    valesFiltrados.remove(k);
                }else{
                    k++;
                }
            }
        }
        //Para las fechas        
        try{            
            SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
            int k=0;
            Date fechaInicial=sdf.parse(sdf.format(calInicial.getDate()));            
            Date fechaFinal=sdf.parse(sdf.format(calFinal.getDate()));            
            String fecha;
            while(k < valesFiltrados.size()){
                fecha = valesFiltrados.get(k).getFecha();
                Date fechaVale = sdf.parse(fecha);
                if(fechaVale.before(fechaInicial) || fechaVale.after(fechaFinal)){
                   valesFiltrados.remove(k); 
                }else{
                    k++;
                }
            }        
        }catch (NullPointerException ex) {
            //para omitir cuando no exita alguna de las fechas
        }catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al convertir fechas al filtrar", "ERROR", WIDTH);
            ex.printStackTrace();
        }
        this.llenarTablaVales(valesFiltrados);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        calInicial = new com.toedter.calendar.JDateChooser();
        calFinal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cmbVigente = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        cmbSucursal = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btnFiltrar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        btnTotales = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnChequeras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Fecha Inicial:");

        jLabel2.setText("FechaFinal:");

        cmbVigente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos", "Vigente", "No Vigente" }));

        jLabel3.setText("Vigente:");

        cmbSucursal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Todos" }));

        jLabel4.setText("Sucursal:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbVigente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(calFinal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(calInicial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbVigente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Folio", "Fecha", "Sucursal", "Maquina", "Concepto", "Nota", "Etiqueta", "Cantidad", "Autorizado", "Activos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Boolean.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tabla);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/filtrarFinal.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnTotales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/check.png"))); // NOI18N
        btnTotales.setText("Activos");
        btnTotales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTotalesActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/eliminarFinal.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnChequeras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/list.png"))); // NOI18N
        btnChequeras.setText("Estado Chequeras");
        btnChequeras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChequerasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTotales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnChequeras, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTotales, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnChequeras, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 973, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 443, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
        this.filtrar();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnTotalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTotalesActionPerformed
        TotalesVales totales = new TotalesVales(vales);
        totales.setLocationRelativeTo(this);
        totales.setVisible(true);
    }//GEN-LAST:event_btnTotalesActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        int index = tabla.getSelectedRow();
        if (index != -1) {            
            if (JOptionPane.showConfirmDialog(null, "Seguro desea eliminar el vale: \"" + valesFiltrados.get(index).getFolioCompleto() +"\"?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                for (int i = 0; i < vales.size(); i++) {
                    if (valesFiltrados.get(index).equals(vales.get(i))) {
                        vales.remove(i);
                        Vale.actualizarVD(vales);
                        JOptionPane.showMessageDialog(rootPane, "Vale eliminado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        this.filtrar();
                    }
                }
            }            
        }else{
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar un vale", "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnChequerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChequerasActionPerformed
        StatusChequeras sc = new StatusChequeras();
        sc.setLocationRelativeTo(this);
        sc.setVisible(true);
    }//GEN-LAST:event_btnChequerasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnChequeras;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnTotales;
    private com.toedter.calendar.JDateChooser calFinal;
    private com.toedter.calendar.JDateChooser calInicial;
    private javax.swing.JComboBox cmbSucursal;
    private javax.swing.JComboBox cmbVigente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
