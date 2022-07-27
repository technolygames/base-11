package clases.tickets;
//clases
import clases.logger;
//java
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.print.Doc;
import javax.print.SimpleDoc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintException;
import javax.print.PrintServiceLookup;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de imprimir y dar forma al ticket.
 * 
 * @author unknown
 */
public class ticket{
    static ArrayList<String> cabezaLineas=new ArrayList<String>();
    static ArrayList<String> subcabezaLineas=new ArrayList<String>();
    static ArrayList<String> items=new ArrayList<String>();
    static ArrayList<String> totales=new ArrayList<String>();
    static ArrayList<String> lineasPie=new ArrayList<String>();
    
    /**
     * Inicializa la instancia para imprimir el ticket por medio de la ticketera.
     * 
     * @param impresora Ticketera a la que se imprimirá¡ el documento.
     * @param gaveta Abrirá la gaveta.
     */
    public ticket(String impresora,boolean gaveta){
        String cadena="";
        try{
            FileWriter imp=new FileWriter(impresora);
            char[] caracter=new char[]{0x1B,'R',18};
            char[] cortarPapel=new char[]{0x1B,'m'};
            
            imp.write(caracter);
            for(int cabecera=0; cabecera<cabezaLineas.size(); cabecera++){
                cadena+=cabezaLineas.get(cabecera);
                imp.write(cadena);
                setFormato(imp,27);
            }
            for(int subcabecera=0;subcabecera<subcabezaLineas.size();subcabecera++){
                cadena+=subcabezaLineas.get(subcabecera);
                imp.write(cadena);
            }
            for(int ITEM=0;ITEM<items.size();ITEM++){
                cadena+=items.get(ITEM);
                imp.write(cadena);
            }
            for(int total=0;total<totales.size();total++){
                cadena+=totales.get(total);
                imp.write(cadena);
            }
            for(int pie=0;pie<lineasPie.size();pie++){
                cadena+=lineasPie.get(pie);
                imp.write(cadena);
            }
            imp.write(cortarPapel);
            
            DocFlavor flavor=DocFlavor.BYTE_ARRAY.AUTOSENSE;
            PrintService service=PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob pj=service.createPrintJob();
            byte[] bytes=cadena.getBytes();
            Doc doc=new SimpleDoc(bytes,flavor,null);
            
            pj.print(doc,null);
            
            abrirGaveta(imp,gaveta);
            
            cabezaLineas.clear();
            subcabezaLineas.clear();
            items.clear();
            totales.clear();
            lineasPie.clear();
            
            imp.flush();
            imp.close();
        }catch(PrintException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error PE_T1",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error PE_T1: "+x.getMessage()+"\nOcurrió en la clase '"+ticket.class.getName()+"', en el método 'imprimirDocumento()'");
            new logger(Level.SEVERE).exceptionLogger(ticket.class.getName(),"imprimirDocumento-PE_T1",x.fillInStackTrace());
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 1IO_H1",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO_H1: "+e.getMessage()+"\nOcurrió en la clase '"+ticket.class.getName()+"', en el método 'imprimirDocumento()'");
            new logger(Level.SEVERE).exceptionLogger(ticket.class.getName(),"imprimirDocumento-1IO_H1",e.fillInStackTrace());
        }catch(IllegalStateException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 15",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 15: "+n.getMessage()+".\nOcurrió en la clase '"+ticket.class.getName()+"', en el método 'imprimirDocumento()'");
            new logger(Level.SEVERE).exceptionLogger(ticket.class.getName(),"imprimirDocumento-15",n.fillInStackTrace());
        }finally{
            cabezaLineas.removeAll(cabezaLineas);
            subcabezaLineas.removeAll(subcabezaLineas);
            items.removeAll(items);
            totales.removeAll(totales);
            lineasPie.removeAll(lineasPie);
        }
    }
    
    public static void addCabecera(String linea){
        cabezaLineas.add(linea);
    }
    
    public static void addSubcabecera(String linea){
        subcabezaLineas.add(linea);
    }
    
    public static void addItem(String cantidad,String item,String precio){
        order2 newItem=new order2(' ');
        items.add(newItem.generarItem(cantidad,item,precio));
    }
    
    public static void addTotal(String nombre,String precio){
        order1 newTotal=new order1(' ');
        totales.add(newTotal.generarTotal(nombre,precio));
    }
    
    public static void addPieLinea(String linea){
        lineasPie.add(linea);
    }
    
    public static String dibujarLinea(int valor){
        String raya="";
        for(int x=0;x<valor;x++){
            raya+="-";
        }
        return raya;
    }
    
    public static void setFormato(FileWriter fw,int formato){
        try{
            char[] ESC_CUT_PAPER=new char[]{0x1B,'!',(char)formato};
            fw.write(ESC_CUT_PAPER);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error IOE_T2",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error IOE_T2: "+e.getMessage()+"\nOcurrió en la clase '"+ticket.class.getName()+"', en el método 'setFormato()'");
            new logger(Level.SEVERE).exceptionLogger(ticket.class.getName(),"setFormato-IOE_T2",e.fillInStackTrace());
        }
    }
    
    public static String darEspacio(){
        return "\n";
    }
    
    public static void abrirGaveta(FileWriter fw,boolean flag){
        try{
            if(flag){
                char abrir[]={(char)27,(char)112,(char)0,(char)10,(char)100};
                fw.write(abrir);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error IOE_T3",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error IOE_T3: "+e.getMessage()+"\nOcurrió en la clase '"+ticket.class.getName()+"', en el método 'abrirGaveta()'");
            new logger(Level.SEVERE).exceptionLogger(ticket.class.getName(),"abrirGaveta-IOE_T3",e.fillInStackTrace());
        }
    }
}