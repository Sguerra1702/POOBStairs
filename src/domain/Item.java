package domain;

public class Item {
    private int start, end;
    private int[] startCoords, endCoords;
    private boolean isSnake, isLadder;

    public Item(int inicio, int fin, boolean isSnake){
        this.endCoords = new int[2];
        this.isSnake = isSnake;
        this.start = inicio;
        this.end = fin;
        if(isSnake){
            this.isLadder = false;
        }
        else{
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

    public void setSnake(boolean snake) {
        isSnake = snake;
        isLadder = !isSnake;
    }

    public void changeState(){
        int temp = start;
        start = end;
        end = temp;
        int[] temp1 = getStartCoords();
        startCoords = endCoords;
        endCoords = temp1;
        if(isSnake()){
           setSnake(false);
        }
    }
}
