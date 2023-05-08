package domain;

import java.util.concurrent.ThreadLocalRandom;

public class Dado {
    private int value;

    public Dado(){
        value = diceShuffle();
    }

    public int diceShuffle(){
        int number = ThreadLocalRandom.current().nextInt(1, 6);
        return number;
    }
}
