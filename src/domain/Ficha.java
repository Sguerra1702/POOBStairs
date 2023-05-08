package domain;

import java.awt.*;

public class Ficha {
    private Color color;
    private int casilla, posX, posY;

    public Ficha(Color color){
        casilla = 1;
        this.color = color;
        this.posX = Tablero.getSIZE()-1;
        this.posY = 0;
    }

    public void setCasilla(int newCasilla) {
        this.casilla = newCasilla;
    }

    public int getCasilla() {
        return casilla;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
}
