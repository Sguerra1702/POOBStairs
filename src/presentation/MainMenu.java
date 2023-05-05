package presentation;

import javax.swing.*;

import domain.*;

import java.awt.*;

@SuppressWarnings("FieldCanBeLocal")
public class MainMenu extends JPanel {
    private JButton newGame, loadGame, exit;

    Image image;
    private JLabel textfield;


    public MainMenu() {
        prepareElements();
    }

    public void prepareElements() {
        this.setOpaque(false);
        prepareElementsMenu();
    }

    public void prepareElementsMenu() {
        GridLayout orden = new GridLayout(4, 1, 70, 80);
        textfield = new JLabel();
        textfield.setFont(new Font("Helvetica",Font.BOLD,69));
        textfield.setText("Escaleras y serpientes");
        textfield.setHorizontalAlignment(JLabel.CENTER);
        JPanel menuBotones = new JPanel();
        menuBotones.setBackground(Color.white);
        menuBotones.setLayout(orden);
        newGame = new JButton("Nueva Partida");
        menuBotones.add(textfield);
        menuBotones.add(newGame);
        loadGame = new JButton("Cargar Partida");
        exit = new JButton("Salir");
        menuBotones.add(loadGame);
        menuBotones.add(exit);
        menuBotones.setOpaque(false);
        this.add(menuBotones);
        setVisible(true);
        image = loadImage("");
    }

    public void prepareActionsMenu() {
        newGame.addActionListener(e -> {
            try {
                prepareElementsPlayerConfig2P();
            } catch (SnakesException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Oops", JOptionPane.WARNING_MESSAGE);
            }
        });
        loadGame.addActionListener(e -> loadGame());
        exit.addActionListener(e -> confirmateClose());
    }

    public void prepareElementsPlayerConfig2P()throws SnakesException {

        SnakesGUI.getGUI().prepareElementsPlayerConfig2P();
    }

    public void loadGame(){
        JOptionPane.showMessageDialog(null, "Próximamente", "Funcion en Construción", JOptionPane.INFORMATION_MESSAGE);
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
            final Image img = Toolkit.getDefaultToolkit().createImage("fondo");
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
