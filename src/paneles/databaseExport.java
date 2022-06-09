package paneles;
//clases
import clases.laf;
import clases.logger;
import clases.thread;
import venPrimarias.start;
//java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
        new laf().LookAndFeel(databaseExport.this,databaseExport.class.getName(),"databaseExport");
        
        botones();
    }
    
    protected File f;
    protected InputStream is;
    protected OutputStream os;
    
    protected final void botones(){
        closeButton.addActionListener((ae)->{
            setVisible(false);
        });
        
        exportButton.addActionListener((ae)->{
            new Thread(new exportDB()).start();
        });
    }
    
    protected class exportDB implements Runnable{
        @Override
        public void run(){
            String nombreUsuario=jTextField1.getText();
            String passUsuario=jPasswordField1.getPassword().toString();
            String based=jTextField3.getText();
            String nombrebdExportada=based+(int)(Math.random()*1000)+".sql";
            
            try{
                Properties p=new Properties();
                p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/databaseInfo.properties"));
                Process pr=Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqldump.exe -u "+nombreUsuario+" -p "+passUsuario+" -h "+p.getProperty("ip")+" "+based);
                
                is=pr.getInputStream();
                os=new FileOutputStream(new File(System.getProperty("user.dir")+"/src/data/database/MySQL/"+nombrebdExportada));
                
                new Thread(new thread(is,os)).start();
                
                JOptionPane.showMessageDialog(null,"Se ha exportado correctamente la base de datos","Rel 3E",JOptionPane.INFORMATION_MESSAGE);
                new logger().staticLogger("Rel 3E: se exportó correctamente la base de datos.\nOcurrió en la clase '"+exportDB.class.getName()+"', en el método 'run()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
                
                os.close();
                os.flush();
                is.close();
            }catch(IOException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 8E",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 8E: "+e.getMessage()+".\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'run()'",Level.WARNING);
                new logger().exceptionLogger(exportDB.class.getName(),Level.WARNING,"run-8E",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+databaseExport.class.getName()+"', en el método 'run()'",Level.WARNING);
                new logger().exceptionLogger(exportDB.class.getName(),Level.WARNING,"run-0",x.fillInStackTrace());
            }
        }
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(exportButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                        .addComponent(closeButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                            .addComponent(jPasswordField1)
                            .addComponent(jTextField3))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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