import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board {
    public final int COLUMN_OFFSET = 65;
    public final int ROW_OFFSET = 1;
    private final int cols;
    private final int rows;
    private final int numMines;
    private final Cell[][] grid;
    private final Set<String> mineSet = new HashSet<>();


    public Board(String difficulty) {
        switch (difficulty) {
            case "B":
                this.cols = 10;
                this.rows = 10;
                this.numMines = 10;
                this.grid = new Cell[rows][cols];
                break;
            case "I":
                this.cols = 16;
                this.rows = 16;
                this.numMines = 40;
                this.grid = new Cell[rows][cols];
                break;
            case "E":
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

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public Cell[][] getGrid() {
        return grid;
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
        for (char col = 0; col < cols; col++) {
            System.out.printf("%2c ", (char) (col + COLUMN_OFFSET));
        }
        System.out.println();

        for (int row = 0; row < rows; row++) {
            System.out.printf("%2d ", row + ROW_OFFSET);
            for (int col = 0; col < cols; col++) {
                System.out.print(" " + grid[row][col] + " ");
            }
            System.out.println();
        }
    }

    public void generateMines() {
        Random rand = new Random();
        while (mineSet.size() < numMines) {
            int column = rand.nextInt(this.cols);
            int row = rand.nextInt(this.rows);
            mineSet.add(column + "," + row);
        }
    }

    public void placeMinesInCells() {
        for (String mine : mineSet) {
            String[] location = mine.split(",");
            int col = Integer.parseInt(location[0]);
            int row = Integer.parseInt(location[1]);
            this.grid[row][col].setMine(true);
        }
    }

    public void fillInAdjacentCount() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (!grid[row][col].isMine()) {
                    int count = 0;
                    for (int rowCheck = -1; rowCheck <= 1; rowCheck++) {
                        for (int colCheck = -1; colCheck <= 1; colCheck++) {
                            if (rowCheck == 0 && colCheck == 0) {
                                continue;
                            }
                            int adjRow = rowCheck + row;
                            int adjCol = colCheck + col;
                            if (checkBoundaries(adjRow, adjCol) && grid[adjRow][adjCol].isMine()) {
                                count++;
                            }
                        }
                    }
                    grid[row][col].setAdjacentMines(count);
                }
            }
        }
    }

    private boolean checkBoundaries(int row, int col) {
        if(row < 0 || row >= rows || col < 0 || col >= cols) {
            return false;
        } else {
            return true;
        }
    }

    public void revealAllCells() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.grid[row][col].reveal();
            }
        }
    }

    public void cascadeReveal(int[] coord) {


        int row = coord[0];
        int col = coord[1];
        if (grid[row][col].getAdjacentMines() == 0) {
            for (int rowCheck = -1; rowCheck <= 1; rowCheck++) {
                for (int colCheck = -1; colCheck <= 1; colCheck++) {
                    if (rowCheck == 0 && colCheck == 0) {
                        continue;
                    }
                    int adjRow = rowCheck + row;
                    int adjCol = colCheck + col;
                    if (checkBoundaries(adjRow, adjCol) && grid[adjRow][adjCol].getAdjacentMines() == 0) {
                        this.grid[adjRow][adjCol].reveal();
                    }
                }
            }
        }
    }

    public int revealedCells() {
        int totalNonMineCells = this.cols * this.rows - this.numMines;
        int count = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if(this.grid[row][col].isRevealed()){
                    count++;
                }
            }
        }
        return totalNonMineCells - count;
    }
}