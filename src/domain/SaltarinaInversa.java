package domain;

import java.util.Random;

public class SaltarinaInversa extends Casilla{
    private int saltos;

    public SaltarinaInversa(int n) {
        super(n);
        this.saltos = new Random().nextInt(3, 10);
    }

    public int getSaltos() {
        return saltos;
    }
}
