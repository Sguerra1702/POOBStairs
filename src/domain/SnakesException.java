package domain;

public class SnakesException extends Exception{
    public static final String NULL_INFORMATION = "Dejaste uno o varios campos sin escoger, por favor completa la información";
    public static final String NO_MISMO_NOMBRE_O_COLOR = "Ya existe un jugador con el mismo nombre o color, verifica la información y utiliza uno distinto";
    public static final String DEMASIADOS_ITEMS = "Ingresaste un número muy grande de escaleras o serpientes, digita un valor menor a 10";
    private static final String NO_OPCION = "Opción en construcción. Archivo ";
    public static final String SE_ENCUENTRA_EN_CONSTRUCCION ="Opcion  en construccion";
    public static final String EXTENSION_ARCHIVO_NO_VALIDO ="La extension del archivo no es  .dat";
    public static final String EXTENSION_ARCHIVO_NO_ES_TXT ="La extension del archivo no es  .txt";
    public static final String TIPO_NO_EXISTENTE_Item = null;
    public SnakesException(String message){
        super(message);
    }
}
