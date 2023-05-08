package domain;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SnakesAndLadders {
    private static Tablero tablero;
    private HashMap<String, Jugador> jugadores;

    private Dado dado;
    private int turno;
    private boolean hasWinner;

    public SnakesAndLadders(int nSerpientes, int nEscaleras, boolean hasEspeciales, int porcCasilla, int porcModif, HashMap<String, Color> infoJugador) {
        jugadores = new HashMap<>();
        startGame(nSerpientes, nEscaleras,hasEspeciales, porcCasilla, porcModif, infoJugador);
        dado = new Dado();
        for (String key : jugadores.keySet()) {
            tablero.putFichaAtStart(jugadores.get(key).getFichaJug());
        }
    }

    public void startGame(int nSerpientes, int nEscaleras, boolean hasEspeciales, int porcCasilla, int porcModif, HashMap<String, Color> infoJugador) {
        turno = 0;
        for (String key : infoJugador.keySet()) {
            if (key.equals("Maquina")) {
                Maquina cpu = new Maquina(key, infoJugador.get(key), turno);
                jugadores.put(key, cpu);
            } else {
                Humano jugador = new Humano(key, infoJugador.get(key), turno);
                jugadores.put(key, jugador);
            }
            turno = 1;
            //System.out.println(key);
        }
        turno = 0;
        tablero = new Tablero(nSerpientes, nEscaleras,hasEspeciales, porcCasilla, porcModif);
    }
    public int[] move(int shuffleDice){
        int[] positions = tablero.move(shuffleDice, getJugadorEnTurno());
        if(checkIfWinner(getJugadorEnTurno())){
            endGame();
        }

        if(turno == 0){
            turno = 1;
        }
        else{
            turno = 1;
        }
        return positions;
    }

    public Jugador getJugadorEnTurno(){
        Jugador enTurno = null;
        for (String key : jugadores.keySet()) {
            if(jugadores.get(key).getTurno() == turno){
                enTurno = jugadores.get(key);
            }
        }
        return enTurno;
    }
    public int shuffleDice(){
        int value = dado.diceShuffle();
        return value;
    }

    public static Tablero getTablero() {
        return tablero;
    }

    public boolean checkIfWinner(Jugador jugador){
        if (jugador.getFichaJug().getCasilla() == 100){
            jugador.hasWon();
        }
        return true;
    }

    public HashMap<String, Jugador> getJugadores() {
        return jugadores;
    }

    public void endGame(){

    }

    public Dado getDado() {
        return dado;
    }
}
