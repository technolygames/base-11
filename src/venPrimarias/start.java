package venPrimarias;

import clases.datos;
import clases.Icono;
import clases.laf;
import clases.logger;
import clases.win10Notification;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon.MessageType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.logging.Level;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
import venSecundarias.loadWindow;

public final class start extends javax.swing.JFrame{
    public start(){
        initComponents();
        new laf().LookAndFeel(start.this,start.class.getName(),"start");
        
        botones();
        settings();
        
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Inicio");
    }
    
    protected Properties p;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    protected final void settings(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            new logger().staticLogger("Se cargaron los datos de configuración de start",Level.INFO);
            Image i=ImageIO.read(new FileInputStream(p.getProperty("imagenes")));
            ImageIcon im=new ImageIcon(i);
            Icon l=new ImageIcon(im.getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT));
            picLabel.setIcon(l);
            nameLabel.setText(p.getProperty("nombre"));
            
            i.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+e.getMessage(),Level.WARNING);
            new logger().exceptionLogger("start(1IO-s)",Level.SEVERE,"settings()",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+x.getMessage(),Level.WARNING);
            new logger().exceptionLogger("start(2IO-s)",Level.SEVERE,"settings()",x.fillInStackTrace());
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener((ae)->{
            System.exit(0);
            dispose();
        });
        
        loginButton.addActionListener((ae)->{
            login();
        });
    }
    
    protected final void login(){
        String usuario=txtUsuario.getText();
        String contra=String.valueOf(txtContraseña.getPassword());
        
        String consulta="select * from empleados where password='"+contra+"' and nombre_emp='"+usuario+"';";
        String fecha="update empleados set fecha_sesion=now() where password='"+contra+"';";
        
        try{
            ps=new datos().getConnection().prepareStatement(consulta);
            ps.executeUpdate(fecha);
            rs=ps.executeQuery();
            if(rs.next()){
                new loadWindow().setVisible(true);
                dispose();
                new win10Notification().trayNotify("Inicio de sesión","Bienvenido, "+rs.getString("nombre_emp"),MessageType.INFO);
                new logger().staticLogger("Ha iniciado sesión sin errores. Usuario: "+rs.getString("nombre_emp"),Level.INFO);
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 9",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 9: "+e.getMessage(),Level.WARNING);
            new logger().exceptionLogger("start",Level.SEVERE,"login()",e.fillInStackTrace());
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error Prueba",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error Prueba: "+x.getMessage(),Level.WARNING);
            new logger().exceptionLogger("start",Level.SEVERE,"login()",x.fillInStackTrace());
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
        txtContraseña = new javax.swing.JPasswordField();

        textField1.setText("textField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
        });

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
                                    .addComponent(txtContraseña, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
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
                            .addComponent(txtContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(loginButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 2",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 2: Se introdujeron caracteres inválidos en el campo de usuario",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtUsuarioKeyPressed
    
    public static void main(String[] args){
        new start().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JButton closeButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    protected javax.swing.JButton loginButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel picLabel;
    private java.awt.TextField textField1;
    protected javax.swing.JPasswordField txtContraseña;
    protected javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}