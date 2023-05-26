package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Random;

public class J12 extends JPanel{
    /**
     * informacion
     */
    private Color color;
    private String name;
    private int nJugadores, cont;
    private HashMap<String, Color> jugadores;

    /**
     * grafico
     */
    Image imagen;


    private JTextField nombreJugador;
    private JLabel nombreJText, colorJugador, title;
    private JPanel entradaInfo, botones;
    private JButton confirm, reset, returnToMenu, exit, colorPlayer;



    /**
     * Constructor para j1 vs maquina
     * @param x
     */

    public J12(int x) {
        nJugadores = x;
        cont = 0;
        jugadores = new HashMap<>();
        prepareElements();

    }

    public void prepareElements(){
        setOpaque(false);
        prepareElementsPlayerSelect();
        prepareActionsPlayerSelect();

    }

    public void prepareElementsPlayerSelect(){
        JOptionPane.showMessageDialog(null, "Jugador " + Integer.toString(cont+1) + ", configura tu partida");
        setLayout(new BorderLayout());
        title = new JLabel("Configuración de Jugadores");
        title.setFont(new Font("Helvetica",Font.BOLD,69));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);
        entradaInfo = new JPanel();
        entradaInfo.setSize(new Dimension(300, 300));
        entradaInfo.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        nombreJText = new JLabel("Introduce tu nombre");
        c. insets = new Insets(0,100,0,0);
        c.weighty = 4;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        entradaInfo.add(nombreJText, c);
        nombreJugador = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 40;
        c.gridx = 5;
        c.gridy = 0;
        entradaInfo.add(nombreJugador, c);
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 0;
        c.gridy = 4;
        colorJugador = new JLabel("Selecciona el color de tu ficha");
        entradaInfo.add(colorJugador, c);
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 5;
        c.gridy = 4;
        colorPlayer = new JButton("Selecciona tu color");
        entradaInfo.add(colorPlayer, c);
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 0;
        c.gridy = 8;
        confirm = new JButton("Confirmar");
        entradaInfo.add(confirm, c);
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 5;
        c.gridy = 8;
        reset = new JButton("Reestablecer");
        entradaInfo.add(reset, c);
        returnToMenu = new JButton("Volver al menu principal");
        exit = new JButton("Salida");
        add(entradaInfo, BorderLayout.CENTER);
        botones = new JPanel();
        botones.setLayout(new GridLayout(1,2, 10,10));
        botones.add(returnToMenu);
        botones.add(exit);
        add(botones, BorderLayout.SOUTH);
    }


    /**
     * Coloca Items para j1
     * @param
     * @throws SnakesException
     */

    private  void setItem() throws SnakesException {
        if (nombreJugador.getText().equals("") || color == null){
            throw new SnakesException(SnakesException.NULL_INFORMATION);
        }
        if (jugadores.containsKey(nombreJugador.getText()) || jugadores.containsValue(color)){
            throw new SnakesException(SnakesException.NO_MISMO_NOMBRE_O_COLOR);
        }
        jugadores.put(nombreJugador.getText(), color);
        cont +=1;
        if(nJugadores == 1){
            Random rand = new Random();
            jugadores.put("Máquina", new Color(rand.nextInt(256),rand.nextInt(256),rand.nextInt(256)));
        }
        reset();
        validate();
    }

    public void validate(){
        System.out.println(jugadores.size());
        if(jugadores.size() == 2) {
            prepareElementsGameConfig(jugadores);
        }
        else{
            JOptionPane.showMessageDialog(null, "Jugador " + (cont + 1) + ", configura tu partida");
        }
    }

    public void prepareActionsPlayerSelect(){
        colorPlayer.addActionListener(e -> color = JColorChooser.showDialog(null, "Escoge el color de tu ficha", null));
        confirm.addActionListener(e -> {
            try {
                setItem();
            } catch (SnakesException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Oops", JOptionPane.WARNING_MESSAGE);
                reset();
            }

        });
        reset.addActionListener(e -> reset());
        returnToMenu.addActionListener(e -> returnToMenu());
        exit.addActionListener(e -> salida());

    }
    public void returnToMenu(){
        int valor = JOptionPane.showConfirmDialog(this, "Deseas volver al menú de selección de jugadores", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            SnakesGUI.getGUI().returnToMenu();
        }
    }

    public void reset(){
        nombreJugador.setText("");
        color = null;
    }

    public void salida(){
        int valor = JOptionPane.showConfirmDialog(this, "¿Deseas salir al escritorio?", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            SnakesGUI.getGUI().getContentPane().removeAll();
            SnakesGUI.getGUI().prepareElementsMenu();
        }
    }
    public void prepareElementsGameConfig(HashMap<String, Color> jugadores){
        SnakesGUI.getGUI().prepareElementsGameConfig(jugadores);
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
        g.drawImage(imagen, 0, 0, this);
    }
}
