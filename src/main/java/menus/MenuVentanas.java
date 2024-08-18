package menus;
//clases
import clases.MediaHandler;
import clases.InternalLogger;
import clases.Validation;
import clases.DisplayNotification;
import java.awt.EventQueue;
import venprimarias.Formulario1;
import venprimarias.Formulario2;
import venprimarias.Formulario3;
import venprimarias.PartnerList;
import venprimarias.SalesList;
import venprimarias.ProviderList;
import venprimarias.InventoryList;
import venprimarias.EmployeesList;
import venprimarias.Settings;
import venprimarias.Start;
import venprimarias.Ventana1;
import venprimarias.Ventana2;
import venterciarias.About;
import venterciarias.DataWindow1;
//java
import java.awt.Image;
import java.awt.TrayIcon;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public final class MenuVentanas extends javax.swing.JFrame{
    public MenuVentanas(){
        initComponents();
        new MediaHandler().setLookAndFeel(MenuVentanas.this);
        
        menu();
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Ventana principal");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    
    protected String rol;
    
    protected final void settings(){
        rol=Start.USER_ROLE;
        jMenuItem2.setText(Start.USER_NAME);
        picLabel.setIcon(new ImageIcon(new ImageIcon(new MediaHandler().getFormImage()).getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT)));
    }
    
    protected final void botones(){
        productButton.addActionListener(a->
            new Ventana1().setVisible(true)
        );
        
        storeButton.addActionListener(a->
            new Ventana2().setVisible(true)
        );
        
        form1Button.addActionListener(a->
            new Validation(new Formulario1(),rol,Formulario1.class.getName()).toRestrictedForm()
        );
        
        form2Button.addActionListener(a->
            new Validation(new Formulario2(),rol,Formulario2.class.getName()).toRestrictedForm()
        );
        
        form3Button.addActionListener(a->
            new Validation(new Formulario3(),rol,Formulario3.class.getName()).toRestrictedForm()
        );
        
        ltprvButton.addActionListener(a->
            new Validation(new ProviderList(),rol,ProviderList.class.getName()).toRestrictedForm()
        );
        
        ltpsButton.addActionListener(a->
            new Validation(new PartnerList(),rol,PartnerList.class.getName()).toRestrictedForm()
        );
        
        ltwkButton.addActionListener(a->
            new Validation(new EmployeesList(),rol,EmployeesList.class.getName()).toRestrictedForm()
        );
        
        ltstButton.addActionListener(a->
            new InventoryList().setVisible(true)
        );
        
        ltshButton.addActionListener(a->
            new SalesList().setVisible(true)
        );
        
        closeButton.addActionListener(a->{
            switch(JOptionPane.showConfirmDialog(null,"¿Deseas cerrar el programa?","Notice 1",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE)){
                case 0:
                    InternalLogger.staticLogger(Level.OFF,"Programa cerrado");
                    System.exit(0);
                    dispose();
                    break;
                case 1:
                    logout();
                    break;
                default:
                    break;
            }
        });
    }
    
    protected final void menu(){
        aboutButton.addActionListener(a->
            new About(this,true).setVisible(true)
        );
        
        jMenuItem2.addActionListener(a->
            new DataWindow1(this,true,Start.USER_ID).setVisible(true)
        );
        
        jMenuItem3.addActionListener(a->
            logout()
        );
        
        properButton.addActionListener(a->
            new Settings().setVisible(true)
        );
    }
    
    protected void logout(){
        new Start().setVisible(true);
        DisplayNotification.trayNotify("Has cerrado sesión","Hasta luego, "+jMenuItem2.getText(),TrayIcon.MessageType.INFO);
        InternalLogger.staticLogger(Level.INFO,"Sesión finalizada. Usuario que terminó sesión: "+jMenuItem2.getText());
        dispose();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        ltwkButton = new javax.swing.JButton();
        ltstButton = new javax.swing.JButton();
        ltshButton = new javax.swing.JButton();
        form1Button = new javax.swing.JButton();
        storeButton = new javax.swing.JButton();
        productButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        form2Button = new javax.swing.JButton();
        ltpsButton = new javax.swing.JButton();
        picLabel = new javax.swing.JLabel();
        form3Button = new javax.swing.JButton();
        ltprvButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        properButton = new javax.swing.JMenuItem();
        aboutButton = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setIconImage(new clases.MediaHandler().getIconImage());

        ltwkButton.setText("Lista de empleados");

        ltstButton.setText("Itinerario");

        ltshButton.setText("Productos vendidos");

        form1Button.setText("Formulario 1");

        storeButton.setText("Almacén");

        productButton.setText("Productos");

        closeButton.setText("Salir");

        form2Button.setText("Formulario 2");

        ltpsButton.setText("Socios");

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        form3Button.setText("Formulario 3");

        ltprvButton.setText("Lista de proveedores");

        jMenu3.setText("Opciones");

        properButton.setText("Ajustes");
        jMenu3.add(properButton);

        aboutButton.setText("Acerca");
        jMenu3.add(aboutButton);

        jMenuBar1.add(jMenu3);

        jMenu1.setText("Usuario");

        jMenuItem2.setText("jMenuItem2");
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Cerrar sesión");
        jMenu1.add(jMenuItem3);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ltwkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ltstButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ltprvButton)
                    .addComponent(ltpsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ltshButton, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(form1Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(storeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(form3Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(form2Button, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(form3Button)
                            .addComponent(ltprvButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(form2Button)
                            .addComponent(ltpsButton))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(form1Button)
                            .addComponent(ltwkButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(storeButton)
                            .addComponent(ltstButton))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productButton)
                            .addComponent(ltshButton)
                            .addComponent(closeButton))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        EventQueue.invokeLater(()->
            new MenuVentanas().setVisible(true)
        );
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutButton;
    private javax.swing.JButton closeButton;
    private javax.swing.JButton form1Button;
    private javax.swing.JButton form2Button;
    private javax.swing.JButton form3Button;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    public static javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JButton ltprvButton;
    private javax.swing.JButton ltpsButton;
    private javax.swing.JButton ltshButton;
    private javax.swing.JButton ltstButton;
    private javax.swing.JButton ltwkButton;
    public static javax.swing.JLabel picLabel;
    private javax.swing.JButton productButton;
    private javax.swing.JMenuItem properButton;
    private javax.swing.JButton storeButton;
    // End of variables declaration//GEN-END:variables
}