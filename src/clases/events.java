package clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class events extends KeyAdapter{
    static boolean w,s,up,down,escape;
    
    @Override
    public void keyPressed(KeyEvent e){
        int id=e.getKeyCode();
        
        if(id==KeyEvent.VK_TAB){
            w=true;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e){
        int id=e.getKeyCode();
        
        if(id==KeyEvent.VK_TAB){
            w=false;
        }
    }
}