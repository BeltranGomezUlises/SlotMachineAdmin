package Grafica;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Grafica {
 
    //tabla de valores
    private ArrayList<ArrayList<Float>> valores;
    // columnas
    private ArrayList<String> type;
    //filas o elementos tipo linea
    private ArrayList<String> series;
    
    //objetos de grafica
    private CategoryDataset dataset;
    private JFreeChart chart;     
    private ChartPanel chartPanel;
    
    public Grafica(ArrayList<String> series, ArrayList<String> type, ArrayList<ArrayList<Float>> valores ) {        
        this.valores = valores;
        this.type = type;
        this.series = series;
        
        dataset = createDataset();
        chart = createChart(dataset);             
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        chartPanel.setDomainZoomable(true);
    }

    private CategoryDataset createDataset() {
                
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        //paramas => valor, seria a la que pertenece y al punto en que se coloca (columna)
        for (int i = 0; i < series.size(); i++) { //sucursales
            float ultimoValor = 0;
            float valorSig = 0; 
            for (int j = 0; j < type.size(); j++) { //fechas               
                dataset.addValue(valores.get(i).get(j), series.get(i), type.get(j));                 
            }
        }        

        return dataset;                
    }
    
  
    private JFreeChart createChart(final CategoryDataset dataset) {
        String rangoFechas = "Del:  "+ type.get(0) + "  Al:  " + type.get(type.size() - 1);
        // create the chart...
        final JFreeChart chart = ChartFactory.createLineChart(
            rangoFechas,       // chart title
            "Fecha",                    // domain axis label
            "Corte",                   // range axis label
            dataset,                   // data
            PlotOrientation.VERTICAL,  // orientation
            true,                      // include legend
            true,                      // tooltips
            false                      // urls
        );

        // NOW DO SOME OPTIONAL CUSTOMISATION OF THE CHART...
//        final StandardLegend legend = (StandardLegend) chart.getLegend();
  //      legend.setDisplaySeriesShapes(true);
    //    legend.setShapeScaleX(1.5);
      //  legend.setShapeScaleY(1.5);
        //legend.setDisplaySeriesLines(true);

        chart.setBackgroundPaint(Color.white);

        final CategoryPlot plot = (CategoryPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setRangeGridlinePaint(Color.white);

        // customise the range axis...
        final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
        rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        rangeAxis.setAutoRangeIncludesZero(true);     
        
        // customise the renderer...
        final LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();
//        renderer.setDrawShapes(true);
        for (int i = 0; i < series.size(); i++) {
            renderer.setSeriesStroke(
                i, new BasicStroke(
                    4.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
                    1.0f, new float[] {100.0f, 0.0f}, 0.0f
                )
            );
        }               
        
        return chart;
    }

    public ChartPanel getChartPanel() {
        return chartPanel;
    }           

}