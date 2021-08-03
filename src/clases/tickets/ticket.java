package clases.tickets;

import clases.tickets.order2;
import clases.tickets.order1;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ticket{
    static ArrayList<String> cabezaLineas=new ArrayList<String>();
    static ArrayList<String> subCabezaLineas=new ArrayList<String>();
    static ArrayList<String> items=new ArrayList<String>();
    static ArrayList<String> totales=new ArrayList<String>();
    static ArrayList<String> lineasPie=new ArrayList<String>();
    
    public static void addCabecera(String linea){
        cabezaLineas.add(linea);
    }
    
    public static void addSubcabecera(String linea){
        subCabezaLineas.add(linea);
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
            for(int subcabecera=0;subcabecera<subCabezaLineas.size();subcabecera++){
                imp.write(subCabezaLineas.get(subcabecera));
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
            }
            
            imp.close();
            
            cabezaLineas.remove(cabezaLineas);
            subCabezaLineas.remove(subCabezaLineas);
            items.remove(items);
            totales.remove(totales);
            lineasPie.remove(lineasPie);
        }catch(IOException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error IOE_T1.1",JOptionPane.WARNING_MESSAGE);
            
            cabezaLineas.removeAll(cabezaLineas);
            subCabezaLineas.removeAll(subCabezaLineas);
            items.removeAll(items);
            totales.removeAll(totales);
            lineasPie.removeAll(lineasPie);
        }
    }
}