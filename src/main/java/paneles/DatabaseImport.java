package paneles;
//clases
import clases.InternalLogger;
import clases.Thread1;
import clases.Thread3;
import venterciarias.DatabaseWindow2;
//java
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DatabaseImport extends javax.swing.JPanel{
    public DatabaseImport(){
        initComponents();
        
        botones();
        configIn();
    }
    
    protected Properties p;
    
    protected String database;
    protected String host;
    protected String user;
    protected String pass;
    
    protected final void configIn(){
        jTextField3.setText(DatabaseWindow2.nombredb);
        try(FileInputStream fis=new FileInputStream("data/config/databaseInfo.properties")){
            p=new Properties();
            p.load(fis);
            
            database=p.getProperty("database");
            host=p.getProperty("ip");
            user=p.getProperty("user");
            pass=p.getProperty("pass");
            
            jTextField1.setText(user);
            jPasswordField1.setText(pass);
            
            p.clear();
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
        
        fileButton.addActionListener(a->{
            try(FileInputStream fis=new FileInputStream("data/config/filechooserd.properties");
                    FileOutputStream fos=new FileOutputStream("data/config/filechooserd.properties")){
                p=new Properties();
                p.load(fis);
                JFileChooser chooser=new JFileChooser(p.getProperty("lastdirectory_database_import"));
                
                chooser.setMultiSelectionEnabled(false);
                chooser.setFileFilter(new FileNameExtensionFilter("Archivo SQL","sql"));
                chooser.setAcceptAllFileFilterUsed(false);
                
                if(JFileChooser.APPROVE_OPTION==chooser.showOpenDialog(this)){
                    File f=chooser.getSelectedFile();
                    
                    jTextField2.setText(f.getPath());
                    
                    p.setProperty("lastdirectory_database_import",f.getParent());
                    p.store(fos,"JFileChooserDirection");
                }
            }catch(IOException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }
        });
        
        importButton.addActionListener(a->{
            configOut();
            importDatabase();
        });
    }
    
    protected void importDatabase(){
        new Thread(()->{
            String user1=jTextField1.getText();
            String pass1=String.valueOf(jPasswordField1.getPassword());
            String db=jTextField3.getText();
            String dbDir=jTextField2.getText();
            
            try(FileInputStream fis=new FileInputStream("data/config/env.properties")){
                p=new Properties();
                p.load(fis);
                
                Process pr=Runtime.getRuntime().exec("cmd /c "+p.getProperty("local_mysql")+"mysql.exe --user="+user1+" -p "+db+"<"+dbDir+" --password="+pass1+" --host="+host);
                try(InputStream is=pr.getErrorStream()){
                    new Thread(new Thread3(is)).start();
                }
                
                try(FileInputStream fis2=new FileInputStream(dbDir);
                        OutputStream os=pr.getOutputStream()){
                    new Thread(new Thread1(fis2,os)).start();
                }
                
                JOptionPane.showMessageDialog(this,"Se ha importado correctamente la base de datos","Rel 2E",JOptionPane.INFORMATION_MESSAGE);
                InternalLogger.staticLogger(Level.INFO,"Rel 2E: se importó correctamente la base de datos.");
            }catch(IOException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(NullPointerException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }
        }).start();
    }
    
    protected void configOut(){
        if(!jTextField3.getText().equals(database)){
            int val=JOptionPane.showConfirmDialog(this,"¿Deseas usar la base de datos importada como principal?","Notice 1",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE);
            if(val==0){
                try(FileInputStream fis=new FileInputStream("data/config/databaseInfo.properties");
                        FileOutputStream fos=new FileOutputStream("data/config/databaseInfo.properties")){
                    p=new Properties();
                    p.load(fis);
                    p.setProperty("database",jTextField3.getText());
                    p.store(fos,"DatabaseConfig");
                }catch(FileNotFoundException e){
                    new InternalLogger().catchException(this,e,Level.SEVERE);
                }catch(IOException x){
                    new InternalLogger().catchException(this,x,Level.SEVERE);
                }
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        fileButton = new javax.swing.JButton();
        createButton = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jTextField2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        importButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();

        fileButton.setText("Buscar");

        createButton.setText("Crear base de datos");

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        jLabel3.setText("Base de datos a usar:");

        importButton.setText("Importar");

        closeButton.setText("Cerrar panel");

        jLabel4.setText("Base de datos a importar:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE)
                                    .addComponent(jPasswordField1)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(closeButton))
                                    .addComponent(jTextField2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fileButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(importButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(fileButton)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(importButton)
                    .addComponent(closeButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton createButton;
    private javax.swing.JButton fileButton;
    private javax.swing.JButton importButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    public static javax.swing.JTextField jTextField3;
    // End of variables declaration//GEN-END:variables
}