package venprimarias;
//clases
import clases.Datos;
import clases.InternalLogger;
import clases.MediaHandler;
import clases.mvc.MvcForm3;
import menus.MenuDatosVentana3;
//java
import java.awt.Image;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
//con extensión larga
import java.util.logging.Level;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Formulario3 extends javax.swing.JFrame{
    public Formulario3(){
        initComponents();
        new MediaHandler().setLookAndFeel(Formulario3.this);
        
        botones();
        
        setLocationRelativeTo(null);
        setTitle("Formulario 3");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    protected JTextField textfields;
    protected JFileChooser jfc;
    
    protected String path;
    
    protected final void botones(){
        for(JTextField tf:new JTextField[]{jTextField1,jTextField2,jTextField3,jTextField4,jTextField5,jTextField6}){
            textfields=tf;
        }
        
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener(a->
            new MenuDatosVentana3().setVisible(true)
        );
        
        jMenuItem2.addActionListener(a->
            clearImage()
        );
        
        miClearFields.addActionListener(a->{
            for(JTextField tf:new JTextField[]{jTextField1,jTextField2,jTextField3,jTextField4,jTextField5,jTextField6}){
                tf.setText("");
            }
            clearImage();
        });
        
        miInsImage.addActionListener(a->{
            try(FileInputStream fis=new FileInputStream("data/config/filechooserd.properties");
                    FileOutputStream fos=new FileOutputStream("data/config/filechooserd.properties")){
                p=new Properties();
                p.load(fis);
                jfc=new JFileChooser(p.getProperty("lastdirectory_form3"));
                
                jfc.setMultiSelectionEnabled(false);
                jfc.setAcceptAllFileFilterUsed(false);
                jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(this)){
                    File f=jfc.getSelectedFile();
                    showImage(f.getPath());
                    
                    p.setProperty("lastdirectory_form3",f.getParent());
                    p.store(fos,"JFileChooserDirection");
                }
            }catch(HeadlessException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(FileNotFoundException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }catch(IOException n){
                new InternalLogger().catchException(this,n,Level.SEVERE);
            }
        });
        
        svdtButton.addActionListener(a->{
            try{
                if(!textfields.getText().isEmpty()||picLabel.getIcon()!=null){
                    List<MvcForm3> datos=new ArrayList<>();
                    MvcForm3 modelo=new MvcForm3();
                    
                    modelo.setCodigo(Integer.parseInt(jTextField1.getText()));
                    modelo.setNombre(jTextField2.getText());
                    modelo.setApellidoPaterno(jTextField3.getText());
                    modelo.setApellidoMaterno(jTextField4.getText());
                    modelo.setEmpresa(jTextField5.getText());
                    modelo.setContacto(Integer.parseInt(jTextField6.getText()));
                    modelo.setImagen(new FileInputStream(path));
                    
                    datos.add(modelo);
                    
                    new Datos().insertarDatosProveedor(datos);
                }else{
                    new InternalLogger().storeError18(this,Level.WARNING);
                }
            }catch(FileNotFoundException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(NullPointerException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }catch(NumberFormatException n){
                new InternalLogger().catchException(this,n,Level.SEVERE);
            }
        });
    }
    
    protected void clearImage(){
        picLabel.setIcon(null);
        picLabel.setText("Foto");
    }
    
    protected void showImage(String path){
        this.path=path;
        picLabel.setText(null);
        picLabel.setIcon(new ImageIcon(new ImageIcon(path).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        svdtButton = new javax.swing.JButton();
        picLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miInsImage = new javax.swing.JMenuItem();
        miClearFields = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        backButton.setText("Regresar");

        jLabel1.setText("Código del proveedor:");

        jLabel2.setText("Nombre(s) del proveedor:");

        jLabel3.setText("Apellido paterno:");

        jLabel4.setText("Apellido materno:");

        jLabel5.setText("Empresa:");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField3KeyPressed(evt);
            }
        });

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });

        svdtButton.setText("Guardar datos");

        picLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picLabel.setText("Foto");
        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Contacto:");

        jTextField6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField6KeyPressed(evt);
            }
        });

        jMenu1.setText("Datos");

        jMenuItem1.setText("Menú de Datos");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones");

        miInsImage.setText("Insertar imagen");
        jMenu2.add(miInsImage);

        miClearFields.setText("Limpiar campos");
        jMenu2.add(miClearFields);

        jMenuItem2.setText("Limpiar foto");
        jMenu2.add(jMenuItem2);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(svdtButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(svdtButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new InternalLogger().storeNumberInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyPressed
    
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new InternalLogger().storeNumberInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyPressed
    
    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new InternalLogger().storeNumberInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyPressed
    
    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            new InternalLogger().storeNumberInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_jTextField5KeyPressed
    
    private void jTextField6KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField6KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            new InternalLogger().storeLetterInputWarning(this,Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_jTextField6KeyPressed
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new Formulario3().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JMenuItem miClearFields;
    private javax.swing.JMenuItem miInsImage;
    private javax.swing.JLabel picLabel;
    private javax.swing.JButton svdtButton;
    // End of variables declaration//GEN-END:variables
}