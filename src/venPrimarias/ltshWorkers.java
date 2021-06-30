package venPrimarias;

import clases.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.RowSorter;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import net.proteanit.sql.DbUtils;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.DefaultTableModel;
import venTerciarias.dataWindow;

public final class ltshWorkers extends javax.swing.JFrame{
    public ltshWorkers(){
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
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error ULAFE",JOptionPane.WARNING_MESSAGE);
        }catch(FileNotFoundException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
        }catch(IOException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
        }
        
        datosMostrar();
        boton();
        
        setSize(1200,700);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Empleados");
        
        wdataButton.setVisible(false);
    }
    
    protected Image retValue;
    protected Properties p;
    
    protected int w;
    
    protected String id;
    
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
    
    protected final void datosMostrar(){
        DefaultTableModel dtm=new DefaultTableModel();
        RowSorter<TableModel> sorter=new TableRowSorter<>(dtm);
        try{
            PreparedStatement ps=new datos().getConnection().prepareStatement("select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,fecha_registro,fecha_sesion from empleados;");
            ResultSet rs=ps.executeQuery();
            dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Edad","Datos extra","Fecha de registro","Fecha de sesión"});
            while(rs.next()){
                dtm.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getDate(11),rs.getDate(12)});
            }
            jTable1.setRowSorter(sorter);
            jTable1.getRowSorter().toggleSortOrder(0);
            jTable1.getTableHeader().setReorderingAllowed(false);
            jTable1.setModel(dtm);
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 16",JOptionPane.WARNING_MESSAGE);
        }catch(NumberFormatException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 15",JOptionPane.WARNING_MESSAGE);
        }catch(NullPointerException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    protected final void boton(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        searchButton.addActionListener((ae)->{
            datosBuscar();
        });
        
        wdataButton.addActionListener((ae)->{
            new dataWindow(new javax.swing.JFrame(),true).setVisible(true);
        });
    }
    
    protected final void datosBuscar(){
        DefaultTableModel dtm=new DefaultTableModel();
        RowSorter<TableModel> sorter=new TableRowSorter<>(dtm);
        try{
            id=txtBuscar.getText();
            int i=jComboBox1.getSelectedIndex();
            if(i==0){
                String query1="select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,fecha_registro,fecha_sesion from empleados where codigo_emp='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query1);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Edad","Datos extra","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getDate(11),rs.getDate(12)});
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
                String query2="select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,fecha_registro,fecha_sesion from empleados where nombre_emp='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query2);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Edad","Datos extra","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getDate(11),rs.getDate(12)});
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
                String query3="select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,fecha_registro,fecha_sesion from empleados where apellidop_emp='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query3);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Edad","Datos extra","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getDate(11),rs.getDate(12)});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                ps.close();
                rs.close();
            }
            if(i==3){
                String query4="select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,fecha_registro,fecha_sesion from empleados where apellidom_emp='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query4);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Edad","Datos extra","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getDate(11),rs.getDate(12)});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                ps.close();
                rs.close();
            }
            if(i==4){
                String query5="select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,fecha_registro,fecha_sesion from empleados where puesto='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query5);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Edad","Datos extra","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getDate(11),rs.getDate(12)});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                ps.close();
                rs.close();
            }
            if(i==5){
                String query6="select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,fecha_registro,fecha_sesion from empleados where experiencia='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query6);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Edad","Datos extra","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getDate(11),rs.getDate(12)});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                ps.close();
                rs.close();
            }
            if(i==6){
                String query7="select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,fecha_registro,fecha_sesion from empleados where grado_estudios='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query7);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Edad","Datos extra","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getDate(11),rs.getDate(12)});
                }
                jTable1.setRowSorter(sorter);
                jTable1.getRowSorter().toggleSortOrder(0);
                jTable1.getTableHeader().setReorderingAllowed(false);
                jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                jTable1.setModel(dtm);
                ps.close();
                rs.close();
            }
            if(i==7){
                String query8="select password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,puesto,experiencia,grado_estudios,edad,datos_extra,fecha_registro,fecha_sesion from empleados where edad='"+id+"';";
                PreparedStatement ps=new datos().getConnection().prepareStatement(query8);
                ResultSet rs=ps.executeQuery();
                dtm.setColumnIdentifiers(new Object[]{"Contraseña","Código","Nombre","Apellido paterno","Apellido materno","Puesto","Experiencia","Grado de estudios","Edad","Datos extra","Fecha de registro","Fecha de sesión"});
                while(rs.next()){
                    dtm.addRow(new Object[]{rs.getString(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getInt(9),rs.getString(10),rs.getDate(11),rs.getDate(12)});
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
            JOptionPane.showMessageDialog(null,"Error:\n"+p.getMessage(),"Error Prueba",JOptionPane.WARNING_MESSAGE);
        }catch(IndexOutOfBoundsException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
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
        wdataButton = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        backButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

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
        jTable1.setEnabled(false);
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Empleados");

        searchButton.setText("Buscar");

        wdataButton.setText("Ver datos");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Código", "Nombre", "Apellido Paterno", "Apellido Materno", "Puesto", "Experiencia", "Grado de Estudios", "Edad" }));

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
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 153, Short.MAX_VALUE)
                        .addComponent(searchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(wdataButton)
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
                    .addComponent(wdataButton)
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
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JButton wdataButton;
    // End of variables declaration//GEN-END:variables
}