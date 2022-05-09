package venSecundarias;
//clases
import clases.logger;
import clases.Icono;
import clases.laf;
import clases.resourceDownload;
import menus.menuVentanas;
//java
import java.awt.Cursor;
import java.util.Properties;
import javax.swing.Timer;
//extension larga
import java.util.logging.Level;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class loadWindow extends javax.swing.JFrame{
    public loadWindow(){
        initComponents();
        new laf().LookAndFeel(loadWindow.this,loadWindow.class.getName(),"loadWindow");
        
        load();
        
        setLocationRelativeTo(null);
        setResizable(false);
    }
    
    protected Timer t;
    
    protected Properties p;
    
    protected final void load(){
        ActionListener al=(ActionEvent ae)->{
            if(jProgressBar1.getValue()<115){
                jProgressBar1.setValue(jProgressBar1.getValue()+5);
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                switch(jProgressBar1.getValue()){
                    case 0:
                        jLabel2.setText(null);
                        jLabel2.setText("bridj-0.6.2.jar");
                        new resourceDownload().downloadLibs("bridj-0.6.2.jar","http://download857.mediafire.com/g481j40i6ykg/lzf97xqjc8i2tou/bridj-0.6.2.jar");
                        break;
                    case 5:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-beanutils-1.8.2.jar");
                        new resourceDownload().downloadLibs("commons-beanutils-1.8.2.jar","http://download948.mediafire.com/8wfgp1dibp1g/1bjokubhviqd7oy/commons-beanutils-1.8.2.jar");
                        break;
                    case 10:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-collections-20040616.jar");
                        new resourceDownload().downloadLibs("commons-collections-20040616.jar","http://download1321.mediafire.com/g2n95cokmvvg/iubx1pu950sqkuh/commons-collections-20040616.jar");
                        break;
                    case 15:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-dbutils-1.7.jar");
                        new resourceDownload().downloadLibs("commons-dbutils-1.7.jar","https://download1640.mediafire.com/51t9hl9efblg/u596d8tvooae3x3/commons-dbutils-1.7.jar");
                        break;
                    case 20:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-digester-2.1.jar");
                        new resourceDownload().downloadLibs("commons-digester-2.1.jar","https://download944.mediafire.com/azl77y51rrvg/8s1tfq5qbqgrte2/commons-digester-2.1.jar");
                        break;
                    case 25:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-logging-1.2.1.1.jar");
                        new resourceDownload().downloadLibs("commons-logging-1.2.1.1.jar","https://download946.mediafire.com/1kyfaheeydmg/t5q1fpls44cy9dx/commons-logging-1.2.1.1.jar");
                        break;
                    case 30:
                        jLabel2.setText(null);
                        jLabel2.setText("dms-19.3.0.0.jar");
                        new resourceDownload().downloadLibs("dms-19.3.0.0.jar","https://download1511.mediafire.com/h6gc1wkgooeg/hr0vah81v4umv6e/dms-19.3.0.0.jar");
                        break;
                    case 35:
                        jLabel2.setText(null);
                        jLabel2.setText("groovy-all-2.4.5.jar");
                        new resourceDownload().downloadLibs("groovy-all-2.4.5.jar","https://download1510.mediafire.com/y7cxz5afzdkg/rbpscmclmf08nbm/groovy-all-2.4.5.jar");
                        break;
                    case 40:
                        jLabel2.setText(null);
                        jLabel2.setText("iText-2.1.7.jar");
                        new resourceDownload().downloadLibs("iText-2.1.7.jar","https://download1593.mediafire.com/au9c8tsbyd1g/pe6hhwfpk0c0d2g/iText-2.1.7.jar");
                        break;
                    case 45:
                        jLabel2.setText(null);
                        jLabel2.setText("itext-pdfa-5.5.4.jar");
                        new resourceDownload().downloadLibs("itext-pdfa-5.5.4.jar","https://download1588.mediafire.com/da3dxv87t6kg/qlvfop758tinnqo/itext-pdfa-5.5.4.jar");
                        break;
                    case 50:
                        jLabel2.setText(null);
                        jLabel2.setText("itextpdf-5.5.4.jar");
                        new resourceDownload().downloadLibs("itextpdf-5.5.4.jar","https://download1583.mediafire.com/v9zo9rbdc6yg/65kk9h26u5pvm8g/itextpdf-5.5.4.jar");
                        break;
                    case 55:
                        jLabel2.setText(null);
                        jLabel2.setText("jasperreports-4.7.1.jar");
                        new resourceDownload().downloadLibs("jasperreports-4.7.1.jar","https://download940.mediafire.com/6uka89u4l6kg/jg4oi1ukqmlgrcw/jasperreports-4.7.1.jar");
                        break;
                    case 60:
                        jLabel2.setText(null);
                        jLabel2.setText("jasperreports-javaflow-4.7.1.jar");
                        new resourceDownload().downloadLibs("jasperreports-javaflow-4.7.1.jar","https://download851.mediafire.com/twxlygkuhzag/n98i3o1cgzz9a1o/jasperreports-javaflow-4.7.1.jar");
                        break;
                    case 65:
                        jLabel2.setText(null);
                        jLabel2.setText("mysql-connector-java-8.0.17.jar");
                        new resourceDownload().downloadLibs("mysql-connector-java-8.0.17.jar","https://download1481.mediafire.com/h3u03fcda5ig/vlith3vil9706rs/mysql-connector-java-8.0.17.jar");
                        break;
                    case 70:
                        jLabel2.setText(null);
                        jLabel2.setText("oclc-dbutils-1.0.20080317.jar");
                        new resourceDownload().downloadLibs("oclc-dbutils-1.0.20080317.jar","https://download1585.mediafire.com/ed75mq82mc5g/s9462u1mtqvnv2n/oclc-dbutils-1.0.20080317.jar");
                        break;
                    case 75:
                        jLabel2.setText(null);
                        jLabel2.setText("ojdbc6_g.jar");
                        new resourceDownload().downloadLibs("ojdbc6_g.jar","https://download844.mediafire.com/t6d607ppm88g/ai1wzj7z8azvyl5/ojdbc6_g.jar");
                        break;
                    case 80:
                        jLabel2.setText(null);
                        jLabel2.setText("poi-3.5-FINAL.jar");
                        new resourceDownload().downloadLibs("poi-3.5-FINAL.jar","https://download1505.mediafire.com/9rco39egrdng/cpc8b9obnl4ob0o/poi-3.5-FINAL.jar");
                        break;
                    case 85:
                        jLabel2.setText(null);
                        jLabel2.setText("rs2xml.jar");
                        new resourceDownload().downloadLibs("rs2xml.jar","https://download850.mediafire.com/f9hgcuvbvy4g/dvc94za7iesvz0f/rs2xml.jar");
                        break;
                    case 90:
                        jLabel2.setText(null);
                        jLabel2.setText("PlaceHolder.jar");
                        new resourceDownload().downloadLibs("PlaceHolder.jar","https://download941.mediafire.com/fhzz83vhg0pg/mykmzydwe3am1x4/PlaceHolder.jar");
                        break;
                    default:
                        new logger().staticLogger("Error 39: no se puede descargar las librerías",Level.WARNING);
                }
            }else{
                t.stop();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                new menuVentanas().setVisible(true);
                dispose();
            }
        };
        t=new Timer(115,al);
        t.start();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());
        setUndecorated(true);

        jProgressBar1.setMaximum(115);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cargando:");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new loadWindow().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}