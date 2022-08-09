package clases;
//java
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
 * Clase encargada de mostrar notificaciones.<br>
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
     * @param message que se mostrará en la notificación.
     * @param messageType de la notificación (puede ser ERROR, INFO, NONE y WARNING).
     */
    public void trayNotify(String notification,String message,MessageType messageType){
        SystemTray st=SystemTray.getSystemTray();
        try{
            if(SystemTray.isSupported()){
                p=new Properties();
                p.load(new FileInputStream(dirs.userdir+"/data/config/config.properties"));
                TrayIcon ti=new TrayIcon(Toolkit.getDefaultToolkit().getImage(p.getProperty("icono")));
                st.add(ti);
                ti.setImageAutoSize(true);
                ti.displayMessage(notification,message,messageType);
                ti.setToolTip(p.getProperty("nombre"));
                st.remove(ti);
            }
        }catch(AWTException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 24: "+e.getMessage()+"\nOcurrió en la clase '"+win10Notification.class.getName()+"', en el método 'trayNotify()'");
            new logger(Level.SEVERE).exceptionLogger(win10Notification.class.getName(),"trayNotify-24",e.fillInStackTrace());
        }catch(UnsupportedOperationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 25",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 25: "+x.getMessage()+"\nOcurrió en la clase '"+win10Notification.class.getName()+"', en el método 'trayNotify()'");
            new logger(Level.SEVERE).exceptionLogger(win10Notification.class.getName(),"trayNotify-25",x.fillInStackTrace());
        }catch(FileNotFoundException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+n.getMessage()+"\nOcurrió en la clase '"+win10Notification.class.getName()+"', en el método 'trayNotify()'");
            new logger(Level.SEVERE).exceptionLogger(win10Notification.class.getName(),"trayNotify-1IO",n.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+k.getMessage()+"\nOcurrió en la clase '"+win10Notification.class.getName()+"', en el método 'trayNotify()'");
            new logger(Level.SEVERE).exceptionLogger(win10Notification.class.getName(),"trayNotify-2IO",k.fillInStackTrace());
        }
    }
}