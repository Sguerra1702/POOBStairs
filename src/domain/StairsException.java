package domain;

public class StairsException extends Exception{
    public static final String NO_ELIGIO_NADA = "Usted no ha elegido ninguna opción, intente de nuevo";
    public static final String J1_NOMBRE_INCORRECTO = "El nombre para el jugador 1 es invalido, no puede ser vacío";
    public static final String J2_NOMBRE_INCORRECTO = "El nombre para el jugador 2 es invalido, no puede ser vacío";
    public static final String NO_MISMO_COLOR = "No pueden elegir el mismo color";
    public static final String NO_MISMO_COLOR_Y_NOMBRE = "No pueden tener el mismo nombre y color";
    public static final String NO_MISMO_NOMBRE = "No pueden tener el mismo nombre";
    public StairsException(String message){
        super(message);
    }
}
