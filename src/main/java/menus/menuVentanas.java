package menus;
//clases
import clases.guiMediaHandler;
import clases.logger;
import clases.validation;
import clases.win10Notification;
import venPrimarias.formulario1;
import venPrimarias.formulario2;
import venPrimarias.formulario3;
import venPrimarias.ltshPartners;
import venPrimarias.ltshProduct;
import venPrimarias.ltshProviders;
import venPrimarias.ltshStorage;
import venPrimarias.ltshWorkers;
import venPrimarias.proper1;
import venPrimarias.start;
import venPrimarias.ventana1;
import venPrimarias.ventana2;
import venTerciarias.about;
import venTerciarias.dataWindow1;
//java
import java.awt.TrayIcon;
import java.util.Properties;
//extension larga
import java.util.logging.Level;
public final class menuVentanas extends javax.swing.JFrame{
    public menuVentanas(){
        initComponents();
        new guiMediaHandler(menuVentanas.class.getName()).LookAndFeel(menuVentanas.this);
        new guiMediaHandler(menuVentanas.class.getName()).FormImage(picLabel);
        
        menu();
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Ventana principal");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    
    protected void settings(){
        String nombre=start.nameUser;
        jMenuItem2.setText(nombre);
    }
    
    protected final void botones(){
        productButton.addActionListener((ae)->{
            new ventana1().setVisible(true);
        });
        
        storeButton.addActionListener((ae)->{
            new ventana2().setVisible(true);
        });
        
        form1Button.addActionListener((ae)->{
            new validation(new formulario1(),start.role,formulario1.class.getName()).toRestrictedForm();
        });
        
        form2Button.addActionListener((ae)->{
            new validation(new formulario2(),start.role,formulario2.class.getName()).toRestrictedForm();
        });
        
        form3Button.addActionListener((ae)->{
            new validation(new formulario3(),start.role,formulario3.class.getName()).toRestrictedForm();
        });
        
        ltprvButton.addActionListener((ae)->{
            new validation(new ltshProviders(),start.role,ltshProviders.class.getName()).toRestrictedForm();
        });
        
        ltpsButton.addActionListener((ae)->{
            new validation(new ltshPartners(),start.role,ltshPartners.class.getName()).toRestrictedForm();
        });
        
        ltwkButton.addActionListener((ae)->{
            new validation(new ltshWorkers(),start.role,ltshWorkers.class.getName()).toRestrictedForm();
        });
        
        ltstButton.addActionListener((ae)->{
            new ltshStorage().setVisible(true);
        });
        
        ltshButton.addActionListener((ae)->{
            new ltshProduct().setVisible(true);
        });
        
        closeButton.addActionListener((ae)->{
            System.exit(0);
            dispose();
        });
    }
    
    protected final void menu(){
        aboutButton.addActionListener((a)->{
            new about(new javax.swing.JFrame(),true).setVisible(true);
        });
        
        properButton.addActionListener((a)->{
            new proper1().setVisible(true);
        });
        
        jMenuItem2.addActionListener((a)->{
            new dataWindow1(new javax.swing.JFrame(),true,start.userID).setVisible(true);
        });
        
        jMenuItem3.addActionListener((a)->{
            new start().setVisible(true);
            new win10Notification().trayNotify("Has cerrado sesión","Hasta luego, "+jMenuItem2.getText(),TrayIcon.MessageType.INFO);
            new logger(Level.SEVERE).staticLogger("Sesión finalizada.\nOcurrió en la clase '"+menuVentanas.class.getName()+"', en el método 'menu(jMenuItem2)'.\nUsuario que terminó sesión: "+jMenuItem2.getText());
            dispose();
        });
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
        setIconImage(new guiMediaHandler(menuVentanas.class.getName()).getIconImage());

        ltwkButton.setText("Lista de empleados");

        ltstButton.setText("Itinerario");

        ltshButton.setText("Productos vendidos");

        form1Button.setText("Formulario 1");

        storeButton.setText("Almacén");

        productButton.setText("Productos");

        closeButton.setText("Salir");

        form2Button.setText("Formulario 2");

        ltpsButton.setText("Socios");

        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(null));

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(ltpsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ltwkButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ltstButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ltshButton, javax.swing.GroupLayout.PREFERRED_SIZE, 133, Short.MAX_VALUE))
                    .addComponent(ltprvButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(form3Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(productButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(form1Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(storeButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(form2Button, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(form3Button)
                        .addGap(18, 18, 18)
                        .addComponent(form2Button)
                        .addGap(18, 18, 18)
                        .addComponent(form1Button)
                        .addGap(18, 18, 18)
                        .addComponent(storeButton)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(productButton)
                            .addComponent(closeButton)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ltprvButton)
                        .addGap(18, 18, 18)
                        .addComponent(ltpsButton)
                        .addGap(18, 18, 18)
                        .addComponent(ltwkButton)
                        .addGap(18, 18, 18)
                        .addComponent(ltstButton)
                        .addGap(18, 18, 18)
                        .addComponent(ltshButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String[] args){
        new menuVentanas().setVisible(true);
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
    private javax.swing.JLabel picLabel;
    private javax.swing.JButton productButton;
    private javax.swing.JMenuItem properButton;
    private javax.swing.JButton storeButton;
    // End of variables declaration//GEN-END:variables
}