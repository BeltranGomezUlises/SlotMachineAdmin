
package Estadistica.Encargados;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TableRender extends DefaultTableCellRenderer{
    private Color naranja = new Color(255,164,032);
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){        
        String valorCelda = String.valueOf(table.getValueAt(row, column));
        if ( valorCelda.contains("***") ){
           this.setOpaque(true);           
           this.setForeground(Color.RED);
        } else{
            if ( valorCelda.contains("**") ){
                this.setOpaque(true);
                this.setForeground(Color.RED);
            }  else{
                if ( valorCelda.contains("*") ){
                    this.setOpaque(true);
                    this.setForeground(naranja);
                }else{
                    //restablecer a los colores default
                    this.setBackground(Color.white);
                    this.setForeground(Color.black);
                }                
            }
        }
        super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);        
        return this;
   }
}
