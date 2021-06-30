package clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;
import javax.swing.JOptionPane;

public class datos{
    protected Connection cn;
    protected PreparedStatement ps;
    
    protected Properties p;
    
    protected String driver;
    protected String ip;
    protected String puerto;
    protected String bd;
    protected String usuario;
    protected String contraseña;
    
    public Connection getConnection(){
        p=new Properties();
        try{
            p.load(new FileInputStream("src/data/config/databaseInfo.properties"));
            
            driver=p.getProperty("driver");
            ip=p.getProperty("ip");
            puerto=p.getProperty("port");
            bd=p.getProperty("database");
            usuario=p.getProperty("user");
            contraseña=p.getProperty("pass");
            
            Class.forName(driver);
            cn=DriverManager.getConnection("jdbc:mysql://"+ip+":"+puerto+"/"+bd+"",usuario,contraseña);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 10",JOptionPane.WARNING_MESSAGE);
        }catch(ClassNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 37",JOptionPane.WARNING_MESSAGE);
        }catch(FileNotFoundException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
        }catch(IOException k){
            JOptionPane.showMessageDialog(null,"Error:\n"+k.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
        }
        return cn;
    }
    
    public void insertarDatosProducto(int codigoProducto,String nombreProducto,String marcaProducto,int cantidad,int precio,int total){
        String ins1_query="insert into productos values('"+codigoProducto+"','"+nombreProducto+"','"+marcaProducto+"','"+cantidad+"','"+precio+"','"+total+"',now())";
        try{
            ps=getConnection().prepareStatement(ins1_query);
            ps.execute();
            JOptionPane.showConfirmDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void insertarDatosAlmacen(int codigoAlmacen,int codigoProducto,int codigoProveedor,String nombreProducto,String nombreProveedor,String marcaProducto,int cantidad,String stock){
        String ins2_query="insert into almacen values('"+codigoAlmacen+"','"+codigoProducto+"','"+codigoProveedor+"','"+nombreProducto+"','"+nombreProveedor+"','"+marcaProducto+"','"+cantidad+"','"+stock+"',now());";
        try{
            ps=getConnection().prepareStatement(ins2_query);
            ps.execute();
            JOptionPane.showConfirmDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void insertarDatosEmpleado(String password,int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmp,String puesto,String experiencia,String gradoEstudios,int edad,String datosExtra){
        String ins3_query="insert into empleados values('"+password+"','"+codigoEmpleado+"','"+nombreEmpleado+"','"+apellidoPaternoEmpleado+"','"+apellidoMaternoEmp+"','"+puesto+"','"+experiencia+"','"+gradoEstudios+"','"+edad+"','"+datosExtra+"',null,now(),now());";
        try{
            ps=getConnection().prepareStatement(ins3_query);
            ps.execute();
            JOptionPane.showConfirmDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void insertarDatosSocio(int codigoSocio,String nombreSocio,String apellidopSocio,String apellidomSocio,String tipoSocio,String datosExtra){
        String ins4_query="insert into socios values('"+codigoSocio+"','"+nombreSocio+"','"+apellidopSocio+"','"+apellidomSocio+"','"+tipoSocio+"','"+datosExtra+"',null,now(),now());";
        try{
            ps=getConnection().prepareStatement(ins4_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void insertarDatosProveedor(int codigoProv,String nombreProv,String apellidopProv,String apellidomProv,String empresa){
        String ins5_query="insert into proveedor value('"+codigoProv+"','"+nombreProv+"','"+apellidopProv+"','"+apellidomProv+"','"+empresa+"',null,now(),now());";
        try{
            ps=getConnection().prepareStatement(ins5_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han guardado los datos","Rel 1",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 11",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void actualizarDatosEmpleado(String password,int codigoEmpleado,String nombreEmpleado,String apellidoPaternoEmpleado,String apellidoMaternoEmpleado,String puesto,String experiencia,String gradoEstudios,int edad){
        String up1_query="update empleados set password='"+password+"',nombre_emp='"+nombreEmpleado+"',apellidop_emp='"+apellidoPaternoEmpleado+"',apellidom_emp='"+apellidoMaternoEmpleado+"',puesto='"+puesto+"',experiencia='"+experiencia+"',grado_estudios='"+gradoEstudios+"',edad='"+edad+"' where codigo_emp='"+codigoEmpleado+"';";
        try{
            ps=getConnection().prepareStatement(up1_query);
            ps.executeUpdate();
            JOptionPane.showConfirmDialog(null,"Se ha actualizado el registro","Rel 2",JOptionPane.INFORMATION_MESSAGE);
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
            JOptionPane.showMessageDialog(null,"Se ha actualizado el registro","Rel 2",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 12",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void eliminarDatosEmpleado(int codigoEmpleado){
        String del1_query="delete from empleados where codigo_emp='"+codigoEmpleado+"';";
        try{
            ps=getConnection().prepareStatement(del1_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void eliminarDatosSocio(int codigoSocio){
        String del2_query="delete from socios where codigo_part='"+codigoSocio+"';";
        try{
            ps=getConnection().prepareStatement(del2_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
        }
    }
    
    public void eliminarDatosProveedor(int codigoPro){
        String del3_query="delete from proveedor where codigo_prov='"+codigoPro+"';";
        try{
            ps=getConnection().prepareStatement(del3_query);
            ps.execute();
            JOptionPane.showMessageDialog(null,"Se han eliminado los datos","Rel 3",JOptionPane.INFORMATION_MESSAGE);
            ps.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 13",JOptionPane.WARNING_MESSAGE);
        }
    }
}