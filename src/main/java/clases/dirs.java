package clases;
//java
import java.io.File;

/**
 * Clase encargada de verificar las carpetas que utiliza el programa.
 * 
 * @author erick
 */
public class dirs{
    public static String userdir=System.getProperty("user.dir");
    
    /**
     * Verifica si existen las carpetas. Si no existen, las crea automáticamente.
     * 
     * @param dir Dirección a verificar y crear.
     */
    public void makeDir(String dir){
        File direccion=new File(dir);
        if(!direccion.exists()){
            direccion.mkdir();
        }
    }
}