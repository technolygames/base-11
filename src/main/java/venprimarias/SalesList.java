package venprimarias;
//clases
import clases.DbUtils;
import clases.Events;
import clases.MediaHandler;
import clases.InternalLogger;
import clases.SQLConnection;
import java.awt.EventQueue;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.RowSorter;
//extension larga
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.sql.Connection;
import java.util.logging.Level;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public final class SalesList extends javax.swing.JFrame{
    public SalesList(){
        initComponents();
        new MediaHandler().setLookAndFeel(SalesList.this);
        
        datosMostrar();
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Ventas");
        pack();
    }
    
    protected DefaultTableModel dtm;
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        refreshButton.addActionListener(a->
            searchAndClear()
        );
        
        searchButton.addActionListener(a->
            searchData()
        );
        
        txtBuscar.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    searchData();
                }
            }
        });
        
        jComboBox1.addActionListener(a->{
            int i=jComboBox1.getSelectedIndex();
            if(i>=0&&i<4){
                searchAndClear();
            }
        });
    }
    
    //NO USAR PARA BUSCAR DATOS
    //Este método se encarga de limpiar el cuadro de búsqueda y la tabla, también de esconder el botón de ver datos detallados
    protected void searchAndClear(){
        textField("");
        datosMostrar();
    }
    
    //Este es para buscar datos en concreto
    protected void searchData(){
        if(!txtBuscar.getText().isEmpty()){
            datosBuscar();
        }else{
            new InternalLogger().storeError18(this,Level.WARNING);
        }
    }
    
    protected final void datosMostrar(){
        dtm=Events.tableModel();
        RowSorter<TableModel> sorter=new TableRowSorter<>(dtm);
        Object[] header=new Object[]{"Código del producto","Código del empleado","Nombre del producto","Marca","Cantidad","Precio","Total","Fecha de compra"};
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("select * from productos");
                ResultSet rs=ps.executeQuery()){
            dtm.setColumnIdentifiers(header);
            while(rs.next()){
                loadData(dtm,rs);
            }
            Events.table(jTable1,sorter,dtm);
        }catch(SQLException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }
    }
    
    protected void datosBuscar(){
        dtm=new DefaultTableModel();
        RowSorter<TableModel> sorter=new TableRowSorter<>(dtm);
        Object[] header=new Object[]{"Código del producto","Código del empleado","Nombre del producto","Marca","Cantidad","Precio","Total","Fecha de compra"};
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0:{
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select * from productos where codigo_prod=?")){
                        ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                        try(ResultSet rs=ps.executeQuery()){
                            dtm.setColumnIdentifiers(header);
                            while(rs.next()){
                                loadData(dtm,rs);
                            }
                            Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                        }
                    }
                    break;
                }
                case 1:{
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select * from productos where codigo_emp=?")){
                        ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                        try(ResultSet rs=ps.executeQuery()){
                            dtm.setColumnIdentifiers(header);
                            while(rs.next()){
                                loadData(dtm,rs);
                            }
                            Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                        }
                    }
                    break;
                }
                case 2:{
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select * from productos where nombre_prod=?")){
                        ps.setString(1,txtBuscar.getText());
                        try(ResultSet rs=ps.executeQuery()){
                            dtm.setColumnIdentifiers(header);
                            while(rs.next()){
                                loadData(dtm,rs);
                            }
                            Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                        }
                    }
                    break;
                }
                case 3:{
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select * from productos where marca=?")){
                        ps.setString(1,txtBuscar.getText());
                        try(ResultSet rs=ps.executeQuery()){
                            dtm.setColumnIdentifiers(header);
                            while(rs.next()){
                                loadData(dtm,rs);
                            }
                            Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                        }
                    }
                    break;
                }
                default:{
                    break;
                }
            }
        }catch(SQLException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(NullPointerException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }catch(ArrayIndexOutOfBoundsException n){
            new InternalLogger().catchException(this,n,Level.SEVERE);
        }catch(IndexOutOfBoundsException s){
            new InternalLogger().catchException(this,s,Level.SEVERE);
        }
    }
    
    protected void textField(String text){
        txtBuscar.setText(text);
    }
    
    protected void loadData(DefaultTableModel dtm1,ResultSet rs1) throws SQLException{
        dtm1.addRow(new Object[]{
            rs1.getInt("codigo_prod"),
            rs1.getInt("codigo_emp"),
            rs1.getString("nombre_prod"),
            rs1.getString("marca"),
            rs1.getInt("cantidad"),
            rs1.getInt("precio"),
            rs1.getInt("total"),
            rs1.getString("fecha_compra")
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Ventas");

        backButton.setText("Regresar");

        searchButton.setText("Buscar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código del producto", "Código del empleado", "Nombre del producto", "Marca" }));

        refreshButton.setText("Recargar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 523, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchButton)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
                new SalesList().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}