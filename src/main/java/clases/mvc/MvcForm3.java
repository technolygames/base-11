package clases.mvc;

import java.io.InputStream;

/**
 *
 * @author erick
 */
public class MvcForm3{
    protected int codigo;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String empresa;
    protected int contacto;
    protected InputStream imagen;
    
    public int getCodigo(){
        return codigo;
    }
    
    public void setCodigo(int codigo){
        this.codigo=codigo;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    
    public String getApellidoPaterno(){
        return apellidoPaterno;
    }
    
    public void setApellidoPaterno(String apellidoPaterno){
        this.apellidoPaterno=apellidoPaterno;
    }
    
    public String getApellidoMaterno(){
        return apellidoMaterno;
    }
    
    public void setApellidoMaterno(String apellidoMaterno){
        this.apellidoMaterno=apellidoMaterno;
    }
    
    public String getEmpresa(){
        return empresa;
    }
    
    public void setEmpresa(String empresa){
        this.empresa=empresa;
    }
    
    public int getContacto(){
        return contacto;
    }
    
    public void setContacto(int contacto){
        this.contacto=contacto;
    }
    
    public InputStream getImagen(){
        return imagen;
    }
    
    public void setImagen(InputStream imagen){
        this.imagen=imagen;
    }
}