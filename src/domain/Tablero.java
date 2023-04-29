package domain;

import java.util.ArrayList;

public class Tablero {
    private final int size = 10;
    private Casilla[][] board;
    private ArrayList<Serpiente> serpientes;
    private ArrayList<Escalera> escaleras;


    public Tablero(){
        board = new Casilla[size][size];

    }


}
