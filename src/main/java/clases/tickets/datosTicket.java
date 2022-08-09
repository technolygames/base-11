package clases.tickets;
//clases
import clases.dirs;
import clases.logger;
//java
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.swing.JTable;
import javax.swing.JOptionPane;
//extension larga
import java.util.logging.Level;

/**
 * Clase encargada de imprimir el ticket de compra.
 * Se encarga de imprimir el ticket con los objetos comprados, cantidad y total.
 * 
 * @author unknown
 */
public class datosTicket{
    //protected String total;
    protected String precio;
    protected String cantidad;
    protected String items;
    protected String userdir=dirs.userdir;
    
    /**
     * Método encargado de imprimir el ticket con método de pago con efectivo.
     * 
     * @param tabla que obtendrá los datos para el ticket.
     * @param empleado que atendió en la venta.
     * @param total0 Precio total de los productos.
     * @param pago Método de pago utilizado al comprar.
     * @param cambio devuelto al comprador.
     * @param flag para abrir o dejar cerrada la gaveta de efectivo.
     */
    public void imprimirTicket(JTable tabla,String empleado,int total0,String pago,int cambio,boolean flag){
        try{
            Date date=new Date();
            Properties p=new Properties();
            ticket ticket=new ticket(userdir+"/data/generic/tickets/ticket-("+new SimpleDateFormat("dd-MM-yyyy hh.mm.ss aa").format(new Date())+").txt",flag);
            
            SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");
            
            p.load(new FileReader(userdir+"/data/config/config.properties",StandardCharsets.UTF_8));
            
            ticket.addCabecera(p.getProperty("nombre"));
            ticket.addCabecera(ticket.darEspacio());
            ticket.addCabecera("tlf: "+p.getProperty("tif")+"----r.u.c: "+p.getProperty("ruc"));
            ticket.addCabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("Ticket no.: '"+(Math.random()*100000)+"'");
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("-"+fecha.format(date)+"-"+hora.format(date));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("Quien atendió: "+empleado);
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("producto----cant----p.u");
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            for(int i=0;i<tabla.getRowCount();i++){
                //cantidad de decimales
                NumberFormat nf=NumberFormat.getNumberInstance(Locale.US);
                DecimalFormat form=(DecimalFormat)nf;
                form.applyPattern("#,###.00");
                //items
                String item2=tabla.getValueAt(i,1).toString();
                String item3="";
                int item4=0;
                if(item2.length()>17){
                    item2=item2.substring(0,16)+".";
                }else{
                    item4=17-item2.length();
                    for(int j=0;j<item4;j++){
                        item3+=" ";
                    }
                    items+=item3;
                }
                //cantidad
                String cantidad2=tabla.getValueAt(i,3).toString();
                String cantidad3="";
                int cantidad4=0;
                if(cantidad2.length()<17){
                    cantidad4=17-cantidad2.length();
                    for(int j=0;j<cantidad4;j++){
                        cantidad3+=" ";
                    }
                    cantidad+=cantidad3;
                }
                //precio
                String precio2=tabla.getValueAt(i,4).toString();
                String precio3="";
                double precio4=Double.parseDouble(precio2);
                int precio5=0;
                precio2=form.format(precio4);
                if(precio2.length()<17){
                    precio5=17-precio2.length();
                    for(int j=0;j<precio5;j++){
                        precio3+=" ";
                    }
                    precio=precio3+precio2;
                }
                //total
                /*String total2=tabla.getValueAt(i,5).toString();
                String total3="";
                int total4=0;
                total2=form.format(Double.parseDouble(total2));
                if(total2.length()<8){
                    total4=8-total2.length();
                    for(int j=0;j<total4;j++){
                        total3+=" ";
                    }
                    total=total3+total2;
                }*/
                //agrego los items al detalle
                ticket.addItem(items,cantidad,precio);
            }
            ticket.addTotal("Subtotal: ",String.valueOf(total0));
            ticket.addTotal("IVA: ","%");
            ticket.addTotal("Total: ",String.valueOf(total0));
            ticket.addTotal("Paga con: ",pago);
            ticket.addTotal("Cambio: ",String.valueOf(cambio));
            ticket.addPieLinea(ticket.darEspacio());
            ticket.addPieLinea("Gracias por su preferencia.");
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 18",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 18: "+e.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'");
            new logger(Level.SEVERE).exceptionLogger(datosTicket.class.getName(),"imprimirTicket-18",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'");
            new logger(Level.SEVERE).exceptionLogger(datosTicket.class.getName(),"imprimirTicket-1IO",x.fillInStackTrace());
        }catch(IOException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'");
            new logger(Level.SEVERE).exceptionLogger(datosTicket.class.getName(),"imprimirTicket-2IO",n.fillInStackTrace());
        }
    }
    
    /**
     * Método encargado de imprimir el ticket con método de pago con tarjeta.
     * 
     * @param tabla que obtendrá los datos para el ticket.
     * @param empleado que atendió en la venta.
     * @param total0 Precio total de los productos.
     * @param pago Método de pago utilizado al comprar.
     * @param flag para abrir o dejar cerrada la gaveta de efectivo.
     */
    public void imprimirTicket(JTable tabla,String empleado,int total0,String pago,boolean flag){
        try{
            Date date=new Date();
            Properties p=new Properties();
            ticket ticket=new ticket(userdir+"/data/generic/tickets/ticket-("+new SimpleDateFormat("dd-MM-yyyy hh.mm.ss aa").format(new Date())+").txt",flag);
            
            SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");
            
            p.load(new FileReader(userdir+"/data/config/config.properties",StandardCharsets.UTF_8));
            
            ticket.addCabecera(p.getProperty("nombre"));
            ticket.addCabecera(ticket.darEspacio());
            ticket.addCabecera("tlf: 222222--r.u.c: 22222222222");
            ticket.addCabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("Ticket no.: '"+(Math.random()*10000)+"'");
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("-"+fecha.format(date)+"-"+hora.format(date));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("Quien atendió: "+empleado);
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("producto----cant----p.u");
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            for(int i=0;i<tabla.getRowCount();i++){
                //cantidad de decimales
                NumberFormat nf=NumberFormat.getNumberInstance(Locale.US);
                DecimalFormat form=(DecimalFormat)nf;
                form.applyPattern("#,###.00");
                //items
                String item2=tabla.getValueAt(i,1).toString();
                String item3="";
                int item4=0;
                if(item2.length()>17){
                    item2=item2.substring(0,16)+".";
                }else{
                    item4=17-item2.length();
                    for(int j=0;j<item4;j++){
                        item3+=" ";
                    }
                    items+=item3;
                }
                //cantidad
                String cantidad2=tabla.getValueAt(i,3).toString();
                String cantidad3="";
                int cantidad4=0;
                if(cantidad2.length()<17){
                    cantidad4=17-cantidad2.length();
                    for(int j=0;j<cantidad4;j++){
                        cantidad3+=" ";
                    }
                    cantidad+=cantidad3;
                }
                //precio
                String precio2=tabla.getValueAt(i,4).toString();
                String precio3="";
                double precio4=Double.parseDouble(precio2);
                int precio5=0;
                precio2=form.format(precio4);
                if(precio2.length()<17){
                    precio5=17-precio2.length();
                    for(int j=0;j<precio5;j++){
                        precio3+=" ";
                    }
                    precio=precio3+precio2;
                }
                //total
                /*String total2=tabla.getValueAt(i,5).toString();
                String total3="";
                int total4=0;
                total2=form.format(Double.parseDouble(total2));
                if(total2.length()<8){
                    total4=8-total2.length();
                    for(int j=0;j<total4;j++){
                        total3+=" ";
                    }
                    total=total3+total2;
                }*/
                //agrego los items al detalle
                ticket.addItem(items,cantidad,precio);
            }
            ticket.addTotal("Subtotal: ",String.valueOf(total0));
            ticket.addTotal("IVA: ","%");
            ticket.addTotal("Total: ",String.valueOf(total0));
            ticket.addTotal("Paga con: ",pago);
            ticket.addPieLinea(ticket.darEspacio());
            ticket.addPieLinea("Gracias por su preferencia.");
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 18",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 18: "+e.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'");
            new logger(Level.SEVERE).exceptionLogger(datosTicket.class.getName(),"imprimirTicket-18",e.fillInStackTrace());
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 1IO: "+x.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'");
            new logger(Level.SEVERE).exceptionLogger(datosTicket.class.getName(),"imprimirTicket-1IO",x.fillInStackTrace());
        }catch(IOException n){
            JOptionPane.showMessageDialog(null,"Error:\n"+n.getMessage(),"Error 2IO",JOptionPane.ERROR_MESSAGE);
            new logger(Level.SEVERE).staticLogger("Error 2IO: "+n.getMessage()+"\nOcurrió en la clase '"+datosTicket.class.getName()+"', en el método 'imprimirTicket()'");
            new logger(Level.SEVERE).exceptionLogger(datosTicket.class.getName(),"imprimirTicket-2IO",n.fillInStackTrace());
        }
    }
}