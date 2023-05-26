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
    ArrayList<Integer> itemsStart, itemsEnd, casillasMortales, casillasSaltarinas;

    /**
     * Constructor del tablero
     *
     * @param nSerpientes numero de Serpientes
     * @param nEscaleras número de Escaleras
     * @param areChangeable Define si las escaleras o serpientes cambian
     * @param porcCasillas Porcentaje de casillas Especiales
     * @param porcModif Porcentaje de probabilidad de modificador de valor
     */
    public Tablero(int nSerpientes, int nEscaleras, boolean areChangeable, int porcCasillas, int porcModif){
        board = new Casilla[SIZE][SIZE];
        itemsStart = new ArrayList<>();
        itemsEnd = new ArrayList<>();
        casillasMortales = new ArrayList<>();
        casillasSaltarinas = new ArrayList<>();
        casillas = new HashMap<>();
        items = new HashMap<>();
        modifval = (double)porcModif/100;
        casillasEsp = (double)porcCasillas/100;
        Random rand = new Random();
        generateTablero(nSerpientes, nEscaleras, casillasEsp, rand);
        if(areChangeable){

        }

    }

    /**
     * Genera las escaleras y las serpientes
     *
     * @param nSerpientes Número de serpientes
     * @param nEscaleras Número de escaleras
     * @param rand Randomizador para las casillas
     */
    public void generateSnakesandLadders(int nSerpientes, int nEscaleras, Random rand){
        setItems(nSerpientes, rand, true);
        setItems(nEscaleras, rand, false);


    }

    /**
     * Asigna los items (Escaleras o serpientes)
     *
     * @param nItems Cantidad de items
     * @param rand Randomizador para las casillas
     * @param isSnake Determina si se comporta como una serpiente o una escalera
     */
    private void setItems(int nItems, Random rand, boolean isSnake) {
        for(int i = 0; i< nItems; i++){
            int start;
            int end;
            if(isSnake){
                start = rand.nextInt(20, 95);
                while(itemsStart.contains(start) || itemsEnd.contains(start)){
                    start = rand.nextInt(20, 95);
                }
                end = rand.nextInt(5, start-10);
                while((itemsStart.contains(end) || itemsEnd.contains(end)) || Math.abs(end - start) <=10 ){
                    end = rand.nextInt(5, start-10);
                }
            }
            else{
                start = rand.nextInt(5, 80);
                while(itemsStart.contains(start) || itemsEnd.contains(start)){
                    start = rand.nextInt(5, 80);
                }
                end = rand.nextInt(start+10, 95);
                while((itemsStart.contains(end) || itemsEnd.contains(end)) || Math.abs(end - start) <=10){
                    end = rand.nextInt(start +10, 95);
                }
            }
            Normal item = new Normal(start, end, isSnake);
            item.assignCoords(new int[]{casillas.get(start).getPosX(), casillas.get(start).getPosY()}, new int[]{casillas.get(end).getPosX(), casillas.get(end).getPosY()} );
            items.put(end + "," + start, item);
            itemsStart.add(start);
            itemsEnd.add(end);
        }
    }

    /**
     * Genera el tablero
     *
     */
    public void generateTablero(int nSerpientes, int nEscaleras, double casillasEsp, Random rand) {
        int n = 1;
        for(int j = 0; j< SIZE*SIZE; j++){
            if(!casillas.containsKey(j+1)){
                casillas.put(j+1, new Casilla(j+1));
            }
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
        generateSnakesandLadders(nSerpientes, nEscaleras, rand);
        generateEspeciales(casillasEsp, rand);
        assignItemsToCasillas();
    }

    private void generateEspeciales(double casillasEsp, Random rand) {
        int cantEspeciales = (int) (((SIZE*SIZE) - (itemsStart.size()+itemsEnd.size()))* casillasEsp);
        ArrayList<Integer> randoms = new ArrayList<>();
        for(int i = 0; i< cantEspeciales; i++){
            int toDelete = rand.nextInt(4, 100);
            while(randoms.contains(toDelete) || itemsStart.contains(toDelete) || itemsEnd.contains(toDelete)){
                toDelete = rand.nextInt(4, 100);
            }
            int x = casillas.get(toDelete).getPosX();
            int y = casillas.get(toDelete).getPosY();
            randoms.add(toDelete);
            casillas.remove(toDelete);
            if(i >= cantEspeciales*0.05 ){
                casillas.put(toDelete, new Saltarina(toDelete));
                casillasSaltarinas.add(toDelete);
            }
            else if(i <cantEspeciales*0.05){
                casillas.put(toDelete, new Mortal(toDelete));
                casillasMortales.add(toDelete);
            }
            board[x][y] = casillas.get(toDelete);
            board[x][y].assignPositions(x, y);
            casillas.get(toDelete).assignPositions(x, y);
        }
    }

    /**
     * Asigna los items a cada casilla
     */
    public void assignItemsToCasillas(){
        int n = 1;
        for (int i = board.length - 1; i >= 0; i--) { // Recorre las filas de la matriz de abajo hacia arriba
            if (i % 2 != board.length % 2) { // Si la fila es par, imprime los elementos de izquierda a derecha
                for (int j = 0; j < board[0].length; j++) {
                    setItem(n, i, j);
                }
            } else { // Si la fila es impar, imprime los elementos de derecha a izquierda
                for (int j = board[0].length - 1; j >= 0; j--) {
                    setItem(n, i, j);
                }
            }
            n +=1;
        }
    }

    /**
     * Define si la casilla se le asignará una serpiente o Escalera o ninguna
     *
     * @param n casilla inicial
     * @param i posición en x de la casilla
     * @param j posición en y de la casilla
     */
    private void setItem(int n, int i, int j) {
        for(String key: items.keySet()){
            if(key.split(",")[0].equals(Integer.toString(n))) {
                if(items.get(key).isSnake()){
                    board[i][j].setItem(true, Integer.parseInt(key.split(",")[1]));
                }
                else{
                    board[i][j].setItem(false, Integer.parseInt(key.split(",")[1]));
                }
            }
        }
    }

    /**
     * Coloca la ficha en la posición inicial del tablero
     * @param ficha ficha a colocar en el inicio
     */
    public void putFichaAtStart(Ficha ficha){

        board[ficha.getPosX()][ficha.getPosY()].addFicha(ficha);
    }

    /**
     * Mueve las fichas
     *
     * @param value valor del dado lanzado
     * @param jugador jugador en turno
     * @return posiciones actualizadas de la ficha
     */
    public int[] move(int value, Jugador jugador){
        if(!(jugador.getFichaJug().getPosX() ==0 && value > jugador.getFichaJug().getPosY())){
            int newCasilla = 0;
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
                newCasilla +=1;
                board[row][col].addFicha(jugador.getFichaJug());
            }
            jugador.getFichaJug().setCasilla(jugador.getFichaJug().getCasilla() + newCasilla);
            jugador.getFichaJug().setPosX(row);
            jugador.getFichaJug().setPosY(col);
            checkCasillaHasItem(row, col, jugador);
            moveIfSpecial(row, col, jugador);
        }
        else{
            
        }
        return new int[] { jugador.getFichaJug().getPosX(), jugador.getFichaJug().getPosY() };
    }

    /**
     * Revisa si la casilla tiene una escalera o serpiente
     *
     * @param row fila de la casilla
     * @param col columna de la casilla
     * @param jugador Jugador en turno
     */
    public void checkCasillaHasItem(int row, int col, Jugador jugador){
        int[] positions = null;
        boolean isSnake = false;
        for(String key: items.keySet()){
            int[] temp = items.get(key).getStartCoords();
            if(temp[0] == row && temp[1] == col){
                positions = items.get(key).getEndCoords();
                if(items.get(key).isSnake()){
                    isSnake = true;
                }
                else{
                    isSnake = false;
                }
            }
        }
        if(positions != null){
            activateItem(jugador.getFichaJug(), positions[0], positions[1], isSnake);
        }
        else{
            jugador.getFichaJug().wentThroughSnake(false);
            jugador.getFichaJug().wentThroughLadder(false);
        }
    }

    /**
     * Activa el item
     *
     * @param fichaToMove ficha a mover según el comportamiento del item
     * @param x posición del fin del item en X
     * @param y posición del fin del item en Y
     * @param isSnake define el verificador a instanciar como true
     */
    public void activateItem(Ficha fichaToMove, int x, int y, boolean isSnake){
        fichaToMove.setPosX(x);
        fichaToMove.setPosY(y);
        if(isSnake){
            fichaToMove.wentThroughSnake(true);
        }
        else{
            fichaToMove.wentThroughLadder(true);
        }
        board[x][y].addFicha(fichaToMove);
    }

    public void moveIfSpecial(int row,int col, Jugador jugador){
        if(casillasSaltarinas.contains(board[row][col].getNumero()) || board[row][col] instanceof Saltarina){
            board[row][col].removeFicha(jugador.getFichaJug());
            Saltarina temp = (Saltarina) board[row][col];
            int newCasilla = jugador.getFichaJug().getCasilla() + temp.getSaltos();
            boolean goUp = false;
            int x = jugador.getFichaJug().getPosX();
            int y = jugador.getFichaJug().getPosY();
            for (int i = 0; i < temp.getSaltos(); i++) {
                if (((y == SIZE -1 && x%2 != 0) || (y == 0 && x != SIZE-1 && x%2 == 0)) && !goUp) {
                    x--;
                    goUp = true;

                } else {
                    if (x % 2 != 0) {
                        y++;
                    } else {
                        y--;
                    }
                }
                newCasilla +=1;
            }
            jugador.getFichaJug().setCasilla(newCasilla);
            jugador.getFichaJug().setPosX(x);
            jugador.getFichaJug().setPosY(y);
            jugador.getFichaJug().wentThroughSpecial("Saltarina");
            board[x][y].addFicha(jugador.getFichaJug());
        } else if (casillasMortales.contains(board[row][col].getNumero())) {
            jugador.getFichaJug().setPosX(SIZE-1);
            jugador.getFichaJug().setPosY(0);
            jugador.getFichaJug().wentThroughSpecial("Mortal");
        }
        else {
            jugador.getFichaJug().wentThroughSpecial(null);
        }
    }
    /**
     * Retorna los items
     *
     * @return los items
     */
    public HashMap<String, Item> getItems() {
        return items;
    }

    public HashMap<Integer, Casilla> getCasillas() {
        return casillas;
    }

    public Casilla[][] getBoard() {
        return board;
    }

    public static int getSIZE() {
        return SIZE;
    }
}
