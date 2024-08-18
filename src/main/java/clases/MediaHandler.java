package clases;
//java
import java.awt.Frame;
import java.awt.Image;
import java.awt.Dialog;
import java.awt.Toolkit;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.BorderLayout;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.UnsupportedLookAndFeelException;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de manejar los diseños de las ventanas o interfaces gráficas de usuario.<br>
 * <ul>
 * <li>Carga y valida las imágenes seleccionadas en la ventana de configuración.</li>
 * <li>Carga el diseño, o color de fondo del programa, de las ventanas.</li>
 * <li>Carga el ícono de la ventana.</li>
 * </ul>
 * 
 * @author erick
 */
public class MediaHandler{
    protected String icon;
    protected String image;
    protected String configDir="data/config/config.properties";
    
    protected Properties p;
    
    /**
     * Muestra en la ventana la imagen que fue seleccionada en la ventana de configuración.
     * 
     * @return la imagen que se mostrará en el formulario o ventanas.
     */
    public Image getFormImage(){
        return Toolkit.getDefaultToolkit().getImage(getImagePath("imagenes","imagen_respaldo"));
    }
    
    /**
     * Obtiene el ícono que está destinado a usarse en las ventanas.<br>
     * Aparece en la esquina superior izquierda de la ventana.
     * 
     * @return la imagen a usar.
     */
    public Image getIconImage(){
        return Toolkit.getDefaultToolkit().getImage(getImagePath("icono","icono_respaldo"));
    }
    
    /**
     * Obtiene la imagen destinanda a usarse en las ventanas y/o como icono de ventana. 
     * Aparece en la esquina superior izquierda de la vetnana o en un recuadro interno con borde negro en las ventanas. 
     * Este método es usado para actualizar la imagen de las ventanas mientras el programa está en ejecución.
     * 
     * @param image Icono/imagen a mostrar y actualizar.
     * 
     * @return la imagen a usar.
     */
    public Image getImage(String image){
        return Toolkit.getDefaultToolkit().getImage(image);
    }
    
    /**
     * Clase para usar el diseño seleccionado en la ventana de configuración.
     * 
     * @param componente en el que mostrará el diseño o color de fondo del programa.
     */
    public void setLookAndFeel(Component componente){
        p=new Properties();
        try(FileInputStream fis=new FileInputStream(configDir)){
            p.load(fis);
            UIManager.setLookAndFeel(p.getProperty("look_and_feel"));
            SwingUtilities.updateComponentTreeUI(componente);
        }catch(ClassNotFoundException e){
            new InternalLogger().catchException(componente,e,Level.SEVERE);
        }catch(InstantiationException x){
            new InternalLogger().catchException(componente,x,Level.SEVERE);
        }catch(IllegalAccessException n){
            new InternalLogger().catchException(componente,n,Level.SEVERE);
        }catch(UnsupportedLookAndFeelException y){
            new InternalLogger().catchException(componente,y,Level.SEVERE);
        }catch(NullPointerException k){
            new InternalLogger().catchException(componente,k,Level.SEVERE);
        }catch(FileNotFoundException s){
            new InternalLogger().catchException(componente,s,Level.SEVERE);
        }catch(IOException d){
            new InternalLogger().catchException(componente,d,Level.SEVERE);
        }
    }
    
    /**
     * Carga la imagen solicitada desde un archivo .properties para ser usada en el programa.
     * 
     * @param propName1 nombre de la propiedad 1.
     * @param propName2 nombre de la propiedad 2.
     * 
     * @return la dirección de la imagen.
     */
    public String getImagePath(String propName1,String propName2){
        p=new Properties();
        try(FileInputStream fis=new FileInputStream(configDir)){
            p.load(fis);
            String dir=p.getProperty(propName1);
            if(!new File(dir).exists()){
                dir=p.getProperty(propName2);
                if(!new File(dir).exists()){
                    try(FileInputStream fis2=new FileInputStream("data/config/preconfig.properties")){
                        p.load(fis2);
                        dir=p.getProperty(propName1);
                    }
                }
            }
            return dir;
        }catch(FileNotFoundException e){
            new InternalLogger().catchException(getFrames(),e,Level.SEVERE);
            return null;
        }catch(IOException e){
            new InternalLogger().catchException(getFrames(),e,Level.SEVERE);
            return null;
        }
    }
    
    /**
     * Obtiene las ventanas existentes en el programa.
     * 
     * @return ventana que se está mostrando en pantalla.
     */
    public static Frame getFrames(){
        Frame f=null;
        for(Frame f1:Frame.getFrames()){
            f=f1;
        }
        return f;
    }
    
    /**
     * Obtiene el nombre del negocio para mostrarlo en pantalla.
     * 
     * @return el nombre del negocio asignado al programa.
     */
    public String getProgramName(){
        p=new Properties();
        try(FileReader fr=new FileReader(configDir,StandardCharsets.UTF_8)){
            p.load(fr);
            return p.getProperty("nombre");
        }catch(FileNotFoundException e){
            new InternalLogger().catchException(getFrames(),e,Level.SEVERE);
            return null;
        }catch(IOException x){
            new InternalLogger().catchException(getFrames(),x,Level.SEVERE);
            return null;
        }
    }
    
    public static void openPanel(Dialog parent,Component child){
        EventQueue.invokeLater(()->{
            parent.setLayout(new BorderLayout());
            parent.add(child,BorderLayout.CENTER);
            parent.pack();
        });
    }
    
    public static void openPanel(Frame parent,Component child){
        EventQueue.invokeLater(()->{
            parent.setLayout(new BorderLayout());
            parent.add(child,BorderLayout.CENTER);
            parent.pack();
        });
    }
}