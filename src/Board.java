public class Board {

    private final int cols;
    private final int rows;
    private final int numMines;
    private Cell[][] grid;


    Board(char difficulty) {
        switch (difficulty) {
            case 'b':
                this.cols = 10;
                this.rows = 10;
                this.numMines = 10;
                this.grid = new Cell[rows][cols];
                break;
            case 'i':
                this.cols = 16;
                this.rows = 16;
                this.numMines = 40;
                this.grid = new Cell[rows][cols];
                break;
            case 'e':
                this.cols = 26;
                this.rows = 20;
                this.numMines = 99;
                this.grid = new Cell[rows][cols];
                break;
            default:
                throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
        createBoard();
    }

    private void createBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = new Cell();
            }
        }
    }

    public void renderBoard() {
        System.out.print("   ");
        for (char col = 'A'; col < ('A' + cols); col++) {
            System.out.printf("%2c ", col);
        }
        System.out.println();

        for (int row = 0; row < rows; row++) {
            System.out.printf("%2d ", row);
            for (int col = 0; col < cols; col++) {
                System.out.print(" " + grid[row][col] + " ");
            }
            System.out.println();
        }
    }
}

