package presentation;

import javax.swing.*;
import javax.swing.border.*;


import java.awt.*;
import java.util.*;

import domain.*;

public class Board extends JPanel{

    private JButton salir, backMainMenu;

    private JPanel board;

    private JPanel midPanel,titlePanel;

    private static final int SIZE = 10;

    private Color colorFondo = Color.white;

    private Color colorFicha;

    private JPanel[][] casillas;

    private Ficha[][] fichas;

    private JLabel  textfield;



    private HashMap<String, Jugador> jugadores;


    private Color color1, color2;

    private String name1, name2;

    private int turnoJ1, turnoJ2;

    private Tablero tablero;
    ArrayList<Ficha> fichasOP;
    /**
     * Constructor de la clase Board
     */

    public Board(){
        
        board = new JPanel();
        casillas = new JPanel[SIZE][SIZE];
        //fichas = tablero.llenaTablero(name1, name2);
        prepareElements();
    }

    /**
     * Define los parámetros del tamaño del tablero,
     */
    public void prepareElements(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        this.setLayout(new BorderLayout(SIZE, SIZE));
        this.setBackground(colorFondo);
        board.setBorder(BorderFactory.createMatteBorder(4,4,4,4,Color.black));
        prepareElementsBoard();
        prepareActionsBoard();
    }

    public void prepareElementsBoard(){
        textfield = new JLabel();
        textfield.setBackground(Color.white);
        textfield.setForeground(Color.black);
        textfield.setFont(new Font("Helvetica",Font.BOLD,20));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setText("Escaleras y serpientes");
        textfield.setOpaque(true);
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,getWidth(),30);
        titlePanel.add(textfield);
        add(titlePanel,BorderLayout.NORTH);
        board.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new TitledBorder("Board Escaleras y Serpientes")));
        board.setLayout(new GridLayout(10, 10));
        board.setBackground(Color.WHITE);
        int n = 1;
        for(int i=0;i<(10);i++) {
            for (int j = 0; j < (10); j++) {
                casillas[i][j] = new JPanel();
                casillas[i][j].setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
                casillas[i][j].setLayout(new GridLayout(3, 3, 5, 5));
                board.add(casillas[i][j]);
                casillas[i][j].setFocusable(true);
            }
        }
        for (int i = casillas.length - 1; i >= 0; i--) { // Recorre las filas de la matriz de abajo hacia arriba
            if (i % 2 != casillas.length % 2) { // Si la fila es par, imprime los elementos de izquierda a derecha
                for (int j = 0; j < casillas[0].length; j++) {
                    JLabel texto = new JLabel(Integer.toString(n), SwingConstants.LEFT);
                    // Establecer la alineación del texto a la esquina superior izquierda
                    texto.setVerticalAlignment(SwingConstants.TOP);
                    texto.setHorizontalAlignment(SwingConstants.LEFT);
                    casillas[i][j].add(texto);
                    n +=1;
                }
            } else { // Si la fila es impar, imprime los elementos de derecha a izquierda
                for (int j = casillas[0].length - 1; j >= 0; j--) {
                    JLabel texto = new JLabel(Integer.toString(n), SwingConstants.LEFT);
                    // Establecer la alineación del texto a la esquina superior izquierda
                    texto.setVerticalAlignment(SwingConstants.TOP);
                    texto.setHorizontalAlignment(SwingConstants.LEFT);
                    casillas[i][j].add(texto);
                    n +=1;
                }
            }
        }

        add(board);
        midPanel = new JPanel();
        midPanel.setBorder(new LineBorder(colorFondo, 3));
        midPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 4, 4));
        midPanel.setBackground(colorFondo);
        JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(2, 1, 5, 5));
        stats.setBorder(new LineBorder(Color.LIGHT_GRAY, 3));
        stats.setBackground(Color.WHITE);
        JLabel textMovimientos = new JLabel("MOVIMIENTOS");
        JLabel textFichas = new JLabel("JUGADOR TURNO");
        int movimientos = 0;
        JLabel moves = new JLabel(Integer.toString(movimientos));
        String turnoJugador = "J1";
        JLabel fichasCap = new JLabel(turnoJugador);
        stats.add(textMovimientos);
        stats.add(moves);
        stats.add(textFichas);
        stats.add(fichasCap);
        salir = new JButton("Finalizar");
        backMainMenu = new JButton("Volver al menu principal");
        midPanel.add(stats, BorderLayout.EAST);
        midPanel.add(salir);
        midPanel.add(backMainMenu);
        add(midPanel, BorderLayout.EAST);
    }

    public void refresh(){
        this.setBackground(colorFondo);
        textfield.setBackground(colorFondo);
        titlePanel.setBackground(colorFondo);
        midPanel.setBackground(colorFondo);
        board.setBackground(colorFondo);

    }



    public void move(Ficha ficha, int turno){

    }

    public void makeAMove(int posix, int posiy, int posfx, int posfy, ArrayList<Ficha> fichasAlt){

    }
    public void prepareActionsFicha(){

    }

    public JPanel getBoard(){
        if(board == null){
            board = new JPanel();
        }
        return board;
    }
    public void prepareActionsBoard(){
        salir.addActionListener(e -> salida());
        backMainMenu.addActionListener(e -> regresarAlMenu());
    }

    public void setColorFondo(Color color){

        this.colorFondo = color;
    }

    private void salida(){
        int valor = JOptionPane.showConfirmDialog(this, "Desea cerrar la aplicacion?", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void regresarAlMenu() {
        if (JOptionPane.showConfirmDialog(this.getRootPane(), "¿Desea regresar al menú? Perderá los datos de esta partida",
                "Regresar al menú", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            SnakesGUI.getGUI().removeAll();
            SnakesGUI.getGUI().prepareElements();
            SnakesGUI.getGUI().revalidate();
            SnakesGUI.getGUI().repaint();
        }
    }

}

