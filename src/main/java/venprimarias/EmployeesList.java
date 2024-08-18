package venprimarias;
//clases
import clases.DbUtils;
import clases.Events;
import clases.MediaHandler;
import clases.InternalLogger;
import clases.SQLConnection;
import java.awt.EventQueue;
import venterciarias.DataWindow1;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.RowSorter;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.AbstractAction;
//extension larga
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.sql.Connection;
import java.util.logging.Level;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public final class EmployeesList extends javax.swing.JFrame{
    public EmployeesList(){
        initComponents();
        new MediaHandler().setLookAndFeel(EmployeesList.this);
        
        botones();
        datosMostrar();
        popup();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Empleados");
        pack();
    }
    
    protected DefaultTableModel dtm;
    protected JPopupMenu popupMenu;
    
    protected final void settings(){
        mostrarBoton(false);
    }
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        jButton2.addActionListener(a->
            new DataWindow1(new javax.swing.JFrame(),true,Integer.parseInt(dtm.getValueAt(0,1).toString())).setVisible(true)
        );
        
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
        
        jTable1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseReleased(MouseEvent a){
                Events.clearTableSelection(jTable1,a);
                Events.showPopup(popupMenu,a);
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
        mostrarBoton(false);
    }
    
    //Este es para buscar datos en concreto
    protected void searchData(){
        if(!txtBuscar.getText().isEmpty()){
            datosBuscar();
            mostrarBoton(true);
        }else{
            new InternalLogger().storeError18(this,Level.WARNING);
        }
    }
    
    protected final void datosMostrar(){
        dtm=Events.tableModel();
        RowSorter<TableModel> sorter=new TableRowSorter<>(dtm);
        Object[] header=new Object[]{"Contraseña","Código","Nombre(s)","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"};
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("select * from empleados");
                ResultSet rs=ps.executeQuery();){
            dtm.setColumnIdentifiers(header);
            while(rs.next()){
                loadData(dtm,rs);
            }
            Events.table(jTable1,sorter,dtm);
        }catch(SQLException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(NumberFormatException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }catch(NullPointerException n){
            new InternalLogger().catchException(this,n,Level.SEVERE);
        }
    }
    
    protected void datosBuscar(){
        dtm=Events.tableModel();
        RowSorter<TableModel> sorter=new TableRowSorter<>(dtm);
        Object[] header=new Object[]{"Contraseña","Código","Nombre(s)","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"};
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0:{
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select * from empleados where codigo_emp=?")){
                        ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                        try(ResultSet rs=ps.executeQuery()){
                            dtm.setColumnIdentifiers(header);
                            if(rs.next()){
                                loadData(dtm,rs);
                            }else{
                                new InternalLogger().storeError14(this,Level.WARNING);
                            }
                            Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                        }
                    }
                    break;
                }
                case 1:{
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select * from empleados where nombre_emp=?")){
                        ps.setString(1,txtBuscar.getText());
                        try(ResultSet rs=ps.executeQuery()){
                            dtm.setColumnIdentifiers(header);
                            if(rs.next()){
                                loadData(dtm,rs);
                            }else{
                                new InternalLogger().storeError14(this,Level.WARNING);
                            }
                            Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                        }
                    }
                    break;
                }
                case 2:{
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select * from empleados where apellidop_emp=?")){
                        ps.setString(1,txtBuscar.getText());
                        try(ResultSet rs=ps.executeQuery()){
                            dtm.setColumnIdentifiers(header);
                            if(rs.next()){
                                loadData(dtm,rs);
                            }else{
                                new InternalLogger().storeError14(this,Level.WARNING);
                            }
                            Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                        }
                    }
                    break;
                }
                case 3:{
                    try(Connection cn=SQLConnection.getConnection();
                            PreparedStatement ps=cn.prepareStatement("select * from empleados where apellidom_emp=?")){
                        ps.setString(1,txtBuscar.getText());
                        try(ResultSet rs=ps.executeQuery()){
                            dtm.setColumnIdentifiers(header);
                            if(rs.next()){
                                loadData(dtm,rs);
                            }else{
                                new InternalLogger().storeError14(this,Level.WARNING);
                            }
                            Events.table(jTable1,sorter,DbUtils.resultSetToTableModel(rs),dtm);
                        }
                    }
                    break;
                }
                default:
                    break;
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
    
    protected final void popup(){
        popupMenu=new JPopupMenu();
        
        JMenuItem mi1=new JMenuItem(new AbstractAction("Ver datos"){
            @Override
            public void actionPerformed(ActionEvent a){
                new DataWindow1(new javax.swing.JFrame(),true,Integer.parseInt(jTable1.getValueAt(jTable1.getSelectedRow(),1).toString())).setVisible(true);
            }
        });
        
        popupMenu.add(mi1);
    }
    
    protected void mostrarBoton(boolean flag){
        if(flag){
            jButton2.setVisible(true);
        }
        if(!flag){
            jButton2.setVisible(false);
            textField("");
        }
    }
    
    protected void textField(String text){
        txtBuscar.setText(text);
    }
    
    protected void loadData(DefaultTableModel dtm1,ResultSet rs1) throws SQLException{
        dtm1.addRow(new Object[]{
            rs1.getString("password"),
            rs1.getInt("codigo_emp"),
            rs1.getString("nombre_emp"),
            rs1.getString("apellidop_emp"),
            rs1.getString("apellidom_emp"),
            rs1.getString("puesto"),
            rs1.getString("experiencia"),
            rs1.getString("grado_estudios"),
            rs1.getInt("contacto"),
            rs1.getInt("edad"),
            rs1.getString("estado"),
            rs1.getString("fecha_registro"),
            rs1.getString("fecha_sesion")
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        backButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Empleados");

        searchButton.setText("Buscar");

        refreshButton.setText("Recargar");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Apellido paterno", "Apellido materno" }));

        backButton.setText("Regresar");

        jButton2.setText("Ver datos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1188, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchButton)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton)
                    .addComponent(backButton)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
                new EmployeesList().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}