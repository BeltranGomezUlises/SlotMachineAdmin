
package Estadistica.Encargados;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TableRender extends DefaultTableCellRenderer{
    
    private String[][] mAsteriscos ;
        
    private Color verde = new Color(0,150,0);
    
    TableRender(String[][] mAsteriscos){
       this.mAsteriscos = mAsteriscos;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){                                
        
        if ( mAsteriscos[row][column].contains("**") ){            
            this.setForeground(Color.RED);                        
        }  else{
            if ( mAsteriscos[row][column].contains("*") ){                
                this.setForeground(Color.blue);
            }else{
                //restablecer a los colores default
                this.setBackground(Color.white);
                this.setForeground(verde);
                //this.setFont(new Font("san-serif", Font.BOLD, 11));
                
            }                
        }        
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setFont((c.getFont().deriveFont(Font.BOLD)));                
        return c;
   }

    public String[][] getmAsteriscos() {
        return mAsteriscos;
    }

    public void setmAsteriscos(String[][] mAsteriscos) {
        this.mAsteriscos = mAsteriscos;
    }
   
}
