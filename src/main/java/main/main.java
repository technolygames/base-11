package main;
//clases
import clases.datos;
import clases.makeDirs;
import venPrimarias.start;

public class main{
    protected static String userdir=datos.userdir;
    public static void main(String[] args){
        new start().setVisible(true);
        new makeDirs().makeDir(userdir+"/data/config");
        new makeDirs().makeDir(userdir+"/data/database/Jasper");
        new makeDirs().makeDir(userdir+"/data/database/MySQL");
        new makeDirs().makeDir(userdir+"/data/libs");
        new makeDirs().makeDir(userdir+"/data/logs/static");
        new makeDirs().makeDir(userdir+"/data/logs/exceptions");
        new makeDirs().makeDir(userdir+"/data/media/forms");
        new makeDirs().makeDir(userdir+"/data/media/forms/copy");
        new makeDirs().makeDir(userdir+"/data/media/icon");
        new makeDirs().makeDir(userdir+"/data/media/icon/copy");
        new makeDirs().makeDir(userdir+"/data/media/secondary");
    }
}