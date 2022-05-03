package clases;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 * Clase encargada de mostrar notificaciones en Windows 10.
 * Muestra notificaciones en Windows 10.
 */
public class win10Notification{
    protected Properties p;
    
    /**
     * Método encargado en mostrar la notificiación.
     * 
     * @param notification: Título de la notificacion
     * @param message: Mensaje que se mostrará en la notificació
     * @param mt: Tipo de mensaje (puede se ERROR, INFO, NONE y WARNING)
     */
    public void trayNotify(String notification,String message,MessageType mt){
        SystemTray st=SystemTray.getSystemTray();
        try{
            if(st.isSupported()){
                p=new Properties();
                p.load(new FileInputStream("src/data/config/config.properties"));
                Image i=Toolkit.getDefaultToolkit().getImage(p.getProperty("icono"));
                TrayIcon ti=new TrayIcon(i);
                st.add(ti);
                ti.setImageAutoSize(true);
                ti.displayMessage(notification,message,mt);
                ti.setToolTip(p.getProperty("nombre"));
            }
        }catch(AWTException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 24",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 24: "+e.getMessage()+" en 'trayNotify()'",Level.WARNING);
        }catch(UnsupportedOperationException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 25",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 25: "+x.getMessage()+" en 'trayNotify()'",Level.WARNING);
        }catch(FileNotFoundException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+ñ.getMessage()+" en 'trayNotify()'",Level.WARNING);
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+k.getMessage()+" en 'trayNotify()'",Level.WARNING);
        }
    }
}