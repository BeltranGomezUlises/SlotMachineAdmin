
package Inventario;

import ControlUsuario.Loged;
import ControlUsuario.Usuario;
import static Utilerias.Utileria.*;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CapturaRevisaInv extends javax.swing.JFrame {
    boolean tipoInv=false; //falso para inventario general y verdadero para invRutero
    
    Inventario inventario;
    InventarioRutero invRutero;    
    
    Vector<String> nomProductos = new Vector<String>();  
    Vector<Integer> cantProductos = new Vector<Integer>();
    
    Vector<InventarioRutero> ruteros;
        
    public CapturaRevisaInv(Inventario inv) {
        initComponents();
        inventario=inv;
        tipoInv=false;
        
        String cabecera[]={"Nombre","Cantidad"};
        Object datos[][]={};
        DefaultTableModel md = new DefaultTableModel(datos, cabecera);
        tabla.setModel(md);
        lb1.setText("Inventario General");
        //llenar tabla con los nombres
        for (int i = 0; i < inventario.getProductos().size(); i++) {
            Vector fila = new Vector();
            fila.add(quitaGuion(inventario.getProductos().elementAt(i)));
            fila.add(0);
            nomProductos.add(inventario.getProductos().elementAt(i));
            cantProductos.add(0);
            md.addRow(fila);            
        }
    }
    
    public CapturaRevisaInv(InventarioRutero inv) {
        initComponents();        
        invRutero=inv;
        tipoInv=true;
        lb1.setText(inv.getRutero());
        String cabecera[]={"Nombre","Cantidad"};
        Object datos[][]={};
        DefaultTableModel md = new DefaultTableModel(datos,cabecera);
        tabla.setModel(md);
        
        //llenar tabla con los nombres
        for (int i = 0; i < invRutero.getProductos().size(); i++) {
            Vector fila = new Vector();
            fila.add(quitaGuion(invRutero.getProductos().elementAt(i)));
            fila.add(0);
            nomProductos.add(invRutero.getProductos().elementAt(i));
            cantProductos.add(0);
            md.addRow(fila);
        }        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnRevisar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Captura de Inventario");

        lb1.setText("Inventario General");

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
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        btnRevisar.setText("Revisar");
        btnRevisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRevisarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lb1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnRevisar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb1)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnRevisar)
                    .addComponent(btnCancelar))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_tablaKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnRevisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRevisarActionPerformed
        //tomar los valores de la tabla
        try {
            for (int i = 0; i < tabla.getRowCount(); i++) {
                cantProductos.setElementAt(Integer.parseInt(tabla.getModel().getValueAt(i, 1).toString()),i);
            }            
            //tomar la fecha del sistema
            Calendar c = new GregorianCalendar();
            SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
            String fecha=sdf.format(c.getTime());        

            Vector<Integer> diferencia = new Vector<Integer>();        
            if (tipoInv){ //true para invRutero
                //sacar la diferencia y mostrar            
                for (int i = 0; i < cantProductos.size(); i++) {
                    diferencia.add(cantProductos.elementAt(i) - invRutero.getCantidades().elementAt(i) );
                }            
                //generar movimientos y actualizar el inventario
                String nota=quitaEspacios(JOptionPane.showInputDialog(null, "Ingrese el motivo del ajuste", "Motivo", JOptionPane.QUESTION_MESSAGE));
                if (nota.equals("")) {
                    nota="null";
                }  

                Movimiento movAjuste = new Movimiento(fecha,'X',"Ajuste","null",invRutero.getRutero(),nota,invRutero.getProductos(),diferencia,"Sin_Encargado",Loged.getLoged().getUsuario());                
                //agregar el movimiento al archivo
                //Agregar al archivo
                try{
                    FileWriter frMov = new FileWriter("Archivos/Movimientos.data",true);
                    PrintWriter pwMov= new PrintWriter(frMov);
                    pwMov.println(movAjuste.toString());
                    pwMov.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error de escritura en archivo de movimientos", "Error", JOptionPane.ERROR_MESSAGE);                
                }

                //actualizar el inventrio del rutero
                ruteros = InventarioRutero.cargarInvRuteros();
                for (int i = 0; i < ruteros.size(); i++) {
                    if (ruteros.elementAt(i).getRutero().equals(invRutero.getRutero())) {
                        //sumar la diferencia                        
                        ruteros.elementAt(i).aumentarExistencia( movAjuste );
                    }
                }
                InventarioRutero.actualizarBD(ruteros);
            }else{ //false para inventario general            
                for (int i = 0; i < cantProductos.size(); i++) {
                    diferencia.add(cantProductos.elementAt(i) - inventario.getCantidades().elementAt(i));
                }
                String nota=quitaEspacios(JOptionPane.showInputDialog(null, "Ingrese el motivo del ajuste", "Motivo", JOptionPane.QUESTION_MESSAGE));
                if (nota.equals("")) {
                    nota="null";
                }               
                //generar movimientos y actualizar el inventario
                Movimiento movAjuste = new Movimiento(fecha,'X',"Ajuste","null","Inventario",nota,inventario.getProductos(),diferencia,"Sin_Encargado",Loged.getLoged().getUsuario());                
                try{
                    FileWriter frMov = new FileWriter("Archivos/Movimientos.data",true);
                    PrintWriter pwMov= new PrintWriter(frMov);
                    pwMov.println(movAjuste.toString());
                    pwMov.close();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error de escritura en archivo de movimientos", "Error", JOptionPane.ERROR_MESSAGE);                
                }
                //aumentar existencias al inventario                
                inventario.aumentarExistencia( movAjuste );
                //actualizar el inventario
                inventario.actualizarBD();           
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error con los valores de la tabla, Verifiquelos", "Error", JOptionPane.WARNING_MESSAGE);
        }
        JOptionPane.showMessageDialog(null, "Inventario Actualizado, revisar en movimientos", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();       
    }//GEN-LAST:event_btnRevisarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnRevisar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
