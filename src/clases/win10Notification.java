package clases;
//java
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.SystemTray;
import java.awt.AWTException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.awt.TrayIcon.MessageType;

/**
 * Clase encargada de mostrar notificaciones.
 * Muestra notificaciones en sistemas que lo soporten.
 * 
 * @author erick
 */
public class win10Notification{
    protected Properties p;
    
    /**
     * Método encargado en mostrar la notificiación.
     * 
     * @param notification Título de la notificacion.
     * @param message Mensaje que se mostrará en la notificación.
     * @param mt Tipo de mensaje (puede se ERROR, INFO, NONE y WARNING).
     */
    public void trayNotify(String notification,String message,MessageType mt){
        SystemTray st=SystemTray.getSystemTray();
        try{
            if(st.isSupported()){
                p=new Properties();
                p.load(new FileInputStream(System.getProperty("user.dir")+"/src/data/config/config.properties"));
                Image i=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
                TrayIcon ti=new TrayIcon(i);
                st.add(ti);
                ti.setImageAutoSize(true);
                ti.displayMessage(notification,message,mt);
                ti.setToolTip(p.getProperty("nombre"));
                st.remove(ti);
                
                i.flush();
            }
        }catch(AWTException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 24: "+e.getMessage()+"\nOcurrió en la clase '"+win10Notification.class.getName()+"', en el método 'trayNotify()'",Level.WARNING);
            new logger().exceptionLogger(win10Notification.class.getName(),Level.WARNING,"trayNotify-24",e.fillInStackTrace());
        }catch(UnsupportedOperationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 25",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 25: "+x.getMessage()+"\nOcurrió en la clase '"+win10Notification.class.getName()+"', en el método 'trayNotify()'",Level.WARNING);
            new logger().exceptionLogger(win10Notification.class.getName(),Level.WARNING,"trayNotify-25",x.fillInStackTrace());
        }catch(FileNotFoundException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+n.getMessage()+"\nOcurrió en la clase '"+win10Notification.class.getName()+"', en el método 'trayNotify()'",Level.WARNING);
            new logger().exceptionLogger(win10Notification.class.getName(),Level.WARNING,"trayNotify-1IO",n.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+k.getMessage()+"\nOcurrió en la clase '"+win10Notification.class.getName()+"', en el método 'trayNotify()'",Level.WARNING);
            new logger().exceptionLogger(win10Notification.class.getName(),Level.WARNING,"trayNotify-2IO",k.fillInStackTrace());
        }
    }
}