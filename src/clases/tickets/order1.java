package clases.tickets;

import java.util.Arrays;

public class order1{
    char[] temp=new char[]{' '};
    
    public order1(char delimit){
        temp=new char[]{delimit};
    }
    
    public String getTotalNombre(String totalItem){
        String[] delimitado=totalItem.split(""+Arrays.toString(temp));
        return delimitado[0];
    }
    
    public String getTotalCantidad(String totalItem){
        String[] delimitado=totalItem.split(""+Arrays.toString(temp));
        return delimitado[1];
    }
    
    public String generarTotal(String Nombre,String precio){
        return Nombre+temp[0]+precio;
    }
}