/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nomina;

import Encargado.Encargado;
import Formato.ConsultaFormato;
import Formato.Formato;
import Utilerias.Utileria;
import static Utilerias.Utileria.quitaEspacios;
import static Utilerias.Utileria.quitaGuion;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import nomina.incidentes.Incidente;
import nomina.prestamos.Prestamo;

/**
 *
 * @author Ulises Beltrán Gómez --- beltrangomezulises@gmail.com
 */
public class ConsultaNomina extends javax.swing.JFrame {

    private final List<Encargado> encargados = Encargado.cargarEncargados();
    private Vector<Formato> formatos;
    private final ArrayList<Incidente> incidentes;
    private final ArrayList<Prestamo> prestamos;
    private float pagarAEmpleado = 0;

    //cada elemento de estas lista le corresponde un nombre de trabajador
    private ArrayList<Integer> cantTurnos;
    private ArrayList<Float> cantFormatos;
    private ArrayList<Float> cantComisiones;
    private ArrayList<Integer> cantPrestamos;
    private ArrayList<Integer> cantIncidentes;

    private List<Formato> formatosFiltrados;
    private List<Incidente> incidentesFiltrados;
    private List<Prestamo> prestamosFiltrados;

    private ArrayList<Float> totalAPagarPorEmpleado;

    private ArrayList<Encargado> activos = new ArrayList<>();
    private ArrayList<Encargado> inactivos = new ArrayList<>();

    Date lunesAnterior;
    Date domingoPosterior;

    public ConsultaNomina() {
        initComponents();
        this.myInitComponents();

        formatos = Formato.cargarFormatos();
        prestamos = Prestamo.cargarPrestamos();
        incidentes = Incidente.cargarIncidentes();

        Vector<String> sucursales = new Vector<>();

        //hacer la revision de los formatos para obtener el total
        for (int i = 0; i < formatos.size(); i++) {
            if (!sucursales.contains(formatos.elementAt(i).getSucursal())) {
                sucursales.add(formatos.elementAt(i).getSucursal());
            }
        }

        Formato.ordenarFormatosQ(formatos, 0, formatos.size() - 1);
        formatos = Formato.ordenarFormatosSucursal(formatos, sucursales);
        Formato.ordenarFormatosTurno(formatos);

        this.asignarFaltantes();

    }

