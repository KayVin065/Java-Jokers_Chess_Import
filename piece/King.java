/**
 * Represents a King piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;

public class King extends Piece {
    private char firstChar;

    /**
     * Initializes a King object with the desired color and position
     * @param color The color of the King ("white" or "black")
     */
    public King(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Validates whether the move inputted by the user is valid for this piece
     */
    @Override
    public boolean validMove(Board board, Spot start, Spot end) {
        if (end.getPiece() != null && end.getPiece().getColor().equals(this.getColor())) {
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return x <= 1 && y <= 1;
    }

    /**
     * Overridden toString method to configure how a King is output to the screen
     */
    @Override
    public String toString() {
        return firstChar + "K";
    }
}
