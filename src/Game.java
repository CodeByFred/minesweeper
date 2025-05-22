import java.util.Scanner;

public class Game {
    private boolean isOver = false;


    public void runApp(Scanner scanner) {
        System.out.println("Welcome to the game!");
        System.out.println("Pick your difficulty: b: Beginner, i: Intermediate, e: Expert");
        char difficulty = scanner.next().charAt(0);
        scanner.nextLine();
        Board board = new Board(difficulty);
        board.generateMines();
        board.placeMinesInCells();
        board.fillInAdjacentCount();
        board.renderBoard();
        System.out.println("Enter letter for column and number for row: ('A5' or 'D7)");
        while (!isOver) {
            String guess = scanner.nextLine();
            int[] move = playerMoveValidator(board, guess);
            checkMove(board, move);
            board.renderBoard();
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
        if (column < 'A' || column > board.COLUMN_OFFSET + board.getCols()) {
            System.out.println("You can't play out of bounds!");
        } else {
            c = column - 'A';
            System.out.println(c);
        }

        int row = Integer.parseInt(playerMove.substring(1));
        if (row < 1 || row > board.ROW_OFFSET + board.getRows()) {
            System.out.println("Errr... You know that's not a valid row... Right?");
        } else {
            r = row - board.ROW_OFFSET;
            System.out.println(r);
        }
        return new int[]{r, c};
    }

    private void checkMove(Board b, int[] coordinate) {
        System.out.println(coordinate[0] + " " + coordinate[1]);
        if (b.getGrid()[coordinate[0]][coordinate[1]].isMine()) {
            System.out.println("BOOM! YOU LOST!");
            isOver = true;
        } else {
            b.getGrid()[coordinate[0]][coordinate[1]].reveal();
        }
    }
}
