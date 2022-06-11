package clases;
//java
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
//con extensión larga
import java.util.logging.Level;

/**
 * Hilo para leer errores.
 * 
 * @author erick
 */
public class threadReader extends Thread{
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
     * Método sobreescrito para que pueda leer e imprimir el error en consola.
     */
    @Override
    public void run(){
        try{
            byte[] buffer=new byte[1000];
            int leido;
            while((leido=is.read(buffer))>0){
                String texto=new String(buffer,0,leido);
                System.out.println(texto);
                leido=is.read(buffer);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+e.getMessage()+"\nOcurrió en la clase '"+threadReader.class.getName()+"', en el método 'run()'",Level.WARNING);
            new logger().exceptionLogger(threadReader.class.getName(),Level.WARNING,"run-2IO",e.fillInStackTrace());
        }
    }
}