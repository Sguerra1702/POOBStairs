package domain;

import persistence.SnakesAndLaddersIO;

import java.awt.*;
import java.io.File;
import java.util.HashMap;

public class SnakesAndLadders {
    private static Tablero tablero;
    private HashMap<String, Jugador> jugadores;
    private Jugador ganador;
    private Dado dado;
    private int turno;
    private boolean hasWinner;

    /**
     * Constructor de objetos tipo SnakesAndLadders
     *
     * @param nSerpientes numero de Serpientes
     * @param nEscaleras número de Escaleras
     * @param hasEspeciales Define si las escaleras o serpientes cambian
     * @param porcCasilla Porcentaje de casillas Especiales
     * @param porcModif Porcentaje de probabilidad de modificador de valor
     * @param infoJugador información de los jugadores
     */
    public SnakesAndLadders(int nSerpientes, int nEscaleras, boolean hasEspeciales, int porcCasilla, int porcModif, HashMap<String, Color> infoJugador) {
        jugadores = new HashMap<>();
        startGame(nSerpientes, nEscaleras,hasEspeciales, porcCasilla, porcModif, infoJugador);
        dado = new Dado();
        for (String key : jugadores.keySet()) {
            tablero.putFichaAtStart(jugadores.get(key).getFichaJug());
        }
        turno = 0;
    }

    /**
     * Inicia el juego
     *
     *
     * @param nSerpientes numero de Serpientes
     * @param nEscaleras número de Escaleras
     * @param areChangeable Define si las escaleras o serpientes cambian
     * @param porcCasilla Porcentaje de casillas Especiales
     * @param porcModif Porcentaje de probabilidad de modificador de valor
     * @param infoJugador información de los jugadores
     */
    public void startGame(int nSerpientes, int nEscaleras, boolean areChangeable, int porcCasilla, int porcModif, HashMap<String, Color> infoJugador) {
        int i = 0;
        for (String key : infoJugador.keySet()) {
            if (!key.equals("Máquina")) {
                Humano jugador = new Humano(key, infoJugador.get(key));
                jugadores.put(key, jugador);
            } else {
                Maquina cpu = new Maquina(key, infoJugador.get(key));
                jugadores.put(key, cpu);
            }
            jugadores.get(key).setTurno(i);
            i +=1;
        }
        System.out.println(jugadores.size());
        tablero = new Tablero(nSerpientes, nEscaleras,areChangeable, porcCasilla, porcModif);
        System.out.println(getJugadorEnTurno().getNombre());
    }

    /**
     * Mueve las fichas en el tablero
     * @param shuffleDice Valor del dado lanzado
     * @return Posiciones a actualizar en la capa de presentación
     */
    public int[] move(int shuffleDice){
        System.out.println(getJugadorEnTurno().getNombre());
        int[] newPositionsGUI;
        newPositionsGUI = tablero.move(shuffleDice, getJugadorEnTurno());
        if(checkIfWinner(getJugadorEnTurno())){
            endGame();
        }
        if(turno == 1){
            turno = 0;
        }
        else{
            turno += 1;
        }    
        return newPositionsGUI;
    }

    /**
     * Retorna el jugador en turno
     * @return Jugador en turno
     */
    public Jugador getJugadorEnTurno(){
        Jugador enTurno = null;
        for (String key : jugadores.keySet()) {
            if(jugadores.get(key).getTurno() == turno){
                enTurno = jugadores.get(key);
            }
        }
        return enTurno;
    }

    /**
     * Retorna el jugador que no es su turno
     * @return Jugador que no es su turno
     */
    public Jugador getJugadorNotEnTurno(){
        Jugador enTurno = null;
        for (String key : jugadores.keySet()) {
            if(jugadores.get(key).getTurno() != turno){
                enTurno = jugadores.get(key);
            }
        }
        return enTurno;
    }

    /**
     * Lanza el dado
     * @return El valor del dado lanzado
     */
    public int shuffleDice(){
        return dado.diceShuffle();
    }

    /**
     * Retorna el tablero
     * @return el tablero del juego
     */
    public Tablero getTablero() {
        return tablero;
    }


    /**
     * Se encarga de confirmar si hay un ganador con base en la posición de las fichas del jugador en turno
     * @param jugador Jugador en turno
     * @return true si hay ganador, false si no
     */
    public boolean checkIfWinner(Jugador jugador){
        boolean value = false;
        if (jugador.getFichaJug().getPosX() == 0 && jugador.getFichaJug().getPosY()==0){
            jugador.hasWon();
            value = true;
        }
        return value;
    }

    /**
     * Retorna los jugadores de la partida
     * @return Jugadores de la partida
     */
    public HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Finaliza el juego lógicamente
     */
    public void endGame(){
        ganador = getJugadorEnTurno();
        ganador.hasWon();
        hasWinner = true;
        tablero = null;
        jugadores = null; 
        
    }

    /**
     * Retorna el jugador que ha ganado el juego para poder finalizarlo gráficamente
     * @return El jugador ganador del juego
     */
    public Jugador getGanador() {
        return ganador;
    }

    /**
     * informa si hay un ganador del juego
     * @return true si hay un ganador, false si no
     */
    public boolean gameHasWinner(){
        return hasWinner;
    }

    public void save(File f)throws SnakesException{
        SnakesAndLaddersIO.save(f, this);
    }

    public SnakesAndLadders open(File f) throws SnakesException{
        return SnakesAndLaddersIO.abrirO1(f);
    }
}
