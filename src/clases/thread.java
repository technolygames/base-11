package clases;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Clase encargada del flujo de datos.
 * Se encarga de usar un hilo de ejecución para que exista un flujo de datos
 */
public class thread extends Thread{
    protected InputStream is;
    protected OutputStream os;
    
    /**
     * Recibe los datos para que haya un flujo de datos y se use el hilo.
     * @param is: flujo de entrada (según sea el caso, se puede usar FileInputStream)
     * @param os: flujo de salida (según sea el caso, se puede usar FileOutputStream)
     */
    public thread(InputStream is,OutputStream os){
        this.os=os;
        this.is=is;
    }
    
    /**
     * Método sobreescrito para utilizar el flujo de datos
     */
    @Override
    public void run(){
        int leido;
        byte[] buffer;
        try{
            buffer=new byte[1024];
            while((leido=is.read(buffer))>0){
                os.write(buffer,0,leido);
                new logger().logStaticSaver("Se está usando el hilo en 'run()'",Level.WARNING);
            }
            
            is.close();
            os.flush();
            os.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 26H",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 26H: "+e.getMessage()+" en 'run()'",Level.WARNING);
        }
    }
}