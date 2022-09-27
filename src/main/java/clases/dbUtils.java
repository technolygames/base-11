package clases;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.Vector;
import java.util.ArrayList;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase hace el manejo de datos para mostrarlos, según el resultado, en la tabla destino.
 * 
 * @author unknown
 */
public class dbUtils{
    public static TableModel resultSetToTableModel(ResultSet rs){
        try{
            ResultSetMetaData metaData=rs.getMetaData();
            int numberOfColumns=metaData.getColumnCount();
            Vector<String> columnNames=new Vector<>();
            
            // Get the column names
            for(int column=0;column<numberOfColumns;column++){
                columnNames.addElement(metaData.getColumnLabel(column+1));
            }
            
            // Get all rows.
            Vector<Vector<Object>> rows=new Vector<>();
            
            while(rs.next()){
                Vector<Object> newRow=new Vector<>();
                
                for(int i=1;i<=numberOfColumns;i++){
                    newRow.addElement(rs.getObject(i));
                }
                
                rows.addElement(newRow);
            }
            
            return new DefaultTableModel(rows,columnNames);
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'resultSetToTableModel()'");
            new logger(Level.SEVERE).exceptionLogger(datos.class.getName(),"resultSetToTableModel-0",e.fillInStackTrace());
            return null;
        }
    }
    
    public static List<List<Object>> resultSetToNestedList(ResultSet rs,boolean includeColumnNames){
        try{
            // To contain all rows.
            List<List<Object>> rows=new ArrayList<>();
            ResultSetMetaData metaData=rs.getMetaData();
            int numberOfColumns=metaData.getColumnCount();
            
            // Include column headers as first row if required
            if(includeColumnNames){
                List<Object> columnNames=new ArrayList<>();
                
                // Get the column names
                for(int column=0;column<numberOfColumns;column++){
                    columnNames.add(metaData.getColumnLabel(column+1));
                }
                rows.add(columnNames);
            }
            
            // Get the data
            while(rs.next()){
                List<Object> newRow=new ArrayList<>();
                
                for(int i=1;i<=numberOfColumns;i++){
                    newRow.add(rs.getObject(i));
                }
                
                rows.add(newRow);
            }
            return rows;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'resultSetToNestedList()'");
            new logger(Level.SEVERE).exceptionLogger(datos.class.getName(),"resultSetToNestedList-0",e.fillInStackTrace());
            return null;
        }
    }

    public static List<List<Object>> resultSetToNestedList(ResultSet rs){
        try{
            // To contain all rows.
            List<List<Object>> rows=new ArrayList<>();
            ResultSetMetaData metaData=rs.getMetaData();
            int numberOfColumns=metaData.getColumnCount();
            
            // Get the data
            while(rs.next()){
                List<Object> newRow=new ArrayList<>();
                
                for(int i=1;i<=numberOfColumns;i++){
                    newRow.add(rs.getObject(i));
                }
                
                rows.add(newRow);
            }
            return rows;
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 0",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 0: "+e.getMessage()+".\nOcurrió en la clase '"+datos.class.getName()+"', en el método 'resultSetToNestedList()'");
            new logger(Level.SEVERE).exceptionLogger(datos.class.getName(),"resultSetToNestedList-0",e.fillInStackTrace());
            return null;
        }
    }
}
