package venPrimarias;
//clases
import clases.datos;
import clases.Icono;
import clases.laf;
import clases.logger;
import menus.menuDatosVentana1;
//librerías
import com.placeholder.PlaceHolder;
//java
import java.awt.Image;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Period;
import java.time.LocalDate;
import java.util.Properties;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
//extension larga
import java.util.logging.Level;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class formulario1 extends javax.swing.JFrame{
    public formulario1(){
        initComponents();
        new laf().LookAndFeel(formulario1.this,formulario1.class.getName(),"formulario1");
        
        botones();
        settings();
        
        setSize(510,570);
        setLocationRelativeTo(null);
        setTitle("Formulario 1");
        setResizable(false);
    }
    
    protected File f;
    protected Properties p;
    protected JFileChooser jfc;
    
    protected String direccion;
    
    protected void settings(){
        new PlaceHolder(txtExp,"En años").inicializar();
        new PlaceHolder(txtFN,"yyyy-MM-dd").inicializar();
        txtNombre.setToolTipText("Primer y/o segundo nombre");
        txtExp.setToolTipText("En años");
        txtFN.setToolTipText("yyyy-MM-dd");
        jTextArea1.setLineWrap(true);
        jTextArea1.setWrapStyleWord(true);
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener((ae)->{
            new menuDatosVentana1().setVisible(true);
        });
        
        jMenuItem2.addActionListener((ae)->{
            try{
                p=new Properties();
                p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/filechooserd.properties"));
                jfc=new JFileChooser(p.getProperty("lastdirectory_form1"));
                
                File f=jfc.getCurrentDirectory();
                
                jfc.setFileFilter(new FileNameExtensionFilter("Archivos JPG","jpg"));
                jfc.setCurrentDirectory(f);
                
                int n=jfc.showOpenDialog(null);
                if(JFileChooser.APPROVE_OPTION==n){
                    try{
                        f=jfc.getSelectedFile();
                        direccion=f.getPath();
                        
                        ImageIcon ii=new ImageIcon(f.toString());
                        Icon i=new ImageIcon(ii.getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(),Image.SCALE_DEFAULT));
                        picLabel.setText(null);
                        picLabel.setIcon(i);
                        
                        p.setProperty("lastdirectory_form1",f.getParent());
                        p.store(new BufferedWriter(new FileWriter(System.getProperty("user.dir")+"/src/data/config/filechooserd.properties")),"JFileChooserDirection");
                    }catch(IOException e){
                        JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.WARNING_MESSAGE);
                        new logger().staticLogger("Error 24: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(insimgButton)'",Level.WARNING);
                        new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.insimg-24",e.fillInStackTrace());
                    }
                }
            }catch(HeadlessException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 40",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 40: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(insimgButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.insimg-40",e.fillInStackTrace());
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(insimgButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.insimg-1IO",x.fillInStackTrace());
            }catch(IOException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 2IO: "+n.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(insimgButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.insimg-2IO",n.fillInStackTrace());
            }
        });
        
        jMenuItem3.addActionListener((ae)->{
            txtPassword.setText("");
            txtCodigo.setText("");
            txtNombre.setText("");
            txtAP.setText("");
            txtAM.setText("");
            txtExp.setText("");
            txtEstudios.setText("");
            txtEdad.setText("");
            picLabel.setIcon(null);
            new PlaceHolder(txtNombre,"Primer y/o segundo nombre").inicializar();
        });
        
        svdtButton.addActionListener((ae)->{
            try{
                if(!txtPassword.getPassword().equals("")||!txtCodigo.getText().equals("")||!txtNombre.getText().equals("")||!txtAP.getText().equals("")||!txtAM.getText().equals("")||!txtDom.getText().equals("")||!txtExp.getText().equals("")||!txtEstudios.getText().equals("")||!txtContacto.getText().equals("")||!txtEdad.getText().equals("")||!jTextArea1.getText().equals("")){
                    String password=String.valueOf(txtPassword.getPassword());
                    int codigo_emp=Integer.parseInt(txtCodigo.getText());
                    String nombre_emp=txtNombre.getText();
                    String apellidop_emp=txtAP.getText();
                    String apellidom_emp=txtAM.getText();
                    String curp=txtCURP.getText();
                    String domicilio=txtDom.getText();
                    String puesto=jComboBox1.getSelectedItem().toString();
                    int experiencia=Integer.parseInt(txtExp.getText());
                    String grado_estudios=txtEstudios.getText();
                    int contacto=Integer.parseInt(txtContacto.getText());
                    String fechaNacimiento=txtFN.getText();
                    int edad=Integer.parseInt(txtEdad.getText());
                    String estado=jComboBox2.getSelectedItem().toString();
                    String datos_extra=jTextArea1.getText();
                    InputStream foto=new FileInputStream(direccion);
                    
                    new datos().insertarDatosEmpleado(password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,curp,domicilio,puesto,experiencia,grado_estudios,contacto,fechaNacimiento,edad,estado,datos_extra,foto);
                }else{
                    JOptionPane.showMessageDialog(null,"Error:\nIngrese los datos que se solicitan","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger().staticLogger("Error 18: no se escribieron o faltan datos en los campos.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'",Level.WARNING);
                }
            }catch(FileNotFoundException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.store-1IO",e.fillInStackTrace());
            }catch(NullPointerException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 0",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 0: "+x.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.store-0",x.fillInStackTrace());
            }catch(NumberFormatException n){
                JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 32: "+n.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.store-32",n.fillInStackTrace());
            }catch(ArrayIndexOutOfBoundsException k){
                JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error AIOOBE",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error AIOOBE: "+k.getMessage()+".\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'botones(storeButton)'",Level.WARNING);
                new logger().exceptionLogger(formulario1.class.getName(),Level.WARNING,"botones.store-AIOOBE",k.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        svdtButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtAP = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtAM = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtExp = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtEstudios = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        txtEstatus = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtEdad = new javax.swing.JTextField();
        picLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        txtCURP = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDom = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtContacto = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtFN = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        backButton.setText("Regresar");

        svdtButton.setText("Guardar datos");

        jLabel2.setText("Nombre(s):");

        txtNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNombreKeyPressed(evt);
            }
        });

        txtAP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAPKeyPressed(evt);
            }
        });

        jLabel4.setText("Apellido paterno:");

        jLabel5.setText("Apellido materno:");

        txtAM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAMKeyPressed(evt);
            }
        });

        jLabel6.setText("Puesto:");

        jLabel7.setText("Experiencia (años):");

        txtExp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtExpKeyPressed(evt);
            }
        });

        jLabel8.setText("Grado de estudios:");

        txtEstudios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEstudiosKeyPressed(evt);
            }
        });

        jLabel1.setText("Código:");

        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodigoKeyPressed(evt);
            }
        });

        jLabel3.setText("Edad:");

        txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtEdadKeyPressed(evt);
            }
        });

        picLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        picLabel.setText("Foto");
        picLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel9.setText("Contraseña:");

        jLabel10.setText("Datos extra:");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Empleado", "Programador", "Desarrollador", "Dueño" }));

        jLabel11.setText("CURP:");

        jLabel12.setText("Domicilio:");

        jLabel13.setText("Contacto:");

        txtContacto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContactoKeyPressed(evt);
            }
        });

        jLabel14.setText("F. nacimiento:");

        txtFN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFNKeyPressed(evt);
            }
        });

        jLabel15.setText("Estado:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo", "Inactivo" }));

        jMenu1.setText("Datos");

        jMenuItem1.setText("Menú de datos");
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones");

        jMenuItem2.setText("Insertar imagen");
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("Limpiar campos");
        jMenu2.add(jMenuItem3);

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(txtEstatus)
                                .addGap(9, 9, 9))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGap(18, 18, 18)
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtDom, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(18, 18, 18)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(txtEdad)
                                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtFN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)))
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(svdtButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(picLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(340, 340, 340))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCodigo)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtAP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtAM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtCURP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtDom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtExp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtEstudios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtFN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(svdtButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEstatus))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void txtCodigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtCodigoKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtCodigoKeyPressed
    
    private void txtNombreKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNombreKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtNombreKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtNombreKeyPressed
    
    private void txtAPKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAPKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtAPKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtAPKeyPressed
    
    private void txtAMKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAMKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtAMKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtAMKeyPressed
    
    private void txtExpKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtExpKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtExpKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtExpKeyPressed
    
    private void txtEstudiosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEstudiosKeyPressed
        if(Character.isDigit(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 7: se ingresaron números en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtEstudiosKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtEstudiosKeyPressed
    
    private void txtContactoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContactoKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtContactoKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtContactoKeyPressed
    
    private void txtEdadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEdadKeyPressed
        if(Character.isLetter(evt.getKeyChar())){
            JOptionPane.showMessageDialog(null,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Let 6: se ingresaron letras en un campo equivocado.\nOcurrió en la clase '"+formulario1.class.getName()+"', en el método 'txtEdadKeyPressed()'",Level.WARNING);
            evt.consume();
        }
    }//GEN-LAST:event_txtEdadKeyPressed
    
    private void txtFNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFNKeyPressed
        try{
            DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fecha=LocalDate.parse(txtFN.getText(),format);
            Period edad=Period.between(fecha,LocalDate.now());
            txtEdad.setText(String.valueOf(edad.getYears()));
        }catch(DateTimeParseException e){
            //not catch
        }
    }//GEN-LAST:event_txtFNKeyPressed
    
    public static void main(String[] args){
        new formulario1().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    public static javax.swing.JLabel picLabel;
    private javax.swing.JButton svdtButton;
    private javax.swing.JTextField txtAM;
    private javax.swing.JTextField txtAP;
    private javax.swing.JTextField txtCURP;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtDom;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JLabel txtEstatus;
    private javax.swing.JTextField txtEstudios;
    private javax.swing.JTextField txtExp;
    private javax.swing.JTextField txtFN;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JPasswordField txtPassword;
    // End of variables declaration//GEN-END:variables
}