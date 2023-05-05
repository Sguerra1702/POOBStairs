package domain;

import java.util.ArrayList;

public class Tablero {
    private final int SIZE = 10;
    private Casilla[][] board;
    private ArrayList<Serpiente> serpientes;
    private ArrayList<Escalera> escaleras;


    public Tablero(int nSerpientes, int nEscaleras){
        board = new Casilla[SIZE][SIZE];

    }


}
