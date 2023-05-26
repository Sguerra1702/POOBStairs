package domain;

import java.util.Random;

public class Saltarina extends Casilla{
    private int saltos;
    public  Saltarina( int n) {
        super(n);
        this.saltos = new Random().nextInt(3, 10);
    }

    public int getSaltos() {
        return saltos;
    }
}
