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
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import static java.util.stream.Collectors.toList;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import nomina.incidentes.Incidente;
import nomina.prestamos.Prestamo;

/**
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
    private ArrayList<Float> cantPendientes;
    private ArrayList<Float> cantTotales;

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

        try {

            Formato.ordenarFormatosQ(formatos, 0, formatos.size() - 1);
            formatos = Formato.ordenarFormatosSucursal(formatos, sucursales);
            Formato.ordenarFormatosTurno(formatos);

            this.asignarFaltantes();
        } catch (Exception e) {
        }
    }

    private void initData() {
        //lbRangofechas.setText("Del: " + Utileria.SDF.format(Utileria.lunesAnterior((Date) evt.getNewValue())) + " al: " + Utileria.SDF.format(Utileria.domingoPosterior((Date) evt.getNewValue())));
        Date d;
        switch (Utileria.getSelectedButtonText(rGroup)) {
            case "Todos no pagados":
                formatosFiltrados = formatos.stream().filter(f -> f.getPagado() == false).collect(toList());
                incidentesFiltrados = incidentes.stream().filter(i -> i.isPagado() == false).collect(toList());
                prestamosFiltrados = prestamos.stream().filter(p -> p.isPagado() == false).collect(toList());
                break;
            case "Solo no pagados y en fecha":
                formatosFiltrados = formatos.stream().filter(f -> f.getPagado() == false).collect(toList());
                incidentesFiltrados = incidentes.stream().filter(i -> i.isPagado() == false).collect(toList());
                prestamosFiltrados = prestamos.stream().filter(p -> p.isPagado() == false).collect(toList());

                d = datechooserGeneral.getDate();

                if (d != null) {
                    lbRangofechas.setText("Del: " + Utileria.SDF.format(Utileria.lunesAnterior(d)) + " al: " + Utileria.SDF.format(Utileria.domingoPosterior(d)));
                    lunesAnterior = Utileria.lunesAnterior(d);
                    domingoPosterior = Utileria.domingoPosterior(d);

                    formatosFiltrados = filtrarFormatosFecha(formatosFiltrados, lunesAnterior, domingoPosterior);
                    incidentesFiltrados = filtrarIncidentesFecha(incidentesFiltrados, lunesAnterior, domingoPosterior);
                    prestamosFiltrados = filtrarPrestamosFecha(prestamosFiltrados, lunesAnterior, domingoPosterior);

                } else {
                    JOptionPane.showMessageDialog(null, "Necesita asignar una fecha", "Atención", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                break;
            case "Solo semana":
                d = datechooserGeneral.getDate();

                if (d != null) {
                    lbRangofechas.setText("Del: " + Utileria.SDF.format(Utileria.lunesAnterior(d)) + " al: " + Utileria.SDF.format(Utileria.domingoPosterior(d)));
                    lunesAnterior = Utileria.lunesAnterior(d);
                    domingoPosterior = Utileria.domingoPosterior(d);

                    formatosFiltrados = filtrarFormatosFecha(formatos, lunesAnterior, domingoPosterior);
                    incidentesFiltrados = filtrarIncidentesFecha(incidentes, lunesAnterior, domingoPosterior);
                    prestamosFiltrados = filtrarPrestamosFecha(prestamos, lunesAnterior, domingoPosterior);

                } else {
                    JOptionPane.showMessageDialog(null, "Necesita asignar una fecha", "Atención", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Esperaba un texto adecuado de entrada en botones de radio", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        //llenar el combobox de empleados
        ArrayList<String> nombreEmpleados = new ArrayList<>();
        formatosFiltrados.stream().filter(formatosFiltrado -> !nombreEmpleados.contains(quitaGuion(formatosFiltrado.getEncargado())))
                .forEach(formatosFiltrado -> {
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
        EmployeeThread employeeThread = new EmployeeThread(formatosFiltrados, incidentesFiltrados, prestamosFiltrados);
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

        rbtnTodosNoPagados.setSelected(true);
        this.rGroup.add(rbtnTodosNoPagados);
        this.rGroup.add(rbtnNoPagadosSemana);
        this.rGroup.add(rbtnSoloSemana);

        Action doNothing = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        };

        tablaGeneral.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "doNothing");
        tablaGeneral.getActionMap().put("doNothing", doNothing);

//        datechooserGeneral.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
//            @Override
//            public void propertyChange(PropertyChangeEvent evt) {
//                if (evt.getPropertyName().equals("date")) {
//                    initData(evt);
//                }
//            }
//        });
    }

    private List<Formato> filtrarFormatosFecha(List<Formato> formatos, Date inicio, Date fin) {
        return formatos.stream().filter((f) -> {
            try {
                return !Utileria.SDF.parse(f.getFecha()).before(inicio) && !Utileria.SDF.parse(f.getFecha()).after(fin);
            } catch (ParseException ex) {
                Logger.getLogger(ConsultaNomina.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        }).collect(toList());
    }

    private ArrayList<Incidente> filtrarIncidentesFecha(List<Incidente> incidentes, Date inicio, Date fin) {
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

    private ArrayList<Prestamo> filtrarPrestamosFecha(List<Prestamo> prestamos, Date inicio, Date fin) {
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

    private void activarComision() {
        //si es la columna 4
        int columna = tablaGeneral.getSelectedColumn();
        int fila = tablaGeneral.getSelectedRow();

        if (columna == 4) {
            boolean valor = (boolean) tablaGeneral.getValueAt(fila, columna);

            if ((fila + 1) == tablaGeneral.getRowCount()) { //si es la ultima fila                
                if (valor) {
                    //poner a todos en el apagado/encendido y setear las comisiones
                    for (int i = 0; i < tablaGeneral.getRowCount() - 1; i++) {
                        tablaGeneral.setValueAt(valor, i, 4);
                        tablaGeneral.setValueAt(cantComisiones.get(i), i, 3);
                        tablaGeneral.setValueAt(cantTotales.get(i), i, 8);
                    }

                } else {
                    //poner a todos en el apagado/encendido setear todos los valores en 0
                    for (int i = 0; i < tablaGeneral.getRowCount() - 1; i++) {
                        tablaGeneral.setValueAt(valor, i, 4);
                        tablaGeneral.setValueAt(0, i, 3);
                        tablaGeneral.setValueAt(cantTotales.get(i) - cantComisiones.get(i), i, 8);
                    }
                }

            } else if (valor) {
                tablaGeneral.setValueAt(cantComisiones.get(fila), fila, columna - 1);
                tablaGeneral.setValueAt(cantTotales.get(fila), fila, 8);
            } else {
                tablaGeneral.setValueAt(0, fila, columna - 1);
                tablaGeneral.setValueAt(cantTotales.get(fila) - cantComisiones.get(fila), fila, 8);
            }

            tablaGeneral.setValueAt((int) Utileria.sumaColumnaSinUltima(3, tablaGeneral), tablaGeneral.getRowCount() - 1, 3);
            tablaGeneral.setValueAt((int) Utileria.sumaColumnaSinUltima(8, tablaGeneral), tablaGeneral.getRowCount() - 1, 8);

        }
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
            cantPendientes = new ArrayList<>();
            cantTotales = new ArrayList<>();
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
                cantPendientes.add(pendiente);

            }
            //llenar la tabla con los datos
            this.llenarTablaGeneral();
        }

        private void llenarTablaGeneral() {
            DefaultTableModel dtm = (DefaultTableModel) tablaGeneral.getModel();
            dtm.setRowCount(0);

            for (int i = 0; i < nombreEmpleados.size(); i++) {
                float total = (cantFormatos.get(i) * -1) + cantComisiones.get(i) + cantPendientes.get(i) - cantPrestamos.get(i) - cantIncidentes.get(i);
                cantTotales.add(total);

                Vector fila = new Vector();
                fila.add(nombreEmpleados.get(i));
                fila.add(cantTurnos.get(i));
                fila.add(cantFormatos.get(i));
                fila.add(0);                  //comision 0              
                fila.add(false);
                fila.add(cantPendientes.get(i));
                fila.add(cantPrestamos.get(i));
                fila.add(cantIncidentes.get(i));
                fila.add(total - cantComisiones.get(i));

                dtm.addRow(fila);
            }

            //generar fila de totales
            Vector fila = new Vector();
            fila.add("TOTALES:"); //columna del nombre
            for (int i = 1; i < tablaGeneral.getColumnCount(); i++) {
                if (i == 4) {
                    fila.add(false);
                    continue;
                }

                if (i == 2 || i == 3 || i == 7) {
                    fila.add(Utileria.sumaColumna(i, tablaGeneral));
                } else {
                    fila.add((int) Utileria.sumaColumna(i, tablaGeneral));
                }

            }
            dtm.addRow(fila);

        }
    }

    private class EmployeeThread extends Thread {

        private List<Formato> formatosEmpleado;
        private List<Incidente> incidentesEmpleado;
        private List<Prestamo> prestamosEmpleado;

        private final List<Formato> misFormatosFiltrados;
        private final ArrayList<Incidente> misIncidentesFiltrados;
        private final ArrayList<Prestamo> misPrestamosFiltrados;

        public EmployeeThread(List<Formato> formatosFiltrados, List<Incidente> incidentesFiltrados, List<Prestamo> prestamosFiltrados) {
            this.misFormatosFiltrados = new ArrayList<>(formatosFiltrados);
            this.misIncidentesFiltrados = new ArrayList<>(incidentesFiltrados);
            this.misPrestamosFiltrados = new ArrayList<>(prestamosFiltrados);
        }

        @Override
        public void run() {
            try {
                final String empleado = cmbEmpleado.getSelectedItem().toString();
                final String empleadoFiltro = quitaEspacios(empleado);

                //filtrar por empleado
                formatosEmpleado = misFormatosFiltrados.stream().filter(f -> f.getEncargado().equals(empleadoFiltro) && f.isPagado() == false).collect(toList());
                incidentesEmpleado = misIncidentesFiltrados.stream().filter(i -> i.getResponsable().equals(empleado) && i.isPagado() == false).collect(toList());
                prestamosEmpleado = misPrestamosFiltrados.stream().filter(i -> i.getEncargado().equals(empleado) && i.isPagado() == false).collect(toList());
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
            } catch (Exception e) {
                e.printStackTrace();
            }
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
            System.out.println(activos);
            System.out.println(inactivos);
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

            dtm.addRow(new Vector());
            tablaDeudoresActivos.setValueAt("Total:", tablaDeudoresActivos.getRowCount() - 1, 2);
            tablaDeudoresActivos.setValueAt(Utileria.sumaColumnaSinUltima(3, tablaDeudoresActivos), tablaDeudoresActivos.getRowCount() - 1, 3);

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
            
            dtm.addRow(new Vector());
            tablaDeudoresInactivos.setModel(dtm);
            tablaDeudoresInactivos.setValueAt("Total:", tablaDeudoresInactivos.getRowCount()-1, 2);
            tablaDeudoresInactivos.setValueAt(Utileria.sumaColumnaSinUltima(3, tablaDeudoresInactivos), tablaDeudoresInactivos.getRowCount()-1, 3);
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rGroup = new javax.swing.ButtonGroup();
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
        rbtnTodosNoPagados = new javax.swing.JRadioButton();
        rbtnNoPagadosSemana = new javax.swing.JRadioButton();
        rbtnSoloSemana = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nómina");

        tablaGeneral.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Empleado", "Turnos", "Formatos", "Comisión", "Pagar comisión", "Pendiente", "Prestamos", "Incidentes", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Boolean.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaGeneral.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tablaGeneralMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaGeneral);

        javax.swing.GroupLayout panelGeneralLayout = new javax.swing.GroupLayout(panelGeneral);
        panelGeneral.setLayout(panelGeneralLayout);
        panelGeneralLayout.setHorizontalGroup(
            panelGeneralLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1017, Short.MAX_VALUE)
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
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 563, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
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
            .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
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
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1041, Short.MAX_VALUE)
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

        rbtnTodosNoPagados.setText("Todos no pagados");
        rbtnTodosNoPagados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnTodosNoPagadosActionPerformed(evt);
            }
        });

        rbtnNoPagadosSemana.setText("Solo no pagados y en fecha");
        rbtnNoPagadosSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnNoPagadosSemanaActionPerformed(evt);
            }
        });

        rbtnSoloSemana.setText("Solo semana");
        rbtnSoloSemana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnSoloSemanaActionPerformed(evt);
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
                    .addGroup(panelFiltroLayout.createSequentialGroup()
                        .addComponent(rbtnTodosNoPagados)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnNoPagadosSemana)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(rbtnSoloSemana)))
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
                .addGroup(panelFiltroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtnTodosNoPagados)
                    .addComponent(rbtnNoPagadosSemana)
                    .addComponent(rbtnSoloSemana))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelFiltro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelPestaña, javax.swing.GroupLayout.Alignment.TRAILING))
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
        EmployeeThread employeeThread = new EmployeeThread(formatosFiltrados, incidentesFiltrados, prestamosFiltrados);
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

                for (Encargado encargado : encargados) {
                    if (encargado.getNombre().equals(empleado)) {
                        encargado.setPendiente(nuevoPendiente);
                        encargado.setNotaPendiente(notaPendiente);
                        encargado.setFechaPendiente(new Date()); //ultima fecha de actualizacion del pendiente
                        break;
                    }
                }

                if (rbtnTodosNoPagados.isSelected()) {
                    //marcar formatos como pagados, marcar prestamos como pagados, marcar incidentes como pagados            
                    //actualizar formatos 
                    for (Formato formato : formatos) {
                        Date fechaFormato = Utileria.SDF.parse(formato.getFecha());
                        if (quitaGuion(formato.getEncargado()).equals(empleado)) {
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
                    //marcar formatos como pagados, marcar prestamos como pagados, marcar incidentes como pagados                                
                    for (Formato formato : formatos) {
                        Date fechaFormato = Utileria.SDF.parse(formato.getFecha());
                        if (quitaGuion(formato.getEncargado()).equals(empleado) && !fechaFormato.before(lunesAnterior) && !fechaFormato.after(domingoPosterior)) {
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

                ExecutorService executor = Executors.newFixedThreadPool(4);

                executor.execute(() -> Encargado.actualizarBD(encargados)); //actuaizar el pendiente del encargado                                 );               
                executor.execute(() -> ConsultaFormato.actualizarFormatos(formatos)); //actualizar los pagado                
                executor.execute(() -> Prestamo.actualizarDB(prestamos)); //actualizar los pagado                                
                executor.execute(() -> Incidente.actualizarDB(incidentes)); //actualizar los pagado

                executor.shutdown();
                while (!executor.isTerminated()) {
                    System.out.println("waiting to update");
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {

                    }
                }

                initData();

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
        EmployeeThread employeeThread = new EmployeeThread(formatosFiltrados, incidentesFiltrados, prestamosFiltrados);
        employeeThread.start();
    }//GEN-LAST:event_chkPagarConPendienteActionPerformed

    private void rbtnTodosNoPagadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnTodosNoPagadosActionPerformed
        datechooserGeneral.setEnabled(false);
        btnPagar.setEnabled(true);
        initData();
    }//GEN-LAST:event_rbtnTodosNoPagadosActionPerformed

    private void rbtnNoPagadosSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnNoPagadosSemanaActionPerformed
        datechooserGeneral.setEnabled(true);
        btnPagar.setEnabled(true);
        initData();
    }//GEN-LAST:event_rbtnNoPagadosSemanaActionPerformed

    private void rbtnSoloSemanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnSoloSemanaActionPerformed
        datechooserGeneral.setEnabled(true);
        btnPagar.setEnabled(false);
        initData();
    }//GEN-LAST:event_rbtnSoloSemanaActionPerformed

    private void tablaGeneralMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaGeneralMouseReleased
        this.activarComision();
    }//GEN-LAST:event_tablaGeneralMouseReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbonarActivo;
    private javax.swing.JButton btnAbonarInactivo;
    private javax.swing.JButton btnGenerar;
    private javax.swing.JButton btnPagar;
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
    private javax.swing.ButtonGroup rGroup;
    private javax.swing.JRadioButton rbtnNoPagadosSemana;
    private javax.swing.JRadioButton rbtnSoloSemana;
    private javax.swing.JRadioButton rbtnTodosNoPagados;
    private javax.swing.JTable tablaDeudoresActivos;
    private javax.swing.JTable tablaDeudoresInactivos;
    private javax.swing.JTable tablaFormatos;
    private javax.swing.JTable tablaGeneral;
    private javax.swing.JTable tablaIncidentes;
    private javax.swing.JTable tablaPrestamos;
    private javax.swing.JTable tablaTotales;
    // End of variables declaration//GEN-END:variables
}
