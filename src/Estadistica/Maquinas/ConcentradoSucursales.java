
package Estadistica.Maquinas;

import java.util.ArrayList;

public class ConcentradoSucursales {
    
    private ArrayList<String> sucursales;
    private ArrayList<Integer> comisiones;
    //lista de la cantidad de maquinas correspondiente a sucursal
    private ArrayList<Integer> numMaquinas;        
    //lista del corte promedio que tiene cada maquina en una sucursal
    private ArrayList<Float> cortesPromedioPorMaquina;
    //lista con la suma del corte por sucursal
    private ArrayList<Float> cortesTotalesPorSucursal;
    //lista con el porcentaje de corte al que corresponde cada sucursal
    private ArrayList<Float> porcentajesDeCorteTotalPorSucursal;
    //lista de cortes promedio por dia de la sucursal
    private ArrayList<Float> cortesPromedioPorDia;    
    //lista del corte promedio que tiene cada sucursal al dia
    private ArrayList<Float> promediosSemanalesDeCorte;
    //matriz de cortes por fecha    
    private ArrayList<ArrayList<Float>> matrizCortesPorFecha;
    //Lista para almacenar el promedio de cortes de una sucursal desde el origen de los tiempos
    private ArrayList<Float> historicosGenerales;
    //Lista para almacenar el el promedio de cortes de la sucursal solo del tango filtrado
    private ArrayList<Float> historicosPorFiltrados;
    //lstas de porcentajes de retencion   
    private ArrayList<Float> porcentajeRetencionContadorPorSucursal;
    private ArrayList<Float> porcentajeRetencionPantallaPorSucursal;
    
    public ArrayList<String> getSucursales() {
        return sucursales;
    }

    public void setSucursales(ArrayList<String> sucursales) {
        this.sucursales = sucursales;
    }

    public ArrayList<Integer> getNumMaquinas() {
        return numMaquinas;
    }

    public void setNumMaquinas(ArrayList<Integer> numMaquinas) {
        this.numMaquinas = numMaquinas;
    }

    public ArrayList<Float> getCortesPromedioPorMaquina() {
        return cortesPromedioPorMaquina;
    }

    public void setCortesPromedioPorMaquina(ArrayList<Float> cortesPromedioPorMaquina) {
        this.cortesPromedioPorMaquina = cortesPromedioPorMaquina;
    }

    public ArrayList<Float> getCortesTotalesPorSucursal() {
        return cortesTotalesPorSucursal;
    }

    public void setCortesTotalesPorSucursal(ArrayList<Float> corteTotalPorSucursal) {
        this.cortesTotalesPorSucursal = corteTotalPorSucursal;
    }

    public ArrayList<Float> getPorcentajesDeCorteTotalPorSucursal() {
        return porcentajesDeCorteTotalPorSucursal;
    }

    public void setPorcentajesDeCorteTotalPorSucursal(ArrayList<Float> porcentajeCorteTotalPorSucursal) {
        this.porcentajesDeCorteTotalPorSucursal = porcentajeCorteTotalPorSucursal;
    }

    public ArrayList<Float> getCortesPromedioPorDia() {
        return cortesPromedioPorDia;
    }

    public void setCortesPromedioPorDia(ArrayList<Float> cortesPromedioPorDia) {
        this.cortesPromedioPorDia = cortesPromedioPorDia;
    }

    public ArrayList<ArrayList<Float>> getMatrizCortesPorFecha() {
        return matrizCortesPorFecha;
    }

    public void setMatrizCortesPorFecha(ArrayList<ArrayList<Float>> matrizCortesPorFecha) {
        this.matrizCortesPorFecha = matrizCortesPorFecha;
    }   

    public ArrayList<Float> getPromediosSemanalesDeCorte() {
        return promediosSemanalesDeCorte;
    }

    public void setPromediosSemanalesDeCorte(ArrayList<Float> promediosSemanalesDeCorte) {
        this.promediosSemanalesDeCorte = promediosSemanalesDeCorte;
    }        

    public ArrayList<Float> getHistoricosGenerales() {
        return historicosGenerales;
    }

    public void setHistoricosGenerales(ArrayList<Float> historicosGenerales) {
        this.historicosGenerales = historicosGenerales;
    }

    public ArrayList<Float> getHistoricosPorFiltrados() {
        return historicosPorFiltrados;
    }

    public void setHistoricosPorFiltrados(ArrayList<Float> historicosPorFiltrados) {
        this.historicosPorFiltrados = historicosPorFiltrados;
    }

    public ArrayList<Float> getPorcentajeRetencionContadorPorSucursal() {
        return porcentajeRetencionContadorPorSucursal;
    }

    public void setPorcentajeRetencionContadorPorSucursal(ArrayList<Float> porcentajeRetencionContadorPorSucursal) {
        this.porcentajeRetencionContadorPorSucursal = porcentajeRetencionContadorPorSucursal;
    }

    public ArrayList<Float> getPorcentajeRetencionPantallaPorSucursal() {
        return porcentajeRetencionPantallaPorSucursal;
    }

    public void setPorcentajeRetencionPantallaPorSucursal(ArrayList<Float> porcentajeRetencionPantallaPorSucursal) {
        this.porcentajeRetencionPantallaPorSucursal = porcentajeRetencionPantallaPorSucursal;
    }

    public ArrayList<Integer> getComisiones() {
        return comisiones;
    }

    public void setComisiones(ArrayList<Integer> comisiones) {
        this.comisiones = comisiones;
    }
        
}
