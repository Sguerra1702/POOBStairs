package domain;

import java.util.ArrayList;

public class Tablero {
    private final int size = 10;
    private int[][] board;
    private ArrayList<Serpiente> serpientes;
    private ArrayList<Escalera> escaleras;


    public Tablero(){
        board = new int[size][size];
    }
}
