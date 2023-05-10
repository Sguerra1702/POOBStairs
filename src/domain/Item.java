package domain;

public class Item {
    private int start, end;
    private int[] startCoords, endCoords;
    private boolean isSnake, isLadder;

    public Item(int inicio, int fin, boolean isSnake){
        this.endCoords = new int[2];
        this.isSnake = isSnake;
        if(isSnake){
            this.start = inicio;
            this.end = fin;
            this.isLadder = false;
        }
        else{
            this.start = fin;
            this.end = inicio;
            this.isLadder = true;
        }

    }

    public void assignCoords(int[] start, int[] end){
        this.startCoords = start;
        this.endCoords = end;
    }

    public int[] getStartCoords() {
        return startCoords;
    }

    public int[] getEndCoords() {
        return endCoords;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public boolean isSnake() {
        return isSnake;
    }

    public boolean isLadder() {
        return isLadder;
    }
}
