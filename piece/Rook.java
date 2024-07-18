/**
 * Represents a Rook piece in a chess game
 * Subclass of the Piece class
 */
package piece;
public class Rook extends Piece {
    private String color;
    private char firstChar;

    /**
     * Initializes a Rook object with the desired color and position
     * @param color The color of the Rook ("white" or "black")
     */
    public Rook(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Outputs a list of possible moves that the Rook can make
     */
    @Override
    public boolean possibleMove() {
        return false;
    }

    @Override
    public String toString() {
        return firstChar + "R";
    }
}
