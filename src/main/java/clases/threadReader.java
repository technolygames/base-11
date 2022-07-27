package clases;
//java
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
//con extensión larga
import java.util.logging.Level;

/**
 * Clase encargada de usar un hilo para leer errores en consola.
 * Hace uso de un hilo (Thread) para leer errores producidos al leer/escribir un archivo.
 * 
 * @author erick
 */
public class threadReader implements Runnable{
    protected InputStream is;
    
    /**
     * Recibe el flujo de datos para leer el error producido en la consola.
     * 
     * @param is Flujo de datos del mensaje de error.
     */
    public threadReader(InputStream is){
        this.is=is;
    }
    
    /**
     * Método que puede ser usado para que pueda leer e imprimir el error en consola.
     */
    @Override
    public void run(){
        try{
            byte[] buffer=new byte[1024];
            int leido;
            while((leido=is.read(buffer))>0){
                String texto=new String(buffer,0,leido);
                JOptionPane.showMessageDialog(null,"Error:\n"+texto,"Error 9E",JOptionPane.ERROR_MESSAGE);
                leido=is.read(buffer);
            }
            
            is.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+e.getMessage()+"\nOcurrió en la clase '"+threadReader.class.getName()+"', en el método 'run()'");
            new logger(Level.SEVERE).exceptionLogger(threadReader.class.getName(),"run-2IO",e.fillInStackTrace());
        }
    }
}