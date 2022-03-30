package venPrimarias;

import clases.datos;
import clases.logger;
import clases.thread;
import venTerciarias.valVentanas.validacionVentana7;
import venTerciarias.valVentanas.validacionVentana8;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class proper1 extends javax.swing.JFrame{
    public proper1(){
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+e.getMessage()+" en '"+proper1.class.getName()+"'", Level.WARNING);
        }catch(ClassNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error CNFE: "+x.getMessage()+" en '"+proper1.class.getName()+"'", Level.WARNING);
        }catch(InstantiationException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
        }catch(IllegalAccessException j){
            JOptionPane.showMessageDialog(null,"Error:\n"+j.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
        }catch(UnsupportedLookAndFeelException t){
            JOptionPane.showMessageDialog(null,"Error:\n"+t.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
        }catch(IOException c){
            JOptionPane.showMessageDialog(null,"Error:\n"+c.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
        }
        
        configIn();
        botones();
        combo1();
        combo2();
        
        setResizable(true);
        setLocationRelativeTo(null);
        setTitle("Configuración");
        
        jComboBox1.setEnabled(false);
        
        expButton.setEnabled(false);
        impButton.setEnabled(false);
    }
    
    protected datos d;
    
    protected File f;
    protected Image retValue;
    protected Properties p;
    protected InputStream is;
    protected OutputStream os;
    protected JFileChooser jfc;
    protected logger l;
    
    protected String icono;
    protected String nombre;
    protected String diseños;
    protected String lenguaje;
    protected String direccion;
    protected String direccion2;
    protected String nombreArchivo1;
    protected String nombreArchivo2;
    
    protected int leido;
    
    protected byte[] buffer;
    
    @Override
    public Image getIconImage(){
        p=new Properties();
        l=new logger();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            l.logStaticSaver("Error 1IO: "+e.getMessage()+" en '"+getIconImage().getClass().getName()+"'", Level.WARNING);
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            l.logStaticSaver("Error 2IO: "+x.getMessage()+" en '"+getIconImage().getClass().getName()+"'", Level.WARNING);
        }
        return retValue;
    }
    
    protected final void configIn(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            
            direccion=p.getProperty("imagenes");
            icono=p.getProperty("icono");
            diseños=p.getProperty("look_and_feel");
            lenguaje=p.getProperty("lenguaje");
            nombre=p.getProperty("nombre");
            
            if(!new File(direccion).exists()){
                direccion=p.getProperty("imagen_respaldo");
            }
            
            Image i=ImageIO.read(new FileInputStream(direccion));
            ImageIcon im=new ImageIcon(i);
            Icon l=new ImageIcon(im.getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT));
            jLabel3.setIcon(l);
            jTextField1.setText(nombre);
            jComboBox2.getModel().setSelectedItem(diseños);
            jComboBox1.getModel().setSelectedItem(lenguaje);
            
            i.flush();
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
        }catch(IOException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Erro 0",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    protected final void botones(){
        p=new Properties();
        
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        expButton.addActionListener((ae)->{
            new validacionVentana7(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        impButton.addActionListener((ae)->{
            new validacionVentana8(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        iconButton.addActionListener((ae)->{
            try{
                p=new Properties();
                p.load(new FileInputStream("src/data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_icon"));
                
                File k=jfc.getCurrentDirectory();
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo PNG","png"));
                
                int ñ=jfc.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==ñ){
                    try{
                        f=jfc.getSelectedFile();
                        icono=f.getPath();
                        nombreArchivo2=f.getName();
                        
                        p.setProperty("lastdirectory_icon",f.getParent());
                        p.setProperty("filter_icon",String.valueOf(jfc.getFileFilter()));
                        p.store(new BufferedWriter(new FileWriter("src/data/config/filechooserd.properties")),"JFileChooserDirection");
                    }catch(IOException ex){
                        JOptionPane.showMessageDialog(null,"Error:\n"+ex.getMessage(),"Error 24",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            }catch(IOException ñ){
                JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            }
        });
        
        imgButton.addActionListener((ActionEvent ae)->{
            try{
                p=new Properties();
                p.load(new FileInputStream("src/data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_image"));
                File k=jfc.getCurrentDirectory();
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo PNG","png"));
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo JPG","jpg"));
                jfc.setFileFilter(new FileNameExtensionFilter("Archivo JPEG","jpeg"));
                
                int ñ=jfc.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==ñ){
                    try{
                        f=jfc.getSelectedFile();
                        direccion=f.getPath();
                        nombreArchivo1=f.getName();
                        
                        Image i=ImageIO.read(new FileInputStream(direccion));
                        ImageIcon im=new ImageIcon(i);
                        Icon l=new ImageIcon(im.getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT));
                        jLabel3.setIcon(l);
                        i.flush();
                        
                        p.setProperty("lastdirectory_image",f.getParent());
                        p.store(new BufferedWriter(new FileWriter("src/data/config/filechooserd.properties")),"JFileChooserDirection");
                    }catch(FileNotFoundException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                    }catch(IOException x){
                        JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 24",JOptionPane.WARNING_MESSAGE);
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            }catch(IOException ñ){
                JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            }
        });
        
        jComboBox1.addActionListener((ActionEvent ae)->{
            lenguaje=jComboBox1.getSelectedItem().toString();
        });
        
        jComboBox2.addActionListener((ae)->{
            try{
                diseños=jComboBox2.getSelectedItem().toString();
                UIManager.setLookAndFeel(diseños);
                SwingUtilities.updateComponentTreeUI(this);
            }catch(ClassNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
            }catch(IllegalAccessException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
            }catch(InstantiationException ñ){
                JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
            }catch(UnsupportedLookAndFeelException k){
                JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
            }
        });
        
        schButton.addActionListener((ae)->{
            configOut();
        });
    }
    
    protected final void combo1(){
        UIManager.LookAndFeelInfo[] lafi=UIManager.getInstalledLookAndFeels();
        try{
            for(UIManager.LookAndFeelInfo lafi1:lafi){
                jComboBox2.addItem(lafi1.getClassName());
            }
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 34",JOptionPane.WARNING_MESSAGE);
        }catch(IllegalArgumentException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 34",JOptionPane.WARNING_MESSAGE);
        }catch(NullPointerException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    protected final void combo2(){
        int combo=jComboBox1.getSelectedIndex();
        p=new Properties();
        if(combo==0){
            try{
                p.load(new FileInputStream("src/data/lang/español.properties"));
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            }catch(IOException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            }
        }
        if(combo==1){
            try{
                p.load(new FileInputStream("src/data/lang/english.properties"));
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            }catch(IOException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    protected final void configOut(){
        p=new Properties();
        f=new File("src/data/config/config.properties");
        String dato="src/data/media/copy/label/";
        try{
            if(f.exists()){
                p.setProperty("imagenes",direccion);
                
                is=new FileInputStream(direccion);
                os=new FileOutputStream(dato+nombreArchivo1);
                
                new thread(is,os).start();
                
                p.setProperty("imagen_respaldo",dato+nombreArchivo1);
                p.setProperty("lenguaje",String.valueOf(lenguaje.getBytes("utf-8")));
                p.setProperty("look_and_feel",diseños);
                p.setProperty("icono",icono);
                p.setProperty("nombre",jTextField1.getText());
                
                is=new FileInputStream(icono);
                os=new FileOutputStream("src/data/media/copy/icon/"+nombreArchivo2);
                
                new thread(is,os).start();
                
                p.store(new BufferedWriter(new FileWriter("src/data/config/config.properties")),"config1");
                
                os.close();
            }else{
                f.createNewFile();
            }
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 27",JOptionPane.WARNING_MESSAGE);
        }catch(NumberFormatException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 32H",JOptionPane.WARNING_MESSAGE);
        }catch(NullPointerException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 26H",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        imgButton = new javax.swing.JButton();
        schButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        impButton = new javax.swing.JButton();
        expButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        iconButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(getIconImage());

        jLabel1.setText("Idioma:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Español", "Inglés" }));

        jLabel2.setText("Imagen:");

        imgButton.setText("Seleccionar imagen");

        schButton.setText("Guardar cambios");

        backButton.setText("Regresar");

        jLabel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel4.setText("Look And Feel:");

        impButton.setText("Importar");

        expButton.setText("Exportar");

        jLabel5.setText("Base de datos:");

        jLabel6.setText("Icono de ventana:");

        iconButton.setText("Seleccionar icono");

        jLabel7.setText("Nombre del programa:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(schButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel1)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel5)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(impButton)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(expButton))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(iconButton))))
                                .addGap(0, 213, Short.MAX_VALUE)))
                        .addGap(10, 10, 10)
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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(impButton)
                            .addComponent(expButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(iconButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(imgButton)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
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
    protected javax.swing.JButton expButton;
    protected javax.swing.JButton iconButton;
    protected javax.swing.JButton imgButton;
    protected javax.swing.JButton impButton;
    protected javax.swing.JComboBox<String> jComboBox1;
    protected javax.swing.JComboBox<String> jComboBox2;
    protected javax.swing.JLabel jLabel1;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    protected javax.swing.JLabel jLabel5;
    protected javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    protected javax.swing.JButton schButton;
    // End of variables declaration//GEN-END:variables
}