package clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import java.util.logging.Level;
import javax.swing.JOptionPane;

/**
 * Clase intermedia entre el gestor de base de datos y el programa.
 * Se encarga de registrar, actualizar y eliminar los datos que el usuario desee.
 */
public class datos{
    protected Connection cn;
    protected PreparedStatement ps;
    
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
            new logger().logStaticSaver("Hay conexión a la base de datos",Level.INFO);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 10",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 10: "+e.getMessage()+"\nen 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"getConnection",e.fillInStackTrace());
        }catch(ClassNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 37",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 37: "+x.getMessage()+" en 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"getConnection",x.fillInStackTrace());
        }catch(FileNotFoundException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+ñ.getMessage()+" en 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"getConnection",ñ.fillInStackTrace());
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+k.getMessage()+" en 'getConnection()'",Level.WARNING);
            new logger().exceptionLogger(datos.class.getName(),Level.SEVERE,"getConnection",k.fillInStackTrace());
        }
        return cn;
    }
    /**
     * Guarda los datos de la ventana de productos en la base de datos.
     * 
     * @param codigoProducto Código de identificación del producto.
     * @param nombreProducto Nombre del producto.
     * @param marcaProducto Marca del producto.
     * @param cantidad Cantidad comprada de los productos.
     * @param precio Precio de cada uno de los productos.
     * @param total Precio total al que se vendieron los prodcutos.
     */
    public void insertarDatosProducto(int codigoProducto,String nombreProducto,String marcaProducto,int cantidad,int precio,int total){
        String ins1_query="insert into productos values('"+codigoProducto+"','"+nombreProducto+"','"+marcaProducto+"','"+cantidad+"','"+precio+"','"+total+"',now());";
        try{
            ps=getConnection().prepareStatement(ins1_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().logStaticSaver("Rel 1: Se han guardado correctamente los datos en la base de datos en 'insertarDatosProdducto()'",Level.INFO);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+" en 'insertarDatosProducto()'",Level.WARNING);
            new logger().exceptionLogger("datos",Level.SEVERE,"insertarDatosProducto()",e.fillInStackTrace());
        }
    }
    
    /**
     * Guarda los datos de la ventana de almacén en la base de datos.
     * 
     * @param codigoAlmacen Código de identificación del almacén.
     * @param codigoProducto Código de identificación del producto que se almacenará.
     * @param codigoProveedor Código de identificación del proveedor de los productos.
     * @param nombreProducto Nombre del producto a almacenar.
     * @param nombreProveedor Nombre del proveedor (persona, no empresa).
     * @param marcaProducto Marca del producto que será almacenado.
     * @param cantidad Cantidad que es entraga y almacenada.
     * @param stock Disponibilidad del producto.
     */
    public void insertarDatosAlmacen(int codigoAlmacen,int codigoProducto,int codigoProveedor,String nombreProducto,String nombreProveedor,String marcaProducto,int cantidad,String stock){
        String ins2_query="insert into almacen values('"+codigoAlmacen+"','"+codigoProducto+"','"+codigoProveedor+"','"+nombreProducto+"','"+nombreProveedor+"','"+marcaProducto+"','"+cantidad+"','"+stock+"',now());";
        try{
            ps=getConnection().prepareStatement(ins2_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().logStaticSaver("Rel 1: Se han guardado correctamente los datos en la base de datos de 'insertarDatosAlmacen()'",Level.INFO);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+" en 'insertarDatosAlmacen()'",Level.WARNING);
            new logger().exceptionLogger("datos",Level.SEVERE,"insertarDatosAlmacen()",e.fillInStackTrace());
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
     * @param puesto Puesto del empleado.
     * @param experiencia Experiencia (en años) del empleado; en caso de no tener, escribir "Nulo".
     * @param gradoEstudios Grado actual de estudios del empleado.
     * @param edad Edad actual del empleado; en caso de cumplir años, este deberá de ser actualizado.
     * @param datosExtra Datos extras que el CV del empleado se quieran agregar.
     */
    public void insertarDatosEmpleado(String password,int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado,String puesto,String experiencia,String gradoEstudios,int edad,String datosExtra){
        String ins3_query="insert into empleados values('"+password+"','"+codigoEmpleado+"','"+nombreEmpleado+"','"+apellidoPaternoEmpleado+"','"+apellidoMaternoEmpleado+"','"+puesto+"','"+experiencia+"','"+gradoEstudios+"','"+edad+"','"+datosExtra+"',null,now(),now());";
        try{
            ps=getConnection().prepareStatement(ins3_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().logStaticSaver("Rel 1: Se han guardado correctamente los datos en la base de datos en 'insertarDatosEmpleado()'",Level.INFO);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+" en 'insertarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger("datos",Level.SEVERE,"insertarDatosEmpleado()",e.fillInStackTrace());
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
        String ins4_query="insert into socios values('"+codigoSocio+"','"+nombreSocio+"','"+apellidopSocio+"','"+apellidomSocio+"','"+tipoSocio+"','"+datosExtra+"',null,now(),now());";
        try{
            ps=getConnection().prepareStatement(ins4_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().logStaticSaver("Rel 1: Se han guardado correctamente los datos en la base de datos en 'insertarDatosSocio()'",Level.INFO);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+" en 'insertarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger("datos",Level.SEVERE,"insertarDatosSocio()",e.fillInStackTrace());
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
        String ins5_query="insert into proveedor value('"+codigoProveedor+"','"+nombreProveedor+"','"+apellidoPaternoProvedor+"','"+apellidoMaternoProveedor+"','"+empresa+"',null,now(),now());";
        try{
            ps=getConnection().prepareStatement(ins5_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            new logger().logStaticSaver("Rel 1: Se han guardado correctamente los datos en la base de datos en 'insertarDatosProveedor()'",Level.INFO);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 11: "+e.getMessage()+" en 'insertarDatosProveedor()'",Level.WARNING);
            new logger().exceptionLogger("datos",Level.SEVERE,"insertarDatosProveedor()",e.fillInStackTrace());
        }
    }
    
    public void actualizarDatosEmpleado(String password,int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado,String puesto,String experiencia,String gradoEstudios,int edad){
        String up1_query="update empleados set password='"+password+"',nombre_emp='"+nombreEmpleado+"',apellidop_emp='"+apellidoPaternoEmpleado+"',apellidom_emp='"+apellidoMaternoEmpleado+"',puesto='"+puesto+"',experiencia='"+experiencia+"',grado_estudios='"+gradoEstudios+"',edad='"+edad+"' where codigo_emp='"+codigoEmpleado+"';";
        try{
            ps=getConnection().prepareStatement(up1_query);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void actualizarDatosSocio(){
        String up2_query="";
        try{
            ps=getConnection().prepareStatement(up2_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han actualizado los datos","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /**
     * Elimina los datos especificados en la tabla 'Empleados'.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido del negocio.
     * 
     * @param codigoEmpleado Código del empleado al que se eliminarán los datos.
     */
    public void eliminarDatosEmpleado(int codigoEmpleado){
        String del1_query="delete from empleados where codigo_emp='"+codigoEmpleado+"';";
        try{
            ps=getConnection().prepareStatement(del1_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            new logger().logStaticSaver("Rel 3: Se han eliminado correctamente los datos de la base de datos en 'eliminarDatosEmpleado()'",Level.INFO);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 13: "+e.getMessage()+" en 'eliminarDatosEmpleado()'",Level.WARNING);
            new logger().exceptionLogger("datos",Level.SEVERE,"eliminarDatosEmpleado()",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina los datos especificados en la tabla 'Socios'.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de desafiliación.
     * 
     * @param codigoSocio Código del socio al que se eliminarán los datos.
     */
    public void eliminarDatosSocio(int codigoSocio){
        String del2_query="delete from socios where codigo_part='"+codigoSocio+"';";
        try{
            ps=getConnection().prepareStatement(del2_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            new logger().logStaticSaver("Rel 3: Se han guardado correctamente los datos de la base de datos en 'eliminarDatosSocio()'",Level.INFO);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 13: "+e.getMessage()+" en 'eliminarDatosSocio()'",Level.WARNING);
            new logger().exceptionLogger("datos",Level.SEVERE,"eliminarDatosSocio()",e.fillInStackTrace());
        }
    }
    
    /**
     * Elimina los datos especificados en la tabla 'Proveedor.
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido de la empresa de origen.
     * 
     * @param codigoProveedor Código del proveedor al que se eliminarán los datos.
     */
    public void eliminarDatosProveedor(int codigoProveedor){
        String del3_query="delete from proveedor where codigo_prov='"+codigoProveedor+"';";
        try{
            ps=getConnection().prepareStatement(del3_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            new logger().logStaticSaver("Rel 3: Se han guardado correctamente los datos de la base de datos en 'eliminarDatosProveedor()'",Level.INFO);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 13: "+e.getMessage()+" en 'eliminarDatosProveedor()'",Level.WARNING);
            new logger().exceptionLogger("datos",Level.SEVERE,"eliminarDatosProveedor()",e.fillInStackTrace());
        }
    }
}