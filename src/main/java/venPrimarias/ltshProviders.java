package venPrimarias;
//clases
import clases.datos;
import clases.dbUtils;
import clases.guiMediaHandler;
import clases.logger;
//java
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
//extension larga
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.logging.Level;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public class ltshProviders extends javax.swing.JFrame{
    public ltshProviders(){
        initComponents();
        new guiMediaHandler(ltshProviders.class.getName()).LookAndFeel(ltshProviders.this);
        
        botones();
        datosMostrar();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Proveedores");
        pack();
    }
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    
    public static int code;
    
    protected final void settings(){
        mostrarBoton(false);
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        jButton1.addActionListener((a)->{
            code=Integer.parseInt(dtm.getValueAt(0, 0).toString());
        });
        
        refreshButton.addActionListener((ae)->{
            searchAndClean();
        });
        
        searchButton.addActionListener((ae)->{
            searchData();
        });
        
        txtBuscar.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    searchData();
                }
            }
        });
        
        jComboBox1.addActionListener((a)->{
            int i=jComboBox1.getSelectedIndex();
            if(i>=0&&i<6){
                searchAndClean();
            }
        });
    }
    
    //NO USAR PARA BUSCAR DATOS
    //Este método se encarga de limpiar el cuadro de búsqueda y la tabla, también de esconder el botón de ver datos detallados
    protected void searchAndClean(){
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
            JOptionPane.showMessageDialog(null,"Error:\nEscribe la palabra clave que deseas buscar","Error 14",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Error 18: no se escribió la palabra clave para hacer la búsqueda.\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'botones(searchButton)'");
        }
    }
    
    protected final void datosMostrar(){
        dtm=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                //all cells false
                return false;
            }
        };
        
        for(int i=0;i<dtm.getRowCount();i++){
            for(int j=0;j<dtm.getColumnCount();j++){
                dtm.isCellEditable(i,j);
            }
        }
        
        sorter=new TableRowSorter<>(dtm);
        try{
            ps=new datos().getConnection().prepareStatement("select * from proveedor;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Código","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosMostrar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshProviders.class.getName(),"datosMostrar-16",e.fillInStackTrace());
        }
    }
    
    protected final void datosBuscar(){
        dtm=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                //all cells false
                return false;
            }
        };
        
        for(int i=0;i<dtm.getRowCount();i++){
            for(int j=0;j<dtm.getColumnCount();j++){
                dtm.isCellEditable(i,j);
            }
        }
        
        sorter=new TableRowSorter<>(dtm);
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0:
                    ps=new datos().getConnection().prepareStatement("select * from proveedor where codigo_prov=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 1:
                    ps=new datos().getConnection().prepareStatement("select * from proveedor where nombre_prov=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 2:
                    ps=new datos().getConnection().prepareStatement("select * from proveedor where apellidop_prov=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 3:
                    ps=new datos().getConnection().prepareStatement("select * from proveedor where apellidom_prov=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 4:
                    ps=new datos().getConnection().prepareStatement("select * from proveedor where empresa=?;");
                    ps.setString(1,txtBuscar.getText());
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 5:
                    ps=new datos().getConnection().prepareStatement("select * from proveedor where contacto=?;");
                    ps.setInt(1,Integer.parseInt(txtBuscar.getText()));
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre","Apellido paterno","Apellido materno","Empresa","Contacto","Fecha de registro","Fecha de última entrega"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prov"),rs.getString("nombre_prov"),rs.getString("apellidop_prov"),rs.getString("apellidom_prov"),rs.getString("empresa"),rs.getInt("contacto"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_uentrega")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(dbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshProviders.class.getName(),"datosBuscar-14",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshProviders.class.getName(),"datosBuscar-0",x.fillInStackTrace());
        }catch(ArrayIndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error AIOOBE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error AIOOBE: "+p.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshProviders.class.getName(),"datosBuscar-AIOOBE",p.fillInStackTrace());
        }catch(IndexOutOfBoundsException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IOOBE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error IOOBE: "+n.getMessage()+".\nOcurrió en la clase '"+ltshProviders.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshProviders.class.getName(),"datosBuscar-IOOBE",n.fillInStackTrace());
        }
    }
    
    protected void mostrarBoton(boolean flag){
        if(flag==true){
            jButton1.setVisible(true);
        }else{
            jButton1.setVisible(false);
            textField("");
        }
    }
    
    protected void textField(String text){
        txtBuscar.setText(text);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        searchButton = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(ltshProviders.class.getName()).getIconImage());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Proveedores");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Apellido paterno", "Apellido materno", "Empresa", "Contacto" }));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTable1);

        backButton.setText("Regresar");

        searchButton.setText("Buscar");

        refreshButton.setText("Recargar");

        jButton1.setText("Ver datos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 510, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 432, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton)
                    .addComponent(jButton1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new ltshProviders().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}