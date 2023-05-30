package domain;

import java.awt.*;
import java.util.ArrayList;

public class Casilla {
    private boolean hasSnake, hasLadder;
    private ArrayList<Ficha> fichasEnCasilla;
    private int numero, posX, posY, casillaEndItem;

    public Casilla(int n){
        this.numero = n;
        fichasEnCasilla = new ArrayList<>();

    }

    public void assignPositions(int x, int y){
        this.posX = x;
        this.posY = y;
    }

    public boolean hasSnake() {
        return hasSnake;
    }

    public boolean hasLadder() {
        return hasLadder;
    }

    public void setItem(boolean hasASnake, int casillaFin) {

        this.hasSnake = hasASnake;
        if(hasASnake){
            setHasLadder(false);
        }
        else{
            setHasLadder(true);
        }
        this.casillaEndItem = casillaFin;
    }

    public ArrayList<Ficha> getFichasEnCasilla() {
        return fichasEnCasilla;
    }
    public Ficha getFichaEnTurno(Color color){
        Ficha toReturn = null;
         for(Ficha ficha: fichasEnCasilla){
             if(ficha.getColor().equals(color)){
                 toReturn = ficha;
             }
         }
        return toReturn;
    }

    public void addFicha(Ficha toAdd){
        fichasEnCasilla.add(toAdd);
    }
    public void setHasLadder(boolean hasALadder) {
        this.hasLadder = hasALadder;
    }
    public void removeFicha(Ficha toRemove){
        fichasEnCasilla.remove(toRemove);
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
    public int getCasillaEndItem() {
        return casillaEndItem;
    }

    public int[] getposArray(){
        return new int[]{posX, posY};
    }

}
