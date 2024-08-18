package clases;
//librerías
import org.apache.commons.io.FilenameUtils;
//java
import java.io.File;

/**
 * Clase encargada de verificar las carpetas que utiliza el programa.
 * 
 * @author erick
 */
public class Dirs{
    public static final String USERDIR=System.getProperty("user.dir");
    
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
    
    /**
     * Verifica si el archivo a guardar existe en la carpeta destino. 
     * Si existe, este le agrega un valor incrementable par que no se sobreescriba.
     * 
     * @param f archivo con la dirección a validar.
     * 
     * @return la dirección con el valor extra.
     */
    public static String exists(File f){
        String filename=f.getName();
        
        String name=FilenameUtils.getBaseName(filename);
        String ext=FilenameUtils.getExtension(filename);
        
        String parent=f.getParent();
        f=new File(parent,name+"."+ext);
        for(int i=0;f.exists();i++){
            f=new File(parent,name+"-("+i+")."+ext);
        }
        
        return f.getPath();
    }
    
    /**
     * Valida si existe o no en la dirección almacenada en un archivo de una imagen. 
     * En caso de que no exista, verificará en la carpeta del archivo en la que está almacenada esta dirección.
     * 
     * @param parent dirección del archivo con la dirección almacenada.
     * @param pic dirección de la imagen que está dentro de ese archivo.
     * 
     * @return la dirección de la imagen corregida.
     */
    public static String findPic(String parent,String pic){
        File f=new File(pic);
        if(f.exists()){
            return pic;
        }
        
        return new File(new File(parent).getParent(),f.getName()).getPath();
    }
}