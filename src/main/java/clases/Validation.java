package clases;
//java
import java.awt.Frame;
import java.awt.EventQueue;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Esta clase se encarga de hacer las validaciones para ventanas especiales.<br>
 * Hace la validación del rol del usuario loggeado.
 * 
 * @author erick
 */
public class Validation{
    protected String rol0="Dueño";
    protected String rol1="Programador";
    protected String rol2="Desarrollador";
    
    protected String puesto;
    protected String clase;
    protected Frame ventana;
    
    protected Properties p;
    
    /**
     * Inicializa la instancia para mandar los datos solicitados y la ventana destino para que pueda hacer uso del método.
     * 
     * @param frame que se abrirá al validar correctamente los datos solicitados.
     * @param role del empleado para verificar si tiene o no permisos.
     * @param clase que es la misma que la ventana que se abrirá ak validar correctamente los datos.
     */
    public Validation(Frame frame,String role,String clase){
        this.ventana=frame;
        this.puesto=role;
        this.clase=clase;
    }
    
    /**
     * Inicializa la instancia para mandar los datos solicitados y validarlos.
     * 
     * @param role del empleado para verificar si tiene o no permisos.
     * @param clase que es la misma que la ventana que se abrirá ak validar correctamente los datos.
     */
    public Validation(String role,String clase){
        this.puesto=role;
        this.clase=clase;
    }
    
    /**
     * Método encargado de validar los datos cuando se crea la instancia de esta clase.<br>
     * Roles permitidos:
     * <ul>
     * <li>Dueño (owner)</li>
     * <li>Programador (programmer)</li>
     * <li>Desarrollador (developer)</li>
     * </ul>
     * Si el usuario tiene el rol de empleado, no tendrá acceso a las características y ventanas que hacen uso de esta clase.
     */
    public void toRestrictedForm(){
        if(isAccessible()){
            EventQueue.invokeLater(()->
                ventana.setVisible(true)
            );
            InternalLogger.staticLogger(Level.INFO,"Rel 5: validación correcta a '"+clase+"'.");
        }else{
            JOptionPane.showMessageDialog(MediaHandler.getFrames(),"Acceso restringido","Error 38",JOptionPane.WARNING_MESSAGE);
            InternalLogger.staticLogger(Level.WARNING,"Error 38: usuario sin privilegios.");
        }
    }
    
    /**
     * Verifica si es posible ver si cierta característica es visible para el puesto que fue asignado al usuario logeado.
     * 
     * @return si es posible ver o acceder a la característica 
     */
    public boolean isAccessible(){
        return puesto.equals(rol0)||puesto.equals(rol1)||puesto.equals(rol2);
    }
    
    /**
     * Verifica si el usuario que está loggeado tiene permiso de Dueño para realizar la acción a la que este método se usará.
     * 
     * @return un valor booleano si tiene o no permiso.
     */
    public boolean hasOwnerRole(){
        return puesto.equals(rol0);
    }
    
    /**
     * Verifica si el usuario que está loggeado tiene permiso de desarrollador o programador para realizar la acción a la que este método se usará.
     * 
     * @return un valor booleano si tiene o no permiso.
     */
    public boolean hasDevRole(){
        return puesto.equals(rol1)||puesto.equals(rol2);
    }
}