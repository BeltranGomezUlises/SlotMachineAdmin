package Excel;

import java.io.*;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;


public class ExcelManager {
    
    private File file;
    private JTable table;
    private String tapName;
    private DefaultTableModel modeloT = new DefaultTableModel();
    Workbook wb;
    
    public ExcelManager(File file, JTable table, String nombreTab) {
        this.file = file;
        this.table = table;
        this.tapName = nombreTab;
    }
    
    public void exportar(){
        try{
            int numFilas = table.getRowCount(), numColumnas=table.getColumnCount();
            if (file.getName().endsWith("xls")) {
                wb = new HSSFWorkbook();
            }else{
                wb = new XSSFWorkbook();
            }
            Sheet hoja = wb.createSheet(tapName);
            for (int i = -1; i < numFilas; i++) {
                Row fila = hoja.createRow(i+1);
                for (int j = 0; j < numColumnas; j++) {
                    Cell celda = fila.createCell(j);
                    if (i==-1) {
                        celda.setCellValue(String.valueOf(table.getColumnName(j)));
                    }else{
                        celda.setCellValue(String.valueOf(table.getValueAt(i, j)));
                    }
                    
                }
            }           
            FileOutputStream fos = new FileOutputStream(file);            
            wb.write(fos);                   
        }catch(Exception e){
            e.printStackTrace();
        }
    }
       
    public void importar(){                
        try {                       
            wb = (Workbook) WorkbookFactory.create(new FileInputStream(file));
            Sheet hoja = (Sheet) wb.getSheetAt(0);
            Iterator<Row> filaIterador = hoja.rowIterator();
            int indiceFila=-1;
            while (filaIterador.hasNext()) {
                indiceFila++;
                Row fila = filaIterador.next();
                Iterator columnaIterador = fila.cellIterator();
                Vector listaColumna = new Vector();
                int indiceColumna=-1;
                while (columnaIterador.hasNext()) {
                    indiceColumna++;
                    Cell celda =(Cell) columnaIterador.next();
                    if (indiceFila==0) {
                        modeloT.addColumn(celda.getStringCellValue());
                    }else{
                        if (celda!=null) {
                            switch (celda.getCellType()) {
                                case Cell.CELL_TYPE_NUMERIC:
                                    listaColumna.add(celda.getNumericCellValue());
                                    break;
                                case Cell.CELL_TYPE_STRING:
                                    listaColumna.add(celda.getStringCellValue());
                                    break;
                                case Cell.CELL_TYPE_BOOLEAN:
                                    listaColumna.add(celda.getBooleanCellValue());
                                    break;
                                case Cell.CELL_TYPE_FORMULA:
                                    listaColumna.add(celda.getCellFormula());                                                                        
                                    break;
                                default:
                                    listaColumna.add(celda.getDateCellValue());
                            }
                        }
                    }                    
                }
                if (indiceFila!=0) {
                    modeloT.addRow(listaColumna);
                }
            }
            table.setModel(modeloT);
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "No Se Econtro El Archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "IO Exception ocurrencia de un fallo en el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }catch (InvalidFormatException ex) {
            ex.printStackTrace();
        } catch (EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }
    
}
