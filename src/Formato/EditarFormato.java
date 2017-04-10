package Formato;

import Encargado.Encargado;
import static Utilerias.Utileria.*;
import Inventario.InventarioRutero;
import Inventario.Movimiento;
import Productos.Producto;
import Sucursal.Sucursal;
import Utilerias.Filtro;
import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EditarFormato extends javax.swing.JFrame {   
    Formato editarFormato;    
    Vector<Formato> misFormatos= new Vector<Formato>();
    Vector<Producto> productos;
    Vector<Sucursal> sucursales = Sucursal.cargarSucursales();
    Filtro filtro;
    int turnos;
    
    public EditarFormato() {
        initComponents();
    }
    
    public EditarFormato(Vector<Formato> Formatos,Formato miFormato, Filtro filtro) {        
        initComponents();   
        this.filtro = filtro;       
        this.editarFormato=miFormato;        
        misFormatos = new Vector<>(Formatos);
        String datos[][]={};
        String cabecera[]={"Nombre","Inv. Inicial","Compras","Inv. Final"};
        int anchos[]={40,20,20,20}; //cargar la tabla
        DefaultTableModel md = new DefaultTableModel(datos, cabecera);
        tabla.setModel(md);
        
        try{                                    
            //llenar el combo box sucursales
            Vector<String> StrSucursales = new Vector<String>();
            for (int i = 0; i < sucursales.size(); i++) {
                cmbSucursal.addItem(quitaGuion(sucursales.elementAt(i).getNombre()));
                StrSucursales.add(sucursales.elementAt(i).getNombre());
            }
            //si no existe en el archivo la sucursal del formato
            if (!StrSucursales.contains(quitaEspacios(editarFormato.getSucursal()))){
                cmbSucursal.addItem(quitaGuion(editarFormato.getSucursal()));               
            }
            
            //El combo box de los encargados            
            List<Encargado> encargados = Encargado.cargarEncargados();                        
            
            //llenar el combo box de encargados
            for(int i = 0; i < encargados.size(); i++) {
               cmbEncargado.addItem(encargados.get(i).getNombre());
            }            
            
            //llenar el comboBox de los ruteros y buscar el rutero en la lista para dejarlo como default
            cmbRutero.addItem("No Registrado");
            FileReader frRutero = new FileReader("Archivos/Ruteros.data");
            BufferedReader brRutero = new BufferedReader(frRutero); 
            StringTokenizer token;
            String linea;
            while((linea=brRutero.readLine())!=null){
                token=new StringTokenizer(linea);                
                String rutero=quitaGuion(token.nextToken());
                cmbRutero.addItem(rutero);
            }
            //buscar el rutero que tiene el formato y dejarlo seleccionado
            cmbRutero.setSelectedItem(editarFormato.getEntregador());
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(rootPane, "Base de datos no encontrada o inutilizable", "Error", WIDTH);
        }catch(IOException e){   
            JOptionPane.showMessageDialog(rootPane, "IOException en llenado de cmb", "Error", WIDTH);
        }
        //llenar todos los campos con los datos del formato enviado
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        try {
//            calendar.getJCalendar().setDate(df.parse(editarFormato.getFecha()));
//            System.out.println(editarFormato.getFecha());
            calendar.setDate(df.parse(editarFormato.getFecha()));
        } catch (ParseException e) {
        }        
        //colocar como default los valores del formato a editiar
        cmbSucursal.setSelectedItem(quitaGuion(editarFormato.getSucursal()));            
        cmbEncargado.setSelectedItem(quitaGuion(editarFormato.getEncargado()));
        
        txtFondoInicial.setText(String.valueOf(editarFormato.getFondoInicial()));
        txtFondoFinal.setText(String.valueOf(editarFormato.getFondoFinal()));
        //llenar tabla con los productos de el formato y los inventario
        productos= editarFormato.getProductos();
        Vector<Integer> invI= editarFormato.getInvI();
        Vector<Integer> compras= editarFormato.getCompras();
        Vector<Integer> invF= editarFormato.getInvF();        
        for (int i = 0; i < productos.size(); i++) {
            Vector fila= new Vector();
            fila.add(productos.elementAt(i).getNombre());
            fila.add(invI.elementAt(i));
            fila.add(compras.elementAt(i));
            fila.add(invF.elementAt(i));
            md.addRow(fila);
        }
        
        txtRetiros.setText(String.valueOf(editarFormato.getRetiro()));
        chkBoxPagado.setSelected(editarFormato.getPagado());        
        chkBoxFirma.setSelected(editarFormato.getFirma());
        if (!editarFormato.getNota().equals("null")) {
            txtNota.setText(quitaGuion(editarFormato.getNota()));
        }
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel9 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtRetiros = new javax.swing.JTextField();
        chkBoxPagado = new javax.swing.JCheckBox();
        chkBoxFirma = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
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
        jLabel1 = new javax.swing.JLabel();
        cmbRutero = new javax.swing.JComboBox<String>();
        jPanel11 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Formato");
        setResizable(false);

        jPanel9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel20.setText("Retiros:");

        txtRetiros.setText("0");

        chkBoxPagado.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        chkBoxPagado.setText("Pagado");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(chkBoxPagado)
                .addGap(18, 18, 18)
                .addComponent(chkBoxFirma)
                .addGap(22, 22, 22))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtRetiros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkBoxPagado)
                    .addComponent(chkBoxFirma)))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        cmbSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSucursalActionPerformed(evt);
            }
        });

        lbSucursal.setText("Sucursal:");

        cmbEncargado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEncargadoActionPerformed(evt);
            }
        });

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
                .addGap(18, 18, 18)
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

        cmbTurno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "D", "N" }));

        lbTurno.setText("Turno:");

        jLabel1.setText("Entrego:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbTurno)
                .addGap(2, 2, 2)
                .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbRutero, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbTurno)
                        .addComponent(cmbTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)
                        .addComponent(cmbRutero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel23.setText("Nota:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel23)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSucursalActionPerformed
     
        String selected = quitaEspacios(cmbSucursal.getSelectedItem().toString());
        for (int i = 0; i < sucursales.size(); i++) {
            if (selected.equals(sucursales.elementAt(i).getNombre())) {
                if (sucursales.elementAt(i).getTurnos()==2) {
                    cmbTurno.removeAllItems();
                    cmbTurno.addItem("D");
                    cmbTurno.addItem("N");
                    turnos=2;
                    break;
                }else{
                    cmbTurno.removeAllItems();
                    cmbTurno.addItem("D");
                    turnos=1;
                    break;
                }
            }
        }        
        
        if(editarFormato.getTurno()=='D'){
            cmbTurno.setSelectedIndex(0);
        }else{
            cmbTurno.setSelectedIndex(1);
        }
    }//GEN-LAST:event_cmbSucursalActionPerformed
    private void txtFondoFinalKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFondoFinalKeyTyped
        char tecla;
        tecla=evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
        }
    }//GEN-LAST:event_txtFondoFinalKeyTyped
    private void txtFondoInicialKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFondoInicialKeyTyped
        char tecla;
        tecla=evt.getKeyChar();
        if(!Character.isDigit(tecla)){
            evt.consume();
        }
    }//GEN-LAST:event_txtFondoInicialKeyTyped
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
            float retiro=Float.parseFloat(txtRetiros.getText());
            boolean pagado =chkBoxPagado.isSelected();            
            String nota;
            if (txtNota.getText().equals(""))                
                nota="null";            
            else                
                nota = quitaEspacios(txtNota.getText());
                                                
            //Validar que no exista ese formato:                       
            //buscar el formato a editar y borrarlo en el vector de formatos
            //PARA REESCRIBIR, BUSCAR EL FORMATO VIEJO Y ELIMINARLO DEL VECTOR, PARA ESCRIBIR EL NUEVO            
            int indexFormatoEditar=0;
            boolean formatoEncontrado=false;
            for(int k=0; k<misFormatos.size(); k++){
                if(misFormatos.elementAt(k).getFecha().equals(editarFormato.getFecha()) && misFormatos.elementAt(k).getTurno()==editarFormato.getTurno() && misFormatos.elementAt(k).getSucursal().equals(editarFormato.getSucursal()) ){            
                    indexFormatoEditar=k;
                    formatoEncontrado=true;                         
                    break;
                }
            }
            //necesitamos saber si cambio el rutero que hizo la entrega  para el cambio de los inventarios
            String nuevoRutero=quitaEspacios(cmbRutero.getSelectedItem().toString());            
            boolean ruteroDiferente=false;
            
            if (!nuevoRutero.equals(editarFormato.getEntregador())) {
                ruteroDiferente=true;
            }
                        
            //buscar el movimiento generado por el formato y tomarlo
            Vector<Movimiento> movs=Movimiento.CargarMovimientos();
            int indexMovEditar=0;
            boolean movEncontrado=false;            
            for (int i = 0; i < movs.size(); i++) {                
                if(movs.elementAt(i).getFecha().equals(editarFormato.getFecha()) && 
                        movs.elementAt(i).getTurno()==editarFormato.getTurno() &&                         
                        movs.elementAt(i).getDestino().equals(editarFormato.getSucursal()) && 
                        movs.elementAt(i).getOrigen().equals(editarFormato.getEntregador()))
                {                    

                    indexMovEditar=i; 
                    movEncontrado=true;                    
                }                 
            }
            
            Formato nuevoFormato = new Formato(editarFormato.getFecha(),editarFormato.getSucursal(),editarFormato.getEncargado(),editarFormato.getTurno(),editarFormato.getProductos(),editarFormato.getInvI(),editarFormato.getCompras(),editarFormato.getInvF(),editarFormato.getFirma());            
            nuevoFormato.setFecha(fecha);
            nuevoFormato.setTurno(turno);
            nuevoFormato.setTurnos(turnos);
            nuevoFormato.setSucursal(sucursal);
            nuevoFormato.setEncargado(encargado);
            nuevoFormato.setFondoInicial(fondoInicial);
            nuevoFormato.setFondoFinal(fondoFinal);
            nuevoFormato.setInvI(invI);
            nuevoFormato.setInvF(invF);
            nuevoFormato.setCompras(compras);
            nuevoFormato.setRetiro(retiro);
            nuevoFormato.setFirma(firmado);
            nuevoFormato.setPagado(pagado);
            nuevoFormato.setNota(nota);               
            if (ruteroDiferente) {
                nuevoFormato.setEntregador(nuevoRutero);
            }else{
                nuevoFormato.setEntregador(editarFormato.getEntregador());
            }
                        
            boolean existe=false;
            boolean turnoDuplicado=false;
            
            for(int k=0; k<misFormatos.size(); k++){
                if (k!=indexFormatoEditar) {
                    if(misFormatos.elementAt(k).getFecha().equals(nuevoFormato.getFecha()) && misFormatos.elementAt(k).getTurno()==nuevoFormato.getTurno() && misFormatos.elementAt(k).getSucursal().equals(nuevoFormato.getSucursal())){
                        existe=true;
                    }
                    if(misFormatos.elementAt(k).getFecha().equals(nuevoFormato.getFecha()) && misFormatos.elementAt(k).getTurno()==nuevoFormato.getTurno() && misFormatos.elementAt(k).getEncargado().equals(quitaGuion(nuevoFormato.getEncargado()))){
                        turnoDuplicado=true;
                    }
                }else{
                    //omitir el indice del formato a editar                   
                }                
            }
            //Escribir en el archivo de base de datos
            if(!existe && !turnoDuplicado){                
                //sacar la diferencia de las comprar para modificar en el inventario del rutero
                Vector<Integer> diferencia = new Vector<Integer>();
                Vector<String> nombreProd = new Vector<String>();
                for (int j = 0; j < editarFormato.getCompras().size(); j++) {
                    nombreProd.add(editarFormato.NombresProductos().elementAt(j));
                    diferencia.add(editarFormato.getCompras().elementAt(j) - nuevoFormato.getCompras().elementAt(j));
                    //pueden ser tanto positivas como negativas, al actualizar en el inventario puede sumarce o restarce
                }
                //movimiento de diferencia solo para la edicion del formato pero con el mismo rutero y poder hacer el cambio de inventario adecuado
                Movimiento movDiferencia = new Movimiento("fecha",'D',"Entrega_sucursal", "entregador","sucursal","nota",nombreProd,diferencia,"Encargado","usuario");
                //Agregar el formato
                misFormatos.setElementAt(nuevoFormato, indexFormatoEditar);                
                ConsultaFormato.actualizarFormatos(misFormatos); //hasta aqui el formato ha sido editado
                //modificar el movimiento en indexMovEditar y sobreescribir en el archivo
                //crear nuevoMovimiento
                if (movEncontrado) {                    
                    Movimiento nm  = new Movimiento(nuevoFormato.getFecha(),nuevoFormato.getTurno(),"Entrega_Sucursal",nuevoFormato.getEntregador(),
                                                    nuevoFormato.getSucursal(),nuevoFormato.getNota(),nuevoFormato.NombresProductos(),
                                                    nuevoFormato.getCompras(), nuevoFormato.getEncargado(),movs.elementAt(indexMovEditar).getUsuario());
                    movs.setElementAt(nm, indexMovEditar);                    
                    //actualizar el archivo de moviemintos
                    FileWriter fr1 = new FileWriter("Archivos/Movimientos.data");
                    PrintWriter pw1= new PrintWriter(fr1);
                    for (int i = 0; i < movs.size(); i++) {
                        pw1.println(movs.elementAt(i).toString());
                    }                
                    pw1.close();                    
                    
                    //Editar el inventario del que realizo la entrega
                    Vector<InventarioRutero> inventarioRuteros = InventarioRutero.cargarInvRuteros();                    
                    try {                                              
                        //teniendo los ruteros y su inventario actulizar al que corresponde
                        if (ruteroDiferente) {
                             //al rutero anterior aumentarle la existencia y al nuevo reducirle la existencia entregada
                            for (int i = 0; i < inventarioRuteros.size(); i++) {
                                //aumentarle la existencia al rutero anterior                                
                                if (inventarioRuteros.elementAt(i).getRutero().equals(editarFormato.getEntregador())) {                                    
                                    inventarioRuteros.elementAt(i).aumentarExistencia( nm );                                                                       
                                }
                                //reducirle la existecia al nuevo rutero                                
                                if (inventarioRuteros.elementAt(i).getRutero().equals(nuevoFormato.getEntregador())) {                                                                        
                                    inventarioRuteros.elementAt(i).reducirExistencia( nm );                                                                       
                                }
                            }                             
                        }else{ //si el rutero es el mismo nada mas aumentarle la diferencia de compras
                            for (int i = 0; i < inventarioRuteros.size(); i++) {
                                if (inventarioRuteros.elementAt(i).getRutero().equals(nuevoFormato.getEntregador())) {                                                                                                                                       
                                    inventarioRuteros.elementAt(i).aumentarExistencia( movDiferencia );                                   
                                    break;
                                }
                            } 
                        }                        
                        InventarioRutero.actualizarBD(inventarioRuteros);                        
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Error actualizando el inventario de los ruteros","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }                                
                JOptionPane.showMessageDialog(null, "Formato Editado con Éxito", "Éxito", WIDTH);//mostrar mensaje y limpiar pantalla
                btnCancelarActionPerformed(null);
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
            e.printStackTrace();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Ingresa todos los datos correctamente por favor", "ERROR", WIDTH);
            e.printStackTrace();
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Base de datos no existe o es inutilisable", "ERROR", WIDTH);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed
    private void btnAceptarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==evt.VK_ENTER){
            this.btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAceptarKeyTyped
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                      
        ConsultaFormato cf = new ConsultaFormato(filtro);
        cf.setVisible(true);
        cf.setLocationRelativeTo(null);
        setCursor(null);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    private void btnCancelarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==evt.VK_ENTER){
            this.btnCancelarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCancelarKeyTyped

    private void cmbEncargadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEncargadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbEncargadoActionPerformed

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
    private javax.swing.JCheckBox chkBoxPagado;
    private javax.swing.JComboBox cmbEncargado;
    private javax.swing.JComboBox<String> cmbRutero;
    private javax.swing.JComboBox cmbSucursal;
    private javax.swing.JComboBox cmbTurno;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
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
