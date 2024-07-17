/**
 * Represents a Bishop piece in a chess game
 * Subclass of the Piece class
 */
package piece;
public class Bishop extends Piece {
    private String color;
    private char firstChar;

    /**
     * Initializes a Bishop object with the desired color and position
     * @param color The color of the Bishop ("white" or "black")
     */
    public Bishop(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Outputs a list of possible moves that the Bishop can make
     */
    @Override
    public boolean possibleMove() {
        return false;
    }

    @Override
    public String toString() {
        return " " + firstChar + "B";
    }
}