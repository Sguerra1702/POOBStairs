package presentation;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.Timer;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;

import domain.*;

public class Board extends JPanel{

    private JButton salir, backMainMenu, shuffleDice;
    private JPanel board;
    private JPanel midPanel,titlePanel, dicePanel;
    private static final int SIZE = 10;
    private Color colorFondo = Color.white;

    private JPanel[][] casillas;

    private ImageIcon[] imageIcons = new ImageIcon[6];
    private JLabel  textfield, imageLabel;
    private SnakesAndLadders escalerasSerpientes;
    private HashMap<Color, Ficha> fichas;
    /**
     * Constructor de la clase Board
     */

    public Board(int nSerpientes, int nEscaleras, boolean hasEspeciales, int porcCasilla, int porcModif, HashMap<String, Color> jugadorColor){
        board = new JPanel();
        fichas = new HashMap<>();
        casillas = new JPanel[SIZE][SIZE];
        escalerasSerpientes = new SnakesAndLadders(nSerpientes, nEscaleras, hasEspeciales, porcCasilla, porcModif, jugadorColor);
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
        midPanel.setLayout(new BorderLayout());
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
        midPanel.add(stats, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(salir);
        buttonPanel.add(backMainMenu);
        midPanel.add(buttonPanel, BorderLayout.SOUTH);
        dicePanel = new JPanel();
        dicePanel.setLayout(new BorderLayout(2, 1));
        imageLabel = new JLabel();
        imageLabel.setSize(512, 512);
        shuffleDice = new JButton("Lanzar Dado");
        String folderPath = new File("C:/Users/Santi/IdeaProjects/POOBStairs/src/resources").getAbsolutePath();

        // Construir la ruta de cada imagen concatenando la ruta de la carpeta con el nombre del archivo de imagen
        String[] imagePaths = {
                folderPath + "/1.png",
                folderPath + "/2.png",
                folderPath + "/3.png",
                folderPath + "/4.png",
                folderPath + "/5.png",
                folderPath + "/6.png"
        };
        // Cargar cada imagen en un ImageIcon y almacenarlos en el arreglo de ImageIcon
        for (int i = 0; i < 6; i++) {
            imageIcons[i] = new ImageIcon(imagePaths[i]);
        }imageLabel.setIcon(imageIcons[0]);
        dicePanel.add(imageLabel, BorderLayout.CENTER);
        dicePanel.add(shuffleDice, BorderLayout.SOUTH);
        midPanel.add(dicePanel, BorderLayout.CENTER);
        add(midPanel, BorderLayout.EAST);
        for (String key : escalerasSerpientes.getJugadores().keySet()) {
            Color tempColor = escalerasSerpientes.getJugadores().get(key).getColorficha();
            int i = escalerasSerpientes.getJugadores().get(key).getFichaJug().getPosX();
            int j = escalerasSerpientes.getJugadores().get(key).getFichaJug().getPosY();
            fichas.put(tempColor, new Ficha(escalerasSerpientes.getJugadores().get(key).getColorficha(), i, j));
            casillas[i][j].add(fichas.get(tempColor));
        }
    }

    public void refresh(){
        this.setBackground(colorFondo);
        textfield.setBackground(colorFondo);
        titlePanel.setBackground(colorFondo);
        midPanel.setBackground(colorFondo);
        board.setBackground(colorFondo);

    }

    public void prepareActionsBoard(){
        salir.addActionListener(e -> salida());
        backMainMenu.addActionListener(e -> regresarAlMenu());

        shuffleDice.addActionListener(e -> {
            int diceShuffled = escalerasSerpientes.shuffleDice();
            System.out.println(diceShuffled);
            // Cambiar la imagen del JLabel
            imageLabel.setIcon(imageIcons[diceShuffled -1]);
            int[] posToMove = escalerasSerpientes.move(diceShuffled);
            Timer timer = new Timer(1000, e1 -> {
                for (int i = fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()).getPosX(); i >= posToMove[0]; i--) { // Recorre las filas de la matriz de abajo hacia arriba
                    if (i % 2 != casillas.length % 2) { // Si la fila es par, imprime los elementos de izquierda a derecha
                        for (int j = fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()).getPosY(); j <casillas[0].length ; j++) {
                            if (moveYAxis(posToMove, i, j)) break;
                        }
                    } else { // Si la fila es impar, imprime los elementos de derecha a izquierda
                        for (int j = fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()).getPosX(); j >= 0; j--) {
                            if (moveYAxis(posToMove, i, j)) break;
                        }
                    }
                }
                ((Timer) e1.getSource()).stop();
            });
            timer.start();
        });

    }

    private boolean moveYAxis(int[] posToMove, int i, int j) {
        if(j == posToMove[1]){
            return true;
        }
        else{
            casillas[i][j].remove(fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()));
            casillas[i][j].repaint();
            casillas[i][j+1].add(fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()));
            casillas[i][j+1].repaint();
        }
        return false;
    }

    public void setColorFondo(Color color){

        this.colorFondo = color;
    }
    public void move(int value){


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

