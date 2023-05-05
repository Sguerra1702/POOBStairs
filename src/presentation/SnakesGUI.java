package presentation;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SnakesGUI extends JFrame {

    private static SnakesGUI stairsInterface;

    private JMenu archivo, settings;

    private JMenuBar menuBar;

    Image image;

    private JMenuItem load, save, start, quit, tamano, colorselect;


    private Board board;

    private MainMenu menuPrincipal;

    private GameSelect menuGameSelect;

    private J12 j12;
    private int nJugadores;

    public SnakesGUI() {
        prepareElements();
        prepareActions();
    }

    public static SnakesGUI getGUI() {
        if (stairsInterface == null) {
            stairsInterface = new SnakesGUI();
        }
        return stairsInterface;
    }

    public void prepareElements() {
        setTitle("Escaleras y Serpientes");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setLocationRelativeTo(null);
        menuPrincipal = new MainMenu();
        prepareElementsMenu();
        menuPrincipal.setOpaque(false);
    }

    public void prepareActions() {
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                salida();
            }
        });
        prepareActionsMenu();
        prepareActionsConfiguration();

    }

    public void prepareElementsMenu() {
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

    public void prepareElementsPlayerConfig2P() throws SnakesException {
        String[] options ={"vs. CPU", "2 Jugadores", "cancelar"};
        var selection = JOptionPane.showOptionDialog(null, "Selecciona el modo de juego:", "Advertencia",
                0, 3, null, options, options[0]);
        if (selection ==0){
            this.remove(menuPrincipal);
            add(j12 = new J12(1));
            validate();
            repaint();
        }
        else if (selection == 1) {
            this.remove(menuPrincipal);
            add(j12 = new J12(2));
            validate();
            repaint();
        } else {
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        }
    }

    public void prepareElementsGameSelect() {
        menuGameSelect = new GameSelect();
        this.remove(j12);
        add(menuGameSelect);
        validate();
        repaint();
    }


    public void prepareElementsBoardNormal1P(){
        board = new Board();
        remove(menuGameSelect);
        add(board);
        Dimension panelSize = board.getBoard().getSize();
        System.out.println(panelSize.width);
        System.out.println(panelSize.height);
        validate();
        repaint();
    }

    public void prepareElementsBoardNormal2P(){
        board = new Board();
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
        stairsInterface = new SnakesGUI();
        stairsInterface.setVisible(true);
        stairsInterface.setExtendedState(stairsInterface.getExtendedState() | JFrame.MAXIMIZED_BOTH);
    }
}
