package clases;
//java
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Clase encargada de manejar los eventos del programa.
 * Guarda los eventos del programa mientras este se está ejecutando.
 * 
 * @author erick
 */
public class logger{
    protected static FileHandler fh;
    protected FileHandler fh2;
    
    static{
        try{
            fh=new FileHandler(System.getProperty("user.dir")+"/src/data/logs/static/staticLog.log",0,1,true);
        }catch(SecurityException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error SE",JOptionPane.WARNING_MESSAGE);
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Método encargado de guardar los datos de eventos ocurridos durante la ejecución del programa en un mismo archivo.
     * 
     * @param message Mensaje que se almacenará en el archivo .log.
     * @param level Nivel de prioridad del evento.
     */
    public void staticLogger(String message,Level level){
        Logger logger=Logger.getLogger("staticLogger");
        try{
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.log(level,message);
        }catch(SecurityException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error SE",JOptionPane.WARNING_MESSAGE);
            new logger().exceptionLogger(logger.class.getName(),Level.WARNING,"staticLogger-SE",e.fillInStackTrace());
        }
    }
    
    /**
     * Método que se encarga de guardar los datos de eventos ocurridos durante la ejecución del programa en varios archivos.
     * 
     * @param className Nombre de la clase en la que sucede el evento.
     * @param level Nivel de prioridadd de la excepción.
     * @param methodName Nombre del método en el que está ocurriendo el error.
     * @param exception Excepción (o error) al que se le manejará y guardará en el archivo log.
     */
    public void exceptionLogger(String className,Level level,String methodName,Throwable exception){
        Logger logger=Logger.getLogger("exceptionLogger");
        try{
            fh2=new FileHandler(System.getProperty("user.dir")+"/src/data/logs/exceptions/"+className+"."+methodName+"-("+(int)(Math.random()*10000)+","+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+").log");
            fh2.setFormatter(new SimpleFormatter());
            logger.addHandler(fh2);
            logger.log(level,methodName,exception);
            
            fh2.flush();
            fh2.close();
        }catch(SecurityException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error SE",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error SE: "+e.getMessage()+".\nOcurrió en la clase '"+logger.class.getName()+"', en el método 'exceptionLogger()'",Level.WARNING);
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+x.getMessage()+".\nOcurrió en la clase '"+logger.class.getName()+"', en el método 'exceptionLogger()'",Level.WARNING);
        }
    }
}