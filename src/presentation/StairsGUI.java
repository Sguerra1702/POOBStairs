package presentation;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class StairsGUI extends JFrame {

    private static StairsGUI stairsInterface;
    private JPanel mainPanel, gameModeSelectionPanel, gamePanel, midPanel;

    private JMenu archivo, settings;

    private JMenuBar menuBar;

    Image image;

    private JMenuItem load, save, start, quit, tamano, colorselect;


    private Board board;

    private MainMenu menuPrincipal;

    private GameSelect menuGameSelect;

    private Color color;
    private J12 j12Maquina, j12Jugadores;
    private int nJugadores;
    public StairsGUI(){
        prepareElements();
        prepareActions();
    }

    public static StairsGUI getGUI(){
        if (stairsInterface == null){
            stairsInterface = new StairsGUI();
        }
        return stairsInterface;
    }
    public void prepareElements(){
        setTitle("Escaleras y Serpientes");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);
        menuPrincipal = new MainMenu();
        menuPrincipal.setOpaque(false);
        try {
            j12Maquina = new J12(0);
        }
        catch(StairsException e){
            e.printStackTrace();
        }
        try {
            j12Jugadores = new J12();
        }
        catch(StairsException e){
            e.printStackTrace();
        }
        prepareElementsMenu();
    }

    public void prepareActions(){
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                salida();
            }
        });
        prepareActionsMenu();
        prepareActionsConfiguration();

    }

    public void prepareElementsMenu(){
        menuBar = new JMenuBar();
        archivo = new JMenu("Archivo");
        menuBar.add(archivo);
        settings = new JMenu("Configuración");
        menuBar.add(settings);
        start = new JMenuItem("Nuevo");
        save = new JMenuItem("Salvar");
        load = new JMenuItem("Abrir");
        quit = new JMenuItem("Salir");
        tamano = new JMenuItem("Tamaño");
        colorselect = new JMenuItem("Color");
        archivo.add(start);
        archivo.add(load);
        archivo.add(save);
        archivo.add(quit);
        settings.add(tamano);
        settings.add(colorselect);
        setJMenuBar(menuBar);
        this.add(menuPrincipal);


    }
    public void prepareElementsPlayerConfig1P(){
        this.remove(menuPrincipal);
        add(j12Maquina);
        validate();
        repaint();
    }

    public void prepareElementsPlayerConfig2P(){
        this.remove(menuPrincipal);
        add(j12Jugadores);
        validate();
        repaint();
    }

    public void prepareElementsGameSelect1P(Color color1, String name1, int turnoJ1){
        menuGameSelect = new GameSelect(color, name1, turnoJ1);
        this.remove(j12Maquina);
        add(menuGameSelect);
        validate();
        repaint();
    }

    public void prepareElementsGameSelect2P(Color color1, Color color2, String name1, String name2, int turnoJ1, int turnoJ2){
        menuGameSelect = new GameSelect(color1, color2, name1, name2, turnoJ1, turnoJ2);
        this.remove(j12Jugadores);
        add(menuGameSelect);
        validate();
        repaint();
    }

    public void prepareElementsBoardNormal1P(Color color1, String name1, int turnoJ1){
        board = new Board(nJugadores, color1, name1, turnoJ1);
        remove(menuGameSelect);
        add(board);
        validate();
        repaint();
    }

    public void prepareElementsBoardNormal2P(Color color1, Color color2, String name1, String name2, int turnoJ1, int turnoJ2){
        board = new Board(nJugadores, color1, color2, name1, name2, turnoJ1, turnoJ2);
        remove(menuGameSelect);
        add(board);
        validate();
        repaint();
    }

    public void prepareActionsMenu() {
        menuPrincipal.prepareActionsMenu();
    }


    /**
     * Metodo que prepara las acciones de la configuracion
     */
    private void prepareActionsConfiguration(){

    }

    private void salida() {
        if (JOptionPane.showConfirmDialog(rootPane, "Seguro que quiere salir", "Salir del sistema", JOptionPane.YES_NO_OPTION
        ) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }else {
            setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        }
    }
    public static void main(String[] Args){
        stairsInterface = new StairsGUI();
        stairsInterface.setVisible(true);
    }
}
