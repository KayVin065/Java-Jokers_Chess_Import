/**
 * Represents a Knight piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;

public class Knight extends Piece {

    /**
     * Initializes a Knight object with the desired color and position
     * @param color The color of the Knight ("white" or "black")
     */
    public Knight(String color, String unicode) {
        super(color, unicode);
    }

    /**
     * Validates whether the move inputted by the user is a valid move for this piece
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn) {
        if (end.getPiece() != null && end.getPiece().getColor().equals(start.getPiece().getColor())) { 
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return x * y == 2;
    }
}