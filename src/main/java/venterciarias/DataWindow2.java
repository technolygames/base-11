package venterciarias;
//clases
import clases.Dirs;
import clases.MediaHandler;
import clases.InternalLogger;
import clases.SQLConnection;
import clases.Thread2;
//java
import java.awt.Image;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.awt.EventQueue;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.HashMap;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;
import java.lang.reflect.InaccessibleObjectException;
import java.sql.Connection;
import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;
import venprimarias.Start;

public class DataWindow2 extends javax.swing.JDialog{
    public DataWindow2(java.awt.Frame parent,boolean modal){
        super(parent, modal);
        initComponents();
        new MediaHandler().setLookAndFeel(DataWindow2.this);
        
        botones();
        datosMostrar();
        settings();
    }
    
    protected int codigo;
    
    public DataWindow2(java.awt.Frame parent,boolean modal,int code){
        super(parent, modal);
        initComponents();
        new MediaHandler().setLookAndFeel(DataWindow2.this);
        
        this.codigo=code;
        
        botones();
        datosMostrar();
        settings();
    }
    
    protected String dir;
    protected String image;
    
    protected Properties p;
    
    protected final void settings(){
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
    }
    
    protected final void datosMostrar(){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("select * from socios where codigo_part=?")){
            ps.setInt(1,codigo);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    etiCodigo.setText(String.valueOf(rs.getInt("codigo_part")));
                    etiNombre.setText(rs.getString("nombre_part"));
                    etiApellidoP.setText(rs.getString("apellidop_part"));
                    etiApellidoM.setText(rs.getString("apellidom_part"));
                    etiTipoSocio.setText(rs.getString("tipo_socio"));
                    etiCorreo.setText(rs.getString("correo"));
                    etiRFC.setText(rs.getString("rfc"));
                    jTextArea1.setText(rs.getString("datos_extra"));
                    etiIngreso.setText(rs.getString("fecha_ingreso"));
                    etiUCompra.setText(rs.getString("fecha_ucompra"));
                    etiFoto.setIcon(new ImageIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(rs.getBytes("foto"))).getImage().getScaledInstance(etiFoto.getWidth(),etiFoto.getHeight(),Image.SCALE_DEFAULT)));
                }else{
                    new InternalLogger().storeError14(this,Level.WARNING);
                }
            }
        }catch(SQLException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(NullPointerException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }
    }
    
    protected final void botones(){
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        miCreateInvoice.addActionListener(a->
            imprimirReporte()
        );
        
        miStorePic.addActionListener(a->{
            try(Connection cn=SQLConnection.getConnection();
                    PreparedStatement ps=cn.prepareStatement("select foto from socios where codigo_part=?")){
                int codigo1=Integer.parseInt(etiCodigo.getText());
                String nombre=etiNombre.getText();
                ps.setInt(1,codigo1);
                try(ResultSet rs=ps.executeQuery();
                        FileOutputStream fos=new FileOutputStream(Dirs.exists(new File("data/media/dataImage/Socios",nombre+"-"+codigo1+".jpg")))){
                    new Thread2(rs,fos).run();
                    InternalLogger.staticLogger(Level.INFO,"Se guardó correctamente la imagen del socio.");
                }
            }catch(SQLException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(IOException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }catch(NullPointerException n){
                new InternalLogger().catchException(this,n,Level.SEVERE);
            }
        });
    }
    
    protected void imprimirReporte(){
        p=new Properties();
        MediaHandler mh=new MediaHandler();
        try(FileReader fr=new FileReader("data/config/config.properties",StandardCharsets.UTF_8)){
            p.load(fr);
            
            Map<String,Object> params=new HashMap<>();
            
            params.put("imagen",mh.getImagePath("imagenes","imagen_respaldo"));
            params.put("nombre_reporte",mh.getProgramName());
            params.put("codigo_emp",Start.USER_ID);
            params.put("codigo_part",Integer.parseInt(etiCodigo.getText()));
            
            JasperPrint jp=JasperFillManager.fillReport(JasperCompileManager.compileReport("data/database/Jasper/reportes.jrxml"),params,SQLConnection.getConnection());
            JasperViewer jv=new JasperViewer(jp);
            jv.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            jv.setVisible(false);
            
            String path=Dirs.exists(new File("data/generic/Jasper/reporte.pdf"));
            
            JasperExportManager.exportReportToPdfFile(jp,path);
            
            dir=path;
            
            if(JOptionPane.showConfirmDialog(this,"¿Deseas abrir el archivo?","Notice 1",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0){
                Desktop.getDesktop().open(new File(path));
            }
        }catch(JRException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(ExceptionInInitializerError x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }catch(NoClassDefFoundError n){
            new InternalLogger().catchException(this,n,Level.SEVERE);
        }catch(IOException k){
            new InternalLogger().catchException(this,k,Level.SEVERE);
        }catch(JRFontNotFoundException l){
            new InternalLogger().catchException(this,l,Level.SEVERE);
        }catch(InaccessibleObjectException r){
            new InternalLogger().catchException(this,r,Level.SEVERE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        storeImgButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        etiFoto = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        etiCodigo = new javax.swing.JLabel();
        etiNombre = new javax.swing.JLabel();
        etiApellidoP = new javax.swing.JLabel();
        etiApellidoM = new javax.swing.JLabel();
        etiTipoSocio = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        etiIngreso = new javax.swing.JLabel();
        etiUCompra = new javax.swing.JLabel();
        etiCorreo = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        etiRFC = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miStorePic = new javax.swing.JMenuItem();
        miCreateInvoice = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        storeImgButton.setText("Guardar imagen");

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N
        jLabel1.setText("Datos");

        etiFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        backButton.setText("Regresar");

        jLabel2.setText("Código del socio:");

        jLabel3.setText("Nombre del socio:");

        jLabel4.setText("Apellido paterno:");

        jLabel5.setText("Apellido materno:");

        jLabel6.setText("Tipo de socio:");

        jLabel7.setText("Datos extra:");

        jLabel8.setText("Fecha de ingreso:");

        jLabel9.setText("Fecha de última compra:");

        etiCodigo.setText(" ");
        etiCodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiNombre.setText(" ");
        etiNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiApellidoP.setText(" ");
        etiApellidoP.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiApellidoM.setText(" ");
        etiApellidoM.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiTipoSocio.setText(" ");
        etiTipoSocio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setFocusable(false);
        jScrollPane1.setViewportView(jTextArea1);

        etiIngreso.setText(" ");
        etiIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiUCompra.setText(" ");
        etiUCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        etiCorreo.setText(" ");
        etiCorreo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel10.setText("Correo electrónico:");

        etiRFC.setText(" ");
        etiRFC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("RFC:");

        jMenu1.setText("Acciones");

        miStorePic.setText("Guardar imagen");
        jMenu1.add(miStorePic);

        miCreateInvoice.setText("Crear factura");
        jMenu1.add(miCreateInvoice);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(etiApellidoM, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etiApellidoP, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(etiCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(etiNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(etiTipoSocio, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiUCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(etiRFC, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeImgButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 318, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(5, 5, 5)
                        .addComponent(etiFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiCodigo)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiNombre)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4)
                            .addComponent(etiApellidoP))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(etiApellidoM))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(etiTipoSocio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(etiCorreo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(etiRFC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8)
                            .addComponent(etiIngreso))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9)
                            .addComponent(etiUCompra))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeImgButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new DataWindow2(new javax.swing.JFrame(),true).setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel etiApellidoM;
    private javax.swing.JLabel etiApellidoP;
    private javax.swing.JLabel etiCodigo;
    private javax.swing.JLabel etiCorreo;
    private javax.swing.JLabel etiFoto;
    private javax.swing.JLabel etiIngreso;
    private javax.swing.JLabel etiNombre;
    private javax.swing.JLabel etiRFC;
    private javax.swing.JLabel etiTipoSocio;
    private javax.swing.JLabel etiUCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem miCreateInvoice;
    private javax.swing.JMenuItem miStorePic;
    private javax.swing.JButton storeImgButton;
    // End of variables declaration//GEN-END:variables
}