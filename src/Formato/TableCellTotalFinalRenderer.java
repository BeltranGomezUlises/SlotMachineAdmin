package Formato;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableCellTotalFinalRenderer extends DefaultTableCellRenderer{
    private Component componente;
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        componente = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        try{
            if(table.getColumnName(12).equals("Total Final")){
                if((float)table.getValueAt(row, 12)>0){
                  componente.setBackground(Color.YELLOW);
                }else{
                  componente.setBackground(null);
                } 
//                if(table.getValueAt(row, 10).toString().contains("*")){
//                    componente.setBackground(Color.RED);
//                }
            }else{
                componente.setBackground(null);
            }
            
            if(isSelected){
                componente.setBackground(Color.LIGHT_GRAY);
                componente.setForeground(Color.BLACK);
            }
            }catch(ClassCastException e){    
            }
            return componente;
    }
}
