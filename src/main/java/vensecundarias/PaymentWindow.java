package vensecundarias;
//clases
import clases.Datos;
import clases.Events;
import clases.MediaHandler;
import clases.InternalLogger;
import clases.SQLConnection;
import clases.tickets.DatosTicket;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import venprimarias.Ventana1;
//java
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;
import venprimarias.Start;

public class PaymentWindow extends javax.swing.JDialog{
    public PaymentWindow(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new MediaHandler().setLookAndFeel(PaymentWindow.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Pago");
        setResizable(false);
        pack();
    }
    
    protected DefaultTableModel dtm;
    
    protected String nombre;
    protected String marca;
    
    protected int codigo_prod;
    protected int codigo_emp;
    protected int resultado;
    protected int cantidad;
    protected int precio;
    public static int result;
    protected int total;
    
    protected boolean state;
    
    protected void settings(){
        dtm=Events.tableModel();
        dtm.setRowCount(0);
        dtm.setColumnIdentifiers(new Object[]{
            "Código del producto",
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio",
            "Total"
        });
        for(int i=0;i<Ventana1.DTM.getRowCount();i++){
            dtm.addRow(new Object[]{
                Ventana1.DTM.getValueAt(i,0),
                Ventana1.DTM.getValueAt(i,1),
                Ventana1.DTM.getValueAt(i,2),
                Ventana1.DTM.getValueAt(i,3),
                Ventana1.DTM.getValueAt(i,4),
                Ventana1.DTM.getValueAt(i,5)
            });
        }
        
        jLabel4.setText(String.valueOf(Start.USER_ID));
        try{
            int res=0;
            for(int i=0;i<dtm.getRowCount();i++){
                int n1=Integer.parseInt(dtm.getValueAt(i,5).toString());
                res+=n1;
                
                resultado=res;
            }
            jLabel5.setText(String.valueOf(resultado));
        }catch(NumberFormatException e){
            new InternalLogger().storeError18(this,Level.WARNING);
        }
        
        jTable1.setEnabled(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setModel(dtm);
    }
    
    protected final void botones(){
        calcButton.addActionListener((a)->{
            result=Integer.parseInt(jLabel5.getText());
            new CalcWindow(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        cancelButton.addActionListener(a->{
            if(state){
                setVisible(false);
                dispose();
            }else{
                int i=JOptionPane.showConfirmDialog(this,"¿Deseas cancelar la compra?","Notice 1",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if(i==0){
                    setVisible(false);
                    dispose();
                }
            }
        });
        
        jComboBox1.addActionListener(a->{
            switch(jComboBox1.getSelectedIndex()){
                case 0:{
                    calcButton.setVisible(true);
                    jLabel3.setVisible(true);
                    jLabel6.setVisible(true);
                    jLabel8.setVisible(true);
                    break;
                }
                default:{
                    calcButton.setVisible(false);
                    jLabel3.setVisible(false);
                    jLabel6.setVisible(false);
                    jLabel8.setVisible(false);
                    break;
                }
            }
        });
        
        mkPaidButton.addActionListener(a->{
            try(Connection cn=SQLConnection.getConnection();
                    PreparedStatement ps=cn.prepareStatement("update almacen set cantidad=cantidad-? where codigo_prod=?")){
                switch(jComboBox1.getSelectedIndex()){
                    case 0:{
                        ps.setInt(1,cantidad);
                        ps.setInt(2,codigo_prod);
                        ps.executeUpdate();
                        storeData();
                        state=false;
                        cancelButton.setText("Regresar");
                        new DatosTicket().imprimirTicket(jTable1,jLabel2.getText(),Integer.parseInt(jLabel4.getText()),jComboBox1.getSelectedItem().toString(),Integer.parseInt(jLabel6.getText()),true);
                        new InternalLogger().storeMessageConfirmation(this,Level.INFO);
                        break;
                    }
                    case 1:{
                        ps.setInt(1,cantidad);
                        ps.setInt(2,codigo_prod);
                        ps.executeUpdate();
                        storeData();
                        state=false;
                        cancelButton.setText("Regresar");
                        new DatosTicket().imprimirTicket(jTable1,jLabel4.getText(),Integer.parseInt(jLabel5.getText()),jComboBox1.getSelectedItem().toString(),Integer.parseInt(jLabel6.getText()),false);
                        new InternalLogger().storeMessageConfirmation(this,Level.INFO);
                        break;
                    }
                }
            }catch(NumberFormatException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(NullPointerException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }catch(SQLException s){
                new InternalLogger().catchException(this,s,Level.SEVERE);
            }
        });
    }
    
    private void storeData(){
        for(int i=0;i<dtm.getRowCount();i++){
            codigo_prod=Integer.parseInt(dtm.getValueAt(i,0).toString());
            codigo_emp=Integer.parseInt(jLabel4.getText());
            nombre=dtm.getValueAt(i,1).toString();
            marca=dtm.getValueAt(i,2).toString();
            cantidad=Integer.parseInt(dtm.getValueAt(i,3).toString());
            precio=Integer.parseInt(dtm.getValueAt(i,4).toString());
            total=Integer.parseInt(dtm.getValueAt(i,5).toString());
            
            new Datos().insertarDatosProducto(codigo_prod,codigo_emp,nombre,marca,cantidad,precio,total);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        mkPaidButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        calcButton = new javax.swing.JButton();

        setIconImage(new clases.MediaHandler().getIconImage());

        cancelButton.setText("Cancelar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        mkPaidButton.setText("Realizar pago");

        jLabel1.setText("Empleado:");

        jLabel2.setText("Total:");

        jLabel3.setText("Cambio:");

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        jLabel7.setText("$");

        jLabel8.setText("$");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Tarjeta" }));

        calcButton.setText("Calcular");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(mkPaidButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(calcButton)
                        .addGap(119, 119, 119)
                        .addComponent(cancelButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 314, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(mkPaidButton)
                    .addComponent(calcButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
                new PaymentWindow(new javax.swing.JFrame(),true).setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton calcButton;
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public static javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton mkPaidButton;
    // End of variables declaration//GEN-END:variables
}