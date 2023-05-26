package presentation;

import javax.swing.*;
import java.awt.*;

public class Ficha extends JButton {
    private int posX, posY;
    public Ficha(Color colorFicha, int x, int y){
        super();
        this.posX = x;
        this.posY = y;
        setSize(new Dimension(50, 50));
        setBackground(colorFicha);
        setEnabled(false);
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }


}
