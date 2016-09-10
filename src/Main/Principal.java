package Main;

import ControlUsuario.Loged;
import Encargado.Encargados;
import Sucursal.Sucursales;
import Ruta.Ruteros;
import Maquinas.ListaMaquinas;
import ControlUsuario.Usuarios;
import Formato.CapturaFormato;
import Formato.ConsultaFormato;
import Maquinas.Tarjetas.Tarjetas;
import Corte.CapturaCorte;
import Corte.ConsultaCorte;
import Estadistica.Encargados.EstadisticaEncargados;
import Estadistica.Maquinas.EstadisticasMaquinas;
import Etiquetas.Etiquetas;
import static Utilerias.Utileria.*;
import Inventario.SeleccionaCapturaMovimientos;
import Inventario.SeleccionaConsultaInventarios;
import Maquinas.Perifericos.Perifericos;
import Productos.Productos;
import Vales.Chequeras.Chequeras;
import Vales.ConsultaVales;
import Vales.SeleccionCaptura;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {
    
    public Principal(){
        initComponents();        
                        
        lblBienvenida.setText("Bienvenido "+quitaGuion(Loged.getLoged().getUsuario()));        
        this.aplicarPermisos();
        
    }
    
    private void aplicarPermisos(){        
        if(Loged.getLoged().getPermiso().equals("Invitado")){
            this.menuConfiguracion.setEnabled(false);
        }
        //permisos para consultar los menus de editar
        this.menuListaEmpleados.setEnabled(Loged.getLoged().isConsultarEmpleados());
        this.menuListaSucursales.setEnabled(Loged.getLoged().isConsultarSucursales());        
        this.menuListaProductos.setEnabled(Loged.getLoged().isConsultarProducto());
        this.menuListaMaquinas.setEnabled(Loged.getLoged().isConsultarMaquinas());
        this.menuListaTarjetas.setEnabled(Loged.getLoged().isConsultarTarjetas());
        this.btnRestaurarRespaldo.setEnabled(Loged.getLoged().isRespaldar());
        
        this.menuListaProductos.setEnabled(Loged.getLoged().isConsultarProducto());
        this.menuListaRuteros.setEnabled(Loged.getLoged().isConsultaRutero());
        
        this.btnCapturaFormato.setEnabled(Loged.getLoged().isCapturaFormato());
        this.btnCapturaCorte.setEnabled(Loged.getLoged().isCapturaCorte());
        this.btnCapturaMovimiento.setEnabled(Loged.getLoged().isCapturaMovimiento());
        
        this.btnConsultaFormato.setEnabled(Loged.getLoged().isConsultaFormato());
        this.btnConsultaCorte.setEnabled(Loged.getLoged().isConsultaCorte());
        this.btnConsultaMovimiento.setEnabled(Loged.getLoged().isConsultaMovimiento());
        
        this.btnEstadisticas.setEnabled(Loged.getLoged().isConsultaEstadisticas());
        this.btnEncargados.setEnabled(Loged.getLoged().isConsultaEncargados());
        
        this.btnCapturaVale.setEnabled(Loged.getLoged().isCapturaVales());
        this.btnConsultaVales.setEnabled(Loged.getLoged().isConsultaVales());
        
        this.menuEtiquetas.setEnabled(Loged.getLoged().isConsultaEtiquetas());
        this.menuPerifericos.setEnabled(Loged.getLoged().isConsultaPerifericos());
        this.menuChequeras.setEnabled(Loged.getLoged().isConsultaChequeras());
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem10 = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        btnConsultaFormato = new javax.swing.JButton();
        btnCapturaFormato = new javax.swing.JButton();
        lblBienvenida = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnConsultaCorte = new javax.swing.JButton();
        btnCapturaCorte = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnConsultaMovimiento = new javax.swing.JButton();
        btnCapturaMovimiento = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnEstadisticas = new javax.swing.JButton();
        btnEncargados = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        btnConsultaVales = new javax.swing.JButton();
        btnCapturaVale = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        menuArchivo = new javax.swing.JMenu();
        btnGenerarRespaldo = new javax.swing.JMenuItem();
        btnRestaurarRespaldo = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        BtnSalir = new javax.swing.JMenuItem();
        menuEditar = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        menuListaSucursales = new javax.swing.JMenuItem();
        menuListaProductos = new javax.swing.JMenuItem();
        menuEtiquetas = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuListaEmpleados = new javax.swing.JMenuItem();
        menuListaRuteros = new javax.swing.JMenuItem();
        menuChequeras = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        menuListaMaquinas = new javax.swing.JMenuItem();
        menuListaTarjetas = new javax.swing.JMenuItem();
        menuPerifericos = new javax.swing.JMenuItem();
        menuConfiguracion = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();

        jMenuItem10.setText("jMenuItem10");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Bon Ice Admin");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Formatos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel1.setToolTipText("MóduloFormatos ");

        btnConsultaFormato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultaFormato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/archivoFinal.png"))); // NOI18N
        btnConsultaFormato.setText("   Consulta");
        btnConsultaFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaFormatoActionPerformed(evt);
            }
        });
        btnConsultaFormato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnConsultaFormatoKeyTyped(evt);
            }
        });

        btnCapturaFormato.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapturaFormato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/ConsultaFinal.png"))); // NOI18N
        btnCapturaFormato.setText("    Captura");
        btnCapturaFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturaFormatoActionPerformed(evt);
            }
        });
        btnCapturaFormato.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCapturaFormatoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(btnCapturaFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnConsultaFormato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnCapturaFormato, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnConsultaFormato)
                .addGap(12, 12, 12))
        );

        lblBienvenida.setText("BIENVENIDO DON UREY");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cortes", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel3.setToolTipText("Módulo Máquinas");

        btnConsultaCorte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultaCorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/consultaLibro.png"))); // NOI18N
        btnConsultaCorte.setText("   Consulta");
        btnConsultaCorte.setPreferredSize(new java.awt.Dimension(183, 59));
        btnConsultaCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaCorteActionPerformed(evt);
            }
        });
        btnConsultaCorte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnConsultaCorteKeyTyped(evt);
            }
        });

        btnCapturaCorte.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapturaCorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/escribir.png"))); // NOI18N
        btnCapturaCorte.setText("    Captura");
        btnCapturaCorte.setPreferredSize(new java.awt.Dimension(183, 59));
        btnCapturaCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturaCorteActionPerformed(evt);
            }
        });
        btnCapturaCorte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCapturaCorteKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCapturaCorte, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                    .addComponent(btnConsultaCorte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnCapturaCorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnConsultaCorte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Inventarios", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel4.setToolTipText("Módulo Inventarios");

        btnConsultaMovimiento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultaMovimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Archivero_consulta.png"))); // NOI18N
        btnConsultaMovimiento.setText("   Consulta");
        btnConsultaMovimiento.setPreferredSize(new java.awt.Dimension(183, 59));
        btnConsultaMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaMovimientoActionPerformed(evt);
            }
        });
        btnConsultaMovimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnConsultaMovimientoKeyTyped(evt);
            }
        });

        btnCapturaMovimiento.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapturaMovimiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/Archivero .png"))); // NOI18N
        btnCapturaMovimiento.setText("    Captura");
        btnCapturaMovimiento.setPreferredSize(new java.awt.Dimension(183, 59));
        btnCapturaMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturaMovimientoActionPerformed(evt);
            }
        });
        btnCapturaMovimiento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCapturaMovimientoKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCapturaMovimiento, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(btnConsultaMovimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnCapturaMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnConsultaMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Concentrado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        btnEstadisticas.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEstadisticas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/estadistica.png"))); // NOI18N
        btnEstadisticas.setText("Estadisticas");
        btnEstadisticas.setPreferredSize(new java.awt.Dimension(183, 59));
        btnEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadisticasActionPerformed(evt);
            }
        });
        btnEstadisticas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnEstadisticasKeyTyped(evt);
            }
        });

        btnEncargados.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnEncargados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/encargados1.png"))); // NOI18N
        btnEncargados.setText("Encargados");
        btnEncargados.setPreferredSize(new java.awt.Dimension(183, 59));
        btnEncargados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEncargadosActionPerformed(evt);
            }
        });
        btnEncargados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnEncargadosKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnEstadisticas, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEncargados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEncargados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Vales", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel5.setToolTipText("Módulo Máquinas");

        btnConsultaVales.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnConsultaVales.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/caja.png"))); // NOI18N
        btnConsultaVales.setText("   Consulta");
        btnConsultaVales.setPreferredSize(new java.awt.Dimension(183, 59));
        btnConsultaVales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultaValesActionPerformed(evt);
            }
        });
        btnConsultaVales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnConsultaValesKeyTyped(evt);
            }
        });

        btnCapturaVale.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnCapturaVale.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/vale.png"))); // NOI18N
        btnCapturaVale.setText("    Captura");
        btnCapturaVale.setPreferredSize(new java.awt.Dimension(183, 59));
        btnCapturaVale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCapturaValeActionPerformed(evt);
            }
        });
        btnCapturaVale.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                btnCapturaValeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnCapturaVale, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                    .addComponent(btnConsultaVales, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(btnCapturaVale, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(btnConsultaVales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12))
        );

        menuArchivo.setText("Archivo");

        btnGenerarRespaldo.setText("Generar Respaldo");
        btnGenerarRespaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarRespaldoActionPerformed(evt);
            }
        });
        menuArchivo.add(btnGenerarRespaldo);

        btnRestaurarRespaldo.setText("Restaurar Respaldo");
        btnRestaurarRespaldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarRespaldoActionPerformed(evt);
            }
        });
        menuArchivo.add(btnRestaurarRespaldo);

        jMenuItem1.setText("Cambiar de Usuario");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuArchivo.add(jMenuItem1);

        BtnSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0));
        BtnSalir.setText("Salir");
        BtnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSalirActionPerformed(evt);
            }
        });
        menuArchivo.add(BtnSalir);

        jMenuBar1.add(menuArchivo);

        menuEditar.setText("Editar");

        jMenu3.setText("Empresa");

        menuListaSucursales.setText("Lista de Sucursales");
        menuListaSucursales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaSucursalesActionPerformed(evt);
            }
        });
        jMenu3.add(menuListaSucursales);

        menuListaProductos.setText("Lista de Productos");
        menuListaProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaProductosActionPerformed(evt);
            }
        });
        jMenu3.add(menuListaProductos);

        menuEtiquetas.setText("Lista de Etiquetas");
        menuEtiquetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEtiquetasActionPerformed(evt);
            }
        });
        jMenu3.add(menuEtiquetas);

        menuEditar.add(jMenu3);

        jMenu1.setText("Personal");

        menuListaEmpleados.setText("Lista de Empleados");
        menuListaEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaEmpleadosActionPerformed(evt);
            }
        });
        jMenu1.add(menuListaEmpleados);

        menuListaRuteros.setText("Lista de Ruteros");
        menuListaRuteros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaRuterosActionPerformed(evt);
            }
        });
        jMenu1.add(menuListaRuteros);

        menuChequeras.setText("Lista de Chequeras");
        menuChequeras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuChequerasActionPerformed(evt);
            }
        });
        jMenu1.add(menuChequeras);

        menuEditar.add(jMenu1);

        jMenu2.setText("Maquinas");

        menuListaMaquinas.setText("Lista de Máquinas");
        menuListaMaquinas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaMaquinasActionPerformed(evt);
            }
        });
        jMenu2.add(menuListaMaquinas);

        menuListaTarjetas.setText("Lista de Tarjetas");
        menuListaTarjetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuListaTarjetasActionPerformed(evt);
            }
        });
        jMenu2.add(menuListaTarjetas);

        menuPerifericos.setText("Lista de Perifericos");
        menuPerifericos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPerifericosActionPerformed(evt);
            }
        });
        jMenu2.add(menuPerifericos);

        menuEditar.add(jMenu2);

        jMenuBar1.add(menuEditar);

        menuConfiguracion.setText("Configuración");

        jMenuItem9.setText("Usuarios");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuConfiguracion.add(jMenuItem9);

        jMenuBar1.add(menuConfiguracion);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(lblBienvenida))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, Short.MAX_VALUE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 260, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBienvenida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_BtnSalirActionPerformed
    private void menuListaEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaEmpleadosActionPerformed
           Encargados e= new Encargados();
           e.setLocationRelativeTo(this);
           e.setVisible(true);           
    }//GEN-LAST:event_menuListaEmpleadosActionPerformed
    private void btnCapturaFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturaFormatoActionPerformed
        CapturaFormato c = new CapturaFormato();
        c.setLocationRelativeTo(this);
        c.setVisible(true);        
    }//GEN-LAST:event_btnCapturaFormatoActionPerformed
    private void menuListaSucursalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaSucursalesActionPerformed
       Sucursales s = new Sucursales();
       s.setLocationRelativeTo(this);
       s.setVisible(true);       
    }//GEN-LAST:event_menuListaSucursalesActionPerformed
    private void btnConsultaFormatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaFormatoActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                      
        ConsultaFormato c = new ConsultaFormato();                               
        c.setLocationRelativeTo(null);
        c.setVisible(true);                               
        setCursor(null);                                        
    }//GEN-LAST:event_btnConsultaFormatoActionPerformed
    private void btnCapturaCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturaCorteActionPerformed
        CapturaCorte cap= new CapturaCorte();
        cap.setLocationRelativeTo(this);
        cap.setVisible(true);        
    }//GEN-LAST:event_btnCapturaCorteActionPerformed
    private void btnConsultaCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaCorteActionPerformed
       setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));   
       ConsultaCorte con= new ConsultaCorte();
       con.setLocationRelativeTo(this);
       con.setVisible(true);       
       setCursor(null);            
    }//GEN-LAST:event_btnConsultaCorteActionPerformed
    private void btnCapturaFormatoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCapturaFormatoKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnCapturaFormatoActionPerformed(null);
        }
    }//GEN-LAST:event_btnCapturaFormatoKeyTyped
    private void btnConsultaFormatoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsultaFormatoKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnConsultaFormatoActionPerformed(null);
        }
    }//GEN-LAST:event_btnConsultaFormatoKeyTyped
    private void btnCapturaCorteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCapturaCorteKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnCapturaCorteActionPerformed(null);
        }
    }//GEN-LAST:event_btnCapturaCorteKeyTyped
    private void btnConsultaCorteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsultaCorteKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnConsultaCorteActionPerformed(null);
        }
    }//GEN-LAST:event_btnConsultaCorteKeyTyped
    private void menuListaMaquinasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaMaquinasActionPerformed
       ListaMaquinas lm= new ListaMaquinas();
       lm.setVisible(true);
       lm.setLocationRelativeTo(this);
    }//GEN-LAST:event_menuListaMaquinasActionPerformed
    private void menuListaTarjetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaTarjetasActionPerformed
       Tarjetas t = new Tarjetas();
       t.setLocationRelativeTo(this);
       t.setVisible(true);       
    }//GEN-LAST:event_menuListaTarjetasActionPerformed
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        Usuarios u = new Usuarios();        
        u.setLocationRelativeTo(this);
        u.setVisible(true);
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void btnGenerarRespaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarRespaldoActionPerformed
        if(JOptionPane.showConfirmDialog(rootPane, "Se creará una copia de seguridad de la base de datos\nsustituirá la ultima copia y no podrá recuperarla", "Atención", JOptionPane.YES_NO_OPTION) == 0){
            //copiar los archivos de la ruta original a la ruta C:/Bon Ice Admin/Respaldo/
            respaldar("Archivos/Formatos.bin");                        
            respaldar("Archivos/Cortes.bin");            
            respaldar("Archivos/Encargados.bin");
            respaldar("Archivos/Maquinas.bin");            
            respaldar("Archivos/Sucursales.bin");
            respaldar("Archivos/Tarjetas.bin");            
            respaldar("Archivos/Perifericos.bin");
            respaldar("Archivos/Chequeras.bin");
            respaldar("Archivos/Etiquetas.bin");
            respaldar("Archivos/Vales.bin");
            respaldar("Archivos/Inventario.data");
            respaldar("Archivos/Movimientos.data");
            respaldar("Archivos/Ruteros.data");
            respaldar("Archivos/Productos.data");
            respaldar("Archivos/Control.data");
                                    
//            JOptionPane.showMessageDialog(null,"Es usted un savandija por respaldar correctamente :)","Yeeeiii!!",JOptionPane.INFORMATION_MESSAGE);
        }       
    }//GEN-LAST:event_btnGenerarRespaldoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        Login log =new Login();                   
        log.setVisible(true);
        log.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnRestaurarRespaldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarRespaldoActionPerformed
        //para restaurar seleccionar carpeta con los archivos con un JFileChooser
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //fc.setCurrentDirectory();
        fc.setToolTipText("Selecciona la carpeta con los archivos a restaurar");        
        if(JFileChooser.APPROVE_OPTION==fc.showDialog(this,"Seleccionar")){            
               File formatos= new File(fc.getSelectedFile().toString().concat("/Formatos.bin"));                                        
               File cortes = new File(fc.getSelectedFile().toString().concat("/Cortes.bin"));               
               File encargados = new File(fc.getSelectedFile().toString().concat("/Encargados.bin"));
               File maquinas = new File(fc.getSelectedFile().toString().concat("/Maquinas.bin"));               
               File sucursales = new File(fc.getSelectedFile().toString().concat("/Sucursales.bin"));
               File tarjetas = new File(fc.getSelectedFile().toString().concat("/Tarjetas.bin"));               
               File Productos = new File(fc.getSelectedFile().toString().concat("/Productos.data"));
               File Inventario= new File(fc.getSelectedFile().toString().concat("/Inventario.data"));
               File Movimientos=  new File(fc.getSelectedFile().toString().concat("/Movimientos.data"));
               File Ruteros = new File(fc.getSelectedFile().toString().concat("/Ruteros.data"));
               File Perifericos = new File(fc.getSelectedFile().toString().concat("/Perifericos.bin"));
               File Chequeras = new File(fc.getSelectedFile().toString().concat("/Chequeras.bin"));
               File Etiquetas = new File(fc.getSelectedFile().toString().concat("/Etiquetas.bin"));
               File Vales = new File(fc.getSelectedFile().toString().concat("/Vales.bin"));
                                           
               restaurar(formatos,"Archivos/Formatos.bin");                              
               restaurar(cortes,"Archivos/Cortes.bin");               
               restaurar(encargados,"Archivos/Encargados.bin");
               restaurar(maquinas,"Archivos/Maquinas.bin");               
               restaurar(sucursales,"Archivos/Sucursales.bin");
               restaurar(tarjetas,"Archivos/Tarjetas.bin");                    
               restaurar(Productos,"Archivos/Productos.data");         
               restaurar(Inventario,"Archivos/Inventario.data");         
               restaurar(Movimientos,"Archivos/Movimientos.data");         
               restaurar(Ruteros,"Archivos/Ruteros.data");    
               restaurar(Perifericos, "Archivos/Perifericos.bin");
               restaurar(Chequeras, "Archivos/Chequeras.bin");
               restaurar(Etiquetas, "Archivos/Etiquetas.bin");
               restaurar(Vales, "Archivos/Vales.bin");
               JOptionPane.showMessageDialog(null,"Archivos Restaurados con éxito","Exito",JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnRestaurarRespaldoActionPerformed

    private void menuListaProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaProductosActionPerformed
        Productos p = new Productos();
        p.setVisible(true);
        p.setLocationRelativeTo(this);        
    }//GEN-LAST:event_menuListaProductosActionPerformed

    private void btnConsultaMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaMovimientoActionPerformed
        SeleccionaConsultaInventarios cm = new SeleccionaConsultaInventarios();
        cm.setLocationRelativeTo(this);
        cm.setVisible(true);
    }//GEN-LAST:event_btnConsultaMovimientoActionPerformed

    private void btnConsultaMovimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsultaMovimientoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_btnConsultaMovimientoKeyTyped

    private void btnCapturaMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturaMovimientoActionPerformed
        SeleccionaCapturaMovimientos cm = new SeleccionaCapturaMovimientos();
        cm.setLocationRelativeTo(this);
        cm.setVisible(true);
    }//GEN-LAST:event_btnCapturaMovimientoActionPerformed

    private void btnCapturaMovimientoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCapturaMovimientoKeyTyped
        btnCapturaMovimientoActionPerformed(null);
    }//GEN-LAST:event_btnCapturaMovimientoKeyTyped

    private void menuListaRuterosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuListaRuterosActionPerformed
       Ruteros r = new Ruteros();
       r.setLocationRelativeTo(this);
       r.setVisible(true);
    }//GEN-LAST:event_menuListaRuterosActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed
        EstadisticasMaquinas es = new EstadisticasMaquinas();
        //es.setLocationRelativeTo(this);
        es.setVisible(true);        
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    private void menuPerifericosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuPerifericosActionPerformed
        Perifericos p = new Perifericos();
        p.setLocationRelativeTo(this);
        p.setVisible(true);
    }//GEN-LAST:event_menuPerifericosActionPerformed

    private void btnEncargadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEncargadosActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                      
        EstadisticaEncargados eg = new EstadisticaEncargados();
        eg.setLocationRelativeTo(null);
        eg.setVisible(true);
        setCursor(null);
    }//GEN-LAST:event_btnEncargadosActionPerformed

    private void btnConsultaValesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultaValesActionPerformed
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));                      
        ConsultaVales cv = new ConsultaVales();
        cv.setLocationRelativeTo(this);
        cv.setVisible(true);
        setCursor(null);                      
    }//GEN-LAST:event_btnConsultaValesActionPerformed

    private void btnConsultaValesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnConsultaValesKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnConsultaValesActionPerformed(null);
        }
    }//GEN-LAST:event_btnConsultaValesKeyTyped

    private void btnCapturaValeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCapturaValeActionPerformed
        SeleccionCaptura sc = new SeleccionCaptura();
        sc.setLocationRelativeTo(this);
        sc.setVisible(true);        
    }//GEN-LAST:event_btnCapturaValeActionPerformed

    private void btnCapturaValeKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnCapturaValeKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnCapturaValeActionPerformed(null);
        }
    }//GEN-LAST:event_btnCapturaValeKeyTyped

    private void btnEstadisticasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEstadisticasKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnEstadisticasActionPerformed(null);
        }
    }//GEN-LAST:event_btnEstadisticasKeyTyped

    private void btnEncargadosKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnEncargadosKeyTyped
        char tecla=evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            this.btnEncargadosActionPerformed(null);
        }
    }//GEN-LAST:event_btnEncargadosKeyTyped

    private void menuChequerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuChequerasActionPerformed
        Chequeras ch = new Chequeras();
        ch.setLocationRelativeTo(this);
        ch.setVisible(true);
    }//GEN-LAST:event_menuChequerasActionPerformed

    private void menuEtiquetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEtiquetasActionPerformed
        Etiquetas e = new Etiquetas();
        e.setLocationRelativeTo(null);
        e.setVisible(true);        
    }//GEN-LAST:event_menuEtiquetasActionPerformed
           
    private void respaldar(String ruta) {
        //crear el archivo de entrada
        try {
            FileReader fr= new FileReader(ruta);
            FileWriter fw = new FileWriter(rutaRespaldo(ruta));            
            int c;
            while((c=fr.read())!=-1){
                fw.write(c);
            }
            fr.close();
            fw.close();
        } catch (FileNotFoundException e) {
//            JOptionPane.showMessageDialog(rootPane, "No se encontro: "+ruta,"Error",JOptionPane.ERROR_MESSAGE);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }catch (IOException e){
            JOptionPane.showMessageDialog(rootPane, "Fallo de escritura en: "+ruta,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void restaurar(File entrada, String salida){
        //crear arhivo de entrada
        try {
            FileReader fr = new FileReader(entrada);
            FileWriter fw = new FileWriter(new File(salida));
            int c;
            while((c=fr.read())!=-1){
                fw.write(c);
            }
            fr.close();
            fw.close();
        } catch (FileNotFoundException e) {
             JOptionPane.showMessageDialog(rootPane, "No se encontro: "+entrada,"Error",JOptionPane.ERROR_MESSAGE);
             e.printStackTrace();
        }catch(IOException e){
            JOptionPane.showMessageDialog(rootPane, "Fallo de escritura en: "+salida,"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private String rutaRespaldo(String ruta){
        String rutaSalida="";        
        rutaSalida = ruta.substring(0,9)+"Respaldo"+ruta.subSequence(8,ruta.length());        
        return rutaSalida;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem BtnSalir;
    private javax.swing.JButton btnCapturaCorte;
    private javax.swing.JButton btnCapturaFormato;
    private javax.swing.JButton btnCapturaMovimiento;
    private javax.swing.JButton btnCapturaVale;
    private javax.swing.JButton btnConsultaCorte;
    private javax.swing.JButton btnConsultaFormato;
    private javax.swing.JButton btnConsultaMovimiento;
    private javax.swing.JButton btnConsultaVales;
    private javax.swing.JButton btnEncargados;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JMenuItem btnGenerarRespaldo;
    private javax.swing.JMenuItem btnRestaurarRespaldo;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JMenu menuArchivo;
    private javax.swing.JMenuItem menuChequeras;
    private javax.swing.JMenu menuConfiguracion;
    private javax.swing.JMenu menuEditar;
    private javax.swing.JMenuItem menuEtiquetas;
    private javax.swing.JMenuItem menuListaEmpleados;
    private javax.swing.JMenuItem menuListaMaquinas;
    private javax.swing.JMenuItem menuListaProductos;
    private javax.swing.JMenuItem menuListaRuteros;
    private javax.swing.JMenuItem menuListaSucursales;
    private javax.swing.JMenuItem menuListaTarjetas;
    private javax.swing.JMenuItem menuPerifericos;
    // End of variables declaration//GEN-END:variables

}
