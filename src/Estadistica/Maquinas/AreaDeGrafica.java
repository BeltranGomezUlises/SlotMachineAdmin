
package Estadistica.Maquinas;

import Grafica.Grafica;
import static Utilerias.Utileria.quitaEspacios;
import static Utilerias.Utileria.quitaGuion;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;

public class AreaDeGrafica extends javax.swing.JFrame {

    ArrayList<JCheckBox> checkBoxes;
    
    private ArrayList<String> sucursales;
    private ArrayList<String> fechasDeCortes;
    private ArrayList<ArrayList<Float>> valores;    
    
    public AreaDeGrafica(ArrayList<String> series, ArrayList<String> type, ArrayList<ArrayList<Float>> valores ) {
        initComponents();
        
        checkBoxes = new ArrayList<>();
        sucursales = series;
        fechasDeCortes = type;
        this.valores = valores;
        
        panelGrafica.setLayout(new BorderLayout());
        
        this.graficar(sucursales, fechasDeCortes, this.valores);                
                
        panelSucursales.setLayout(new BoxLayout(panelSucursales, BoxLayout.Y_AXIS));
        
        ChangeListener changeListener = new ChangeListener();
        
        for (int i = 0; i < series.size(); i++) { //series con las sucursales
            JCheckBox checkBox = new JCheckBox(quitaGuion(series.get(i)));
            checkBox.setSelected(true);
            checkBox.addActionListener(changeListener);
            checkBoxes.add(checkBox);
            panelSucursales.add(checkBox);
        }
    }

    private void graficar(ArrayList<String> series, ArrayList<String> type, ArrayList<ArrayList<Float>> valores ){
        Grafica grafica = new Grafica(series, type, valores);                
        panelGrafica.removeAll();
        panelGrafica.add(grafica.getChartPanel(), BorderLayout.CENTER);
        panelGrafica.updateUI();
        panelGrafica.repaint();        
    }
        
    private class ChangeListener implements ActionListener{

        private ArrayList<String> misSucursales;
        private ArrayList<String> misFechasDeCortes;
        private ArrayList<ArrayList<Float>> misValores; 
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            misSucursales = (ArrayList<String>) sucursales.clone();
            misFechasDeCortes = (ArrayList<String>) fechasDeCortes.clone();
            misValores = (ArrayList<ArrayList<Float>>) valores.clone();
            //cuando se accione un JCheckBox recorrer los jCheckBox y remover aquello no seleccionados de la lista de sucursales y de la matriz
            for (int i = 0; i < checkBoxes.size(); i++) {
                if (!checkBoxes.get(i).isSelected()) {
                    //buscar el indice que corresponde al checkBox en la lista y remover
                    for (int j = 0; j < misSucursales.size(); j++) {
                        if (misSucursales.get(j).equals(quitaEspacios(checkBoxes.get(i).getText()))) {
                            misSucursales.remove(j);
                            misValores.remove(j);  
                        }
                    }                                      
                }
            }            
            //mandar graficar con  mis listas
            graficar(misSucursales, misFechasDeCortes, misValores);
                        
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        panelSucursales = new javax.swing.JPanel();
        panelGrafica = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Grafica");

        jSplitPane1.setDividerLocation(200);

        panelSucursales.setBorder(javax.swing.BorderFactory.createTitledBorder("Sucursales"));

        javax.swing.GroupLayout panelSucursalesLayout = new javax.swing.GroupLayout(panelSucursales);
        panelSucursales.setLayout(panelSucursalesLayout);
        panelSucursalesLayout.setHorizontalGroup(
            panelSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 187, Short.MAX_VALUE)
        );
        panelSucursalesLayout.setVerticalGroup(
            panelSucursalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        jSplitPane1.setLeftComponent(panelSucursales);

        panelGrafica.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout panelGraficaLayout = new javax.swing.GroupLayout(panelGrafica);
        panelGrafica.setLayout(panelGraficaLayout);
        panelGraficaLayout.setHorizontalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 908, Short.MAX_VALUE)
        );
        panelGraficaLayout.setVerticalGroup(
            panelGraficaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 518, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(panelGrafica);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JPanel panelGrafica;
    private javax.swing.JPanel panelSucursales;
    // End of variables declaration//GEN-END:variables
}
