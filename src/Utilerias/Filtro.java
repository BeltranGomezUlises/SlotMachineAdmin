
package Utilerias;

public class Filtro {
    
    private String sucursal;
    private String numMaquina;
    private String mes;
    private int año;
        
    private String encargado;
    private String turno;
    private String pagado;
    
    private String fechaIni;
    private String fechaFin;

    public Filtro(String sucursal, String numMaquina, String mes, int año) {
        this.sucursal = sucursal;
        this.numMaquina = numMaquina;
        this.mes = mes;
        this.año = año;
    }

    public Filtro(String sucursal, String encargado, String turno, String pagado) {
        this.sucursal = sucursal;
        this.encargado = encargado;
        this.turno = turno;
        this.pagado = pagado;        
    }
    
    public Filtro(String sucursal, String encargado, String turno, String pagado, String fechaIni, String fechaFin) {
        this.sucursal = sucursal;
        this.encargado = encargado;
        this.turno = turno;
        this.pagado = pagado;        
        this.fechaIni = fechaIni;
        this.fechaFin = fechaFin;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getNumMaquina() {
        return numMaquina;
    }

    public void setNumMaquina(String numMaquina) {
        this.numMaquina = numMaquina;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getEncargado() {
        return encargado;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getPagado() {
        return pagado;
    }

    public void setPagado(String pagado) {
        this.pagado = pagado;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    
}
