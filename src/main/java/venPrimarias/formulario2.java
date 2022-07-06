package venPrimarias;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import menus.menuDatosVentana2;
//java
import java.awt.Image;
import java.awt.HeadlessException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
//extension larga
import javax.swing.filechooser.FileNameExtensionFilter;

public class formulario2 extends javax.swing.JFrame{
    public formulario2(){
        initComponents();
        new guiMediaHandler(formulario2.class.getName()).LookAndFeel(formulario2.this);
         
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Formulario 2");
        setResizable(false);
        pack();
    }
    
    protected Properties p;
    protected JFileChooser jfc;
    
    protected String userdir=System.getProperty("user.dir");
    protected String direccion;
    
    protected void settings(){
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener((ae)->{
            new menuDatosVentana2().setVisible(true);
        });
        
        miClearFields.addActionListener((a)->{
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextArea1.setText("");
            picLabel.setIcon(null);
            picLabel.setText("Foto");
        });
        
        miInsImage.addActionListener((a)->{
            try{
                p=new Properties();
                p.load(new FileInputStream(userdir+"/data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_form2"));
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                
                if(JFileChooser.APPROVE_OPTION==jfc.showOpenDialog(null)){
                    try{
                        File f=jfc.getSelectedFile();
                        direccion=f.getPath();
                        
                        ImageIcon ii=new ImageIcon(direccion);
                        Icon i=new ImageIcon(ii.getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT));
                        picLabel.setText(null);
                        picLabel.setIcon(i);
                        
                        p.setProperty("lastdirectory_form2",f.getParent());
                        p.store(new BufferedWriter(new FileWriter(userdir+"/data/config/filechooserd.properties")),"JFileChooserDirection");
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.ERROR_MESSAGE);
                        new logger(Level.SEVERE).staticLogger("Error 24: "+e.getMessage()+".\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'botones(miInsImage)'");
                        new logger(Level.SEVERE).exceptionLogger(formulario2.class.getName(),"botones.miInsImage-24",e.fillInStackTrace());
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'botones(miInsImage)'");
                new logger(Level.SEVERE).exceptionLogger(formulario2.class.getName(),"botones.miInsImage-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'botones(miInsImage)'");
                new logger(Level.SEVERE).exceptionLogger(formulario2.class.getName(),"botones.miInsImage-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'botones(miInsImage)'");
                new logger(Level.SEVERE).exceptionLogger(formulario2.class.getName(),"botones.miInsImage-2IO",n.fillInStackTrace());
            }
        });
        
        storeButton.addActionListener((ae)->{
           try{
                if(!jTextField1.getText().equals("")||!jTextField2.getText().equals("")||!jTextField3.getText().equals("")||!jTextField4.getText().equals("")||!jTextArea1.getText().equals("")){
                    int codigo=Integer.parseInt(jTextField1.getText());
                    String nombre=jTextField2.getText();
                    String apellidop=jTextField3.getText();
                    String apellidom=jTextField4.getText();
                    String tipo=jComboBox1.getSelectedItem().toString();
                    String correo=jTextField5.getText();
                    String rfc=jTextField6.getText();
                    String datos=jTextArea1.getText();
                    InputStream foto=new FileInputStream(direccion);
                    
                    new datos().insertarDatosSocio(codigo,nombre,apellidop,apellidom,tipo,correo,rfc,datos,foto);
                }else{
                    JOptionPane.showMessageDialog(null,"Error: escribe los datos faltantes","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger(Level.WARNING).staticLogger("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'botones(storeButton)'");
                }
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'botones(storeButton)'");
                new logger(Level.SEVERE).exceptionLogger(formulario2.class.getName(),"botones.store-1IO",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'botones(storeButton)'");
                new logger(Level.SEVERE).exceptionLogger(formulario2.class.getName(),"botones.store-0",x.fillInStackTrace());
            }catch(NumberFormatException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 32",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 32: "+n.getMessage()+".\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'botones(storeButton)'");
                new logger(Level.SEVERE).exceptionLogger(formulario2.class.getName(),"botones.store-32",n.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        backButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        storeButton = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        picLabel = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        miInsImage = new javax.swing.JMenuItem();
        miClearFields = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(formulario2.class.getName()).getIconImage());

        backButton.setText("Regresar");

        jLabel1.setText("Código del socio:");

        jLabel2.setText("Nombre(s):");

        jLabel3.setText("Apellido paterno:");

        jLabel4.setText("Tipo de socio:");

        jLabel5.setText("Datos extra:");

        storeButton.setText("Guardar datos");

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

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Apellido materno:");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });

        picLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picLabel.setText("Foto");
        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(null));

        jLabel6.setText("E-mail:");

        jLabel8.setText("RFC:");

        jMenu1.setText("Datos");

        jMenuItem1.setText("Menú de Datos");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones");

        miInsImage.setText("Insertar imagen");
        jMenu2.add(miInsImage);

        miClearFields.setText("Limpiar campos");
        jMenu2.add(miClearFields);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(storeButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jTextField6)
                                .addComponent(jTextField5)
                                .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, 120, Short.MAX_VALUE)
                                .addComponent(jTextField3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.Alignment.LEADING)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
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
                            .addComponent(jLabel7)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(storeButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'jTextField1KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'jTextField2KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField2KeyPressed
    
    private void jTextField3KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'jTextField3KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField3KeyPressed
    
    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger(Level.WARNING).staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario2.class.getName()+"', en el método 'jTextField4KeyPressed()'");
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyPressed
    
    public static void main(String args[]){
        new formulario2().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JMenuItem miClearFields;
    private javax.swing.JMenuItem miInsImage;
    public static javax.swing.JLabel picLabel;
    private javax.swing.JButton storeButton;
    // End of variables declaration//GEN-END:variables
}