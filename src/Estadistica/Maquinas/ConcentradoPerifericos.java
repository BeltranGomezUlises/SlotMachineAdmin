
package Estadistica.Maquinas;

import java.util.ArrayList;


public class ConcentradoPerifericos {
    
    
    //lista con el conteno de maquinas de la sucursal seleccionada segun su tarjeta
    private ArrayList<Integer> cantMaquinasPorPeriferico;
    //lista del porcentaje que representan segun la tarjeta
    private ArrayList<Float> porcMaquinasPorPeriferico;
    //lista del porcentaje de corte que representan segun su tarjeta
    private ArrayList<Float> porcCortePorPeriferico;
    //lista de la cantidad de corte que tienen las maquinas segun su tarjeta 
    private ArrayList<Float> cantCortePorPeriferico;
    //lista con los nombres de las tarjetsa que existen en la sucursal
    private ArrayList<String> nomPerifericos;
    //lista de historicos
    private ArrayList<Float> historicosGeneralesPorPeriferico;
    //lista de historicos por filtrado
    private ArrayList<Float> historicosPorFiltradosPorPeriferico;
    //listas de porcentajes de retencion    
    private ArrayList<Float> porcentajeRetencionContadorPorPeriferico;
    private ArrayList<Float> porcentajeRetencionPantallaPorPeriferico;
    
    public ArrayList<Integer> getCantMaquinasPorPeriferico() {
        return cantMaquinasPorPeriferico;
    }

    public void setCantMaquinasPorPeriferico(ArrayList<Integer> cantMaquinasPorPeriferico) {
        this.cantMaquinasPorPeriferico = cantMaquinasPorPeriferico;
    }

    public ArrayList<Float> getPorcMaquinasPorPeriferico() {
        return porcMaquinasPorPeriferico;
    }

    public void setPorcMaquinasPorPeriferico(ArrayList<Float> porcMaquinasPorPeriferico) {
        this.porcMaquinasPorPeriferico = porcMaquinasPorPeriferico;
    }

    public ArrayList<Float> getPorcCortePorPeriferico() {
        return porcCortePorPeriferico;
    }

    public void setPorcCortePorPeriferico(ArrayList<Float> porcCortePorPeriferico) {
        this.porcCortePorPeriferico = porcCortePorPeriferico;
    }

    public ArrayList<Float> getCantCortePorPeriferico() {
        return cantCortePorPeriferico;
    }

    public void setCantCortePorPeriferico(ArrayList<Float> cantCortePorPeriferico) {
        this.cantCortePorPeriferico = cantCortePorPeriferico;
    }

    public ArrayList<String> getNomPerifericos() {
        return nomPerifericos;
    }

    public void setNomPerifericos(ArrayList<String> nomPerifericos) {
        this.nomPerifericos = nomPerifericos;
    }

    public ArrayList<Float> getHistoricosGeneralesPorPeriferico() {
        return historicosGeneralesPorPeriferico;
    }

    public void setHistoricosGeneralesPorPeriferico(ArrayList<Float> historicosGeneralesPorPeriferico) {
        this.historicosGeneralesPorPeriferico = historicosGeneralesPorPeriferico;
    }

    public ArrayList<Float> getHistoricosPorFiltradosPorPeriferico() {
        return historicosPorFiltradosPorPeriferico;
    }

    public void setHistoricosPorFiltradosPorPeriferico(ArrayList<Float> historicosPorFiltradosPorPeriferico) {
        this.historicosPorFiltradosPorPeriferico = historicosPorFiltradosPorPeriferico;
    }

    public ArrayList<Float> getPorcentajeRetencionContadorPorPeriferico() {
        return porcentajeRetencionContadorPorPeriferico;
    }

    public void setPorcentajeRetencionContadorPorPeriferico(ArrayList<Float> porcentajeRetencionContadorPorPeriferico) {
        this.porcentajeRetencionContadorPorPeriferico = porcentajeRetencionContadorPorPeriferico;
    }

    public ArrayList<Float> getPorcentajeRetencionPantallaPorPeriferico() {
        return porcentajeRetencionPantallaPorPeriferico;
    }

    public void setPorcentajeRetencionPantallaPorPeriferico(ArrayList<Float> porcentajeRetencionPantallaPorPeriferico) {
        this.porcentajeRetencionPantallaPorPeriferico = porcentajeRetencionPantallaPorPeriferico;
    }
    
    
}
