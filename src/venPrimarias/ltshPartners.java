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

public class ltshPartners extends javax.swing.JFrame{
    public ltshPartners(){
        initComponents();
        new laf().LookAndFeel(ltshPartners.this,ltshPartners.class.getName(),"ltshPartners");
        
        botones();
        datosMostrar();
        
        setSize(1100,600);
        setLocationRelativeTo(null);
        setTitle("Socios");
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
        
        refreshButton.addActionListener((ae)->{
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
            ps=new datos().getConnection().prepareStatement("select * from socios;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosMostrar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosMostrar-16",e.fillInStackTrace());
        }
    }
    
    protected final void datosBuscar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<TableModel>(dtm);
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0:
                    ps=new datos().getConnection().prepareStatement("select * from socios where codigo_part="+jTextField1.getText()+";");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
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
                    ps=new datos().getConnection().prepareStatement("select * from socios where nombre_part='"+jTextField1.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
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
                    ps=new datos().getConnection().prepareStatement("select * from socios where apellidop_part='"+jTextField1.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
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
                    ps=new datos().getConnection().prepareStatement("select * from socios where apellidom_part='"+jTextField1.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Código","Nombre(s)","Apellido paterno","Apellido materno","Tipo de socio","Datos extra","Fecha de registro","Fecha de última compra"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getInt("codigo_part"),rs.getString("nombre_part"),rs.getString("apellidop_part"),rs.getString("apellidom_part"),rs.getString("tipo_socio"),rs.getString("datos_extra"),rs.getDate("fecha_ingreso"),rs.getDate("fecha_ucompra")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger().staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
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
            new logger().staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosBuscar-14",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosBuscar-0",x.fillInStackTrace());
        }catch(ArrayIndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error AIOOBE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error AIOOBE: "+p.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosBuscar-AIOOBE",p.fillInStackTrace());
        }catch(IndexOutOfBoundsException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IOOBE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error IOOBE: "+n.getMessage()+".\nOcurrió en la clase '"+ltshPartners.class.getName()+"', en el método 'datosBuscar()'",Level.WARNING);
            new logger().exceptionLogger(ltshPartners.class.getName(),Level.WARNING,"datosBuscar-IOOBE",n.fillInStackTrace());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField1 = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        backButton.setText("Regresar");

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
        jLabel1.setText("Socios");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Apellido paterno", "Apellido materno" }));

        searchButton.setText("Buscar");

        refreshButton.setText("Recargar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
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
        new ltshPartners().setVisible(true);
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