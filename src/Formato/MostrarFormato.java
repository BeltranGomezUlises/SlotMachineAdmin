package Formato;


import static Utilerias.Utileria.*;
import Productos.Producto;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class MostrarFormato extends javax.swing.JFrame {
    DecimalFormat df = new DecimalFormat("0.0");
    Vector<Integer> posFaltantesAnteriores = new Vector<Integer>();
    Vector<Integer> cantFaltantesAnteriores = new Vector<Integer>();
    Vector<Integer> posFaltantesPosteriores = new Vector<Integer>();
    Vector<Integer> cantFaltantesPosteriores= new Vector<Integer>();
    
    Vector<Integer> posSobrantes = new Vector<Integer>();
    Vector<Integer> cantSobrantes = new Vector<Integer>();
        
    float faltanteFondoInicial=0;
    float faltanteFondoFinal=0;
            
    float fondoSobrante=0;
    
    Vector<Producto> productos;
    
    public MostrarFormato(){
        initComponents();
    }
    public MostrarFormato(Formato f) {
        initComponents();
//        DecimalFormat df= new DecimalFormat("0.0");
        String datos[][]={};
        String encabezado[]={"Nombre","InvI","Compras","InvF","Cant","Precio","Utilidad","Comision"};
        int anchos[]={50,8,8,8,8,60,60,60};
        DefaultTableModel md = new DefaultTableModel(datos,encabezado);
        tabla.setModel(md);
        for(int k=0; k<anchos.length; k++){//ajustar los tamaños
            tabla.getColumnModel().getColumn(k).setPreferredWidth(anchos[k]); 
        }
        productos=f.getProductos();
        fondoSobrante=f.getFondoSobrante();
        txtFecha.setText(f.getFecha());
        txtSucursal.setText(f.getSucursal());
        txtEncargado.setText(f.getEncargado());
        txtTurno.setText(String.valueOf(f.getTurno()));
        txtFondoInicial.setText(String.valueOf(f.getFondoInicial()));
        txtFondoFinal.setText(String.valueOf(f.getFondoFinal())); 
        txtEntregador.setText(quitaGuion(f.getEntregador()));
        if (!f.getNota().equals("null")) {
            txtNota.setText(quitaGuion(f.getNota()));
        }        
        //llenar tabla con la informacion
        Vector fila;
        Vector<Producto> productos=f.getProductos();
        Vector<Integer> invI=f.getInvI();
        Vector<Integer> compras=f.getCompras();
        Vector<Integer> invF=f.getInvF();
        int cant;
        float totalVenta=0,totalUtilidad=0,totalComision=0;
        for (int i = 0; i < f.getProductos().size(); i++) {            
            fila=new Vector();
            
            cant = invI.elementAt(i)+compras.elementAt(i)-invF.elementAt(i);
            fila.add(quitaGuion(productos.elementAt(i).getNombre()));
            fila.add(invI.elementAt(i));
            fila.add(compras.elementAt(i));
            fila.add(invF.elementAt(i));
            fila.add(cant);
            fila.add(df.format(cant*productos.elementAt(i).getPrecio()));
            fila.add(df.format(cant*productos.elementAt(i).utilidad()));
            fila.add(df.format(cant*productos.elementAt(i).comision()));
            md.addRow(fila);
            //para los totales
            totalVenta+=cant*productos.elementAt(i).getPrecio();
            totalUtilidad+=cant*productos.elementAt(i).utilidad();
            totalComision+=cant*productos.elementAt(i).comision();
        }
        //restar los sobrantes en caso de existir
        totalVenta=totalVenta-f.sobrante+f.faltante;
        totalUtilidad=totalUtilidad-f.getSobranteUtilidad()+f.getFaltanteUtilidad();
        totalComision=totalComision-f.getSobranteComision()+f.getFaltanteComision();
        //agregar fila de totales
        Object[] filaTotales={"","","","","Totales:",df.format(totalVenta),df.format(totalUtilidad),df.format(totalComision)};
        md.addRow(filaTotales);
        
        txtRetiro.setText(String.valueOf(f.getRetiro()));
        txtFaltante.setText(String.valueOf(f.getFaltante()));
        txtSobrante.setText(String.valueOf(f.getSobrante()));
        txtFinal.setText(String.valueOf(f.totalVenta()-f.sobrante-f.fondoFinal-f.fondoSobrante+f.fondoInicial-f.getRetiro()+f.getFondoFaltante()-200+f.faltante));
        txtFondo.setText(String.valueOf(f.getFondoInicial()-f.getFondoFinal()+f.getFondoFaltante()-f.getFondoSobrante()));
    
        chkFirmado.setSelected(f.getFirma());
        chkPagado.setSelected(f.getPagado());
    }

    public void setPosFaltantesAnteriores(Vector<Integer> posFaltantesAnteriores) {
        this.posFaltantesAnteriores = posFaltantesAnteriores;
    }
    public void setCantFaltantesAnteriores(Vector<Integer> cantFaltantesAnteriores) {
        this.cantFaltantesAnteriores = cantFaltantesAnteriores;
    }
    
    public void setPosFaltantesPosteriores(Vector<Integer> posFaltantesPosteriores) {
        this.posFaltantesPosteriores = posFaltantesPosteriores;
    }
    public void setCantFaltantesPosteriores(Vector<Integer> cantFaltantesPosteriores) {
        this.cantFaltantesPosteriores = cantFaltantesPosteriores;
    }
    
    public void setPosSobrantes(Vector<Integer> posSobrantes) {
        this.posSobrantes = posSobrantes;
    }
    public void setCantSobrantes(Vector<Integer> cantSobrantes) {
        this.cantSobrantes = cantSobrantes;
    }
    
    public void setFaltanteFondoInicial(float FaltanteFondoInicial) {
        this.faltanteFondoInicial = FaltanteFondoInicial;
    }
    public void setFaltanteFondoFinal(float FaltanteFondoFinal) {
        this.faltanteFondoFinal = FaltanteFondoFinal;
    }

    public void setFondoSobrante(float fondoSobrante) {
        this.fondoSobrante = fondoSobrante;
    }
    
    
  
    public void setMarcas(){
        for(int k=0; k<posFaltantesAnteriores.size(); k++){
            tabla.setValueAt(tabla.getValueAt(posFaltantesAnteriores.elementAt(k), 1)+"+"+cantFaltantesAnteriores.elementAt(k), posFaltantesAnteriores.elementAt(k), 1);
            tabla.setValueAt(tabla.getValueAt(posFaltantesAnteriores.elementAt(k),4)+"+"+cantFaltantesAnteriores.elementAt(k), posFaltantesAnteriores.elementAt(k), 4);
            tabla.setValueAt(tabla.getValueAt(posFaltantesAnteriores.elementAt(k),5)+"+"+df.format(cantFaltantesAnteriores.elementAt(k)*productos.elementAt(posFaltantesAnteriores.elementAt(k)).getPrecio()), posFaltantesAnteriores.elementAt(k), 5);
            tabla.setValueAt(tabla.getValueAt(posFaltantesAnteriores.elementAt(k),6)+"+"+df.format(cantFaltantesAnteriores.elementAt(k)*productos.elementAt(posFaltantesAnteriores.elementAt(k)).utilidad()), posFaltantesAnteriores.elementAt(k), 6);
            tabla.setValueAt(tabla.getValueAt(posFaltantesAnteriores.elementAt(k),7)+"+"+df.format(cantFaltantesAnteriores.elementAt(k)*productos.elementAt(posFaltantesAnteriores.elementAt(k)).comision()), posFaltantesAnteriores.elementAt(k), 7);
        }
        for(int k=0; k<posFaltantesPosteriores.size(); k++){            
            tabla.setValueAt(tabla.getValueAt(posFaltantesPosteriores.elementAt(k), 3)+"-"+cantFaltantesPosteriores.elementAt(k), posFaltantesPosteriores.elementAt(k), 3);          
            tabla.setValueAt(tabla.getValueAt(posFaltantesPosteriores.elementAt(k), 4)+"+"+cantFaltantesPosteriores.elementAt(k), posFaltantesPosteriores.elementAt(k),4);
            tabla.setValueAt(tabla.getValueAt(posFaltantesPosteriores.elementAt(k), 5)+"+"+df.format(cantFaltantesPosteriores.elementAt(k)*productos.elementAt(posFaltantesPosteriores.elementAt(k)).getPrecio()), posFaltantesPosteriores.elementAt(k),5);
            tabla.setValueAt(tabla.getValueAt(posFaltantesPosteriores.elementAt(k), 6)+"+"+df.format(cantFaltantesPosteriores.elementAt(k)*productos.elementAt(posFaltantesPosteriores.elementAt(k)).utilidad()),posFaltantesPosteriores.elementAt(k),6);
            tabla.setValueAt(tabla.getValueAt(posFaltantesPosteriores.elementAt(k), 7)+"+"+df.format(cantFaltantesPosteriores.elementAt(k)*productos.elementAt(posFaltantesPosteriores.elementAt(k)).comision()),posFaltantesPosteriores.elementAt(k),7);            
        }
        for(int k=0; k<posSobrantes.size(); k++){            
                tabla.setValueAt(tabla.getValueAt(posSobrantes.elementAt(k), 3)+"+"+cantSobrantes.elementAt(k), posSobrantes.elementAt(k), 3);
                tabla.setValueAt(tabla.getValueAt(posSobrantes.elementAt(k), 4)+"-"+cantSobrantes.elementAt(k), posSobrantes.elementAt(k), 4);
                tabla.setValueAt(tabla.getValueAt(posSobrantes.elementAt(k), 5)+"-"+df.format(cantSobrantes.elementAt(k)*productos.elementAt(posSobrantes.elementAt(k)).getPrecio()), posSobrantes.elementAt(k), 5);
                tabla.setValueAt(tabla.getValueAt(posSobrantes.elementAt(k), 6)+"-"+df.format(cantSobrantes.elementAt(k)*productos.elementAt(posSobrantes.elementAt(k)).utilidad()),posSobrantes.elementAt(k),6);
                tabla.setValueAt(tabla.getValueAt(posSobrantes.elementAt(k), 7)+"-"+df.format(cantSobrantes.elementAt(k)*productos.elementAt(posSobrantes.elementAt(k)).comision()), posSobrantes.elementAt(k), 7);                                                                                
        }
        if(faltanteFondoFinal!=0){
            txtFondoFinal.setText(txtFondoFinal.getText()+"-"+faltanteFondoFinal);
        }
        if(faltanteFondoInicial!=0){
            txtFondoInicial.setText(txtFondoInicial.getText()+"+"+faltanteFondoFinal);
        }
        if(fondoSobrante!=0){
            txtFondoFinal.setText(txtFondoFinal.getText()+"+"+fondoSobrante);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel8 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        lbTurno = new javax.swing.JLabel();
        txtTurno = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtEntregador = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        lbSucursal = new javax.swing.JLabel();
        txtSucursal = new javax.swing.JTextField();
        lbEncargado = new javax.swing.JLabel();
        txtEncargado = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        txtFondoInicial = new javax.swing.JTextField();
        txtFondoFinal = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtRetiro = new javax.swing.JTextField();
        txtFondo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFaltante = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtFinal = new javax.swing.JTextField();
        chkPagado = new javax.swing.JCheckBox();
        chkFirmado = new javax.swing.JCheckBox();
        lineaSeparadora = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        txtSobrante = new javax.swing.JTextField();
        btnCerrar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtNota = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Formato");
        setResizable(false);

        jPanel12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel19.setText("Fecha:");

        txtFecha.setEditable(false);

        lbTurno.setText("Turno:");

        txtTurno.setEditable(false);

        jLabel7.setText("Entregador:");

        txtEntregador.setEditable(false);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel19)
                .addGap(9, 9, 9)
                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(lbTurno)
                .addGap(15, 15, 15)
                .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEntregador)
                .addGap(80, 80, 80))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtEntregador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTurno)
                            .addComponent(txtTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 0, 0))
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        lbSucursal.setText("Sucursal:");

        txtSucursal.setEditable(false);

        lbEncargado.setText("Encargado:");

        txtEncargado.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lbSucursal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(lbEncargado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbEncargado)
                .addComponent(txtEncargado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lbSucursal)
                .addComponent(txtSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        jLabel20.setText("Fondo Inicial:");

        jLabel30.setText("Fondo Final:");

        txtFondoInicial.setEditable(false);

        txtFondoFinal.setEditable(false);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(16, 16, 16)
                .addComponent(txtFondoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFondoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel30)
                    .addComponent(txtFondoInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFondoFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

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
        tabla.setEnabled(false);
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 151, Short.MAX_VALUE)
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setText("Retiro(-):");

        txtRetiro.setEditable(false);
        txtRetiro.setFocusable(false);

        txtFondo.setEditable(false);
        txtFondo.setFocusable(false);

        jLabel2.setText("Fondo(+/-):");

        jLabel3.setText("Falta(+):");

        txtFaltante.setEditable(false);
        txtFaltante.setFocusable(false);

        jLabel4.setText("Final:");

        txtFinal.setEditable(false);
        txtFinal.setFocusable(false);
        txtFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFinalActionPerformed(evt);
            }
        });

        chkPagado.setText("Pagado");
        chkPagado.setEnabled(false);

        chkFirmado.setText("Firmado");
        chkFirmado.setEnabled(false);

        lineaSeparadora.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 153, 153)));

        javax.swing.GroupLayout lineaSeparadoraLayout = new javax.swing.GroupLayout(lineaSeparadora);
        lineaSeparadora.setLayout(lineaSeparadoraLayout);
        lineaSeparadoraLayout.setHorizontalGroup(
            lineaSeparadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );
        lineaSeparadoraLayout.setVerticalGroup(
            lineaSeparadoraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel5.setText("Sobra(-):");

        txtSobrante.setEditable(false);
        txtSobrante.setFocusable(false);
        txtSobrante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSobranteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFondo, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSobrante, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(lineaSeparadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkPagado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chkFirmado, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtRetiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFaltante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chkFirmado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(chkPagado)))
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSobrante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(3, 3, 3)
                .addComponent(lineaSeparadora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCerrar.setText("Regresar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel6.setText("Nota:");

        txtNota.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(3, 3, 3)
                .addComponent(txtNota)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(129, 129, 129)))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCerrar)
                                .addGap(0, 0, Short.MAX_VALUE))))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void txtFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFinalActionPerformed

    private void txtSobranteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSobranteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSobranteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCerrar;
    private javax.swing.JCheckBox chkFirmado;
    private javax.swing.JCheckBox chkPagado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbEncargado;
    private javax.swing.JLabel lbSucursal;
    private javax.swing.JLabel lbTurno;
    private javax.swing.JPanel lineaSeparadora;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtEncargado;
    private javax.swing.JTextField txtEntregador;
    private javax.swing.JTextField txtFaltante;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFinal;
    private javax.swing.JTextField txtFondo;
    private javax.swing.JTextField txtFondoFinal;
    private javax.swing.JTextField txtFondoInicial;
    private javax.swing.JTextField txtNota;
    private javax.swing.JTextField txtRetiro;
    private javax.swing.JTextField txtSobrante;
    private javax.swing.JTextField txtSucursal;
    private javax.swing.JTextField txtTurno;
    // End of variables declaration//GEN-END:variables
}
