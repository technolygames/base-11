package main;
//clases
import clases.makeDirs;
import venPrimarias.start;

public class main{
    public static void main(String[] args){
        new start().setVisible(true);
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/config");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/database/Jasper");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/database/MySQL");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/libs");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/logs/static");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/logs/exceptions");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/forms");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/forms/copy");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/icon");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/icon/copy");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/data/media/secondary");
    }
}