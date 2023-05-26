package presentation;

import javax.swing.*;
import java.util.ArrayList;

public class Casilla extends JPanel {
    private ArrayList<Ficha> fichasEnCasilla;


    public Casilla(){
        fichasEnCasilla = new ArrayList<>();
    }
    public void addFicha(Ficha toAdd){
        fichasEnCasilla.add(toAdd);
        add(toAdd);
        revalidate();
        repaint();


    }
    public void removeFicha(Ficha toRemove){
        fichasEnCasilla.remove(toRemove);
        remove(toRemove);
        revalidate();
        repaint();
    }

    public void updateGrpahics(){
        removeAll();
        revalidate();
        repaint();
        for (Ficha f: fichasEnCasilla){
            addFicha(f);
        }
    }

    public ArrayList<Ficha> getFichasEnCasilla() {
        return fichasEnCasilla;
    }
}
