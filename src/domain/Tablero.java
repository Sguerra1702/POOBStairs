package domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Tablero {
    private static final int SIZE = 10;
    private Casilla[][] board;

    private HashMap<Integer, Casilla> casillas;
    private HashMap<String, Item> items;
    private double casillasEsp, modifval;
    ArrayList<Integer> itemsStart;
    ArrayList<Integer> itemsEnd;

    public Tablero(int nSerpientes, int nEscaleras, boolean hasEspeciales, int porcCasilla, int porcModif){
        board = new Casilla[SIZE][SIZE];
        itemsStart = new ArrayList<>();
        itemsEnd = new ArrayList<>();
        casillas = new HashMap<>();
        items = new HashMap<>();
        casillasEsp = porcCasilla/100;
        Random rand = new Random();
        generateTablero(casillasEsp, rand);
        if(hasEspeciales){

        }
        generateSnakesandLadders(nSerpientes, nEscaleras, rand);

    }

    public void generateSnakesandLadders(int nSerpientes, int nEscaleras, Random rand){
        assignItems(nSerpientes, rand, true);
        assignItems(nEscaleras, rand, false);
    }

    private void assignItems(int nItems, Random rand, boolean isSnake) {
        for(int i = 0; i< nItems; i++){
            int start = rand.nextInt(30, 95);
            while(itemsStart.contains(start)){
                start = rand.nextInt(1, 100);
            }
            itemsStart.add(start);
            int end = rand.nextInt(start, start + 10);
            while(itemsEnd.contains(end)){
                end = rand.nextInt(start, start + 10);
            }
            itemsEnd.add(end);
            Normal item = new Normal(start, end, isSnake);
            item.assignCoords(new int[]{casillas.get(start).getPosX(), casillas.get(start).getPosY()}, new int[]{casillas.get(end).getPosX(), casillas.get(end).getPosY()} );
            items.put(start + ", " + end, item);
        }
    }

    public void generateTablero(double casillasEsp, Random rand) {
        int cantEspeciales = (int)(SIZE*SIZE*casillasEsp);
        int n = 1;
        for(int j = 0; j< SIZE*SIZE; j++){
            if(!casillas.containsKey(j+1)){
                casillas.put(j+1, new Casilla(j+1));
            }
        }
        ArrayList<Integer> randoms = new ArrayList<>();
        for(int i = 0; i< cantEspeciales; i++){
            int toDelete = rand.nextInt(1, 100);
            while(randoms.contains(toDelete)){
                toDelete = rand.nextInt(1, 100);
            }
            randoms.add(toDelete);
            casillas.remove(toDelete);
            casillas.put(toDelete, new Saltarina(rand.nextInt(1, 15)));
        }
        for (int i = board.length - 1; i >= 0; i--) { // Recorre las filas de la matriz de abajo hacia arriba
            if (i % 2 != board.length % 2) { // Si la fila es par, imprime los elementos de izquierda a derecha
                for (int j = 0; j < board[0].length; j++) {
                    board[i][j] = casillas.get(n);
                    board[i][j].assignPositions(i, j);
                    casillas.get(n).assignPositions(i, j);
                    n +=1;
                }
            } else { // Si la fila es impar, imprime los elementos de derecha a izquierda
                for (int j = board[0].length - 1; j >= 0; j--) {
                    board[i][j] = casillas.get(n);
                    board[i][j].assignPositions(i, j);
                    casillas.get(n).assignPositions(i, j);
                    n +=1;
                }
            }
        }
        System.out.println(casillas.size());
    }

    public void putFichaAtStart(Ficha ficha){

        board[ficha.getPosX()][ficha.getPosY()].addFicha(ficha);
    }
    public int[] move(int value, Jugador jugador){
        System.out.println(jugador.getFichaJug().getPosX() + " , " + jugador.getFichaJug().getPosY());
        boolean goUp = false;
        int row = jugador.getFichaJug().getPosX();
        int col = jugador.getFichaJug().getPosY();
        for (int i = 0; i < value; i++) {
            board[row][col].removeFicha(jugador.getFichaJug());
            if (((col == SIZE -1 && row%2 != 0) || (col == 0 && row != SIZE-1 && row%2 == 0)) && !goUp) {
                row--;
                goUp = true;
            } else {
                if (row % 2 != 0) {
                    col++;
                } else {
                    col--;
                }
            }
            board[row][col].addFicha(jugador.getFichaJug());
        }
        jugador.getFichaJug().setPosX(row);
        jugador.getFichaJug().setPosY(col);
        System.out.println(jugador.getFichaJug().getPosX() + " , " + jugador.getFichaJug().getPosY());
        return new int[] { row, col };
    }

    public void casillaHasItem(int row, int col){
        if (board[row][col].hasSnake()){

        }
    }
    public void moveSnake(){

    }
    public Casilla[][] getBoard() {
        return board;
    }

    public HashMap<String, Item> getItems() {
        return items;
    }

    public static int getSIZE() {
        return SIZE;
    }
}
