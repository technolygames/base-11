package venSecundarias;

import menuVentanas.menuVentanas;

import java.awt.Cursor;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.net.UnknownServiceException;
import java.util.Properties;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public final class loadWindow extends javax.swing.JDialog{
    public loadWindow(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        try{
            Properties style=new Properties();
            style.load(new FileInputStream("src/data/config/config.properties"));
            UIManager.setLookAndFeel(style.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(this);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error CNFE",JOptionPane.WARNING_MESSAGE);
        }catch(InstantiationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IE",JOptionPane.WARNING_MESSAGE);
        }catch(IllegalAccessException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IAE",JOptionPane.WARNING_MESSAGE);
        }catch(UnsupportedLookAndFeelException m){
            JOptionPane.showMessageDialog(null,"Error:\n"+m.getMessage(),"Error 28",JOptionPane.WARNING_MESSAGE);
        }catch(NullPointerException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
        }catch(FileNotFoundException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
        }catch(IOException d){
            JOptionPane.showMessageDialog(null,"Error:\n"+d.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
        }
        
        load();
        
        setLocationRelativeTo(null);
    }
    
    protected Timer t;
    
    protected File f;
    protected Properties p;
    protected InputStream is;
    protected FileOutputStream fos;
    
    protected URL u;
    protected URLConnection uc;
    
    protected int ent;
    
    protected byte[] bites;
    
    protected final void load(){
        ActionListener al=(ActionEvent ae)->{
            if(jProgressBar1.getValue()<115){
                jProgressBar1.setValue(jProgressBar1.getValue()+5);
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                if(jProgressBar1.getValue()==0){
                    jLabel2.setText(null);
                    jLabel2.setText("bridj-0.6.2");
                    if(!new File("src/data/libs/bridj-0.6.2.jar").exists()){
                        try{
                            u=new URL("http://download857.mediafire.com/g481j40i6ykg/lzf97xqjc8i2tou/bridj-0.6.2.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/bridj-0.6.2.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1I",JOptionPane.WARNING_MESSAGE);
                        }catch(UnknownServiceException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2I",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException k){
                            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==5){
                    jLabel2.setText(null);
                    jLabel2.setText("commons-beanutils-1.8.2");
                    if(!new File("src/data/libs/commons-beanutils-1.8.2.jar").exists()){
                        try{
                            u=new URL("http://download948.mediafire.com/8wfgp1dibp1g/1bjokubhviqd7oy/commons-beanutils-1.8.2.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/commons-beanutils-1.8.2.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==10){
                    jLabel2.setText(null);
                    jLabel2.setText("commons-collections-20040616.jar");
                    if(!new File("src/data/libs/commons-collections-20040616.jar").exists()){
                        try{
                            u=new URL("http://download1321.mediafire.com/g2n95cokmvvg/iubx1pu950sqkuh/commons-collections-20040616.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/commons-collections-20040616.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==15){
                    jLabel2.setText(null);
                    jLabel2.setText("commons-dbutils-1.7.jar");
                    if(!new File("src/data/libs/commons-dbutils-1.7.jar").exists()){
                        try{
                            u=new URL("https://download1640.mediafire.com/51t9hl9efblg/u596d8tvooae3x3/commons-dbutils-1.7.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/commons-dbutils-1.7.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==20){
                    jLabel2.setText(null);
                    jLabel2.setText("commons-digester-2.1.jar");
                    if(!new File("src/data/libs/commons-digester-2.1.jar").exists()){
                        try{
                            u=new URL("https://download944.mediafire.com/azl77y51rrvg/8s1tfq5qbqgrte2/commons-digester-2.1.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/commons-digester-2.1.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==25){
                    jLabel2.setText(null);
                    jLabel2.setText("commons-logging-1.2.1.1.jar");
                    if(!new File("src/data/libs/commons-logging-1.2.1.1.jar").exists()){
                        try{
                            u=new URL("https://download946.mediafire.com/1kyfaheeydmg/t5q1fpls44cy9dx/commons-logging-1.2.1.1.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/commons-logging-1.2.1.1.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==30){
                    jLabel2.setText(null);
                    jLabel2.setText("dms-19.3.0.0.jar");
                    if(!new File("src/data/libs/dms-19.3.0.0.jar").exists()){
                        try{
                            u=new URL("https://download1511.mediafire.com/h6gc1wkgooeg/hr0vah81v4umv6e/dms-19.3.0.0.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/dms-19.3.0.0.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==35){
                    jLabel2.setText(null);
                    jLabel2.setText("groovy-all-2.4.5.jar");
                    if(!new File("src/data/libs/groovy-all-2.4.5.jar").exists()){
                        try{
                            u=new URL("https://download1510.mediafire.com/y7cxz5afzdkg/rbpscmclmf08nbm/groovy-all-2.4.5.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/groovy-all-2.4.5.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==40){
                    jLabel2.setText(null);
                    jLabel2.setText("iText-2.1.7.jar");
                    if(!new File("src/data/libs/iText-2.1.7.jar").exists()){
                        try{
                            u=new URL("https://download1593.mediafire.com/au9c8tsbyd1g/pe6hhwfpk0c0d2g/iText-2.1.7.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/iText-2.1.7.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==45){
                    jLabel2.setText(null);
                    jLabel2.setText("itext-pdfa-5.5.4.jar");
                    if(!new File("src/data/libs/itext-pdfa-5.5.4.jar").exists()){
                        try{
                            u=new URL("https://download1588.mediafire.com/da3dxv87t6kg/qlvfop758tinnqo/itext-pdfa-5.5.4.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/itext-pdfa-5.5.4.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==50){
                    jLabel2.setText(null);
                    jLabel2.setText("itextpdf-5.5.4.jar");
                    if(!new File("src/data/libs/itextpdf-5.5.4.jar").exists()){
                        try{
                            u=new URL("https://download1583.mediafire.com/v9zo9rbdc6yg/65kk9h26u5pvm8g/itextpdf-5.5.4.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/itextpdf-5.5.4.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==55){
                    jLabel2.setText(null);
                    jLabel2.setText("jasperreports-4.7.1.jar");
                    if(!new File("src/data/libs/jasperreports-4.7.1.jar").exists()){
                        try{
                            u=new URL("https://download940.mediafire.com/6uka89u4l6kg/jg4oi1ukqmlgrcw/jasperreports-4.7.1.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/jasperreports-4.7.1.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==60){
                    jLabel2.setText(null);
                    jLabel2.setText("jasperreports-6.0.0.jar");
                    if(!new File("src/data/libs/jasperreports-6.0.0.jar").exists()){
                        try{
                            u=new URL("https://download1509.mediafire.com/708u9pgxswug/yue06ldio0qz21q/jasperreports-6.0.0.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/jasperreports-6.0.0.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==65){
                    jLabel2.setText(null);
                    jLabel2.setText("jasperreports-javaflow-4.7.1.jar");
                    if(!new File("src/data/libs/jasperreports-javaflow-4.7.1.jar").exists()){
                        try{
                            u=new URL("https://download851.mediafire.com/twxlygkuhzag/n98i3o1cgzz9a1o/jasperreports-javaflow-4.7.1.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/jasperreports-javaflow-4.7.1.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==70){
                    jLabel2.setText(null);
                    jLabel2.setText("JPanelWebCam.jar");
                    if(!new File("src/data/libs/JPanelWebCam.jar").exists()){
                        try{
                            u=new URL("https://download1334.mediafire.com/dkyh1n1rtmgg/0o7jbfrehjphqvz/JPanelWebCam.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/JPanelWebCam.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==75){
                    jLabel2.setText(null);
                    jLabel2.setText("mysql-connector-java-8.0.17.jar");
                    if(!new File("src/data/libs/mysql-connector-java-8.0.17.jar").exists()){
                        try{
                            u=new URL("https://download1481.mediafire.com/h3u03fcda5ig/vlith3vil9706rs/mysql-connector-java-8.0.17.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/mysql-connector-java-8.0.17.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==80){
                    jLabel2.setText(null);
                    jLabel2.setText("oclc-dbutils-1.0.20080317.jar");
                    if(!new File("src/data/libs/oclc-dbutils-1.0.20080317.jar").exists()){
                        try{
                            u=new URL("https://download1585.mediafire.com/ed75mq82mc5g/s9462u1mtqvnv2n/oclc-dbutils-1.0.20080317.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/oclc-dbutils-1.0.20080317.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==85){
                    jLabel2.setText(null);
                    jLabel2.setText("ojdbc6_g.jar");
                    if(!new File("src/data/libs/ojdbc6_g.jar").exists()){
                        try{
                            u=new URL("https://download844.mediafire.com/t6d607ppm88g/ai1wzj7z8azvyl5/ojdbc6_g.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/ojdbc6_g.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==90){
                    jLabel2.setText(null);
                    jLabel2.setText("poi-3.5-FINAL.jar");
                    if(!new File("src/data/libs/poi-3.5-FINAL.jar").exists()){
                        try{
                            u=new URL("https://download1505.mediafire.com/9rco39egrdng/cpc8b9obnl4ob0o/poi-3.5-FINAL.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/poi-3.5-FINAL.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==95){
                    jLabel2.setText(null);
                    jLabel2.setText("rs2xml.jar");
                    if(!new File("src/data/libs/rs2xml.jar").exists()){
                        try{
                            u=new URL("https://download850.mediafire.com/f9hgcuvbvy4g/dvc94za7iesvz0f/rs2xml.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/rs2xml.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==100){
                    jLabel2.setText(null);
                    jLabel2.setText("slf4j-api-1.7.2.jar");
                    if(!new File("src/data/libs/slf4j-api-1.7.2.jar").exists()){
                        try{
                            u=new URL("https://download1583.mediafire.com/rha2eun6yy4g/3ggur09zdev86b1/slf4j-api-1.7.2.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/slf4j-api-1.7.2.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==105){
                    jLabel2.setText(null);
                    jLabel2.setText("slf4j-simple-1.6.1.jar");
                    if(!new File("src/data/libs/slf4j-simple-1.6.1.jar").exists()){
                        try{
                            u=new URL("https://download1581.mediafire.com/h5pkq5l7ydrg/4hce15bg4tp4ak6/slf4j-simple-1.6.1.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/slf4j-simple-1.6.1.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==110){
                    jLabel2.setText(null);
                    jLabel2.setText("webcam-capture-0.3.10.jar");
                    if(!new File("src/data/libs/webcam-capture-0.3.10.jar").exists()){
                        try{
                            u=new URL("https://download1073.mediafire.com/1geeo2nqwwog/z00fyqio5fr1qrm/webcam-capture-0.3.10.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/webcam-capture-0.3.10.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
                if(jProgressBar1.getValue()==115){
                    jLabel2.setText(null);
                    jLabel2.setText("PlaceHolder.jar");
                    if(!new File("src/data/libs/PlaceHolder.jar").exists()){
                        try{
                            u=new URL("https://download941.mediafire.com/fhzz83vhg0pg/mykmzydwe3am1x4/PlaceHolder.jar");
                            uc=u.openConnection();
                            
                            is=uc.getInputStream();
                            fos=new FileOutputStream("src/data/libs/webcam-capture-0.3.10.jar");
                            
                            bites=new byte[2048];
                            ent=is.read(bites);
                            while(ent>0){
                                fos.write(bites,0,ent);
                                ent=is.read(bites);
                            }
                            
                            is.close();
                            fos.flush();
                            fos.close();
                        }catch(MalformedURLException e){
                            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error MUE",JOptionPane.WARNING_MESSAGE);
                        }catch(FileNotFoundException x){
                            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
                        }catch(IOException ñ){
                            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
                        }
                    }
                }
            }else{
                t.stop();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                new menuVentanas().setVisible(true);
                dispose();
            }
        };
        t=new Timer(115,al);
        t.start();
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jProgressBar1.setMaximum(115);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Cargando:");

        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new loadWindow(new javax.swing.JFrame(),true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}