package clases;
//clases
import venPrimarias.start;
//java
import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
/**
 * Clase intermedia entre el gestor de base de datos y el programa.
 * Se encarga de registrar, actualizar y eliminar los datos que el usuario desee.
 */
public class datos{
    protected Connection cn;
    protected PreparedStatement ps;
    protected Statement s;
    
    protected Properties p;
    
    protected String controlador;
    protected String ip;
    protected String puerto;
    protected String bd;
    protected String usuario;
    protected String contraseña;
    
    /**
     * Conexión a la base de datos
     * 
     * @return Regresa la conexión a la base de datos
     */
    public Connection getConnection(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/databaseInfo.properties"));
            
            controlador=p.getProperty("driver");
            ip=p.getProperty("ip");
            puerto=p.getProperty("port");
            bd=p.getProperty("database");
            usuario=p.getProperty("user");
            contraseña=p.getProperty("pass");
            
            Class.forName(controlador);
            cn=DriverManager.getConnection("jdbc:mysql://"+ip+":"+puerto+"/"+bd+"",usuario,contraseña);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 10",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 10: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-10",e.fillInStackTrace());
        }catch(ClassNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 37",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 37: "+x.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-37",x.fillInStackTrace());
        }catch(FileNotFoundException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 1IO: "+ñ.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-1IO",ñ.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 2IO: "+k.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"getConnection-2IO",k.fillInStackTrace());
        }
        return cn;
    }
    /**
     * Guarda los datos de la ventana de productos en la base de datos.
     * 
     * @param codigoProducto Código de identificación del producto.
     * @param codigoEmpleado Código de identificación del empleado que atendió la venta.
     * @param nombreProducto Nombre del producto.
     * @param marca Marca del producto.
     * @param cantidad Cantidad comprada de los productos.
     * @param precio Precio de cada uno de los productos.
     * @param total Precio total al que se vendieron los prodcutos.
     */
    public void insertarDatosProducto(int codigoProducto,int codigoEmpleado,String nombreProducto,String marca,int cantidad,int precio,int total){
        try{
            s=getConnection().createStatement();
            s.addBatch("insert into productos values('"+codigoProducto+"','"+codigoEmpleado+"','"+nombreProducto+"','"+marca+"','"+cantidad+"','"+precio+"','"+total+"',now())");
            s.executeBatch();
            
            s.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosProducto()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosProducto-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de almacén en la base de datos.
     * 
     * @param codigoProducto Código de identificación del almacén.
     * @param codigoLote Código de identificación del lote de productos que se almacenará.
     * @param codigoProveedor Código de identificación del proveedor de los productos.
     * @param nombreProducto Nombre del producto a almacenar.
     * @param marca Marca del producto que será almacenado.
     * @param cantidad Cantidad que es entraga y almacenada.
     * @param precioUnitario Precio de cada producto del lote.
     * @param stock Disponibilidad del producto.
     */
    public void insertarDatosAlmacen(int codigoProducto,int codigoLote,int codigoProveedor,String nombreProducto,String marca,int cantidad,int precioUnitario,String stock){
        try{
            s=getConnection().createStatement();
            s.addBatch("insert into almacen values('"+codigoProducto+"','"+codigoLote+"','"+codigoProveedor+"','"+nombreProducto+"','"+marca+"','"+cantidad+"','"+precioUnitario+"','"+stock+"',now())");
            s.executeBatch();
            
            s.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosAlmacen()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosAlmacen-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de empleados en la base de datos.
     * 
     * @param password Contraseña asiganada para que el empleado pueda acceder al programa.
     * @param codigoEmpleado Código de identificación del empleado.
     * @param nombreEmpleado Nombre(s) del empleado.
     * @param apellidoPaternoEmpleado Apellido paterno del empleado.
     * @param apellidoMaternoEmpleado Apellido materno del empleado.
     * @param curp Clave única de registro de población del empleado.
     * @param domicilio Domicilio del empleado.
     * @param puesto Puesto del empleado.
     * @param experiencia Experiencia (en años) del empleado; en caso de no tener, escribir "Nulo".
     * @param gradoEstudios Grado actual de estudios del empleado.
     * @param contacto Número telefónico del empleado.
     * @param fechaNacimiento Fecha de nacimiento del empleado.
     * @param edad Edad actual del empleado; en caso de cumplir años, este deberá de ser actualizado.
     * @param estado Estado actual en el negocio.
     * @param datosExtra Datos extras que el CV del empleado se quieran agregar.
     * @param foto Foto del empleado.
     */
    public void insertarDatosEmpleado(String password,int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado,String curp,String domicilio,String puesto,int experiencia,String gradoEstudios,int contacto,String fechaNacimiento,int edad,String estado,String datosExtra,InputStream foto){
        try{
            ps=getConnection().prepareStatement("insert into empleados(password,codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,curp,domicilio,puesto,experiencia,grado_estudios,contacto,fecha_nacimiento,edad,estado,datos_extra,foto,fecha_registro,fecha_sesion) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now());");
            ps.setString(1,password);
            ps.setInt(2,codigoEmpleado);
            ps.setString(3,nombreEmpleado);
            ps.setString(4,apellidoPaternoEmpleado);
            ps.setString(5,apellidoMaternoEmpleado);
            ps.setString(6,curp);
            ps.setString(7,domicilio);
            ps.setString(8,puesto);
            ps.setInt(9,experiencia);
            ps.setString(10,gradoEstudios);
            ps.setInt(11,contacto);
            ps.setString(12,fechaNacimiento);
            ps.setInt(13,edad);
            ps.setString(14,estado);
            ps.setString(15,datosExtra);
            ps.setBlob(16,foto);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().staticLogger("Rel 1: se guardaron correctamente los datos a la base de datos.\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosEmpleado()'.\nUsuario que hizo la acción: "+String.valueOf(start.userID),Level.INFO);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosEmpleado-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de socios en la base de datos.
     * 
     * @param codigoSocio Código de identificación del socio.
     * @param nombreSocio Nombre(s) del socio.
     * @param apellidopSocio Apellido paterno del socio.
     * @param apellidomSocio Apellido materno del socio.
     * @param tipoSocio Tipo de afiliación.
     * @param datosExtra Datos extra que se quieran agregar como descripción del socio.
     */
    public void insertarDatosSocio(int codigoSocio,String nombreSocio,String apellidopSocio,String apellidomSocio,String tipoSocio,String datosExtra){
        try{
            ps=getConnection().prepareStatement("insert into socios values('"+codigoSocio+"','"+nombreSocio+"','"+apellidopSocio+"','"+apellidomSocio+"','"+tipoSocio+"','"+datosExtra+"',null,now(),now());");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosSocio-11",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de proveedor en la base de datos.
     * 
     * @param codigoProveedor Código de identificación del proveedor.
     * @param nombreProveedor Nombre(s) del proveedor.
     * @param apellidoPaternoProvedor Apellido paterno del proveedor.
     * @param apellidoMaternoProveedor Apellido materno del proveedor.
     * @param empresa Empresa procedencia del proveedor.
     */
    public void insertarDatosProveedor(int codigoProveedor,String nombreProveedor,String apellidoPaternoProvedor,String apellidoMaternoProveedor,String empresa){
        try{
            ps=getConnection().prepareStatement("insert into proveedor value('"+codigoProveedor+"','"+nombreProveedor+"','"+apellidoPaternoProvedor+"','"+apellidoMaternoProveedor+"','"+empresa+"',null,now(),now());");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 11: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'insertarDatosProveedor()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"insertarDatosProveedor-11",e.fillInStackTrace());
        }
    }
    
    public void actualizarDatosEmpleado(String query){
        try{
            ps=getConnection().prepareStatement("update empleados "+query);
            ps.executeUpdate();
            
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 12: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"actualizarDatosEmpleado-12",e.fillInStackTrace());
        }
    }
    
    public void actualizarDatosSocio(String query){
        try{
            ps=getConnection().prepareStatement("update socios "+query);
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 12: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'actualizarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"actualizarDatosSocio-12",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina los datos especificados en la tabla 'Empleados'.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido del negocio.
     * 
     * @param codigoEmpleado Código del empleado al que se eliminarán los datos.
     */
    public void eliminarDatosEmpleado(int codigoEmpleado){
        try{
            ps=getConnection().prepareStatement("delete from empleados where codigo_emp='"+codigoEmpleado+"';");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"eliminarDatosEmpleado-13",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina los datos especificados en la tabla 'Socios'.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de desafiliación.
     * 
     * @param codigoSocio Código del socio al que se eliminarán los datos.
     */
    public void eliminarDatosSocio(int codigoSocio){
        try{
            ps=getConnection().prepareStatement("delete from socios where codigo_part='"+codigoSocio+"';");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"eliminarDatosSocio-13",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina los datos especificados en la tabla 'Proveedor.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido de la empresa de origen.
     * 
     * @param codigoProveedor Código del proveedor al que se eliminarán los datos.
     */
    public void eliminarDatosProveedor(int codigoProveedor){
        try{
            ps=getConnection().prepareStatement("delete from proveedor where codigo_prov='"+codigoProveedor+"';");
            ps.execute();
            
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().staticLogger("Error 13: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'eliminarDatosProveedor()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.WARNING,"eliminarDatosProveedor-13",e.fillInStackTrace());
        }
    }
}