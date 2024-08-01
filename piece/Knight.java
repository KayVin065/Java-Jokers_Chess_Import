/**
 * Represents a Knight piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;

public class Knight extends Piece {
    private char firstChar;

    /**
     * Initializes a Knight object with the desired color and position
     * @param color The color of the Knight ("white" or "black")
     */
    public Knight(String color) {
        super(color);
        firstChar = color.charAt(0);
    }

    /**
     * Validates whether the move inputted by the user is a valid move for this piece
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn) {
        if (end.getPiece() != null && !(start.getPiece().getColor().equals(currentTurn.getColor()))) { 
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        if (x * y == 2) {
            return true;
        }
        
        return false;
    }

    /**
     * Overridden toString method to configure how a Knight is output to the screen
     */
    @Override
    public String toString() {
        return " " + firstChar + "N";
    }
}