package clases;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.swing.JOptionPane;

public class thread extends Thread{
    protected InputStream is;
    protected OutputStream os;
    
    public thread(InputStream is,OutputStream os){
        this.os=os;
        this.is=is;
    }

    @Override
    public void run(){
        int leido;
        byte[] buffer;
        try{
            buffer=new byte[1024];
            while((leido=is.read(buffer))>0){
                os.write(buffer,0,leido);
            }
            
            os.close();
            os.flush();
            is.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,"Error:\n"+e.getMessage(),"Error 26H",JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
}