import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Pick your difficulty: b: Beginner, i: Intermediate, e: Expert");
        Scanner scanner = new Scanner(System.in);
        char difficulty = scanner.next().charAt(0);
        scanner.close();
        Board board = new Board(difficulty);
        board.renderBoard();
    }
}