package Excel;

import ControlUsuario.Usuario;
import Corte.ConsultaCorte;
import Corte.Corte;
import java.io.File;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class ImportarForm extends javax.swing.JFrame {
    
    String datos[][];
    String cabecera[]={"Fecha","Sucursal","Encargado","# Máquina","Pant. IN"," Pant. OUT"," Pant. IN-OUT","Cont. IN","Cont. OUT","IN-OUT"};
    Vector<Corte> Cortes;
    
    public ImportarForm(File archivo,Vector<Corte> Cortes){        
        initComponents();        
        tabla.setModel(new DefaultTableModel(datos,cabecera));
        this.Cortes=Cortes;
        //llenar la tabla con los valores del archivo
        new ExcelManager(archivo,tabla,"Cortes").importar();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnImportar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Importacion de Cortes");
        setResizable(false);

        btnImportar.setText("Importar a Archivos");
        btnImportar.setEnabled(false);
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar importacion");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
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
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(214, 214, 214)
                .addComponent(btnImportar)
                .addGap(124, 124, 124)
                .addComponent(btnCancelar)
                .addContainerGap(181, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnImportar)
                    .addComponent(btnCancelar))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
            this.setVisible(false);
            ConsultaCorte cc= new ConsultaCorte();
            cc.setVisible(true);
            cc.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        /*try {
            
            int filas=tabla.getRowCount();
            for (int i = 0; i < filas; i++) {
                String fecha=tabla.getValueAt(i,0).toString();
                String sucursal=tabla.getValueAt(i, 1).toString();
                String encargado=tabla.getValueAt(i, 2).toString();
                String maquina=quitaEspacios(tabla.getValueAt(i, 3).toString());
                String tarjeta="";
                for(int k=0; k<numMaquinas.size(); k++){
                    if(numMaquinas.elementAt(k).equals(maquina)){
                        tarjeta=tarjetas.elementAt(k);                        
                    }
                }
                int pantIn=Integer.parseInt(tabla.getValueAt(i, 4).toString());
                int pantOut=Integer.parseInt(tabla.getValueAt(i, 5).toString());
                int pantInOut=Integer.parseInt(tabla.getValueAt(i,6).toString());
                int contIn=Integer.parseInt(tabla.getValueAt(i,7).toString());
                int contOut=Integer.parseInt(tabla.getValueAt(i,8).toString());
                int inOut=Integer.parseInt(tabla.getValueAt(i,9).toString());
                Corte c= new Corte(fecha,sucursal,encargado,maquina,tarjeta,contIn,contOut,pantIn,pantOut,pantInOut,inOut);
                
                boolean existe=false;
                for (int j = 0; j < Cortes.size(); j++) { //verificar que no exista
                    if(c.getFecha().equals(Cortes.elementAt(j).getFecha())
                    && c.getSucursal().equals(Cortes.elementAt(j).getSucursal())
                    && c.getNumMaquina()==Cortes.elementAt(j).getNumMaquina()){
                        existe=true;
                    }
                }
                if(!existe){
                    pw.println(c.toString());    
                }else{
                    JOptionPane.showMessageDialog(rootPane,"El Corte de la maquina:" + c.getNumMaquina()+" en: "+c.getFecha()+"\nYa Existe y se omitirá en la importación","Atención",JOptionPane.WARNING_MESSAGE);
                }                
            }
            pw.close();
            btnCancelarActionPerformed(null);
        } catch (Exception e) {
            e.printStackTrace();
        } */       
    }//GEN-LAST:event_btnImportarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImportar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
