package clases;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * Clase encargada de mostrar notificaciones en Windows 10.
 * Muestra notificaciones en Windows 10
 */
public class win10Notification{
    protected String notificacion;
    protected String mensaje;
    protected MessageType tipo;
    
    /**
     * Recibe los datos para mostrar notificaciones en Windows 10
     * @param notification: Título de la notificacion
     * @param message: Mensaje que se mostrará en la notificació
     * @param mt: Tipo de mensaje (puede se ERROR, INFO, NONE y WARNING)
     */
    public win10Notification(String notification,String message,MessageType mt){
        this.notificacion=notification;
        this.mensaje=message;
        this.tipo=mt;
    }
    
    protected Properties p;
    
    public void trayNotify(){
        SystemTray st=SystemTray.getSystemTray();
        try{
            if(st.isSupported()){
                Image i=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
                TrayIcon ti=new TrayIcon(i);
                st.add(ti);
                ti.setImageAutoSize(true);
                ti.displayMessage(notificacion,mensaje,tipo);
            }
        }catch(AWTException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.WARNING_MESSAGE);
        }catch(UnsupportedOperationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 25",JOptionPane.WARNING_MESSAGE);
        }
    }
}