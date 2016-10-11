package Maquinas;

import ControlUsuario.Loged;
import static Utilerias.Utileria.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ListaMaquinas extends javax.swing.JFrame {
        
    Vector<Maquina> maquinas;  
    
    public ListaMaquinas() {
        initComponents();
        tabla.setAutoCreateRowSorter(true);
        maquinas = Maquina.cargarMaquinas();
        if (maquinas.size() > 0) { //preguntamos si no esta vacio el vector, si es asi omitir ordenamiento y actualizacion
            ordenarMaquinas(maquinas, 0, maquinas.size()-1);
            //una vez ordenado actualizar el archivo de maquinas
            try {
                FileWriter fr = new FileWriter("Archivos/Maquinas.bin");
                try (PrintWriter pw = new PrintWriter(fr)) {
                    for (int i = 0; i < maquinas.size(); i++) {
                        pw.println(maquinas.elementAt(i).toString());
                    }
                } 
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al actualizar el orden de las maquinas", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
                                        
        //llenar la tabla
        String datos[][]={};
        String cabecera[]={"Maquina","tarjeta","Sucursal","Fondo","Prestamo", "Moneda", "Periferico"};
        DefaultTableModel dtm = new DefaultTableModel(datos, cabecera);
        tabla.setModel(dtm);
        
        for (int i = 0; i < maquinas.size(); i++) {
            Vector fila= new Vector();
            fila.add(quitaGuion(maquinas.elementAt(i).getNombre()));
            fila.add(quitaGuion(maquinas.elementAt(i).getTarjeta().getNombre()));
            fila.add(quitaGuion(maquinas.elementAt(i).getSucursal().getNombre()));
            fila.add(maquinas.elementAt(i).getFondo());
            fila.add(maquinas.elementAt(i).getPrestRecu());
            fila.add(maquinas.elementAt(i).getMoneda());
            fila.add(quitaGuion(maquinas.elementAt(i).getPeriferico().getNombre()));
            dtm.addRow(fila);
        }
        //revisar permiso
        if(!Loged.getLoged().isEditarMaquinas()){                
            this.btnEliminar.setEnabled(false);
            this.btnAgregar.setEnabled(false);
        }
    }
       
    private void ordenarMaquinas(Vector<Maquina> maqs, int izq, int der){            
        int i = izq; 
        int j = der; 
        Maquina x = maqs.elementAt((izq + der) /2 ); 
        Maquina aux;
        do{ 
            while( (maqs.elementAt(i).compareTo(x) < 0) && (j <= der) ){ 
                   i++;} 
            while( (maqs.elementAt(j).compareTo(x) > 0) && (j > izq) ){ 
                   j--;} 
            if( i <= j ){ 
                aux = Maquina.copy(maqs.elementAt(i));                            
                maqs.setElementAt(maqs.elementAt(j), i);
                maqs.setElementAt(aux,j);                            
                i++;  j--; 
            } 
        }while( i <= j ); 
        if( izq < j ) 
            ordenarMaquinas( maqs, izq, j ); 
        if( i < der ) 
            ordenarMaquinas( maqs, i, der );                         
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Máquinas");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Lista de Máquinas");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAgregar.setText("Agregar Máquina");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar Maquina");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cerrar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                .addComponent(btnCancelar)
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
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 528, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       AgregarMaquina am= new AgregarMaquina();
       am.setVisible(true);
       am.setLocationRelativeTo(this);
       this.setVisible(false);
    }//GEN-LAST:event_btnAgregarActionPerformed
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        EliminarMaquina em= new EliminarMaquina();
        em.setVisible(true);
        em.setLocationRelativeTo(this);
        this.setVisible(false);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        int index = tabla.getSelectedRow();
        if (index!=-1) {                   
            EditarMaquina em = new EditarMaquina(maquinas.elementAt(index), maquinas, index);
            em.setVisible(true);
            em.setLocationRelativeTo(this);            
            this.dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Debe de seleccionar una maquina a editar","Atención",JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarActionPerformed
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
