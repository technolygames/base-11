package clases;
//java
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.net.URL;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.URLConnection;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.net.MalformedURLException;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de descargar los recursos necesarios para el correcto funcionamiento del programa.<br>
 * Descarga las librerías e idiomas.
 * 
 * @author erick
 */
public class resourceDownload{
    protected boolean estado=false;
    protected String userdir=dirs.userdir;
    
    protected File f;
    protected InputStream is;
    protected OutputStream os;
    
    protected URL u;
    protected Socket s;
    protected SocketAddress sa;
    protected URLConnection uc;
    
    /**
     * Esta clase se encarga de verificar si hay conectividad a internet.
     * 
     * @param url Dirección a la que se verificará la conexión.
     * @param puerto Número del puerto por el que se va a verificar la conexión.
     */
    public boolean checkConnection(String url,int puerto){
        try{
            s=new Socket();
            sa=new InetSocketAddress(url,puerto);
            
            s.bind(sa);
            s.connect(sa);
            
            if(s.isConnected()==true){
                estado=true;
            }else{
                estado=false;
            }
        }catch(UnknownHostException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1I",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1I: "+e.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'checkConnection()'");
            new logger(Level.SEVERE).exceptionLogger(resourceDownload.class.getName(),"checkConnection-1I",e.fillInStackTrace());
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'checkConnection()'");
            new logger(Level.SEVERE).exceptionLogger(resourceDownload.class.getName(),"checkConnection-1IO",x.fillInStackTrace());
        }
        return estado;
    }
    
    /**
     * Método encargado de descargar de internet las librerías(.jar).
     * 
     * @param validar Archivo que se validará y guardará
     * @param link Página web del recurso a decargar
     */
    public void downloadLibs(String validar,String link){
        f=new File(userdir+"/data/libs/"+validar);
        try{
            if(f.exists()&&f.length()==0){
                u=new URL(link);
                uc=u.openConnection();
                
                is=uc.getInputStream();
                os=new FileOutputStream(userdir+"/data/libs/"+validar);
                
                new Thread(new thread(is,os)).start();
            }else{
                f.createNewFile();
            }
            
            is.close();
            os.flush();
            os.close();
        }catch(MalformedURLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1I",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1I: "+e.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'downloadLibs()'");
            new logger(Level.SEVERE).exceptionLogger(resourceDownload.class.getName(),"downloadLibs-1I",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'downloadLibs()'");
            new logger(Level.SEVERE).exceptionLogger(resourceDownload.class.getName(),"downloadLibs-1IO",x.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+k.getMessage()+"\nOcurrió en la clase '"+resourceDownload.class.getName()+"', en el método 'downloadLibs()'");
            new logger(Level.SEVERE).exceptionLogger(resourceDownload.class.getName(),"downloadLibs-2IO",k.fillInStackTrace());
        }
    }
}