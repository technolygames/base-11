package main;
//clases
import clases.Dirs;
import venprimarias.Start;
//java
import java.awt.EventQueue;

public class Main{
    public static void main(String[] args){
        String[] dirs={
            "data/config",
            "data/database/Jasper",
            "data/database/MySQL",
            "data/libs",
            "data/logs/exceptions",
            "data/logs/static",
            "data/media/forms",
            "data/media/forms/copy",
            "data/media/icon",
            "data/media/icon/copy",
            "data/media/secondary"
        };
        
        for (String dir:dirs){
            new Dirs().makeDir(dir);
        }
        
        EventQueue.invokeLater(()->
            new Start().setVisible(true)
        );
    }
}