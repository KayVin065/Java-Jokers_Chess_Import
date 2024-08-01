/**
 * Represents a Rook piece in a chess game
 * Subclass of the Piece class
 */
package piece;
import board.*;

public class Rook extends Piece {
    private final char firstChar;

    /**
     * Initializes a Rook object with the desired color and position
     * @param color The color of the Rook ("white" or "black")
     */
    public Rook(String color, String unicode) {
        
        super(color, unicode);
        firstChar = color.charAt(0);
    }

    /**
     * Validates whether the move inputted by the user is a valid move for this piece
     */
    @Override
    public boolean validMove(Spot[][] board, Spot start, Spot end, Player currentTurn) {
        // Check if the destination has a piece of the same color
        if (end.getPiece() != null && start.getPiece().getColor().equals(end.getPiece().getColor())) {
            return false;
        }

        int xStart = start.getX();
        int yStart = start.getY();
        int xEnd = end.getX();
        int yEnd = end.getY();

        if (xStart != xEnd && yStart == yEnd) {
            return false;
        }

        // Check if there are any pieces in the path
        // Horizontal
        if (xStart == xEnd) {
            int min = Math.min(yStart, yEnd);
            int max = Math.max(yStart, yEnd);
            for (int i = min + 1; i < max; i++) 
            {
                if (board[xStart][i].getPiece() != null) 
                {
                    return false;
                }
            }
        } 
        // Vertical
        else {
            int min = Math.min(xStart, xEnd);
            int max = Math.max(xStart, xEnd);
            for (int i = min + 1; i < max; i++) {
                if (board[i][yStart].getPiece() != null) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Overridden toString method to configure how a Rook is output to the screen
     */
    @Override
    public String toString() {
        return " " + firstChar + "R";
    }
}