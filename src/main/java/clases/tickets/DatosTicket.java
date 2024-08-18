package clases.tickets;
//clases
import clases.Dirs;
import clases.InternalLogger;
//java
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import javax.swing.JTable;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
//extension larga
import java.util.logging.Level;
import java.nio.charset.StandardCharsets;

/**
 * Clase encargada de imprimir el ticket de compra.
 * Se encarga de imprimir el ticket con los objetos comprados, cantidad y total.
 * 
 * @author unknown
 */
public class DatosTicket{
    protected String total0;
    protected String precio0;
    protected String cantidad0;
    protected String item0;
    
    protected String userdir=Dirs.USERDIR;
    
    /**
     * Método encargado de imprimir el Ticket con método de pago con efectivo.
     * 
     * @param tabla que obtendrá los datos para el Ticket.
     * @param empleado que atendió en la venta.
     * @param total Precio total de los productos.
     * @param pago Método de pago utilizado al comprar.
     * @param cambio devuelto al comprador.
     * @param flag para abrir o dejar cerrada la gaveta de efectivo.
     */
    public void imprimirTicket(JTable tabla,String empleado,int total,String pago,int cambio,boolean flag){
        try(FileReader fr=new FileReader("data/config/config.properties",StandardCharsets.UTF_8)){
            Date date=new Date();
            Properties p=new Properties();
            
            int random=SecureRandom.getInstanceStrong().nextInt();
            
            SimpleDateFormat fecha=new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh.mm.ss aa");
            
            Ticket ticket=new Ticket();
            
            p.load(fr);
            
            ticket.addCabecera(p.getProperty("nombre"));
            ticket.addCabecera(Ticket.LINE_BREAK);
            ticket.addCabecera("tlf: "+p.getProperty("tif")+" ---- r.u.c: "+p.getProperty("ruc"));
            ticket.addCabecera(Ticket.LINE_BREAK);
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(Ticket.LINE_BREAK);
            ticket.addSubcabecera("Ticket no.: '"+random+"'");
            ticket.addSubcabecera(Ticket.LINE_BREAK);
            ticket.addSubcabecera(fecha.format(date)+"-"+hora.format(date));
            ticket.addSubcabecera(Ticket.LINE_BREAK);
            ticket.addSubcabecera("Quien atendió: "+empleado);
            ticket.addSubcabecera(Ticket.LINE_BREAK);
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(Ticket.LINE_BREAK);
            ticket.addSubcabecera("producto----cant----p.u");
            ticket.addSubcabecera(Ticket.LINE_BREAK);
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(Ticket.LINE_BREAK);
            for(int i=0;i<tabla.getRowCount();i++){
                //cantidad de decimales
                NumberFormat nf=NumberFormat.getNumberInstance(Locale.US);
                DecimalFormat form=(DecimalFormat)nf;
                form.applyPattern("#,###.00");
                //items
                String item2=tabla.getValueAt(i,1).toString();
                String item3="";
                if(item2.length()>17){
                    item2=item2.substring(0,16)+".";
                }else{
                    int item4=17-item2.length();
                    for(int j=0;j<item4;j++){
                        item3+=" ";
                    }
                    item0+=item3;
                }
                //cantidad
                String cantidad2=tabla.getValueAt(i,3).toString();
                String cantidad3="";
                if(cantidad2.length()<4){
                    int cantidad4=17-cantidad2.length();
                    for(int j=0;j<cantidad4;j++){
                        cantidad3+=" ";
                    }
                    cantidad0=cantidad0+cantidad3;
                }
                //precio
                String precio2=tabla.getValueAt(i,4).toString();
                String precio3="";
                double precio4=Double.parseDouble(precio2);
                precio2=form.format(precio4);
                if(precio2.length()<8){
                    int precio5=17-precio2.length();
                    for(int j=0;j<precio5;j++){
                        precio3+=" ";
                    }
                    precio0=precio3+precio2;
                }
                //total
                String total2=tabla.getValueAt(i,5).toString();
                String total3="";
                total2=form.format(Double.parseDouble(total2));
                if(total2.length()<8){
                    int total4=8-total2.length();
                    for(int j=0;j<total4;j++){
                        total3=total3.concat(" ");
                    }
                    total0=total3.concat(total2);
                }
                //agrego los items al detalle
                ticket.addItem(item0,cantidad0,precio0,total0);
            }
            ticket.addTotal("Subtotal: ",String.valueOf(total));
            ticket.addTotal("IVA: ","%");
            ticket.addTotal("Total: ",String.valueOf(total));
            ticket.addTotal("Paga con: ",pago);
            
            /*if(pago.equals(Validation.paymentType(0))){
                ticket.addTotal("Cambio: ",String.valueOf(cambio));
            }*/
            
            ticket.addPieLinea(Ticket.LINE_BREAK);
            ticket.addPieLinea("Gracias por su preferencia.");
            
            ticket.imprimir(Dirs.exists(new File("data/generic/tickets/ticket-("+fecha.format(date)+"-"+hora.format(date)+").txt")),flag);
        }catch(NumberFormatException e){
            new InternalLogger().catchException(null,e,Level.SEVERE);
        }catch(FileNotFoundException x){
            new InternalLogger().catchException(null,x,Level.SEVERE);
        }catch(IOException n){
            new InternalLogger().catchException(null,n,Level.SEVERE);
        }catch(NoSuchAlgorithmException s){
            new InternalLogger().catchException(null,s,Level.SEVERE);
        }
    }
}