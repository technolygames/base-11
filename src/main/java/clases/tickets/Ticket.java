package clases.tickets;
//clases
import clases.MediaHandler;
import clases.InternalLogger;
import java.awt.Frame;
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
//extension larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
/**
 * Clase encargada de imprimir y dar forma al ticket.
 * 
 * @author unknown
 */
public class Ticket{
    public static final String LINE_BREAK="\n";
    
    static ArrayList<String> cabezaLineas=new ArrayList<>();
    static ArrayList<String> subcabezaLineas=new ArrayList<>();
    static ArrayList<String> items=new ArrayList<>();
    static ArrayList<String> totales=new ArrayList<>();
    static ArrayList<String> lineasPie=new ArrayList<>();
    
    /**
     * Inicializa la instancia para imprimir el ticket por medio de la ticketera.
     * 
     * @param impresora a la que se imprimirá el documento.
     * @param gaveta Abrirá la gaveta.
     */
    public void imprimir(String impresora,boolean gaveta){
        try(FileWriter imp=new FileWriter(impresora,StandardCharsets.UTF_8)){
            char[] caracter=new char[]{0x1B,'R',18};
            char[] cortarPapel=new char[]{0x1B,'m'};
            
            imp.write(caracter);
            for(int cabecera=0;cabecera<cabezaLineas.size();cabecera++){
                imp.write(cabezaLineas.get(cabecera));
                setFormato(imp,27);
            }
            for(int subcabecera=0;subcabecera<subcabezaLineas.size();subcabecera++){
                imp.write(subcabezaLineas.get(subcabecera));
            }
            for(int ITEM=0;ITEM<items.size();ITEM++){
                imp.write(items.get(ITEM));
            }
            for(int total=0;total<totales.size();total++){
                imp.write(totales.get(total));
            }
            for(int pie=0;pie<lineasPie.size();pie++){
                imp.write(lineasPie.get(pie));
            }
            imp.write(cortarPapel);
            
            Doc doc=new SimpleDoc(imp,DocFlavor.INPUT_STREAM.AUTOSENSE,null);
            PrintRequestAttributeSet pras=new HashPrintRequestAttributeSet();
            PrintService service=PrintServiceLookup.lookupDefaultPrintService();
            DocPrintJob pj=service.createPrintJob();
            
            pj.print(doc,pras);
            
            abrirGaveta(imp,gaveta);
            
            remove();
        }catch(PrintException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
            remove();
        }catch(IOException x){
            new InternalLogger().catchException(null,x,Level.SEVERE);
            remove();
        }catch(IllegalStateException n){
            new InternalLogger().catchException(null,n,Level.SEVERE);
            remove();
        }catch(IllegalArgumentException s){
            new InternalLogger().catchException(null,s,Level.SEVERE);
            remove();
        }
    }
    
    public void addCabecera(String linea){
        cabezaLineas.add(linea);
    }
    
    public void addSubcabecera(String linea){
        subcabezaLineas.add(linea);
    }
    
    public void addItem(String cantidad,String item,String precio,String total){
        Order2 newItem=new Order2(' ');
        items.add(newItem.generarItem(cantidad,item,precio,total));
    }
    
    public void addTotal(String nombre,String precio){
        Order1 newTotal=new Order1(' ');
        totales.add(newTotal.generarTotal(nombre,precio));
    }
    
    public void addPieLinea(String linea){
        lineasPie.add(linea);
    }
    
    public String dibujarLinea(int valor){
        String raya="";
        for(int i=0;i<valor;i++){
            raya=raya.concat("-");
        }
        return raya;
    }
    
    public void setFormato(FileWriter fw,int formato) throws IOException{
        char[] cortarPapel=new char[]{0x1B,'!',(char)formato};
        fw.write(cortarPapel);
    }
    
    public void abrirGaveta(FileWriter fw,boolean flag) throws IOException{
        if(flag){
            char[] abrir={(char)27,(char)112,(char)0,(char)10,(char)100};
            fw.write(abrir);
        }
    }
    
    protected void remove(){
        cabezaLineas.clear();
        subcabezaLineas.clear();
        items.clear();
        totales.clear();
        lineasPie.clear();
    }
}