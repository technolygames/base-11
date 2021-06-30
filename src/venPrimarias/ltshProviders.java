package venPrimarias;

import clases.datos;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.RowSorter;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.proteanit.sql.DbUtils;

public class ltshProviders extends javax.swing.JFrame{
    public ltshProviders(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
        }catch(IllegalAccessException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
        }catch(UnsupportedLookAndFeelException j){
            JOptionPane.showMessageDialog(null,"Error:\n"+j.getMessage(),"Error ULAFE",JOptionPane.WARNING_MESSAGE);
        }catch(FileNotFoundException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
        }catch(IOException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
        }
        
        datosMostrar();
        botones();
        
        setSize(950,500);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Productos vendidos");
    }
    
    protected Image retValue;
    protected Properties p;
    
    @Override
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
        }
        return retValue;
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        refreshButton.addActionListener((ae)->{
            datosMostrar();
        });
        
        searchButton.addActionListener((ae)->{
            datosBuscar();
        });
    }
    
    protected final void datosMostrar(){
        DefaultTableModel dtm=new DefaultTableModel();
        RowSorter<TableModel> sorter=new TableRowSorter<>(dtm);
        try{
            PreparedStatement ps=new datos().getConnection().prepareStatement("select codigo_prov,nombre_prov,apellidop_prov,apellidom_prov,empresa,fecha_ingreso,fecha_uentrega from proveedor;");
            ResultSet rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Código del proveedor","Nombre","Apellido paterno","Apellido materno","Empresa","Fecha de registro","Fecha de última entrega"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getDate(6),rs.getDate(7)});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            jTable1.getModel();
            ps.close();
            rs.close();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Error:\n"+ex.getMessage(),"Error 16",JOptionPane.WARNING_MESSAGE);
        }catch(NullPointerException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 14",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    protected final void datosBuscar(){
        DefaultTableModel dtm=new DefaultTableModel();
        RowSorter<TableModel> sorter=new TableRowSorter<>(dtm);
        try{
            String id=jTextField1.getText();
            int i=jComboBox1.getSelectedIndex();
            if(i==0){
                String query1="select * from productos where codigo_prod='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query1);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código del producto","Nombre del producto","Marca del producto","Cantidad","Precio","Ganancia","Fecha de compra"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDate(7)});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                ps.close();
                rs.close();
            }
            if(i==1){
                String query2="select * from productos where nombre_prod='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query2);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código del producto","Nombre del producto","Marca del producto","Cantidad","Precio","Ganancia","Fecha de compra"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDate(7)});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                ps.close();
                rs.close();
            }
            if(i==2){
                String query3="select * from productos where marca_prod='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query3);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Código del producto","Nombre del producto","Marca del producto","Cantidad","Precio","Ganancia","Fecha de compra"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getInt(6),rs.getDate(7)});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                ps.close();
                rs.close();
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.WARNING_MESSAGE);
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
        }catch(ArrayIndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error ",JOptionPane.WARNING_MESSAGE);
        }catch(IndexOutOfBoundsException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
        }
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
        jTextField1 = new javax.swing.JTextField();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Proveedores");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

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
        jTable1.setCellSelectionEnabled(false);
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        backButton.setText("Regresar");

        searchButton.setText("Buscar");

        refreshButton.setText("Recargar");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
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
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(refreshButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new ltshProviders().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton searchButton;
    // End of variables declaration//GEN-END:variables
}