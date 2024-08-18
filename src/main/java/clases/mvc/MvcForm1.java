package clases.mvc;

import java.io.InputStream;

/**
 *  
 * @author erick
 */
public class MvcForm1{
    protected String password;
    protected int codigo;
    protected String nombre;
    protected String apellidoMaterno;
    protected String apellidoPaterno;
    protected String curp;
    protected String domicilio;
    protected String puesto;
    protected int experiencia;
    protected String gradoEstudios;
    protected int contacto;
    protected String fechaNacimiento;
    protected int edad;
    protected String estado;
    protected String datosExtra;
    protected InputStream imagen;
    protected int numeroVentas;
    
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    
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
    
    public String getCurp(){
        return curp;
    }
    public void setCurp(String curp){
        this.curp=curp;
    }
    
    public String getDomicilio(){
        return domicilio;
    }
    public void setDomicilio(String domicilio){
        this.domicilio=domicilio;
    }
    
    public String getPuesto(){
        return puesto;
    }
    public void setPuesto(String puesto){
        this.puesto=puesto;
    }
    
    public int getExperiencia(){
        return experiencia;
    }
    public void setExperiencia(int experiencia){
        this.experiencia=experiencia;
    }
    
    public String getGradoEstudios(){
        return gradoEstudios;
    }
    public void setGradoEstudios(String gradoEstudios){
        this.gradoEstudios=gradoEstudios;
    }
    
    public int getContacto(){
        return contacto;
    }
    public void setContacto(int contacto){
        this.contacto=contacto;
    }
    
    public String getFechaNacimiento(){
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento){
        this.fechaNacimiento=fechaNacimiento;
    }
    
    public int getEdad(){
        return edad;
    }
    public void setEdad(int edad){
        this.edad=edad;
    }
    
    public String getEstado(){
        return estado;
    }
    public void setEstado(String estado){
        this.estado=estado;
    }
    
    public String getDatosExtra(){
        return datosExtra;
    }
    public void setDatosExtra(String datosExtra){
        this.datosExtra=datosExtra;
    }
    
    public InputStream getImagen(){
        return imagen;
    }
    public void setImagen(InputStream imagen){
        this.imagen=imagen;
    }
    
    public int getNumeroVentas(){
        return numeroVentas;
    }
    public void setNumeroVentas(int numeroVentas){
        this.numeroVentas=numeroVentas;
    }
}