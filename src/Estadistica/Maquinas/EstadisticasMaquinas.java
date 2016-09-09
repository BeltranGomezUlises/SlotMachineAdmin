/*Necesitamos obtener una tabla, donde muestre las sucursales y sus datos de los corte de forma estadistica
 las columnas de la tabla son: SUCURSAL, N. MAQUINAS, FECHAS DE LOS CORTES Y SU CANTIDAD,
 PROMEDIO DE CORTE POR MAQUINA, TOTAL DE CORTE EN ESAS FECHAS, % QUE REPRESENTA DEL CORTE GENERAL.
*/
package Estadistica.Maquinas;

import Corte.Corte;
import Sucursal.Sucursal;
import Utilerias.Utileria;
import static Utilerias.Utileria.quitaGuion;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EstadisticasMaquinas extends javax.swing.JFrame {

    private DateFormat dateFormat = new SimpleDateFormat("d/MM/yyyy");
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private Vector<Corte> cortes;
   
    private Vector<Corte> cortesFiltrados;
    private float corteTotalGeneral = 0;    
    private String datos[][]={};        
    private DefaultTableModel md;
    
    //private int deltaDias;
    
    //LISTAS DE LA TABLA, CADA UNA REPRESENTA UNA COLUMNA
    
    //lista de sucursales existente en los cortes
    private ArrayList<String> sucursales;
    private ArrayList<Integer> comisionesSucursales;
    private Vector<Sucursal> sucursalesArchivo; 
   //lista de la cantidad de maquinas correspondiente a sucursal
    private ArrayList<Integer> numMaquinas;        
    //lista del corte promedio que tiene cada sucursal al dia
    private ArrayList<Float> promediosSemanalesDeCorte;
    //lista con la suma del corte por sucursal
    private ArrayList<Float> cortesTotalesPorSucursal;
    //lista con el porcentaje de corte al que corresponde cada sucursal
    private ArrayList<Float> porcentajesDeCorteTotalPorSucursal;    
    //lista para almacenar las dechas de los cortes
    private ArrayList<String> fechasDeCortes;
    //lista para almacenar las dechas de los cortes
    private ArrayList<String> fechasDeInicioDeSemana;
    //Lista para almacenar el promedio de cortes de una sucursal desde el origen de los tiempos
    private ArrayList<Float> historicosGenerales;
    //Lista para almacenar el el promedio de cortes de la sucursal solo del tango filtrado
    private ArrayList<Float> historicosPorFiltrados;
    //matriz de la cantidad de cortes por fecha de las sucursales
    private ArrayList<ArrayList<Float>> matrizCortesPorFecha;    
    //lista del porcentaje de retencion que tiene la sucursal
    private ArrayList<Float> porcentajeRetencionContadorPorSucursal;
    private ArrayList<Float> porcentajeRetencionPantallaPorSucursal;
    
    
    //LISTAS PARA LA TABLA DE TARJETAS
    private ArrayList<Integer> cantMaquinasPorTarjeta;
    private ArrayList<Float> porcMaquinasPorTarjeta;
    private ArrayList<Float> porcCortePorTarjeta;
    private ArrayList<Float> cantCortePorTarjeta;
    private ArrayList<String> nomTarjetas;
    private ArrayList<Float> historicosGeneralesPorTarjeta;
    private ArrayList<Float> historicosPorFiltradosPorTarjeta;
    private ArrayList<Float> porcentajeRetencionContadorPorTarjeta;
    private ArrayList<Float> porcentajeRetencionPantallaPorTarjeta;
    
    // LISTAS PARA LA TABLA DE PERIFERICOS
    private ArrayList<Integer> cantMaquinasPorPeriferico;
    private ArrayList<Float> porcMaquinasPorPeriferico;
    private ArrayList<Float> porcCortePorPeriferico;
    private ArrayList<Float> cantCortePorPeriferico;
    private ArrayList<String> nomPerifericos;
    private ArrayList<Float> historicosGeneralesPorPeriferico;
    private ArrayList<Float> historicosPorFiltradosPorPeriferico;
    private ArrayList<Float> porcentajeRetencionContadorPorPeriferico;
    private ArrayList<Float> porcentajeRetencionPantallaPorPeriferico;
    //LISTAS PARA TABLA DE MONEDAS
    private ArrayList<Integer> cantMaquinasPorMoneda;
    private ArrayList<Float> porcMaquinasPorMoneda;
    private ArrayList<Float> porcCortePorMoneda;
    private ArrayList<Float> cantCortePorMoneda;
    private ArrayList<String> nomMonedas;
    private ArrayList<Float> historicosGeneralesPorMoneda;
    private ArrayList<Float> historicosPorFiltradosPorMoneda;
    private ArrayList<Float> porcentajeRetencionContadorPorMoneda;
    private ArrayList<Float> porcentajeRetencionPantallaPorMoneda;
    
    //CONSTRUCTOR =>
    public EstadisticasMaquinas() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        cortes = Corte.cargarCortes(); //la lista general de todos los cortes  
        //inicializar las listas
        this.inicializarListas();        
        //llenar con listas de valores
        try {
            this.llenarListasDeValores();        
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "No hay cortes para mostrar estadisticas");
        }
        
        //calcular los valores de la listas
        this.calcularValoresSucursales();
         //obtener el corte historico general de las sucursales del origen de los tiempos
        historicosGenerales = this.calcularCorteHistorico();
        historicosPorFiltrados = this.calcularCorteHistorico();
        //obtener el valor de filtrado aunque sea el mismo por ser el proceso de construccion
        //llenar las tablas y campos
        ConcentradoSucursales cs = this.crearCS();
        this.llenarTablaSucursales(cs); //enpaquetar las listas en el objeto        
        
       
        
    }
    
    //INICIALIZAR VARIABLES =>
    private void inicializarListas(){                                      
        sucursales = new ArrayList<>();  
        comisionesSucursales = new ArrayList<>();
        sucursalesArchivo = Sucursal.cargarSucursales();
        numMaquinas = new ArrayList<>();        
        promediosSemanalesDeCorte = new ArrayList<>();
        cortesTotalesPorSucursal = new ArrayList<>();
        porcentajesDeCorteTotalPorSucursal = new ArrayList<>(); 
        fechasDeInicioDeSemana = new ArrayList<>();
        porcentajeRetencionContadorPorSucursal = new ArrayList<>();
        porcentajeRetencionPantallaPorSucursal = new ArrayList<>();
        //cargar a la lista de cortes filtrados los cortes de la lista general
        cortesFiltrados = new Vector<>();
        for (Corte corte : cortes) {
            cortesFiltrados.add(corte);
        }        
    }                
    
    //COMPORTAMIENTO
    private void llenarListasDeValores(){        
               
        //obtener las fechas de los cortes
        fechasDeCortes = fechasDeCortes(cortesFiltrados);
        
        //obtener la primer fecha y la ultima                
        String fechaInicial = fechasDeCortes.get(0);
        String fechaFinal = fechasDeCortes.get(fechasDeCortes.size() - 1);
       
        //deltaDias = Utileria.diferenciaDias(fechaInicial, fechaFinal);
        //if (deltaDias == 0) deltaDias = 1; //si solo hay una fecha obtendremos 0, para evitar errores lo convertimos  a 1

        //obtener lunes anterior de la primer fecha y lunes posterior de la ultima fecha
        Date lunesAnterior = Utileria.lunesAnterior(fechaInicial);
        Date lunesPosterior = Utileria.lunesPosterior(fechaFinal);
        //obtener todos los lunes desde el lunesAnterior hasta el lunes posterior
        fechasDeInicioDeSemana.add(dateFormat.format(lunesAnterior));
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(lunesAnterior);
        while (cal.getTime().before(lunesPosterior)) {
            cal.add(Calendar.DATE, 7);
            fechasDeInicioDeSemana.add(dateFormat.format(cal.getTime()));
        }                          
        //remover el lunes posterior que tambien se agrega en el ciclo
        fechasDeInicioDeSemana.remove(fechasDeInicioDeSemana.size() - 1);
        //llenar la lista de sucursales con las que alla en los cortes
        sucursales = new ArrayList<>();
        for (Corte corte : cortesFiltrados) {
            String suc = corte.getSucursal();
            if (!sucursales.contains(suc)) {
                sucursales.add(suc);     
                //buscar su comision y asiganrla
                int comision = 0;
                for (Sucursal sucursalArchivo : sucursalesArchivo) {
                    if (suc.equals(sucursalArchivo.getNombre())) {
                        comision = sucursalArchivo.getComision();
                    }
                }
                comisionesSucursales.add(comision);
            }
        }
        //inicializar la matriz de las cantidades de corte por fecha con 0 en todos los campos
//        matrizCortesPorFecha = new float[sucursales.size()][fechasDeCortes.size()];
//        for (int i = 0; i < sucursales.size(); i++) {
//            Arrays.fill(matrizCortesPorFecha[i], 0); //llenar cada columna con 0
//               
        matrizCortesPorFecha = new ArrayList<>();
        for (int i = 0; i < sucursales.size(); i++) {
            matrizCortesPorFecha.add(new ArrayList<Float>());
            for (int j = 0; j < fechasDeInicioDeSemana.size(); j++) {
                matrizCortesPorFecha.get(i).add(0.0f);
            }
        }
    }
    
    private void calcularValoresSucursales(){
        //con los nombres de las sucursales en cortes, llenar las maquinas que tienen las sucursales segun los cortes
        for (int k = 0; k < sucursales.size(); k++) {                                
            //obtener los cortes que corresponden a esta sucursal
            ArrayList<Corte> cortesEnSucursal = this.cortesEnSucursal(sucursales.get(k));             
            //obtener el corte total de la sucursal
            float corteTotalSucursal = this.calcularCorteTotal(cortesEnSucursal);            
            cortesTotalesPorSucursal.add(corteTotalSucursal);            
            //obtener los nombres de las maquinas que tienen los cortes de la sucursal
            ArrayList<String> nombresMaquinasSuc = new ArrayList<>();
            //listas para agregar los porcentajes de retencion de cada corte
           
            float sumaPorcentajePantalla = 0;            
            float sumaPorcentajeContador = 0;
            
            for (Corte c : cortesEnSucursal) {                
                if (!nombresMaquinasSuc.contains(c.getMaquina().getNombre())) {
                    nombresMaquinasSuc.add(c.getMaquina().getNombre());
                }      
                //obtener la cantidad de retencion de cada corte
                sumaPorcentajePantalla += c.getPorcentajePantalla();
                sumaPorcentajeContador += c.getPorcentajeContador();
            }  
            
            porcentajeRetencionPantallaPorSucursal.add(((float) sumaPorcentajePantalla / (float) cortesEnSucursal.size() ));
            porcentajeRetencionContadorPorSucursal.add(((float) sumaPorcentajeContador / (float) cortesEnSucursal.size() ));
            //añadir el numero de maquinas que tiene la sucursal a la lista general
            numMaquinas.add(nombresMaquinasSuc.size());
            ArrayList<Corte> cortesEnFecha;
            for (int i = 0; i < fechasDeInicioDeSemana.size(); i++) {
                cortesEnFecha = new ArrayList<>(); //obtener los cortes de esa fecha en sucursal actual
                for (int j = 0; j < cortesEnSucursal.size(); j++) {
                    if (Utileria.belongsDateToWeek(cortesEnSucursal.get(j).getFecha(), fechasDeInicioDeSemana.get(i))) {
                        cortesEnFecha.add(cortesEnSucursal.get(j));
                    }
                }
                //ahora con los cortes, obtener la suma del inout
                float corteEnFecha = 0;
                for (Corte c : cortesEnFecha) {
                    corteEnFecha += c.getInOut();
                }
                //matrizCortesPorFecha[k][i] = corteEnFecha; //usar metodo set por que ya tiene valores la lista de 0.0f
                matrizCortesPorFecha.get(k).set(i, corteEnFecha);
            }
        } 
                                
        //con el corte total de cada sucursal, obtener la suma total de corte y obtener los porcentajes
        float sumaCortes = 0;
        for (int i = 0; i < cortesTotalesPorSucursal.size(); i++) {                    
            sumaCortes += cortesTotalesPorSucursal.get(i);            
            //obtener el promedio de corte por dia
            promediosSemanalesDeCorte.add( (float) cortesTotalesPorSucursal.get(i) / (float) fechasDeInicioDeSemana.size() );
        }
        corteTotalGeneral = sumaCortes;
        
        for (float corteTotal : cortesTotalesPorSucursal) {
             //con el corteTotalGeneral obtener los porcentajes de corte
            porcentajesDeCorteTotalPorSucursal.add(corteTotal / corteTotalGeneral * 100);
        }              
    }
       
    private void calcularValoresTarjetas(ArrayList<Corte> cortesEnSucursal){
        cantMaquinasPorTarjeta = new ArrayList<>();
        porcMaquinasPorTarjeta = new ArrayList<>();
        cantCortePorTarjeta = new ArrayList<>();
        porcCortePorTarjeta = new ArrayList<>();   
        porcentajeRetencionContadorPorTarjeta = new ArrayList<>(); 
        porcentajeRetencionPantallaPorTarjeta = new ArrayList<>(); 
        
        //obtener la listas de las tarjetas que existan en los cortes de la sucursal y que maquinas tiene la sucursal
        nomTarjetas = new ArrayList<>();
        ArrayList<String> nomMaquinasEnSucursal = new ArrayList<>();            
        for (Corte c : cortesEnSucursal) {
            if (!nomTarjetas.contains(c.getMaquina().getTarjeta().getNombre())) {
                nomTarjetas.add(c.getMaquina().getTarjeta().getNombre());
            }
            if (!nomMaquinasEnSucursal.contains(c.getMaquina().getNombre())) {
                nomMaquinasEnSucursal.add(c.getMaquina().getNombre());
            }
        }
        
        int cantMaquinasEnSucursal = nomMaquinasEnSucursal.size();        
        //con los nombres de todas las tarjeras recorrer la lista de cortes y generar una lista con cada tipo de tarjeta
        ArrayList<Corte> cortesDeTarjeta;
        ArrayList<String> maquinasDeTarjeta;
        for (String nomTarjeta : nomTarjetas) {
            maquinasDeTarjeta = new ArrayList<>();
            cortesDeTarjeta = new ArrayList<>();
            for (Corte c : cortesEnSucursal) {
                if (c.getMaquina().getTarjeta().getNombre().equals(nomTarjeta)) {
                    if (!maquinasDeTarjeta.contains(c.getMaquina().getNombre())) {
                        maquinasDeTarjeta.add(c.getMaquina().getNombre());
                    }
                    cortesDeTarjeta.add(c);
                }
            }                  
            
            float sumaPorcentajePantalla = 0;            
            float sumaPorcentajeContador = 0;
            
            for (Corte corte : cortesDeTarjeta) {
                sumaPorcentajePantalla += corte.getPorcentajePantalla();
                sumaPorcentajeContador += corte.getPorcentajeContador();                
            }
            porcentajeRetencionPantallaPorTarjeta.add((float) sumaPorcentajePantalla / (float)cortesDeTarjeta.size());
            porcentajeRetencionContadorPorTarjeta.add((float) sumaPorcentajeContador / (float)cortesDeTarjeta.size());
            
            int cantMaquinasDeTarjeta = maquinasDeTarjeta.size();
            float porcMaquinasDeTarjeta = ((float)cantMaquinasDeTarjeta / (float)cantMaquinasEnSucursal) * 100;
            
            float cantCorteDeTarjeta = this.calcularCorteTotal(cortesDeTarjeta);                        
            float corteTotalDeSucursal = cortesTotalesPorSucursal.get(tablaSucursales.getSelectedRow());                
            float porcCorteDeTarjeta = (float) cantCorteDeTarjeta / (float)corteTotalDeSucursal * 100;
            
            cantMaquinasPorTarjeta.add(cantMaquinasDeTarjeta);
            porcMaquinasPorTarjeta.add(porcMaquinasDeTarjeta);
            cantCortePorTarjeta.add(cantCorteDeTarjeta);
            porcCortePorTarjeta.add(porcCorteDeTarjeta);
        }

        ConcentradoTarjetas ct = this.crearCT();
        this.llenarTablaTarjetas(ct);
    }
    
    private void calcularValoresPerifericos(ArrayList<Corte> cortesEnSucursal) {
        cantMaquinasPorPeriferico = new ArrayList<>();
        porcMaquinasPorPeriferico = new ArrayList<>();
        cantCortePorPeriferico = new ArrayList<>();
        porcCortePorPeriferico = new ArrayList<>(); 
        porcentajeRetencionPantallaPorPeriferico = new ArrayList<>();
        porcentajeRetencionContadorPorPeriferico = new ArrayList<>();
                
        //obtener la listas de las tarjetas que existan en los cortes de la sucursal y que maquinas tiene la sucursal
        nomPerifericos = new ArrayList<>();
        ArrayList<String> nomMaquinasEnSucursal = new ArrayList<>();            
        for (Corte c : cortesEnSucursal) {
            if (!nomPerifericos.contains(c.getMaquina().getPeriferico().getNombre())) {
                nomPerifericos.add(c.getMaquina().getPeriferico().getNombre());
            }
            if (!nomMaquinasEnSucursal.contains(c.getMaquina().getNombre())) {
                nomMaquinasEnSucursal.add(c.getMaquina().getNombre());
            }
        }
        
        int cantMaquinasEnSucursal = nomMaquinasEnSucursal.size();        
        //con los nombres de todas las tarjeras recorrer la lista de cortes y generar una lista con cada tipo de tarjeta
        ArrayList<Corte> cortesDePeriferico;
        ArrayList<String> maquinasDePeriferico;
        for (String nomPeriferico : nomPerifericos) {
            maquinasDePeriferico = new ArrayList<>();
            cortesDePeriferico = new ArrayList<>();
            for (Corte c : cortesEnSucursal) {
                if (c.getMaquina().getPeriferico().getNombre().equals(nomPeriferico)) {
                    if (!maquinasDePeriferico.contains(c.getMaquina().getNombre())) {
                        maquinasDePeriferico.add(c.getMaquina().getNombre());
                    }
                    cortesDePeriferico.add(c);
                }
            }
            float sumaPorcentajePantalla = 0;            
            float sumaPorcentajeContador = 0;
            
            for (Corte corte : cortesDePeriferico) {
                sumaPorcentajePantalla += corte.getPorcentajePantalla();
                sumaPorcentajeContador += corte.getPorcentajeContador();                
            }
            porcentajeRetencionPantallaPorPeriferico.add((float) sumaPorcentajePantalla / (float)cortesDePeriferico.size());
            porcentajeRetencionContadorPorPeriferico.add((float) sumaPorcentajeContador / (float)cortesDePeriferico.size());
            
            int cantMaquinasDePeriferico = maquinasDePeriferico.size();
            float porcMaquinasDePeriferico = ((float)cantMaquinasDePeriferico / (float)cantMaquinasEnSucursal) * 100;
            
            float cantCorteDePeriferico = this.calcularCorteTotal(cortesDePeriferico);                        
            float corteTotalDeSucursal = cortesTotalesPorSucursal.get(tablaSucursales.getSelectedRow());                
            float porcCorteDePeriferico = (float) cantCorteDePeriferico / (float)corteTotalDeSucursal * 100;
            
            cantMaquinasPorPeriferico.add(cantMaquinasDePeriferico);
            porcMaquinasPorPeriferico.add(porcMaquinasDePeriferico);
            cantCortePorPeriferico.add(cantCorteDePeriferico);
            porcCortePorPeriferico.add(porcCorteDePeriferico);
        }

        ConcentradoPerifericos cp = this.crearCP();
        this.llenarTablaPerifericos(cp);
    }
    
    private void calcularValoresMonedas(ArrayList<Corte> cortesEnSucursal){
        cantMaquinasPorMoneda = new ArrayList<>();
        porcMaquinasPorMoneda = new ArrayList<>();
        cantCortePorMoneda = new ArrayList<>();
        porcCortePorMoneda = new ArrayList<>(); 
        porcentajeRetencionPantallaPorMoneda = new ArrayList<>();
        porcentajeRetencionContadorPorMoneda = new ArrayList<>();        
        
        //obtener la listas de las tarjetas que existan en los cortes de la sucursal y que maquinas tiene la sucursal
        nomMonedas = new ArrayList<>();
        ArrayList<String> nomMaquinasEnSucursal = new ArrayList<>();            
        for (Corte c : cortesEnSucursal) {
            if (!nomMonedas.contains( String.valueOf(c.getMaquina().getMoneda()) ) ) {
                nomMonedas.add(String.valueOf(c.getMaquina().getMoneda()));
            }
            if (!nomMaquinasEnSucursal.contains(c.getMaquina().getNombre())) {
                nomMaquinasEnSucursal.add(c.getMaquina().getNombre());
            }
        }
        
        int cantMaquinasEnSucursal = nomMaquinasEnSucursal.size();        
        //con los nombres de todas las tarjeras recorrer la lista de cortes y generar una lista con cada tipo de tarjeta
        ArrayList<Corte> cortesDeMoneda;
        ArrayList<String> maquinasDeMoneda;
        for (String nomMoneda : nomMonedas) {
            maquinasDeMoneda = new ArrayList<>();
            cortesDeMoneda = new ArrayList<>();
            for (Corte c : cortesEnSucursal) {
                if (String.valueOf(c.getMaquina().getMoneda()).equals(nomMoneda)) {
                    if (!maquinasDeMoneda.contains(c.getMaquina().getNombre())) {
                        maquinasDeMoneda.add(c.getMaquina().getNombre());
                    }
                    cortesDeMoneda.add(c);
                }
            }
            float sumaPorcentajePantalla = 0;            
            float sumaPorcentajeContador = 0;
            
            for (Corte corte : cortesDeMoneda) {
                sumaPorcentajePantalla += corte.getPorcentajePantalla();
                sumaPorcentajeContador += corte.getPorcentajeContador();                
            }
            porcentajeRetencionPantallaPorMoneda.add((float) sumaPorcentajePantalla / (float)cortesDeMoneda.size());
            porcentajeRetencionContadorPorMoneda.add((float) sumaPorcentajeContador / (float)cortesDeMoneda.size());
            
            int cantMaquinasDeMoneda = maquinasDeMoneda.size();
            float porcMaquinasDeMoneda = ((float)cantMaquinasDeMoneda / (float)cantMaquinasEnSucursal) * 100;
            
            float cantCorteDeMoneda = this.calcularCorteTotal(cortesDeMoneda);                        
            float corteTotalDeSucursal = cortesTotalesPorSucursal.get(tablaSucursales.getSelectedRow());                
            float porcCorteDeMoneda= (float) cantCorteDeMoneda / (float)corteTotalDeSucursal * 100;
            
            cantMaquinasPorMoneda.add(cantMaquinasDeMoneda);
            porcMaquinasPorMoneda.add(porcMaquinasDeMoneda);
            cantCortePorMoneda.add(cantCorteDeMoneda);
            porcCortePorMoneda.add(porcCorteDeMoneda);
        }

        ConcentradoMonedas cm = this.crearCM();
        this.llenarTablaMonedas(cm);
    }
            
    private ArrayList<Float> calcularCorteHistorico(){
        ArrayList<Float> historicoPorSucursal = new ArrayList<>();
        float suma;
        int nCortes;
        for (int i = 0; i < sucursales.size(); i++) {                    
            //para cada sucursal obtener el corte por fecha y dividir entre el numero de cortes contados
            suma = 0;
            nCortes = 0;
            for (int j = 0; j < matrizCortesPorFecha.get(i).size(); j++) {
                if (matrizCortesPorFecha.get(i).get(j) > 0) {
                    suma += matrizCortesPorFecha.get(i).get(j);
                    nCortes ++;
                }
            }          
            historicoPorSucursal.add(suma / (float) nCortes);
        }
        return historicoPorSucursal;
    }
    
    //FUNCIONES COMUNES
    private ArrayList<String> fechasDeCortes(Vector<Corte> listaCortes){
        ArrayList<String> listaFechasDeCortes = new ArrayList<>();        
        for (Corte c : listaCortes) {
            if (!listaFechasDeCortes.contains(c.getFecha())) {
                listaFechasDeCortes.add(c.getFecha());
            }
        }        
        //ordenas las fechas
        Utileria.BubbleOrderStringOfDates(listaFechasDeCortes);
        
        return listaFechasDeCortes;
    }
    
    private float calcularCorteTotal(ArrayList<Corte> cortes){
        float suma = 0 ;
        for (Corte corte : cortes) {
            suma += corte.getInOut();
        }
        return suma;
    }
    
    private ArrayList<Corte> cortesEnSucursal(String sucursal){
        ArrayList<Corte> cortesEnSucursal = new ArrayList<>();            
            for (Corte corte : cortesFiltrados) {
                if (corte.getSucursal().equals(sucursal)) {
                    cortesEnSucursal.add(corte);
                }
            } 
        return cortesEnSucursal;
    }
    
    
    //LLENADO DE TABLAS
    private void llenarTablaSucursales(ConcentradoSucursales cs){
        String cabecera[]={"Sucursal","#. Maquinas","Corte Total","comision","Utilidad","% de Corte", "Corte Semanal", "C.S. p/Maquina", "Historico", "P. Filtrado", "Retención P - C"};
        md = new DefaultTableModel(datos, cabecera);
        Vector fila;
        Vector filaTotales = new Vector();
        for (int i = 0; i < 10 ; i++) {
            filaTotales.add(0);
        }
        filaTotales.set(0, "Totales:");
        float retencionPantalla = 0; //acomulador de porcentaje de retencion
        float retencionContador = 0;
        float comision = 0;
        for (int i = 0; i < cs.getSucursales().size(); i++) {
            fila = new Vector();
            fila.add(quitaGuion(cs.getSucursales().get(i))); // pos 0
            fila.add(cs.getNumMaquinas().get(i));            //pos 1
            fila.add(cs.getCortesTotalesPorSucursal().get(i)); //pos 2
            fila.add(cs.getComisiones().get(i) + "%");         //pos 3
            fila.add(cs.getCortesTotalesPorSucursal().get(i) / 100 * cs.getComisiones().get(i)); //pos 4
            fila.add(decimalFormat.format(cs.getPorcentajesDeCorteTotalPorSucursal().get(i)) + "%");  //pos 5
            fila.add(decimalFormat.format(cs.getPromediosSemanalesDeCorte().get(i))); //pos 6
            fila.add(decimalFormat.format((float) cs.getPromediosSemanalesDeCorte().get(i) / (float) cs.getNumMaquinas().get(i))); //pos 7
            fila.add(decimalFormat.format(cs.getHistoricosGenerales().get(i)));//pos 8
            fila.add(decimalFormat.format(cs.getHistoricosPorFiltrados().get(i)));//pos 9
            fila.add(decimalFormat.format(cs.getPorcentajeRetencionPantallaPorSucursal().get(i)) + "% - " + decimalFormat.format(cs.getPorcentajeRetencionContadorPorSucursal().get(i)) + "%"); //pos 10
            md.addRow(fila);
            //sumar al total de cada columna
            filaTotales.set(1, Integer.parseInt(filaTotales.get(1).toString()) + cs.getNumMaquinas().get(i));
            filaTotales.set(2, Float.parseFloat(filaTotales.get(2).toString()) + cs.getCortesTotalesPorSucursal().get(i));
            comision += cs.getComisiones().get(i);
            filaTotales.set(4, Float.parseFloat(filaTotales.get(4).toString()) + (cs.getCortesTotalesPorSucursal().get(i) / 100 * cs.getComisiones().get(i)) );
            filaTotales.set(5, Float.parseFloat(filaTotales.get(5).toString()) + cs.getPorcentajesDeCorteTotalPorSucursal().get(i));
            filaTotales.set(6, Float.parseFloat(filaTotales.get(6).toString()) + cs.getPromediosSemanalesDeCorte().get(i));
            filaTotales.set(7, Float.parseFloat(filaTotales.get(7).toString()) + ( (float) cs.getPromediosSemanalesDeCorte().get(i) / (float) cs.getNumMaquinas().get(i)) );
            filaTotales.set(8, Float.parseFloat(filaTotales.get(8).toString()) + cs.getHistoricosGenerales().get(i));
            filaTotales.set(9, Float.parseFloat(filaTotales.get(9).toString()) + cs.getHistoricosPorFiltrados().get(i));  
            retencionPantalla += cs.getPorcentajeRetencionPantallaPorSucursal().get(i); //posiciones 10
            retencionContador += cs.getPorcentajeRetencionContadorPorSucursal().get(i);
            
        }
        //añadir formatos a fila de totales
        filaTotales.add(decimalFormat.format(retencionPantalla / (float) sucursales.size()) + "% - " +  decimalFormat.format(retencionContador / (float) sucursales.size()) + "%");
        filaTotales.set(3, decimalFormat.format(comision / (float) sucursales.size()) + "%");
        filaTotales.set(5, decimalFormat.format(filaTotales.get(5)) + "%" );
        filaTotales.set(7, decimalFormat.format(filaTotales.get(7)) );
        //agregar la matriz de cortes por semana a la tabla y agregar a la fila de totales su correspondiente cantidad
        for (int i = 0; i < fechasDeInicioDeSemana.size(); i++) {
            String columnaDeCortes[] = new String[cs.getMatrizCortesPorFecha().size()];
            float totalDeColumnaDeCortes = 0;
            for (int j = 0; j < columnaDeCortes.length; j++) {
                columnaDeCortes[j] = String.valueOf(cs.getMatrizCortesPorFecha().get(j).get(i));
                totalDeColumnaDeCortes += Float.parseFloat(columnaDeCortes[j]);
            }         
            filaTotales.add( totalDeColumnaDeCortes );
            md.addColumn(fechasDeInicioDeSemana.get(i),columnaDeCortes );
        }
        
        //agregar la fila de totales al modelo
        md.addRow(filaTotales);
        tablaSucursales.setModel(md);
        
        //txt resultados
        txtCortePorMaquina.setText(decimalFormat.format((Float.parseFloat(filaTotales.get(2).toString()) / Float.parseFloat(filaTotales.get(1).toString())) / (float) fechasDeInicioDeSemana.size() ) );
        txtCorteGeneral.setText(decimalFormat.format(corteTotalGeneral));
    }
    
    private ConcentradoSucursales crearCS(){
        ConcentradoSucursales cs = new ConcentradoSucursales();
        cs.setSucursales(sucursales);
        cs.setComisiones(comisionesSucursales);
        cs.setNumMaquinas(numMaquinas);        
        cs.setCortesTotalesPorSucursal(cortesTotalesPorSucursal);        
        cs.setPorcentajesDeCorteTotalPorSucursal(porcentajesDeCorteTotalPorSucursal);
        cs.setMatrizCortesPorFecha(matrizCortesPorFecha);
        cs.setPromediosSemanalesDeCorte(promediosSemanalesDeCorte);
        cs.setHistoricosGenerales(historicosGenerales);
        cs.setHistoricosPorFiltrados(historicosPorFiltrados);
        cs.setPorcentajeRetencionContadorPorSucursal(porcentajeRetencionContadorPorSucursal);
        cs.setPorcentajeRetencionPantallaPorSucursal(porcentajeRetencionPantallaPorSucursal);
        return cs;
    }
    
    private void llenarTablaTarjetas(ConcentradoTarjetas ct){
        String cabecera[]={"Tarjeta","#. Maquinas","%. Maquinas","$ de Corte","% de Corte", "Retención P - C"};
        DefaultTableModel md = new DefaultTableModel(datos, cabecera);
        Vector fila;
        for (int i = 0; i < nomTarjetas.size(); i++) {
            fila = new Vector<>();
            fila.add(quitaGuion(nomTarjetas.get(i)));
            fila.add(ct.getCantMaquinasPorTarjeta().get(i));
            fila.add(decimalFormat.format(ct.getPorcMaquinasPorTarjeta().get(i)) + "%");
            fila.add(ct.getCantCortePorTarjeta().get(i));
            fila.add(decimalFormat.format(ct.getPorcCortePorTarjeta().get(i)) + "%");
            fila.add(decimalFormat.format(ct.getPorcentajeRetencionPantallaPorTarjeta().get(i)) + "% - " + decimalFormat.format(ct.getPorcentajeRetencionContadorPorTarjeta().get(i)) + "%");
            md.addRow(fila);
        }
        tablaTarjetas.setModel(md);
    }
    
    private ConcentradoTarjetas crearCT(){
        ConcentradoTarjetas ct = new ConcentradoTarjetas();
        ct.setCantMaquinasPorTarjeta(cantMaquinasPorTarjeta);
        ct.setPorcMaquinasPorTarjeta(porcMaquinasPorTarjeta);
        ct.setCantCortePorTarjeta(cantCortePorTarjeta);
        ct.setPorcCortePorTarjeta(porcCortePorTarjeta);
        ct.setPorcentajeRetencionContadorPorTarjeta(porcentajeRetencionContadorPorTarjeta);
        ct.setPorcentajeRetencionPantallaPorTarjeta(porcentajeRetencionPantallaPorTarjeta);
        return ct;
    }
    
    private void llenarTablaPerifericos(ConcentradoPerifericos cp){
        String cabecera[]={"Periférico","#. Maquinas","%. Maquinas","$ de Corte","% de Corte", "Retención P - C"};
        DefaultTableModel md = new DefaultTableModel(datos, cabecera);
        Vector fila;
        for (int i = 0; i < nomPerifericos.size(); i++) {
            fila = new Vector<>();
            fila.add(quitaGuion(nomPerifericos.get(i)));
            fila.add(cp.getCantMaquinasPorPeriferico().get(i));
            fila.add(decimalFormat.format(cp.getPorcMaquinasPorPeriferico().get(i)) + "%");
            fila.add(cp.getCantCortePorPeriferico().get(i));
            fila.add(decimalFormat.format(cp.getPorcCortePorPeriferico().get(i)) + "%");
            fila.add(decimalFormat.format(cp.getPorcentajeRetencionPantallaPorPeriferico().get(i)) + "% - " 
                    + decimalFormat.format(cp.getPorcentajeRetencionContadorPorPeriferico().get(i)) + "%");
            md.addRow(fila);
        }
        tablaPerifericos.setModel(md);
    }
    
    private ConcentradoPerifericos crearCP(){
        ConcentradoPerifericos ct = new ConcentradoPerifericos();
        ct.setCantMaquinasPorPeriferico(cantMaquinasPorPeriferico);
        ct.setPorcMaquinasPorPeriferico(porcMaquinasPorPeriferico);
        ct.setCantCortePorPeriferico(cantCortePorPeriferico);
        ct.setPorcCortePorPeriferico(porcCortePorPeriferico);
        ct.setPorcentajeRetencionPantallaPorPeriferico(porcentajeRetencionPantallaPorPeriferico);
        ct.setPorcentajeRetencionContadorPorPeriferico(porcentajeRetencionContadorPorPeriferico);
        return ct;
    }
    
    private void llenarTablaMonedas(ConcentradoMonedas cm){
        String cabecera[]={"Moneda","#. Maquinas","%. Maquinas","$ de Corte","% de Corte", "Retención P - C"};
        DefaultTableModel md = new DefaultTableModel(datos, cabecera);
        Vector fila;
        for (int i = 0; i < nomMonedas.size(); i++) {
            fila = new Vector<>();
            fila.add(quitaGuion(nomMonedas.get(i)));
            fila.add(cm.getCantMaquinasPorMoneda().get(i));
            fila.add(decimalFormat.format(cm.getPorcMaquinasPorMoneda().get(i)) + "%");
            fila.add(cm.getCantCortePorMoneda().get(i));
            fila.add(decimalFormat.format(cm.getPorcCortePorMoneda().get(i)) + "%");
            fila.add(decimalFormat.format(cm.getPorcentajeRetencionPantallaPorMoneda().get(i)) + "% - " 
                    + decimalFormat.format(cm.getPorcentajeRetencionContadorPorMoneda().get(i)) + "%");
            md.addRow(fila);
        }
        tablaMonedas.setModel(md);
    }
    
    private ConcentradoMonedas crearCM(){
        ConcentradoMonedas cm = new ConcentradoMonedas();
        cm.setCantMaquinasPorMoneda(cantMaquinasPorMoneda);
        cm.setPorcMaquinasPorMoneda(porcMaquinasPorMoneda);
        cm.setCantCortePorMoneda(cantCortePorMoneda);
        cm.setPorcCortePorMoneda(porcCortePorMoneda);
        cm.setPorcentajeRetencionPantallaPorMoneda(porcentajeRetencionPantallaPorMoneda);
        cm.setPorcentajeRetencionContadorPorMoneda(porcentajeRetencionContadorPorMoneda);
        return cm;
    }
    //FILTRADO
    private void filtrar(){
        //inicializar las listas
        this.inicializarListas();
        //filtrar la lista de cortes con los que se va a calcular
        try{            
            Date fechaInicial=dateFormat.parse(dateFormat.format(jDateInicial.getDate()));            
            Date fechaFinal=dateFormat.parse(dateFormat.format(jDateFinal.getDate()));            
            String fecha;
            int k=0;
            while(k < cortesFiltrados.size()){
                fecha=cortesFiltrados.elementAt(k).getFecha();
                Date fechaCorte = dateFormat.parse(fecha);
                if(fechaCorte.before(fechaInicial) || fechaCorte.after(fechaFinal)){
                   cortesFiltrados.removeElementAt(k); 
                }else{
                    k++;
                }
            }        
        }catch(NullPointerException e){
//            JOptionPane.showMessageDialog(rootPane, "Referencia nula al filtrar por fechas", "ERROR", WIDTH);
        }catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al convertir fechas al filtrar", "ERROR", WIDTH);
            ex.printStackTrace();
        }
        //llenar con listas de valores
        this.llenarListasDeValores();        
        //calcular los valores de la listas
        this.calcularValoresSucursales();
        //calcular el historico
        historicosPorFiltrados = this.calcularCorteHistorico();
        //llenar las tablas y campos                
        ConcentradoSucursales cs = this.crearCS();
        this.llenarTablaSucursales(cs); //enpaquetar las listas en el objeto
        
        txtCorteGeneral.setText(String.valueOf(corteTotalGeneral));
    }
        
    //GRAFICAR
    private void graficar(){
        AreaDeGrafica ag = new AreaDeGrafica(sucursales, fechasDeInicioDeSemana, matrizCortesPorFecha);
        ag.setLocationRelativeTo(this);
        ag.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jDateInicial = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jDateFinal = new com.toedter.calendar.JDateChooser();
        btnFiltrar = new javax.swing.JButton();
        btnGraficar = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        splitpane = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaSucursales = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCorteGeneral = new javax.swing.JTextField();
        txtCortePorMaquina = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaTarjetas = new javax.swing.JTable(){
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaPerifericos = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaMonedas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Estadisticas de Sucursales");

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Fecha Inicial:");

        jLabel2.setText("Fecha Final:");

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/filtrarFinal.png"))); // NOI18N
        btnFiltrar.setText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        btnGraficar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/grafica_icono.png"))); // NOI18N
        btnGraficar.setText("Mostrar Grafica");
        btnGraficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGraficar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateInicial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateFinal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGraficar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(6, 6, 6))
        );

        splitpane.setDividerLocation(805);
        splitpane.setResizeWeight(0.5);
        splitpane.setToolTipText("");

        tablaSucursales.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaSucursales.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaSucursales.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaSucursalesMouseClicked(evt);
            }
        });
        tablaSucursales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaSucursalesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablaSucursales);

        splitpane.setLeftComponent(jScrollPane1);

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Resultados"));

        jLabel3.setText("Total Corte General:");

        txtCorteGeneral.setEditable(false);

        txtCortePorMaquina.setEditable(false);

        jLabel7.setText("Corte Promedio p/Maquina:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtCortePorMaquina, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCorteGeneral, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCorteGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCortePorMaquina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addContainerGap())
        );

        tablaTarjetas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tarjeta", "#. Maquinas", "%. Maquinas", "$ de Corte", "% de Corte", "Retención P - C"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaTarjetas.setToolTipText("");
        jScrollPane2.setViewportView(tablaTarjetas);

        tablaPerifericos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Periferico", "#. Maquinas", "%. Maquinas", "$ de Corte", "% de Corte", "Retención P - C"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tablaPerifericos);

        tablaMonedas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Moneda", "#. Maquinas", "%. Maquinas", "$ de Corte", "% de Corte", "Retención P - C"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tablaMonedas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
            .addComponent(jScrollPane2)
            .addComponent(jScrollPane4)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        splitpane.setRightComponent(jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(splitpane, javax.swing.GroupLayout.DEFAULT_SIZE, 1180, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(splitpane)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //EVENTOS =>
    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed
       this.filtrar();
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void tablaSucursalesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaSucursalesMouseClicked
        //obtener los tipos de tarjetas y sacar la cantidad de cada una y porcentaje que representa
        //tambien cuanto vendio esa maquina y obtener su porcentaje de venta
        int index = tablaSucursales.getSelectedRow();
        if ((index != -1) && (index != tablaSucursales.getRowCount() - 1)) {
            String sucursal = sucursales.get(index);
            ArrayList<Corte> cortesEnSucursal = this.cortesEnSucursal(sucursal);
            
            new procesoTarjeta(cortesEnSucursal).run();
            //calcularValoresTarjetas(cortesEnSucursal);
            new procesoPeriferico(cortesEnSucursal).run();
            //calcularValoresPerifericos(cortesEnSucursal);
            new procesoMoneda(cortesEnSucursal).run();
            //calcularValoresMonedas(cortesEnSucursal);
        }
    }//GEN-LAST:event_tablaSucursalesMouseClicked

    private void tablaSucursalesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaSucursalesKeyReleased
        if (evt.getKeyCode() == 38 || evt.getKeyCode()== 40) { //tecla nav arriba o abajo
            int index = tablaSucursales.getSelectedRow();
            if ((index != -1) && (index != tablaSucursales.getRowCount() - 1)) {
                String sucursal = sucursales.get(index);
                ArrayList<Corte> cortesEnSucursal = this.cortesEnSucursal(sucursal);
                
                new procesoTarjeta(cortesEnSucursal).run();
                //calcularValoresTarjetas(cortesEnSucursal);
                new procesoPeriferico(cortesEnSucursal).run();
                //calcularValoresPerifericos(cortesEnSucursal);
                new procesoMoneda(cortesEnSucursal).run();
                //calcularValoresMonedas(cortesEnSucursal);
            }
        } 
    }//GEN-LAST:event_tablaSucursalesKeyReleased

    private void btnGraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficarActionPerformed
        this.graficar();
    }//GEN-LAST:event_btnGraficarActionPerformed


    private class procesoTarjeta implements Runnable{

        ArrayList<Corte> cortesEnSucursal;
        
        procesoTarjeta(ArrayList<Corte> cortesEnSucursal){
            this.cortesEnSucursal = cortesEnSucursal;                   
        }
                
        public void run() {
            calcularValoresTarjetas(cortesEnSucursal);
        }        
    }
    
    private class procesoPeriferico implements Runnable{

        ArrayList<Corte> cortesEnSucursal;
        
        procesoPeriferico(ArrayList<Corte> cortesEnSucursal){
            this.cortesEnSucursal = cortesEnSucursal;                   
        }
                
        public void run() {
            calcularValoresPerifericos(cortesEnSucursal);
        }        
    }
    
    private class procesoMoneda implements Runnable{

        ArrayList<Corte> cortesEnSucursal;
        
        procesoMoneda(ArrayList<Corte> cortesEnSucursal){
            this.cortesEnSucursal = cortesEnSucursal;                   
        }
                
        public void run() {
            calcularValoresMonedas(cortesEnSucursal);
        }        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnGraficar;
    private com.toedter.calendar.JDateChooser jDateFinal;
    private com.toedter.calendar.JDateChooser jDateInicial;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSplitPane splitpane;
    private javax.swing.JTable tablaMonedas;
    private javax.swing.JTable tablaPerifericos;
    private javax.swing.JTable tablaSucursales;
    private javax.swing.JTable tablaTarjetas;
    private javax.swing.JTextField txtCorteGeneral;
    private javax.swing.JTextField txtCortePorMaquina;
    // End of variables declaration//GEN-END:variables

    
}
