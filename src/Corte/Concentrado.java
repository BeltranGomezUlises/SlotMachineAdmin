package Corte;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Concentrado extends javax.swing.JFrame {
    
    private String sucursal;
    private int mes,año;
    private Vector<Corte> CortesFiltrados= new Vector<>();
    private Vector<Integer> numSemanas;
    private Vector<String> rangoFechasPorSemana;
    private Vector<Integer> discrepanciaSemanaCont= new Vector<>();
    private Vector<Integer> discrepanciaSemanaPant= new Vector<>();
    private Vector<Integer> corteSemana= new Vector<>();
    private int cortePromedio;
    private String datos[][]={};
    private String cabecera[]={"Semana","Fechas","D.   Cont/Pant","Corte"};
    private int anchosColumna[]={10,60,60,25};
    private DateFormat df;
    private Calendar calendar;
    
    public Concentrado(String sucursal, int mes, int año,Vector<Corte> cortes) {
        initComponents();
        this.sucursal = sucursal;
        this.mes = mes;
        this.año = año;
        df  = new SimpleDateFormat("d/MM/yyyy");
        calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        
        //copiar los cortes
        for(Corte c : cortes){
            CortesFiltrados.add( c );
        }
        lb1.setText(sucursal+"    "+(mes+1)+"/"+año);
        this.filtrarCortes(CortesFiltrados); // eliminar los cortes que no corresponden
        numSemanas = this.numSemanas(CortesFiltrados); //asignar al vector los numeros de semanas
        this.inicializarVectores();
        // sacar los datos por semana que se necesitan
        for (int i = 0; i < numSemanas.size(); i++) {
            //para cada numero de semana sumar las discrepancias de contador,pant e InOut
            //solo aquellas que el corte no tenga fallo de contador o pantalla
            for (int j = 0; j <CortesFiltrados.size(); j++) { //sacar el indice del numero de semana 
                try {
                    calendar.setTime(df.parse(CortesFiltrados.elementAt(j).getFecha()));
                    if(calendar.get(Calendar.WEEK_OF_YEAR) == numSemanas.elementAt(i)){
                        if(!CortesFiltrados.elementAt(j).isFalloContador()){
                            discrepanciaSemanaCont.setElementAt(discrepanciaSemanaCont.elementAt(i)+CortesFiltrados.elementAt(j).discrepanciaCont(), i); //sumar su discrepancia
                        }
                        if(!CortesFiltrados.elementAt(j).isFalloPantalla()){
                            discrepanciaSemanaPant.setElementAt(discrepanciaSemanaPant.elementAt(i)+CortesFiltrados.elementAt(j).discrepanciaPant(), i);
                        }
                        corteSemana.setElementAt(corteSemana.elementAt(i)+CortesFiltrados.elementAt(j).getInOut(), i);
                    }
                } catch (ParseException e) {  
                    JOptionPane.showMessageDialog(rootPane, "Error de parseo de fechas, se omitirá corte","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        this.rangoFechasPorSemana = this.rangoFechas();
        //llenar la tabla
        this.llenarTabla(numSemanas, discrepanciaSemanaCont, discrepanciaSemanaPant, corteSemana);
        //promedio de cortes
        float promedioCorteSemana=0;
        for (int i = 0; i < corteSemana.size(); i++) {
            promedioCorteSemana += corteSemana.elementAt(i);
        }
        if(corteSemana.size() > 0){
            promedioCorteSemana /= corteSemana.size();
        }
        lb2.setText("Promedio de Corte: $" + promedioCorteSemana);
    }
 
    private void inicializarVectores(){
        for (int i = 0; i <numSemanas.size(); i++) {
            discrepanciaSemanaCont.add(0);
            discrepanciaSemanaPant.add(0);
            corteSemana.add(0);
        }
    }
    
    private void filtrarCortes(Vector<Corte> cortesFiltrados){
        //filtrar el Vector Cortes que cumplan con sucursal mes y año        
        try{
            int k=0;
            while(k<cortesFiltrados.size()){
                if(!cortesFiltrados.elementAt(k).getSucursal().equals(sucursal) ||
                   df.parse(cortesFiltrados.elementAt(k).getFecha()).getMonth()!=mes ||
                   df.parse(cortesFiltrados.elementAt(k).getFecha()).getYear()+1900!=año){
                    cortesFiltrados.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }catch(ParseException e){
            JOptionPane.showMessageDialog(rootPane, "Error de conversion de fechas con parseo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private Vector<Integer> numSemanas(Vector<Corte> cortesFiltrados){
        Vector<Integer> vNumSemanas= new Vector<Integer>();                
        for (int i = 0; i < cortesFiltrados.size(); i++) {
            try {
                calendar.setTime(df.parse(cortesFiltrados.elementAt(i).getFecha()));                
                if(!vNumSemanas.contains(calendar.get(Calendar.WEEK_OF_YEAR))){
                    vNumSemanas.add(calendar.get(Calendar.WEEK_OF_YEAR));
                }
            } catch (ParseException e) {
                JOptionPane.showMessageDialog(rootPane, "Error de parseo de fechas, se omitirá corte","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        return vNumSemanas;
    }
    
    private Vector<String> rangoFechas(){
        Vector<String> rangoFechas = new Vector<String>(); //este contendra los rangos de fechas por cada semana
        Vector<Date> fechasPorSemana; //aqui pondre todas las fechas de una semana para sacar la fecha menor y fecha mayor para obtener el rango
        for (Integer numSemana : numSemanas) { 
//            System.out.println(numSemana);
            fechasPorSemana = new Vector<Date>();
            for (Corte c : CortesFiltrados) {
                try {
                    calendar.setTime(df.parse(c.getFecha()));
                    if (calendar.get(Calendar.WEEK_OF_YEAR) == numSemana) {
                        fechasPorSemana.add(df.parse(c.getFecha()));
                    }
                } catch (Exception e) {//omitir
                }                
            }
            //con las fechas de la semana ordenar y luego obtener menor y mayor
            for (int i = 0; i < fechasPorSemana.size() - 1; i++) {
                for (int j = 0; j < fechasPorSemana.size() - i - 1; j++) {
                    if (fechasPorSemana.elementAt(j).after(fechasPorSemana.elementAt(j + 1))) {
                        Date aux = fechasPorSemana.elementAt(j + 1);
                        fechasPorSemana.setElementAt(fechasPorSemana.elementAt(j), j + 1);
                        fechasPorSemana.setElementAt(aux, j);
                    }
                }
            }
//            for (Date date : fechasPorSemana) { //verificar tener ordenadas las fechas
//                System.out.println(df.format(date));                
//            }
            String fechaMenor = df.format(fechasPorSemana.elementAt(0));
            String fechaMayor = df.format(fechasPorSemana.lastElement());
            rangoFechas.add(fechaMenor +" - " + fechaMayor);
//            System.out.println("Rango de fechas: " + rangoFechas.lastElement());
        }
        
        
        return rangoFechas;
    }
    private void llenarTabla(Vector<Integer> numSemanas, Vector<Integer> discrepanciaContador,Vector<Integer> discrepanciaPantalla,Vector<Integer> corteSemana){
        DefaultTableModel md= new DefaultTableModel(datos,cabecera);
        tabla.setModel(md);
        this.ajustarTabla();
        for (int i = 0; i < numSemanas.size(); i++) {
            Vector fila = new Vector();
            fila.add(numSemanas.elementAt(i));
            fila.add(rangoFechasPorSemana.elementAt(i));
            fila.add(discrepanciaContador.elementAt(i)+"    ||    "+discrepanciaPantalla.elementAt(i));
            fila.add("$"+corteSemana.elementAt(i));
            md.addRow(fila);
        }
        //fila de totales
        int totalDiscCont=0, totalDiscPant=0,totalCorte=0;
        for (int i = 0; i < numSemanas.size(); i++) {
            totalDiscCont+=discrepanciaSemanaCont.elementAt(i);
            totalDiscPant+=discrepanciaSemanaPant.elementAt(i);
//            System.out.println("Cont: "+discrepanciaSemanaCont.elementAt(i)+", Pant: "+discrepanciaSemanaPant.elementAt(i)+" CORTE:"+corteSemana.elementAt(i));
            totalCorte+=corteSemana.elementAt(i);
        }
        Vector filaFinal= new Vector();
        filaFinal.add("");
        filaFinal.add("TOTALES:"); filaFinal.add(totalDiscCont+"    ||    "+totalDiscPant);
        filaFinal.add("$"+totalCorte);
        md.addRow(filaFinal);
        
    }
    private void ajustarTabla(){
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();        
        tcr.setHorizontalAlignment(SwingConstants.CENTER);        
        for(int k=0; k < cabecera.length; k++){
              tabla.getColumnModel().getColumn(k).setPreferredWidth(anchosColumna[k]); 
              tabla.getColumnModel().getColumn(k).setCellRenderer(tcr); //ajustar contenido al centro
        }        
                
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        lb1 = new javax.swing.JLabel();
        btnCerrar = new javax.swing.JButton();
        lb2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Concentrados");
        setResizable(false);

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        lb1.setText("Sucursal mes año");

        btnCerrar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        lb2.setText("Promedio de Corte");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb2)
                .addGap(50, 50, 50))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(lb1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 585, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 36, Short.MAX_VALUE)
                        .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lb2)
                        .addGap(50, 50, 50))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
         this.dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed
          
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb1;
    private javax.swing.JLabel lb2;
    private javax.swing.JTable tabla;
    // End of variables declaration//GEN-END:variables
}
