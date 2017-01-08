package Formato;

import Productos.Producto;
import static Utilerias.Utileria.quitaEspacios;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JOptionPane;

public class Formato {

    String fecha, sucursal, encargado, nota, entregador;
    char turno;
    int turnos;
    Vector<Integer> invI, compras, invF;
    Vector<Producto> productos;
    boolean firma, pagado;
    float retiro, sobrante = 0, sobranteUtilidad = 0, faltanteUtilidad = 0, faltanteComision = 0, fondoSobrante = 0, faltante = 0, fondoInicial = 0, fondoFinal = 0, fondoFaltante = 0, sobranteComision = 0;

    public Formato(String fecha, String sucursal, String encargado, char turno, Vector<Producto> productos, Vector<Integer> invI, Vector<Integer> compras, Vector<Integer> invF, boolean firma) {
        this.fecha = fecha;
        this.sucursal = sucursal;
        this.encargado = encargado;
        this.turno = turno;
        //copia de vectores
        this.invI = invI;
        this.invF = invF;
        this.compras = compras;
        this.productos = productos;
        this.firma = firma;
        nota = "null";
        entregador = "No_Registrado";
    }
    //gets()

    public String getEntregador() {
        return entregador;
    }

    public boolean isFirma() {
        return firma;
    }

    public Vector<String> NombresProductos() {
        Vector<String> nombres = new Vector<String>();
        for (int i = 0; i < productos.size(); i++) {
            nombres.add(productos.elementAt(i).getNombre());
        }
        return nombres;
    }

    public String getFecha() {
        return fecha;
    }

    public boolean getPagado() {
        return pagado;
    }

    public String getSucursal() {
        return sucursal;
    }

    public String getEncargado() {
        return encargado;
    }

    public char getTurno() {
        return turno;
    }

    public Vector<Integer> getInvI() {
        return invI;
    }

    public Vector<Integer> getCompras() {
        return compras;
    }

    public Vector<Integer> getInvF() {
        return invF;
    }

    public boolean getFirma() {
        return firma;
    }

    public float getRetiro() {
        return retiro;
    }

    public int getTurnos() {
        return turnos;
    }

    public float getFaltante() {
        return faltante;
    }

    public float getFondoInicial() {
        return fondoInicial;
    }

    public float getFondoFinal() {
        return fondoFinal;
    }

    public String getNota() {
        return nota;
    }

    public float getFondoSobrante() {
        return fondoSobrante;
    }

    public float getSobrante() {
        return sobrante;
    }

    public Vector<Float> getCostos() {
        Vector<Float> costos = new Vector<Float>();
        for (int i = 0; i < productos.size(); i++) {
            costos.add(productos.elementAt(i).getCosto());
        }
        return costos;
    }

    public Vector<Float> getPrecios() {
        Vector<Float> precios = new Vector<Float>();
        for (int i = 0; i < productos.size(); i++) {
            precios.add(productos.elementAt(i).getPrecio());
        }
        return precios;
    }

    public Vector<Float> getComisiones() {
        Vector<Float> comisiones = new Vector<Float>();
        for (int i = 0; i < productos.size(); i++) {
            comisiones.add(productos.elementAt(i).getComision());
        }
        return comisiones;
    }

    public float getComision() {
        float comision = 0;
        for (int k = 0; k < productos.size(); k++) {
            comision += ((invI.elementAt(k) + compras.elementAt(k) - invF.elementAt(k)) * productos.elementAt(k).getPrecio()) * (productos.elementAt(k).getComision() / 100);
        }
        return comision;
    }

    public float getSobranteUtilidad() {
        return sobranteUtilidad;
    }

    public float getSobranteComision() {
        return sobranteComision;
    }

    public Vector<Producto> getProductos() {
        return productos;
    }

    public boolean isPagado() {
        return pagado;
    }

    public float getFaltanteUtilidad() {
        return faltanteUtilidad;
    }

    public float getFaltanteComision() {
        return faltanteComision;
    }

    public float getFondoFaltante() {
        return fondoFaltante;
    }
    //sets()

    public void setNota(String nota) {
        this.nota = nota;
    }

