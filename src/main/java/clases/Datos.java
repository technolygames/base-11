package clases;
//clases
import clases.mvc.MvcForm1;
import clases.mvc.MvcForm2;
import clases.mvc.MvcForm3;
//java
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.List;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Clase intermedia entre el gestor de base de datos y el programa. 
 * Se encarga de registrar, actualizar y eliminar los datos que el usuario desee.
 * 
 * @author erick
 */
public class Datos{
    /**
     * Crea la base de datos que se usará para importar el archivo en el que está la base de datos.
     * Advertencia: no usar en otras clases.
     * 
     * @param nombre Nombre de la base de datos.
     */
    public void crearBD(String nombre){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("create database "+nombre+"")){
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se creó la base de datos, pero falta importar el archivo","Rel 1E",JOptionPane.INFORMATION_MESSAGE);
            InternalLogger.staticLogger(Level.INFO,"Rel 1E: se creó correctamente la base de datos.");
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Guarda los modelo de la ventana de productos en la base de modelo.
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
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("insert into productos values(?,?,?,?,?,?,?,now())")){
            ps.setInt(1,codigoProducto);
            ps.setInt(2,codigoEmpleado);
            ps.setString(3,nombreProducto);
            ps.setString(4,marca);
            ps.setInt(5,cantidad);
            ps.setInt(6,precio);
            ps.setInt(7,total);
            ps.addBatch();
            ps.executeBatch();
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Guarda los modelo de la ventana de almacén en la base de modelo.
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
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("insert into almacen values(?,?,?,?,?,?,?,?,now())")){
            ps.setInt(1,codigoProducto);
            ps.setInt(2,codigoLote);
            ps.setInt(3,codigoProveedor);
            ps.setString(4,nombreProducto);
            ps.setString(5,marca);
            ps.setInt(6,cantidad);
            ps.setInt(7,precioUnitario);
            ps.setString(8,stock);
            ps.addBatch();
            ps.executeBatch();
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Guarda los modelo de la ventana de empleados en la base de modelo.
     * 
     * @param datos que serán almacenados del empleado en la base de modelo.
     */
    public void insertarDatosEmpleado(List<MvcForm1> datos){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("insert into empleados values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now())")){
            ps.setString(1,datos.get(0).getPassword());
            ps.setInt(2,datos.get(0).getCodigo());
            ps.setString(3,datos.get(0).getNombre());
            ps.setString(4,datos.get(0).getApellidoPaterno());
            ps.setString(5,datos.get(0).getApellidoMaterno());
            ps.setString(6,datos.get(0).getCurp());
            ps.setString(7,datos.get(0).getDomicilio());
            ps.setString(8,datos.get(0).getPuesto());
            ps.setInt(9,datos.get(0).getExperiencia());
            ps.setString(10,datos.get(0).getGradoEstudios());
            ps.setInt(11,datos.get(0).getContacto());
            ps.setString(12,datos.get(0).getFechaNacimiento());
            ps.setInt(13,datos.get(0).getEdad());
            ps.setString(14,datos.get(0).getEstado());
            ps.setString(15,datos.get(0).getDatosExtra());
            ps.setBlob(16,datos.get(0).getImagen());
            ps.execute();

            new InternalLogger().storeMessageConfirmation(null,Level.INFO);
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Guarda los modelo de la ventana de proveedor en la base de modelo.
     * 
     * @param datos lista con los modelo del proveedor.
     */
    public void insertarDatosProveedor(List<MvcForm3> datos){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("insert into proveedor value(?,?,?,?,?,?,?,now(),now())")){
            ps.setInt(1,datos.get(0).getCodigo());
            ps.setString(2,datos.get(0).getNombre());
            ps.setString(3,datos.get(0).getApellidoPaterno());
            ps.setString(4,datos.get(0).getApellidoMaterno());
            ps.setString(5,datos.get(0).getEmpresa());
            ps.setInt(6,datos.get(0).getContacto());
            ps.setBinaryStream(7,datos.get(0).getImagen());
            ps.execute();
            
            new InternalLogger().storeMessageConfirmation(null,Level.INFO);
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Guarda los modelo de la ventana de socios en la base de modelo.
     * 
     * @param datos que serán almacenados del socio en la base de modelo.
     */
    public void insertarDatosSocio(List<MvcForm2> datos){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("insert into socios values(?,?,?,?,?,?,?,?,?,now(),now())")){
            ps.setInt(1,datos.get(0).getCodigo());
            ps.setString(2,datos.get(0).getNombre());
            ps.setString(3,datos.get(0).getApellidoPaterno());
            ps.setString(4,datos.get(0).getApellidoMaterno());
            ps.setString(5,datos.get(0).getTipo());
            ps.setString(6,datos.get(0).getCorreo());
            ps.setString(7,datos.get(0).getRfc());
            ps.setString(8,datos.get(0).getDatos());
            ps.setBinaryStream(9,datos.get(0).getImagen());
            ps.execute();

            new InternalLogger().storeMessageConfirmation(null,Level.INFO);
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Guarda los modelo para conteo de asistencia del empleado.
     * 
     * @param codigoEmpleado Código de identificación del empleado.
     * @param nombreEmpleado Nombre(s) del empleado.
     * @param apellidoPaternoEmpleado Apellido paterno del empleado.
     * @param apellidoMaternoEmpleado Apellido materno del empleado.
     */
    public void insertarDatosConteo(int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("insert into conteo(codigo_emp,nombre_emp,apellidop_emp,apellidom_emp,no_ventas,fecha_sesion) values(?,?,?,?,0,now())")){
            ps.setInt(1,codigoEmpleado);
            ps.setString(2,nombreEmpleado);
            ps.setString(3,apellidoPaternoEmpleado);
            ps.setString(4,apellidoMaternoEmpleado);
            ps.execute();

            InternalLogger.staticLogger(Level.INFO,"Rel 1: se guardaron correctamente los datos a la base de datos.");
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Actualiza datos al iniciar sesión en el programa. 
     * Este método no es universal y se debe usar en casos muy específicos.
     * 
     * @param password del usuario loggeado.
     * @param user1 usuario loggeado.
     */
    public void actualizarDatosLogin(String password,String user1){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("update empleados set fecha_sesion=now() where password=? and nombre_emp=? or curp=?")){
            ps.setString(1,password);
            ps.setString(2,user1);
            ps.setString(3,user1);
            ps.execute();
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Actualiza la cantidad del producto que hay en stock (en almacén).
     * 
     * @param cantidad del producto que se vendió.
     * @param codigo de identificación del producto vendido.
     */
    public void actualizarDatosAlmacen(int cantidad,int codigo){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("update almacen set cantidad=cantidad-? where codigo_prod=?")){
            ps.setInt(1,cantidad);
            ps.setInt(2,codigo);
            ps.execute();
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Actualiza el historial de ventas del empleado de ese día. 
     * No actualiza los registros de otros días.
     * 
     * @param codigo de identificación del empleado.
     * @param fecha de inicio de sesión del empleado.
     */
    public void actualizarDatosConteoVentas(int codigo,String fecha){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("update conteo set no_ventas=no_ventas+1 where codigo_emp=? and fecha_sesion=?")){
            ps.setInt(1,codigo);
            ps.setString(2,fecha);
            ps.execute();
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    //
    
    /**
     * Actualiza registros en la base de datos. 
     * Este método es específico para cadena de texto.
     * 
     * @param tabla a cambiar registros.
     * @param campo1 del registro a cambiar.
     * @param campo2 de identificación.
     * @param datos a cambiar (nuevos modelo).
     * @param codigo de identificación del registro.
     * @param flag para mostrar la notificación de confirmación.
     */
    public void actualizarDatosString(String tabla,String campo1,String campo2,String datos,int codigo,boolean flag){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("update "+tabla+" set "+campo1+"=? where "+campo2+"=?")){
            ps.setString(1,datos);
            ps.setInt(2,codigo);
            ps.execute();

            if(flag){
                new InternalLogger().updateMessageConfirmation(null,Level.INFO);
            }else{
                InternalLogger.staticLogger(Level.INFO,"Rel 2: se actualizaron correctamente los datos.");
            }
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Actualiza registros en la base de datos. 
     * Este método es específico para enteros (números).
     * 
     * @param tabla a cambiar registros.
     * @param campo1 del registro a cambiar.
     * @param campo2 de identificación.
     * @param datos a cambiar (nuevos modelo).
     * @param codigo de identificación del registro.
     * @param flag para mostrar la notificación de confirmación.
     */
    public void actualizarDatosInteger(String tabla,String campo1,String campo2,int datos,int codigo,boolean flag){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("update "+tabla+" set "+campo1+"=? where "+campo2+"=?")){
            ps.setInt(1,datos);
            ps.setInt(2,codigo);
            ps.execute();

            if(flag){
                new InternalLogger().updateMessageConfirmation(null,Level.INFO);
            }else{
                InternalLogger.staticLogger(Level.INFO,"Rel 2: se actualizaron correctamente los datos.");
            }
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Actualiza registros en la base de datos. 
     * Este método es específico para fechas.
     * 
     * @param tabla a cambiar registros.
     * @param campo1 del registro a cambiar.
     * @param campo2 de identificación.
     * @param fecha a cambiar (nuevos modelo).
     * @param codigo de identificación del registro.
     * 
     * @throws SQLException si el gestor detecta un problema, lanzará este error.
     */
    public void actualizarDatosDate(String tabla,String campo1,String campo2,Date fecha,int codigo) throws SQLException{
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("update "+tabla+" set "+campo1+"=? where "+campo2+"=?")){
            ps.setDate(1,fecha);
            ps.setInt(2,codigo);
            ps.execute();

            new InternalLogger().updateMessageConfirmation(null,Level.INFO);
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Elimina datos específicos de la tabla empleados. 
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido del negocio. 
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoEmpleado a eliminar.
     */
    public void eliminarDatosEmpleado(int codigoEmpleado){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("delete from empleados where codigo_emp=?")){
            ps.setInt(1,codigoEmpleado);
            ps.execute();

            new InternalLogger().deleteMessageConfirmation(null,Level.INFO);
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Elimina datos específicos de la tabla socios. 
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de desafiliación. 
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoSocio a eliminar.
     */
    public void eliminarDatosSocio(int codigoSocio){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("delete from socios where codigo_part=?")){
            ps.setInt(1,codigoSocio);
            ps.execute();

            new InternalLogger().deleteMessageConfirmation(null,Level.INFO);
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Elimina datos específicos de la tabla proveedor. 
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido de la empresa de origen. 
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoProveedor a eliminar.
     */
    public void eliminarDatosProveedor(int codigoProveedor){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("delete from proveedor where codigo_prov=?")){
            ps.setInt(1,codigoProveedor);
            ps.execute();

            new InternalLogger().deleteMessageConfirmation(null,Level.INFO);
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
    
    /**
     * Elimina datos específicos de la tabla conteo. 
     * Si se eliminan los datos, no se podrán recuperar. Usar solamente en caso de despido del negocio. 
     * Se pueden reestablecer si previamente se creó la copia de seguridad.
     * 
     * @param codigoEmpleado a eliminar.
     */
    public void eliminarDatosConteo(int codigoEmpleado){
        try(Connection cn=SQLConnection.getConnection();
                PreparedStatement ps=cn.prepareStatement("delete from conteo where codigo_emp=?")){
            ps.setInt(1,codigoEmpleado);
            ps.execute();

            InternalLogger.staticLogger(Level.INFO,"Rel 3: se eliminaron correctamente los registros de la base de datos.");
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }
    }
}