public class Cell {
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";
    private static final String ZERO_ADJACENT_MINES = "\u25A1";
    private static final String UNPLAYED = "\u25A0";
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
        if (!isRevealed) {
            return UNPLAYED;
        }

        if (isMine) {
            return ANSI_YELLOW + "*" + ANSI_RESET;
        }

        int count = getAdjacentMines();
        return switch (count) {
            case 0 -> ZERO_ADJACENT_MINES;
            case 1 -> ANSI_BLUE + String.valueOf(count) + ANSI_RESET;
            case 2 -> ANSI_GREEN + String.valueOf(count) + ANSI_RESET;
            default -> ANSI_RED + String.valueOf(count) + ANSI_RESET;
        };
    }
}
