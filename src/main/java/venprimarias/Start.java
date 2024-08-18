package venprimarias;
//clases
import clases.Datos;
import clases.MediaHandler;
import clases.InternalLogger;
import clases.DisplayNotification;
import clases.SQLConnection;
import vensecundarias.LoadWindow;
//java
import java.awt.EventQueue;
import java.awt.Image;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Properties;
//extension larga
import java.util.logging.Level;
import java.awt.TrayIcon.MessageType;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;

public final class Start extends javax.swing.JFrame{
    public Start(){
        initComponents();
        new MediaHandler().setLookAndFeel(Start.this);
        
        botones();
        settings();
        
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Inicio");
        pack();
    }
    
    public static int USER_ID;
    public static String USER_NAME;
    public static String USER_ROLE;
    
    protected final void settings(){
        Properties p=new Properties();
        picLabel.setIcon(new ImageIcon(new ImageIcon(new MediaHandler().getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
        try(FileReader fr=new FileReader("data/config/config.properties",StandardCharsets.UTF_8)){
            p.load(fr);
            nameLabel.setText(p.getProperty("nombre"));
        }catch(FileNotFoundException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(IOException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener(a->{
            System.exit(0);
            dispose();
        });
        
        loginButton.addActionListener(a->
            login()
        );
        
        txtPassword.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent a){
                if(a.getKeyCode()==KeyEvent.VK_ENTER){
                    login();
                }
            }
        });
    }
    
    protected void login(){
        String user=txtUsuario.getText();
        String pass=String.valueOf(txtPassword.getPassword());
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("select * from empleados where password=? and nombre_emp=? or curp=?");
                PreparedStatement ps2=cn.prepareStatement("select * from conteo where codigo_emp=? and fecha_sesion=?")){
            ps.setString(1,pass);
            ps.setString(2,user);
            ps.setString(3,user);
            if(!user.isEmpty()||!pass.isEmpty()){
                new Datos().actualizarDatosLogin(pass,user);
                try(ResultSet rs=ps.executeQuery()){
                    if(rs.next()){
                        new LoadWindow().setVisible(true);
                        dispose();
                        USER_NAME=rs.getString("nombre_emp");
                        USER_ID=rs.getInt("codigo_emp");
                        USER_ROLE=rs.getString("puesto");
                        
                        Date fecha=rs.getDate("fecha_nacimiento");
                        int edad1=rs.getInt("edad");
                        String edad2=String.valueOf(Period.between(LocalDate.parse(fecha.toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd")),LocalDate.now()).getYears());
                        if(!edad2.equals(String.valueOf(edad1))){
                            InternalLogger.staticLogger(Level.INFO,"no es igual");
                            new Datos().actualizarDatosInteger("empleados","edad","codigo_emp",Integer.parseInt(edad2),USER_ID,false);
                        }else{
                            InternalLogger.staticLogger(Level.INFO,"es igual");
                        }
                        
                        ps2.setInt(1,USER_ID);
                        ps2.setString(2,LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
                        try(ResultSet rs2=ps2.executeQuery()){
                            if(!rs2.next()){
                                InternalLogger.staticLogger(Level.INFO,"2; no hay");
                                new Datos().insertarDatosConteo(USER_ID,USER_NAME,rs.getString("apellidop_emp"),rs.getString("apellidom_emp"));
                            }else{
                                InternalLogger.staticLogger(Level.INFO,"1; si hay");
                            }
                        }
                        DisplayNotification.trayNotify("Inicio de sesión","Bienvenido, "+USER_NAME,MessageType.INFO);
                        InternalLogger.staticLogger(Level.INFO,"Inicio de sesión correcto. Usuario logeado: "+USER_ID);
                    }else{
                        new InternalLogger().storeError14(this,Level.WARNING);
                    }
                }
            }else{
                new InternalLogger().storeError18(this,Level.WARNING);
            }
        }catch(SQLException e){
            new InternalLogger().catchException(this,e,Level.SEVERE);
        }catch(NullPointerException x){
            new InternalLogger().catchException(this,x,Level.SEVERE);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textField1 = new java.awt.TextField();
        txtUsuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        nameLabel = new javax.swing.JLabel();
        picLabel = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();

        textField1.setText("textField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        loginButton.setText("Ingresar");
        loginButton.setToolTipText("");

        closeButton.setText("Salir");

        nameLabel.setFont(new java.awt.Font("Tahoma", 3, 14)); // NOI18N

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtUsuario)
                                    .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(loginButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(closeButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(loginButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new Start().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel picLabel;
    private java.awt.TextField textField1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}