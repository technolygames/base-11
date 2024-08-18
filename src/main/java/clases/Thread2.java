package clases;
//java
import java.io.IOException;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
//con extensión larga
import java.util.logging.Level;

/**
 * Clase encargada de crear una imagen. 
 * Este hilo se encarga de leer y crear una imagen desde bytes almacenados en la base de datos.
 * 
 * @author erick
 */
public class Thread2 implements Runnable{
    protected ResultSet resultado;
    protected OutputStream os;
    
    /**
     * Recibe los datos para que pueda leer y escribir la foto de la base de datos.
     * 
     * @param result de la consulta.
     * @param os destino de la imagen.
     */
    public Thread2(ResultSet result,OutputStream os){
        this.resultado=result;
        this.os=os;
    }
    
    protected String methodName="run";
    
    /**
     * Método para realizar la lectura de la imagen en la base de datos, crearla y almacenarla en una dirección específica.
     */
    
    @Override
    public void run(){
        try{
            while(resultado.next()){
                Blob blob=resultado.getBlob("foto");
                byte[] bytes=blob.getBytes(1,(int)blob.length());
                os.write(bytes);
                break;
            }
            
            os.flush();
            os.close();
        }catch(FileNotFoundException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }catch(IOException x){
            new InternalLogger().catchException(null,x,Level.SEVERE);
        }catch(SQLException n){
            new InternalLogger().catchException(null,n,Level.SEVERE);
        }
    }
}
