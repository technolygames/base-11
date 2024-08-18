package venprimarias;
//clases
import clases.Events;
import clases.Datos;
import clases.MediaHandler;
import clases.InternalLogger;
//java
import java.awt.Image;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import java.util.Properties;
//extension larga
import java.util.logging.Level;
import javax.swing.table.DefaultTableModel;

public final class Ventana2 extends javax.swing.JFrame{
    public Ventana2(){
        initComponents();
        new MediaHandler().setLookAndFeel(Ventana2.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Almacén");
        pack();
    }
    
    protected Properties p;
    protected DefaultTableModel dtm;
    
    protected final void settings(){
        p=new Properties();
        dtm=Events.tableModel();
        picLabel.setIcon(new ImageIcon(new ImageIcon(new MediaHandler().getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
        
        dtm.setColumnIdentifiers(new Object[]{
            "Código del producto",
            "Código del lote",
            "Código del proveedor",
            "Nombre del producto",
            "Marca",
            "Cantidad",
            "Precio unitario",
            "Stock"
        });
        dtm.setRowCount(0);
        
        jTable1.setModel(dtm);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jTable1.setEnabled(false);
    }
    
    protected final void botones(){
        dtm=new DefaultTableModel();
        addButton.addActionListener(a->{
            if(!txtCodProd.getText().isEmpty()||!txtCodLote.getText().isEmpty()||!txtCodProv.getText().isEmpty()||!txtProd.getText().isEmpty()||!txtMarca.getText().isEmpty()||!txtCant.getText().isEmpty()||!txtPU.getText().isEmpty()||!jComboBox1.getSelectedItem().equals("En Existencia")){
                dtm.addRow(new Object[]{
                    txtCodProd.getText(),
                    txtCodLote.getText(),
                    txtCodProv.getText(),
                    txtProd.getText(),
                    txtMarca.getText(),
                    txtCant.getText(),
                    txtPU.getText(),
                    jComboBox1.getSelectedItem()
                });
            }else{
                new InternalLogger().storeError14(this,Level.WARNING);
            }
            cleanFields();
        });
        
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        cleanButton.addActionListener(a->{
            dtm.setRowCount(0);
            cleanFields();
        });
        
        svdtButton.addActionListener(a->{
            try{
                for(int i=0;i<dtm.getRowCount();i++){
                    int codigoProducto=Integer.parseInt(dtm.getValueAt(i,0).toString());
                    int codigoLote=Integer.parseInt(dtm.getValueAt(i,1).toString());
                    int codigoProveedor=Integer.parseInt(dtm.getValueAt(i,2).toString());
                    String nombreProducto=dtm.getValueAt(i,3).toString();
                    String marca=dtm.getValueAt(i,4).toString();
                    int cantidad=Integer.parseInt(dtm.getValueAt(i,5).toString());
                    int preciou=Integer.parseInt(dtm.getValueAt(i,6).toString());
                    String stock=dtm.getValueAt(i,7).toString();
                    
                    new Datos().insertarDatosAlmacen(codigoProducto,codigoLote,codigoProveedor,nombreProducto,marca,cantidad,preciou,stock);
                }
                new InternalLogger().storeMessageConfirmation(this,Level.INFO);
            }catch(NumberFormatException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(NullPointerException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }
        });
    }
    
    protected void cleanFields(){
        txtCodProd.setText("");
        txtCodLote.setText("");
        txtCodProv.setText("");
        txtProd.setText("");
        txtMarca.setText("");
        txtCant.setText("");
        txtPU.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        svdtButton = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtCodProd = new javax.swing.JTextField();
        txtProd = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtPU = new javax.swing.JTextField();
        txtCodLote = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtCodProv = new javax.swing.JTextField();
        cleanButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        addButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCant = new javax.swing.JTextField();
        picLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        backButton.setText("Regresar");

        svdtButton.setText("Guardar datos");

        jLabel6.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel6.setText("Almacén");

        jLabel7.setText("Código del producto:");

        jLabel8.setText("Nombre del producto:");

        jLabel9.setText("Marca:");

        jLabel10.setText("Precio unitario:");

        txtCodProd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProdKeyPressed(evt);
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

        txtPU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPUKeyPressed(evt);
            }
        });

        txtCodLote.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodLoteKeyPressed(evt);
            }
        });

        jLabel2.setText("Código del lote:");

        jLabel4.setText("Código del proveedor:");

        txtCodProv.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodProvKeyPressed(evt);
            }
        });

        cleanButton.setText("Limpiar campos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        addButton.setText("Añadir campos");

        jLabel1.setText("Cantidad:");

        txtCant.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCantKeyPressed(evt);
            }
        });

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("Stock:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "En Existencia", "Agotado" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(svdtButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cleanButton)
                        .addGap(213, 213, 213)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodLote, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(txtCodProv, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPU, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodProv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtProd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCant, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPU, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(svdtButton)
                    .addComponent(addButton)
                    .addComponent(cleanButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtCodProdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProdKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodProdKeyPressed
    
    private void txtCodLoteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodLoteKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodLoteKeyPressed
    
    private void txtCodProvKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodProvKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodProvKeyPressed
    
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
    
    private void txtPUKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPUKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtPUKeyPressed
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
                new Ventana2().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton addButton;
    protected javax.swing.JButton backButton;
    protected javax.swing.JButton cleanButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    protected javax.swing.JLabel picLabel;
    protected javax.swing.JButton svdtButton;
    protected javax.swing.JTextField txtCant;
    protected javax.swing.JTextField txtCodLote;
    protected javax.swing.JTextField txtCodProd;
    protected javax.swing.JTextField txtCodProv;
    protected javax.swing.JTextField txtMarca;
    protected javax.swing.JTextField txtPU;
    protected javax.swing.JTextField txtProd;
    // End of variables declaration//GEN-END:variables
}