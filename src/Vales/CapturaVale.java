
package Vales;

import ControlUsuario.Loged;
import Etiquetas.Etiqueta;
import Maquinas.Maquina;
import Sucursal.Sucursal;
import static Utilerias.Utileria.quitaEspacios;
import static Utilerias.Utileria.quitaGuion;
import Utilerias.Validacion;
import Vales.Chequeras.Chequera;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;

public class CapturaVale extends javax.swing.JFrame {

    SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
    ArrayList<Chequera> chequeras;
    Vector<Maquina> maquinas;
    
    public CapturaVale() {
        initComponents();
        maquinas = Maquina.cargarMaquinas();        
        chequeras = Chequera.cargarChequeras();
        for (Chequera chequera : chequeras) {
            cmbChequera.addItem(quitaGuion(chequera.getId()));
        }
        Vector<Sucursal> sucursales = Sucursal.cargarSucursales();        
        for (Sucursal sucursal : sucursales) {
            cmbSucursal.addItem(quitaGuion(sucursal.getNombre()));
        }
        ArrayList<Etiqueta> etiquetas = Etiqueta.cargarEtiquetas();
        for (Etiqueta etiqueta : etiquetas) {
            cmbEtiqueta.addItem(quitaGuion(etiqueta.getNombre()));
        }
        GregorianCalendar cal = new GregorianCalendar();
        txtFecha.setDate(cal.getTime());
    }

    private Vale crearVale() throws Exception{                  
        //validaciones
        if (txtFolio.getText().isEmpty() || txtCantidad.getText().isEmpty() || cmbChequera.getSelectedItem() == null || txtNota.getText().isEmpty() || txtConcepto.getText().isEmpty()) {            
            throw new Exception();
        }   
        String nomMaquina ;
        if (chkSinMaquina.isSelected()) {
            nomMaquina = "Sin_Máquina";
        }else{
            nomMaquina = quitaEspacios(cmbMaquina.getSelectedItem().toString());
        }
        //crear vale
        Chequera c = chequeras.get(cmbChequera.getSelectedIndex());        
        String folio = quitaEspacios(txtFolio.getText());
        String fecha = sdf.format(txtFecha.getDate());//puede lanzar una excepcion        
        String sucursal = quitaEspacios(cmbSucursal.getSelectedItem().toString());
        String concepto = quitaEspacios(txtConcepto.getText());        
        String nota = quitaEspacios(txtNota.getText());               
        float cantidad = Float.parseFloat(txtCantidad.getText());
        String nomEtiqueta = quitaEspacios(cmbEtiqueta.getSelectedItem().toString());
        
        return new Vale(c, folio, fecha, sucursal, concepto, nota, nomEtiqueta, nomMaquina, cantidad, false, true); //por omision mantener como no checked
    }
 
    private void limpiarFormulario(){
        txtFolio.setText("");
        txtConcepto.setText("");
        txtNota.setText("");
        txtCantidad.setText("");    
        
        txtFolio.requestFocus();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtFolio = new javax.swing.JTextField();
        txtFecha = new com.toedter.calendar.JDateChooser();
        cmbSucursal = new javax.swing.JComboBox();
        txtConcepto = new javax.swing.JTextField();
        txtNota = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        cmbChequera = new javax.swing.JComboBox();
        txtRutero = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cmbMaquina = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        cmbEtiqueta = new javax.swing.JComboBox();
        chkSinMaquina = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Captura de Vale");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Folio:");

        jLabel2.setText("Fecha: ");

        jLabel3.setText("Sucursal:");

        jLabel4.setText("Concepto:");

        jLabel5.setText("Nota:");

        jLabel6.setText("Cantidad:");

        cmbSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbSucursalActionPerformed(evt);
            }
        });

        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });

        jLabel7.setText("Chequera:");

        cmbChequera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbChequeraActionPerformed(evt);
            }
        });

        jLabel8.setText("Maquina:");

        jLabel9.setText("Etiqueta:");

        chkSinMaquina.setText("Sin Maquina");
        chkSinMaquina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkSinMaquinaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(16, 16, 16)
                            .addComponent(jLabel3))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(13, 13, 13)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel4))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel1))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNota)
                            .addComponent(txtConcepto, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbChequera, javax.swing.GroupLayout.Alignment.LEADING, 0, 110, Short.MAX_VALUE)
                            .addComponent(txtFolio))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(txtRutero, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cmbEtiqueta, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbMaquina, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbSucursal, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(chkSinMaquina))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtRutero, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(cmbChequera, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel1))
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cmbMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkSinMaquina))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cmbEtiqueta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        btnAgregar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnAgregarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addComponent(btnAgregar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addGap(61, 61, 61))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnAgregar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       try {
            Vale vale = this.crearVale();                        
            //validar que el folio no este registrado
            ArrayList<Vale> vales = Vale.cargarVales();
            boolean existe = false;
            for (Vale v : vales) {
                if (vale.equals(v)) {
                    existe = true;                    
                    break;
                }
            }
            if (!existe) {
                try {
                    FileWriter fw = new FileWriter("Archivos/Vales.bin", true);
                    PrintWriter pw = new PrintWriter(fw);
                    pw.println(vale);
                    fw.close();
                    pw.close();
                    
                    //actualizar el status de la chequera
                    //obtener fecha y hora
                    GregorianCalendar cal = new GregorianCalendar();
                    SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy HH:mm:ss");
                    String fechaHora = quitaEspacios(sdf.format(cal.getTime()));
                    //agregar datos a la chequera
                    chequeras.get(cmbChequera.getSelectedIndex()).setUltimaFecha(fechaHora);
                    chequeras.get(cmbChequera.getSelectedIndex()).setUltimoUsuario(Loged.getLoged().getUsuario());
                    chequeras.get(cmbChequera.getSelectedIndex()).setUltimoFolio(vale.getFolio());
                    Chequera.actualizarDB(chequeras);
                    
                    JOptionPane.showMessageDialog(null, "Vale agregado a la lista con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    this.limpiarFormulario();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al intentar guardar el vale al archivo", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "Ese vale ya existe", "Error", JOptionPane.ERROR_MESSAGE);
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Los valores ingresados no son validos", "Atención", JOptionPane.WARNING_MESSAGE);
        }               
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        if (!Validacion.isDigit(evt)) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void btnAgregarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnAgregarKeyTyped
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            btnAgregarActionPerformed(null);
        }
    }//GEN-LAST:event_btnAgregarKeyTyped

    private void cmbChequeraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbChequeraActionPerformed
        txtRutero.setText(quitaGuion(chequeras.get(cmbChequera.getSelectedIndex()).getRutero()));
        //colocar en el campo de folio, el folio siguiente respecto al ultimo capturado
        String parteDecimal = ""; //en caso de necesitar
        int folio = 0;
        try {
            folio = Integer.parseInt(chequeras.get(cmbChequera.getSelectedIndex()).getUltimoFolio());            
        } catch (Exception e) {
            //obtener la parte de numeros del folio
            String ultimoFolio = chequeras.get(cmbChequera.getSelectedIndex()).getUltimoFolio();            
            for (int i = 0; i < ultimoFolio.length(); i++) {
                if (Character.isDigit(ultimoFolio.charAt(i))) {
                    parteDecimal += ultimoFolio.charAt(i);
                }
            }            
        }
        finally{
            if (!parteDecimal.isEmpty()) {
                folio = Integer.parseInt(parteDecimal);
            }
            folio++;
            txtFolio.setText(String.valueOf(folio));
        }
    }//GEN-LAST:event_cmbChequeraActionPerformed

    private void cmbSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbSucursalActionPerformed
        String sucursal = quitaEspacios(cmbSucursal.getSelectedItem().toString());  
        cmbMaquina.removeAllItems();
        for (Maquina maquina : maquinas) {
            if (maquina.getSucursal().getNombre().equals(sucursal)) {
                cmbMaquina.addItem(maquina.getNombre());
            }
        }              
    }//GEN-LAST:event_cmbSucursalActionPerformed

    private void chkSinMaquinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkSinMaquinaActionPerformed
        if (chkSinMaquina.isSelected()) {
            cmbMaquina.setEnabled(false);
        }else{
            cmbMaquina.setEnabled(true);
        }
    }//GEN-LAST:event_chkSinMaquinaActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chkSinMaquina;
    private javax.swing.JComboBox cmbChequera;
    private javax.swing.JComboBox cmbEtiqueta;
    private javax.swing.JComboBox cmbMaquina;
    private javax.swing.JComboBox cmbSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtConcepto;
    private com.toedter.calendar.JDateChooser txtFecha;
    private javax.swing.JTextField txtFolio;
    private javax.swing.JTextField txtNota;
    private javax.swing.JLabel txtRutero;
    // End of variables declaration//GEN-END:variables
}
