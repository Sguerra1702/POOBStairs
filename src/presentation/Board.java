package presentation;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.*;
import javax.sound.sampled.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.io.File;
import java.util.*;
import java.net.URL;

import domain.*;

public class Board extends JPanel{

    private JButton salir, backMainMenu, shuffleDice;
    private JPanel board;
    private JPanel midPanel,titlePanel, dicePanel;
    private static final int SIZE = 10;
    private Color colorFondo = Color.white;

    private Casilla[][] casillas;

    private ImageIcon[] imageIcons = new ImageIcon[6];
    private JLabel  textfield, imageLabel, jugadorEnTurno, moves;
    private SnakesAndLadders escalerasSerpientes;
    private HashMap<Color, Ficha> fichas;
    private Timer timer;
    private JFileChooser fileChooser;
    private boolean value;
    private int porcModif;

    /**
     * Constructor de la clase Board
     */

    public Board(int nSerpientes, int nEscaleras, boolean hasEspeciales, int porcCasilla, int porcModif, HashMap<String, Color> jugadorColor){
        board = new JPanel();
        fichas = new HashMap<>();
        casillas = new Casilla[SIZE][SIZE];
        this.porcModif = porcModif/10;
        escalerasSerpientes = getSnakesAndLadders(nSerpientes, nEscaleras, hasEspeciales, porcCasilla, porcModif, jugadorColor);
        prepareElements();
    }

