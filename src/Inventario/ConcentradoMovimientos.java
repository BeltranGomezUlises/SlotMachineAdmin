
package Inventario;

import static Utilerias.Utileria.*;
import static java.awt.image.ImageObserver.WIDTH;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConcentradoMovimientos extends javax.swing.JFrame {
    
    Vector<Movimiento> mov;
       
    public ConcentradoMovimientos(Vector<Movimiento> movimientos,String rutero, String sucursal,String tipo,Date fechaInicial, Date fechaFinal) {
        
        initComponents();
        mov=movimientos;
        //generar filtros segun el rutero, sucursal y tipo para el concentrado
        txtRutero.setText(rutero);
        txtSucursal.setText(sucursal);
        txtTipo.setText(tipo);
        
        rutero=quitaEspacios(rutero);
        sucursal=quitaEspacios(sucursal);
        tipo=quitaEspacios(tipo);
        
        tabla.setEnabled(false);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.doLayout();
        
        //filtrar los movimientos del vector y llenar la tabla con todos los productos existentes
        //despues rellenar con la suma total de cada uno de los productos
        
        //filtrar las fechas
        SimpleDateFormat sdf = new SimpleDateFormat("d/MM/yyyy");
        if (fechaInicial!=null && fechaFinal!=null) {
            lbFecha.setText("De:  "+sdf.format(fechaInicial)+" A:  "+sdf.format(fechaFinal));
        }        
        try{
            int k=0;
            Date fechaI=sdf.parse(sdf.format(fechaInicial));            
            Date fechaF=sdf.parse(sdf.format(fechaFinal));            
            while(k<mov.size()){
                String fecha=mov.elementAt(k).getFecha();
                Date fechaFormato = sdf.parse(fecha);                                  
                if(fechaFormato.before(fechaI)|| fechaFormato.after(fechaF)){
                   mov.removeElementAt(k); 
                }else{
                    k++;
                }
            }        
        }catch(NullPointerException e){
//            JOptionPane.showMessageDialog(rootPane, "Referencia nula al filtrar por fechas", "ERROR", WIDTH);
        }catch (ParseException ex) {
            JOptionPane.showMessageDialog(rootPane, "Error al convertir fechas al filtrar", "ERROR", WIDTH);            
        }
        
        if(!"Todos".equals(rutero)){
            int k=0;
            while(k<mov.size()){
                if(!mov.elementAt(k).getOrigen().equals(rutero) && !mov.elementAt(k).getDestino().equals(rutero)){
                    mov.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        //mov por Sucursal
        if(!"Todos".equals(sucursal)){
            int k=0;
            while(k<mov.size()){
                if(!mov.elementAt(k).getOrigen().equals(sucursal) && !mov.elementAt(k).getDestino().equals(sucursal)){
                    mov.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        //mov por tipo        
        if(!tipo.equals("Todos")){
            int k=0;
            while(k<mov.size()){
                if(!mov.elementAt(k).getTipo().equals(tipo)){
                    mov.removeElementAt(k);
                }else{
                    k++;
                }
            }
        }
        
        //llenar tabla fecha-tipo-origen-destino-productos 
        //crear el vector de productos de todos los movimientos:
        Vector<String> productos = new Vector<String>();
        for (int i = 0; i < mov.size(); i++) {
            for (int j = 0; j < mov.elementAt(i).getProductos().size(); j++) {
                if(!productos.contains(mov.elementAt(i).getProductos().elementAt(j))){
                    productos.add(mov.elementAt(i).getProductos().elementAt(j));
                }
            }            
        }
        //recorrer el vector de movimientos y llenar las cantidades según el producto
        //al mismo tiempo ir llenando la tabla
        Vector<String> VCabecera = new Vector<String>();
        VCabecera.add("Fecha");
        VCabecera.add("Tipo");
        VCabecera.add("Origen");
        VCabecera.add("Destino");
        for (int i = 0; i <productos.size(); i++) {
            VCabecera.add(quitaGuion(productos.elementAt(i)));
        }
        String[] cabecera = new String[VCabecera.size()];
        for (int i = 0; i < VCabecera.size(); i++) {
            cabecera[i]=VCabecera.elementAt(i);
        }        
        DefaultTableModel md = new DefaultTableModel( new Object [][] {}, cabecera);
        tabla.setModel(md);
        Vector<Integer> cantidadesPorFila; //las cantidades de cada producto por movimiento
        Vector<Integer> totalesPorProducto= new Vector<Integer>(); //las cantiadades totales de todos los movimientos
        
        for (int i = 0; i < productos.size(); i++) { //inicializar los totales con 0 para usarlo como acumulador
            totalesPorProducto.add(0);
        }
        
        Vector fila;
        for (int i = 0; i < mov.size(); i++) {
            fila = new Vector();            
            fila.add(mov.elementAt(i).getFecha());
            fila.add(mov.elementAt(i).getTipo());
            fila.add(mov.elementAt(i).getOrigen());
            fila.add(mov.elementAt(i).getDestino());
            
            cantidadesPorFila= new Vector<Integer>();
            //inicializar cantidadesPorFila
            for (int j = 0; j < productos.size(); j++) {
                cantidadesPorFila.add(0);
            }
            
            for (int j = 0; j < productos.size(); j++) {
                for (int k = 0; k < mov.elementAt(i).getProductos().size(); k++) {
                    if (productos.elementAt(j).equals(mov.elementAt(i).getProductos().elementAt(k))) {
                        cantidadesPorFila.setElementAt(mov.elementAt(i).getCantidades().elementAt(k), j);
                    }
                }                
            }
            //sumar a los
            for (int j = 0; j < cantidadesPorFila.size(); j++) {
                totalesPorProducto.setElementAt(totalesPorProducto.elementAt(j)+cantidadesPorFila.elementAt(j), j);
                fila.add(cantidadesPorFila.elementAt(j));
            }
            md.addRow(fila);
        }
        
        //añadir fila de totales        
        fila=new Vector();
        fila.add(""); fila.add(""); fila.add(""); fila.add("TOTALES:");        
        for (int i = 0; i < totalesPorProducto.size(); i++) {
            fila.add(totalesPorProducto.elementAt(i));
        }
        
        md.addRow(fila);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRutero = new javax.swing.JTextField();
        txtSucursal = new javax.swing.JTextField();
        txtTipo = new javax.swing.JTextField();
        lbFecha = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Concentrado");

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
        tabla.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tabla);

        jLabel1.setText("Rutero:");

        jLabel2.setText("Sucursal:");

        jLabel3.setText("Tipo:");

        txtRutero.setEditable(false);

        txtSucursal.setEditable(false);

        txtTipo.setEditable(false);

        lbFecha.setText("De:  A:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtRutero, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(11, 11, 11)
                .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(lbFecha)
                .addContainerGap(267, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtRutero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFecha))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFecha;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtRutero;
    private javax.swing.JTextField txtSucursal;
    private javax.swing.JTextField txtTipo;
    // End of variables declaration//GEN-END:variables
}
