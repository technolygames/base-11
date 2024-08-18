package clases;
//java
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import java.util.List;
import java.util.ArrayList;
//extension larga
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

/**
 * Esta clase hace el manejo de datos para mostrarlos, según el resultado, en la tabla destino.
 * 
 * @author unknown
 */
public class DbUtils{
    protected DbUtils(){}
    public static TableModel resultSetToTableModel(ResultSet rs) throws SQLException{
        ResultSetMetaData metaData=rs.getMetaData();
        int numberOfColumns=metaData.getColumnCount();
        List<String> columnNames=new ArrayList<>();

        // Get the column names
        for(int column=0;column<numberOfColumns;column++){
            columnNames.add(metaData.getColumnLabel(column+1));
        }

        // Get all rows.
        List<List<Object>> rows=new ArrayList<>();

        while(rs.next()){
            List<Object> newRow=new ArrayList<>();
            for(int i=1;i<=numberOfColumns;i++){
                newRow.add(rs.getObject(i));
            }
            rows.add(newRow);
        }

        // Convert List<List<Object>> to Object[][]
        Object[][] data=new Object[rows.size()][];
        for(int i=0;i<rows.size();i++){
            List<Object> row=rows.get(i);
            data[i]=row.toArray(Object[]::new);
        }

        return new DefaultTableModel(data, columnNames.toArray());
    }
    
    public static List<List<Object>> resultSetToNestedList(ResultSet rs,boolean includeColumnNames) throws SQLException {
        List<List<Object>> rows=new ArrayList<>();
        ResultSetMetaData metaData=rs.getMetaData();
        int numberOfColumns=metaData.getColumnCount();

        // Include column headers as first row if required
        if(includeColumnNames){
            List<Object> columnNames=new ArrayList<>();
            for(int column=0;column<numberOfColumns;column++){
                columnNames.add(metaData.getColumnLabel(column+1));
            }
            rows.add(columnNames);
        }

        // Get the data
        while(rs.next()){
            List<Object> newRow=new ArrayList<>(numberOfColumns);
            for(int i=1;i<=numberOfColumns;i++){
                newRow.add(rs.getObject(i));
            }
            rows.add(newRow);
        }
        return rows;
    }

    public static List<List<Object>> resultSetToNestedList(ResultSet rs) throws SQLException{
        // Preallocate capacity for the list of rows
        ResultSetMetaData metaData=rs.getMetaData();
        int numberOfColumns=metaData.getColumnCount();
        List<List<Object>> rows=new ArrayList<>(numberOfColumns);

        // Get the data
        while(rs.next()){
            List<Object> newRow=new ArrayList<>(numberOfColumns);
            for(int i=1;i<=numberOfColumns;i++){
                newRow.add(rs.getObject(i));
            }
            rows.add(newRow);
        }
        return rows;
    }
}
