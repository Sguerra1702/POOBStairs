package persistence;

import java.io.*;
import domain.*;

public class SnakesAndLaddersIO     {
    public static void save( File file, SnakesAndLadders a) throws SnakesException {
        try{
            FileOutputStream fileOut =  new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream( fileOut );
            out.writeObject(a);
            out.close();
            fileOut.close();
        }
        catch( Exception e){
            throw new SnakesException("Ocurrio un error a     save "+file.getName());
        }
    }
    public static void saveO1( File file, SnakesAndLadders a) throws SnakesException {
        if ( file.getName().endsWith(".dat")){
            save(file,a);
        }
        else{
            throw new SnakesException(SnakesException.EXTENSION_ARCHIVO_NO_VALIDO);
        }
    }

    public static SnakesAndLadders abrir( File file ) throws SnakesException {
        try{
            ObjectInputStream in = new ObjectInputStream( new FileInputStream(file));
            SnakesAndLadders a = (SnakesAndLadders)in.readObject();
            in.close();
            return a;}
        catch( Exception e){
            System.out.println(e);
            throw new SnakesException("Ocurrio un error al Abrir "+file.getName());
        }
    }

    public static SnakesAndLadders abrirO1( File file ) throws SnakesException {
        if ( file.getName().endsWith(".dat")){
            return abrir( file );
        }
        else{
            throw new SnakesException(SnakesException.EXTENSION_ARCHIVO_NO_VALIDO);
        }
    }

    public static void exportar ( File f,SnakesAndLadders a ) throws SnakesException{
        try{
            PrintWriter out = new PrintWriter( new FileOutputStream( f) );
            out.close();
        }
        catch( Exception e){
            System.out.println(e);
            throw new SnakesException("Ocurrio un error al intentar exportar el archivo "+ f.getName());
        }
    }



}
