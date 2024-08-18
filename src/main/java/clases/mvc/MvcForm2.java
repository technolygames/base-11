package clases.mvc;

import java.io.InputStream;

/**
 * 
 * @author erick
 */
public class MvcForm2{
    protected int codigo;
    protected String nombre;
    protected String apellidoPaterno;
    protected String apellidoMaterno;
    protected String tipo;
    protected String correo;
    protected String rfc;
    protected String datos;
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
    
    public String getTipo(){
        return tipo;
    }
    public void setTipo(String tipo){
        this.tipo=tipo;
    }
    
    public String getCorreo(){
        return correo;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }
    
    public String getRfc(){
        return rfc;
    }
    public void setRfc(String rfc){
        this.rfc=rfc;
    }
    
    public String getDatos(){
        return datos;
    }
    public void setDatos(String datos){
        this.datos=datos;
    }
    
    public InputStream getImagen(){
        return imagen;
    }
    public void setImagen(InputStream imagen){
        this.imagen=imagen;
    }
}