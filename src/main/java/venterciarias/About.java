package venterciarias;
//clases
import clases.MediaHandler;
import clases.InternalLogger;
import clases.Dirs;
//java
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.net.URI;
import java.net.URISyntaxException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
//extension larga
import java.util.logging.Level;

public final class About extends javax.swing.JDialog{
    public About(java.awt.Frame parent, boolean modal){
        super(parent, modal);
        initComponents();
        new MediaHandler().setLookAndFeel(About.this);
        
        etiquetas();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Acerca del programa");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    
    protected String userdir=Dirs.USERDIR;
    
    protected void settings(){
        websiteLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
    
    protected void etiquetas(){
        p=new Properties();
        try(FileReader fr=new FileReader("data/config/acerca.properties",StandardCharsets.UTF_8)){
            p.load(fr);
            
            versionLabel.setText(p.getProperty("version"));
            estableLabel.setText(p.getProperty("estable"));
            inicioLabel.setText(p.getProperty("inicio"));
            finLabel.setText(p.getProperty("fin"));
            devLabel.setText(p.getProperty("dev"));
            pubLabel.setText(p.getProperty("pub"));
            fpubLabel.setText(p.getProperty("fpub"));
            jdkLabel.setText(p.getProperty("jdk"));
            jreLabel.setText(p.getProperty("jre"));
            devonLabel.setText(p.getProperty("devon"));
            websiteLabel.setText(p.getProperty("website"));
        }catch(FileNotFoundException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(IOException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        versionLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        devLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        pubLabel = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        fpubLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jdkLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jreLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        devonLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        estableLabel = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        finLabel = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inicioLabel = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        websiteLabel = new javax.swing.JLabel();

        jLabel7.setText("jLabel7");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        jLabel1.setText("Versión:");

        versionLabel.setText("jLabel1");

        jLabel5.setText("Desarrolladora:");

        devLabel.setText("jLabel5");

        jLabel6.setText("Publicadora:");

        pubLabel.setText("jLabel6");

        jLabel67.setText("Fecha de publicación:");

        fpubLabel.setText("jLabel7");

        jLabel8.setText("Versión de JDK:");

        jdkLabel.setText("jLabel8");

        jLabel9.setText("Versión de JRE");

        jreLabel.setText("jLabel9");

        jLabel10.setText("Dessarrollado en:");

        devonLabel.setText("jLabel10");

        jLabel2.setText("Última versión estable:");

        estableLabel.setText("jLabel2");

        jLabel4.setText("Fin de desarrollo:");

        finLabel.setText("jLabel4");

        jLabel24.setText("Gracias por adquirirlo.");

        jLabel3.setText("Inicio del desarrollo:");

        inicioLabel.setText("jLabel3");

        jLabel11.setText("Página del desarrollador");

        websiteLabel.setText("jLabel11");
        websiteLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                websiteLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel67, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(versionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(estableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(inicioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(finLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(devLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pubLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fpubLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jdkLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(devonLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(websiteLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(versionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(estableLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inicioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(finLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(devLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pubLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(fpubLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jdkLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jreLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(devonLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(websiteLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel23))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void websiteLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_websiteLabelMouseClicked
        p=new Properties();
        try(FileInputStream fis=new FileInputStream("data/config/acerca.properties")){
            p.load(fis);
            Desktop.getDesktop().browse(new URI(p.getProperty("website")));
        }catch(URISyntaxException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }catch(NullPointerException x){
            new InternalLogger().catchException(null,x,Level.SEVERE);
        }catch(FileNotFoundException n){
            new InternalLogger().catchException(null,n,Level.SEVERE);
        }catch(IOException k){
            new InternalLogger().catchException(null,k,Level.SEVERE);
        }
    }//GEN-LAST:event_websiteLabelMouseClicked
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
                new About(new javax.swing.JFrame(),true).setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel devLabel;
    private javax.swing.JLabel devonLabel;
    private javax.swing.JLabel estableLabel;
    private javax.swing.JLabel finLabel;
    private javax.swing.JLabel fpubLabel;
    private javax.swing.JLabel inicioLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jdkLabel;
    private javax.swing.JLabel jreLabel;
    private javax.swing.JLabel pubLabel;
    private javax.swing.JLabel versionLabel;
    private javax.swing.JLabel websiteLabel;
    // End of variables declaration//GEN-END:variables
}