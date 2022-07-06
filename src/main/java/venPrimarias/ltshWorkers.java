package venPrimarias;
//clases
import clases.datos;
import clases.guiMediaHandler;
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

public final class ltshWorkers extends javax.swing.JFrame{
    public ltshWorkers(){
        initComponents();
        new guiMediaHandler(ltshWorkers.class.getName()).LookAndFeel(ltshWorkers.this);
        
        botones();
        datosMostrar();
        
        setSize(1200,700);
        setLocationRelativeTo(null);
        setTitle("Empleados");
        pack();
    }
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected DefaultTableModel dtm;
    protected RowSorter<TableModel> sorter;
    
    protected final void botones(){
        dtm=new DefaultTableModel();
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
            ps=new datos().getConnection().prepareStatement("select * from empleados;");
            rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre(s)","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 16: "+e.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosMostrar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshWorkers.class.getName(),"datosMostrar-16",e.fillInStackTrace());
        }catch(NumberFormatException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error NFE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error NFE: "+x.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosMostrar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshWorkers.class.getName(),"datosMostrar-NFE",x.fillInStackTrace());
        }catch(NullPointerException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+n.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosMostrar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshWorkers.class.getName(),"datosMostrar-0",n.fillInStackTrace());
        }
    }
    
    protected final void datosBuscar(){
        dtm=new DefaultTableModel();
        sorter=new TableRowSorter<TableModel>(dtm);
        try{
            switch(jComboBox1.getSelectedIndex()){
                case 0:
                    ps=new datos().getConnection().prepareStatement("select * from empleados where codigo_emp='"+txtBuscar.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre(s)","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'");
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
                    ps=new datos().getConnection().prepareStatement("select * from empleados where nombre_emp='"+txtBuscar.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre(s)","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'");
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
                    ps=new datos().getConnection().prepareStatement("select * from empleados where apellidop_emp='"+txtBuscar.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre(s)","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'");
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
                    ps=new datos().getConnection().prepareStatement("select * from empleados where apellidom_emp='"+txtBuscar.getText()+"';");
                    rs=ps.executeQuery();
                    dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre(s)","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Contacto","Edad","Estado","Fecha de registro","Fecha de sesión"});
                    if(rs.next()){
                        dtm.addRow(new Object[]{rs.getString("password"),rs.getInt("codigo_emp"),rs.getString("nombre_emp"),rs.getString("apellidop_emp"),rs.getString("apellidom_emp"),rs.getString("puesto"),rs.getString("experiencia"),rs.getString("grado_estudios"),rs.getInt("contacto"),rs.getInt("edad"),rs.getString("estado"),rs.getDate("fecha_registro"),rs.getDate("fecha_sesion")});
                    }else{
                        JOptionPane.showMessageDialog(null,"Error:\nNo existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no hay datos que concuerden con los datos escritos.\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'");
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
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+e.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshWorkers.class.getName(),"datosBuscar-14",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshWorkers.class.getName(),"datosBuscar-0",x.fillInStackTrace());
        }catch(ArrayIndexOutOfBoundsException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error AIOOBE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error AIOOBE: "+n.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshWorkers.class.getName(),"datosBuscar-AIOOBE",n.fillInStackTrace());
        }catch(IndexOutOfBoundsException p){
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error IOOBE",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error IOOBE: "+p.getMessage()+".\nOcurrió en la clase '"+ltshWorkers.class.getName()+"', en el método 'datosBuscar()'");
            new logger(Level.SEVERE).exceptionLogger(ltshWorkers.class.getName(),"datosBuscar-IOOBE",p.fillInStackTrace());
        }
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

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(ltshWorkers.class.getName()).getIconImage());

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
        jLabel1.setText("Empleados");

        searchButton.setText("Buscar");

        refreshButton.setText("Ver datos");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Apellido paterno", "Apellido materno" }));

        backButton.setText("Regresar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(searchButton)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(refreshButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new ltshWorkers().setVisible(true);
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