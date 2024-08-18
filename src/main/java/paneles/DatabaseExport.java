package paneles;
//clases
import clases.InternalLogger;
import clases.Dirs;
import clases.Thread1;
import clases.Thread3;
//java
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class DatabaseExport extends javax.swing.JPanel{
    public DatabaseExport(){
        initComponents();
        
        botones();
        configIn();
    }
    
    protected Properties p;
    
    protected String database;
    protected String host;
    protected String pass;
    protected String user;
    
    protected final void configIn(){
        try(FileInputStream fis=new FileInputStream("data/config/databaseInfo.properties")){
            p=new Properties();
            p.load(fis);
            
            database=p.getProperty("database");
            host=p.getProperty("ip");
            pass=p.getProperty("pass");
            user=p.getProperty("user");
            
            jTextField1.setText(user);
            jPasswordField1.setText(pass);
            jTextField3.setText(database);
        }catch(FileNotFoundException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(IOException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->
            setVisible(false)
        );
        
        exportButton.addActionListener(a->
            exportDatabase()
        );
    }
    
    protected void exportDatabase(){
        new Thread(()->{
            String user1=jTextField1.getText();
            String pass2=String.valueOf(jPasswordField1.getPassword());
            String db=jTextField3.getText();
            
            try(FileInputStream fis=new FileInputStream("data/config/databaseInfo.properties")){
                p=new Properties();
                p.load(fis);
                
                String userdir=Dirs.USERDIR;
                
                File f=new File(userdir+"/data/database/MySQL/"+db+".sql");
                
                String path=Dirs.exists(f);
                try(FileOutputStream fos=new FileOutputStream(path)){
                    Process pr=Runtime.getRuntime().exec("cmd /c "+p.getProperty("local_mysql")+"mysqldump.exe --user="+user1+" -p "+db+" --result-file="+path+" --password="+pass2+" --host="+host+" --hex-blob --dump-date --compress");
                    try(InputStream is=pr.getErrorStream()){
                        new Thread(new Thread3(is)).start();
                    }
                    try(InputStream is=pr.getInputStream()){
                        new Thread(new Thread1(is,fos)).start();
                    }
                }
                
                JOptionPane.showMessageDialog(this,"Se ha exportado correctamente la base de datos","Rel 3E",JOptionPane.INFORMATION_MESSAGE);
                InternalLogger.staticLogger(Level.INFO,"Rel 3E: se exportó correctamente la base de datos.");
            }catch(IOException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(NullPointerException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }
        }).start();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exportButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();

        exportButton.setText("Exportar");

        closeButton.setText("Cerrar panel");

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        jLabel3.setText("Base de datos:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jPasswordField1)
                            .addComponent(jTextField3))))
                .addContainerGap(219, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exportButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton exportButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}