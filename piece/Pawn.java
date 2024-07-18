/**
 * Represents a Pawn piece in a chess game
 * Subclass of the Piece class
 */
package piece;
public class Pawn extends Piece {
    private String color;
    private char firstChar;

    /**
     * Initializes a Pawn object with the desired color and position
     * @param color The color of the Pawn ("white" or "black")
     */
    public Pawn(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Outputs a list of possible moves that the Pawn can make
     */
    @Override
    public boolean possibleMove() {
        return false;
    }

    @Override
    public String toString() {
        return firstChar + "p";
    }
}