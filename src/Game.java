import java.util.Scanner;

public class Game {

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
        System.out.println("Enter letter for column and number for row: ");
        System.out.println("For example: A5");
        while (true) {
            String guess = scanner.nextLine();
        }
    }
}
