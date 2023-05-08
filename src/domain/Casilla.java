package domain;

import java.util.ArrayList;

public class Casilla {
    private boolean hasSnake, hasLadder;
    private ArrayList<Ficha> fichasEnCasilla;
    int numero, posX, posY;

    public Casilla(int n){
        this.numero = n;
        fichasEnCasilla = new ArrayList<>();

    }

    public void assignPositions(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public boolean isHasSnake() {
        return hasSnake;
    }

    public boolean isHasLadder() {
        return hasLadder;
    }

    public void setHasSnake(boolean hasASnake) {
        this.hasSnake = hasASnake;
        if(hasASnake){
            setHasLadder(false);
        }
        else{
            setHasLadder(true);
        }

    }
    public void addFicha(Ficha toAdd){
        fichasEnCasilla.add(toAdd);
    }
    public void setHasLadder(boolean hasALadder) {
        this.hasLadder = hasALadder;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getNumero() {
        return numero;
    }
}
