package clases.tickets;

import java.util.Arrays;

public class order2{
    char[] temp=new char[]{' '};
    
    public order2(char delimit){
        temp=new char[]{delimit};
    }
    
    public String getItemCantidad(String orderItem){
        String[] delimitado=orderItem.split(""+Arrays.toString(temp));
        return delimitado[0];
    }
    
    public String getItemNombre(String orderItem){
        String[] delimitado=orderItem.split(""+Arrays.toString(temp));
        return delimitado[1];
    }
    
    public String getItemPrecio(String orderItem){
        String[] delimitado=orderItem.split(""+Arrays.toString(temp));
        return delimitado[2];
    }
    
    public String generarItem(String cantidad,String nombre,String precio){
        return cantidad+temp[0]+nombre+temp[0]+precio;
    }
}