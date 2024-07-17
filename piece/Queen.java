/**
 * Represents a Queen piece in a chess game
 * Subclass of the Piece class
 */
package piece;
public class Queen extends Piece {
    private String color;
    private char firstChar;

    /**
     * Initializes a Queen object with the desired color and position
     * @param color The color of the Queen ("white" or "black")
     */
    public Queen(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Outputs a list of possible moves that the Queen can make
     */
    @Override
    public boolean possibleMove() {
        return false;
    }

    @Override
    public String toString() {
        return firstChar + "Q";
    }
}
