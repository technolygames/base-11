package paneles;
//clases
import clases.logger;
import clases.dirs;
import clases.thread1;
import clases.thread3;
import venPrimarias.start;
//java
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class databaseExport extends javax.swing.JPanel{
    public databaseExport(){
        initComponents();
        
        botones();
    }
    
    protected OutputStream os;
    
    protected String userdir=dirs.userdir;
    
    protected final void botones(){
        closeButton.addActionListener((ae)->{
            setVisible(false);
        });
        
        exportButton.addActionListener((ae)->{
            exportDatabase();
        });
    }
    
    protected void exportDatabase(){
        new Thread(()->{
            String nombreUsuario=jTextField1.getText();
            String passUsuario=jPasswordField1.getPassword().toString();
            String based=jTextField3.getText();
            String nombrebdExportada=based+(int)(Math.random()*1000)+".sql";
            String dir=userdir+"/data/database/MySQL/"+nombrebdExportada;
            
            try{
                Properties p=new Properties();
                p.load(new FileInputStream(userdir+"/data/config/databaseInfo.properties"));
                Process pr=Runtime.getRuntime().exec("cmd /c mysqldump.exe -u "+nombreUsuario+" -p "+based+">"+dir+" -password="+passUsuario+" -h "+p.getProperty("ip"));
                new Thread(new thread3(pr.getErrorStream())).start();
                
                os=new FileOutputStream(dir);
                
                new Thread(new thread1(pr.getInputStream(),os)).start();
                
                JOptionPane.showMessageDialog(null,"Se ha exportado correctamente la base de datos","Rel 3E",JOptionPane.INFORMATION_MESSAGE);
                new logger(Level.INFO).staticLogger("Rel 3E: se exportó correctamente la base de datos.\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'exportDatabase()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                
                os.flush();
                os.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 8E",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 8E: "+e.getMessage()+".\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'exportDatabase()'");
                new logger(Level.SEVERE).exceptionLogger(databaseExport.class.getName(),"exportDatabase-8E",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'exportDatabase()'");
                new logger(Level.SEVERE).exceptionLogger(databaseExport.class.getName(),"exportDatabase-0",x.fillInStackTrace());
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