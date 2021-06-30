package clases;

import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;

public class hilos extends Thread{
    
    private final InputStream is;
    
    String texto;
    byte[] buffer;
    int leido;

    public hilos(InputStream is){
        this.is = is;
    }

    @Override
    public void run(){
        try{
            buffer=new byte[1048];
            while((leido=is.read(buffer))>0){
                leido=is.read(buffer,0,leido);
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error IOE_H1",JOptionPane.WARNING_MESSAGE);
        }
    }
}