
package Estadistica.Encargados;

import Formato.Formato;
import Utilerias.TableHeader.ColumnGroup;
import Utilerias.TableHeader.GroupableTableHeader;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class EstadisticaEncargados extends JFrame{

    public EstadisticaEncargados() {                
        super( "Groupable Header Example" );
        super.setExtendedState(MAXIMIZED_BOTH);
        super.setSize(600, 400);
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
        Formato.ordenarFormatosQ(formatos, 0, formatos.size() - 1);
        formatos = Formato.ordenarFormatosSucursal(formatos, sucursales);
        Formato.ordenarFormatosTurno(formatos);
        
        //obtener su correspondiente encargado de dia y de noche por fecha y agregarlo a la fila de la sucursal
        
        //definir el modelo y su encabezado
        DefaultTableModel dm = new DefaultTableModel();
        
        dm.addColumn("Sucursales");
        for (String fecha : fechas) {
            dm.addColumn("D");
            dm.addColumn("N");            
        }
        //teniendo el modelo de la tabla con las columnas de menor nivel crear la tabla para agrupar columnas
        JTable table = new JTable( dm ) {
            @Override
            protected JTableHeader createDefaultTableHeader() {
               return new GroupableTableHeader(columnModel);
            }
        };                
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
        
        JScrollPane scroll = new JScrollPane( table );
        getContentPane().add( scroll );           
    }
    
    public static void main(String[] args) {
        EstadisticaEncargados ed = new EstadisticaEncargados();        
        ed.addWindowListener( new WindowAdapter() {
            public void windowClosing( WindowEvent e ) {
            System.exit(0);
            }
        });
        ed.setVisible(true);        
    }
}
