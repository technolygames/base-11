package venSecundarias;
//clases
import clases.logger;
import clases.Icono;
import clases.laf;
import clases.resourceDownload;//still in use
import menus.menuVentanas;
//java
import java.awt.Cursor;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.Timer;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

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
        p=new Properties();
        try{
            p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/libs.properties"));
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'load()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"load-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+loadWindow.class.getName()+"', en el método 'load()'",Level.WARNING);
            new logger().exceptionLogger(loadWindow.class.getName(),Level.WARNING,"load-2IO",x.fillInStackTrace());
        }
        
        t=new Timer(120,(a)->{
            if(jProgressBar1.getValue()<105){
                int valor=jProgressBar1.getValue();
                jProgressBar1.setValue(valor+5);
                jProgressBar1.setMaximum(105);
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                switch(valor){
                    case 0:
                        jLabel2.setText(null);
                        jLabel2.setText("bcmail-jdk14-1.38.jar");
                        //new resourceDownload().downloadLibs("bcmail-jdk14-1.38.jar",p.getProperty("bcmail-jdk14-1.38.jar"));
                        break;
                    case 5:
                        jLabel2.setText(null);
                        jLabel2.setText("bcmail-jdk14-138.jar");
                        //new resourceDownload().downloadLibs("bcmail-jdk14-138.jar",p.getProperty("bcmail-jdk14-138.jar"));
                        break;
                    case 10:
                        jLabel2.setText(null);
                        jLabel2.setText("bcprov-jdk14-1.38.jar");
                        //new resourceDownload().downloadLibs("bcprov-jdk14-1.38.jar",p.getProperty("bcprov-jdk14-1.38.jar"));
                        break;
                    case 15:
                        jLabel2.setText(null);
                        jLabel2.setText("bcprov-jdk14-138.jar");
                        //new resourceDownload().downloadLibs("bcprov-jdk14-138.jar",p.getProperty("bcprov-jdk14-138.jar"));
                        break;
                    case 20:
                        jLabel2.setText(null);
                        jLabel2.setText("bctsp-jdk14-1.38.jar");
                        //new resourceDownload().downloadLibs("bctsp-jdk14-1.38.jar",p.getProperty("bctsp-jdk14-1.38.jar"));
                        break;
                    case 25:
                        jLabel2.setText(null);
                        jLabel2.setText("castor-1.2.jar");
                        //new resourceDownload().downloadLibs("castor-1.2.jar",p.getProperty("castor-1.2.jar"));
                        break;
                    case 30:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-beanutils-1.8.2.jar");
                        //new resourceDownload().downloadLibs("commons-beanutils-1.8.2.jar",p.getProperty("commons-beanutils-1.8.2.jar"));
                        break;
                    case 35:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-collections-2.1.jar");
                        //new resourceDownload().downloadLibs("commons-collections-2.1.jar",p.getProperty("commons-collections-2.1.jar"));
                        break;
                    case 40:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-digester-2.1.jar");
                        //new resourceDownload().downloadLibs("commons-digester-2.1.jar",p.getProperty("commons-digester-2.1.jar"));
                        break;
                    case 45:
                        jLabel2.setText(null);
                        jLabel2.setText("commons-logging-1.2.1.1.jar");
                        //new resourceDownload().downloadLibs("commons-logging-1.2.1.1.jar",p.getProperty("commons-logging-1.2.1.1.jar"));
                        break;
                    case 50:
                        jLabel2.setText(null);
                        jLabel2.setText("iText-2.1.7.jar");
                        //new resourceDownload().downloadLibs("iText-2.1.7.jar",p.getProperty("iText-2.1.7.jar"));
                        break;
                    case 55:
                        jLabel2.setText(null);
                        jLabel2.setText("jackson-annotations-2.1.4.jar");
                        //new resourceDownload().downloadLibs("jackson-annotations-2.1.4.jar",p.getProperty("jackson-annotations-2.1.4.jar"));
                        break;
                    case 60:
                        jLabel2.setText(null);
                        jLabel2.setText("jackson-core-2.1.4.jar");
                        //new resourceDownload().downloadLibs("jackson-core-2.1.4.jar",p.getProperty("jackson-core-2.1.4.jar"));
                        break;
                    case 65:
                        jLabel2.setText(null);
                        jLabel2.setText("jackson-databind-2.1.4.jar");
                        //new resourceDownload().downloadLibs("jackson-databind-2.1.4.jar",p.getProperty("jackson-databind-2.1.4.jar"));
                        break;
                    case 70:
                        jLabel2.setText(null);
                        jLabel2.setText("jasperreports-5.5.0.jar");
                        //new resourceDownload().downloadLibs("jasperreports-5.5.0.jar",p.getProperty("jasperreports-5.5.0.jar"));
                        break;
                    case 75:
                        jLabel2.setText(null);
                        jLabel2.setText("jcommon-1.0.15.jar");
                        //new resourceDownload().downloadLibs("jcommon-1.0.15.jar",p.getProperty("jcommon-1.0.15.jar"));
                        break;
                    case 80:
                        jLabel2.setText(null);
                        jLabel2.setText("jdtcore-3.1.0.jar");
                        //new resourceDownload().downloadLibs("jdtcore-3.1.0.jar",p.getProperty("jdtcore-3.1.0.jar"));
                        break;
                    case 85:
                        jLabel2.setText(null);
                        jLabel2.setText("jfreechart-1.0.12.jar");
                        //new resourceDownload().downloadLibs("jfreechart-1.0.12.jar",p.getProperty("jfreechart-1.0.12.jar"));
                        break;
                    case 90:
                        jLabel2.setText(null);
                        jLabel2.setText("mysql-connector-java-8.0.17.jar");
                        //new resourceDownload().downloadLibs("mysql-connector-java-8.0.17.jar",p.getProperty("mysql-connector-java-8.0.17.jar"));
                        break;
                    case 95:
                        jLabel2.setText(null);
                        jLabel2.setText("PlaceHolder.jar");
                        //new resourceDownload().downloadLibs("PlaceHolder.jar",p.getProperty("PlaceHolder.jar"));
                        break;
                    case 100:
                        jLabel2.setText(null);
                        jLabel2.setText("rs2xml.jar");
                        //new resourceDownload().downloadLibs("rs2xml.jar",p.getProperty("rs2xml.jar"));
                        break;
                    case 105:
                        jLabel2.setText(null);
                        jLabel2.setText("xml-apis-1.3.02.jar");
                        //new resourceDownload().downloadLibs("xml-apis-1.3.02.jar",p.getProperty("xml-apis-1.3.02.jar"));
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
        });
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

        jProgressBar1.setMaximum(90);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cargando:");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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