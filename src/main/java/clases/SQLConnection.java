package clases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;

public class SQLConnection{
    private SQLConnection(){}
    public static Connection getConnection(){
        Properties p=new Properties();
        try(FileInputStream fis=new FileInputStream("data/config/databaseInfo.properties")){
            p.load(fis);
            
            String db=p.getProperty("database");
            String ip=p.getProperty("ip");
            String pass=p.getProperty("pass");
            String port=p.getProperty("port");
            String user=p.getProperty("user");
            
            return DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/"+db+"?serverTimezone=UTC",user,pass);
        }catch(SQLException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
            return null;
        }catch(FileNotFoundException n){
            new InternalLogger().catchException(null,n,Level.SEVERE);
            return null;
        }catch(IOException k){
            new InternalLogger().catchException(null,k,Level.SEVERE);
            return null;
        }
    }
}