    private SnakesAndLadders getSnakesAndLadders(int nSerpientes, int nEscaleras, boolean hasEspeciales, int porcCasilla, int porcModif, HashMap<String, Color> jugadorColor) {
        if(escalerasSerpientes == null){
            escalerasSerpientes = new SnakesAndLadders(nSerpientes, nEscaleras, hasEspeciales, porcCasilla, jugadorColor);
        }
        return escalerasSerpientes;
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
                casillas[i][j] = new Casilla();
                casillas[i][j].setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
                casillas[i][j].setLayout(new GridLayout(3, 3, 5, 5));
                board.add(casillas[i][j]);
                casillas[i][j].setFocusable(true);
            }
        }
        for (int i = casillas.length - 1; i >= 0; i--) { // Recorre las filas de la matriz de abajo hacia arriba
            if (i % 2 != casillas.length % 2) { // Si la fila es par, imprime los elementos de izquierda a derecha
                for (int j = 0; j < casillas[0].length; j++) {
                    casillas[i][j].setText(n);
                    n +=1;
                }
            } else { // Si la fila es impar, imprime los elementos de derecha a izquierda
                for (int j = casillas[0].length - 1; j >= 0; j--) {
                    casillas[i][j].setText(n);
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
        JLabel textMovimientos = new JLabel("Valor dado");
        JLabel textFichas = new JLabel("JUGADOR TURNO");
        int movimientos = 0;
        moves = new JLabel(Integer.toString(movimientos));
        String turnoJugador = escalerasSerpientes.getJugadorEnTurno().getNombre();
        jugadorEnTurno = new JLabel(turnoJugador);
        stats.add(textMovimientos);
        stats.add(moves);
        stats.add(textFichas);
        stats.add(jugadorEnTurno);
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
        imageLabel.setIcon(imageIcons[0]);
        // Construir la ruta de cada imagen concatenando la ruta de la carpeta con el nombre del archivo de imagen

        // Cargar cada imagen en un ImageIcon y almacenarlos en el arreglo de ImageIcon
        for (int i = 0; i < 6; i++) {
            String imagePath = "/" + (i+1) + ".png";
            URL imageUrl = getClass().getResource(imagePath);
            if (imageUrl != null) {
                imageIcons[i] = new ImageIcon(imageUrl);
            }
        }


        dicePanel.add(imageLabel, BorderLayout.CENTER);
        dicePanel.add(shuffleDice, BorderLayout.SOUTH);
        midPanel.add(dicePanel, BorderLayout.CENTER);
        add(midPanel, BorderLayout.EAST);
        for (String key : escalerasSerpientes.getJugadores().keySet()) {
            Color tempColor = escalerasSerpientes.getJugadores().get(key).getColorficha();
            int i = escalerasSerpientes.getJugadores().get(key).getFichaJug().getPosX();
            int j = escalerasSerpientes.getJugadores().get(key).getFichaJug().getPosY();
            fichas.put(tempColor, new Ficha(tempColor, i, j));
            casillas[i][j].addFicha(fichas.get(tempColor));
        }
        paintItems();
        HashMap<Integer, domain.Casilla> casillaHashMap = escalerasSerpientes.getTablero().getCasillas();
        for(Integer key: casillaHashMap.keySet()){
            if (casillaHashMap.get(key) instanceof Saltarina){
                casillas[casillaHashMap.get(key).getPosX()][casillaHashMap.get(key).getPosY()].setBackground(Color.RED);
            } else if (casillaHashMap.get(key) instanceof Mortal) {
                casillas[casillaHashMap.get(key).getPosX()][casillaHashMap.get(key).getPosY()].setBackground(new Color(116, 0, 255));
            }
        }
    }

    private void paintItems() {
        Random rand = new Random();
        HashMap<String, Item> powerUps = escalerasSerpientes.getTablero().getItems();
        for (String key: powerUps.keySet()){
            Item temp = powerUps.get(key);
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            if (temp.isSnake()){
                casillas[temp.getStartCoords()[0]][temp.getStartCoords()[1]].setBackground(new Color(53,186,35));
                casillas[temp.getStartCoords()[0]][temp.getStartCoords()[1]].setColorTexto(new Color(r, g, b));
                casillas[temp.getEndCoords()[0]][temp.getEndCoords()[1]].setBackground(new Color(53,186,35));
                casillas[temp.getEndCoords()[0]][temp.getEndCoords()[1]].setColorTexto(new Color(r, g, b));
            }
            else {
                casillas[temp.getStartCoords()[0]][temp.getStartCoords()[1]].setBackground(new Color(186,76,0));
                casillas[temp.getStartCoords()[0]][temp.getStartCoords()[1]].setColorTexto(new Color(r, g, b));
                casillas[temp.getEndCoords()[0]][temp.getEndCoords()[1]].setBackground(new Color(186,76,0));
                casillas[temp.getEndCoords()[0]][temp.getEndCoords()[1]].setColorTexto(new Color(r, g, b));
            }
        }
        revalidate();
        repaint();
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
            prepareMovement();
        });

    }

    public void prepareMovement(){
        int diceShuffled = escalerasSerpientes.shuffleDice();
        if(new Random().nextInt(1, 11) <= porcModif){

            Random rand = new Random();
            int value = rand.nextInt(1,3);
            if(value == 1){
                if ((JOptionPane.showConfirmDialog(null, "Modificador obtenido! Avanzarás 1 casilla más de lo que indica el dado ¿Deseas usarlo?")) == JOptionPane.YES_OPTION){
                    diceShuffled +=1;
                }
            }
            else {
                if ((JOptionPane.showConfirmDialog(null, "Modificador obtenido! Retrocederás 1 casilla menos de lo que indica el dado ¿Deseas usarlo?")) == JOptionPane.YES_OPTION){
                    diceShuffled -=1;
                }
            }
        }
        //System.out.println(diceShuffled);
        moves.setText(Integer.toString(diceShuffled));
        // Cambiar la imagen del JLabel
        imageLabel.setIcon(imageIcons[diceShuffled -1]);
        int x = fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()).getPosX();
        int y = fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()).getPosY();
        move(x, y, diceShuffled);
    }
    public void move(int x, int y, int diceShuffled){
        casillas[x][y].removeFicha(fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()));
        int[] newPos = escalerasSerpientes.move(diceShuffled);
        if(escalerasSerpientes.gameHasWinner()){
            finishGame();
        }
        else{
            fichas.get(escalerasSerpientes.getJugadorNotEnTurno().getColorficha()).setPosX(newPos[0]);
            fichas.get(escalerasSerpientes.getJugadorNotEnTurno().getColorficha()).setPosY(newPos[1]);
            casillas[newPos[0]][newPos[1]].addFicha(fichas.get(escalerasSerpientes.getJugadorNotEnTurno().getColorficha()));
            jugadorEnTurno.setText(escalerasSerpientes.getJugadorEnTurno().getNombre());
            if(escalerasSerpientes.getJugadorNotEnTurno().getFichaJug().isThroughSnake()){
                playSound("/snake.wav");
                JOptionPane panel = new JOptionPane("Caíste en una Serpiente! D:", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = panel.createDialog(null, "Title");
                dialog.setModal(false);
                dialog.setVisible(true);
                new Timer(1000, e -> dialog.setVisible(false)).start();
            } else if (escalerasSerpientes.getJugadorNotEnTurno().getFichaJug().isThroughLadder()) {
                playSound("/ladder.wav");
                JOptionPane panel = new JOptionPane("Subiste una escalera! :D", JOptionPane.INFORMATION_MESSAGE);
                JDialog dialog = panel.createDialog(null, "Title");
                dialog.setModal(false);
                dialog.setVisible(true);
                new Timer(1000, e -> dialog.setVisible(false)).start();
            } else if (escalerasSerpientes.getJugadorNotEnTurno().getFichaJug().isThroughSpecial()) {
                if(escalerasSerpientes.getJugadorNotEnTurno().getFichaJug().getTypeSpecial().equals("Saltarina")){
                    playSound("/saltarina.wav");
                    JOptionPane panel = new JOptionPane("Casilla Saltarina!! Qué suerte!", JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = panel.createDialog(null, "Title");
                    dialog.setModal(false);
                    dialog.setVisible(true);
                    new Timer(1000, e -> dialog.setVisible(false)).start();
                } else if(escalerasSerpientes.getJugadorNotEnTurno().getFichaJug().getTypeSpecial().equals("Mortal")){
                    playSound("/mortal.wav");
                    JOptionPane panel = new JOptionPane("Oh no, caíste en una casilla mortal :(", JOptionPane.INFORMATION_MESSAGE);
                    JDialog dialog = panel.createDialog(null, "Title");
                    dialog.setModal(false);
                    dialog.setVisible(true);
                    new Timer(1000, e -> dialog.setVisible(false)).start();
                }
            }
            nextMovement();
        }

    }
    public void moveMaquina(){
        paintItems();
        int diceShuffled = escalerasSerpientes.shuffleDice();
        //System.out.println(diceShuffled);
        moves.setText(Integer.toString(diceShuffled));
        imageLabel.setIcon(imageIcons[diceShuffled -1]);
        int x = fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()).getPosX();
        int y = fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()).getPosY();
        casillas[x][y].removeFicha(fichas.get(escalerasSerpientes.getJugadorEnTurno().getColorficha()));
        int[] newPos = escalerasSerpientes.move(diceShuffled);
        if(escalerasSerpientes.gameHasWinner()){
            finishGame();
        }
        else{
            fichas.get(escalerasSerpientes.getJugadorNotEnTurno().getColorficha()).setPosX(newPos[0]);
            fichas.get(escalerasSerpientes.getJugadorNotEnTurno().getColorficha()).setPosY(newPos[1]);
            casillas[newPos[0]][newPos[1]].addFicha(fichas.get(escalerasSerpientes.getJugadorNotEnTurno().getColorficha()));
            jugadorEnTurno.setText(escalerasSerpientes.getJugadorEnTurno().getNombre());
            timer.stop();
            nextMovement();
        }

    }
    public void nextMovement(){
        if(escalerasSerpientes.getJugadorEnTurno().getNombre().equals("Máquina")){
            timer = new Timer(1000, e -> moveMaquina());
            timer.start();
        }
        else{
            JOptionPane panel = new JOptionPane(escalerasSerpientes.getJugadorEnTurno().getNombre() + ", Es tu turno.", JOptionPane.INFORMATION_MESSAGE);
            JDialog dialog = panel.createDialog(null, "Title");
            dialog.setModal(false);
            dialog.setVisible(true);
            new Timer(1000, e -> dialog.setVisible(false)).start();
        }
    }
    public void finishGame(){
        shuffleDice.setEnabled(false);
        playSound("/victory.wav");
        JOptionPane.showMessageDialog(null, escalerasSerpientes.getGanador().getNombre() + " es el ganador!!!! Gracias por jugar :D");
        String[] options ={"Nueva partida", "Salir"};
        var selection = JOptionPane.showOptionDialog(null, "Deseas Iniciar una nueva partida o finalizar el juego", "Advertencia",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if(selection == JOptionPane.YES_OPTION){
            SnakesGUI.getGUI().restartGame();
        } else if (selection == JOptionPane.NO_OPTION) {
            SnakesGUI.getGUI().finishGame();
        }
    }



    public static void playSound(String soundFilePath) {
        try {
            // Obtener el archivo de sonido
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(Board.class.getResource(soundFilePath));
            AudioFormat audioFormat = audioInputStream.getFormat();

            // Convertir el audio a un formato reproducible
            DataLine.Info info = new DataLine.Info(Clip.class, audioFormat);
            Clip clip = (Clip) AudioSystem.getLine(info);
            clip.open(audioInputStream);

            // Reproducir el sonido
            clip.start();

            // Esperar hasta que el sonido termine de reproducirse
            Thread.sleep(clip.getMicrosecondLength() / 1000);

            // Cerrar el clip de sonido
            clip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    
    public void save() throws SnakesException {
        fileChooser = new JFileChooser();
        fileChooser.setVisible(true );
        fileChooser.setFileFilter( new FileNameExtensionFilter(".dat","dat"));
        int answ = fileChooser.showSaveDialog(this);
        if(escalerasSerpientes != null){

            value=true;
        }
        if( answ == JFileChooser.APPROVE_OPTION){
            if (value){
                File file = fileChooser.getSelectedFile();
                escalerasSerpientes.save(file) ;
            }
            else{
                throw new SnakesException("Primero debe existir alguna partida creada");
            }
        }
    }
    public void abrir() throws SnakesException {
        fileChooser = new JFileChooser();
        fileChooser.setVisible(true );
        fileChooser.setFileFilter( new FileNameExtensionFilter("Extension archivo .dat","dat"));
        int answ = fileChooser.showOpenDialog(this);
        if( answ == fileChooser.APPROVE_OPTION){
            this.escalerasSerpientes = escalerasSerpientes.open(fileChooser.getSelectedFile());
            prepareElements();
        }
    }
}

