package presentation;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputAdapter;


import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import domain.*;

public class Board extends JPanel{

    private JButton salir, backMainMenu;

    private JPanel board;

    private JPanel midPanel,titlePanel;

    private int size;

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
    public Board(int nJugadores, Color color1, String name1, int turnoJ1){
        this.size = 10;
        this.color1 = color1;
        this.name1 = name1;

        casillas = new JPanel[size][size];
        //fichas = tablero.llenaTablero(name1, "Maquina");
        board = new JPanel();
        prepareElements();
    }

    public Board(int nJugadores, Color color1, Color color2, String name1, String name2, int turnoJ1, int turnoJ2){
        this.size = 10;
        this.color1 = color1;
        this.name1 = name1;
        this.color2 = color2;
        this.name2 = name2;

        for(String key: jugadores.keySet()){
            System.out.println(key);
        }

        casillas = new JPanel[size][size];
        //fichas = tablero.llenaTablero(name1, name2);
        board = new JPanel();
        prepareElements();
    }

    /**
     * Define los parámetros del tamaño del tablero, 
     */
    public void prepareElements(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        this.setLayout(new BorderLayout(size, size));
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
        textfield.setText("DAPOOS");
        textfield.setOpaque(true);
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setBounds(0,0,getWidth(),30);
        titlePanel.add(textfield);
        add(titlePanel,BorderLayout.NORTH);
        board = new JPanel();
        board.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), new TitledBorder("Board DaPOOs")));
        board.setLayout(new GridLayout(10, 10));
        for(int i=0;i<(10);i++) {
            for (int j = 0; j < (10); j++) {
                casillas[i][j] = new JPanel();
                board.add(casillas[i][j]);
                casillas[i][j].setFocusable(false);


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
        stats.setBackground(Color.LIGHT_GRAY);
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
            this.setVisible(false);
            StairsGUI.getGUI().setVisible(true);;
            refresh();
        }
    }

}

