package presentation;

import domain.*;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class J12 extends JPanel{
    /**
     * informacion
     */
    private Color color1;
    private Color color2;
    private String name1;
    private String name2;
    private int noeligionada1;
    private int turnoJ1, turnoJ2;


    /**
     * grafico
     */
    Image imagen;
    Image scaledImage;
    JLabel imageLabel = new JLabel();
    private javax.swing.JButton Siguiente;
    private javax.swing.JLabel Title;
    private javax.swing.JButton Volver;
    private javax.swing.JButton colorj1;
    private javax.swing.JLabel colorj1text;
    private javax.swing.JButton colorj2;
    private javax.swing.JLabel colorj2text;
    private javax.swing.JLabel datos;
    private javax.swing.JLabel j1;
    private javax.swing.JLabel j2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField nombrej1;
    private javax.swing.JLabel nombrej1text;
    private javax.swing.JTextField nombrej2;
    private javax.swing.JLabel nombrej2text;

    /**
     * Constructor para j1 vs maquina
     * @param x
     * @throws StairsException
     */

    public J12(int x) throws StairsException{
        jSeparator1 = new javax.swing.JSeparator();
        Title = new javax.swing.JLabel();
        datos = new javax.swing.JLabel();
        j1 = new javax.swing.JLabel();
        nombrej1 = new javax.swing.JTextField();
        nombrej1text = new javax.swing.JLabel();
        colorj1text = new javax.swing.JLabel();
        colorj1 = new javax.swing.JButton("Selección de color");
        Volver = new javax.swing.JButton();
        Siguiente = new javax.swing.JButton();

        Title.setFont(new java.awt.Font("Hotel De Paris", 0, 36)); // NOI18N
        Title.setText("Configuración de jugadores");

        datos.setText("Ingresa tus datos:");

        j1.setText("J1");

        nombrej1.setText("Ingresa tu nombre");
        nombrej1text.setText("Nombre:");
        colorj1text.setText("Color:");
        colorj1.setModel(new javax.swing.DefaultButtonModel());
        Volver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Volver.setText("Volver");
        /**
         *
         *
         *
         *  seccion listeners botones
         *
         *
         *
         *
         */

        Volver.addActionListener(e -> returnToMenu());
        Siguiente.addActionListener(e -> {
            try {
                setItem(0);
            } catch (StairsException e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        });


        /**
         *
         *
         * Fin seccion Listeners
         *
         *
         *
         *
         */
        /**
         *
         *
         * Fin seccion Listeners
         *
         *
         *
         *
         */

        Siguiente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Siguiente.setText("Siguente");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Title)
                                .addGap(189, 189, 189))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(219, 219, 219)
                                                .addComponent(Siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(8, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nombrej1text)
                                                                        .addComponent(colorj1text))
                                                                .addGap(15, 15, 15)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nombrej1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(colorj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(datos)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(j1)))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(Title)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(j1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nombrej1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nombrej1text))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(colorj1text)
                                        .addComponent(colorj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Volver)
                                        .addComponent(Siguiente))
                                .addGap(15, 15, 15))
        );
        colorj1text.setVisible(false);
        colorj1text.setVisible(false);
        imagen = loadImage("https://miro.medium.com/max/720/1*kmi3_mISigRQnwxsR4WQmw.gif");

        revalidate();
        repaint();
    }
    /**
     *
     * coloca items para j1 y j2
     *
     * @throws StairsException
     */

    private  void setItem() throws StairsException{
        color1 = seleccionColorJ1();
        color2 = seleccionColorJ2();
        name1 = nombrej1.getText();
        name2 = nombrej2.getText();
        turnoJ1 = 0;
        turnoJ2 = 1;
        if(name1 == null || name1 == ""){
            throw new StairsException(StairsException.J1_NOMBRE_INCORRECTO);
        }
        else if(name2 == null || name2 == ""){
            throw new StairsException(StairsException.J1_NOMBRE_INCORRECTO);
        }
        prepareElementsGameselect2P(color1, color2, name1, name2, turnoJ1, turnoJ2);


    }

    /**
     * Coloca Items para j1
     * @param x
     * @throws StairsException
     */

    private  void setItem(int x) throws StairsException{
        color1 = seleccionColorJ1();
        name1 = nombrej1.getText();
        turnoJ1 = 0;
        if(name1 == null || name1 == ""){
            throw new StairsException(StairsException.J1_NOMBRE_INCORRECTO);
        }
        prepareElementsGameSelect1P(color1, name1, turnoJ1);
    }

    /**
     * Constructor para jugadores humanos
     * @throws StairsException
     */

    public J12() throws StairsException{

        jSeparator1 = new javax.swing.JSeparator();
        Title = new javax.swing.JLabel();
        datos = new javax.swing.JLabel();
        j1 = new javax.swing.JLabel();
        nombrej1 = new javax.swing.JTextField();
        nombrej1text = new javax.swing.JLabel();
        colorj1text = new javax.swing.JLabel();
        colorj1 = new JButton("selección color J1");
        j2 = new javax.swing.JLabel();
        nombrej2 = new javax.swing.JTextField();
        nombrej2text = new javax.swing.JLabel();
        colorj2 = new JButton("selección color J2");
        colorj2text = new javax.swing.JLabel();
        Volver = new javax.swing.JButton();
        Siguiente = new javax.swing.JButton();

        Title.setFont(new java.awt.Font("Hotel De Paris", 0, 36)); // NOI18N
        Title.setText("Damas");

        datos.setText("Ingresa tus datos:");

        j1.setText("J1");

        nombrej1.setText("Ingresa tu nombre");

        nombrej1text.setText("Nombre:");

        colorj1text.setText("Color:");

        colorj1.setModel(new javax.swing.DefaultButtonModel());

        j2.setText("J2");

        nombrej2.setText("Ingresa tu nombre");

        nombrej2text.setText("Nombre:");

        colorj2.setModel(new javax.swing.DefaultButtonModel());

        colorj2text.setText("Color:");

        Volver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Volver.setText("Volver");

        /**
         *
         *
         *
         *  seccion listeners botones
         *
         *
         *
         *
         */
        Volver.addActionListener(e -> returnToMenu());
        Siguiente.addActionListener(e -> {
            try {
                setItem();
            } catch (StairsException e1) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(null, e1.getMessage());
            }
        });
        /**
         *
         *
         * Fin seccion Listeners
         *
         *
         *
         *
         */

        /**
         *
         *
         * Tratado de datos
         *
         *
         */

        /**
         *
         *
         * Fin tratado de datos
         *
         *
         */
        Siguiente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        Siguiente.setText("Siguente");
        imagen = loadImage("https://i.pinimg.com/originals/c4/39/e6/c439e63ea965afac8ff261a0b904898f.gif");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Title)
                                .addGap(189, 189, 189))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(Volver, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(219, 219, 219)
                                                .addComponent(Siguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addContainerGap(8, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator1)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nombrej1text)
                                                                        .addComponent(colorj1text))
                                                                .addGap(15, 15, 15)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nombrej1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(colorj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nombrej2text)
                                                                        .addComponent(colorj2text))
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(nombrej2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(colorj2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(2, 2, 2)))
                                                .addContainerGap())
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(datos)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addGap(6, 6, 6)
                                                                .addComponent(j1)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(j2)
                                                .addGap(169, 169, 169))))
        );
        colorj1text.setVisible(false);
        colorj1.setVisible(false);
        colorj2text.setVisible(false);
        colorj2.setVisible(false);
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(Title)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datos)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(j1)
                                        .addComponent(j2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(nombrej1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nombrej1text)
                                        .addComponent(nombrej2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nombrej2text))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(colorj1text)
                                        .addComponent(colorj1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(colorj2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(colorj2text))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 108, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(Volver)
                                        .addComponent(Siguiente))
                                .addGap(15, 15, 15))
        );
        //Image scaledImage = imagen.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
        //JLabel picLabel = new JLabel(new ImageIcon(scaledImage));
        //add(picLabel);
        revalidate();
        repaint();
    }
    public void returnToMenu(){
        int valor = JOptionPane.showConfirmDialog(this, "Desea volver al menú de selección de jugadores", "Advertencia",
                JOptionPane.YES_NO_OPTION);
        if (valor == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    public Color seleccionColorJ1(){
        JOptionPane.showMessageDialog(null, "Jugador 1, selecciona el color de tus fichas");
        color1 = JColorChooser.showDialog(null, "Seleccione un Color", Color.BLUE);
        return color1;
    }

    public Color seleccionColorJ2(){
        JOptionPane.showMessageDialog(null, "Jugador 2, selecciona el color de tus fichas");
        Color color2 = JColorChooser.showDialog(null, "Seleccione un Color", Color.BLUE);
        return color2;
    }

    public void prepareElementsGameSelect1P(Color color1, String name, int turnoJ1){
        StairsGUI.getGUI().prepareElementsGameSelect1P(color1, name1, turnoJ1);


    }

    public void prepareElementsGameselect2P(Color color1, Color color2, String name1, String name2, int turnoJ1, int turnoJ2){
        StairsGUI.getGUI().prepareElementsGameSelect2P(color1, color2, name1, name2, turnoJ1, turnoJ2);
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
