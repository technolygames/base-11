package venPrimarias;
//clases
import clases.datos;
import clases.Icono;
import clases.laf;
import clases.logger;
//librerías
import net.proteanit.sql.DbUtils;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.RowSorter;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;

public final class ltshProduct extends javax.swing.JFrame{
    public ltshProduct(){
        initComponents();
        new laf().LookAndFeel(ltshProduct.this,ltshProduct.class.getName(),"ltshProduct");
        
        datosMostrar();
        botones();
        
        setSize(950,550);
        setLocationRelativeTo(null);
        setTitle("Ventas");
        setResizable(false);
    }
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        refreshButton.addActionListener((e)->{
            datosMostrar();
        });
        
        searchButton.addActionListener((ae)->{
            datosBuscar();
        });
    }
    
    protected final void datosMostrar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<TableModel>(dtm);
        try{
            ps=new datos().getConnection().prepareStatement("select * from productos;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Código del producto","Código del empleado","Nombre del producto","Marca","Cantidad","Precio","Total","Fecha de compra"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getDate("fecha_compra")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshProduct.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProduct.class.getName(),Level.WARNING,"datosMostrar-16",e.fillInStackTrace());
        }
    }
    
    protected final void datosBuscar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<TableModel>(dtm);
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0:
                    ps=new datos().getConnection().prepareStatement("select * from productos where codigo_prod='"+txtBuscar.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código del producto","Código del empleado","Nombre del producto","Marca","Cantidad","Precio","Total","Fecha de compra"});
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getDate("fecha_compra")});
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 1:
                    ps=new datos().getConnection().prepareStatement("select * from productos where codigo_emp='"+txtBuscar.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código del producto","Código del empleado","Nombre del producto","Marca","Cantidad","Precio","Total","Fecha de compra"});
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getDate("fecha_compra")});
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 2:
                    ps=new datos().getConnection().prepareStatement("select * from productos where nombre_prod='"+txtBuscar.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código del producto","Código del empleado","Nombre del producto","Marca","Cantidad","Precio","Total","Fecha de compra"});
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getDate("fecha_compra")});
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
                case 3:
                    ps=new datos().getConnection().prepareStatement("select * from productos where marca='"+txtBuscar.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código del producto","Código del empleado","Nombre del producto","Marca","Cantidad","Precio","Total","Fecha de compra"});
                    while(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_prod"),rs.getInt("codigo_emp"),rs.getString("nombre_prod"),rs.getString("marca"),rs.getInt("cantidad"),rs.getInt("precio"),rs.getInt("total"),rs.getDate("fecha_compra")});
                    }
                    jTable1.setRowSorter(sorter);
                    jTable1.getRowSorter().toggleSortOrder(0);
                    jTable1.getTableHeader().setReorderingAllowed(false);
                    jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                    jTable1.setModel(dtm);
                    
                    ps.close();
                    rs.close();
                    break;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+ltshProduct.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProduct.class.getName(),Level.WARNING,"datosBuscar-14",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ltshProduct.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProduct.class.getName(),Level.WARNING,"datosBuscar-0",x.fillInStackTrace());
        }catch(ArrayIndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error AIOOBE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error AIOOBE: "+p.getMessage()+".\nOcurrió en la clase '"+ltshProduct.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProduct.class.getName(),Level.WARNING,"datosBuscar-AIOOBE",p.fillInStackTrace());
        }catch(IndexOutOfBoundsException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IOOBE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error IOOBE: "+n.getMessage()+".\nOcurrió en la clase '"+ltshProduct.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshProduct.class.getName(),Level.WARNING,"datosBuscar-IOOBE",n.fillInStackTrace());
        }
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
        setIconImage(new Icono().getIconImage());

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(refreshButton)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(searchButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING))))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new ltshProduct().setVisible(true);
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