package venprimarias;
//clases
import clases.Events;
import clases.MediaHandler;
import clases.InternalLogger;
import clases.SQLConnection;
import vensecundarias.PaymentWindow;
//java
import java.awt.Image;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//extension larga
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;

public final class Ventana1 extends javax.swing.JFrame{
    public Ventana1(){
        initComponents();
        new MediaHandler().setLookAndFeel(Ventana1.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Productos");
        pack();
    }
    
    public static DefaultTableModel DTM;
    
    protected final void settings(){
        txtCodEmp.setText(String.valueOf(Start.USER_ID));
        picLabel.setIcon(new ImageIcon(new ImageIcon(new MediaHandler().getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
        
        DTM=Events.tableModel();
        DTM.setColumnIdentifiers(new Object[]{
            "Código del producto",
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio",
            "Total"
        });
        
        DTM.setRowCount(0);
        jTable1.setEnabled(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setModel(DTM);
    }
    
    protected final void botones(){
        DTM=Events.tableModel();
        addButton.addActionListener(a->{
            if(!txtCodigo.getText().isEmpty()||!txtProd.getText().isEmpty()||!txtMarca.getText().isEmpty()||!txtPrecio.getText().isEmpty()||!txtCant.getText().isEmpty()||!txtTotal.getText().isEmpty()){
                DTM.addRow(new Object[]{
                    txtCodigo.getText(),
                    txtProd.getText(),
                    txtMarca.getText(),
                    txtCant.getText(),
                    txtPrecio.getText(),
                    txtTotal.getText()
                });
            }else{
                new InternalLogger().storeError18(this,Level.WARNING);
            }
            
            cleanFields();
        });
        
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        cleanButton.addActionListener(a->{
            DTM.setRowCount(0);
            cleanFields();
        });
        
        jButton2.addActionListener(a->{
            try{
                DTM.removeRow(jTable1.getSelectedRow());
            }catch(ArrayIndexOutOfBoundsException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }
        });
        
        mkPaidButton.addActionListener((a)->{
            try{
                new PaymentWindow(new javax.swing.JFrame(),true).setVisible(true);
            }catch(NumberFormatException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(NullPointerException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }
        });
        
        txtCodigo.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select * from almacen where codigo_prod=?");
                            PreparedStatement ps2=cn.prepareStatement("update almacen set stock='Agotado' where codigo_prod=?")){
                        ps.setInt(1,Integer.parseInt(txtCodigo.getText()));
                        try(ResultSet rs=ps.executeQuery()){
                            if(rs.next()){
                                if(rs.getInt("cantidad")==0){
                                    if(rs.getString("stock").equals("Agotado")){
                                        JOptionPane.showMessageDialog(Ventana1.this,"Sin stock","Error Prueba",JOptionPane.WARNING_MESSAGE);
                                    }else{
                                        JOptionPane.showMessageDialog(Ventana1.this,"Sin stock","Error Prueba",JOptionPane.WARNING_MESSAGE);
                                        ps2.setInt(1,Integer.parseInt(txtCodigo.getText()));
                                        ps.executeUpdate();
                                    }
                                }else{
                                    txtProd.setText(rs.getString("nombre_prod"));
                                    txtMarca.setText(rs.getString("marca"));
                                    txtPrecio.setText(String.valueOf(rs.getInt("precio_unitario")));
                                }
                            }else{
                                new InternalLogger().storeError14(Ventana1.this,Level.WARNING);
                            }
                        }
                    }catch(SQLException e){
                        new InternalLogger().catchException(Ventana1.this,e,Level.SEVERE);
                    }
                }
            }
        });
        
        txtCant.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select*from almacen where codigo_prod=?")){
                        ps.setInt(1,Integer.parseInt(txtCodigo.getText()));
                        try(ResultSet rs=ps.executeQuery()){
                            if(rs.next()){
                                if(Integer.parseInt(txtCant.getText())>=rs.getInt("cantidad")&&Integer.parseInt(txtCant.getText())>rs.getInt("cantidad")||Integer.parseInt(txtCant.getText())==rs.getInt("cantidad")&&rs.getInt("cantidad")>=1){
                                    if(Integer.parseInt(txtCant.getText())>rs.getInt("cantidad")){
                                        JOptionPane.showMessageDialog(null,"No tienes mucho stock a partir de la cantidad ingresada.","Error Prueba",JOptionPane.ERROR_MESSAGE);
                                        new InternalLogger().storeError14(Ventana1.this,Level.WARNING);
                                    }else{
                                        calc();
                                        new InternalLogger().storeError14(Ventana1.this,Level.WARNING);
                                    }
                                }else{
                                    calc();
                                }
                            }
                        }
                    }catch(SQLException e){
                        new InternalLogger().catchException(Ventana1.this,e,Level.SEVERE);
                    }
                }
            }
        });
    }
    
    protected void calc(){
        try{
            int n1=Integer.parseInt(txtCant.getText());
            int n2=Integer.parseInt(txtPrecio.getText());
            int res=n2*n1;

            txtTotal.setText(String.valueOf(res));
        }catch(NumberFormatException e){
            new InternalLogger().catchException(Ventana1.this,e,Level.SEVERE);
        }
    }
    
    protected void cleanFields(){
        txtCodigo.setText("");
        txtProd.setText("");
        txtMarca.setText("");
        txtPrecio.setText("");
        txtCant.setText("");
        txtTotal.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtProd = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JTextField();
        txtCant = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        cleanButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        mkPaidButton = new javax.swing.JButton();
        picLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtCodEmp = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        jLabel2.setText("Código del producto:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        txtProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProdKeyPressed(evt);
            }
        });

        txtMarca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtMarcaKeyPressed(evt);
            }
        });

        jLabel3.setText("Nombre del producto:");

        jLabel4.setText("Marca:");

        jLabel5.setText("Precio:");

        jLabel6.setText("Cantidad:");

        txtPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrecioKeyPressed(evt);
            }
        });

        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
        });

        backButton.setText("Regresar");

        jLabel8.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel8.setText("Productos");

        txtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTotalKeyPressed(evt);
            }
        });

        jLabel9.setText("Total:");

        cleanButton.setText("Limpiar campos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        addButton.setText("Añadir campos");

        mkPaidButton.setText("Realizar pago");

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Código del empleado:");

        txtCodEmp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodEmpKeyPressed(evt);
            }
        });

        jButton2.setText("Eliminar fila");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtCodEmp, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(mkPaidButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton)
                        .addGap(113, 113, 113)
                        .addComponent(cleanButton)
                        .addGap(32, 32, 32)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodEmp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPrecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(cleanButton)
                    .addComponent(addButton)
                    .addComponent(mkPaidButton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed
    
    private void txtCodEmpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodEmpKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodEmpKeyPressed
    
    private void txtProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProdKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new InternalLogger().storeNumberInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtProdKeyPressed
    
    private void txtMarcaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtMarcaKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new InternalLogger().storeNumberInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtMarcaKeyPressed
    
    private void txtCantKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCantKeyPressed
    
    private void txtPrecioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioKeyPressed
    
    private void txtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTotalKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtTotalKeyPressed
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
                new Ventana1().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton addButton;
    protected javax.swing.JButton backButton;
    protected javax.swing.JButton cleanButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JTable jTable1;
    protected javax.swing.JButton mkPaidButton;
    protected javax.swing.JLabel picLabel;
    protected javax.swing.JTextField txtCant;
    protected javax.swing.JTextField txtCodEmp;
    protected javax.swing.JTextField txtCodigo;
    protected javax.swing.JTextField txtMarca;
    protected javax.swing.JTextField txtPrecio;
    protected javax.swing.JTextField txtProd;
    protected javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}