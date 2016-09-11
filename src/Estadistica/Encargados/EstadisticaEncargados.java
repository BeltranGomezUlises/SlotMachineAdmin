
package Estadistica.Encargados;

import Formato.Formato;
import Utilerias.TableHeader.ColumnGroup;
import Utilerias.TableHeader.GroupableTableHeader;
import Utilerias.Utileria;
import static Utilerias.Utileria.quitaGuion;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class EstadisticaEncargados extends JFrame{

    public EstadisticaEncargados() {                
        super( "Groupable Header Example" );
        super.setExtendedState(MAXIMIZED_BOTH);
        super.setSize(800, 400);
        //cargar y llenar listas
        Vector<Formato> formatos = Formato.cargarFormatos();
        
        Vector<String> sucursales = new Vector<>();
        ArrayList<String> fechas = new ArrayList<>();
        for (Formato formato : formatos) {
            if (!sucursales.contains(formato.getSucursal())) {
                sucursales.add(formato.getSucursal());
            }
            if (!fechas.contains(formato.getFecha())) {
                fechas.add(formato.getFecha());
            }
        }        
        
        try {
            Utileria.QSortStringDates(fechas);
        } catch (ParseException ex) {
            JOptionPane.showMessageDialog(null, "Error de ordenamiento", "Error", JOptionPane.ERROR_MESSAGE);
        }
                
        ArrayList<ArrayList<String>> mEncargados = new ArrayList<>();
        //obtener su correspondiente encargado de dia y de noche por fecha y agregarlo a la fila de la sucursal
        ArrayList<Formato> formatosPorSucursal;        
        for (String sucursal : sucursales) {            
            formatosPorSucursal = new ArrayList<>();
            for (int j = 0; j < formatos.size(); j++) {                
                if (formatos.get(j).getSucursal().equals(sucursal)) {
                    formatosPorSucursal.add(formatos.get(j));
                    formatos.remove(j);
                    j--;
                }
            }
            //Acomodar el encargado correspondiente a el dia en turno 
            ArrayList<String> encargadosDeSucursal = new ArrayList<>();
            for (int i = 0; i < (fechas.size() * 2 + 1); i++) { //inicializar todos los espacion con default
                encargadosDeSucursal.add("");
            }
            for (Formato formato : formatosPorSucursal) {
                boolean encontrado = false;
                for (int i = 0; i < fechas.size(); i++) {
                    if (fechas.get(i).equals(formato.getFecha())) {
                        if (formato.getTurno() == 'D') {
                            encargadosDeSucursal.set( ((i + 1) * 2) - 1, formato.getEncargado());
                        }else{
                            encargadosDeSucursal.set( ((i + 1) * 2), formato.getEncargado());
                        }                        
                        break;
                    }
                }                
            }
            encargadosDeSucursal.remove(0); //eliminar la posicion 0 que es la columna que corresponde a sucursal
            /*
            for (int i = 0; i < fechas.size(); i++) {
                System.out.println(fechas.get(i));
                System.out.println("    D: " + encargadosDeSucursal.get((i + 1) * 2 - 1) );
                System.out.println("    N: " + encargadosDeSucursal.get((i + 1) * 2) );                
            }*/
            mEncargados.add(encargadosDeSucursal); //añadir fila a la matriz
        }
        
        //definir el modelo y su encabezado
        DefaultTableModel dm = new DefaultTableModel();
        
        dm.addColumn("Sucursales");
        for (String fecha : fechas) {
            dm.addColumn("D");
            dm.addColumn("N");            
        }
        //teniendo el modelo de la tabla con las columnas de menor nivel crear la tabla para agrupar columnas
        final JTable table = new JTable( dm ) {
            @Override
            protected JTableHeader createDefaultTableHeader() {
               return new GroupableTableHeader(columnModel);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        }; 
        table.setDefaultRenderer(Object.class, new TableRender());
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getColumnModel().getColumn(0).setPreferredWidth(120); //columna de sucursales mas ancha
        
                
        TableColumnModel cm = table.getColumnModel();        
        GroupableTableHeader header = (GroupableTableHeader)table.getTableHeader();
        //header.addColumnGroup(g_name);  
        int indiceTurno = 1;
        for (int i = 0; i < fechas.size(); i++) {
            ColumnGroup gFecha = new ColumnGroup(fechas.get(i));
            gFecha.add(cm.getColumn(indiceTurno));
            indiceTurno++;
            gFecha.add(cm.getColumn(indiceTurno));
            indiceTurno++;
            header.addColumnGroup(gFecha);
        }        
        
        //llenar con el contenido la tabla        
        for (int i = 0; i < mEncargados.size(); i++) {
            Vector fila = new Vector();
            fila.add(quitaGuion(sucursales.get(i)));
            for (int j = 0; j < mEncargados.get(i).size(); j++) {
                fila.add(quitaGuion(mEncargados.get(i).get(j)));
            }
            dm.addRow(fila);
        } 
        //agregar asteriscos a las celdas donde el encargado este doblando turno
        String[][] mAsteriscos = new String[table.getRowCount()][table.getColumnCount()];
        for (int i = 0; i < mAsteriscos.length; i++) { //inicializar
            Arrays.fill(mAsteriscos[i], "");                    
        }
        for (int i = 1; i < table.getColumnCount() - 1; i++) {
            for (int j = 0; j < table.getRowCount(); j++) {
                String valorCeldaActual = String.valueOf(table.getValueAt(j, i));
                if (!valorCeldaActual.isEmpty()) {
                    //preguntar por el turno siguiente
                    for (int k = 0; k < table.getRowCount(); k++) {
                        if (String.valueOf(table.getValueAt(k, i + 1)).equals(valorCeldaActual)) {
                            mAsteriscos[k][i + 1] += "*";
                            //si duplico entonces chear si triplico
                            for (int l = 0; l < table.getRowCount(); l++) {
                                if (String.valueOf(table.getValueAt(l, i + 2)).equals(valorCeldaActual)) {
                                    mAsteriscos[l][i + 2] += "**";
                                    break;
                                }
                            }
                            break;
                        }
                    }                                        
                }                
            }
        }
        //agregar los asteriscos para que se pinten los nombres
        for (int i = 0; i < mAsteriscos.length; i++) {
            for (int j = 0; j < mAsteriscos[i].length; j++) {
                table.setValueAt(table.getValueAt(i, j) + mAsteriscos[i][j], i, j);
            }
        }        
        //ajustar el ancho de la tabla
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(120); 
        }     
        
        JScrollPane scroll = new JScrollPane( table );
        getContentPane().add( scroll );  
        
        super.setVisible(true);
    }
    
    public static void main(String[] args) {
        EstadisticaEncargados ed = new EstadisticaEncargados();        
        ed.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
            System.exit(0);
            }
        });        
    }
}
