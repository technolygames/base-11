package paneles;
//clases
import clases.datos;
import clases.laf;
import clases.logger;
//java
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

public class delDatosPanel3 extends javax.swing.JPanel{
    public delDatosPanel3(){
        initComponents();
        new laf().LookAndFeel(delDatosPanel3.this,delDatosPanel3.class.getName(),"delDatosPanel3");
        
        botones();
    }
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
        
        deleteButton.addActionListener((ae)->{
            try{
                if(!jTextField1.getText().equals("")){
                    int codigo=Integer.parseInt(jTextField1.getText());
                    new datos().eliminarDatosProveedor(codigo);
                }else{
                    JOptionPane.showMessageDialog(null,"Escribe el número de identificación da eliminar","Error 18",JOptionPane.WARNING_MESSAGE);
                    new logger().staticLogger("Error 18: no se escribió correctamente el código del empleado a eliminar. Ocurrió en '"+delDatosPanel3.class.getName()+"', en el método 'botones(deleteButton)'",Level.WARNING);
                }
            }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 32",JOptionPane.WARNING_MESSAGE);
                new logger().staticLogger("Error 32: "+e.getMessage()+".\nOcurrió en '"+delDatosPanel3.class.getName()+"', en el método 'botones(deleteButton)'",Level.WARNING);
                new logger().exceptionLogger(delDatosPanel3.class.getName(),Level.WARNING,"botones.delete-32",e.fillInStackTrace());
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        closeButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();

        jLabel1.setText("Código del proveedor:");

        closeButton.setText("Cerrar panel");

        deleteButton.setText("Eliminar datos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton)))
                .addContainerGap(159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(closeButton)
                    .addComponent(deleteButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}