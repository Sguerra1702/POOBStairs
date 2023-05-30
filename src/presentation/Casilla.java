package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Casilla extends JPanel {
    private ArrayList<Ficha> fichasEnCasilla;
    private JLabel texto;

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

    public void setText(int n){
        texto = new JLabel(Integer.toString(n), SwingConstants.LEFT);
        // Establecer la alineación del texto a la esquina superior izquierda
        texto.setVerticalAlignment(SwingConstants.TOP);
        texto.setHorizontalAlignment(SwingConstants.LEFT);
        add(texto);
    }
    public ArrayList<Ficha> getFichasEnCasilla() {
        return fichasEnCasilla;
    }

    public void setColorTexto(Color color){
        texto.setForeground(color);
    }
}
