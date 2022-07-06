package venTerciarias.valVentanas;
//clases
import clases.datos;
import clases.guiMediaHandler;
import clases.logger;
import venPrimarias.formulario3;
import venPrimarias.start;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class validacionVentana5 extends javax.swing.JDialog{
    public validacionVentana5(java.awt.Frame parent,boolean modal){
        super(parent,modal);
        initComponents();
        new guiMediaHandler(validacionVentana5.class.getName()).LookAndFeel(validacionVentana5.this);
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Validación");
        setResizable(false);
        pack();
    }
    
    protected void settings(){
        jTextField1.setText(start.nameUser);
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        valButton.addActionListener((ae)->{
            String usuario=jTextField1.getText();
            String contra=String.valueOf(jPasswordField1.getPassword());
            
            String consulta="select * from empleados where password='"+contra+"' and nombre_emp='"+usuario+"';";
            
            try{
                if(!jTextField1.getText().equals("")||!jPasswordField1.getPassword().equals("")){
                    PreparedStatement ps=new datos().getConnection().prepareStatement(consulta);
                    ResultSet rs=ps.executeQuery();
                    if(rs.next()){
                        if(rs.getString("puesto").equals("Dueño")||rs.getString("puesto").equals("Programador")||rs.getString("puesto").equals("Desarrollador")){
                            new formulario3().setVisible(true);
                            new logger(Level.INFO).staticLogger("Rel 5: validación correcta a 'formulario3'.\nOcurrió en la clase '"+validacionVentana5.class.getName()+"', en el método 'botones(valButton)'.\nUsuario que hizo la acción: "+String.valueOf(start.userID));
                            dispose();
                        }else{
                            JOptionPane.showMessageDialog(null,"Acceso restringido","Error 38",JOptionPane.WARNING_MESSAGE);
                            new logger(Level.WARNING).staticLogger("Error 38: usuario sin privilegios en '"+validacionVentana5.class.getName()+".botones(valButton)'");
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
                        new logger(Level.WARNING).staticLogger("Error 14: no existen o no se ingresaron los datos a buscar y cambiar.\nOcurrió en '"+validacionVentana5.class.getName()+"', en el método 'botones(valButton)'");
                    }
                    ps.close();
                    rs.close();
                }else{
                    JOptionPane.showMessageDialog(null,"Error:\n Escribe tu usuario y/o contraseña","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger(Level.WARNING).staticLogger("Error 18: no se escribió usuario y/o contraseña en '"+validacionVentana5.class.getName()+".botones(valButton)'");
                }
            }catch(SQLException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 9",JOptionPane.ERROR_MESSAGE);
                new logger(Level.SEVERE).staticLogger("Error 9: "+e.getMessage()+".\nOcurrió en la clase '"+validacionVentana5.class.getName()+"', en el método 'botones(valButton)'");
                new logger(Level.SEVERE).exceptionLogger(validacionVentana5.class.getName(),"botones.val-9",e.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        backButton = new javax.swing.JButton();
        valButton = new javax.swing.JButton();
        jPasswordField1 = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new guiMediaHandler(validacionVentana5.class.getName()).getIconImage());

        jLabel1.setText("Usuario:");

        jLabel2.setText("Contraseña:");

        backButton.setText("Regresar");

        valButton.setText("Validar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jPasswordField1)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(valButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(backButton)
                    .addComponent(valButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new validacionVentana5(new javax.swing.JFrame(),true).setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JButton valButton;
    // End of variables declaration//GEN-END:variables
}