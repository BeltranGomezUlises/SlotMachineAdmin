
package Estadistica.Encargados;

import Formato.Formato;
import Utilerias.TableHeader.ColumnGroup;
import Utilerias.TableHeader.GroupableTableHeader;
import Utilerias.Utileria;
import static Utilerias.Utileria.quitaGuion;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;

public class EstadisticaEncargados extends JFrame{
    // listas de objetos
    Vector<Formato> formatosGeneral = Formato.cargarFormatos();
    Vector<Formato> formatosFiltrados;
    Vector<String> sucursales;
    ArrayList<String> fechas ;
    ArrayList<ArrayList<String>> mEncargados;
    
    //objetos de la tabla    
    JTable table;
    JScrollPane scroll ;
    // elementos para los duplicados de encargados
    String[][] mAsteriscos ;
    DefaultTableModel dm;
    
    //componentes
    JDateChooser jDateInicial = new JDateChooser();
    JDateChooser jDateFinal = new JDateChooser();
    JButton btnFiltrar = new JButton("Filtrar");
    
    public EstadisticaEncargados() {                
        super( "Groupable Header Example" );
        super.setExtendedState(MAXIMIZED_BOTH);
        super.setSize(800, 400);
        //colocar en la ultima semana del calendario
        GregorianCalendar cal = new GregorianCalendar();        
        jDateFinal.setDate(cal.getTime());
        cal.add(Calendar.DATE, -7);
        jDateInicial.setDate(cal.getTime());
        this.filtrar();
            
        //comportamiento boton
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                        
                filtrar();
                
                remove(scroll);
                
                scroll = new JScrollPane( table );
                add( scroll, BorderLayout.CENTER ); 
                ((JPanel)getContentPane()).updateUI();
                
                setCursor(null);
            }
        });
        //componentes
        this.setLayout(new BorderLayout());
        //panel superior
        JPanel panel = new JPanel();        
        panel.setLayout(new FlowLayout(FlowLayout.LEFT, 5,0));        
                                               
        
        JLabel lbInicial = new JLabel("Fecha Inicial: ");        
        panel.add(lbInicial);                        
        panel.add(jDateInicial);           
        panel.add(new JLabel("    ")); //separador        
        
        JLabel lbFinal = new JLabel("Fecha Final: ");
        panel.add(lbFinal);
        panel.add(jDateFinal);
        panel.add(new JLabel("    ")); //separador        
        
        panel.add(btnFiltrar);
               
        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Filtro", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11)));
        this.add(panel, BorderLayout.NORTH);
        
        //table central
        scroll = new JScrollPane( table );
        this.add( scroll, BorderLayout.CENTER );  
        
        super.setVisible(true);
    }
    
    private void llenarListas(Vector<Formato> formatos){
        sucursales = new Vector<>();
        fechas = new ArrayList<>();
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
    }
    
    private void llenarMatrizEncargados(){
        mEncargados = new ArrayList<>();
        //obtener su correspondiente encargado de dia y de noche por fecha y agregarlo a la fila de la sucursal
        ArrayList<Formato> formatosPorSucursal;        
        for (String sucursal : sucursales) {            
            formatosPorSucursal = new ArrayList<>();
            for (int j = 0; j < formatosFiltrados.size(); j++) {                
                if (formatosFiltrados.get(j).getSucursal().equals(sucursal)) {
                    formatosPorSucursal.add(formatosFiltrados.get(j));
                    formatosFiltrados.remove(j);
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
    }
    
    private void llenarMatrizDeDuplicados(){
         //llenar matriz de asteriscos a las celdas donde el encargado este doblando turno
        mAsteriscos = new String[mEncargados.size()][mEncargados.get(0).size() + 1 ];
        for (int i = 0; i < mAsteriscos.length; i++) { //inicializar
            Arrays.fill(mAsteriscos[i], "");                    
        }
        for (int i = 1; i < mEncargados.get(0).size() - 1; i++) {
            for (int j = 0; j < mEncargados.size(); j++) {
                String valorCeldaActual = String.valueOf(mEncargados.get(j).get(i));
                if (!valorCeldaActual.isEmpty()) {
                    //preguntar por el turno siguiente
                    for (int k = 0; k < mEncargados.size(); k++) {
                        if (String.valueOf(mEncargados.get(k).get( i + 1 )).equals(valorCeldaActual)) {
                            mAsteriscos[k][i + 2] += "*";
                            //si duplico entonces chear si triplico
                            try {
                                for (int l = 0; l < mEncargados.size(); l++) {
                                    if (String.valueOf(mEncargados.get(l).get( i + 2 )).equals(valorCeldaActual)) {
                                        mAsteriscos[l][i + 3] += "*";
                                        //aqui podemos preguntar si cuatruplico con otro ciclo
                                        break;
                                    }
                                } 
                            } catch (IndexOutOfBoundsException e) {
                                //cuando sea el penultimo elemento saltara esta excepcion
                            }                           
                            break;
                        }
                    }                                        
                }                
            }
        }
    }
    
    private void crearTablaYDiseño(){
        //definir modelo de tabla, encabezado y grupos
        dm = new DefaultTableModel();
        
        dm.addColumn("Sucursales");
        for (String fecha : fechas) {
            dm.addColumn("D");
            dm.addColumn("N");            
        }
        //teniendo el modelo de la tabla con las columnas de menor nivel crear la tabla para agrupar columnas
        table = new JTable( dm ) {
            @Override
            protected JTableHeader createDefaultTableHeader() {
               return new GroupableTableHeader(columnModel);
            }
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        }; 
        TableRender tr = new TableRender(mAsteriscos);        
        table.setDefaultRenderer(Object.class, tr);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);                
                
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
    }        
    
    private void llenarTabla(){
        //llenar con el contenido la tabla        
        for (int i = 0; i < mEncargados.size(); i++) {
            Vector fila = new Vector();
            fila.add(quitaGuion(sucursales.get(i)));
            for (int j = 0; j < mEncargados.get(i).size(); j++) {
                fila.add(quitaGuion(mEncargados.get(i).get(j)));
            }
            dm.addRow(fila);
        } 
    }        
        
    private void ajustarAnchosTabla(){
        //ajustar el ancho de la tabla
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(120); 
        } 
    }
    
    private void filtrar(){            
        formatosFiltrados = new Vector<>();
        for (Formato formato : formatosGeneral) {
            formatosFiltrados.add(formato);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        try {            
            int k=0;
            Date fechaInicial=sdf.parse(sdf.format(jDateInicial.getDate()));            
            Date fechaFinal=sdf.parse(sdf.format(jDateFinal.getDate()));            
            String fecha;
            while(k < formatosFiltrados.size()){
                fecha=formatosFiltrados.elementAt(k).getFecha();
                Date fechaFormato = sdf.parse(fecha);
                if(fechaFormato.before(fechaInicial) || fechaFormato.after(fechaFinal)){
                   formatosFiltrados.removeElementAt(k); 
                }else{
                    k++;
                }
            }        
            
            //cargar y llenar listas        
            this.llenarListas(formatosFiltrados); //inicalizar las listas y llenarlas
            this.llenarMatrizEncargados(); //utiliza las listas llenadas anteriormente
            this.llenarMatrizDeDuplicados(); //calcular posiciones de duplicados en una matriz
            this.crearTablaYDiseño(); //creamos el modelo y la tabla
            this.llenarTabla(); //llenamos con los encargados la tabla
            
            //this.asignarDuplicadosATabla(); //meter los datos de la matriz a la tabla
            this.ajustarAnchosTabla(); // ajustar los anchos de las columnas  
        } catch (Exception e) {
            System.out.println("sin fecha");
        }        
       
        
         
        
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
