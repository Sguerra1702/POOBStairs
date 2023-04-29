package presentation;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import domain.*;

public class GameSelect extends JPanel {


    private Board board;

    Image image;

    private JMenu archivo, settings;

    private JMenuBar menuBar;

    private JMenuItem load, save, start, quit, tamano, colorselect;

    private JButton normal, gottagofast, exit;

    private int nJugadores;

    private Tablero tablero;

    private Color color1, color2;

    private String name1, name2;

    private int turnoJ1, turnoJ2;


    public GameSelect(){
        prepareElements1P();

    }
    public void prepareElements1P(){
        setBackground(Color.black);
        setOpaque(false);
        prepareElementsGameSelect();
        prepareActionsGameSelect1P();
        image = loadImage("https://www.gifcen.com/wp-content/uploads/2022/01/wallpaper-gif-9.gif");
    }

    public void prepareElements2P(){
        setBackground(Color.black);
        setOpaque(false);
        prepareElementsGameSelect();
        prepareActionsGameSelect2P();
        image = loadImage("https://www.gifcen.com/wp-content/uploads/2022/01/wallpaper-gif-9.gif");
    }

    public void prepareActionsGameSelect1P() {
        normal.addActionListener(e -> prepareElementsBoardNormal1P());
        exit.addActionListener(e -> confirmateClose());
    }

    public void prepareActionsGameSelect2P() {
        normal.addActionListener(e -> prepareElementsBoardNormal2P());
        exit.addActionListener(e -> confirmateClose());
    }

    public void prepareElementsGameSelect() {
        JPanel gameModeSelectionPanel = new JPanel();
        gameModeSelectionPanel.setOpaque(false);
        gameModeSelectionPanel.setLayout(new GridLayout(4,1,200,80));
        JPanel vacio = new JPanel();
        vacio.setVisible(false);
        normal = new JButton("Partida Normal");
        gottagofast = new JButton("Partida QuickTime");
        exit = new JButton("Salir");
        gameModeSelectionPanel.add(vacio);
        gameModeSelectionPanel.add(normal);
        gameModeSelectionPanel.add(gottagofast);
        gameModeSelectionPanel.add(exit);
        add(gameModeSelectionPanel);
        setVisible(true);

    }

    public void prepareElementsBoardNormal1P(){
        StairsGUI.getGUI().prepareElementsBoardNormal1P();
    }

    public void prepareElementsBoardNormal2P(){
        StairsGUI.getGUI().prepareElementsBoardNormal2P();
    }

    private void confirmateClose() {
        int valor = JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicacion?", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }
    private Image loadImage(String url){
        try{
            getToolkit();
            final Image img = Toolkit.getDefaultToolkit().createImage(new URL(url));
            getToolkit();
            Toolkit.getDefaultToolkit().prepareImage(img, -1, -1, null);
            return img;
        }
        catch(Exception e){
            return null;
        }

    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

}
