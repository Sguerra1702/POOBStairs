package domain;

public class Item {
    private int start, end;

    public Item(int inicio, int fin, boolean isSnake){
        if(isSnake){
            this.start = inicio;
            this.end = fin;
        }
        else{
            this.start = fin;
            this.end = inicio;
        }

    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
