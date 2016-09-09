
package Estadistica.Maquinas;

import java.util.ArrayList;

public class ConcentradoTarjetas {
    //lista con el conteno de maquinas de la sucursal seleccionada segun su tarjeta
    private ArrayList<Integer> cantMaquinasPorTarjeta;
    //lista del porcentaje que representan segun la tarjeta
    private ArrayList<Float> porcMaquinasPorTarjeta;
    //lista del porcentaje de corte que representan segun su tarjeta
    private ArrayList<Float> porcCortePorTarjeta;
    //lista de la cantidad de corte que tienen las maquinas segun su tarjeta 
    private ArrayList<Float> cantCortePorTarjeta;
    //lista con los nombres de las tarjetsa que existen en la sucursal
    private ArrayList<String> nomTarjetas;
    //lista de historicos
    private ArrayList<Float> historicosGeneralesPorTarjeta;
    //lista de historicos por filtrado
    private ArrayList<Float> historicosPorFiltradosPorTarjeta;
    //listas de retencion
    private ArrayList<Float> porcentajeRetencionContadorPorTarjeta;
    private ArrayList<Float> porcentajeRetencionPantallaPorTarjeta;
    
    public ArrayList<Integer> getCantMaquinasPorTarjeta() {
        return cantMaquinasPorTarjeta;
    }

    public void setCantMaquinasPorTarjeta(ArrayList<Integer> cantMaquinasPorTarjeta) {
        this.cantMaquinasPorTarjeta = cantMaquinasPorTarjeta;
    }

    public ArrayList<Float> getPorcMaquinasPorTarjeta() {
        return porcMaquinasPorTarjeta;
    }

    public void setPorcMaquinasPorTarjeta(ArrayList<Float> porcMaquinasPorTarjeta) {
        this.porcMaquinasPorTarjeta = porcMaquinasPorTarjeta;
    }

    public ArrayList<Float> getPorcCortePorTarjeta() {
        return porcCortePorTarjeta;
    }

    public void setPorcCortePorTarjeta(ArrayList<Float> porcCortePorTarjeta) {
        this.porcCortePorTarjeta = porcCortePorTarjeta;
    }

    public ArrayList<Float> getCantCortePorTarjeta() {
        return cantCortePorTarjeta;
    }

    public void setCantCortePorTarjeta(ArrayList<Float> cantCortePorTarjeta) {
        this.cantCortePorTarjeta = cantCortePorTarjeta;
    }

    public ArrayList<String> getNomTarjetas() {
        return nomTarjetas;
    }

    public void setNomTarjetas(ArrayList<String> nomTarjetas) {
        this.nomTarjetas = nomTarjetas;
    }    

    public ArrayList<Float> getHistoricosGeneralesPorTarjeta() {
        return historicosGeneralesPorTarjeta;
    }

    public void setHistoricosGeneralesPorTarjeta(ArrayList<Float> historicosGeneralesPorTarjeta) {
        this.historicosGeneralesPorTarjeta = historicosGeneralesPorTarjeta;
    }

    public ArrayList<Float> getHistoricosPorFiltradosPorTarjeta() {
        return historicosPorFiltradosPorTarjeta;
    }

    public void setHistoricosPorFiltradosPorTarjeta(ArrayList<Float> historicosPorFiltradosPorTarjeta) {
        this.historicosPorFiltradosPorTarjeta = historicosPorFiltradosPorTarjeta;
    }

    public ArrayList<Float> getPorcentajeRetencionContadorPorTarjeta() {
        return porcentajeRetencionContadorPorTarjeta;
    }

    public void setPorcentajeRetencionContadorPorTarjeta(ArrayList<Float> porcentajeRetencionContadorPorTarjeta) {
        this.porcentajeRetencionContadorPorTarjeta = porcentajeRetencionContadorPorTarjeta;
    }

    public ArrayList<Float> getPorcentajeRetencionPantallaPorTarjeta() {
        return porcentajeRetencionPantallaPorTarjeta;
    }

    public void setPorcentajeRetencionPantallaPorTarjeta(ArrayList<Float> porcentajeRetencionPantallaPorTarjeta) {
        this.porcentajeRetencionPantallaPorTarjeta = porcentajeRetencionPantallaPorTarjeta;
    }
    
    
}
