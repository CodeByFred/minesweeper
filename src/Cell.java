public class Cell {
    private final String EMPTY = "\u25A1";
    private boolean isMine;
    private boolean isRevealed;
    private int adjacentMines;

    Cell() {
        this.isMine = false;
        this.isRevealed = false;
        this.adjacentMines = 0;
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMine(boolean mine) {
        this.isMine = mine;
    }

    public boolean isRevealed() {
        return isRevealed;
    }

    public void reveal() {
        this.isRevealed = true;
    }

    public int getAdjacentMines() {
        return adjacentMines;
    }

    public void setAdjacentMines(int count) {
        this.adjacentMines = count;
    }

    @Override
    public String toString() {
        if(!isRevealed) {
            return EMPTY;
        } else {
            return isMine ? "*" : String.valueOf(adjacentMines);
        }
    }
}
