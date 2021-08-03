package venPrimarias;

import clases.datos;
import clases.logger;
import clases.win10Notification;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
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
        }catch(UnsupportedLookAndFeelException y){
            JOptionPane.showMessageDialog(null,"Error:\n"+y.getMessage(),"Error ULAFE",JOptionPane.WARNING_MESSAGE);
        }catch(FileNotFoundException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error FNFE",JOptionPane.WARNING_MESSAGE);
        }catch(IOException s){
            JOptionPane.showMessageDialog(null,"Error:\n"+s.getMessage(),"Error IOE",JOptionPane.WARNING_MESSAGE);
        }
        
        botones();
        settings();
        
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Inicio");
    }
    
    protected datos d;
    
    protected Image retValue;
    protected Properties p;
    
    protected ResultSet rs;
    protected PreparedStatement ps;
    
    @Override
    public Image getIconImage(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            retValue=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
            retValue.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
        }
        return retValue;
    }
    
    protected final void settings(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/config.properties"));
            Image i=ImageIO.read(new FileInputStream(p.getProperty("imagenes")));
            ImageIcon im=new ImageIcon(i);
            Icon l=new ImageIcon(im.getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT));
            picLabel.setIcon(l);
            nameLabel.setText(p.getProperty("nombre"));
            i.flush();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    protected final void botones(){
        closeButton.addActionListener((ae)->{
            System.exit(0);
            dispose();
        });
        
        loginButton.addActionListener((ae)->{
            try{
                login();
                /*ingresarPrueba();*/
            }catch(NullPointerException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }
        });
    }
    
    /*protected final void ingresarPrueba(){
        String usuario=txtUsuario.getText();
        String contra=String.valueOf(txtContraseña.getPassword());
        
        if(usuario.equals("Prueba")&&contra.equals("123")){
            new loadWindow(new javax.swing.JFrame(),true).setVisible(true);
            dispose();
        }else{
            JOptionPane.showMessageDialog(null,"Ingrese correctamente los datos","Error 9",JOptionPane.WARNING_MESSAGE);
        }
    }*/
    
    protected final void login(){
        d=new datos();
        
        String usuario=txtUsuario.getText();
        String contra=String.valueOf(txtContraseña.getPassword());
        
        String consulta="select * from empleados where password='"+contra+"' and nombre_emp='"+usuario+"';";
        String fecha="update empleados set fecha_sesion=now() where password='"+contra+"';";
        
        try{
            ps=d.getConnection().prepareStatement(consulta);
            ps.executeUpdate(fecha);
            rs=ps.executeQuery();
            if(rs.next()){
                new loadWindow(new javax.swing.JFrame(),true).setVisible(true);
                dispose();
                new win10Notification("Inicio de sesión","Bienvenido, "+rs.getString("nombre_emp"),MessageType.INFO).trayNotify();
                new logger("start","Ha iniciado sesión sin errores. Usuario: "+rs.getString("nombre_emp"),Level.INFO).logSaver();
            }
            ps.close();
            rs.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 9",JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }catch(NullPointerException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error Prueba",JOptionPane.WARNING_MESSAGE);
            x.printStackTrace();
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
        setIconImage(getIconImage());

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