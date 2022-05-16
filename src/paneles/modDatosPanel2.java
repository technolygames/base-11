package paneles;
//clases
import clases.laf;

public class modDatosPanel2 extends javax.swing.JPanel{
    public modDatosPanel2(){
        initComponents();
        new laf().LookAndFeel(modDatosPanel2.this,modDatosPanel2.class.getName(),"modDatosPanel2");
        
        botones();
        settings();
    }
    
    protected void settings(){
        
    }
    
    protected final void botones(){
        closeButton.addActionListener((a)->{
            setVisible(false);
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        closeButton = new javax.swing.JButton();

        closeButton.setText("Cerrar panel");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(297, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(266, Short.MAX_VALUE)
                .addComponent(closeButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    // End of variables declaration//GEN-END:variables
}