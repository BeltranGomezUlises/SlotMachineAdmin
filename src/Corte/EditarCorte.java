package Corte;

import ControlUsuario.Usuario;
import static Utilerias.Utileria.*;
import Maquinas.Maquina;
import Sucursal.Sucursal;
import Utilerias.Filtro;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;

public class EditarCorte extends javax.swing.JFrame {
    
    Corte corteEditar;
    Vector<Corte> cortes;
    Vector<Maquina>  maquinas = Maquina.cargarMaquinas();    
    Vector<Maquina> maquinasFiltradas;    
    Vector<Sucursal> sucursales;
    DateFormat df = new SimpleDateFormat("d/MM/yyyy");
    Filtro filtro;
    
    public EditarCorte() {
        initComponents();
    }
    
    public EditarCorte(Corte c, Vector<Corte> cortes, Filtro filtro) {
        initComponents();        
        this.corteEditar = c;
        this.cortes = cortes;
        this.filtro=filtro;
        //llenar los datos del corte en los campos
                
        try {
            calendar.setDate(df.parse(corteEditar.getFecha()));
        } catch (ParseException e) { 
            
        } 
//        maquinas = Maquina.cargarMaquinas();        
        sucursales = Sucursal.cargarSucursales();
        for (int i = 0; i < sucursales.size(); i++) {
           cmbSucursal.addItem(quitaGuion(sucursales.elementAt(i).getNombre()));
        }
        try{
            //El combo box de los encargados
            FileReader fr1 = new FileReader("Archivos/Encargados.bin");
            BufferedReader br1 = new BufferedReader(fr1);
            String linea1= br1.readLine();
            while(linea1!=null){
                cmbEncargado.addItem(linea1);
                linea1=br1.readLine();
            }
            br1.close();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error de lectura en archivo de encargados");
        } 
        
        //llenar los datos de los textfield                
        txtPantIn.setText(String.valueOf(corteEditar.getPantIn()));
        txtPantOut.setText(String.valueOf(corteEditar.getPantOut()));
        txtPantInOut.setText(String.valueOf(corteEditar.getPantInOut()));
        txtContPos.setText(String.valueOf(corteEditar.getContPos()));
        txtContNeg.setText(String.valueOf(corteEditar.getContNeg()));
        
        txtInOut.setText(String.valueOf(corteEditar.getInOut()));
        if (!corteEditar.getNota().equals("null")) {
            txtNota.setText(quitaGuion(corteEditar.getNota()));
        }
        //CheckBoxs de los fallos
        chkFalloContador.setSelected(corteEditar.isFalloContador());
        chkFalloPant.setSelected(corteEditar.isFalloPantalla());
        chkFalloPendiente.setSelected(corteEditar.isFalloPendiente());
        chkVueltaContPos.setSelected(corteEditar.isVueltaContadorPos());
        chkVueltaContNeg.setSelected(corteEditar.isVueltaContadorNeg());
        chkRobo.setSelected(corteEditar.isFalloRobo());
        txtRobo.setEnabled(corteEditar.isFalloRobo());
        txtRobo.setText(String.valueOf(corteEditar.getRobo()));
        
        cmbSucursal.setSelectedItem(quitaGuion(corteEditar.getSucursal()));
        cmbMaquina.setSelectedItem(quitaGuion(corteEditar.getMaquina().getNombre()));
        
        txtFondo.setText(String.valueOf(corteEditar.getMaquina().getFondo()));
        txtPrestRecuperable.setText(String.valueOf(corteEditar.getMaquina().getPrestRecu()));
        txtPrestNoRecuperable.setText(String.valueOf(corteEditar.getMaquina().getPrestNoRecu()));
        
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel7 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        cmbMaquina = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        txtTarjeta = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        calendar = new com.toedter.calendar.JDateChooser();
        lbSucursal = new javax.swing.JLabel();
        cmbSucursal = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        lbEncargado = new javax.swing.JLabel();
        cmbEncargado = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtPantOut = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPantIn = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPantInOut = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtInOut = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        txtContNeg = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtContPos = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        txtNota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        chkFalloContador = new javax.swing.JCheckBox();
        chkFalloPant = new javax.swing.JCheckBox();
        chkFalloPendiente = new javax.swing.JCheckBox();
        chkRobo = new javax.swing.JCheckBox();
        chkVueltaContPos = new javax.swing.JCheckBox();
        txtRobo = new javax.swing.JTextField();
        chkVueltaContNeg = new javax.swing.JCheckBox();
        chkDescompostura = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        btnMasFondo = new javax.swing.JButton();
        btnMenosFondo = new javax.swing.JButton();
        btnMenorPrestRecu = new javax.swing.JButton();
        btnMasPrestRecu = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        txtFondo = new javax.swing.JTextField();
        btnPrestNoRecu = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtPrestRecuperable = new javax.swing.JTextField();
        txtPrestNoRecuperable = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar Corte");

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel21.setText("#. Maquina:");

        cmbMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbMaquinaActionPerformed(evt);
            }
        });

        jLabel8.setText("Tarjeta:");

        txtTarjeta.setEditable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel21)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTarjeta)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(txtTarjeta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel19.setText("Fecha:");

        lbSucursal.setText("Sucursal:");

        cmbSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSucursalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lbSucursal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbSucursal)
                        .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(calendar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lbEncargado.setText("Encargado:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbEncargado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmbEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbEncargado)
                .addComponent(cmbEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(5, 5, 5))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Pantalla"));

        txtPantOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPantOutKeyTyped(evt);
            }
        });

        jLabel4.setText("Out:");

        txtPantIn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPantInKeyTyped(evt);
            }
        });

        jLabel5.setText("In:");

        jLabel6.setText("In-Out:");

        txtPantInOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPantInOutKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPantInOut, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPantOut, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPantIn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtPantIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtPantOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPantInOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Real"));

        jLabel7.setText("In-Out:");

        txtInOut.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtInOutKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(txtInOut, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInOut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(0, 0, 0))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Contadores"));

        txtContNeg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContNegActionPerformed(evt);
            }
        });
        txtContNeg.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContNegKeyTyped(evt);
            }
        });

        jLabel3.setText("Cont. (-):");

        txtContPos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtContPosKeyTyped(evt);
            }
        });

        jLabel2.setText("Cont. (+):");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtContNeg, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(txtContPos))
                .addGap(5, 5, 5))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtContPos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtContNeg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 11, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addGap(59, 59, 59)
                .addComponent(btnCancelar)
                .addGap(106, 106, 106))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        txtNota.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNotaKeyTyped(evt);
            }
        });

        jLabel1.setText("Nota:");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jPanel11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        chkFalloContador.setText("Fallo Contador");

        chkFalloPant.setText("Fallo Pantalla");

        chkFalloPendiente.setText("Pendiente");

        chkRobo.setText("Robo:");
        chkRobo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkRoboActionPerformed(evt);
            }
        });

        chkVueltaContPos.setText("Vuelta de Contador(+)");

        txtRobo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtRoboKeyTyped(evt);
            }
        });

        chkVueltaContNeg.setText("Vuelta de Contador(-)");

        chkDescompostura.setText("Descompostura");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(chkVueltaContNeg)
                                .addGap(61, 61, 61)
                                .addComponent(chkRobo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtRobo))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkFalloContador)
                                    .addComponent(chkVueltaContPos))
                                .addGap(56, 56, 56)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(chkFalloPendiente)
                                    .addComponent(chkFalloPant))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(13, 13, 13))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(chkDescompostura)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkFalloContador)
                    .addComponent(chkFalloPant))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkVueltaContPos)
                    .addComponent(chkFalloPendiente))
                .addGap(0, 0, 0)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(chkRobo)
                        .addComponent(txtRobo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(chkVueltaContNeg))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkDescompostura)
                .addContainerGap())
        );

        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        btnMasFondo.setText("+");
        btnMasFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasFondoActionPerformed(evt);
            }
        });

        btnMenosFondo.setText("-");
        btnMenosFondo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenosFondoActionPerformed(evt);
            }
        });

        btnMenorPrestRecu.setText("-");
        btnMenorPrestRecu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMenorPrestRecuActionPerformed(evt);
            }
        });

        btnMasPrestRecu.setText("+");
        btnMasPrestRecu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasPrestRecuActionPerformed(evt);
            }
        });

        jLabel9.setText("Fondo:");

        txtFondo.setEditable(false);

        btnPrestNoRecu.setText("Asignar");
        btnPrestNoRecu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrestNoRecuActionPerformed(evt);
            }
        });

        jLabel10.setText("P. recuperable:");

        jLabel11.setText("F. no recuerable:");

        txtPrestRecuperable.setEditable(false);

        txtPrestNoRecuperable.setEditable(false);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtPrestNoRecuperable)
                    .addComponent(txtPrestRecuperable)
                    .addComponent(txtFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnMasFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMenosFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(btnMasPrestRecu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMenorPrestRecu, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPrestNoRecu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMasFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenosFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPrestRecuperable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnMasPrestRecu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnMenorPrestRecu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtPrestNoRecuperable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPrestNoRecu, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMaquinaActionPerformed
        if (cmbMaquina.getSelectedIndex() != -1) {            
            txtTarjeta.setText(quitaGuion(maquinasFiltradas.elementAt(cmbMaquina.getSelectedIndex()).getTarjeta().getNombre()));
        }
    }//GEN-LAST:event_cmbMaquinaActionPerformed

    private void cmbSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSucursalActionPerformed
        String suc = quitaEspacios(cmbSucursal.getSelectedItem().toString());
        maquinasFiltradas= new Vector<Maquina>();
        for (int i = 0; i < maquinas.size(); i++) { //llenar masquinasFiltradas de maquinas con la sucursal actual
            if (maquinas.elementAt(i).getSucursal().getNombre().equals(suc)) {
                maquinasFiltradas.add(maquinas.elementAt(i));                
            }
        }
        cmbMaquina.removeAllItems();
        for (int i = 0; i < maquinasFiltradas.size(); i++) {
            cmbMaquina.addItem(maquinasFiltradas.elementAt(i).getNombre());
        }
        
    }//GEN-LAST:event_cmbSucursalActionPerformed

    private void txtPantOutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPantOutKeyTyped
        char tecla;
        tecla=evt.getKeyChar();        
        if (tecla == KeyEvent.VK_ENTER) {
            txtPantInOut.requestFocus();
        }
        if(!Character.isDigit(tecla)){
            evt.consume();
        }
    }//GEN-LAST:event_txtPantOutKeyTyped
    private void txtPantInKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPantInKeyTyped
        char tecla;        
        tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            txtPantOut.requestFocus();
        }
        if(!Character.isDigit(tecla)){
            evt.consume();
        }
    }//GEN-LAST:event_txtPantInKeyTyped
    private void txtPantInOutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPantInOutKeyTyped
        char tecla;
        tecla=evt.getKeyChar();        
        if (tecla == KeyEvent.VK_ENTER) {
            txtContPos.requestFocus();
        }
        if(!Character.isDigit(tecla) && tecla!='-'){
            evt.consume();
        }
    }//GEN-LAST:event_txtPantInOutKeyTyped
    private void txtInOutKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtInOutKeyTyped
        char tecla;
        tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            txtNota.requestFocus();
        }
        if(!Character.isDigit(tecla)&& tecla!='-'){
            evt.consume();
        }
    }//GEN-LAST:event_txtInOutKeyTyped
    private void txtContNegKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContNegKeyTyped
        char tecla;
        int digitos = txtContNeg.getText().length();
        tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            txtInOut.requestFocus();
        }
        if(!Character.isDigit(tecla) || (digitos > 5)){
            evt.consume();
        }
    }//GEN-LAST:event_txtContNegKeyTyped
    private void txtContPosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContPosKeyTyped
        char tecla;
        int digitos = txtContPos.getText().length();
        tecla=evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            txtContNeg.requestFocus();
        }
        if(!Character.isDigit(tecla) || (digitos > 5)){
            evt.consume();
        }
    }//GEN-LAST:event_txtContPosKeyTyped
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        try{            
            String fecha= df.format(calendar.getDate());
            String sucursal=quitaEspacios(cmbSucursal.getSelectedItem().toString());
            String encargado=quitaEspacios(cmbEncargado.getSelectedItem().toString());
            Maquina m = maquinasFiltradas.elementAt(cmbMaquina.getSelectedIndex()); 
            m.setPrestNoRecu(Integer.parseInt(txtPrestNoRecuperable.getText()));
            int contPos= Integer.parseInt(txtContPos.getText());
            int contNeg= Integer.parseInt(txtContNeg.getText());
            int pantIn= Integer.parseInt(txtPantIn.getText());
            int pantOut= Integer.parseInt(txtPantOut.getText());
            int pantInOut= Integer.parseInt(txtPantInOut.getText());
            int inOut= Integer.parseInt(txtInOut.getText());
            String nota = txtNota.getText();
            if (nota.equals("")) {
                nota="null";
            }else{
                nota = quitaEspacios(nota);
            }
             int robo = 0;
            if (!txtRobo.getText().equals(""))
                robo = Integer.parseInt(txtRobo.getText());     

            //guardar fallos
            boolean falloContador = chkFalloContador.isSelected();
            boolean falloPantalla = chkFalloPant.isSelected();
            boolean falloVueltaContPos = chkVueltaContPos.isSelected();
            boolean falloVueltaContNeg = chkVueltaContNeg.isSelected();
            boolean falloPendiente = chkFalloPendiente.isSelected();
            boolean falloRobo = chkRobo.isSelected();
            boolean falloDescomp = chkDescompostura.isSelected();
            //por default dejarlo verdadero           
            boolean solucionado = corteEditar.isSolucionado();
            
            int prestNoRecu = Integer.parseInt(txtPrestNoRecuperable.getText());
            Corte nuevoCorte= new Corte(fecha, sucursal, encargado, m, contPos, contNeg, pantIn, pantOut, pantInOut, inOut, nota, robo, falloContador, falloPantalla, falloRobo, falloPendiente, falloVueltaContPos, falloVueltaContNeg, falloDescomp, solucionado);
            
            //buscar que no exista
            boolean existe=false;            
            //sacar el indice del corteEditar para omitirlo en la busqueda
            int indiceEditar=-1;
            for (int i = 0; i < cortes.size(); i++) {
                if (cortes.elementAt(i).equals(corteEditar)) {
                    indiceEditar=i;
                }
            }
            //buscar si no existe omitiendo el indice del corte a editar
            for(int k=0; k<cortes.size(); k++){
                if (indiceEditar!=k) {
                    if (cortes.elementAt(k).equals(nuevoCorte)) {
                        existe=true;
                    }
                }                
            }

            //buscar el corte anterior de esta maquina para saber si los contadores son correctos
            boolean contMenor=false;
            boolean contMayor=false;            
            //buscar los cortes de la misma maquina y comparar los contadores con el anterior
            Vector<Corte> cortesDeMaquina = new Vector<Corte>();
            for (int i = 0; i < cortes.size(); i++) {
                if (cortes.elementAt(i).getMaquina().getNombre().equals(nuevoCorte.getMaquina().getNombre())) {
                    cortesDeMaquina.add(cortes.elementAt(i));
                }
            }

            if (!cortesDeMaquina.isEmpty()) {
                cortesDeMaquina.add(nuevoCorte);
                ConsultaCorte.ordenarCortesPorFecha(cortesDeMaquina, 0, cortesDeMaquina.size()-1); //ordenar los cortes de esa maquina
                //sacar el indice del nuevoCorte
                int indiceNuevoCorte=0;
                for (int i = 0; i < cortesDeMaquina.size(); i++) {
                    if (cortesDeMaquina.elementAt(i).equals(nuevoCorte)){
                        indiceNuevoCorte=i;
                    }
                }                                
                try{//si el corte actual tiene contador menor al corte anterior
                    if (!cortesDeMaquina.elementAt(indiceNuevoCorte-1).isFalloContador()) {
                        if(nuevoCorte.getContNeg()<cortesDeMaquina.elementAt(indiceNuevoCorte-1).getContNeg() || nuevoCorte.getContPos()<cortesDeMaquina.elementAt(indiceNuevoCorte-1).getContPos()){
                            contMenor=true;
                        }                                    
                    }
                    //si el corte actual tiene contador mayor que el corte posterior
                    if(nuevoCorte.getContNeg()>cortesDeMaquina.elementAt(indiceNuevoCorte+1).getContNeg() || nuevoCorte.getContPos()>cortesDeMaquina.elementAt(indiceNuevoCorte+1).getContPos()){
                        contMayor=true;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //si salta esta excepcion es que no hay un corte posterior que nuevoCorte, entonces omitir
                }
            }

            //escribir en el archivo con el toString
            if(!existe && !contMenor && !contMayor){
                //poner en el indice del corteEditar el nuevoCorte
                cortes.setElementAt(nuevoCorte, indiceEditar);
                FileWriter fw = new FileWriter("Archivos/Cortes.bin");
                PrintWriter pw= new PrintWriter(fw) ;
                //reemplazar todos los cortes
                for (int i = 0; i < cortes.size(); i++) {
                    pw.println(cortes.elementAt(i));
                }
                pw.close();                
                JOptionPane.showMessageDialog(rootPane, "Corte de Maquina editado con éxito", "Éxito", WIDTH);
                btnCancelarActionPerformed(null);
            }else{
                if(existe){
                    JOptionPane.showMessageDialog(rootPane, "El Corte que ested esta ingresando ya existe", "Atencion",WARNING_MESSAGE);
                }else{
                    if(contMenor){
                        JOptionPane.showMessageDialog(rootPane, "El Corte que ested esta ingresando posee un contador menor al corte anterior", "Atencion",WARNING_MESSAGE);
                    }else{
                        if(contMayor){
                            JOptionPane.showMessageDialog(rootPane, "El Corte que ested esta ingresando posee un contador mayor al corte posterior", "Atencion",WARNING_MESSAGE);
                        } 
                    }                   
                }                
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(rootPane, "Debe tener todos los campos llenos para poder guardar el corte", "ERROR", WIDTH);
        }catch(NullPointerException e){
            JOptionPane.showMessageDialog(rootPane, "No ingresó Fecha del Corte", "ERROR", WIDTH);
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Base de Datos no encontrada o Inutilizable", "Error", ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAceptarActionPerformed
    private void btnAceptarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAceptarKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==evt.VK_ENTER){
            this.btnAceptarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAceptarKeyTyped
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        ConsultaCorte cc = new ConsultaCorte(filtro);
        cc.setLocationRelativeTo(null);
        cc.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed
    private void btnCancelarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCancelarKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==evt.VK_ENTER){
            this.btnCancelarActionPerformed(null);
        }
    }//GEN-LAST:event_btnCancelarKeyTyped

    private void txtContNegActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContNegActionPerformed
        
    }//GEN-LAST:event_txtContNegActionPerformed

    private void txtNotaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNotaKeyTyped
        char tecla = evt.getKeyChar();
        if (tecla == KeyEvent.VK_ENTER) {
            btnAceptar.requestFocus();
        }
    }//GEN-LAST:event_txtNotaKeyTyped

    private void chkRoboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkRoboActionPerformed
        txtRobo.setEnabled(chkRobo.isSelected());
    }//GEN-LAST:event_chkRoboActionPerformed

    private void txtRoboKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtRoboKeyTyped
        char key = evt.getKeyChar();
        if (!Character.isDigit(key)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtRoboKeyTyped

    private void btnMasFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasFondoActionPerformed
        if (cmbMaquina.getSelectedIndex() != -1) {
            for (Maquina maquina : maquinas) {
                if (maquina.equals(maquinasFiltradas.elementAt(cmbMaquina.getSelectedIndex()))) {
                    String delta = JOptionPane.showInputDialog("Ingrese el aumento del fondo:", 0);
                    if (delta != null){ //para omitir el mensaje cuando obtienes cancelar
                        try {
                            int cantidad = Integer.parseInt(delta);
                            maquina.setFondo(maquina.getFondo() + cantidad); //aumentar el fondo
                            //actualizar el archivo de maquina
                            try {
                                FileWriter fr = new FileWriter("Archivos/Maquinas.bin");
                                PrintWriter pw= new PrintWriter(fr);
                                for (int i = 0; i < maquinas.size(); i++) {
                                    pw.println(maquinas.elementAt(i).toString());
                                }
                                pw.close();
                                txtFondo.setText(String.valueOf(maquina.getFondo()));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error actualizando el archivo de maquinas", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Ingreso un valor no valido para aumentar el fondo", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnMasFondoActionPerformed

    private void btnMenosFondoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenosFondoActionPerformed
        if (cmbMaquina.getSelectedIndex() != -1) {
            for (Maquina maquina : maquinas) {
                if (maquina.equals(maquinasFiltradas.elementAt(cmbMaquina.getSelectedIndex()))) {
                    String delta = JOptionPane.showInputDialog("Ingrese la recudccion del fondo:", 0);
                    if (delta != null){ //para omitir el mensaje cuando obtienes cancelar
                        try {
                            int cantidad = Integer.parseInt(delta);
                            maquina.setFondo(maquina.getFondo() - cantidad); //aumentar el fondo
                            //actualizar el archivo de maquina
                            try {
                                FileWriter fr = new FileWriter("Archivos/Maquinas.bin");
                                PrintWriter pw= new PrintWriter(fr);
                                for (int i = 0; i < maquinas.size(); i++) {
                                    pw.println(maquinas.elementAt(i).toString());
                                }
                                pw.close();
                                txtFondo.setText(String.valueOf(maquina.getFondo()));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error actualizando el archivo de maquinas", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Ingreso un valor no valido", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnMenosFondoActionPerformed

    private void btnMenorPrestRecuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMenorPrestRecuActionPerformed
        if (cmbMaquina.getSelectedIndex() != -1) {
            for (Maquina maquina : maquinas) {
                if (maquina.equals(maquinasFiltradas.elementAt(cmbMaquina.getSelectedIndex()))) {
                    String delta = JOptionPane.showInputDialog("Ingrese la reducción de préstamo recuperable:", 0);
                    if (delta != null){ //para omitir el mensaje cuando obtienes cancelar
                        try {
                            int cantidad = Integer.parseInt(delta);
                            maquina.setPrestRecu(maquina.getPrestRecu() - cantidad); //aumentar el fondo
                            //actualizar el archivo de maquina
                            try {
                                FileWriter fr = new FileWriter("Archivos/Maquinas.bin");
                                PrintWriter pw= new PrintWriter(fr);
                                for (int i = 0; i < maquinas.size(); i++) {
                                    pw.println(maquinas.elementAt(i).toString());
                                }
                                pw.close();
                                txtPrestRecuperable.setText(String.valueOf(maquina.getPrestRecu()));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error actualizando el archivo de maquinas", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Ingreso un valor no valido", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnMenorPrestRecuActionPerformed

    private void btnMasPrestRecuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasPrestRecuActionPerformed
        if (cmbMaquina.getSelectedIndex() != -1) {
            for (Maquina maquina : maquinas) {
                if (maquina.equals(maquinasFiltradas.elementAt(cmbMaquina.getSelectedIndex()))) {
                    String delta = JOptionPane.showInputDialog("Ingrese el aumento de préstamo recuperable:", 0);
                    if (delta != null){ //para omitir el mensaje cuando obtienes cancelar
                        try {
                            int cantidad = Integer.parseInt(delta);
                            maquina.setPrestRecu(maquina.getPrestRecu() + cantidad); //aumentar el fondo
                            //actualizar el archivo de maquina
                            try {
                                FileWriter fr = new FileWriter("Archivos/Maquinas.bin");
                                PrintWriter pw= new PrintWriter(fr);
                                for (int i = 0; i < maquinas.size(); i++) {
                                    pw.println(maquinas.elementAt(i).toString());
                                }
                                pw.close();
                                txtPrestRecuperable.setText(String.valueOf(maquina.getPrestRecu()));
                            } catch (Exception e) {
                                JOptionPane.showMessageDialog(null, "Error actualizando el archivo de maquinas", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Ingreso un valor no valido", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnMasPrestRecuActionPerformed

    private void btnPrestNoRecuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrestNoRecuActionPerformed
        if (cmbMaquina.getSelectedIndex() != -1) {
            for (Maquina maquina : maquinas) {
                if (maquina.equals(maquinasFiltradas.elementAt(cmbMaquina.getSelectedIndex()))) {
                    String delta = JOptionPane.showInputDialog("Ingrese el aumento de préstamo no recuperable:", 0);
                    if (delta != null){ //para omitir el mensaje cuando obtienes cancelar
                        try {
                            int cantidad = Integer.parseInt(delta);
                            maquina.setPrestNoRecu(maquina.getPrestNoRecu() + cantidad); //aumentar el fondo
                            txtPrestNoRecuperable.setText(String.valueOf(maquina.getPrestNoRecu()));
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Ingreso un valor no valido", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnPrestNoRecuActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnMasFondo;
    private javax.swing.JButton btnMasPrestRecu;
    private javax.swing.JButton btnMenorPrestRecu;
    private javax.swing.JButton btnMenosFondo;
    private javax.swing.JButton btnPrestNoRecu;
    private com.toedter.calendar.JDateChooser calendar;
    private javax.swing.JCheckBox chkDescompostura;
    private javax.swing.JCheckBox chkFalloContador;
    private javax.swing.JCheckBox chkFalloPant;
    private javax.swing.JCheckBox chkFalloPendiente;
    private javax.swing.JCheckBox chkRobo;
    private javax.swing.JCheckBox chkVueltaContNeg;
    private javax.swing.JCheckBox chkVueltaContPos;
    private javax.swing.JComboBox cmbEncargado;
    private javax.swing.JComboBox cmbMaquina;
    private javax.swing.JComboBox cmbSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbEncargado;
    private javax.swing.JLabel lbSucursal;
    private javax.swing.JTextField txtContNeg;
    private javax.swing.JTextField txtContPos;
    private javax.swing.JTextField txtFondo;
    private javax.swing.JTextField txtInOut;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtPantIn;
    private javax.swing.JTextField txtPantInOut;
    private javax.swing.JTextField txtPantOut;
    private javax.swing.JTextField txtPrestNoRecuperable;
    private javax.swing.JTextField txtPrestRecuperable;
    private javax.swing.JTextField txtRobo;
    private javax.swing.JTextField txtTarjeta;
    // End of variables declaration//GEN-END:variables
}
