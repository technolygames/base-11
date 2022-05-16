package menus;
//clases
import clases.Icono;
import clases.laf;
import paneles.delDatosPanel1;
//java
import java.awt.BorderLayout;
import paneles.modDatosPanel1;

public class menuDatosVentana1 extends javax.swing.JFrame{
    public menuDatosVentana1(){
        initComponents();
        new laf().LookAndFeel(menuDatosVentana1.this,menuDatosVentana1.class.getName(),"menuDatosVentana1");
        
        botones();
        settings();
        
        setLocationRelativeTo(null);
        setTitle("Menú de Datos");
        setResizable(false);
    }
    
    protected void settings(){
        backButton.setToolTipText("Regresar al formulario"); 
    }
    
    protected final void botones(){
        backButton.addActionListener((ae)->{
            setVisible(false);
            dispose();
        });
        
        jMenuItem1.addActionListener((a)->{
            menuDatosVentana1.this.getContentPane().setLayout(new BorderLayout());
            menuDatosVentana1.this.getContentPane().add(new delDatosPanel1(),BorderLayout.CENTER);
        });
        
        jMenuItem2.addActionListener((a)->{
            menuDatosVentana1.this.getContentPane().setLayout(new BorderLayout());
            menuDatosVentana1.this.getContentPane().add(new modDatosPanel1(),BorderLayout.CENTER);
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(new Icono().getIconImage());

        backButton.setText("Regresar");

        jMenu1.setText("Ventana");

        jMenuItem1.setText("Eliminar datos");
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Cambiar datos");
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(273, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(235, Short.MAX_VALUE)
                .addComponent(backButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public static void main(String args[]){
        new menuDatosVentana1().setVisible(true);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    // End of variables declaration//GEN-END:variables
}