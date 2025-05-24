import java.util.Scanner;

public class Game {
    private boolean isOver = false;
    Coordinate cellSelected = null;

    public void runApp() {
        // if game is reset in endOfGameOptions we want to clear screen to get rid of old board
        clearTerminal();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to MineSweeper!");
        System.out.println("Pick your difficulty: B: Beginner, I: Intermediate, E: Expert, Q: Quit");

        String difficulty = scanner.nextLine().toUpperCase();
        if (difficulty.equals("Q")) {
            return;
        }
        if (!(difficulty.equals("B") || difficulty.equals("I") || difficulty.equals("E"))) {
            runApp();
        }

        clearTerminal();
        Board board = new Board(difficulty);
        initializeBoard(board);

        System.out.print("Enter a letter for column and number for row (Example - A5): ");

        while (!isOver) {
            String guess = scanner.nextLine();
            cellSelected = playerMoveValidator(board, guess);
            if (cellSelected != null) {
                clearTerminal();
                checkMove(board, cellSelected);
                board.renderBoard();
                if (board.revealedCells() == 0) {
                    System.out.println("Congratulations! You won!");
                    isOver = true;
                }
            }
            if (!isOver) {
                System.out.println();
                System.out.print("You've survived, enter another cell: ");
            }
        }
        endOfGameOptions(scanner);
    }

    private void initializeBoard(Board board) {
        board.generateMines();
        board.placeMinesInCells();
        board.fillInAdjacentCount();
        board.renderBoard();
    }

    private void clearTerminal() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private Coordinate playerMoveValidator(Board board, String move) {
        int row = 0;

        String playerMove = move.trim().toUpperCase();
        if (playerMove.length() < 2) {
            System.out.println("Invalid move, wise guy!");
            return null;
        }

        char column = playerMove.charAt(0);
        if (column < 'A' || column >= ('A' + board.getCols())) {
            System.out.println("You can't play out of bounds!");
            return null;
        }

        try {
            int r = Integer.parseInt(playerMove.substring(1));
            System.out.println(r);
            if (r < 1 || r > board.ROW_OFFSET + board.getRows()) {
                System.out.println("Errr... You know that's not a valid row... Right?");
                return null;
            } else row = r;
        } catch (NumberFormatException e) {
            System.out.println("Invalid move, wise guy!");
            return null;
        }

            return new Coordinate(column, row);

    }
    // 2D is array is set up so row is first value and column is second
    private void checkMove(Board b, Coordinate coord) {
        if (b.getGrid()[coord.getRow()][coord.getCol()].isMine()) {
            System.out.println("BOOM! YOU LOST!");
            System.out.println();
            b.revealAllCells();
            isOver = true;
        } else {
            b.getGrid()[coord.getRow()][coord.getCol()].reveal();
            if (b.getGrid()[coord.getRow()][coord.getCol()].getAdjacentMines() == 0) {
                b.cascadeReveal(coord);
            }
        }
    }

    private void endOfGameOptions(Scanner sc) {
        System.out.println();
        System.out.print("Would you like to play again? (Y/N):");
        String input = String.valueOf(sc.next().charAt(0)).toUpperCase();
        sc.nextLine();
        if (input.equals("Y")) {
            isOver = false;
            runApp();
        } else {
            sc.close();
        }
    }
}