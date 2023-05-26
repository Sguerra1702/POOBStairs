package domain;


import java.awt.Color;

public abstract class Jugador {
    private String nombre;
    private Color colorficha;
    private Ficha fichaJug;
    private boolean isWinner;
    private int turno;

    public Jugador(String name, Color color){
        this.nombre = name;
        this.colorficha = color;
        fichaJug = new Ficha(colorficha);
    }

    public Color getColorficha() {
        return colorficha;
    }

    public Ficha getFichaJug() {
        return fichaJug;
    }

    public int getTurno() {
        return turno;
    }

    public void hasWon(){
        isWinner = true;
    }

    public String getNombre() {
        return nombre;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }
}