    private void initData() {
        //lbRangofechas.setText("Del: " + Utileria.SDF.format(Utileria.lunesAnterior((Date) evt.getNewValue())) + " al: " + Utileria.SDF.format(Utileria.domingoPosterior((Date) evt.getNewValue())));

        if (!chkNoPagados.isSelected()) {
            Date d = datechooserGeneral.getDate();
            lbRangofechas.setText("Del: " + Utileria.SDF.format(Utileria.lunesAnterior(d)) + " al: " + Utileria.SDF.format(Utileria.domingoPosterior(d)));

            if (d != null) {

                lunesAnterior = Utileria.lunesAnterior(d);
                domingoPosterior = Utileria.domingoPosterior(d);
                
                formatosFiltrados = filtrarFormatosFecha(lunesAnterior, domingoPosterior);                
                incidentesFiltrados = filtrarIncidentesFecha(lunesAnterior, domingoPosterior);
                prestamosFiltrados = filtrarPrestamosFecha(lunesAnterior, domingoPosterior);
                
            } else {
                JOptionPane.showMessageDialog(null, "Necesita asignar una fecha", "Atención", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } else {
            formatosFiltrados = formatos.stream().filter(f -> f.getPagado() == false).collect(toList());
            incidentesFiltrados = incidentes.stream().filter(i -> i.isPagado() == false).collect(toList());
            prestamosFiltrados = prestamos.stream().filter(p -> p.isPagado() == false).collect(toList());
        }
        
        //llenar el combobox de empleados
            ArrayList<String> nombreEmpleados = new ArrayList<>();
            formatosFiltrados.stream().filter(formatosFiltrado -> !nombreEmpleados.contains(quitaGuion(formatosFiltrado.getEncargado())))                    
                    .forEach( formatosFiltrado -> {
                        nombreEmpleados.add(quitaGuion(formatosFiltrado.getEncargado()));
                    });            

            Collections.sort(nombreEmpleados);
            
            DefaultComboBoxModel boxModel = (DefaultComboBoxModel) cmbEmpleado.getModel();

            boxModel.removeAllElements();
            nombreEmpleados.stream().forEach(nombreEmpleado -> {
                boxModel.addElement(nombreEmpleado);
            });
            //cmbEmpleado.setModel(boxModel);
            //generar la tabla general                        
            GeneralThread generalThread = new GeneralThread(formatosFiltrados, incidentesFiltrados, prestamosFiltrados, nombreEmpleados);
            generalThread.start();

            //generar tabla de nomina por empleado
            EmployeeThread employeeThread = new EmployeeThread();
            employeeThread.start();

            //generar la lista de deudores
            DebtThread debtThread = new DebtThread();
            debtThread.start();

    }

    private void myInitComponents() {
        tablaGeneral.setAutoCreateRowSorter(true);
        tablaGeneral.getTableHeader().setReorderingAllowed(false);
        tablaFormatos.setAutoCreateRowSorter(true);
        tablaFormatos.getTableHeader().setReorderingAllowed(false);
        tablaIncidentes.setAutoCreateRowSorter(true);
        tablaIncidentes.getTableHeader().setReorderingAllowed(false);
        tablaPrestamos.setAutoCreateRowSorter(true);
        tablaPrestamos.getTableHeader().setReorderingAllowed(false);
        tablaDeudoresActivos.setAutoCreateRowSorter(true);
        tablaDeudoresActivos.getTableHeader().setReorderingAllowed(false);
        tablaDeudoresInactivos.setAutoCreateRowSorter(true);
        tablaDeudoresInactivos.getTableHeader().setReorderingAllowed(false);

        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.chkPagarConComision.setSelected(true);
        this.chkPagarConPendiente.setSelected(true);
        this.tablaTotales.setDefaultRenderer(Object.class, new TablaTotalesRenderer());        
//        datechooserGeneral.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                if (evt.getPropertyName().equals("date")) {
//                    initData(evt);
//                }
//            }
//        });
    }

    private List<Formato> filtrarFormatosFecha(Date inicio, Date fin) {
        return formatos.stream().filter((f) -> {
            try {
                return !Utileria.SDF.parse(f.getFecha()).before(inicio) && !Utileria.SDF.parse(f.getFecha()).after(fin);
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaNomina.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }).collect(toList());
    }

    private ArrayList<Incidente> filtrarIncidentesFecha(Date inicio, Date fin) {
        ArrayList<Incidente> misIncidentesFiltrados = new ArrayList<>(incidentes);
        int i = 0;
        while (i < misIncidentesFiltrados.size()) {
            try {
                Date fechaIncidente = Utileria.SDF.parse(misIncidentesFiltrados.get(i).getFecha());
                if (fechaIncidente.before(inicio) || fechaIncidente.after(fin)) {
                    misIncidentesFiltrados.remove(i);
                } else {
                    i++;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaNomina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return misIncidentesFiltrados;
    }

    private ArrayList<Prestamo> filtrarPrestamosFecha(Date inicio, Date fin) {
        ArrayList<Prestamo> misPrestamosFiltrados = new ArrayList<>(prestamos);
        int i = 0;
        while (i < misPrestamosFiltrados.size()) {
            try {
                Date fechaPrestamo = Utileria.SDF.parse(misPrestamosFiltrados.get(i).getFecha());
                if (fechaPrestamo.before(inicio) || fechaPrestamo.after(fin)) {
                    misPrestamosFiltrados.remove(i);
                } else {
                    i++;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaNomina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return misPrestamosFiltrados;
    }

    private void asignarFaltantes() {
        GregorianCalendar c = new GregorianCalendar();

        for (int k = 0; k < formatos.size() - 1; k++) {
            if (formatos.elementAt(k).getSucursal().equals(formatos.elementAt(k + 1).getSucursal())) {
                if (formatos.elementAt(k).getTurnos() == 2) {
                    if (formatos.elementAt(k).getTurno() == 'D') {
                        if (formatos.elementAt(k).getFecha().equals(formatos.elementAt(k + 1).getFecha())) {
                            //asignar primero los sobrantes                            
                            formatos.elementAt(k).setSobrante(formatos.elementAt(k).sobrante(formatos.elementAt(k + 1)));
                            formatos.elementAt(k).setSobranteUtilidad(formatos.elementAt(k).sobranteUtilidad(formatos.elementAt(k + 1)));
                            formatos.elementAt(k).setSobranteComision(formatos.elementAt(k).sobranteComision(formatos.elementAt(k + 1)));
                            formatos.elementAt(k).setSobranteFondo(formatos.elementAt(k).sobranteFondo(formatos.elementAt(k + 1)));
                            if (formatos.elementAt(k).getFirma()) {//firma para saber hacia donde iran los faltantes
                                //firmado los faltantes seran los anteriores de k+1                                                                
                                formatos.elementAt(k + 1).setFaltante(formatos.elementAt(k).faltante(formatos.elementAt(k + 1)));
                                formatos.elementAt(k + 1).setFaltanteUtilidad(formatos.elementAt(k).faltanteUtilidad(formatos.elementAt(k + 1)));
                                formatos.elementAt(k + 1).setFaltanteComision(formatos.elementAt(k).faltanteComision(formatos.elementAt(k + 1)));
                                formatos.elementAt(k + 1).setFondoFaltante(formatos.elementAt(k).faltanteFondo(formatos.elementAt(k + 1)));

                            } else {
                                //no firmado sera los posteriores de k                                                                
                                formatos.elementAt(k).setFaltante(formatos.elementAt(k).faltante(formatos.elementAt(k + 1)));
                                formatos.elementAt(k).setFaltanteUtilidad(formatos.elementAt(k).faltanteUtilidad(formatos.elementAt(k + 1)));
                                formatos.elementAt(k).setFaltanteComision(formatos.elementAt(k).faltanteComision(formatos.elementAt(k + 1)));
                                formatos.elementAt(k).setFondoFaltante(formatos.elementAt(k).faltanteFondo(formatos.elementAt(k + 1)));
                            }
                        }
                    } else {
                        try {
                            c.setTime(Utileria.SDF.parse(formatos.elementAt(k).getFecha()));
                            c.add(Calendar.DATE, 1); //sumarle un dia
                            if (formatos.elementAt(k + 1).getFecha().equals(Utileria.SDF.format(c.getTime())) && formatos.elementAt(k + 1).getTurno() == 'D') {
                                //si es el correcto asignar sobrantes
                                //asignar primero los sobrantes
//                                System.out.println(formatos.elementAt(k));
//                                System.out.println(formatos.elementAt(k+1));                                
                                formatos.elementAt(k).setSobranteUtilidad(formatos.elementAt(k).sobranteUtilidad(formatos.elementAt(k + 1)));
                                formatos.elementAt(k).setSobranteComision(formatos.elementAt(k).sobranteComision(formatos.elementAt(k + 1)));
                                formatos.elementAt(k).setSobrante(formatos.elementAt(k).sobrante(formatos.elementAt(k + 1)));
                                formatos.elementAt(k).setSobranteFondo(formatos.elementAt(k).sobranteFondo(formatos.elementAt(k + 1)));
                                if (formatos.elementAt(k).getFirma()) {
                                    //firmado los faltantes seran los anteriores de k+1                                                                        
                                    formatos.elementAt(k + 1).setFaltante(formatos.elementAt(k).faltante(formatos.elementAt(k + 1)));
                                    formatos.elementAt(k + 1).setFaltanteUtilidad(formatos.elementAt(k).faltanteUtilidad(formatos.elementAt(k + 1)));
                                    formatos.elementAt(k + 1).setFaltanteComision(formatos.elementAt(k).faltanteComision(formatos.elementAt(k + 1)));
                                    formatos.elementAt(k + 1).setFondoFaltante(formatos.elementAt(k).faltanteFondo(formatos.elementAt(k + 1)));
                                } else {
                                    //no firmado sera los posteriores de k                                                                       
                                    formatos.elementAt(k).setFaltante(formatos.elementAt(k).faltante(formatos.elementAt(k + 1)));
                                    formatos.elementAt(k).setFaltanteUtilidad(formatos.elementAt(k).faltanteUtilidad(formatos.elementAt(k + 1)));
                                    formatos.elementAt(k).setFaltanteComision(formatos.elementAt(k).faltanteComision(formatos.elementAt(k + 1)));
                                    formatos.elementAt(k).setFondoFaltante(formatos.elementAt(k).faltanteFondo(formatos.elementAt(k + 1)));
                                }
                            }
                        } catch (ParseException e) {
                            JOptionPane.showMessageDialog(rootPane, "No se logró marcar los formatos, por error de parseo de fechas", "Error", JOptionPane.ERROR_MESSAGE);
                            e.printStackTrace();
                        }
                    }
                } else {
                    try {
                        //tomar la fecha del formato k y preguntar si el siguiente formato es el siguiente dia y turno 'D'                    
                        c.setTime(Utileria.SDF.parse(formatos.elementAt(k).getFecha()));
                        c.add(Calendar.DATE, 1); //sumarle un dia a la fecha
                        if (formatos.elementAt(k + 1).getFecha().equals(Utileria.SDF.format(c.getTime())) && formatos.elementAt(k + 1).getTurno() == 'D') {
                            //asignar primero los sobrantes                                                                       
                            formatos.elementAt(k).setSobrante(formatos.elementAt(k).sobrante(formatos.elementAt(k + 1)));
                            formatos.elementAt(k).setSobranteUtilidad(formatos.elementAt(k).sobranteUtilidad(formatos.elementAt(k + 1)));
                            formatos.elementAt(k).setSobranteComision(formatos.elementAt(k).sobranteComision(formatos.elementAt(k + 1)));
                            formatos.elementAt(k).setSobranteFondo(formatos.elementAt(k).sobranteFondo(formatos.elementAt(k + 1)));
                            if (formatos.elementAt(k).getFirma()) {
                                //firmado los faltantes seran los anteriores de k+1                                                        
                                formatos.elementAt(k + 1).setFaltante(formatos.elementAt(k).faltante(formatos.elementAt(k + 1)));
                                formatos.elementAt(k + 1).setFaltanteUtilidad(formatos.elementAt(k).faltanteUtilidad(formatos.elementAt(k + 1)));
                                formatos.elementAt(k + 1).setFaltanteComision(formatos.elementAt(k).faltanteComision(formatos.elementAt(k + 1)));
                                formatos.elementAt(k + 1).setFondoFaltante(formatos.elementAt(k).faltanteFondo(formatos.elementAt(k + 1)));
                            } else {
                                //no firmado sera los posteriores de k                                                        
                                formatos.elementAt(k).setFaltante(formatos.elementAt(k).faltante(formatos.elementAt(k + 1)));
                                formatos.elementAt(k).setFaltanteUtilidad(formatos.elementAt(k).faltanteUtilidad(formatos.elementAt(k + 1)));
                                formatos.elementAt(k).setFaltanteComision(formatos.elementAt(k).faltanteComision(formatos.elementAt(k + 1)));
                                formatos.elementAt(k).setFondoFaltante(formatos.elementAt(k).faltanteFondo(formatos.elementAt(k + 1)));
                            }
                        }
                    } catch (ParseException e) {
                        JOptionPane.showMessageDialog(rootPane, "No se logró marcar los formatos, por error de parseo de fechas", "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelPestaña = new javax.swing.JTabbedPane();
        panelGeneral = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaGeneral = new javax.swing.JTable();
        panelEmpleado = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        cmbEmpleado = new javax.swing.JComboBox<>();
        btnPagar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaFormatos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPrestamos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaIncidentes = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaTotales = new javax.swing.JTable();
        chkPagarConComision = new javax.swing.JCheckBox();
        chkPagarConPendiente = new javax.swing.JCheckBox();
        panelDeudores = new javax.swing.JPanel();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaDeudoresActivos = new javax.swing.JTable();
        btnAbonarActivo = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaDeudoresInactivos = new javax.swing.JTable();
        btnAbonarInactivo = new javax.swing.JButton();
        panelFiltro = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        datechooserGeneral = new com.toedter.calendar.JDateChooser();
        lbRangofechas = new javax.swing.JLabel();
        btnGenerar = new javax.swing.JButton();
        chkNoPagados = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nómina");

        tablaGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Empleado", "Turnos", "Formatos", "Comisión", "Prestamos", "Incidentes", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaGeneral);

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
        );

        panelPestaña.addTab("General", panelGeneral);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Empleado:");

        cmbEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbEmpleadoActionPerformed(evt);
            }
        });

        btnPagar.setText("Pagar");
        btnPagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPagar)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnPagar))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaFormatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha - Turno", "Sucursal", "Comision", "Venta"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tablaFormatos);

        tablaPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Autorizó", "Nota", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaPrestamos);

        tablaIncidentes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Fecha", "Nota", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaIncidentes);

        jLabel2.setText("Prestamos");

        jLabel4.setText("Incidentes");

        tablaTotales.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Concepto", "Cantidad"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(tablaTotales);

        chkPagarConComision.setText("Pagar con comisión");
        chkPagarConComision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPagarConComisionActionPerformed(evt);
            }
        });

        chkPagarConPendiente.setText("Pendiente");
        chkPagarConPendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkPagarConPendienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelEmpleadoLayout = new javax.swing.GroupLayout(panelEmpleado);
        panelEmpleado.setLayout(panelEmpleadoLayout);
        panelEmpleadoLayout.setHorizontalGroup(
            panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane5)
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(chkPagarConPendiente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(chkPagarConComision)))))
                .addContainerGap())
        );
        panelEmpleadoLayout.setVerticalGroup(
            panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(4, 4, 4)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chkPagarConComision)
                            .addComponent(chkPagarConPendiente))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelPestaña.addTab("Por Empleado", panelEmpleado);

        jSplitPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jSplitPane1.setDividerLocation(650);

        jLabel5.setText("Activos");

        tablaDeudoresActivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Nombre Completo", "Fecha ultimo mov.", "Deuda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane6.setViewportView(tablaDeudoresActivos);

        btnAbonarActivo.setText("Abonar");
        btnAbonarActivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarActivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAbonarActivo)
                .addContainerGap())
            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnAbonarActivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        jLabel6.setText("Inactivos");

        tablaDeudoresInactivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Nombre Completo", "Fecha ultimo mov.", "Deuda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(tablaDeudoresInactivos);

        btnAbonarInactivo.setText("Abonar");
        btnAbonarInactivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbonarInactivoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAbonarInactivo)
                .addContainerGap())
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 574, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(btnAbonarInactivo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(jPanel2);

        javax.swing.GroupLayout panelDeudoresLayout = new javax.swing.GroupLayout(panelDeudores);
        panelDeudores.setLayout(panelDeudoresLayout);
        panelDeudoresLayout.setHorizontalGroup(
            panelDeudoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );
        panelDeudoresLayout.setVerticalGroup(
            panelDeudoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1)
        );

        panelPestaña.addTab("Deudores", panelDeudores);

        panelFiltro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Semana:");

        lbRangofechas.setText("del: al:");

        btnGenerar.setText("Generar");
        btnGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarActionPerformed(evt);
            }
        });

        chkNoPagados.setText("Todos no pagados");
        chkNoPagados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNoPagadosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelFiltroLayout = new javax.swing.GroupLayout(panelFiltro);
        panelFiltro.setLayout(panelFiltroLayout);
        panelFiltroLayout.setHorizontalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltroLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(datechooserGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbRangofechas))
                    .addComponent(chkNoPagados))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFiltroLayout.setVerticalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGenerar)
                        .addComponent(lbRangofechas))
                    .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(datechooserGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chkNoPagados)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPestaña)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelPestaña))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cmbEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEmpleadoActionPerformed
        EmployeeThread employeeThread = new EmployeeThread();
        employeeThread.start();
    }//GEN-LAST:event_cmbEmpleadoActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        String empleado = "";

        try {
            empleado = cmbEmpleado.getSelectedItem().toString();
            Object res = JOptionPane.showInputDialog("Ingrese la cantidad a pagar de " + empleado + ":", "0");
            int pago = 0;
            if (res != null) {
                pago = Integer.valueOf(res.toString());
            } else {
                return;
            }

            if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea pagar a " + empleado + " la cantidad de $" + pago + "?", "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                float nuevoPendiente = pagarAEmpleado - pago;
                String notaPendiente = "";

                //obtener la nota del pendiente en caso de existir
                if (nuevoPendiente > 0) {
                    notaPendiente = JOptionPane.showInputDialog(null, "ingrese la nota del pendiente", "Atención", JOptionPane.INFORMATION_MESSAGE);
                }

                if (!chkNoPagados.isSelected()) {
                    for (Encargado encargado : encargados) {
                        if (encargado.getNombre().equals(empleado)) {
                            encargado.setPendiente(nuevoPendiente);
                            encargado.setNotaPendiente(notaPendiente);
                            encargado.setFechaPendiente(new Date()); //ultima fecha de actualizacion del pendiente
                            break;
                        }
                    }

                    //marcar formatos como pagados, marcar prestamos como pagados, marcar incidentes como pagados            
                    //actualizar formatos 
                    for (Formato formato : formatos) {
                        Date fechaFormato = Utileria.SDF.parse(formato.getFecha());
                        if (quitaGuion(formato.getEncargado()).equals(empleado)) {
                            //este formato corresponde a la semana de pago
                            formato.setPagado(true);
                        }
                    }
                    //actualizar prestamos
                    for (Prestamo prestamo : prestamos) {
                        Date fechaPrestamo = Utileria.SDF.parse(prestamo.getFecha());
                        if (prestamo.getEncargado().equals(empleado)) {
                            prestamo.setPagado(true);
                        }
                    }
                    //actualizar pendientes
                    for (Incidente incidente : incidentes) {
                        Date fechaIncidente = Utileria.SDF.parse(incidente.getFecha());
                        if (incidente.getResponsable().equals(empleado)) {
                            incidente.setPagado(true);
                        }
                    }
                } else {
                    for (Encargado encargado : encargados) {
                        if (encargado.getNombre().equals(empleado)) {
                            encargado.setPendiente(nuevoPendiente);
                            encargado.setNotaPendiente(notaPendiente);
                            encargado.setFechaPendiente(new Date()); //ultima fecha de actualizacion del pendiente
                            break;
                        }
                    }

                    //marcar formatos como pagados, marcar prestamos como pagados, marcar incidentes como pagados            
                    //actualizar formatos 
                    for (Formato formato : formatos) {
                        Date fechaFormato = Utileria.SDF.parse(formato.getFecha());
                        if (quitaGuion(formato.getEncargado()).equals(empleado) && !fechaFormato.before(lunesAnterior) && !fechaFormato.after(domingoPosterior)) {
                            //este formato corresponde a la semana de pago
                            formato.setPagado(true);
                        }
                    }
                    //actualizar prestamos
                    for (Prestamo prestamo : prestamos) {
                        Date fechaPrestamo = Utileria.SDF.parse(prestamo.getFecha());
                        if (prestamo.getEncargado().equals(empleado) && !fechaPrestamo.before(lunesAnterior) && !fechaPrestamo.after(domingoPosterior)) {
                            prestamo.setPagado(true);
                        }
                    }
                    //actualizar pendientes
                    for (Incidente incidente : incidentes) {
                        Date fechaIncidente = Utileria.SDF.parse(incidente.getFecha());
                        if (incidente.getResponsable().equals(empleado) && !fechaIncidente.before(lunesAnterior) && !fechaIncidente.after(domingoPosterior)) {
                            incidente.setPagado(true);
                        }
                    }
                }

                Encargado.actualizarBD(encargados); //actuaizar el pendiente del encargado                                
                ConsultaFormato.actualizarFormatos(formatos); //actualizar los pagado
                Prestamo.actualizarDB(prestamos); //actualizar los pagado
                Incidente.actualizarDB(incidentes); //actualizar los pagado

                //actualizar la pantalla de empleado
                EmployeeThread employeeThread = new EmployeeThread();
                employeeThread.start();
                //actualizar la pantalla deudores
                DebtThread debtThread = new DebtThread();
                debtThread.start();

            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Debe de existir un empleado en lista", "Atención", JOptionPane.WARNING_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Se ingresó un valor no permitido, intente de nuevo", "Atención", JOptionPane.WARNING_MESSAGE);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error de parseo, revisar con proveedor", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnPagarActionPerformed

    private void chkPagarConComisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPagarConComisionActionPerformed
        EmployeeThread employeeThread = new EmployeeThread();
        employeeThread.start();
    }//GEN-LAST:event_chkPagarConComisionActionPerformed

    private void btnGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarActionPerformed
        this.initData();
    }//GEN-LAST:event_btnGenerarActionPerformed

    private void btnAbonarInactivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarInactivoActionPerformed
        int index = tablaDeudoresInactivos.getSelectedRow();
        if (index != -1) {
            Encargado seleccionado = inactivos.get(index);
            Object res = JOptionPane.showInputDialog(null, "¿Cuánto desea abonar?", "0");
            int pago = 0;
            if (res != null) {
                pago = Integer.valueOf(res.toString());
            } else {
                return;
            }

            for (Encargado encargado : encargados) {
                if (encargado.getNombre().equals(seleccionado.getNombre())) {
                    encargado.setPendiente(encargado.getPendiente() + pago);
                    encargado.setFechaPendiente(new Date());
                    break;
                }
            }
            Encargado.actualizarBD(encargados);

            DebtThread debtThread = new DebtThread();
            debtThread.start();

        } else {
            JOptionPane.showMessageDialog(null, "Debe de seleccionar una fila", "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAbonarInactivoActionPerformed

    private void btnAbonarActivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbonarActivoActionPerformed
        int index = tablaDeudoresActivos.getSelectedRow();
        if (index != -1) {
            Encargado seleccionado = activos.get(index);
            Object res = JOptionPane.showInputDialog(null, "¿Cuánto desea abonar?", "0");
            int pago = 0;
            if (res != null) {
                pago = Integer.valueOf(res.toString());
            } else {
                return;
            }

            for (Encargado encargado : encargados) {
                if (encargado.getNombre().equals(seleccionado.getNombre())) {
                    encargado.setPendiente(encargado.getPendiente() + pago);
                    encargado.setFechaPendiente(new Date());
                    break;
                }
            }
            Encargado.actualizarBD(encargados);

            DebtThread debtThread = new DebtThread();
            debtThread.start();

        } else {
            JOptionPane.showMessageDialog(null, "Debe de seleccionar una fila", "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAbonarActivoActionPerformed

    private void chkPagarConPendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkPagarConPendienteActionPerformed
        EmployeeThread employeeThread = new EmployeeThread();
        employeeThread.start();
    }//GEN-LAST:event_chkPagarConPendienteActionPerformed

    private void chkNoPagadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNoPagadosActionPerformed
        if (chkNoPagados.isSelected()) {
           datechooserGeneral.setEnabled(false);
        }else{
           datechooserGeneral.setEnabled(true); 
        }        
        initData();        
    }//GEN-LAST:event_chkNoPagadosActionPerformed

    private class GeneralThread extends Thread {

        private final Vector<Formato> formatosFiltrados;
        private final ArrayList<Incidente> incidentesFiltrados;
        private final ArrayList<Prestamo> prestamosFiltrados;
        private final ArrayList<String> nombreEmpleados;

        public GeneralThread(List<Formato> formatosFiltrados, List<Incidente> incidentesFiltrados, List<Prestamo> prestamosFiltrados, ArrayList<String> nombreEmpleados) {
            this.formatosFiltrados = new Vector<>(formatosFiltrados);
            this.incidentesFiltrados = new ArrayList<>(incidentesFiltrados);
            this.prestamosFiltrados = new ArrayList<>(prestamosFiltrados);
            this.nombreEmpleados = nombreEmpleados;
        }

        @Override
        public void run() {

            //cada elemento de estas lista le corresponde a un nombre de trabajador
            cantTurnos = new ArrayList<>();
            cantFormatos = new ArrayList<>();
            cantComisiones = new ArrayList<>();
            cantPrestamos = new ArrayList<>();
            cantIncidentes = new ArrayList<>();

            //para cada uno de los trabajadores obtenidos llenar su correspondiente indice con los valores
            ArrayList<Formato> formatosDeEngargado;
            ArrayList<Prestamo> prestamosDeEngargado;
            ArrayList<Incidente> incidentesDeEngargado;

            float totalFormatosDeTrabajador;
            int totalIncidenteDeTrabajador;
            int totalPrestamosDeTrabajador;
            float totalComisionesDeTrabajaor;
            int turnosTrabajadosDeTrabajador;
            float pendiente;

            for (String nombreTrabajore : nombreEmpleados) {
                //obtener los formatos de el, los prestamos y los incidentes
                formatosDeEngargado = new ArrayList<>();
                prestamosDeEngargado = new ArrayList<>();
                incidentesDeEngargado = new ArrayList<>();

                String trabajador = quitaGuion(nombreTrabajore);

                for (Formato formatosFiltrado : formatosFiltrados) {
                    if (quitaGuion(formatosFiltrado.getEncargado()).equals(trabajador)) {
                        formatosDeEngargado.add(formatosFiltrado);
                    }
                }
                for (Incidente incidentesFiltrado : incidentesFiltrados) {
                    if (trabajador.equals(incidentesFiltrado.getResponsable())) {
                        incidentesDeEngargado.add(incidentesFiltrado);
                    }
                }
                for (Prestamo prestamosFiltrado : prestamosFiltrados) {
                    if (trabajador.equals(prestamosFiltrado.getEncargado())) {
                        prestamosDeEngargado.add(prestamosFiltrado);
                    }
                }

                totalFormatosDeTrabajador = 0f;
                totalIncidenteDeTrabajador = 0;
                totalPrestamosDeTrabajador = 0;
                totalComisionesDeTrabajaor = 0f;
                turnosTrabajadosDeTrabajador = formatosDeEngargado.size();
                pendiente = 0;

                for (Formato formato : formatosDeEngargado) {
                    totalFormatosDeTrabajador += formato.obtenerTotalFinal();
                    totalComisionesDeTrabajaor += formato.getComision();
                }
                for (Incidente incidente : incidentesDeEngargado) {
                    totalIncidenteDeTrabajador += incidente.getCantidad();
                }
                for (Prestamo prestamo : prestamosDeEngargado) {
                    totalPrestamosDeTrabajador += prestamo.getCantidad();
                }

                //buscar le pendiente del empleado y asiganarlo a la variable
                for (Encargado encargado : encargados) {                    
                    if (nombreTrabajore.equals(encargado.getNombre())) {
                        pendiente = encargado.getPendiente();
                    }
                }
                
                //llenar su posicion en la lista
                cantFormatos.add(totalFormatosDeTrabajador);
                cantIncidentes.add(totalIncidenteDeTrabajador);
                cantPrestamos.add(totalPrestamosDeTrabajador);
                cantComisiones.add(totalComisionesDeTrabajaor);
                cantTurnos.add(turnosTrabajadosDeTrabajador);

            }
            //llenar la tabla con los datos
            this.llenarTablaGeneral();
        }

        private void llenarTablaGeneral() {
            DefaultTableModel dtm = (DefaultTableModel) tablaGeneral.getModel();
            dtm.setRowCount(0);
            for (int i = 0; i < nombreEmpleados.size(); i++) {
                Vector fila = new Vector();
                fila.add(nombreEmpleados.get(i));
                fila.add(cantTurnos.get(i));
                fila.add(cantFormatos.get(i));
                fila.add(cantComisiones.get(i));
                fila.add(cantPrestamos.get(i));
                fila.add(cantIncidentes.get(i));
                //columna de totales
                fila.add((cantFormatos.get(i) * -1) + cantComisiones.get(i) - cantPrestamos.get(i) - cantIncidentes.get(i));

                dtm.addRow(fila);
            }

            //generar fila de totoles
            Vector fila = new Vector();
            fila.add("TOTALES:"); //columna del nombre
            for (int i = 1; i < 7; i++) {
                if (i == 2 || i == 3 || i == 6) {
                    fila.add(sumaColumna(i, tablaGeneral));
                } else {
                    fila.add((int) sumaColumna(i, tablaGeneral));
                }

            }
            dtm.addRow(fila);

        }
    }

    private class EmployeeThread extends Thread {

        private Vector<Formato> formatosEmpleado;
        private ArrayList<Incidente> incidentesEmpleado;
        private ArrayList<Prestamo> prestamosEmpleado;

        @Override
        public void run() {
            String empleado = "";
            try {
                empleado = cmbEmpleado.getSelectedItem().toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            formatosEmpleado = filtrarFormatosEmpleado(empleado);
            incidentesEmpleado = filtrarIncidentesEmpleado(empleado);
            prestamosEmpleado = filtrarPrestamosEmpleado(empleado);

            if (chkNoPagados.isSelected()) {
                try {
                    formatosEmpleado = filtrarFormatosFecha(formatosEmpleado, lunesAnterior, domingoPosterior);
                    incidentesEmpleado = filtrarIncidentesFecha(incidentesEmpleado, lunesAnterior, domingoPosterior);
                    prestamosEmpleado = filtrarPrestamosFecha(prestamosEmpleado, lunesAnterior, domingoPosterior);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            //obtener totales de formatos, incidentes y prestamos
            float totalFormatos = 0f;
            float totalComisiones = 0f;
            float totalIncidentes = 0f;
            float totalPrestamos = 0f;
            float totalGeneral;
            float pendiente = 0;

            for (Encargado encargado : encargados) {
                if (encargado.getNombre().equals(empleado)) {
                    pendiente = encargado.getPendiente();
                    break;
                }
            }

            for (Formato formato : formatosEmpleado) {
                totalFormatos += formato.obtenerTotalFinal();
                totalComisiones += formato.getComision();
            }

            for (Incidente incidente : incidentesEmpleado) {
                totalIncidentes += incidente.getCantidad();
            }

            for (Prestamo prestamo : prestamosEmpleado) {
                totalPrestamos += prestamo.getCantidad();
            }
            totalFormatos = totalFormatos * -1;

            if (!chkPagarConComision.isSelected()) {
                totalComisiones = 0; //mandamos la comision a 0 para no afectar las cuentas
            }
            if (!chkPagarConPendiente.isSelected()) {
                pendiente = 0;
            }

            totalGeneral = totalFormatos + totalComisiones + pendiente - totalIncidentes - totalPrestamos;
            pagarAEmpleado = totalGeneral;

            llenarTablaEmpleados();
            llenarTablaIncidentes();
            llenarTablaPrestamos();
            llenarTablaTotales(totalFormatos, totalComisiones, totalIncidentes, totalPrestamos, pendiente, totalGeneral);

        }

        private void llenarTablaTotales(float totalFormatos, float totalComisiones, float totalIncidentes, float totalPrestamos, float pendiente, float totalGeneral) {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    DefaultTableModel dtm = (DefaultTableModel) tablaTotales.getModel();
                    dtm.setRowCount(0);
                    Vector fila = new Vector();
                    //formatos
                    fila.add("Formatos");
                    fila.add(totalFormatos);
                    dtm.addRow(fila);

                    fila = new Vector();
                    //formatos
                    fila.add("Comisiones");
                    fila.add(totalComisiones);
                    dtm.addRow(fila);

                    fila = new Vector();
                    //formatos
                    fila.add("Incidentes");
                    fila.add(totalIncidentes);
                    dtm.addRow(fila);

                    fila = new Vector();
                    //formatos
                    fila.add("Prestamos");
                    fila.add(totalPrestamos);
                    dtm.addRow(fila);

                    fila = new Vector();
                    //formatos
                    fila.add("Pendiente");
                    fila.add(pendiente);
                    dtm.addRow(fila);

                    fila = new Vector();
                    //formatos
                    fila.add("Total");
                    fila.add(totalGeneral);
                    dtm.addRow(fila);
                }
            });
        }

        private void llenarTablaEmpleados() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    DefaultTableModel dtm = (DefaultTableModel) tablaFormatos.getModel();
                    dtm.setRowCount(0);
                    formatosEmpleado.stream().map((formatosFiltrado) -> {
                        Vector fila = new Vector();
                        fila.add(formatosFiltrado.getFecha() + " - " + formatosFiltrado.getTurno());
                        fila.add(quitaGuion(formatosFiltrado.getSucursal()));
                        fila.add(formatosFiltrado.getComision());
                        fila.add(formatosFiltrado.obtenerTotalFinal());
                        return fila;
                    }).forEach((fila) -> {
                        dtm.addRow(fila);
                    });
                }
            });

        }

        private void llenarTablaIncidentes() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    DefaultTableModel dtm = (DefaultTableModel) tablaIncidentes.getModel();
                    dtm.setRowCount(0);
                    incidentesEmpleado.stream().map((incidentesFiltrado) -> {
                        Vector fila = new Vector();
                        fila.add(incidentesFiltrado.getFecha());
                        fila.add(incidentesFiltrado.getNota());
                        fila.add(incidentesFiltrado.getCantidad());
                        return fila;
                    }).forEach((fila) -> {
                        dtm.addRow(fila);
                    });
                }
            });

        }

        private void llenarTablaPrestamos() {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    DefaultTableModel dtm = (DefaultTableModel) tablaPrestamos.getModel();
                    dtm.setRowCount(0);
                    prestamosEmpleado.stream().map((prestamoFiltrado) -> {
                        Vector fila = new Vector();
                        fila.add(prestamoFiltrado.getFecha());
                        fila.add(prestamoFiltrado.getAutorizador());
                        fila.add(prestamoFiltrado.getNota());
                        fila.add(prestamoFiltrado.getCantidad());
                        return fila;
                    }).forEach((fila) -> {
                        dtm.addRow(fila);
                    });
                }
            });

        }

        private Vector<Formato> filtrarFormatosEmpleado(String nombreEmpleado) {
            Vector<Formato> misFormatosFiltrados = new Vector<>(formatos);
            String comp = quitaEspacios(nombreEmpleado);
            for (int i = 0; i < misFormatosFiltrados.size(); i++) {
                if (!misFormatosFiltrados.get(i).getEncargado().equals(comp) || misFormatosFiltrados.get(i).isPagado()) {
                    misFormatosFiltrados.remove(i);
                    i--;
                }
            }
            return misFormatosFiltrados;
        }

        private ArrayList<Incidente> filtrarIncidentesEmpleado(String nombreEmpleado) {
            ArrayList<Incidente> misIncidentesFiltrados = new ArrayList<>(incidentes);
            for (int i = 0; i < misIncidentesFiltrados.size(); i++) {
                if (!misIncidentesFiltrados.get(i).getResponsable().equals(nombreEmpleado) || misIncidentesFiltrados.get(i).isPagado()) {
                    misIncidentesFiltrados.remove(i);
                    i--;
                }
            }
            return misIncidentesFiltrados;
        }

        private ArrayList<Prestamo> filtrarPrestamosEmpleado(String nombreEmpleado) {
            ArrayList<Prestamo> misPrestamosFiltrados = new ArrayList<>(prestamos);
            for (int i = 0; i < misPrestamosFiltrados.size(); i++) {
                if (!misPrestamosFiltrados.get(i).getEncargado().equals(nombreEmpleado) || misPrestamosFiltrados.get(i).isPagado()) {
                    misPrestamosFiltrados.remove(i);
                    i--;
                }
            }
            return misPrestamosFiltrados;
        }

        private Vector<Formato> filtrarFormatosFecha(Vector<Formato> formatos, Date fechaInit, Date fechaFin) throws ParseException {
            Vector<Formato> misFormatosFiltrados = new Vector<>(formatos);
            Date fechaFormato;
            for (int i = 0; i < misFormatosFiltrados.size(); i++) {
                fechaFormato = Utileria.SDF.parse(misFormatosFiltrados.get(i).getFecha());
                if (fechaFormato.before(fechaInit) || fechaFormato.after(fechaFin)) {
                    misFormatosFiltrados.remove(i);
                    i--;
                }
            }
            return misFormatosFiltrados;
        }

        private ArrayList<Incidente> filtrarIncidentesFecha(ArrayList<Incidente> incidentes, Date fechaInit, Date fechaFin) throws ParseException {
            ArrayList<Incidente> misIncidentesFiltrados = new ArrayList<>(incidentes);
            Date fechaIncidente;
            for (int i = 0; i < misIncidentesFiltrados.size(); i++) {
                fechaIncidente = Utileria.SDF.parse(misIncidentesFiltrados.get(i).getFecha());
                if (fechaIncidente.before(fechaInit) || fechaIncidente.after(fechaFin)) {
                    misIncidentesFiltrados.remove(i);
                    i--;
                }
            }
            return misIncidentesFiltrados;
        }

        private ArrayList<Prestamo> filtrarPrestamosFecha(ArrayList<Prestamo> prestamos, Date fechaInit, Date fechaFin) throws ParseException {
            ArrayList<Prestamo> misPrestamosFiltrados = new ArrayList<>(prestamos);
            Date fechaPrestamo;
            for (int i = 0; i < misPrestamosFiltrados.size(); i++) {
                fechaPrestamo = Utileria.SDF.parse(misPrestamosFiltrados.get(i).getFecha());
                if (fechaPrestamo.before(fechaInit) || fechaPrestamo.after(fechaFin)) {
                    misPrestamosFiltrados.remove(i);
                    i--;
                }
            }
            return misPrestamosFiltrados;
        }

    }

    private class DebtThread extends Thread {

        @Override
        public void run() {
            //PARA TODOS LOS ENCARGADOS OBTENER SU PENDIENTE
            //SI TIENEN PENDIENTE NEGATIVO ES UN DEUDOR
            //LOS DEUDORES CON FORMATOS EXISTENTES DE 2 SEMANAS A LA FECHA SON ACTIVOS
            //LOS DEUDORES SIN FORMATOS EXISTENTES DE 2 SEMANAS A LA FECHA SON INACTIVOS

            activos = new ArrayList<>();
            inactivos = new ArrayList<>();

            for (Encargado encargado : encargados) {
                if (encargado.getPendiente() < 0) {
                    //es deudor
                    // buscar si tiene formatos activos
                    GregorianCalendar cal = new GregorianCalendar();
                    cal.add(Calendar.WEEK_OF_YEAR, -2); //fecha de hace 2 semanas

                    boolean activo = false;
                    for (Formato formato : formatos) {
                        if (formato.getEncargado().equals(quitaEspacios(encargado.getNombre()))) {//es formato del encargado
                            try {
                                Date fechaFormato = Utileria.SDF.parse(formato.getFecha());
                                if (!fechaFormato.before(cal.getTime())) { //es formato dentro de las 2 semanas
                                    activo = true;
                                    break;
                                }
                            } catch (ParseException ex) {
                                Logger.getLogger(ConsultaNomina.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                    if (activo) {
                        activos.add(encargado);
                    } else {
                        inactivos.add(encargado);
                    }
                }
            }
//            System.out.println("inactivos:");
//            inactivos.stream().forEach(a -> System.out.println(a.getNombre()));
//            System.out.println("");
//            System.out.println("activos:");
//            activos.stream().forEach(a -> System.out.println(a.getNombre())); //con las listas de encargados activos e inactivos
            //llenar las tablas
            this.llenarTabalDeudoresInactivos();
            this.llenarTablaDeudoresActivos();

        }

        private void llenarTablaDeudoresActivos() {
            DefaultTableModel dtm = (DefaultTableModel) tablaDeudoresActivos.getModel();
            dtm.setRowCount(0);
            activos.stream().map((activo) -> {
                Vector fila = new Vector();
                fila.add(activo.getNombre());
                fila.add(activo.getNombreCompleto());
                if (activo.getFechaPendiente() != null) {
                    fila.add(Utileria.SDF.format(activo.getFechaPendiente()));
                } else {
                    fila.add("");
                }
                fila.add(activo.getPendiente());
                return fila;
            }).forEach((fila) -> {
                dtm.addRow(fila);
            });
        }

        private void llenarTabalDeudoresInactivos() {
            DefaultTableModel dtm = (DefaultTableModel) tablaDeudoresInactivos.getModel();
            dtm.setRowCount(0);
            inactivos.stream().map((activo) -> {
                Vector fila = new Vector();
                fila.add(activo.getNombre());
                fila.add(activo.getNombreCompleto());
                if (activo.getFechaPendiente() != null) {
                    fila.add(Utileria.SDF.format(activo.getFechaPendiente()));
                } else {
                    fila.add("");
                }
                fila.add(activo.getPendiente());
                return fila;
            }).forEach((fila) -> {
                dtm.addRow(fila);
            });
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultaNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaNomina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaNomina().setVisible(true);
            }
        });
    }

    public float sumaColumna(int columna, JTable tabla) {
        float suma = 0;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            suma += Float.valueOf(tabla.getValueAt(i, columna).toString());
        }
        return suma;
    }

    private float sumafila(int fila, ArrayList<Integer> columnas, JTable tabla) {
        float suma = 0;
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            if (columnas.contains(i)) {
                suma += Float.valueOf(tabla.getValueAt(fila, i).toString());
            }
        }
        return suma;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbonarActivo;
    private javax.swing.JButton btnAbonarInactivo;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnPagar;
    private javax.swing.JCheckBox chkNoPagados;
    private javax.swing.JCheckBox chkPagarConComision;
    private javax.swing.JCheckBox chkPagarConPendiente;
    private javax.swing.JComboBox<String> cmbEmpleado;
    private com.toedter.calendar.JDateChooser datechooserGeneral;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lbRangofechas;
    private javax.swing.JPanel panelDeudores;
    private javax.swing.JPanel panelEmpleado;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JTabbedPane panelPestaña;
    private javax.swing.JTable tablaDeudoresActivos;
    private javax.swing.JTable tablaDeudoresInactivos;
    private javax.swing.JTable tablaFormatos;
    private javax.swing.JTable tablaGeneral;
    private javax.swing.JTable tablaIncidentes;
    private javax.swing.JTable tablaPrestamos;
    private javax.swing.JTable tablaTotales;
    // End of variables declaration//GEN-END:variables
}
