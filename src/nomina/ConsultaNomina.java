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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
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

    private SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");

    //cada elemento de estas lista le corresponde a un nombre de trabajador
    private ArrayList<Integer> cantTurnos;
    private ArrayList<Float> cantFormatos;
    private ArrayList<Float> cantComisiones;
    private ArrayList<Integer> cantPrestamos;
    private ArrayList<Integer> cantIncidentes;
    private ArrayList<Float> cantPendientes;

    private Vector<Formato> formatosFiltrados;
    private ArrayList<Incidente> incidentesFiltrados;
    private ArrayList<Prestamo> prestamosFiltrados;

    private ArrayList<Float> totalAPagarPorEmpleado;

    Date lunesAnterior;
    Date domingoPosterior;

    public ConsultaNomina() {
        initComponents();
        this.myInitComponents();
        this.setLocationRelativeTo(null);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.chkPagarConComision.setSelected(true);
        this.tablaTotales.setDefaultRenderer(Object.class, new TablaTotalesRenderer());
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

    private void myInitComponents() {
        tablaGeneral.setAutoCreateRowSorter(true);
        tablaFormatos.setAutoCreateRowSorter(true);
        tablaIncidentes.setAutoCreateRowSorter(true);
        tablaPrestamos.setAutoCreateRowSorter(true);

        datechooserGeneral.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    lbRangofechas.setText("Del: " + sdf.format(Utileria.lunesAnterior((Date) evt.getNewValue())) + " al: " + sdf.format(Utileria.domingoPosterior((Date) evt.getNewValue())));
                }
            }
        });
    }

    private Vector<Formato> filtrarFormatos(Date inicio, Date fin) {
        Vector<Formato> formatosFiltrados = new Vector(formatos);
        int i = 0;
        while (i < formatosFiltrados.size()) {
            try {
                Date fechaFormato = sdf.parse(formatosFiltrados.elementAt(i).getFecha());

                if (fechaFormato.before(inicio) || fechaFormato.after(fin)) {
                    formatosFiltrados.remove(i);
                } else {
                    i++;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaNomina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return formatosFiltrados;
    }

    private ArrayList<Incidente> filtrarIncidentes(Date inicio, Date fin) {
        ArrayList<Incidente> incidentesFiltrados = new ArrayList<>(incidentes);
        int i = 0;
        while (i < incidentesFiltrados.size()) {
            try {
                Date fechaIncidente = sdf.parse(incidentesFiltrados.get(i).getFecha());
                if (fechaIncidente.before(inicio) || fechaIncidente.after(fin)) {
                    incidentesFiltrados.remove(i);
                } else {
                    i++;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaNomina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return incidentesFiltrados;
    }

    private ArrayList<Prestamo> filtrarPrestamos(Date inicio, Date fin) {
        ArrayList<Prestamo> prestamosFiltrados = new ArrayList<>(prestamos);
        int i = 0;
        while (i < prestamosFiltrados.size()) {
            try {
                Date fechaPrestamo = sdf.parse(prestamosFiltrados.get(i).getFecha());
                if (fechaPrestamo.before(inicio) || fechaPrestamo.after(fin)) {
                    prestamosFiltrados.remove(i);
                } else {
                    i++;
                }
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaNomina.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return prestamosFiltrados;
    }

    private Vector<Formato> filtrarFormatosEmpleado(String nombreEmpleado) {
        Vector<Formato> misFormatosFiltrados = new Vector<>(formatosFiltrados);
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
        ArrayList<Incidente> misIncidentesFiltrados = new ArrayList<>(incidentesFiltrados);
        for (int i = 0; i < misIncidentesFiltrados.size(); i++) {
            if (!misIncidentesFiltrados.get(i).getResponsable().equals(nombreEmpleado) || misIncidentesFiltrados.get(i).isPagado()) {
                misIncidentesFiltrados.remove(i);
                i--;
            }
        }
        return misIncidentesFiltrados;
    }

    private ArrayList<Prestamo> filtrarPrestamosEmpleado(String nombreEmpleado) {
        ArrayList<Prestamo> misPrestamosFiltrados = new ArrayList<>(prestamosFiltrados);
        for (int i = 0; i < misPrestamosFiltrados.size(); i++) {
            if (!misPrestamosFiltrados.get(i).getEncargado().equals(nombreEmpleado) || misPrestamosFiltrados.get(i).isPagado()) {
                misPrestamosFiltrados.remove(i);
                i--;
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
                            c.setTime(sdf.parse(formatos.elementAt(k).getFecha()));
                            c.add(Calendar.DATE, 1); //sumarle un dia
                            if (formatos.elementAt(k + 1).getFecha().equals(sdf.format(c.getTime())) && formatos.elementAt(k + 1).getTurno() == 'D') {
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
                        c.setTime(sdf.parse(formatos.elementAt(k).getFecha()));
                        c.add(Calendar.DATE, 1); //sumarle un dia a la fecha
                        if (formatos.elementAt(k + 1).getFecha().equals(sdf.format(c.getTime())) && formatos.elementAt(k + 1).getTurno() == 'D') {
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
        panelDeudores = new javax.swing.JPanel();
        panelFiltro = new javax.swing.JPanel();
        btnGenerarGeneral = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        datechooserGeneral = new com.toedter.calendar.JDateChooser();
        lbRangofechas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nómina");

        tablaGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Empleado", "Turnos", "Formatos", "Comisión", "Prestamos", "Incidentes", "Pendiente"
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 996, Short.MAX_VALUE)
        );
        panelGeneralLayout.setVerticalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
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

        javax.swing.GroupLayout panelEmpleadoLayout = new javax.swing.GroupLayout(panelEmpleado);
        panelEmpleado.setLayout(panelEmpleadoLayout);
        panelEmpleadoLayout.setHorizontalGroup(
            panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelEmpleadoLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4)
                            .addComponent(jScrollPane3)
                            .addComponent(jScrollPane5)
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(panelEmpleadoLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
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
                        .addComponent(chkPagarConComision)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelPestaña.addTab("Por Empleado", panelEmpleado);

        javax.swing.GroupLayout panelDeudoresLayout = new javax.swing.GroupLayout(panelDeudores);
        panelDeudores.setLayout(panelDeudoresLayout);
        panelDeudoresLayout.setHorizontalGroup(
            panelDeudoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );
        panelDeudoresLayout.setVerticalGroup(
            panelDeudoresLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 522, Short.MAX_VALUE)
        );

        panelPestaña.addTab("Deudores", panelDeudores);

        panelFiltro.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnGenerarGeneral.setText("Generar");
        btnGenerarGeneral.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarGeneralActionPerformed(evt);
            }
        });

        jLabel1.setText("Semana:");

        lbRangofechas.setText("del: al:");

        javax.swing.GroupLayout panelFiltroLayout = new javax.swing.GroupLayout(panelFiltro);
        panelFiltro.setLayout(panelFiltroLayout);
        panelFiltroLayout.setHorizontalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(datechooserGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGenerarGeneral)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbRangofechas)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelFiltroLayout.setVerticalGroup(
            panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelFiltroLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datechooserGeneral, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnGenerarGeneral)
                        .addComponent(lbRangofechas))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void btnGenerarGeneralActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarGeneralActionPerformed

        Date d = datechooserGeneral.getDate();
        if (d != null) {

            lunesAnterior = Utileria.lunesAnterior(d);
            domingoPosterior = Utileria.domingoPosterior(d);

            formatosFiltrados = this.filtrarFormatos(lunesAnterior, domingoPosterior);
            incidentesFiltrados = this.filtrarIncidentes(lunesAnterior, domingoPosterior);
            prestamosFiltrados = this.filtrarPrestamos(lunesAnterior, domingoPosterior);

            //llenar el combobox de empleados
            ArrayList<String> nombreEmpleados = new ArrayList<>();
            for (Formato formatosFiltrado : formatosFiltrados) {
                if (!nombreEmpleados.contains(quitaGuion(formatosFiltrado.getEncargado()))) {
                    nombreEmpleados.add(quitaGuion(formatosFiltrado.getEncargado()));
                }
            }

            Collections.sort(nombreEmpleados);

            for (String nombreEmpleado : nombreEmpleados) {

                cmbEmpleado.addItem(nombreEmpleado);
            }

            //generar la tabla general
            GeneralThread generalThread = new GeneralThread(formatosFiltrados, incidentesFiltrados, prestamosFiltrados, nombreEmpleados);
            generalThread.run();

            //generar tabla de nomina por empleado
            EmployeeThread employeeThread = new EmployeeThread(formatosFiltrados, incidentesFiltrados, prestamosFiltrados);
            employeeThread.run();

            //generar la lista de deudores
        } else {
            JOptionPane.showMessageDialog(null, "Necesita asignar una fecha", "Atención", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnGenerarGeneralActionPerformed

    private void cmbEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbEmpleadoActionPerformed
        EmployeeThread employeeThread = new EmployeeThread(formatosFiltrados, incidentesFiltrados, prestamosFiltrados);
        employeeThread.run();
    }//GEN-LAST:event_cmbEmpleadoActionPerformed

    private void btnPagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarActionPerformed
        String empleado = "";

        try {
            empleado = cmbEmpleado.getSelectedItem().toString();
            Object res = JOptionPane.showInputDialog("Ingrese la cantidad a pagar de " + empleado + ":", "0");
            int pago = 0;
            if ( res != null) {
                pago = Integer.valueOf(res.toString());
            }else{
                return;
            }
            
            
            

            if (JOptionPane.showConfirmDialog(null, "¿Seguro que desea pagar a " + empleado + " la cantidad de $" + pago, "Confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {

                float nuevoPendiente = pagarAEmpleado - pago;
//            System.out.println("nuevo pendiente: " + nuevoPendiente);
                for (Encargado encargado : encargados) {
                    if (encargado.getNombre().equals(empleado)) {
                        encargado.setPendiente(nuevoPendiente);
//                    System.out.println("nuevo asignado");
                        break;
                    }
                }

                //marcar formatos como pagados, marcar prestamos como pagados, marcar incidentes como pagados            
                //actualizar formatos 
                for (Formato formato : formatos) {
                    Date fechaFormato = sdf.parse(formato.getFecha());
                    if (quitaGuion(formato.getEncargado()).equals(empleado) && !fechaFormato.after(domingoPosterior) && !fechaFormato.before(lunesAnterior)) {
                        //este formato corresponde a la semana de pago
                        formato.setPagado(true);
                    }
                }
                //actualizar prestamos
                for (Prestamo prestamo : prestamos) {
                    Date fechaPrestamo = sdf.parse(prestamo.getFecha());
                    if (prestamo.getEncargado().equals(empleado) && !fechaPrestamo.before(lunesAnterior) && !fechaPrestamo.after(domingoPosterior)) {
                        prestamo.setPagado(true);
                    }
                }
                //actualizar pendientes
                for (Incidente incidente : incidentes) {
                    Date fechaIncidente = sdf.parse(incidente.getFecha());
                    if (incidente.getResponsable().equals(empleado) && !fechaIncidente.before(lunesAnterior) && !fechaIncidente.after(domingoPosterior)) {
                        incidente.setPagado(true);
                    }
                }

                Encargado.actualizarBD(encargados); //actuaizar el pendiente del encargado                                
                ConsultaFormato.actualizarFormatos(formatos); //actualizar los pagado
                Prestamo.actualizarDB(prestamos); //actualizar los pagado
                Incidente.actualizarDB(incidentes); //actualizar los pagado
                
                //actualizar la pantalla de empleado
                
                EmployeeThread employeeThread = new EmployeeThread(formatosFiltrados, incidentesFiltrados, prestamosFiltrados);
                employeeThread.run();
                
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
        EmployeeThread employeeThread = new EmployeeThread(formatosFiltrados, incidentesFiltrados, prestamosFiltrados);
        employeeThread.run();
    }//GEN-LAST:event_chkPagarConComisionActionPerformed

    private class GeneralThread implements Runnable {

        private Vector<Formato> formatosFiltrados;
        private ArrayList<Incidente> incidentesFiltrados;
        private ArrayList<Prestamo> prestamosFiltrados;
        private ArrayList<String> nombreEmpleados;

        public GeneralThread(Vector<Formato> formatosFiltrados, ArrayList<Incidente> incidentesFiltrados, ArrayList<Prestamo> prestamosFiltrados, ArrayList<String> nombreEmpleados) {
            this.formatosFiltrados = formatosFiltrados;
            this.incidentesFiltrados = incidentesFiltrados;
            this.prestamosFiltrados = prestamosFiltrados;
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
            cantPendientes = new ArrayList<>();

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
                cantPendientes.add(pendiente);
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
                fila.add(cantPendientes.get(i));
                dtm.addRow(fila);
            }
        }
    }

    private class EmployeeThread implements Runnable {

        private Vector<Formato> formatosFiltrados;
        private ArrayList<Incidente> incidentesFiltrados;
        private ArrayList<Prestamo> prestamosFiltrados;

        public EmployeeThread(Vector<Formato> formatosFiltrados, ArrayList<Incidente> incidentesFiltrados, ArrayList<Prestamo> prestamosFiltrados) {
            this.formatosFiltrados = new Vector<>(formatosFiltrados);
            this.incidentesFiltrados = new ArrayList<>(incidentesFiltrados);
            this.prestamosFiltrados = new ArrayList<>(prestamosFiltrados);
        }

        @Override
        public void run() {

            String empleado = cmbEmpleado.getSelectedItem().toString();

            formatosFiltrados = filtrarFormatosEmpleado(empleado);
            incidentesFiltrados = filtrarIncidentesEmpleado(empleado);
            prestamosFiltrados = filtrarPrestamosEmpleado(empleado);

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

            for (Formato formato : formatosFiltrados) {
                totalFormatos += formato.obtenerTotalFinal();
                totalComisiones += formato.getComision();
            }

            for (Incidente incidente : incidentesFiltrados) {
                totalIncidentes += incidente.getCantidad();
            }

            for (Prestamo prestamo : prestamosFiltrados) {
                totalPrestamos += prestamo.getCantidad();
            }
            totalFormatos = totalFormatos * -1;

            if (!chkPagarConComision.isSelected()) {
                totalComisiones = 0; //mandamos la comision a 0 para no afectar las cuentas
            }
            totalGeneral = totalFormatos + totalComisiones + pendiente - totalIncidentes - totalPrestamos;
            pagarAEmpleado = totalGeneral;

            llenarTablaEmpleados();
            llenarTablaIncidentes();
            llenarTablaPrestamos();
            llenarTablaTotales(totalFormatos, totalComisiones, totalIncidentes, totalPrestamos, pendiente, totalGeneral);

        }

        private void llenarTablaTotales(float totalFormatos, float totalComisiones, float totalIncidentes, float totalPrestamos, float pendiente, float totalGeneral) {
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

        private void llenarTablaEmpleados() {
            DefaultTableModel dtm = (DefaultTableModel) tablaFormatos.getModel();
            dtm.setRowCount(0);
            for (Formato formatosFiltrado : this.formatosFiltrados) {
                Vector fila = new Vector();
                fila.add(formatosFiltrado.getFecha() + " - " + formatosFiltrado.getTurno());
                fila.add(quitaGuion(formatosFiltrado.getSucursal()));
                fila.add(formatosFiltrado.getComision());
                fila.add(formatosFiltrado.obtenerTotalFinal());
                dtm.addRow(fila);
            }
        }

        private void llenarTablaIncidentes() {
            DefaultTableModel dtm = (DefaultTableModel) tablaIncidentes.getModel();
            dtm.setRowCount(0);
            for (Incidente incidentesFiltrado : this.incidentesFiltrados) {
                Vector fila = new Vector();
                fila.add(incidentesFiltrado.getFecha());
                fila.add(incidentesFiltrado.getNota());
                fila.add(incidentesFiltrado.getCantidad());
                dtm.addRow(fila);
            }
        }

        private void llenarTablaPrestamos() {
            DefaultTableModel dtm = (DefaultTableModel) tablaPrestamos.getModel();
            dtm.setRowCount(0);
            for (Prestamo prestamoFiltrado : this.prestamosFiltrados) {
                Vector fila = new Vector();
                fila.add(prestamoFiltrado.getFecha());
                fila.add(prestamoFiltrado.getAutorizador());
                fila.add(prestamoFiltrado.getNota());
                fila.add(prestamoFiltrado.getCantidad());
                dtm.addRow(fila);
            }
        }

    }

    private class DebtThread implements Runnable {

        private Vector<Formato> formatosFiltrados;
        private ArrayList<Incidente> incidentesFiltrados;
        private ArrayList<Prestamo> prestamosFiltrados;

        public DebtThread(Vector<Formato> formatosFiltrados, ArrayList<Incidente> incidentesFiltrados, ArrayList<Prestamo> prestamosFiltrados) {
            this.formatosFiltrados = formatosFiltrados;
            this.incidentesFiltrados = incidentesFiltrados;
            this.prestamosFiltrados = prestamosFiltrados;
        }

        @Override
        public void run() {

        }

        private void llenarTablaDeudores() {

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarGeneral;
    private javax.swing.JButton btnPagar;
    private javax.swing.JCheckBox chkPagarConComision;
    private javax.swing.JComboBox<String> cmbEmpleado;
    private com.toedter.calendar.JDateChooser datechooserGeneral;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbRangofechas;
    private javax.swing.JPanel panelDeudores;
    private javax.swing.JPanel panelEmpleado;
    private javax.swing.JPanel panelFiltro;
    private javax.swing.JPanel panelGeneral;
    private javax.swing.JTabbedPane panelPestaña;
    private javax.swing.JTable tablaFormatos;
    private javax.swing.JTable tablaGeneral;
    private javax.swing.JTable tablaIncidentes;
    private javax.swing.JTable tablaPrestamos;
    private javax.swing.JTable tablaTotales;
    // End of variables declaration//GEN-END:variables
}
