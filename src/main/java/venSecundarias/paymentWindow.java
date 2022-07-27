package venSecundarias;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import clases.tickets.datosTicket;
import java.text.SimpleDateFormat;
import java.util.Date;
import venPrimarias.ventana1;
//java
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;
import venPrimarias.start;

public class paymentWindow extends javax.swing.JDialog{
    public paymentWindow(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new guiMediaHandler(paymentWindow.class.getName()).LookAndFeel(paymentWindow.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Pago");
        setResizable(false);
        pack();
    }
    
    protected DefaultTableModel dtm;
    
    protected String nombre_prod;
    protected String marca_prod;
    
    protected int codigo_prod;
    protected int codigo_emp;
    protected int resultado;
    protected int cantidad;
    protected int precio;
    protected int total;
    
    protected void settings(){
        dtm=new DefaultTableModel();
        dtm.setRowCount(0);
        dtm.setColumnIdentifiers(new Object[]{
            "Código del producto",
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio",
            "Total"
        });
        for(int i=0;i<ventana1.dtm.getRowCount();i++){
            dtm.addRow(new Object[]{
                ventana1.dtm.getValueAt(i,0),
                ventana1.dtm.getValueAt(i,1),
                ventana1.dtm.getValueAt(i,2),
                ventana1.dtm.getValueAt(i,3),
                ventana1.dtm.getValueAt(i,4),
                ventana1.dtm.getValueAt(i,5)
            });
        }
        jLabel4.setText(ventana1.nombre_emp);
        try{
            int res=0;
            for(int i=0;i<dtm.getRowCount();i++){
                int n1=Integer.parseInt(dtm.getValueAt(i,5).toString());
                res+=n1;
                
                resultado=res;
            }
            jLabel5.setText(String.valueOf(resultado));
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'settings()'");
            new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"settings-32",e.fillInStackTrace());
        }
        jLabel6.setText(String.valueOf(ventana1.cambio));
        
        jTable1.setEnabled(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setModel(dtm);
    }
    
    protected final void botones(){
        cancelButton.addActionListener((a)->{
            int i=JOptionPane.showConfirmDialog(null,"¿Deseas cancelar la compra?","Notice 1",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            switch(i){
                case 0:{
                    setVisible(false);
                    dispose();
                    break;
                }
            }
        });
        
        mkPaidButton.addActionListener((a)->{
            try{
                switch(jComboBox1.getSelectedIndex()){
                    case 0:{
                        for(int i=0;i<dtm.getRowCount();i++){
                            codigo_prod=Integer.parseInt(dtm.getValueAt(i,0).toString());
                            codigo_emp=Integer.parseInt(jLabel4.getText());
                            nombre_prod=dtm.getValueAt(i,1).toString();
                            marca_prod=dtm.getValueAt(i,2).toString();
                            cantidad=Integer.parseInt(dtm.getValueAt(i,3).toString());
                            precio=Integer.parseInt(dtm.getValueAt(i,4).toString());
                            total=Integer.parseInt(dtm.getValueAt(i,5).toString());
                            
                            new datos().insertarDatosProducto(codigo_prod,codigo_emp,nombre_prod,marca_prod,cantidad,precio,total);
                        }
                        
                        new datosTicket().imprimirTicket(jTable1,jLabel2.getText(),Integer.parseInt(jLabel4.getText()),jComboBox1.getSelectedItem().toString(),Integer.parseInt(jLabel6.getText()),true);
                        JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
                        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a ka base de datos.\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
                        break;
                    }
                    case 1:{
                        for(int i=0;i<dtm.getRowCount();i++){
                            codigo_prod=Integer.parseInt(dtm.getValueAt(i,0).toString());
                            codigo_emp=Integer.parseInt(dtm.getValueAt(i,1).toString());
                            nombre_prod=dtm.getValueAt(i,2).toString();
                            marca_prod=dtm.getValueAt(i,3).toString();
                            cantidad=Integer.parseInt(dtm.getValueAt(i,4).toString());
                            precio=Integer.parseInt(dtm.getValueAt(i,5).toString());
                            total=Integer.parseInt(dtm.getValueAt(i,6).toString());
                            
                            new datos().insertarDatosProducto(codigo_prod,codigo_emp,nombre_prod,marca_prod,cantidad,precio,total);
                        }
                        /*
                        Aquí deberá ir el código para que se pague con tarjeta
                        Here will be write card payment code
                        */
                        new datosTicket().imprimirTicket(jTable1,jLabel2.getText(),Integer.parseInt(jLabel4.getText()),jComboBox1.getSelectedItem().toString(),Integer.parseInt(jLabel6.getText()),false);
                        JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
                        new logger(Level.INFO).staticLogger("Rel 1: se guardaron correctamente los datos a ka base de datos.\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
                        break;
                    }
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'");
                new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"botones.mkPaid-32",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+paymentWindow.class.getName()+"', en el método 'botones(mkPaidButton)'");
                new logger(Level.SEVERE).exceptionLogger(paymentWindow.class.getName(),"botones.mkPaid-0",x.fillInStackTrace());
            }
        });
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

        setIconImage(new guiMediaHandler(paymentWindow.class.getName()).getIconImage());

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
                    .addComponent(mkPaidButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new paymentWindow(new javax.swing.JFrame(),true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton mkPaidButton;
    // End of variables declaration//GEN-END:variables
}