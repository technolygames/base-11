package clases.tickets;

import clases.logger;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.NumberFormat;//still in use, but commented
import java.text.DecimalFormat;//still in use, but commented
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;//still in use, but commented
import java.util.Properties;
import javax.swing.JOptionPane;

import java.util.logging.Level;

/**
 * Clase encargada de imprimir el ticket de compra.
 * Se encarga de imprimir el ticket con los objetos comprados, cantidad y total.
 */
public class datosTicket{
    int total;
    String metodoPago;
    String cambio;
    
    /**
     * Método encargado de obtener los datos faltantes para imprimir el ticket
     * @param total: Precio total de los productos a comprar.
     * @param metodoPago: Tipo de pago con el que se realizará el pago de los productos.
     * @param cambio: Cambio devuelto al cliente; en caso de pagar con tarjeta, será null.
     */
    public void datosCalc(int total,String metodoPago,String cambio){
        this.total=total;
        this.metodoPago=metodoPago;
        this.cambio=cambio;
    }
    
    /**
     * Método encargado de imprimir el ticket.
     * 
     * @param codigoProducto: Código del producto comprado.
     * @param nombreProducto: Nombre del producto comprado.
     * @param precio: Precio unitario del producto.
     * @param cantidad: Cantidad total del producto.
     */
    public void imprimirTicket(int codigoProducto,String nombreProducto,int precio,int cantidad){
        try{
            Date date=new Date();
            Properties p=new Properties();
            ticket ticket=new ticket();
            
            SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");
            
            p.load(new FileInputStream("src/data/config/config.properties"));
            
            ticket.addCabecera(p.getProperty("nombre"));
            ticket.addCabecera(ticket.darEspacio());
            ticket.addCabecera("tlf: 222222--r.u.c: 22222222222");
            ticket.addCabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("Ticket Factura No:'003-000011'");
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("-"+fecha.format(date)+"-"+hora.format(date));
            ticket.addSubcabecera(ticket.darEspacio());
            /*ticket.addSubcabecera("Mesa"+jTextField7.getText()+"Mozo"+jTextField8.getText()+"Pers"+jTextField9.getText());*/
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera("cant----producto----p.u----total");
            ticket.addSubcabecera(ticket.darEspacio());
            ticket.addSubcabecera(ticket.dibujarLinea(40));
            ticket.addSubcabecera(ticket.darEspacio());
            /*for(int y=0;y<jTable1.getRowCount();y++){
                //cantidad de decimales
                NumberFormat nf=NumberFormat.getNumberInstance(Locale.ENGLISH);
                DecimalFormat form=(DecimalFormat) nf;
                form.applyPattern("#,###.00");
                //cantidad
                String cantidad=jTable1.getValueAt(y,0).toString();
                if(cantidad.length()<4){
                    int cant=4-cantidad.length();
                    String can="";
                    for(int f=0;f<cant;f++){
                        can+=" ";
                    }
                    cantidad+=can;
                }
                //items
                String item=jTable1.getValueAt(y,1).toString();
                if(item.length()>17){
                    item=item.substring(0,16)+".";
                }else{
                    int c=17-item.length();
                    String comple="";
                    for(int y1=0;y1<c;y1++){
                        comple+=" ";
                    }
                    item+=comple;
                }
                //precio
                String precio=jTable1.getValueAt(y,2).toString();
                double pre1=Double.parseDouble(precio);
                precio=form.format(pre1);
                if(precio.length()<8){
                    int p=8-precio.length();
                    String pre="";
                    for(int y1=0;y1<p;y1++){
                        pre+=" ";
                    }
                    precio=pre+precio;
                }
                //total
                String total=jTable1.getValueAt(y,3).toString();
                total=form.format(Double.parseDouble(total));
                if(total.length()<8){
                    int t=8-total.length();
                    String tota="";
                    for(int y1=0;y1<t;y1++){
                        tota+=" ";
                    }
                    total=tota+total;
                }
                //agrego los items al detalle
                ticket.addItem(String.valueOf(cantidad),item,String.valueOf(precio));
                //ticket.AddItem("","","",ticket.DarEspacio());
            }
            ticket.addItem(ticket.dibujarLinea(40),"","","");*/
            ticket.addTotal("Total: ",String.valueOf(total));
            ticket.addTotal("IGV: ",ticket.darEspacio());
            ticket.addTotal("Paga con: ",metodoPago);
            ticket.addTotal("Cambio: ",String.valueOf(cambio));
            ticket.addPieLinea(ticket.darEspacio());
            ticket.addPieLinea("Gracias por su preferencia");
            ticket.imprimirDocumento("",true);
            new logger().logStaticSaver("Se imprimió correctamente el ticket",Level.INFO);
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error NFE_T1",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error NFE_T1: "+e.getMessage()+" en 'imprimirTicket()'",Level.WARNING);
        }catch(FileNotFoundException x){
            JOptionPane.showMessageDialog(null,"Error:\n"+x.getMessage(),"Error 1IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 1IO: "+x.getMessage()+" en 'imprimirTicket()'",Level.WARNING);
        }catch(IOException ñ){
            JOptionPane.showMessageDialog(null,"Error:\n"+ñ.getMessage(),"Error 2IO",JOptionPane.WARNING_MESSAGE);
            new logger().logStaticSaver("Error 2IO: "+ñ.getMessage()+" en 'imprimirTicket()'",Level.WARNING);
        }
    }
}