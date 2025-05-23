import java.util.Scanner;

public class Game {
    private boolean isOver = false;
    int[] move = null;

    public void runApp() {
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

        Board board = new Board(difficulty);
        initializeBoard(board);

        System.out.print("Enter a letter for column and number for row (Example - A5): ");

        while (!isOver) {
            String guess = scanner.nextLine();
            move = playerMoveValidator(board, guess);
            if (move != null) {
                checkMove(board, move);
                clearTerminal();
                board.renderBoard();
                if (board.revealedCells() == 0) {
                    System.out.println("Congratulations! You won!");
                    isOver = true;
                }
            }
            if (!isOver) {
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
    }

    private int[] playerMoveValidator(Board board, String move) {
        int c = 0;
        int r = 0;

        String playerMove = move.trim().toUpperCase();
        if (playerMove.length() < 2) {
            System.out.println("Invalid move, wise guy!");
        }

        char column = playerMove.charAt(0);
        if (column < 'A' || column >= ('A' + board.getCols())) {
            System.out.println("You can't play out of bounds!");
            return null;
        } else {
            c = column - 'A';
        }

        try {
            int row = Integer.parseInt(playerMove.substring(1));
            if (row < 1 || row > board.ROW_OFFSET + board.getRows()) {
                System.out.println("Errr... You know that's not a valid row... Right?");
                return null;
            } else {
                r = row - board.ROW_OFFSET;

            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid move, wise guy!");
        }
            return new int[]{r, c};

    }

    private void checkMove(Board b, int[] coordinate) {
        if (b.getGrid()[coordinate[0]][coordinate[1]].isMine()) {
            System.out.println("BOOM! YOU LOST!");
            b.revealAllCells();
            isOver = true;
        } else {
            b.getGrid()[coordinate[0]][coordinate[1]].reveal();
            if (b.getGrid()[coordinate[0]][coordinate[1]].getAdjacentMines() == 0) {
                b.cascadeReveal(coordinate);
            }
        }
    }

    private void endOfGameOptions(Scanner sc) {
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