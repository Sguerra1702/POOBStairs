package domain;

import java.util.HashMap;
import java.util.Random;

public class Tablero {
    private static final int SIZE = 10;
    private Casilla[][] board;

    private HashMap<Integer, Casilla> casillas;
    private HashMap<String, Item> items;
    private double casillasEsp, modifval;


    public Tablero(int nSerpientes, int nEscaleras, boolean hasEspeciales, int porcCasilla, int porcModif){
        board = new Casilla[SIZE][SIZE];

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
            int top = rand.nextInt(30, 95);
            int bottom = rand.nextInt(2, top -10);
            while(bottom <= 1){
                bottom = rand.nextInt(2, top -10);
            }
            Normal item = new Normal(top, bottom, isSnake);
            items.put(top + ", " + bottom, item);
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
        for(int i = 0; i< cantEspeciales; i++){
            int toDelete = rand.nextInt(1, 100);
            casillas.remove(toDelete);
            casillas.put(toDelete, new Saltarina(rand.nextInt(1, 15)));
        }
        for (int i = board.length - 1; i >= 0; i--) { // Recorre las filas de la matriz de abajo hacia arriba
            if (i % 2 != board.length % 2) { // Si la fila es par, imprime los elementos de izquierda a derecha
                for (int j = 0; j < board[0].length; j++) {
                    board[i][j] = casillas.get(n);
                    casillas.get(n).assignPositions(i, j);
                    n +=1;
                }
            } else { // Si la fila es impar, imprime los elementos de derecha a izquierda
                for (int j = board[0].length - 1; j >= 0; j--) {
                    board[i][j] = casillas.get(n);
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
        int casillaFin = jugador.getFichaJug().getCasilla()+ value;
        int[] posMat = {casillas.get(casillaFin).getPosX() , casillas.get(casillaFin).getPosY()};
        jugador.getFichaJug().setPosX(posMat[0]);
        jugador.getFichaJug().setPosY(posMat[1]);
        return posMat;
    }

    public static int getSIZE() {
        return SIZE;
    }
}
