package clases;
//java
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
//con extensión larga
import java.util.logging.Level;

/**
 * Clase encargada de crear una imagen. 
 * Este hilo se encarga de leer y crear una imagen desde bytes almacenados en la base de datos.
 * 
 * @author erick
 */
public class thread2 implements Runnable{
    protected ResultSet resultado;
    protected Blob blob;
    protected OutputStream os;
    
    /**
     * Recibe los datos para que pueda leer y escribir la foto de la base de datos.
     * 
     * @param result de la consulta.
     * @param os destino de la imagen.
     */
    public thread2(ResultSet result,OutputStream os){
        this.resultado=result;
        this.os=os;
    }
    
    /**
     * Método para realizar la lectura de la imagen en la base de datos, crearla y almacenarla en una dirección específica.
     */
    @Override
    public void run(){
        try{
            while(resultado.next()){
                blob=resultado.getBlob("foto");
                byte[] bytes=blob.getBytes(1,(int)blob.length());
                os.write(bytes);
                break;
            }
            
            blob.free();
            os.flush();
            os.close();
        }catch(FileNotFoundException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+e.getMessage()+".\nOcurrió en la clase '"+thread2.class.getName()+"', en el método 'run()'");
            new logger(Level.SEVERE).exceptionLogger(thread2.class.getName(),"run-1IO",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+x.getMessage()+".\nOcurrió en la clase '"+thread2.class.getName()+"', en el método 'run()'");
            new logger(Level.SEVERE).exceptionLogger(thread2.class.getName(),"run-2IO",x.fillInStackTrace());
        }catch(SQLException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 14",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 14: "+n.getMessage()+".\nOcurrió en la clase '"+thread2.class.getName()+"', en el método 'run()'");
            new logger(Level.SEVERE).exceptionLogger(thread2.class.getName(),"run-14",n.fillInStackTrace());
        }
    }
}
