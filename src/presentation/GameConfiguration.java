package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class GameConfiguration extends JPanel {
    private JLabel escalerasText, serpientesText, transformText, casillaText, modifText, tableroText, title;
    private JTextField escaleraInput, serpienteInput;
    private JRadioButton transformSelectYes, transformSelectNo;
    private JComboBox<String> casillaSelect, modifSelect, tableroSelect;
    private JPanel configPanel;
    private JButton returnMenu, confirm;
    private HashMap<String, Color> jugadores;
    int nSerpientes, nEscaleras, porcCasilla, porcModif;
    boolean hasEspeciales;
    public GameConfiguration(HashMap<String, Color> jugadores){
        this.jugadores = jugadores;
        prepareElements();
        prepareActions();
    }

    public void prepareElements(){
        setLayout(new BorderLayout());
        title = new JLabel("Configuración de Partida");
        title.setFont(new Font("Helvetica",Font.BOLD,65));
        title.setHorizontalAlignment(JLabel.CENTER);
        add(title, BorderLayout.NORTH);
        configPanel = new JPanel();
        configPanel.setSize(new Dimension(300, 300));
        configPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        escalerasText = new JLabel("Número de escaleras");
        c.insets = new Insets(0,100,0,0);
        c.weighty = 4;
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 0;
        configPanel.add(escalerasText, c);
        escaleraInput = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 40;
        c.gridx = 5;
        c.gridy = 0;
        configPanel.add(escaleraInput, c);
        serpientesText = new JLabel("Número de serpientes");
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 0;
        c.gridy = 2;
        configPanel.add(serpientesText, c);
        serpienteInput = new JTextField();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 40;
        c.gridx = 5;
        c.gridy = 2;
        configPanel.add(serpienteInput, c);
        transformText = new JLabel("Escaleras y serpientes cambiables");
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 0;
        c.gridy = 4;
        configPanel.add(transformText, c);
        transformSelectYes = new JRadioButton("Sí");
        transformSelectNo = new JRadioButton("No");
        ButtonGroup group = new ButtonGroup();
        group.add(transformSelectYes);
        group.add(transformSelectNo);
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 5;
        c.gridy = 4;
        configPanel.add(transformSelectYes, c);
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 8;
        c.gridy = 4;
        configPanel.add(transformSelectNo, c);
        casillaText = new JLabel("Porcentaje de casillas especiales");
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 0;
        c.gridy = 6;
        configPanel.add(casillaText, c);
        casillaSelect = new JComboBox<String>();
        casillaSelect.addItem("");
        casillaSelect.addItem("10");
        casillaSelect.addItem("20");
        casillaSelect.addItem("50");
        casillaSelect.addItem("40");
        casillaSelect.addItem("60");
        casillaSelect.addItem("70");
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 5;
        c.gridy = 6;
        configPanel.add(casillaSelect, c);
        modifText = new JLabel("Porcentaje de modificación de valores");
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 0;
        c.gridy = 8;
        configPanel.add(modifText, c);
        modifSelect = new JComboBox<String>();
        modifSelect.addItem("");
        modifSelect.addItem("10");
        modifSelect.addItem("20");
        modifSelect.addItem("50");
        modifSelect.addItem("40");
        modifSelect.addItem("60");
        modifSelect.addItem("70");
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 5;
        c.gridy = 8;
        configPanel.add(modifSelect, c);
        tableroText = new JLabel("Tamaño del tablero");
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 0;
        c.gridy = 10;
        configPanel.add(tableroText, c);
        tableroSelect = new JComboBox<String>();
        tableroSelect.addItem("");
        tableroSelect.addItem("Normal");
        tableroSelect.addItem("Grande");
        tableroSelect.addItem("Extragrande");
        c.fill = GridBagConstraints.NONE;
        c.weighty = 40;
        c.gridx = 5;
        c.gridy = 10;
        configPanel.add(tableroSelect, c);
        returnMenu = new JButton("Volver al inicio");
        confirm = new JButton("Iniciar Juego");
        add(configPanel);
        JPanel botones = new JPanel();
        botones.setLayout(new GridLayout(1,2, 10,10));
        botones.add(returnMenu);
        botones.add(confirm);
        add(botones, BorderLayout.SOUTH);
    }

    public void prepareActions(){
        confirm.addActionListener(e -> startGame());
        returnMenu.addActionListener(e -> SnakesGUI.getGUI().returnToMenu());
    }

    public void startGame(){
        nSerpientes = Integer.parseInt(serpienteInput.getText());
        nEscaleras = Integer.parseInt(escaleraInput.getText());
        if(transformSelectYes.isSelected()){
            hasEspeciales = true;
        }
        else{
            hasEspeciales = false;
        }
        porcCasilla = Integer.parseInt(String.valueOf(casillaSelect.getSelectedItem()));
        porcModif = Integer.parseInt(String.valueOf(modifSelect.getSelectedItem()));
        SnakesGUI.getGUI().prepareElementsBoard(nSerpientes, nEscaleras, hasEspeciales, porcCasilla, porcModif, jugadores);
    }
}
