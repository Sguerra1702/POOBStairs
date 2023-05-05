package domain;

public class SnakesException extends Exception{
    public static final String NO_ELIGIO_NADA = "Usted no ha elegido ninguna opción, intente de nuevo";
    public static final String NULL_INFORMATION = "Dejaste uno o varios campos sin escoger, por favor completa la información";
    public static final String NO_PLAYERS = "Numero inválido de jugadores, intenta de nuevo";
    public static final String NO_MISMO_NOMBRE_O_COLOR = "Ya existe un jugador con el mismo nombre o color, verifica la información y utiliza uno distinto";
    public SnakesException(String message){
        super(message);
    }
}
