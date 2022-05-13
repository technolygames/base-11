package main;
//clases
import clases.makeDirs;
import venPrimarias.start;

public class main{
    public static void main(String[] args){
        new start().setVisible(true);
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/libs");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/logs/static");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/logs/exceptions");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/config");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/media/copy/label");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/media/copy/icon");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/media/forms");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/media/secondary");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/dataBackup");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/dataBackup/Empleados");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/dataBackup/Socios");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/dataBackup/Proveedores");
        new makeDirs().makeDir(System.getProperty("user.dir")+"/src/data/generic/tickets");
    }
}