
package Estadistica.Encargados;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class TableRender extends DefaultTableCellRenderer{
    
    private String[][] mAsteriscos ;
    
    private Color naranja = new Color(255,100,0);
    
    TableRender(String[][] mAsteriscos){
       this.mAsteriscos = mAsteriscos;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){                                
        if ( mAsteriscos[row][column].contains("**") ){
            this.setOpaque(true);
            this.setForeground(Color.RED);
        }  else{
            if ( mAsteriscos[row][column].contains("*") ){
                this.setOpaque(true);
                this.setForeground(naranja);
            }else{
                //restablecer a los colores default
                this.setBackground(Color.white);
                this.setForeground(Color.black);
            }                
        }        
        super.getTableCellRendererComponent (table, value, isSelected, hasFocus, row, column);        
        return this;
   }

    public String[][] getmAsteriscos() {
        return mAsteriscos;
    }

    public void setmAsteriscos(String[][] mAsteriscos) {
        this.mAsteriscos = mAsteriscos;
    }

    public Color getNaranja() {
        return naranja;
    }

    public void setNaranja(Color naranja) {
        this.naranja = naranja;
    }
    
    
}
