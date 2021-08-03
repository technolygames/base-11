package clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.JOptionPane;

/**
 * Clase encargada de descargar los recursos necesarios para el correcto funcionamiento del programa.
 * Descarga las librerías e idiomas.
 */
public class resourceDownload{
    protected String validacion;
    protected String url;
    
    /**
     * Recibe los datos para que se puedan descargar los datos necesarios.
     * @param validar: Archivo que se validará y guardará
     * @param link: Página web del recurso a decargar
     */
    public resourceDownload(String validar,String link){
        this.validacion=validar;
        this.url=link;
    }
    
    protected InputStream is;
    protected FileOutputStream fos;
    
    protected URL u;
    protected URLConnection uc;
    
    public void downloadLibs(){
        if(!new File("src/data/libs/"+validacion).exists()){
            try{
                u=new URL(url);
                uc=u.openConnection();
                
                is=uc.getInputStream();
                fos=new FileOutputStream("src/data/libs/"+validacion);
                
                new thread(is,fos).run();
                
                is.close();
                fos.flush();
                fos.close();
            }catch(MalformedURLException e){
                JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1I",JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }catch(FileNotFoundException x){
                JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
                x.printStackTrace();
            }catch(IOException k){
                JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
                k.printStackTrace();
            }
        }
    }
}