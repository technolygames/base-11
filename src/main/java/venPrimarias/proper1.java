package venPrimarias;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import clases.thread;
import java.awt.Frame;
import venTerciarias.valVentanas.validacionVentana7;
//java
import java.awt.Image;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedWriter;
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
import java.awt.event.ActionEvent;
import java.nio.charset.StandardCharsets;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class proper1 extends javax.swing.JFrame{
    public proper1(){
        initComponents();
        new guiMediaHandler(proper1.class.getName()).LookAndFeel(proper1.this);
        
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
    protected InputStream is;
    protected OutputStream os;
    protected JFileChooser jfc;
    
    protected String icono;
    protected String nombre;
    protected String design;
    protected String userdir=datos.userdir;
    protected String imagenes;
    protected String nombreArchivo1;
    protected String nombreArchivo2;
    
    protected void settings(){
        jLabel5.setText("Advertencia: la imagen y el ícono no son lo mismo. Asegúrate que hayas cambiado ambos, en caso de que lo hayas hecho");
    }
    
    protected final void configIn(){
        p=new Properties();
        try{
            p.load(new FileReader(userdir+"/data/config/config.properties",StandardCharsets.UTF_8));
            
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
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configIn()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"configIn-32",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configIn()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"configIn-1IO",x.fillInStackTrace());
        }catch(IOException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configIn()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"configIn-2IO",n.fillInStackTrace());
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+k.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configIn()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"configIn-0",k.fillInStackTrace());
        }
    }
    
    protected final void botones(){
        p=new Properties();
        
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        iconButton.addActionListener((ae)->{
            try{
                p=new Properties();
                p.load(new FileInputStream(userdir+"/data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_icon"));
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo PNG","png"));
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                    try{
                        File f3=jfc.getSelectedFile();
                        icono=f3.getPath();
                        nombreArchivo2=f3.getName();
                        
                        p.setProperty("lastdirectory_icon",f1.getParent());
                        p.store(new BufferedWriter(new FileWriter(userdir+"/data/config/filechooserd.properties")),"JFileChooserDirection");
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.ERROR_MESSAGE);
                        new logger(Level.SEVERE).staticLogger("Error 24: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(iconButton)'");
                        new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.icon-24",e.fillInStackTrace());
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(iconButton)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.icon-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(iconButton)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.icon-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(iconButton)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.icon-2IO",n.fillInStackTrace());
            }
        });
        
        imgButton.addActionListener((ActionEvent ae)->{
            try{
                p=new Properties();
                p.load(new FileInputStream(userdir+"/data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_image"));
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo PNG","png"));
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo JPG","jpg"));
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo JPEG","jpeg"));
                
                int ñ=jfc.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==ñ){
                    try{
                        File f2=jfc.getSelectedFile();
                        imagenes=f2.getPath();
                        nombreArchivo1=f2.getName();
                        
                        ImageIcon im=new ImageIcon(imagenes);
                        Icon l=new ImageIcon(im.getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT));
                        jLabel3.setIcon(l);
                        
                        p.setProperty("lastdirectory_image",f1.getParent());
                        p.store(new BufferedWriter(new FileWriter(userdir+"/data/config/filechooserd.properties")),"JFil eChooserDirection");
                    }catch(IOException x){
                        JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 24",JOptionPane.ERROR_MESSAGE);
                        new logger(Level.SEVERE).staticLogger("Error 24: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(imgButton)'");
                        new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.img-24",x.fillInStackTrace());
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(imgButton)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.img-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(imgButton)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.img-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(imgButton)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.img-2IO",n.fillInStackTrace());
            }
        });
        
        jComboBox1.addActionListener((ae)->{
            try{
                design=jComboBox1.getSelectedItem().toString();
                UIManager.setLookAndFeel(design);
                for(Frame frame:Frame.getFrames()){
                    SwingUtilities.updateComponentTreeUI(frame);
                    frame.pack();
                }
            }catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error CNFE: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(jComboBox2)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.combo2-CNFE",e.fillInStackTrace());
            }catch(IllegalAccessException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IAE",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error IAE: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(jComboBox2)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.combo2-IAE",x.fillInStackTrace());
            }catch(InstantiationException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error IE",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error IE: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(jComboBox2)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.combo2-IE",n.fillInStackTrace());
            }catch(UnsupportedLookAndFeelException k){
                JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 28",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 28: "+k.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'botones(jComboBox2)'");
                new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"botones.combo2-28",k.fillInStackTrace());
            }
        });
        
        schButton.addActionListener((ae)->{
            configOut();
        });
        
        toolsButton.addActionListener((a)->{
            new validacionVentana7(new javax.swing.JFrame(),true).setVisible(true);
        });
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
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'combo1()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"combo1-32",e.fillInStackTrace());
        }catch(IllegalArgumentException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 34",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 34: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'combo1()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"combo1-34",x.fillInStackTrace());
        }catch(NullPointerException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'combo1()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"combo1-0",n.fillInStackTrace());
        }
    }
    
    protected final void configOut(){
        p=new Properties();
        f1=new File(userdir+"/data/config/config.properties");
        String dato1=userdir+"\\data\\media\\forms\\copy\\";
        String dato2=userdir+"\\data\\media\\icon\\copy\\";
        try{
            if(f1.exists()){
                p.setProperty("imagenes",imagenes);
                
                is=new FileInputStream(imagenes);
                os=new FileOutputStream(dato1+nombreArchivo1);
                
                new Thread(new thread(is,os)).start();
                
                p.setProperty("imagen_respaldo",dato1+nombreArchivo1);
                p.setProperty("look_and_feel",design);
                p.setProperty("icono",icono);
                p.setProperty("icono_respaldo",dato2+nombreArchivo2);
                p.setProperty("nombre",jTextField1.getText());
                
                is=new FileInputStream(icono);
                os=new FileOutputStream(dato2+nombreArchivo2);
                
                new Thread(new thread(is,os)).start();
                
                p.store(new BufferedWriter(new FileWriter(userdir+"/data/config/config.properties")),"config1");
                
                JOptionPane.showMessageDialog(null,"Se guardaron correctamente","Rel 4",JOptionPane.INFORMATION_MESSAGE);
                new logger(Level.INFO).staticLogger("Rel 4: se han guardado las condiguraciones.\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'.\nUsuario que hizo los cambios: "+String.valueOf(start.userID));
                
                is.close();
                os.flush();
                os.close();
            }else{
                f1.createNewFile();
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"configOut-1IO",e.fillInStackTrace());
        }catch(NumberFormatException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 32: "+x.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"configOut-32",x.fillInStackTrace());
        }catch(NullPointerException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+n.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"configOut-0",n.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+k.getMessage()+".\nOcurrió en la clase '"+proper1.class.getName()+"', en el método 'configOut()'");
            new logger(Level.SEVERE).exceptionLogger(proper1.class.getName(),"configOut-2IO",k.fillInStackTrace());
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
        setIconImage(new guiMediaHandler(proper1.class.getName()).getIconImage());

        jLabel2.setText("Imagen:");

        imgButton.setText("Seleccionar imagen");

        schButton.setText("Guardar cambios");

        backButton.setText("Regresar");

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(null));

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
        new proper1().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton backButton;
    protected javax.swing.JButton iconButton;
    protected javax.swing.JButton imgButton;
    protected javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    protected javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    protected javax.swing.JButton schButton;
    private javax.swing.JButton toolsButton;
    // End of variables declaration//GEN-END:variables
}