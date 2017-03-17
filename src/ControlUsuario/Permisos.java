package ControlUsuario;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Permisos extends javax.swing.JFrame {

    ArrayList<Usuario> usuarios;
    Usuario selected;

    public Permisos() {
        initComponents();
    }

    public Permisos(ArrayList<Usuario> usuarios, Usuario usuario) {
        this.usuarios = usuarios;
        selected = usuario;
        initComponents();

        lbUsuario.setText("Permisos del Usuario: " + usuario.getUsuario());
        chkEliminarFormato.setSelected(usuario.isEliminarFormato());
        chkPagarFormato.setSelected(usuario.isPagarFormato());
        chkExportarFormato.setSelected(usuario.isExportarFormatos());
        chkEliminarCorte.setSelected(usuario.isEliminarCorte());
        chkAsignarFallo.setSelected(usuario.isAsignarFalloCortes());
        chkExportarCortes.setSelected(usuario.isExportarCortes());
        chkImportarCortes.setSelected(usuario.isImportarCortes());
        chkConsultarEmpleados.setSelected(usuario.isConsultarEmpleados());
        chkConsultarSucursales.setSelected(usuario.isConsultarSucursales());
        chkConsultarProducto.setSelected(usuario.isConsultarProducto());
        chkConsultarMaquinas.setSelected(usuario.isConsultarMaquinas());
        chkConsultarTarjetas.setSelected(usuario.isConsultarTarjetas());
        chkConsultaPerifericos.setSelected(usuario.isConsultaPerifericos());
        chkConsultaEtiquetas.setSelected(usuario.isConsultaEtiquetas());
        chkConsultarChequeras.setSelected(usuario.isConsultaChequeras());
        chkEditarEmpleados.setSelected(usuario.isEditarEmpleados());
        chkEditarSucursales.setSelected(usuario.isEditarSucursales());
        chkEditarProductos.setSelected(usuario.isEditarProducto());
        chkEditarMaquinas.setSelected(usuario.isEditarMaquinas());
        chkEditarTarjetas.setSelected(usuario.isEditarTarjetas());
        chkEditarFormato.setSelected(usuario.isEditarFormato());
        chkEditarCorte.setSelected(usuario.isEditarCorte());
        chkEditarPerifericos.setSelected(usuario.isEditarPerifericos());
        chkEditarEtiquetas.setSelected(usuario.isEditarEtiquetas());
        chkEditarChequeras.setSelected(usuario.isEditarChequeras());

        this.chkEditarRutero.setSelected(usuario.isEditarRutero());
        this.chkConsultarRuteros.setSelected(usuario.isConsultaRutero());

        this.chkCapturaFormato.setSelected(usuario.isCapturaFormato());
        this.chkConsultaFormatos.setSelected(usuario.isConsultaFormato());

        this.chkCapturaCorte.setSelected(usuario.isCapturaCorte());
        this.chkConsultaCortes.setSelected(usuario.isConsultaCorte());

        this.chkCapturaMovimiento.setSelected(usuario.isCapturaMovimiento());
        this.chkConsultaMovimientos.setSelected(usuario.isConsultaMovimiento());

        chkEliminarMovimiento.setSelected(usuario.isEliminarMovimiento());
        chkImportarMovimiento.setSelected(usuario.isImportarMovimiento());
        chkAjusteMovimiento.setSelected(usuario.isAjusteMovimiento());

        chkEstadisticas.setSelected(usuario.isConsultaEstadisticas());
        chkEncargados.setSelected(usuario.isConsultaEncargados());

        chkCapturaVale.setSelected(usuario.isCapturaVales());
        chkConsultaVales.setSelected(usuario.isConsultaVales());

        chkEditarVale.setSelected(usuario.isEditarVales());

        //nomina
        chkNomina.setSelected(usuario.isConsultaNomina());
        chkConsIncidentes.setSelected(usuario.isConsultaIncidentes());
        chkConsPrestamos.setSelected(usuario.isConsultaPrestamos());
        chkCapIncidentes.setSelected(usuario.isCapturaIncidentes());
        chkCapPrestamos.setSelected(usuario.isCapturaPrestamos());                        
        
        chkRespaldo.setSelected(usuario.isRespaldar());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbUsuario = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        chkRespaldo = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        chkEliminarFormato = new javax.swing.JCheckBox();
        chkPagarFormato = new javax.swing.JCheckBox();
        chkExportarFormato = new javax.swing.JCheckBox();
        chkEditarFormato = new javax.swing.JCheckBox();
        chkConsultaFormatos = new javax.swing.JCheckBox();
        chkCapturaFormato = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        chkEliminarCorte = new javax.swing.JCheckBox();
        chkAsignarFallo = new javax.swing.JCheckBox();
        chkExportarCortes = new javax.swing.JCheckBox();
        chkImportarCortes = new javax.swing.JCheckBox();
        chkEditarCorte = new javax.swing.JCheckBox();
        chkCapturaCorte = new javax.swing.JCheckBox();
        chkConsultaCortes = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        chkEliminarMovimiento = new javax.swing.JCheckBox();
        chkImportarMovimiento = new javax.swing.JCheckBox();
        chkAjusteMovimiento = new javax.swing.JCheckBox();
        chkConsultaMovimientos = new javax.swing.JCheckBox();
        chkCapturaMovimiento = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        chkEstadisticas = new javax.swing.JCheckBox();
        chkEncargados = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        chkCapturaVale = new javax.swing.JCheckBox();
        chkConsultaVales = new javax.swing.JCheckBox();
        chkEditarVale = new javax.swing.JCheckBox();
        jPanel12 = new javax.swing.JPanel();
        chkNomina = new javax.swing.JCheckBox();
        chkConsIncidentes = new javax.swing.JCheckBox();
        chkConsPrestamos = new javax.swing.JCheckBox();
        chkCapIncidentes = new javax.swing.JCheckBox();
        chkCapPrestamos = new javax.swing.JCheckBox();
        jPanel11 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        chkConsultarEmpleados = new javax.swing.JCheckBox();
        chkConsultarSucursales = new javax.swing.JCheckBox();
        chkConsultarProducto = new javax.swing.JCheckBox();
        chkConsultarMaquinas = new javax.swing.JCheckBox();
        chkConsultarTarjetas = new javax.swing.JCheckBox();
        chkConsultarRuteros = new javax.swing.JCheckBox();
        chkConsultaEtiquetas = new javax.swing.JCheckBox();
        chkConsultarChequeras = new javax.swing.JCheckBox();
        chkConsultaPerifericos = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        chkEditarEmpleados = new javax.swing.JCheckBox();
        chkEditarSucursales = new javax.swing.JCheckBox();
        chkEditarProductos = new javax.swing.JCheckBox();
        chkEditarMaquinas = new javax.swing.JCheckBox();
        chkEditarTarjetas = new javax.swing.JCheckBox();
        chkEditarRutero = new javax.swing.JCheckBox();
        chkEditarEtiquetas = new javax.swing.JCheckBox();
        chkEditarChequeras = new javax.swing.JCheckBox();
        chkEditarPerifericos = new javax.swing.JCheckBox();
        jPanel10 = new javax.swing.JPanel();
        btnAceptar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Permisos");
        setResizable(false);

        lbUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbUsuario.setText("Usuario");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        chkRespaldo.setText("Restaurar Respaldo");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Modulos"), "Modulos"));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Formatos"));

        chkEliminarFormato.setText("Eliminar");

        chkPagarFormato.setText("Pagar");

        chkExportarFormato.setText("Exportar");

        chkEditarFormato.setText("Editar");

        chkConsultaFormatos.setText("Consultar");
        chkConsultaFormatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultaFormatosActionPerformed(evt);
            }
        });

        chkCapturaFormato.setText("Capturar");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEliminarFormato)
                    .addComponent(chkPagarFormato)
                    .addComponent(chkExportarFormato)
                    .addComponent(chkEditarFormato)
                    .addComponent(chkConsultaFormatos)
                    .addComponent(chkCapturaFormato))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(chkCapturaFormato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkConsultaFormatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEliminarFormato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkPagarFormato)
                .addGap(0, 0, 0)
                .addComponent(chkExportarFormato)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEditarFormato)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Cortes"));

        chkEliminarCorte.setText("Eliminar");

        chkAsignarFallo.setText("Asignar Fallo");

        chkExportarCortes.setText("Exportar");

        chkImportarCortes.setText("Importar");

        chkEditarCorte.setText("Editar");

        chkCapturaCorte.setText("Capturar");

        chkConsultaCortes.setText("Consultar");
        chkConsultaCortes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultaCortesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEliminarCorte)
                    .addComponent(chkExportarCortes)
                    .addComponent(chkAsignarFallo)
                    .addComponent(chkImportarCortes)
                    .addComponent(chkEditarCorte)
                    .addComponent(chkCapturaCorte)
                    .addComponent(chkConsultaCortes))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(chkCapturaCorte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkConsultaCortes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkEliminarCorte)
                .addGap(0, 0, 0)
                .addComponent(chkAsignarFallo, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkExportarCortes, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkImportarCortes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEditarCorte))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Movimientos"));

        chkEliminarMovimiento.setText("Eliminar");

        chkImportarMovimiento.setText("Importar");

        chkAjusteMovimiento.setText("Revisión");

        chkConsultaMovimientos.setText("Consulta");
        chkConsultaMovimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultaMovimientosActionPerformed(evt);
            }
        });

        chkCapturaMovimiento.setText("Captura");
        chkCapturaMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCapturaMovimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEliminarMovimiento)
                    .addComponent(chkImportarMovimiento)
                    .addComponent(chkAjusteMovimiento)
                    .addComponent(chkConsultaMovimientos)
                    .addComponent(chkCapturaMovimiento))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(chkCapturaMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkConsultaMovimientos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEliminarMovimiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkImportarMovimiento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkAjusteMovimiento)
                .addGap(0, 0, 0))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Concentrado"));

        chkEstadisticas.setText("Estadisticas");

        chkEncargados.setText("Encargados");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEstadisticas)
                    .addComponent(chkEncargados))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(chkEstadisticas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEncargados)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Vales"));

        chkCapturaVale.setText("Captura");

        chkConsultaVales.setText("Consulta");
        chkConsultaVales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultaValesActionPerformed(evt);
            }
        });

        chkEditarVale.setText("Editar");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkCapturaVale)
                    .addComponent(chkConsultaVales)
                    .addComponent(chkEditarVale))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(chkCapturaVale)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkConsultaVales)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEditarVale)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Nómina"));

        chkNomina.setText("Nómina");

        chkConsIncidentes.setText("Cons. Incidentes");

        chkConsPrestamos.setText("Cons. Prestamos");

        chkCapIncidentes.setText("Cap. Incidente");

        chkCapPrestamos.setText("Cap. Prestamos");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkNomina)
                    .addComponent(chkConsPrestamos)
                    .addComponent(chkConsIncidentes)
                    .addComponent(chkCapIncidentes)
                    .addComponent(chkCapPrestamos))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(chkNomina)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkConsIncidentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkConsPrestamos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkCapIncidentes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCapPrestamos))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Listas"));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Consultar Listas"));

        chkConsultarEmpleados.setText("Empleados");
        chkConsultarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultarEmpleadosActionPerformed(evt);
            }
        });

        chkConsultarSucursales.setText("Sucursales");
        chkConsultarSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultarSucursalesActionPerformed(evt);
            }
        });

        chkConsultarProducto.setText("Productos");
        chkConsultarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultarProductoActionPerformed(evt);
            }
        });

        chkConsultarMaquinas.setText("Maquinas");
        chkConsultarMaquinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultarMaquinasActionPerformed(evt);
            }
        });

        chkConsultarTarjetas.setText("Tarjetas");
        chkConsultarTarjetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultarTarjetasActionPerformed(evt);
            }
        });

        chkConsultarRuteros.setText("Ruteros");
        chkConsultarRuteros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultarRuterosActionPerformed(evt);
            }
        });

        chkConsultaEtiquetas.setText("Etiquetas");
        chkConsultaEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultaEtiquetasActionPerformed(evt);
            }
        });

        chkConsultarChequeras.setText("Chequeras");
        chkConsultarChequeras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultarChequerasActionPerformed(evt);
            }
        });

        chkConsultaPerifericos.setText("Perifericos");
        chkConsultaPerifericos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkConsultaPerifericosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkConsultarEmpleados)
                    .addComponent(chkConsultarSucursales)
                    .addComponent(chkConsultarProducto)
                    .addComponent(chkConsultaEtiquetas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkConsultaPerifericos)
                    .addComponent(chkConsultarRuteros)
                    .addComponent(chkConsultarTarjetas)
                    .addComponent(chkConsultarMaquinas)
                    .addComponent(chkConsultarChequeras))
                .addGap(62, 62, 62))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkConsultarEmpleados)
                            .addComponent(chkConsultarMaquinas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkConsultaPerifericos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkConsultarTarjetas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkConsultarRuteros))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(chkConsultarSucursales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkConsultarProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkConsultaEtiquetas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkConsultarChequeras)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Editar Listas"));

        chkEditarEmpleados.setText("Empleados");

        chkEditarSucursales.setText("Sucursales");

        chkEditarProductos.setText("Productos");

        chkEditarMaquinas.setText("Maquinas");

        chkEditarTarjetas.setText("Tarjetas");

        chkEditarRutero.setText("Ruteros");

        chkEditarEtiquetas.setText("Etiquetas");

        chkEditarChequeras.setText("Chequeras");

        chkEditarPerifericos.setText("Perifericos");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEditarEmpleados)
                    .addComponent(chkEditarSucursales)
                    .addComponent(chkEditarProductos)
                    .addComponent(chkEditarEtiquetas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkEditarPerifericos)
                    .addComponent(chkEditarTarjetas)
                    .addComponent(chkEditarMaquinas)
                    .addComponent(chkEditarRutero)
                    .addComponent(chkEditarChequeras))
                .addGap(60, 60, 60))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chkEditarEmpleados)
                    .addComponent(chkEditarMaquinas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(chkEditarPerifericos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkEditarTarjetas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkEditarRutero))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(chkEditarSucursales)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkEditarProductos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkEditarEtiquetas)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkEditarChequeras)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(180, 180, 180)
                        .addComponent(chkRespaldo))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(chkRespaldo)))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lbUsuario)
                .addGap(447, 447, 447))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(lbUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed

        selected.setEliminarFormato(chkEliminarFormato.isSelected());
        selected.setPagarFormato(chkPagarFormato.isSelected());
        selected.setExportarFormatos(chkExportarFormato.isSelected());
        selected.setEliminarCorte(chkEliminarCorte.isSelected());
        selected.setAsignarFalloCortes(chkAsignarFallo.isSelected());
        selected.setExportarCortes(chkExportarCortes.isSelected());
        selected.setImportarCortes(chkImportarCortes.isSelected());
        selected.setConsultarEmpleados(chkConsultarEmpleados.isSelected());
        selected.setConsultarSucursales(chkConsultarSucursales.isSelected());
        selected.setConsultarProducto(chkConsultarProducto.isSelected());
        selected.setConsultarMaquinas(chkConsultarMaquinas.isSelected());
        selected.setConsultarTarjetas(chkConsultarTarjetas.isSelected());
        selected.setConsultaPerifericos(chkConsultaPerifericos.isSelected());
        selected.setConsultaEtiquetas(chkConsultaEtiquetas.isSelected());
        selected.setConsultaChequeras(chkConsultarChequeras.isSelected());
        selected.setEditarEmpleados(chkEditarEmpleados.isSelected());
        selected.setEditarSucursales(chkEditarSucursales.isSelected());
        selected.setEditarProducto(chkEditarProductos.isSelected());
        selected.setEditarMaquinas(chkEditarMaquinas.isSelected());
        selected.setEditarTarjetas(chkEditarTarjetas.isSelected());
        selected.setEditarFormato(chkEditarFormato.isSelected());
        selected.setEditarCorte(chkEditarCorte.isSelected());
        selected.setEditarPerifericos(chkEditarPerifericos.isSelected());
        selected.setEditarEtiquetas(chkEditarEtiquetas.isSelected());
        selected.setEditarChequeras(chkEditarChequeras.isSelected());
        selected.setRespaldar(chkRespaldo.isSelected());

        selected.setEditarRutero(chkEditarRutero.isSelected());
        selected.setConsultaRutero(chkConsultarRuteros.isSelected());

        selected.setCapturaCorte(chkCapturaCorte.isSelected());
        selected.setConsultaCorte(chkConsultaCortes.isSelected());

        selected.setCapturaFormato(chkCapturaFormato.isSelected());
        selected.setConsultaFormato(chkConsultaFormatos.isSelected());

        selected.setCapturaMovimiento(chkCapturaMovimiento.isSelected());
        selected.setConsultaMovimiento(chkConsultaMovimientos.isSelected());

        selected.setEliminarMovimiento(chkEliminarMovimiento.isSelected());
        selected.setImportarMovimiento(chkImportarMovimiento.isSelected());
        selected.setAjusteMovimiento(chkAjusteMovimiento.isSelected());

        selected.setConsultaEstadisticas(chkEstadisticas.isSelected());
        selected.setConsultaEncargados(chkEncargados.isSelected());
        selected.setCapturaVales(chkCapturaVale.isSelected());
        selected.setConsultaVales(chkConsultaVales.isSelected());

        selected.setEditarVales(chkEditarVale.isSelected());

        //nomina
        selected.setConsultaNomina(chkNomina.isSelected());
        selected.setConsultaIncidentes(chkConsIncidentes.isSelected());
        selected.setConsultaPrestamos(chkConsPrestamos.isSelected());
        selected.setCapturaPrestamos(chkCapPrestamos.isSelected());
        selected.setCapturaIncidentes(chkCapIncidentes.isSelected());
        
        //buscar y actualizar en el vector de usuarios
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getUsuario().equals(selected.getUsuario())) {
                usuarios.set(i, selected);
            }
        }        
        Usuario.actualizarDB(usuarios);
        btnCancelarActionPerformed(null);
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void chkConsultaFormatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultaFormatosActionPerformed
        if (chkConsultaFormatos.isSelected()) {
            chkEliminarFormato.setEnabled(true);
            chkPagarFormato.setEnabled(true);
            chkEditarFormato.setEnabled(true);
            chkExportarFormato.setEnabled(true);
        } else {
            chkEliminarFormato.setEnabled(false);
            chkPagarFormato.setEnabled(false);
            chkEditarFormato.setEnabled(false);
            chkExportarFormato.setEnabled(false);
            chkEliminarFormato.setSelected(false);
            chkPagarFormato.setSelected(false);
            chkEditarFormato.setSelected(false);
            chkExportarFormato.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultaFormatosActionPerformed

    private void chkConsultaCortesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultaCortesActionPerformed
        if (chkConsultaCortes.isSelected()) {
            chkEliminarCorte.setEnabled(true);
            chkAsignarFallo.setEnabled(true);
            chkEditarCorte.setEnabled(true);
            chkExportarCortes.setEnabled(true);
            chkImportarCortes.setEnabled(true);
        } else {
            chkEliminarCorte.setEnabled(false);
            chkAsignarFallo.setEnabled(false);
            chkEditarCorte.setEnabled(false);
            chkExportarCortes.setEnabled(false);
            chkImportarCortes.setEnabled(false);
            chkEliminarCorte.setSelected(false);
            chkAsignarFallo.setSelected(false);
            chkEditarCorte.setSelected(false);
            chkExportarCortes.setSelected(false);
            chkImportarCortes.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultaCortesActionPerformed

    private void chkCapturaMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCapturaMovimientoActionPerformed
        if (chkCapturaMovimiento.isSelected()) {
            chkAjusteMovimiento.setEnabled(true);
        } else {
            chkAjusteMovimiento.setEnabled(false);
            chkAjusteMovimiento.setSelected(false);
        }
    }//GEN-LAST:event_chkCapturaMovimientoActionPerformed

    private void chkConsultaMovimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultaMovimientosActionPerformed
        if (chkConsultaMovimientos.isSelected()) {
            chkEliminarMovimiento.setEnabled(true);
            chkImportarMovimiento.setEnabled(true);
        } else {
            chkEliminarMovimiento.setSelected(false);
            chkImportarMovimiento.setSelected(false);
            chkEliminarMovimiento.setEnabled(false);
            chkImportarMovimiento.setEnabled(false);
        }
    }//GEN-LAST:event_chkConsultaMovimientosActionPerformed

    private void chkConsultarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultarEmpleadosActionPerformed
        if (chkConsultarEmpleados.isSelected()) {
            chkEditarEmpleados.setEnabled(true);
        } else {
            chkEditarEmpleados.setEnabled(false);
            chkEditarEmpleados.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultarEmpleadosActionPerformed

    private void chkConsultarSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultarSucursalesActionPerformed
        if (chkConsultarSucursales.isSelected()) {
            chkEditarSucursales.setEnabled(true);
        } else {
            chkEditarSucursales.setEnabled(false);
            chkEditarSucursales.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultarSucursalesActionPerformed

    private void chkConsultarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultarProductoActionPerformed
        if (chkConsultarProducto.isSelected()) {
            chkEditarProductos.setEnabled(true);
        } else {
            chkEditarProductos.setEnabled(false);
            chkEditarProductos.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultarProductoActionPerformed

    private void chkConsultaEtiquetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultaEtiquetasActionPerformed
        if (chkConsultaEtiquetas.isSelected()) {
            chkEditarEtiquetas.setEnabled(true);
        } else {
            chkEditarEtiquetas.setEnabled(false);
            chkEditarEtiquetas.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultaEtiquetasActionPerformed

    private void chkConsultarMaquinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultarMaquinasActionPerformed
        if (chkConsultarMaquinas.isSelected()) {
            chkEditarMaquinas.setEnabled(true);
        } else {
            chkEditarMaquinas.setEnabled(false);
            chkEditarMaquinas.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultarMaquinasActionPerformed

    private void chkConsultaPerifericosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultaPerifericosActionPerformed
        if (chkConsultaPerifericos.isSelected()) {
            chkEditarPerifericos.setEnabled(true);
        } else {
            chkEditarPerifericos.setEnabled(false);
            chkEditarPerifericos.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultaPerifericosActionPerformed

    private void chkConsultarTarjetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultarTarjetasActionPerformed
        if (chkConsultarTarjetas.isSelected()) {
            chkEditarTarjetas.setEnabled(true);
        } else {
            chkEditarTarjetas.setEnabled(false);
            chkEditarTarjetas.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultarTarjetasActionPerformed

    private void chkConsultarRuterosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultarRuterosActionPerformed
        if (chkConsultarRuteros.isSelected()) {
            chkEditarRutero.setEnabled(true);
        } else {
            chkEditarRutero.setEnabled(false);
            chkEditarRutero.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultarRuterosActionPerformed

    private void chkConsultarChequerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultarChequerasActionPerformed
        if (chkConsultarChequeras.isSelected()) {
            chkEditarChequeras.setEnabled(true);
        } else {
            chkEditarChequeras.setEnabled(false);
            chkEditarChequeras.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultarChequerasActionPerformed

    private void chkConsultaValesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkConsultaValesActionPerformed
        if (chkConsultaVales.isSelected()) {
            chkEditarVale.setEnabled(true);
        } else {
            chkEditarVale.setEnabled(false);
            chkEditarVale.setSelected(false);
        }
    }//GEN-LAST:event_chkConsultaValesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JCheckBox chkAjusteMovimiento;
    private javax.swing.JCheckBox chkAsignarFallo;
    private javax.swing.JCheckBox chkCapIncidentes;
    private javax.swing.JCheckBox chkCapPrestamos;
    private javax.swing.JCheckBox chkCapturaCorte;
    private javax.swing.JCheckBox chkCapturaFormato;
    private javax.swing.JCheckBox chkCapturaMovimiento;
    private javax.swing.JCheckBox chkCapturaVale;
    private javax.swing.JCheckBox chkConsIncidentes;
    private javax.swing.JCheckBox chkConsPrestamos;
    private javax.swing.JCheckBox chkConsultaCortes;
    private javax.swing.JCheckBox chkConsultaEtiquetas;
    private javax.swing.JCheckBox chkConsultaFormatos;
    private javax.swing.JCheckBox chkConsultaMovimientos;
    private javax.swing.JCheckBox chkConsultaPerifericos;
    private javax.swing.JCheckBox chkConsultaVales;
    private javax.swing.JCheckBox chkConsultarChequeras;
    private javax.swing.JCheckBox chkConsultarEmpleados;
    private javax.swing.JCheckBox chkConsultarMaquinas;
    private javax.swing.JCheckBox chkConsultarProducto;
    private javax.swing.JCheckBox chkConsultarRuteros;
    private javax.swing.JCheckBox chkConsultarSucursales;
    private javax.swing.JCheckBox chkConsultarTarjetas;
    private javax.swing.JCheckBox chkEditarChequeras;
    private javax.swing.JCheckBox chkEditarCorte;
    private javax.swing.JCheckBox chkEditarEmpleados;
    private javax.swing.JCheckBox chkEditarEtiquetas;
    private javax.swing.JCheckBox chkEditarFormato;
    private javax.swing.JCheckBox chkEditarMaquinas;
    private javax.swing.JCheckBox chkEditarPerifericos;
    private javax.swing.JCheckBox chkEditarProductos;
    private javax.swing.JCheckBox chkEditarRutero;
    private javax.swing.JCheckBox chkEditarSucursales;
    private javax.swing.JCheckBox chkEditarTarjetas;
    private javax.swing.JCheckBox chkEditarVale;
    private javax.swing.JCheckBox chkEliminarCorte;
    private javax.swing.JCheckBox chkEliminarFormato;
    private javax.swing.JCheckBox chkEliminarMovimiento;
    private javax.swing.JCheckBox chkEncargados;
    private javax.swing.JCheckBox chkEstadisticas;
    private javax.swing.JCheckBox chkExportarCortes;
    private javax.swing.JCheckBox chkExportarFormato;
    private javax.swing.JCheckBox chkImportarCortes;
    private javax.swing.JCheckBox chkImportarMovimiento;
    private javax.swing.JCheckBox chkNomina;
    private javax.swing.JCheckBox chkPagarFormato;
    private javax.swing.JCheckBox chkRespaldo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lbUsuario;
    // End of variables declaration//GEN-END:variables
}
