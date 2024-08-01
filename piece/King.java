/**
 * Represents a King piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;

public class King extends Piece {

    /**
     * Initializes a King object with the desired color and position
     * @param color The color of the King ("white" or "black")
     */
    public King(String color, String unicode) {
        super(color, unicode);
    }

    public String getPieceUnicode()
    {
        return unicode;
    }
    /**
     * Validates whether the move inputted by the user is valid for this piece
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn) {
        if (end.getPiece() != null && end.getPiece().getColor().equals(start.getPiece().getColor())) { 
            return false;
        }

        int x = Math.abs(start.getX() - end.getX());
        int y = Math.abs(start.getY() - end.getY());

        return x <= 1 && y <= 1;
    }
}