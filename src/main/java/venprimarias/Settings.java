package venprimarias;
//clases
import clases.MediaHandler;
import clases.InternalLogger;
import clases.Dirs;
import clases.Thread1;
import clases.Validation;
import java.awt.EventQueue;
import java.awt.Frame;
//java
import java.awt.Image;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//extension larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Settings extends javax.swing.JFrame{
    public Settings(){
        initComponents();
        new MediaHandler().setLookAndFeel(Settings.this);
        
        configIn();
        botones();
        combo();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Configuración");
        setResizable(false);
        pack();
    }
    
    protected File f1;
    protected Properties p;
    protected JFileChooser jfc;
    
    protected String icono;
    protected String nombre;
    protected String design;
    protected String userdir=Dirs.USERDIR;
    protected String imagenes;
    protected String nombreArchivo1;
    protected String nombreArchivo2;
    
    protected void settings(){
        jLabel5.setText("Advertencia: la imagen y el ícono no son lo mismo. Asegúrate que hayas cambiado ambos, en caso de que lo hayas hecho");
    }
    
    protected final void configIn(){
        p=new Properties();
        try(FileReader fr=new FileReader("data/config/config.properties",StandardCharsets.UTF_8)){
            p.load(fr);
            
            imagenes=p.getProperty("imagenes");
            File f2=new File(imagenes);
            nombreArchivo1=f2.getName();
            
            icono=p.getProperty("icono");
            File f3=new File(icono);
            nombreArchivo2=f3.getName();
            
            design=p.getProperty("look_and_feel");
            nombre=p.getProperty("nombre");
            
            if(!new File(imagenes).exists()){
                imagenes=p.getProperty("imagen_respaldo");
                File k=new File(imagenes);
                nombreArchivo1=k.getName();
            }
            
            if(!new File(icono).exists()){
                icono=p.getProperty("icono_respaldo");
                File k=new File(icono);
                nombreArchivo2=k.getName();
            }
            
            ImageIcon im=new ImageIcon(imagenes);
            Icon l=new ImageIcon(im.getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT));
            jLabel3.setIcon(l);
            jTextField1.setText(nombre);
            jComboBox1.getModel().setSelectedItem(design);
        }catch(NumberFormatException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(FileNotFoundException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }catch(IOException n){
            new InternalLogger().catchException(this,n,Level.SEVERE);
        }catch(NullPointerException k){
            new InternalLogger().catchException(this,k,Level.SEVERE);
        }
    }
    
    protected final void botones(){
        p=new Properties();
        
        backButton.addActionListener(a->{
            setVisible(false);
            dispose();
        });
        
        iconButton.addActionListener(a->{
            try(FileInputStream fis=new FileInputStream("data/config/filechooserd.properties");
                    FileOutputStream fos=new FileOutputStream("data/config/filechooserd.properties")){
                p=new Properties();
                p.load(fis);
                jfc=new JFileChooser(p.getProperty("lastdirectory_icon"));
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo PNG","png"));
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(this)){
                    File f3=jfc.getSelectedFile();
                    icono=f3.getPath();
                    nombreArchivo2=f3.getName();

                    p.setProperty("lastdirectory_icon",f3.getParent());
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
        
        imgButton.addActionListener(a->{
            try(FileInputStream fis=new FileInputStream("data/config/filechooserd.properties");
                    FileOutputStream fos=new FileOutputStream("data/config/filechooserd.properties")){
                p=new Properties();
                p.load(fis);
                jfc=new JFileChooser(p.getProperty("lastdirectory_image"));
                
                for(FileNameExtensionFilter filtro:new FileNameExtensionFilter[]{new FileNameExtensionFilter("Archivo PNG","png"),new FileNameExtensionFilter("Archivo JPG","jpg"),new FileNameExtensionFilter("Archivo JPEG","jpeg")}){
                    jfc.setFileFilter(filtro);
                }
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(this)){
                    File f2=jfc.getSelectedFile();
                    imagenes=f2.getPath();
                    nombreArchivo1=f2.getName();

                    jLabel3.setIcon(new ImageIcon(new ImageIcon(imagenes).getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT)));

                    p.setProperty("lastdirectory_image",f2.getParent());
                    p.store(fos,"JFil eChooserDirection");
                }
            }catch(HeadlessException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(FileNotFoundException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }catch(IOException n){
                new InternalLogger().catchException(this,n,Level.SEVERE);
            }
        });
        
        jComboBox1.addActionListener(a->{
            try{
                design=jComboBox1.getSelectedItem().toString();
                UIManager.setLookAndFeel(design);
                for(Frame frame:Frame.getFrames()){
                    SwingUtilities.updateComponentTreeUI(frame);
                    frame.pack();
                }
            }catch(ClassNotFoundException e){
                new InternalLogger().catchException(this,e,Level.SEVERE);
            }catch(IllegalAccessException x){
                new InternalLogger().catchException(this,x,Level.SEVERE);
            }catch(InstantiationException n){
                new InternalLogger().catchException(this,n,Level.SEVERE);
            }catch(UnsupportedLookAndFeelException k){
                new InternalLogger().catchException(this,k,Level.SEVERE);
            }
        });
        
        schButton.addActionListener(a->
                configOut()
        );
        
        toolsButton.addActionListener(a->
                new Validation(new AdminTools(),Start.USER_ROLE,AdminTools.class.getName()).toRestrictedForm()
        );
    }
    
    static{
        /*
        Aquí van los look and feel para ser instalado
        Here goes look and feel to be install
        */
    }
    
    protected final void combo(){
        try{
            for(UIManager.LookAndFeelInfo lafi1:UIManager.getInstalledLookAndFeels()){
                jComboBox1.addItem(lafi1.getClassName());
            }
        }catch(NumberFormatException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(IllegalArgumentException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }catch(NullPointerException n){
            new InternalLogger().catchException(this,n,Level.SEVERE);
        }
    }
    
    protected final void configOut(){
        p=new Properties();
        f1=new File("data/config/config.properties");
        String dato1=userdir+"\\data\\media\\forms\\copy\\";
        String dato2=userdir+"\\data\\media\\icon\\copy\\";
        try(FileWriter fw=new FileWriter("data/config/config.properties",StandardCharsets.UTF_8)){
            if(f1.exists()){
                p.setProperty("imagenes",imagenes);
                
                try(FileInputStream fis=new FileInputStream(imagenes);
                        FileOutputStream fos=new FileOutputStream(dato1+nombreArchivo1)){
                    new Thread(new Thread1(fis,fos)).start();
                }
                
                p.setProperty("imagen_respaldo",dato1+nombreArchivo1);
                p.setProperty("look_and_feel",design);
                p.setProperty("icono",icono);
                p.setProperty("icono_respaldo",dato2+nombreArchivo2);
                p.setProperty("nombre",jTextField1.getText());
                
                try(FileInputStream fis=new FileInputStream(icono);
                        FileOutputStream fos=new FileOutputStream(dato2+nombreArchivo2)){
                    new Thread(new Thread1(fis,fos)).start();
                }
                
                p.store(fw,"config1");
                
                JOptionPane.showMessageDialog(this,"Se guardaron correctamente","Rel 4",JOptionPane.INFORMATION_MESSAGE);
                InternalLogger.staticLogger(Level.INFO,"Rel 4: se han guardado las condiguraciones.");
            }else{
                f1.createNewFile();
            }
        }catch(FileNotFoundException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(NumberFormatException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }catch(NullPointerException n){
            new InternalLogger().catchException(this,n,Level.SEVERE);
        }catch(IOException k){
            new InternalLogger().catchException(this,k,Level.SEVERE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        imgButton = new javax.swing.JButton();
        schButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        iconButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        toolsButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        jLabel2.setText("Imagen:");

        imgButton.setText("Seleccionar imagen");

        schButton.setText("Guardar cambios");

        backButton.setText("Regresar");

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Look And Feel:");

        jLabel6.setText("Icono de ventana:");

        iconButton.setText("Seleccionar icono");

        jLabel7.setText("Nombre del programa:");

        jLabel1.setText("Administrador:");

        toolsButton.setText("Herramientas");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("jLabel5");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(schButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextField1))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(iconButton)
                                                .addGap(24, 24, 24)))
                                        .addGap(0, 213, Short.MAX_VALUE)))
                                .addGap(10, 10, 10))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(toolsButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(imgButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(iconButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(toolsButton))))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgButton)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 79, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(schButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
                new Settings().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton iconButton;
    private javax.swing.JButton imgButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton schButton;
    private javax.swing.JButton toolsButton;
    // End of variables declaration//GEN-END:variables
}