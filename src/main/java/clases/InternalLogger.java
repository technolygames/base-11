package clases;
//java
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Clase encargada de manejar los eventos del programa.<br>
 * Guarda los eventos del programa mientras este se está ejecutando.
 * 
 * @author erick
 */
public class InternalLogger{
    static FileHandler fh1;
    
    static{
        try{
            fh1=new FileHandler("data/logs/static/staticLog.log",0,1,true);
            fh1.setFormatter(new SimpleFormatter());
        }catch(SecurityException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error SE",JOptionPane.ERROR_MESSAGE);
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Método encargado de guardar los datos de eventos ocurridos durante la ejecución del programa en un mismo archivo.
     * 
     * @param level del evento.
     * @param message que se almacenará en el archivo .log.
     */
    public static void staticLogger(Level level,String message){
        Logger l=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        try{
            l.addHandler(fh1);
            l.log(level,message);
        }catch(SecurityException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Método que se encarga de guardar los datos de eventos ocurridos durante la ejecución del programa en varios archivos.
     * 
     * @param level
     * @param mesage en el que está ocurriendo el error.
     * @param ex Excepción (o error) al que se le manejará y guardará en el archivo log.
     */
    public void exceptionLogger(Level level,String mesage,Throwable ex){
        Logger l=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
        try{
            FileHandler fh=new FileHandler(Dirs.exists(new File("data/logs/exceptions/exception.log")));
            fh.setFormatter(new SimpleFormatter());
            l.addHandler(fh);
            l.log(level,mesage,ex);
            
            fh.flush();
            fh.close();
        }catch(SecurityException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }catch(IOException x){
            new InternalLogger().catchException(null,x,Level.SEVERE);
        }
    }
    
    /**
     * Mostrará una subventana para mostrar un mensaje de error y almacenar en un archivo este mismo para su posterior análisis.
     * 
     * @param comp en el que se mostrará el option pane.
     * @param ex que mostrará y almacenará en un archivo .log.
     * @param level el tipo de nivel del error.
     */
    public void catchException(Component comp,Throwable ex,Level level){
        JOptionPane.showMessageDialog(comp,ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
        InternalLogger.staticLogger(level,ex.getMessage());
        new InternalLogger().exceptionLogger(level,ex.getMessage(),ex);
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param level
     */
    public void storeNumberInputWarning(Component comp,Level level){
        JOptionPane.showMessageDialog(comp,"Solo números","Let 6",JOptionPane.WARNING_MESSAGE);
        InternalLogger.staticLogger(level,"Let 6: se ingresaron letras en un campo equivocado.");
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param level
     */
    public void storeLetterInputWarning(Component comp,Level level){
        JOptionPane.showMessageDialog(comp,"Solo letras","Let 7",JOptionPane.WARNING_MESSAGE);
        InternalLogger.staticLogger(level,"Let 7: se ingresaron números en un campo equivocado.");
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param level
     */
    public void storeError14(Component comp,Level level){
        JOptionPane.showMessageDialog(comp,"Error: no existen los datos","Error 14",JOptionPane.WARNING_MESSAGE);
        InternalLogger.staticLogger(level,"Error 14: no existen o no se ingresaron los datos a buscar y/o cambiar.");
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param level
     */
    public void storeError18(Component comp,Level level){
        JOptionPane.showMessageDialog(comp,"Error:\nEscribe correctamente lo que deseas realizar en esta acción","Error 18",JOptionPane.WARNING_MESSAGE);
        InternalLogger.staticLogger(level,"Error 18: no se escribió la palabra clave para hacer la búsqueda.");
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param level
     */
    public void storeMessageConfirmation(Component comp,Level level){
        JOptionPane.showMessageDialog(comp,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
        InternalLogger.staticLogger(level,"Rel 1: se guardaron correctamente los datos a la base de datos.");
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param level
     */
    public void updateMessageConfirmation(Component comp,Level level){
        JOptionPane.showMessageDialog(comp,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
        InternalLogger.staticLogger(level,"Rel 2: se actualizaron correctamente los datos.");
    }
    
    /**
     * @param comp ventana madre para que se centre el mensaje.
     * @param level
     */
    public void deleteMessageConfirmation(Component comp,Level level){
        JOptionPane.showMessageDialog(comp,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
        InternalLogger.staticLogger(level,"Rel 3: se eliminaron correctamente los registros de la base de datos.");
    }
}