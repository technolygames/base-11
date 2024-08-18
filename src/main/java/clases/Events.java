package clases;
//java
import java.sql.Date;
import java.time.Period;
import java.time.LocalDate;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.JPopupMenu;
//extension larga
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

/**
 * Clase encargada de manejar eventos de ventana.
 * 
 * @author erick
 */
public class Events{
    protected Events(){}
    
    /**
     * Método usado para mostrar un menú popup.
     * 
     * @param pm menú popup.
     * @param me evento del ratón.
     */
    public static void showPopup(JPopupMenu pm,MouseEvent me){
        if(me.isPopupTrigger()){
            pm.show(me.getComponent(),me.getX(),me.getY());
        }
    }
    
    /**
     * Método usado para crear una instancia de DefaultTableModel con bloqueo para editar celdas. 
     * Este método se usa con frecuencia en algunas ventanas.
     * 
     * @return instancia de la clase DefaultTableModel.
     */
    public static DefaultTableModel tableModel(){
        DefaultTableModel dtm=new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column){
                //all cells false
                return false;
            }
        };
        
        for(int i=0;i<dtm.getRowCount();i++){
            for(int j=0;j<dtm.getColumnCount();j++){
                dtm.isCellEditable(i,j);
            }
        }
        
        return dtm;
    }
    
    /**
     * Método usado para limpiar la selección de una celda en una tabla.
     * 
     * @param table en la que se limpiará la selección de la celda.
     * @param me evento del ratón.
     */
    public static void clearTableSelection(JTable table,MouseEvent me){
        int row=table.rowAtPoint(me.getPoint());
        if(row>=0&&row<table.getRowCount()){
            table.setRowSelectionInterval(row,row);
        }else{
            table.clearSelection();
        }
    }
    
    /**
     * Método encargado de establecer los parámetros necesarios para usar las funciones de una tabla, como ordenar los datos de las filas.
     * 
     * @param table a la que se le darán los parámetros.
     * @param rws RowSorter que usará la tabla.
     * @param dtm modelo que usará la tabla.
     */
    public static void table(JTable table,RowSorter<TableModel> rws,DefaultTableModel dtm){
        table.setRowSorter(rws);
        table.getRowSorter().toggleSortOrder(0);
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(dtm);
    }
    
    /**
     * Método encargado de establecer los parámetros necesarios para usar las funciones de una tabla, como:
     * <br>
     * <ul>
     * <li>Ordenar los datos de las filas.</li>
     * <li>Al realizar búsqueda de datos específicos, estos los agrega a la tabla destino.</li>
     * </ul>
     * 
     * @param table a la que se le darán los parámetros y los valores de la búsqueda realizada.
     * @param rws RowSorter que usará la tabla.
     * @param tm modelo con el que se mostrarán los datos que se obtuvieron de la búsqueda de registros específicos.
     * @param dtm modelo que usará la tabla.
     */
    public static void table(JTable table,RowSorter<TableModel> rws,TableModel tm,DefaultTableModel dtm){
        table.setRowSorter(rws);
        table.getRowSorter().toggleSortOrder(0);
        table.getTableHeader().setReorderingAllowed(false);
        table.setModel(tm);
        table.setModel(dtm);
    }
    
    /**
     * Crea una cadena con los años y el texto concorde a la experiencia del empleado.<br>
     * Por ejemplo:
     * <ul>
     * <li>0 años = Sin Experiencia.</li>
     * <li>1 año = 1 año.</li>
     * <li>+2 años = 2 años.</li>
     * </ul>
     * 
     * @param exp la experiencia de empleado.
     * @return regresa una cadena con los años de experiencia.
     */
    public static String exp(int exp){
        //este método lo usan 3 ventanas
        //para no estar copiando y pegando, mejor lo hago un método externo y que lo puedan utilizar varias clases
        switch(exp){
            case 0:return "Sin experiencia";
            case 1:return String.valueOf(exp).concat(" año");
            default:return String.valueOf(exp).concat(" años");
        }
    }
    
    /**
     * Calcula la edad del empleado. Si no es igual, regresa la edad acorde a su fecha de nacimiento.<br>
     * Si es igual, regresa la edad que se ingresó en la variable del método.
     * 
     * @param date fecha de nacimiento del empleado.
     * @param age edad a calcular y validar si es igual o no.
     * @return la edad calculada según la fecha de nacimiento.
     */
    public static int calcAge(long date,int age){
        int edad=Period.between(LocalDate.parse(new Date(date).toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd")),LocalDate.now()).getYears();
        String edad2=String.valueOf(edad);
        
        if(edad2.equals(String.valueOf(age))){
            return age;
        }
        return edad;
    }
    
    /**
     * Calcula los años de servicio de un empleado trabajando en el negocio. 
     * Si es igual, regresa el valor de la variable "exp". Si no es igual, regresa la fecha calculada.
     * 
     * @param yos los años de experiencia.
     * @param exp la cantidad de años registrada.
     * 
     * @return los años de servicio según la fecha dada.
     */
    public static int calcYoS(long yos,int exp){
        int years=Period.between(LocalDate.parse(new Date(yos).toString(),DateTimeFormatter.ofPattern("yyyy-MM-dd")),LocalDate.now()).getYears();
        String ads=String.valueOf(years);
        
        if(ads.equals(String.valueOf(exp))){
            return exp;
        }
        return years;
    }
}
