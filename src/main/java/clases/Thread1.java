package clases;
//java
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
//con extensión larga
import java.util.logging.Level;

/**
 * Clase encargada del flujo de datos. 
 * Este hilo se encarga de que exista un flujo de datos y crear un archivo a partir de ese flujo. 
 * Es usado para leer/escribir el contenido de un archivo para crearlo en una dirección especificada.
 * 
 * @author erick
 */
public class Thread1 implements Runnable{
    protected InputStream is;
    protected OutputStream os;
    
    /**
     * Recibe los datos para que haya un flujo de datos y se pueda crear un archivo.
     * 
     * @param is: flujo de entrada del archivo a leer.
     * @param os: flujo de salida del archivo a escribir.
     */
    public Thread1(InputStream is,OutputStream os){
        this.os=os;
        this.is=is;
    }
    
    /**
     * Método que puede ser usado para habilitar el flujo de datos.
     */
    @Override
    public void run(){
        int leido;
        try{
            byte[] buffer=new byte[2048];
            while((leido=is.read(buffer))>0){
                os.write(buffer,0,leido);
            }
            is.close();
            os.flush();
            os.close();
        }catch(IOException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
}