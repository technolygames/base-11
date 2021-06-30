package tickets;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class datosTicket{
    public void imTicket(int codigo_prod,String nombre_prod,int precio,int cantidad,int total){
        try{
            Date date=new Date();
            
            SimpleDateFormat fecha=new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat hora=new SimpleDateFormat("hh:mm:ss aa");
            
            ticket ticket=new ticket();
            ticket.addCabecera("SANDALS RESTAURANT");
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
                NumberFormat nf = NumberFormat.getNumberInstance(Locale.ENGLISH);
                DecimalFormat form = (DecimalFormat)nf;
                form.applyPattern("#,###.00");
                //cantidad
                String cantidad=jTable1.getValueAt(y,0).toString();
                if(cantidad.length()<4){
                    int cant=4-cantidad.length();String can="";
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
                    int c=17-item.length();String comple="";
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
                    int p=8-precio.length();String pre="";
                    for(int y1=0;y1<p;y1++){
                        pre+=" ";
                    }
                    precio=pre+precio;
                }
                //total
                String total=jTable1.getValueAt(y,3).toString();
                total=form.format(Double.parseDouble(total));
                if(total.length()<8){
                    int t=8-total.length();String tota="";
                    for(int y1=0;y1<t;y1++){
                        tota+=" ";
                    }
                    total=tota+total;
                }
                //agrego los items al detalle
                ticket.addItem(String.valueOf(cantidad),item,String.valueOf(precio));
                //ticket.AddItem("","","",ticket.DarEspacio());
            }*/
            /*ticket.addItem(ticket.dibujarLinea(40),"","","");*/
            ticket.addTotal("",ticket.darEspacio());
            /*ticket.addTotal("total",jTextField1.getText());*/
            ticket.addTotal("",ticket.darEspacio());
            /*ticket.addTotal("Igv",jTextField2.getText());*/
            ticket.addTotal("",ticket.darEspacio());
            /*ticket.addTotal("total venta",jTextField3.getText());*/
            ticket.addTotal("",ticket.darEspacio());
            /*ticket.addTotal("paga con",jTextField4.getText());*/
            ticket.addTotal("",ticket.darEspacio());
            /*ticket.addTotal("vuelto",jTextField5.getText());*/
            ticket.addPieLinea(ticket.darEspacio());
            ticket.addPieLinea("Gracias por su Preferencia");
            /*ticket.imprimirDocumento();*/
        }catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error NFE_T1",JOptionPane.WARNING_MESSAGE);
        }
    }
}