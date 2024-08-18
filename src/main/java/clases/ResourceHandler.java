package clases;
//java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.sql.Date;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de descargar los recursos necesarios para el correcto funcionamiento del programa.<br>
 * Descarga las librerías e idiomas.
 * 
 * @author erick
 */
public class ResourceHandler{
    protected ResourceHandler(){}
    
    protected static String path0="data/generic/temp/";
    
    /**
     * Método encargado de descargar de internet los recursos del programa.
     * 
     * @param link del recurso a decargar.
     */
    public static void downloadResources(String link){
        try{
            while(checkConnection(link)!=null){
                URL u=new URL(link);
                URLConnection uc=u.openConnection();
                
                String file=u.getFile();
                File f=new File(path0+file);
                String path1=Dirs.exists(f);
                
                if(!f.exists()&&f.length()==0){
                    try(InputStream is=uc.getInputStream();
                            FileOutputStream fos=new FileOutputStream(path1)){
                        new Thread1(is,fos).run();
                    }
                }
                break;
            }
        }catch(MalformedURLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }catch(FileNotFoundException x){
            new InternalLogger().catchException(null,x,Level.SEVERE);
        }catch(IOException k){
            new InternalLogger().catchException(null,k,Level.SEVERE);
        }
    }
    
    /**
     * Verifica si el programa necesita ser actualizado revisando registros previos de actualización.
     * 
     * @param file que será validado si hay actualización pendiente.
     * @param link del archivo a verificar usando internet.
     * 
     * @return un valor booleano en caso de que se deba actualizar o no el programa o archivo.
     */
    public static boolean checkUpdate(String file,String link){
        try{
            URL u=new URL(link);
            URLConnection uc=u.openConnection();
            
            File f=new File(path0+file);
            
            LocalDate fechalocal1=LocalDate.parse(new Date(f.lastModified()).toString()/*,DateTimeFormatter.ofPattern("dd/MM/yyyy")*/);
            LocalDate fechalocal2=LocalDate.parse(new Date(uc.getIfModifiedSince()).toString()/*,DateTimeFormatter.ofPattern("dd/MM/yyyy")*/);
            
            return fechalocal1.equals(fechalocal2);
        }catch(MalformedURLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
            return false;
        }catch(IOException x){
            new InternalLogger().catchException(null,x,Level.SEVERE);
            return false;
        }
    }
    
    /**
     * Esta clase se encarga de verificar si hay conectividad a internet.
     * 
     * @param url a la que se verificará la conexión.
     * 
     * @return un valor cadena si está conectado o no. Puede ser null si no está conectado a una red.
     * 
     * @throws MalformedURLException
     * @throws IOException
     */
    protected static String checkConnection(String url) throws MalformedURLException,IOException{
        URL u=new URL(url);
        URLConnection uc=u.openConnection();
        uc.connect();
        
        return u.getPath();
    }
}