import java.util.Objects;

public class Coordinate {
    private final int row;
    private final int col;

    // constructor when using player entered value
    public Coordinate(char columnCharacter, int rowInput) {
        this.col = Character.toUpperCase(columnCharacter) - 'A';
        this.row = rowInput - 1;
    }

    // constructor overload when passing coordinates that match board coordinates
    public Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    // needed for cascade reveal when comparing coordinates
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Coordinate that)) return false;
        return row == that.row && col == that.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "(" + (char) (col + 'A') + ", " + (row + 1) + ")";
    }
}
