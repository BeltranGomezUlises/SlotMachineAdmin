
package Inventario;

import ControlUsuario.Loged;
import ControlUsuario.Usuario;
import Excel.ExcelManager;
import static Utilerias.Utileria.*;
import Productos.Producto;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;

public class ImportarMovimientos extends javax.swing.JFrame {
    
    Vector<Movimiento> movimientos;
    File archivo;
    
    
    public ImportarMovimientos() {
        initComponents();
    }
    
    public ImportarMovimientos(File archivo,Vector<Movimiento> movimientos) {
        initComponents();        
        this.movimientos=movimientos;
        this.archivo=archivo;
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.doLayout();
        ExcelManager xlsManager = new ExcelManager(archivo,tabla,"movimientos");
        xlsManager.importar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnImportar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane1.setViewportView(tabla);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnImportar.setText("Importar Movimientos");
        btnImportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImportarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(37, Short.MAX_VALUE)
                .addComponent(btnImportar)
                .addGap(101, 101, 101)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnImportar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel1.setText("Orden de pestañas:  Record Name, Date Created, Status, Dia Hora, Sucursal, Encargado, Turno, TODOS LAS COLUMNAS DE PRODUCTOS, Foto de Hoja, Nota, Firma de Encargado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 94, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnImportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImportarActionPerformed
        //generar un vector de objetos movimiento con la tabla
        //verificar el orden de las columnas y sacar el nombre de los productos
        Vector<String> nombreProductos;
        Vector<Integer> cantProductos;
        Vector<Movimiento> movsImportacion = new Vector<Movimiento>();
        Vector<Movimiento> movsImportacionExistentes = new Vector<Movimiento>();        
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");       
        boolean valoresValidos=true; //en caso de fallar en alguna conversion        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            try {                
                String fecha =  quitaEspacios(sdf.format(sdf.parse(String.valueOf(tabla.getValueAt(i, 3)).substring(0, 10))));                
                if (fecha.charAt(0)=='0') { //sirve para eliminar el cero que se pone para al dia en la importcion
                    fecha= fecha.substring(1,fecha.length());                                        
                }
                String sucursal = quitaEspacios(String.valueOf(tabla.getValueAt(i, 4)));                                
                String encargado = quitaEspacios(String.valueOf(tabla.getValueAt(i, 5))); 
                String turnoString = String.valueOf(tabla.getValueAt(i,6));
                char turnoChar = turnoString.charAt(0);
                nombreProductos = new Vector<String>();
                cantProductos = new Vector<Integer>();
                int contColumna=7;
                while(!tabla.getColumnName(contColumna).equals("Foto de Hoja")){ //asi llenamos con los produtos los vectores
                    nombreProductos.add(quitaEspacios(tabla.getColumnName(contColumna)));                                        
                    cantProductos.add(Math.round(Float.parseFloat(String.valueOf(tabla.getValueAt(i, contColumna)))));
                    contColumna++;
                }                
                String fotoHoja = String.valueOf(tabla.getValueAt(i, contColumna));                
                if (fotoHoja.equals(""))  //validar que la tabla este en blanco
                    fotoHoja="Sin_Link";                                
                String nota= quitaEspacios(String.valueOf(tabla.getValueAt(i, contColumna+1)));
                String fotoFirma = String.valueOf(tabla.getValueAt(i, contColumna+2));                
                if (fotoFirma.equals("")) //validar que la tabla este en blanco
                    fotoFirma="Sin_Link";                       
                String rutero = quitaEspacios(String.valueOf(tabla.getValueAt(i, contColumna+5)));
                //cambiar la cantidad de los productos por su correspondiente en presentacion
                Vector<Producto> productos = Producto.cargarProductos();
                for (int j = 0; j < productos.size(); j++) {
                    for (int k = 0; k < cantProductos.size(); k++) {
                        if (nombreProductos.elementAt(k).equals(productos.elementAt(j).getNombre())) {
                            cantProductos.setElementAt(cantProductos.elementAt(k) / productos.elementAt(j).getPresentacion(), k);
                        }
                    }
                }
                if (sucursal.isEmpty() || fecha.isEmpty() || encargado.isEmpty() || turnoString.isEmpty() ) {
                    throw new Exception();
                }
                Movimiento m = new Movimiento(fecha,turnoChar,"Entrega_Sucursal",rutero,sucursal,nota,nombreProductos,cantProductos,encargado,Loged.getLoged().getUsuario());                
                m.setLinkHoja(fotoHoja);
                m.setLinkFirma(fotoFirma);
                movsImportacion.add(m);
                //System.out.println(m.toString());
            } catch (Exception e) {               
                valoresValidos=false;
                JOptionPane.showMessageDialog(null, "Error de conversion de valores de la tabla en la fila: "+ (i+1) +", verificar que los datos sean correctos", "Atención", JOptionPane.WARNING_MESSAGE);
                ///pintar en la tabla la fila con error de color amarillo                
                e.printStackTrace();
                break;
            }
        }
//        for (int i = 0; i < movsImportacion.size(); i++) {
//            System.out.println(movsImportacion.elementAt(i).toString());
//        }
        if (valoresValidos) {
            //ahora el con el vector de movimientos sacar de el aquellos que ya existan y pasarlos a otro vector        
            for (int i = 0; i < movimientos.size(); i++) {
                for (int j = 0; j < movsImportacion.size(); j++) {                            
                    if (movimientos.elementAt(i).equals(movsImportacion.elementAt(j))) {                    
                        movsImportacionExistentes.add(Movimiento.copy(movsImportacion.elementAt(j)));
                        movsImportacion.removeElementAt(j);
                    }
                }
            }

            try {//actualizando el archivo
                FileWriter fr = new FileWriter("Archivos/Movimientos.data",true);
                PrintWriter pw = new PrintWriter(fr);
                for (int i = 0; i < movsImportacion.size(); i++) {
                    pw.println(movsImportacion.elementAt(i).toString());
                }
                pw.close();
                //aplicar cambio al inventario del rutero, reducir existencia de todas las entregas
                Vector<InventarioRutero> invRuteros = InventarioRutero.cargarInvRuteros();                
                //actualizar el inventario del rutero
                for (int i = 0; i < movsImportacion.size(); i++) { // i = movimiento
                    for (int j = 0; j < invRuteros.size(); j++) {        //j = rutero                            
                        if (movsImportacion.elementAt(i).getOrigen().equals(invRuteros.elementAt(j).getRutero())) {                            
                            invRuteros.elementAt(j).reducirExistencia(movsImportacion.elementAt(i));                              
                        }
                    }
                }
                InventarioRutero.actualizarBD(invRuteros);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error I/O en actualizacion de Movimientos.data", "Atención", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            btnCancelarActionPerformed(null);

            //lanzar la lista de movsImportacionExistentes en caso de haber
            if (!movsImportacionExistentes.isEmpty()) {
                ListaMovsExistentes listaMovs = new ListaMovsExistentes(movsImportacionExistentes);
                listaMovs.setLocationRelativeTo(null);
                listaMovs.setVisible(true);
            }
        }
    }//GEN-LAST:event_btnImportarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ConsultaMovimientos c = new ConsultaMovimientos();
        c.setLocationRelativeTo(null);
        c.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnImportar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