    public void setEntregador(String entregador) {
        this.entregador = entregador;
    }

    public void setProductos(Vector<Producto> productos) {
        this.productos = productos;
    }

    public void setFondoSobrante(float fondoSobrante) {
        this.fondoSobrante = fondoSobrante;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public void setEncargado(String encargado) {
        this.encargado = encargado;
    }

    public void setTurno(char turno) {
        this.turno = turno;
    }

    public void setInvI(Vector<Integer> invI) {
        this.invI = invI;
    }

    public void setCompras(Vector<Integer> compras) {
        this.compras = compras;
    }

    public void setInvF(Vector<Integer> invF) {
        this.invF = invF;
    }

    public void setFirma(boolean firma) {
        this.firma = firma;
    }

    public void setRetiro(float retiro) {
        this.retiro = retiro;
    }

    public void setFondoFaltante(float f) {
        fondoFaltante += f;
    }

    public void setSobranteFondo(float f) {
        fondoSobrante += f;
    }

    public void setRetiro(Float r) {
        retiro = r;
    }

    public void setFondoInicial(float f) {
        fondoInicial = f;
    }

    public void setFondoFinal(float f) {
        fondoFinal = f;
    }

    public void setTurnos(int t) {
        turnos = t;
    }

    public void setFaltante(float f) {
        faltante += f;
    }

    public void setSobrante(float f) {
        sobrante += f;
    }

    public void setPagado(boolean b) {
        pagado = b;
    }

    public void setFaltanteUtilidad(float faltanteUtilidad) {
        this.faltanteUtilidad = faltanteUtilidad;
    }

    public void setFaltanteComision(float faltanteComision) {
        this.faltanteComision = faltanteComision;
    }

    public void setSobranteUtilidad(float f) {
        sobranteUtilidad = f;
    }

    public void setSobranteComision(float f) {
        sobranteComision = f;
    }

    public float faltante(Formato f) {
        float sumafaltante = 0;
        for (int k = 0; k < this.productos.size(); k++) { //para cada producto del formato
            for (int i = 0; i < f.productos.size(); i++) { //revisar con cada producto del otro formato
                if (productos.elementAt(k).compareTo(f.productos.elementAt(i)) == 0) { //si estamos en el mismo producto
                    if (invF.elementAt(k) - f.invI.elementAt(i) > 0) {
                        sumafaltante += productos.elementAt(k).getPrecio() * (invF.elementAt(k) - f.invI.elementAt(i));
                    }
                }
            }
        }
        return sumafaltante;
    }

    public float sobrante(Formato f) {
        float sumasobrante = 0;
        for (int k = 0; k < productos.size(); k++) {
            for (int i = 0; i < f.productos.size(); i++) {
                if (productos.elementAt(k).compareTo(f.productos.elementAt(i)) == 0) {//si son el mismo producto
                    if (invF.elementAt(k) - f.invI.elementAt(i) <= 0) {
                        sumasobrante += Math.abs(productos.elementAt(k).getPrecio() * (invF.elementAt(k) - f.invI.elementAt(i)));
                    }
                }
            }
        }
        return sumasobrante;
    }

    public float sobranteUtilidad(Formato f) {
        float sumasobranteUtilidad = 0;
        for (int i = 0; i < productos.size(); i++) {
            for (int k = 0; k < f.productos.size(); k++) {
                if (productos.elementAt(i).compareTo(f.productos.elementAt(k)) == 0) {
                    if (invF.elementAt(i) - f.invI.elementAt(k) < 0) {
                        int cantidad = invF.elementAt(i) - f.invI.elementAt(k);
                        sumasobranteUtilidad += Math.abs((cantidad * (productos.elementAt(i).utilidad())));
                    }
                }
            }
        }
        return sumasobranteUtilidad;
    }

    public float sobranteComision(Formato f) {
        float sumasobranteComision = 0;
        for (int i = 0; i < productos.size(); i++) {
            for (int k = 0; k < f.productos.size(); k++) {
                if (productos.elementAt(i).compareTo(f.productos.elementAt(k)) == 0) {
                    if (invF.elementAt(i) - f.invI.elementAt(k) <= 0) {
                        sumasobranteComision += Math.abs((invF.elementAt(i) - f.invI.elementAt(k)) * productos.elementAt(i).comision());
                    }
                }
            }
        }
        return sumasobranteComision;
    }

    public float faltanteUtilidad(Formato f) {
        float sumaFaltanteUtilidad = 0;
        for (int i = 0; i < productos.size(); i++) {
            for (int k = 0; k < f.productos.size(); k++) {
                if (productos.elementAt(i).compareTo(f.productos.elementAt(k)) == 0) {
                    if (invF.elementAt(i) - f.invI.elementAt(k) > 0) {
                        int cantidad = invF.elementAt(i) - f.invI.elementAt(k);
                        sumaFaltanteUtilidad += Math.abs((cantidad * (productos.elementAt(i).utilidad())));
                    }
                }
            }
        }
        return sumaFaltanteUtilidad;
    }

    public float faltanteComision(Formato f) {
        float sumaFaltanteComision = 0;
        for (int i = 0; i < productos.size(); i++) {
            for (int k = 0; k < f.productos.size(); k++) {
                if (productos.elementAt(i).compareTo(f.productos.elementAt(k)) == 0) {
                    if (invF.elementAt(i) - f.invI.elementAt(k) > 0) {
                        sumaFaltanteComision += Math.abs((invF.elementAt(i) - f.invI.elementAt(k)) * productos.elementAt(i).comision());
                    }
                }
            }
        }
        return sumaFaltanteComision;
    }

    public Vector<Integer> posFaltantesPosteriores(Formato f) {
        Vector<Integer> pos = new Vector<Integer>();
        for (int i = 0; i < productos.size(); i++) {
            for (int k = 0; k < f.productos.size(); k++) {
                if (productos.elementAt(i).compareTo(f.productos.elementAt(k)) == 0) {
                    if (invF.elementAt(i) - f.invI.elementAt(k) > 0) {
                        pos.add(i);
                    }
                }
            }
        }
        return pos;
    }

    public Vector<Integer> posFaltantesAnteriores(Formato f) {
        Vector<Integer> pos = new Vector<Integer>();
        for (int i = 0; i < productos.size(); i++) {
            for (int j = 0; j < f.productos.size(); j++) {
                if (productos.elementAt(i).compareTo(f.productos.elementAt(j)) == 0) {
                    if (invF.elementAt(i) - f.invI.elementAt(j) > 0) {
                        pos.add(j);
                    }
                }
            }
        }
        return pos;
    }

    public Vector<Integer> posSobrantes(Formato f) {
        Vector<Integer> pos = new Vector<Integer>();
        for (int i = 0; i < productos.size(); i++) {

            for (int k = 0; k < f.productos.size(); k++) {
                if (productos.elementAt(i).compareTo(f.productos.elementAt(k)) == 0) {
                    if (invF.elementAt(i) - f.invI.elementAt(k) < 0) {
                        pos.add(i);
                    }
                }
            }
        }
        return pos;
    }

    public Vector<Integer> cantSobrantes(Formato f) {
        Vector<Integer> cant = new Vector<Integer>();
        for (int i = 0; i < invF.size(); i++) {
            for (int k = 0; k < f.productos.size(); k++) {
                if (productos.elementAt(i).compareTo(f.productos.elementAt(k)) == 0) {
                    if (invF.elementAt(i) - f.invI.elementAt(k) < 0) {
                        cant.add(Math.abs(invF.elementAt(i) - f.invI.elementAt(k)));
                    }
                }
            }
        }
        return cant;
    }

    public Vector<Integer> cantFaltantes(Formato f) {
        Vector<Integer> cant = new Vector<Integer>();
        for (int i = 0; i < invF.size(); i++) {
            for (int k = 0; k < f.productos.size(); k++) {
                if (productos.elementAt(i).compareTo(f.productos.elementAt(k)) == 0) {
                    if (invF.elementAt(i) - f.invI.elementAt(k) > 0) {
                        cant.add(Math.abs(invF.elementAt(i) - f.invI.elementAt(k)));
                    }
                }
            }
        }
        return cant;
    }

    public float faltanteFondo(Formato f) {
        float faltante = 0;
        if (fondoFinal - f.fondoInicial > 0) {
            faltante = fondoFinal - f.fondoInicial;
        }
        return faltante;
    }

    public float sobranteFondo(Formato f) {
        float sobrante = 0;
        if (fondoFinal - f.fondoInicial < 0) {
            sobrante = Math.abs(fondoFinal - f.fondoInicial);
        }
        return sobrante;
    }

    public float totalVenta() {
        float total = 0;
        for (int k = 0; k < productos.size(); k++) {
            total += productos.elementAt(k).getPrecio() * ((invI.elementAt(k) + compras.elementAt(k)) - invF.elementAt(k));
        }
        return total;
    }

    public float ganancia() {
        float ganancia = 0;
        for (int k = 0; k < productos.size(); k++) {
            ganancia += (productos.elementAt(k).utilidad()) * ((invI.elementAt(k) + compras.elementAt(k)) - invF.elementAt(k));
        }
        return ganancia;
    }

    public float obtenerTotalFinal() {
        return this.totalVenta() - this.getSobrante() - this.getFondoFinal() - this.getFondoSobrante() + this.getFondoInicial() + this.getFaltante() + this.getFondoFaltante() - this.getRetiro() - 200;
    }

    public Vector<Integer> cantVentas() {
        Vector<Integer> cantVentas = new Vector<Integer>();
        for (int i = 0; i < productos.size(); i++) {
            cantVentas.add(invI.elementAt(i) + compras.elementAt(i) - invF.elementAt(i));
        }
        return cantVentas;
    }

    public static Formato copy(Formato f) {
        Formato copia = new Formato(f.getFecha(),
                f.getSucursal(),
                f.getEncargado(),
                f.getTurno(),
                f.getProductos(),
                f.getInvI(),
                f.getCompras(),
                f.getInvF(),
                f.getFirma()
        );
        copia.setRetiro(f.getRetiro());
        copia.setPagado(f.getPagado());
        copia.setTurnos(f.getTurnos());
        copia.setNota(f.getNota());
        copia.setEntregador(f.getEntregador());

        return copia;
    }

    public static Vector<Formato> cargarFormatos() {
        Vector<Formato> formatos = new Vector<Formato>();
        try {
            FileReader fr = new FileReader("Archivos/Formatos.bin");
            BufferedReader br = new BufferedReader(fr);
            StringTokenizer token;
            String linea;
            //llenar el vector de formatos con los formatos almacenados en el archivo
            Vector<Producto> productos;
            Vector<Integer> invI;
            Vector<Integer> invF;
            Vector<Integer> compras;
            while ((linea = br.readLine()) != null) {
                Formato miFormato;
                token = new StringTokenizer(linea);
                String fe = token.nextToken();
                String s = token.nextToken();
                String e = token.nextToken();
                char t = token.nextToken().charAt(0);
                productos = new Vector<>();
                invI = new Vector<>();
                invF = new Vector<>();
                compras = new Vector<>();
                String seg = token.nextToken();
                while (true) {
                    String nombreP = seg;
                    float precio = Float.parseFloat(token.nextToken());
                    float costo = Float.parseFloat(token.nextToken());
                    float comision = Float.parseFloat(token.nextToken());
                    int pres = Integer.parseInt(token.nextToken());
                    productos.add(new Producto(nombreP, precio, costo, comision, pres));
                    invI.add(Integer.parseInt(token.nextToken()));
                    compras.add(Integer.parseInt(token.nextToken()));
                    invF.add(Integer.parseInt(token.nextToken()));
                    seg = token.nextToken();
                    if (seg.equals("true") || seg.equals("false")) {
                        break;
                    }
                }
                boolean firmado = Boolean.parseBoolean(seg);
                float retiro = Float.parseFloat(token.nextToken());
                int turnos = Integer.parseInt(token.nextToken());
                boolean pagado = Boolean.parseBoolean(token.nextToken());
                float fondoI = Float.parseFloat(token.nextToken());
                float fondoF = Float.parseFloat(token.nextToken());
                String nota = token.nextToken();
                String entregador = token.nextToken();
                miFormato = new Formato(fe, s, e, t, productos, invI, compras, invF, firmado);
                miFormato.setRetiro(retiro);
                miFormato.setTurnos(turnos);
                miFormato.setPagado(pagado);
                miFormato.setFondoFinal(fondoF);
                miFormato.setFondoInicial(fondoI);
                miFormato.setNota(nota);
                miFormato.setEntregador(entregador);
                formatos.add(miFormato);
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error de IO en Formatos.bin", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return formatos;
    }

    public static void ordenarFormatosQ(Vector<Formato> formatos, int izq, int der) {
        int i = izq;
        int j = der;
        Formato x = formatos.elementAt((izq + der) / 2);
        Formato aux;
        do {
            while ((formatos.elementAt(i).compareTo(x) < 0) && (j <= der)) {
                i++;
            }
            while ((formatos.elementAt(j).compareTo(x) > 0) && (j > izq)) {
                j--;
            }
            if (i <= j) {
                aux = Formato.copy(formatos.elementAt(i));
                formatos.setElementAt(formatos.elementAt(j), i);
                formatos.setElementAt(aux, j);
                i++;
                j--;
            }
        } while (i <= j);
        if (izq < j) {
            ordenarFormatosQ(formatos, izq, j);
        }
        if (i < der) {
            ordenarFormatosQ(formatos, i, der);
        }
    }

    public static Vector<Formato> ordenarFormatosSucursal(Vector<Formato> formatos, Vector<String> sucursales) {
        //Ordenar los formatos, primero sucursaly luego turno
        Vector<Formato> formatosFiltrados = new Vector<>();
        for (int k = 0; k < sucursales.size(); k++) {
            //Ordenar la sucursal            
            for (int f = 0; f < formatos.size(); f++) {
                if (formatos.elementAt(f).getSucursal().equals(sucursales.elementAt(k))) {
                    formatosFiltrados.add(formatos.elementAt(f));
                    formatos.remove(f);
                    f--;
                }
            }
        }
        return formatosFiltrados;
    }

    public static void ordenarFormatosTurno(Vector<Formato> formatos) {
        for (int j = 0; j < formatos.size() - 1; j++) {
            if (formatos.elementAt(j).getFecha().equals(formatos.elementAt(j + 1).getFecha())) {
                if (formatos.elementAt(j).getTurno() == 'N') {
                    Formato fAux = Formato.copy(formatos.elementAt(j));
                    formatos.setElementAt(formatos.elementAt(j + 1), j);
                    formatos.setElementAt(fAux, j + 1);
                }
            }
        }
    }

    //toString()
    @Override
    public String toString() {
//        System.out.println(fecha+" "+quitaEspacios(sucursal)+" "+quitaEspacios(encargado)+" "+turno);
        String vector = "";
//        System.out.println(productos.toString());
//        System.out.println(invI.size());
        for (int i = 0; i < productos.size(); i++) {

            vector += (quitaEspacios(productos.elementAt(i).getNombre())
                    + " " + productos.elementAt(i).getPrecio()
                    + " " + productos.elementAt(i).getCosto()
                    + " " + productos.elementAt(i).getComision()
                    + " " + productos.elementAt(i).getPresentacion()
                    + " " + invI.elementAt(i)
                    + " " + compras.elementAt(i)
                    + " " + invF.elementAt(i) + " ");
        }
        return fecha + " " + quitaEspacios(sucursal) + " " + quitaEspacios(encargado) + " " + turno + " " + vector + firma + " " + retiro + " " + turnos + " " + pagado + " " + fondoInicial + " " + fondoFinal + " " + nota + " " + entregador;
    }

    public int compareTo(Formato f) {
        int res = 0;
        SimpleDateFormat df = new SimpleDateFormat("d/MM/yyyy");
        try {
            if (df.parse(this.getFecha()).after(df.parse(f.getFecha()))) {
                res = 1;
            } else if (df.parse(this.getFecha()).before(df.parse(f.getFecha()))) {
                res = -1;
            }
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
        return res;
    }

}
