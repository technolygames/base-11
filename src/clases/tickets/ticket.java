package clases.tickets;

import clases.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import javax.swing.JOptionPane;

public class ticket{
    static ArrayList<String> cabezaLineas=new ArrayList<String>();
    static ArrayList<String> subcabezaLineas=new ArrayList<String>();
    static ArrayList<String> items=new ArrayList<String>();
    static ArrayList<String> totales=new ArrayList<String>();
    static ArrayList<String> lineasPie=new ArrayList<String>();
    
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
            raya+="=";
        }
        return raya;
    }
    
    public static void setFormato(FileWriter fw,int formato){
        try{
            char[] ESC_CUT_PAPER=new char[]{0x1B,'!',(char)formato};
            fw.write(ESC_CUT_PAPER);
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error IOE_T2",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IOE_T2: "+e.getMessage()+" en 'setFormato()'",Level.WARNING);
        }
    }
    
    public static String darEspacio(){
        return "\n";
    }
    
    public String cortarHoja(){
        return (char)27+"m";
    }
    
    public static void imprimirDocumento(String impresora,boolean abrir){
        try{
            FileWriter imp = new FileWriter(impresora);
            char[] cortarPapel=new char[]{0x1B,'m'};
            char abrirGaveta[]={(char)27,(char)112,(char)0,(char)10,(char)100};
            
            for(int cabecera=0; cabecera<cabezaLineas.size(); cabecera++){
                imp.write(cabezaLineas.get(cabecera));
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
            for(int u=0;u<=10;u++){
                imp.write("\n");
            }
            
            imp.write(cortarPapel);
            
            if(abrir){
                imp.write(abrirGaveta);
                new logger().logStaticSaver("Se abrió la gaveta",Level.INFO);
            }
            
            cabezaLineas.clear();
            subcabezaLineas.clear();
            items.clear();
            totales.clear();
            lineasPie.clear();
            imp.flush();
            imp.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error IOE_T1.1",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error IOE_T1.1: "+e.getMessage()+" en 'imprimirDocumento()'",Level.WARNING);
            
            cabezaLineas.removeAll(cabezaLineas);
            subcabezaLineas.removeAll(subcabezaLineas);
            items.removeAll(items);
            totales.removeAll(totales);
            lineasPie.removeAll(lineasPie);
        }
    }
}