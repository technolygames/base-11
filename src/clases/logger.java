package clases;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;

/**
 * Clase encargada de manejar los eventos del programa
 */
public class logger{
    protected String mensaje;
    protected String nombreClase;
    protected Level nivel;
    
    /**
     * Recibe los datos para que el manejador de eventos pueda guardar en un archivo los eventos ocurridos durante la ejecución del programa.
     * @param className: nombre de la clase en la que sucede el evento
     * @param message. Mensaje que recibe el manejador de eventos
     * @param level: Nivel de severidad del evento
     */
    public logger(String className,String message,Level level){
        this.mensaje=message;
        this.nombreClase=className;
        this.nivel=level;
    }
    
    /**
     * Método encargado de guardar los datos de eventos ocurridos durante la ejecución del programa
     */
    public void logSaver(){
        Logger logger=Logger.getLogger("ClassLog");
        SimpleFormatter formatter=new SimpleFormatter();
        try{
            FileHandler fh=new FileHandler("src/data/logs/"+nombreClase+".log");
            fh.setFormatter(formatter);
            logger.addHandler(fh);
            logger.log(nivel,mensaje);
        }catch(SecurityException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage());
        }
    }
}