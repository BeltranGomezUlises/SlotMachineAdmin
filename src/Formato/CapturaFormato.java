package Formato;

import ControlUsuario.Loged;
import ControlUsuario.Usuario;
import static Utilerias.Utileria.*;
import Inventario.InventarioRutero;
import Inventario.Movimiento;
import Productos.Producto;
import Sucursal.Sucursal;
import java.awt.event.KeyEvent;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CapturaFormato extends javax.swing.JFrame {
    
    int turnos;    
    Vector<Producto> productos;
    Vector<Sucursal> sucursales;  
   
    public CapturaFormato() {
        
        initComponents();        
        this.setTitle("Captura de Formato");    
        tabla.getTableHeader().setReorderingAllowed(false);
        //llenar los combo box con los datos de los archivos
        try{
            
            sucursales = Sucursal.cargarSucursalesConProductos();
            for (int i = 0; i < sucursales.size(); i++) {
                cmbSucursal.addItem(quitaGuion(sucursales.elementAt(i).getNombre()));
            }
            //El combo box de los encargados
            FileReader fr1 = new FileReader("Archivos/Encargados.bin");
            BufferedReader br1 = new BufferedReader(fr1);
            String linea1= br1.readLine();
            while(linea1!=null){
                cmbEncargado.addItem(linea1);
                linea1=br1.readLine();
            }
            br1.close();

            int anchos[]={40,20,20,20}; //cargar la tabla
            for (int i = 0; i < anchos.length; i++) {//ajustar anchos                
               tabla.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);             
            }
            
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(rootPane, "Base de datos no encontrada o inutilizable-Encargados.bin", "Error", WIDTH);
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Base de datos no encontrada o inutilizable-Encargados.bin", "Error", WIDTH);
        }        
    }
    
    private void limpiarCaptura(){
        for (int i = 0; i < tabla.getRowCount(); i++) {//vaciar la tabla
            tabla.setValueAt("", i, 1);
            tabla.setValueAt(0, i, 2);
            tabla.setValueAt("", i, 3);                                                
        }
        txtRetiros.setText("0");                    
        txtNota.setText("");                    
        chkBoxFirma.setSelected(false);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel11 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        cmbSucursal = new javax.swing.JComboBox();
        lbSucursal = new javax.swing.JLabel();
        cmbEncargado = new javax.swing.JComboBox();
        lbEncargado = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txtFondoFinal = new javax.swing.JTextField();
        txtFondoInicial = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        calendar = new com.toedter.calendar.JDateChooser();
        cmbTurno = new javax.swing.JComboBox();
        lbTurno = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtRetiros = new javax.swing.JTextField();
        chkBoxFirma = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Captura de Formato");
        setResizable(false);

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Inv. Inicial", "Compras", "Inv. Final"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaKeyTyped(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        btnAceptar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnAceptarKeyTyped(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        btnCancelar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCancelarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addGap(3, 3, 3))
        );

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        cmbSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSucursalActionPerformed(evt);
            }
        });

        lbSucursal.setText("Sucursal:");

        lbEncargado.setText("Encargado:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbSucursal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbEncargado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbEncargado)
                .addComponent(cmbEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbSucursal)
                .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel22.setText("Fondo Final:");

        txtFondoFinal.setText("0");
        txtFondoFinal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFondoFinalKeyTyped(evt);
            }
        });

        txtFondoInicial.setText("0");
        txtFondoInicial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtFondoInicialKeyTyped(evt);
            }
        });

        jLabel21.setText("Fondo Inicial:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFondoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFondoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel21)
                        .addComponent(txtFondoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel22)
                        .addComponent(txtFondoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel19.setText("Fecha:");

        lbTurno.setText("Turno:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTurno)
                .addGap(18, 18, 18)
                .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTurno)
                        .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel23.setText("Nota:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addGap(3, 3, 3))
        );

        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setText("Retiros:");

        txtRetiros.setText("0");

        chkBoxFirma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkBoxFirma.setText("Firmado");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRetiros, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkBoxFirma)
                .addGap(18, 18, 18))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtRetiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkBoxFirma)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void cmbSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSucursalActionPerformed
        
        
        turnos = sucursales.elementAt(cmbSucursal.getSelectedIndex()).getTurnos();
                
        if(turnos == 2){
            cmbTurno.removeAllItems();
            cmbTurno.addItem("D");
            cmbTurno.addItem("N");
            turnos = 2;
        }else{
            cmbTurno.removeAllItems();
            cmbTurno.addItem("D");
            turnos = 1;                
        } 
        
        //generar la lista de productos a poner en la tabla
        DefaultTableModel dtm = (DefaultTableModel) tabla.getModel();
        dtm.setRowCount(0); //para vaciar
        
        productos= new Vector<Producto>();
        Vector<Producto> productosArchivo = Producto.cargarProductos();
        for (String nomProducto : sucursales.get(cmbSucursal.getSelectedIndex()).getNomProductos()) {            
            for (Producto producto : productosArchivo) {
                if (producto.getNombre().equals(nomProducto)) {
                    productos.add(producto);
                    break;
                }
            }
        }
        for (Producto producto : productos) {
            dtm.addRow(new String[]{quitaGuion(producto.getNombre()), "", "0"});
        }       
    }//GEN-LAST:event_cmbSucursalActionPerformed
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{
            DateFormat df = DateFormat.getDateInstance();
            //almacenar todos los datos en variables
            String fecha= df.format(calendar.getDate());
            String encargado=quitaEspacios(cmbEncargado.getSelectedItem().toString());
            String sucursal= quitaEspacios(cmbSucursal.getSelectedItem().toString());
            char turno=cmbTurno.getSelectedItem().toString().charAt(0);
            float fondoInicial= Float.parseFloat(txtFondoInicial.getText());
            float fondoFinal = Float.parseFloat(txtFondoFinal.getText());
            Vector<Integer> invI= new Vector<Integer>();
            Vector<Integer> invF= new Vector<Integer>();
            Vector<Integer> compras = new Vector<Integer>();                
            for (int i = 0; i < productos.size(); i++) {
                 invI.add(Integer.parseInt(tabla.getValueAt(i, 1).toString()));
                 compras.add(Integer.parseInt(tabla.getValueAt(i, 2).toString()));
                 invF.add(Integer.parseInt(tabla.getValueAt(i, 3).toString()));
            }                
            boolean firmado=chkBoxFirma.isSelected();                               
            Formato f= new Formato(fecha,sucursal,encargado,turno,productos,invI,compras,invF,firmado);
            float retiro=Float.parseFloat(txtRetiros.getText());
            String nota = quitaEspacios(txtNota.getText());
            f.setRetiro(retiro);
            f.setTurnos(turnos);
            f.setPagado(false);
            f.setFondoInicial(fondoInicial);
            f.setFondoFinal(fondoFinal);
            if (nota.equals("")) {
                 nota="null";
            }
            f.setNota(nota);
            //Validar que no exista ese formato:
            Vector<Formato> formatos = Formato.cargarFormatos();

            boolean existe=false;
            boolean turnoDuplicado=false;
            for(int k=0; k<formatos.size(); k++){                    
                if(formatos.elementAt(k).getFecha().equals(f.getFecha()) && formatos.elementAt(k).getTurno()==f.getTurno() && formatos.elementAt(k).getSucursal().equals(f.getSucursal())){
                    existe=true;
                }
                if(formatos.elementAt(k).getFecha().equals(f.getFecha()) && formatos.elementAt(k).getTurno()==f.getTurno() && formatos.elementAt(k).getEncargado().equals(quitaGuion(f.getEncargado()))){
                    turnoDuplicado=true;
                }
            }                               

            //Escribir en el archivo de base de datos                 
            if(!existe && !turnoDuplicado){                    
                boolean comp=false;                    
                for (int i = 0; i < f.getCompras().size(); i++) {                        
                    if (f.getCompras().elementAt(i).compareTo(new Integer(0))!=0) {
                        comp=true;
                    }
                }                    
                if (comp){                                    
                    String entregador;
                    //cargar un arreglo con los ruteros
                    Vector<InventarioRutero> invRuteros = InventarioRutero.cargarInvRuteros();

                    String nombreRuteros[] = new String[invRuteros.size()];
                    for (int i = 0; i < invRuteros.size(); i++) {
                        nombreRuteros[i]=quitaGuion(invRuteros.elementAt(i).getRutero());
                    }                        
                    entregador=quitaEspacios((String)JOptionPane.showInputDialog(null, "Seleccione el rutero","Ruteros", JOptionPane.QUESTION_MESSAGE,null, nombreRuteros, 0));                                               
                    f.setEntregador(entregador);                       
                    //generar movimiento si existe entrega en el turno
                    //tomar las compras de los productos y dividirlos en su presentacion                        
                    Vector<Integer> cantProductos = new Vector<>();
                    for (int i = 0; i < productos.size(); i++) {
                        cantProductos.add(f.getCompras().elementAt(i) / productos.elementAt(i).getPresentacion());
                    }
                    Movimiento mov = new Movimiento(f.getFecha(),f.getTurno(),"Entrega_Sucursal",entregador,f.getSucursal(),
                                                    "null",f.NombresProductos(), cantProductos,encargado,quitaEspacios(Loged.getLoged().getUsuario()),"null","null");                        
                    //buscar que el movimiento no exista ya
                    boolean movExiste=false;
                    int indiceMovRepetido=-1;
                    Vector<Movimiento> movs = Movimiento.CargarMovimientos();
                    for (int i = 0; i < movs.size(); i++) {
                        if (mov.equals(movs.elementAt(i))) {
                            movExiste=true;
                            indiceMovRepetido=i;
                        }
                    }
                    if (movExiste) {
                        JOptionPane.showMessageDialog(null, "Ya existe el movimiento se omitirá el registro al inventario","Atención",JOptionPane.WARNING_MESSAGE);
                        //comparar el mov con el que ya existe en movs
                        boolean discrepancia = false;
                        for (int i = 0; i < movs.elementAt(indiceMovRepetido).getProductos().size(); i++) {
                            for (int j = 0; j < mov.getProductos().size(); j++) {
                                if (movs.elementAt(indiceMovRepetido).getProductos().elementAt(i).equals(mov.getProductos().elementAt(j))) { //si son el mismo producto
                                    if (movs.elementAt(indiceMovRepetido).getCantidades().elementAt(i)-mov.getCantidades().elementAt(j)!=0) {
                                        discrepancia = true;
                                    }
                                }
                            }
                        }
                        if (discrepancia) {
                            JOptionPane.showMessageDialog(null, "El Movimiento Generado por el formato, no coincide con los datos del movimiento capturado por importación, reviselo","Atención",JOptionPane.WARNING_MESSAGE);
                        }
                    }else{
                        //Agregar al archivo
                        FileWriter frMov = new FileWriter("Archivos/Movimientos.data",true);
                        PrintWriter pwMov= new PrintWriter(frMov);
                        pwMov.println(mov.toString());
                        pwMov.close();                             
                        //actualizar el inventario del rutero
                        for (int i = 0; i < invRuteros.size(); i++) {
                            if (invRuteros.elementAt(i).getRutero().equals(entregador)) {  //i=entregador                                  
                                 invRuteros.elementAt(i).reducirExistencia( mov );                                    
                            }
                        }
                        InventarioRutero.actualizarBD(invRuteros);                            
                    }
                }        

                FileWriter fr = new FileWriter("Archivos/Formatos.bin",true);
                PrintWriter pw= new PrintWriter(fr);
                pw.println(f.toString());
                pw.close();                    

                JOptionPane.showMessageDialog(rootPane, "Formato Agregado con Éxito", "Éxito", WIDTH);//mostrar mensaje y limpiar pantalla
                this.limpiarCaptura();                   
            }else{
                if(turnoDuplicado){
                    JOptionPane.showMessageDialog(rootPane, "El encargado que ingresó ya trabajó en esa fecha", "Error", WIDTH);
                }
                if(existe){
                  JOptionPane.showMessageDialog(rootPane, "El Formato que está ingresando ya existe", "Error", WIDTH); 
                }                    
            }
        }catch(NullPointerException e){
             JOptionPane.showMessageDialog(rootPane, "No ingresó Fecha al Formato ó existe un valor en blanco de la tabla", "ERROR", WIDTH);                     
        }catch(NumberFormatException e){
             JOptionPane.showMessageDialog(rootPane, "Ingresa todos los datos correctamente por favor", "ERROR", WIDTH);                                            
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Base de datos no existe o es inutilisable", "ERROR", WIDTH);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed
    private void txtFondoInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFondoInicialKeyTyped
        char tecla;
        tecla=evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
        }
    }//GEN-LAST:event_txtFondoInicialKeyTyped
    private void txtFondoFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFondoFinalKeyTyped
        char tecla;
        tecla=evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
        }
    }//GEN-LAST:event_txtFondoFinalKeyTyped
    private void btnAceptarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAceptarKeyTyped
    private void btnCancelarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyTyped
       char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnCancelarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCancelarKeyTyped

    private void tablaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaKeyTyped
        if (!Character.isDigit(evt.getKeyChar())) {
            evt.consume();
        }
    }//GEN-LAST:event_tablaKeyTyped
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private com.toedter.calendar.JDateChooser calendar;
    private javax.swing.JCheckBox chkBoxFirma;
    private javax.swing.JComboBox cmbEncargado;
    private javax.swing.JComboBox cmbSucursal;
    private javax.swing.JComboBox cmbTurno;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbEncargado;
    private javax.swing.JLabel lbSucursal;
    private javax.swing.JLabel lbTurno;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtFondoFinal;
    private javax.swing.JTextField txtFondoInicial;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtRetiros;
    // End of variables declaration//GEN-END:variables
}
